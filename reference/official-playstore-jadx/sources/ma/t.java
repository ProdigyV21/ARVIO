package ma;

import ka.h0;

/* JADX INFO: loaded from: classes5.dex */
public final class t extends k implements u {
    @Override // ka.a
    public final void P(Throwable th, boolean z) {
        if (this.f20344n.g(th, false) || z) {
            return;
        }
        h0.a(this.f19541m, th);
    }

    @Override // ka.a
    public final void Q(Object obj) {
        this.f20344n.close(null);
    }

    @Override // ma.u
    public final t getChannel() {
        return this;
    }
}
