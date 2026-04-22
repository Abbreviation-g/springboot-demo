package com.my.springboot.demo.config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.filter.LevelFilter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.AbstractMatcherFilter;
import ch.qos.logback.core.spi.FilterReply;
import org.slf4j.MarkerFactory;
import org.slf4j.Marker;

public class MyLogLevelFilter extends AbstractMatcherFilter<ILoggingEvent> {

    public static final Marker MY_LEVEL = MarkerFactory.getMarker("MY_LEVEL");


    Level level;

    @Override
    public FilterReply decide(ILoggingEvent event) {
        if (!isStarted()) {
            return FilterReply.NEUTRAL;
        }

        if (event.getLevel().equals(level)) {
            if (event.getMarkerList().contains(MY_LEVEL)) {
                return onMatch;
            }
        }
        return onMismatch;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public void start() {
        if (this.level != null) {
            super.start();
        }
    }
}