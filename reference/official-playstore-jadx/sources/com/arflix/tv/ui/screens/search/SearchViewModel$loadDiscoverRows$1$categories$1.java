package com.arflix.tv.ui.screens.search;

import androidx.media3.extractor.ts.TsExtractor;
import com.arflix.tv.data.model.Category;
import java.util.List;
import ka.k0;
import ka.l0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/Category;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.search.SearchViewModel$loadDiscoverRows$1$categories$1", f = "SearchViewModel.kt", l = {TsExtractor.TS_STREAM_TYPE_SPLICE_INFO}, m = "invokeSuspend", v = 2)
public final class SearchViewModel$loadDiscoverRows$1$categories$1 extends f7.j implements r7.p<k0, d7.d<? super List<? extends Category>>, Object> {
    final /* synthetic */ String $genre;
    final /* synthetic */ boolean $isAnime;
    final /* synthetic */ String $lang;
    final /* synthetic */ String $oneYearAgo;
    final /* synthetic */ String $threeMonthsAgo;
    final /* synthetic */ String $today;
    final /* synthetic */ DiscoverType $type;
    int label;
    final /* synthetic */ SearchViewModel this$0;

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.search.SearchViewModel$loadDiscoverRows$1$categories$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/Category;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.search.SearchViewModel$loadDiscoverRows$1$categories$1$1", f = "SearchViewModel.kt", l = {145, 145, 145, 145, 145}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends f7.j implements r7.p<k0, d7.d<? super List<? extends Category>>, Object> {
        final /* synthetic */ String $genre;
        final /* synthetic */ boolean $isAnime;
        final /* synthetic */ String $lang;
        final /* synthetic */ String $oneYearAgo;
        final /* synthetic */ String $threeMonthsAgo;
        final /* synthetic */ String $today;
        final /* synthetic */ DiscoverType $type;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        final /* synthetic */ SearchViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(SearchViewModel searchViewModel, DiscoverType discoverType, String str, String str2, boolean z, String str3, String str4, String str5, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.this$0 = searchViewModel;
            this.$type = discoverType;
            this.$genre = str;
            this.$lang = str2;
            this.$isAnime = z;
            this.$today = str3;
            this.$oneYearAgo = str4;
            this.$threeMonthsAgo = str5;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$type, this.$genre, this.$lang, this.$isAnime, this.$today, this.$oneYearAgo, this.$threeMonthsAgo, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        /* JADX WARN: Removed duplicated region for block: B:27:0x0209  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x022a  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x024a  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r31) {
            /*
                Method dump skipped, instruction units count: 595
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.search.SearchViewModel$loadDiscoverRows$1$categories$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super List<Category>> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchViewModel$loadDiscoverRows$1$categories$1(SearchViewModel searchViewModel, DiscoverType discoverType, String str, String str2, boolean z, String str3, String str4, String str5, d7.d<? super SearchViewModel$loadDiscoverRows$1$categories$1> dVar) {
        super(2, dVar);
        this.this$0 = searchViewModel;
        this.$type = discoverType;
        this.$genre = str;
        this.$lang = str2;
        this.$isAnime = z;
        this.$today = str3;
        this.$oneYearAgo = str4;
        this.$threeMonthsAgo = str5;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new SearchViewModel$loadDiscoverRows$1$categories$1(this.this$0, this.$type, this.$genre, this.$lang, this.$isAnime, this.$today, this.$oneYearAgo, this.$threeMonthsAgo, dVar);
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
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$type, this.$genre, this.$lang, this.$isAnime, this.$today, this.$oneYearAgo, this.$threeMonthsAgo, null);
        this.label = 1;
        Object objC = l0.c(anonymousClass1, this);
        e7.a aVar = e7.a.f15033i;
        return objC == aVar ? aVar : objC;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super List<Category>> dVar) {
        return ((SearchViewModel$loadDiscoverRows$1$categories$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
