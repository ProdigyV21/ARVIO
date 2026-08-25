package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.CollectionSourceConfig;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lka/k0;", "", "Lx6/x;", "Lcom/arflix/tv/data/model/MediaType;", "", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.MediaRepository$resolveCollectionCatalogRefs$perSourceRefs$1$1$1", f = "MediaRepository.kt", l = {279}, m = "invokeSuspend", v = 2)
public final class MediaRepository$resolveCollectionCatalogRefs$perSourceRefs$1$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends x6.x>>, Object> {
    final /* synthetic */ int $index;
    final /* synthetic */ CollectionSourceConfig $source;
    final /* synthetic */ List<Integer> $sourceBudgets;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ MediaRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaRepository$resolveCollectionCatalogRefs$perSourceRefs$1$1$1(MediaRepository mediaRepository, CollectionSourceConfig collectionSourceConfig, List<Integer> list, int i10, d7.d<? super MediaRepository$resolveCollectionCatalogRefs$perSourceRefs$1$1$1> dVar) {
        super(2, dVar);
        this.this$0 = mediaRepository;
        this.$source = collectionSourceConfig;
        this.$sourceBudgets = list;
        this.$index = i10;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        MediaRepository$resolveCollectionCatalogRefs$perSourceRefs$1$1$1 mediaRepository$resolveCollectionCatalogRefs$perSourceRefs$1$1$1 = new MediaRepository$resolveCollectionCatalogRefs$perSourceRefs$1$1$1(this.this$0, this.$source, this.$sourceBudgets, this.$index, dVar);
        mediaRepository$resolveCollectionCatalogRefs$perSourceRefs$1$1$1.L$0 = obj;
        return mediaRepository$resolveCollectionCatalogRefs$perSourceRefs$1$1$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Object c0Var;
        int i10 = this.label;
        try {
            if (i10 == 0) {
                k2.c.G(obj);
                MediaRepository mediaRepository = this.this$0;
                CollectionSourceConfig collectionSourceConfig = this.$source;
                int iIntValue = this.$sourceBudgets.get(this.$index).intValue();
                this.L$0 = null;
                this.L$1 = null;
                this.I$0 = 0;
                this.label = 1;
                obj = mediaRepository.resolveCollectionSourceRefs(collectionSourceConfig, 0, iIntValue, this);
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
            c0Var = (List) obj;
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        return c0Var instanceof x6.c0 ? kotlin.collections.z.f19728i : c0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<? extends x6.x>> dVar) {
        return ((MediaRepository$resolveCollectionCatalogRefs$perSourceRefs$1$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
