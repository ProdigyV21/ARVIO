package androidx.compose.material3;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"<anonymous>", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "invoke-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class ExposedDropdownMenu_androidKt$ExposedDropdownMenuBox$scope$1$1$exposedDropdownSize$1 extends r implements q<MeasureScope, Measurable, Constraints, MeasureResult> {
    final /* synthetic */ MutableIntState $anchorWidth$delegate;
    final /* synthetic */ boolean $matchTextFieldWidth;
    final /* synthetic */ MutableIntState $menuMaxHeight$delegate;

    /* JADX INFO: renamed from: androidx.compose.material3.ExposedDropdownMenu_androidKt$ExposedDropdownMenuBox$scope$1$1$exposedDropdownSize$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<Placeable.PlacementScope, t0> {
        final /* synthetic */ Placeable $placeable;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Placeable placeable) {
            super(1);
            this.$placeable = placeable;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Placeable.PlacementScope) obj);
            return t0.f22605a;
        }

        public final void invoke(Placeable.PlacementScope placementScope) {
            Placeable.PlacementScope.place$default(placementScope, this.$placeable, 0, 0, 0.0f, 4, null);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExposedDropdownMenu_androidKt$ExposedDropdownMenuBox$scope$1$1$exposedDropdownSize$1(boolean z, MutableIntState mutableIntState, MutableIntState mutableIntState2) {
        super(3);
        this.$matchTextFieldWidth = z;
        this.$anchorWidth$delegate = mutableIntState;
        this.$menuMaxHeight$delegate = mutableIntState2;
    }

    @Override // r7.q
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        return m1559invoke3p2s80s((MeasureScope) obj, (Measurable) obj2, ((Constraints) obj3).getValue());
    }

    /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
    public final MeasureResult m1559invoke3p2s80s(MeasureScope measureScope, Measurable measurable, long j10) {
        int iM5660constrainWidthK40F9xA = ConstraintsKt.m5660constrainWidthK40F9xA(j10, this.$anchorWidth$delegate.getIntValue());
        int iM5659constrainHeightK40F9xA = ConstraintsKt.m5659constrainHeightK40F9xA(j10, this.$menuMaxHeight$delegate.getIntValue());
        int iM5648getMinWidthimpl = this.$matchTextFieldWidth ? iM5660constrainWidthK40F9xA : Constraints.m5648getMinWidthimpl(j10);
        if (!this.$matchTextFieldWidth) {
            iM5660constrainWidthK40F9xA = Constraints.m5646getMaxWidthimpl(j10);
        }
        Placeable placeableMo4631measureBRTryo0 = measurable.mo4631measureBRTryo0(Constraints.m5637copyZbe2FdA$default(j10, iM5648getMinWidthimpl, iM5660constrainWidthK40F9xA, 0, iM5659constrainHeightK40F9xA, 4, null));
        return MeasureScope.CC.q(measureScope, placeableMo4631measureBRTryo0.getWidth(), placeableMo4631measureBRTryo0.getHeight(), null, new AnonymousClass1(placeableMo4631measureBRTryo0), 4, null);
    }
}
