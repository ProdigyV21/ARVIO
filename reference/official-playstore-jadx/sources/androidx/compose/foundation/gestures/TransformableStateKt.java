package androidx.compose.foundation.gestures;

import androidx.compose.animation.core.AnimationScope;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.AnimationStateKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.foundation.MutatePriority;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Offset;
import androidx.media3.extractor.ts.TsExtractor;
import f7.j;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.h0;
import kotlin.jvm.internal.j0;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aZ\u0010\u000b\u001a\u00020\n2K\u0010\t\u001aG\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a\\\u0010\r\u001a\u00020\n2K\u0010\t\u001aG\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0000H\u0007¢\u0006\u0004\b\r\u0010\u000e\u001a,\u0010\u0012\u001a\u00020\b*\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00012\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u0010H\u0086@¢\u0006\u0004\b\u0012\u0010\u0013\u001a,\u0010\u0015\u001a\u00020\b*\u00020\n2\u0006\u0010\u0014\u001a\u00020\u00012\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u0010H\u0086@¢\u0006\u0004\b\u0015\u0010\u0013\u001a/\u0010\u0019\u001a\u00020\b*\u00020\n2\u0006\u0010\u0016\u001a\u00020\u00052\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0010H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a\u001c\u0010\u001a\u001a\u00020\b*\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0001H\u0086@¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u001c\u0010\u001c\u001a\u00020\b*\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0001H\u0086@¢\u0006\u0004\b\u001c\u0010\u001b\u001a\u001f\u0010\u001f\u001a\u00020\b*\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0005H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001e\u001a\u001e\u0010\"\u001a\u00020\b*\u00020\n2\b\b\u0002\u0010!\u001a\u00020 H\u0086@¢\u0006\u0004\b\"\u0010#\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006$"}, d2 = {"Lkotlin/Function3;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "zoomChange", "Landroidx/compose/ui/geometry/Offset;", "panChange", "rotationChange", "Lx6/t0;", "onTransformation", "Landroidx/compose/foundation/gestures/TransformableState;", "TransformableState", "(Lr7/q;)Landroidx/compose/foundation/gestures/TransformableState;", "rememberTransformableState", "(Lr7/q;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/gestures/TransformableState;", "zoomFactor", "Landroidx/compose/animation/core/AnimationSpec;", "animationSpec", "animateZoomBy", "(Landroidx/compose/foundation/gestures/TransformableState;FLandroidx/compose/animation/core/AnimationSpec;Ld7/d;)Ljava/lang/Object;", "degrees", "animateRotateBy", "offset", "animatePanBy-ubNVwUQ", "(Landroidx/compose/foundation/gestures/TransformableState;JLandroidx/compose/animation/core/AnimationSpec;Ld7/d;)Ljava/lang/Object;", "animatePanBy", "zoomBy", "(Landroidx/compose/foundation/gestures/TransformableState;FLd7/d;)Ljava/lang/Object;", "rotateBy", "panBy-d-4ec7I", "(Landroidx/compose/foundation/gestures/TransformableState;JLd7/d;)Ljava/lang/Object;", "panBy", "Landroidx/compose/foundation/MutatePriority;", "terminationPriority", "stopTransformation", "(Landroidx/compose/foundation/gestures/TransformableState;Landroidx/compose/foundation/MutatePriority;Ld7/d;)Ljava/lang/Object;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TransformableStateKt {

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TransformableStateKt$animateRotateBy$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/gestures/TransformScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/foundation/gestures/TransformScope;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.foundation.gestures.TransformableStateKt$animateRotateBy$2", f = "TransformableState.kt", l = {158}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<TransformScope, d7.d<? super t0>, Object> {
        final /* synthetic */ AnimationSpec<Float> $animationSpec;
        final /* synthetic */ float $degrees;
        final /* synthetic */ h0 $previous;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TransformableStateKt$animateRotateBy$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/animation/core/AnimationScope;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "Lx6/t0;", "invoke", "(Landroidx/compose/animation/core/AnimationScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass1 extends r implements l<AnimationScope<Float, AnimationVector1D>, t0> {
            final /* synthetic */ TransformScope $$this$transform;
            final /* synthetic */ h0 $previous;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(h0 h0Var, TransformScope transformScope) {
                super(1);
                this.$previous = h0Var;
                this.$$this$transform = transformScope;
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((AnimationScope<Float, AnimationVector1D>) obj);
                return t0.f22605a;
            }

            public final void invoke(AnimationScope<Float, AnimationVector1D> animationScope) {
                g.a(this.$$this$transform, 0.0f, 0L, animationScope.getValue().floatValue() - this.$previous.f19742i, 3, null);
                this.$previous.f19742i = animationScope.getValue().floatValue();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(h0 h0Var, float f10, AnimationSpec<Float> animationSpec, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$previous = h0Var;
            this.$degrees = f10;
            this.$animationSpec = animationSpec;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$previous, this.$degrees, this.$animationSpec, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // r7.p
        public final Object invoke(TransformScope transformScope, d7.d<? super t0> dVar) {
            return ((AnonymousClass2) create(transformScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                TransformScope transformScope = (TransformScope) this.L$0;
                AnimationState animationStateAnimationState$default = AnimationStateKt.AnimationState$default(this.$previous.f19742i, 0.0f, 0L, 0L, false, 30, null);
                Float f10 = new Float(this.$degrees);
                AnimationSpec<Float> animationSpec = this.$animationSpec;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$previous, transformScope);
                this.label = 1;
                Object objAnimateTo$default = SuspendAnimationKt.animateTo$default(animationStateAnimationState$default, f10, animationSpec, false, anonymousClass1, this, 4, null);
                e7.a aVar = e7.a.f15033i;
                if (objAnimateTo$default == aVar) {
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
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TransformableStateKt$animateZoomBy$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/gestures/TransformScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/foundation/gestures/TransformScope;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.foundation.gestures.TransformableStateKt$animateZoomBy$3", f = "TransformableState.kt", l = {TsExtractor.TS_STREAM_TYPE_DTS}, m = "invokeSuspend")
    public static final class AnonymousClass3 extends j implements p<TransformScope, d7.d<? super t0>, Object> {
        final /* synthetic */ AnimationSpec<Float> $animationSpec;
        final /* synthetic */ h0 $previous;
        final /* synthetic */ float $zoomFactor;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TransformableStateKt$animateZoomBy$3$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/animation/core/AnimationScope;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "Lx6/t0;", "invoke", "(Landroidx/compose/animation/core/AnimationScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass1 extends r implements l<AnimationScope<Float, AnimationVector1D>, t0> {
            final /* synthetic */ TransformScope $$this$transform;
            final /* synthetic */ h0 $previous;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(h0 h0Var, TransformScope transformScope) {
                super(1);
                this.$previous = h0Var;
                this.$$this$transform = transformScope;
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((AnimationScope<Float, AnimationVector1D>) obj);
                return t0.f22605a;
            }

            public final void invoke(AnimationScope<Float, AnimationVector1D> animationScope) {
                g.a(this.$$this$transform, this.$previous.f19742i == 0.0f ? 1.0f : animationScope.getValue().floatValue() / this.$previous.f19742i, 0L, 0.0f, 6, null);
                this.$previous.f19742i = animationScope.getValue().floatValue();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(h0 h0Var, float f10, AnimationSpec<Float> animationSpec, d7.d<? super AnonymousClass3> dVar) {
            super(2, dVar);
            this.$previous = h0Var;
            this.$zoomFactor = f10;
            this.$animationSpec = animationSpec;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$previous, this.$zoomFactor, this.$animationSpec, dVar);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        @Override // r7.p
        public final Object invoke(TransformScope transformScope, d7.d<? super t0> dVar) {
            return ((AnonymousClass3) create(transformScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                TransformScope transformScope = (TransformScope) this.L$0;
                AnimationState animationStateAnimationState$default = AnimationStateKt.AnimationState$default(this.$previous.f19742i, 0.0f, 0L, 0L, false, 30, null);
                Float f10 = new Float(this.$zoomFactor);
                AnimationSpec<Float> animationSpec = this.$animationSpec;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$previous, transformScope);
                this.label = 1;
                Object objAnimateTo$default = SuspendAnimationKt.animateTo$default(animationStateAnimationState$default, f10, animationSpec, false, anonymousClass1, this, 4, null);
                e7.a aVar = e7.a.f15033i;
                if (objAnimateTo$default == aVar) {
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
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TransformableStateKt$rotateBy$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/gestures/TransformScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/foundation/gestures/TransformScope;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.foundation.gestures.TransformableStateKt$rotateBy$2", f = "TransformableState.kt", l = {}, m = "invokeSuspend")
    public static final class C03282 extends j implements p<TransformScope, d7.d<? super t0>, Object> {
        final /* synthetic */ float $degrees;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C03282(float f10, d7.d<? super C03282> dVar) {
            super(2, dVar);
            this.$degrees = f10;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C03282 c03282 = new C03282(this.$degrees, dVar);
            c03282.L$0 = obj;
            return c03282;
        }

        @Override // r7.p
        public final Object invoke(TransformScope transformScope, d7.d<? super t0> dVar) {
            return ((C03282) create(transformScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            ((TransformScope) this.L$0).mo302transformByd4ec7I(1.0f, Offset.INSTANCE.m3252getZeroF1C5BW0(), this.$degrees);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TransformableStateKt$stopTransformation$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/gestures/TransformScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/foundation/gestures/TransformScope;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.foundation.gestures.TransformableStateKt$stopTransformation$2", f = "TransformableState.kt", l = {}, m = "invokeSuspend")
    public static final class C03292 extends j implements p<TransformScope, d7.d<? super t0>, Object> {
        int label;

        public C03292(d7.d<? super C03292> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new C03292(dVar);
        }

        @Override // r7.p
        public final Object invoke(TransformScope transformScope, d7.d<? super t0> dVar) {
            return ((C03292) create(transformScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TransformableStateKt$zoomBy$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/gestures/TransformScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/foundation/gestures/TransformScope;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.foundation.gestures.TransformableStateKt$zoomBy$2", f = "TransformableState.kt", l = {}, m = "invokeSuspend")
    public static final class C03302 extends j implements p<TransformScope, d7.d<? super t0>, Object> {
        final /* synthetic */ float $zoomFactor;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C03302(float f10, d7.d<? super C03302> dVar) {
            super(2, dVar);
            this.$zoomFactor = f10;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C03302 c03302 = new C03302(this.$zoomFactor, dVar);
            c03302.L$0 = obj;
            return c03302;
        }

        @Override // r7.p
        public final Object invoke(TransformScope transformScope, d7.d<? super t0> dVar) {
            return ((C03302) create(transformScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            ((TransformScope) this.L$0).mo302transformByd4ec7I(this.$zoomFactor, Offset.INSTANCE.m3252getZeroF1C5BW0(), 0.0f);
            return t0.f22605a;
        }
    }

    public static final TransformableState TransformableState(q<? super Float, ? super Offset, ? super Float, t0> qVar) {
        return new DefaultTransformableState(qVar);
    }

    /* JADX INFO: renamed from: animatePanBy-ubNVwUQ, reason: not valid java name */
    public static final Object m393animatePanByubNVwUQ(TransformableState transformableState, long j10, AnimationSpec<Offset> animationSpec, d7.d<? super t0> dVar) {
        j0 j0Var = new j0();
        j0Var.f19745i = Offset.INSTANCE.m3252getZeroF1C5BW0();
        Object objA = h.a(transformableState, null, new TransformableStateKt$animatePanBy$2(j0Var, j10, animationSpec, null), dVar, 1, null);
        return objA == e7.a.f15033i ? objA : t0.f22605a;
    }

    /* JADX INFO: renamed from: animatePanBy-ubNVwUQ$default, reason: not valid java name */
    public static /* synthetic */ Object m394animatePanByubNVwUQ$default(TransformableState transformableState, long j10, AnimationSpec animationSpec, d7.d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            animationSpec = new SpringSpec(0.0f, 200.0f, null, 5, null);
        }
        return m393animatePanByubNVwUQ(transformableState, j10, animationSpec, dVar);
    }

    public static final Object animateRotateBy(TransformableState transformableState, float f10, AnimationSpec<Float> animationSpec, d7.d<? super t0> dVar) {
        Object objA = h.a(transformableState, null, new AnonymousClass2(new h0(), f10, animationSpec, null), dVar, 1, null);
        return objA == e7.a.f15033i ? objA : t0.f22605a;
    }

    public static /* synthetic */ Object animateRotateBy$default(TransformableState transformableState, float f10, AnimationSpec animationSpec, d7.d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            animationSpec = new SpringSpec(0.0f, 200.0f, null, 5, null);
        }
        return animateRotateBy(transformableState, f10, animationSpec, dVar);
    }

    public static final Object animateZoomBy(TransformableState transformableState, float f10, AnimationSpec<Float> animationSpec, d7.d<? super t0> dVar) {
        if (f10 <= 0.0f) {
            throw new IllegalArgumentException("zoom value should be greater than 0");
        }
        h0 h0Var = new h0();
        h0Var.f19742i = 1.0f;
        Object objA = h.a(transformableState, null, new AnonymousClass3(h0Var, f10, animationSpec, null), dVar, 1, null);
        return objA == e7.a.f15033i ? objA : t0.f22605a;
    }

    public static /* synthetic */ Object animateZoomBy$default(TransformableState transformableState, float f10, AnimationSpec animationSpec, d7.d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            animationSpec = new SpringSpec(0.0f, 200.0f, null, 5, null);
        }
        return animateZoomBy(transformableState, f10, animationSpec, dVar);
    }

    /* JADX INFO: renamed from: panBy-d-4ec7I, reason: not valid java name */
    public static final Object m395panByd4ec7I(TransformableState transformableState, long j10, d7.d<? super t0> dVar) {
        Object objA = h.a(transformableState, null, new TransformableStateKt$panBy$2(j10, null), dVar, 1, null);
        return objA == e7.a.f15033i ? objA : t0.f22605a;
    }

    public static final TransformableState rememberTransformableState(q<? super Float, ? super Offset, ? super Float, t0> qVar, Composer composer, int i10) {
        composer.startReplaceableGroup(1681419281);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1681419281, i10, -1, "androidx.compose.foundation.gestures.rememberTransformableState (TransformableState.kt:116)");
        }
        State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(qVar, composer, i10 & 14);
        composer.startReplaceableGroup(-492369756);
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = TransformableState(new TransformableStateKt$rememberTransformableState$1$1(stateRememberUpdatedState));
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        TransformableState transformableState = (TransformableState) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return transformableState;
    }

    public static final Object rotateBy(TransformableState transformableState, float f10, d7.d<? super t0> dVar) {
        Object objA = h.a(transformableState, null, new C03282(f10, null), dVar, 1, null);
        return objA == e7.a.f15033i ? objA : t0.f22605a;
    }

    public static final Object stopTransformation(TransformableState transformableState, MutatePriority mutatePriority, d7.d<? super t0> dVar) {
        Object objTransform = transformableState.transform(mutatePriority, new C03292(null), dVar);
        return objTransform == e7.a.f15033i ? objTransform : t0.f22605a;
    }

    public static /* synthetic */ Object stopTransformation$default(TransformableState transformableState, MutatePriority mutatePriority, d7.d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            mutatePriority = MutatePriority.Default;
        }
        return stopTransformation(transformableState, mutatePriority, dVar);
    }

    public static final Object zoomBy(TransformableState transformableState, float f10, d7.d<? super t0> dVar) {
        Object objA = h.a(transformableState, null, new C03302(f10, null), dVar, 1, null);
        return objA == e7.a.f15033i ? objA : t0.f22605a;
    }
}
