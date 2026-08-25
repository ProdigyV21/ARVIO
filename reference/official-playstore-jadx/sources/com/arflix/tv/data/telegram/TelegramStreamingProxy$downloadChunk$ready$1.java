package com.arflix.tv.data.telegram;

import f7.j;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Z"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.telegram.TelegramStreamingProxy$downloadChunk$ready$1", f = "TelegramStreamingProxy.kt", l = {271, 276}, m = "invokeSuspend", v = 2)
public final class TelegramStreamingProxy$downloadChunk$ready$1 extends j implements p<k0, d7.d<? super Boolean>, Object> {
    final /* synthetic */ int $fileId;
    final /* synthetic */ int $limit;
    int I$0;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ TelegramStreamingProxy this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TelegramStreamingProxy$downloadChunk$ready$1(TelegramStreamingProxy telegramStreamingProxy, int i10, int i11, d7.d<? super TelegramStreamingProxy$downloadChunk$ready$1> dVar) {
        super(2, dVar);
        this.this$0 = telegramStreamingProxy;
        this.$fileId = i10;
        this.$limit = i11;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TelegramStreamingProxy$downloadChunk$ready$1(this.this$0, this.$fileId, this.$limit, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0086, code lost:
    
        if (ka.s0.a(100, r13) != r4) goto L34;
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x008c  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x0086 -> B:34:0x0089). Please report as a decompilation issue!!! */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            r13 = this;
            int r0 = r13.label
            r1 = 2
            r2 = 1
            r3 = 0
            e7.a r4 = e7.a.f15033i
            if (r0 == 0) goto L2c
            if (r0 == r2) goto L25
            if (r0 != r1) goto L1d
            int r0 = r13.I$0
            java.lang.Object r5 = r13.L$1
            org.drinkless.tdlib.TdApi$LocalFile r5 = (org.drinkless.tdlib.TdApi.LocalFile) r5
            java.lang.Object r5 = r13.L$0
            org.drinkless.tdlib.TdApi$File r5 = (org.drinkless.tdlib.TdApi.File) r5
            k2.c.G(r14)
            r9 = r13
            goto L89
        L1d:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L25:
            int r0 = r13.I$0
            k2.c.G(r14)
            r9 = r13
            goto L58
        L2c:
            k2.c.G(r14)
            r14 = 0
        L30:
            r0 = 300(0x12c, float:4.2E-43)
            if (r14 >= r0) goto L8c
            com.arflix.tv.data.telegram.TelegramStreamingProxy r0 = r13.this$0
            com.arflix.tv.data.telegram.TelegramClient r5 = com.arflix.tv.data.telegram.TelegramStreamingProxy.access$getClient$p(r0)
            org.drinkless.tdlib.TdApi$GetFile r6 = new org.drinkless.tdlib.TdApi$GetFile
            int r0 = r13.$fileId
            r6.<init>(r0)
            r13.L$0 = r3
            r13.L$1 = r3
            r13.I$0 = r14
            r13.label = r2
            r7 = 0
            r10 = 2
            r11 = 0
            r9 = r13
            java.lang.Object r0 = com.arflix.tv.data.telegram.TelegramClient.sendRequest$default(r5, r6, r7, r9, r10, r11)
            if (r0 != r4) goto L55
            goto L88
        L55:
            r12 = r0
            r0 = r14
            r14 = r12
        L58:
            boolean r5 = r14 instanceof org.drinkless.tdlib.TdApi.File
            if (r5 == 0) goto L5f
            org.drinkless.tdlib.TdApi$File r14 = (org.drinkless.tdlib.TdApi.File) r14
            goto L60
        L5f:
            r14 = r3
        L60:
            if (r14 == 0) goto L65
            org.drinkless.tdlib.TdApi$LocalFile r14 = r14.local
            goto L66
        L65:
            r14 = r3
        L66:
            if (r14 == 0) goto L78
            boolean r5 = r14.isDownloadingCompleted
            if (r5 != 0) goto L75
            long r5 = r14.downloadedPrefixSize
            int r14 = r9.$limit
            long r7 = (long) r14
            int r14 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r14 < 0) goto L78
        L75:
            java.lang.Boolean r14 = java.lang.Boolean.TRUE
            return r14
        L78:
            r9.L$0 = r3
            r9.L$1 = r3
            r9.I$0 = r0
            r9.label = r1
            r5 = 100
            java.lang.Object r14 = ka.s0.a(r5, r13)
            if (r14 != r4) goto L89
        L88:
            return r4
        L89:
            int r14 = r0 + 1
            goto L30
        L8c:
            r9 = r13
            java.lang.Boolean r14 = java.lang.Boolean.FALSE
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.telegram.TelegramStreamingProxy$downloadChunk$ready$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super Boolean> dVar) {
        return ((TelegramStreamingProxy$downloadChunk$ready$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
