package androidx.compose.ui.text;

import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.text.intl.Locale;
import androidx.compose.ui.text.intl.LocaleList;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/ui/text/intl/LocaleList;", "it", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class SaversKt$LocaleListSaver$2 extends r implements l<Object, LocaleList> {
    public static final SaversKt$LocaleListSaver$2 INSTANCE = new SaversKt$LocaleListSaver$2();

    public SaversKt$LocaleListSaver$2() {
        super(1);
    }

    @Override // r7.l
    public final LocaleList invoke(Object obj) {
        List list = (List) obj;
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            Object obj2 = list.get(i10);
            Saver<Locale, Object> saver = SaversKt.getSaver(Locale.INSTANCE);
            Locale localeRestore = null;
            if ((!p.a(obj2, Boolean.FALSE) || (saver instanceof NonNullValueClassSaver)) && obj2 != null) {
                localeRestore = saver.restore(obj2);
            }
            arrayList.add(localeRestore);
        }
        return new LocaleList(arrayList);
    }
}
