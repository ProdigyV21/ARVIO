package androidx.compose.foundation.text2.input.internal.selection;

import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.unit.IntSizeKt;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/unit/DpSize;", ContentDisposition.Parameters.Size, "Lx6/t0;", "invoke-EaSLcWc", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class TextFieldMagnifierNodeImpl28$magnifierNode$2 extends r implements l<DpSize, t0> {
    final /* synthetic */ TextFieldMagnifierNodeImpl28 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextFieldMagnifierNodeImpl28$magnifierNode$2(TextFieldMagnifierNodeImpl28 textFieldMagnifierNodeImpl28) {
        super(1);
        this.this$0 = textFieldMagnifierNodeImpl28;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        m1157invokeEaSLcWc(((DpSize) obj).getPackedValue());
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke-EaSLcWc, reason: not valid java name */
    public final void m1157invokeEaSLcWc(long j10) {
        TextFieldMagnifierNodeImpl28 textFieldMagnifierNodeImpl28 = this.this$0;
        Density density = (Density) CompositionLocalConsumerModifierNodeKt.currentValueOf(textFieldMagnifierNodeImpl28, CompositionLocalsKt.getLocalDensity());
        textFieldMagnifierNodeImpl28.m1155setMagnifierSizeozmzZPI(IntSizeKt.IntSize(density.mo279roundToPx0680j_4(DpSize.m5776getWidthD9Ej5fM(j10)), density.mo279roundToPx0680j_4(DpSize.m5774getHeightD9Ej5fM(j10))));
    }
}
