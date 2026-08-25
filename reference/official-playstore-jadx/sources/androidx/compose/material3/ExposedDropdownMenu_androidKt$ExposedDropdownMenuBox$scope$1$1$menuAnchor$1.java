package androidx.compose.material3;

import android.view.View;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/layout/LayoutCoordinates;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ExposedDropdownMenu_androidKt$ExposedDropdownMenuBox$scope$1$1$menuAnchor$1 extends r implements l<LayoutCoordinates, t0> {
    final /* synthetic */ MutableState<LayoutCoordinates> $anchorCoordinates$delegate;
    final /* synthetic */ MutableIntState $anchorWidth$delegate;
    final /* synthetic */ MutableIntState $menuMaxHeight$delegate;
    final /* synthetic */ int $verticalMargin;
    final /* synthetic */ View $view;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExposedDropdownMenu_androidKt$ExposedDropdownMenuBox$scope$1$1$menuAnchor$1(View view, int i10, MutableState<LayoutCoordinates> mutableState, MutableIntState mutableIntState, MutableIntState mutableIntState2) {
        super(1);
        this.$view = view;
        this.$verticalMargin = i10;
        this.$anchorCoordinates$delegate = mutableState;
        this.$anchorWidth$delegate = mutableIntState;
        this.$menuMaxHeight$delegate = mutableIntState2;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((LayoutCoordinates) obj);
        return t0.f22605a;
    }

    public final void invoke(LayoutCoordinates layoutCoordinates) {
        this.$anchorCoordinates$delegate.setValue(layoutCoordinates);
        this.$anchorWidth$delegate.setIntValue(IntSize.m5844getWidthimpl(layoutCoordinates.mo4638getSizeYbymL2g()));
        this.$menuMaxHeight$delegate.setIntValue(ExposedDropdownMenu_androidKt.calculateMaxHeight(ExposedDropdownMenu_androidKt.getWindowBounds(this.$view.getRootView()), ExposedDropdownMenu_androidKt.getAnchorBounds(ExposedDropdownMenu_androidKt.ExposedDropdownMenuBox$lambda$2(this.$anchorCoordinates$delegate)), this.$verticalMargin));
    }
}
