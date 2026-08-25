package androidx.compose.ui.text.platform.style;

import android.graphics.Paint;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import androidx.compose.ui.graphics.AndroidPathEffect_androidKt;
import androidx.compose.ui.graphics.PathEffect;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.graphics.drawscope.Stroke;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0016\u0010\u000b\u001a\u00020\b*\u00020\u0007H\u0002ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ\u0016\u0010\u0010\u001a\u00020\r*\u00020\fH\u0002ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u0019\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0016¢\u0006\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0019"}, d2 = {"Landroidx/compose/ui/text/platform/style/DrawStyleSpan;", "Landroid/text/style/CharacterStyle;", "Landroid/text/style/UpdateAppearance;", "Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "drawStyle", "<init>", "(Landroidx/compose/ui/graphics/drawscope/DrawStyle;)V", "Landroidx/compose/ui/graphics/StrokeJoin;", "Landroid/graphics/Paint$Join;", "toAndroidJoin-Ww9F2mQ", "(I)Landroid/graphics/Paint$Join;", "toAndroidJoin", "Landroidx/compose/ui/graphics/StrokeCap;", "Landroid/graphics/Paint$Cap;", "toAndroidCap-BeK7IIE", "(I)Landroid/graphics/Paint$Cap;", "toAndroidCap", "Landroid/text/TextPaint;", "textPaint", "Lx6/t0;", "updateDrawState", "(Landroid/text/TextPaint;)V", "Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "getDrawStyle", "()Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DrawStyleSpan extends CharacterStyle implements UpdateAppearance {
    public static final int $stable = 8;
    private final DrawStyle drawStyle;

    public DrawStyleSpan(DrawStyle drawStyle) {
        this.drawStyle = drawStyle;
    }

    /* JADX INFO: renamed from: toAndroidCap-BeK7IIE, reason: not valid java name */
    private final Paint.Cap m5462toAndroidCapBeK7IIE(int i10) {
        StrokeCap.Companion companion = StrokeCap.INSTANCE;
        return StrokeCap.m3799equalsimpl0(i10, companion.m3803getButtKaPHkGw()) ? Paint.Cap.BUTT : StrokeCap.m3799equalsimpl0(i10, companion.m3804getRoundKaPHkGw()) ? Paint.Cap.ROUND : StrokeCap.m3799equalsimpl0(i10, companion.m3805getSquareKaPHkGw()) ? Paint.Cap.SQUARE : Paint.Cap.BUTT;
    }

    /* JADX INFO: renamed from: toAndroidJoin-Ww9F2mQ, reason: not valid java name */
    private final Paint.Join m5463toAndroidJoinWw9F2mQ(int i10) {
        StrokeJoin.Companion companion = StrokeJoin.INSTANCE;
        return StrokeJoin.m3809equalsimpl0(i10, companion.m3814getMiterLxFBmk8()) ? Paint.Join.MITER : StrokeJoin.m3809equalsimpl0(i10, companion.m3815getRoundLxFBmk8()) ? Paint.Join.ROUND : StrokeJoin.m3809equalsimpl0(i10, companion.m3813getBevelLxFBmk8()) ? Paint.Join.BEVEL : Paint.Join.MITER;
    }

    public final DrawStyle getDrawStyle() {
        return this.drawStyle;
    }

    @Override // android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        if (textPaint != null) {
            DrawStyle drawStyle = this.drawStyle;
            if (p.a(drawStyle, Fill.INSTANCE)) {
                textPaint.setStyle(Paint.Style.FILL);
                return;
            }
            if (drawStyle instanceof Stroke) {
                textPaint.setStyle(Paint.Style.STROKE);
                textPaint.setStrokeWidth(((Stroke) this.drawStyle).getWidth());
                textPaint.setStrokeMiter(((Stroke) this.drawStyle).getMiter());
                textPaint.setStrokeJoin(m5463toAndroidJoinWw9F2mQ(((Stroke) this.drawStyle).getJoin()));
                textPaint.setStrokeCap(m5462toAndroidCapBeK7IIE(((Stroke) this.drawStyle).getCap()));
                PathEffect pathEffect = ((Stroke) this.drawStyle).getPathEffect();
                textPaint.setPathEffect(pathEffect != null ? AndroidPathEffect_androidKt.asAndroidPathEffect(pathEffect) : null);
            }
        }
    }
}
