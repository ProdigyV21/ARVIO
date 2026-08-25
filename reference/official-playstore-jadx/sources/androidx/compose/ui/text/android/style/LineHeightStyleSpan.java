package androidx.compose.ui.text.android.style;

import android.graphics.Paint;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u001b\b\u0000\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\b\b\u0001\u0010\n\u001a\u00020\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J?\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0017\u0010\u0018J)\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0007H\u0000¢\u0006\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u001fR\u0014\u0010\u0006\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u001fR\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010 R\u0017\u0010\t\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010 \u001a\u0004\b!\u0010\"R\u0014\u0010\n\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u001cR\u0016\u0010#\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b#\u0010\u001fR\u0016\u0010$\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b$\u0010\u001fR\u0016\u0010%\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b%\u0010\u001fR\u0016\u0010&\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b&\u0010\u001fR$\u0010(\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u00048\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b(\u0010\u001f\u001a\u0004\b)\u0010*R$\u0010+\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u00048\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b+\u0010\u001f\u001a\u0004\b,\u0010*¨\u0006-"}, d2 = {"Landroidx/compose/ui/text/android/style/LineHeightStyleSpan;", "Landroid/text/style/LineHeightSpan;", "", "lineHeight", "", "startIndex", "endIndex", "", "trimFirstLineTop", "trimLastLineBottom", "topRatio", "<init>", "(FIIZZF)V", "Landroid/graphics/Paint$FontMetricsInt;", "fontMetricsInt", "Lx6/t0;", "calculateTargetMetrics", "(Landroid/graphics/Paint$FontMetricsInt;)V", "", "text", TtmlNode.START, TtmlNode.END, "spanStartVertical", "chooseHeight", "(Ljava/lang/CharSequence;IIIILandroid/graphics/Paint$FontMetricsInt;)V", "copy$ui_text_release", "(IIZ)Landroidx/compose/ui/text/android/style/LineHeightStyleSpan;", "copy", "F", "getLineHeight", "()F", "I", "Z", "getTrimLastLineBottom", "()Z", "firstAscent", "ascent", "descent", "lastDescent", "<set-?>", "firstAscentDiff", "getFirstAscentDiff", "()I", "lastDescentDiff", "getLastDescentDiff", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LineHeightStyleSpan implements android.text.style.LineHeightSpan {
    public static final int $stable = 8;
    private final int endIndex;
    private int firstAscentDiff;
    private int lastDescentDiff;
    private final float lineHeight;
    private final int startIndex;
    private final float topRatio;
    private final boolean trimFirstLineTop;
    private final boolean trimLastLineBottom;
    private int firstAscent = Integer.MIN_VALUE;
    private int ascent = Integer.MIN_VALUE;
    private int descent = Integer.MIN_VALUE;
    private int lastDescent = Integer.MIN_VALUE;

    public LineHeightStyleSpan(float f10, int i10, int i11, boolean z, boolean z5, float f11) {
        this.lineHeight = f10;
        this.startIndex = i10;
        this.endIndex = i11;
        this.trimFirstLineTop = z;
        this.trimLastLineBottom = z5;
        this.topRatio = f11;
        if ((0.0f > f11 || f11 > 1.0f) && f11 != -1.0f) {
            throw new IllegalStateException("topRatio should be in [0..1] range or -1");
        }
    }

    private final void calculateTargetMetrics(Paint.FontMetricsInt fontMetricsInt) {
        double dCeil;
        int iCeil = (int) Math.ceil(this.lineHeight);
        int iLineHeight = iCeil - LineHeightStyleSpanKt.lineHeight(fontMetricsInt);
        float fAbs = this.topRatio;
        if (fAbs == -1.0f) {
            fAbs = Math.abs(fontMetricsInt.ascent) / LineHeightStyleSpanKt.lineHeight(fontMetricsInt);
        }
        if (iLineHeight <= 0) {
            dCeil = Math.ceil(iLineHeight * fAbs);
        } else {
            dCeil = Math.ceil((1.0f - fAbs) * iLineHeight);
        }
        int i10 = (int) dCeil;
        int i11 = fontMetricsInt.descent;
        int i12 = i10 + i11;
        this.descent = i12;
        int i13 = i12 - iCeil;
        this.ascent = i13;
        if (this.trimFirstLineTop) {
            i13 = fontMetricsInt.ascent;
        }
        this.firstAscent = i13;
        if (this.trimLastLineBottom) {
            i12 = i11;
        }
        this.lastDescent = i12;
        this.firstAscentDiff = fontMetricsInt.ascent - i13;
        this.lastDescentDiff = i12 - i11;
    }

    public static /* synthetic */ LineHeightStyleSpan copy$ui_text_release$default(LineHeightStyleSpan lineHeightStyleSpan, int i10, int i11, boolean z, int i12, Object obj) {
        if ((i12 & 4) != 0) {
            z = lineHeightStyleSpan.trimFirstLineTop;
        }
        return lineHeightStyleSpan.copy$ui_text_release(i10, i11, z);
    }

    @Override // android.text.style.LineHeightSpan
    public void chooseHeight(CharSequence text, int start, int end, int spanStartVertical, int lineHeight, Paint.FontMetricsInt fontMetricsInt) {
        if (LineHeightStyleSpanKt.lineHeight(fontMetricsInt) <= 0) {
            return;
        }
        boolean z = start == this.startIndex;
        boolean z5 = end == this.endIndex;
        if (z && z5 && this.trimFirstLineTop && this.trimLastLineBottom) {
            return;
        }
        if (this.firstAscent == Integer.MIN_VALUE) {
            calculateTargetMetrics(fontMetricsInt);
        }
        fontMetricsInt.ascent = z ? this.firstAscent : this.ascent;
        fontMetricsInt.descent = z5 ? this.lastDescent : this.descent;
    }

    public final LineHeightStyleSpan copy$ui_text_release(int startIndex, int endIndex, boolean trimFirstLineTop) {
        return new LineHeightStyleSpan(this.lineHeight, startIndex, endIndex, trimFirstLineTop, this.trimLastLineBottom, this.topRatio);
    }

    public final int getFirstAscentDiff() {
        return this.firstAscentDiff;
    }

    public final int getLastDescentDiff() {
        return this.lastDescentDiff;
    }

    public final float getLineHeight() {
        return this.lineHeight;
    }

    public final boolean getTrimLastLineBottom() {
        return this.trimLastLineBottom;
    }
}
