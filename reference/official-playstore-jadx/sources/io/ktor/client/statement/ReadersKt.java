package io.ktor.client.statement;

import d7.d;
import e7.a;
import f7.c;
import f7.e;
import io.ktor.utils.io.ByteReadChannelKt;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001f\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u0017\u0010\u0004\u001a\u00020\u0003*\u00020\u0000H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0006\u001a\u0017\u0010\b\u001a\u00020\u0007*\u00020\u0000H\u0086@ø\u0001\u0000¢\u0006\u0004\b\b\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, d2 = {"Lio/ktor/client/statement/HttpResponse;", "", "count", "", "readBytes", "(Lio/ktor/client/statement/HttpResponse;ILd7/d;)Ljava/lang/Object;", "(Lio/ktor/client/statement/HttpResponse;Ld7/d;)Ljava/lang/Object;", "Lx6/t0;", "discardRemaining", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ReadersKt {

    /* JADX INFO: renamed from: io.ktor.client.statement.ReadersKt$readBytes$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.client.statement.ReadersKt", f = "Readers.kt", l = {16}, m = "readBytes")
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
            return ReadersKt.readBytes(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.statement.ReadersKt$readBytes$3, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.client.statement.ReadersKt", f = "Readers.kt", l = {24}, m = "readBytes")
    public static final class AnonymousClass3 extends c {
        int label;
        /* synthetic */ Object result;

        public AnonymousClass3(d<? super AnonymousClass3> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ReadersKt.readBytes(null, this);
        }
    }

    public static final Object discardRemaining(HttpResponse httpResponse, d<? super t0> dVar) {
        Object objDiscard = ByteReadChannelKt.discard(httpResponse.getContent(), dVar);
        return objDiscard == a.f15033i ? objDiscard : t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readBytes(io.ktor.client.statement.HttpResponse r4, int r5, d7.d<? super byte[]> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.client.statement.ReadersKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.client.statement.ReadersKt$readBytes$1 r0 = (io.ktor.client.statement.ReadersKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.statement.ReadersKt$readBytes$1 r0 = new io.ktor.client.statement.ReadersKt$readBytes$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r4 = r0.L$0
            byte[] r4 = (byte[]) r4
            k2.c.G(r6)
            return r4
        L29:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L31:
            k2.c.G(r6)
            byte[] r5 = new byte[r5]
            io.ktor.utils.io.ByteReadChannel r4 = r4.getContent()
            r0.L$0 = r5
            r0.label = r2
            java.lang.Object r4 = io.ktor.utils.io.ByteReadChannelKt.readFully(r4, r5, r0)
            e7.a r6 = e7.a.f15033i
            if (r4 != r6) goto L47
            return r6
        L47:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.statement.ReadersKt.readBytes(io.ktor.client.statement.HttpResponse, int, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readBytes(io.ktor.client.statement.HttpResponse r8, d7.d<? super byte[]> r9) {
        /*
            boolean r0 = r9 instanceof io.ktor.client.statement.ReadersKt.AnonymousClass3
            if (r0 == 0) goto L14
            r0 = r9
            io.ktor.client.statement.ReadersKt$readBytes$3 r0 = (io.ktor.client.statement.ReadersKt.AnonymousClass3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.label = r1
        L12:
            r4 = r0
            goto L1a
        L14:
            io.ktor.client.statement.ReadersKt$readBytes$3 r0 = new io.ktor.client.statement.ReadersKt$readBytes$3
            r0.<init>(r9)
            goto L12
        L1a:
            java.lang.Object r9 = r4.result
            int r0 = r4.label
            r7 = 1
            if (r0 == 0) goto L2f
            if (r0 != r7) goto L27
            k2.c.G(r9)
            goto L45
        L27:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2f:
            k2.c.G(r9)
            io.ktor.utils.io.ByteReadChannel r1 = r8.getContent()
            r4.label = r7
            r2 = 0
            r5 = 1
            r6 = 0
            java.lang.Object r9 = io.ktor.utils.io.ByteReadChannel.DefaultImpls.readRemaining$default(r1, r2, r4, r5, r6)
            e7.a r8 = e7.a.f15033i
            if (r9 != r8) goto L45
            return r8
        L45:
            io.ktor.utils.io.core.ByteReadPacket r9 = (io.ktor.utils.io.core.ByteReadPacket) r9
            r8 = 0
            r0 = 0
            byte[] r8 = io.ktor.utils.io.core.StringsKt.readBytes$default(r9, r8, r7, r0)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.statement.ReadersKt.readBytes(io.ktor.client.statement.HttpResponse, d7.d):java.lang.Object");
    }
}
