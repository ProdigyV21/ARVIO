package io.ktor.server.util;

import io.ktor.util.InternalAPI;
import io.ktor.util.date.DateJvmKt;
import io.ktor.util.date.GMTDate;
import j$.time.Instant;
import j$.time.LocalDateTime;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import j$.time.ZonedDateTime;
import j$.util.DateRetargetClass;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\b\u001a\u0011\u0010\u0002\u001a\u00020\u0001*\u00020\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0011\u0010\u0002\u001a\u00020\u0001*\u00020\u0004¢\u0006\u0004\b\u0002\u0010\u0005\u001a\u0013\u0010\b\u001a\u00020\u0007*\u00020\u0006H\u0007¢\u0006\u0004\b\b\u0010\t\u001a\u0013\u0010\n\u001a\u00020\u0004*\u00020\u0006H\u0007¢\u0006\u0004\b\n\u0010\u000b\" \u0010\r\u001a\u00020\f8\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"j$/time/Instant", "Lio/ktor/util/date/GMTDate;", "toGMTDate", "(Lj$/time/Instant;)Lio/ktor/util/date/GMTDate;", "j$/time/ZonedDateTime", "(Lj$/time/ZonedDateTime;)Lio/ktor/util/date/GMTDate;", "Ljava/util/Date;", "j$/time/LocalDateTime", "toLocalDateTime", "(Ljava/util/Date;)Lj$/time/LocalDateTime;", "toZonedDateTime", "(Ljava/util/Date;)Lj$/time/ZonedDateTime;", "j$/time/ZoneId", "GreenwichMeanTime", "Lj$/time/ZoneId;", "getGreenwichMeanTime", "()Lj$/time/ZoneId;", "getGreenwichMeanTime$annotations", "()V", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DateUtilsJvmKt {
    private static final ZoneId GreenwichMeanTime = ZoneId.of("GMT");

    public static final ZoneId getGreenwichMeanTime() {
        return GreenwichMeanTime;
    }

    @InternalAPI
    public static /* synthetic */ void getGreenwichMeanTime$annotations() {
    }

    public static final GMTDate toGMTDate(Instant instant) {
        return DateJvmKt.GMTDate(Long.valueOf(TimeUnit.SECONDS.toMillis(instant.atZone(ZoneOffset.UTC).toEpochSecond())));
    }

    @InternalAPI
    public static final LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(DateRetargetClass.toInstant(date), ZoneId.systemDefault());
    }

    @InternalAPI
    public static final ZonedDateTime toZonedDateTime(Date date) {
        return ZonedDateTime.ofInstant(DateRetargetClass.toInstant(date), GreenwichMeanTime);
    }

    public static final GMTDate toGMTDate(ZonedDateTime zonedDateTime) {
        return toGMTDate(zonedDateTime.toInstant());
    }
}
