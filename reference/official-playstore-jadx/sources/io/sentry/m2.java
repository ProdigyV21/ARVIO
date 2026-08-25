package io.sentry;

import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public interface m2 {

    public enum a implements m2 {
        NANOSECOND,
        MICROSECOND,
        MILLISECOND,
        SECOND,
        MINUTE,
        HOUR,
        DAY,
        WEEK;

        @Override // io.sentry.m2
        public String apiName() {
            return name().toLowerCase(Locale.ROOT);
        }
    }

    String apiName();
}
