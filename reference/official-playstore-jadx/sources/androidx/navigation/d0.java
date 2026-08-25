package androidx.navigation;

import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.i1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public abstract class d0 {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final /* synthetic */ int f4656r = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f4657i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public h0 f4658l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayList f4659m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final t.m0 f4660n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final LinkedHashMap f4661o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f4662p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public String f4663q;

    static {
        new LinkedHashMap();
    }

    public d0(a1 a1Var) {
        LinkedHashMap linkedHashMap = b1.f4528b;
        this.f4657i = m2.f0.t(a1Var.getClass());
        this.f4659m = new ArrayList();
        this.f4660n = new t.m0(0);
        this.f4661o = new LinkedHashMap();
    }

    public final void a(y yVar) {
        ArrayList arrayListQ = f4.f.q(this.f4661o, new ab.h(yVar, 1));
        if (arrayListQ.isEmpty()) {
            this.f4659m.add(yVar);
            return;
        }
        throw new IllegalArgumentException(("Deep link " + yVar.f4805a + " can't be used to open destination " + this + ".\nFollowing required arguments are missing: " + arrayListQ).toString());
    }

    public final Bundle b(Bundle bundle) {
        LinkedHashMap linkedHashMap = this.f4661o;
        if (bundle == null && (linkedHashMap == null || linkedHashMap.isEmpty())) {
            return null;
        }
        Bundle bundle2 = new Bundle();
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            String str = (String) entry.getKey();
            g gVar = (g) entry.getValue();
            if (gVar.f4668c) {
                gVar.f4666a.e(bundle2, str, gVar.f4669d);
            }
        }
        if (bundle != null) {
            bundle2.putAll(bundle);
            for (Map.Entry entry2 : linkedHashMap.entrySet()) {
                String str2 = (String) entry2.getKey();
                g gVar2 = (g) entry2.getValue();
                boolean z = gVar2.f4667b;
                w0 w0Var = gVar2.f4666a;
                if (z || !bundle2.containsKey(str2) || bundle2.get(str2) != null) {
                    try {
                        w0Var.a(bundle2, str2);
                    } catch (ClassCastException unused) {
                    }
                }
                StringBuilder sbT = a0.c.t("Wrong argument type for '", str2, "' in argument bundle. ");
                sbT.append(w0Var.b());
                sbT.append(" expected.");
                throw new IllegalArgumentException(sbT.toString().toString());
            }
        }
        return bundle2;
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0167  */
    /* JADX WARN: Type inference failed for: r11v5, types: [java.lang.Object, x6.s] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public androidx.navigation.c0 c(androidx.appcompat.app.i1 r18) {
        /*
            Method dump skipped, instruction units count: 387
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.d0.c(androidx.appcompat.app.i1):androidx.navigation.c0");
    }

    public final c0 d(String str) {
        Object obj = null;
        i1 i1Var = new i1(Uri.parse("android-app://androidx.navigation/".concat(str)), obj, obj, 6);
        return this instanceof h0 ? ((h0) this).h(i1Var) : c(i1Var);
    }

    public final void e(String str) {
        Object next;
        if (str == null) {
            this.f4662p = 0;
        } else {
            if (kotlin.text.o.h0(str)) {
                throw new IllegalArgumentException("Cannot have an empty route");
            }
            String strConcat = "android-app://androidx.navigation/".concat(str);
            this.f4662p = strConcat.hashCode();
            a(new y(strConcat));
        }
        ArrayList arrayList = this.f4659m;
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            String str2 = ((y) next).f4805a;
            String str3 = this.f4663q;
            if (str2.equals(str3 != null ? "android-app://androidx.navigation/".concat(str3) : "")) {
                break;
            }
        }
        kotlin.jvm.internal.q0.a(arrayList).remove(next);
        this.f4663q = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00a2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean equals(java.lang.Object r10) {
        /*
            r9 = this;
            r0 = 1
            if (r9 != r10) goto L5
            goto Lb2
        L5:
            r1 = 0
            if (r10 == 0) goto Lb3
            boolean r2 = r10 instanceof androidx.navigation.d0
            if (r2 != 0) goto Le
            goto Lb3
        Le:
            androidx.navigation.d0 r10 = (androidx.navigation.d0) r10
            t.m0 r2 = r10.f4660n
            java.util.LinkedHashMap r3 = r10.f4661o
            java.util.ArrayList r4 = r10.f4659m
            java.util.ArrayList r5 = r9.f4659m
            boolean r4 = kotlin.jvm.internal.p.a(r5, r4)
            t.m0 r5 = r9.f4660n
            int r6 = r5.f()
            int r7 = r2.f()
            if (r6 != r7) goto L59
            kotlin.jvm.internal.b r6 = new kotlin.jvm.internal.b
            r7 = 1
            r6.<init>(r5, r7)
            ga.m r6 = ga.r.E(r6)
            ga.a r6 = (ga.a) r6
            java.util.Iterator r6 = r6.iterator()
        L38:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L57
            java.lang.Object r7 = r6.next()
            java.lang.Number r7 = (java.lang.Number) r7
            int r7 = r7.intValue()
            java.lang.Object r8 = r5.c(r7)
            java.lang.Object r7 = r2.c(r7)
            boolean r7 = kotlin.jvm.internal.p.a(r8, r7)
            if (r7 != 0) goto L38
            goto L59
        L57:
            r2 = r0
            goto L5a
        L59:
            r2 = r1
        L5a:
            java.util.LinkedHashMap r5 = r9.f4661o
            int r6 = r5.size()
            int r7 = r3.size()
            if (r6 != r7) goto L9b
            java.util.Set r5 = r5.entrySet()
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.Iterator r5 = r5.iterator()
        L70:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L99
            java.lang.Object r6 = r5.next()
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6
            java.lang.Object r7 = r6.getKey()
            boolean r7 = r3.containsKey(r7)
            if (r7 == 0) goto L9b
            java.lang.Object r7 = r6.getKey()
            java.lang.Object r7 = r3.get(r7)
            java.lang.Object r6 = r6.getValue()
            boolean r6 = kotlin.jvm.internal.p.a(r7, r6)
            if (r6 == 0) goto L9b
            goto L70
        L99:
            r3 = r0
            goto L9c
        L9b:
            r3 = r1
        L9c:
            int r5 = r9.f4662p
            int r6 = r10.f4662p
            if (r5 != r6) goto Lb3
            java.lang.String r5 = r9.f4663q
            java.lang.String r10 = r10.f4663q
            boolean r10 = kotlin.jvm.internal.p.a(r5, r10)
            if (r10 == 0) goto Lb3
            if (r4 == 0) goto Lb3
            if (r2 == 0) goto Lb3
            if (r3 == 0) goto Lb3
        Lb2:
            return r0
        Lb3:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.d0.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int i10 = this.f4662p * 31;
        String str = this.f4663q;
        int iHashCode = i10 + (str != null ? str.hashCode() : 0);
        Iterator it = this.f4659m.iterator();
        while (it.hasNext()) {
            iHashCode = (((y) it.next()).f4805a.hashCode() + (iHashCode * 31)) * 961;
        }
        t.m0 m0Var = this.f4660n;
        if (m0Var.f() > 0) {
            m0Var.g(0).getClass();
            throw new ClassCastException();
        }
        LinkedHashMap linkedHashMap = this.f4661o;
        for (String str2 : linkedHashMap.keySet()) {
            int iC = androidx.compose.foundation.c.c(iHashCode * 31, 31, str2);
            Object obj = linkedHashMap.get(str2);
            iHashCode = iC + (obj != null ? obj.hashCode() : 0);
        }
        return iHashCode;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getClass().getSimpleName());
        sb2.append("(0x");
        sb2.append(Integer.toHexString(this.f4662p));
        sb2.append(")");
        String str = this.f4663q;
        if (str != null && !kotlin.text.o.h0(str)) {
            sb2.append(" route=");
            sb2.append(this.f4663q);
        }
        return sb2.toString();
    }
}
