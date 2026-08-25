package androidx.compose.material3;

import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.ui.unit.Density;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0018\b\u0007\u0018\u0000 ?2\u00020\u0001:\u0001?B;\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nBC\b\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0014\u001a\u00020\u0013H\u0086@¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u0013H\u0086@¢\u0006\u0004\b\u0016\u0010\u0015J\u0010\u0010\u0017\u001a\u00020\u0013H\u0086@¢\u0006\u0004\b\u0017\u0010\u0015J\u0010\u0010\u0018\u001a\u00020\u0013H\u0086@¢\u0006\u0004\b\u0018\u0010\u0015J\"\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u00042\b\b\u0002\u0010\u001a\u001a\u00020\u0010H\u0080@¢\u0006\u0004\b\u001b\u0010\u001cJ\u0018\u0010 \u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0004H\u0080@¢\u0006\u0004\b\u001e\u0010\u001fJ\u0018\u0010#\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u0010H\u0080@¢\u0006\u0004\b!\u0010\"R\u001a\u0010\u0003\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0003\u0010$\u001a\u0004\b%\u0010&R\u001a\u0010\b\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\b\u0010$\u001a\u0004\b'\u0010&R(\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00040(8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R$\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\f\u0010/\u001a\u0004\b0\u0010\u000f\"\u0004\b1\u00102R\u0011\u00105\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b3\u00104R\u0011\u0010\u0019\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b6\u00104R\u0011\u00107\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\b7\u0010&R\u0011\u00109\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\b8\u0010&R\u0011\u0010;\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\b:\u0010&R\u0016\u0010>\u001a\u0004\u0018\u00010\u00108@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b<\u0010=¨\u0006@"}, d2 = {"Landroidx/compose/material3/SheetState;", "", "", "skipPartiallyExpanded", "Landroidx/compose/material3/SheetValue;", "initialValue", "Lkotlin/Function1;", "confirmValueChange", "skipHiddenState", "<init>", "(ZLandroidx/compose/material3/SheetValue;Lr7/l;Z)V", "Landroidx/compose/ui/unit/Density;", "density", "(ZLandroidx/compose/ui/unit/Density;Landroidx/compose/material3/SheetValue;Lr7/l;Z)V", "requireDensity", "()Landroidx/compose/ui/unit/Density;", "", "requireOffset", "()F", "Lx6/t0;", "expand", "(Ld7/d;)Ljava/lang/Object;", "partialExpand", "show", "hide", "targetValue", "velocity", "animateTo$material3_release", "(Landroidx/compose/material3/SheetValue;FLd7/d;)Ljava/lang/Object;", "animateTo", "snapTo$material3_release", "(Landroidx/compose/material3/SheetValue;Ld7/d;)Ljava/lang/Object;", "snapTo", "settle$material3_release", "(FLd7/d;)Ljava/lang/Object;", "settle", "Z", "getSkipPartiallyExpanded$material3_release", "()Z", "getSkipHiddenState$material3_release", "Landroidx/compose/material3/AnchoredDraggableState;", "anchoredDraggableState", "Landroidx/compose/material3/AnchoredDraggableState;", "getAnchoredDraggableState$material3_release", "()Landroidx/compose/material3/AnchoredDraggableState;", "setAnchoredDraggableState$material3_release", "(Landroidx/compose/material3/AnchoredDraggableState;)V", "Landroidx/compose/ui/unit/Density;", "getDensity$material3_release", "setDensity$material3_release", "(Landroidx/compose/ui/unit/Density;)V", "getCurrentValue", "()Landroidx/compose/material3/SheetValue;", "currentValue", "getTargetValue", "isVisible", "getHasExpandedState", "hasExpandedState", "getHasPartiallyExpandedState", "hasPartiallyExpandedState", "getOffset$material3_release", "()Ljava/lang/Float;", "offset", "Companion", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SheetState {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private AnchoredDraggableState<SheetValue> anchoredDraggableState;
    private Density density;
    private final boolean skipHiddenState;
    private final boolean skipPartiallyExpanded;

    /* JADX INFO: renamed from: androidx.compose.material3.SheetState$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Landroidx/compose/material3/SheetValue;", "invoke", "(Landroidx/compose/material3/SheetValue;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<SheetValue, Boolean> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public final Boolean invoke(SheetValue sheetValue) {
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SheetState$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Landroidx/compose/material3/SheetValue;", "invoke", "(Landroidx/compose/material3/SheetValue;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements l<SheetValue, Boolean> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(1);
        }

        @Override // r7.l
        public final Boolean invoke(SheetValue sheetValue) {
            return Boolean.TRUE;
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J=\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00070\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00040\u00062\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\r\u0010\u000eJ7\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00070\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00040\u0006H\u0007¢\u0006\u0004\b\r\u0010\u000f¨\u0006\u0010"}, d2 = {"Landroidx/compose/material3/SheetState$Companion;", "", "<init>", "()V", "", "skipPartiallyExpanded", "Lkotlin/Function1;", "Landroidx/compose/material3/SheetValue;", "confirmValueChange", "Landroidx/compose/ui/unit/Density;", "density", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/material3/SheetState;", "Saver", "(ZLr7/l;Landroidx/compose/ui/unit/Density;)Landroidx/compose/runtime/saveable/Saver;", "(ZLr7/l;)Landroidx/compose/runtime/saveable/Saver;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        public final Saver<SheetState, SheetValue> Saver(boolean skipPartiallyExpanded, l<? super SheetValue, Boolean> confirmValueChange, Density density) {
            return SaverKt.Saver(SheetState$Companion$Saver$1.INSTANCE, new SheetState$Companion$Saver$2(skipPartiallyExpanded, density, confirmValueChange));
        }

        private Companion() {
        }

        @x6.e
        public final Saver<SheetState, SheetValue> Saver(boolean skipPartiallyExpanded, l<? super SheetValue, Boolean> confirmValueChange) {
            return SaverKt.Saver(SheetState$Companion$Saver$3.INSTANCE, new SheetState$Companion$Saver$4(skipPartiallyExpanded, confirmValueChange));
        }
    }

    @x6.e
    public SheetState(boolean z, SheetValue sheetValue, l<? super SheetValue, Boolean> lVar, boolean z5) {
        this.skipPartiallyExpanded = z;
        this.skipHiddenState = z5;
        if (z && sheetValue == SheetValue.PartiallyExpanded) {
            throw new IllegalArgumentException("The initial value must not be set to PartiallyExpanded if skipPartiallyExpanded is set to true.");
        }
        if (z5 && sheetValue == SheetValue.Hidden) {
            throw new IllegalArgumentException("The initial value must not be set to Hidden if skipHiddenState is set to true.");
        }
        this.anchoredDraggableState = new AnchoredDraggableState<>(sheetValue, new SheetState$anchoredDraggableState$1(this), new SheetState$anchoredDraggableState$2(this), AnchoredDraggableDefaults.INSTANCE.getAnimationSpec(), lVar);
    }

    public static /* synthetic */ Object animateTo$material3_release$default(SheetState sheetState, SheetValue sheetValue, float f10, d7.d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            f10 = sheetState.anchoredDraggableState.getLastVelocity();
        }
        return sheetState.animateTo$material3_release(sheetValue, f10, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Density requireDensity() {
        Density density = this.density;
        if (density != null) {
            return density;
        }
        throw new IllegalArgumentException("SheetState did not have a density attached. Are you using SheetState with BottomSheetScaffold or ModalBottomSheet component?");
    }

    public final Object animateTo$material3_release(SheetValue sheetValue, float f10, d7.d<? super t0> dVar) {
        Object objAnimateTo = AnchoredDraggableKt.animateTo(this.anchoredDraggableState, sheetValue, f10, dVar);
        return objAnimateTo == e7.a.f15033i ? objAnimateTo : t0.f22605a;
    }

    public final Object expand(d7.d<? super t0> dVar) {
        Object objAnimateTo$default = AnchoredDraggableKt.animateTo$default(this.anchoredDraggableState, SheetValue.Expanded, 0.0f, dVar, 2, null);
        return objAnimateTo$default == e7.a.f15033i ? objAnimateTo$default : t0.f22605a;
    }

    public final AnchoredDraggableState<SheetValue> getAnchoredDraggableState$material3_release() {
        return this.anchoredDraggableState;
    }

    public final SheetValue getCurrentValue() {
        return this.anchoredDraggableState.getCurrentValue();
    }

    /* JADX INFO: renamed from: getDensity$material3_release, reason: from getter */
    public final Density getDensity() {
        return this.density;
    }

    public final boolean getHasExpandedState() {
        return this.anchoredDraggableState.getAnchors().hasAnchorFor(SheetValue.Expanded);
    }

    public final boolean getHasPartiallyExpandedState() {
        return this.anchoredDraggableState.getAnchors().hasAnchorFor(SheetValue.PartiallyExpanded);
    }

    public final Float getOffset$material3_release() {
        return Float.valueOf(this.anchoredDraggableState.getOffset());
    }

    /* JADX INFO: renamed from: getSkipHiddenState$material3_release, reason: from getter */
    public final boolean getSkipHiddenState() {
        return this.skipHiddenState;
    }

    /* JADX INFO: renamed from: getSkipPartiallyExpanded$material3_release, reason: from getter */
    public final boolean getSkipPartiallyExpanded() {
        return this.skipPartiallyExpanded;
    }

    public final SheetValue getTargetValue() {
        return this.anchoredDraggableState.getTargetValue();
    }

    public final Object hide(d7.d<? super t0> dVar) {
        if (this.skipHiddenState) {
            throw new IllegalStateException("Attempted to animate to hidden when skipHiddenState was enabled. Set skipHiddenState to false to use this function.");
        }
        Object objAnimateTo$material3_release$default = animateTo$material3_release$default(this, SheetValue.Hidden, 0.0f, dVar, 2, null);
        return objAnimateTo$material3_release$default == e7.a.f15033i ? objAnimateTo$material3_release$default : t0.f22605a;
    }

    public final boolean isVisible() {
        return this.anchoredDraggableState.getCurrentValue() != SheetValue.Hidden;
    }

    public final Object partialExpand(d7.d<? super t0> dVar) {
        if (this.skipPartiallyExpanded) {
            throw new IllegalStateException("Attempted to animate to partial expanded when skipPartiallyExpanded was enabled. Set skipPartiallyExpanded to false to use this function.");
        }
        Object objAnimateTo$material3_release$default = animateTo$material3_release$default(this, SheetValue.PartiallyExpanded, 0.0f, dVar, 2, null);
        return objAnimateTo$material3_release$default == e7.a.f15033i ? objAnimateTo$material3_release$default : t0.f22605a;
    }

    public final float requireOffset() {
        return this.anchoredDraggableState.requireOffset();
    }

    public final void setAnchoredDraggableState$material3_release(AnchoredDraggableState<SheetValue> anchoredDraggableState) {
        this.anchoredDraggableState = anchoredDraggableState;
    }

    public final void setDensity$material3_release(Density density) {
        this.density = density;
    }

    public final Object settle$material3_release(float f10, d7.d<? super t0> dVar) {
        Object obj = this.anchoredDraggableState.settle(f10, dVar);
        return obj == e7.a.f15033i ? obj : t0.f22605a;
    }

    public final Object show(d7.d<? super t0> dVar) {
        Object objAnimateTo$material3_release$default = animateTo$material3_release$default(this, getHasPartiallyExpandedState() ? SheetValue.PartiallyExpanded : SheetValue.Expanded, 0.0f, dVar, 2, null);
        return objAnimateTo$material3_release$default == e7.a.f15033i ? objAnimateTo$material3_release$default : t0.f22605a;
    }

    public final Object snapTo$material3_release(SheetValue sheetValue, d7.d<? super t0> dVar) {
        Object objSnapTo = AnchoredDraggableKt.snapTo(this.anchoredDraggableState, sheetValue, dVar);
        return objSnapTo == e7.a.f15033i ? objSnapTo : t0.f22605a;
    }

    public /* synthetic */ SheetState(boolean z, SheetValue sheetValue, l lVar, boolean z5, int i10, kotlin.jvm.internal.h hVar) {
        this(z, (i10 & 2) != 0 ? SheetValue.Hidden : sheetValue, (i10 & 4) != 0 ? AnonymousClass1.INSTANCE : lVar, (i10 & 8) != 0 ? false : z5);
    }

    public /* synthetic */ SheetState(boolean z, Density density, SheetValue sheetValue, l lVar, boolean z5, int i10, kotlin.jvm.internal.h hVar) {
        this(z, density, (i10 & 4) != 0 ? SheetValue.Hidden : sheetValue, (i10 & 8) != 0 ? AnonymousClass2.INSTANCE : lVar, (i10 & 16) != 0 ? false : z5);
    }

    public SheetState(boolean z, Density density, SheetValue sheetValue, l<? super SheetValue, Boolean> lVar, boolean z5) {
        this(z, sheetValue, lVar, z5);
        this.density = density;
    }
}
