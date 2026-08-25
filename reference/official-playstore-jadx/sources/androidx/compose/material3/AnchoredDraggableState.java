package androidx.compose.material3;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.gestures.DraggableState;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import f7.j;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b,\b\u0001\u0018\u0000 q*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001qBs\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012!\u0010\t\u001a\u001d\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\n\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\f\u0012#\b\u0002\u0010\u0010\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\u0004¢\u0006\u0004\b\u0011\u0010\u0012B\u0083\u0001\b\u0017\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013\u0012!\u0010\t\u001a\u001d\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\n\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\f\u0012#\b\u0002\u0010\u0010\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\u0004¢\u0006\u0004\b\u0011\u0010\u0015J\r\u0010\u0016\u001a\u00020\u0005¢\u0006\u0004\b\u0016\u0010\u0017J%\u0010\u001b\u001a\u00020\u001a2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u00132\b\b\u0002\u0010\u0019\u001a\u00028\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ\u0018\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u0005H\u0086@¢\u0006\u0004\b\u001e\u0010\u001fJ^\u0010'\u001a\u00020\u001a2\b\b\u0002\u0010!\u001a\u00020 2B\u0010&\u001a>\b\u0001\u0012\u0004\u0012\u00020#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u0013¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0$\u0012\u0006\u0012\u0004\u0018\u00010\u00020\"¢\u0006\u0002\b%H\u0086@¢\u0006\u0004\b'\u0010(J{\u0010'\u001a\u00020\u001a2\u0006\u0010)\u001a\u00028\u00002\b\b\u0002\u0010!\u001a\u00020 2W\u0010&\u001aS\b\u0001\u0012\u0004\u0012\u00020#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u0013¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b()\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0$\u0012\u0006\u0012\u0004\u0018\u00010\u00020*¢\u0006\u0002\b%H\u0086@¢\u0006\u0004\b'\u0010+J\u0017\u0010/\u001a\u00020\u00052\u0006\u0010,\u001a\u00020\u0005H\u0000¢\u0006\u0004\b-\u0010.J\u0015\u00100\u001a\u00020\u00052\u0006\u0010,\u001a\u00020\u0005¢\u0006\u0004\b0\u0010.J'\u00103\u001a\u00028\u00002\u0006\u00101\u001a\u00020\u00052\u0006\u00102\u001a\u00028\u00002\u0006\u0010\u001d\u001a\u00020\u0005H\u0002¢\u0006\u0004\b3\u00104J\u001f\u00105\u001a\u00028\u00002\u0006\u00101\u001a\u00020\u00052\u0006\u00102\u001a\u00028\u0000H\u0002¢\u0006\u0004\b5\u00106J\u0017\u00107\u001a\u00020\u000f2\u0006\u0010)\u001a\u00028\u0000H\u0002¢\u0006\u0004\b7\u00108R5\u0010\t\u001a\u001d\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00050\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\t\u00109\u001a\u0004\b:\u0010;R \u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\n8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u000b\u0010<\u001a\u0004\b=\u0010>R\u001d\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\f8\u0006¢\u0006\f\n\u0004\b\r\u0010?\u001a\u0004\b@\u0010AR5\u0010\u0010\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0010\u00109\u001a\u0004\bB\u0010;R\u0014\u0010D\u001a\u00020C8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bD\u0010ER\u001a\u0010G\u001a\u00020F8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bG\u0010H\u001a\u0004\bI\u0010JR+\u00102\u001a\u00028\u00002\u0006\u0010K\u001a\u00028\u00008F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bL\u0010M\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR\u001b\u0010)\u001a\u00028\u00008FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bR\u0010S\u001a\u0004\bT\u0010OR\u001b\u0010W\u001a\u00028\u00008@X\u0080\u0084\u0002¢\u0006\f\n\u0004\bU\u0010S\u001a\u0004\bV\u0010OR+\u00101\u001a\u00020\u00052\u0006\u0010K\u001a\u00020\u00058F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bX\u0010Y\u001a\u0004\bZ\u0010\u0017\"\u0004\b[\u0010\\R\u001b\u0010_\u001a\u00020\u00058GX\u0086\u0084\u0002¢\u0006\f\n\u0004\b]\u0010S\u001a\u0004\b^\u0010\u0017R+\u0010c\u001a\u00020\u00052\u0006\u0010K\u001a\u00020\u00058F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b`\u0010Y\u001a\u0004\ba\u0010\u0017\"\u0004\bb\u0010\\R/\u0010g\u001a\u0004\u0018\u00018\u00002\b\u0010K\u001a\u0004\u0018\u00018\u00008B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bd\u0010M\u001a\u0004\be\u0010O\"\u0004\bf\u0010QR7\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u00132\f\u0010K\u001a\b\u0012\u0004\u0012\u00028\u00000\u00138F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bh\u0010M\u001a\u0004\bi\u0010j\"\u0004\bk\u0010lR\u0014\u0010m\u001a\u00020#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bm\u0010nR\u0011\u0010o\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\bo\u0010p¨\u0006r"}, d2 = {"Landroidx/compose/material3/AnchoredDraggableState;", "T", "", "initialValue", "Lkotlin/Function1;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "totalDistance", "positionalThreshold", "Lkotlin/Function0;", "velocityThreshold", "Landroidx/compose/animation/core/AnimationSpec;", "animationSpec", "newValue", "", "confirmValueChange", "<init>", "(Ljava/lang/Object;Lr7/l;Lr7/a;Landroidx/compose/animation/core/AnimationSpec;Lr7/l;)V", "Landroidx/compose/material3/DraggableAnchors;", "anchors", "(Ljava/lang/Object;Landroidx/compose/material3/DraggableAnchors;Lr7/l;Lr7/a;Landroidx/compose/animation/core/AnimationSpec;Lr7/l;)V", "requireOffset", "()F", "newAnchors", "newTarget", "Lx6/t0;", "updateAnchors", "(Landroidx/compose/material3/DraggableAnchors;Ljava/lang/Object;)V", "velocity", "settle", "(FLd7/d;)Ljava/lang/Object;", "Landroidx/compose/foundation/MutatePriority;", "dragPriority", "Lkotlin/Function3;", "Landroidx/compose/material3/AnchoredDragScope;", "Ld7/d;", "Lx6/n;", "block", "anchoredDrag", "(Landroidx/compose/foundation/MutatePriority;Lr7/q;Ld7/d;)Ljava/lang/Object;", "targetValue", "Lkotlin/Function4;", "(Ljava/lang/Object;Landroidx/compose/foundation/MutatePriority;Lr7/r;Ld7/d;)Ljava/lang/Object;", "delta", "newOffsetForDelta$material3_release", "(F)F", "newOffsetForDelta", "dispatchRawDelta", "offset", "currentValue", "computeTarget", "(FLjava/lang/Object;F)Ljava/lang/Object;", "computeTargetWithoutThresholds", "(FLjava/lang/Object;)Ljava/lang/Object;", "trySnapTo", "(Ljava/lang/Object;)Z", "Lr7/l;", "getPositionalThreshold$material3_release", "()Lr7/l;", "Lr7/a;", "getVelocityThreshold$material3_release", "()Lr7/a;", "Landroidx/compose/animation/core/AnimationSpec;", "getAnimationSpec", "()Landroidx/compose/animation/core/AnimationSpec;", "getConfirmValueChange$material3_release", "Landroidx/compose/material3/InternalMutatorMutex;", "dragMutex", "Landroidx/compose/material3/InternalMutatorMutex;", "Landroidx/compose/foundation/gestures/DraggableState;", "draggableState", "Landroidx/compose/foundation/gestures/DraggableState;", "getDraggableState$material3_release", "()Landroidx/compose/foundation/gestures/DraggableState;", "<set-?>", "currentValue$delegate", "Landroidx/compose/runtime/MutableState;", "getCurrentValue", "()Ljava/lang/Object;", "setCurrentValue", "(Ljava/lang/Object;)V", "targetValue$delegate", "Landroidx/compose/runtime/State;", "getTargetValue", "closestValue$delegate", "getClosestValue$material3_release", "closestValue", "offset$delegate", "Landroidx/compose/runtime/MutableFloatState;", "getOffset", "setOffset", "(F)V", "progress$delegate", "getProgress", "progress", "lastVelocity$delegate", "getLastVelocity", "setLastVelocity", "lastVelocity", "dragTarget$delegate", "getDragTarget", "setDragTarget", "dragTarget", "anchors$delegate", "getAnchors", "()Landroidx/compose/material3/DraggableAnchors;", "setAnchors", "(Landroidx/compose/material3/DraggableAnchors;)V", "anchoredDragScope", "Landroidx/compose/material3/AnchoredDragScope;", "isAnimationRunning", "()Z", "Companion", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AnchoredDraggableState<T> {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final AnchoredDragScope anchoredDragScope;

    /* JADX INFO: renamed from: anchors$delegate, reason: from kotlin metadata */
    private final MutableState anchors;
    private final AnimationSpec<Float> animationSpec;

    /* JADX INFO: renamed from: closestValue$delegate, reason: from kotlin metadata */
    private final State closestValue;
    private final l<T, Boolean> confirmValueChange;

    /* JADX INFO: renamed from: currentValue$delegate, reason: from kotlin metadata */
    private final MutableState currentValue;
    private final InternalMutatorMutex dragMutex;

    /* JADX INFO: renamed from: dragTarget$delegate, reason: from kotlin metadata */
    private final MutableState dragTarget;
    private final DraggableState draggableState;

    /* JADX INFO: renamed from: lastVelocity$delegate, reason: from kotlin metadata */
    private final MutableFloatState lastVelocity;

    /* JADX INFO: renamed from: offset$delegate, reason: from kotlin metadata */
    private final MutableFloatState offset;
    private final l<Float, Float> positionalThreshold;

    /* JADX INFO: renamed from: progress$delegate, reason: from kotlin metadata */
    private final State progress;

    /* JADX INFO: renamed from: targetValue$delegate, reason: from kotlin metadata */
    private final State targetValue;
    private final r7.a<Float> velocityThreshold;

    /* JADX INFO: renamed from: androidx.compose.material3.AnchoredDraggableState$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u0002H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "T", "it", "invoke", "(Ljava/lang/Object;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<T, Boolean> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public final Boolean invoke(T t2) {
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.AnchoredDraggableState$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u0002H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "T", "it", "invoke", "(Ljava/lang/Object;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements l<T, Boolean> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(1);
        }

        @Override // r7.l
        public final Boolean invoke(T t2) {
            return Boolean.TRUE;
        }
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J~\u0010\u0013\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0012\u0012\u0004\u0012\u00028\u00010\u0011\"\b\b\u0001\u0010\u0004*\u00020\u00012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\t0\b2!\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00060\b2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u000fH\u0007¢\u0006\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Landroidx/compose/material3/AnchoredDraggableState$Companion;", "", "<init>", "()V", "T", "Landroidx/compose/animation/core/AnimationSpec;", "", "animationSpec", "Lkotlin/Function1;", "", "confirmValueChange", "Lx6/y;", ContentDisposition.Parameters.Name, "distance", "positionalThreshold", "Lkotlin/Function0;", "velocityThreshold", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/material3/AnchoredDraggableState;", "Saver", "(Landroidx/compose/animation/core/AnimationSpec;Lr7/l;Lr7/l;Lr7/a;)Landroidx/compose/runtime/saveable/Saver;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        public final <T> Saver<AnchoredDraggableState<T>, T> Saver(AnimationSpec<Float> animationSpec, l<? super T, Boolean> confirmValueChange, l<? super Float, Float> positionalThreshold, r7.a<Float> velocityThreshold) {
            return SaverKt.Saver(AnchoredDraggableState$Companion$Saver$1.INSTANCE, new AnchoredDraggableState$Companion$Saver$2(positionalThreshold, velocityThreshold, animationSpec, confirmValueChange));
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.AnchoredDraggableState$anchoredDrag$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.material3.AnchoredDraggableState", f = "AnchoredDraggable.kt", l = {529}, m = "anchoredDrag")
    public static final class C04521 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ AnchoredDraggableState<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04521(AnchoredDraggableState<T> anchoredDraggableState, d7.d<? super C04521> dVar) {
            super(dVar);
            this.this$0 = anchoredDraggableState;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.anchoredDrag(null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.AnchoredDraggableState$anchoredDrag$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"T", "Lx6/t0;", "<anonymous>", "()V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.material3.AnchoredDraggableState$anchoredDrag$2", f = "AnchoredDraggable.kt", l = {530}, m = "invokeSuspend")
    public static final class C04532 extends j implements l<d7.d<? super t0>, Object> {
        final /* synthetic */ q<AnchoredDragScope, DraggableAnchors<T>, d7.d<? super t0>, Object> $block;
        int label;
        final /* synthetic */ AnchoredDraggableState<T> this$0;

        /* JADX INFO: renamed from: androidx.compose.material3.AnchoredDraggableState$anchoredDrag$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/material3/DraggableAnchors;", "T", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
        public static final class AnonymousClass1 extends r implements r7.a<DraggableAnchors<T>> {
            final /* synthetic */ AnchoredDraggableState<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(AnchoredDraggableState<T> anchoredDraggableState) {
                super(0);
                this.this$0 = anchoredDraggableState;
            }

            @Override // r7.a
            public final DraggableAnchors<T> invoke() {
                return this.this$0.getAnchors();
            }
        }

        /* JADX INFO: renamed from: androidx.compose.material3.AnchoredDraggableState$anchoredDrag$2$2, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "Landroidx/compose/material3/DraggableAnchors;", "latestAnchors", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/material3/DraggableAnchors;)V"}, k = 3, mv = {1, 8, 0})
        @f7.e(c = "androidx.compose.material3.AnchoredDraggableState$anchoredDrag$2$2", f = "AnchoredDraggable.kt", l = {531}, m = "invokeSuspend")
        public static final class C00832 extends j implements p<DraggableAnchors<T>, d7.d<? super t0>, Object> {
            final /* synthetic */ q<AnchoredDragScope, DraggableAnchors<T>, d7.d<? super t0>, Object> $block;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ AnchoredDraggableState<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public C00832(q<? super AnchoredDragScope, ? super DraggableAnchors<T>, ? super d7.d<? super t0>, ? extends Object> qVar, AnchoredDraggableState<T> anchoredDraggableState, d7.d<? super C00832> dVar) {
                super(2, dVar);
                this.$block = qVar;
                this.this$0 = anchoredDraggableState;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                C00832 c00832 = new C00832(this.$block, this.this$0, dVar);
                c00832.L$0 = obj;
                return c00832;
            }

            @Override // r7.p
            public final Object invoke(DraggableAnchors<T> draggableAnchors, d7.d<? super t0> dVar) {
                return ((C00832) create(draggableAnchors, dVar)).invokeSuspend(t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    k2.c.G(obj);
                    DraggableAnchors draggableAnchors = (DraggableAnchors) this.L$0;
                    q<AnchoredDragScope, DraggableAnchors<T>, d7.d<? super t0>, Object> qVar = this.$block;
                    AnchoredDragScope anchoredDragScope = ((AnchoredDraggableState) this.this$0).anchoredDragScope;
                    this.label = 1;
                    Object objInvoke = qVar.invoke(anchoredDragScope, draggableAnchors, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objInvoke == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                }
                return t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C04532(AnchoredDraggableState<T> anchoredDraggableState, q<? super AnchoredDragScope, ? super DraggableAnchors<T>, ? super d7.d<? super t0>, ? extends Object> qVar, d7.d<? super C04532> dVar) {
            super(1, dVar);
            this.this$0 = anchoredDraggableState;
            this.$block = qVar;
        }

        @Override // f7.a
        public final d7.d<t0> create(d7.d<?> dVar) {
            return new C04532(this.this$0, this.$block, dVar);
        }

        @Override // r7.l
        public final Object invoke(d7.d<? super t0> dVar) {
            return ((C04532) create(dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0);
                C00832 c00832 = new C00832(this.$block, this.this$0, null);
                this.label = 1;
                Object objRestartable = AnchoredDraggableKt.restartable(anonymousClass1, c00832, this);
                e7.a aVar = e7.a.f15033i;
                if (objRestartable == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.AnchoredDraggableState$anchoredDrag$3, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.material3.AnchoredDraggableState", f = "AnchoredDraggable.kt", l = {575}, m = "anchoredDrag")
    public static final class AnonymousClass3 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ AnchoredDraggableState<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(AnchoredDraggableState<T> anchoredDraggableState, d7.d<? super AnonymousClass3> dVar) {
            super(dVar);
            this.this$0 = anchoredDraggableState;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.anchoredDrag(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.AnchoredDraggableState$anchoredDrag$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"T", "Lx6/t0;", "<anonymous>", "()V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.material3.AnchoredDraggableState$anchoredDrag$4", f = "AnchoredDraggable.kt", l = {577}, m = "invokeSuspend")
    public static final class AnonymousClass4 extends j implements l<d7.d<? super t0>, Object> {
        final /* synthetic */ r7.r<AnchoredDragScope, DraggableAnchors<T>, T, d7.d<? super t0>, Object> $block;
        final /* synthetic */ T $targetValue;
        int label;
        final /* synthetic */ AnchoredDraggableState<T> this$0;

        /* JADX INFO: renamed from: androidx.compose.material3.AnchoredDraggableState$anchoredDrag$4$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"T", "Lx6/x;", "Landroidx/compose/material3/DraggableAnchors;", "invoke", "()Lx6/x;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass1 extends r implements r7.a<x> {
            final /* synthetic */ AnchoredDraggableState<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(AnchoredDraggableState<T> anchoredDraggableState) {
                super(0);
                this.this$0 = anchoredDraggableState;
            }

            @Override // r7.a
            public final x invoke() {
                return new x(this.this$0.getAnchors(), this.this$0.getTargetValue());
            }
        }

        /* JADX INFO: renamed from: androidx.compose.material3.AnchoredDraggableState$anchoredDrag$4$2, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u00002\u0018\u0010\u0003\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0004\u0012\u00028\u00000\u0001H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"T", "Lx6/x;", "Landroidx/compose/material3/DraggableAnchors;", "<name for destructuring parameter 0>", "Lx6/t0;", "<anonymous>", "(Lx6/x;)V"}, k = 3, mv = {1, 8, 0})
        @f7.e(c = "androidx.compose.material3.AnchoredDraggableState$anchoredDrag$4$2", f = "AnchoredDraggable.kt", l = {580}, m = "invokeSuspend")
        public static final class AnonymousClass2 extends j implements p<x, d7.d<? super t0>, Object> {
            final /* synthetic */ r7.r<AnchoredDragScope, DraggableAnchors<T>, T, d7.d<? super t0>, Object> $block;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ AnchoredDraggableState<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass2(r7.r<? super AnchoredDragScope, ? super DraggableAnchors<T>, ? super T, ? super d7.d<? super t0>, ? extends Object> rVar, AnchoredDraggableState<T> anchoredDraggableState, d7.d<? super AnonymousClass2> dVar) {
                super(2, dVar);
                this.$block = rVar;
                this.this$0 = anchoredDraggableState;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$block, this.this$0, dVar);
                anonymousClass2.L$0 = obj;
                return anonymousClass2;
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    k2.c.G(obj);
                    x xVar = (x) this.L$0;
                    DraggableAnchors draggableAnchors = (DraggableAnchors) xVar.f22608i;
                    Object obj2 = xVar.f22609l;
                    r7.r<AnchoredDragScope, DraggableAnchors<T>, T, d7.d<? super t0>, Object> rVar = this.$block;
                    AnchoredDragScope anchoredDragScope = ((AnchoredDraggableState) this.this$0).anchoredDragScope;
                    this.label = 1;
                    Object objInvoke = rVar.invoke(anchoredDragScope, draggableAnchors, obj2, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objInvoke == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                }
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(x xVar, d7.d<? super t0> dVar) {
                return ((AnonymousClass2) create(xVar, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass4(AnchoredDraggableState<T> anchoredDraggableState, T t2, r7.r<? super AnchoredDragScope, ? super DraggableAnchors<T>, ? super T, ? super d7.d<? super t0>, ? extends Object> rVar, d7.d<? super AnonymousClass4> dVar) {
            super(1, dVar);
            this.this$0 = anchoredDraggableState;
            this.$targetValue = t2;
            this.$block = rVar;
        }

        @Override // f7.a
        public final d7.d<t0> create(d7.d<?> dVar) {
            return new AnonymousClass4(this.this$0, this.$targetValue, this.$block, dVar);
        }

        @Override // r7.l
        public final Object invoke(d7.d<? super t0> dVar) {
            return ((AnonymousClass4) create(dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                this.this$0.setDragTarget(this.$targetValue);
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0);
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$block, this.this$0, null);
                this.label = 1;
                Object objRestartable = AnchoredDraggableKt.restartable(anonymousClass1, anonymousClass2, this);
                e7.a aVar = e7.a.f15033i;
                if (objRestartable == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.AnchoredDraggableState$trySnapTo$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"T", "Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C04541 extends r implements r7.a<t0> {
        final /* synthetic */ T $targetValue;
        final /* synthetic */ AnchoredDraggableState<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04541(AnchoredDraggableState<T> anchoredDraggableState, T t2) {
            super(0);
            this.this$0 = anchoredDraggableState;
            this.$targetValue = t2;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m1219invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m1219invoke() {
            AnchoredDragScope anchoredDragScope = ((AnchoredDraggableState) this.this$0).anchoredDragScope;
            AnchoredDraggableState<T> anchoredDraggableState = this.this$0;
            T t2 = this.$targetValue;
            float fPositionOf = anchoredDraggableState.getAnchors().positionOf(t2);
            if (!Float.isNaN(fPositionOf)) {
                a.a(anchoredDragScope, fPositionOf, 0.0f, 2, null);
                anchoredDraggableState.setDragTarget(null);
            }
            anchoredDraggableState.setCurrentValue(t2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AnchoredDraggableState(T t2, l<? super Float, Float> lVar, r7.a<Float> aVar, AnimationSpec<Float> animationSpec, l<? super T, Boolean> lVar2) {
        this.positionalThreshold = lVar;
        this.velocityThreshold = aVar;
        this.animationSpec = animationSpec;
        this.confirmValueChange = lVar2;
        this.dragMutex = new InternalMutatorMutex();
        this.draggableState = new AnchoredDraggableState$draggableState$1(this);
        this.currentValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(t2, null, 2, null);
        this.targetValue = SnapshotStateKt.derivedStateOf(new AnchoredDraggableState$targetValue$2(this));
        this.closestValue = SnapshotStateKt.derivedStateOf(new AnchoredDraggableState$closestValue$2(this));
        this.offset = PrimitiveSnapshotStateKt.mutableFloatStateOf(Float.NaN);
        this.progress = SnapshotStateKt.derivedStateOf(SnapshotStateKt.structuralEqualityPolicy(), new AnchoredDraggableState$progress$2(this));
        this.lastVelocity = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.dragTarget = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.anchors = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(AnchoredDraggableKt.emptyDraggableAnchors(), null, 2, null);
        this.anchoredDragScope = new AnchoredDragScope(this) { // from class: androidx.compose.material3.AnchoredDraggableState$anchoredDragScope$1
            final /* synthetic */ AnchoredDraggableState<T> this$0;

            {
                this.this$0 = this;
            }

            @Override // androidx.compose.material3.AnchoredDragScope
            public void dragTo(float newOffset, float lastKnownVelocity) {
                this.this$0.setOffset(newOffset);
                this.this$0.setLastVelocity(lastKnownVelocity);
            }
        };
    }

    public static /* synthetic */ Object anchoredDrag$default(AnchoredDraggableState anchoredDraggableState, MutatePriority mutatePriority, q qVar, d7.d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            mutatePriority = MutatePriority.Default;
        }
        return anchoredDraggableState.anchoredDrag(mutatePriority, qVar, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final T computeTarget(float offset, T currentValue, float velocity) {
        DraggableAnchors<T> anchors = getAnchors();
        float fPositionOf = anchors.positionOf(currentValue);
        float fFloatValue = ((Number) this.velocityThreshold.invoke()).floatValue();
        if (fPositionOf != offset && !Float.isNaN(fPositionOf)) {
            if (fPositionOf < offset) {
                if (velocity >= fFloatValue) {
                    return anchors.closestAnchor(offset, true);
                }
                T tClosestAnchor = anchors.closestAnchor(offset, true);
                if (offset >= Math.abs(Math.abs(((Number) this.positionalThreshold.invoke(Float.valueOf(Math.abs(anchors.positionOf(tClosestAnchor) - fPositionOf)))).floatValue()) + fPositionOf)) {
                    return tClosestAnchor;
                }
            } else {
                if (velocity <= (-fFloatValue)) {
                    return anchors.closestAnchor(offset, false);
                }
                T tClosestAnchor2 = anchors.closestAnchor(offset, false);
                float fAbs = Math.abs(fPositionOf - Math.abs(((Number) this.positionalThreshold.invoke(Float.valueOf(Math.abs(fPositionOf - anchors.positionOf(tClosestAnchor2))))).floatValue()));
                if (offset >= 0.0f ? offset <= fAbs : Math.abs(offset) >= fAbs) {
                    return tClosestAnchor2;
                }
            }
        }
        return currentValue;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final T computeTargetWithoutThresholds(float offset, T currentValue) {
        DraggableAnchors<T> anchors = getAnchors();
        float fPositionOf = anchors.positionOf(currentValue);
        if (fPositionOf != offset && !Float.isNaN(fPositionOf)) {
            if (fPositionOf < offset) {
                T tClosestAnchor = anchors.closestAnchor(offset, true);
                if (tClosestAnchor != null) {
                    return tClosestAnchor;
                }
            } else {
                T tClosestAnchor2 = anchors.closestAnchor(offset, false);
                if (tClosestAnchor2 != null) {
                    return tClosestAnchor2;
                }
            }
        }
        return currentValue;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final T getDragTarget() {
        return this.dragTarget.getValue();
    }

    private final void setAnchors(DraggableAnchors<T> draggableAnchors) {
        this.anchors.setValue(draggableAnchors);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCurrentValue(T t2) {
        this.currentValue.setValue(t2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setDragTarget(T t2) {
        this.dragTarget.setValue(t2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setLastVelocity(float f10) {
        this.lastVelocity.setFloatValue(f10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setOffset(float f10) {
        this.offset.setFloatValue(f10);
    }

    private final boolean trySnapTo(T targetValue) {
        return this.dragMutex.tryMutate(new C04541(this, targetValue));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void updateAnchors$default(AnchoredDraggableState anchoredDraggableState, DraggableAnchors draggableAnchors, Object obj, int i10, Object obj2) {
        if ((i10 & 2) != 0 && (Float.isNaN(anchoredDraggableState.getOffset()) || (obj = draggableAnchors.closestAnchor(anchoredDraggableState.getOffset())) == null)) {
            obj = anchoredDraggableState.getTargetValue();
        }
        anchoredDraggableState.updateAnchors(draggableAnchors, obj);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object anchoredDrag(androidx.compose.foundation.MutatePriority r6, r7.q<? super androidx.compose.material3.AnchoredDragScope, ? super androidx.compose.material3.DraggableAnchors<T>, ? super d7.d<? super x6.t0>, ? extends java.lang.Object> r7, d7.d<? super x6.t0> r8) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r8 instanceof androidx.compose.material3.AnchoredDraggableState.C04521
            if (r0 == 0) goto L13
            r0 = r8
            androidx.compose.material3.AnchoredDraggableState$anchoredDrag$1 r0 = (androidx.compose.material3.AnchoredDraggableState.C04521) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.material3.AnchoredDraggableState$anchoredDrag$1 r0 = new androidx.compose.material3.AnchoredDraggableState$anchoredDrag$1
            r0.<init>(r5, r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 1056964608(0x3f000000, float:0.5)
            r3 = 1
            if (r1 == 0) goto L35
            if (r1 != r3) goto L2d
            java.lang.Object r6 = r0.L$0
            androidx.compose.material3.AnchoredDraggableState r6 = (androidx.compose.material3.AnchoredDraggableState) r6
            k2.c.G(r8)     // Catch: java.lang.Throwable -> L2b
            goto L4e
        L2b:
            r7 = move-exception
            goto L87
        L2d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L35:
            k2.c.G(r8)
            androidx.compose.material3.InternalMutatorMutex r8 = r5.dragMutex     // Catch: java.lang.Throwable -> L85
            androidx.compose.material3.AnchoredDraggableState$anchoredDrag$2 r1 = new androidx.compose.material3.AnchoredDraggableState$anchoredDrag$2     // Catch: java.lang.Throwable -> L85
            r4 = 0
            r1.<init>(r5, r7, r4)     // Catch: java.lang.Throwable -> L85
            r0.L$0 = r5     // Catch: java.lang.Throwable -> L85
            r0.label = r3     // Catch: java.lang.Throwable -> L85
            java.lang.Object r6 = r8.mutate(r6, r1, r0)     // Catch: java.lang.Throwable -> L85
            e7.a r7 = e7.a.f15033i
            if (r6 != r7) goto L4d
            return r7
        L4d:
            r6 = r5
        L4e:
            androidx.compose.material3.DraggableAnchors r7 = r6.getAnchors()
            float r8 = r6.getOffset()
            java.lang.Object r7 = r7.closestAnchor(r8)
            if (r7 == 0) goto L82
            float r8 = r6.getOffset()
            androidx.compose.material3.DraggableAnchors r0 = r6.getAnchors()
            float r0 = r0.positionOf(r7)
            float r8 = r8 - r0
            float r8 = java.lang.Math.abs(r8)
            int r8 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r8 > 0) goto L82
            r7.l<T, java.lang.Boolean> r8 = r6.confirmValueChange
            java.lang.Object r8 = r8.invoke(r7)
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto L82
            r6.setCurrentValue(r7)
        L82:
            x6.t0 r6 = x6.t0.f22605a
            return r6
        L85:
            r7 = move-exception
            r6 = r5
        L87:
            androidx.compose.material3.DraggableAnchors r8 = r6.getAnchors()
            float r0 = r6.getOffset()
            java.lang.Object r8 = r8.closestAnchor(r0)
            if (r8 == 0) goto Lbb
            float r0 = r6.getOffset()
            androidx.compose.material3.DraggableAnchors r1 = r6.getAnchors()
            float r1 = r1.positionOf(r8)
            float r0 = r0 - r1
            float r0 = java.lang.Math.abs(r0)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 > 0) goto Lbb
            r7.l<T, java.lang.Boolean> r0 = r6.confirmValueChange
            java.lang.Object r0 = r0.invoke(r8)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto Lbb
            r6.setCurrentValue(r8)
        Lbb:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AnchoredDraggableState.anchoredDrag(androidx.compose.foundation.MutatePriority, r7.q, d7.d):java.lang.Object");
    }

    public final float dispatchRawDelta(float delta) {
        float fNewOffsetForDelta$material3_release = newOffsetForDelta$material3_release(delta);
        float offset = Float.isNaN(getOffset()) ? 0.0f : getOffset();
        setOffset(fNewOffsetForDelta$material3_release);
        return fNewOffsetForDelta$material3_release - offset;
    }

    public final DraggableAnchors<T> getAnchors() {
        return (DraggableAnchors) this.anchors.getValue();
    }

    public final AnimationSpec<Float> getAnimationSpec() {
        return this.animationSpec;
    }

    public final T getClosestValue$material3_release() {
        return (T) this.closestValue.getValue();
    }

    public final l<T, Boolean> getConfirmValueChange$material3_release() {
        return this.confirmValueChange;
    }

    public final T getCurrentValue() {
        return this.currentValue.getValue();
    }

    /* JADX INFO: renamed from: getDraggableState$material3_release, reason: from getter */
    public final DraggableState getDraggableState() {
        return this.draggableState;
    }

    public final float getLastVelocity() {
        return this.lastVelocity.getFloatValue();
    }

    public final float getOffset() {
        return this.offset.getFloatValue();
    }

    public final l<Float, Float> getPositionalThreshold$material3_release() {
        return this.positionalThreshold;
    }

    public final float getProgress() {
        return ((Number) this.progress.getValue()).floatValue();
    }

    public final T getTargetValue() {
        return (T) this.targetValue.getValue();
    }

    public final r7.a<Float> getVelocityThreshold$material3_release() {
        return this.velocityThreshold;
    }

    public final boolean isAnimationRunning() {
        return getDragTarget() != null;
    }

    public final float newOffsetForDelta$material3_release(float delta) {
        return qb.d.m((Float.isNaN(getOffset()) ? 0.0f : getOffset()) + delta, getAnchors().minAnchor(), getAnchors().maxAnchor());
    }

    public final float requireOffset() {
        if (Float.isNaN(getOffset())) {
            throw new IllegalStateException("The offset was read before being initialized. Did you access the offset in a phase before layout, like effects or composition?");
        }
        return getOffset();
    }

    public final Object settle(float f10, d7.d<? super t0> dVar) {
        T currentValue = getCurrentValue();
        T tComputeTarget = computeTarget(requireOffset(), currentValue, f10);
        boolean zBooleanValue = ((Boolean) this.confirmValueChange.invoke(tComputeTarget)).booleanValue();
        t0 t0Var = t0.f22605a;
        e7.a aVar = e7.a.f15033i;
        if (zBooleanValue) {
            Object objAnimateTo = AnchoredDraggableKt.animateTo(this, tComputeTarget, f10, dVar);
            return objAnimateTo == aVar ? objAnimateTo : t0Var;
        }
        Object objAnimateTo2 = AnchoredDraggableKt.animateTo(this, currentValue, f10, dVar);
        return objAnimateTo2 == aVar ? objAnimateTo2 : t0Var;
    }

    public final void updateAnchors(DraggableAnchors<T> newAnchors, T newTarget) {
        if (kotlin.jvm.internal.p.a(getAnchors(), newAnchors)) {
            return;
        }
        setAnchors(newAnchors);
        if (trySnapTo(newTarget)) {
            return;
        }
        setDragTarget(newTarget);
    }

    public static /* synthetic */ Object anchoredDrag$default(AnchoredDraggableState anchoredDraggableState, Object obj, MutatePriority mutatePriority, r7.r rVar, d7.d dVar, int i10, Object obj2) {
        if ((i10 & 2) != 0) {
            mutatePriority = MutatePriority.Default;
        }
        return anchoredDraggableState.anchoredDrag(obj, mutatePriority, rVar, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object anchoredDrag(T r6, androidx.compose.foundation.MutatePriority r7, r7.r<? super androidx.compose.material3.AnchoredDragScope, ? super androidx.compose.material3.DraggableAnchors<T>, ? super T, ? super d7.d<? super x6.t0>, ? extends java.lang.Object> r8, d7.d<? super x6.t0> r9) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 208
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AnchoredDraggableState.anchoredDrag(java.lang.Object, androidx.compose.foundation.MutatePriority, r7.r, d7.d):java.lang.Object");
    }

    public /* synthetic */ AnchoredDraggableState(Object obj, l lVar, r7.a aVar, AnimationSpec animationSpec, l lVar2, int i10, kotlin.jvm.internal.h hVar) {
        this(obj, lVar, aVar, animationSpec, (i10 & 16) != 0 ? AnonymousClass1.INSTANCE : lVar2);
    }

    public /* synthetic */ AnchoredDraggableState(Object obj, DraggableAnchors draggableAnchors, l lVar, r7.a aVar, AnimationSpec animationSpec, l lVar2, int i10, kotlin.jvm.internal.h hVar) {
        this(obj, draggableAnchors, lVar, aVar, animationSpec, (i10 & 32) != 0 ? AnonymousClass2.INSTANCE : lVar2);
    }

    public AnchoredDraggableState(T t2, DraggableAnchors<T> draggableAnchors, l<? super Float, Float> lVar, r7.a<Float> aVar, AnimationSpec<Float> animationSpec, l<? super T, Boolean> lVar2) {
        this(t2, lVar, aVar, animationSpec, lVar2);
        setAnchors(draggableAnchors);
        trySnapTo(t2);
    }
}
