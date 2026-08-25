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
@f7.e(c = "com.arflix.tv.ui.screens.collections.CollectionDetailsScreenKt$CollectionDetailsScreen$requestTabFocus$1", f = "CollectionDetailsScreen.kt", l = {541, 566}, m = "invokeSuspend", v = 2)
public final class CollectionDetailsScreenKt$CollectionDetailsScreen$requestTabFocus$1 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
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
    int I$0;
    int I$1;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CollectionTab.values().length];
            try {
                iArr[CollectionTab.MOVIES.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CollectionTab.SERIES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public CollectionDetailsScreenKt$CollectionDetailsScreen$requestTabFocus$1(b1 b1Var, b1 b1Var2, MutableState<Boolean> mutableState, FocusRequester focusRequester, FocusRequester focusRequester2, State<? extends CollectionTab> state, State<Boolean> state2, State<Boolean> state3, MutableState<Integer> mutableState2, MutableState<Integer> mutableState3, MutableState<Integer> mutableState4, d7.d<? super CollectionDetailsScreenKt$CollectionDetailsScreen$requestTabFocus$1> dVar) {
        super(2, dVar);
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
        CollectionDetailsScreenKt$CollectionDetailsScreen$requestTabFocus$1 collectionDetailsScreenKt$CollectionDetailsScreen$requestTabFocus$1 = new CollectionDetailsScreenKt$CollectionDetailsScreen$requestTabFocus$1(this.$moviesGridState, this.$seriesGridState, this.$hasReceivedInitialFocus$delegate, this.$moviesTabFocusRequester, this.$seriesTabFocusRequester, this.$currentTab$delegate, this.$currentSupportsMovies$delegate, this.$currentSupportsSeries$delegate, this.$lastFocusedMovieIndex$delegate, this.$lastFocusedSeriesIndex$delegate, this.$pendingFocusIndex$delegate, dVar);
        collectionDetailsScreenKt$CollectionDetailsScreen$requestTabFocus$1.L$0 = obj;
        return collectionDetailsScreenKt$CollectionDetailsScreen$requestTabFocus$1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x00d4, code lost:
    
        if (androidx.tv.foundation.lazy.grid.b1.a(r0, r1, r7) == r4) goto L48;
     */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            Method dump skipped, instruction units count: 274
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.collections.CollectionDetailsScreenKt$CollectionDetailsScreen$requestTabFocus$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((CollectionDetailsScreenKt$CollectionDetailsScreen$requestTabFocus$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
