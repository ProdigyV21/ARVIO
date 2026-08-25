package j$.time.temporal;

import j$.time.LocalDate;

/* JADX INFO: loaded from: classes2.dex */
public interface Temporal extends TemporalAccessor {
    /* JADX INFO: renamed from: C */
    Temporal v(long j10, ChronoUnit chronoUnit);

    Temporal c(long j10, o oVar);

    Temporal d(long j10, q qVar);

    long g(Temporal temporal, q qVar);

    Temporal m(LocalDate localDate);
}
