package com.arflix.tv.ui.screens.home;

import com.arflix.tv.data.model.MediaItem;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Ljava/lang/String;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeViewModel$prefetchHeroDetailsForFocusWindow$providerLogoUrls$1$1$1", f = "HomeViewModel.kt", l = {4859, 4194}, m = "invokeSuspend", v = 2)
public final class HomeViewModel$prefetchHeroDetailsForFocusWindow$providerLogoUrls$1$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super String>, Object> {
    final /* synthetic */ MediaItem $item;
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ HomeViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeViewModel$prefetchHeroDetailsForFocusWindow$providerLogoUrls$1$1$1(HomeViewModel homeViewModel, MediaItem mediaItem, d7.d<? super HomeViewModel$prefetchHeroDetailsForFocusWindow$providerLogoUrls$1$1$1> dVar) {
        super(2, dVar);
        this.this$0 = homeViewModel;
        this.$item = mediaItem;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeViewModel$prefetchHeroDetailsForFocusWindow$providerLogoUrls$1$1$1(this.this$0, this.$item, dVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:31:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0090 A[Catch: all -> 0x001f, TRY_LEAVE, TryCatch #5 {all -> 0x001f, blocks: (B:7:0x001a, B:29:0x008a, B:32:0x0090), top: B:54:0x001a }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00c3 A[Catch: all -> 0x0042, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x0042, blocks: (B:44:0x00b5, B:48:0x00c3, B:14:0x003c, B:21:0x0053), top: B:51:0x0008 }] */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 206
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.home.HomeViewModel$prefetchHeroDetailsForFocusWindow$providerLogoUrls$1$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super String> dVar) {
        return ((HomeViewModel$prefetchHeroDetailsForFocusWindow$providerLogoUrls$1$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
