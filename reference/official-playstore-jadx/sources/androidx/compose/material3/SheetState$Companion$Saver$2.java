package androidx.compose.material3;

import androidx.compose.ui.unit.Density;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/material3/SheetState;", "savedValue", "Landroidx/compose/material3/SheetValue;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class SheetState$Companion$Saver$2 extends r implements l<SheetValue, SheetState> {
    final /* synthetic */ l<SheetValue, Boolean> $confirmValueChange;
    final /* synthetic */ Density $density;
    final /* synthetic */ boolean $skipPartiallyExpanded;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SheetState$Companion$Saver$2(boolean z, Density density, l<? super SheetValue, Boolean> lVar) {
        super(1);
        this.$skipPartiallyExpanded = z;
        this.$density = density;
        this.$confirmValueChange = lVar;
    }

    @Override // r7.l
    public final SheetState invoke(SheetValue sheetValue) {
        return new SheetState(this.$skipPartiallyExpanded, this.$density, sheetValue, this.$confirmValueChange, false, 16, null);
    }
}
