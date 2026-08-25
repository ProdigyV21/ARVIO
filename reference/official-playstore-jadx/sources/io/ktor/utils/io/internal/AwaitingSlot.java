package io.ktor.utils.io.internal;

import d7.d;
import f7.c;
import f7.e;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import ka.t;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\u0007\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ!\u0010\n\u001a\u00020\t2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0086@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\bJ\r\u0010\u000b\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\u0003J\u0017\u0010\u000e\u001a\u00020\t2\b\u0010\r\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u000e\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"Lio/ktor/utils/io/internal/AwaitingSlot;", "", "<init>", "()V", "Lkotlin/Function0;", "", "sleepCondition", "trySuspend", "(Lr7/a;Ld7/d;)Ljava/lang/Object;", "Lx6/t0;", "sleep", "resume", "", "cause", "cancel", "(Ljava/lang/Throwable;)V", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AwaitingSlot {
    private static final /* synthetic */ AtomicReferenceFieldUpdater suspension$FU = AtomicReferenceFieldUpdater.newUpdater(AwaitingSlot.class, Object.class, "suspension");
    private volatile /* synthetic */ Object suspension = null;

    /* JADX INFO: renamed from: io.ktor.utils.io.internal.AwaitingSlot$sleep$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.internal.AwaitingSlot", f = "AwaitingSlot.kt", l = {24}, m = "sleep")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AwaitingSlot.this.sleep(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.internal.AwaitingSlot$trySuspend$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.internal.AwaitingSlot", f = "AwaitingSlot.kt", l = {57}, m = "trySuspend")
    public static final class C20471 extends c {
        int I$0;
        int label;
        /* synthetic */ Object result;

        public C20471(d<? super C20471> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AwaitingSlot.this.trySuspend(null, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object trySuspend(r7.a<java.lang.Boolean> r6, d7.d<? super java.lang.Boolean> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.ktor.utils.io.internal.AwaitingSlot.C20471
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.utils.io.internal.AwaitingSlot$trySuspend$1 r0 = (io.ktor.utils.io.internal.AwaitingSlot.C20471) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.internal.AwaitingSlot$trySuspend$1 r0 = new io.ktor.utils.io.internal.AwaitingSlot$trySuspend$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L30
            if (r1 != r3) goto L28
            int r6 = r0.I$0
            k2.c.G(r7)
            goto L62
        L28:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L30:
            k2.c.G(r7)
            ka.x1 r7 = ka.m0.c()
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = io.ktor.utils.io.internal.AwaitingSlot.suspension$FU
        L39:
            r4 = 0
            boolean r4 = r1.compareAndSet(r5, r4, r7)
            if (r4 == 0) goto L5b
            java.lang.Object r6 = r6.invoke()
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L61
            r0.I$0 = r3
            r0.label = r3
            java.lang.Object r6 = r7.join(r0)
            e7.a r7 = e7.a.f15033i
            if (r6 != r7) goto L59
            return r7
        L59:
            r6 = r3
            goto L62
        L5b:
            java.lang.Object r4 = r1.get(r5)
            if (r4 == 0) goto L39
        L61:
            r6 = r2
        L62:
            if (r6 == 0) goto L65
            r2 = r3
        L65:
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r2)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.internal.AwaitingSlot.trySuspend(r7.a, d7.d):java.lang.Object");
    }

    public final void cancel(Throwable cause) {
        t tVar = (t) suspension$FU.getAndSet(this, null);
        if (tVar == null) {
            return;
        }
        if (cause != null) {
            tVar.i(cause);
        } else {
            tVar.complete();
        }
    }

    public final void resume() {
        t tVar = (t) suspension$FU.getAndSet(this, null);
        if (tVar != null) {
            tVar.complete();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object sleep(r7.a<java.lang.Boolean> r5, d7.d<? super x6.t0> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.utils.io.internal.AwaitingSlot.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.utils.io.internal.AwaitingSlot$sleep$1 r0 = (io.ktor.utils.io.internal.AwaitingSlot.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.internal.AwaitingSlot$sleep$1 r0 = new io.ktor.utils.io.internal.AwaitingSlot$sleep$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r5 = r0.L$0
            io.ktor.utils.io.internal.AwaitingSlot r5 = (io.ktor.utils.io.internal.AwaitingSlot) r5
            k2.c.G(r6)
            goto L42
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            k2.c.G(r6)
            r0.L$0 = r4
            r0.label = r2
            java.lang.Object r6 = r4.trySuspend(r5, r0)
            e7.a r5 = e7.a.f15033i
            if (r6 != r5) goto L41
            return r5
        L41:
            r5 = r4
        L42:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            x6.t0 r0 = x6.t0.f22605a
            if (r6 == 0) goto L4d
            return r0
        L4d:
            r5.resume()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.internal.AwaitingSlot.sleep(r7.a, d7.d):java.lang.Object");
    }
}
