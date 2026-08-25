package androidx.compose.foundation.text.modifiers;

import androidx.compose.foundation.text.TextDelegateKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.MultiParagraph;
import androidx.compose.ui.text.MultiParagraphIntrinsics;
import androidx.compose.ui.text.Paragraph;
import androidx.compose.ui.text.ParagraphIntrinsics;
import androidx.compose.ui.text.ParagraphIntrinsicsKt;
import androidx.compose.ui.text.ParagraphKt;
import androidx.compose.ui.text.TextLayoutInput;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.TextStyleKt;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.h;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\b\u0000\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\b\b\u0002\u0010\u000e\u001a\u00020\f¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\"\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u0011H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\"\u0010\u001e\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u0011H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010 \u001a\u00020\u001fH\u0002¢\u0006\u0004\b \u0010!J \u0010#\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u0011ø\u0001\u0000¢\u0006\u0004\b\"\u0010\u001dJ\u001d\u0010%\u001a\u00020\f2\u0006\u0010$\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b%\u0010&JH\u0010)\u001a\u00020\u001f2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fø\u0001\u0000¢\u0006\u0004\b'\u0010(J\u0017\u0010+\u001a\u0004\u0018\u00010*2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b+\u0010,J\u0015\u0010-\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b-\u0010.J\u0015\u0010/\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b/\u0010.R\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0003\u00100R\u0016\u0010\u0005\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0005\u00101R\u0016\u0010\u0007\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0007\u00102R\u001c\u0010\t\u001a\u00020\b8\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\t\u00103R\u0016\u0010\u000b\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000b\u00104R\u0016\u0010\r\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\r\u00103R\u0016\u0010\u000e\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000e\u00103R\u001c\u00106\u001a\u0002058\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b6\u00107R.\u0010:\u001a\u0004\u0018\u0001082\b\u00109\u001a\u0004\u0018\u0001088\u0000@@X\u0080\u000e¢\u0006\u0012\n\u0004\b:\u0010;\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R$\u0010@\u001a\u0004\u0018\u00010\u00188\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b@\u0010A\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER\"\u0010F\u001a\u00020\n8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bF\u00104\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR(\u0010L\u001a\u00020K8\u0000@\u0000X\u0080\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\bL\u00107\u001a\u0004\bM\u0010N\"\u0004\bO\u0010PR\u0018\u0010R\u001a\u0004\u0018\u00010Q8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bR\u0010SR\u0018\u0010T\u001a\u0004\u0018\u00010\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bT\u0010UR\u0018\u0010V\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bV\u0010WR\u001c\u0010X\u001a\u00020\u00168\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\bX\u00107R\u0016\u0010Y\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bY\u00103R\u0016\u0010Z\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bZ\u00103R\u0014\u0010]\u001a\u00020\u001f8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b[\u0010\\\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006^"}, d2 = {"Landroidx/compose/foundation/text/modifiers/ParagraphLayoutCache;", "", "", "text", "Landroidx/compose/ui/text/TextStyle;", "style", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "fontFamilyResolver", "Landroidx/compose/ui/text/style/TextOverflow;", "overflow", "", "softWrap", "", "maxLines", "minLines", "<init>", "(Ljava/lang/String;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/font/FontFamily$Resolver;IZIILkotlin/jvm/internal/h;)V", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "Landroidx/compose/ui/text/ParagraphIntrinsics;", "setLayoutDirection", "(Landroidx/compose/ui/unit/LayoutDirection;)Landroidx/compose/ui/text/ParagraphIntrinsics;", "Landroidx/compose/ui/unit/Constraints;", "constraints", "Landroidx/compose/ui/text/Paragraph;", "layoutText-K40F9xA", "(JLandroidx/compose/ui/unit/LayoutDirection;)Landroidx/compose/ui/text/Paragraph;", "layoutText", "newLayoutWillBeDifferent-K40F9xA", "(JLandroidx/compose/ui/unit/LayoutDirection;)Z", "newLayoutWillBeDifferent", "Lx6/t0;", "markDirty", "()V", "layoutWithConstraints-K40F9xA", "layoutWithConstraints", "width", "intrinsicHeight", "(ILandroidx/compose/ui/unit/LayoutDirection;)I", "update-L6sJoHM", "(Ljava/lang/String;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/font/FontFamily$Resolver;IZII)V", "update", "Landroidx/compose/ui/text/TextLayoutResult;", "slowCreateTextLayoutResultOrNull", "(Landroidx/compose/ui/text/TextStyle;)Landroidx/compose/ui/text/TextLayoutResult;", "minIntrinsicWidth", "(Landroidx/compose/ui/unit/LayoutDirection;)I", "maxIntrinsicWidth", "Ljava/lang/String;", "Landroidx/compose/ui/text/TextStyle;", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "I", "Z", "Landroidx/compose/foundation/text/modifiers/InlineDensity;", "lastDensity", "J", "Landroidx/compose/ui/unit/Density;", "value", "density", "Landroidx/compose/ui/unit/Density;", "getDensity$foundation_release", "()Landroidx/compose/ui/unit/Density;", "setDensity$foundation_release", "(Landroidx/compose/ui/unit/Density;)V", "paragraph", "Landroidx/compose/ui/text/Paragraph;", "getParagraph$foundation_release", "()Landroidx/compose/ui/text/Paragraph;", "setParagraph$foundation_release", "(Landroidx/compose/ui/text/Paragraph;)V", "didOverflow", "getDidOverflow$foundation_release", "()Z", "setDidOverflow$foundation_release", "(Z)V", "Landroidx/compose/ui/unit/IntSize;", "layoutSize", "getLayoutSize-YbymL2g$foundation_release", "()J", "setLayoutSize-ozmzZPI$foundation_release", "(J)V", "Landroidx/compose/foundation/text/modifiers/MinLinesConstrainer;", "mMinLinesConstrainer", "Landroidx/compose/foundation/text/modifiers/MinLinesConstrainer;", "paragraphIntrinsics", "Landroidx/compose/ui/text/ParagraphIntrinsics;", "intrinsicsLayoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "prevConstraints", "cachedIntrinsicHeightInputWidth", "cachedIntrinsicHeight", "getObserveFontChanges$foundation_release", "()Lx6/t0;", "observeFontChanges", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ParagraphLayoutCache {
    public static final int $stable = 8;
    private int cachedIntrinsicHeight;
    private int cachedIntrinsicHeightInputWidth;
    private Density density;
    private boolean didOverflow;
    private FontFamily.Resolver fontFamilyResolver;
    private LayoutDirection intrinsicsLayoutDirection;
    private long lastDensity;
    private long layoutSize;
    private MinLinesConstrainer mMinLinesConstrainer;
    private int maxLines;
    private int minLines;
    private int overflow;
    private Paragraph paragraph;
    private ParagraphIntrinsics paragraphIntrinsics;
    private long prevConstraints;
    private boolean softWrap;
    private TextStyle style;
    private String text;

    public /* synthetic */ ParagraphLayoutCache(String str, TextStyle textStyle, FontFamily.Resolver resolver, int i10, boolean z, int i11, int i12, h hVar) {
        this(str, textStyle, resolver, i10, z, i11, i12);
    }

    /* JADX INFO: renamed from: layoutText-K40F9xA, reason: not valid java name */
    private final Paragraph m938layoutTextK40F9xA(long constraints, LayoutDirection layoutDirection) {
        ParagraphIntrinsics layoutDirection2 = setLayoutDirection(layoutDirection);
        return ParagraphKt.m5085Paragraph_EkL_Y(layoutDirection2, LayoutUtilsKt.m927finalConstraintstfFHcEY(constraints, this.softWrap, this.overflow, layoutDirection2.getMaxIntrinsicWidth()), LayoutUtilsKt.m928finalMaxLinesxdlQI24(this.softWrap, this.overflow, this.maxLines), TextOverflow.m5624equalsimpl0(this.overflow, TextOverflow.INSTANCE.m5632getEllipsisgIe3tQ8()));
    }

    private final void markDirty() {
        this.paragraph = null;
        this.paragraphIntrinsics = null;
        this.intrinsicsLayoutDirection = null;
        this.cachedIntrinsicHeightInputWidth = -1;
        this.cachedIntrinsicHeight = -1;
        this.prevConstraints = Constraints.INSTANCE.m5654fixedJhjzzOo(0, 0);
        this.layoutSize = IntSizeKt.IntSize(0, 0);
        this.didOverflow = false;
    }

    /* JADX INFO: renamed from: newLayoutWillBeDifferent-K40F9xA, reason: not valid java name */
    private final boolean m939newLayoutWillBeDifferentK40F9xA(long constraints, LayoutDirection layoutDirection) {
        ParagraphIntrinsics paragraphIntrinsics;
        Paragraph paragraph = this.paragraph;
        if (paragraph == null || (paragraphIntrinsics = this.paragraphIntrinsics) == null || paragraphIntrinsics.getHasStaleResolvedFonts() || layoutDirection != this.intrinsicsLayoutDirection) {
            return true;
        }
        if (Constraints.m5639equalsimpl0(constraints, this.prevConstraints)) {
            return false;
        }
        return Constraints.m5646getMaxWidthimpl(constraints) != Constraints.m5646getMaxWidthimpl(this.prevConstraints) || ((float) Constraints.m5645getMaxHeightimpl(constraints)) < paragraph.getHeight() || paragraph.getDidExceedMaxLines();
    }

    private final ParagraphIntrinsics setLayoutDirection(LayoutDirection layoutDirection) {
        ParagraphIntrinsics paragraphIntrinsicsParagraphIntrinsics$default = this.paragraphIntrinsics;
        if (paragraphIntrinsicsParagraphIntrinsics$default == null || layoutDirection != this.intrinsicsLayoutDirection || paragraphIntrinsicsParagraphIntrinsics$default.getHasStaleResolvedFonts()) {
            this.intrinsicsLayoutDirection = layoutDirection;
            paragraphIntrinsicsParagraphIntrinsics$default = ParagraphIntrinsicsKt.ParagraphIntrinsics$default(this.text, TextStyleKt.resolveDefaults(this.style, layoutDirection), (List) null, (List) null, this.density, this.fontFamilyResolver, 12, (Object) null);
        }
        this.paragraphIntrinsics = paragraphIntrinsicsParagraphIntrinsics$default;
        return paragraphIntrinsicsParagraphIntrinsics$default;
    }

    /* JADX INFO: renamed from: getDensity$foundation_release, reason: from getter */
    public final Density getDensity() {
        return this.density;
    }

    /* JADX INFO: renamed from: getDidOverflow$foundation_release, reason: from getter */
    public final boolean getDidOverflow() {
        return this.didOverflow;
    }

    /* JADX INFO: renamed from: getLayoutSize-YbymL2g$foundation_release, reason: not valid java name and from getter */
    public final long getLayoutSize() {
        return this.layoutSize;
    }

    public final t0 getObserveFontChanges$foundation_release() {
        ParagraphIntrinsics paragraphIntrinsics = this.paragraphIntrinsics;
        if (paragraphIntrinsics != null) {
            paragraphIntrinsics.getHasStaleResolvedFonts();
        }
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: getParagraph$foundation_release, reason: from getter */
    public final Paragraph getParagraph() {
        return this.paragraph;
    }

    public final int intrinsicHeight(int width, LayoutDirection layoutDirection) {
        int i10 = this.cachedIntrinsicHeightInputWidth;
        int i11 = this.cachedIntrinsicHeight;
        if (width == i10 && i10 != -1) {
            return i11;
        }
        int iCeilToIntPx = TextDelegateKt.ceilToIntPx(m938layoutTextK40F9xA(ConstraintsKt.Constraints(0, width, 0, Integer.MAX_VALUE), layoutDirection).getHeight());
        this.cachedIntrinsicHeightInputWidth = width;
        this.cachedIntrinsicHeight = iCeilToIntPx;
        return iCeilToIntPx;
    }

    /* JADX INFO: renamed from: layoutWithConstraints-K40F9xA, reason: not valid java name */
    public final boolean m941layoutWithConstraintsK40F9xA(long constraints, LayoutDirection layoutDirection) {
        LayoutDirection layoutDirection2;
        boolean z = true;
        if (this.minLines > 1) {
            layoutDirection2 = layoutDirection;
            MinLinesConstrainer minLinesConstrainerFrom = MinLinesConstrainer.INSTANCE.from(this.mMinLinesConstrainer, layoutDirection2, this.style, this.density, this.fontFamilyResolver);
            this.mMinLinesConstrainer = minLinesConstrainerFrom;
            constraints = minLinesConstrainerFrom.m930coerceMinLinesOh53vG4$foundation_release(constraints, this.minLines);
        } else {
            layoutDirection2 = layoutDirection;
        }
        boolean z5 = false;
        if (m939newLayoutWillBeDifferentK40F9xA(constraints, layoutDirection2)) {
            Paragraph paragraphM938layoutTextK40F9xA = m938layoutTextK40F9xA(constraints, layoutDirection2);
            this.prevConstraints = constraints;
            this.layoutSize = ConstraintsKt.m5657constrain4WqzIAM(constraints, IntSizeKt.IntSize(TextDelegateKt.ceilToIntPx(paragraphM938layoutTextK40F9xA.getWidth()), TextDelegateKt.ceilToIntPx(paragraphM938layoutTextK40F9xA.getHeight())));
            if (!TextOverflow.m5624equalsimpl0(this.overflow, TextOverflow.INSTANCE.m5633getVisiblegIe3tQ8()) && (IntSize.m5844getWidthimpl(r9) < paragraphM938layoutTextK40F9xA.getWidth() || IntSize.m5843getHeightimpl(r9) < paragraphM938layoutTextK40F9xA.getHeight())) {
                z5 = true;
            }
            this.didOverflow = z5;
            this.paragraph = paragraphM938layoutTextK40F9xA;
            return true;
        }
        if (!Constraints.m5639equalsimpl0(constraints, this.prevConstraints)) {
            Paragraph paragraph = this.paragraph;
            this.layoutSize = ConstraintsKt.m5657constrain4WqzIAM(constraints, IntSizeKt.IntSize(TextDelegateKt.ceilToIntPx(Math.min(paragraph.getMaxIntrinsicWidth(), paragraph.getWidth())), TextDelegateKt.ceilToIntPx(paragraph.getHeight())));
            if (TextOverflow.m5624equalsimpl0(this.overflow, TextOverflow.INSTANCE.m5633getVisiblegIe3tQ8()) || (IntSize.m5844getWidthimpl(r2) >= paragraph.getWidth() && IntSize.m5843getHeightimpl(r2) >= paragraph.getHeight())) {
                z = false;
            }
            this.didOverflow = z;
            this.prevConstraints = constraints;
        }
        return false;
    }

    public final int maxIntrinsicWidth(LayoutDirection layoutDirection) {
        return TextDelegateKt.ceilToIntPx(setLayoutDirection(layoutDirection).getMaxIntrinsicWidth());
    }

    public final int minIntrinsicWidth(LayoutDirection layoutDirection) {
        return TextDelegateKt.ceilToIntPx(setLayoutDirection(layoutDirection).getMinIntrinsicWidth());
    }

    public final void setDensity$foundation_release(Density density) {
        Density density2 = this.density;
        long jM918constructorimpl = density != null ? InlineDensity.m918constructorimpl(density) : InlineDensity.INSTANCE.m926getUnspecifiedL26CHvs();
        if (density2 == null) {
            this.density = density;
            this.lastDensity = jM918constructorimpl;
        } else if (density == null || !InlineDensity.m920equalsimpl0(this.lastDensity, jM918constructorimpl)) {
            this.density = density;
            this.lastDensity = jM918constructorimpl;
            markDirty();
        }
    }

    public final void setDidOverflow$foundation_release(boolean z) {
        this.didOverflow = z;
    }

    /* JADX INFO: renamed from: setLayoutSize-ozmzZPI$foundation_release, reason: not valid java name */
    public final void m942setLayoutSizeozmzZPI$foundation_release(long j10) {
        this.layoutSize = j10;
    }

    public final void setParagraph$foundation_release(Paragraph paragraph) {
        this.paragraph = paragraph;
    }

    public final TextLayoutResult slowCreateTextLayoutResultOrNull(TextStyle style) {
        Density density;
        LayoutDirection layoutDirection = this.intrinsicsLayoutDirection;
        if (layoutDirection == null || (density = this.density) == null) {
            return null;
        }
        AnnotatedString annotatedString = new AnnotatedString(this.text, null, null, 6, null);
        if (this.paragraph == null || this.paragraphIntrinsics == null) {
            return null;
        }
        long jM5637copyZbe2FdA$default = Constraints.m5637copyZbe2FdA$default(this.prevConstraints, 0, 0, 0, 0, 10, null);
        int i10 = this.maxLines;
        boolean z = this.softWrap;
        int i11 = this.overflow;
        FontFamily.Resolver resolver = this.fontFamilyResolver;
        z zVar = z.f19728i;
        return new TextLayoutResult(new TextLayoutInput(annotatedString, style, zVar, i10, z, i11, density, layoutDirection, resolver, jM5637copyZbe2FdA$default, (h) null), new MultiParagraph(new MultiParagraphIntrinsics(annotatedString, style, zVar, density, this.fontFamilyResolver), jM5637copyZbe2FdA$default, this.maxLines, TextOverflow.m5624equalsimpl0(this.overflow, TextOverflow.INSTANCE.m5632getEllipsisgIe3tQ8()), null), this.layoutSize, null);
    }

    /* JADX INFO: renamed from: update-L6sJoHM, reason: not valid java name */
    public final void m943updateL6sJoHM(String text, TextStyle style, FontFamily.Resolver fontFamilyResolver, int overflow, boolean softWrap, int maxLines, int minLines) {
        this.text = text;
        this.style = style;
        this.fontFamilyResolver = fontFamilyResolver;
        this.overflow = overflow;
        this.softWrap = softWrap;
        this.maxLines = maxLines;
        this.minLines = minLines;
        markDirty();
    }

    private ParagraphLayoutCache(String str, TextStyle textStyle, FontFamily.Resolver resolver, int i10, boolean z, int i11, int i12) {
        this.text = str;
        this.style = textStyle;
        this.fontFamilyResolver = resolver;
        this.overflow = i10;
        this.softWrap = z;
        this.maxLines = i11;
        this.minLines = i12;
        this.lastDensity = InlineDensity.INSTANCE.m926getUnspecifiedL26CHvs();
        this.layoutSize = IntSizeKt.IntSize(0, 0);
        this.prevConstraints = Constraints.INSTANCE.m5654fixedJhjzzOo(0, 0);
        this.cachedIntrinsicHeightInputWidth = -1;
        this.cachedIntrinsicHeight = -1;
    }

    public /* synthetic */ ParagraphLayoutCache(String str, TextStyle textStyle, FontFamily.Resolver resolver, int i10, boolean z, int i11, int i12, int i13, h hVar) {
        this(str, textStyle, resolver, (i13 & 8) != 0 ? TextOverflow.INSTANCE.m5631getClipgIe3tQ8() : i10, (i13 & 16) != 0 ? true : z, (i13 & 32) != 0 ? Integer.MAX_VALUE : i11, (i13 & 64) != 0 ? 1 : i12, null);
    }
}
