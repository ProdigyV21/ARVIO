package e8;

import g8.b0;
import j8.d0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.x;
import kotlin.text.o;
import kotlin.text.u;
import u9.p;

/* JADX INFO: loaded from: classes5.dex */
public final class a implements i8.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final p f15043a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b0 f15044b;

    public a(p pVar, d0 d0Var) {
        this.f15043a = pVar;
        this.f15044b = d0Var;
    }

    @Override // i8.c
    public final boolean a(f9.c cVar, f9.f fVar) {
        String strD = fVar.d();
        return (u.P(strD, "Function", false) || u.P(strD, "KFunction", false) || u.P(strD, "SuspendFunction", false) || u.P(strD, "KSuspendFunction", false)) && l.f15063c.a(cVar, strD) != null;
    }

    @Override // i8.c
    public final g8.f b(f9.b bVar) {
        f9.c cVarG;
        k kVarA;
        if (bVar.f15357c || !bVar.f15356b.e().d()) {
            return null;
        }
        String strB = bVar.h().b();
        if (!o.T(strB, "Function", false) || (kVarA = l.f15063c.a((cVarG = bVar.g()), strB)) == null) {
            return null;
        }
        j jVar = kVarA.f15061a;
        int i10 = kVarA.f15062b;
        List listH0 = this.f15044b.B(cVarG).h0();
        ArrayList arrayList = new ArrayList();
        for (Object obj : listH0) {
            if (obj instanceof d8.d) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : arrayList) {
            if (obj2 instanceof d8.g) {
                arrayList2.add(obj2);
            }
        }
        d8.d dVar = (d8.g) x.o0(arrayList2);
        if (dVar == null) {
            dVar = (d8.d) x.m0(arrayList);
        }
        return new c(this.f15043a, dVar, jVar, i10);
    }

    @Override // i8.c
    public final Collection c(f9.c cVar) {
        return kotlin.collections.b0.f19686i;
    }
}
