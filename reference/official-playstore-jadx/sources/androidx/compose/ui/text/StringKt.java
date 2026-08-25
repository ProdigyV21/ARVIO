package androidx.compose.ui.text;

import androidx.compose.ui.text.intl.Locale;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.platform.AndroidStringDelegate_androidKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0012\u0010\u0002\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0012\u0010\u0002\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u0012\u0010\b\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0012\u0010\b\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u0012\u0010\t\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0012\u0010\t\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u0012\u0010\n\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0012\u0010\n\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"stringDelegate", "Landroidx/compose/ui/text/PlatformStringDelegate;", "capitalize", "", "locale", "Landroidx/compose/ui/text/intl/Locale;", "localeList", "Landroidx/compose/ui/text/intl/LocaleList;", "decapitalize", "toLowerCase", "toUpperCase", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StringKt {
    private static final PlatformStringDelegate stringDelegate = AndroidStringDelegate_androidKt.ActualStringDelegate();

    public static final String capitalize(String str, Locale locale) {
        return stringDelegate.capitalize(str, locale.getPlatformLocale());
    }

    public static final String decapitalize(String str, Locale locale) {
        return stringDelegate.decapitalize(str, locale.getPlatformLocale());
    }

    public static final String toLowerCase(String str, Locale locale) {
        return stringDelegate.toLowerCase(str, locale.getPlatformLocale());
    }

    public static final String toUpperCase(String str, Locale locale) {
        return stringDelegate.toUpperCase(str, locale.getPlatformLocale());
    }

    public static final String capitalize(String str, LocaleList localeList) {
        return capitalize(str, localeList.isEmpty() ? Locale.INSTANCE.getCurrent() : localeList.get(0));
    }

    public static final String decapitalize(String str, LocaleList localeList) {
        return decapitalize(str, localeList.isEmpty() ? Locale.INSTANCE.getCurrent() : localeList.get(0));
    }

    public static final String toLowerCase(String str, LocaleList localeList) {
        return toLowerCase(str, localeList.isEmpty() ? Locale.INSTANCE.getCurrent() : localeList.get(0));
    }

    public static final String toUpperCase(String str, LocaleList localeList) {
        return toUpperCase(str, localeList.isEmpty() ? Locale.INSTANCE.getCurrent() : localeList.get(0));
    }
}
