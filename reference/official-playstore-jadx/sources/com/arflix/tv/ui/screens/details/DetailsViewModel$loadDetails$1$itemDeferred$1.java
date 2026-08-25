package com.arflix.tv.ui.screens.details;

import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.ui.screens.details.DetailsViewModel;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/model/MediaItem;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/model/MediaItem;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$itemDeferred$1", f = "DetailsViewModel.kt", l = {388}, m = "invokeSuspend", v = 2)
public final class DetailsViewModel$loadDetails$1$itemDeferred$1 extends f7.j implements r7.p<ka.k0, d7.d<? super MediaItem>, Object> {
    final /* synthetic */ ka.k0 $$this$launch;
    final /* synthetic */ int $mediaId;
    final /* synthetic */ MediaType $mediaType;
    int label;
    final /* synthetic */ DetailsViewModel this$0;

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$itemDeferred$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", "Lcom/arflix/tv/data/model/MediaItem;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$itemDeferred$1$1", f = "DetailsViewModel.kt", l = {390, 392}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends f7.j implements r7.l<d7.d<? super MediaItem>, Object> {
        final /* synthetic */ int $mediaId;
        final /* synthetic */ MediaType $mediaType;
        int label;
        final /* synthetic */ DetailsViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(MediaType mediaType, DetailsViewModel detailsViewModel, int i10, d7.d<? super AnonymousClass1> dVar) {
            super(1, dVar);
            this.$mediaType = mediaType;
            this.this$0 = detailsViewModel;
            this.$mediaId = i10;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(d7.d<?> dVar) {
            return new AnonymousClass1(this.$mediaType, this.this$0, this.$mediaId, dVar);
        }

        @Override // r7.l
        public final Object invoke(d7.d<? super MediaItem> dVar) {
            return ((AnonymousClass1) create(dVar)).invokeSuspend(x6.t0.f22605a);
        }

        /* JADX WARN: Code restructure failed: missing block: B:13:0x0033, code lost:
        
            if (r5 == r3) goto L19;
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x0047, code lost:
        
            if (r5 == r3) goto L19;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r5) {
            /*
                r4 = this;
                int r0 = r4.label
                r1 = 2
                r2 = 1
                if (r0 == 0) goto L1a
                if (r0 == r2) goto L16
                if (r0 != r1) goto Le
                k2.c.G(r5)
                goto L4a
            Le:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r0)
                throw r5
            L16:
                k2.c.G(r5)
                goto L36
            L1a:
                k2.c.G(r5)
                com.arflix.tv.data.model.MediaType r5 = r4.$mediaType
                com.arflix.tv.data.model.MediaType r0 = com.arflix.tv.data.model.MediaType.TV
                e7.a r3 = e7.a.f15033i
                if (r5 != r0) goto L39
                com.arflix.tv.ui.screens.details.DetailsViewModel r5 = r4.this$0
                com.arflix.tv.data.repository.MediaRepository r5 = com.arflix.tv.ui.screens.details.DetailsViewModel.access$getMediaRepository$p(r5)
                int r0 = r4.$mediaId
                r4.label = r2
                java.lang.Object r5 = r5.getTvDetails(r0, r4)
                if (r5 != r3) goto L36
                goto L49
            L36:
                com.arflix.tv.data.model.MediaItem r5 = (com.arflix.tv.data.model.MediaItem) r5
                return r5
            L39:
                com.arflix.tv.ui.screens.details.DetailsViewModel r5 = r4.this$0
                com.arflix.tv.data.repository.MediaRepository r5 = com.arflix.tv.ui.screens.details.DetailsViewModel.access$getMediaRepository$p(r5)
                int r0 = r4.$mediaId
                r4.label = r1
                java.lang.Object r5 = r5.getMovieDetails(r0, r4)
                if (r5 != r3) goto L4a
            L49:
                return r3
            L4a:
                com.arflix.tv.data.model.MediaItem r5 = (com.arflix.tv.data.model.MediaItem) r5
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$itemDeferred$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DetailsViewModel$loadDetails$1$itemDeferred$1(MediaType mediaType, DetailsViewModel detailsViewModel, int i10, ka.k0 k0Var, d7.d<? super DetailsViewModel$loadDetails$1$itemDeferred$1> dVar) {
        super(2, dVar);
        this.$mediaType = mediaType;
        this.this$0 = detailsViewModel;
        this.$mediaId = i10;
        this.$$this$launch = k0Var;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new DetailsViewModel$loadDetails$1$itemDeferred$1(this.$mediaType, this.this$0, this.$mediaId, this.$$this$launch, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        int i10 = this.label;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return obj;
        }
        k2.c.G(obj);
        ka.k0 k0Var = this.$$this$launch;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$mediaType, this.this$0, this.$mediaId, null);
        this.label = 1;
        Object objInvokeSuspend$loadDetailsPart = DetailsViewModel.C13181.invokeSuspend$loadDetailsPart(k0Var, "item", anonymousClass1, this);
        e7.a aVar = e7.a.f15033i;
        return objInvokeSuspend$loadDetailsPart == aVar ? aVar : objInvokeSuspend$loadDetailsPart;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super MediaItem> dVar) {
        return ((DetailsViewModel$loadDetails$1$itemDeferred$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
