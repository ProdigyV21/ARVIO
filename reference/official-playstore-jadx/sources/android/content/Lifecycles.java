package android.content;

import androidx.lifecycle.r;
import androidx.lifecycle.x;
import d7.d;
import f7.c;
import f7.e;
import kotlin.Metadata;

/* JADX INFO: renamed from: coil.util.-Lifecycles, reason: invalid class name */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0017\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0081@ø\u0001\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u001b\u0010\u0006\u001a\u00020\u0001*\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004H\u0001¢\u0006\u0004\b\u0006\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, d2 = {"Landroidx/lifecycle/r;", "Lx6/t0;", "awaitStarted", "(Landroidx/lifecycle/r;Ld7/d;)Ljava/lang/Object;", "Landroidx/lifecycle/x;", "observer", "removeAndAddObserver", "(Landroidx/lifecycle/r;Landroidx/lifecycle/x;)V", "coil-base_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class Lifecycles {

    /* JADX INFO: renamed from: coil.util.-Lifecycles$awaitStarted$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "coil.util.-Lifecycles", f = "Lifecycles.kt", l = {44}, m = "awaitStarted")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Lifecycles.awaitStarted(null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object awaitStarted(androidx.lifecycle.r r5, d7.d<? super x6.t0> r6) throws java.lang.Throwable {
        /*
            boolean r0 = r6 instanceof android.content.Lifecycles.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            coil.util.-Lifecycles$awaitStarted$1 r0 = (android.content.Lifecycles.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            coil.util.-Lifecycles$awaitStarted$1 r0 = new coil.util.-Lifecycles$awaitStarted$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            x6.t0 r2 = x6.t0.f22605a
            r3 = 1
            if (r1 == 0) goto L39
            if (r1 != r3) goto L31
            java.lang.Object r5 = r0.L$1
            kotlin.jvm.internal.k0 r5 = (kotlin.jvm.internal.k0) r5
            java.lang.Object r0 = r0.L$0
            androidx.lifecycle.r r0 = (androidx.lifecycle.r) r0
            k2.c.G(r6)     // Catch: java.lang.Throwable -> L2f
            goto L77
        L2f:
            r6 = move-exception
            goto L86
        L31:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L39:
            k2.c.G(r6)
            androidx.lifecycle.q r6 = r5.getCurrentState()
            androidx.lifecycle.q r1 = androidx.lifecycle.q.f3269n
            boolean r6 = r6.a(r1)
            if (r6 == 0) goto L49
            return r2
        L49:
            kotlin.jvm.internal.k0 r6 = new kotlin.jvm.internal.k0
            r6.<init>()
            r0.L$0 = r5     // Catch: java.lang.Throwable -> L81
            r0.L$1 = r6     // Catch: java.lang.Throwable -> L81
            r0.label = r3     // Catch: java.lang.Throwable -> L81
            ka.l r1 = new ka.l     // Catch: java.lang.Throwable -> L81
            d7.d r0 = t7.a.A(r0)     // Catch: java.lang.Throwable -> L81
            r1.<init>(r3, r0)     // Catch: java.lang.Throwable -> L81
            r1.s()     // Catch: java.lang.Throwable -> L81
            coil.util.-Lifecycles$awaitStarted$2$1 r0 = new coil.util.-Lifecycles$awaitStarted$2$1     // Catch: java.lang.Throwable -> L81
            r0.<init>()     // Catch: java.lang.Throwable -> L81
            r6.f19746i = r0     // Catch: java.lang.Throwable -> L81
            androidx.lifecycle.x r0 = (androidx.lifecycle.x) r0     // Catch: java.lang.Throwable -> L81
            r5.addObserver(r0)     // Catch: java.lang.Throwable -> L81
            java.lang.Object r0 = r1.r()     // Catch: java.lang.Throwable -> L81
            e7.a r1 = e7.a.f15033i
            if (r0 != r1) goto L75
            return r1
        L75:
            r0 = r5
            r5 = r6
        L77:
            java.lang.Object r5 = r5.f19746i
            androidx.lifecycle.x r5 = (androidx.lifecycle.x) r5
            if (r5 == 0) goto L80
            r0.removeObserver(r5)
        L80:
            return r2
        L81:
            r0 = move-exception
            r4 = r0
            r0 = r5
            r5 = r6
            r6 = r4
        L86:
            java.lang.Object r5 = r5.f19746i
            androidx.lifecycle.x r5 = (androidx.lifecycle.x) r5
            if (r5 == 0) goto L8f
            r0.removeObserver(r5)
        L8f:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.Lifecycles.awaitStarted(androidx.lifecycle.r, d7.d):java.lang.Object");
    }

    public static final void removeAndAddObserver(r rVar, x xVar) {
        rVar.removeObserver(xVar);
        rVar.addObserver(xVar);
    }
}
