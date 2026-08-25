package j$.time.temporal;

import j$.time.format.a0;
import j$.time.format.b0;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public interface o {
    long C(TemporalAccessor temporalAccessor);

    Temporal G(Temporal temporal, long j10);

    boolean isDateBased();

    boolean l(TemporalAccessor temporalAccessor);

    s m(TemporalAccessor temporalAccessor);

    TemporalAccessor n(Map map, a0 a0Var, b0 b0Var);

    s r();
}
