package androidx.compose.animation.core;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotLongStateKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import io.ktor.http.ContentDisposition;
import java.util.List;
import ka.k0;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010 \n\u0002\b\n\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0004tuvwB#\b\u0001\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bB\u001b\b\u0010\u0012\u0006\u0010\t\u001a\u00028\u0000\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\nB#\b\u0011\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\fJ\u001f\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\rH\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u001a\u001a\u00020\u0011H\u0000¢\u0006\u0004\b\u0018\u0010\u0019J'\u0010\u001f\u001a\u00020\u00112\u0006\u0010\t\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00028\u00002\u0006\u0010\u001c\u001a\u00020\rH\u0007¢\u0006\u0004\b\u001d\u0010\u001eJ\u001b\u0010$\u001a\u00020!2\n\u0010 \u001a\u0006\u0012\u0002\b\u00030\u0000H\u0000¢\u0006\u0004\b\"\u0010#J\u001b\u0010&\u001a\u00020!2\n\u0010 \u001a\u0006\u0012\u0002\b\u00030\u0000H\u0000¢\u0006\u0004\b%\u0010#J)\u0010+\u001a\u00020!2\u0018\u0010(\u001a\u0014\u0012\u0002\b\u0003\u0012\u0002\b\u00030'R\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0000¢\u0006\u0004\b)\u0010*J)\u0010.\u001a\u00020\u00112\u0018\u0010(\u001a\u0014\u0012\u0002\b\u0003\u0012\u0002\b\u00030'R\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0000¢\u0006\u0004\b,\u0010-J\u0017\u00101\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00028\u0000H\u0001¢\u0006\u0004\b/\u00100J\u0017\u00103\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00028\u0000H\u0001¢\u0006\u0004\b2\u00100J\u000f\u00104\u001a\u00020\u0005H\u0016¢\u0006\u0004\b4\u00105J)\u0010.\u001a\u00020\u00112\u0018\u00107\u001a\u0014\u0012\u0002\b\u0003\u0012\u0002\b\u000306R\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0000¢\u0006\u0004\b,\u00108J\u000f\u00109\u001a\u00020\u0011H\u0002¢\u0006\u0004\b9\u0010\u0019R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010:R\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010;\u001a\u0004\b<\u00105R+\u0010\u001b\u001a\u00028\u00002\u0006\u0010=\u001a\u00028\u00008F@@X\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b>\u0010?\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR7\u0010J\u001a\b\u0012\u0004\u0012\u00028\u00000D2\f\u0010=\u001a\b\u0012\u0004\u0012\u00028\u00000D8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bE\u0010?\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR1\u0010\u001c\u001a\u00020\r2\u0006\u0010=\u001a\u00020\r8F@FX\u0087\u008e\u0002¢\u0006\u0018\n\u0004\bK\u0010L\u0012\u0004\bP\u0010\u0019\u001a\u0004\bM\u0010N\"\u0004\bO\u0010\u0016R+\u0010T\u001a\u00020\r2\u0006\u0010=\u001a\u00020\r8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bQ\u0010L\u001a\u0004\bR\u0010N\"\u0004\bS\u0010\u0016R+\u0010Z\u001a\u00020!2\u0006\u0010=\u001a\u00020!8@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bU\u0010?\u001a\u0004\bV\u0010W\"\u0004\bX\u0010YR,\u0010\\\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0002\b\u0003\u0012\u0002\b\u00030'R\b\u0012\u0004\u0012\u00028\u00000\u00000[8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\\\u0010]R\u001e\u0010^\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00000[8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b^\u0010]R1\u0010`\u001a\u00020!2\u0006\u0010=\u001a\u00020!8F@@X\u0087\u008e\u0002¢\u0006\u0018\n\u0004\b_\u0010?\u0012\u0004\bb\u0010\u0019\u001a\u0004\b`\u0010W\"\u0004\ba\u0010YR\"\u0010c\u001a\u00020\r8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bc\u0010d\u001a\u0004\be\u0010N\"\u0004\bf\u0010\u0016R\u001b\u0010j\u001a\u00020\r8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bg\u0010h\u001a\u0004\bi\u0010NR\u0011\u0010l\u001a\u00028\u00008F¢\u0006\u0006\u001a\u0004\bk\u0010AR\u0011\u0010m\u001a\u00020!8F¢\u0006\u0006\u001a\u0004\bm\u0010WR\u001b\u0010q\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00000n8F¢\u0006\u0006\u001a\u0004\bo\u0010pR)\u0010s\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0002\b\u0003\u0012\u0002\b\u00030'R\b\u0012\u0004\u0012\u00028\u00000\u00000n8F¢\u0006\u0006\u001a\u0004\br\u0010p¨\u0006x"}, d2 = {"Landroidx/compose/animation/core/Transition;", "S", "", "Landroidx/compose/animation/core/TransitionState;", "transitionState", "", "label", "<init>", "(Landroidx/compose/animation/core/TransitionState;Ljava/lang/String;)V", "initialState", "(Ljava/lang/Object;Ljava/lang/String;)V", "Landroidx/compose/animation/core/MutableTransitionState;", "(Landroidx/compose/animation/core/MutableTransitionState;Ljava/lang/String;)V", "", "frameTimeNanos", "", "durationScale", "Lx6/t0;", "onFrame$animation_core_release", "(JF)V", "onFrame", "onTransitionStart$animation_core_release", "(J)V", "onTransitionStart", "onTransitionEnd$animation_core_release", "()V", "onTransitionEnd", "targetState", "playTimeNanos", "seek", "(Ljava/lang/Object;Ljava/lang/Object;J)V", "setPlaytimeAfterInitialAndTargetStateEstablished", "transition", "", "addTransition$animation_core_release", "(Landroidx/compose/animation/core/Transition;)Z", "addTransition", "removeTransition$animation_core_release", "removeTransition", "Landroidx/compose/animation/core/Transition$TransitionAnimationState;", "animation", "addAnimation$animation_core_release", "(Landroidx/compose/animation/core/Transition$TransitionAnimationState;)Z", "addAnimation", "removeAnimation$animation_core_release", "(Landroidx/compose/animation/core/Transition$TransitionAnimationState;)V", "removeAnimation", "updateTarget$animation_core_release", "(Ljava/lang/Object;Landroidx/compose/runtime/Composer;I)V", "updateTarget", "animateTo$animation_core_release", "animateTo", "toString", "()Ljava/lang/String;", "Landroidx/compose/animation/core/Transition$DeferredAnimation;", "deferredAnimation", "(Landroidx/compose/animation/core/Transition$DeferredAnimation;)V", "onChildAnimationUpdated", "Landroidx/compose/animation/core/TransitionState;", "Ljava/lang/String;", "getLabel", "<set-?>", "targetState$delegate", "Landroidx/compose/runtime/MutableState;", "getTargetState", "()Ljava/lang/Object;", "setTargetState$animation_core_release", "(Ljava/lang/Object;)V", "Landroidx/compose/animation/core/Transition$Segment;", "segment$delegate", "getSegment", "()Landroidx/compose/animation/core/Transition$Segment;", "setSegment", "(Landroidx/compose/animation/core/Transition$Segment;)V", "segment", "playTimeNanos$delegate", "Landroidx/compose/runtime/MutableLongState;", "getPlayTimeNanos", "()J", "setPlayTimeNanos", "getPlayTimeNanos$annotations", "startTimeNanos$delegate", "getStartTimeNanos", "setStartTimeNanos", "startTimeNanos", "updateChildrenNeeded$delegate", "getUpdateChildrenNeeded$animation_core_release", "()Z", "setUpdateChildrenNeeded$animation_core_release", "(Z)V", "updateChildrenNeeded", "Landroidx/compose/runtime/snapshots/SnapshotStateList;", "_animations", "Landroidx/compose/runtime/snapshots/SnapshotStateList;", "_transitions", "isSeeking$delegate", "isSeeking", "setSeeking$animation_core_release", "isSeeking$annotations", "lastSeekedTimeNanos", "J", "getLastSeekedTimeNanos$animation_core_release", "setLastSeekedTimeNanos$animation_core_release", "totalDurationNanos$delegate", "Landroidx/compose/runtime/State;", "getTotalDurationNanos", "totalDurationNanos", "getCurrentState", "currentState", "isRunning", "", "getTransitions", "()Ljava/util/List;", "transitions", "getAnimations", "animations", "DeferredAnimation", "Segment", "SegmentImpl", "TransitionAnimationState", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Transition<S> {
    public static final int $stable = 0;
    private final SnapshotStateList<Transition<S>.TransitionAnimationState<?, ?>> _animations;
    private final SnapshotStateList<Transition<?>> _transitions;

    /* JADX INFO: renamed from: isSeeking$delegate, reason: from kotlin metadata */
    private final MutableState isSeeking;
    private final String label;
    private long lastSeekedTimeNanos;

    /* JADX INFO: renamed from: playTimeNanos$delegate, reason: from kotlin metadata */
    private final MutableLongState playTimeNanos;

    /* JADX INFO: renamed from: segment$delegate, reason: from kotlin metadata */
    private final MutableState segment;

    /* JADX INFO: renamed from: startTimeNanos$delegate, reason: from kotlin metadata */
    private final MutableLongState startTimeNanos;

    /* JADX INFO: renamed from: targetState$delegate, reason: from kotlin metadata */
    private final MutableState targetState;

    /* JADX INFO: renamed from: totalDurationNanos$delegate, reason: from kotlin metadata */
    private final State totalDurationNanos;
    private final TransitionState<S> transitionState;

    /* JADX INFO: renamed from: updateChildrenNeeded$delegate, reason: from kotlin metadata */
    private final MutableState updateChildrenNeeded;

    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0087\u0004\u0018\u0000*\u0004\b\u0001\u0010\u0001*\b\b\u0002\u0010\u0003*\u00020\u00022\u00020\u0004:\u0001+B%\b\u0000\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ[\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00010\u00142#\u0010\u000f\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\r0\u000b¢\u0006\u0002\b\u000e2!\u0010\u0013\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00028\u00010\u000b¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u001a\u001a\u00020\u0017H\u0000¢\u0006\u0004\b\u0018\u0010\u0019R#\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\u001e\u001a\u0004\b\u001f\u0010 R{\u0010*\u001a*\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0018\u00010!R\u0018\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0000R\b\u0012\u0004\u0012\u00028\u00000\"2.\u0010#\u001a*\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0018\u00010!R\u0018\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0000R\b\u0012\u0004\u0012\u00028\u00000\"8@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)¨\u0006,"}, d2 = {"Landroidx/compose/animation/core/Transition$DeferredAnimation;", "T", "Landroidx/compose/animation/core/AnimationVector;", "V", "", "Landroidx/compose/animation/core/TwoWayConverter;", "typeConverter", "", "label", "<init>", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/core/TwoWayConverter;Ljava/lang/String;)V", "Lkotlin/Function1;", "Landroidx/compose/animation/core/Transition$Segment;", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "Lx6/n;", "transitionSpec", "Lx6/y;", ContentDisposition.Parameters.Name, "state", "targetValueByState", "Landroidx/compose/runtime/State;", "animate", "(Lr7/l;Lr7/l;)Landroidx/compose/runtime/State;", "Lx6/t0;", "setupSeeking$animation_core_release", "()V", "setupSeeking", "Landroidx/compose/animation/core/TwoWayConverter;", "getTypeConverter", "()Landroidx/compose/animation/core/TwoWayConverter;", "Ljava/lang/String;", "getLabel", "()Ljava/lang/String;", "Landroidx/compose/animation/core/Transition$DeferredAnimation$DeferredAnimationData;", "Landroidx/compose/animation/core/Transition;", "<set-?>", "data$delegate", "Landroidx/compose/runtime/MutableState;", "getData$animation_core_release", "()Landroidx/compose/animation/core/Transition$DeferredAnimation$DeferredAnimationData;", "setData$animation_core_release", "(Landroidx/compose/animation/core/Transition$DeferredAnimation$DeferredAnimationData;)V", "data", "DeferredAnimationData", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public final class DeferredAnimation<T, V extends AnimationVector> {

        /* JADX INFO: renamed from: data$delegate, reason: from kotlin metadata */
        private final MutableState data = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        private final String label;
        private final TwoWayConverter<T, V> typeConverter;

        @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0080\u0004\u0018\u0000*\u0004\b\u0003\u0010\u0001*\b\b\u0004\u0010\u0003*\u00020\u00022\b\u0012\u0004\u0012\u00028\u00030\u0004Bm\u0012\u001c\u0010\u0007\u001a\u0018\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u00040\u0005R\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012#\u0010\f\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\t\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00030\n0\b¢\u0006\u0002\b\u000b\u0012!\u0010\u0010\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00028\u00030\b¢\u0006\u0004\b\u0011\u0010\u0012J\u001b\u0010\u0015\u001a\u00020\u00142\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\t¢\u0006\u0004\b\u0015\u0010\u0016R-\u0010\u0007\u001a\u0018\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u00040\u0005R\b\u0012\u0004\u0012\u00028\u00000\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R?\u0010\f\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\t\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00030\n0\b¢\u0006\u0002\b\u000b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\f\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR=\u0010\u0010\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00028\u00030\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010\u001a\u001a\u0004\b\u001f\u0010\u001c\"\u0004\b \u0010\u001eR\u0014\u0010#\u001a\u00028\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"¨\u0006$"}, d2 = {"Landroidx/compose/animation/core/Transition$DeferredAnimation$DeferredAnimationData;", "T", "Landroidx/compose/animation/core/AnimationVector;", "V", "Landroidx/compose/runtime/State;", "Landroidx/compose/animation/core/Transition$TransitionAnimationState;", "Landroidx/compose/animation/core/Transition;", "animation", "Lkotlin/Function1;", "Landroidx/compose/animation/core/Transition$Segment;", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "Lx6/n;", "transitionSpec", "Lx6/y;", ContentDisposition.Parameters.Name, "state", "targetValueByState", "<init>", "(Landroidx/compose/animation/core/Transition$DeferredAnimation;Landroidx/compose/animation/core/Transition$TransitionAnimationState;Lr7/l;Lr7/l;)V", "segment", "Lx6/t0;", "updateAnimationStates", "(Landroidx/compose/animation/core/Transition$Segment;)V", "Landroidx/compose/animation/core/Transition$TransitionAnimationState;", "getAnimation", "()Landroidx/compose/animation/core/Transition$TransitionAnimationState;", "Lr7/l;", "getTransitionSpec", "()Lr7/l;", "setTransitionSpec", "(Lr7/l;)V", "getTargetValueByState", "setTargetValueByState", "getValue", "()Ljava/lang/Object;", "value", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public final class DeferredAnimationData<T, V extends AnimationVector> implements State<T> {
            private final Transition<S>.TransitionAnimationState<T, V> animation;
            private l<? super S, ? extends T> targetValueByState;
            private l<? super Segment<S>, ? extends FiniteAnimationSpec<T>> transitionSpec;

            public DeferredAnimationData(Transition<S>.TransitionAnimationState<T, V> transitionAnimationState, l<? super Segment<S>, ? extends FiniteAnimationSpec<T>> lVar, l<? super S, ? extends T> lVar2) {
                this.animation = transitionAnimationState;
                this.transitionSpec = lVar;
                this.targetValueByState = lVar2;
            }

            public final Transition<S>.TransitionAnimationState<T, V> getAnimation() {
                return this.animation;
            }

            public final l<S, T> getTargetValueByState() {
                return this.targetValueByState;
            }

            public final l<Segment<S>, FiniteAnimationSpec<T>> getTransitionSpec() {
                return this.transitionSpec;
            }

            @Override // androidx.compose.runtime.State
            public T getValue() {
                updateAnimationStates(Transition.this.getSegment());
                return this.animation.getValue();
            }

            public final void setTargetValueByState(l<? super S, ? extends T> lVar) {
                this.targetValueByState = lVar;
            }

            public final void setTransitionSpec(l<? super Segment<S>, ? extends FiniteAnimationSpec<T>> lVar) {
                this.transitionSpec = lVar;
            }

            public final void updateAnimationStates(Segment<S> segment) {
                Object objInvoke = this.targetValueByState.invoke(segment.getTargetState());
                if (!Transition.this.isSeeking()) {
                    this.animation.updateTargetValue$animation_core_release((T) objInvoke, (FiniteAnimationSpec) this.transitionSpec.invoke(segment));
                } else {
                    this.animation.updateInitialAndTargetValue$animation_core_release((T) this.targetValueByState.invoke(segment.getInitialState()), (T) objInvoke, (FiniteAnimationSpec) this.transitionSpec.invoke(segment));
                }
            }
        }

        public DeferredAnimation(TwoWayConverter<T, V> twoWayConverter, String str) {
            this.typeConverter = twoWayConverter;
            this.label = str;
        }

        public final State<T> animate(l<? super Segment<S>, ? extends FiniteAnimationSpec<T>> transitionSpec, l<? super S, ? extends T> targetValueByState) {
            Transition<S>.DeferredAnimationData<T, V>.DeferredAnimationData<T, V> data$animation_core_release = getData$animation_core_release();
            if (data$animation_core_release == null) {
                Transition<S> transition = Transition.this;
                data$animation_core_release = new DeferredAnimationData<>(transition.new TransitionAnimationState(targetValueByState.invoke(transition.getCurrentState()), AnimationStateKt.createZeroVectorFrom(this.typeConverter, targetValueByState.invoke(Transition.this.getCurrentState())), this.typeConverter, this.label), transitionSpec, targetValueByState);
                Transition<S> transition2 = Transition.this;
                setData$animation_core_release(data$animation_core_release);
                transition2.addAnimation$animation_core_release(data$animation_core_release.getAnimation());
            }
            Transition<S> transition3 = Transition.this;
            data$animation_core_release.setTargetValueByState(targetValueByState);
            data$animation_core_release.setTransitionSpec(transitionSpec);
            data$animation_core_release.updateAnimationStates(transition3.getSegment());
            return data$animation_core_release;
        }

        public final Transition<S>.DeferredAnimationData<T, V>.DeferredAnimationData<T, V> getData$animation_core_release() {
            return (DeferredAnimationData) this.data.getValue();
        }

        public final String getLabel() {
            return this.label;
        }

        public final TwoWayConverter<T, V> getTypeConverter() {
            return this.typeConverter;
        }

        public final void setData$animation_core_release(Transition<S>.DeferredAnimationData<T, V>.DeferredAnimationData<T, V> deferredAnimationData) {
            this.data.setValue(deferredAnimationData);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void setupSeeking$animation_core_release() {
            Transition<S>.DeferredAnimationData<T, V>.DeferredAnimationData<T, V> data$animation_core_release = getData$animation_core_release();
            if (data$animation_core_release != null) {
                Transition<S> transition = Transition.this;
                data$animation_core_release.getAnimation().updateInitialAndTargetValue$animation_core_release(data$animation_core_release.getTargetValueByState().invoke(transition.getSegment().getInitialState()), data$animation_core_release.getTargetValueByState().invoke(transition.getSegment().getTargetState()), (FiniteAnimationSpec) data$animation_core_release.getTransitionSpec().invoke(transition.getSegment()));
            }
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002J\u001a\u0010\b\u001a\u00020\t*\u00028\u00012\u0006\u0010\u0006\u001a\u00028\u0001H\u0096\u0004¢\u0006\u0002\u0010\nR\u0012\u0010\u0003\u001a\u00028\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00028\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0003"}, d2 = {"Landroidx/compose/animation/core/Transition$Segment;", "S", "", "initialState", "getInitialState", "()Ljava/lang/Object;", "targetState", "getTargetState", "isTransitioningTo", "", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface Segment<S> {

        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        public static final class DefaultImpls {
            @Deprecated
            public static <S> boolean isTransitioningTo(Segment<S> segment, S s10, S s11) {
                return d.a(segment, s10, s11);
            }
        }

        S getInitialState();

        S getTargetState();

        boolean isTransitioningTo(S s10, S s11);
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00028\u0001\u0012\u0006\u0010\u0004\u001a\u00028\u0001¢\u0006\u0002\u0010\u0005J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0096\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u0016\u0010\u0003\u001a\u00028\u0001X\u0096\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\u0004\u001a\u00028\u0001X\u0096\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\t\u0010\u0007¨\u0006\u0010"}, d2 = {"Landroidx/compose/animation/core/Transition$SegmentImpl;", "S", "Landroidx/compose/animation/core/Transition$Segment;", "initialState", "targetState", "(Ljava/lang/Object;Ljava/lang/Object;)V", "getInitialState", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getTargetState", "equals", "", "other", "", "hashCode", "", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class SegmentImpl<S> implements Segment<S> {
        private final S initialState;
        private final S targetState;

        public SegmentImpl(S s10, S s11) {
            this.initialState = s10;
            this.targetState = s11;
        }

        public boolean equals(Object other) {
            if (!(other instanceof Segment)) {
                return false;
            }
            Segment segment = (Segment) other;
            return p.a(getInitialState(), segment.getInitialState()) && p.a(getTargetState(), segment.getTargetState());
        }

        @Override // androidx.compose.animation.core.Transition.Segment
        public S getInitialState() {
            return this.initialState;
        }

        @Override // androidx.compose.animation.core.Transition.Segment
        public S getTargetState() {
            return this.targetState;
        }

        public int hashCode() {
            S initialState = getInitialState();
            int iHashCode = (initialState != null ? initialState.hashCode() : 0) * 31;
            S targetState = getTargetState();
            return iHashCode + (targetState != null ? targetState.hashCode() : 0);
        }

        @Override // androidx.compose.animation.core.Transition.Segment
        public final /* synthetic */ boolean isTransitioningTo(Object obj, Object obj2) {
            return d.a(this, obj, obj2);
        }
    }

    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b!\b\u0087\u0004\u0018\u0000*\u0004\b\u0001\u0010\u0001*\b\b\u0002\u0010\u0003*\u00020\u00022\b\u0012\u0004\u0012\u00028\u00010\u0004B5\b\u0000\u0012\u0006\u0010\u0005\u001a\u00028\u0001\u0012\u0006\u0010\u0006\u001a\u00028\u0002\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ#\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0005\u001a\u00028\u00012\b\b\u0002\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u001f\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0014H\u0000¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0012H\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001e\u001a\u00020\u000fH\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001f\u001a\u00020\tH\u0016¢\u0006\u0004\b\u001f\u0010 J%\u0010&\u001a\u00020\u000f2\u0006\u0010!\u001a\u00028\u00012\f\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00010\"H\u0000¢\u0006\u0004\b$\u0010%J-\u0010)\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00028\u00012\u0006\u0010!\u001a\u00028\u00012\f\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00010\"H\u0000¢\u0006\u0004\b'\u0010(R#\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010*\u001a\u0004\b+\u0010,R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010-\u001a\u0004\b.\u0010 R+\u0010!\u001a\u00028\u00012\u0006\u0010/\u001a\u00028\u00018B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b0\u00101\u001a\u0004\b2\u00103\"\u0004\b4\u00105R7\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00010\"2\f\u0010/\u001a\b\u0012\u0004\u0012\u00028\u00010\"8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b6\u00101\u001a\u0004\b7\u00108\"\u0004\b9\u0010:RC\u0010A\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020;2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020;8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b<\u00101\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R+\u0010G\u001a\u00020\r2\u0006\u0010/\u001a\u00020\r8@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bB\u00101\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR+\u0010M\u001a\u00020\u00122\u0006\u0010/\u001a\u00020\u00128B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bH\u0010I\u001a\u0004\bJ\u0010K\"\u0004\bL\u0010\u001aR+\u0010Q\u001a\u00020\r2\u0006\u0010/\u001a\u00020\r8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bN\u00101\u001a\u0004\bO\u0010D\"\u0004\bP\u0010FR+\u0010U\u001a\u00028\u00012\u0006\u0010/\u001a\u00028\u00018V@PX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\bR\u00101\u001a\u0004\bS\u00103\"\u0004\bT\u00105R\u0016\u0010V\u001a\u00028\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bV\u0010WR\u001a\u0010X\u001a\b\u0012\u0004\u0012\u00028\u00010\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bX\u0010YR\u0014\u0010[\u001a\u00020\u00128@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bZ\u0010K¨\u0006\\"}, d2 = {"Landroidx/compose/animation/core/Transition$TransitionAnimationState;", "T", "Landroidx/compose/animation/core/AnimationVector;", "V", "Landroidx/compose/runtime/State;", "initialValue", "initialVelocityVector", "Landroidx/compose/animation/core/TwoWayConverter;", "typeConverter", "", "label", "<init>", "(Landroidx/compose/animation/core/Transition;Ljava/lang/Object;Landroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/TwoWayConverter;Ljava/lang/String;)V", "", "isInterrupted", "Lx6/t0;", "updateAnimation", "(Ljava/lang/Object;Z)V", "", "playTimeNanos", "", "durationScale", "onPlayTimeChanged$animation_core_release", "(JF)V", "onPlayTimeChanged", "seekTo$animation_core_release", "(J)V", "seekTo", "resetAnimation$animation_core_release", "()V", "resetAnimation", "toString", "()Ljava/lang/String;", "targetValue", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "animationSpec", "updateTargetValue$animation_core_release", "(Ljava/lang/Object;Landroidx/compose/animation/core/FiniteAnimationSpec;)V", "updateTargetValue", "updateInitialAndTargetValue$animation_core_release", "(Ljava/lang/Object;Ljava/lang/Object;Landroidx/compose/animation/core/FiniteAnimationSpec;)V", "updateInitialAndTargetValue", "Landroidx/compose/animation/core/TwoWayConverter;", "getTypeConverter", "()Landroidx/compose/animation/core/TwoWayConverter;", "Ljava/lang/String;", "getLabel", "<set-?>", "targetValue$delegate", "Landroidx/compose/runtime/MutableState;", "getTargetValue", "()Ljava/lang/Object;", "setTargetValue", "(Ljava/lang/Object;)V", "animationSpec$delegate", "getAnimationSpec", "()Landroidx/compose/animation/core/FiniteAnimationSpec;", "setAnimationSpec", "(Landroidx/compose/animation/core/FiniteAnimationSpec;)V", "Landroidx/compose/animation/core/TargetBasedAnimation;", "animation$delegate", "getAnimation", "()Landroidx/compose/animation/core/TargetBasedAnimation;", "setAnimation", "(Landroidx/compose/animation/core/TargetBasedAnimation;)V", "animation", "isFinished$delegate", "isFinished$animation_core_release", "()Z", "setFinished$animation_core_release", "(Z)V", "isFinished", "offsetTimeNanos$delegate", "Landroidx/compose/runtime/MutableLongState;", "getOffsetTimeNanos", "()J", "setOffsetTimeNanos", "offsetTimeNanos", "needsReset$delegate", "getNeedsReset", "setNeedsReset", "needsReset", "value$delegate", "getValue", "setValue$animation_core_release", "value", "velocityVector", "Landroidx/compose/animation/core/AnimationVector;", "interruptionSpec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "getDurationNanos$animation_core_release", "durationNanos", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public final class TransitionAnimationState<T, V extends AnimationVector> implements State<T> {

        /* JADX INFO: renamed from: animation$delegate, reason: from kotlin metadata */
        private final MutableState animation;
        private final FiniteAnimationSpec<T> interruptionSpec;
        private final String label;

        /* JADX INFO: renamed from: targetValue$delegate, reason: from kotlin metadata */
        private final MutableState targetValue;
        private final TwoWayConverter<T, V> typeConverter;

        /* JADX INFO: renamed from: value$delegate, reason: from kotlin metadata */
        private final MutableState value;
        private V velocityVector;

        /* JADX INFO: renamed from: animationSpec$delegate, reason: from kotlin metadata */
        private final MutableState animationSpec = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null), null, 2, null);

        /* JADX INFO: renamed from: isFinished$delegate, reason: from kotlin metadata */
        private final MutableState isFinished = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.TRUE, null, 2, null);

        /* JADX INFO: renamed from: offsetTimeNanos$delegate, reason: from kotlin metadata */
        private final MutableLongState offsetTimeNanos = SnapshotLongStateKt.mutableLongStateOf(0);

        /* JADX INFO: renamed from: needsReset$delegate, reason: from kotlin metadata */
        private final MutableState needsReset = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);

        public TransitionAnimationState(T t2, V v2, TwoWayConverter<T, V> twoWayConverter, String str) {
            Object objInvoke;
            this.typeConverter = twoWayConverter;
            this.label = str;
            this.targetValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(t2, null, 2, null);
            this.animation = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new TargetBasedAnimation(getAnimationSpec(), twoWayConverter, t2, getTargetValue(), v2), null, 2, null);
            this.value = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(t2, null, 2, null);
            this.velocityVector = v2;
            Float f10 = VisibilityThresholdsKt.getVisibilityThresholdMap().get(twoWayConverter);
            if (f10 != null) {
                float fFloatValue = f10.floatValue();
                AnimationVector animationVector = (AnimationVector) twoWayConverter.getConvertToVector().invoke(t2);
                int size = animationVector.getSize();
                for (int i10 = 0; i10 < size; i10++) {
                    animationVector.set$animation_core_release(i10, fFloatValue);
                }
                objInvoke = this.typeConverter.getConvertFromVector().invoke(animationVector);
            } else {
                objInvoke = null;
            }
            this.interruptionSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, objInvoke, 3, null);
        }

        private final boolean getNeedsReset() {
            return ((Boolean) this.needsReset.getValue()).booleanValue();
        }

        private final long getOffsetTimeNanos() {
            return this.offsetTimeNanos.getLongValue();
        }

        private final T getTargetValue() {
            return this.targetValue.getValue();
        }

        private final void setAnimation(TargetBasedAnimation<T, V> targetBasedAnimation) {
            this.animation.setValue(targetBasedAnimation);
        }

        private final void setAnimationSpec(FiniteAnimationSpec<T> finiteAnimationSpec) {
            this.animationSpec.setValue(finiteAnimationSpec);
        }

        private final void setNeedsReset(boolean z) {
            this.needsReset.setValue(Boolean.valueOf(z));
        }

        private final void setOffsetTimeNanos(long j10) {
            this.offsetTimeNanos.setLongValue(j10);
        }

        private final void setTargetValue(T t2) {
            this.targetValue.setValue(t2);
        }

        private final void updateAnimation(T initialValue, boolean isInterrupted) {
            FiniteAnimationSpec<T> animationSpec = (!isInterrupted || (getAnimationSpec() instanceof SpringSpec)) ? getAnimationSpec() : this.interruptionSpec;
            setAnimation(new TargetBasedAnimation<>(animationSpec, this.typeConverter, initialValue, getTargetValue(), this.velocityVector));
            Transition.this.onChildAnimationUpdated();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void updateAnimation$default(TransitionAnimationState transitionAnimationState, Object obj, boolean z, int i10, Object obj2) {
            if ((i10 & 1) != 0) {
                obj = transitionAnimationState.getValue();
            }
            if ((i10 & 2) != 0) {
                z = false;
            }
            transitionAnimationState.updateAnimation(obj, z);
        }

        public final TargetBasedAnimation<T, V> getAnimation() {
            return (TargetBasedAnimation) this.animation.getValue();
        }

        public final FiniteAnimationSpec<T> getAnimationSpec() {
            return (FiniteAnimationSpec) this.animationSpec.getValue();
        }

        public final long getDurationNanos$animation_core_release() {
            return getAnimation().getDurationNanos();
        }

        public final String getLabel() {
            return this.label;
        }

        public final TwoWayConverter<T, V> getTypeConverter() {
            return this.typeConverter;
        }

        @Override // androidx.compose.runtime.State
        public T getValue() {
            return this.value.getValue();
        }

        public final boolean isFinished$animation_core_release() {
            return ((Boolean) this.isFinished.getValue()).booleanValue();
        }

        public final void onPlayTimeChanged$animation_core_release(long playTimeNanos, float durationScale) {
            long durationNanos;
            if (durationScale > 0.0f) {
                float offsetTimeNanos = (playTimeNanos - getOffsetTimeNanos()) / durationScale;
                if (Float.isNaN(offsetTimeNanos)) {
                    throw new IllegalStateException(("Duration scale adjusted time is NaN. Duration scale: " + durationScale + ",playTimeNanos: " + playTimeNanos + ", offsetTimeNanos: " + getOffsetTimeNanos()).toString());
                }
                durationNanos = (long) offsetTimeNanos;
            } else {
                durationNanos = getAnimation().getDurationNanos();
            }
            setValue$animation_core_release(getAnimation().getValueFromNanos(durationNanos));
            this.velocityVector = (V) getAnimation().getVelocityVectorFromNanos(durationNanos);
            TargetBasedAnimation<T, V> animation = getAnimation();
            animation.getClass();
            if (a.a(animation, durationNanos)) {
                setFinished$animation_core_release(true);
                setOffsetTimeNanos(0L);
            }
        }

        public final void resetAnimation$animation_core_release() {
            setNeedsReset(true);
        }

        public final void seekTo$animation_core_release(long playTimeNanos) {
            setValue$animation_core_release(getAnimation().getValueFromNanos(playTimeNanos));
            this.velocityVector = (V) getAnimation().getVelocityVectorFromNanos(playTimeNanos);
        }

        public final void setFinished$animation_core_release(boolean z) {
            this.isFinished.setValue(Boolean.valueOf(z));
        }

        public void setValue$animation_core_release(T t2) {
            this.value.setValue(t2);
        }

        public String toString() {
            return "current value: " + getValue() + ", target: " + getTargetValue() + ", spec: " + getAnimationSpec();
        }

        public final void updateInitialAndTargetValue$animation_core_release(T initialValue, T targetValue, FiniteAnimationSpec<T> animationSpec) {
            setTargetValue(targetValue);
            setAnimationSpec(animationSpec);
            if (p.a(getAnimation().getInitialValue(), initialValue) && p.a(getAnimation().getTargetValue(), targetValue)) {
                return;
            }
            updateAnimation$default(this, initialValue, false, 2, null);
        }

        public final void updateTargetValue$animation_core_release(T targetValue, FiniteAnimationSpec<T> animationSpec) {
            if (!p.a(getTargetValue(), targetValue) || getNeedsReset()) {
                setTargetValue(targetValue);
                setAnimationSpec(animationSpec);
                updateAnimation$default(this, null, !isFinished$animation_core_release(), 1, null);
                setFinished$animation_core_release(false);
                setOffsetTimeNanos(Transition.this.getPlayTimeNanos());
                setNeedsReset(false);
            }
        }
    }

    public Transition(TransitionState<S> transitionState, String str) {
        this.transitionState = transitionState;
        this.label = str;
        this.targetState = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(getCurrentState(), null, 2, null);
        this.segment = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new SegmentImpl(getCurrentState(), getCurrentState()), null, 2, null);
        this.playTimeNanos = SnapshotLongStateKt.mutableLongStateOf(0L);
        this.startTimeNanos = SnapshotLongStateKt.mutableLongStateOf(Long.MIN_VALUE);
        this.updateChildrenNeeded = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.TRUE, null, 2, null);
        this._animations = SnapshotStateKt.mutableStateListOf();
        this._transitions = SnapshotStateKt.mutableStateListOf();
        this.isSeeking = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
        this.totalDurationNanos = SnapshotStateKt.derivedStateOf(new Transition$totalDurationNanos$2(this));
        transitionState.transitionConfigured$animation_core_release(this);
    }

    public static /* synthetic */ void getPlayTimeNanos$annotations() {
    }

    private final long getStartTimeNanos() {
        return this.startTimeNanos.getLongValue();
    }

    public static /* synthetic */ void isSeeking$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onChildAnimationUpdated() {
        setUpdateChildrenNeeded$animation_core_release(true);
        if (isSeeking()) {
            SnapshotStateList<Transition<S>.TransitionAnimationState<?, ?>> snapshotStateList = this._animations;
            int size = snapshotStateList.size();
            long jMax = 0;
            for (int i10 = 0; i10 < size; i10++) {
                Transition<S>.TransitionAnimationState<?, ?> transitionAnimationState = snapshotStateList.get(i10);
                jMax = Math.max(jMax, transitionAnimationState.getDurationNanos$animation_core_release());
                transitionAnimationState.seekTo$animation_core_release(this.lastSeekedTimeNanos);
            }
            setUpdateChildrenNeeded$animation_core_release(false);
        }
    }

    private final void setSegment(Segment<S> segment) {
        this.segment.setValue(segment);
    }

    private final void setStartTimeNanos(long j10) {
        this.startTimeNanos.setLongValue(j10);
    }

    public final boolean addAnimation$animation_core_release(Transition<S>.TransitionAnimationState<?, ?> animation) {
        return this._animations.add(animation);
    }

    public final boolean addTransition$animation_core_release(Transition<?> transition) {
        return this._transitions.add(transition);
    }

    public final void animateTo$animation_core_release(S s10, Composer composer, int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1493585151);
        if ((i10 & 14) == 0) {
            i11 = (composerStartRestartGroup.changed(s10) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 112) == 0) {
            i11 |= composerStartRestartGroup.changed(this) ? 32 : 16;
        }
        if ((i11 & 91) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1493585151, i11, -1, "androidx.compose.animation.core.Transition.animateTo (Transition.kt:639)");
            }
            if (!isSeeking()) {
                updateTarget$animation_core_release(s10, composerStartRestartGroup, i11 & 126);
                if (!p.a(s10, getCurrentState()) || isRunning() || getUpdateChildrenNeeded$animation_core_release()) {
                    composerStartRestartGroup.startReplaceableGroup(1951115890);
                    boolean zChanged = composerStartRestartGroup.changed(this);
                    Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Transition$animateTo$1$1(this, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composerStartRestartGroup.endReplaceableGroup();
                    EffectsKt.LaunchedEffect(this, (r7.p<? super k0, ? super d7.d<? super t0>, ? extends Object>) objRememberedValue, composerStartRestartGroup, ((i11 >> 3) & 14) | 64);
                }
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Transition$animateTo$2(this, s10, i10));
        }
    }

    public final List<Transition<S>.TransitionAnimationState<?, ?>> getAnimations() {
        return this._animations;
    }

    public final S getCurrentState() {
        return this.transitionState.getCurrentState();
    }

    public final String getLabel() {
        return this.label;
    }

    /* JADX INFO: renamed from: getLastSeekedTimeNanos$animation_core_release, reason: from getter */
    public final long getLastSeekedTimeNanos() {
        return this.lastSeekedTimeNanos;
    }

    public final long getPlayTimeNanos() {
        return this.playTimeNanos.getLongValue();
    }

    public final Segment<S> getSegment() {
        return (Segment) this.segment.getValue();
    }

    public final S getTargetState() {
        return (S) this.targetState.getValue();
    }

    public final long getTotalDurationNanos() {
        return ((Number) this.totalDurationNanos.getValue()).longValue();
    }

    public final List<Transition<?>> getTransitions() {
        return this._transitions;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean getUpdateChildrenNeeded$animation_core_release() {
        return ((Boolean) this.updateChildrenNeeded.getValue()).booleanValue();
    }

    public final boolean isRunning() {
        return getStartTimeNanos() != Long.MIN_VALUE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isSeeking() {
        return ((Boolean) this.isSeeking.getValue()).booleanValue();
    }

    public final void onFrame$animation_core_release(long frameTimeNanos, float durationScale) {
        if (getStartTimeNanos() == Long.MIN_VALUE) {
            onTransitionStart$animation_core_release(frameTimeNanos);
        }
        setUpdateChildrenNeeded$animation_core_release(false);
        setPlayTimeNanos(frameTimeNanos - getStartTimeNanos());
        SnapshotStateList<Transition<S>.TransitionAnimationState<?, ?>> snapshotStateList = this._animations;
        int size = snapshotStateList.size();
        boolean z = true;
        for (int i10 = 0; i10 < size; i10++) {
            Transition<S>.TransitionAnimationState<?, ?> transitionAnimationState = snapshotStateList.get(i10);
            if (!transitionAnimationState.isFinished$animation_core_release()) {
                transitionAnimationState.onPlayTimeChanged$animation_core_release(getPlayTimeNanos(), durationScale);
            }
            if (!transitionAnimationState.isFinished$animation_core_release()) {
                z = false;
            }
        }
        SnapshotStateList<Transition<?>> snapshotStateList2 = this._transitions;
        int size2 = snapshotStateList2.size();
        for (int i11 = 0; i11 < size2; i11++) {
            Transition<?> transition = snapshotStateList2.get(i11);
            if (!p.a(transition.getTargetState(), transition.getCurrentState())) {
                transition.onFrame$animation_core_release(getPlayTimeNanos(), durationScale);
            }
            if (!p.a(transition.getTargetState(), transition.getCurrentState())) {
                z = false;
            }
        }
        if (z) {
            onTransitionEnd$animation_core_release();
        }
    }

    public final void onTransitionEnd$animation_core_release() {
        setStartTimeNanos(Long.MIN_VALUE);
        TransitionState<S> transitionState = this.transitionState;
        if (transitionState instanceof MutableTransitionState) {
            ((MutableTransitionState) transitionState).setCurrentState$animation_core_release(getTargetState());
        }
        setPlayTimeNanos(0L);
        this.transitionState.setRunning$animation_core_release(false);
    }

    public final void onTransitionStart$animation_core_release(long frameTimeNanos) {
        setStartTimeNanos(frameTimeNanos);
        this.transitionState.setRunning$animation_core_release(true);
    }

    public final void removeAnimation$animation_core_release(Transition<S>.TransitionAnimationState<?, ?> animation) {
        this._animations.remove(animation);
    }

    public final boolean removeTransition$animation_core_release(Transition<?> transition) {
        return this._transitions.remove(transition);
    }

    public final void seek(S initialState, S targetState, long playTimeNanos) {
        setStartTimeNanos(Long.MIN_VALUE);
        this.transitionState.setRunning$animation_core_release(false);
        if (!isSeeking() || !p.a(getCurrentState(), initialState) || !p.a(getTargetState(), targetState)) {
            if (!p.a(getCurrentState(), initialState)) {
                TransitionState<S> transitionState = this.transitionState;
                if (transitionState instanceof MutableTransitionState) {
                    ((MutableTransitionState) transitionState).setCurrentState$animation_core_release(initialState);
                }
            }
            setTargetState$animation_core_release(targetState);
            setSeeking$animation_core_release(true);
            setSegment(new SegmentImpl(initialState, targetState));
        }
        SnapshotStateList<Transition<?>> snapshotStateList = this._transitions;
        int size = snapshotStateList.size();
        for (int i10 = 0; i10 < size; i10++) {
            Transition<?> transition = snapshotStateList.get(i10);
            if (transition.isSeeking()) {
                transition.seek(transition.getCurrentState(), transition.getTargetState(), playTimeNanos);
            }
        }
        SnapshotStateList<Transition<S>.TransitionAnimationState<?, ?>> snapshotStateList2 = this._animations;
        int size2 = snapshotStateList2.size();
        for (int i11 = 0; i11 < size2; i11++) {
            snapshotStateList2.get(i11).seekTo$animation_core_release(playTimeNanos);
        }
        this.lastSeekedTimeNanos = playTimeNanos;
    }

    public final void setLastSeekedTimeNanos$animation_core_release(long j10) {
        this.lastSeekedTimeNanos = j10;
    }

    public final void setPlayTimeNanos(long j10) {
        this.playTimeNanos.setLongValue(j10);
    }

    public final void setSeeking$animation_core_release(boolean z) {
        this.isSeeking.setValue(Boolean.valueOf(z));
    }

    public final void setTargetState$animation_core_release(S s10) {
        this.targetState.setValue(s10);
    }

    public final void setUpdateChildrenNeeded$animation_core_release(boolean z) {
        this.updateChildrenNeeded.setValue(Boolean.valueOf(z));
    }

    public String toString() {
        List<Transition<S>.TransitionAnimationState<?, ?>> animations = getAnimations();
        int size = animations.size();
        String str = "Transition animation values: ";
        for (int i10 = 0; i10 < size; i10++) {
            str = str + animations.get(i10) + ", ";
        }
        return str;
    }

    public final void updateTarget$animation_core_release(S s10, Composer composer, int i10) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-583974681);
        int i11 = (i10 & 14) == 0 ? (composerStartRestartGroup.changed(s10) ? 4 : 2) | i10 : i10;
        if ((i10 & 112) == 0) {
            i11 |= composerStartRestartGroup.changed(this) ? 32 : 16;
        }
        if ((i11 & 91) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-583974681, i11, -1, "androidx.compose.animation.core.Transition.updateTarget (Transition.kt:608)");
            }
            if (!isSeeking() && !p.a(getTargetState(), s10)) {
                setSegment(new SegmentImpl(getTargetState(), s10));
                if (!p.a(getCurrentState(), getTargetState())) {
                    TransitionState<S> transitionState = this.transitionState;
                    if (!(transitionState instanceof MutableTransitionState)) {
                        throw new IllegalStateException("Can only update the current state with MutableTransitionState");
                    }
                    ((MutableTransitionState) transitionState).setCurrentState$animation_core_release(getTargetState());
                }
                setTargetState$animation_core_release(s10);
                if (!isRunning()) {
                    setUpdateChildrenNeeded$animation_core_release(true);
                }
                SnapshotStateList<Transition<S>.TransitionAnimationState<?, ?>> snapshotStateList = this._animations;
                int size = snapshotStateList.size();
                for (int i12 = 0; i12 < size; i12++) {
                    snapshotStateList.get(i12).resetAnimation$animation_core_release();
                }
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Transition$updateTarget$3(this, s10, i10));
        }
    }

    public final void removeAnimation$animation_core_release(Transition<S>.DeferredAnimation<?, ?> deferredAnimation) {
        Transition<S>.TransitionAnimationState<?, ?> animation;
        Transition<S>.DeferredAnimationData<?, ?>.DeferredAnimationData<?, V> data$animation_core_release = deferredAnimation.getData$animation_core_release();
        if (data$animation_core_release == 0 || (animation = data$animation_core_release.getAnimation()) == null) {
            return;
        }
        removeAnimation$animation_core_release(animation);
    }

    public /* synthetic */ Transition(TransitionState transitionState, String str, int i10, h hVar) {
        this(transitionState, (i10 & 2) != 0 ? null : str);
    }

    public Transition(S s10, String str) {
        this(new MutableTransitionState(s10), str);
    }

    public /* synthetic */ Transition(MutableTransitionState mutableTransitionState, String str, int i10, h hVar) {
        this(mutableTransitionState, (i10 & 2) != 0 ? null : str);
    }

    public Transition(MutableTransitionState<S> mutableTransitionState, String str) {
        this((TransitionState) mutableTransitionState, str);
    }
}
