package androidx.compose.animation.core;

import androidx.compose.runtime.snapshots.SnapshotStateObserver;
import kotlin.Metadata;
import kotlin.jvm.internal.j0;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0006\u0010\u0004\u001a\u00028\u0000¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\b\u0010\tJ\u001a\u0010\f\u001a\u00020\u00072\b\b\u0001\u0010\u000b\u001a\u00020\nH\u0086@¢\u0006\u0004\b\f\u0010\rJ \u0010\u0010\u001a\u00020\u00072\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\u000eH\u0086@¢\u0006\u0004\b\u0010\u0010\u0011J \u0010\u0012\u001a\u00020\u00072\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\u000eH\u0086@¢\u0006\u0004\b\u0012\u0010\u0011J\u001d\u0010\u0017\u001a\u00020\u00072\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H\u0010¢\u0006\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0004\u001a\u00028\u00008\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u001e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010\u001bR \u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u001d0\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0014\u0010!\u001a\u00020 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00028\u00008\u0016X\u0096\u0004¢\u0006\f\n\u0004\b#\u0010\u0018\u001a\u0004\b$\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\n8G¢\u0006\u0006\u001a\u0004\b%\u0010&¨\u0006'"}, d2 = {"Landroidx/compose/animation/core/SeekableTransitionState;", "S", "Landroidx/compose/animation/core/TransitionState;", "initialState", "targetState", "<init>", "(Ljava/lang/Object;Ljava/lang/Object;)V", "Lx6/t0;", "seekToFraction", "()V", "", "fraction", "snapToFraction", "(FLd7/d;)Ljava/lang/Object;", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "animationSpec", "animateToTargetState", "(Landroidx/compose/animation/core/FiniteAnimationSpec;Ld7/d;)Ljava/lang/Object;", "animateToCurrentState", "Landroidx/compose/animation/core/Transition;", "transition", "transitionConfigured$animation_core_release", "(Landroidx/compose/animation/core/Transition;)V", "transitionConfigured", "Ljava/lang/Object;", "getTargetState", "()Ljava/lang/Object;", "Landroidx/compose/animation/core/Transition;", "Landroidx/compose/animation/core/Animatable;", "Landroidx/compose/animation/core/AnimationVector1D;", "animatedFraction", "Landroidx/compose/animation/core/Animatable;", "Landroidx/compose/runtime/snapshots/SnapshotStateObserver;", "observer", "Landroidx/compose/runtime/snapshots/SnapshotStateObserver;", "currentState", "getCurrentState", "getFraction", "()F", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SeekableTransitionState<S> extends TransitionState<S> {
    public static final int $stable = 8;
    private final Animatable<Float, AnimationVector1D> animatedFraction;
    private final S currentState;
    private final SnapshotStateObserver observer;
    private final S targetState;
    private Transition<S> transition;

    /* JADX INFO: renamed from: androidx.compose.animation.core.SeekableTransitionState$animateToCurrentState$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"S", "Landroidx/compose/animation/core/Animatable;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "Lx6/t0;", "invoke", "(Landroidx/compose/animation/core/Animatable;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<Animatable<Float, AnimationVector1D>, t0> {
        final /* synthetic */ SeekableTransitionState<S> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(SeekableTransitionState<S> seekableTransitionState) {
            super(1);
            this.this$0 = seekableTransitionState;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Animatable<Float, AnimationVector1D>) obj);
            return t0.f22605a;
        }

        public final void invoke(Animatable<Float, AnimationVector1D> animatable) {
            this.this$0.seekToFraction();
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.SeekableTransitionState$animateToTargetState$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"S", "Landroidx/compose/animation/core/Animatable;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "Lx6/t0;", "invoke", "(Landroidx/compose/animation/core/Animatable;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C02702 extends r implements l<Animatable<Float, AnimationVector1D>, t0> {
        final /* synthetic */ SeekableTransitionState<S> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C02702(SeekableTransitionState<S> seekableTransitionState) {
            super(1);
            this.this$0 = seekableTransitionState;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Animatable<Float, AnimationVector1D>) obj);
            return t0.f22605a;
        }

        public final void invoke(Animatable<Float, AnimationVector1D> animatable) {
            this.this$0.seekToFraction();
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.SeekableTransitionState$seekToFraction$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0005\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"S", "Lx6/t0;", "it", "invoke", "(Lx6/t0;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<t0, t0> {
        final /* synthetic */ SeekableTransitionState<S> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(SeekableTransitionState<S> seekableTransitionState) {
            super(1);
            this.this$0 = seekableTransitionState;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((t0) obj);
            return t0.f22605a;
        }

        public final void invoke(t0 t0Var) {
            this.this$0.seekToFraction();
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.SeekableTransitionState$seekToFraction$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"S", "Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C02712 extends r implements r7.a<t0> {
        final /* synthetic */ j0 $duration;
        final /* synthetic */ Transition<S> $transition;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C02712(j0 j0Var, Transition<S> transition) {
            super(0);
            this.$duration = j0Var;
            this.$transition = transition;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m110invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m110invoke() {
            this.$duration.f19745i = this.$transition.getTotalDurationNanos();
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.SeekableTransitionState$snapToFraction$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.animation.core.SeekableTransitionState", f = "Transition.kt", l = {228}, m = "snapToFraction")
    public static final class C02721 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ SeekableTransitionState<S> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C02721(SeekableTransitionState<S> seekableTransitionState, d7.d<? super C02721> dVar) {
            super(dVar);
            this.this$0 = seekableTransitionState;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.snapToFraction(0.0f, this);
        }
    }

    public SeekableTransitionState(S s10, S s11) {
        super(null);
        this.targetState = s11;
        Animatable<Float, AnimationVector1D> animatableAnimatable$default = AnimatableKt.Animatable$default(0.0f, 0.0f, 2, null);
        animatableAnimatable$default.updateBounds(Float.valueOf(0.0f), Float.valueOf(1.0f));
        this.animatedFraction = animatableAnimatable$default;
        this.observer = new SnapshotStateObserver(SeekableTransitionState$observer$1.INSTANCE);
        this.currentState = s10;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object animateToCurrentState$default(SeekableTransitionState seekableTransitionState, FiniteAnimationSpec finiteAnimationSpec, d7.d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            finiteAnimationSpec = seekableTransitionState.animatedFraction.getDefaultSpringSpec$animation_core_release();
        }
        return seekableTransitionState.animateToCurrentState(finiteAnimationSpec, dVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object animateToTargetState$default(SeekableTransitionState seekableTransitionState, FiniteAnimationSpec finiteAnimationSpec, d7.d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            finiteAnimationSpec = seekableTransitionState.animatedFraction.getDefaultSpringSpec$animation_core_release();
        }
        return seekableTransitionState.animateToTargetState(finiteAnimationSpec, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void seekToFraction() {
        Transition<S> transition = this.transition;
        if (transition == null) {
            return;
        }
        this.observer.observeReads(t0.f22605a, new AnonymousClass1(this), new C02712(new j0(), transition));
        transition.seek(getCurrentState(), getTargetState(), t7.a.N(this.animatedFraction.getValue().floatValue() * r1.f19745i));
    }

    public final Object animateToCurrentState(FiniteAnimationSpec<Float> finiteAnimationSpec, d7.d<? super t0> dVar) {
        Object objAnimateTo$default;
        return (this.transition == null || p.a(getCurrentState(), getTargetState()) || (objAnimateTo$default = Animatable.animateTo$default(this.animatedFraction, new Float(0.0f), finiteAnimationSpec, null, new AnonymousClass2(this), dVar, 4, null)) != e7.a.f15033i) ? t0.f22605a : objAnimateTo$default;
    }

    public final Object animateToTargetState(FiniteAnimationSpec<Float> finiteAnimationSpec, d7.d<? super t0> dVar) {
        Object objAnimateTo$default;
        return (this.transition == null || p.a(getCurrentState(), getTargetState()) || (objAnimateTo$default = Animatable.animateTo$default(this.animatedFraction, new Float(1.0f), finiteAnimationSpec, null, new C02702(this), dVar, 4, null)) != e7.a.f15033i) ? t0.f22605a : objAnimateTo$default;
    }

    @Override // androidx.compose.animation.core.TransitionState
    public S getCurrentState() {
        return this.currentState;
    }

    public final float getFraction() {
        return this.animatedFraction.getValue().floatValue();
    }

    @Override // androidx.compose.animation.core.TransitionState
    public S getTargetState() {
        return this.targetState;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object snapToFraction(float r5, d7.d<? super x6.t0> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof androidx.compose.animation.core.SeekableTransitionState.C02721
            if (r0 == 0) goto L13
            r0 = r6
            androidx.compose.animation.core.SeekableTransitionState$snapToFraction$1 r0 = (androidx.compose.animation.core.SeekableTransitionState.C02721) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.animation.core.SeekableTransitionState$snapToFraction$1 r0 = new androidx.compose.animation.core.SeekableTransitionState$snapToFraction$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            x6.t0 r2 = x6.t0.f22605a
            r3 = 1
            if (r1 == 0) goto L33
            if (r1 != r3) goto L2b
            java.lang.Object r5 = r0.L$0
            androidx.compose.animation.core.SeekableTransitionState r5 = (androidx.compose.animation.core.SeekableTransitionState) r5
            k2.c.G(r6)
            goto L65
        L2b:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L33:
            k2.c.G(r6)
            r6 = 0
            int r6 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1))
            if (r6 > 0) goto L69
            r6 = 1065353216(0x3f800000, float:1.0)
            int r6 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r6 > 0) goto L69
            java.lang.Object r6 = r4.getCurrentState()
            java.lang.Object r1 = r4.getTargetState()
            boolean r6 = kotlin.jvm.internal.p.a(r6, r1)
            if (r6 == 0) goto L50
            return r2
        L50:
            androidx.compose.animation.core.Animatable<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r6 = r4.animatedFraction
            java.lang.Float r1 = new java.lang.Float
            r1.<init>(r5)
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r6.snapTo(r1, r0)
            e7.a r6 = e7.a.f15033i
            if (r5 != r6) goto L64
            return r6
        L64:
            r5 = r4
        L65:
            r5.seekToFraction()
            return r2
        L69:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r0 = "Expecting fraction between 0 and 1. Got "
            r6.<init>(r0)
            r6.append(r5)
            java.lang.String r5 = r6.toString()
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r5 = r5.toString()
            r6.<init>(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.core.SeekableTransitionState.snapToFraction(float, d7.d):java.lang.Object");
    }

    @Override // androidx.compose.animation.core.TransitionState
    public void transitionConfigured$animation_core_release(Transition<S> transition) {
        Transition<S> transition2 = this.transition;
        if (transition2 == null || p.a(transition, transition2)) {
            this.transition = transition;
            seekToFraction();
        } else {
            throw new IllegalStateException(("An instance of SeekableTransitionState has been used in different Transitions. Previous instance: " + this.transition + ", new instance: " + transition).toString());
        }
    }
}
