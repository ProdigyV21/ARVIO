package androidx.compose.material3;

import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import ka.k0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1 extends r implements l<SemanticsPropertyReceiver, t0> {
    final /* synthetic */ String $dismissActionLabel;
    final /* synthetic */ String $expandActionLabel;
    final /* synthetic */ String $partialExpandActionLabel;
    final /* synthetic */ k0 $scope;
    final /* synthetic */ boolean $sheetSwipeEnabled;
    final /* synthetic */ SheetState $state;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1(SheetState sheetState, boolean z, String str, String str2, String str3, k0 k0Var) {
        super(1);
        this.$state = sheetState;
        this.$sheetSwipeEnabled = z;
        this.$expandActionLabel = str;
        this.$partialExpandActionLabel = str2;
        this.$dismissActionLabel = str3;
        this.$scope = k0Var;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((SemanticsPropertyReceiver) obj);
        return t0.f22605a;
    }

    public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SheetState sheetState = this.$state;
        boolean z = this.$sheetSwipeEnabled;
        String str = this.$expandActionLabel;
        String str2 = this.$partialExpandActionLabel;
        String str3 = this.$dismissActionLabel;
        k0 k0Var = this.$scope;
        if (sheetState.getAnchoredDraggableState$material3_release().getAnchors().getSize() <= 1 || !z) {
            return;
        }
        SheetValue currentValue = sheetState.getCurrentValue();
        SheetValue sheetValue = SheetValue.PartiallyExpanded;
        if (currentValue == sheetValue) {
            if (((Boolean) sheetState.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.Expanded)).booleanValue()) {
                SemanticsPropertiesKt.expand(semanticsPropertyReceiver, str, new BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1$1$1(k0Var, sheetState));
            }
        } else if (((Boolean) sheetState.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(sheetValue)).booleanValue()) {
            SemanticsPropertiesKt.collapse(semanticsPropertyReceiver, str2, new BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1$1$2(k0Var, sheetState));
        }
        if (sheetState.getSkipHiddenState()) {
            return;
        }
        SemanticsPropertiesKt.dismiss(semanticsPropertyReceiver, str3, new BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1$1$3(k0Var, sheetState));
    }
}
