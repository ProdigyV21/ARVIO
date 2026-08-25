package androidx.compose.foundation.gestures;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.unit.Velocity;
import f7.j;
import io.ktor.http.ContentDisposition;
import ka.k0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a#\u0010\u0005\u001a\u00020\u00042\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000H\u0007¢\u0006\u0004\b\u0005\u0010\u0006\u001a#\u0010\u0007\u001a\u00020\u00042\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000H\u0007¢\u0006\u0004\b\u0007\u0010\b\u001aÅ\u0001\u0010\u001d\u001a\u00020\t*\u00020\t2\u0006\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u000b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u000f\u001a\u00020\u000b2>\b\u0002\u0010\u0018\u001a8\b\u0001\u0012\u0004\u0012\u00020\u0011\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u0010¢\u0006\u0002\b\u00172>\b\u0002\u0010\u001b\u001a8\b\u0001\u0012\u0004\u0012\u00020\u0011\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u0010¢\u0006\u0002\b\u00172\b\b\u0002\u0010\u001c\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\u001d\u0010\u001e\"\u001a\u0010 \u001a\u00020\u001f8\u0002X\u0082\u0004¢\u0006\f\n\u0004\b \u0010!\u0012\u0004\b\"\u0010#¨\u0006$"}, d2 = {"Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Offset;", "Lx6/t0;", "onDelta", "Landroidx/compose/foundation/gestures/Draggable2DState;", "Draggable2DState", "(Lr7/l;)Landroidx/compose/foundation/gestures/Draggable2DState;", "rememberDraggable2DState", "(Lr7/l;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/gestures/Draggable2DState;", "Landroidx/compose/ui/Modifier;", "state", "", "enabled", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "startDragImmediately", "Lkotlin/Function3;", "Lka/k0;", "Lx6/y;", ContentDisposition.Parameters.Name, "startedPosition", "Ld7/d;", "", "Lx6/n;", "onDragStarted", "Landroidx/compose/ui/unit/Velocity;", "velocity", "onDragStopped", "reverseDirection", "draggable2D", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/gestures/Draggable2DState;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;ZLr7/q;Lr7/q;Z)Landroidx/compose/ui/Modifier;", "Landroidx/compose/foundation/gestures/Drag2DScope;", "NoOpDrag2DScope", "Landroidx/compose/foundation/gestures/Drag2DScope;", "getNoOpDrag2DScope$annotations", "()V", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class Draggable2DKt {
    private static final Drag2DScope NoOpDrag2DScope = new Drag2DScope() { // from class: androidx.compose.foundation.gestures.Draggable2DKt$NoOpDrag2DScope$1
        @Override // androidx.compose.foundation.gestures.Drag2DScope
        /* JADX INFO: renamed from: dragBy-k-4lQ0M */
        public void mo301dragByk4lQ0M(long pixels) {
        }
    };

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.Draggable2DKt$draggable2D$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lka/k0;", "Landroidx/compose/ui/geometry/Offset;", "it", "Lx6/t0;", "<anonymous>", "(Lka/k0;Landroidx/compose/ui/geometry/Offset;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.foundation.gestures.Draggable2DKt$draggable2D$1", f = "Draggable2D.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements q<k0, Offset, d7.d<? super t0>, Object> {
        int label;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(3, dVar);
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return m340invoked4ec7I((k0) obj, ((Offset) obj2).getPackedValue(), (d7.d) obj3);
        }

        /* JADX INFO: renamed from: invoke-d-4ec7I, reason: not valid java name */
        public final Object m340invoked4ec7I(k0 k0Var, long j10, d7.d<? super t0> dVar) {
            return new AnonymousClass1(dVar).invokeSuspend(t0.f22605a);
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

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.Draggable2DKt$draggable2D$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lka/k0;", "Landroidx/compose/ui/unit/Velocity;", "it", "Lx6/t0;", "<anonymous>", "(Lka/k0;Landroidx/compose/ui/unit/Velocity;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.foundation.gestures.Draggable2DKt$draggable2D$2", f = "Draggable2D.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements q<k0, Velocity, d7.d<? super t0>, Object> {
        int label;

        public AnonymousClass2(d7.d<? super AnonymousClass2> dVar) {
            super(3, dVar);
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return m341invokeLuvzFrg((k0) obj, ((Velocity) obj2).getPackedValue(), (d7.d) obj3);
        }

        /* JADX INFO: renamed from: invoke-LuvzFrg, reason: not valid java name */
        public final Object m341invokeLuvzFrg(k0 k0Var, long j10, d7.d<? super t0> dVar) {
            return new AnonymousClass2(dVar).invokeSuspend(t0.f22605a);
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

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.Draggable2DKt$draggable2D$3, reason: invalid class name */
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

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.Draggable2DKt$draggable2D$4, reason: invalid class name */
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

    public static final Draggable2DState Draggable2DState(l<? super Offset, t0> lVar) {
        return new DefaultDraggable2DState(lVar);
    }

    public static final Modifier draggable2D(Modifier modifier, Draggable2DState draggable2DState, boolean z, MutableInteractionSource mutableInteractionSource, boolean z5, q<? super k0, ? super Offset, ? super d7.d<? super t0>, ? extends Object> qVar, q<? super k0, ? super Velocity, ? super d7.d<? super t0>, ? extends Object> qVar2, boolean z10) {
        return modifier.then(new Draggable2DElement(draggable2DState, AnonymousClass3.INSTANCE, z, mutableInteractionSource, new AnonymousClass4(z5), qVar, qVar2, z10));
    }

    private static /* synthetic */ void getNoOpDrag2DScope$annotations() {
    }

    public static final Draggable2DState rememberDraggable2DState(l<? super Offset, t0> lVar, Composer composer, int i10) {
        composer.startReplaceableGroup(-1150277615);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1150277615, i10, -1, "androidx.compose.foundation.gestures.rememberDraggable2DState (Draggable2D.kt:118)");
        }
        State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(lVar, composer, i10 & 14);
        composer.startReplaceableGroup(-492369756);
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = Draggable2DState(new Draggable2DKt$rememberDraggable2DState$1$1(stateRememberUpdatedState));
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        Draggable2DState draggable2DState = (Draggable2DState) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return draggable2DState;
    }
}
