package com.arflix.tv.ui.screens.details;

import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.repository.WatchlistRepository;
import com.arflix.tv.ui.screens.details.DetailsViewModel;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Z"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$watchlistDeferred$1", f = "DetailsViewModel.kt", l = {397}, m = "invokeSuspend", v = 2)
public final class DetailsViewModel$loadDetails$1$watchlistDeferred$1 extends f7.j implements r7.p<ka.k0, d7.d<? super Boolean>, Object> {
    final /* synthetic */ ka.k0 $$this$launch;
    final /* synthetic */ int $mediaId;
    final /* synthetic */ MediaType $mediaType;
    int label;
    final /* synthetic */ DetailsViewModel this$0;

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$watchlistDeferred$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u000b\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$watchlistDeferred$1$1", f = "DetailsViewModel.kt", l = {398}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends f7.j implements r7.l<d7.d<? super Boolean>, Object> {
        final /* synthetic */ int $mediaId;
        final /* synthetic */ MediaType $mediaType;
        int label;
        final /* synthetic */ DetailsViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(DetailsViewModel detailsViewModel, MediaType mediaType, int i10, d7.d<? super AnonymousClass1> dVar) {
            super(1, dVar);
            this.this$0 = detailsViewModel;
            this.$mediaType = mediaType;
            this.$mediaId = i10;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(d7.d<?> dVar) {
            return new AnonymousClass1(this.this$0, this.$mediaType, this.$mediaId, dVar);
        }

        @Override // r7.l
        public final Object invoke(d7.d<? super Boolean> dVar) {
            return ((AnonymousClass1) create(dVar)).invokeSuspend(x6.t0.f22605a);
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
            WatchlistRepository watchlistRepository = this.this$0.watchlistRepository;
            MediaType mediaType = this.$mediaType;
            int i11 = this.$mediaId;
            this.label = 1;
            Object objIsInWatchlist = watchlistRepository.isInWatchlist(mediaType, i11, this);
            e7.a aVar = e7.a.f15033i;
            return objIsInWatchlist == aVar ? aVar : objIsInWatchlist;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DetailsViewModel$loadDetails$1$watchlistDeferred$1(DetailsViewModel detailsViewModel, MediaType mediaType, int i10, ka.k0 k0Var, d7.d<? super DetailsViewModel$loadDetails$1$watchlistDeferred$1> dVar) {
        super(2, dVar);
        this.this$0 = detailsViewModel;
        this.$mediaType = mediaType;
        this.$mediaId = i10;
        this.$$this$launch = k0Var;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new DetailsViewModel$loadDetails$1$watchlistDeferred$1(this.this$0, this.$mediaType, this.$mediaId, this.$$this$launch, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            ka.k0 k0Var = this.$$this$launch;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$mediaType, this.$mediaId, null);
            this.label = 1;
            obj = DetailsViewModel.C13181.invokeSuspend$loadDetailsPart(k0Var, "watchlist", anonymousClass1, this);
            e7.a aVar = e7.a.f15033i;
            if (obj == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        Boolean bool = (Boolean) obj;
        return Boolean.valueOf(bool != null ? bool.booleanValue() : false);
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super Boolean> dVar) {
        return ((DetailsViewModel$loadDetails$1$watchlistDeferred$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
