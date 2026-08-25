package androidx.compose.animation.core;

import androidx.compose.animation.core.AnimationVector;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import com.google.android.gms.cast.MediaError;
import f7.j;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.internal.f0;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0004B9\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00018\u0000\u0012\b\b\u0002\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fB1\b\u0017\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u000b\u0010\rJ%\u0010\u0011\u001a\u00020\u00102\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00018\u00002\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u0011\u0010\u0012Jg\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001a2\u0006\u0010\u0013\u001a\u00028\u00002\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u00142\b\b\u0002\u0010\u0016\u001a\u00028\u00002'\b\u0002\u0010\u0019\u001a!\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0017¢\u0006\u0002\b\u0018H\u0086@¢\u0006\u0004\b\u001b\u0010\u001cJ[\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001a2\u0006\u0010\u0016\u001a\u00028\u00002\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u001d2'\b\u0002\u0010\u0019\u001a!\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0017¢\u0006\u0002\b\u0018H\u0086@¢\u0006\u0004\b\u001e\u0010\u001fJ\u0018\u0010 \u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00028\u0000H\u0086@¢\u0006\u0004\b \u0010!J\u0010\u0010\"\u001a\u00020\u0010H\u0086@¢\u0006\u0004\b\"\u0010#J\u0013\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00000$¢\u0006\u0004\b%\u0010&J_\u0010)\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001a2\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010'2\u0006\u0010\u0016\u001a\u00028\u00002%\u0010\u0019\u001a!\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0017¢\u0006\u0002\b\u0018H\u0082@¢\u0006\u0004\b)\u0010*J\u0017\u0010,\u001a\u00028\u00002\u0006\u0010+\u001a\u00028\u0000H\u0002¢\u0006\u0004\b,\u0010-J\u000f\u0010.\u001a\u00020\u0010H\u0002¢\u0006\u0004\b.\u0010/R#\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u00100\u001a\u0004\b1\u00102R\u0016\u0010\b\u001a\u0004\u0018\u00018\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u00103R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u00104\u001a\u0004\b5\u00106R&\u00108\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001078\u0000X\u0080\u0004¢\u0006\f\n\u0004\b8\u00109\u001a\u0004\b:\u0010;R+\u0010@\u001a\u00020<2\u0006\u0010=\u001a\u00020<8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b>\u0010?\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR+\u0010\u0013\u001a\u00028\u00002\u0006\u0010=\u001a\u00028\u00008F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bD\u0010?\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR(\u0010\u000e\u001a\u0004\u0018\u00018\u00002\b\u0010=\u001a\u0004\u0018\u00018\u00008\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u000e\u00103\u001a\u0004\bI\u0010FR(\u0010\u000f\u001a\u0004\u0018\u00018\u00002\b\u0010=\u001a\u0004\u0018\u00018\u00008\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u000f\u00103\u001a\u0004\bJ\u0010FR\u0014\u0010L\u001a\u00020K8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bL\u0010MR \u0010O\u001a\b\u0012\u0004\u0012\u00028\u00000N8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bO\u0010P\u001a\u0004\bQ\u0010RR\u001a\u0010S\u001a\u00028\u00018\u0002X\u0082\u0004¢\u0006\f\n\u0004\bS\u0010T\u0012\u0004\bU\u0010/R\u001a\u0010V\u001a\u00028\u00018\u0002X\u0082\u0004¢\u0006\f\n\u0004\bV\u0010T\u0012\u0004\bW\u0010/R\u0016\u0010X\u001a\u00028\u00018\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bX\u0010TR\u0016\u0010Y\u001a\u00028\u00018\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bY\u0010TR\u0011\u0010+\u001a\u00028\u00008F¢\u0006\u0006\u001a\u0004\bZ\u0010FR\u0011\u0010]\u001a\u00028\u00018F¢\u0006\u0006\u001a\u0004\b[\u0010\\R\u0011\u0010_\u001a\u00028\u00008F¢\u0006\u0006\u001a\u0004\b^\u0010F¨\u0006`"}, d2 = {"Landroidx/compose/animation/core/Animatable;", "T", "Landroidx/compose/animation/core/AnimationVector;", "V", "", "initialValue", "Landroidx/compose/animation/core/TwoWayConverter;", "typeConverter", "visibilityThreshold", "", "label", "<init>", "(Ljava/lang/Object;Landroidx/compose/animation/core/TwoWayConverter;Ljava/lang/Object;Ljava/lang/String;)V", "(Ljava/lang/Object;Landroidx/compose/animation/core/TwoWayConverter;Ljava/lang/Object;)V", "lowerBound", "upperBound", "Lx6/t0;", "updateBounds", "(Ljava/lang/Object;Ljava/lang/Object;)V", "targetValue", "Landroidx/compose/animation/core/AnimationSpec;", "animationSpec", "initialVelocity", "Lkotlin/Function1;", "Lx6/n;", "block", "Landroidx/compose/animation/core/AnimationResult;", "animateTo", "(Ljava/lang/Object;Landroidx/compose/animation/core/AnimationSpec;Ljava/lang/Object;Lr7/l;Ld7/d;)Ljava/lang/Object;", "Landroidx/compose/animation/core/DecayAnimationSpec;", "animateDecay", "(Ljava/lang/Object;Landroidx/compose/animation/core/DecayAnimationSpec;Lr7/l;Ld7/d;)Ljava/lang/Object;", "snapTo", "(Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "stop", "(Ld7/d;)Ljava/lang/Object;", "Landroidx/compose/runtime/State;", "asState", "()Landroidx/compose/runtime/State;", "Landroidx/compose/animation/core/Animation;", "animation", "runAnimation", "(Landroidx/compose/animation/core/Animation;Ljava/lang/Object;Lr7/l;Ld7/d;)Ljava/lang/Object;", "value", "clampToBounds", "(Ljava/lang/Object;)Ljava/lang/Object;", "endAnimation", "()V", "Landroidx/compose/animation/core/TwoWayConverter;", "getTypeConverter", "()Landroidx/compose/animation/core/TwoWayConverter;", "Ljava/lang/Object;", "Ljava/lang/String;", "getLabel", "()Ljava/lang/String;", "Landroidx/compose/animation/core/AnimationState;", "internalState", "Landroidx/compose/animation/core/AnimationState;", "getInternalState$animation_core_release", "()Landroidx/compose/animation/core/AnimationState;", "", "<set-?>", "isRunning$delegate", "Landroidx/compose/runtime/MutableState;", "isRunning", "()Z", "setRunning", "(Z)V", "targetValue$delegate", "getTargetValue", "()Ljava/lang/Object;", "setTargetValue", "(Ljava/lang/Object;)V", "getLowerBound", "getUpperBound", "Landroidx/compose/animation/core/MutatorMutex;", "mutatorMutex", "Landroidx/compose/animation/core/MutatorMutex;", "Landroidx/compose/animation/core/SpringSpec;", "defaultSpringSpec", "Landroidx/compose/animation/core/SpringSpec;", "getDefaultSpringSpec$animation_core_release", "()Landroidx/compose/animation/core/SpringSpec;", "negativeInfinityBounds", "Landroidx/compose/animation/core/AnimationVector;", "getNegativeInfinityBounds$annotations", "positiveInfinityBounds", "getPositiveInfinityBounds$annotations", "lowerBoundVector", "upperBoundVector", "getValue", "getVelocityVector", "()Landroidx/compose/animation/core/AnimationVector;", "velocityVector", "getVelocity", "velocity", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Animatable<T, V extends AnimationVector> {
    public static final int $stable = 8;
    private final SpringSpec<T> defaultSpringSpec;
    private final AnimationState<T, V> internalState;

    /* JADX INFO: renamed from: isRunning$delegate, reason: from kotlin metadata */
    private final MutableState isRunning;
    private final String label;
    private T lowerBound;
    private V lowerBoundVector;
    private final MutatorMutex mutatorMutex;
    private final V negativeInfinityBounds;
    private final V positiveInfinityBounds;

    /* JADX INFO: renamed from: targetValue$delegate, reason: from kotlin metadata */
    private final MutableState targetValue;
    private final TwoWayConverter<T, V> typeConverter;
    private T upperBound;
    private V upperBoundVector;
    private final T visibilityThreshold;

    /* JADX INFO: renamed from: androidx.compose.animation.core.Animatable$runAnimation$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\u0003*\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "Landroidx/compose/animation/core/AnimationResult;", "T", "V", "Landroidx/compose/animation/core/AnimationVector;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.animation.core.Animatable$runAnimation$2", f = "Animatable.kt", l = {MediaError.DetailedErrorCode.HLS_NETWORK_PLAYLIST}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements l<d7.d<? super AnimationResult<T, V>>, Object> {
        final /* synthetic */ Animation<T, V> $animation;
        final /* synthetic */ l<Animatable<T, V>, t0> $block;
        final /* synthetic */ T $initialVelocity;
        final /* synthetic */ long $startTime;
        Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ Animatable<T, V> this$0;

        /* JADX INFO: renamed from: androidx.compose.animation.core.Animatable$runAnimation$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"T", "Landroidx/compose/animation/core/AnimationVector;", "V", "Landroidx/compose/animation/core/AnimationScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/animation/core/AnimationScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass1 extends r implements l<AnimationScope<T, V>, t0> {
            final /* synthetic */ l<Animatable<T, V>, t0> $block;
            final /* synthetic */ f0 $clampingNeeded;
            final /* synthetic */ AnimationState<T, V> $endState;
            final /* synthetic */ Animatable<T, V> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass1(Animatable<T, V> animatable, AnimationState<T, V> animationState, l<? super Animatable<T, V>, t0> lVar, f0 f0Var) {
                super(1);
                this.this$0 = animatable;
                this.$endState = animationState;
                this.$block = lVar;
                this.$clampingNeeded = f0Var;
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((AnimationScope) obj);
                return t0.f22605a;
            }

            /* JADX WARN: Multi-variable type inference failed */
            public final void invoke(AnimationScope<T, V> animationScope) {
                SuspendAnimationKt.updateState(animationScope, this.this$0.getInternalState$animation_core_release());
                Object objClampToBounds = this.this$0.clampToBounds(animationScope.getValue());
                if (p.a(objClampToBounds, animationScope.getValue())) {
                    l<Animatable<T, V>, t0> lVar = this.$block;
                    if (lVar != null) {
                        lVar.invoke(this.this$0);
                        return;
                    }
                    return;
                }
                this.this$0.getInternalState$animation_core_release().setValue$animation_core_release(objClampToBounds);
                this.$endState.setValue$animation_core_release((T) objClampToBounds);
                l<Animatable<T, V>, t0> lVar2 = this.$block;
                if (lVar2 != null) {
                    lVar2.invoke(this.this$0);
                }
                animationScope.cancelAnimation();
                this.$clampingNeeded.f19738i = true;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(Animatable<T, V> animatable, T t2, Animation<T, V> animation, long j10, l<? super Animatable<T, V>, t0> lVar, d7.d<? super AnonymousClass2> dVar) {
            super(1, dVar);
            this.this$0 = animatable;
            this.$initialVelocity = t2;
            this.$animation = animation;
            this.$startTime = j10;
            this.$block = lVar;
        }

        @Override // f7.a
        public final d7.d<t0> create(d7.d<?> dVar) {
            return new AnonymousClass2(this.this$0, this.$initialVelocity, this.$animation, this.$startTime, this.$block, dVar);
        }

        @Override // r7.l
        public final Object invoke(d7.d<? super AnimationResult<T, V>> dVar) {
            return ((AnonymousClass2) create(dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            AnimationState animationState;
            f0 f0Var;
            int i10 = this.label;
            try {
                if (i10 == 0) {
                    k2.c.G(obj);
                    this.this$0.getInternalState$animation_core_release().setVelocityVector$animation_core_release((AnimationVector) this.this$0.getTypeConverter().getConvertToVector().invoke(this.$initialVelocity));
                    this.this$0.setTargetValue(this.$animation.getTargetValue());
                    this.this$0.setRunning(true);
                    AnimationState animationStateCopy$default = AnimationStateKt.copy$default((AnimationState) this.this$0.getInternalState$animation_core_release(), (Object) null, (AnimationVector) null, 0L, Long.MIN_VALUE, false, 23, (Object) null);
                    f0 f0Var2 = new f0();
                    Animation<T, V> animation = this.$animation;
                    long j10 = this.$startTime;
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, animationStateCopy$default, this.$block, f0Var2);
                    this.L$0 = animationStateCopy$default;
                    this.L$1 = f0Var2;
                    this.label = 1;
                    Object objAnimate = SuspendAnimationKt.animate(animationStateCopy$default, animation, j10, anonymousClass1, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objAnimate == aVar) {
                        return aVar;
                    }
                    animationState = animationStateCopy$default;
                    f0Var = f0Var2;
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    f0Var = (f0) this.L$1;
                    animationState = (AnimationState) this.L$0;
                    k2.c.G(obj);
                }
                AnimationEndReason animationEndReason = f0Var.f19738i ? AnimationEndReason.BoundReached : AnimationEndReason.Finished;
                this.this$0.endAnimation();
                return new AnimationResult(animationState, animationEndReason);
            } catch (CancellationException e5) {
                this.this$0.endAnimation();
                throw e5;
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.Animatable$snapTo$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "Landroidx/compose/animation/core/AnimationVector;", "V", "Lx6/t0;", "<anonymous>", "()V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.animation.core.Animatable$snapTo$2", f = "Animatable.kt", l = {}, m = "invokeSuspend")
    public static final class C02662 extends j implements l<d7.d<? super t0>, Object> {
        final /* synthetic */ T $targetValue;
        int label;
        final /* synthetic */ Animatable<T, V> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C02662(Animatable<T, V> animatable, T t2, d7.d<? super C02662> dVar) {
            super(1, dVar);
            this.this$0 = animatable;
            this.$targetValue = t2;
        }

        @Override // f7.a
        public final d7.d<t0> create(d7.d<?> dVar) {
            return new C02662(this.this$0, this.$targetValue, dVar);
        }

        @Override // r7.l
        public final Object invoke(d7.d<? super t0> dVar) {
            return ((C02662) create(dVar)).invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            this.this$0.endAnimation();
            Object objClampToBounds = this.this$0.clampToBounds(this.$targetValue);
            this.this$0.getInternalState$animation_core_release().setValue$animation_core_release(objClampToBounds);
            this.this$0.setTargetValue(objClampToBounds);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.Animatable$stop$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "Landroidx/compose/animation/core/AnimationVector;", "V", "Lx6/t0;", "<anonymous>", "()V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.animation.core.Animatable$stop$2", f = "Animatable.kt", l = {}, m = "invokeSuspend")
    public static final class C02672 extends j implements l<d7.d<? super t0>, Object> {
        int label;
        final /* synthetic */ Animatable<T, V> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C02672(Animatable<T, V> animatable, d7.d<? super C02672> dVar) {
            super(1, dVar);
            this.this$0 = animatable;
        }

        @Override // f7.a
        public final d7.d<t0> create(d7.d<?> dVar) {
            return new C02672(this.this$0, dVar);
        }

        @Override // r7.l
        public final Object invoke(d7.d<? super t0> dVar) {
            return ((C02672) create(dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            this.this$0.endAnimation();
            return t0.f22605a;
        }
    }

    public Animatable(T t2, TwoWayConverter<T, V> twoWayConverter, T t10, String str) {
        this.typeConverter = twoWayConverter;
        this.visibilityThreshold = t10;
        this.label = str;
        this.internalState = new AnimationState<>(twoWayConverter, t2, null, 0L, 0L, false, 60, null);
        this.isRunning = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
        this.targetValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(t2, null, 2, null);
        this.mutatorMutex = new MutatorMutex();
        this.defaultSpringSpec = new SpringSpec<>(0.0f, 0.0f, t10, 3, null);
        AnimationVector velocityVector = getVelocityVector();
        V v2 = velocityVector instanceof AnimationVector1D ? AnimatableKt.negativeInfinityBounds1D : velocityVector instanceof AnimationVector2D ? AnimatableKt.negativeInfinityBounds2D : velocityVector instanceof AnimationVector3D ? AnimatableKt.negativeInfinityBounds3D : AnimatableKt.negativeInfinityBounds4D;
        this.negativeInfinityBounds = v2;
        AnimationVector velocityVector2 = getVelocityVector();
        V v10 = velocityVector2 instanceof AnimationVector1D ? AnimatableKt.positiveInfinityBounds1D : velocityVector2 instanceof AnimationVector2D ? AnimatableKt.positiveInfinityBounds2D : velocityVector2 instanceof AnimationVector3D ? AnimatableKt.positiveInfinityBounds3D : AnimatableKt.positiveInfinityBounds4D;
        this.positiveInfinityBounds = v10;
        this.lowerBoundVector = v2;
        this.upperBoundVector = v10;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object animateDecay$default(Animatable animatable, Object obj, DecayAnimationSpec decayAnimationSpec, l lVar, d7.d dVar, int i10, Object obj2) {
        if ((i10 & 4) != 0) {
            lVar = null;
        }
        return animatable.animateDecay(obj, decayAnimationSpec, lVar, dVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object animateTo$default(Animatable animatable, Object obj, AnimationSpec animationSpec, Object obj2, l lVar, d7.d dVar, int i10, Object obj3) {
        if ((i10 & 2) != 0) {
            animationSpec = animatable.defaultSpringSpec;
        }
        AnimationSpec animationSpec2 = animationSpec;
        if ((i10 & 4) != 0) {
            obj2 = animatable.getVelocity();
        }
        Object obj4 = obj2;
        if ((i10 & 8) != 0) {
            lVar = null;
        }
        return animatable.animateTo(obj, animationSpec2, obj4, lVar, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final T clampToBounds(T value) {
        if (p.a(this.lowerBoundVector, this.negativeInfinityBounds) && p.a(this.upperBoundVector, this.positiveInfinityBounds)) {
            return value;
        }
        AnimationVector animationVector = (AnimationVector) this.typeConverter.getConvertToVector().invoke(value);
        int size = animationVector.getSize();
        boolean z = false;
        for (int i10 = 0; i10 < size; i10++) {
            if (animationVector.get$animation_core_release(i10) < this.lowerBoundVector.get$animation_core_release(i10) || animationVector.get$animation_core_release(i10) > this.upperBoundVector.get$animation_core_release(i10)) {
                animationVector.set$animation_core_release(i10, qb.d.m(animationVector.get$animation_core_release(i10), this.lowerBoundVector.get$animation_core_release(i10), this.upperBoundVector.get$animation_core_release(i10)));
                z = true;
            }
        }
        return z ? (T) this.typeConverter.getConvertFromVector().invoke(animationVector) : value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void endAnimation() {
        AnimationState<T, V> animationState = this.internalState;
        animationState.getVelocityVector().reset$animation_core_release();
        animationState.setLastFrameTimeNanos$animation_core_release(Long.MIN_VALUE);
        setRunning(false);
    }

    private static /* synthetic */ void getNegativeInfinityBounds$annotations() {
    }

    private static /* synthetic */ void getPositiveInfinityBounds$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object runAnimation(Animation<T, V> animation, T t2, l<? super Animatable<T, V>, t0> lVar, d7.d<? super AnimationResult<T, V>> dVar) {
        return MutatorMutex.mutate$default(this.mutatorMutex, null, new AnonymousClass2(this, t2, animation, this.internalState.getLastFrameTimeNanos(), lVar, null), dVar, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setRunning(boolean z) {
        this.isRunning.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTargetValue(T t2) {
        this.targetValue.setValue(t2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void updateBounds$default(Animatable animatable, Object obj, Object obj2, int i10, Object obj3) {
        if ((i10 & 1) != 0) {
            obj = animatable.lowerBound;
        }
        if ((i10 & 2) != 0) {
            obj2 = animatable.upperBound;
        }
        animatable.updateBounds(obj, obj2);
    }

    public final Object animateDecay(T t2, DecayAnimationSpec<T> decayAnimationSpec, l<? super Animatable<T, V>, t0> lVar, d7.d<? super AnimationResult<T, V>> dVar) {
        return runAnimation(new DecayAnimation((DecayAnimationSpec) decayAnimationSpec, (TwoWayConverter<T, AnimationVector>) this.typeConverter, (Object) getValue(), (AnimationVector) this.typeConverter.getConvertToVector().invoke(t2)), t2, lVar, dVar);
    }

    public final Object animateTo(T t2, AnimationSpec<T> animationSpec, T t10, l<? super Animatable<T, V>, t0> lVar, d7.d<? super AnimationResult<T, V>> dVar) {
        return runAnimation(AnimationKt.TargetBasedAnimation(animationSpec, this.typeConverter, getValue(), t2, t10), t10, lVar, dVar);
    }

    public final State<T> asState() {
        return this.internalState;
    }

    public final SpringSpec<T> getDefaultSpringSpec$animation_core_release() {
        return this.defaultSpringSpec;
    }

    public final AnimationState<T, V> getInternalState$animation_core_release() {
        return this.internalState;
    }

    public final String getLabel() {
        return this.label;
    }

    public final T getLowerBound() {
        return this.lowerBound;
    }

    public final T getTargetValue() {
        return this.targetValue.getValue();
    }

    public final TwoWayConverter<T, V> getTypeConverter() {
        return this.typeConverter;
    }

    public final T getUpperBound() {
        return this.upperBound;
    }

    public final T getValue() {
        return this.internalState.getValue();
    }

    public final T getVelocity() {
        return (T) this.typeConverter.getConvertFromVector().invoke(getVelocityVector());
    }

    public final V getVelocityVector() {
        return (V) this.internalState.getVelocityVector();
    }

    public final boolean isRunning() {
        return ((Boolean) this.isRunning.getValue()).booleanValue();
    }

    public final Object snapTo(T t2, d7.d<? super t0> dVar) {
        Object objMutate$default = MutatorMutex.mutate$default(this.mutatorMutex, null, new C02662(this, t2, null), dVar, 1, null);
        return objMutate$default == e7.a.f15033i ? objMutate$default : t0.f22605a;
    }

    public final Object stop(d7.d<? super t0> dVar) {
        Object objMutate$default = MutatorMutex.mutate$default(this.mutatorMutex, null, new C02672(this, null), dVar, 1, null);
        return objMutate$default == e7.a.f15033i ? objMutate$default : t0.f22605a;
    }

    public final void updateBounds(T lowerBound, T upperBound) {
        V v2;
        V v10;
        if (lowerBound == null || (v2 = (V) this.typeConverter.getConvertToVector().invoke(lowerBound)) == null) {
            v2 = this.negativeInfinityBounds;
        }
        if (upperBound == null || (v10 = (V) this.typeConverter.getConvertToVector().invoke(upperBound)) == null) {
            v10 = this.positiveInfinityBounds;
        }
        int size = v2.getSize();
        for (int i10 = 0; i10 < size; i10++) {
            if (v2.get$animation_core_release(i10) > v10.get$animation_core_release(i10)) {
                throw new IllegalStateException(("Lower bound must be no greater than upper bound on *all* dimensions. The provided lower bound: " + v2 + " is greater than upper bound " + v10 + " on index " + i10).toString());
            }
        }
        this.lowerBoundVector = v2;
        this.upperBoundVector = v10;
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
        if (isRunning()) {
            return;
        }
        T tClampToBounds = clampToBounds(getValue());
        if (p.a(tClampToBounds, getValue())) {
            return;
        }
        this.internalState.setValue$animation_core_release(tClampToBounds);
    }

    public /* synthetic */ Animatable(Object obj, TwoWayConverter twoWayConverter, Object obj2, String str, int i10, h hVar) {
        this(obj, twoWayConverter, (i10 & 4) != 0 ? null : obj2, (i10 & 8) != 0 ? "Animatable" : str);
    }

    public /* synthetic */ Animatable(Object obj, TwoWayConverter twoWayConverter, Object obj2, int i10, h hVar) {
        this(obj, twoWayConverter, (i10 & 4) != 0 ? null : obj2);
    }

    @x6.e
    public /* synthetic */ Animatable(Object obj, TwoWayConverter twoWayConverter, Object obj2) {
        this(obj, twoWayConverter, obj2, "Animatable");
    }
}
