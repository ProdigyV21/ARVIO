package oa;

import na.h1;
import na.x0;

/* JADX INFO: loaded from: classes5.dex */
public final class z extends x0 implements h1 {
    @Override // na.h1
    public final Object getValue() {
        Integer numValueOf;
        synchronized (this) {
            numValueOf = Integer.valueOf(((Number) this.f20679r[((int) ((this.f20680s + ((long) ((int) ((n() + ((long) this.f20682u)) - this.f20680s)))) - 1)) & (r0.length - 1)]).intValue());
        }
        return numValueOf;
    }

    public final void u(int i10) {
        synchronized (this) {
            b(Integer.valueOf(((Number) this.f20679r[((int) ((this.f20680s + ((long) ((int) ((n() + ((long) this.f20682u)) - this.f20680s)))) - 1)) & (r0.length - 1)]).intValue() + i10));
        }
    }
}
