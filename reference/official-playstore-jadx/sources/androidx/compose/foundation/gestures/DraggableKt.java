package androidx.compose.foundation.gestures;

import androidx.compose.foundation.gestures.DragEvent;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.util.VelocityTracker;
import androidx.compose.ui.unit.Velocity;
import f7.j;
import io.ktor.http.ContentDisposition;
import ka.k0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import ma.x;
import r7.l;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a!\u0010\u0005\u001a\u00020\u00042\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a#\u0010\u0007\u001a\u00020\u00042\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000H\u0007¢\u0006\u0004\b\u0007\u0010\b\u001aË\u0001\u0010\u001f\u001a\u00020\t*\u00020\t2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\r2>\b\u0002\u0010\u001b\u001a8\b\u0001\u0012\u0004\u0012\u00020\u0013\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u0012¢\u0006\u0002\b\u001a2>\b\u0002\u0010\u001d\u001a8\b\u0001\u0012\u0004\u0012\u00020\u0013\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u001c\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u0012¢\u0006\u0002\b\u001a2\b\b\u0002\u0010\u001e\u001a\u00020\r¢\u0006\u0004\b\u001f\u0010 \u001aT\u0010*\u001a\u0010\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u0014\u0018\u00010)*\u00020!2\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\r0\u00002\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0$2\u0006\u0010&\u001a\u00020%2\u0006\u0010(\u001a\u00020'H\u0082@¢\u0006\u0004\b*\u0010+\u001aY\u00104\u001a\u00020\r*\u00020!2\u0006\u0010,\u001a\u00020\"2\u0006\u0010-\u001a\u00020\u00142\u0006\u0010&\u001a\u00020%2\f\u00100\u001a\b\u0012\u0004\u0012\u00020/0.2\u0006\u0010\u001e\u001a\u00020\r2\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\r0\u0000H\u0082@ø\u0001\u0000¢\u0006\u0004\b2\u00103\u001aG\u0010:\u001a\u00020\r*\u00020!2\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\r0\u00002\u0006\u00106\u001a\u0002052\u0012\u00107\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u00020\u0000H\u0082@ø\u0001\u0000¢\u0006\u0004\b8\u00109\u001a\u001e\u0010=\u001a\u00020\u0001*\u00020\u00142\u0006\u0010\f\u001a\u00020\u000bH\u0002ø\u0001\u0000¢\u0006\u0004\b;\u0010<\u001a\u001e\u0010=\u001a\u00020\u0001*\u00020>2\u0006\u0010\f\u001a\u00020\u000bH\u0002ø\u0001\u0000¢\u0006\u0004\b?\u0010<\"\u0014\u0010A\u001a\u00020@8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bA\u0010B\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006C"}, d2 = {"Lkotlin/Function1;", "", "Lx6/t0;", "onDelta", "Landroidx/compose/foundation/gestures/DraggableState;", "DraggableState", "(Lr7/l;)Landroidx/compose/foundation/gestures/DraggableState;", "rememberDraggableState", "(Lr7/l;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/gestures/DraggableState;", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/gestures/Orientation;", "orientation", "", "enabled", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "startDragImmediately", "Lkotlin/Function3;", "Lka/k0;", "Landroidx/compose/ui/geometry/Offset;", "Lx6/y;", ContentDisposition.Parameters.Name, "startedPosition", "Ld7/d;", "", "Lx6/n;", "onDragStarted", "velocity", "onDragStopped", "reverseDirection", "draggable", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/gestures/DraggableState;Landroidx/compose/foundation/gestures/Orientation;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;ZLr7/q;Lr7/q;Z)Landroidx/compose/ui/Modifier;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "canDrag", "Lkotlin/Function0;", "Landroidx/compose/ui/input/pointer/util/VelocityTracker;", "velocityTracker", "Landroidx/compose/foundation/gestures/PointerDirectionConfig;", "pointerDirectionConfig", "Lx6/x;", "awaitDownAndSlop", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Lr7/l;Lr7/a;Landroidx/compose/ui/input/pointer/util/VelocityTracker;Landroidx/compose/foundation/gestures/PointerDirectionConfig;Ld7/d;)Ljava/lang/Object;", "startEvent", "initialDelta", "Lma/x;", "Landroidx/compose/foundation/gestures/DragEvent;", "channel", "hasDragged", "awaitDrag-Su4bsnU", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Landroidx/compose/ui/input/pointer/PointerInputChange;JLandroidx/compose/ui/input/pointer/util/VelocityTracker;Lma/x;ZLr7/l;Ld7/d;)Ljava/lang/Object;", "awaitDrag", "Landroidx/compose/ui/input/pointer/PointerId;", "pointerId", "onDrag", "onDragOrUp-Axegvzg", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Lr7/l;JLr7/l;Ld7/d;)Ljava/lang/Object;", "onDragOrUp", "toFloat-3MmeM6k", "(JLandroidx/compose/foundation/gestures/Orientation;)F", "toFloat", "Landroidx/compose/ui/unit/Velocity;", "toFloat-sF-c-tU", "Landroidx/compose/foundation/gestures/DragScope;", "NoOpDragScope", "Landroidx/compose/foundation/gestures/DragScope;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DraggableKt {
    private static final DragScope NoOpDragScope = new DragScope() { // from class: androidx.compose.foundation.gestures.DraggableKt$NoOpDragScope$1
        @Override // androidx.compose.foundation.gestures.DragScope
        public void dragBy(float pixels) {
        }
    };

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DraggableKt$awaitDownAndSlop$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.foundation.gestures.DraggableKt", f = "Draggable.kt", l = {575, 584, 689, 731}, m = "awaitDownAndSlop")
    public static final class AnonymousClass1 extends f7.c {
        float F$0;
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DraggableKt.awaitDownAndSlop(null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DraggableKt$draggable$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lka/k0;", "Landroidx/compose/ui/geometry/Offset;", "it", "Lx6/t0;", "<anonymous>", "(Lka/k0;Landroidx/compose/ui/geometry/Offset;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.foundation.gestures.DraggableKt$draggable$1", f = "Draggable.kt", l = {}, m = "invokeSuspend")
    public static final class C03151 extends j implements q<k0, Offset, d7.d<? super t0>, Object> {
        int label;

        public C03151(d7.d<? super C03151> dVar) {
            super(3, dVar);
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return m352invoked4ec7I((k0) obj, ((Offset) obj2).getPackedValue(), (d7.d) obj3);
        }

        /* JADX INFO: renamed from: invoke-d-4ec7I, reason: not valid java name */
        public final Object m352invoked4ec7I(k0 k0Var, long j10, d7.d<? super t0> dVar) {
            return new C03151(dVar).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DraggableKt$draggable$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lka/k0;", "", "it", "Lx6/t0;", "<anonymous>", "(Lka/k0;F)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.foundation.gestures.DraggableKt$draggable$2", f = "Draggable.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements q<k0, Float, d7.d<? super t0>, Object> {
        int label;

        public AnonymousClass2(d7.d<? super AnonymousClass2> dVar) {
            super(3, dVar);
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((k0) obj, ((Number) obj2).floatValue(), (d7.d<? super t0>) obj3);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return t0.f22605a;
        }

        public final Object invoke(k0 k0Var, float f10, d7.d<? super t0> dVar) {
            return new AnonymousClass2(dVar).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DraggableKt$draggable$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "invoke", "(Landroidx/compose/ui/input/pointer/PointerInputChange;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass3 extends r implements l<PointerInputChange, Boolean> {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        public AnonymousClass3() {
            super(1);
        }

        @Override // r7.l
        public final Boolean invoke(PointerInputChange pointerInputChange) {
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DraggableKt$draggable$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass4 extends r implements r7.a<Boolean> {
        final /* synthetic */ boolean $startDragImmediately;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(boolean z) {
            super(0);
            this.$startDragImmediately = z;
        }

        @Override // r7.a
        public final Boolean invoke() {
            return Boolean.valueOf(this.$startDragImmediately);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DraggableKt$draggable$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lka/k0;", "Landroidx/compose/ui/unit/Velocity;", "velocity", "Lx6/t0;", "<anonymous>", "(Lka/k0;Landroidx/compose/ui/unit/Velocity;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.foundation.gestures.DraggableKt$draggable$5", f = "Draggable.kt", l = {203}, m = "invokeSuspend")
    public static final class AnonymousClass5 extends j implements q<k0, Velocity, d7.d<? super t0>, Object> {
        final /* synthetic */ q<k0, Float, d7.d<? super t0>, Object> $onDragStopped;
        final /* synthetic */ Orientation $orientation;
        /* synthetic */ long J$0;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass5(q<? super k0, ? super Float, ? super d7.d<? super t0>, ? extends Object> qVar, Orientation orientation, d7.d<? super AnonymousClass5> dVar) {
            super(3, dVar);
            this.$onDragStopped = qVar;
            this.$orientation = orientation;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return m353invokeLuvzFrg((k0) obj, ((Velocity) obj2).getPackedValue(), (d7.d) obj3);
        }

        /* JADX INFO: renamed from: invoke-LuvzFrg, reason: not valid java name */
        public final Object m353invokeLuvzFrg(k0 k0Var, long j10, d7.d<? super t0> dVar) {
            AnonymousClass5 anonymousClass5 = new AnonymousClass5(this.$onDragStopped, this.$orientation, dVar);
            anonymousClass5.L$0 = k0Var;
            anonymousClass5.J$0 = j10;
            return anonymousClass5.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                k0 k0Var = (k0) this.L$0;
                long j10 = this.J$0;
                q<k0, Float, d7.d<? super t0>, Object> qVar = this.$onDragStopped;
                Float f10 = new Float(DraggableKt.m350toFloatsFctU(j10, this.$orientation));
                this.label = 1;
                Object objInvoke = qVar.invoke(k0Var, f10, this);
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

    public static final DraggableState DraggableState(l<? super Float, t0> lVar) {
        return new DefaultDraggableState(lVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0130, code lost:
    
        if (r1 == r10) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0258, code lost:
    
        if (r3.isConsumed() == false) goto L78;
     */
    /* JADX WARN: Removed duplicated region for block: B:41:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0286 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01c6 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:67:0x0207 -> B:68:0x0217). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:74:0x0251 -> B:75:0x0254). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:86:0x0287 -> B:78:0x025c). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object awaitDownAndSlop(androidx.compose.ui.input.pointer.AwaitPointerEventScope r20, r7.l<? super androidx.compose.ui.input.pointer.PointerInputChange, java.lang.Boolean> r21, r7.a<java.lang.Boolean> r22, androidx.compose.ui.input.pointer.util.VelocityTracker r23, androidx.compose.foundation.gestures.PointerDirectionConfig r24, d7.d<? super x6.x> r25) {
        /*
            Method dump skipped, instruction units count: 656
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DraggableKt.awaitDownAndSlop(androidx.compose.ui.input.pointer.AwaitPointerEventScope, r7.l, r7.a, androidx.compose.ui.input.pointer.util.VelocityTracker, androidx.compose.foundation.gestures.PointerDirectionConfig, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: awaitDrag-Su4bsnU, reason: not valid java name */
    public static final Object m347awaitDragSu4bsnU(AwaitPointerEventScope awaitPointerEventScope, PointerInputChange pointerInputChange, long j10, VelocityTracker velocityTracker, x<? super DragEvent> xVar, boolean z, l<? super PointerInputChange, Boolean> lVar, d7.d<? super Boolean> dVar) {
        xVar.mo6685trySendJP2dKIU(new DragEvent.DragStarted(Offset.m3240minusMKHz9U(pointerInputChange.getPosition(), OffsetKt.Offset(Offset.m3236getXimpl(j10) * Math.signum(Offset.m3236getXimpl(pointerInputChange.getPosition())), Offset.m3237getYimpl(j10) * Math.signum(Offset.m3237getYimpl(pointerInputChange.getPosition())))), null));
        xVar.mo6685trySendJP2dKIU(new DragEvent.DragDelta(z ? Offset.m3243timestuRUvjQ(j10, -1.0f) : j10, null));
        return m348onDragOrUpAxegvzg(awaitPointerEventScope, lVar, pointerInputChange.getId(), new DraggableKt$awaitDrag$2(velocityTracker, xVar, z), dVar);
    }

    public static final Modifier draggable(Modifier modifier, DraggableState draggableState, Orientation orientation, boolean z, MutableInteractionSource mutableInteractionSource, boolean z5, q<? super k0, ? super Offset, ? super d7.d<? super t0>, ? extends Object> qVar, q<? super k0, ? super Float, ? super d7.d<? super t0>, ? extends Object> qVar2, boolean z10) {
        return modifier.then(new DraggableElement(draggableState, AnonymousClass3.INSTANCE, orientation, z, mutableInteractionSource, new AnonymousClass4(z5), qVar, new AnonymousClass5(qVar2, orientation, null), z10));
    }

    public static /* synthetic */ Modifier draggable$default(Modifier modifier, DraggableState draggableState, Orientation orientation, boolean z, MutableInteractionSource mutableInteractionSource, boolean z5, q qVar, q qVar2, boolean z10, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            z = true;
        }
        return draggable(modifier, draggableState, orientation, z, (i10 & 8) != 0 ? null : mutableInteractionSource, (i10 & 16) != 0 ? false : z5, (i10 & 32) != 0 ? new C03151(null) : qVar, (i10 & 64) != 0 ? new AnonymousClass2(null) : qVar2, (i10 & 128) != 0 ? false : z10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00ed, code lost:
    
        if (((java.lang.Boolean) r1.invoke(r13)).booleanValue() != false) goto L49;
     */
    /* JADX WARN: Path cross not found for [B:47:0x00e3, B:36:0x00ba], limit reached: 64 */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0082 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00ad A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x0083 -> B:24:0x0088). Please report as a decompilation issue!!! */
    /* JADX INFO: renamed from: onDragOrUp-Axegvzg, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m348onDragOrUpAxegvzg(androidx.compose.ui.input.pointer.AwaitPointerEventScope r17, r7.l<? super androidx.compose.ui.input.pointer.PointerInputChange, java.lang.Boolean> r18, long r19, r7.l<? super androidx.compose.ui.input.pointer.PointerInputChange, x6.t0> r21, d7.d<? super java.lang.Boolean> r22) {
        /*
            Method dump skipped, instruction units count: 289
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DraggableKt.m348onDragOrUpAxegvzg(androidx.compose.ui.input.pointer.AwaitPointerEventScope, r7.l, long, r7.l, d7.d):java.lang.Object");
    }

    public static final DraggableState rememberDraggableState(l<? super Float, t0> lVar, Composer composer, int i10) {
        composer.startReplaceableGroup(-183245213);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-183245213, i10, -1, "androidx.compose.foundation.gestures.rememberDraggableState (Draggable.kt:141)");
        }
        State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(lVar, composer, i10 & 14);
        composer.startReplaceableGroup(-492369756);
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = DraggableState(new DraggableKt$rememberDraggableState$1$1(stateRememberUpdatedState));
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        DraggableState draggableState = (DraggableState) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return draggableState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toFloat-3MmeM6k, reason: not valid java name */
    public static final float m349toFloat3MmeM6k(long j10, Orientation orientation) {
        return orientation == Orientation.Vertical ? Offset.m3237getYimpl(j10) : Offset.m3236getXimpl(j10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toFloat-sF-c-tU, reason: not valid java name */
    public static final float m350toFloatsFctU(long j10, Orientation orientation) {
        return orientation == Orientation.Vertical ? Velocity.m5910getYimpl(j10) : Velocity.m5909getXimpl(j10);
    }
}
