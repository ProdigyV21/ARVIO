package com.arflix.tv.ui.screens.home;

import com.arflix.tv.data.model.CatalogConfig;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/CatalogConfig;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeViewModel$loadHomeData$1$savedCatalogs$1", f = "HomeViewModel.kt", l = {2242, 2246, 2247, 2248, 2248, 2257, 2260, 2264}, m = "invokeSuspend", v = 2)
public final class HomeViewModel$loadHomeData$1$savedCatalogs$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends CatalogConfig>>, Object> {
    int I$0;
    int I$1;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    final /* synthetic */ HomeViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeViewModel$loadHomeData$1$savedCatalogs$1(HomeViewModel homeViewModel, d7.d<? super HomeViewModel$loadHomeData$1$savedCatalogs$1> dVar) {
        super(2, dVar);
        this.this$0 = homeViewModel;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        HomeViewModel$loadHomeData$1$savedCatalogs$1 homeViewModel$loadHomeData$1$savedCatalogs$1 = new HomeViewModel$loadHomeData$1$savedCatalogs$1(this.this$0, dVar);
        homeViewModel$loadHomeData$1$savedCatalogs$1.L$0 = obj;
        return homeViewModel$loadHomeData$1$savedCatalogs$1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:65:0x01a6, code lost:
    
        if (r9 == r4) goto L66;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0087 A[PHI: r1 r5
      0x0087: PHI (r1v12 int) = (r1v10 int), (r1v13 int) binds: [B:43:0x0106, B:26:0x0084] A[DONT_GENERATE, DONT_INLINE]
      0x0087: PHI (r5v14 com.arflix.tv.ui.screens.home.HomeViewModel) = (r5v9 com.arflix.tv.ui.screens.home.HomeViewModel), (r5v21 com.arflix.tv.ui.screens.home.HomeViewModel) binds: [B:43:0x0106, B:26:0x0084] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00ef A[Catch: all -> 0x0036, PHI: r1 r5 r9
      0x00ef: PHI (r1v10 int) = (r1v8 int), (r1v11 int) binds: [B:40:0x00eb, B:29:0x0094] A[DONT_GENERATE, DONT_INLINE]
      0x00ef: PHI (r5v9 com.arflix.tv.ui.screens.home.HomeViewModel) = (r5v4 com.arflix.tv.ui.screens.home.HomeViewModel), (r5v13 com.arflix.tv.ui.screens.home.HomeViewModel) binds: [B:40:0x00eb, B:29:0x0094] A[DONT_GENERATE, DONT_INLINE]
      0x00ef: PHI (r9v18 java.lang.Object) = (r9v17 java.lang.Object), (r9v0 java.lang.Object) binds: [B:40:0x00eb, B:29:0x0094] A[DONT_GENERATE, DONT_INLINE], TryCatch #0 {all -> 0x0036, blocks: (B:12:0x0031, B:58:0x017a, B:17:0x0047, B:55:0x0162, B:20:0x005a, B:52:0x0142, B:23:0x0071, B:48:0x0129, B:26:0x0084, B:45:0x010a, B:29:0x0094, B:42:0x00ef, B:32:0x00a2, B:39:0x00d4, B:35:0x00ab), top: B:75:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0129 A[Catch: all -> 0x0036, PHI: r1 r5 r6 r9
      0x0129: PHI (r1v14 int) = (r1v12 int), (r1v15 int) binds: [B:46:0x0125, B:23:0x0071] A[DONT_GENERATE, DONT_INLINE]
      0x0129: PHI (r5v22 com.arflix.tv.data.repository.CatalogRepository) = (r5v15 com.arflix.tv.data.repository.CatalogRepository), (r5v25 com.arflix.tv.data.repository.CatalogRepository) binds: [B:46:0x0125, B:23:0x0071] A[DONT_GENERATE, DONT_INLINE]
      0x0129: PHI (r6v6 com.arflix.tv.ui.screens.home.HomeViewModel) = (r6v5 com.arflix.tv.ui.screens.home.HomeViewModel), (r6v12 com.arflix.tv.ui.screens.home.HomeViewModel) binds: [B:46:0x0125, B:23:0x0071] A[DONT_GENERATE, DONT_INLINE]
      0x0129: PHI (r9v23 java.lang.Object) = (r9v22 java.lang.Object), (r9v0 java.lang.Object) binds: [B:46:0x0125, B:23:0x0071] A[DONT_GENERATE, DONT_INLINE], TryCatch #0 {all -> 0x0036, blocks: (B:12:0x0031, B:58:0x017a, B:17:0x0047, B:55:0x0162, B:20:0x005a, B:52:0x0142, B:23:0x0071, B:48:0x0129, B:26:0x0084, B:45:0x010a, B:29:0x0094, B:42:0x00ef, B:32:0x00a2, B:39:0x00d4, B:35:0x00ab), top: B:75:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0162 A[Catch: all -> 0x0036, PHI: r1 r5
      0x0162: PHI (r1v18 int) = (r1v16 int), (r1v19 int) binds: [B:53:0x015f, B:17:0x0047] A[DONT_GENERATE, DONT_INLINE]
      0x0162: PHI (r5v33 com.arflix.tv.ui.screens.home.HomeViewModel) = (r5v26 com.arflix.tv.ui.screens.home.HomeViewModel), (r5v39 com.arflix.tv.ui.screens.home.HomeViewModel) binds: [B:53:0x015f, B:17:0x0047] A[DONT_GENERATE, DONT_INLINE], TryCatch #0 {all -> 0x0036, blocks: (B:12:0x0031, B:58:0x017a, B:17:0x0047, B:55:0x0162, B:20:0x005a, B:52:0x0142, B:23:0x0071, B:48:0x0129, B:26:0x0084, B:45:0x010a, B:29:0x0094, B:42:0x00ef, B:32:0x00a2, B:39:0x00d4, B:35:0x00ab), top: B:75:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x018c A[Catch: all -> 0x0026, TRY_ENTER, TryCatch #1 {all -> 0x0026, blocks: (B:7:0x0021, B:67:0x01a9, B:64:0x018c), top: B:75:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:78:? A[RETURN, SYNTHETIC] */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            Method dump skipped, instruction units count: 472
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.home.HomeViewModel$loadHomeData$1$savedCatalogs$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<CatalogConfig>> dVar) {
        return ((HomeViewModel$loadHomeData$1$savedCatalogs$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
