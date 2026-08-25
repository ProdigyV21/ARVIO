package androidx.compose.ui.text.platform;

import androidx.compose.ui.text.PlatformStringDelegate;
import androidx.compose.ui.text.intl.AndroidLocale;
import androidx.compose.ui.text.intl.PlatformLocale;
import com.google.common.util.concurrent.r0;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/text/platform/AndroidStringDelegate;", "Landroidx/compose/ui/text/PlatformStringDelegate;", "()V", "capitalize", "", "string", "locale", "Landroidx/compose/ui/text/intl/PlatformLocale;", "decapitalize", "toLowerCase", "toUpperCase", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AndroidStringDelegate implements PlatformStringDelegate {
    public static final int $stable = 0;

    @Override // androidx.compose.ui.text.PlatformStringDelegate
    public String capitalize(String string, PlatformLocale locale) {
        if (string.length() <= 0) {
            return string;
        }
        StringBuilder sb2 = new StringBuilder();
        char cCharAt = string.charAt(0);
        sb2.append((Object) (Character.isLowerCase(cCharAt) ? r0.F(cCharAt, ((AndroidLocale) locale).getJavaLocale()) : String.valueOf(cCharAt)));
        sb2.append(string.substring(1));
        return sb2.toString();
    }

    @Override // androidx.compose.ui.text.PlatformStringDelegate
    public String decapitalize(String string, PlatformLocale locale) {
        if (string.length() <= 0) {
            return string;
        }
        StringBuilder sb2 = new StringBuilder();
        char cCharAt = string.charAt(0);
        sb2.append((Object) String.valueOf(cCharAt).toLowerCase(((AndroidLocale) locale).getJavaLocale()));
        sb2.append(string.substring(1));
        return sb2.toString();
    }

    @Override // androidx.compose.ui.text.PlatformStringDelegate
    public String toLowerCase(String string, PlatformLocale locale) {
        return string.toLowerCase(((AndroidLocale) locale).getJavaLocale());
    }

    @Override // androidx.compose.ui.text.PlatformStringDelegate
    public String toUpperCase(String string, PlatformLocale locale) {
        return string.toUpperCase(((AndroidLocale) locale).getJavaLocale());
    }
}
