package androidx.compose.foundation.gestures;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.animation.core.VectorizedAnimationSpec;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.i;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\n\b\u0000\u0018\u0000 #2\u00020\u0001:\u0001#B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006JL\u0010\u000f\u001a\u00020\u000b2!\u0010\f\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u00072\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\rH\u0086@\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0016\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0018\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001b\u001a\u00020\u001a8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\"\u0010\u001d\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"¨\u0006$"}, d2 = {"Landroidx/compose/foundation/gestures/UpdatableAnimationState;", "", "Landroidx/compose/animation/core/AnimationSpec;", "", "animationSpec", "<init>", "(Landroidx/compose/animation/core/AnimationSpec;)V", "Lkotlin/Function1;", "Lx6/y;", ContentDisposition.Parameters.Name, "valueDelta", "Lx6/t0;", "beforeFrame", "Lkotlin/Function0;", "afterFrame", "animateToZero", "(Lr7/l;Lr7/a;Ld7/d;)Ljava/lang/Object;", "Landroidx/compose/animation/core/VectorizedAnimationSpec;", "Landroidx/compose/animation/core/AnimationVector1D;", "vectorizedSpec", "Landroidx/compose/animation/core/VectorizedAnimationSpec;", "", "lastFrameTime", "J", "lastVelocity", "Landroidx/compose/animation/core/AnimationVector1D;", "", "isRunning", "Z", "value", "F", "getValue", "()F", "setValue", "(F)V", "Companion", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class UpdatableAnimationState {

    @Deprecated
    public static final float VisibilityThreshold = 0.01f;
    private boolean isRunning;
    private long lastFrameTime = Long.MIN_VALUE;
    private AnimationVector1D lastVelocity = ZeroVector;
    private float value;
    private final VectorizedAnimationSpec<AnimationVector1D> vectorizedSpec;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static final AnimationVector1D ZeroVector = new AnimationVector1D(0.0f);

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\n\u0010\t\u001a\u00020\n*\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Landroidx/compose/foundation/gestures/UpdatableAnimationState$Companion;", "", "()V", "VisibilityThreshold", "", "ZeroVector", "Landroidx/compose/animation/core/AnimationVector1D;", "getZeroVector", "()Landroidx/compose/animation/core/AnimationVector1D;", "isZeroish", "", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        public final AnimationVector1D getZeroVector() {
            return UpdatableAnimationState.ZeroVector;
        }

        public final boolean isZeroish(float f10) {
            return Math.abs(f10) < 0.01f;
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.UpdatableAnimationState$animateToZero$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.foundation.gestures.UpdatableAnimationState", f = "UpdatableAnimationState.kt", l = {101, 147}, m = "animateToZero")
    public static final class AnonymousClass1 extends f7.c {
        float F$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return UpdatableAnimationState.this.animateToZero(null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.UpdatableAnimationState$animateToZero$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "frameTime", "Lx6/t0;", "invoke", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass4 extends r implements l<Long, t0> {
        final /* synthetic */ l<Float, t0> $beforeFrame;
        final /* synthetic */ float $durationScale;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass4(float f10, l<? super Float, t0> lVar) {
            super(1);
            this.$durationScale = f10;
            this.$beforeFrame = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke(((Number) obj).longValue());
            return t0.f22605a;
        }

        public final void invoke(long j10) {
            if (UpdatableAnimationState.this.lastFrameTime == Long.MIN_VALUE) {
                UpdatableAnimationState.this.lastFrameTime = j10;
            }
            AnimationVector1D animationVector1D = new AnimationVector1D(UpdatableAnimationState.this.getValue());
            long durationNanos = this.$durationScale == 0.0f ? UpdatableAnimationState.this.vectorizedSpec.getDurationNanos(new AnimationVector1D(UpdatableAnimationState.this.getValue()), UpdatableAnimationState.Companion.getZeroVector(), UpdatableAnimationState.this.lastVelocity) : t7.a.N((j10 - UpdatableAnimationState.this.lastFrameTime) / this.$durationScale);
            float value = ((AnimationVector1D) UpdatableAnimationState.this.vectorizedSpec.getValueFromNanos(durationNanos, animationVector1D, UpdatableAnimationState.Companion.getZeroVector(), UpdatableAnimationState.this.lastVelocity)).getValue();
            UpdatableAnimationState updatableAnimationState = UpdatableAnimationState.this;
            updatableAnimationState.lastVelocity = (AnimationVector1D) updatableAnimationState.vectorizedSpec.getVelocityFromNanos(durationNanos, animationVector1D, UpdatableAnimationState.Companion.getZeroVector(), UpdatableAnimationState.this.lastVelocity);
            UpdatableAnimationState.this.lastFrameTime = j10;
            float value2 = UpdatableAnimationState.this.getValue() - value;
            UpdatableAnimationState.this.setValue(value);
            this.$beforeFrame.invoke(Float.valueOf(value2));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.UpdatableAnimationState$animateToZero$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "it", "Lx6/t0;", "invoke", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass5 extends r implements l<Long, t0> {
        final /* synthetic */ l<Float, t0> $beforeFrame;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass5(l<? super Float, t0> lVar) {
            super(1);
            this.$beforeFrame = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke(((Number) obj).longValue());
            return t0.f22605a;
        }

        public final void invoke(long j10) {
            float value = UpdatableAnimationState.this.getValue();
            UpdatableAnimationState.this.setValue(0.0f);
            this.$beforeFrame.invoke(Float.valueOf(value));
        }
    }

    public UpdatableAnimationState(AnimationSpec<Float> animationSpec) {
        this.vectorizedSpec = animationSpec.vectorize(VectorConvertersKt.getVectorConverter(i.f19743a));
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x00cc, code lost:
    
        if (androidx.compose.runtime.MonotonicFrameClockKt.withFrameNanos(r0, r1) == r8) goto L47;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0080 A[Catch: all -> 0x00ac, PHI: r0 r1 r12 r13 r14
      0x0080: PHI (r0v9 ??) = (r0v3 ??), (r0v15 ??) binds: [B:30:0x0079, B:38:0x00a5] A[DONT_GENERATE, DONT_INLINE]
      0x0080: PHI (r1v4 androidx.compose.foundation.gestures.UpdatableAnimationState$animateToZero$1) = 
      (r1v2 androidx.compose.foundation.gestures.UpdatableAnimationState$animateToZero$1)
      (r1v5 androidx.compose.foundation.gestures.UpdatableAnimationState$animateToZero$1)
     binds: [B:30:0x0079, B:38:0x00a5] A[DONT_GENERATE, DONT_INLINE]
      0x0080: PHI (r12v7 float) = (r12v4 float), (r12v8 float) binds: [B:30:0x0079, B:38:0x00a5] A[DONT_GENERATE, DONT_INLINE]
      0x0080: PHI (r13v7 r7.l<? super java.lang.Float, x6.t0>) = (r13v4 r7.l<? super java.lang.Float, x6.t0>), (r13v8 r7.l<? super java.lang.Float, x6.t0>) binds: [B:30:0x0079, B:38:0x00a5] A[DONT_GENERATE, DONT_INLINE]
      0x0080: PHI (r14v15 androidx.compose.foundation.gestures.UpdatableAnimationState) = 
      (r14v9 androidx.compose.foundation.gestures.UpdatableAnimationState)
      (r14v16 androidx.compose.foundation.gestures.UpdatableAnimationState)
     binds: [B:30:0x0079, B:38:0x00a5] A[DONT_GENERATE, DONT_INLINE], TRY_ENTER, TryCatch #2 {all -> 0x00ac, blocks: (B:36:0x00a0, B:31:0x0080, B:33:0x008a), top: B:59:0x00a0 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x008a A[Catch: all -> 0x00ac, TryCatch #2 {all -> 0x00ac, blocks: (B:36:0x00a0, B:31:0x0080, B:33:0x008a), top: B:59:0x00a0 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00a7 A[PHI: r0 r1 r13 r14
      0x00a7: PHI (r0v4 ??) = (r0v16 ??), (r0v17 ??) binds: [B:32:0x0088, B:38:0x00a5] A[DONT_GENERATE, DONT_INLINE]
      0x00a7: PHI (r1v3 androidx.compose.foundation.gestures.UpdatableAnimationState$animateToZero$1) = 
      (r1v4 androidx.compose.foundation.gestures.UpdatableAnimationState$animateToZero$1)
      (r1v5 androidx.compose.foundation.gestures.UpdatableAnimationState$animateToZero$1)
     binds: [B:32:0x0088, B:38:0x00a5] A[DONT_GENERATE, DONT_INLINE]
      0x00a7: PHI (r13v5 r7.l<? super java.lang.Float, x6.t0>) = (r13v7 r7.l<? super java.lang.Float, x6.t0>), (r13v8 r7.l<? super java.lang.Float, x6.t0>) binds: [B:32:0x0088, B:38:0x00a5] A[DONT_GENERATE, DONT_INLINE]
      0x00a7: PHI (r14v11 androidx.compose.foundation.gestures.UpdatableAnimationState) = 
      (r14v15 androidx.compose.foundation.gestures.UpdatableAnimationState)
      (r14v16 androidx.compose.foundation.gestures.UpdatableAnimationState)
     binds: [B:32:0x0088, B:38:0x00a5] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r0v10, types: [r7.a] */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v12, types: [r7.a] */
    /* JADX WARN: Type inference failed for: r12v18 */
    /* JADX WARN: Type inference failed for: r12v19 */
    /* JADX WARN: Type inference failed for: r12v6, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r13v0, types: [r7.a<x6.t0>] */
    /* JADX WARN: Type inference failed for: r13v1 */
    /* JADX WARN: Type inference failed for: r13v10, types: [androidx.compose.foundation.gestures.UpdatableAnimationState] */
    /* JADX WARN: Type inference failed for: r13v11 */
    /* JADX WARN: Type inference failed for: r13v14 */
    /* JADX WARN: Type inference failed for: r13v19 */
    /* JADX WARN: Type inference failed for: r13v2, types: [androidx.compose.foundation.gestures.UpdatableAnimationState] */
    /* JADX WARN: Type inference failed for: r13v20 */
    /* JADX WARN: Type inference failed for: r13v21 */
    /* JADX WARN: Type inference failed for: r13v22 */
    /* JADX WARN: Type inference failed for: r13v9 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x009d -> B:59:0x00a0). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object animateToZero(r7.l<? super java.lang.Float, x6.t0> r12, r7.a<x6.t0> r13, d7.d<? super x6.t0> r14) {
        /*
            Method dump skipped, instruction units count: 238
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.UpdatableAnimationState.animateToZero(r7.l, r7.a, d7.d):java.lang.Object");
    }

    public final float getValue() {
        return this.value;
    }

    public final void setValue(float f10) {
        this.value = f10;
    }
}
