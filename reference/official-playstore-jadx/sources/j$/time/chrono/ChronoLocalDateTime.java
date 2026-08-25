package j$.time.chrono;

import j$.time.LocalTime;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import j$.time.chrono.ChronoLocalDate;
import j$.time.temporal.Temporal;

/* JADX INFO: loaded from: classes2.dex */
public interface ChronoLocalDateTime<D extends ChronoLocalDate> extends Temporal, j$.time.temporal.l, Comparable<ChronoLocalDateTime<?>> {
    ChronoZonedDateTime D(ZoneId zoneId);

    /* JADX INFO: renamed from: M */
    int compareTo(ChronoLocalDateTime chronoLocalDateTime);

    Chronology a();

    LocalTime b();

    ChronoLocalDate f();

    long toEpochSecond(ZoneOffset zoneOffset);
}
