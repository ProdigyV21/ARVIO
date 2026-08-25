package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.MediaItem;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/model/MediaItem;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/model/MediaItem;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.MediaRepository$getMovieDetails$item$1", f = "MediaRepository.kt", l = {2858, 2859, 2860}, m = "invokeSuspend", v = 2)
public final class MediaRepository$getMovieDetails$item$1 extends f7.j implements r7.p<ka.k0, d7.d<? super MediaItem>, Object> {
    final /* synthetic */ int $movieId;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    final /* synthetic */ MediaRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaRepository$getMovieDetails$item$1(MediaRepository mediaRepository, int i10, d7.d<? super MediaRepository$getMovieDetails$item$1> dVar) {
        super(2, dVar);
        this.this$0 = mediaRepository;
        this.$movieId = i10;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        MediaRepository$getMovieDetails$item$1 mediaRepository$getMovieDetails$item$1 = new MediaRepository$getMovieDetails$item$1(this.this$0, this.$movieId, dVar);
        mediaRepository$getMovieDetails$item$1.L$0 = obj;
        return mediaRepository$getMovieDetails$item$1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x00cc, code lost:
    
        if (r1 == r7) goto L27;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00d8  */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r55) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 305
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository$getMovieDetails$item$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super MediaItem> dVar) {
        return ((MediaRepository$getMovieDetails$item$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
