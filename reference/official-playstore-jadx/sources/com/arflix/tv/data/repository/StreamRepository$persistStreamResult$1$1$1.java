package com.arflix.tv.data.repository;

import com.arflix.tv.data.repository.StreamRepository;
import java.util.Map;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.StreamRepository$persistStreamResult$1$1$1", f = "StreamRepository.kt", l = {}, m = "invokeSuspend", v = 2)
public final class StreamRepository$persistStreamResult$1$1$1 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
    final /* synthetic */ c1.e $bundleKey;
    final /* synthetic */ Map<String, StreamRepository.PersistedStreamResultPayload> $trimmed;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ StreamRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StreamRepository$persistStreamResult$1$1$1(Map<String, StreamRepository.PersistedStreamResultPayload> map, c1.e eVar, StreamRepository streamRepository, d7.d<? super StreamRepository$persistStreamResult$1$1$1> dVar) {
        super(2, dVar);
        this.$trimmed = map;
        this.$bundleKey = eVar;
        this.this$0 = streamRepository;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        StreamRepository$persistStreamResult$1$1$1 streamRepository$persistStreamResult$1$1$1 = new StreamRepository$persistStreamResult$1$1$1(this.$trimmed, this.$bundleKey, this.this$0, dVar);
        streamRepository$persistStreamResult$1$1$1.L$0 = obj;
        return streamRepository$persistStreamResult$1$1$1;
    }

    @Override // r7.p
    public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
        return ((StreamRepository$persistStreamResult$1$1$1) create(bVar, dVar)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        c1.b bVar = (c1.b) this.L$0;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        if (this.$trimmed.isEmpty()) {
            bVar.c(this.$bundleKey);
        } else {
            bVar.d(this.$bundleKey, this.this$0.gson.toJson(this.$trimmed));
        }
        return t0.f22605a;
    }
}
