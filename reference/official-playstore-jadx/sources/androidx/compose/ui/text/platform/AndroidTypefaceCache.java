package androidx.compose.ui.text.platform;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.TypedValue;
import androidx.compose.ui.text.font.AndroidFont;
import androidx.compose.ui.text.font.AndroidPreloadedFont;
import androidx.compose.ui.text.font.Font;
import androidx.compose.ui.text.font.ResourceFont;
import i0.q;
import kotlin.Metadata;
import t.r;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\f\u0010\rR \u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\b0\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Landroidx/compose/ui/text/platform/AndroidTypefaceCache;", "", "<init>", "()V", "Landroid/content/Context;", "context", "Landroidx/compose/ui/text/font/Font;", "font", "Landroid/graphics/Typeface;", "getOrCreate", "(Landroid/content/Context;Landroidx/compose/ui/text/font/Font;)Landroid/graphics/Typeface;", "", "getKey", "(Landroid/content/Context;Landroidx/compose/ui/text/font/Font;)Ljava/lang/String;", "Lt/r;", "cache", "Lt/r;", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@e
public final class AndroidTypefaceCache {
    public static final AndroidTypefaceCache INSTANCE = new AndroidTypefaceCache();
    private static final r cache = new r(16);
    public static final int $stable = 8;

    private AndroidTypefaceCache() {
    }

    public final String getKey(Context context, Font font) {
        if (font instanceof ResourceFont) {
            TypedValue typedValue = new TypedValue();
            context.getResources().getValue(((ResourceFont) font).getResId(), typedValue, true);
            CharSequence charSequence = typedValue.string;
            return "res:".concat(charSequence != null ? charSequence.toString() : null);
        }
        if (font instanceof AndroidPreloadedFont) {
            return ((AndroidPreloadedFont) font).getCacheKey();
        }
        throw new IllegalArgumentException("Unknown font type: " + font);
    }

    public final Typeface getOrCreate(Context context, Font font) {
        Typeface typefaceLoadBlocking;
        Typeface typeface;
        String key = getKey(context, font);
        if (key != null && (typeface = (Typeface) cache.get(key)) != null) {
            return typeface;
        }
        if (font instanceof ResourceFont) {
            typefaceLoadBlocking = Build.VERSION.SDK_INT >= 26 ? AndroidResourceFontLoaderHelper.INSTANCE.create(context, ((ResourceFont) font).getResId()) : q.b(context, ((ResourceFont) font).getResId());
        } else {
            if (!(font instanceof AndroidFont)) {
                throw new IllegalArgumentException("Unknown font type: " + font);
            }
            AndroidFont androidFont = (AndroidFont) font;
            typefaceLoadBlocking = androidFont.getTypefaceLoader().loadBlocking(context, androidFont);
        }
        if (typefaceLoadBlocking != null) {
            if (key != null) {
            }
            return typefaceLoadBlocking;
        }
        throw new IllegalArgumentException("Unable to load font " + font);
    }
}
