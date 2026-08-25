package androidx.compose.material3;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.text.input.TextFieldValue;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/ui/text/input/TextFieldValue;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class DateInputKt$DateInputTextField$text$2$1 extends r implements r7.a<MutableState<TextFieldValue>> {
    final /* synthetic */ CalendarModel $calendarModel;
    final /* synthetic */ DateInputFormat $dateInputFormat;
    final /* synthetic */ Long $initialDateMillis;
    final /* synthetic */ Locale $locale;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DateInputKt$DateInputTextField$text$2$1(Long l10, CalendarModel calendarModel, DateInputFormat dateInputFormat, Locale locale) {
        super(0);
        this.$initialDateMillis = l10;
        this.$calendarModel = calendarModel;
        this.$dateInputFormat = dateInputFormat;
        this.$locale = locale;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x001b  */
    @Override // r7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final androidx.compose.runtime.MutableState<androidx.compose.ui.text.input.TextFieldValue> invoke() {
        /*
            r8 = this;
            java.lang.Long r0 = r8.$initialDateMillis
            if (r0 == 0) goto L1b
            androidx.compose.material3.CalendarModel r1 = r8.$calendarModel
            androidx.compose.material3.DateInputFormat r2 = r8.$dateInputFormat
            java.util.Locale r3 = r8.$locale
            long r4 = r0.longValue()
            java.lang.String r0 = r2.getPatternWithoutDelimiters()
            java.lang.String r0 = r1.formatWithPattern(r4, r0, r3)
            if (r0 != 0) goto L19
            goto L1b
        L19:
            r2 = r0
            goto L1e
        L1b:
            java.lang.String r0 = ""
            goto L19
        L1e:
            r0 = 0
            long r3 = androidx.compose.ui.text.TextRangeKt.TextRange(r0, r0)
            androidx.compose.ui.text.input.TextFieldValue r1 = new androidx.compose.ui.text.input.TextFieldValue
            r5 = 0
            r6 = 4
            r7 = 0
            r1.<init>(r2, r3, r5, r6, r7)
            r0 = 2
            r2 = 0
            androidx.compose.runtime.MutableState r0 = androidx.compose.runtime.SnapshotStateKt.mutableStateOf$default(r1, r2, r0, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.DateInputKt$DateInputTextField$text$2$1.invoke():androidx.compose.runtime.MutableState");
    }
}
