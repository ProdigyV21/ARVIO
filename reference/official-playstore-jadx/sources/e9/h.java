package e9;

import a9.i0;
import a9.k1;
import a9.n;
import a9.n0;
import a9.t0;
import a9.x1;
import androidx.work.impl.constraints.j;
import com.google.common.util.concurrent.r0;
import d9.o;
import d9.q;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.s;
import kotlin.collections.x;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;
import kotlin.reflect.jvm.internal.impl.protobuf.i;
import kotlin.reflect.jvm.internal.impl.protobuf.l;

/* JADX INFO: loaded from: classes5.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final l f15080a;

    static {
        l lVar = new l();
        lVar.a(q.f14863a);
        lVar.a(q.f14864b);
        lVar.a(q.f14865c);
        lVar.a(q.f14866d);
        lVar.a(q.f14867e);
        lVar.a(q.f14868f);
        lVar.a(q.f14869g);
        lVar.a(q.f14870h);
        lVar.a(q.f14871i);
        lVar.a(q.f14872j);
        lVar.a(q.k);
        lVar.a(q.f14873l);
        lVar.a(q.f14874m);
        lVar.a(q.f14875n);
        f15080a = lVar;
    }

    public static e a(a9.q qVar, c9.f fVar, j jVar) {
        String strU0;
        d9.e eVar = (d9.e) r0.l(qVar, q.f14863a);
        String string = (eVar == null || (eVar.f14805l & 1) != 1) ? "<init>" : fVar.getString(eVar.f14806m);
        if (eVar == null || (eVar.f14805l & 2) != 2) {
            List list = qVar.f636o;
            ArrayList arrayList = new ArrayList(s.U(list, 10));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                String strE = e(f4.f.A((x1) it.next(), jVar), fVar);
                if (strE == null) {
                    return null;
                }
                arrayList.add(strE);
            }
            strU0 = x.u0(arrayList, "", "(", ")V", null, 56);
        } else {
            strU0 = fVar.getString(eVar.f14807n);
        }
        return new e(string, strU0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0050, code lost:
    
        if (r4 == null) goto L29;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static e9.d b(a9.t0 r4, c9.f r5, androidx.work.impl.constraints.j r6, boolean r7) {
        /*
            kotlin.reflect.jvm.internal.impl.protobuf.t r0 = d9.q.f14866d
            java.lang.Object r0 = com.google.common.util.concurrent.r0.l(r4, r0)
            d9.h r0 = (d9.h) r0
            r1 = 0
            if (r0 != 0) goto Lc
            goto L52
        Lc:
            int r2 = r0.f14819l
            r3 = 1
            r2 = r2 & r3
            if (r2 != r3) goto L15
            d9.b r0 = r0.f14820m
            goto L16
        L15:
            r0 = r1
        L16:
            if (r0 != 0) goto L1b
            if (r7 == 0) goto L1b
            goto L52
        L1b:
            if (r0 == 0) goto L25
            int r7 = r0.f14794l
            r7 = r7 & r3
            if (r7 != r3) goto L25
            int r7 = r0.f14795m
            goto L27
        L25:
            int r7 = r4.f680p
        L27:
            if (r0 == 0) goto L36
            int r2 = r0.f14794l
            r3 = 2
            r2 = r2 & r3
            if (r2 != r3) goto L36
            int r4 = r0.f14796n
            java.lang.String r4 = r5.getString(r4)
            goto L53
        L36:
            int r0 = r4.f677m
            r2 = r0 & 8
            r3 = 8
            if (r2 != r3) goto L41
            a9.k1 r4 = r4.f681q
            goto L4c
        L41:
            r2 = 16
            r0 = r0 & r2
            if (r0 != r2) goto L5d
            int r4 = r4.f682r
            a9.k1 r4 = r6.b(r4)
        L4c:
            java.lang.String r4 = e(r4, r5)
            if (r4 != 0) goto L53
        L52:
            return r1
        L53:
            e9.d r6 = new e9.d
            java.lang.String r5 = r5.getString(r7)
            r6.<init>(r5, r4)
            return r6
        L5d:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "No returnType in ProtoBuf.Property"
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: e9.h.b(a9.t0, c9.f, androidx.work.impl.constraints.j, boolean):e9.d");
    }

    public static e c(i0 i0Var, c9.f fVar, j jVar) {
        k1 k1VarB;
        String strP;
        d9.e eVar = (d9.e) r0.l(i0Var, q.f14864b);
        int i10 = (eVar == null || (eVar.f14805l & 1) != 1) ? i0Var.f489p : eVar.f14806m;
        if (eVar == null || (eVar.f14805l & 2) != 2) {
            int i11 = i0Var.f486m;
            List listF = t7.a.F((i11 & 32) == 32 ? i0Var.f493t : (i11 & 64) == 64 ? jVar.b(i0Var.f494u) : null);
            List list = i0Var.f497y;
            ArrayList arrayList = new ArrayList(s.U(list, 10));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(f4.f.A((x1) it.next(), jVar));
            }
            ArrayList arrayListI0 = x.I0(listF, arrayList);
            ArrayList arrayList2 = new ArrayList(s.U(arrayListI0, 10));
            Iterator it2 = arrayListI0.iterator();
            while (true) {
                if (it2.hasNext()) {
                    String strE = e((k1) it2.next(), fVar);
                    if (strE == null) {
                        break;
                    }
                    arrayList2.add(strE);
                } else {
                    int i12 = i0Var.f486m;
                    if ((i12 & 8) == 8) {
                        k1VarB = i0Var.f490q;
                    } else {
                        if ((i12 & 16) != 16) {
                            throw new IllegalStateException("No returnType in ProtoBuf.Function");
                        }
                        k1VarB = jVar.b(i0Var.f491r);
                    }
                    String strE2 = e(k1VarB, fVar);
                    if (strE2 != null) {
                        strP = a0.c.p(new StringBuilder(), x.u0(arrayList2, "", "(", ")", null, 56), strE2);
                    }
                }
            }
            return null;
        }
        strP = fVar.getString(eVar.f14807n);
        return new e(fVar.getString(i10), strP);
    }

    public static final boolean d(t0 t0Var) {
        return c.f15068a.c(((Number) t0Var.j(q.f14867e)).intValue()).booleanValue();
    }

    public static String e(k1 k1Var, c9.f fVar) {
        if ((k1Var.f520m & 16) == 16) {
            return b.b(fVar.o(k1Var.f526s));
        }
        return null;
    }

    public static final x6.x f(String[] strArr, String[] strArr2) throws InvalidProtocolBufferException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(a.a(strArr));
        g gVarG = g(byteArrayInputStream, strArr2);
        a9.a aVar = n.U;
        aVar.getClass();
        i iVar = new i(byteArrayInputStream);
        c0 c0Var = (c0) aVar.b(iVar, f15080a);
        try {
            iVar.a(0);
            kotlin.reflect.jvm.internal.impl.protobuf.d.c(c0Var);
            return new x6.x(gVarG, (n) c0Var);
        } catch (InvalidProtocolBufferException e5) {
            e5.f19759i = c0Var;
            throw e5;
        }
    }

    public static g g(ByteArrayInputStream byteArrayInputStream, String[] strArr) {
        return new g((o) o.f14856r.a(byteArrayInputStream, f15080a), strArr);
    }

    public static final x6.x h(String[] strArr, String[] strArr2) throws InvalidProtocolBufferException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(a.a(strArr));
        g gVarG = g(byteArrayInputStream, strArr2);
        a9.a aVar = n0.f595v;
        aVar.getClass();
        i iVar = new i(byteArrayInputStream);
        c0 c0Var = (c0) aVar.b(iVar, f15080a);
        try {
            iVar.a(0);
            kotlin.reflect.jvm.internal.impl.protobuf.d.c(c0Var);
            return new x6.x(gVarG, (n0) c0Var);
        } catch (InvalidProtocolBufferException e5) {
            e5.f19759i = c0Var;
            throw e5;
        }
    }
}
