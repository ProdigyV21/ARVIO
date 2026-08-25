package androidx.emoji2.text;

import android.os.Build;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class f extends m2.f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ g f2604a;

    public f(g gVar) {
        this.f2604a = gVar;
    }

    @Override // m2.f0
    public final void D(Throwable th) {
        this.f2604a.f2607a.e(th);
    }

    @Override // m2.f0
    public final void E(e0 e0Var) {
        g gVar = this.f2604a;
        gVar.f2609c = e0Var;
        e0 e0Var2 = gVar.f2609c;
        o oVar = gVar.f2607a;
        gVar.f2608b = new z(e0Var2, oVar.f2633g, oVar.f2635i, Build.VERSION.SDK_INT >= 34 ? t.a() : qb.d.D());
        o oVar2 = gVar.f2607a;
        oVar2.getClass();
        ArrayList arrayList = new ArrayList();
        oVar2.f2627a.writeLock().lock();
        try {
            oVar2.f2629c = 1;
            arrayList.addAll(oVar2.f2628b);
            oVar2.f2628b.clear();
            oVar2.f2627a.writeLock().unlock();
            oVar2.f2630d.post(new android.support.v4.os.e(arrayList, oVar2.f2629c, null));
        } catch (Throwable th) {
            oVar2.f2627a.writeLock().unlock();
            throw th;
        }
    }
}
