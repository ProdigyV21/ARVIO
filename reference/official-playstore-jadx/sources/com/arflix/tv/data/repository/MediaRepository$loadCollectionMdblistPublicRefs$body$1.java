package com.arflix.tv.data.repository;

import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Ljava/lang/String;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.MediaRepository$loadCollectionMdblistPublicRefs$body$1", f = "MediaRepository.kt", l = {}, m = "invokeSuspend", v = 2)
public final class MediaRepository$loadCollectionMdblistPublicRefs$body$1 extends f7.j implements r7.p<ka.k0, d7.d<? super String>, Object> {
    final /* synthetic */ String $slug;
    int label;
    final /* synthetic */ MediaRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaRepository$loadCollectionMdblistPublicRefs$body$1(MediaRepository mediaRepository, String str, d7.d<? super MediaRepository$loadCollectionMdblistPublicRefs$body$1> dVar) {
        super(2, dVar);
        this.this$0 = mediaRepository;
        this.$slug = str;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new MediaRepository$loadCollectionMdblistPublicRefs$body$1(this.this$0, this.$slug, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        return this.this$0.fetchUrl("https://mdblist.com/lists/" + this.$slug + "/json");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super String> dVar) {
        return ((MediaRepository$loadCollectionMdblistPublicRefs$body$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
