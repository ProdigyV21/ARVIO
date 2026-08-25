package io.ktor.client.call;

import d7.d;
import f7.c;
import f7.e;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0017\u0010\u0001\u001a\u00020\u0000*\u00020\u0000H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0001\u0010\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0003"}, d2 = {"Lio/ktor/client/call/HttpClientCall;", "save", "(Lio/ktor/client/call/HttpClientCall;Ld7/d;)Ljava/lang/Object;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SavedCallKt {

    /* JADX INFO: renamed from: io.ktor.client.call.SavedCallKt$save$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.client.call.SavedCallKt", f = "SavedCall.kt", l = {73}, m = "save")
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
            return SavedCallKt.save(null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object save(io.ktor.client.call.HttpClientCall r8, d7.d<? super io.ktor.client.call.HttpClientCall> r9) {
        /*
            boolean r0 = r9 instanceof io.ktor.client.call.SavedCallKt.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r9
            io.ktor.client.call.SavedCallKt$save$1 r0 = (io.ktor.client.call.SavedCallKt.AnonymousClass1) r0
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
            io.ktor.client.call.SavedCallKt$save$1 r0 = new io.ktor.client.call.SavedCallKt$save$1
            r0.<init>(r9)
            goto L12
        L1a:
            java.lang.Object r9 = r4.result
            int r0 = r4.label
            r7 = 1
            if (r0 == 0) goto L33
            if (r0 != r7) goto L2b
            java.lang.Object r8 = r4.L$0
            io.ktor.client.call.HttpClientCall r8 = (io.ktor.client.call.HttpClientCall) r8
            k2.c.G(r9)
            goto L4f
        L2b:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L33:
            k2.c.G(r9)
            io.ktor.client.statement.HttpResponse r9 = r8.getResponse()
            io.ktor.utils.io.ByteReadChannel r1 = r9.getContent()
            r4.L$0 = r8
            r4.label = r7
            r2 = 0
            r5 = 1
            r6 = 0
            java.lang.Object r9 = io.ktor.utils.io.ByteReadChannel.DefaultImpls.readRemaining$default(r1, r2, r4, r5, r6)
            e7.a r0 = e7.a.f15033i
            if (r9 != r0) goto L4f
            return r0
        L4f:
            io.ktor.utils.io.core.ByteReadPacket r9 = (io.ktor.utils.io.core.ByteReadPacket) r9
            r0 = 0
            r1 = 0
            byte[] r9 = io.ktor.utils.io.core.StringsKt.readBytes$default(r9, r0, r7, r1)
            io.ktor.client.call.SavedHttpCall r0 = new io.ktor.client.call.SavedHttpCall
            io.ktor.client.HttpClient r1 = r8.getClient()
            io.ktor.client.request.HttpRequest r2 = r8.getRequest()
            io.ktor.client.statement.HttpResponse r8 = r8.getResponse()
            r0.<init>(r1, r2, r8, r9)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.call.SavedCallKt.save(io.ktor.client.call.HttpClientCall, d7.d):java.lang.Object");
    }
}
