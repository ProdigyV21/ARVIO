package androidx.compose.ui.text.font;

import android.content.Context;
import androidx.compose.ui.text.platform.AndroidDefaultTypeface;
import androidx.compose.ui.text.platform.AndroidFontListTypeface;
import androidx.compose.ui.text.platform.AndroidGenericFontFamilyTypeface;
import androidx.compose.ui.text.platform.AndroidTypefaceWrapper;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import x6.e;
import x6.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a=\u0010\n\u001a\u00020\t2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u001c\b\u0002\u0010\b\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0018\u00010\u0004H\u0007¢\u0006\u0004\b\n\u0010\u000b\u001a\u0015\u0010\n\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\n\u0010\u000e\u001a\u0015\u0010\u000f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Landroid/content/Context;", "context", "Landroidx/compose/ui/text/font/FontFamily;", TtmlNode.ATTR_TTS_FONT_FAMILY, "", "Lx6/x;", "Landroidx/compose/ui/text/font/FontWeight;", "Landroidx/compose/ui/text/font/FontStyle;", "styles", "Landroidx/compose/ui/text/font/Typeface;", "Typeface", "(Landroid/content/Context;Landroidx/compose/ui/text/font/FontFamily;Ljava/util/List;)Landroidx/compose/ui/text/font/Typeface;", "Landroid/graphics/Typeface;", "typeface", "(Landroid/graphics/Typeface;)Landroidx/compose/ui/text/font/Typeface;", "FontFamily", "(Landroid/graphics/Typeface;)Landroidx/compose/ui/text/font/FontFamily;", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidTypeface_androidKt {
    public static final FontFamily FontFamily(android.graphics.Typeface typeface) {
        return FontFamilyKt.FontFamily(Typeface(typeface));
    }

    @e
    public static final Typeface Typeface(Context context, FontFamily fontFamily, List<x> list) {
        if (fontFamily instanceof FontListFontFamily) {
            return new AndroidFontListTypeface((FontListFontFamily) fontFamily, context, list, null, 8, null);
        }
        if (fontFamily instanceof GenericFontFamily) {
            return new AndroidGenericFontFamilyTypeface((GenericFontFamily) fontFamily);
        }
        if (fontFamily instanceof DefaultFontFamily) {
            return new AndroidDefaultTypeface();
        }
        if (fontFamily instanceof LoadedFontFamily) {
            return ((LoadedFontFamily) fontFamily).getTypeface();
        }
        throw new NoWhenBranchMatchedException();
    }

    public static /* synthetic */ Typeface Typeface$default(Context context, FontFamily fontFamily, List list, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            list = null;
        }
        return Typeface(context, fontFamily, list);
    }

    public static final Typeface Typeface(android.graphics.Typeface typeface) {
        return new AndroidTypefaceWrapper(typeface);
    }
}
