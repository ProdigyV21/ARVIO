package androidx.compose.material3;

import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.ui.unit.Density;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0007\u0018\u0000 /2\u00020\u0001:\u0001/BP\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006\u0012!\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\t0\u0006¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\t¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0012H\u0007¢\u0006\u0004\b\u0014\u0010\u0015J\u0018\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u0002H\u0086@¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\u0017H\u0086@¢\u0006\u0004\b\u001a\u0010\u001bJ\u0018\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\u0002H\u0086@¢\u0006\u0004\b\u001c\u0010\u0019R\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR \u0010!\u001a\b\u0012\u0004\u0012\u00020\u00020 8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$R\u0014\u0010&\u001a\u00020\t8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b%\u0010\u0011R\u0011\u0010)\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\b'\u0010(R\u0011\u0010\u0016\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\b*\u0010(R\u0011\u0010,\u001a\u00020\t8G¢\u0006\u0006\u001a\u0004\b+\u0010\u0011R\u0011\u0010.\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\b-\u0010(¨\u00060"}, d2 = {"Landroidx/compose/material3/SwipeToDismissBoxState;", "", "Landroidx/compose/material3/SwipeToDismissBoxValue;", "initialValue", "Landroidx/compose/ui/unit/Density;", "density", "Lkotlin/Function1;", "", "confirmValueChange", "", "Lx6/y;", ContentDisposition.Parameters.Name, "totalDistance", "positionalThreshold", "<init>", "(Landroidx/compose/material3/SwipeToDismissBoxValue;Landroidx/compose/ui/unit/Density;Lr7/l;Lr7/l;)V", "requireOffset", "()F", "Landroidx/compose/material3/DismissDirection;", "direction", "isDismissed", "(Landroidx/compose/material3/DismissDirection;)Z", "targetValue", "Lx6/t0;", "snapTo", "(Landroidx/compose/material3/SwipeToDismissBoxValue;Ld7/d;)Ljava/lang/Object;", "reset", "(Ld7/d;)Ljava/lang/Object;", "dismiss", "Landroidx/compose/ui/unit/Density;", "getDensity$material3_release", "()Landroidx/compose/ui/unit/Density;", "Landroidx/compose/material3/AnchoredDraggableState;", "anchoredDraggableState", "Landroidx/compose/material3/AnchoredDraggableState;", "getAnchoredDraggableState$material3_release", "()Landroidx/compose/material3/AnchoredDraggableState;", "getOffset$material3_release", "offset", "getCurrentValue", "()Landroidx/compose/material3/SwipeToDismissBoxValue;", "currentValue", "getTargetValue", "getProgress", "progress", "getDismissDirection", "dismissDirection", "Companion", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SwipeToDismissBoxState {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final AnchoredDraggableState<SwipeToDismissBoxValue> anchoredDraggableState;
    private final Density density;

    /* JADX INFO: renamed from: androidx.compose.material3.SwipeToDismissBoxState$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Landroidx/compose/material3/SwipeToDismissBoxValue;", "invoke", "(Landroidx/compose/material3/SwipeToDismissBoxValue;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<SwipeToDismissBoxValue, Boolean> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public final Boolean invoke(SwipeToDismissBoxValue swipeToDismissBoxValue) {
            return Boolean.TRUE;
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JX\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00050\u000f2\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00042!\u0010\f\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\b0\u00042\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Landroidx/compose/material3/SwipeToDismissBoxState$Companion;", "", "<init>", "()V", "Lkotlin/Function1;", "Landroidx/compose/material3/SwipeToDismissBoxValue;", "", "confirmValueChange", "", "Lx6/y;", ContentDisposition.Parameters.Name, "totalDistance", "positionalThreshold", "Landroidx/compose/ui/unit/Density;", "density", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/material3/SwipeToDismissBoxState;", "Saver", "(Lr7/l;Lr7/l;Landroidx/compose/ui/unit/Density;)Landroidx/compose/runtime/saveable/Saver;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        public final Saver<SwipeToDismissBoxState, SwipeToDismissBoxValue> Saver(l<? super SwipeToDismissBoxValue, Boolean> confirmValueChange, l<? super Float, Float> positionalThreshold, Density density) {
            return SaverKt.Saver(SwipeToDismissBoxState$Companion$Saver$1.INSTANCE, new SwipeToDismissBoxState$Companion$Saver$2(density, confirmValueChange, positionalThreshold));
        }

        private Companion() {
        }
    }

    public SwipeToDismissBoxState(SwipeToDismissBoxValue swipeToDismissBoxValue, Density density, l<? super SwipeToDismissBoxValue, Boolean> lVar, l<? super Float, Float> lVar2) {
        this.density = density;
        this.anchoredDraggableState = new AnchoredDraggableState<>(swipeToDismissBoxValue, lVar2, new SwipeToDismissBoxState$anchoredDraggableState$1(this), AnchoredDraggableDefaults.INSTANCE.getAnimationSpec(), lVar);
    }

    public final Object dismiss(SwipeToDismissBoxValue swipeToDismissBoxValue, d7.d<? super t0> dVar) {
        Object objAnimateTo$default = AnchoredDraggableKt.animateTo$default(this.anchoredDraggableState, swipeToDismissBoxValue, 0.0f, dVar, 2, null);
        return objAnimateTo$default == e7.a.f15033i ? objAnimateTo$default : t0.f22605a;
    }

    public final AnchoredDraggableState<SwipeToDismissBoxValue> getAnchoredDraggableState$material3_release() {
        return this.anchoredDraggableState;
    }

    public final SwipeToDismissBoxValue getCurrentValue() {
        return this.anchoredDraggableState.getCurrentValue();
    }

    /* JADX INFO: renamed from: getDensity$material3_release, reason: from getter */
    public final Density getDensity() {
        return this.density;
    }

    public final SwipeToDismissBoxValue getDismissDirection() {
        return (getOffset$material3_release() == 0.0f || Float.isNaN(getOffset$material3_release())) ? SwipeToDismissBoxValue.Settled : getOffset$material3_release() > 0.0f ? SwipeToDismissBoxValue.StartToEnd : SwipeToDismissBoxValue.EndToStart;
    }

    public final float getOffset$material3_release() {
        return this.anchoredDraggableState.getOffset();
    }

    public final float getProgress() {
        return this.anchoredDraggableState.getProgress();
    }

    public final SwipeToDismissBoxValue getTargetValue() {
        return this.anchoredDraggableState.getTargetValue();
    }

    @x6.e
    public final /* synthetic */ boolean isDismissed(DismissDirection direction) {
        return getCurrentValue() == (direction == DismissDirection.StartToEnd ? SwipeToDismissBoxValue.StartToEnd : SwipeToDismissBoxValue.EndToStart);
    }

    public final float requireOffset() {
        return this.anchoredDraggableState.requireOffset();
    }

    public final Object reset(d7.d<? super t0> dVar) {
        Object objAnimateTo$default = AnchoredDraggableKt.animateTo$default(this.anchoredDraggableState, SwipeToDismissBoxValue.Settled, 0.0f, dVar, 2, null);
        return objAnimateTo$default == e7.a.f15033i ? objAnimateTo$default : t0.f22605a;
    }

    public final Object snapTo(SwipeToDismissBoxValue swipeToDismissBoxValue, d7.d<? super t0> dVar) {
        Object objSnapTo = AnchoredDraggableKt.snapTo(this.anchoredDraggableState, swipeToDismissBoxValue, dVar);
        return objSnapTo == e7.a.f15033i ? objSnapTo : t0.f22605a;
    }

    public /* synthetic */ SwipeToDismissBoxState(SwipeToDismissBoxValue swipeToDismissBoxValue, Density density, l lVar, l lVar2, int i10, kotlin.jvm.internal.h hVar) {
        this(swipeToDismissBoxValue, density, (i10 & 4) != 0 ? AnonymousClass1.INSTANCE : lVar, lVar2);
    }
}
