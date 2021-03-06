package org.egov.eis.indexer.model;

import org.slf4j.MDC;

public class RequestContext {

    public static String CORRELATION_ID = "X-CORRELATION-ID";

    private static final ThreadLocal<String> id = new ThreadLocal<>();

    public static String getId() {
        return id.get();
    }

    public static void setId(final String correlationId) {
        MDC.put(RequestContext.CORRELATION_ID, correlationId);
        id.set(correlationId);
    }
}
