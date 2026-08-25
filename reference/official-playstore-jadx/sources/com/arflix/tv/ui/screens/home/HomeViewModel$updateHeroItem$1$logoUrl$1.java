package com.arflix.tv.ui.screens.home;

import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.repository.MediaRepository;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Ljava/lang/String;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeViewModel$updateHeroItem$1$logoUrl$1", f = "HomeViewModel.kt", l = {4011}, m = "invokeSuspend", v = 2)
public final class HomeViewModel$updateHeroItem$1$logoUrl$1 extends f7.j implements r7.p<ka.k0, d7.d<? super String>, Object> {
    final /* synthetic */ MediaItem $item;
    int label;
    final /* synthetic */ HomeViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeViewModel$updateHeroItem$1$logoUrl$1(HomeViewModel homeViewModel, MediaItem mediaItem, d7.d<? super HomeViewModel$updateHeroItem$1$logoUrl$1> dVar) {
        super(2, dVar);
        this.this$0 = homeViewModel;
        this.$item = mediaItem;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeViewModel$updateHeroItem$1$logoUrl$1(this.this$0, this.$item, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return obj;
        }
        k2.c.G(obj);
        MediaRepository mediaRepository = this.this$0.mediaRepository;
        MediaType mediaType = this.$item.getMediaType();
        int id = this.$item.getId();
        this.label = 1;
        Object logoUrl = mediaRepository.getLogoUrl(mediaType, id, this);
        e7.a aVar = e7.a.f15033i;
        return logoUrl == aVar ? aVar : logoUrl;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super String> dVar) {
        return ((HomeViewModel$updateHeroItem$1$logoUrl$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
