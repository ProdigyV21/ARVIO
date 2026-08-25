package androidx.compose.ui.text;

import androidx.compose.runtime.saveable.Saver;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/ui/text/AnnotatedString;", "it", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class SaversKt$AnnotatedStringSaver$2 extends r implements l<Object, AnnotatedString> {
    public static final SaversKt$AnnotatedStringSaver$2 INSTANCE = new SaversKt$AnnotatedStringSaver$2();

    public SaversKt$AnnotatedStringSaver$2() {
        super(1);
    }

    @Override // r7.l
    public final AnnotatedString invoke(Object obj) {
        List list = (List) obj;
        Object obj2 = list.get(1);
        Saver saver = SaversKt.AnnotationRangeListSaver;
        Boolean bool = Boolean.FALSE;
        List list2 = null;
        List list3 = ((!p.a(obj2, bool) || (saver instanceof NonNullValueClassSaver)) && obj2 != null) ? (List) saver.restore(obj2) : null;
        Object obj3 = list.get(2);
        Saver saver2 = SaversKt.AnnotationRangeListSaver;
        List list4 = ((!p.a(obj3, bool) || (saver2 instanceof NonNullValueClassSaver)) && obj3 != null) ? (List) saver2.restore(obj3) : null;
        Object obj4 = list.get(0);
        String str = obj4 != null ? (String) obj4 : null;
        if (list3 == null || list3.isEmpty()) {
            list3 = null;
        }
        if (list4 == null || list4.isEmpty()) {
            list4 = null;
        }
        Object obj5 = list.get(3);
        Saver saver3 = SaversKt.AnnotationRangeListSaver;
        if ((!p.a(obj5, bool) || (saver3 instanceof NonNullValueClassSaver)) && obj5 != null) {
            list2 = (List) saver3.restore(obj5);
        }
        return new AnnotatedString(str, list3, list4, list2);
    }
}
