package com.arflix.tv.ui.screens.home;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusRequester;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeScreenKt$HomeInputLayer$10$1", f = "HomeScreen.kt", l = {2285}, m = "invokeSuspend", v = 2)
public final class HomeScreenKt$HomeInputLayer$10$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ long $focusRecoveryDelayMs;
    final /* synthetic */ FocusRequester $focusRequester;
    final /* synthetic */ boolean $isContextMenuOpen;
    final /* synthetic */ boolean $isMobile;
    final /* synthetic */ MutableState<Boolean> $rootHasFocus$delegate;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeScreenKt$HomeInputLayer$10$1(boolean z, boolean z5, long j10, MutableState<Boolean> mutableState, FocusRequester focusRequester, d7.d<? super HomeScreenKt$HomeInputLayer$10$1> dVar) {
        super(2, dVar);
        this.$isMobile = z;
        this.$isContextMenuOpen = z5;
        this.$focusRecoveryDelayMs = j10;
        this.$rootHasFocus$delegate = mutableState;
        this.$focusRequester = focusRequester;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        HomeScreenKt$HomeInputLayer$10$1 homeScreenKt$HomeInputLayer$10$1 = new HomeScreenKt$HomeInputLayer$10$1(this.$isMobile, this.$isContextMenuOpen, this.$focusRecoveryDelayMs, this.$rootHasFocus$delegate, this.$focusRequester, dVar);
        homeScreenKt$HomeInputLayer$10$1.L$0 = obj;
        return homeScreenKt$HomeInputLayer$10$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        ka.k0 k0Var = (ka.k0) this.L$0;
        int i10 = this.label;
        t0 t0Var = t0.f22605a;
        if (i10 == 0) {
            k2.c.G(obj);
            if (!this.$isMobile && !this.$isContextMenuOpen && !HomeScreenKt.HomeInputLayer_0DYc4_I$lambda$16(this.$rootHasFocus$delegate)) {
                long j10 = this.$focusRecoveryDelayMs;
                this.L$0 = k0Var;
                this.label = 1;
                Object objA = ka.s0.a(j10, this);
                e7.a aVar = e7.a.f15033i;
                if (objA == aVar) {
                    return aVar;
                }
            }
            return t0Var;
        }
        if (i10 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        if (!HomeScreenKt.HomeInputLayer_0DYc4_I$lambda$16(this.$rootHasFocus$delegate) && !this.$isContextMenuOpen) {
            try {
                this.$focusRequester.requestFocus();
            } catch (Throwable unused) {
            }
        }
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((HomeScreenKt$HomeInputLayer$10$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
