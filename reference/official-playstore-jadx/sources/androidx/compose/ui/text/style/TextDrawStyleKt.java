package androidx.compose.ui.text.style;

import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.text.SpanStyleKt;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import r7.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a'\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001e\u0010\u000b\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H\u0000ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a!\u0010\u000e\u001a\u00020\u0003*\u00020\u00032\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\fH\u0002¢\u0006\u0004\b\u000e\u0010\u000f\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0010"}, d2 = {"Landroidx/compose/ui/text/style/TextForegroundStyle;", TtmlNode.START, "stop", "", "fraction", "lerp", "(Landroidx/compose/ui/text/style/TextForegroundStyle;Landroidx/compose/ui/text/style/TextForegroundStyle;F)Landroidx/compose/ui/text/style/TextForegroundStyle;", "Landroidx/compose/ui/graphics/Color;", "alpha", "modulate-DxMtmZc", "(JF)J", "modulate", "Lkotlin/Function0;", "block", "takeOrElse", "(FLr7/a;)F", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextDrawStyleKt {
    public static final TextForegroundStyle lerp(TextForegroundStyle textForegroundStyle, TextForegroundStyle textForegroundStyle2, float f10) {
        boolean z = textForegroundStyle instanceof BrushStyle;
        return (z || (textForegroundStyle2 instanceof BrushStyle)) ? (z && (textForegroundStyle2 instanceof BrushStyle)) ? TextForegroundStyle.INSTANCE.from((Brush) SpanStyleKt.lerpDiscrete(((BrushStyle) textForegroundStyle).getBrush(), ((BrushStyle) textForegroundStyle2).getBrush(), f10), MathHelpersKt.lerp(textForegroundStyle.getAlpha(), textForegroundStyle2.getAlpha(), f10)) : (TextForegroundStyle) SpanStyleKt.lerpDiscrete(textForegroundStyle, textForegroundStyle2, f10) : TextForegroundStyle.INSTANCE.m5603from8_81llA(ColorKt.m3523lerpjxsXWHM(textForegroundStyle.mo5480getColor0d7_KjU(), textForegroundStyle2.mo5480getColor0d7_KjU(), f10));
    }

    /* JADX INFO: renamed from: modulate-DxMtmZc, reason: not valid java name */
    public static final long m5602modulateDxMtmZc(long j10, float f10) {
        return (Float.isNaN(f10) || f10 >= 1.0f) ? j10 : Color.m3471copywmQWz5c$default(j10, Color.m3474getAlphaimpl(j10) * f10, 0.0f, 0.0f, 0.0f, 14, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float takeOrElse(float f10, a<Float> aVar) {
        return Float.isNaN(f10) ? ((Number) aVar.invoke()).floatValue() : f10;
    }
}
