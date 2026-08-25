package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.TmdbApi;
import com.arflix.tv.data.api.TmdbListResponse;
import com.arflix.tv.data.api.TmdbMediaItem;
import com.arflix.tv.data.repository.MediaRepository;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/api/TmdbMediaItem;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.MediaRepository$getHomeCategoriesInternal$2$trendingTv$1", f = "MediaRepository.kt", l = {1647}, m = "invokeSuspend", v = 2)
public final class MediaRepository$getHomeCategoriesInternal$2$trendingTv$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends TmdbMediaItem>>, Object> {
    final /* synthetic */ ka.k0 $$this$coroutineScope;
    int label;
    final /* synthetic */ MediaRepository this$0;

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$getHomeCategoriesInternal$2$trendingTv$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lcom/arflix/tv/data/api/TmdbListResponse;", "page", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository$getHomeCategoriesInternal$2$trendingTv$1$1", f = "MediaRepository.kt", l = {1647}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends f7.j implements r7.p<Integer, d7.d<? super TmdbListResponse>, Object> {
        /* synthetic */ int I$0;
        int label;
        final /* synthetic */ MediaRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(MediaRepository mediaRepository, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.this$0 = mediaRepository;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, dVar);
            anonymousClass1.I$0 = ((Number) obj).intValue();
            return anonymousClass1;
        }

        public final Object invoke(int i10, d7.d<? super TmdbListResponse> dVar) {
            return ((AnonymousClass1) create(Integer.valueOf(i10), dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.I$0;
            int i11 = this.label;
            if (i11 != 0) {
                if (i11 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return obj;
            }
            k2.c.G(obj);
            TmdbApi tmdbApi = this.this$0.tmdbApi;
            String str = this.this$0.apiKey;
            String contentLanguage = this.this$0.getContentLanguage();
            this.I$0 = i10;
            this.label = 1;
            Object trendingTv = tmdbApi.getTrendingTv(str, contentLanguage, i10, this);
            e7.a aVar = e7.a.f15033i;
            return trendingTv == aVar ? aVar : trendingTv;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return invoke(((Number) obj).intValue(), (d7.d<? super TmdbListResponse>) obj2);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaRepository$getHomeCategoriesInternal$2$trendingTv$1(MediaRepository mediaRepository, ka.k0 k0Var, d7.d<? super MediaRepository$getHomeCategoriesInternal$2$trendingTv$1> dVar) {
        super(2, dVar);
        this.this$0 = mediaRepository;
        this.$$this$coroutineScope = k0Var;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new MediaRepository$getHomeCategoriesInternal$2$trendingTv$1(this.this$0, this.$$this$coroutineScope, dVar);
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
        ka.k0 k0Var = this.$$this$coroutineScope;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, null);
        this.label = 1;
        Object objInvokeSuspend$fetchUpTo40 = MediaRepository.C08892.invokeSuspend$fetchUpTo40(k0Var, anonymousClass1, this);
        e7.a aVar = e7.a.f15033i;
        return objInvokeSuspend$fetchUpTo40 == aVar ? aVar : objInvokeSuspend$fetchUpTo40;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<TmdbMediaItem>> dVar) {
        return ((MediaRepository$getHomeCategoriesInternal$2$trendingTv$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
