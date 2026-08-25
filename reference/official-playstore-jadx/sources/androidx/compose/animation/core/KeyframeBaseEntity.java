package androidx.compose.animation.core;

import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.l;
import x6.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0019\b\u0004\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J9\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u00040\f\"\b\b\u0001\u0010\t*\u00020\b2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\nH\u0000¢\u0006\u0004\b\r\u0010\u000eR\u001a\u0010\u0003\u001a\u00028\u00008\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\"\u0010\u0005\u001a\u00020\u00048\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017\u0082\u0001\u0001\u0018¨\u0006\u0019"}, d2 = {"Landroidx/compose/animation/core/KeyframeBaseEntity;", "T", "", "value", "Landroidx/compose/animation/core/Easing;", "easing", "<init>", "(Ljava/lang/Object;Landroidx/compose/animation/core/Easing;)V", "Landroidx/compose/animation/core/AnimationVector;", "V", "Lkotlin/Function1;", "convertToVector", "Lx6/x;", "toPair$animation_core_release", "(Lr7/l;)Lx6/x;", "toPair", "Ljava/lang/Object;", "getValue$animation_core_release", "()Ljava/lang/Object;", "Landroidx/compose/animation/core/Easing;", "getEasing$animation_core_release", "()Landroidx/compose/animation/core/Easing;", "setEasing$animation_core_release", "(Landroidx/compose/animation/core/Easing;)V", "Landroidx/compose/animation/core/KeyframesSpec$KeyframeEntity;", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class KeyframeBaseEntity<T> {
    public static final int $stable = 8;
    private Easing easing;
    private final T value;

    public /* synthetic */ KeyframeBaseEntity(Object obj, Easing easing, h hVar) {
        this(obj, easing);
    }

    /* JADX INFO: renamed from: getEasing$animation_core_release, reason: from getter */
    public final Easing getEasing() {
        return this.easing;
    }

    public final T getValue$animation_core_release() {
        return this.value;
    }

    public final void setEasing$animation_core_release(Easing easing) {
        this.easing = easing;
    }

    public final <V extends AnimationVector> x toPair$animation_core_release(l<? super T, ? extends V> convertToVector) {
        return new x(convertToVector.invoke(this.value), this.easing);
    }

    private KeyframeBaseEntity(T t2, Easing easing) {
        this.value = t2;
        this.easing = easing;
    }
}
