package androidx.compose.animation.core;

import androidx.compose.animation.core.AnimationVector;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import t.k;
import t.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003B/\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\fJ'\u0010\u0011\u001a\u00020\u00102\u0006\u0010\r\u001a\u00028\u00002\u0006\u0010\u000e\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u0011\u0010\u0012J/\u0010\u0015\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\r\u001a\u00028\u00002\u0006\u0010\u000e\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J/\u0010\u0017\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\r\u001a\u00028\u00002\u0006\u0010\u000e\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0017\u0010\u0016R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0018R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u0019R\u001a\u0010\t\u001a\u00020\b8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\t\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\n\u001a\u00020\b8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u001a\u001a\u0004\b\u001d\u0010\u001cR\u0016\u0010\u001e\u001a\u00028\u00008\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0016\u0010 \u001a\u00028\u00008\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b \u0010\u001fR\u0016\u0010!\u001a\u00028\u00008\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b!\u0010\u001fR\u0016\u0010\"\u001a\u00028\u00008\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\"\u0010\u001fR\u0016\u0010$\u001a\u00020#8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b$\u0010%¨\u0006&"}, d2 = {"Landroidx/compose/animation/core/VectorizedMonoSplineKeyframesSpec;", "Landroidx/compose/animation/core/AnimationVector;", "V", "Landroidx/compose/animation/core/VectorizedDurationBasedAnimationSpec;", "Lt/k;", "timestamps", "Lt/l;", "keyframes", "", "durationMillis", "delayMillis", "<init>", "(Lt/k;Lt/l;II)V", "initialValue", "targetValue", "initialVelocity", "Lx6/t0;", "init", "(Landroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/AnimationVector;)V", "", "playTimeNanos", "getValueFromNanos", "(JLandroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/AnimationVector;)Landroidx/compose/animation/core/AnimationVector;", "getVelocityFromNanos", "Lt/k;", "Lt/l;", "I", "getDurationMillis", "()I", "getDelayMillis", "valueVector", "Landroidx/compose/animation/core/AnimationVector;", "velocityVector", "lastInitialValue", "lastTargetValue", "Landroidx/compose/animation/core/MonoSpline;", "monoSpline", "Landroidx/compose/animation/core/MonoSpline;", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class VectorizedMonoSplineKeyframesSpec<V extends AnimationVector> implements VectorizedDurationBasedAnimationSpec<V> {
    public static final int $stable = 8;
    private final int delayMillis;
    private final int durationMillis;
    private final l keyframes;
    private V lastInitialValue;
    private V lastTargetValue;
    private MonoSpline monoSpline;
    private final k timestamps;
    private V valueVector;
    private V velocityVector;

    public VectorizedMonoSplineKeyframesSpec(k kVar, l lVar, int i10, int i11) {
        this.timestamps = kVar;
        this.keyframes = lVar;
        this.durationMillis = i10;
        this.delayMillis = i11;
    }

    private final void init(V initialValue, V targetValue, V initialVelocity) {
        if (this.valueVector == null) {
            this.valueVector = (V) AnimationVectorsKt.newInstance(initialValue);
            this.velocityVector = (V) AnimationVectorsKt.newInstance(initialVelocity);
        }
        if (this.monoSpline != null) {
            V v2 = this.lastInitialValue;
            if (v2 == null) {
                p.i("lastInitialValue");
                throw null;
            }
            if (v2.equals(initialValue)) {
                V v10 = this.lastTargetValue;
                if (v10 == null) {
                    p.i("lastTargetValue");
                    throw null;
                }
                if (v10.equals(targetValue)) {
                    return;
                }
            }
        }
        this.lastInitialValue = initialValue;
        this.lastTargetValue = targetValue;
        int i10 = this.keyframes.f21846e;
        int i11 = i10 + 2;
        float[] fArr = new float[i11];
        ArrayList arrayList = new ArrayList(i11);
        for (int i12 = 0; i12 < i11; i12++) {
            arrayList.add(new float[initialValue.getSize()]);
        }
        fArr[0] = 0.0f;
        int i13 = i10 + 1;
        float f10 = 1000L;
        fArr[i13] = getDurationMillis() / f10;
        float[] fArr2 = (float[]) arrayList.get(0);
        float[] fArr3 = (float[]) arrayList.get(i13);
        int size = initialValue.getSize();
        for (int i14 = 0; i14 < size; i14++) {
            fArr2[i14] = initialValue.get$animation_core_release(i14);
            fArr3[i14] = targetValue.get$animation_core_release(i14);
        }
        k kVar = this.timestamps;
        int[] iArr = kVar.f21839a;
        int i15 = kVar.f21840b;
        int i16 = 0;
        while (i16 < i15) {
            int i17 = iArr[i16];
            AnimationVector animationVector = (AnimationVector) this.keyframes.b(i17);
            i16++;
            fArr[i16] = i17 / f10;
            float[] fArr4 = (float[]) arrayList.get(i16);
            int length = fArr4.length;
            for (int i18 = 0; i18 < length; i18++) {
                fArr4[i18] = animationVector.get$animation_core_release(i18);
            }
        }
        this.monoSpline = new MonoSpline(fArr, arrayList);
    }

    @Override // androidx.compose.animation.core.VectorizedDurationBasedAnimationSpec
    public int getDelayMillis() {
        return this.delayMillis;
    }

    @Override // androidx.compose.animation.core.VectorizedDurationBasedAnimationSpec
    public int getDurationMillis() {
        return this.durationMillis;
    }

    @Override // androidx.compose.animation.core.VectorizedDurationBasedAnimationSpec, androidx.compose.animation.core.VectorizedAnimationSpec
    public final /* synthetic */ long getDurationNanos(AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        return f.a(this, animationVector, animationVector2, animationVector3);
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public final /* synthetic */ AnimationVector getEndVelocity(AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        return e.a(this, animationVector, animationVector2, animationVector3);
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public V getValueFromNanos(long playTimeNanos, V initialValue, V targetValue, V initialVelocity) {
        int iClampPlayTime = (int) VectorizedAnimationSpecKt.clampPlayTime(this, playTimeNanos / 1000000);
        if (this.keyframes.a(iClampPlayTime)) {
            return (V) this.keyframes.b(iClampPlayTime);
        }
        if (iClampPlayTime >= getDurationMillis()) {
            return targetValue;
        }
        if (iClampPlayTime <= 0) {
            return initialValue;
        }
        init(initialValue, targetValue, initialVelocity);
        MonoSpline monoSpline = this.monoSpline;
        if (monoSpline == null) {
            p.i("monoSpline");
            throw null;
        }
        float f10 = iClampPlayTime / 1000;
        V v2 = this.valueVector;
        if (v2 == null) {
            p.i("valueVector");
            throw null;
        }
        monoSpline.getPos(f10, v2);
        V v10 = this.valueVector;
        if (v10 != null) {
            return v10;
        }
        p.i("valueVector");
        throw null;
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public V getVelocityFromNanos(long playTimeNanos, V initialValue, V targetValue, V initialVelocity) {
        long jClampPlayTime = VectorizedAnimationSpecKt.clampPlayTime(this, playTimeNanos / 1000000);
        if (jClampPlayTime < 0) {
            return initialVelocity;
        }
        init(initialValue, targetValue, initialVelocity);
        MonoSpline monoSpline = this.monoSpline;
        if (monoSpline == null) {
            p.i("monoSpline");
            throw null;
        }
        float f10 = jClampPlayTime / 1000;
        V v2 = this.velocityVector;
        if (v2 == null) {
            p.i("velocityVector");
            throw null;
        }
        monoSpline.getSlope(f10, v2);
        V v10 = this.velocityVector;
        if (v10 != null) {
            return v10;
        }
        p.i("velocityVector");
        throw null;
    }

    @Override // androidx.compose.animation.core.VectorizedFiniteAnimationSpec, androidx.compose.animation.core.VectorizedAnimationSpec
    public final /* synthetic */ boolean isInfinite() {
        return g.a(this);
    }

    public /* synthetic */ VectorizedMonoSplineKeyframesSpec(k kVar, l lVar, int i10, int i11, int i12, h hVar) {
        this(kVar, lVar, i10, (i12 & 8) != 0 ? 0 : i11);
    }
}
