package com.users.management.infrastructure.logging;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import ch.qos.logback.classic.Level;

public class BusinessErrorLogFilter extends Filter<ILoggingEvent> {

    private boolean acceptBusinessErrorLog = true;

    public boolean getAcceptBusinessErrorLog() {
        return this.acceptBusinessErrorLog;
    }

    public void setAcceptBusinessErrorLog(final boolean acceptBusinessErrorLog) {
        this.acceptBusinessErrorLog = acceptBusinessErrorLog;
    }

    @Override
    public FilterReply decide(final ILoggingEvent event) {
        if (!event.getLevel().equals(Level.ERROR)) {
            return FilterReply.DENY;
        }
        if (event.getMessage().contains("BUSINESS - ")) {
            return this.acceptBusinessErrorLog ? FilterReply.ACCEPT : FilterReply.DENY;
        } else {
            return this.acceptBusinessErrorLog ? FilterReply.DENY : FilterReply.ACCEPT;
        }
    }
}
