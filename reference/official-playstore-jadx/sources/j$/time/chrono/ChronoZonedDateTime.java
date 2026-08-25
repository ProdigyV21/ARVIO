package j$.time.chrono;

import j$.time.Instant;
import j$.time.LocalTime;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import j$.time.chrono.ChronoLocalDate;
import j$.time.temporal.Temporal;

/* JADX INFO: loaded from: classes2.dex */
public interface ChronoZonedDateTime<D extends ChronoLocalDate> extends Temporal, Comparable<ChronoZonedDateTime<?>> {
    ChronoZonedDateTime A(ZoneId zoneId);

    ZoneId F();

    Chronology a();

    LocalTime b();

    ChronoLocalDate f();

    ZoneOffset h();

    ChronoZonedDateTime i(ZoneId zoneId);

    ChronoLocalDateTime s();

    long toEpochSecond();

    Instant toInstant();
}
