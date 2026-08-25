package androidx.compose.ui.text;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/ui/text/TextRange;", "it", "", "invoke-VqIyPBM"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class SaversKt$TextRangeSaver$2 extends r implements l<Object, TextRange> {
    public static final SaversKt$TextRangeSaver$2 INSTANCE = new SaversKt$TextRangeSaver$2();

    public SaversKt$TextRangeSaver$2() {
        super(1);
    }

    @Override // r7.l
    /* JADX INFO: renamed from: invoke-VqIyPBM, reason: not valid java name and merged with bridge method [inline-methods] */
    public final TextRange invoke(Object obj) {
        List list = (List) obj;
        Object obj2 = list.get(0);
        int iIntValue = (obj2 != null ? (Integer) obj2 : null).intValue();
        Object obj3 = list.get(1);
        return TextRange.m5180boximpl(TextRangeKt.TextRange(iIntValue, (obj3 != null ? (Integer) obj3 : null).intValue()));
    }
}
