package androidx.compose.material3;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0001\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Landroidx/compose/material3/TimePickerState;", "value", "", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class TimePickerState$Companion$Saver$2 extends r implements l<List, TimePickerState> {
    public static final TimePickerState$Companion$Saver$2 INSTANCE = new TimePickerState$Companion$Saver$2();

    public TimePickerState$Companion$Saver$2() {
        super(1);
    }

    @Override // r7.l
    public final TimePickerState invoke(List<? extends Object> list) {
        return new TimePickerState(((Integer) list.get(0)).intValue(), ((Integer) list.get(1)).intValue(), ((Boolean) list.get(2)).booleanValue());
    }
}
