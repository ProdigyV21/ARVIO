package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.StreamSource;
import com.arflix.tv.network.OkHttpProvider;
import java.util.Map;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.StreamRepository$warmHttpConnection$7$1", f = "StreamRepository.kt", l = {}, m = "invokeSuspend", v = 2)
public final class StreamRepository$warmHttpConnection$7$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ Map<String, String> $headers;
    final /* synthetic */ String $rawUrl;
    final /* synthetic */ StreamSource $stream;
    final /* synthetic */ StreamRepository $this_runCatching;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StreamRepository$warmHttpConnection$7$1(String str, Map<String, String> map, StreamRepository streamRepository, StreamSource streamSource, d7.d<? super StreamRepository$warmHttpConnection$7$1> dVar) {
        super(2, dVar);
        this.$rawUrl = str;
        this.$headers = map;
        this.$this_runCatching = streamRepository;
        this.$stream = streamSource;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new StreamRepository$warmHttpConnection$7$1(this.$rawUrl, this.$headers, this.$this_runCatching, this.$stream, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        gb.j0 j0Var = new gb.j0();
        j0Var.i(this.$rawUrl);
        j0Var.d();
        for (Map.Entry<String, String> entry : this.$headers.entrySet()) {
            j0Var.a(entry.getKey(), entry.getValue());
        }
        gb.p0 p0VarD = OkHttpProvider.INSTANCE.getPlaybackClient().a(j0Var.b()).d();
        StreamRepository streamRepository = this.$this_runCatching;
        StreamSource streamSource = this.$stream;
        try {
            if (p0VarD.k() || (i10 = p0VarD.f15783n) == 206 || i10 == 416) {
                streamRepository.notePlaybackHostSuccess(streamSource);
            } else {
                streamRepository.notePlaybackHostFailure(streamSource, "prewarm_http_" + i10);
            }
            p0VarD.close();
            return t0.f22605a;
        } finally {
        }
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((StreamRepository$warmHttpConnection$7$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
