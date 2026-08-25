package androidx.compose.ui.text;

import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.font.DelegatingFontLoaderForDeprecatedUsage_androidKt;
import androidx.compose.ui.text.font.Font;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.platform.AndroidParagraphIntrinsics_androidKt;
import androidx.compose.ui.unit.Density;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aT\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00072\u0014\b\u0002\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\b0\u00072\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007\u001aR\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00072\u0014\b\u0002\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\b0\u00072\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011¨\u0006\u0012"}, d2 = {"ParagraphIntrinsics", "Landroidx/compose/ui/text/ParagraphIntrinsics;", "text", "", "style", "Landroidx/compose/ui/text/TextStyle;", "spanStyles", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/SpanStyle;", "placeholders", "Landroidx/compose/ui/text/Placeholder;", "density", "Landroidx/compose/ui/unit/Density;", "resourceLoader", "Landroidx/compose/ui/text/font/Font$ResourceLoader;", "fontFamilyResolver", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ParagraphIntrinsicsKt {
    @e
    public static final ParagraphIntrinsics ParagraphIntrinsics(String str, TextStyle textStyle, List<AnnotatedString.Range<SpanStyle>> list, List<AnnotatedString.Range<Placeholder>> list2, Density density, Font.ResourceLoader resourceLoader) {
        return AndroidParagraphIntrinsics_androidKt.ActualParagraphIntrinsics(str, textStyle, list, list2, density, DelegatingFontLoaderForDeprecatedUsage_androidKt.createFontFamilyResolver(resourceLoader));
    }

    public static /* synthetic */ ParagraphIntrinsics ParagraphIntrinsics$default(String str, TextStyle textStyle, List list, List list2, Density density, Font.ResourceLoader resourceLoader, int i10, Object obj) {
        int i11 = i10 & 4;
        z zVar = z.f19728i;
        if (i11 != 0) {
            list = zVar;
        }
        if ((i10 & 8) != 0) {
            list2 = zVar;
        }
        return ParagraphIntrinsics(str, textStyle, (List<AnnotatedString.Range<SpanStyle>>) list, (List<AnnotatedString.Range<Placeholder>>) list2, density, resourceLoader);
    }

    public static /* synthetic */ ParagraphIntrinsics ParagraphIntrinsics$default(String str, TextStyle textStyle, List list, List list2, Density density, FontFamily.Resolver resolver, int i10, Object obj) {
        int i11 = i10 & 4;
        z zVar = z.f19728i;
        if (i11 != 0) {
            list = zVar;
        }
        if ((i10 & 8) != 0) {
            list2 = zVar;
        }
        return ParagraphIntrinsics(str, textStyle, (List<AnnotatedString.Range<SpanStyle>>) list, (List<AnnotatedString.Range<Placeholder>>) list2, density, resolver);
    }

    public static final ParagraphIntrinsics ParagraphIntrinsics(String str, TextStyle textStyle, List<AnnotatedString.Range<SpanStyle>> list, List<AnnotatedString.Range<Placeholder>> list2, Density density, FontFamily.Resolver resolver) {
        return AndroidParagraphIntrinsics_androidKt.ActualParagraphIntrinsics(str, textStyle, list, list2, density, resolver);
    }
}
