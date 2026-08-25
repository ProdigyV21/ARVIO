package ma;

import kotlin.jvm.internal.l0;
import x6.t0;

/* JADX INFO: loaded from: classes5.dex */
public final class q extends f {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final int f20350u;

    public q(int i10, int i11) {
        super(i10);
        this.f20350u = i11;
        if (i11 != 1) {
            if (i10 < 1) {
                throw new IllegalArgumentException(androidx.compose.foundation.c.o(i10, "Buffered channel capacity must be at least 1, but ", " was specified").toString());
            }
        } else {
            throw new IllegalArgumentException(("This implementation does not support suspension for senders, use " + l0.f19747a.b(f.class).r() + " instead").toString());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x00b5, code lost:
    
        return r8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object E(java.lang.Object r17, boolean r18) {
        /*
            r16 = this;
            r0 = r16
            x6.t0 r8 = x6.t0.f22605a
            int r1 = r0.f20350u
            r9 = 3
            if (r1 != r9) goto L18
            java.lang.Object r1 = super.mo6685trySendJP2dKIU(r17)
            boolean r2 = r1 instanceof ma.n
            if (r2 == 0) goto L17
            boolean r2 = r1 instanceof ma.m
            if (r2 == 0) goto L16
            goto L17
        L16:
            return r8
        L17:
            return r1
        L18:
            c2.a r6 = ma.h.f20326d
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = ma.f.f20316p
            java.lang.Object r1 = r1.get(r0)
            ma.p r1 = (ma.p) r1
        L22:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r2 = ma.f.f20312l
            long r2 = r2.getAndIncrement(r0)
            r4 = 1152921504606846975(0xfffffffffffffff, double:1.2882297539194265E-231)
            long r4 = r4 & r2
            r7 = 0
            boolean r7 = r0.q(r2, r7)
            int r10 = ma.h.f20324b
            long r11 = (long) r10
            long r2 = r4 / r11
            long r13 = r4 % r11
            int r13 = (int) r13
            long r14 = r1.f21298m
            int r14 = (r14 > r2 ? 1 : (r14 == r2 ? 0 : -1))
            if (r14 == 0) goto L54
            ma.p r2 = ma.f.a(r0, r2, r1)
            if (r2 != 0) goto L53
            if (r7 == 0) goto L22
            java.lang.Throwable r1 = r0.n()
            ma.m r2 = new ma.m
            r2.<init>(r1)
            return r2
        L53:
            r1 = r2
        L54:
            r3 = r17
            r2 = r13
            int r13 = ma.f.d(r0, r1, r2, r3, r4, r6, r7)
            if (r13 == 0) goto Lb6
            r3 = 1
            if (r13 == r3) goto Lb5
            r3 = 2
            if (r13 == r3) goto L8f
            if (r13 == r9) goto L87
            r2 = 4
            if (r13 == r2) goto L70
            r2 = 5
            if (r13 == r2) goto L6c
            goto L22
        L6c:
            r1.a()
            goto L22
        L70:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r2 = ma.f.f20313m
            long r2 = r2.get(r0)
            int r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r2 >= 0) goto L7d
            r1.a()
        L7d:
            java.lang.Throwable r1 = r0.n()
            ma.m r2 = new ma.m
            r2.<init>(r1)
            return r2
        L87:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "unexpected"
            r1.<init>(r2)
            throw r1
        L8f:
            if (r7 == 0) goto L9e
            r1.h()
            java.lang.Throwable r1 = r0.n()
            ma.m r2 = new ma.m
            r2.<init>(r1)
            return r2
        L9e:
            boolean r3 = r6 instanceof ka.y2
            if (r3 == 0) goto La5
            ka.y2 r6 = (ka.y2) r6
            goto La6
        La5:
            r6 = 0
        La6:
            if (r6 == 0) goto Lad
            int r13 = r2 + r10
            r6.e(r1, r13)
        Lad:
            long r3 = r1.f21298m
            long r3 = r3 * r11
            long r1 = (long) r2
            long r3 = r3 + r1
            r0.i(r3)
        Lb5:
            return r8
        Lb6:
            r1.a()
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: ma.q.E(java.lang.Object, boolean):java.lang.Object");
    }

    @Override // ma.f
    public final boolean s() {
        return this.f20350u == 2;
    }

    @Override // ma.f, ma.x
    public final Object send(Object obj, d7.d dVar) throws Throwable {
        if (E(obj, true) instanceof m) {
            throw n();
        }
        return t0.f22605a;
    }

    @Override // ma.f, ma.x
    /* JADX INFO: renamed from: trySend-JP2dKIU */
    public final Object mo6685trySendJP2dKIU(Object obj) {
        return E(obj, false);
    }
}
