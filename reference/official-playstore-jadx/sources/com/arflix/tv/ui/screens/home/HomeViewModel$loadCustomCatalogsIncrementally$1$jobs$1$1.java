package com.arflix.tv.ui.screens.home;

import com.arflix.tv.data.model.CatalogConfig;
import com.arflix.tv.data.model.Category;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeViewModel$loadCustomCatalogsIncrementally$1$jobs$1$1", f = "HomeViewModel.kt", l = {4859, 3001, 2998, 3028}, m = "invokeSuspend", v = 2)
public final class HomeViewModel$loadCustomCatalogsIncrementally$1$jobs$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ Map<String, Category> $baseById;
    final /* synthetic */ CatalogConfig $catalog;
    final /* synthetic */ ua.f $catalogSemaphore;
    final /* synthetic */ Set<String> $customIds;
    final /* synthetic */ Map<String, Category> $existingCustomById;
    final /* synthetic */ kotlin.jvm.internal.j0 $lastCustomCatalogPublishMs;
    final /* synthetic */ ConcurrentHashMap<String, Category> $loadedById;
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$10;
    Object L$11;
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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeViewModel$loadCustomCatalogsIncrementally$1$jobs$1$1(ua.f fVar, ConcurrentHashMap<String, Category> concurrentHashMap, CatalogConfig catalogConfig, HomeViewModel homeViewModel, kotlin.jvm.internal.j0 j0Var, Set<String> set, Map<String, Category> map, Map<String, Category> map2, d7.d<? super HomeViewModel$loadCustomCatalogsIncrementally$1$jobs$1$1> dVar) {
        super(2, dVar);
        this.$catalogSemaphore = fVar;
        this.$loadedById = concurrentHashMap;
        this.$catalog = catalogConfig;
        this.this$0 = homeViewModel;
        this.$lastCustomCatalogPublishMs = j0Var;
        this.$customIds = set;
        this.$existingCustomById = map;
        this.$baseById = map2;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        HomeViewModel$loadCustomCatalogsIncrementally$1$jobs$1$1 homeViewModel$loadCustomCatalogsIncrementally$1$jobs$1$1 = new HomeViewModel$loadCustomCatalogsIncrementally$1$jobs$1$1(this.$catalogSemaphore, this.$loadedById, this.$catalog, this.this$0, this.$lastCustomCatalogPublishMs, this.$customIds, this.$existingCustomById, this.$baseById, dVar);
        homeViewModel$loadCustomCatalogsIncrementally$1$jobs$1$1.L$0 = obj;
        return homeViewModel$loadCustomCatalogsIncrementally$1$jobs$1$1;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(9:0|2|84|(1:(15:(1:(1:(4:8|9|78|79)(2:13|14))(17:15|91|16|17|89|41|42|43|87|55|(1:57)|58|(2:62|73)(3:63|(1:70)(1:67)|71)|72|74|(3:77|78|79)|76))(12:20|85|21|22|93|34|35|82|36|37|(15:40|89|41|42|43|87|55|(0)|58|(1:60)|62|73|72|74|(0))|76)|53|54|43|87|55|(0)|58|(0)|62|73|72|74|(0)|76)(1:25))(3:26|(1:29)|76)|95|30|(8:33|93|34|35|82|36|37|(0))|76|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x01cf, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x01d0, code lost:
    
        r12 = r5;
        r8 = r13;
        r4 = 0;
        r13 = r1;
        r1 = r11;
        r11 = r6;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:40:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01ea A[Catch: all -> 0x023a, TryCatch #3 {all -> 0x023a, blocks: (B:55:0x01e1, B:58:0x01e6, B:60:0x01ea, B:63:0x01f7, B:65:0x022d, B:71:0x0240, B:74:0x0283, B:73:0x0250), top: B:87:0x01e1 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x02b2  */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v1, types: [ua.f] */
    /* JADX WARN: Type inference failed for: r1v12 */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r27) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 701
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.home.HomeViewModel$loadCustomCatalogsIncrementally$1$jobs$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((HomeViewModel$loadCustomCatalogsIncrementally$1$jobs$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
