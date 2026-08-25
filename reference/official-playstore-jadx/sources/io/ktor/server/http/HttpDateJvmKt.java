package io.ktor.server.http;

import j$.time.ZoneId;
import j$.time.ZonedDateTime;
import j$.time.format.DateTimeFormatter;
import j$.time.temporal.Temporal;
import java.util.Locale;
import kotlin.Metadata;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0006\u001a\u0011\u0010\u0002\u001a\u00020\u0001*\u00020\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0013\u0010\u0005\u001a\u00020\u0004*\u00020\u0001H\u0007¢\u0006\u0004\b\u0005\u0010\u0006\"\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\t\"\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"j$/time/temporal/Temporal", "", "toHttpDateString", "(Lj$/time/temporal/Temporal;)Ljava/lang/String;", "j$/time/ZonedDateTime", "fromHttpDateString", "(Ljava/lang/String;)Lj$/time/ZonedDateTime;", "j$/time/ZoneId", "GreenwichMeanTime", "Lj$/time/ZoneId;", "j$/time/format/DateTimeFormatter", "httpDateFormat", "Lj$/time/format/DateTimeFormatter;", "getHttpDateFormat", "()Lj$/time/format/DateTimeFormatter;", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HttpDateJvmKt {
    private static final ZoneId GreenwichMeanTime;
    private static final DateTimeFormatter httpDateFormat;

    static {
        ZoneId zoneIdOf = ZoneId.of("GMT");
        GreenwichMeanTime = zoneIdOf;
        httpDateFormat = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z").withLocale(Locale.US).withZone(zoneIdOf);
    }

    @e
    public static final ZonedDateTime fromHttpDateString(String str) {
        return ZonedDateTime.parse(str, httpDateFormat);
    }

    public static final DateTimeFormatter getHttpDateFormat() {
        return httpDateFormat;
    }

    public static final String toHttpDateString(Temporal temporal) {
        return httpDateFormat.format(temporal);
    }
}
