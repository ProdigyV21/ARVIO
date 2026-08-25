package f9;

import io.sentry.protocol.DebugImage;
import java.util.LinkedHashMap;
import java.util.Set;
import kotlin.collections.i0;
import kotlin.collections.q0;
import kotlin.collections.r;
import kotlin.collections.s;

/* JADX INFO: loaded from: classes5.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c f15379a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c f15380b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c f15381c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final c f15382d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final c f15383e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final c f15384f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final c f15385g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final Set f15386h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final b f15387i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final b f15388j;
    public static final b k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final b f15389l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final b f15390m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final b f15391n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final b f15392o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final Set f15393p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final Set f15394q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final b f15395r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final b f15396s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final b f15397t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final b f15398u;

    static {
        c cVar = new c("kotlin");
        f15379a = cVar;
        c cVarC = cVar.c(f.j("reflect"));
        f15380b = cVarC;
        c cVarC2 = cVar.c(f.j("collections"));
        f15381c = cVarC2;
        c cVarC3 = cVar.c(f.j("ranges"));
        f15382d = cVarC3;
        cVar.c(f.j(DebugImage.JVM)).c(f.j("internal"));
        c cVarC4 = cVar.c(f.j("annotation"));
        f15383e = cVarC4;
        c cVarC5 = cVar.c(f.j("internal"));
        cVarC5.c(f.j("ir"));
        c cVarC6 = cVar.c(f.j("coroutines"));
        f15384f = cVarC6;
        f15385g = cVar.c(f.j("enums"));
        cVar.c(f.j("contracts"));
        cVar.c(f.j("concurrent"));
        cVar.c(f.j("test"));
        f15386h = r.p0(new c[]{cVar, cVarC2, cVarC3, cVarC4, cVarC, cVarC5, cVarC6});
        j.a("Nothing");
        j.a("Unit");
        j.a("Any");
        j.a("Enum");
        j.a("Annotation");
        f15387i = j.a("Array");
        b bVarA = j.a("Boolean");
        b bVarA2 = j.a("Char");
        b bVarA3 = j.a("Byte");
        b bVarA4 = j.a("Short");
        b bVarA5 = j.a("Int");
        b bVarA6 = j.a("Long");
        b bVarA7 = j.a("Float");
        b bVarA8 = j.a("Double");
        f15388j = j.f(bVarA3);
        k = j.f(bVarA4);
        f15389l = j.f(bVarA5);
        f15390m = j.f(bVarA6);
        j.a("CharSequence");
        f15391n = j.a("String");
        j.a("Throwable");
        j.a("Cloneable");
        j.e("KProperty");
        j.e("KMutableProperty");
        j.e("KProperty0");
        j.e("KMutableProperty0");
        j.e("KProperty1");
        j.e("KMutableProperty1");
        j.e("KProperty2");
        j.e("KMutableProperty2");
        f15392o = j.e("KFunction");
        j.e("KClass");
        j.e("KCallable");
        j.e("KType");
        j.a("Comparable");
        j.a("Number");
        j.a("Function");
        Set setP0 = r.p0(new b[]{bVarA, bVarA2, bVarA3, bVarA4, bVarA5, bVarA6, bVarA7, bVarA8});
        f15393p = setP0;
        Set set = setP0;
        int iQ0 = i0.q0(s.U(set, 10));
        if (iQ0 < 16) {
            iQ0 = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iQ0);
        for (Object obj : set) {
            linkedHashMap.put(obj, j.d(((b) obj).i()));
        }
        j.c(linkedHashMap);
        Set setP02 = r.p0(new b[]{f15388j, k, f15389l, f15390m});
        f15394q = setP02;
        Set set2 = setP02;
        int iQ02 = i0.q0(s.U(set2, 10));
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(iQ02 >= 16 ? iQ02 : 16);
        for (Object obj2 : set2) {
            linkedHashMap2.put(obj2, j.d(((b) obj2).i()));
        }
        j.c(linkedHashMap2);
        q0.c0(q0.b0(f15393p, f15394q), f15391n);
        c cVar2 = f15384f;
        f fVarJ = f.j("Continuation");
        if (cVar2 == null) {
            b.a(3);
            throw null;
        }
        c.j(fVarJ);
        j.b("Iterator");
        j.b("Iterable");
        j.b("Collection");
        j.b("List");
        j.b("ListIterator");
        j.b("Set");
        b bVarB = j.b("Map");
        j.b("MutableIterator");
        j.b("CharIterator");
        j.b("MutableIterable");
        j.b("MutableCollection");
        f15395r = j.b("MutableList");
        j.b("MutableListIterator");
        f15396s = j.b("MutableSet");
        b bVarB2 = j.b("MutableMap");
        f15397t = bVarB2;
        bVarB.d(f.j("Entry"));
        bVarB2.d(f.j("MutableEntry"));
        j.a("Result");
        c cVar3 = f15382d;
        f fVarJ2 = f.j("IntRange");
        if (cVar3 == null) {
            b.a(3);
            throw null;
        }
        c.j(fVarJ2);
        c.j(f.j("LongRange"));
        c.j(f.j("CharRange"));
        c cVar4 = f15383e;
        f fVarJ3 = f.j("AnnotationRetention");
        if (cVar4 == null) {
            b.a(3);
            throw null;
        }
        c.j(fVarJ3);
        c.j(f.j("AnnotationTarget"));
        j.a("DeprecationLevel");
        f15398u = new b(f15385g, f.j("EnumEntries"));
    }
}
