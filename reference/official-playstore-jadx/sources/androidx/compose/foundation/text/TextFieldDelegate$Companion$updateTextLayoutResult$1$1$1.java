package androidx.compose.foundation.text;

import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/graphics/Matrix;", "matrix", "Lx6/t0;", "invoke-58bKbWc", "([F)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class TextFieldDelegate$Companion$updateTextLayoutResult$1$1$1 extends r implements l<Matrix, t0> {
    final /* synthetic */ LayoutCoordinates $innerTextFieldCoordinates;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextFieldDelegate$Companion$updateTextLayoutResult$1$1$1(LayoutCoordinates layoutCoordinates) {
        super(1);
        this.$innerTextFieldCoordinates = layoutCoordinates;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        m884invoke58bKbWc(((Matrix) obj).m3705unboximpl());
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke-58bKbWc, reason: not valid java name */
    public final void m884invoke58bKbWc(float[] fArr) {
        LayoutCoordinatesKt.findRootCoordinates(this.$innerTextFieldCoordinates).mo4642transformFromEL8BTi8(this.$innerTextFieldCoordinates, fArr);
    }
}
