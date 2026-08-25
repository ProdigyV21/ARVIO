package androidx.compose.ui.text;

import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.ui.unit.TextUnitType;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/ui/unit/TextUnit;", "it", "", "invoke-XNhUCwk"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class SaversKt$TextUnitSaver$2 extends r implements l<Object, TextUnit> {
    public static final SaversKt$TextUnitSaver$2 INSTANCE = new SaversKt$TextUnitSaver$2();

    public SaversKt$TextUnitSaver$2() {
        super(1);
    }

    @Override // r7.l
    /* JADX INFO: renamed from: invoke-XNhUCwk, reason: not valid java name and merged with bridge method [inline-methods] */
    public final TextUnit invoke(Object obj) {
        if (p.a(obj, Boolean.FALSE)) {
            return TextUnit.m5855boximpl(TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE());
        }
        List list = (List) obj;
        Object obj2 = list.get(0);
        float fFloatValue = (obj2 != null ? (Float) obj2 : null).floatValue();
        Object obj3 = list.get(1);
        return TextUnit.m5855boximpl(TextUnitKt.m5877TextUnitanM5pPY(fFloatValue, (obj3 != null ? (TextUnitType) obj3 : null).getType()));
    }
}
