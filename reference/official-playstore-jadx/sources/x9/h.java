package x9;

import java.util.Arrays;
import java.util.List;
import p9.n;
import v9.b0;
import v9.d1;
import v9.k0;
import v9.o0;
import v9.w;

/* JADX INFO: loaded from: classes5.dex */
public final class h extends b0 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final o0 f22682l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final g f22683m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final j f22684n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final List f22685o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f22686p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final String[] f22687q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final String f22688r;

    public h(o0 o0Var, g gVar, j jVar, List list, boolean z, String... strArr) {
        this.f22682l = o0Var;
        this.f22683m = gVar;
        this.f22684n = jVar;
        this.f22685o = list;
        this.f22686p = z;
        this.f22687q = strArr;
        String str = jVar.f22704i;
        Object[] objArrCopyOf = Arrays.copyOf(strArr, strArr.length);
        this.f22688r = String.format(str, Arrays.copyOf(objArrCopyOf, objArrCopyOf.length));
    }

    @Override // v9.w
    public final List G0() {
        return this.f22685o;
    }

    @Override // v9.w
    public final k0 H0() {
        k0.f22220l.getClass();
        return k0.f22221m;
    }

    @Override // v9.w
    public final o0 I0() {
        return this.f22682l;
    }

    @Override // v9.w
    public final boolean J0() {
        return this.f22686p;
    }

    @Override // v9.b0
    /* JADX INFO: renamed from: P0 */
    public final b0 M0(boolean z) {
        String[] strArr = this.f22687q;
        return new h(this.f22682l, this.f22683m, this.f22684n, this.f22685o, z, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    @Override // v9.w
    public final n l() {
        return this.f22683m;
    }

    @Override // v9.w
    public final w K0(w9.e eVar) {
        return this;
    }

    @Override // v9.d1
    public final d1 N0(w9.e eVar) {
        return this;
    }

    @Override // v9.b0, v9.d1
    public final d1 O0(k0 k0Var) {
        return this;
    }

    @Override // v9.b0
    /* JADX INFO: renamed from: Q0 */
    public final b0 O0(k0 k0Var) {
        return this;
    }
}
