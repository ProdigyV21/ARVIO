package com.arflix.tv.ui.screens.home;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import kotlin.Metadata;
import na.y0;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeScreenKt$HomeScreen$14$1", f = "HomeScreen.kt", l = {743}, m = "invokeSuspend", v = 2)
public final class HomeScreenKt$HomeScreen$14$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ HomeFocusState $focusState;
    final /* synthetic */ long $heroVideoIdleThresholdMs;
    final /* synthetic */ boolean $isMobile;
    final /* synthetic */ MutableState<Boolean> $suppressHeroVideoPlayback$delegate;
    int label;

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.home.HomeScreenKt$HomeScreen$14$1$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u00042\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lx6/x;", "", "", "<destruct>", "Lx6/t0;", "<anonymous>", "(Lx6/x;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.home.HomeScreenKt$HomeScreen$14$1$2", f = "HomeScreen.kt", l = {753}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements r7.p<x6.x, d7.d<? super t0>, Object> {
        final /* synthetic */ HomeFocusState $focusState;
        final /* synthetic */ long $heroVideoIdleThresholdMs;
        final /* synthetic */ MutableState<Boolean> $suppressHeroVideoPlayback$delegate;
        long J$0;
        /* synthetic */ Object L$0;
        boolean Z$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(long j10, HomeFocusState homeFocusState, MutableState<Boolean> mutableState, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$heroVideoIdleThresholdMs = j10;
            this.$focusState = homeFocusState;
            this.$suppressHeroVideoPlayback$delegate = mutableState;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$heroVideoIdleThresholdMs, this.$focusState, this.$suppressHeroVideoPlayback$delegate, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            long j10;
            x6.x xVar = (x6.x) this.L$0;
            int i10 = this.label;
            t0 t0Var = t0.f22605a;
            if (i10 == 0) {
                k2.c.G(obj);
                long jLongValue = ((Number) xVar.f22608i).longValue();
                boolean zBooleanValue = ((Boolean) xVar.f22609l).booleanValue();
                if (zBooleanValue) {
                    HomeScreenKt.HomeScreen$lambda$30(this.$suppressHeroVideoPlayback$delegate, true);
                    return t0Var;
                }
                if (jLongValue <= 0) {
                    HomeScreenKt.HomeScreen$lambda$30(this.$suppressHeroVideoPlayback$delegate, false);
                    return t0Var;
                }
                HomeScreenKt.HomeScreen$lambda$30(this.$suppressHeroVideoPlayback$delegate, true);
                long j11 = this.$heroVideoIdleThresholdMs;
                this.L$0 = null;
                this.J$0 = jLongValue;
                this.Z$0 = zBooleanValue;
                this.label = 1;
                Object objA = ka.s0.a(j11, this);
                e7.a aVar = e7.a.f15033i;
                if (objA == aVar) {
                    return aVar;
                }
                j10 = jLongValue;
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                j10 = this.J$0;
                k2.c.G(obj);
            }
            if (this.$focusState.getLastNavEventTime() == j10 && !this.$focusState.isSidebarFocused()) {
                HomeScreenKt.HomeScreen$lambda$30(this.$suppressHeroVideoPlayback$delegate, false);
            }
            return t0Var;
        }

        @Override // r7.p
        public final Object invoke(x6.x xVar, d7.d<? super t0> dVar) {
            return ((AnonymousClass2) create(xVar, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeScreenKt$HomeScreen$14$1(boolean z, MutableState<Boolean> mutableState, HomeFocusState homeFocusState, long j10, d7.d<? super HomeScreenKt$HomeScreen$14$1> dVar) {
        super(2, dVar);
        this.$isMobile = z;
        this.$suppressHeroVideoPlayback$delegate = mutableState;
        this.$focusState = homeFocusState;
        this.$heroVideoIdleThresholdMs = j10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.x invokeSuspend$lambda$0(HomeFocusState homeFocusState) {
        return new x6.x(Long.valueOf(homeFocusState.getLastNavEventTime()), Boolean.valueOf(homeFocusState.isSidebarFocused()));
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeScreenKt$HomeScreen$14$1(this.$isMobile, this.$suppressHeroVideoPlayback$delegate, this.$focusState, this.$heroVideoIdleThresholdMs, dVar);
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
        if (this.$isMobile) {
            HomeScreenKt.HomeScreen$lambda$30(this.$suppressHeroVideoPlayback$delegate, false);
            return t0Var;
        }
        na.j jVarH = y0.h(SnapshotStateKt.snapshotFlow(new r0(this.$focusState, 0)));
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$heroVideoIdleThresholdMs, this.$focusState, this.$suppressHeroVideoPlayback$delegate, null);
        this.label = 1;
        Object objG = y0.g(jVarH, anonymousClass2, this);
        e7.a aVar = e7.a.f15033i;
        return objG == aVar ? aVar : t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((HomeScreenKt$HomeScreen$14$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
