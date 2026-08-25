package androidx.compose.ui.text;

import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDirection;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.TextUnit;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/ui/text/ParagraphStyle;", "it", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class SaversKt$ParagraphStyleSaver$2 extends r implements l<Object, ParagraphStyle> {
    public static final SaversKt$ParagraphStyleSaver$2 INSTANCE = new SaversKt$ParagraphStyleSaver$2();

    public SaversKt$ParagraphStyleSaver$2() {
        super(1);
    }

    @Override // r7.l
    public final ParagraphStyle invoke(Object obj) {
        List list = (List) obj;
        Object obj2 = list.get(0);
        TextIndent textIndentRestore = null;
        int value = (obj2 != null ? (TextAlign) obj2 : null).getValue();
        Object obj3 = list.get(1);
        int value2 = (obj3 != null ? (TextDirection) obj3 : null).getValue();
        Object obj4 = list.get(2);
        Saver<TextUnit, Object> saver = SaversKt.getSaver(TextUnit.INSTANCE);
        Boolean bool = Boolean.FALSE;
        long packedValue = (((!p.a(obj4, bool) || (saver instanceof NonNullValueClassSaver)) && obj4 != null) ? saver.restore(obj4) : null).getPackedValue();
        Object obj5 = list.get(3);
        Saver<TextIndent, Object> saver2 = SaversKt.getSaver(TextIndent.INSTANCE);
        if ((!p.a(obj5, bool) || (saver2 instanceof NonNullValueClassSaver)) && obj5 != null) {
            textIndentRestore = saver2.restore(obj5);
        }
        return new ParagraphStyle(value, value2, packedValue, textIndentRestore, (PlatformParagraphStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 496, (h) null);
    }
}
