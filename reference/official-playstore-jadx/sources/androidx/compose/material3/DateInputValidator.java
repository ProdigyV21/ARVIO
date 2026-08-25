package androidx.compose.material3;

import kotlin.Metadata;
import x7.i;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0001\u0018\u00002\u00020\u0001B_\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\n\u0012\u0006\u0010\u000e\u001a\u00020\n\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u0012\u0010\u0013J.\u0010\u001d\u001a\u00020\n2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0017\u001a\u00020\u00162\n\u0010\u001a\u001a\u00060\u0018j\u0002`\u0019ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001eR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u001fR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010 R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010!R\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\"R\u0014\u0010\f\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\"R\u0014\u0010\r\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\"R\u0014\u0010\u000e\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\"R$\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010#\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R$\u0010\u0011\u001a\u0004\u0018\u00010\u000f8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010#\u001a\u0004\b(\u0010%\"\u0004\b)\u0010'\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006*"}, d2 = {"Landroidx/compose/material3/DateInputValidator;", "", "Lx7/i;", "yearRange", "Landroidx/compose/material3/SelectableDates;", "selectableDates", "Landroidx/compose/material3/DateInputFormat;", "dateInputFormat", "Landroidx/compose/material3/DatePickerFormatter;", "dateFormatter", "", "errorDatePattern", "errorDateOutOfYearRange", "errorInvalidNotAllowed", "errorInvalidRangeInput", "", "currentStartDateMillis", "currentEndDateMillis", "<init>", "(Lx7/i;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DateInputFormat;Landroidx/compose/material3/DatePickerFormatter;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;)V", "Landroidx/compose/material3/CalendarDate;", "dateToValidate", "Landroidx/compose/material3/InputIdentifier;", "inputIdentifier", "Ljava/util/Locale;", "Landroidx/compose/material3/CalendarLocale;", "locale", "validate-XivgLIo", "(Landroidx/compose/material3/CalendarDate;ILjava/util/Locale;)Ljava/lang/String;", "validate", "Lx7/i;", "Landroidx/compose/material3/SelectableDates;", "Landroidx/compose/material3/DateInputFormat;", "Landroidx/compose/material3/DatePickerFormatter;", "Ljava/lang/String;", "Ljava/lang/Long;", "getCurrentStartDateMillis$material3_release", "()Ljava/lang/Long;", "setCurrentStartDateMillis$material3_release", "(Ljava/lang/Long;)V", "getCurrentEndDateMillis$material3_release", "setCurrentEndDateMillis$material3_release", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DateInputValidator {
    public static final int $stable = 0;
    private Long currentEndDateMillis;
    private Long currentStartDateMillis;
    private final DatePickerFormatter dateFormatter;
    private final DateInputFormat dateInputFormat;
    private final String errorDateOutOfYearRange;
    private final String errorDatePattern;
    private final String errorInvalidNotAllowed;
    private final String errorInvalidRangeInput;
    private final SelectableDates selectableDates;
    private final i yearRange;

    public DateInputValidator(i iVar, SelectableDates selectableDates, DateInputFormat dateInputFormat, DatePickerFormatter datePickerFormatter, String str, String str2, String str3, String str4, Long l10, Long l11) {
        this.yearRange = iVar;
        this.selectableDates = selectableDates;
        this.dateInputFormat = dateInputFormat;
        this.dateFormatter = datePickerFormatter;
        this.errorDatePattern = str;
        this.errorDateOutOfYearRange = str2;
        this.errorInvalidNotAllowed = str3;
        this.errorInvalidRangeInput = str4;
        this.currentStartDateMillis = l10;
        this.currentEndDateMillis = l11;
    }

    /* JADX INFO: renamed from: getCurrentEndDateMillis$material3_release, reason: from getter */
    public final Long getCurrentEndDateMillis() {
        return this.currentEndDateMillis;
    }

    /* JADX INFO: renamed from: getCurrentStartDateMillis$material3_release, reason: from getter */
    public final Long getCurrentStartDateMillis() {
        return this.currentStartDateMillis;
    }

    public final void setCurrentEndDateMillis$material3_release(Long l10) {
        this.currentEndDateMillis = l10;
    }

    public final void setCurrentStartDateMillis$material3_release(Long l10) {
        this.currentStartDateMillis = l10;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x008b  */
    /* JADX INFO: renamed from: validate-XivgLIo, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String m1448validateXivgLIo(androidx.compose.material3.CalendarDate r9, int r10, java.util.Locale r11) {
        /*
            Method dump skipped, instruction units count: 207
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.DateInputValidator.m1448validateXivgLIo(androidx.compose.material3.CalendarDate, int, java.util.Locale):java.lang.String");
    }

    public /* synthetic */ DateInputValidator(i iVar, SelectableDates selectableDates, DateInputFormat dateInputFormat, DatePickerFormatter datePickerFormatter, String str, String str2, String str3, String str4, Long l10, Long l11, int i10, kotlin.jvm.internal.h hVar) {
        this(iVar, selectableDates, dateInputFormat, datePickerFormatter, str, str2, str3, str4, (i10 & 256) != 0 ? null : l10, (i10 & 512) != 0 ? null : l11);
    }
}
