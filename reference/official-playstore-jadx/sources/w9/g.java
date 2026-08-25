package w9;

import a8.l0;
import java.util.List;
import kotlin.collections.z;
import v9.b0;
import v9.d1;
import v9.k0;
import v9.o0;
import v9.r0;

/* JADX INFO: loaded from: classes5.dex */
public final class g extends b0 implements y9.c {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f22510l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final i f22511m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final d1 f22512n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final k0 f22513o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f22514p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final boolean f22515q;

    public g(int i10, i iVar, d1 d1Var, k0 k0Var, boolean z, boolean z5) {
        this.f22510l = i10;
        this.f22511m = iVar;
        this.f22512n = d1Var;
        this.f22513o = k0Var;
        this.f22514p = z;
        this.f22515q = z5;
    }

    @Override // v9.w
    public final List G0() {
        return z.f19728i;
    }

    @Override // v9.w
    public final k0 H0() {
        return this.f22513o;
    }

    @Override // v9.w
    public final o0 I0() {
        return this.f22511m;
    }

    @Override // v9.w
    public final boolean J0() {
        return this.f22514p;
    }

    @Override // v9.b0, v9.d1
    public final d1 M0(boolean z) {
        return new g(this.f22510l, this.f22511m, this.f22512n, this.f22513o, z, 32);
    }

    @Override // v9.b0
    /* JADX INFO: renamed from: P0 */
    public final b0 M0(boolean z) {
        return new g(this.f22510l, this.f22511m, this.f22512n, this.f22513o, z, 32);
    }

    @Override // v9.b0
    /* JADX INFO: renamed from: Q0 */
    public final b0 O0(k0 k0Var) {
        return new g(this.f22510l, this.f22511m, this.f22512n, k0Var, this.f22514p, this.f22515q);
    }

    @Override // v9.d1
    /* JADX INFO: renamed from: R0, reason: merged with bridge method [inline-methods] */
    public final g N0(e eVar) {
        i iVar = this.f22511m;
        r0 r0VarA = iVar.f22518a.a(eVar);
        l0 l0Var = iVar.f22519b != null ? new l0(iVar, eVar, 25) : null;
        i iVar2 = iVar.f22520c;
        if (iVar2 == null) {
            iVar2 = iVar;
        }
        i iVar3 = new i(r0VarA, l0Var, iVar2, iVar.f22521d);
        d1 d1Var = this.f22512n;
        return new g(this.f22510l, iVar3, d1Var != null ? d1Var : null, this.f22513o, this.f22514p, 32);
    }

    @Override // v9.w
    public final p9.n l() {
        return x9.k.a(1, true, new String[0]);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public g(int i10, i iVar, d1 d1Var, k0 k0Var, boolean z, int i11) {
        if ((i11 & 8) != 0) {
            k0.f22220l.getClass();
            k0Var = k0.f22221m;
        }
        this(i10, iVar, d1Var, k0Var, (i11 & 16) != 0 ? false : z, false);
    }
}
