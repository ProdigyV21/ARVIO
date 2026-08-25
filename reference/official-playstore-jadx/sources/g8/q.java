package g8;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public abstract class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final p f15524a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final p f15525b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final p f15526c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final p f15527d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final p f15528e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final p f15529f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final p f15530g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final p f15531h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final p f15532i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final Set f15533j;
    public static final Map k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final p f15534l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final y0 f15535m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final y0 f15536n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final y0 f15537o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final ba.p f15538p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final HashMap f15539q;

    static {
        i1 i1Var = i1.f15511h;
        p pVar = new p(i1Var, 0);
        f15524a = pVar;
        i1 i1Var2 = i1.f15512i;
        p pVar2 = new p(i1Var2, 1);
        f15525b = pVar2;
        i1 i1Var3 = i1.f15513j;
        p pVar3 = new p(i1Var3, 2);
        f15526c = pVar3;
        i1 i1Var4 = i1.f15508e;
        p pVar4 = new p(i1Var4, 3);
        f15527d = pVar4;
        i1 i1Var5 = i1.k;
        p pVar5 = new p(i1Var5, 4);
        f15528e = pVar5;
        i1 i1Var6 = i1.f15510g;
        p pVar6 = new p(i1Var6, 5);
        f15529f = pVar6;
        i1 i1Var7 = i1.f15507d;
        p pVar7 = new p(i1Var7, 6);
        f15530g = pVar7;
        i1 i1Var8 = i1.f15509f;
        p pVar8 = new p(i1Var8, 7);
        f15531h = pVar8;
        i1 i1Var9 = i1.f15514l;
        p pVar9 = new p(i1Var9, 8);
        f15532i = pVar9;
        f15533j = Collections.unmodifiableSet(kotlin.collections.r.p0(new p[]{pVar, pVar2, pVar4, pVar6}));
        HashMap map = new HashMap(6);
        map.put(pVar2, 0);
        map.put(pVar, 0);
        map.put(pVar4, 1);
        map.put(pVar3, 1);
        map.put(pVar5, 2);
        k = Collections.unmodifiableMap(map);
        f15534l = pVar5;
        f15535m = new y0(1);
        f15536n = new y0(2);
        f15537o = new y0(3);
        Iterator it = ServiceLoader.load(ba.p.class, ba.p.class.getClassLoader()).iterator();
        f15538p = it.hasNext() ? (ba.p) it.next() : ba.o.f7302a;
        HashMap map2 = new HashMap();
        f15539q = map2;
        map2.put(i1Var, pVar);
        map2.put(i1Var2, pVar2);
        map2.put(i1Var3, pVar3);
        map2.put(i1Var4, pVar4);
        map2.put(i1Var5, pVar5);
        map2.put(i1Var6, pVar6);
        map2.put(i1Var7, pVar7);
        map2.put(i1Var8, pVar8);
        map2.put(i1Var9, pVar9);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ void a(int r8) {
        /*
            r0 = 16
            if (r8 == r0) goto L7
            java.lang.String r1 = "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            goto L9
        L7:
            java.lang.String r1 = "@NotNull method %s.%s must not return null"
        L9:
            r2 = 3
            r3 = 2
            if (r8 == r0) goto Lf
            r4 = r2
            goto L10
        Lf:
            r4 = r3
        L10:
            java.lang.Object[] r4 = new java.lang.Object[r4]
            java.lang.String r5 = "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities"
            r6 = 1
            r7 = 0
            if (r8 == r6) goto L3a
            if (r8 == r2) goto L3a
            r2 = 5
            if (r8 == r2) goto L3a
            r2 = 7
            if (r8 == r2) goto L3a
            switch(r8) {
                case 9: goto L3a;
                case 10: goto L35;
                case 11: goto L30;
                case 12: goto L35;
                case 13: goto L30;
                case 14: goto L2b;
                case 15: goto L2b;
                case 16: goto L28;
                default: goto L23;
            }
        L23:
            java.lang.String r2 = "what"
            r4[r7] = r2
            goto L3e
        L28:
            r4[r7] = r5
            goto L3e
        L2b:
            java.lang.String r2 = "visibility"
            r4[r7] = r2
            goto L3e
        L30:
            java.lang.String r2 = "second"
            r4[r7] = r2
            goto L3e
        L35:
            java.lang.String r2 = "first"
            r4[r7] = r2
            goto L3e
        L3a:
            java.lang.String r2 = "from"
            r4[r7] = r2
        L3e:
            java.lang.String r2 = "toDescriptorVisibility"
            if (r8 == r0) goto L45
            r4[r6] = r5
            goto L47
        L45:
            r4[r6] = r2
        L47:
            switch(r8) {
                case 2: goto L70;
                case 3: goto L70;
                case 4: goto L6b;
                case 5: goto L6b;
                case 6: goto L66;
                case 7: goto L66;
                case 8: goto L61;
                case 9: goto L61;
                case 10: goto L5c;
                case 11: goto L5c;
                case 12: goto L57;
                case 13: goto L57;
                case 14: goto L52;
                case 15: goto L4f;
                case 16: goto L74;
                default: goto L4a;
            }
        L4a:
            java.lang.String r2 = "isVisible"
            r4[r3] = r2
            goto L74
        L4f:
            r4[r3] = r2
            goto L74
        L52:
            java.lang.String r2 = "isPrivate"
            r4[r3] = r2
            goto L74
        L57:
            java.lang.String r2 = "compare"
            r4[r3] = r2
            goto L74
        L5c:
            java.lang.String r2 = "compareLocal"
            r4[r3] = r2
            goto L74
        L61:
            java.lang.String r2 = "findInvisibleMember"
            r4[r3] = r2
            goto L74
        L66:
            java.lang.String r2 = "inSameFile"
            r4[r3] = r2
            goto L74
        L6b:
            java.lang.String r2 = "isVisibleWithAnyReceiver"
            r4[r3] = r2
            goto L74
        L70:
            java.lang.String r2 = "isVisibleIgnoringReceiver"
            r4[r3] = r2
        L74:
            java.lang.String r1 = java.lang.String.format(r1, r4)
            if (r8 == r0) goto L80
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            r8.<init>(r1)
            goto L85
        L80:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            r8.<init>(r1)
        L85:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: g8.q.a(int):void");
    }

    public static Integer b(p pVar, p pVar2) {
        if (pVar == null) {
            a(12);
            throw null;
        }
        k1 k1Var = pVar.f15522a;
        if (pVar2 == null) {
            a(13);
            throw null;
        }
        k1 k1Var2 = pVar2.f15522a;
        Integer numA = k1Var.a(k1Var2);
        if (numA != null) {
            return numA;
        }
        Integer numA2 = k1Var2.a(k1Var);
        if (numA2 != null) {
            return Integer.valueOf(-numA2.intValue());
        }
        return null;
    }

    public static o c(q9.f fVar, o oVar, k kVar) {
        o oVarC;
        if (oVar == null) {
            a(8);
            throw null;
        }
        if (kVar == null) {
            a(9);
            throw null;
        }
        for (o oVar2 = (o) oVar.a(); oVar2 != null && oVar2.getVisibility() != f15529f; oVar2 = (o) i9.f.i(oVar2, o.class, true)) {
            if (!oVar2.getVisibility().a(fVar, oVar2, kVar)) {
                return oVar2;
            }
        }
        if (!(oVar instanceof j8.q0) || (oVarC = c(fVar, ((j8.q0) oVar).P(), kVar)) == null) {
            return null;
        }
        return oVarC;
    }

    public static boolean d(o oVar, k kVar) {
        if (kVar == null) {
            a(7);
            throw null;
        }
        w0 w0VarF = i9.f.f(kVar);
        if (w0VarF != w0.f15555c) {
            return w0VarF.equals(i9.f.f(oVar));
        }
        return false;
    }

    public static boolean e(p pVar) {
        if (pVar != null) {
            return pVar == f15524a || pVar == f15525b;
        }
        a(14);
        throw null;
    }

    public static boolean f(c cVar, k kVar) {
        if (cVar == null) {
            a(2);
            throw null;
        }
        if (kVar != null) {
            return c(f15536n, cVar, kVar) == null;
        }
        a(3);
        throw null;
    }

    public static p g(k1 k1Var) {
        if (k1Var == null) {
            a(15);
            throw null;
        }
        p pVar = (p) f15539q.get(k1Var);
        if (pVar != null) {
            return pVar;
        }
        throw new IllegalArgumentException("Inapplicable visibility: " + k1Var);
    }
}
