package com.arflix.tv.ui.screens.search;

import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.ui.screens.search.SearchViewModel;
import java.util.List;
import ka.k0;
import ka.m0;
import ka.p0;
import ka.q0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/MediaItem;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.search.SearchViewModel$executeSmartSearch$1$items$1", f = "SearchViewModel.kt", l = {337, 337, 341, 342, 343, 345}, m = "invokeSuspend", v = 2)
public final class SearchViewModel$executeSmartSearch$1$items$1 extends f7.j implements r7.p<k0, d7.d<? super List<? extends MediaItem>>, Object> {
    final /* synthetic */ SearchViewModel.SmartQuery $sq;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ SearchViewModel this$0;

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.search.SearchViewModel$executeSmartSearch$1$items$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/MediaItem;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.search.SearchViewModel$executeSmartSearch$1$items$1$1", f = "SearchViewModel.kt", l = {348, 348}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends f7.j implements r7.p<k0, d7.d<? super List<? extends MediaItem>>, Object> {
        final /* synthetic */ SearchViewModel.SmartQuery $sq;
        final /* synthetic */ String $tvGenre;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        final /* synthetic */ SearchViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(SearchViewModel searchViewModel, SearchViewModel.SmartQuery smartQuery, String str, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.this$0 = searchViewModel;
            this.$sq = smartQuery;
            this.$tvGenre = str;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$sq, this.$tvGenre, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            p0 p0VarE;
            SearchViewModel searchViewModel;
            SearchViewModel searchViewModel2;
            List list;
            k0 k0Var = (k0) this.L$0;
            int i10 = this.label;
            e7.a aVar = e7.a.f15033i;
            if (i10 == 0) {
                k2.c.G(obj);
                q0 q0VarE = m0.e(3, null, k0Var, new SearchViewModel$executeSmartSearch$1$items$1$1$a$1(this.this$0, this.$sq, null));
                p0VarE = m0.e(3, null, k0Var, new SearchViewModel$executeSmartSearch$1$items$1$1$b$1(this.this$0, this.$tvGenre, this.$sq, null));
                searchViewModel = this.this$0;
                this.L$0 = null;
                this.L$1 = null;
                this.L$2 = p0VarE;
                this.L$3 = searchViewModel;
                this.label = 1;
                obj = q0VarE.j(this);
                if (obj != aVar) {
                }
                return aVar;
            }
            if (i10 != 1) {
                if (i10 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                list = (List) this.L$4;
                searchViewModel2 = (SearchViewModel) this.L$3;
                k2.c.G(obj);
                return searchViewModel2.interleave(list, (List) obj);
            }
            searchViewModel = (SearchViewModel) this.L$3;
            p0VarE = (p0) this.L$2;
            k2.c.G(obj);
            p0 p0Var = p0VarE;
            searchViewModel2 = searchViewModel;
            List list2 = (List) obj;
            this.L$0 = null;
            this.L$1 = null;
            this.L$2 = null;
            this.L$3 = searchViewModel2;
            this.L$4 = list2;
            this.label = 2;
            Object objO = p0Var.o(this);
            if (objO != aVar) {
                list = list2;
                obj = objO;
                return searchViewModel2.interleave(list, (List) obj);
            }
            return aVar;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super List<MediaItem>> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DiscoverType.values().length];
            try {
                iArr[DiscoverType.MOVIES.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DiscoverType.TV_SHOWS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DiscoverType.ANIME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[DiscoverType.ALL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchViewModel$executeSmartSearch$1$items$1(SearchViewModel.SmartQuery smartQuery, SearchViewModel searchViewModel, d7.d<? super SearchViewModel$executeSmartSearch$1$items$1> dVar) {
        super(2, dVar);
        this.$sq = smartQuery;
        this.this$0 = searchViewModel;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new SearchViewModel$executeSmartSearch$1$items$1(this.$sq, this.this$0, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x008f, code lost:
    
        if (r0 == r13) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00d1, code lost:
    
        if (r0 == r13) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x010f, code lost:
    
        if (r0 == r13) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x013e, code lost:
    
        if (r0 == r13) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0170, code lost:
    
        if (r0 == r13) goto L51;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0096  */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 392
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.search.SearchViewModel$executeSmartSearch$1$items$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super List<MediaItem>> dVar) {
        return ((SearchViewModel$executeSmartSearch$1$items$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
