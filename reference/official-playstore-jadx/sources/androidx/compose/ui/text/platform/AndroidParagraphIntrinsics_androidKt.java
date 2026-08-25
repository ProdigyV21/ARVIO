package androidx.compose.ui.text.platform;

import android.text.TextUtils;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.EmojiSupportMatch;
import androidx.compose.ui.text.ParagraphIntrinsics;
import androidx.compose.ui.text.Placeholder;
import androidx.compose.ui.text.PlatformParagraphStyle;
import androidx.compose.ui.text.PlatformTextStyle;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.intl.AndroidLocale;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.TextDirection;
import androidx.compose.ui.unit.Density;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000P\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aP\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00022\u0012\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000b2\u0012\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\f0\u000b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0000\u001a&\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0000ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001c"}, d2 = {"hasEmojiCompat", "", "Landroidx/compose/ui/text/TextStyle;", "getHasEmojiCompat", "(Landroidx/compose/ui/text/TextStyle;)Z", "ActualParagraphIntrinsics", "Landroidx/compose/ui/text/ParagraphIntrinsics;", "text", "", "style", "spanStyles", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/SpanStyle;", "placeholders", "Landroidx/compose/ui/text/Placeholder;", "density", "Landroidx/compose/ui/unit/Density;", "fontFamilyResolver", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "resolveTextDirectionHeuristics", "", "textDirection", "Landroidx/compose/ui/text/style/TextDirection;", "localeList", "Landroidx/compose/ui/text/intl/LocaleList;", "resolveTextDirectionHeuristics-HklW4sA", "(ILandroidx/compose/ui/text/intl/LocaleList;)I", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidParagraphIntrinsics_androidKt {
    public static final ParagraphIntrinsics ActualParagraphIntrinsics(String str, TextStyle textStyle, List<AnnotatedString.Range<SpanStyle>> list, List<AnnotatedString.Range<Placeholder>> list2, Density density, FontFamily.Resolver resolver) {
        return new AndroidParagraphIntrinsics(str, textStyle, list, list2, resolver, density);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean getHasEmojiCompat(TextStyle textStyle) {
        PlatformParagraphStyle paragraphStyle;
        PlatformTextStyle platformStyle = textStyle.getPlatformStyle();
        return !(((platformStyle == null || (paragraphStyle = platformStyle.getParagraphStyle()) == null) ? null : EmojiSupportMatch.m5059boximpl(paragraphStyle.getEmojiSupportMatch())) == null ? false : EmojiSupportMatch.m5062equalsimpl0(r1.getValue(), EmojiSupportMatch.INSTANCE.m5067getNone_3YsG6Y()));
    }

    /* JADX INFO: renamed from: resolveTextDirectionHeuristics-HklW4sA, reason: not valid java name */
    public static final int m5438resolveTextDirectionHeuristicsHklW4sA(int i10, LocaleList localeList) {
        Locale javaLocale;
        TextDirection.Companion companion = TextDirection.INSTANCE;
        if (TextDirection.m5592equalsimpl0(i10, companion.m5597getContentOrLtrs_7Xco())) {
            return 2;
        }
        if (TextDirection.m5592equalsimpl0(i10, companion.m5598getContentOrRtls_7Xco())) {
            return 3;
        }
        if (TextDirection.m5592equalsimpl0(i10, companion.m5599getLtrs_7Xco())) {
            return 0;
        }
        if (TextDirection.m5592equalsimpl0(i10, companion.m5600getRtls_7Xco())) {
            return 1;
        }
        if (!(TextDirection.m5592equalsimpl0(i10, companion.m5596getContents_7Xco()) ? true : TextDirection.m5592equalsimpl0(i10, companion.m5601getUnspecifieds_7Xco()))) {
            throw new IllegalStateException("Invalid TextDirection.");
        }
        if (localeList == null || (javaLocale = ((AndroidLocale) localeList.get(0).getPlatformLocale()).getJavaLocale()) == null) {
            javaLocale = Locale.getDefault();
        }
        int layoutDirectionFromLocale = TextUtils.getLayoutDirectionFromLocale(javaLocale);
        return (layoutDirectionFromLocale == 0 || layoutDirectionFromLocale != 1) ? 2 : 3;
    }

    /* JADX INFO: renamed from: resolveTextDirectionHeuristics-HklW4sA$default, reason: not valid java name */
    public static /* synthetic */ int m5439resolveTextDirectionHeuristicsHklW4sA$default(int i10, LocaleList localeList, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            localeList = null;
        }
        return m5438resolveTextDirectionHeuristicsHklW4sA(i10, localeList);
    }
}
