package io.ktor.websocket;

import d7.d;
import f7.e;
import f7.j;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.websocket.RawWebSocketCommon$writerJob$1", f = "RawWebSocketCommon.kt", l = {58, 60}, m = "invokeSuspend")
public final class RawWebSocketCommon$writerJob$1 extends j implements p<k0, d<? super t0>, Object> {
    Object L$0;
    int label;
    final /* synthetic */ RawWebSocketCommon this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RawWebSocketCommon$writerJob$1(RawWebSocketCommon rawWebSocketCommon, d<? super RawWebSocketCommon$writerJob$1> dVar) {
        super(2, dVar);
        this.this$0 = rawWebSocketCommon;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new RawWebSocketCommon$writerJob$1(this.this$0, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003b A[Catch: all -> 0x0015, ChannelWriteException -> 0x0018, PHI: r9
      0x003b: PHI (r9v15 java.lang.Object) = (r9v20 java.lang.Object), (r9v0 java.lang.Object) binds: [B:19:0x0038, B:15:0x0023] A[DONT_GENERATE, DONT_INLINE], TryCatch #3 {ChannelWriteException -> 0x0018, all -> 0x0015, blocks: (B:7:0x0011, B:27:0x005a, B:18:0x002a, B:21:0x003b, B:23:0x003f, B:31:0x0087, B:33:0x008b, B:34:0x0091, B:35:0x00a8, B:29:0x0067, B:15:0x0023), top: B:53:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x003f A[Catch: all -> 0x0015, ChannelWriteException -> 0x0018, TryCatch #3 {ChannelWriteException -> 0x0018, all -> 0x0015, blocks: (B:7:0x0011, B:27:0x005a, B:18:0x002a, B:21:0x003b, B:23:0x003f, B:31:0x0087, B:33:0x008b, B:34:0x0091, B:35:0x00a8, B:29:0x0067, B:15:0x0023), top: B:53:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0067 A[Catch: all -> 0x0015, ChannelWriteException -> 0x0018, TRY_LEAVE, TryCatch #3 {ChannelWriteException -> 0x0018, all -> 0x0015, blocks: (B:7:0x0011, B:27:0x005a, B:18:0x002a, B:21:0x003b, B:23:0x003f, B:31:0x0087, B:33:0x008b, B:34:0x0091, B:35:0x00a8, B:29:0x0067, B:15:0x0023), top: B:53:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0087 A[Catch: all -> 0x0015, ChannelWriteException -> 0x0018, TRY_ENTER, TryCatch #3 {ChannelWriteException -> 0x0018, all -> 0x0015, blocks: (B:7:0x0011, B:27:0x005a, B:18:0x002a, B:21:0x003b, B:23:0x003f, B:31:0x0087, B:33:0x008b, B:34:0x0091, B:35:0x00a8, B:29:0x0067, B:15:0x0023), top: B:53:0x0009 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0059 -> B:27:0x005a). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x008b -> B:18:0x002a). Please report as a decompilation issue!!! */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            Method dump skipped, instruction units count: 249
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.RawWebSocketCommon$writerJob$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super t0> dVar) {
        return ((RawWebSocketCommon$writerJob$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
