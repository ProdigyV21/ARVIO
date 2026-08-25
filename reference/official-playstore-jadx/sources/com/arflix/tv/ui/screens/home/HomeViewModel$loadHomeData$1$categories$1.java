package com.arflix.tv.ui.screens.home;

import com.arflix.tv.data.model.CatalogConfig;
import com.arflix.tv.data.model.Category;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.repository.ContinueWatchingItem;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/Category;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeViewModel$loadHomeData$1$categories$1", f = "HomeViewModel.kt", l = {2306, 2366, 2404, 2480}, m = "invokeSuspend", v = 2)
public final class HomeViewModel$loadHomeData$1$categories$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<Category>>, Object> {
    final /* synthetic */ List<ContinueWatchingItem> $cachedContinueWatching;
    final /* synthetic */ List<Category> $currentBaseCategories;
    final /* synthetic */ Category $favoriteTvCategory;
    final /* synthetic */ long $requestId;
    final /* synthetic */ List<CatalogConfig> $savedCatalogs;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$10;
    Object L$11;
    Object L$12;
    Object L$13;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;
    final /* synthetic */ HomeViewModel this$0;

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.home.HomeViewModel$loadHomeData$1$categories$1$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.home.HomeViewModel$loadHomeData$1$categories$1$2", f = "HomeViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        final /* synthetic */ List<Category> $earlyCategories;
        final /* synthetic */ MediaItem $earlyHero;
        final /* synthetic */ long $requestId;
        int label;
        final /* synthetic */ HomeViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(long j10, HomeViewModel homeViewModel, MediaItem mediaItem, List<Category> list, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$requestId = j10;
            this.this$0 = homeViewModel;
            this.$earlyHero = mediaItem;
            this.$earlyCategories = list;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new AnonymousClass2(this.$requestId, this.this$0, this.$earlyHero, this.$earlyCategories, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            if (this.$requestId == this.this$0.loadHomeRequestId) {
                na.q0 q0Var = this.this$0._uiState;
                HomeUiState homeUiState = (HomeUiState) this.this$0._uiState.getValue();
                MediaItem heroItem = ((HomeUiState) this.this$0._uiState.getValue()).getHeroItem();
                if (heroItem == null) {
                    heroItem = this.$earlyHero;
                }
                q0Var.setValue(HomeUiState.copy$default(homeUiState, false, false, this.$earlyCategories, null, null, heroItem, null, null, false, false, 0, false, false, null, null, null, null, false, false, null, null, null, null, null, false, false, null, false, 268435400, null));
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.home.HomeViewModel$loadHomeData$1$categories$1$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.home.HomeViewModel$loadHomeData$1$categories$1$3", f = "HomeViewModel.kt", l = {2430}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass3 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        final /* synthetic */ List<CatalogConfig> $deferredBatch;
        int I$0;
        int I$1;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        final /* synthetic */ HomeViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(List<CatalogConfig> list, HomeViewModel homeViewModel, d7.d<? super AnonymousClass3> dVar) {
            super(2, dVar);
            this.$deferredBatch = list;
            this.this$0 = homeViewModel;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$deferredBatch, this.this$0, dVar);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0047  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x009d  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x013d  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x008e -> B:18:0x0091). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r42) {
            /*
                Method dump skipped, instruction units count: 320
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.home.HomeViewModel$loadHomeData$1$categories$1.AnonymousClass3.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass3) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeViewModel$loadHomeData$1$categories$1(List<CatalogConfig> list, HomeViewModel homeViewModel, List<ContinueWatchingItem> list2, List<Category> list3, Category category, long j10, d7.d<? super HomeViewModel$loadHomeData$1$categories$1> dVar) {
        super(2, dVar);
        this.$savedCatalogs = list;
        this.this$0 = homeViewModel;
        this.$cachedContinueWatching = list2;
        this.$currentBaseCategories = list3;
        this.$favoriteTvCategory = category;
        this.$requestId = j10;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        HomeViewModel$loadHomeData$1$categories$1 homeViewModel$loadHomeData$1$categories$1 = new HomeViewModel$loadHomeData$1$categories$1(this.$savedCatalogs, this.this$0, this.$cachedContinueWatching, this.$currentBaseCategories, this.$favoriteTvCategory, this.$requestId, dVar);
        homeViewModel$loadHomeData$1$categories$1.L$0 = obj;
        return homeViewModel$loadHomeData$1$categories$1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0348  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0399 A[LOOP:15: B:150:0x0393->B:152:0x0399, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x03d0 A[PHI: r0 r4 r5 r13 r14
      0x03d0: PHI (r0v18 int) = (r0v17 int), (r0v35 int) binds: [B:154:0x03cc, B:11:0x0063] A[DONT_GENERATE, DONT_INLINE]
      0x03d0: PHI (r4v24 java.lang.Object) = (r4v23 java.lang.Object), (r4v60 java.lang.Object) binds: [B:154:0x03cc, B:11:0x0063] A[DONT_GENERATE, DONT_INLINE]
      0x03d0: PHI (r5v28 java.util.List) = (r5v27 java.util.List), (r5v49 java.util.List) binds: [B:154:0x03cc, B:11:0x0063] A[DONT_GENERATE, DONT_INLINE]
      0x03d0: PHI (r13v10 ??) = (r13v29 ??), (r13v30 ??) binds: [B:154:0x03cc, B:11:0x0063] A[DONT_GENERATE, DONT_INLINE]
      0x03d0: PHI (r14v3 java.util.List<com.arflix.tv.data.model.Category>) = (r14v2 java.util.List<com.arflix.tv.data.model.Category>), (r14v12 java.util.List<com.arflix.tv.data.model.Category>) binds: [B:154:0x03cc, B:11:0x0063] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:159:0x03e9 A[LOOP:8: B:157:0x03e3->B:159:0x03e9, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0414  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0425 A[LOOP:9: B:164:0x041f->B:166:0x0425, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:169:0x043a  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0465  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0491  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x04ba  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x04d3  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x04d5  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x04ef A[LOOP:13: B:197:0x04e9->B:199:0x04ef, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:203:0x052f  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0548  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0590  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x05a1 A[LOOP:2: B:224:0x059b->B:226:0x05a1, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:229:0x05bc  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x05cc A[LOOP:3: B:231:0x05c6->B:233:0x05cc, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:237:0x05ea A[LOOP:4: B:235:0x05e4->B:237:0x05ea, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:241:0x0602  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0630  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x06a1  */
    /* JADX WARN: Type inference failed for: r13v10, types: [java.util.Collection] */
    /* JADX WARN: Type inference failed for: r13v22, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r13v29 */
    /* JADX WARN: Type inference failed for: r13v30 */
    /* JADX WARN: Type inference failed for: r13v5, types: [java.lang.Object, java.util.ArrayList, java.util.Collection] */
    /* JADX WARN: Type inference failed for: r13v9, types: [java.lang.Object] */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r32) {
        /*
            Method dump skipped, instruction units count: 1789
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.home.HomeViewModel$loadHomeData$1$categories$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<Category>> dVar) {
        return ((HomeViewModel$loadHomeData$1$categories$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
