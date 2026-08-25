package androidx.compose.material3;

import androidx.compose.foundation.lazy.grid.LazyGridItemInfo;
import androidx.compose.foundation.lazy.grid.LazyGridState;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import ka.k0;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class DatePickerKt$YearPicker$1$2$1$1$1$1 extends r implements l<SemanticsPropertyReceiver, t0> {
    final /* synthetic */ k0 $coroutineScope;
    final /* synthetic */ int $it;
    final /* synthetic */ LazyGridState $lazyGridState;
    final /* synthetic */ String $scrollToEarlierYearsLabel;
    final /* synthetic */ String $scrollToLaterYearsLabel;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DatePickerKt$YearPicker$1$2$1$1$1$1(LazyGridState lazyGridState, int i10, k0 k0Var, String str, String str2) {
        super(1);
        this.$lazyGridState = lazyGridState;
        this.$it = i10;
        this.$coroutineScope = k0Var;
        this.$scrollToEarlierYearsLabel = str;
        this.$scrollToLaterYearsLabel = str2;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((SemanticsPropertyReceiver) obj);
        return t0.f22605a;
    }

    public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        LazyGridItemInfo lazyGridItemInfo;
        SemanticsPropertiesKt.setCustomActions(semanticsPropertyReceiver, (this.$lazyGridState.getFirstVisibleItemIndex() == this.$it || ((lazyGridItemInfo = (LazyGridItemInfo) x.y0(this.$lazyGridState.getLayoutInfo().getVisibleItemsInfo())) != null && lazyGridItemInfo.getIndex() == this.$it)) ? DatePickerKt.customScrollActions(this.$lazyGridState, this.$coroutineScope, this.$scrollToEarlierYearsLabel, this.$scrollToLaterYearsLabel) : z.f19728i);
    }
}
