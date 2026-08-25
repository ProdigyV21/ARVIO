package androidx.compose.material3;

import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x7.i;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0001\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Landroidx/compose/material3/DatePickerStateImpl;", "value", "", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class DatePickerStateImpl$Companion$Saver$2 extends r implements l<List, DatePickerStateImpl> {
    final /* synthetic */ Locale $locale;
    final /* synthetic */ SelectableDates $selectableDates;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DatePickerStateImpl$Companion$Saver$2(SelectableDates selectableDates, Locale locale) {
        super(1);
        this.$selectableDates = selectableDates;
        this.$locale = locale;
    }

    @Override // r7.l
    public final DatePickerStateImpl invoke(List<? extends Object> list) {
        return new DatePickerStateImpl((Long) list.get(0), (Long) list.get(1), new i(((Integer) list.get(2)).intValue(), ((Integer) list.get(3)).intValue(), 1), DisplayMode.m1524constructorimpl(((Integer) list.get(4)).intValue()), this.$selectableDates, this.$locale, null);
    }
}
