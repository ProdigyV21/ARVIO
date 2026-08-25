package com.arflix.tv.ui.screens.search;

import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.repository.MediaRepository;
import ka.k0;
import kotlin.Metadata;
import x6.c0;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "Lx6/x;", "", "<anonymous>", "(Lka/k0;)Lx6/x;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.search.SearchViewModel$loadDiscoverRows$1$1$logos$1$1", f = "SearchViewModel.kt", l = {155}, m = "invokeSuspend", v = 2)
public final class SearchViewModel$loadDiscoverRows$1$1$logos$1$1 extends f7.j implements r7.p<k0, d7.d<? super x6.x>, Object> {
    final /* synthetic */ MediaItem $item;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ SearchViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchViewModel$loadDiscoverRows$1$1$logos$1$1(MediaItem mediaItem, SearchViewModel searchViewModel, d7.d<? super SearchViewModel$loadDiscoverRows$1$1$logos$1$1> dVar) {
        super(2, dVar);
        this.$item = mediaItem;
        this.this$0 = searchViewModel;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        SearchViewModel$loadDiscoverRows$1$1$logos$1$1 searchViewModel$loadDiscoverRows$1$1$logos$1$1 = new SearchViewModel$loadDiscoverRows$1$1$logos$1$1(this.$item, this.this$0, dVar);
        searchViewModel$loadDiscoverRows$1$1$logos$1$1.L$0 = obj;
        return searchViewModel$loadDiscoverRows$1$1$logos$1$1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3 */
    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Object c0Var;
        Object obj2;
        int i10 = this.label;
        try {
            if (i10 == 0) {
                k2.c.G(obj);
                String str = this.$item.getMediaType() + "_" + this.$item.getId();
                SearchViewModel searchViewModel = this.this$0;
                MediaItem mediaItem = this.$item;
                MediaRepository mediaRepository = searchViewModel.mediaRepository;
                MediaType mediaType = mediaItem.getMediaType();
                int id = mediaItem.getId();
                this.L$0 = null;
                this.L$1 = str;
                this.L$2 = null;
                this.I$0 = 0;
                this.label = 1;
                obj = mediaRepository.getLogoUrl(mediaType, id, this);
                e7.a aVar = e7.a.f15033i;
                i10 = str;
                if (obj == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                String str2 = (String) this.L$1;
                k2.c.G(obj);
                i10 = str2;
            }
            c0Var = (String) obj;
            obj2 = i10;
        } catch (Throwable th) {
            c0Var = new c0(th);
            obj2 = i10;
        }
        if (c0Var instanceof c0) {
            c0Var = null;
        }
        String str3 = (String) c0Var;
        if (str3 == null || kotlin.text.o.h0(str3)) {
            return null;
        }
        return new x6.x(obj2, str3);
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super x6.x> dVar) {
        return ((SearchViewModel$loadDiscoverRows$1$1$logos$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
