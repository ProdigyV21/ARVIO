package androidx.compose.material3;

import androidx.compose.ui.unit.Density;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/compose/material3/SheetState;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class SheetDefaultsKt$rememberSheetState$2$1 extends r implements r7.a<SheetState> {
    final /* synthetic */ l<SheetValue, Boolean> $confirmValueChange;
    final /* synthetic */ Density $density;
    final /* synthetic */ SheetValue $initialValue;
    final /* synthetic */ boolean $skipHiddenState;
    final /* synthetic */ boolean $skipPartiallyExpanded;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SheetDefaultsKt$rememberSheetState$2$1(boolean z, Density density, SheetValue sheetValue, l<? super SheetValue, Boolean> lVar, boolean z5) {
        super(0);
        this.$skipPartiallyExpanded = z;
        this.$density = density;
        this.$initialValue = sheetValue;
        this.$confirmValueChange = lVar;
        this.$skipHiddenState = z5;
    }

    @Override // r7.a
    public final SheetState invoke() {
        return new SheetState(this.$skipPartiallyExpanded, this.$density, this.$initialValue, this.$confirmValueChange, this.$skipHiddenState);
    }
}
