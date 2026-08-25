package d8;

import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: loaded from: classes5.dex */
public abstract class p {
    public static final f9.c A;
    public static final f9.c B;
    public static final f9.c C;
    public static final f9.c D;
    public static final f9.c E;
    public static final f9.c F;
    public static final f9.c G;
    public static final f9.c H;
    public static final f9.c I;
    public static final f9.c J;
    public static final f9.c K;
    public static final f9.c L;
    public static final f9.c M;
    public static final f9.c N;
    public static final f9.c O;
    public static final f9.e P;
    public static final f9.b Q;
    public static final f9.b R;
    public static final f9.b S;
    public static final f9.b T;
    public static final f9.b U;
    public static final f9.c V;
    public static final f9.c W;
    public static final f9.c X;
    public static final f9.c Y;
    public static final HashSet Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public static final HashSet f14733a0;
    public static final HashMap b0;
    public static final HashMap c0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final f9.e f14736d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final f9.e f14737e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final f9.e f14738f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final f9.e f14739g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final f9.e f14740h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final f9.e f14741i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final f9.e f14742j;
    public static final f9.c k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final f9.c f14743l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final f9.c f14744m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final f9.c f14745n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final f9.c f14746o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final f9.c f14747p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final f9.c f14748q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final f9.c f14749r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final f9.c f14750s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final f9.c f14751t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final f9.c f14752u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final f9.c f14753v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final f9.c f14754w;
    public static final f9.c x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final f9.c f14755y;
    public static final f9.c z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f9.e f14732a = d("Any");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final f9.e f14734b = d("Nothing");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final f9.e f14735c = d("Cloneable");

    static {
        c("Suppress");
        f14736d = d("Unit");
        f14737e = d("CharSequence");
        f14738f = d("String");
        f14739g = d("Array");
        f14740h = d("Boolean");
        d("Char");
        d("Byte");
        d("Short");
        d("Int");
        d("Long");
        d("Float");
        d("Double");
        f14741i = d("Number");
        f14742j = d("Enum");
        d("Function");
        k = c("Throwable");
        f14743l = c("Comparable");
        f9.c cVar = q.f14769o;
        cVar.c(f9.f.j("IntRange")).i();
        cVar.c(f9.f.j("LongRange")).i();
        f14744m = c("Deprecated");
        c("DeprecatedSinceKotlin");
        f14745n = c("DeprecationLevel");
        f14746o = c("ReplaceWith");
        f14747p = c("ExtensionFunctionType");
        f14748q = c("ContextFunctionTypeParams");
        f9.c cVarC = c("ParameterName");
        f14749r = cVarC;
        f9.b.j(cVarC);
        f14750s = c("Annotation");
        f9.c cVarA = a("Target");
        f14751t = cVarA;
        f9.b.j(cVarA);
        f14752u = a("AnnotationTarget");
        f14753v = a("AnnotationRetention");
        f9.c cVarA2 = a("Retention");
        f14754w = cVarA2;
        f9.b.j(cVarA2);
        f9.b.j(a("Repeatable"));
        x = a("MustBeDocumented");
        f14755y = c("UnsafeVariance");
        c("PublishedApi");
        q.f14770p.c(f9.f.j("AccessibleLateinitPropertyLiteral"));
        z = b("Iterator");
        A = b("Iterable");
        B = b("Collection");
        C = b("List");
        D = b("ListIterator");
        E = b("Set");
        f9.c cVarB = b("Map");
        F = cVarB;
        G = cVarB.c(f9.f.j("Entry"));
        H = b("MutableIterator");
        I = b("MutableIterable");
        J = b("MutableCollection");
        K = b("MutableList");
        L = b("MutableListIterator");
        M = b("MutableSet");
        f9.c cVarB2 = b("MutableMap");
        N = cVarB2;
        O = cVarB2.c(f9.f.j("MutableEntry"));
        P = e("KClass");
        e("KType");
        e("KCallable");
        e("KProperty0");
        e("KProperty1");
        e("KProperty2");
        e("KMutableProperty0");
        e("KMutableProperty1");
        e("KMutableProperty2");
        f9.e eVarE = e("KProperty");
        e("KMutableProperty");
        Q = f9.b.j(eVarE.g());
        e("KDeclarationContainer");
        f9.c cVarC2 = c("UByte");
        f9.c cVarC3 = c("UShort");
        f9.c cVarC4 = c("UInt");
        f9.c cVarC5 = c("ULong");
        R = f9.b.j(cVarC2);
        S = f9.b.j(cVarC3);
        T = f9.b.j(cVarC4);
        U = f9.b.j(cVarC5);
        V = c("UByteArray");
        W = c("UShortArray");
        X = c("UIntArray");
        Y = c("ULongArray");
        int length = m.values().length;
        HashSet hashSet = new HashSet(length < 3 ? 3 : (length / 3) + length + 1);
        for (m mVar : m.values()) {
            hashSet.add(mVar.f14721i);
        }
        Z = hashSet;
        int length2 = m.values().length;
        HashSet hashSet2 = new HashSet(length2 < 3 ? 3 : (length2 / 3) + length2 + 1);
        for (m mVar2 : m.values()) {
            hashSet2.add(mVar2.f14722l);
        }
        f14733a0 = hashSet2;
        int length3 = m.values().length;
        HashMap map = new HashMap(length3 < 3 ? 3 : (length3 / 3) + length3 + 1);
        for (m mVar3 : m.values()) {
            map.put(d(mVar3.f14721i.d()), mVar3);
        }
        b0 = map;
        int length4 = m.values().length;
        HashMap map2 = new HashMap(length4 >= 3 ? (length4 / 3) + length4 + 1 : 3);
        for (m mVar4 : m.values()) {
            map2.put(d(mVar4.f14722l.d()), mVar4);
        }
        c0 = map2;
    }

    public static f9.c a(String str) {
        return q.f14767m.c(f9.f.j(str));
    }

    public static f9.c b(String str) {
        return q.f14768n.c(f9.f.j(str));
    }

    public static f9.c c(String str) {
        return q.f14766l.c(f9.f.j(str));
    }

    public static f9.e d(String str) {
        return c(str).i();
    }

    public static final f9.e e(String str) {
        return q.f14764i.c(f9.f.j(str)).i();
    }
}
