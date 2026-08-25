package com.arflix.tv.ui.screens.home;

import android.os.SystemClock;
import com.arflix.tv.data.model.Category;
import com.arflix.tv.ui.screens.home.HomeViewModel;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeViewModel$loadCustomCatalogsIncrementally$1$publishMergedThrottled$2", f = "HomeViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
public final class HomeViewModel$loadCustomCatalogsIncrementally$1$publishMergedThrottled$2 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ Map<String, Category> $baseById;
    final /* synthetic */ Set<String> $customIds;
    final /* synthetic */ Map<String, Category> $existingCustomById;
    final /* synthetic */ kotlin.jvm.internal.j0 $lastCustomCatalogPublishMs;
    final /* synthetic */ ConcurrentHashMap<String, Category> $loadedById;
    int label;
    final /* synthetic */ HomeViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeViewModel$loadCustomCatalogsIncrementally$1$publishMergedThrottled$2(kotlin.jvm.internal.j0 j0Var, HomeViewModel homeViewModel, Set<String> set, ConcurrentHashMap<String, Category> concurrentHashMap, Map<String, Category> map, Map<String, Category> map2, d7.d<? super HomeViewModel$loadCustomCatalogsIncrementally$1$publishMergedThrottled$2> dVar) {
        super(2, dVar);
        this.$lastCustomCatalogPublishMs = j0Var;
        this.this$0 = homeViewModel;
        this.$customIds = set;
        this.$loadedById = concurrentHashMap;
        this.$existingCustomById = map;
        this.$baseById = map2;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeViewModel$loadCustomCatalogsIncrementally$1$publishMergedThrottled$2(this.$lastCustomCatalogPublishMs, this.this$0, this.$customIds, this.$loadedById, this.$existingCustomById, this.$baseById, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        HomeViewModel.C13621.invokeSuspend$publishMerged(this.this$0, this.$customIds, this.$loadedById, this.$existingCustomById, this.$baseById);
        this.$lastCustomCatalogPublishMs.f19745i = SystemClock.elapsedRealtime();
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((HomeViewModel$loadCustomCatalogsIncrementally$1$publishMergedThrottled$2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
