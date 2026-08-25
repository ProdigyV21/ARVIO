package ea;

import java.util.AbstractSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import kotlin.collections.r;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.q0;

/* JADX INFO: loaded from: classes5.dex */
public final class l extends AbstractSet {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final /* synthetic */ int f15095m = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Object f15096i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f15097l;

    /* JADX WARN: Code restructure failed: missing block: B:22:0x005f, code lost:
    
        if (kotlin.jvm.internal.q0.d(r4.f15096i).add(r5) == false) goto L23;
     */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean add(java.lang.Object r5) {
        /*
            r4 = this;
            int r0 = r4.f15097l
            r1 = 1
            if (r0 != 0) goto L8
            r4.f15096i = r5
            goto L62
        L8:
            r2 = 0
            if (r0 != r1) goto L20
            java.lang.Object r0 = r4.f15096i
            boolean r0 = kotlin.jvm.internal.p.a(r0, r5)
            if (r0 == 0) goto L14
            goto L61
        L14:
            java.lang.Object r0 = r4.f15096i
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r3[r2] = r0
            r3[r1] = r5
            r4.f15096i = r3
            goto L62
        L20:
            r3 = 5
            if (r0 >= r3) goto L55
            java.lang.Object r0 = r4.f15096i
            java.lang.Object[] r0 = (java.lang.Object[]) r0
            boolean r3 = kotlin.collections.r.J(r0, r5)
            if (r3 == 0) goto L2e
            goto L61
        L2e:
            int r2 = r4.f15097l
            r3 = 4
            if (r2 != r3) goto L49
            int r2 = r0.length
            java.lang.Object[] r0 = java.util.Arrays.copyOf(r0, r2)
            java.util.LinkedHashSet r2 = new java.util.LinkedHashSet
            int r3 = r0.length
            int r3 = kotlin.collections.i0.q0(r3)
            r2.<init>(r3)
            kotlin.collections.r.m0(r0, r2)
            r2.add(r5)
            goto L52
        L49:
            int r2 = r2 + r1
            java.lang.Object[] r2 = java.util.Arrays.copyOf(r0, r2)
            int r0 = r2.length
            int r0 = r0 - r1
            r2[r0] = r5
        L52:
            r4.f15096i = r2
            goto L62
        L55:
            java.lang.Object r0 = r4.f15096i
            java.util.Set r0 = kotlin.jvm.internal.q0.d(r0)
            boolean r5 = r0.add(r5)
            if (r5 != 0) goto L62
        L61:
            return r2
        L62:
            int r5 = r4.f15097l
            int r5 = r5 + r1
            r4.f15097l = r5
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: ea.l.add(java.lang.Object):boolean");
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.f15096i = null;
        this.f15097l = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        int i10 = this.f15097l;
        if (i10 == 0) {
            return false;
        }
        return i10 == 1 ? p.a(this.f15096i, obj) : i10 < 5 ? r.J((Object[]) this.f15096i, obj) : ((Set) this.f15096i).contains(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        int i10 = this.f15097l;
        return i10 == 0 ? Collections.EMPTY_SET.iterator() : i10 == 1 ? new k(this.f15096i) : i10 < 5 ? new j((Object[]) this.f15096i) : q0.d(this.f15096i).iterator();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.f15097l;
    }
}
