package com.google.android.gms.internal.auth;

/* JADX INFO: loaded from: classes4.dex */
public final class b3 implements i3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final w2 f12998a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final m3 f12999b;

    public b3(m3 m3Var, z1 z1Var, w2 w2Var) {
        this.f12999b = m3Var;
        this.f12998a = w2Var;
    }

    @Override // com.google.android.gms.internal.auth.i3
    public final void a(Object obj) {
        this.f12999b.getClass();
        l3 l3Var = ((d2) obj).zzc;
        if (l3Var.f13069d) {
            l3Var.f13069d = false;
        }
        obj.getClass();
        throw new ClassCastException();
    }

    @Override // com.google.android.gms.internal.auth.i3
    public final boolean b(d2 d2Var, d2 d2Var2) {
        this.f12999b.getClass();
        return d2Var.zzc.equals(d2Var2.zzc);
    }

    @Override // com.google.android.gms.internal.auth.i3
    public final int c(d2 d2Var) {
        this.f12999b.getClass();
        return d2Var.zzc.hashCode();
    }

    @Override // com.google.android.gms.internal.auth.i3
    public final void d(Object obj, Object obj2) {
        k3.a(this.f12999b, obj, obj2);
    }

    @Override // com.google.android.gms.internal.auth.i3
    public final boolean e(Object obj) {
        obj.getClass();
        throw new ClassCastException();
    }

    @Override // com.google.android.gms.internal.auth.i3
    public final void f(Object obj, byte[] bArr, int i10, int i11, a3.d dVar) {
        d2 d2Var = (d2) obj;
        if (d2Var.zzc == l3.f13065e) {
            d2Var.zzc = l3.a();
        }
        obj.getClass();
        throw new ClassCastException();
    }

    @Override // com.google.android.gms.internal.auth.i3
    public final d2 zzd() {
        w2 w2Var = this.f12998a;
        return w2Var instanceof d2 ? ((d2) w2Var).b() : ((w3) ((d2) w2Var).h(5)).a();
    }
}
