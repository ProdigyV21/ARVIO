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
public final class ModalBottomSheet_androidKt$ModalBottomSheet$3$1$5$1$1$1 extends r implements l<SemanticsPropertyReceiver, t0> {
    final /* synthetic */ r7.a<t0> $animateToDismiss;
    final /* synthetic */ String $collapseActionLabel;
    final /* synthetic */ String $dismissActionLabel;
    final /* synthetic */ String $expandActionLabel;
    final /* synthetic */ k0 $scope;
    final /* synthetic */ SheetState $sheetState;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModalBottomSheet_androidKt$ModalBottomSheet$3$1$5$1$1$1(SheetState sheetState, String str, String str2, String str3, r7.a<t0> aVar, k0 k0Var) {
        super(1);
        this.$sheetState = sheetState;
        this.$dismissActionLabel = str;
        this.$expandActionLabel = str2;
        this.$collapseActionLabel = str3;
        this.$animateToDismiss = aVar;
        this.$scope = k0Var;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((SemanticsPropertyReceiver) obj);
        return t0.f22605a;
    }

    public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SheetState sheetState = this.$sheetState;
        String str = this.$dismissActionLabel;
        String str2 = this.$expandActionLabel;
        String str3 = this.$collapseActionLabel;
        r7.a<t0> aVar = this.$animateToDismiss;
        k0 k0Var = this.$scope;
        SemanticsPropertiesKt.dismiss(semanticsPropertyReceiver, str, new ModalBottomSheet_androidKt$ModalBottomSheet$3$1$5$1$1$1$1$1(aVar));
        if (sheetState.getCurrentValue() == SheetValue.PartiallyExpanded) {
            SemanticsPropertiesKt.expand(semanticsPropertyReceiver, str2, new ModalBottomSheet_androidKt$ModalBottomSheet$3$1$5$1$1$1$1$2(sheetState, k0Var, sheetState));
        } else if (sheetState.getHasPartiallyExpandedState()) {
            SemanticsPropertiesKt.collapse(semanticsPropertyReceiver, str3, new ModalBottomSheet_androidKt$ModalBottomSheet$3$1$5$1$1$1$1$3(sheetState, k0Var));
        }
    }
}
