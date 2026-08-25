package e8;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final l f15063c = new l(t7.a.E(f.f15055c, i.f15058c, g.f15056c, h.f15057c));

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f15064a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LinkedHashMap f15065b;

    public l(List list) {
        this.f15064a = list;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : list) {
            f9.c cVar = ((j) obj).f15059a;
            Object arrayList = linkedHashMap.get(cVar);
            if (arrayList == null) {
                arrayList = new ArrayList();
                linkedHashMap.put(cVar, arrayList);
            }
            ((List) arrayList).add(obj);
        }
        this.f15065b = linkedHashMap;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0056 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0010 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final e8.k a(f9.c r9, java.lang.String r10) {
        /*
            r8 = this;
            java.util.LinkedHashMap r0 = r8.f15065b
            java.lang.Object r9 = r0.get(r9)
            java.util.List r9 = (java.util.List) r9
            r0 = 0
            if (r9 != 0) goto Lc
            goto L60
        Lc:
            java.util.Iterator r9 = r9.iterator()
        L10:
            boolean r1 = r9.hasNext()
            if (r1 == 0) goto L60
            java.lang.Object r1 = r9.next()
            e8.j r1 = (e8.j) r1
            java.lang.String r2 = r1.f15060b
            r3 = 0
            boolean r2 = kotlin.text.u.P(r10, r2, r3)
            if (r2 == 0) goto L10
            java.lang.String r2 = r1.f15060b
            int r2 = r2.length()
            java.lang.String r2 = r10.substring(r2)
            int r4 = r2.length()
            if (r4 != 0) goto L37
        L35:
            r2 = r0
            goto L54
        L37:
            int r4 = r2.length()
            r5 = r3
        L3c:
            if (r3 >= r4) goto L50
            char r6 = r2.charAt(r3)
            int r6 = r6 + (-48)
            if (r6 < 0) goto L35
            r7 = 10
            if (r6 >= r7) goto L35
            int r5 = r5 * 10
            int r5 = r5 + r6
            int r3 = r3 + 1
            goto L3c
        L50:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r5)
        L54:
            if (r2 == 0) goto L10
            int r9 = r2.intValue()
            e8.k r10 = new e8.k
            r10.<init>(r1, r9)
            return r10
        L60:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: e8.l.a(f9.c, java.lang.String):e8.k");
    }
}
