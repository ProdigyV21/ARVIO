package c2;

import android.content.Context;
import androidx.appcompat.widget.f0;
import java.util.ArrayList;
import java.util.Iterator;
import p8.y;
import v9.b0;
import v9.q;
import v9.w;
import y9.h;

/* JADX INFO: loaded from: classes3.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f7380a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f7381b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f7382c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f7383d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f7384e;

    public c(h8.a aVar, boolean z, androidx.core.provider.e eVar, p8.a aVar2, boolean z5) {
        this.f7382c = aVar;
        this.f7380a = z;
        this.f7383d = eVar;
        this.f7384e = aVar2;
        this.f7381b = z5;
    }

    public static void a(Object obj, ArrayList arrayList, s8.d dVar) {
        arrayList.add(obj);
        Iterable iterable = (Iterable) dVar.invoke(obj);
        if (iterable != null) {
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                a(it.next(), arrayList, dVar);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:72:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.lang.Iterable, java.util.Collection] */
    /* JADX WARN: Type inference failed for: r0v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static x8.h c(y9.n r3) {
        /*
            boolean r0 = r3 instanceof t8.e0
            if (r0 != 0) goto L6
            goto Lb6
        L6:
            g8.b1 r3 = (g8.b1) r3
            java.util.List r3 = r3.getUpperBounds()
            boolean r0 = r3.isEmpty()
            if (r0 == 0) goto L14
            goto Lb6
        L14:
            java.util.Iterator r0 = r3.iterator()
        L18:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto Lb6
            java.lang.Object r1 = r0.next()
            y9.h r1 = (y9.h) r1
            boolean r1 = w9.f.C(r1)
            if (r1 != 0) goto L18
            boolean r0 = r3.isEmpty()
            if (r0 == 0) goto L31
            goto L49
        L31:
            java.util.Iterator r0 = r3.iterator()
        L35:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L49
            java.lang.Object r1 = r0.next()
            y9.h r1 = (y9.h) r1
            x8.g r1 = d(r1)
            if (r1 == 0) goto L35
            r0 = r3
            goto L89
        L49:
            boolean r0 = r3.isEmpty()
            if (r0 == 0) goto L50
            goto Lb6
        L50:
            java.util.Iterator r0 = r3.iterator()
        L54:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto Lb6
            java.lang.Object r1 = r0.next()
            y9.h r1 = (y9.h) r1
            v9.w r1 = (v9.w) r1
            v9.w r1 = v9.c.d(r1)
            if (r1 == 0) goto L54
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Iterator r1 = r3.iterator()
        L71:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L89
            java.lang.Object r2 = r1.next()
            y9.h r2 = (y9.h) r2
            v9.w r2 = (v9.w) r2
            v9.w r2 = v9.c.d(r2)
            if (r2 == 0) goto L71
            r0.add(r2)
            goto L71
        L89:
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L90
            goto La9
        L90:
            java.util.Iterator r1 = r0.iterator()
        L94:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto La9
            java.lang.Object r2 = r1.next()
            y9.h r2 = (y9.h) r2
            boolean r2 = w9.f.I(r2)
            if (r2 != 0) goto L94
            x8.g r1 = x8.g.f22650m
            goto Lab
        La9:
            x8.g r1 = x8.g.f22649l
        Lab:
            x8.h r2 = new x8.h
            if (r0 == r3) goto Lb1
            r3 = 1
            goto Lb2
        Lb1:
            r3 = 0
        Lb2:
            r2.<init>(r1, r3)
            return r2
        Lb6:
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: c2.c.c(y9.n):x8.h");
    }

    public static x8.g d(h hVar) {
        b0 b0VarH;
        b0 b0VarH2;
        q qVarG = w9.f.g(hVar);
        if (qVarG == null || (b0VarH = w9.f.O(qVarG)) == null) {
            b0VarH = w9.f.h(hVar);
        }
        if (w9.f.G(b0VarH)) {
            return x8.g.f22649l;
        }
        q qVarG2 = w9.f.g(hVar);
        if (qVarG2 == null || (b0VarH2 = w9.f.Z(qVarG2)) == null) {
            b0VarH2 = w9.f.h(hVar);
        }
        if (w9.f.G(b0VarH2)) {
            return null;
        }
        return x8.g.f22650m;
    }

    public p8.c b() {
        return ((s8.a) ((androidx.core.provider.e) this.f7383d).f2140l).f21660q;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.Object, x6.s] */
    public ArrayList e(h hVar) {
        x8.a aVar = new x8.a(hVar, b().b((y) ((androidx.core.provider.e) this.f7383d).f2143o.getValue(), ((w) hVar).getAnnotations()), null);
        s8.d dVar = new s8.d(this, 13);
        ArrayList arrayList = new ArrayList(1);
        a(aVar, arrayList, dVar);
        return arrayList;
    }

    public c(Context context, String str, f0 f0Var, boolean z, boolean z5) {
        this.f7382c = context;
        this.f7383d = str;
        this.f7384e = f0Var;
        this.f7380a = z;
        this.f7381b = z5;
    }
}
