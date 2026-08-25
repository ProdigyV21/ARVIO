package com.arflix.tv.ui.screens.search;

import com.arflix.tv.data.model.MediaItem;
import java.util.List;
import ka.k0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/MediaItem;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.search.SearchViewModel$buildRow$items$1", f = "SearchViewModel.kt", l = {186, 186}, m = "invokeSuspend", v = 2)
public final class SearchViewModel$buildRow$items$1 extends f7.j implements r7.p<k0, d7.d<? super List<? extends MediaItem>>, Object> {
    final /* synthetic */ String $lang;
    final /* synthetic */ Integer $minVotes;
    final /* synthetic */ String $movieGenre;
    final /* synthetic */ int $page;
    final /* synthetic */ String $releaseDateGte;
    final /* synthetic */ String $releaseDateLte;
    final /* synthetic */ String $sort;
    final /* synthetic */ String $tvGenre;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    final /* synthetic */ SearchViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchViewModel$buildRow$items$1(SearchViewModel searchViewModel, String str, String str2, Integer num, int i10, String str3, String str4, String str5, String str6, d7.d<? super SearchViewModel$buildRow$items$1> dVar) {
        super(2, dVar);
        this.this$0 = searchViewModel;
        this.$movieGenre = str;
        this.$sort = str2;
        this.$minVotes = num;
        this.$page = i10;
        this.$lang = str3;
        this.$releaseDateLte = str4;
        this.$releaseDateGte = str5;
        this.$tvGenre = str6;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        SearchViewModel$buildRow$items$1 searchViewModel$buildRow$items$1 = new SearchViewModel$buildRow$items$1(this.this$0, this.$movieGenre, this.$sort, this.$minVotes, this.$page, this.$lang, this.$releaseDateLte, this.$releaseDateGte, this.$tvGenre, dVar);
        searchViewModel$buildRow$items$1.L$0 = obj;
        return searchViewModel$buildRow$items$1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x00af, code lost:
    
        if (r1 == r6) goto L16;
     */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r20) {
        /*
            r19 = this;
            r0 = r19
            java.lang.Object r1 = r0.L$0
            ka.k0 r1 = (ka.k0) r1
            int r2 = r0.label
            r3 = 2
            r4 = 1
            r5 = 0
            e7.a r6 = e7.a.f15033i
            if (r2 == 0) goto L48
            if (r2 == r4) goto L33
            if (r2 != r3) goto L2b
            java.lang.Object r1 = r0.L$4
            java.util.List r1 = (java.util.List) r1
            java.lang.Object r2 = r0.L$3
            com.arflix.tv.ui.screens.search.SearchViewModel r2 = (com.arflix.tv.ui.screens.search.SearchViewModel) r2
            java.lang.Object r3 = r0.L$2
            ka.p0 r3 = (ka.p0) r3
            java.lang.Object r3 = r0.L$1
            ka.p0 r3 = (ka.p0) r3
            k2.c.G(r20)
            r3 = r1
            r1 = r20
            goto Lb2
        L2b:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L33:
            java.lang.Object r1 = r0.L$3
            com.arflix.tv.ui.screens.search.SearchViewModel r1 = (com.arflix.tv.ui.screens.search.SearchViewModel) r1
            java.lang.Object r2 = r0.L$2
            ka.p0 r2 = (ka.p0) r2
            java.lang.Object r4 = r0.L$1
            ka.p0 r4 = (ka.p0) r4
            k2.c.G(r20)
            r3 = r2
            r2 = r1
            r1 = r3
            r3 = r20
            goto L9c
        L48:
            k2.c.G(r20)
            com.arflix.tv.ui.screens.search.SearchViewModel$buildRow$items$1$m$1 r7 = new com.arflix.tv.ui.screens.search.SearchViewModel$buildRow$items$1$m$1
            com.arflix.tv.ui.screens.search.SearchViewModel r8 = r0.this$0
            java.lang.String r9 = r0.$movieGenre
            java.lang.String r10 = r0.$sort
            java.lang.Integer r11 = r0.$minVotes
            int r12 = r0.$page
            java.lang.String r13 = r0.$lang
            java.lang.String r14 = r0.$releaseDateLte
            java.lang.String r15 = r0.$releaseDateGte
            r16 = 0
            r7.<init>(r8, r9, r10, r11, r12, r13, r14, r15, r16)
            r2 = 3
            ka.q0 r7 = ka.m0.e(r2, r5, r1, r7)
            com.arflix.tv.ui.screens.search.SearchViewModel$buildRow$items$1$t$1 r8 = new com.arflix.tv.ui.screens.search.SearchViewModel$buildRow$items$1$t$1
            com.arflix.tv.ui.screens.search.SearchViewModel r9 = r0.this$0
            java.lang.String r10 = r0.$tvGenre
            java.lang.String r11 = r0.$sort
            java.lang.Integer r12 = r0.$minVotes
            int r13 = r0.$page
            java.lang.String r14 = r0.$lang
            java.lang.String r15 = r0.$releaseDateLte
            java.lang.String r3 = r0.$releaseDateGte
            r17 = 0
            r16 = r3
            r8.<init>(r9, r10, r11, r12, r13, r14, r15, r16, r17)
            ka.q0 r2 = ka.m0.e(r2, r5, r1, r8)
            com.arflix.tv.ui.screens.search.SearchViewModel r1 = r0.this$0
            r0.L$0 = r5
            r0.L$1 = r5
            r0.L$2 = r2
            r0.L$3 = r1
            r0.label = r4
            java.lang.Object r3 = r7.j(r0)
            if (r3 != r6) goto L97
            goto Lb1
        L97:
            r18 = r2
            r2 = r1
            r1 = r18
        L9c:
            java.util.List r3 = (java.util.List) r3
            r0.L$0 = r5
            r0.L$1 = r5
            r0.L$2 = r5
            r0.L$3 = r2
            r0.L$4 = r3
            r4 = 2
            r0.label = r4
            java.lang.Object r1 = r1.o(r0)
            if (r1 != r6) goto Lb2
        Lb1:
            return r6
        Lb2:
            java.util.List r1 = (java.util.List) r1
            java.util.List r1 = com.arflix.tv.ui.screens.search.SearchViewModel.access$interleave(r2, r3, r1)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.search.SearchViewModel$buildRow$items$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super List<MediaItem>> dVar) {
        return ((SearchViewModel$buildRow$items$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
