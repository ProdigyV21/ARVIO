package kotlin.reflect.jvm.internal.impl.protobuf;

import androidx.datastore.preferences.protobuf.w2;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes5.dex */
public final class i0 extends AbstractMap {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final /* synthetic */ int f19792p = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f19793i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public List f19794l = Collections.EMPTY_LIST;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Map f19795m = Collections.EMPTY_MAP;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f19796n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public volatile w2 f19797o;

    public i0(int i10) {
        this.f19793i = i10;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int b(java.lang.Comparable r5) {
        /*
            r4 = this;
            java.util.List r0 = r4.f19794l
            int r0 = r0.size()
            int r1 = r0 + (-1)
            if (r1 < 0) goto L21
            java.util.List r2 = r4.f19794l
            java.lang.Object r2 = r2.get(r1)
            kotlin.reflect.jvm.internal.impl.protobuf.m0 r2 = (kotlin.reflect.jvm.internal.impl.protobuf.m0) r2
            java.lang.Comparable r2 = r2.f19808i
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
            java.util.List r3 = r4.f19794l
            java.lang.Object r3 = r3.get(r2)
            kotlin.reflect.jvm.internal.impl.protobuf.m0 r3 = (kotlin.reflect.jvm.internal.impl.protobuf.m0) r3
            java.lang.Comparable r3 = r3.f19808i
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
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.protobuf.i0.b(java.lang.Comparable):int");
    }

    public final void c() {
        if (this.f19796n) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        c();
        if (!this.f19794l.isEmpty()) {
            this.f19794l.clear();
        }
        if (this.f19795m.isEmpty()) {
            return;
        }
        this.f19795m.clear();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return b(comparable) >= 0 || this.f19795m.containsKey(comparable);
    }

    public final Iterable e() {
        return this.f19795m.isEmpty() ? l0.f19807b : this.f19795m.entrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        if (this.f19797o == null) {
            this.f19797o = new w2(1, this);
        }
        return this.f19797o;
    }

    public final SortedMap g() {
        c();
        if (this.f19795m.isEmpty() && !(this.f19795m instanceof TreeMap)) {
            this.f19795m = new TreeMap();
        }
        return (SortedMap) this.f19795m;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int iB = b(comparable);
        return iB >= 0 ? ((m0) this.f19794l.get(iB)).f19809l : this.f19795m.get(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public final Object put(Comparable comparable, Object obj) {
        c();
        int iB = b(comparable);
        if (iB >= 0) {
            return ((m0) this.f19794l.get(iB)).setValue(obj);
        }
        c();
        boolean zIsEmpty = this.f19794l.isEmpty();
        int i10 = this.f19793i;
        if (zIsEmpty && !(this.f19794l instanceof ArrayList)) {
            this.f19794l = new ArrayList(i10);
        }
        int i11 = -(iB + 1);
        if (i11 >= i10) {
            return g().put(comparable, obj);
        }
        if (this.f19794l.size() == i10) {
            m0 m0Var = (m0) this.f19794l.remove(i10 - 1);
            g().put(m0Var.f19808i, m0Var.f19809l);
        }
        this.f19794l.add(i11, new m0(this, comparable, obj));
        return null;
    }

    public final Object i(int i10) {
        c();
        Object obj = ((m0) this.f19794l.remove(i10)).f19809l;
        if (!this.f19795m.isEmpty()) {
            Iterator it = g().entrySet().iterator();
            List list = this.f19794l;
            Map.Entry entry = (Map.Entry) it.next();
            list.add(new m0(this, (Comparable) entry.getKey(), entry.getValue()));
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
            return i(iB);
        }
        if (this.f19795m.isEmpty()) {
            return null;
        }
        return this.f19795m.remove(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.f19795m.size() + this.f19794l.size();
    }
}
