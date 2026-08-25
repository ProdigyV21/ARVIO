package androidx.compose.animation.core;

import androidx.compose.animation.core.AnimationVector;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b&\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0004B[\b\u0000\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006\u0012\u0006\u0010\b\u001a\u00028\u0001\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00028\u0000\u0012\u0006\u0010\f\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0014\u001a\u00020\u0010¢\u0006\u0004\b\u0014\u0010\u0015J\u0019\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0016¢\u0006\u0004\b\u0017\u0010\u0018R#\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u000b\u001a\u00028\u00008\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\f\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\f\u0010\u001f\u001a\u0004\b \u0010!R\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\"R+\u0010)\u001a\u00028\u00002\u0006\u0010#\u001a\u00028\u00008F@@X\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b&\u0010\u001e\"\u0004\b'\u0010(R*\u0010*\u001a\u00028\u00012\u0006\u0010#\u001a\u00028\u00018\u0006@@X\u0086\u000e¢\u0006\u0012\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R*\u0010\n\u001a\u00020\t2\u0006\u0010#\u001a\u00020\t8F@@X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u001f\u001a\u0004\b0\u0010!\"\u0004\b1\u00102R*\u00103\u001a\u00020\t2\u0006\u0010#\u001a\u00020\t8F@@X\u0086\u000e¢\u0006\u0012\n\u0004\b3\u0010\u001f\u001a\u0004\b4\u0010!\"\u0004\b5\u00102R+\u0010\u000e\u001a\u00020\r2\u0006\u0010#\u001a\u00020\r8F@@X\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b6\u0010%\u001a\u0004\b\u000e\u00107\"\u0004\b8\u00109R\u0011\u0010;\u001a\u00028\u00008F¢\u0006\u0006\u001a\u0004\b:\u0010\u001e¨\u0006<"}, d2 = {"Landroidx/compose/animation/core/AnimationScope;", "T", "Landroidx/compose/animation/core/AnimationVector;", "V", "", "initialValue", "Landroidx/compose/animation/core/TwoWayConverter;", "typeConverter", "initialVelocityVector", "", "lastFrameTimeNanos", "targetValue", "startTimeNanos", "", "isRunning", "Lkotlin/Function0;", "Lx6/t0;", "onCancel", "<init>", "(Ljava/lang/Object;Landroidx/compose/animation/core/TwoWayConverter;Landroidx/compose/animation/core/AnimationVector;JLjava/lang/Object;JZLr7/a;)V", "cancelAnimation", "()V", "Landroidx/compose/animation/core/AnimationState;", "toAnimationState", "()Landroidx/compose/animation/core/AnimationState;", "Landroidx/compose/animation/core/TwoWayConverter;", "getTypeConverter", "()Landroidx/compose/animation/core/TwoWayConverter;", "Ljava/lang/Object;", "getTargetValue", "()Ljava/lang/Object;", "J", "getStartTimeNanos", "()J", "Lr7/a;", "<set-?>", "value$delegate", "Landroidx/compose/runtime/MutableState;", "getValue", "setValue$animation_core_release", "(Ljava/lang/Object;)V", "value", "velocityVector", "Landroidx/compose/animation/core/AnimationVector;", "getVelocityVector", "()Landroidx/compose/animation/core/AnimationVector;", "setVelocityVector$animation_core_release", "(Landroidx/compose/animation/core/AnimationVector;)V", "getLastFrameTimeNanos", "setLastFrameTimeNanos$animation_core_release", "(J)V", "finishedTimeNanos", "getFinishedTimeNanos", "setFinishedTimeNanos$animation_core_release", "isRunning$delegate", "()Z", "setRunning$animation_core_release", "(Z)V", "getVelocity", "velocity", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AnimationScope<T, V extends AnimationVector> {
    public static final int $stable = 8;
    private long finishedTimeNanos = Long.MIN_VALUE;

    /* JADX INFO: renamed from: isRunning$delegate, reason: from kotlin metadata */
    private final MutableState isRunning;
    private long lastFrameTimeNanos;
    private final r7.a<t0> onCancel;
    private final long startTimeNanos;
    private final T targetValue;
    private final TwoWayConverter<T, V> typeConverter;

    /* JADX INFO: renamed from: value$delegate, reason: from kotlin metadata */
    private final MutableState value;
    private V velocityVector;

    public AnimationScope(T t2, TwoWayConverter<T, V> twoWayConverter, V v2, long j10, T t10, long j11, boolean z, r7.a<t0> aVar) {
        this.typeConverter = twoWayConverter;
        this.targetValue = t10;
        this.startTimeNanos = j11;
        this.onCancel = aVar;
        this.value = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(t2, null, 2, null);
        this.velocityVector = (V) AnimationVectorsKt.copy(v2);
        this.lastFrameTimeNanos = j10;
        this.isRunning = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(z), null, 2, null);
    }

    public final void cancelAnimation() {
        setRunning$animation_core_release(false);
        this.onCancel.invoke();
    }

    public final long getFinishedTimeNanos() {
        return this.finishedTimeNanos;
    }

    public final long getLastFrameTimeNanos() {
        return this.lastFrameTimeNanos;
    }

    public final long getStartTimeNanos() {
        return this.startTimeNanos;
    }

    public final T getTargetValue() {
        return this.targetValue;
    }

    public final TwoWayConverter<T, V> getTypeConverter() {
        return this.typeConverter;
    }

    public final T getValue() {
        return this.value.getValue();
    }

    public final T getVelocity() {
        return (T) this.typeConverter.getConvertFromVector().invoke(this.velocityVector);
    }

    public final V getVelocityVector() {
        return this.velocityVector;
    }

    public final boolean isRunning() {
        return ((Boolean) this.isRunning.getValue()).booleanValue();
    }

    public final void setFinishedTimeNanos$animation_core_release(long j10) {
        this.finishedTimeNanos = j10;
    }

    public final void setLastFrameTimeNanos$animation_core_release(long j10) {
        this.lastFrameTimeNanos = j10;
    }

    public final void setRunning$animation_core_release(boolean z) {
        this.isRunning.setValue(Boolean.valueOf(z));
    }

    public final void setValue$animation_core_release(T t2) {
        this.value.setValue(t2);
    }

    public final void setVelocityVector$animation_core_release(V v2) {
        this.velocityVector = v2;
    }

    public final AnimationState<T, V> toAnimationState() {
        return new AnimationState<>(this.typeConverter, getValue(), this.velocityVector, this.lastFrameTimeNanos, this.finishedTimeNanos, isRunning());
    }
}
