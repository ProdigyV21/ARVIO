package androidx.compose.ui.text;

import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.unit.TextUnit;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/ui/text/style/TextIndent;", "it", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class SaversKt$TextIndentSaver$2 extends r implements l<Object, TextIndent> {
    public static final SaversKt$TextIndentSaver$2 INSTANCE = new SaversKt$TextIndentSaver$2();

    public SaversKt$TextIndentSaver$2() {
        super(1);
    }

    @Override // r7.l
    public final TextIndent invoke(Object obj) {
        List list = (List) obj;
        Object obj2 = list.get(0);
        TextUnit.Companion companion = TextUnit.INSTANCE;
        Saver<TextUnit, Object> saver = SaversKt.getSaver(companion);
        Boolean bool = Boolean.FALSE;
        TextUnit textUnitRestore = null;
        long packedValue = (((!p.a(obj2, bool) || (saver instanceof NonNullValueClassSaver)) && obj2 != null) ? saver.restore(obj2) : null).getPackedValue();
        Object obj3 = list.get(1);
        Saver<TextUnit, Object> saver2 = SaversKt.getSaver(companion);
        if ((!p.a(obj3, bool) || (saver2 instanceof NonNullValueClassSaver)) && obj3 != null) {
            textUnitRestore = saver2.restore(obj3);
        }
        return new TextIndent(packedValue, textUnitRestore.getPackedValue(), null);
    }
}
