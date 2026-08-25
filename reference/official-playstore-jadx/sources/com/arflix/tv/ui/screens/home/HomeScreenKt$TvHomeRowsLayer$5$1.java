package com.arflix.tv.ui.screens.home;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import na.y0;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeScreenKt$TvHomeRowsLayer$5$1", f = "HomeScreen.kt", l = {3073}, m = "invokeSuspend", v = 2)
public final class HomeScreenKt$TvHomeRowsLayer$5$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ long $fastScrollThresholdMs;
    final /* synthetic */ HomeFocusState $focusState;
    final /* synthetic */ MutableState<Boolean> $isFastScrolling$delegate;
    int label;

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.home.HomeScreenKt$TvHomeRowsLayer$5$1$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", LinkHeader.Parameters.Anchor, "Lx6/t0;", "<anonymous>", "(J)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.home.HomeScreenKt$TvHomeRowsLayer$5$1$2", f = "HomeScreen.kt", l = {3079}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements r7.p<Long, d7.d<? super t0>, Object> {
        final /* synthetic */ long $fastScrollThresholdMs;
        final /* synthetic */ HomeFocusState $focusState;
        final /* synthetic */ MutableState<Boolean> $isFastScrolling$delegate;
        /* synthetic */ long J$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(long j10, HomeFocusState homeFocusState, MutableState<Boolean> mutableState, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$fastScrollThresholdMs = j10;
            this.$focusState = homeFocusState;
            this.$isFastScrolling$delegate = mutableState;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$fastScrollThresholdMs, this.$focusState, this.$isFastScrolling$delegate, dVar);
            anonymousClass2.J$0 = ((Number) obj).longValue();
            return anonymousClass2;
        }

        public final Object invoke(long j10, d7.d<? super t0> dVar) {
            return ((AnonymousClass2) create(Long.valueOf(j10), dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            long j10 = this.J$0;
            int i10 = this.label;
            t0 t0Var = t0.f22605a;
            if (i10 == 0) {
                k2.c.G(obj);
                if (j10 <= 0) {
                    HomeScreenKt.TvHomeRowsLayer_GVfq81o$lambda$13(this.$isFastScrolling$delegate, false);
                    return t0Var;
                }
                HomeScreenKt.TvHomeRowsLayer_GVfq81o$lambda$13(this.$isFastScrolling$delegate, true);
                long j11 = this.$fastScrollThresholdMs;
                this.J$0 = j10;
                this.label = 1;
                Object objA = ka.s0.a(j11, this);
                e7.a aVar = e7.a.f15033i;
                if (objA == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            if (this.$focusState.getLastNavEventTime() == j10) {
                HomeScreenKt.TvHomeRowsLayer_GVfq81o$lambda$13(this.$isFastScrolling$delegate, false);
            }
            return t0Var;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return invoke(((Number) obj).longValue(), (d7.d<? super t0>) obj2);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeScreenKt$TvHomeRowsLayer$5$1(HomeFocusState homeFocusState, long j10, MutableState<Boolean> mutableState, d7.d<? super HomeScreenKt$TvHomeRowsLayer$5$1> dVar) {
        super(2, dVar);
        this.$focusState = homeFocusState;
        this.$fastScrollThresholdMs = j10;
        this.$isFastScrolling$delegate = mutableState;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeScreenKt$TvHomeRowsLayer$5$1(this.$focusState, this.$fastScrollThresholdMs, this.$isFastScrolling$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            na.j jVarH = y0.h(SnapshotStateKt.snapshotFlow(new r0(this.$focusState, 3)));
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$fastScrollThresholdMs, this.$focusState, this.$isFastScrolling$delegate, null);
            this.label = 1;
            Object objG = y0.g(jVarH, anonymousClass2, this);
            e7.a aVar = e7.a.f15033i;
            if (objG == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((HomeScreenKt$TvHomeRowsLayer$5$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
