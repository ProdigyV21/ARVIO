package androidx.compose.animation.core;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\b\u0007\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\f\u001a\u00020\tH\u0010¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\u000f\u001a\u00020\u0000H\u0010¢\u0006\u0004\b\r\u0010\u000eJ\u0018\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0010H\u0090\u0002¢\u0006\u0004\b\u0012\u0010\u0013J \u0010\u0018\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0002H\u0090\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ\u001a\u0010\u001f\u001a\u00020\u001e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001cH\u0096\u0002¢\u0006\u0004\b\u001f\u0010 J\u000f\u0010!\u001a\u00020\u0010H\u0016¢\u0006\u0004\b!\u0010\"R*\u0010\u0003\u001a\u00020\u00022\u0006\u0010#\u001a\u00020\u00028\u0006@@X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010$\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R*\u0010\u0004\u001a\u00020\u00022\u0006\u0010#\u001a\u00020\u00028\u0006@@X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010$\u001a\u0004\b)\u0010&\"\u0004\b*\u0010(R*\u0010\u0005\u001a\u00020\u00022\u0006\u0010#\u001a\u00020\u00028\u0006@@X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010$\u001a\u0004\b+\u0010&\"\u0004\b,\u0010(R*\u0010\u0006\u001a\u00020\u00022\u0006\u0010#\u001a\u00020\u00028\u0006@@X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0006\u0010$\u001a\u0004\b-\u0010&\"\u0004\b.\u0010(R\u001a\u0010/\u001a\u00020\u00108\u0010X\u0090D¢\u0006\f\n\u0004\b/\u00100\u001a\u0004\b1\u0010\"¨\u00062"}, d2 = {"Landroidx/compose/animation/core/AnimationVector4D;", "Landroidx/compose/animation/core/AnimationVector;", "", "v1", "v2", "v3", "v4", "<init>", "(FFFF)V", "Lx6/t0;", "reset$animation_core_release", "()V", "reset", "newVector$animation_core_release", "()Landroidx/compose/animation/core/AnimationVector4D;", "newVector", "", "index", "get$animation_core_release", "(I)F", "get", "value", "set$animation_core_release", "(IF)V", "set", "", "toString", "()Ljava/lang/String;", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "()I", "<set-?>", "F", "getV1", "()F", "setV1$animation_core_release", "(F)V", "getV2", "setV2$animation_core_release", "getV3", "setV3$animation_core_release", "getV4", "setV4$animation_core_release", ContentDisposition.Parameters.Size, "I", "getSize$animation_core_release", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AnimationVector4D extends AnimationVector {
    public static final int $stable = 8;
    private final int size;
    private float v1;
    private float v2;
    private float v3;
    private float v4;

    public AnimationVector4D(float f10, float f11, float f12, float f13) {
        super(null);
        this.v1 = f10;
        this.v2 = f11;
        this.v3 = f12;
        this.v4 = f13;
        this.size = 4;
    }

    public boolean equals(Object other) {
        if (!(other instanceof AnimationVector4D)) {
            return false;
        }
        AnimationVector4D animationVector4D = (AnimationVector4D) other;
        return animationVector4D.v1 == this.v1 && animationVector4D.v2 == this.v2 && animationVector4D.v3 == this.v3 && animationVector4D.v4 == this.v4;
    }

    @Override // androidx.compose.animation.core.AnimationVector
    public float get$animation_core_release(int index) {
        if (index == 0) {
            return this.v1;
        }
        if (index == 1) {
            return this.v2;
        }
        if (index == 2) {
            return this.v3;
        }
        if (index != 3) {
            return 0.0f;
        }
        return this.v4;
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

    public final float getV3() {
        return this.v3;
    }

    public final float getV4() {
        return this.v4;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.v4) + a0.c.b(this.v3, a0.c.b(this.v2, Float.floatToIntBits(this.v1) * 31, 31), 31);
    }

    @Override // androidx.compose.animation.core.AnimationVector
    public void reset$animation_core_release() {
        this.v1 = 0.0f;
        this.v2 = 0.0f;
        this.v3 = 0.0f;
        this.v4 = 0.0f;
    }

    @Override // androidx.compose.animation.core.AnimationVector
    public void set$animation_core_release(int index, float value) {
        if (index == 0) {
            this.v1 = value;
            return;
        }
        if (index == 1) {
            this.v2 = value;
        } else if (index == 2) {
            this.v3 = value;
        } else {
            if (index != 3) {
                return;
            }
            this.v4 = value;
        }
    }

    public final void setV1$animation_core_release(float f10) {
        this.v1 = f10;
    }

    public final void setV2$animation_core_release(float f10) {
        this.v2 = f10;
    }

    public final void setV3$animation_core_release(float f10) {
        this.v3 = f10;
    }

    public final void setV4$animation_core_release(float f10) {
        this.v4 = f10;
    }

    public String toString() {
        return "AnimationVector4D: v1 = " + this.v1 + ", v2 = " + this.v2 + ", v3 = " + this.v3 + ", v4 = " + this.v4;
    }

    @Override // androidx.compose.animation.core.AnimationVector
    public AnimationVector4D newVector$animation_core_release() {
        return new AnimationVector4D(0.0f, 0.0f, 0.0f, 0.0f);
    }
}
