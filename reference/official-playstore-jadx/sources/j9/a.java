package j9;

import java.util.List;
import kotlin.collections.z;
import p9.n;
import v9.b0;
import v9.d1;
import v9.k0;
import v9.o0;
import v9.r0;
import v9.w;
import w9.e;
import x9.k;

/* JADX INFO: loaded from: classes5.dex */
public final class a extends b0 implements y9.c {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final r0 f19402l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final c f19403m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f19404n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final k0 f19405o;

    public a(r0 r0Var, c cVar, boolean z, k0 k0Var) {
        this.f19402l = r0Var;
        this.f19403m = cVar;
        this.f19404n = z;
        this.f19405o = k0Var;
    }

    @Override // v9.w
    public final List G0() {
        return z.f19728i;
    }

    @Override // v9.w
    public final k0 H0() {
        return this.f19405o;
    }

    @Override // v9.w
    public final o0 I0() {
        return this.f19403m;
    }

    @Override // v9.w
    public final boolean J0() {
        return this.f19404n;
    }

    @Override // v9.w
    /* JADX INFO: renamed from: K0 */
    public final w N0(e eVar) {
        return new a(this.f19402l.a(eVar), this.f19403m, this.f19404n, this.f19405o);
    }

    @Override // v9.b0, v9.d1
    public final d1 M0(boolean z) {
        if (z == this.f19404n) {
            return this;
        }
        return new a(this.f19402l, this.f19403m, z, this.f19405o);
    }

    @Override // v9.d1
    public final d1 N0(e eVar) {
        return new a(this.f19402l.a(eVar), this.f19403m, this.f19404n, this.f19405o);
    }

    @Override // v9.b0
    /* JADX INFO: renamed from: P0 */
    public final b0 M0(boolean z) {
        if (z == this.f19404n) {
            return this;
        }
        return new a(this.f19402l, this.f19403m, z, this.f19405o);
    }

    @Override // v9.b0
    /* JADX INFO: renamed from: Q0 */
    public final b0 O0(k0 k0Var) {
        return new a(this.f19402l, this.f19403m, this.f19404n, k0Var);
    }

    @Override // v9.w
    public final n l() {
        return k.a(1, true, new String[0]);
    }

    @Override // v9.b0
    public final String toString() {
        StringBuilder sb2 = new StringBuilder("Captured(");
        sb2.append(this.f19402l);
        sb2.append(')');
        sb2.append(this.f19404n ? "?" : "");
        return sb2.toString();
    }
}
