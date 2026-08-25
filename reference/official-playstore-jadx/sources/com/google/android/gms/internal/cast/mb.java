package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class mb implements rb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ib f13478a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final k5 f13479b;

    public mb(k5 k5Var, ib ibVar) {
        k5 k5Var2 = ka.f13390a;
        this.f13479b = k5Var;
        this.f13478a = ibVar;
    }

    @Override // com.google.android.gms.internal.cast.rb
    public final boolean a(pa paVar, pa paVar2) {
        return paVar.zzc.equals(paVar2.zzc);
    }

    @Override // com.google.android.gms.internal.cast.rb
    public final int b(ba baVar) {
        c cVar = ((pa) baVar).zzc;
        int i10 = cVar.f13201c;
        if (i10 != -1) {
            return i10;
        }
        cVar.f13201c = 0;
        return 0;
    }

    @Override // com.google.android.gms.internal.cast.rb
    public final void c(Object obj, Object obj2) {
        tb.b(obj, obj2);
    }

    @Override // com.google.android.gms.internal.cast.rb
    public final int d(pa paVar) {
        paVar.zzc.getClass();
        return 506991;
    }

    @Override // com.google.android.gms.internal.cast.rb
    public final void e(Object obj, ja jaVar) {
        obj.getClass();
        throw new ClassCastException();
    }

    @Override // com.google.android.gms.internal.cast.rb
    public final boolean f(Object obj) {
        obj.getClass();
        throw new ClassCastException();
    }

    @Override // com.google.android.gms.internal.cast.rb
    public final void g(Object obj) {
        this.f13479b.getClass();
        c cVar = ((pa) obj).zzc;
        if (cVar.f13202d) {
            cVar.f13202d = false;
        }
        k5 k5Var = ka.f13390a;
        obj.getClass();
        throw new ClassCastException();
    }

    @Override // com.google.android.gms.internal.cast.rb
    public final Object zza() {
        ib ibVar = this.f13478a;
        return ibVar instanceof pa ? (pa) ((pa) ibVar).k(4, null) : ibVar.b().d();
    }
}
