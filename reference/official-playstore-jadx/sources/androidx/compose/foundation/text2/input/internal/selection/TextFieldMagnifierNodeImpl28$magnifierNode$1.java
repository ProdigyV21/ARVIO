package androidx.compose.foundation.text2.input.internal.selection;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.unit.Density;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/ui/geometry/Offset;", "Landroidx/compose/ui/unit/Density;", "invoke-tuRUvjQ", "(Landroidx/compose/ui/unit/Density;)J"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class TextFieldMagnifierNodeImpl28$magnifierNode$1 extends r implements l<Density, Offset> {
    final /* synthetic */ TextFieldMagnifierNodeImpl28 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextFieldMagnifierNodeImpl28$magnifierNode$1(TextFieldMagnifierNodeImpl28 textFieldMagnifierNodeImpl28) {
        super(1);
        this.this$0 = textFieldMagnifierNodeImpl28;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return Offset.m3225boximpl(m1156invoketuRUvjQ((Density) obj));
    }

    /* JADX INFO: renamed from: invoke-tuRUvjQ, reason: not valid java name */
    public final long m1156invoketuRUvjQ(Density density) {
        return ((Offset) this.this$0.animatable.getValue()).getPackedValue();
    }
}
