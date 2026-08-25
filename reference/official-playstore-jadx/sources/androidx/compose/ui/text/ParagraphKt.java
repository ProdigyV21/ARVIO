package androidx.compose.ui.text;

import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.font.Font;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000`\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a4\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\nø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a,\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0007\u001ax\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0014\b\u0002\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00190\u00182\u0014\b\u0002\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u00190\u00182\b\b\u0002\u0010\b\u001a\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\nø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001e\u001ap\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0014\b\u0002\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00190\u00182\u0014\b\u0002\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u00190\u00182\b\b\u0002\u0010\b\u001a\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\nH\u0007\u001ap\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0014\b\u0002\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00190\u00182\u0014\b\u0002\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u00190\u00182\b\b\u0002\u0010\b\u001a\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020 H\u0007\u001a\f\u0010!\u001a\u00020\u0001*\u00020\u000eH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\""}, d2 = {"DefaultMaxLines", "", "Paragraph", "Landroidx/compose/ui/text/Paragraph;", "paragraphIntrinsics", "Landroidx/compose/ui/text/ParagraphIntrinsics;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "maxLines", "ellipsis", "", "Paragraph-_EkL_-Y", "(Landroidx/compose/ui/text/ParagraphIntrinsics;JIZ)Landroidx/compose/ui/text/Paragraph;", "width", "", "text", "", "style", "Landroidx/compose/ui/text/TextStyle;", "density", "Landroidx/compose/ui/unit/Density;", "fontFamilyResolver", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "spanStyles", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/SpanStyle;", "placeholders", "Landroidx/compose/ui/text/Placeholder;", "Paragraph-UdtVg6A", "(Ljava/lang/String;Landroidx/compose/ui/text/TextStyle;JLandroidx/compose/ui/unit/Density;Landroidx/compose/ui/text/font/FontFamily$Resolver;Ljava/util/List;Ljava/util/List;IZ)Landroidx/compose/ui/text/Paragraph;", "resourceLoader", "Landroidx/compose/ui/text/font/Font$ResourceLoader;", "ceilToInt", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ParagraphKt {
    public static final int DefaultMaxLines = Integer.MAX_VALUE;

    @e
    public static final Paragraph Paragraph(String str, TextStyle textStyle, List<AnnotatedString.Range<SpanStyle>> list, List<AnnotatedString.Range<Placeholder>> list2, int i10, boolean z, float f10, Density density, Font.ResourceLoader resourceLoader) {
        return androidx.compose.ui.text.platform.AndroidParagraph_androidKt.ActualParagraph(str, textStyle, list, list2, i10, z, f10, density, resourceLoader);
    }

    public static /* synthetic */ Paragraph Paragraph$default(String str, TextStyle textStyle, List list, List list2, int i10, boolean z, float f10, Density density, Font.ResourceLoader resourceLoader, int i11, Object obj) {
        int i12 = i11 & 4;
        z zVar = z.f19728i;
        if (i12 != 0) {
            list = zVar;
        }
        if ((i11 & 8) != 0) {
            list2 = zVar;
        }
        if ((i11 & 16) != 0) {
            i10 = Integer.MAX_VALUE;
        }
        if ((i11 & 32) != 0) {
            z = false;
        }
        return Paragraph(str, textStyle, (List<AnnotatedString.Range<SpanStyle>>) list, (List<AnnotatedString.Range<Placeholder>>) list2, i10, z, f10, density, resourceLoader);
    }

    /* JADX INFO: renamed from: Paragraph-UdtVg6A, reason: not valid java name */
    public static final Paragraph m5083ParagraphUdtVg6A(String str, TextStyle textStyle, long j10, Density density, FontFamily.Resolver resolver, List<AnnotatedString.Range<SpanStyle>> list, List<AnnotatedString.Range<Placeholder>> list2, int i10, boolean z) {
        return androidx.compose.ui.text.platform.AndroidParagraph_androidKt.m5441ActualParagraphO3s9Psw(str, textStyle, list, list2, i10, z, j10, density, resolver);
    }

    /* JADX INFO: renamed from: Paragraph-UdtVg6A$default, reason: not valid java name */
    public static /* synthetic */ Paragraph m5084ParagraphUdtVg6A$default(String str, TextStyle textStyle, long j10, Density density, FontFamily.Resolver resolver, List list, List list2, int i10, boolean z, int i11, Object obj) {
        int i12 = i11 & 32;
        z zVar = z.f19728i;
        if (i12 != 0) {
            list = zVar;
        }
        if ((i11 & 64) != 0) {
            list2 = zVar;
        }
        if ((i11 & 128) != 0) {
            i10 = Integer.MAX_VALUE;
        }
        if ((i11 & 256) != 0) {
            z = false;
        }
        return m5083ParagraphUdtVg6A(str, textStyle, j10, density, resolver, list, list2, i10, z);
    }

    /* JADX INFO: renamed from: Paragraph-_EkL_-Y, reason: not valid java name */
    public static final Paragraph m5085Paragraph_EkL_Y(ParagraphIntrinsics paragraphIntrinsics, long j10, int i10, boolean z) {
        return androidx.compose.ui.text.platform.AndroidParagraph_androidKt.m5440ActualParagraphhBUhpc(paragraphIntrinsics, i10, z, j10);
    }

    /* JADX INFO: renamed from: Paragraph-_EkL_-Y$default, reason: not valid java name */
    public static /* synthetic */ Paragraph m5086Paragraph_EkL_Y$default(ParagraphIntrinsics paragraphIntrinsics, long j10, int i10, boolean z, int i11, Object obj) {
        if ((i11 & 4) != 0) {
            i10 = Integer.MAX_VALUE;
        }
        if ((i11 & 8) != 0) {
            z = false;
        }
        return m5085Paragraph_EkL_Y(paragraphIntrinsics, j10, i10, z);
    }

    public static final int ceilToInt(float f10) {
        return (int) Math.ceil(f10);
    }

    @e
    public static final Paragraph Paragraph(String str, TextStyle textStyle, float f10, Density density, FontFamily.Resolver resolver, List<AnnotatedString.Range<SpanStyle>> list, List<AnnotatedString.Range<Placeholder>> list2, int i10, boolean z) {
        return androidx.compose.ui.text.platform.AndroidParagraph_androidKt.m5441ActualParagraphO3s9Psw(str, textStyle, list, list2, i10, z, ConstraintsKt.Constraints$default(0, ceilToInt(f10), 0, 0, 13, null), density, resolver);
    }

    public static /* synthetic */ Paragraph Paragraph$default(String str, TextStyle textStyle, float f10, Density density, FontFamily.Resolver resolver, List list, List list2, int i10, boolean z, int i11, Object obj) {
        int i12 = i11 & 32;
        z zVar = z.f19728i;
        if (i12 != 0) {
            list = zVar;
        }
        if ((i11 & 64) != 0) {
            list2 = zVar;
        }
        if ((i11 & 128) != 0) {
            i10 = Integer.MAX_VALUE;
        }
        if ((i11 & 256) != 0) {
            z = false;
        }
        return Paragraph(str, textStyle, f10, density, resolver, (List<AnnotatedString.Range<SpanStyle>>) list, (List<AnnotatedString.Range<Placeholder>>) list2, i10, z);
    }

    public static /* synthetic */ Paragraph Paragraph$default(ParagraphIntrinsics paragraphIntrinsics, int i10, boolean z, float f10, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            i10 = Integer.MAX_VALUE;
        }
        if ((i11 & 4) != 0) {
            z = false;
        }
        return Paragraph(paragraphIntrinsics, i10, z, f10);
    }

    @e
    public static final Paragraph Paragraph(ParagraphIntrinsics paragraphIntrinsics, int i10, boolean z, float f10) {
        return androidx.compose.ui.text.platform.AndroidParagraph_androidKt.m5440ActualParagraphhBUhpc(paragraphIntrinsics, i10, z, ConstraintsKt.Constraints$default(0, ceilToInt(f10), 0, 0, 13, null));
    }
}
