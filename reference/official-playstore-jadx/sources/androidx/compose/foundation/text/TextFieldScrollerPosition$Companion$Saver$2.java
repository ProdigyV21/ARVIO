package androidx.compose.foundation.text;

import androidx.compose.foundation.gestures.Orientation;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/text/TextFieldScrollerPosition;", "restored", "", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class TextFieldScrollerPosition$Companion$Saver$2 extends r implements l<List<? extends Object>, TextFieldScrollerPosition> {
    public static final TextFieldScrollerPosition$Companion$Saver$2 INSTANCE = new TextFieldScrollerPosition$Companion$Saver$2();

    public TextFieldScrollerPosition$Companion$Saver$2() {
        super(1);
    }

    @Override // r7.l
    public final TextFieldScrollerPosition invoke(List<? extends Object> list) {
        return new TextFieldScrollerPosition(((Boolean) list.get(1)).booleanValue() ? Orientation.Vertical : Orientation.Horizontal, ((Float) list.get(0)).floatValue());
    }
}
