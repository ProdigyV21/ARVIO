package io.ktor.utils.io;

import d7.d;
import f7.c;
import f7.e;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a+\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001H\u0086\bø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a;\u0010\u0006\u001a\u00020\u0005*\u00020\b2\"\u0010\u0004\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\tH\u0086Hø\u0001\u0001¢\u0006\u0004\b\u0006\u0010\f\u0082\u0002\u000b\n\u0005\b\u009920\u0001\n\u0002\b\u0019¨\u0006\r"}, d2 = {"Lio/ktor/utils/io/LookAheadSession;", "Lkotlin/Function1;", "Ljava/nio/ByteBuffer;", "", "visitor", "Lx6/t0;", "consumeEachRemaining", "(Lio/ktor/utils/io/LookAheadSession;Lr7/l;)V", "Lio/ktor/utils/io/LookAheadSuspendSession;", "Lkotlin/Function2;", "Ld7/d;", "", "(Lio/ktor/utils/io/LookAheadSuspendSession;Lr7/p;Ld7/d;)Ljava/lang/Object;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LookAheadSessionKt {

    /* JADX INFO: renamed from: io.ktor.utils.io.LookAheadSessionKt$consumeEachRemaining$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
    @e(c = "io.ktor.utils.io.LookAheadSessionKt", f = "LookAheadSession.kt", l = {54, 59}, m = "consumeEachRemaining")
    public static final class AnonymousClass1 extends c {
        int I$0;
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
            return LookAheadSessionKt.consumeEachRemaining(null, null, this);
        }
    }

    public static final void consumeEachRemaining(LookAheadSession lookAheadSession, l<? super ByteBuffer, Boolean> lVar) {
        boolean z;
        do {
            z = false;
            ByteBuffer byteBufferRequest = lookAheadSession.request(0, 1);
            if (byteBufferRequest != null) {
                int iRemaining = byteBufferRequest.remaining();
                boolean zBooleanValue = ((Boolean) lVar.invoke(byteBufferRequest)).booleanValue();
                lookAheadSession.mo7011consumed(iRemaining);
                z = zBooleanValue;
            }
        } while (z);
    }

    private static final Object consumeEachRemaining$$forInline(LookAheadSuspendSession lookAheadSuspendSession, p<? super ByteBuffer, ? super d<? super Boolean>, ? extends Object> pVar, d<? super t0> dVar) {
        while (true) {
            ByteBuffer byteBufferRequest = lookAheadSuspendSession.request(0, 1);
            if (byteBufferRequest != null) {
                int iRemaining = byteBufferRequest.remaining();
                boolean zBooleanValue = ((Boolean) pVar.invoke(byteBufferRequest, dVar)).booleanValue();
                lookAheadSuspendSession.mo7011consumed(iRemaining);
                if (!zBooleanValue) {
                    break;
                }
            } else if (!((Boolean) lookAheadSuspendSession.awaitAtLeast(1, dVar)).booleanValue()) {
                break;
            }
        }
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x006a -> B:17:0x0049). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x0081 -> B:30:0x0083). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object consumeEachRemaining(io.ktor.utils.io.LookAheadSuspendSession r6, r7.p<? super java.nio.ByteBuffer, ? super d7.d<? super java.lang.Boolean>, ? extends java.lang.Object> r7, d7.d<? super x6.t0> r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.utils.io.LookAheadSessionKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r8
            io.ktor.utils.io.LookAheadSessionKt$consumeEachRemaining$1 r0 = (io.ktor.utils.io.LookAheadSessionKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.LookAheadSessionKt$consumeEachRemaining$1 r0 = new io.ktor.utils.io.LookAheadSessionKt$consumeEachRemaining$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L46
            if (r1 == r3) goto L3a
            if (r1 != r2) goto L32
            int r6 = r0.I$0
            java.lang.Object r7 = r0.L$1
            r7.p r7 = (r7.p) r7
            java.lang.Object r1 = r0.L$0
            io.ktor.utils.io.LookAheadSuspendSession r1 = (io.ktor.utils.io.LookAheadSuspendSession) r1
            k2.c.G(r8)
            goto L83
        L32:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3a:
            java.lang.Object r6 = r0.L$1
            r7.p r6 = (r7.p) r6
            java.lang.Object r7 = r0.L$0
            io.ktor.utils.io.LookAheadSuspendSession r7 = (io.ktor.utils.io.LookAheadSuspendSession) r7
            k2.c.G(r8)
            goto L62
        L46:
            k2.c.G(r8)
        L49:
            r8 = 0
            java.nio.ByteBuffer r8 = r6.request(r8, r3)
            e7.a r1 = e7.a.f15033i
            if (r8 != 0) goto L6e
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r8 = r6.awaitAtLeast(r3, r0)
            if (r8 != r1) goto L5f
            goto L80
        L5f:
            r5 = r7
            r7 = r6
            r6 = r5
        L62:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto L90
            r5 = r7
            r7 = r6
            r6 = r5
            goto L49
        L6e:
            int r4 = r8.remaining()
            r0.L$0 = r6
            r0.L$1 = r7
            r0.I$0 = r4
            r0.label = r2
            java.lang.Object r8 = r7.invoke(r8, r0)
            if (r8 != r1) goto L81
        L80:
            return r1
        L81:
            r1 = r6
            r6 = r4
        L83:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            r1.mo7011consumed(r6)
            if (r8 == 0) goto L90
            r6 = r1
            goto L49
        L90:
            x6.t0 r6 = x6.t0.f22605a
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.LookAheadSessionKt.consumeEachRemaining(io.ktor.utils.io.LookAheadSuspendSession, r7.p, d7.d):java.lang.Object");
    }
}
