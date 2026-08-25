package f8;

import com.google.common.util.concurrent.p0;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import kotlin.text.u;

/* JADX INFO: loaded from: classes5.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f15297a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f15298b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f15299c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String f15300d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final f9.b f15301e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final f9.c f15302f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final f9.b f15303g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final HashMap f15304h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final HashMap f15305i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final HashMap f15306j;
    public static final HashMap k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final HashMap f15307l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final HashMap f15308m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final List f15309n;

    static {
        StringBuilder sb2 = new StringBuilder();
        e8.f fVar = e8.f.f15055c;
        sb2.append(fVar.f15059a.f15359a.toString());
        sb2.append('.');
        sb2.append(fVar.f15060b);
        f15297a = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        e8.g gVar = e8.g.f15056c;
        sb3.append(gVar.f15059a.f15359a.toString());
        sb3.append('.');
        sb3.append(gVar.f15060b);
        f15298b = sb3.toString();
        StringBuilder sb4 = new StringBuilder();
        e8.i iVar = e8.i.f15058c;
        sb4.append(iVar.f15059a.f15359a.toString());
        sb4.append('.');
        sb4.append(iVar.f15060b);
        f15299c = sb4.toString();
        StringBuilder sb5 = new StringBuilder();
        e8.h hVar = e8.h.f15057c;
        sb5.append(hVar.f15059a.f15359a.toString());
        sb5.append('.');
        sb5.append(hVar.f15060b);
        f15300d = sb5.toString();
        f9.b bVarJ = f9.b.j(new f9.c("kotlin.jvm.functions.FunctionN"));
        f15301e = bVarJ;
        f15302f = bVarJ.b();
        f15303g = f9.i.f15392o;
        c(Class.class);
        f15304h = new HashMap();
        f15305i = new HashMap();
        f15306j = new HashMap();
        k = new HashMap();
        f15307l = new HashMap();
        f15308m = new HashMap();
        f9.b bVarJ2 = f9.b.j(d8.p.A);
        c cVar = new c(c(Iterable.class), bVarJ2, new f9.b(bVarJ2.g(), p0.D(d8.p.I, bVarJ2.g()), false));
        f9.b bVarJ3 = f9.b.j(d8.p.z);
        c cVar2 = new c(c(Iterator.class), bVarJ3, new f9.b(bVarJ3.g(), p0.D(d8.p.H, bVarJ3.g()), false));
        f9.b bVarJ4 = f9.b.j(d8.p.B);
        c cVar3 = new c(c(Collection.class), bVarJ4, new f9.b(bVarJ4.g(), p0.D(d8.p.J, bVarJ4.g()), false));
        f9.b bVarJ5 = f9.b.j(d8.p.C);
        c cVar4 = new c(c(List.class), bVarJ5, new f9.b(bVarJ5.g(), p0.D(d8.p.K, bVarJ5.g()), false));
        f9.b bVarJ6 = f9.b.j(d8.p.E);
        c cVar5 = new c(c(Set.class), bVarJ6, new f9.b(bVarJ6.g(), p0.D(d8.p.M, bVarJ6.g()), false));
        f9.b bVarJ7 = f9.b.j(d8.p.D);
        c cVar6 = new c(c(ListIterator.class), bVarJ7, new f9.b(bVarJ7.g(), p0.D(d8.p.L, bVarJ7.g()), false));
        f9.c cVar7 = d8.p.F;
        f9.b bVarJ8 = f9.b.j(cVar7);
        c cVar8 = new c(c(Map.class), bVarJ8, new f9.b(bVarJ8.g(), p0.D(d8.p.N, bVarJ8.g()), false));
        f9.b bVarD = f9.b.j(cVar7).d(d8.p.G.f());
        List<c> listE = t7.a.E(cVar, cVar2, cVar3, cVar4, cVar5, cVar6, cVar8, new c(c(Map.Entry.class), bVarD, new f9.b(bVarD.g(), p0.D(d8.p.O, bVarD.g()), false)));
        f15309n = listE;
        b(Object.class, d8.p.f14732a);
        b(String.class, d8.p.f14738f);
        b(CharSequence.class, d8.p.f14737e);
        a(c(Throwable.class), f9.b.j(d8.p.k));
        b(Cloneable.class, d8.p.f14735c);
        b(Number.class, d8.p.f14741i);
        a(c(Comparable.class), f9.b.j(d8.p.f14743l));
        b(Enum.class, d8.p.f14742j);
        a(c(Annotation.class), f9.b.j(d8.p.f14750s));
        for (c cVar9 : listE) {
            f9.b bVar = cVar9.f15294a;
            f9.b bVar2 = cVar9.f15295b;
            f9.b bVar3 = cVar9.f15296c;
            a(bVar, bVar2);
            f15305i.put(bVar3.b().i(), bVar);
            f15307l.put(bVar3, bVar2);
            f15308m.put(bVar2, bVar3);
            f9.c cVarB = bVar2.b();
            f9.c cVarB2 = bVar3.b();
            f15306j.put(bVar3.b().i(), cVarB);
            k.put(cVarB.i(), cVarB2);
        }
        for (n9.c cVar10 : n9.c.values()) {
            a(f9.b.j(cVar10.j()), f9.b.j(d8.q.f14766l.c(cVar10.i().f14721i)));
        }
        for (f9.b bVar4 : d8.e.f14697a) {
            a(f9.b.j(new f9.c("kotlin.jvm.internal." + bVar4.i().d() + "CompanionObject")), bVar4.d(f9.h.f15373b));
        }
        for (int i10 = 0; i10 < 23; i10++) {
            a(f9.b.j(new f9.c(a0.c.i(i10, "kotlin.jvm.functions.Function"))), new f9.b(d8.q.f14766l, f9.f.j("Function" + i10)));
            f15305i.put(new f9.c(androidx.compose.foundation.c.p(i10, f15298b, new StringBuilder())).i(), f15303g);
        }
        for (int i11 = 0; i11 < 22; i11++) {
            e8.h hVar2 = e8.h.f15057c;
            f15305i.put(new f9.c((hVar2.f15059a.f15359a.toString() + '.' + hVar2.f15060b) + i11).i(), f15303g);
        }
        f15305i.put(d8.p.f14734b.g().i(), c(Void.class));
    }

    public static void a(f9.b bVar, f9.b bVar2) {
        f15304h.put(bVar.b().i(), bVar2);
        f15305i.put(bVar2.b().i(), bVar);
    }

    public static void b(Class cls, f9.e eVar) {
        a(c(cls), f9.b.j(eVar.g()));
    }

    public static f9.b c(Class cls) {
        if (!cls.isPrimitive()) {
            cls.isArray();
        }
        Class<?> declaringClass = cls.getDeclaringClass();
        return declaringClass == null ? f9.b.j(new f9.c(cls.getCanonicalName())) : c(declaringClass).d(f9.f.j(cls.getSimpleName()));
    }

    public static boolean d(f9.e eVar, String str) {
        Integer numR;
        String str2 = eVar.f15364a;
        if (str2 != null) {
            String strD0 = kotlin.text.o.D0(str2, str, "");
            return strD0.length() > 0 && !kotlin.text.o.A0(strD0, '0') && (numR = u.R(strD0)) != null && numR.intValue() >= 23;
        }
        f9.e.a(4);
        throw null;
    }

    public static f9.b e(f9.e eVar) {
        return (d(eVar, f15297a) || d(eVar, f15299c)) ? f15301e : (d(eVar, f15298b) || d(eVar, f15300d)) ? f15303g : (f9.b) f15305i.get(eVar);
    }
}
