package com.arflix.tv.ui.screens.home;

import androidx.compose.runtime.MutableState;
import com.arflix.tv.data.model.MediaItem;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeScreenKt$HomeScreen$19$1", f = "HomeScreen.kt", l = {881}, m = "invokeSuspend", v = 2)
public final class HomeScreenKt$HomeScreen$19$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ MutableState<Boolean> $contextMenuIsInWatchlist$delegate;
    final /* synthetic */ MutableState<MediaItem> $contextMenuItem$delegate;
    final /* synthetic */ MutableState<Boolean> $showContextMenu$delegate;
    final /* synthetic */ HomeViewModel $viewModel;
    Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeScreenKt$HomeScreen$19$1(HomeViewModel homeViewModel, MutableState<Boolean> mutableState, MutableState<MediaItem> mutableState2, MutableState<Boolean> mutableState3, d7.d<? super HomeScreenKt$HomeScreen$19$1> dVar) {
        super(2, dVar);
        this.$viewModel = homeViewModel;
        this.$showContextMenu$delegate = mutableState;
        this.$contextMenuItem$delegate = mutableState2;
        this.$contextMenuIsInWatchlist$delegate = mutableState3;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeScreenKt$HomeScreen$19$1(this.$viewModel, this.$showContextMenu$delegate, this.$contextMenuItem$delegate, this.$contextMenuIsInWatchlist$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        boolean zBooleanValue;
        MutableState<Boolean> mutableState;
        MutableState<Boolean> mutableState2;
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            zBooleanValue = false;
            if (!HomeScreenKt.HomeScreen$lambda$34(this.$showContextMenu$delegate)) {
                HomeScreenKt.HomeScreen$lambda$44(this.$contextMenuIsInWatchlist$delegate, false);
                return t0.f22605a;
            }
            MediaItem mediaItemHomeScreen$lambda$37 = HomeScreenKt.HomeScreen$lambda$37(this.$contextMenuItem$delegate);
            mutableState = this.$contextMenuIsInWatchlist$delegate;
            if (mediaItemHomeScreen$lambda$37 != null) {
                HomeViewModel homeViewModel = this.$viewModel;
                this.L$0 = null;
                this.L$1 = mutableState;
                this.label = 1;
                obj = homeViewModel.isInWatchlist(mediaItemHomeScreen$lambda$37, this);
                e7.a aVar = e7.a.f15033i;
                if (obj == aVar) {
                    return aVar;
                }
                mutableState2 = mutableState;
            }
            HomeScreenKt.HomeScreen$lambda$44(mutableState, zBooleanValue);
            return t0.f22605a;
        }
        if (i10 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        mutableState2 = (MutableState) this.L$1;
        k2.c.G(obj);
        mutableState = mutableState2;
        zBooleanValue = ((Boolean) obj).booleanValue();
        HomeScreenKt.HomeScreen$lambda$44(mutableState, zBooleanValue);
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((HomeScreenKt$HomeScreen$19$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
