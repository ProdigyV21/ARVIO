package androidx.compose.animation.core;

import androidx.compose.runtime.MonotonicFrameClockKt;
import androidx.compose.ui.MotionDurationScale;
import d7.j;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.i;
import kotlin.jvm.internal.k0;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\u001ar\u0010\r\u001a\u00020\u000b2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00002\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00000\u000426\u0010\f\u001a2\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0006H\u0086@¢\u0006\u0004\b\r\u0010\u000e\u001a`\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u000f26\u0010\f\u001a2\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0006H\u0086@¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u0098\u0001\u0010\r\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u0012\"\b\b\u0001\u0010\u0014*\u00020\u00132\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00152\u0006\u0010\u0001\u001a\u00028\u00002\u0006\u0010\u0002\u001a\u00028\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00018\u00002\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u000426\u0010\f\u001a2\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0006H\u0086@¢\u0006\u0004\b\r\u0010\u0017\u001ay\u0010\u001e\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u0012\"\b\b\u0001\u0010\u0014*\u00020\u0013*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00182\u0006\u0010\u0002\u001a\u00028\u00002\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\b\b\u0002\u0010\u001a\u001a\u00020\u00192%\b\u0002\u0010\f\u001a\u001f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001c\u0012\u0004\u0012\u00020\u000b0\u001b¢\u0006\u0002\b\u001dH\u0086@¢\u0006\u0004\b\u001e\u0010\u001f\u001ao\u0010\u0010\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u0012\"\b\b\u0001\u0010\u0014*\u00020\u0013*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00182\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000 2\b\b\u0002\u0010\u001a\u001a\u00020\u00192%\b\u0002\u0010\f\u001a\u001f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001c\u0012\u0004\u0012\u00020\u000b0\u001b¢\u0006\u0002\b\u001dH\u0086@¢\u0006\u0004\b\u0010\u0010!\u001au\u0010\r\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u0012\"\b\b\u0001\u0010\u0014*\u00020\u0013*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00182\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\"2\b\b\u0002\u0010%\u001a\u00020$2%\b\u0002\u0010\f\u001a\u001f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001c\u0012\u0004\u0012\u00020\u000b0\u001b¢\u0006\u0002\b\u001dH\u0080@¢\u0006\u0004\b\r\u0010&\u001aY\u0010*\u001a\u00028\u0000\"\u0004\b\u0000\u0010'\"\u0004\b\u0001\u0010\u0012\"\b\b\u0002\u0010\u0014*\u00020\u0013*\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\"2!\u0010)\u001a\u001d\u0012\u0013\u0012\u00110$¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b((\u0012\u0004\u0012\u00028\u00000\u001bH\u0082@¢\u0006\u0004\b*\u0010+\u001aC\u0010-\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u0012\"\b\b\u0001\u0010\u0014*\u00020\u0013*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001c2\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0018H\u0000¢\u0006\u0004\b-\u0010.\u001a\u008c\u0001\u00101\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u0012\"\b\b\u0001\u0010\u0014*\u00020\u0013*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001c2\u0006\u0010(\u001a\u00020$2\u0006\u0010/\u001a\u00020\u00002\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\"2\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00182#\u0010\f\u001a\u001f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001c\u0012\u0004\u0012\u00020\u000b0\u001b¢\u0006\u0002\b\u001dH\u0002¢\u0006\u0004\b1\u00102\u001a\u008c\u0001\u00104\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u0012\"\b\b\u0001\u0010\u0014*\u00020\u0013*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001c2\u0006\u0010(\u001a\u00020$2\u0006\u00103\u001a\u00020$2\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\"2\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00182#\u0010\f\u001a\u001f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001c\u0012\u0004\u0012\u00020\u000b0\u001b¢\u0006\u0002\b\u001dH\u0002¢\u0006\u0004\b4\u00105\"\u0018\u0010/\u001a\u00020\u0000*\u0002068@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b7\u00108¨\u00069"}, d2 = {"", "initialValue", "targetValue", "initialVelocity", "Landroidx/compose/animation/core/AnimationSpec;", "animationSpec", "Lkotlin/Function2;", "Lx6/y;", ContentDisposition.Parameters.Name, "value", "velocity", "Lx6/t0;", "block", "animate", "(FFFLandroidx/compose/animation/core/AnimationSpec;Lr7/p;Ld7/d;)Ljava/lang/Object;", "Landroidx/compose/animation/core/FloatDecayAnimationSpec;", "animateDecay", "(FFLandroidx/compose/animation/core/FloatDecayAnimationSpec;Lr7/p;Ld7/d;)Ljava/lang/Object;", "T", "Landroidx/compose/animation/core/AnimationVector;", "V", "Landroidx/compose/animation/core/TwoWayConverter;", "typeConverter", "(Landroidx/compose/animation/core/TwoWayConverter;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Landroidx/compose/animation/core/AnimationSpec;Lr7/p;Ld7/d;)Ljava/lang/Object;", "Landroidx/compose/animation/core/AnimationState;", "", "sequentialAnimation", "Lkotlin/Function1;", "Landroidx/compose/animation/core/AnimationScope;", "Lx6/n;", "animateTo", "(Landroidx/compose/animation/core/AnimationState;Ljava/lang/Object;Landroidx/compose/animation/core/AnimationSpec;ZLr7/l;Ld7/d;)Ljava/lang/Object;", "Landroidx/compose/animation/core/DecayAnimationSpec;", "(Landroidx/compose/animation/core/AnimationState;Landroidx/compose/animation/core/DecayAnimationSpec;ZLr7/l;Ld7/d;)Ljava/lang/Object;", "Landroidx/compose/animation/core/Animation;", "animation", "", "startTimeNanos", "(Landroidx/compose/animation/core/AnimationState;Landroidx/compose/animation/core/Animation;JLr7/l;Ld7/d;)Ljava/lang/Object;", "R", "frameTimeNanos", "onFrame", "callWithFrameNanos", "(Landroidx/compose/animation/core/Animation;Lr7/l;Ld7/d;)Ljava/lang/Object;", "state", "updateState", "(Landroidx/compose/animation/core/AnimationScope;Landroidx/compose/animation/core/AnimationState;)V", "durationScale", "anim", "doAnimationFrameWithScale", "(Landroidx/compose/animation/core/AnimationScope;JFLandroidx/compose/animation/core/Animation;Landroidx/compose/animation/core/AnimationState;Lr7/l;)V", "playTimeNanos", "doAnimationFrame", "(Landroidx/compose/animation/core/AnimationScope;JJLandroidx/compose/animation/core/Animation;Landroidx/compose/animation/core/AnimationState;Lr7/l;)V", "Ld7/j;", "getDurationScale", "(Ld7/j;)F", "animation-core_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SuspendAnimationKt {

    /* JADX INFO: Add missing generic type declarations: [T, V] */
    /* JADX INFO: renamed from: androidx.compose.animation.core.SuspendAnimationKt$animate$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"T", "Landroidx/compose/animation/core/AnimationVector;", "V", "Landroidx/compose/animation/core/AnimationScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/animation/core/AnimationScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass3<T, V> extends r implements l<AnimationScope<T, V>, t0> {
        final /* synthetic */ p<T, T, t0> $block;
        final /* synthetic */ TwoWayConverter<T, V> $typeConverter;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(p<? super T, ? super T, t0> pVar, TwoWayConverter<T, V> twoWayConverter) {
            super(1);
            this.$block = pVar;
            this.$typeConverter = twoWayConverter;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((AnimationScope) obj);
            return t0.f22605a;
        }

        public final void invoke(AnimationScope<T, V> animationScope) {
            this.$block.invoke(animationScope.getValue(), this.$typeConverter.getConvertFromVector().invoke(animationScope.getVelocityVector()));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.SuspendAnimationKt$animate$4, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.animation.core.SuspendAnimationKt", f = "SuspendAnimation.kt", l = {239, 278}, m = "animate")
    public static final class AnonymousClass4<T, V extends AnimationVector> extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass4(d7.d<? super AnonymousClass4> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SuspendAnimationKt.animate(null, null, 0L, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.SuspendAnimationKt$animate$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"T", "Landroidx/compose/animation/core/AnimationVector;", "V", "Landroidx/compose/animation/core/AnimationScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/animation/core/AnimationScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass5 extends r implements l {
        public static final AnonymousClass5 INSTANCE = new AnonymousClass5();

        public AnonymousClass5() {
            super(1);
        }

        public final void invoke(AnimationScope animationScope) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((AnimationScope) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.SuspendAnimationKt$animate$6, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\b\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0000\"\b\b\u0001\u0010\u0002*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"T", "Landroidx/compose/animation/core/AnimationVector;", "V", "", "it", "Lx6/t0;", "invoke", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass6 extends r implements l<Long, t0> {
        final /* synthetic */ Animation<T, V> $animation;
        final /* synthetic */ l<AnimationScope<T, V>, t0> $block;
        final /* synthetic */ float $durationScale;
        final /* synthetic */ T $initialValue;

        /* JADX INFO: Incorrect field signature: TV; */
        final /* synthetic */ AnimationVector $initialVelocityVector;
        final /* synthetic */ k0 $lateInitScope;
        final /* synthetic */ AnimationState<T, V> $this_animate;

        /* JADX INFO: renamed from: androidx.compose.animation.core.SuspendAnimationKt$animate$6$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "Landroidx/compose/animation/core/AnimationVector;", "V", "Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass1 extends r implements r7.a<t0> {
            final /* synthetic */ AnimationState<T, V> $this_animate;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(AnimationState<T, V> animationState) {
                super(0);
                this.$this_animate = animationState;
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m132invoke() {
                this.$this_animate.setRunning$animation_core_release(false);
            }

            @Override // r7.a
            public /* bridge */ /* synthetic */ Object invoke() {
                m132invoke();
                return t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Incorrect types in method signature: (Lkotlin/jvm/internal/k0;TT;Landroidx/compose/animation/core/Animation<TT;TV;>;TV;Landroidx/compose/animation/core/AnimationState<TT;TV;>;FLr7/l<-Landroidx/compose/animation/core/AnimationScope<TT;TV;>;Lx6/t0;>;)V */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass6(k0 k0Var, Object obj, Animation animation, AnimationVector animationVector, AnimationState animationState, float f10, l lVar) {
            super(1);
            this.$lateInitScope = k0Var;
            this.$initialValue = obj;
            this.$animation = animation;
            this.$initialVelocityVector = animationVector;
            this.$this_animate = animationState;
            this.$durationScale = f10;
            this.$block = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke(((Number) obj).longValue());
            return t0.f22605a;
        }

        public final void invoke(long j10) {
            k0 k0Var = this.$lateInitScope;
            AnimationScope animationScope = new AnimationScope(this.$initialValue, this.$animation.getTypeConverter(), this.$initialVelocityVector, j10, this.$animation.getTargetValue(), j10, true, new AnonymousClass1(this.$this_animate));
            SuspendAnimationKt.doAnimationFrameWithScale(animationScope, j10, this.$durationScale, this.$animation, this.$this_animate, this.$block);
            k0Var.f19746i = animationScope;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.SuspendAnimationKt$animate$7, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "Landroidx/compose/animation/core/AnimationVector;", "V", "Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass7 extends r implements r7.a<t0> {
        final /* synthetic */ AnimationState<T, V> $this_animate;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass7(AnimationState<T, V> animationState) {
            super(0);
            this.$this_animate = animationState;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m133invoke() {
            this.$this_animate.setRunning$animation_core_release(false);
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m133invoke();
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.SuspendAnimationKt$animate$9, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\b\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0000\"\b\b\u0001\u0010\u0002*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"T", "Landroidx/compose/animation/core/AnimationVector;", "V", "", "it", "Lx6/t0;", "invoke", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass9 extends r implements l<Long, t0> {
        final /* synthetic */ Animation<T, V> $animation;
        final /* synthetic */ l<AnimationScope<T, V>, t0> $block;
        final /* synthetic */ float $durationScale;
        final /* synthetic */ k0 $lateInitScope;
        final /* synthetic */ AnimationState<T, V> $this_animate;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass9(k0 k0Var, float f10, Animation<T, V> animation, AnimationState<T, V> animationState, l<? super AnimationScope<T, V>, t0> lVar) {
            super(1);
            this.$lateInitScope = k0Var;
            this.$durationScale = f10;
            this.$animation = animation;
            this.$this_animate = animationState;
            this.$block = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke(((Number) obj).longValue());
            return t0.f22605a;
        }

        public final void invoke(long j10) {
            SuspendAnimationKt.doAnimationFrameWithScale((AnimationScope) this.$lateInitScope.f19746i, j10, this.$durationScale, this.$animation, this.$this_animate, this.$block);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.SuspendAnimationKt$animateDecay$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/animation/core/AnimationScope;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "Lx6/t0;", "invoke", "(Landroidx/compose/animation/core/AnimationScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<AnimationScope<Float, AnimationVector1D>, t0> {
        final /* synthetic */ p<Float, Float, t0> $block;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(p<? super Float, ? super Float, t0> pVar) {
            super(1);
            this.$block = pVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((AnimationScope<Float, AnimationVector1D>) obj);
            return t0.f22605a;
        }

        public final void invoke(AnimationScope<Float, AnimationVector1D> animationScope) {
            this.$block.invoke(animationScope.getValue(), Float.valueOf(((AnimationVector1D) animationScope.getVelocityVector()).getValue()));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.SuspendAnimationKt$animateDecay$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"T", "Landroidx/compose/animation/core/AnimationVector;", "V", "Landroidx/compose/animation/core/AnimationScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/animation/core/AnimationScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C02734 extends r implements l {
        public static final C02734 INSTANCE = new C02734();

        public C02734() {
            super(1);
        }

        public final void invoke(AnimationScope animationScope) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((AnimationScope) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.SuspendAnimationKt$animateTo$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"T", "Landroidx/compose/animation/core/AnimationVector;", "V", "Landroidx/compose/animation/core/AnimationScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/animation/core/AnimationScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C02742 extends r implements l {
        public static final C02742 INSTANCE = new C02742();

        public C02742() {
            super(1);
        }

        public final void invoke(AnimationScope animationScope) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((AnimationScope) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: androidx.compose.animation.core.SuspendAnimationKt$callWithFrameNanos$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\u0002\"\b\b\u0002\u0010\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"<anonymous>", "R", "T", "V", "Landroidx/compose/animation/core/AnimationVector;", "it", "", "invoke", "(J)Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C02752<R> extends r implements l<Long, R> {
        final /* synthetic */ l<Long, R> $onFrame;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C02752(l<? super Long, ? extends R> lVar) {
            super(1);
            this.$onFrame = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).longValue());
        }

        public final R invoke(long j10) {
            return (R) this.$onFrame.invoke(Long.valueOf(j10));
        }
    }

    public static final Object animate(float f10, float f11, float f12, AnimationSpec<Float> animationSpec, p<? super Float, ? super Float, t0> pVar, d7.d<? super t0> dVar) {
        Object objAnimate = animate(VectorConvertersKt.getVectorConverter(i.f19743a), new Float(f10), new Float(f11), new Float(f12), animationSpec, pVar, dVar);
        return objAnimate == e7.a.f15033i ? objAnimate : t0.f22605a;
    }

    public static /* synthetic */ Object animate$default(float f10, float f11, float f12, AnimationSpec animationSpec, p pVar, d7.d dVar, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            f12 = 0.0f;
        }
        if ((i10 & 8) != 0) {
            animationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
        }
        return animate(f10, f11, f12, animationSpec, pVar, dVar);
    }

    public static final Object animateDecay(float f10, float f11, FloatDecayAnimationSpec floatDecayAnimationSpec, p<? super Float, ? super Float, t0> pVar, d7.d<? super t0> dVar) {
        Object objAnimate$default = animate$default(AnimationStateKt.AnimationState$default(f10, f11, 0L, 0L, false, 28, null), AnimationKt.DecayAnimation(floatDecayAnimationSpec, f10, f11), 0L, new AnonymousClass2(pVar), dVar, 2, null);
        return objAnimate$default == e7.a.f15033i ? objAnimate$default : t0.f22605a;
    }

    public static /* synthetic */ Object animateDecay$default(AnimationState animationState, DecayAnimationSpec decayAnimationSpec, boolean z, l lVar, d7.d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = false;
        }
        if ((i10 & 4) != 0) {
            lVar = C02734.INSTANCE;
        }
        return animateDecay(animationState, decayAnimationSpec, z, lVar, (d7.d<? super t0>) dVar);
    }

    public static final <T, V extends AnimationVector> Object animateTo(AnimationState<T, V> animationState, T t2, AnimationSpec<T> animationSpec, boolean z, l<? super AnimationScope<T, V>, t0> lVar, d7.d<? super t0> dVar) {
        Object objAnimate = animate(animationState, new TargetBasedAnimation(animationSpec, animationState.getTypeConverter(), animationState.getValue(), t2, animationState.getVelocityVector()), z ? animationState.getLastFrameTimeNanos() : Long.MIN_VALUE, lVar, dVar);
        return objAnimate == e7.a.f15033i ? objAnimate : t0.f22605a;
    }

    public static /* synthetic */ Object animateTo$default(AnimationState animationState, Object obj, AnimationSpec animationSpec, boolean z, l lVar, d7.d dVar, int i10, Object obj2) {
        if ((i10 & 2) != 0) {
            animationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
        }
        AnimationSpec animationSpec2 = animationSpec;
        if ((i10 & 4) != 0) {
            z = false;
        }
        boolean z5 = z;
        if ((i10 & 8) != 0) {
            lVar = C02742.INSTANCE;
        }
        return animateTo(animationState, obj, animationSpec2, z5, lVar, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <R, T, V extends AnimationVector> Object callWithFrameNanos(Animation<T, V> animation, l<? super Long, ? extends R> lVar, d7.d<? super R> dVar) {
        return animation.getIsInfinite() ? InfiniteAnimationPolicyKt.withInfiniteAnimationFrameNanos(lVar, dVar) : MonotonicFrameClockKt.withFrameNanos(new C02752(lVar), dVar);
    }

    private static final <T, V extends AnimationVector> void doAnimationFrame(AnimationScope<T, V> animationScope, long j10, long j11, Animation<T, V> animation, AnimationState<T, V> animationState, l<? super AnimationScope<T, V>, t0> lVar) {
        animationScope.setLastFrameTimeNanos$animation_core_release(j10);
        animationScope.setValue$animation_core_release(animation.getValueFromNanos(j11));
        animationScope.setVelocityVector$animation_core_release(animation.getVelocityVectorFromNanos(j11));
        if (animation.isFinishedFromNanos(j11)) {
            animationScope.setFinishedTimeNanos$animation_core_release(animationScope.getLastFrameTimeNanos());
            animationScope.setRunning$animation_core_release(false);
        }
        updateState(animationScope, animationState);
        lVar.invoke(animationScope);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T, V extends AnimationVector> void doAnimationFrameWithScale(AnimationScope<T, V> animationScope, long j10, float f10, Animation<T, V> animation, AnimationState<T, V> animationState, l<? super AnimationScope<T, V>, t0> lVar) {
        doAnimationFrame(animationScope, j10, f10 == 0.0f ? animation.getDurationNanos() : (long) ((j10 - animationScope.getStartTimeNanos()) / f10), animation, animationState, lVar);
    }

    public static final float getDurationScale(j jVar) {
        MotionDurationScale motionDurationScale = (MotionDurationScale) jVar.get(MotionDurationScale.INSTANCE);
        float scaleFactor = motionDurationScale != null ? motionDurationScale.getScaleFactor() : 1.0f;
        if (scaleFactor >= 0.0f) {
            return scaleFactor;
        }
        throw new IllegalStateException("negative scale factor");
    }

    public static final <T, V extends AnimationVector> void updateState(AnimationScope<T, V> animationScope, AnimationState<T, V> animationState) {
        animationState.setValue$animation_core_release(animationScope.getValue());
        AnimationVectorsKt.copyFrom(animationState.getVelocityVector(), animationScope.getVelocityVector());
        animationState.setFinishedTimeNanos$animation_core_release(animationScope.getFinishedTimeNanos());
        animationState.setLastFrameTimeNanos$animation_core_release(animationScope.getLastFrameTimeNanos());
        animationState.setRunning$animation_core_release(animationScope.isRunning());
    }

    public static /* synthetic */ Object animate$default(TwoWayConverter twoWayConverter, Object obj, Object obj2, Object obj3, AnimationSpec animationSpec, p pVar, d7.d dVar, int i10, Object obj4) {
        if ((i10 & 8) != 0) {
            obj3 = null;
        }
        if ((i10 & 16) != 0) {
            animationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
        }
        return animate(twoWayConverter, obj, obj2, obj3, animationSpec, pVar, dVar);
    }

    public static final <T, V extends AnimationVector> Object animateDecay(AnimationState<T, V> animationState, DecayAnimationSpec<T> decayAnimationSpec, boolean z, l<? super AnimationScope<T, V>, t0> lVar, d7.d<? super t0> dVar) {
        Object objAnimate = animate(animationState, new DecayAnimation((DecayAnimationSpec) decayAnimationSpec, (TwoWayConverter<T, AnimationVector>) animationState.getTypeConverter(), (Object) animationState.getValue(), animationState.getVelocityVector()), z ? animationState.getLastFrameTimeNanos() : Long.MIN_VALUE, lVar, dVar);
        return objAnimate == e7.a.f15033i ? objAnimate : t0.f22605a;
    }

    public static /* synthetic */ Object animate$default(AnimationState animationState, Animation animation, long j10, l lVar, d7.d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            j10 = Long.MIN_VALUE;
        }
        long j11 = j10;
        if ((i10 & 4) != 0) {
            lVar = AnonymousClass5.INSTANCE;
        }
        return animate(animationState, animation, j11, lVar, dVar);
    }

    public static final <T, V extends AnimationVector> Object animate(TwoWayConverter<T, V> twoWayConverter, T t2, T t10, T t11, AnimationSpec<T> animationSpec, p<? super T, ? super T, t0> pVar, d7.d<? super t0> dVar) {
        AnimationVector animationVectorNewInstance;
        if (t11 == null || (animationVectorNewInstance = (AnimationVector) twoWayConverter.getConvertToVector().invoke(t11)) == null) {
            animationVectorNewInstance = AnimationVectorsKt.newInstance((AnimationVector) twoWayConverter.getConvertToVector().invoke(t2));
        }
        AnimationVector animationVector = animationVectorNewInstance;
        Object objAnimate$default = animate$default(new AnimationState(twoWayConverter, t2, animationVector, 0L, 0L, false, 56, null), new TargetBasedAnimation(animationSpec, twoWayConverter, t2, t10, animationVector), 0L, new AnonymousClass3(pVar, twoWayConverter), dVar, 2, null);
        return objAnimate$default == e7.a.f15033i ? objAnimate$default : t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <T, V extends androidx.compose.animation.core.AnimationVector> java.lang.Object animate(androidx.compose.animation.core.AnimationState<T, V> r23, androidx.compose.animation.core.Animation<T, V> r24, long r25, r7.l<? super androidx.compose.animation.core.AnimationScope<T, V>, x6.t0> r27, d7.d<? super x6.t0> r28) {
        /*
            Method dump skipped, instruction units count: 323
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.core.SuspendAnimationKt.animate(androidx.compose.animation.core.AnimationState, androidx.compose.animation.core.Animation, long, r7.l, d7.d):java.lang.Object");
    }
}
