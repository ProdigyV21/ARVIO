package io.sentry.clientreport;

/* JADX INFO: loaded from: classes5.dex */
public enum d {
    QUEUE_OVERFLOW("queue_overflow"),
    CACHE_OVERFLOW("cache_overflow"),
    RATELIMIT_BACKOFF("ratelimit_backoff"),
    NETWORK_ERROR("network_error"),
    SEND_ERROR("send_error"),
    SAMPLE_RATE("sample_rate"),
    BEFORE_SEND("before_send"),
    EVENT_PROCESSOR("event_processor"),
    BACKPRESSURE("backpressure");

    private final String reason;

    d(String str) {
        this.reason = str;
    }

    public String getReason() {
        return this.reason;
    }
}
