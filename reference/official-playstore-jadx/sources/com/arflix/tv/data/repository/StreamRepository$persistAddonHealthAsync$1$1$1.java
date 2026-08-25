package com.arflix.tv.data.repository;

import com.arflix.tv.data.repository.StreamRepository;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.StreamRepository$persistAddonHealthAsync$1$1$1", f = "StreamRepository.kt", l = {}, m = "invokeSuspend", v = 2)
public final class StreamRepository$persistAddonHealthAsync$1$1$1 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
    final /* synthetic */ String $loadedProfile;
    final /* synthetic */ LinkedHashMap<String, StreamRepository.AddonRuntimeHealth> $snapshot;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ StreamRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StreamRepository$persistAddonHealthAsync$1$1$1(StreamRepository streamRepository, String str, LinkedHashMap<String, StreamRepository.AddonRuntimeHealth> linkedHashMap, d7.d<? super StreamRepository$persistAddonHealthAsync$1$1$1> dVar) {
        super(2, dVar);
        this.this$0 = streamRepository;
        this.$loadedProfile = str;
        this.$snapshot = linkedHashMap;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        StreamRepository$persistAddonHealthAsync$1$1$1 streamRepository$persistAddonHealthAsync$1$1$1 = new StreamRepository$persistAddonHealthAsync$1$1$1(this.this$0, this.$loadedProfile, this.$snapshot, dVar);
        streamRepository$persistAddonHealthAsync$1$1$1.L$0 = obj;
        return streamRepository$persistAddonHealthAsync$1$1$1;
    }

    @Override // r7.p
    public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
        return ((StreamRepository$persistAddonHealthAsync$1$1$1) create(bVar, dVar)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        c1.b bVar = (c1.b) this.L$0;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        bVar.d(this.this$0.addonHealthKeyFor(this.$loadedProfile), this.this$0.gson.toJson(this.$snapshot));
        return t0.f22605a;
    }
}
