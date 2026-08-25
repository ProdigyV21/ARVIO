package com.arflix.tv.ui.screens.home;

import com.arflix.tv.data.model.CatalogConfig;
import com.arflix.tv.data.model.Category;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/model/Category;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/model/Category;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeViewModel$loadHomeData$1$categories$1$freshCustomCategories$1$1", f = "HomeViewModel.kt", l = {4859, 2464, 2461}, m = "invokeSuspend", v = 2)
public final class HomeViewModel$loadHomeData$1$categories$1$freshCustomCategories$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super Category>, Object> {
    final /* synthetic */ CatalogConfig $cfg;
    final /* synthetic */ ua.f $customSemaphore;
    int I$0;
    int I$1;
    int I$2;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    final /* synthetic */ HomeViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeViewModel$loadHomeData$1$categories$1$freshCustomCategories$1$1(ua.f fVar, HomeViewModel homeViewModel, CatalogConfig catalogConfig, d7.d<? super HomeViewModel$loadHomeData$1$categories$1$freshCustomCategories$1$1> dVar) {
        super(2, dVar);
        this.$customSemaphore = fVar;
        this.this$0 = homeViewModel;
        this.$cfg = catalogConfig;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeViewModel$loadHomeData$1$categories$1$freshCustomCategories$1$1(this.$customSemaphore, this.this$0, this.$cfg, dVar);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:0|2|(1:(1:(1:(8:7|57|8|9|36|(4:38|(1:43)(1:42)|44|45)|52|53)(2:14|15))(7:16|62|17|18|32|(5:35|36|(0)|52|53)|34))(1:23))(3:24|(1:27)|34)|60|28|(2:30|34)(4:31|32|(0)|34)|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x012d, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x012e, code lost:
    
        r3 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0130, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0131, code lost:
    
        r3 = r10;
     */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00e6 A[Catch: all -> 0x0027, Exception -> 0x002a, TryCatch #2 {Exception -> 0x002a, blocks: (B:8:0x001f, B:36:0x00da, B:38:0x00e6, B:40:0x0113, B:44:0x0120), top: B:57:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x013a A[Catch: all -> 0x0027, TRY_ENTER, TRY_LEAVE, TryCatch #3 {all -> 0x0027, blocks: (B:8:0x001f, B:36:0x00da, B:38:0x00e6, B:40:0x0113, B:44:0x0120, B:50:0x0132, B:54:0x013a), top: B:57:0x001f }] */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 319
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.home.HomeViewModel$loadHomeData$1$categories$1$freshCustomCategories$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super Category> dVar) {
        return ((HomeViewModel$loadHomeData$1$categories$1$freshCustomCategories$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
