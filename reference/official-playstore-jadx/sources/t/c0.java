package t;

import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class c0 extends h0 implements s7.h {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ d0 f21798m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c0(d0 d0Var) {
        super(d0Var, 1);
        this.f21798m = d0Var;
    }

    @Override // t.h0, java.util.Set, java.util.Collection
    public final boolean add(Object obj) {
        return this.f21798m.d(obj);
    }

    @Override // t.h0, java.util.Set, java.util.Collection
    public final boolean addAll(Collection collection) {
        d0 d0Var = this.f21798m;
        int i10 = d0Var.f21838d;
        for (Object obj : collection) {
            d0Var.f21836b[d0Var.f(obj)] = obj;
        }
        return i10 != d0Var.f21838d;
    }

    @Override // t.h0, java.util.Set, java.util.Collection
    public final void clear() {
        this.f21798m.e();
    }

    @Override // t.h0, java.util.Set, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new b0(this.f21798m);
    }

    @Override // t.h0, java.util.Set, java.util.Collection
    public final boolean remove(Object obj) {
        return this.f21798m.j(obj);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x008a, code lost:
    
        if (((r5 & ((~r5) << 6)) & r12) == 0) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x008c, code lost:
    
        r14 = -1;
     */
    @Override // t.h0, java.util.Set, java.util.Collection
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean removeAll(java.util.Collection r21) {
        /*
            r20 = this;
            r0 = r20
            t.d0 r1 = r0.f21798m
            int r2 = r1.f21838d
            java.util.Iterator r3 = r21.iterator()
        La:
            boolean r4 = r3.hasNext()
            r5 = 1
            r6 = 0
            if (r4 == 0) goto L9b
            java.lang.Object r4 = r3.next()
            if (r4 == 0) goto L20
            r1.getClass()
            int r7 = r4.hashCode()
            goto L21
        L20:
            r7 = r6
        L21:
            r8 = -862048943(0xffffffffcc9e2d51, float:-8.293031E7)
            int r7 = r7 * r8
            int r8 = r7 << 16
            r7 = r7 ^ r8
            r8 = r7 & 127(0x7f, float:1.78E-43)
            int r9 = r1.f21837c
            int r7 = r7 >>> 7
            r7 = r7 & r9
        L2f:
            long[] r10 = r1.f21835a
            int r11 = r7 >> 3
            r12 = r7 & 7
            int r12 = r12 << 3
            r13 = r10[r11]
            long r13 = r13 >>> r12
            int r11 = r11 + r5
            r15 = r10[r11]
            int r10 = 64 - r12
            long r10 = r15 << r10
            r21 = r5
            r15 = r6
            long r5 = (long) r12
            long r5 = -r5
            r12 = 63
            long r5 = r5 >> r12
            long r5 = r5 & r10
            long r5 = r5 | r13
            long r10 = (long) r8
            r12 = 72340172838076673(0x101010101010101, double:7.748604185489348E-304)
            long r10 = r10 * r12
            long r10 = r10 ^ r5
            long r12 = r10 - r12
            long r10 = ~r10
            long r10 = r10 & r12
            r12 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r10 = r10 & r12
        L5d:
            r16 = 0
            int r14 = (r10 > r16 ? 1 : (r10 == r16 ? 0 : -1))
            if (r14 == 0) goto L80
            int r14 = java.lang.Long.numberOfTrailingZeros(r10)
            int r14 = r14 >> 3
            int r14 = r14 + r7
            r14 = r14 & r9
            r18 = r12
            java.lang.Object[] r12 = r1.f21836b
            r12 = r12[r14]
            boolean r12 = kotlin.jvm.internal.p.a(r12, r4)
            if (r12 == 0) goto L78
            goto L8d
        L78:
            r12 = 1
            long r12 = r10 - r12
            long r10 = r10 & r12
            r12 = r18
            goto L5d
        L80:
            r18 = r12
            long r10 = ~r5
            r12 = 6
            long r10 = r10 << r12
            long r5 = r5 & r10
            long r5 = r5 & r18
            int r5 = (r5 > r16 ? 1 : (r5 == r16 ? 0 : -1))
            if (r5 == 0) goto L94
            r14 = -1
        L8d:
            if (r14 < 0) goto La
            r1.k(r14)
            goto La
        L94:
            int r6 = r15 + 8
            int r7 = r7 + r6
            r7 = r7 & r9
            r5 = r21
            goto L2f
        L9b:
            r21 = r5
            int r1 = r1.f21838d
            if (r2 == r1) goto La2
            return r21
        La2:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: t.c0.removeAll(java.util.Collection):boolean");
    }

    @Override // t.h0, java.util.Set, java.util.Collection
    public final boolean retainAll(Collection collection) {
        d0 d0Var = this.f21798m;
        long[] jArr = d0Var.f21835a;
        int length = jArr.length - 2;
        if (length < 0) {
            return false;
        }
        int i10 = 0;
        boolean z = false;
        while (true) {
            long j10 = jArr[i10];
            if ((((~j10) << 7) & j10 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i11 = 8 - ((~(i10 - length)) >>> 31);
                for (int i12 = 0; i12 < i11; i12++) {
                    if ((255 & j10) < 128) {
                        int i13 = (i10 << 3) + i12;
                        if (!collection.contains(d0Var.f21836b[i13])) {
                            d0Var.k(i13);
                            z = true;
                        }
                    }
                    j10 >>= 8;
                }
                if (i11 != 8) {
                    return z;
                }
            }
            if (i10 == length) {
                return z;
            }
            i10++;
        }
    }
}
