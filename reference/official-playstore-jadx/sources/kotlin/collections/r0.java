package kotlin.collections;

import java.util.Iterator;
import x6.t0;

/* JADX INFO: loaded from: classes5.dex */
public final class r0 extends f7.h implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Object f19715i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Iterator f19716l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f19717m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f19718n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f19719o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public /* synthetic */ Object f19720p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f19721q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ int f19722r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Iterator f19723s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ boolean f19724t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ boolean f19725u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r0(int i10, int i11, Iterator it, boolean z, boolean z5, d7.d dVar) {
        super(2, dVar);
        this.f19721q = i10;
        this.f19722r = i11;
        this.f19723s = it;
        this.f19724t = z;
        this.f19725u = z5;
    }

    @Override // f7.a
    public final d7.d create(Object obj, d7.d dVar) {
        r0 r0Var = new r0(this.f19721q, this.f19722r, this.f19723s, this.f19724t, this.f19725u, dVar);
        r0Var.f19720p = obj;
        return r0Var;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        return ((r0) create((ga.o) obj, (d7.d) obj2)).invokeSuspend(t0.f22605a);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x015f A[SYNTHETIC] */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r21) {
        /*
            Method dump skipped, instruction units count: 413
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.r0.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
