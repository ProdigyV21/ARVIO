package androidx.compose.animation.core;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\n\u001a\u00020\u0007H\u0010¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\r\u001a\u00020\u0000H\u0010¢\u0006\u0004\b\u000b\u0010\fJ\u0018\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u000eH\u0090\u0002¢\u0006\u0004\b\u0010\u0010\u0011J \u0010\u0016\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0002H\u0090\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u001a\u0010\u001d\u001a\u00020\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001aH\u0096\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010\u001f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u001f\u0010 R*\u0010\u0003\u001a\u00020\u00022\u0006\u0010!\u001a\u00020\u00028\u0006@@X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R*\u0010\u0004\u001a\u00020\u00022\u0006\u0010!\u001a\u00020\u00028\u0006@@X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010\"\u001a\u0004\b'\u0010$\"\u0004\b(\u0010&R\u001a\u0010)\u001a\u00020\u000e8\u0010X\u0090D¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b+\u0010 ¨\u0006,"}, d2 = {"Landroidx/compose/animation/core/AnimationVector2D;", "Landroidx/compose/animation/core/AnimationVector;", "", "v1", "v2", "<init>", "(FF)V", "Lx6/t0;", "reset$animation_core_release", "()V", "reset", "newVector$animation_core_release", "()Landroidx/compose/animation/core/AnimationVector2D;", "newVector", "", "index", "get$animation_core_release", "(I)F", "get", "value", "set$animation_core_release", "(IF)V", "set", "", "toString", "()Ljava/lang/String;", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "()I", "<set-?>", "F", "getV1", "()F", "setV1$animation_core_release", "(F)V", "getV2", "setV2$animation_core_release", ContentDisposition.Parameters.Size, "I", "getSize$animation_core_release", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AnimationVector2D extends AnimationVector {
    public static final int $stable = 8;
    private final int size;
    private float v1;
    private float v2;

    public AnimationVector2D(float f10, float f11) {
        super(null);
        this.v1 = f10;
        this.v2 = f11;
        this.size = 2;
    }

    public boolean equals(Object other) {
        if (!(other instanceof AnimationVector2D)) {
            return false;
        }
        AnimationVector2D animationVector2D = (AnimationVector2D) other;
        return animationVector2D.v1 == this.v1 && animationVector2D.v2 == this.v2;
    }

    @Override // androidx.compose.animation.core.AnimationVector
    public float get$animation_core_release(int index) {
        if (index == 0) {
            return this.v1;
        }
        if (index != 1) {
            return 0.0f;
        }
        return this.v2;
    }

    @Override // androidx.compose.animation.core.AnimationVector
    /* JADX INFO: renamed from: getSize$animation_core_release, reason: from getter */
    public int getSize() {
        return this.size;
    }

    public final float getV1() {
        return this.v1;
    }

    public final float getV2() {
        return this.v2;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.v2) + (Float.floatToIntBits(this.v1) * 31);
    }

    @Override // androidx.compose.animation.core.AnimationVector
    public void reset$animation_core_release() {
        this.v1 = 0.0f;
        this.v2 = 0.0f;
    }

    @Override // androidx.compose.animation.core.AnimationVector
    public void set$animation_core_release(int index, float value) {
        if (index == 0) {
            this.v1 = value;
        } else {
            if (index != 1) {
                return;
            }
            this.v2 = value;
        }
    }

    public final void setV1$animation_core_release(float f10) {
        this.v1 = f10;
    }

    public final void setV2$animation_core_release(float f10) {
        this.v2 = f10;
    }

    public String toString() {
        return "AnimationVector2D: v1 = " + this.v1 + ", v2 = " + this.v2;
    }

    @Override // androidx.compose.animation.core.AnimationVector
    public AnimationVector2D newVector$animation_core_release() {
        return new AnimationVector2D(0.0f, 0.0f);
    }
}
