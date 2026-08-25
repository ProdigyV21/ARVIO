package androidx.compose.ui.text;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.graphics.drawscope.d;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextDrawStyleKt;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntSize;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0080\u0001\u0010\u001a\u001a\u00020\u0017*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\u0014\b\u0002\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000f2\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0015ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u001aj\u0010\u001a\u001a\u00020\u0017*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u001b2\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0015ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001ah\u0010\u001a\u001a\u00020\u0017*\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u001e2\b\b\u0002\u0010!\u001a\u00020 2\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010#\u001a\u00020\"2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010$2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010&2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010(2\b\b\u0002\u0010\u0016\u001a\u00020\u0015ø\u0001\u0000¢\u0006\u0004\b*\u0010+\u001af\u0010\u001a\u001a\u00020\u0017*\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010-\u001a\u00020,2\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010#\u001a\u00020\"2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010$2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010&2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010(2\b\b\u0002\u0010\u0016\u001a\u00020\u0015ø\u0001\u0000¢\u0006\u0004\b.\u0010/\u001a\u001b\u00101\u001a\u00020\u0017*\u0002002\u0006\u0010\u001f\u001a\u00020\u001eH\u0002¢\u0006\u0004\b1\u00102\u001a&\u00106\u001a\u000203*\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0006\u001a\u00020\u0005H\u0002ø\u0001\u0000¢\u0006\u0004\b4\u00105\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00067"}, d2 = {"Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Landroidx/compose/ui/text/TextMeasurer;", "textMeasurer", "Landroidx/compose/ui/text/AnnotatedString;", "text", "Landroidx/compose/ui/geometry/Offset;", "topLeft", "Landroidx/compose/ui/text/TextStyle;", "style", "Landroidx/compose/ui/text/style/TextOverflow;", "overflow", "", "softWrap", "", "maxLines", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/Placeholder;", "placeholders", "Landroidx/compose/ui/geometry/Size;", ContentDisposition.Parameters.Size, "Landroidx/compose/ui/graphics/BlendMode;", "blendMode", "Lx6/t0;", "drawText-JFhB2K4", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/text/TextMeasurer;Landroidx/compose/ui/text/AnnotatedString;JLandroidx/compose/ui/text/TextStyle;IZILjava/util/List;JI)V", "drawText", "", "drawText-TPWCCtM", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/text/TextMeasurer;Ljava/lang/String;JLandroidx/compose/ui/text/TextStyle;IZIJI)V", "Landroidx/compose/ui/text/TextLayoutResult;", "textLayoutResult", "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_COLOR, "", "alpha", "Landroidx/compose/ui/graphics/Shadow;", "shadow", "Landroidx/compose/ui/text/style/TextDecoration;", TtmlNode.ATTR_TTS_TEXT_DECORATION, "Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "drawStyle", "drawText-d8-rzKo", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/text/TextLayoutResult;JJFLandroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/drawscope/DrawStyle;I)V", "Landroidx/compose/ui/graphics/Brush;", "brush", "drawText-LVfH_YU", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/text/TextLayoutResult;Landroidx/compose/ui/graphics/Brush;JFLandroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/drawscope/DrawStyle;I)V", "Landroidx/compose/ui/graphics/drawscope/DrawTransform;", "clip", "(Landroidx/compose/ui/graphics/drawscope/DrawTransform;Landroidx/compose/ui/text/TextLayoutResult;)V", "Landroidx/compose/ui/unit/Constraints;", "textLayoutConstraints-v_w8tDc", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JJ)J", "textLayoutConstraints", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextPainterKt {
    private static final void clip(DrawTransform drawTransform, TextLayoutResult textLayoutResult) {
        if (!textLayoutResult.getHasVisualOverflow() || TextOverflow.m5624equalsimpl0(textLayoutResult.getLayoutInput().getOverflow(), TextOverflow.INSTANCE.m5633getVisiblegIe3tQ8())) {
            return;
        }
        d.d(drawTransform, 0.0f, 0.0f, IntSize.m5844getWidthimpl(textLayoutResult.getSize()), IntSize.m5843getHeightimpl(textLayoutResult.getSize()), 0, 16, null);
    }

    /* JADX INFO: renamed from: drawText-JFhB2K4, reason: not valid java name */
    public static final void m5171drawTextJFhB2K4(DrawScope drawScope, TextMeasurer textMeasurer, AnnotatedString annotatedString, long j10, TextStyle textStyle, int i10, boolean z, int i11, List<AnnotatedString.Range<Placeholder>> list, long j11, int i12) {
        TextLayoutResult textLayoutResultM5168measurexDpz5zY$default = TextMeasurer.m5168measurexDpz5zY$default(textMeasurer, annotatedString, textStyle, i10, z, i11, list, m5179textLayoutConstraintsv_w8tDc(drawScope, j11, j10), drawScope.getLayoutDirection(), drawScope, null, false, 1536, null);
        DrawContext drawContext = drawScope.getDrawContext();
        long jMo3922getSizeNHjbRc = drawContext.mo3922getSizeNHjbRc();
        drawContext.getCanvas().save();
        DrawTransform transform = drawContext.getTransform();
        transform.translate(Offset.m3236getXimpl(j10), Offset.m3237getYimpl(j10));
        clip(transform, textLayoutResultM5168measurexDpz5zY$default);
        textLayoutResultM5168measurexDpz5zY$default.getMultiParagraph().m5074paintLG529CI(drawScope.getDrawContext().getCanvas(), (32 & 2) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : 0L, (32 & 4) != 0 ? null : null, (32 & 8) != 0 ? null : null, (32 & 16) == 0 ? null : null, (32 & 32) != 0 ? DrawScope.INSTANCE.m3946getDefaultBlendMode0nO6VwU() : i12);
        drawContext.getCanvas().restore();
        drawContext.mo3923setSizeuvyYCjk(jMo3922getSizeNHjbRc);
    }

    /* JADX INFO: renamed from: drawText-LVfH_YU, reason: not valid java name */
    public static final void m5173drawTextLVfH_YU(DrawScope drawScope, TextLayoutResult textLayoutResult, Brush brush, long j10, float f10, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle, int i10) {
        Shadow shadow2 = shadow == null ? textLayoutResult.getLayoutInput().getStyle().getShadow() : shadow;
        TextDecoration textDecoration2 = textDecoration == null ? textLayoutResult.getLayoutInput().getStyle().getTextDecoration() : textDecoration;
        DrawStyle drawStyle2 = drawStyle == null ? textLayoutResult.getLayoutInput().getStyle().getDrawStyle() : drawStyle;
        DrawContext drawContext = drawScope.getDrawContext();
        long jMo3922getSizeNHjbRc = drawContext.mo3922getSizeNHjbRc();
        drawContext.getCanvas().save();
        DrawTransform transform = drawContext.getTransform();
        transform.translate(Offset.m3236getXimpl(j10), Offset.m3237getYimpl(j10));
        clip(transform, textLayoutResult);
        textLayoutResult.getMultiParagraph().m5076painthn5TExg(drawScope.getDrawContext().getCanvas(), brush, !Float.isNaN(f10) ? f10 : textLayoutResult.getLayoutInput().getStyle().getAlpha(), shadow2, textDecoration2, drawStyle2, i10);
        drawContext.getCanvas().restore();
        drawContext.mo3923setSizeuvyYCjk(jMo3922getSizeNHjbRc);
    }

    /* JADX INFO: renamed from: drawText-TPWCCtM, reason: not valid java name */
    public static final void m5175drawTextTPWCCtM(DrawScope drawScope, TextMeasurer textMeasurer, String str, long j10, TextStyle textStyle, int i10, boolean z, int i11, long j11, int i12) {
        TextLayoutResult textLayoutResultM5168measurexDpz5zY$default = TextMeasurer.m5168measurexDpz5zY$default(textMeasurer, new AnnotatedString(str, null, null, 6, null), textStyle, i10, z, i11, null, m5179textLayoutConstraintsv_w8tDc(drawScope, j11, j10), drawScope.getLayoutDirection(), drawScope, null, false, 1568, null);
        DrawContext drawContext = drawScope.getDrawContext();
        long jMo3922getSizeNHjbRc = drawContext.mo3922getSizeNHjbRc();
        drawContext.getCanvas().save();
        DrawTransform transform = drawContext.getTransform();
        transform.translate(Offset.m3236getXimpl(j10), Offset.m3237getYimpl(j10));
        clip(transform, textLayoutResultM5168measurexDpz5zY$default);
        textLayoutResultM5168measurexDpz5zY$default.getMultiParagraph().m5074paintLG529CI(drawScope.getDrawContext().getCanvas(), (32 & 2) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : 0L, (32 & 4) != 0 ? null : null, (32 & 8) != 0 ? null : null, (32 & 16) == 0 ? null : null, (32 & 32) != 0 ? DrawScope.INSTANCE.m3946getDefaultBlendMode0nO6VwU() : i12);
        drawContext.getCanvas().restore();
        drawContext.mo3923setSizeuvyYCjk(jMo3922getSizeNHjbRc);
    }

    /* JADX INFO: renamed from: drawText-d8-rzKo, reason: not valid java name */
    public static final void m5177drawTextd8rzKo(DrawScope drawScope, TextLayoutResult textLayoutResult, long j10, long j11, float f10, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle, int i10) {
        float alpha;
        int i11;
        Shadow shadow2;
        TextDecoration textDecoration2;
        DrawStyle drawStyle2;
        Canvas canvas;
        MultiParagraph multiParagraph;
        Shadow shadow3 = shadow == null ? textLayoutResult.getLayoutInput().getStyle().getShadow() : shadow;
        TextDecoration textDecoration3 = textDecoration == null ? textLayoutResult.getLayoutInput().getStyle().getTextDecoration() : textDecoration;
        DrawStyle drawStyle3 = drawStyle == null ? textLayoutResult.getLayoutInput().getStyle().getDrawStyle() : drawStyle;
        DrawContext drawContext = drawScope.getDrawContext();
        long jMo3922getSizeNHjbRc = drawContext.mo3922getSizeNHjbRc();
        drawContext.getCanvas().save();
        DrawTransform transform = drawContext.getTransform();
        transform.translate(Offset.m3236getXimpl(j11), Offset.m3237getYimpl(j11));
        clip(transform, textLayoutResult);
        Brush brush = textLayoutResult.getLayoutInput().getStyle().getBrush();
        if (brush == null || j10 != Color.INSTANCE.m3508getUnspecified0d7_KjU()) {
            MultiParagraph multiParagraph2 = textLayoutResult.getMultiParagraph();
            Canvas canvas2 = drawScope.getDrawContext().getCanvas();
            if (j10 == Color.INSTANCE.m3508getUnspecified0d7_KjU()) {
                j10 = textLayoutResult.getLayoutInput().getStyle().m5222getColor0d7_KjU();
            }
            multiParagraph2.m5074paintLG529CI(canvas2, TextDrawStyleKt.m5602modulateDxMtmZc(j10, f10), shadow3, textDecoration3, drawStyle3, i10);
        } else {
            MultiParagraph multiParagraph3 = textLayoutResult.getMultiParagraph();
            Canvas canvas3 = drawScope.getDrawContext().getCanvas();
            if (Float.isNaN(f10)) {
                alpha = textLayoutResult.getLayoutInput().getStyle().getAlpha();
                i11 = i10;
                shadow2 = shadow3;
                textDecoration2 = textDecoration3;
                drawStyle2 = drawStyle3;
                canvas = canvas3;
                multiParagraph = multiParagraph3;
            } else {
                alpha = f10;
                canvas = canvas3;
                multiParagraph = multiParagraph3;
                i11 = i10;
                shadow2 = shadow3;
                textDecoration2 = textDecoration3;
                drawStyle2 = drawStyle3;
            }
            multiParagraph.m5076painthn5TExg(canvas, brush, alpha, shadow2, textDecoration2, drawStyle2, i11);
        }
        drawContext.getCanvas().restore();
        drawContext.mo3923setSizeuvyYCjk(jMo3922getSizeNHjbRc);
    }

    /* JADX INFO: renamed from: textLayoutConstraints-v_w8tDc, reason: not valid java name */
    private static final long m5179textLayoutConstraintsv_w8tDc(DrawScope drawScope, long j10, long j11) {
        int iM;
        int iM2;
        int iM3;
        Size.Companion companion = Size.INSTANCE;
        int iM4 = 0;
        if (j10 == companion.m3313getUnspecifiedNHjbRc() || Float.isNaN(Size.m3305getWidthimpl(j10))) {
            iM = t7.a.M((float) Math.ceil(Size.m3305getWidthimpl(drawScope.mo3916getSizeNHjbRc()) - Offset.m3236getXimpl(j11)));
            iM2 = 0;
        } else {
            iM2 = t7.a.M((float) Math.ceil(Size.m3305getWidthimpl(j10)));
            iM = iM2;
        }
        if (j10 == companion.m3313getUnspecifiedNHjbRc() || Float.isNaN(Size.m3302getHeightimpl(j10))) {
            iM3 = t7.a.M((float) Math.ceil(Size.m3302getHeightimpl(drawScope.mo3916getSizeNHjbRc()) - Offset.m3237getYimpl(j11)));
        } else {
            iM4 = t7.a.M((float) Math.ceil(Size.m3302getHeightimpl(j10)));
            iM3 = iM4;
        }
        return ConstraintsKt.Constraints(iM2, iM, iM4, iM3);
    }
}
