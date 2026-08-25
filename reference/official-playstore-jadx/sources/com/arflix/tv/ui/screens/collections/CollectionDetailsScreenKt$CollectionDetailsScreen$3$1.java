package com.arflix.tv.ui.screens.collections;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import ka.k0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.collections.CollectionDetailsScreenKt$CollectionDetailsScreen$3$1", f = "CollectionDetailsScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class CollectionDetailsScreenKt$CollectionDetailsScreen$3$1 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ MutableState<CollectionTab> $selectedTab$delegate;
    final /* synthetic */ State<CollectionDetailsUiState> $uiState$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CollectionDetailsScreenKt$CollectionDetailsScreen$3$1(MutableState<CollectionTab> mutableState, State<CollectionDetailsUiState> state, d7.d<? super CollectionDetailsScreenKt$CollectionDetailsScreen$3$1> dVar) {
        super(2, dVar);
        this.$selectedTab$delegate = mutableState;
        this.$uiState$delegate = state;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new CollectionDetailsScreenKt$CollectionDetailsScreen$3$1(this.$selectedTab$delegate, this.$uiState$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        CollectionTab collectionTabCollectionDetailsScreen$lambda$6 = CollectionDetailsScreenKt.CollectionDetailsScreen$lambda$6(this.$selectedTab$delegate);
        CollectionTab collectionTab = CollectionTab.MOVIES;
        if (collectionTabCollectionDetailsScreen$lambda$6 != collectionTab || !CollectionDetailsScreenKt.CollectionDetailsScreen$lambda$1(this.$uiState$delegate).getSupportsMovies()) {
            CollectionTab collectionTabCollectionDetailsScreen$lambda$62 = CollectionDetailsScreenKt.CollectionDetailsScreen$lambda$6(this.$selectedTab$delegate);
            CollectionTab collectionTab2 = CollectionTab.SERIES;
            if ((collectionTabCollectionDetailsScreen$lambda$62 == collectionTab2 && CollectionDetailsScreenKt.CollectionDetailsScreen$lambda$1(this.$uiState$delegate).getSupportsSeries()) || (!CollectionDetailsScreenKt.CollectionDetailsScreen$lambda$1(this.$uiState$delegate).getSupportsMovies() && CollectionDetailsScreenKt.CollectionDetailsScreen$lambda$1(this.$uiState$delegate).getSupportsSeries())) {
                collectionTab = collectionTab2;
            }
        }
        if (collectionTab != CollectionDetailsScreenKt.CollectionDetailsScreen$lambda$6(this.$selectedTab$delegate)) {
            this.$selectedTab$delegate.setValue(collectionTab);
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((CollectionDetailsScreenKt$CollectionDetailsScreen$3$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
