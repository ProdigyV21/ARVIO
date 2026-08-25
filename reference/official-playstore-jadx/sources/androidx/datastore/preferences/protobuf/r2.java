package androidx.datastore.preferences.protobuf;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes3.dex */
public final class r2 extends AbstractMap {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final /* synthetic */ int f2516q = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f2517i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public List f2518l = Collections.EMPTY_LIST;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Map f2519m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f2520n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public volatile w2 f2521o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Map f2522p;

    public r2(int i10) {
        this.f2517i = i10;
        Map map = Collections.EMPTY_MAP;
        this.f2519m = map;
        this.f2522p = map;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int b(java.lang.Comparable r5) {
        /*
            r4 = this;
            java.util.List r0 = r4.f2518l
            int r0 = r0.size()
            int r1 = r0 + (-1)
            if (r1 < 0) goto L21
            java.util.List r2 = r4.f2518l
            java.lang.Object r2 = r2.get(r1)
            androidx.datastore.preferences.protobuf.u2 r2 = (androidx.datastore.preferences.protobuf.u2) r2
            java.lang.Comparable r2 = r2.f2536i
            int r2 = r5.compareTo(r2)
            if (r2 <= 0) goto L1e
            int r0 = r0 + 1
        L1c:
            int r5 = -r0
            return r5
        L1e:
            if (r2 != 0) goto L21
            return r1
        L21:
            r0 = 0
        L22:
            if (r0 > r1) goto L43
            int r2 = r0 + r1
            int r2 = r2 / 2
            java.util.List r3 = r4.f2518l
            java.lang.Object r3 = r3.get(r2)
            androidx.datastore.preferences.protobuf.u2 r3 = (androidx.datastore.preferences.protobuf.u2) r3
            java.lang.Comparable r3 = r3.f2536i
            int r3 = r5.compareTo(r3)
            if (r3 >= 0) goto L3c
            int r2 = r2 + (-1)
            r1 = r2
            goto L22
        L3c:
            if (r3 <= 0) goto L42
            int r2 = r2 + 1
            r0 = r2
            goto L22
        L42:
            return r2
        L43:
            int r0 = r0 + 1
            goto L1c
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.r2.b(java.lang.Comparable):int");
    }

    public final void c() {
        if (this.f2520n) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        c();
        if (!this.f2518l.isEmpty()) {
            this.f2518l.clear();
        }
        if (this.f2519m.isEmpty()) {
            return;
        }
        this.f2519m.clear();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return b(comparable) >= 0 || this.f2519m.containsKey(comparable);
    }

    public final Map.Entry e(int i10) {
        return (Map.Entry) this.f2518l.get(i10);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        if (this.f2521o == null) {
            this.f2521o = new w2(0, this);
        }
        return this.f2521o;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof r2)) {
            return super.equals(obj);
        }
        r2 r2Var = (r2) obj;
        int size = size();
        if (size == r2Var.size()) {
            int size2 = this.f2518l.size();
            if (size2 != r2Var.f2518l.size()) {
                return ((AbstractSet) entrySet()).equals(r2Var.entrySet());
            }
            for (int i10 = 0; i10 < size2; i10++) {
                if (e(i10).equals(r2Var.e(i10))) {
                }
            }
            if (size2 != size) {
                return this.f2519m.equals(r2Var.f2519m);
            }
            return true;
        }
        return false;
    }

    public final Iterable g() {
        return this.f2519m.isEmpty() ? x1.f2549b : this.f2519m.entrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int iB = b(comparable);
        return iB >= 0 ? ((u2) this.f2518l.get(iB)).f2537l : this.f2519m.get(comparable);
    }

    public final SortedMap h() {
        c();
        if (this.f2519m.isEmpty() && !(this.f2519m instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.f2519m = treeMap;
            this.f2522p = treeMap.descendingMap();
        }
        return (SortedMap) this.f2519m;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        int size = this.f2518l.size();
        int iHashCode = 0;
        for (int i10 = 0; i10 < size; i10++) {
            iHashCode += ((u2) this.f2518l.get(i10)).hashCode();
        }
        return this.f2519m.size() > 0 ? this.f2519m.hashCode() + iHashCode : iHashCode;
    }

    @Override // java.util.AbstractMap, java.util.Map
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public final Object put(Comparable comparable, Object obj) {
        c();
        int iB = b(comparable);
        if (iB >= 0) {
            return ((u2) this.f2518l.get(iB)).setValue(obj);
        }
        c();
        boolean zIsEmpty = this.f2518l.isEmpty();
        int i10 = this.f2517i;
        if (zIsEmpty && !(this.f2518l instanceof ArrayList)) {
            this.f2518l = new ArrayList(i10);
        }
        int i11 = -(iB + 1);
        if (i11 >= i10) {
            return h().put(comparable, obj);
        }
        if (this.f2518l.size() == i10) {
            u2 u2Var = (u2) this.f2518l.remove(i10 - 1);
            h().put(u2Var.f2536i, u2Var.f2537l);
        }
        this.f2518l.add(i11, new u2(this, comparable, obj));
        return null;
    }

    public final Object j(int i10) {
        c();
        Object obj = ((u2) this.f2518l.remove(i10)).f2537l;
        if (!this.f2519m.isEmpty()) {
            Iterator it = h().entrySet().iterator();
            List list = this.f2518l;
            Map.Entry entry = (Map.Entry) it.next();
            list.add(new u2(this, (Comparable) entry.getKey(), entry.getValue()));
            it.remove();
        }
        return obj;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        c();
        Comparable comparable = (Comparable) obj;
        int iB = b(comparable);
        if (iB >= 0) {
            return j(iB);
        }
        if (this.f2519m.isEmpty()) {
            return null;
        }
        return this.f2519m.remove(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.f2519m.size() + this.f2518l.size();
    }
}
