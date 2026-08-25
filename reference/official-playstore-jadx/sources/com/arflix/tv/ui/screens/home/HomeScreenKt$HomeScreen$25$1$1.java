package com.arflix.tv.ui.screens.home;

import android.os.SystemClock;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeScreenKt$HomeScreen$25$1$1", f = "HomeScreen.kt", l = {AnalyticsListener.EVENT_DRM_SESSION_RELEASED}, m = "invokeSuspend", v = 2)
public final class HomeScreenKt$HomeScreen$25$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ String $currentBackdrop;
    final /* synthetic */ HomeFocusState $focusState;
    final /* synthetic */ boolean $isMobile;
    final /* synthetic */ State<String> $latestCurrentBackdrop$delegate;
    final /* synthetic */ MutableState<String> $settledBackdrop$delegate;
    long J$0;
    long J$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeScreenKt$HomeScreen$25$1$1(boolean z, String str, HomeFocusState homeFocusState, MutableState<String> mutableState, State<String> state, d7.d<? super HomeScreenKt$HomeScreen$25$1$1> dVar) {
        super(2, dVar);
        this.$isMobile = z;
        this.$currentBackdrop = str;
        this.$focusState = homeFocusState;
        this.$settledBackdrop$delegate = mutableState;
        this.$latestCurrentBackdrop$delegate = state;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeScreenKt$HomeScreen$25$1$1(this.$isMobile, this.$currentBackdrop, this.$focusState, this.$settledBackdrop$delegate, this.$latestCurrentBackdrop$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        t0 t0Var = t0.f22605a;
        if (i10 == 0) {
            k2.c.G(obj);
            if (this.$isMobile) {
                this.$settledBackdrop$delegate.setValue(this.$currentBackdrop);
                return t0Var;
            }
            String str = this.$currentBackdrop;
            if (str == null || kotlin.text.o.h0(str) || HomeScreenKt.HomeScreen$lambda$77$2(this.$settledBackdrop$delegate) == null) {
                this.$settledBackdrop$delegate.setValue(this.$currentBackdrop);
                return t0Var;
            }
            if (kotlin.jvm.internal.p.a(this.$currentBackdrop, HomeScreenKt.HomeScreen$lambda$77$2(this.$settledBackdrop$delegate))) {
                return t0Var;
            }
            long jElapsedRealtime = SystemClock.elapsedRealtime() - this.$focusState.getLastNavEventTime();
            long j10 = 420 - jElapsedRealtime;
            if (j10 < 0) {
                j10 = 0;
            }
            if (j10 > 0) {
                this.J$0 = jElapsedRealtime;
                this.J$1 = j10;
                this.label = 1;
                Object objA = ka.s0.a(j10, this);
                e7.a aVar = e7.a.f15033i;
                if (objA == aVar) {
                    return aVar;
                }
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        if (kotlin.jvm.internal.p.a(HomeScreenKt.HomeScreen$lambda$77$4(this.$latestCurrentBackdrop$delegate), this.$currentBackdrop)) {
            this.$settledBackdrop$delegate.setValue(this.$currentBackdrop);
        }
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((HomeScreenKt$HomeScreen$25$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
