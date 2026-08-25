package com.arflix.tv.ui.screens.home;

import android.os.SystemClock;
import androidx.compose.runtime.SnapshotStateKt;
import kotlin.Metadata;
import na.y0;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeScreenKt$HomeScreen$16$1", f = "HomeScreen.kt", l = {784}, m = "invokeSuspend", v = 2)
public final class HomeScreenKt$HomeScreen$16$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ boolean $allowHomeBackgroundWork;
    final /* synthetic */ HomeFocusState $focusState;
    final /* synthetic */ HomeViewModel $viewModel;
    long J$0;
    int label;

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.home.HomeScreenKt$HomeScreen$16$1$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u00042\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lx6/j0;", "", "", "<destruct>", "Lx6/t0;", "<anonymous>", "(Lx6/j0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.home.HomeScreenKt$HomeScreen$16$1$2", f = "HomeScreen.kt", l = {791}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements r7.p<x6.j0, d7.d<? super t0>, Object> {
        final /* synthetic */ HomeFocusState $focusState;
        final /* synthetic */ long $rowPreloadIdleMs;
        final /* synthetic */ HomeViewModel $viewModel;
        int I$0;
        int I$1;
        long J$0;
        long J$1;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(long j10, HomeFocusState homeFocusState, HomeViewModel homeViewModel, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$rowPreloadIdleMs = j10;
            this.$focusState = homeFocusState;
            this.$viewModel = homeViewModel;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$rowPreloadIdleMs, this.$focusState, this.$viewModel, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int iIntValue;
            int iIntValue2;
            int i10;
            int currentRowIndex;
            t0 t0Var;
            x6.j0 j0Var = (x6.j0) this.L$0;
            int i11 = this.label;
            if (i11 == 0) {
                k2.c.G(obj);
                iIntValue = ((Number) j0Var.f22587i).intValue();
                iIntValue2 = ((Number) j0Var.f22588l).intValue();
                long jLongValue = ((Number) j0Var.f22589m).longValue();
                long jElapsedRealtime = jLongValue > 0 ? SystemClock.elapsedRealtime() - jLongValue : this.$rowPreloadIdleMs;
                long j10 = this.$rowPreloadIdleMs;
                if (jElapsedRealtime < j10) {
                    this.L$0 = null;
                    this.I$0 = iIntValue;
                    this.I$1 = iIntValue2;
                    this.J$0 = jLongValue;
                    this.J$1 = jElapsedRealtime;
                    this.label = 1;
                    Object objA = ka.s0.a(j10 - jElapsedRealtime, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objA == aVar) {
                        return aVar;
                    }
                    i10 = iIntValue2;
                }
                currentRowIndex = this.$focusState.getCurrentRowIndex();
                t0Var = t0.f22605a;
                if (currentRowIndex == iIntValue || this.$focusState.getCurrentItemIndex() != iIntValue2) {
                    return t0Var;
                }
                this.$viewModel.onFocusChanged(iIntValue, iIntValue2, true);
                this.$viewModel.preloadLogosForCategory(iIntValue, true);
                this.$viewModel.preloadLogosForCategory(iIntValue + 1, false);
                this.$viewModel.preloadLogosForCategory(iIntValue + 2, false);
                return t0Var;
            }
            if (i11 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            i10 = this.I$1;
            iIntValue = this.I$0;
            k2.c.G(obj);
            iIntValue2 = i10;
            currentRowIndex = this.$focusState.getCurrentRowIndex();
            t0Var = t0.f22605a;
            if (currentRowIndex == iIntValue) {
                return t0Var;
            }
            this.$viewModel.onFocusChanged(iIntValue, iIntValue2, true);
            this.$viewModel.preloadLogosForCategory(iIntValue, true);
            this.$viewModel.preloadLogosForCategory(iIntValue + 1, false);
            this.$viewModel.preloadLogosForCategory(iIntValue + 2, false);
            return t0Var;
        }

        @Override // r7.p
        public final Object invoke(x6.j0 j0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass2) create(j0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeScreenKt$HomeScreen$16$1(boolean z, HomeFocusState homeFocusState, HomeViewModel homeViewModel, d7.d<? super HomeScreenKt$HomeScreen$16$1> dVar) {
        super(2, dVar);
        this.$allowHomeBackgroundWork = z;
        this.$focusState = homeFocusState;
        this.$viewModel = homeViewModel;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.j0 invokeSuspend$lambda$0(HomeFocusState homeFocusState) {
        return new x6.j0(Integer.valueOf(homeFocusState.getCurrentRowIndex()), Integer.valueOf(homeFocusState.getCurrentItemIndex()), Long.valueOf(homeFocusState.getLastNavEventTime()));
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeScreenKt$HomeScreen$16$1(this.$allowHomeBackgroundWork, this.$focusState, this.$viewModel, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        t0 t0Var = t0.f22605a;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return t0Var;
        }
        k2.c.G(obj);
        if (this.$allowHomeBackgroundWork) {
            na.j jVarH = y0.h(SnapshotStateKt.snapshotFlow(new r0(this.$focusState, 1)));
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(320L, this.$focusState, this.$viewModel, null);
            this.J$0 = 320L;
            this.label = 1;
            Object objG = y0.g(jVarH, anonymousClass2, this);
            e7.a aVar = e7.a.f15033i;
            if (objG == aVar) {
                return aVar;
            }
        }
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((HomeScreenKt$HomeScreen$16$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
