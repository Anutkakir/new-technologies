package com.ivan.newtechnologies.datetime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TimeZoneDetailsMain {

    public static void main(String[] args) {
        final LocalDateTime dt = LocalDateTime.now();
        final Set<TimeZoneDetails> timeZoneDetails = Collections.unmodifiableSet(ZoneId.getAvailableZoneIds()
                .stream()
                .map(ZoneId::of)
                .map(zoneId -> {
                    final ZoneOffset offset = dt.atZone(zoneId).getOffset();
                    return new TimeZoneDetails(zoneId, offset.getTotalSeconds() / 60);
                })
                .filter(TimeZoneDetails::isValidTimeZoneDetails)
                .collect(Collectors.toSet()));

        final Map<Integer, List<TimeZoneDetails>> integerListMap = Collections.unmodifiableMap(
                timeZoneDetails
                        .stream()
                        .collect(Collectors.groupingBy(TimeZoneDetails::getOffset, Collectors.mapping(a -> a, Collectors.toList()))));

        final List<String> collect = integerListMap.get(-120)
                .stream()
                .sorted(TimeZoneDetails.ORDERED_BY_OFFSET)
                .map(TimeZoneDetails::getName)
                .collect(Collectors.toList());

        System.out.println(collect.get(0));

    }

}
