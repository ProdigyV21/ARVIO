package androidx.compose.material3;

import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class c {
    public static /* synthetic */ String a(DatePickerFormatter datePickerFormatter, Long l10, Locale locale, boolean z, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: formatDate");
        }
        if ((i10 & 4) != 0) {
            z = false;
        }
        return datePickerFormatter.formatDate(l10, locale, z);
    }
}
