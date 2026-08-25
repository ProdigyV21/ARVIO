package com.arflix.tv.ui.screens.home;

import com.arflix.tv.data.model.MediaItem;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/model/MediaItem;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/model/MediaItem;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeViewModel$loadHeroDetailsSnapshot$2$detailsDeferred$1", f = "HomeViewModel.kt", l = {646, 648}, m = "invokeSuspend", v = 2)
public final class HomeViewModel$loadHeroDetailsSnapshot$2$detailsDeferred$1 extends f7.j implements r7.p<ka.k0, d7.d<? super MediaItem>, Object> {
    final /* synthetic */ MediaItem $item;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ HomeViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeViewModel$loadHeroDetailsSnapshot$2$detailsDeferred$1(MediaItem mediaItem, HomeViewModel homeViewModel, d7.d<? super HomeViewModel$loadHeroDetailsSnapshot$2$detailsDeferred$1> dVar) {
        super(2, dVar);
        this.$item = mediaItem;
        this.this$0 = homeViewModel;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        HomeViewModel$loadHeroDetailsSnapshot$2$detailsDeferred$1 homeViewModel$loadHeroDetailsSnapshot$2$detailsDeferred$1 = new HomeViewModel$loadHeroDetailsSnapshot$2$detailsDeferred$1(this.$item, this.this$0, dVar);
        homeViewModel$loadHeroDetailsSnapshot$2$detailsDeferred$1.L$0 = obj;
        return homeViewModel$loadHeroDetailsSnapshot$2$detailsDeferred$1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0069, code lost:
    
        if (r9 == r6) goto L26;
     */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = r8.L$0
            ka.k0 r0 = (ka.k0) r0
            int r0 = r8.label
            r1 = 2
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L29
            if (r0 == r2) goto L21
            if (r0 != r1) goto L19
            java.lang.Object r0 = r8.L$1
            ka.k0 r0 = (ka.k0) r0
            k2.c.G(r9)     // Catch: java.lang.Throwable -> L17
            goto L6c
        L17:
            r9 = move-exception
            goto L6f
        L19:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L21:
            java.lang.Object r0 = r8.L$1
            ka.k0 r0 = (ka.k0) r0
            k2.c.G(r9)     // Catch: java.lang.Throwable -> L17
            goto L52
        L29:
            k2.c.G(r9)
            com.arflix.tv.data.model.MediaItem r9 = r8.$item
            com.arflix.tv.ui.screens.home.HomeViewModel r0 = r8.this$0
            com.arflix.tv.data.model.MediaType r4 = r9.getMediaType()     // Catch: java.lang.Throwable -> L17
            com.arflix.tv.data.model.MediaType r5 = com.arflix.tv.data.model.MediaType.MOVIE     // Catch: java.lang.Throwable -> L17
            e7.a r6 = e7.a.f15033i
            r7 = 0
            if (r4 != r5) goto L55
            com.arflix.tv.data.repository.MediaRepository r0 = com.arflix.tv.ui.screens.home.HomeViewModel.access$getMediaRepository$p(r0)     // Catch: java.lang.Throwable -> L17
            int r9 = r9.getId()     // Catch: java.lang.Throwable -> L17
            r8.L$0 = r3     // Catch: java.lang.Throwable -> L17
            r8.L$1 = r3     // Catch: java.lang.Throwable -> L17
            r8.I$0 = r7     // Catch: java.lang.Throwable -> L17
            r8.label = r2     // Catch: java.lang.Throwable -> L17
            java.lang.Object r9 = r0.getMovieDetails(r9, r8)     // Catch: java.lang.Throwable -> L17
            if (r9 != r6) goto L52
            goto L6b
        L52:
            com.arflix.tv.data.model.MediaItem r9 = (com.arflix.tv.data.model.MediaItem) r9     // Catch: java.lang.Throwable -> L17
            goto L75
        L55:
            com.arflix.tv.data.repository.MediaRepository r0 = com.arflix.tv.ui.screens.home.HomeViewModel.access$getMediaRepository$p(r0)     // Catch: java.lang.Throwable -> L17
            int r9 = r9.getId()     // Catch: java.lang.Throwable -> L17
            r8.L$0 = r3     // Catch: java.lang.Throwable -> L17
            r8.L$1 = r3     // Catch: java.lang.Throwable -> L17
            r8.I$0 = r7     // Catch: java.lang.Throwable -> L17
            r8.label = r1     // Catch: java.lang.Throwable -> L17
            java.lang.Object r9 = r0.getTvDetails(r9, r8)     // Catch: java.lang.Throwable -> L17
            if (r9 != r6) goto L6c
        L6b:
            return r6
        L6c:
            com.arflix.tv.data.model.MediaItem r9 = (com.arflix.tv.data.model.MediaItem) r9     // Catch: java.lang.Throwable -> L17
            goto L75
        L6f:
            x6.c0 r0 = new x6.c0
            r0.<init>(r9)
            r9 = r0
        L75:
            boolean r0 = r9 instanceof x6.c0
            if (r0 == 0) goto L7b
            goto L7c
        L7b:
            r3 = r9
        L7c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.home.HomeViewModel$loadHeroDetailsSnapshot$2$detailsDeferred$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super MediaItem> dVar) {
        return ((HomeViewModel$loadHeroDetailsSnapshot$2$detailsDeferred$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
