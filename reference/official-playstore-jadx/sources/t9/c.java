package t9;

import a9.p0;
import androidx.emoji2.text.e0;
import g8.b0;
import i9.f;
import j8.f0;
import j8.t0;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.t;
import p9.n;
import s9.j;
import u9.p;

/* JADX INFO: loaded from: classes5.dex */
public final class c extends f0 implements d8.d {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final b9.a f22001q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final io.sentry.internal.debugmeta.c f22002r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final e0 f22003s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public p0 f22004t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public t f22005u;

    public c(f9.c cVar, p pVar, b0 b0Var, p0 p0Var, b9.a aVar) {
        super(b0Var, cVar);
        this.f22001q = aVar;
        io.sentry.internal.debugmeta.c cVar2 = new io.sentry.internal.debugmeta.c(12, p0Var.f618n, false, p0Var.f619o);
        this.f22002r = cVar2;
        this.f22003s = new e0(p0Var, cVar2, aVar, new s9.f0(this));
        this.f22004t = p0Var;
    }

    public final void G0(j jVar) {
        p0 p0Var = this.f22004t;
        if (p0Var == null) {
            throw new IllegalStateException("Repeated call to DeserializedPackageFragmentImpl::initialize");
        }
        this.f22004t = null;
        this.f22005u = new t(this, p0Var.f620p, this.f22002r, this.f22001q, null, jVar, "scope of " + this, new t0(this, 6));
    }

    @Override // g8.h0
    public final n l() {
        t tVar = this.f22005u;
        if (tVar != null) {
            return tVar;
        }
        kotlin.jvm.internal.p.i("_memberScope");
        throw null;
    }

    @Override // j8.f0, j8.o
    public final String toString() {
        StringBuilder sb2 = new StringBuilder("builtins package fragment for ");
        sb2.append(this.f19274o);
        sb2.append(" from ");
        int i10 = m9.d.f20302a;
        sb2.append(f.d(this));
        return sb2.toString();
    }
}
