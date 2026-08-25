package androidx.compose.ui.text.platform;

import android.text.TextPaint;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.PaintingStyle;
import androidx.compose.ui.graphics.ShaderBrush;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.text.platform.extensions.TextPaintExtensions_androidKt;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import qb.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000f\u001a\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000f\u0010\u0010J\u0018\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0011ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014J,\u0010\u001d\u001a\u00020\n2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010 \u001a\u00020\n2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e¢\u0006\u0004\b \u0010!R\u0014\u0010#\u001a\u00020\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$R\u0016\u0010\t\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010%R(\u0010\u000e\u001a\u00020\r8\u0000@\u0000X\u0081\u000e¢\u0006\u0018\n\u0004\b\u000e\u0010&\u0012\u0004\b*\u0010+\u001a\u0004\b'\u0010(\"\u0004\b)\u0010\u0010R\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010,R1\u00105\u001a\u00020-2\u0006\u0010.\u001a\u00020-8F@FX\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\u001a\u0004\b/\u00100\"\u0004\b1\u00102*\u0004\b3\u00104\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u00066"}, d2 = {"Landroidx/compose/ui/text/platform/AndroidTextPaint;", "Landroid/text/TextPaint;", "", "flags", "", "density", "<init>", "(IF)V", "Landroidx/compose/ui/text/style/TextDecoration;", TtmlNode.ATTR_TTS_TEXT_DECORATION, "Lx6/t0;", "setTextDecoration", "(Landroidx/compose/ui/text/style/TextDecoration;)V", "Landroidx/compose/ui/graphics/Shadow;", "shadow", "setShadow", "(Landroidx/compose/ui/graphics/Shadow;)V", "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_COLOR, "setColor-8_81llA", "(J)V", "setColor", "Landroidx/compose/ui/graphics/Brush;", "brush", "Landroidx/compose/ui/geometry/Size;", ContentDisposition.Parameters.Size, "alpha", "setBrush-12SF9DM", "(Landroidx/compose/ui/graphics/Brush;JF)V", "setBrush", "Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "drawStyle", "setDrawStyle", "(Landroidx/compose/ui/graphics/drawscope/DrawStyle;)V", "Landroidx/compose/ui/graphics/Paint;", "composePaint", "Landroidx/compose/ui/graphics/Paint;", "Landroidx/compose/ui/text/style/TextDecoration;", "Landroidx/compose/ui/graphics/Shadow;", "getShadow$ui_text_release", "()Landroidx/compose/ui/graphics/Shadow;", "setShadow$ui_text_release", "getShadow$ui_text_release$annotations", "()V", "Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "Landroidx/compose/ui/graphics/BlendMode;", "<set-?>", "getBlendMode-0nO6VwU", "()I", "setBlendMode-s9anfk8", "(I)V", "getBlendMode-0nO6VwU$delegate", "(Landroidx/compose/ui/text/platform/AndroidTextPaint;)Ljava/lang/Object;", "blendMode", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AndroidTextPaint extends TextPaint {
    public static final int $stable = 8;
    private final Paint composePaint;
    private DrawStyle drawStyle;
    private Shadow shadow;
    private TextDecoration textDecoration;

    public AndroidTextPaint(int i10, float f10) {
        super(i10);
        ((TextPaint) this).density = f10;
        this.composePaint = AndroidPaint_androidKt.asComposePaint(this);
        this.textDecoration = TextDecoration.INSTANCE.getNone();
        this.shadow = Shadow.INSTANCE.getNone();
    }

    public static /* synthetic */ void getShadow$ui_text_release$annotations() {
    }

    /* JADX INFO: renamed from: setBrush-12SF9DM$default, reason: not valid java name */
    public static /* synthetic */ void m5443setBrush12SF9DM$default(AndroidTextPaint androidTextPaint, Brush brush, long j10, float f10, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            f10 = Float.NaN;
        }
        androidTextPaint.m5446setBrush12SF9DM(brush, j10, f10);
    }

    /* JADX INFO: renamed from: getBlendMode-0nO6VwU, reason: not valid java name */
    public final int m5444getBlendMode0nO6VwU() {
        return this.composePaint.get_blendMode();
    }

    /* JADX INFO: renamed from: getShadow$ui_text_release, reason: from getter */
    public final Shadow getShadow() {
        return this.shadow;
    }

    /* JADX INFO: renamed from: setBlendMode-s9anfk8, reason: not valid java name */
    public final void m5445setBlendModes9anfk8(int i10) {
        this.composePaint.mo3356setBlendModes9anfk8(i10);
    }

    /* JADX INFO: renamed from: setBrush-12SF9DM, reason: not valid java name */
    public final void m5446setBrush12SF9DM(Brush brush, long size, float alpha) {
        if (((brush instanceof SolidColor) && ((SolidColor) brush).getValue() != Color.INSTANCE.m3508getUnspecified0d7_KjU()) || ((brush instanceof ShaderBrush) && size != Size.INSTANCE.m3313getUnspecifiedNHjbRc())) {
            brush.mo3425applyToPq9zytI(size, this.composePaint, Float.isNaN(alpha) ? this.composePaint.getAlpha() : d.m(alpha, 0.0f, 1.0f));
        } else if (brush == null) {
            this.composePaint.setShader(null);
        }
    }

    /* JADX INFO: renamed from: setColor-8_81llA, reason: not valid java name */
    public final void m5447setColor8_81llA(long color) {
        if (color != Color.INSTANCE.m3508getUnspecified0d7_KjU()) {
            this.composePaint.mo3357setColor8_81llA(color);
            this.composePaint.setShader(null);
        }
    }

    public final void setDrawStyle(DrawStyle drawStyle) {
        if (drawStyle == null || p.a(this.drawStyle, drawStyle)) {
            return;
        }
        this.drawStyle = drawStyle;
        if (drawStyle.equals(Fill.INSTANCE)) {
            this.composePaint.mo3361setStylek9PVt8s(PaintingStyle.INSTANCE.m3720getFillTiuSbCo());
            return;
        }
        if (drawStyle instanceof Stroke) {
            this.composePaint.mo3361setStylek9PVt8s(PaintingStyle.INSTANCE.m3721getStrokeTiuSbCo());
            Stroke stroke = (Stroke) drawStyle;
            this.composePaint.setStrokeWidth(stroke.getWidth());
            this.composePaint.setStrokeMiterLimit(stroke.getMiter());
            this.composePaint.mo3360setStrokeJoinWw9F2mQ(stroke.getJoin());
            this.composePaint.mo3359setStrokeCapBeK7IIE(stroke.getCap());
            this.composePaint.setPathEffect(stroke.getPathEffect());
        }
    }

    public final void setShadow(Shadow shadow) {
        if (shadow == null || p.a(this.shadow, shadow)) {
            return;
        }
        this.shadow = shadow;
        if (p.a(shadow, Shadow.INSTANCE.getNone())) {
            clearShadowLayer();
        } else {
            setShadowLayer(TextPaintExtensions_androidKt.correctBlurRadius(this.shadow.getBlurRadius()), Offset.m3236getXimpl(this.shadow.getOffset()), Offset.m3237getYimpl(this.shadow.getOffset()), ColorKt.m3526toArgb8_81llA(this.shadow.getColor()));
        }
    }

    public final void setShadow$ui_text_release(Shadow shadow) {
        this.shadow = shadow;
    }

    public final void setTextDecoration(TextDecoration textDecoration) {
        if (textDecoration == null || p.a(this.textDecoration, textDecoration)) {
            return;
        }
        this.textDecoration = textDecoration;
        TextDecoration.Companion companion = TextDecoration.INSTANCE;
        setUnderlineText(textDecoration.contains(companion.getUnderline()));
        setStrikeThruText(this.textDecoration.contains(companion.getLineThrough()));
    }
}
