package com.arflix.tv.ui.screens.home;

import com.arflix.tv.data.model.CatalogConfig;
import com.arflix.tv.data.model.CollectionGroupKind;
import com.arflix.tv.data.repository.CollectionTemplateManifest;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/ui/screens/home/HomeCollectionRow;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeViewModel$loadHomeData$1$collectionRows$1", f = "HomeViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
public final class HomeViewModel$loadHomeData$1$collectionRows$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends HomeCollectionRow>>, Object> {
    final /* synthetic */ List<CatalogConfig> $savedCatalogs;
    int label;
    final /* synthetic */ HomeViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeViewModel$loadHomeData$1$collectionRows$1(List<CatalogConfig> list, HomeViewModel homeViewModel, d7.d<? super HomeViewModel$loadHomeData$1$collectionRows$1> dVar) {
        super(2, dVar);
        this.$savedCatalogs = list;
        this.this$0 = homeViewModel;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeViewModel$loadHomeData$1$collectionRows$1(this.$savedCatalogs, this.this$0, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        CollectionGroupKind collectionGroup;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        List<CatalogConfig> list = this.$savedCatalogs;
        HomeViewModel homeViewModel = this.this$0;
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : list) {
            CatalogConfig catalogConfig = (CatalogConfig) obj2;
            if (homeViewModel.isCollectionTileConfig(catalogConfig) && CollectionTemplateManifest.INSTANCE.isValidCollectionConfig(catalogConfig)) {
                arrayList.add(obj2);
            }
        }
        List<CatalogConfig> list2 = this.$savedCatalogs;
        HomeViewModel homeViewModel2 = this.this$0;
        ArrayList arrayList2 = new ArrayList();
        for (CatalogConfig catalogConfig2 : list2) {
            HomeCollectionRow homeCollectionRow = null;
            if (homeViewModel2.isCollectionRailConfig(catalogConfig2) && CollectionTemplateManifest.INSTANCE.isValidCollectionConfig(catalogConfig2) && (collectionGroup = catalogConfig2.getCollectionGroup()) != null) {
                ArrayList arrayList3 = new ArrayList();
                for (Object obj3 : arrayList) {
                    if (((CatalogConfig) obj3).getCollectionGroup() == collectionGroup) {
                        arrayList3.add(obj3);
                    }
                }
                if (!arrayList3.isEmpty()) {
                    homeCollectionRow = new HomeCollectionRow(homeViewModel2.collectionRowId(collectionGroup), catalogConfig2.getTitle(), arrayList3);
                }
            }
            if (homeCollectionRow != null) {
                arrayList2.add(homeCollectionRow);
            }
        }
        return arrayList2;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<HomeCollectionRow>> dVar) {
        return ((HomeViewModel$loadHomeData$1$collectionRows$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
