package androidx.compose.material3;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.ui.unit.Density;
import androidx.media3.extractor.text.ttml.TtmlNode;
import f7.j;
import kotlin.Metadata;
import kotlin.jvm.internal.h0;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0019\b\u0007\u0018\u0000 ;2\u00020\u0001:\u0001;B%\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\n\u0010\u000bJ2\u0010\u0012\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\u00022\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\b\b\u0002\u0010\u0010\u001a\u00020\u000eH\u0082@¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u0011H\u0086@¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u0011H\u0086@¢\u0006\u0004\b\u0016\u0010\u0015J&\u0010\u0012\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\u00022\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0087@¢\u0006\u0004\b\u0012\u0010\u0018J\u0018\u0010\u0019\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\u0002H\u0086@¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001d\u001a\u00020\u000eH\u0000¢\u0006\u0004\b\u001b\u0010\u001cR \u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00020\u001e8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"R&\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000e0#8\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b$\u0010%\u0012\u0004\b(\u0010)\u001a\u0004\b&\u0010'R/\u00100\u001a\u0004\u0018\u00010\t2\b\u0010*\u001a\u0004\u0018\u00010\t8@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b+\u0010,\u001a\u0004\b-\u0010\u000b\"\u0004\b.\u0010/R\u0011\u00101\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b1\u00102R\u0011\u00103\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b3\u00102R\u0011\u00106\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\b4\u00105R\u0011\u00107\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b7\u00102R\u0011\u0010\f\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\b8\u00105R\u0011\u0010:\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b9\u0010\u001c¨\u0006<"}, d2 = {"Landroidx/compose/material3/DrawerState;", "", "Landroidx/compose/material3/DrawerValue;", "initialValue", "Lkotlin/Function1;", "", "confirmStateChange", "<init>", "(Landroidx/compose/material3/DrawerValue;Lr7/l;)V", "Landroidx/compose/ui/unit/Density;", "requireDensity", "()Landroidx/compose/ui/unit/Density;", "targetValue", "Landroidx/compose/animation/core/AnimationSpec;", "", "animationSpec", "velocity", "Lx6/t0;", "animateTo", "(Landroidx/compose/material3/DrawerValue;Landroidx/compose/animation/core/AnimationSpec;FLd7/d;)Ljava/lang/Object;", TtmlNode.TEXT_EMPHASIS_MARK_OPEN, "(Ld7/d;)Ljava/lang/Object;", "close", "anim", "(Landroidx/compose/material3/DrawerValue;Landroidx/compose/animation/core/AnimationSpec;Ld7/d;)Ljava/lang/Object;", "snapTo", "(Landroidx/compose/material3/DrawerValue;Ld7/d;)Ljava/lang/Object;", "requireOffset$material3_release", "()F", "requireOffset", "Landroidx/compose/material3/AnchoredDraggableState;", "anchoredDraggableState", "Landroidx/compose/material3/AnchoredDraggableState;", "getAnchoredDraggableState$material3_release", "()Landroidx/compose/material3/AnchoredDraggableState;", "Landroidx/compose/runtime/State;", "offset", "Landroidx/compose/runtime/State;", "getOffset", "()Landroidx/compose/runtime/State;", "getOffset$annotations", "()V", "<set-?>", "density$delegate", "Landroidx/compose/runtime/MutableState;", "getDensity$material3_release", "setDensity$material3_release", "(Landroidx/compose/ui/unit/Density;)V", "density", "isOpen", "()Z", "isClosed", "getCurrentValue", "()Landroidx/compose/material3/DrawerValue;", "currentValue", "isAnimationRunning", "getTargetValue", "getCurrentOffset", "currentOffset", "Companion", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DrawerState {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final AnchoredDraggableState<DrawerValue> anchoredDraggableState;

    /* JADX INFO: renamed from: density$delegate, reason: from kotlin metadata */
    private final MutableState density;
    private final State<Float> offset;

    /* JADX INFO: renamed from: androidx.compose.material3.DrawerState$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Landroidx/compose/material3/DrawerValue;", "invoke", "(Landroidx/compose/material3/DrawerValue;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<DrawerValue, Boolean> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public final Boolean invoke(DrawerValue drawerValue) {
            return Boolean.TRUE;
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\b2\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/compose/material3/DrawerState$Companion;", "", "<init>", "()V", "Lkotlin/Function1;", "Landroidx/compose/material3/DrawerValue;", "", "confirmStateChange", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/material3/DrawerState;", "Saver", "(Lr7/l;)Landroidx/compose/runtime/saveable/Saver;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        public final Saver<DrawerState, DrawerValue> Saver(l<? super DrawerValue, Boolean> confirmStateChange) {
            return SaverKt.Saver(DrawerState$Companion$Saver$1.INSTANCE, new DrawerState$Companion$Saver$2(confirmStateChange));
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DrawerState$animateTo$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0002H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"Landroidx/compose/material3/AnchoredDragScope;", "Landroidx/compose/material3/DraggableAnchors;", "Landroidx/compose/material3/DrawerValue;", "anchors", "latestTarget", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/material3/AnchoredDragScope;Landroidx/compose/material3/DraggableAnchors;Landroidx/compose/material3/DrawerValue;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.material3.DrawerState$animateTo$3", f = "NavigationDrawer.kt", l = {246}, m = "invokeSuspend")
    public static final class AnonymousClass3 extends j implements r7.r<AnchoredDragScope, DraggableAnchors<DrawerValue>, DrawerValue, d7.d<? super t0>, Object> {
        final /* synthetic */ AnimationSpec<Float> $animationSpec;
        final /* synthetic */ float $velocity;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        /* synthetic */ Object L$2;
        int label;

        /* JADX INFO: renamed from: androidx.compose.material3.DrawerState$animateTo$3$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"", "value", "velocity", "Lx6/t0;", "invoke", "(FF)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass1 extends r implements p<Float, Float, t0> {
            final /* synthetic */ AnchoredDragScope $$this$anchoredDrag;
            final /* synthetic */ h0 $prev;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(AnchoredDragScope anchoredDragScope, h0 h0Var) {
                super(2);
                this.$$this$anchoredDrag = anchoredDragScope;
                this.$prev = h0Var;
            }

            @Override // r7.p
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke(((Number) obj).floatValue(), ((Number) obj2).floatValue());
                return t0.f22605a;
            }

            public final void invoke(float f10, float f11) {
                this.$$this$anchoredDrag.dragTo(f10, f11);
                this.$prev.f19742i = f10;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(float f10, AnimationSpec<Float> animationSpec, d7.d<? super AnonymousClass3> dVar) {
            super(4, dVar);
            this.$velocity = f10;
            this.$animationSpec = animationSpec;
        }

        @Override // r7.r
        public final Object invoke(AnchoredDragScope anchoredDragScope, DraggableAnchors<DrawerValue> draggableAnchors, DrawerValue drawerValue, d7.d<? super t0> dVar) {
            AnonymousClass3 anonymousClass3 = DrawerState.this.new AnonymousClass3(this.$velocity, this.$animationSpec, dVar);
            anonymousClass3.L$0 = anchoredDragScope;
            anonymousClass3.L$1 = draggableAnchors;
            anonymousClass3.L$2 = drawerValue;
            return anonymousClass3.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                AnchoredDragScope anchoredDragScope = (AnchoredDragScope) this.L$0;
                float fPositionOf = ((DraggableAnchors) this.L$1).positionOf((DrawerValue) this.L$2);
                if (!Float.isNaN(fPositionOf)) {
                    h0 h0Var = new h0();
                    float currentOffset = Float.isNaN(DrawerState.this.getCurrentOffset()) ? 0.0f : DrawerState.this.getCurrentOffset();
                    h0Var.f19742i = currentOffset;
                    float f10 = this.$velocity;
                    AnimationSpec<Float> animationSpec = this.$animationSpec;
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(anchoredDragScope, h0Var);
                    this.L$0 = null;
                    this.L$1 = null;
                    this.label = 1;
                    Object objAnimate = SuspendAnimationKt.animate(currentOffset, fPositionOf, f10, animationSpec, anonymousClass1, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objAnimate == aVar) {
                        return aVar;
                    }
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

    public DrawerState(DrawerValue drawerValue, l<? super DrawerValue, Boolean> lVar) {
        this.anchoredDraggableState = new AnchoredDraggableState<>(drawerValue, DrawerState$anchoredDraggableState$1.INSTANCE, new DrawerState$anchoredDraggableState$2(this), NavigationDrawerKt.AnimationSpec, lVar);
        this.offset = new State<Float>() { // from class: androidx.compose.material3.DrawerState$offset$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // androidx.compose.runtime.State
            public Float getValue() {
                return Float.valueOf(this.this$0.getAnchoredDraggableState$material3_release().getOffset());
            }
        };
        this.density = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object animateTo$default(DrawerState drawerState, DrawerValue drawerValue, AnimationSpec animationSpec, float f10, d7.d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            animationSpec = NavigationDrawerKt.AnimationSpec;
        }
        if ((i10 & 4) != 0) {
            f10 = drawerState.anchoredDraggableState.getLastVelocity();
        }
        return drawerState.animateTo(drawerValue, animationSpec, f10, dVar);
    }

    @x6.e
    public static /* synthetic */ void getOffset$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Density requireDensity() {
        Density density$material3_release = getDensity$material3_release();
        if (density$material3_release != null) {
            return density$material3_release;
        }
        throw new IllegalArgumentException(("The density on BottomDrawerState (" + this + ") was not set. Did you use BottomDrawer with the BottomDrawer composable?").toString());
    }

    @x6.e
    public final Object animateTo(DrawerValue drawerValue, AnimationSpec<Float> animationSpec, d7.d<? super t0> dVar) {
        Object objAnimateTo$default = animateTo$default(this, drawerValue, animationSpec, 0.0f, dVar, 4, null);
        return objAnimateTo$default == e7.a.f15033i ? objAnimateTo$default : t0.f22605a;
    }

    public final Object close(d7.d<? super t0> dVar) {
        Object objAnimateTo$default = animateTo$default(this, DrawerValue.Closed, null, 0.0f, dVar, 6, null);
        return objAnimateTo$default == e7.a.f15033i ? objAnimateTo$default : t0.f22605a;
    }

    public final AnchoredDraggableState<DrawerValue> getAnchoredDraggableState$material3_release() {
        return this.anchoredDraggableState;
    }

    public final float getCurrentOffset() {
        return this.anchoredDraggableState.getOffset();
    }

    public final DrawerValue getCurrentValue() {
        return this.anchoredDraggableState.getCurrentValue();
    }

    public final Density getDensity$material3_release() {
        return (Density) this.density.getValue();
    }

    public final State<Float> getOffset() {
        return this.offset;
    }

    public final DrawerValue getTargetValue() {
        return this.anchoredDraggableState.getTargetValue();
    }

    public final boolean isAnimationRunning() {
        return this.anchoredDraggableState.isAnimationRunning();
    }

    public final boolean isClosed() {
        return getCurrentValue() == DrawerValue.Closed;
    }

    public final boolean isOpen() {
        return getCurrentValue() == DrawerValue.Open;
    }

    public final Object open(d7.d<? super t0> dVar) {
        Object objAnimateTo$default = animateTo$default(this, DrawerValue.Open, null, 0.0f, dVar, 6, null);
        return objAnimateTo$default == e7.a.f15033i ? objAnimateTo$default : t0.f22605a;
    }

    public final float requireOffset$material3_release() {
        return this.anchoredDraggableState.requireOffset();
    }

    public final void setDensity$material3_release(Density density) {
        this.density.setValue(density);
    }

    public final Object snapTo(DrawerValue drawerValue, d7.d<? super t0> dVar) {
        Object objSnapTo = AnchoredDraggableKt.snapTo(this.anchoredDraggableState, drawerValue, dVar);
        return objSnapTo == e7.a.f15033i ? objSnapTo : t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object animateTo(DrawerValue drawerValue, AnimationSpec<Float> animationSpec, float f10, d7.d<? super t0> dVar) {
        Object objAnchoredDrag$default = AnchoredDraggableState.anchoredDrag$default(this.anchoredDraggableState, drawerValue, null, new AnonymousClass3(f10, animationSpec, null), dVar, 2, null);
        return objAnchoredDrag$default == e7.a.f15033i ? objAnchoredDrag$default : t0.f22605a;
    }

    public /* synthetic */ DrawerState(DrawerValue drawerValue, l lVar, int i10, kotlin.jvm.internal.h hVar) {
        this(drawerValue, (i10 & 2) != 0 ? AnonymousClass1.INSTANCE : lVar);
    }
}
