package androidx.compose.animation.core;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0006\u001a\u00020\u0004H ¢\u0006\u0004\b\u0005\u0010\u0003J\u000f\u0010\t\u001a\u00020\u0000H ¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\u000f\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH \u0002¢\u0006\u0004\b\r\u0010\u000eJ \u0010\u0013\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\fH \u0002¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0016\u001a\u00020\n8 X \u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015\u0082\u0001\u0004\u0017\u0018\u0019\u001a¨\u0006\u001b"}, d2 = {"Landroidx/compose/animation/core/AnimationVector;", "", "<init>", "()V", "Lx6/t0;", "reset$animation_core_release", "reset", "newVector$animation_core_release", "()Landroidx/compose/animation/core/AnimationVector;", "newVector", "", "index", "", "get$animation_core_release", "(I)F", "get", "value", "set$animation_core_release", "(IF)V", "set", "getSize$animation_core_release", "()I", ContentDisposition.Parameters.Size, "Landroidx/compose/animation/core/AnimationVector1D;", "Landroidx/compose/animation/core/AnimationVector2D;", "Landroidx/compose/animation/core/AnimationVector3D;", "Landroidx/compose/animation/core/AnimationVector4D;", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class AnimationVector {
    public static final int $stable = 0;

    public /* synthetic */ AnimationVector(h hVar) {
        this();
    }

    public abstract float get$animation_core_release(int index);

    public abstract int getSize$animation_core_release();

    public abstract AnimationVector newVector$animation_core_release();

    public abstract void reset$animation_core_release();

    public abstract void set$animation_core_release(int index, float value);

    private AnimationVector() {
    }
}
