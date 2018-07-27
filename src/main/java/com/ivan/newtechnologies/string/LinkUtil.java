package com.ivan.newtechnologies.string;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LinkUtil {

    private static final int MAXIMUM_ALLOWED_SPACES = 6;

    private static final int MINIMUM_LENGTH_TO_PROCESS = 4;

    // Regular Expressions of URLs
    private static final String HTTP_PROTOCOL = "http://";
    private static final String HTTPS_PROTOCOL = "https://";
    private static final String FTP_PROTOCOL = "ftp://";

    private static final String PROTOCOL = "(https?|ftp)://";
//    private static final String DOMAIN_NAME = "[a-zA-Z0-9_/\\-.]+";
//    private static final String DOMAIN_NAME = "(?:[a-zA-Z0-9_\\-]+)(?:.[a-zA-Z0-9_\\-]+)*";

    private static final String DOMAIN_PART = "(?:[a-zA-Z0-9_]+)(?:-?[a-zA-Z0-9_]+)*";
    private static final String DOMAIN_NAME = String.format("(?:%1$s)(?:\\.%1$s)*", DOMAIN_PART);

    private static final String VALID_GTLD = "(?:(?:aero|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|xxx)(?=[^\\p{Alnum}@]|$))";
    private static final String VALID_CCTLD = "(?:(?:ac|ad|ae|af|ag|ai|al|am|an|ao|aq|ar|as|at|au|aw|ax|az|ba|bb|bd|be|bf|bg|bh|bi|bj|bm|bn|bo|br|bs|bt|"
            + "bv|bw|by|bz|ca|cc|cd|cf|cg|ch|ci|ck|cl|cm|cn|co|cr|cs|cu|cv|cx|cy|cz|dd|de|dj|dk|dm|do|dz|ec|ee|eg|eh|"
            + "er|es|et|eu|fi|fj|fk|fm|fo|fr|ga|gb|gd|ge|gf|gg|gh|gi|gl|gm|gn|gp|gq|gr|gs|gt|gu|gw|gy|hk|hm|hn|hr|ht|"
            + "hu|id|ie|il|im|in|io|iq|ir|is|it|je|jm|jo|jp|ke|kg|kh|ki|km|kn|kp|kr|kw|ky|kz|la|lb|lc|li|lk|lr|ls|lt|"
            + "lu|lv|ly|ma|mc|md|me|mg|mh|mk|ml|mm|mn|mo|mp|mq|mr|ms|mt|mu|mv|mw|mx|my|mz|na|nc|ne|nf|ng|ni|nl|no|np|"
            + "nr|nu|nz|om|pa|pe|pf|pg|ph|pk|pl|pm|pn|pr|ps|pt|pw|py|qa|re|ro|rs|ru|rw|sa|sb|sc|sd|se|sg|sh|si|sj|sk|"
            + "sl|sm|sn|so|sr|ss|st|su|sv|sx|sy|sz|tc|td|tf|tg|th|tj|tk|tl|tm|tn|to|tp|tr|tt|tv|tw|tz|ua|ug|uk|us|uy|"
            + "uz|va|vc|ve|vg|vi|vn|vu|wf|ws|ye|yt|za|zm|zw)(?=[^\\p{Alnum}@]|$))";

    private static final String TOP_LEVEL_DOMAIN = "\\.(" + LinkUtil.VALID_GTLD + "|" + LinkUtil.VALID_CCTLD + ")";
    private static final String URI_PATH_SAFE_CHARACTERS = "[a-zA-Z0-9_+#/\\?\\=\\-\\.\\~\\%]";
    private static final String URI_PATH_SAFE_CHARACTERS_ENDING = "[a-zA-Z0-9_+#/\\=\\-\\~\\%]"; // skips dot and question mark at the end of an uri
    private static final String RESOURCE_PATH_AND_PARAMETERS = "(/(?:\\&amp;|\\([a-zA-Z0-9_+\\-]*\\)|" + LinkUtil.URI_PATH_SAFE_CHARACTERS + ")*"
            + LinkUtil.URI_PATH_SAFE_CHARACTERS_ENDING + ")*";

    private static final String PORT = "(:[0-9]+)?";

    private static final String URL_BODY_WITHOUT_PROTOCOL = LinkUtil.DOMAIN_NAME + LinkUtil.TOP_LEVEL_DOMAIN + LinkUtil.PORT
            + LinkUtil.RESOURCE_PATH_AND_PARAMETERS;

    private static final String EMAIL_USER = "[-0-9a-zA-Z.+_]+";
    private static final String EMAIL_ADDRESS_GROUP = "(" + LinkUtil.EMAIL_USER + "@" + LinkUtil.DOMAIN_NAME + LinkUtil.TOP_LEVEL_DOMAIN + ")";

    private static final String FTP_PREFIX = "ftp.";
    private static final String MAILTO_PROTOCOL = "mailto:";
    private static final String LINE_END_OR_WHITESPACE_GROUP = "($|\\s)";
    private static final String LINE_START_OR_WHITESPACE_GROUP = "(^|\\s)";

    // HTML replacements
    private static final String A_CLOSING = "</a>";
    private static final String A_HREF = "<a href='";
    private static final String TARGET_BLANK = " target='_blank'";
    private static final String BR = "<br/>";
    private static final String NON_BREAKING_SPACE = "&nbsp;";

    // Patterns for HTML replacements

    private static final Pattern SPACES_AT_LINE_START_PATTERN = Pattern.compile("(<br/>)([ ]+)");

    private static final String LINE_ENDINGS_GROUP = "(\r\n|\n\r|\r|\n)";

    private static final String DEFAULT_LINE_END = "\n";

    private static final String SLASH = "/";
    private static final String APOSTROPHE = "'";
    private static final String TAG_END = ">";

    // For brackets, braces, parenthesis, etc around the link
    private static final String HTML_SPECIAL_CHARACTER = "\\&(?:\\#[0-9]+|[a-zA-Z]+)\\;";
    private static final String ZERO_OR_MORE_NON_WORD_CHARACTERS = "(?:" + LinkUtil.HTML_SPECIAL_CHARACTER + "|\\W)*+";

    private static final Cache<WordCacheKey, String> WORD_CACHE = CacheBuilder.newBuilder().maximumSize(1000L * 100L).build();

    private static final Pattern MISSING_PROTOCOL_FIXING_PATTERN = Pattern
            .compile(LinkUtil.LINE_START_OR_WHITESPACE_GROUP + "(" + LinkUtil.URL_BODY_WITHOUT_PROTOCOL + ")" + LinkUtil.LINE_END_OR_WHITESPACE_GROUP);

    private static final Pattern ADD_MISSING_PROTOCOL_FIXING_PATTERN = Pattern
            .compile(LinkUtil.LINE_START_OR_WHITESPACE_GROUP + LinkUtil.ZERO_OR_MORE_NON_WORD_CHARACTERS + "(" + LinkUtil.URL_BODY_WITHOUT_PROTOCOL + ")"
                    + LinkUtil.ZERO_OR_MORE_NON_WORD_CHARACTERS + LinkUtil.LINE_END_OR_WHITESPACE_GROUP);

    private static final Pattern WEBLINK_WITH_PROTOCOL_PATTERN = Pattern.compile(LinkUtil.ZERO_OR_MORE_NON_WORD_CHARACTERS + "(" + LinkUtil.PROTOCOL
            + LinkUtil.URL_BODY_WITHOUT_PROTOCOL + ")" + LinkUtil.ZERO_OR_MORE_NON_WORD_CHARACTERS + LinkUtil.LINE_END_OR_WHITESPACE_GROUP);

    private static final Pattern EMAIL_PATTERN = Pattern.compile(LinkUtil.LINE_START_OR_WHITESPACE_GROUP + LinkUtil.ZERO_OR_MORE_NON_WORD_CHARACTERS
            + LinkUtil.EMAIL_ADDRESS_GROUP + LinkUtil.ZERO_OR_MORE_NON_WORD_CHARACTERS);

    private static final Pattern EMAIL_WITH_MAILTO_PATTERN = Pattern.compile(LinkUtil.ZERO_OR_MORE_NON_WORD_CHARACTERS + "(" + LinkUtil.MAILTO_PROTOCOL + ")"
            + LinkUtil.EMAIL_ADDRESS_GROUP + LinkUtil.ZERO_OR_MORE_NON_WORD_CHARACTERS);

    private static final Pattern USER_PROFILE_URL_PATTERN = Pattern.compile("(.*)(/profiles/.+/(?:show|edit)/?)(.*)");

    private LinkUtil() {
        // Utility class...
    }

    public static String activeLinks(final String original, final String siteUrl) {
        return LinkUtil.doActiveLinks(original, true, siteUrl);
    }

    public static boolean isURLWithoutProtocol(final String aPossibleUrl) {
        if (StringUtils.isBlank(aPossibleUrl)) {
            return false;
        }

        return LinkUtil.MISSING_PROTOCOL_FIXING_PATTERN.matcher(aPossibleUrl).matches();
    }

    public static String replaceLineEndsWithBreaks(final String text) {
        return text.replaceAll(LinkUtil.LINE_ENDINGS_GROUP, LinkUtil.BR);
    }

    /**
     * Checks if the given link is an external link or not.
     *
     * @param link    The link, which is checked if it's external.
     * @param siteUrl The URL of the site
     * @return Returns true if the given link is external.
     */
    public static boolean isExternalLink(final String link, final String siteUrl) {
        if (StringUtils.isBlank(link)) {
            return false;
        }

        final String preparedSiteUrl = LinkUtil.prepareSiteUrl(siteUrl);

        boolean isExternal = !link.toLowerCase().contains(preparedSiteUrl);
        // if the given link contains the SITE_URL, so seems to be internal, then check if the SITE_URL is in the query string.
        if (!isExternal) {
            final String linkWithoutProtocol = LinkUtil.removeProtocol(link.toLowerCase());
            final int slashIndex = linkWithoutProtocol.indexOf(LinkUtil.SLASH);
            if (slashIndex != -1 && linkWithoutProtocol.indexOf(preparedSiteUrl) > slashIndex) {
                isExternal = true;
            }
        }
        return isExternal;
    }

    private static String doActiveLinks(final String original, final boolean isEscaped, final String siteUrl) {

        if (StringUtils.isEmpty(original)) {
            return original;
        }

        final String[] lines = StringUtils.splitByWholeSeparatorPreserveAllTokens(original, LinkUtil.DEFAULT_LINE_END);

        // @formatter:off
        String text = LinkUtil.replaceLineEndsWithBreaks(Arrays.stream(lines)
            .map(line -> StringUtils.splitByWholeSeparatorPreserveAllTokens(line, StringUtils.SPACE))
            .map(words -> Arrays.stream(words)
                    .map(word -> processWord(isEscaped, siteUrl, word))
                    .collect(Collectors.joining(StringUtils.SPACE)))
            .collect(Collectors.joining(LinkUtil.DEFAULT_LINE_END)));
        // @formatter:on

        final Matcher spacesAtLineStartMatcher = SPACES_AT_LINE_START_PATTERN.matcher(text);

        while (spacesAtLineStartMatcher.find()) {
            final int length = Math.min(spacesAtLineStartMatcher.group(2).length(), LinkUtil.MAXIMUM_ALLOWED_SPACES);
            final StringBuilder replacementBuilder = new StringBuilder(LinkUtil.BR).append(StringUtils.repeat(LinkUtil.NON_BREAKING_SPACE, length));
            text = text.replace(spacesAtLineStartMatcher.group(), replacementBuilder.toString());
        }

        return text;
    }

    private static String processWord(final boolean isEscaped, final String siteUrl, final String aSingleWord) {

        if (aSingleWord.length() > LinkUtil.MINIMUM_LENGTH_TO_PROCESS) {
            return LinkUtil.activeLinksInWordCached(new WordCacheKey(aSingleWord, isEscaped), siteUrl);
        } else if (isEscaped) {
            return StringEscapeUtils.escapeXml(aSingleWord);
        }

        return aSingleWord;
    }

    private static String activeLinksInWordCached(final WordCacheKey wordVo, final String siteUrl) {

        try {
            return LinkUtil.WORD_CACHE.get(wordVo, () -> LinkUtil.activeLinksInWord(wordVo, siteUrl));
        } catch (final ExecutionException e) {
            return LinkUtil.activeLinksInWord(wordVo, siteUrl);
        }

    }

    private static String activeLinksInWord(final WordCacheKey wordVo, final String siteUrl) {

        String word = wordVo.getWord();

        word = word.replace("&amp;", "&");

        if (wordVo.getEscaped()) {
            word = StringEscapeUtils.escapeXml(word);
        }

//        word = LinkUtil.addMissingProtocols(word);

        word = LinkUtil.convertWebLinksToLinks(word, siteUrl);

        word = LinkUtil.addMissingMailToProtocols(word);

        word = LinkUtil.convertEmailAddressesToLinks(word);

        word = LinkUtil.convertRelativeProfileUrlToLinks(word, siteUrl);

        return word;
    }

    private static String convertRelativeProfileUrlToLinks(final String word, final String siteUrl) {
        final Matcher matcher = USER_PROFILE_URL_PATTERN.matcher(word);
        if (matcher.find()) {

            final String userProfileUrl = matcher.group(2);
            //@formatter:off
            final String wrappedWord = new StringBuilder(LinkUtil.A_HREF)
                    .append(addMissingProtocols(prepareSiteUrl(siteUrl)) + userProfileUrl)
                    .append(LinkUtil.APOSTROPHE)
                    .append(LinkUtil.TARGET_BLANK)
                    .append(LinkUtil.TAG_END)
                    .append(userProfileUrl)
                    .append(LinkUtil.A_CLOSING).toString();
            //@formatter:on

            return  matcher.group(1) + wrappedWord +  matcher.group(3);
        }

        return word;
    }

    private static String convertEmailAddressesToLinks(String word) {

        final Matcher emailWithMailtoMatcher = LinkUtil.EMAIL_WITH_MAILTO_PATTERN.matcher(word);
        while (emailWithMailtoMatcher.find()) {
            final String mailto = emailWithMailtoMatcher.group(1);
            final String email = emailWithMailtoMatcher.group(2);
            final String link = mailto + email;
            word = word.replace(link, LinkUtil.A_HREF + mailto.trim() + email.trim() + LinkUtil.APOSTROPHE + LinkUtil.TARGET_BLANK + LinkUtil.TAG_END
                    + email.trim() + LinkUtil.A_CLOSING);
        }
        return word;
    }

    private static String addMissingMailToProtocols(String word) {

        final Matcher emailMatcher = LinkUtil.EMAIL_PATTERN.matcher(word);
        while (emailMatcher.find()) {
            if (!emailMatcher.group(2).startsWith(LinkUtil.MAILTO_PROTOCOL)) {
                word = word.replace(emailMatcher.group(2), LinkUtil.MAILTO_PROTOCOL + emailMatcher.group(2));
            }
        }

        return word;
    }

    private static String convertWebLinksToLinks(final String word, final String siteUrl) {

        String wordEnd = StringUtils.EMPTY;
        String wordBeforeClosingQuote = word;
        if (word.indexOf("&quot;&gt;") != -1) {
            wordEnd = word.substring(word.indexOf("&quot;&gt;"));
            wordBeforeClosingQuote = word.substring(0, word.indexOf("&quot;&gt;"));
        }

        final Matcher webLinkMatcher = LinkUtil.WEBLINK_WITH_PROTOCOL_PATTERN.matcher(wordBeforeClosingQuote);
        boolean found = false;
        while (webLinkMatcher.find()) {
            final String link = webLinkMatcher.group(1).trim();
            final String targetAttribute = LinkUtil.isExternalLink(link, siteUrl) ? LinkUtil.TARGET_BLANK : StringUtils.EMPTY;

            //@formatter:off
            final String wrappedWord = new StringBuilder(LinkUtil.A_HREF)
                    .append(link)
                    .append(LinkUtil.APOSTROPHE)
                    .append(targetAttribute)
                    .append(LinkUtil.TAG_END)
                    .append(StringEscapeUtils.unescapeXml(webLinkMatcher.group(1).trim()))
                    .append(LinkUtil.A_CLOSING + wordEnd).toString();
            //@formatter:on
            wordBeforeClosingQuote = wordBeforeClosingQuote.replace(webLinkMatcher.group(1), wrappedWord);
            found = true;
        }
        if (found) {
            return wordBeforeClosingQuote;
        } else {
            return word;
        }
    }

    private static String prepareSiteUrl(final String rawSiteUrl) {
        String siteUrl = rawSiteUrl.toLowerCase();

        // remove trailing slash
        siteUrl = StringUtils.removeEnd(siteUrl, LinkUtil.SLASH);

        // remove protocol
        if (!LinkUtil.isURLWithoutProtocol(siteUrl)) {
            siteUrl = LinkUtil.removeProtocol(siteUrl);
        }

        return siteUrl;
    }

    private static String removeProtocol(final String link) {
        String withoutProtocol = link.replace(LinkUtil.HTTP_PROTOCOL, StringUtils.EMPTY);
        withoutProtocol = withoutProtocol.replace(LinkUtil.HTTPS_PROTOCOL, StringUtils.EMPTY);
        withoutProtocol = withoutProtocol.replace(LinkUtil.FTP_PROTOCOL, StringUtils.EMPTY);
        return withoutProtocol;
    }

    private static String addMissingProtocols(String word) {

        final Matcher missingProtocolMatcher = LinkUtil.ADD_MISSING_PROTOCOL_FIXING_PATTERN.matcher(word);
        while (missingProtocolMatcher.find()) {
            if (missingProtocolMatcher.group(2).startsWith(LinkUtil.FTP_PREFIX)) {
                word = word.replace(missingProtocolMatcher.group(2), LinkUtil.FTP_PROTOCOL + missingProtocolMatcher.group(2));
            } else {
                word = word.replace(missingProtocolMatcher.group(2), LinkUtil.HTTP_PROTOCOL + missingProtocolMatcher.group(2));
            }
        }
        return word;
    }

    public static class WordCacheKey implements Serializable {

        private static final long serialVersionUID = 1L;

        private String word;

        private Boolean escaped;

        public WordCacheKey() {
            super();
        }

        public WordCacheKey(final String word, final Boolean escaped) {
            this.word = word;
            this.escaped = escaped;
        }

        public String getWord() {
            return this.word;
        }

        public void setWord(final String word) {
            this.word = word;
        }

        public Boolean getEscaped() {
            return this.escaped;
        }

        public void setEscaped(final Boolean escaped) {
            this.escaped = escaped;
        }

        @Override
        public int hashCode() {
            int hash = 1;
            hash = hash * 17 + this.word.hashCode();
            hash = hash * 31 + this.escaped.hashCode();
            return hash;
        }

        @Override
        public boolean equals(final Object obj) {
            if (obj == null) {
                return false;
            }
            if (this.getClass() != obj.getClass()) {
                return false;
            }
            final WordCacheKey other = (WordCacheKey) obj;

            if (this.word == null ? other.word != null : !this.word.equals(other.word)) {
                return false;
            }
            if (this.escaped == null ? other.escaped != null : !this.escaped.equals(other.escaped)) {
                return false;
            }
            return true;
        }

    }

}
