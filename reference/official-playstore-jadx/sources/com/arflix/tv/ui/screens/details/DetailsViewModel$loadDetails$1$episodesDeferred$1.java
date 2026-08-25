package com.arflix.tv.ui.screens.details;

import com.arflix.tv.data.model.Episode;
import com.arflix.tv.data.repository.MediaRepository;
import com.arflix.tv.ui.screens.details.DetailsViewModel;
import com.google.android.gms.cast.MediaError;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/Episode;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$episodesDeferred$1", f = "DetailsViewModel.kt", l = {410}, m = "invokeSuspend", v = 2)
public final class DetailsViewModel$loadDetails$1$episodesDeferred$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends Episode>>, Object> {
    final /* synthetic */ ka.k0 $$this$launch;
    final /* synthetic */ int $mediaId;
    final /* synthetic */ int $seasonToLoad;
    int label;
    final /* synthetic */ DetailsViewModel this$0;

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$episodesDeferred$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n"}, d2 = {"<anonymous>", "", "Lcom/arflix/tv/data/model/Episode;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$episodesDeferred$1$1", f = "DetailsViewModel.kt", l = {MediaError.DetailedErrorCode.HLS_MANIFEST_MASTER}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends f7.j implements r7.l<d7.d<? super List<? extends Episode>>, Object> {
        final /* synthetic */ int $mediaId;
        final /* synthetic */ int $seasonToLoad;
        int label;
        final /* synthetic */ DetailsViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(DetailsViewModel detailsViewModel, int i10, int i11, d7.d<? super AnonymousClass1> dVar) {
            super(1, dVar);
            this.this$0 = detailsViewModel;
            this.$mediaId = i10;
            this.$seasonToLoad = i11;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(d7.d<?> dVar) {
            return new AnonymousClass1(this.this$0, this.$mediaId, this.$seasonToLoad, dVar);
        }

        @Override // r7.l
        public final Object invoke(d7.d<? super List<Episode>> dVar) {
            return ((AnonymousClass1) create(dVar)).invokeSuspend(x6.t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws Exception {
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
            int i11 = this.$mediaId;
            int i12 = this.$seasonToLoad;
            this.label = 1;
            Object seasonEpisodes = mediaRepository.getSeasonEpisodes(i11, i12, this);
            e7.a aVar = e7.a.f15033i;
            return seasonEpisodes == aVar ? aVar : seasonEpisodes;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DetailsViewModel$loadDetails$1$episodesDeferred$1(int i10, DetailsViewModel detailsViewModel, int i11, ka.k0 k0Var, d7.d<? super DetailsViewModel$loadDetails$1$episodesDeferred$1> dVar) {
        super(2, dVar);
        this.$seasonToLoad = i10;
        this.this$0 = detailsViewModel;
        this.$mediaId = i11;
        this.$$this$launch = k0Var;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new DetailsViewModel$loadDetails$1$episodesDeferred$1(this.$seasonToLoad, this.this$0, this.$mediaId, this.$$this$launch, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            ka.k0 k0Var = this.$$this$launch;
            String strO = androidx.compose.foundation.c.o(this.$seasonToLoad, "season ", " episodes");
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$mediaId, this.$seasonToLoad, null);
            this.label = 1;
            obj = DetailsViewModel.C13181.invokeSuspend$loadDetailsPart(k0Var, strO, anonymousClass1, this);
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
        List list = (List) obj;
        return list == null ? kotlin.collections.z.f19728i : list;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<Episode>> dVar) {
        return ((DetailsViewModel$loadDetails$1$episodesDeferred$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
