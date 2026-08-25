package com.arflix.tv.ui.screens.collections;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.ui.focus.FocusRequester;
import androidx.tv.foundation.lazy.grid.b1;
import ka.k0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.collections.CollectionDetailsScreenKt$CollectionDetailsScreen$5$1", f = "CollectionDetailsScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class CollectionDetailsScreenKt$CollectionDetailsScreen$5$1 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ k0 $coroutineScope;
    final /* synthetic */ State<Boolean> $currentSupportsMovies$delegate;
    final /* synthetic */ State<Boolean> $currentSupportsSeries$delegate;
    final /* synthetic */ State<CollectionTab> $currentTab$delegate;
    final /* synthetic */ MutableState<Boolean> $hasReceivedInitialFocus$delegate;
    final /* synthetic */ MutableState<Integer> $lastFocusedMovieIndex$delegate;
    final /* synthetic */ MutableState<Integer> $lastFocusedSeriesIndex$delegate;
    final /* synthetic */ b1 $moviesGridState;
    final /* synthetic */ FocusRequester $moviesTabFocusRequester;
    final /* synthetic */ MutableState<Integer> $pendingFocusIndex$delegate;
    final /* synthetic */ b1 $seriesGridState;
    final /* synthetic */ FocusRequester $seriesTabFocusRequester;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public CollectionDetailsScreenKt$CollectionDetailsScreen$5$1(k0 k0Var, b1 b1Var, b1 b1Var2, MutableState<Boolean> mutableState, FocusRequester focusRequester, FocusRequester focusRequester2, State<? extends CollectionTab> state, State<Boolean> state2, State<Boolean> state3, MutableState<Integer> mutableState2, MutableState<Integer> mutableState3, MutableState<Integer> mutableState4, d7.d<? super CollectionDetailsScreenKt$CollectionDetailsScreen$5$1> dVar) {
        super(2, dVar);
        this.$coroutineScope = k0Var;
        this.$moviesGridState = b1Var;
        this.$seriesGridState = b1Var2;
        this.$hasReceivedInitialFocus$delegate = mutableState;
        this.$moviesTabFocusRequester = focusRequester;
        this.$seriesTabFocusRequester = focusRequester2;
        this.$currentTab$delegate = state;
        this.$currentSupportsMovies$delegate = state2;
        this.$currentSupportsSeries$delegate = state3;
        this.$lastFocusedMovieIndex$delegate = mutableState2;
        this.$lastFocusedSeriesIndex$delegate = mutableState3;
        this.$pendingFocusIndex$delegate = mutableState4;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new CollectionDetailsScreenKt$CollectionDetailsScreen$5$1(this.$coroutineScope, this.$moviesGridState, this.$seriesGridState, this.$hasReceivedInitialFocus$delegate, this.$moviesTabFocusRequester, this.$seriesTabFocusRequester, this.$currentTab$delegate, this.$currentSupportsMovies$delegate, this.$currentSupportsSeries$delegate, this.$lastFocusedMovieIndex$delegate, this.$lastFocusedSeriesIndex$delegate, this.$pendingFocusIndex$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        CollectionDetailsScreenKt.CollectionDetailsScreen$requestTabFocus(this.$coroutineScope, this.$moviesGridState, this.$seriesGridState, this.$hasReceivedInitialFocus$delegate, this.$moviesTabFocusRequester, this.$seriesTabFocusRequester, this.$currentTab$delegate, this.$currentSupportsMovies$delegate, this.$currentSupportsSeries$delegate, this.$lastFocusedMovieIndex$delegate, this.$lastFocusedSeriesIndex$delegate, this.$pendingFocusIndex$delegate);
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((CollectionDetailsScreenKt$CollectionDetailsScreen$5$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
