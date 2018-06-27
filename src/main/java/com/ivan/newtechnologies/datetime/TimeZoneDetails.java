package com.ivan.newtechnologies.datetime;

import com.google.common.collect.ComparisonChain;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.TextStyle;
import java.util.Comparator;
import java.util.Locale;
import java.util.TimeZone;

public class TimeZoneDetails {

    // @formatter:off
    public static final Comparator<TimeZoneDetails> ORDERED_BY_OFFSET = (o1, o2) -> ComparisonChain.start()
            .compare(o1.getOffset(), o2.getOffset())
            .compare(o2.getContinent(), o1.getContinent())
            .compare(o2.getCity(), o1.getCity())
            .result();
    // @formatter:on

    private final Integer offset;

    private final ZoneId zoneId;

    private String continent;

    private String city;

    private final String[] timeZoneParts;

    public TimeZoneDetails(final TimeZone timeZone) {
        this(timeZone.toZoneId(), timeZone.getOffset(System.currentTimeMillis()));
    }

    public TimeZoneDetails(final ZoneId zoneId, final int offset) {
        this.zoneId = zoneId;
        this.offset = offset;

        this.timeZoneParts = this.getTimeZoneParts();

        if (ArrayUtils.getLength(this.timeZoneParts) > 0) {
            this.continent = this.timeZoneParts[0];
        }

        if (ArrayUtils.getLength(this.timeZoneParts) > 1) {
            this.city = this.timeZoneParts[1];
        }

    }

    public String getContinent() {
        return this.continent;
    }

    public String getCity() {
        return this.city;
    }

    public String getName() {
        return this.zoneId.getId();
    }

    public String getDisplayName(final TextStyle textStyle, final Locale locale) {
        return this.zoneId.getDisplayName(textStyle, locale);
    }

    public String getOffsetAsString() {
        final ZoneOffset offset = LocalDateTime.now().atZone(this.zoneId).getOffset();
        return offset.getTotalSeconds() == 0 ? "+00:00" : offset.getId();
    }

    public String getLocalizationId() {
        return this.continent.toLowerCase() + "." + this.city.toLowerCase();
    }

    public Integer getOffset() {
        return this.offset;
    }

    public boolean isValidTimeZoneDetails() {
        if (StringUtils.isBlank(this.getName())) {
            return false;
        }

        if (ArrayUtils.getLength(this.timeZoneParts) != 2) {
            return false;
        }

        if ("etc".equalsIgnoreCase(this.timeZoneParts[0]) || "systemv".equalsIgnoreCase(this.timeZoneParts[0])) {
            return false;
        }

        return true;
    }

    private String[] getTimeZoneParts() {
        return this.getName().split("/");
    }

}