package j$.time.chrono;

import j$.time.LocalTime;
import j$.time.temporal.Temporal;

/* JADX INFO: loaded from: classes2.dex */
public interface ChronoLocalDate extends Temporal, j$.time.temporal.l, Comparable<ChronoLocalDate> {
    /* JADX INFO: renamed from: B */
    ChronoLocalDate m(j$.time.temporal.l lVar);

    long I();

    ChronoLocalDateTime J(LocalTime localTime);

    j L();

    ChronoLocalDate O(j$.time.temporal.n nVar);

    int R();

    /* JADX INFO: renamed from: S */
    int compareTo(ChronoLocalDate chronoLocalDate);

    Chronology a();

    @Override // j$.time.temporal.Temporal
    ChronoLocalDate c(long j10, j$.time.temporal.o oVar);

    @Override // j$.time.temporal.Temporal
    ChronoLocalDate d(long j10, j$.time.temporal.q qVar);

    @Override // j$.time.temporal.TemporalAccessor
    boolean e(j$.time.temporal.o oVar);

    boolean equals(Object obj);

    @Override // j$.time.temporal.Temporal
    long g(Temporal temporal, j$.time.temporal.q qVar);

    int hashCode();

    boolean t();

    String toString();

    ChronoLocalDate v(long j10, j$.time.temporal.q qVar);
}
