package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.TmdbApi;
import com.arflix.tv.data.api.TmdbListResponse;
import com.arflix.tv.data.model.MediaType;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lka/k0;", "", "Lx6/x;", "Lcom/arflix/tv/data/model/MediaType;", "", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.MediaRepository$loadCollectionKeywordRefs$2$tvJob$1", f = "MediaRepository.kt", l = {2232}, m = "invokeSuspend", v = 2)
public final class MediaRepository$loadCollectionKeywordRefs$2$tvJob$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends x6.x>>, Object> {
    final /* synthetic */ String $keyword;
    final /* synthetic */ int $limit;
    final /* synthetic */ String $sortBy;
    int label;
    final /* synthetic */ MediaRepository this$0;

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$loadCollectionKeywordRefs$2$tvJob$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lcom/arflix/tv/data/api/TmdbListResponse;", "page", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository$loadCollectionKeywordRefs$2$tvJob$1$1", f = "MediaRepository.kt", l = {2236}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends f7.j implements r7.p<Integer, d7.d<? super TmdbListResponse>, Object> {
        final /* synthetic */ String $keyword;
        final /* synthetic */ String $sortBy;
        /* synthetic */ int I$0;
        int label;
        final /* synthetic */ MediaRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(MediaRepository mediaRepository, String str, String str2, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.this$0 = mediaRepository;
            this.$sortBy = str;
            this.$keyword = str2;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$sortBy, this.$keyword, dVar);
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
            String str2 = this.$sortBy;
            String str3 = this.$keyword;
            this.I$0 = i10;
            this.label = 1;
            Object objB = com.arflix.tv.data.api.g.b(tmdbApi, str, null, null, str2, null, null, null, null, null, str3, null, null, contentLanguage, i10, this, 3574, null);
            e7.a aVar = e7.a.f15033i;
            return objB == aVar ? aVar : objB;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return invoke(((Number) obj).intValue(), (d7.d<? super TmdbListResponse>) obj2);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaRepository$loadCollectionKeywordRefs$2$tvJob$1(MediaRepository mediaRepository, int i10, String str, String str2, d7.d<? super MediaRepository$loadCollectionKeywordRefs$2$tvJob$1> dVar) {
        super(2, dVar);
        this.this$0 = mediaRepository;
        this.$limit = i10;
        this.$sortBy = str;
        this.$keyword = str2;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new MediaRepository$loadCollectionKeywordRefs$2$tvJob$1(this.this$0, this.$limit, this.$sortBy, this.$keyword, dVar);
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
        MediaRepository mediaRepository = this.this$0;
        MediaType mediaType = MediaType.TV;
        int i11 = this.$limit;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(mediaRepository, this.$sortBy, this.$keyword, null);
        this.label = 1;
        Object objLoadPagedTmdbDiscoverRefs = mediaRepository.loadPagedTmdbDiscoverRefs(mediaType, i11, anonymousClass1, this);
        e7.a aVar = e7.a.f15033i;
        return objLoadPagedTmdbDiscoverRefs == aVar ? aVar : objLoadPagedTmdbDiscoverRefs;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<? extends x6.x>> dVar) {
        return ((MediaRepository$loadCollectionKeywordRefs$2$tvJob$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
