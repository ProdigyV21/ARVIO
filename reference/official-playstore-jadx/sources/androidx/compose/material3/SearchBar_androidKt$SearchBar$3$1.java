package androidx.compose.material3;

import androidx.compose.runtime.State;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpKt;
import androidx.compose.ui.util.MathHelpersKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"<anonymous>", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "invoke-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class SearchBar_androidKt$SearchBar$3$1 extends r implements q<MeasureScope, Measurable, Constraints, MeasureResult> {
    final /* synthetic */ State<Float> $animationProgress;
    final /* synthetic */ State<Dp> $topPadding;

    /* JADX INFO: renamed from: androidx.compose.material3.SearchBar_androidKt$SearchBar$3$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<Placeable.PlacementScope, t0> {
        final /* synthetic */ int $animatedTopPadding;
        final /* synthetic */ Placeable $placeable;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Placeable placeable, int i10) {
            super(1);
            this.$placeable = placeable;
            this.$animatedTopPadding = i10;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Placeable.PlacementScope) obj);
            return t0.f22605a;
        }

        public final void invoke(Placeable.PlacementScope placementScope) {
            Placeable.PlacementScope.placeRelative$default(placementScope, this.$placeable, 0, this.$animatedTopPadding, 0.0f, 4, null);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchBar_androidKt$SearchBar$3$1(State<Dp> state, State<Float> state2) {
        super(3);
        this.$topPadding = state;
        this.$animationProgress = state2;
    }

    @Override // r7.q
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        return m1819invoke3p2s80s((MeasureScope) obj, (Measurable) obj2, ((Constraints) obj3).getValue());
    }

    /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
    public final MeasureResult m1819invoke3p2s80s(MeasureScope measureScope, Measurable measurable, long j10) {
        int iMo279roundToPx0680j_4 = measureScope.mo279roundToPx0680j_4(DpKt.m5721lerpMdfbLM(this.$topPadding.getValue().m5692unboximpl(), Dp.m5678constructorimpl(0), this.$animationProgress.getValue().floatValue()));
        int iMax = Math.max(Constraints.m5648getMinWidthimpl(j10), measureScope.mo279roundToPx0680j_4(SearchBar_androidKt.getSearchBarMinWidth()));
        int iMin = Math.min(Constraints.m5646getMaxWidthimpl(j10), measureScope.mo279roundToPx0680j_4(SearchBar_androidKt.SearchBarMaxWidth));
        if (iMax > iMin) {
            iMax = iMin;
        }
        int iMax2 = Math.max(Constraints.m5647getMinHeightimpl(j10), measureScope.mo279roundToPx0680j_4(SearchBarDefaults.INSTANCE.m1811getInputFieldHeightD9Ej5fM()));
        int iM5645getMaxHeightimpl = Constraints.m5645getMaxHeightimpl(j10);
        if (iMax2 > iM5645getMaxHeightimpl) {
            iMax2 = iM5645getMaxHeightimpl;
        }
        int iM5646getMaxWidthimpl = Constraints.m5646getMaxWidthimpl(j10);
        int iM5645getMaxHeightimpl2 = Constraints.m5645getMaxHeightimpl(j10);
        int iLerp = MathHelpersKt.lerp(iMax, iM5646getMaxWidthimpl, this.$animationProgress.getValue().floatValue());
        int iLerp2 = MathHelpersKt.lerp(iMax2, iM5645getMaxHeightimpl2, this.$animationProgress.getValue().floatValue()) + iMo279roundToPx0680j_4;
        return MeasureScope.CC.q(measureScope, iLerp, iLerp2, null, new AnonymousClass1(measurable.mo4631measureBRTryo0(ConstraintsKt.m5663offsetNN6EwU$default(Constraints.INSTANCE.m5654fixedJhjzzOo(iLerp, iLerp2), 0, -iMo279roundToPx0680j_4, 1, null)), iMo279roundToPx0680j_4), 4, null);
    }
}
