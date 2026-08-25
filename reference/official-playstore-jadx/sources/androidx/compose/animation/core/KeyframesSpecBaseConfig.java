package androidx.compose.animation.core;

import androidx.compose.animation.core.KeyframeBaseEntity;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import t.m;
import t.v;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u000e\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u00028\u00000\u00022\u00020\u0004B\t\b\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\n\u001a\u00028\u00012\u0006\u0010\u0007\u001a\u00028\u0000H ¢\u0006\u0004\b\b\u0010\tJ\u001e\u0010\r\u001a\u00028\u0001*\u00028\u00002\b\b\u0001\u0010\f\u001a\u00020\u000bH\u0096\u0004¢\u0006\u0004\b\r\u0010\u000eJ\u001c\u0010\u0011\u001a\u00028\u0001*\u00028\u00002\u0006\u0010\u0010\u001a\u00020\u000fH\u0096\u0004¢\u0006\u0004\b\u0011\u0010\u0012J\u001c\u0010\u0015\u001a\u00028\u0001*\u00028\u00012\u0006\u0010\u0014\u001a\u00020\u0013H\u0086\u0004¢\u0006\u0004\b\u0015\u0010\u0016R\"\u0010\u0017\u001a\u00020\u000b8G@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\"\u0010\u001d\u001a\u00020\u000b8G@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\u0018\u001a\u0004\b\u001e\u0010\u001a\"\u0004\b\u001f\u0010\u001cR \u0010!\u001a\b\u0012\u0004\u0012\u00028\u00010 8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$\u0082\u0001\u0002%&¨\u0006'"}, d2 = {"Landroidx/compose/animation/core/KeyframesSpecBaseConfig;", "T", "Landroidx/compose/animation/core/KeyframeBaseEntity;", "E", "", "<init>", "()V", "value", "createEntityFor$animation_core_release", "(Ljava/lang/Object;)Landroidx/compose/animation/core/KeyframeBaseEntity;", "createEntityFor", "", "timeStamp", "at", "(Ljava/lang/Object;I)Landroidx/compose/animation/core/KeyframeBaseEntity;", "", "fraction", "atFraction", "(Ljava/lang/Object;F)Landroidx/compose/animation/core/KeyframeBaseEntity;", "Landroidx/compose/animation/core/Easing;", "easing", "using", "(Landroidx/compose/animation/core/KeyframeBaseEntity;Landroidx/compose/animation/core/Easing;)Landroidx/compose/animation/core/KeyframeBaseEntity;", "durationMillis", "I", "getDurationMillis", "()I", "setDurationMillis", "(I)V", "delayMillis", "getDelayMillis", "setDelayMillis", "Lt/v;", "keyframes", "Lt/v;", "getKeyframes$animation_core_release", "()Lt/v;", "Landroidx/compose/animation/core/KeyframesSpec$KeyframesSpecConfig;", "Landroidx/compose/animation/core/KeyframesWithSplineSpec$KeyframesWithSplineSpecConfig;", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class KeyframesSpecBaseConfig<T, E extends KeyframeBaseEntity<T>> {
    public static final int $stable = 8;
    private int delayMillis;
    private int durationMillis;
    private final v keyframes;

    public /* synthetic */ KeyframesSpecBaseConfig(h hVar) {
        this();
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public E at(T t2, int i10) {
        E e5 = (E) createEntityFor$animation_core_release(t2);
        this.keyframes.f(i10, e5);
        return e5;
    }

    public E atFraction(T t2, float f10) {
        return (E) at(t2, t7.a.M(this.durationMillis * f10));
    }

    public abstract E createEntityFor$animation_core_release(T value);

    public final int getDelayMillis() {
        return this.delayMillis;
    }

    public final int getDurationMillis() {
        return this.durationMillis;
    }

    /* JADX INFO: renamed from: getKeyframes$animation_core_release, reason: from getter */
    public final v getKeyframes() {
        return this.keyframes;
    }

    public final void setDelayMillis(int i10) {
        this.delayMillis = i10;
    }

    public final void setDurationMillis(int i10) {
        this.durationMillis = i10;
    }

    public final E using(E e5, Easing easing) {
        e5.setEasing$animation_core_release(easing);
        return e5;
    }

    private KeyframesSpecBaseConfig() {
        this.durationMillis = 300;
        int i10 = m.f21850a;
        this.keyframes = new v();
    }
}
