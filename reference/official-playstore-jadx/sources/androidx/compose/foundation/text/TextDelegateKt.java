package androidx.compose.foundation.text;

import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.Placeholder;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Density;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import t7.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\u001av\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\u0012\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u0013H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a\f\u0010\u0018\u001a\u00020\u0010*\u00020\u0019H\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001a"}, d2 = {"updateTextDelegate", "Landroidx/compose/foundation/text/TextDelegate;", "current", "text", "Landroidx/compose/ui/text/AnnotatedString;", "style", "Landroidx/compose/ui/text/TextStyle;", "density", "Landroidx/compose/ui/unit/Density;", "fontFamilyResolver", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "softWrap", "", "overflow", "Landroidx/compose/ui/text/style/TextOverflow;", "maxLines", "", "minLines", "placeholders", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/Placeholder;", "updateTextDelegate-rm0N8CA", "(Landroidx/compose/foundation/text/TextDelegate;Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/unit/Density;Landroidx/compose/ui/text/font/FontFamily$Resolver;ZIIILjava/util/List;)Landroidx/compose/foundation/text/TextDelegate;", "ceilToIntPx", "", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextDelegateKt {
    public static final int ceilToIntPx(float f10) {
        return a.M((float) Math.ceil(f10));
    }

    /* JADX INFO: renamed from: updateTextDelegate-rm0N8CA, reason: not valid java name */
    public static final TextDelegate m873updateTextDelegaterm0N8CA(TextDelegate textDelegate, AnnotatedString annotatedString, TextStyle textStyle, Density density, FontFamily.Resolver resolver, boolean z, int i10, int i11, int i12, List<AnnotatedString.Range<Placeholder>> list) {
        FontFamily.Resolver resolver2;
        boolean z5;
        int i13;
        int i14;
        int i15;
        List<AnnotatedString.Range<Placeholder>> list2;
        if (p.a(textDelegate.getText(), annotatedString) && p.a(textDelegate.getStyle(), textStyle)) {
            z5 = z;
            if (textDelegate.getSoftWrap() == z5) {
                i13 = i10;
                if (!TextOverflow.m5624equalsimpl0(textDelegate.getOverflow(), i13)) {
                    resolver2 = resolver;
                    i14 = i11;
                    i15 = i12;
                    list2 = list;
                    return new TextDelegate(annotatedString, textStyle, i14, i15, z5, i13, density, resolver2, list2, null);
                }
                i14 = i11;
                if (textDelegate.getMaxLines() != i14) {
                    resolver2 = resolver;
                    i15 = i12;
                    list2 = list;
                    return new TextDelegate(annotatedString, textStyle, i14, i15, z5, i13, density, resolver2, list2, null);
                }
                i15 = i12;
                if (textDelegate.getMinLines() != i15 || !p.a(textDelegate.getDensity(), density)) {
                    resolver2 = resolver;
                    list2 = list;
                    return new TextDelegate(annotatedString, textStyle, i14, i15, z5, i13, density, resolver2, list2, null);
                }
                list2 = list;
                if (p.a(textDelegate.getPlaceholders(), list2)) {
                    resolver2 = resolver;
                    if (textDelegate.getFontFamilyResolver() == resolver2) {
                        return textDelegate;
                    }
                } else {
                    resolver2 = resolver;
                }
                return new TextDelegate(annotatedString, textStyle, i14, i15, z5, i13, density, resolver2, list2, null);
            }
            resolver2 = resolver;
        } else {
            resolver2 = resolver;
            z5 = z;
        }
        i13 = i10;
        i14 = i11;
        i15 = i12;
        list2 = list;
        return new TextDelegate(annotatedString, textStyle, i14, i15, z5, i13, density, resolver2, list2, null);
    }

    /* JADX INFO: renamed from: updateTextDelegate-rm0N8CA$default, reason: not valid java name */
    public static /* synthetic */ TextDelegate m874updateTextDelegaterm0N8CA$default(TextDelegate textDelegate, AnnotatedString annotatedString, TextStyle textStyle, Density density, FontFamily.Resolver resolver, boolean z, int i10, int i11, int i12, List list, int i13, Object obj) {
        if ((i13 & 32) != 0) {
            z = true;
        }
        if ((i13 & 64) != 0) {
            i10 = TextOverflow.INSTANCE.m5631getClipgIe3tQ8();
        }
        if ((i13 & 128) != 0) {
            i11 = Integer.MAX_VALUE;
        }
        if ((i13 & 256) != 0) {
            i12 = 1;
        }
        return m873updateTextDelegaterm0N8CA(textDelegate, annotatedString, textStyle, density, resolver, z, i10, i11, i12, list);
    }
}
