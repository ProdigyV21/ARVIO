package androidx.compose.foundation.layout;

import android.view.WindowInsetsAnimationController;
import androidx.compose.animation.core.SuspendAnimationKt;
import f7.j;
import java.util.concurrent.CancellationException;
import ka.k0;
import ka.m0;
import ka.v1;
import kotlin.Metadata;
import kotlin.jvm.internal.h0;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@f7.e(c = "androidx.compose.foundation.layout.WindowInsetsNestedScrollConnection$fling$2", f = "WindowInsetsConnection.android.kt", l = {364}, m = "invokeSuspend")
public final class WindowInsetsNestedScrollConnection$fling$2 extends j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ WindowInsetsAnimationController $animationController;
    final /* synthetic */ int $current;
    final /* synthetic */ h0 $endVelocity;
    final /* synthetic */ float $flingAmount;
    final /* synthetic */ int $hidden;
    final /* synthetic */ int $shown;
    final /* synthetic */ SplineBasedFloatDecayAnimationSpec $spec;
    final /* synthetic */ boolean $targetShown;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ WindowInsetsNestedScrollConnection this$0;

    /* JADX INFO: renamed from: androidx.compose.foundation.layout.WindowInsetsNestedScrollConnection$fling$2$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.foundation.layout.WindowInsetsNestedScrollConnection$fling$2$1", f = "WindowInsetsConnection.android.kt", l = {348}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ WindowInsetsAnimationController $animationController;
        final /* synthetic */ int $current;
        final /* synthetic */ h0 $endVelocity;
        final /* synthetic */ float $flingAmount;
        final /* synthetic */ int $hidden;
        final /* synthetic */ int $shown;
        final /* synthetic */ SplineBasedFloatDecayAnimationSpec $spec;
        final /* synthetic */ boolean $targetShown;
        int label;
        final /* synthetic */ WindowInsetsNestedScrollConnection this$0;

        /* JADX INFO: renamed from: androidx.compose.foundation.layout.WindowInsetsNestedScrollConnection$fling$2$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"", "value", "velocity", "Lx6/t0;", "invoke", "(FF)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class C00311 extends r implements p<Float, Float, t0> {
            final /* synthetic */ WindowInsetsAnimationController $animationController;
            final /* synthetic */ h0 $endVelocity;
            final /* synthetic */ int $hidden;
            final /* synthetic */ int $shown;
            final /* synthetic */ boolean $targetShown;
            final /* synthetic */ WindowInsetsNestedScrollConnection this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00311(int i10, int i11, WindowInsetsNestedScrollConnection windowInsetsNestedScrollConnection, h0 h0Var, WindowInsetsAnimationController windowInsetsAnimationController, boolean z) {
                super(2);
                this.$hidden = i10;
                this.$shown = i11;
                this.this$0 = windowInsetsNestedScrollConnection;
                this.$endVelocity = h0Var;
                this.$animationController = windowInsetsAnimationController;
                this.$targetShown = z;
            }

            @Override // r7.p
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke(((Number) obj).floatValue(), ((Number) obj2).floatValue());
                return t0.f22605a;
            }

            public final void invoke(float f10, float f11) {
                float f12 = this.$hidden;
                if (f10 <= this.$shown && f12 <= f10) {
                    this.this$0.adjustInsets(f10);
                    return;
                }
                this.$endVelocity.f19742i = f11;
                this.$animationController.finish(this.$targetShown);
                this.this$0.animationController = null;
                v1 v1Var = this.this$0.animationJob;
                if (v1Var != null) {
                    v1Var.cancel((CancellationException) new WindowInsetsAnimationCancelledException());
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(int i10, float f10, SplineBasedFloatDecayAnimationSpec splineBasedFloatDecayAnimationSpec, int i11, int i12, WindowInsetsNestedScrollConnection windowInsetsNestedScrollConnection, h0 h0Var, WindowInsetsAnimationController windowInsetsAnimationController, boolean z, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$current = i10;
            this.$flingAmount = f10;
            this.$spec = splineBasedFloatDecayAnimationSpec;
            this.$hidden = i11;
            this.$shown = i12;
            this.this$0 = windowInsetsNestedScrollConnection;
            this.$endVelocity = h0Var;
            this.$animationController = windowInsetsAnimationController;
            this.$targetShown = z;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new AnonymousClass1(this.$current, this.$flingAmount, this.$spec, this.$hidden, this.$shown, this.this$0, this.$endVelocity, this.$animationController, this.$targetShown, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                float f10 = this.$current;
                float f11 = this.$flingAmount;
                SplineBasedFloatDecayAnimationSpec splineBasedFloatDecayAnimationSpec = this.$spec;
                C00311 c00311 = new C00311(this.$hidden, this.$shown, this.this$0, this.$endVelocity, this.$animationController, this.$targetShown);
                this.label = 1;
                Object objAnimateDecay = SuspendAnimationKt.animateDecay(f10, f11, splineBasedFloatDecayAnimationSpec, c00311, this);
                e7.a aVar = e7.a.f15033i;
                if (objAnimateDecay == aVar) {
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
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WindowInsetsNestedScrollConnection$fling$2(WindowInsetsNestedScrollConnection windowInsetsNestedScrollConnection, int i10, float f10, SplineBasedFloatDecayAnimationSpec splineBasedFloatDecayAnimationSpec, int i11, int i12, h0 h0Var, WindowInsetsAnimationController windowInsetsAnimationController, boolean z, d7.d<? super WindowInsetsNestedScrollConnection$fling$2> dVar) {
        super(2, dVar);
        this.this$0 = windowInsetsNestedScrollConnection;
        this.$current = i10;
        this.$flingAmount = f10;
        this.$spec = splineBasedFloatDecayAnimationSpec;
        this.$hidden = i11;
        this.$shown = i12;
        this.$endVelocity = h0Var;
        this.$animationController = windowInsetsAnimationController;
        this.$targetShown = z;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        WindowInsetsNestedScrollConnection$fling$2 windowInsetsNestedScrollConnection$fling$2 = new WindowInsetsNestedScrollConnection$fling$2(this.this$0, this.$current, this.$flingAmount, this.$spec, this.$hidden, this.$shown, this.$endVelocity, this.$animationController, this.$targetShown, dVar);
        windowInsetsNestedScrollConnection$fling$2.L$0 = obj;
        return windowInsetsNestedScrollConnection$fling$2;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            k0 k0Var = (k0) this.L$0;
            WindowInsetsNestedScrollConnection windowInsetsNestedScrollConnection = this.this$0;
            windowInsetsNestedScrollConnection.animationJob = m0.p(k0Var, null, 0, new AnonymousClass1(this.$current, this.$flingAmount, this.$spec, this.$hidden, this.$shown, windowInsetsNestedScrollConnection, this.$endVelocity, this.$animationController, this.$targetShown, null), 3);
            v1 v1Var = this.this$0.animationJob;
            if (v1Var != null) {
                this.label = 1;
                Object objJoin = v1Var.join(this);
                e7.a aVar = e7.a.f15033i;
                if (objJoin == aVar) {
                    return aVar;
                }
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        this.this$0.animationJob = null;
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((WindowInsetsNestedScrollConnection$fling$2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
