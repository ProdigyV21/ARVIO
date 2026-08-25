package androidx.compose.ui.text.android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Trace;
import android.text.BoringLayout;
import android.text.Layout;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextPaint;
import android.text.TextUtils;
import androidx.compose.ui.text.android.style.BaselineShiftSpan;
import androidx.compose.ui.text.android.style.IndentationFixSpanKt;
import androidx.compose.ui.text.android.style.LineHeightStyleSpan;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.session.MediaUtils;
import f4.f;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import x6.s;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0000\u0018\u00002\u00020\u0001BÅ\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\f\u001a\u00020\b\u0012\b\b\u0002\u0010\r\u001a\u00020\u0004\u0012\b\b\u0003\u0010\u000e\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0012\u001a\u00020\b\u0012\b\b\u0002\u0010\u0013\u001a\u00020\b\u0012\b\b\u0002\u0010\u0014\u001a\u00020\b\u0012\b\b\u0002\u0010\u0015\u001a\u00020\b\u0012\b\b\u0002\u0010\u0016\u001a\u00020\b\u0012\b\b\u0002\u0010\u0017\u001a\u00020\b\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0018\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0018\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\b\u001d\u0010\u001eJ\u0015\u0010 \u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\b¢\u0006\u0004\b \u0010!J\u0015\u0010\"\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\b¢\u0006\u0004\b\"\u0010!J\u0015\u0010$\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\b¢\u0006\u0004\b$\u0010!J\u0015\u0010%\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\b¢\u0006\u0004\b%\u0010!J\u0015\u0010&\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\b¢\u0006\u0004\b&\u0010!J\u0015\u0010'\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\b¢\u0006\u0004\b'\u0010!J\u0015\u0010(\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\b¢\u0006\u0004\b(\u0010!J\u0015\u0010)\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\b¢\u0006\u0004\b)\u0010!J\u0015\u0010*\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\b¢\u0006\u0004\b*\u0010!J\u0015\u0010+\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\b¢\u0006\u0004\b+\u0010,J\u0015\u0010-\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\b¢\u0006\u0004\b-\u0010,J\u0015\u0010.\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\b¢\u0006\u0004\b.\u0010,J\u0015\u0010/\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\b¢\u0006\u0004\b/\u00100J\u0015\u00101\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\b¢\u0006\u0004\b1\u0010,J\u0015\u00102\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\b¢\u0006\u0004\b2\u0010,J\u0015\u00104\u001a\u00020\b2\u0006\u00103\u001a\u00020\b¢\u0006\u0004\b4\u0010,J\u001d\u00106\u001a\u00020\b2\u0006\u0010#\u001a\u00020\b2\u0006\u00105\u001a\u00020\u0004¢\u0006\u0004\b6\u00107J\u001f\u0010:\u001a\u00020\u00042\u0006\u00108\u001a\u00020\b2\b\b\u0002\u00109\u001a\u00020\u000f¢\u0006\u0004\b:\u0010;J\u001f\u0010<\u001a\u00020\u00042\u0006\u00108\u001a\u00020\b2\b\b\u0002\u00109\u001a\u00020\u000f¢\u0006\u0004\b<\u0010;J\u0015\u0010=\u001a\u00020\b2\u0006\u00108\u001a\u00020\b¢\u0006\u0004\b=\u0010,J\u0015\u0010>\u001a\u00020\u000f2\u0006\u00108\u001a\u00020\b¢\u0006\u0004\b>\u00100J\u0015\u0010?\u001a\u00020\b2\u0006\u0010#\u001a\u00020\b¢\u0006\u0004\b?\u0010,J%\u0010E\u001a\u00020D2\u0006\u0010@\u001a\u00020\b2\u0006\u0010A\u001a\u00020\b2\u0006\u0010C\u001a\u00020B¢\u0006\u0004\bE\u0010FJ-\u0010L\u001a\u00020D2\u0006\u0010G\u001a\u00020\b2\u0006\u0010H\u001a\u00020\b2\u0006\u0010J\u001a\u00020I2\u0006\u0010K\u001a\u00020\b¢\u0006\u0004\bL\u0010MJ\u0015\u0010O\u001a\u00020N2\u0006\u00108\u001a\u00020\b¢\u0006\u0004\bO\u0010PJ\u0015\u0010S\u001a\u00020D2\u0006\u0010R\u001a\u00020Q¢\u0006\u0004\bS\u0010TJ\u000f\u0010W\u001a\u00020\u000fH\u0000¢\u0006\u0004\bU\u0010VJ\u0017\u0010X\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\bH\u0002¢\u0006\u0004\bX\u0010!R\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010Y\u001a\u0004\bZ\u0010VR\u0017\u0010\u0011\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0011\u0010Y\u001a\u0004\b[\u0010VR\u0017\u0010\u001c\u001a\u00020\u001b8\u0006¢\u0006\f\n\u0004\b\u001c\u0010\\\u001a\u0004\b]\u0010^R\u0017\u0010_\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b_\u0010Y\u001a\u0004\b`\u0010VR \u0010b\u001a\u00020a8\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\bb\u0010c\u0012\u0004\bf\u0010g\u001a\u0004\bd\u0010eR\u0017\u0010h\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\bh\u0010i\u001a\u0004\bj\u0010kR \u0010l\u001a\u00020\b8\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\bl\u0010i\u0012\u0004\bn\u0010g\u001a\u0004\bm\u0010kR \u0010o\u001a\u00020\b8\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\bo\u0010i\u0012\u0004\bq\u0010g\u001a\u0004\bp\u0010kR\u0014\u0010r\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\br\u0010sR\u0014\u0010t\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bt\u0010sR\u0014\u0010u\u001a\u00020\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bu\u0010YR\u0016\u0010w\u001a\u0004\u0018\u00010v8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bw\u0010xR\u0014\u0010y\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\by\u0010iR\u001a\u0010|\u001a\b\u0012\u0004\u0012\u00020{0z8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b|\u0010}R\u0015\u0010\u007f\u001a\u00020~8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u007f\u0010\u0080\u0001R!\u0010\u0086\u0001\u001a\u00030\u0081\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b\u0082\u0001\u0010\u0083\u0001\u001a\u0006\b\u0084\u0001\u0010\u0085\u0001R\u0014\u0010\u0089\u0001\u001a\u00020\u00048F¢\u0006\b\u001a\u0006\b\u0087\u0001\u0010\u0088\u0001R\u0014\u0010\u008b\u0001\u001a\u00020\u00048F¢\u0006\b\u001a\u0006\b\u008a\u0001\u0010\u0088\u0001R\u0014\u0010\u008e\u0001\u001a\u00020\u00028F¢\u0006\b\u001a\u0006\b\u008c\u0001\u0010\u008d\u0001R\u0013\u0010\u0090\u0001\u001a\u00020\b8F¢\u0006\u0007\u001a\u0005\b\u008f\u0001\u0010k¨\u0006\u0091\u0001"}, d2 = {"Landroidx/compose/ui/text/android/TextLayout;", "", "", "charSequence", "", "width", "Landroid/text/TextPaint;", "textPaint", "", "alignment", "Landroid/text/TextUtils$TruncateAt;", "ellipsize", "textDirectionHeuristic", "lineSpacingMultiplier", "lineSpacingExtra", "", "includePadding", "fallbackLineSpacing", "maxLines", "breakStrategy", "lineBreakStyle", "lineBreakWordStyle", "hyphenationFrequency", "justificationMode", "", "leftIndents", "rightIndents", "Landroidx/compose/ui/text/android/LayoutIntrinsics;", "layoutIntrinsics", "<init>", "(Ljava/lang/CharSequence;FLandroid/text/TextPaint;ILandroid/text/TextUtils$TruncateAt;IFFZZIIIIII[I[ILandroidx/compose/ui/text/android/LayoutIntrinsics;)V", "lineIndex", "getLineLeft", "(I)F", "getLineRight", "line", "getLineTop", "getLineBottom", "getLineAscent", "getLineBaseline", "getLineDescent", "getLineHeight", "getLineWidth", "getLineStart", "(I)I", "getLineEnd", "getLineVisibleEnd", "isLineEllipsized", "(I)Z", "getLineEllipsisOffset", "getLineEllipsisCount", "vertical", "getLineForVertical", "horizontal", "getOffsetForHorizontal", "(IF)I", "offset", "upstream", "getPrimaryHorizontal", "(IZ)F", "getSecondaryHorizontal", "getLineForOffset", "isRtlCharAt", "getParagraphDirection", TtmlNode.START, TtmlNode.END, "Landroid/graphics/Path;", "dest", "Lx6/t0;", "getSelectionPath", "(IILandroid/graphics/Path;)V", "startOffset", "endOffset", "", "array", "arrayStart", "fillBoundingBoxes", "(II[FI)V", "Landroid/graphics/RectF;", "getBoundingBox", "(I)Landroid/graphics/RectF;", "Landroid/graphics/Canvas;", "canvas", "paint", "(Landroid/graphics/Canvas;)V", "isFallbackLinespacingApplied$ui_text_release", "()Z", "isFallbackLinespacingApplied", "getHorizontalPadding", "Z", "getIncludePadding", "getFallbackLineSpacing", "Landroidx/compose/ui/text/android/LayoutIntrinsics;", "getLayoutIntrinsics", "()Landroidx/compose/ui/text/android/LayoutIntrinsics;", "didExceedMaxLines", "getDidExceedMaxLines", "Landroid/text/Layout;", TtmlNode.TAG_LAYOUT, "Landroid/text/Layout;", "getLayout", "()Landroid/text/Layout;", "getLayout$annotations", "()V", "lineCount", "I", "getLineCount", "()I", "topPadding", "getTopPadding$ui_text_release", "getTopPadding$ui_text_release$annotations", "bottomPadding", "getBottomPadding$ui_text_release", "getBottomPadding$ui_text_release$annotations", "leftPadding", "F", "rightPadding", "isBoringLayout", "Landroid/graphics/Paint$FontMetricsInt;", "lastLineFontMetrics", "Landroid/graphics/Paint$FontMetricsInt;", "lastLineExtra", "", "Landroidx/compose/ui/text/android/style/LineHeightStyleSpan;", "lineHeightSpans", "[Landroidx/compose/ui/text/android/style/LineHeightStyleSpan;", "Landroid/graphics/Rect;", "rect", "Landroid/graphics/Rect;", "Landroidx/compose/ui/text/android/LayoutHelper;", "layoutHelper$delegate", "Lx6/s;", "getLayoutHelper", "()Landroidx/compose/ui/text/android/LayoutHelper;", "layoutHelper", "getMaxIntrinsicWidth", "()F", "maxIntrinsicWidth", "getMinIntrinsicWidth", "minIntrinsicWidth", "getText", "()Ljava/lang/CharSequence;", "text", "getHeight", "height", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextLayout {
    public static final int $stable = 8;
    private final int bottomPadding;
    private final boolean didExceedMaxLines;
    private final boolean fallbackLineSpacing;
    private final boolean includePadding;
    private final boolean isBoringLayout;
    private final int lastLineExtra;
    private final Paint.FontMetricsInt lastLineFontMetrics;
    private final Layout layout;

    /* JADX INFO: renamed from: layoutHelper$delegate, reason: from kotlin metadata */
    private final s layoutHelper;
    private final LayoutIntrinsics layoutIntrinsics;
    private final float leftPadding;
    private final int lineCount;
    private final LineHeightStyleSpan[] lineHeightSpans;
    private final Rect rect;
    private final float rightPadding;
    private final int topPadding;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v2 */
    /* JADX WARN: Type inference failed for: r14v3, types: [int] */
    /* JADX WARN: Type inference failed for: r14v5 */
    public TextLayout(CharSequence charSequence, float f10, TextPaint textPaint, int i10, TextUtils.TruncateAt truncateAt, int i11, float f11, float f12, boolean z, boolean z5, int i12, int i13, int i14, int i15, int i16, int i17, int[] iArr, int[] iArr2, LayoutIntrinsics layoutIntrinsics) {
        int i18;
        boolean z10;
        TextDirectionHeuristic textDirectionHeuristic;
        boolean z11;
        Layout layoutCreate;
        this.includePadding = z;
        this.fallbackLineSpacing = z5;
        this.layoutIntrinsics = layoutIntrinsics;
        this.rect = new Rect();
        int length = charSequence.length();
        TextDirectionHeuristic textDirectionHeuristic2 = TextLayoutKt.getTextDirectionHeuristic(i11);
        Layout.Alignment alignment = TextAlignmentAdapter.INSTANCE.get(i10);
        boolean z12 = (charSequence instanceof Spanned) && ((Spanned) charSequence).nextSpanTransition(-1, length, BaselineShiftSpan.class) < length;
        Trace.beginSection("TextLayout:initLayout");
        try {
            BoringLayout.Metrics boringMetrics = layoutIntrinsics.getBoringMetrics();
            double d4 = f10;
            int iCeil = (int) Math.ceil(d4);
            if (boringMetrics == null || layoutIntrinsics.getMaxIntrinsicWidth() > f10 || z12) {
                this.isBoringLayout = false;
                int iCeil2 = (int) Math.ceil(d4);
                i18 = i12;
                z10 = false;
                textDirectionHeuristic = textDirectionHeuristic2;
                z11 = true;
                layoutCreate = StaticLayoutFactory.INSTANCE.create(charSequence, textPaint, iCeil, 0, charSequence.length(), textDirectionHeuristic, alignment, i18, truncateAt, iCeil2, f11, f12, i17, z, z5, i13, i14, i15, i16, iArr, iArr2);
            } else {
                this.isBoringLayout = true;
                i18 = i12;
                layoutCreate = BoringLayoutFactory.INSTANCE.create(charSequence, textPaint, iCeil, boringMetrics, alignment, z, z5, truncateAt, iCeil);
                textDirectionHeuristic = textDirectionHeuristic2;
                z11 = true;
                z10 = false;
            }
            this.layout = layoutCreate;
            Trace.endSection();
            int iMin = Math.min(layoutCreate.getLineCount(), i18);
            this.lineCount = iMin;
            int i19 = iMin - 1;
            this.didExceedMaxLines = (iMin >= i18 && (layoutCreate.getEllipsisCount(i19) > 0 || layoutCreate.getLineEnd(i19) != charSequence.length())) ? z11 : z10;
            long verticalPaddings = TextLayoutKt.getVerticalPaddings(this);
            LineHeightStyleSpan[] lineHeightSpans = TextLayoutKt.getLineHeightSpans(this);
            this.lineHeightSpans = lineHeightSpans;
            long lineHeightPaddings = TextLayoutKt.getLineHeightPaddings(this, lineHeightSpans);
            this.topPadding = Math.max(VerticalPaddings.m5244getTopPaddingimpl(verticalPaddings), VerticalPaddings.m5244getTopPaddingimpl(lineHeightPaddings));
            this.bottomPadding = Math.max(VerticalPaddings.m5243getBottomPaddingimpl(verticalPaddings), VerticalPaddings.m5243getBottomPaddingimpl(lineHeightPaddings));
            Paint.FontMetricsInt lastLineMetrics = TextLayoutKt.getLastLineMetrics(this, textPaint, textDirectionHeuristic, lineHeightSpans);
            this.lastLineExtra = lastLineMetrics != null ? lastLineMetrics.bottom - ((int) getLineHeight(i19)) : z10;
            this.lastLineFontMetrics = lastLineMetrics;
            this.leftPadding = IndentationFixSpanKt.getEllipsizedLeftPadding$default(layoutCreate, i19, null, 2, null);
            this.rightPadding = IndentationFixSpanKt.getEllipsizedRightPadding$default(layoutCreate, i19, null, 2, null);
            this.layoutHelper = f.o(3, new TextLayout$layoutHelper$2(this));
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    public static /* synthetic */ void getBottomPadding$ui_text_release$annotations() {
    }

    private final float getHorizontalPadding(int line) {
        if (line == this.lineCount - 1) {
            return this.leftPadding + this.rightPadding;
        }
        return 0.0f;
    }

    public static /* synthetic */ void getLayout$annotations() {
    }

    private final LayoutHelper getLayoutHelper() {
        return (LayoutHelper) this.layoutHelper.getValue();
    }

    public static /* synthetic */ float getPrimaryHorizontal$default(TextLayout textLayout, int i10, boolean z, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            z = false;
        }
        return textLayout.getPrimaryHorizontal(i10, z);
    }

    public static /* synthetic */ float getSecondaryHorizontal$default(TextLayout textLayout, int i10, boolean z, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            z = false;
        }
        return textLayout.getSecondaryHorizontal(i10, z);
    }

    public static /* synthetic */ void getTopPadding$ui_text_release$annotations() {
    }

    public final void fillBoundingBoxes(int startOffset, int endOffset, float[] array, int arrayStart) {
        float secondaryDownstream;
        float secondaryUpstream;
        int length = getText().length();
        if (startOffset < 0) {
            throw new IllegalArgumentException("startOffset must be > 0");
        }
        if (startOffset >= length) {
            throw new IllegalArgumentException("startOffset must be less than text length");
        }
        if (endOffset <= startOffset) {
            throw new IllegalArgumentException("endOffset must be greater than startOffset");
        }
        if (endOffset > length) {
            throw new IllegalArgumentException("endOffset must be smaller or equal to text length");
        }
        if (array.length - arrayStart < (endOffset - startOffset) * 4) {
            throw new IllegalArgumentException("array.size - arrayStart must be greater or equal than (endOffset - startOffset) * 4");
        }
        int lineForOffset = getLineForOffset(startOffset);
        int lineForOffset2 = getLineForOffset(endOffset - 1);
        HorizontalPositionCache horizontalPositionCache = new HorizontalPositionCache(this);
        if (lineForOffset > lineForOffset2) {
            return;
        }
        while (true) {
            int lineStart = getLineStart(lineForOffset);
            int lineEnd = getLineEnd(lineForOffset);
            int iMin = Math.min(endOffset, lineEnd);
            float lineTop = getLineTop(lineForOffset);
            float lineBottom = getLineBottom(lineForOffset);
            boolean z = getParagraphDirection(lineForOffset) == 1;
            for (int iMax = Math.max(startOffset, lineStart); iMax < iMin; iMax++) {
                boolean zIsRtlCharAt = isRtlCharAt(iMax);
                if (z && !zIsRtlCharAt) {
                    secondaryDownstream = horizontalPositionCache.getPrimaryDownstream(iMax);
                    secondaryUpstream = horizontalPositionCache.getPrimaryUpstream(iMax + 1);
                } else if (z && zIsRtlCharAt) {
                    secondaryUpstream = horizontalPositionCache.getSecondaryDownstream(iMax);
                    secondaryDownstream = horizontalPositionCache.getSecondaryUpstream(iMax + 1);
                } else if (z || !zIsRtlCharAt) {
                    secondaryDownstream = horizontalPositionCache.getSecondaryDownstream(iMax);
                    secondaryUpstream = horizontalPositionCache.getSecondaryUpstream(iMax + 1);
                } else {
                    secondaryUpstream = horizontalPositionCache.getPrimaryDownstream(iMax);
                    secondaryDownstream = horizontalPositionCache.getPrimaryUpstream(iMax + 1);
                }
                array[arrayStart] = secondaryDownstream;
                array[arrayStart + 1] = lineTop;
                array[arrayStart + 2] = secondaryUpstream;
                array[arrayStart + 3] = lineBottom;
                arrayStart += 4;
            }
            if (lineForOffset == lineForOffset2) {
                return;
            } else {
                lineForOffset++;
            }
        }
    }

    /* JADX INFO: renamed from: getBottomPadding$ui_text_release, reason: from getter */
    public final int getBottomPadding() {
        return this.bottomPadding;
    }

    public final RectF getBoundingBox(int offset) {
        float secondaryHorizontal;
        float secondaryHorizontal2;
        float primaryHorizontal;
        float primaryHorizontal2;
        int lineForOffset = getLineForOffset(offset);
        float lineTop = getLineTop(lineForOffset);
        float lineBottom = getLineBottom(lineForOffset);
        boolean z = getParagraphDirection(lineForOffset) == 1;
        boolean zIsRtlCharAt = this.layout.isRtlCharAt(offset);
        if (!z || zIsRtlCharAt) {
            if (z && zIsRtlCharAt) {
                primaryHorizontal = getSecondaryHorizontal(offset, false);
                primaryHorizontal2 = getSecondaryHorizontal(offset + 1, true);
            } else if (zIsRtlCharAt) {
                primaryHorizontal = getPrimaryHorizontal(offset, false);
                primaryHorizontal2 = getPrimaryHorizontal(offset + 1, true);
            } else {
                secondaryHorizontal = getSecondaryHorizontal(offset, false);
                secondaryHorizontal2 = getSecondaryHorizontal(offset + 1, true);
            }
            float f10 = primaryHorizontal;
            secondaryHorizontal = primaryHorizontal2;
            secondaryHorizontal2 = f10;
        } else {
            secondaryHorizontal = getPrimaryHorizontal(offset, false);
            secondaryHorizontal2 = getPrimaryHorizontal(offset + 1, true);
        }
        return new RectF(secondaryHorizontal, lineTop, secondaryHorizontal2, lineBottom);
    }

    public final boolean getDidExceedMaxLines() {
        return this.didExceedMaxLines;
    }

    public final boolean getFallbackLineSpacing() {
        return this.fallbackLineSpacing;
    }

    public final int getHeight() {
        return (this.didExceedMaxLines ? this.layout.getLineBottom(this.lineCount - 1) : this.layout.getHeight()) + this.topPadding + this.bottomPadding + this.lastLineExtra;
    }

    public final boolean getIncludePadding() {
        return this.includePadding;
    }

    public final Layout getLayout() {
        return this.layout;
    }

    public final LayoutIntrinsics getLayoutIntrinsics() {
        return this.layoutIntrinsics;
    }

    public final float getLineAscent(int line) {
        Paint.FontMetricsInt fontMetricsInt;
        return (line != this.lineCount + (-1) || (fontMetricsInt = this.lastLineFontMetrics) == null) ? this.layout.getLineAscent(line) : fontMetricsInt.ascent;
    }

    public final float getLineBaseline(int line) {
        return this.topPadding + ((line != this.lineCount + (-1) || this.lastLineFontMetrics == null) ? this.layout.getLineBaseline(line) : getLineTop(line) - this.lastLineFontMetrics.ascent);
    }

    public final float getLineBottom(int line) {
        if (line != this.lineCount - 1 || this.lastLineFontMetrics == null) {
            return this.topPadding + this.layout.getLineBottom(line) + (line == this.lineCount + (-1) ? this.bottomPadding : 0);
        }
        return this.layout.getLineBottom(line - 1) + this.lastLineFontMetrics.bottom;
    }

    public final int getLineCount() {
        return this.lineCount;
    }

    public final float getLineDescent(int line) {
        Paint.FontMetricsInt fontMetricsInt;
        return (line != this.lineCount + (-1) || (fontMetricsInt = this.lastLineFontMetrics) == null) ? this.layout.getLineDescent(line) : fontMetricsInt.descent;
    }

    public final int getLineEllipsisCount(int lineIndex) {
        return this.layout.getEllipsisCount(lineIndex);
    }

    public final int getLineEllipsisOffset(int lineIndex) {
        return this.layout.getEllipsisStart(lineIndex);
    }

    public final int getLineEnd(int lineIndex) {
        return this.layout.getEllipsisStart(lineIndex) == 0 ? this.layout.getLineEnd(lineIndex) : this.layout.getText().length();
    }

    public final int getLineForOffset(int offset) {
        return this.layout.getLineForOffset(offset);
    }

    public final int getLineForVertical(int vertical) {
        return this.layout.getLineForVertical(vertical - this.topPadding);
    }

    public final float getLineHeight(int lineIndex) {
        return getLineBottom(lineIndex) - getLineTop(lineIndex);
    }

    public final float getLineLeft(int lineIndex) {
        return this.layout.getLineLeft(lineIndex) + (lineIndex == this.lineCount + (-1) ? this.leftPadding : 0.0f);
    }

    public final float getLineRight(int lineIndex) {
        return this.layout.getLineRight(lineIndex) + (lineIndex == this.lineCount + (-1) ? this.rightPadding : 0.0f);
    }

    public final int getLineStart(int lineIndex) {
        return this.layout.getLineStart(lineIndex);
    }

    public final float getLineTop(int line) {
        return this.layout.getLineTop(line) + (line == 0 ? 0 : this.topPadding);
    }

    public final int getLineVisibleEnd(int lineIndex) {
        if (this.layout.getEllipsisStart(lineIndex) == 0) {
            return getLayoutHelper().getLineVisibleEnd(lineIndex);
        }
        return this.layout.getEllipsisStart(lineIndex) + this.layout.getLineStart(lineIndex);
    }

    public final float getLineWidth(int lineIndex) {
        return this.layout.getLineWidth(lineIndex);
    }

    public final float getMaxIntrinsicWidth() {
        return this.layoutIntrinsics.getMaxIntrinsicWidth();
    }

    public final float getMinIntrinsicWidth() {
        return this.layoutIntrinsics.getMinIntrinsicWidth();
    }

    public final int getOffsetForHorizontal(int line, float horizontal) {
        return this.layout.getOffsetForHorizontal(line, ((-1) * getHorizontalPadding(line)) + horizontal);
    }

    public final int getParagraphDirection(int line) {
        return this.layout.getParagraphDirection(line);
    }

    public final float getPrimaryHorizontal(int offset, boolean upstream) {
        return getLayoutHelper().getHorizontalPosition(offset, true, upstream) + getHorizontalPadding(getLineForOffset(offset));
    }

    public final float getSecondaryHorizontal(int offset, boolean upstream) {
        return getLayoutHelper().getHorizontalPosition(offset, false, upstream) + getHorizontalPadding(getLineForOffset(offset));
    }

    public final void getSelectionPath(int start, int end, Path dest) {
        this.layout.getSelectionPath(start, end, dest);
        if (this.topPadding == 0 || dest.isEmpty()) {
            return;
        }
        dest.offset(0.0f, this.topPadding);
    }

    public final CharSequence getText() {
        return this.layout.getText();
    }

    /* JADX INFO: renamed from: getTopPadding$ui_text_release, reason: from getter */
    public final int getTopPadding() {
        return this.topPadding;
    }

    public final boolean isFallbackLinespacingApplied$ui_text_release() {
        return this.isBoringLayout ? BoringLayoutFactory.INSTANCE.isFallbackLineSpacingEnabled((BoringLayout) this.layout) : StaticLayoutFactory.INSTANCE.isFallbackLineSpacingEnabled((StaticLayout) this.layout, this.fallbackLineSpacing);
    }

    public final boolean isLineEllipsized(int lineIndex) {
        return TextLayoutKt.isLineEllipsized(this.layout, lineIndex);
    }

    public final boolean isRtlCharAt(int offset) {
        return this.layout.isRtlCharAt(offset);
    }

    public final void paint(Canvas canvas) {
        if (canvas.getClipBounds(this.rect)) {
            int i10 = this.topPadding;
            if (i10 != 0) {
                canvas.translate(0.0f, i10);
            }
            TextAndroidCanvas textAndroidCanvas = TextLayoutKt.SharedTextAndroidCanvas;
            textAndroidCanvas.setCanvas(canvas);
            this.layout.draw(textAndroidCanvas);
            int i11 = this.topPadding;
            if (i11 != 0) {
                canvas.translate(0.0f, (-1) * i11);
            }
        }
    }

    public /* synthetic */ TextLayout(CharSequence charSequence, float f10, TextPaint textPaint, int i10, TextUtils.TruncateAt truncateAt, int i11, float f11, float f12, boolean z, boolean z5, int i12, int i13, int i14, int i15, int i16, int i17, int[] iArr, int[] iArr2, LayoutIntrinsics layoutIntrinsics, int i18, h hVar) {
        CharSequence charSequence2;
        TextPaint textPaint2;
        LayoutIntrinsics layoutIntrinsics2;
        int i19 = (i18 & 8) != 0 ? 0 : i10;
        TextUtils.TruncateAt truncateAt2 = (i18 & 16) != 0 ? null : truncateAt;
        int i20 = (i18 & 32) != 0 ? 2 : i11;
        float f13 = (i18 & 64) != 0 ? 1.0f : f11;
        float f14 = (i18 & 128) != 0 ? 0.0f : f12;
        boolean z10 = (i18 & 256) != 0 ? false : z;
        boolean z11 = (i18 & 512) != 0 ? true : z5;
        int i21 = (i18 & 1024) != 0 ? Integer.MAX_VALUE : i12;
        int i22 = (i18 & 2048) != 0 ? 0 : i13;
        int i23 = (i18 & 4096) != 0 ? 0 : i14;
        int i24 = (i18 & 8192) != 0 ? 0 : i15;
        int i25 = (i18 & 16384) != 0 ? 0 : i16;
        int i26 = (32768 & i18) != 0 ? 0 : i17;
        int[] iArr3 = (65536 & i18) != 0 ? null : iArr;
        int[] iArr4 = (131072 & i18) != 0 ? null : iArr2;
        if ((i18 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0) {
            charSequence2 = charSequence;
            textPaint2 = textPaint;
            layoutIntrinsics2 = new LayoutIntrinsics(charSequence2, textPaint2, i20);
        } else {
            charSequence2 = charSequence;
            textPaint2 = textPaint;
            layoutIntrinsics2 = layoutIntrinsics;
        }
        this(charSequence2, f10, textPaint2, i19, truncateAt2, i20, f13, f14, z10, z11, i21, i22, i23, i24, i25, i26, iArr3, iArr4, layoutIntrinsics2);
    }
}
