package androidx.tv.material3;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.node.DrawModifierNode;

/* JADX INFO: loaded from: classes3.dex */
public final class g3 extends Modifier.Node implements DrawModifierNode {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Shape f6011i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f6012l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f6013m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Paint f6014n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public android.graphics.Paint f6015o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public u3 f6016p;

    public final void a() {
        int iM3526toArgb8_81llA = ColorKt.m3526toArgb8_81llA(Color.m3471copywmQWz5c$default(this.f6013m, 0.0f, 0.0f, 0.0f, 0.0f, 14, null));
        int iM3526toArgb8_81llA2 = ColorKt.m3526toArgb8_81llA(this.f6013m);
        this.f6015o.setColor(iM3526toArgb8_81llA);
        this.f6015o.setShadowLayer(this.f6012l, 0.0f, 0.0f, iM3526toArgb8_81llA2);
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public final void draw(ContentDrawScope contentDrawScope) {
        ContentDrawScope contentDrawScope2;
        Canvas canvas = contentDrawScope.getDrawContext().getCanvas();
        if (this.f6014n == null) {
            Paint Paint = AndroidPaint_androidKt.Paint();
            this.f6014n = Paint;
            this.f6015o = Paint.getInternalPaint();
            a();
        }
        if (this.f6016p == null) {
            this.f6016p = new u3(this.f6011i, contentDrawScope.mo3916getSizeNHjbRc(), contentDrawScope.getLayoutDirection(), contentDrawScope);
            contentDrawScope2 = contentDrawScope;
        } else {
            contentDrawScope2 = contentDrawScope;
        }
        Outline outlineC = this.f6016p.c(this.f6011i, contentDrawScope2.mo3916getSizeNHjbRc(), contentDrawScope2.getLayoutDirection(), contentDrawScope2);
        if (outlineC instanceof Outline.Rectangle) {
            canvas.drawRect(((Outline.Rectangle) outlineC).getRect(), this.f6014n);
        } else if (outlineC instanceof Outline.Rounded) {
            Outline.Rounded rounded = (Outline.Rounded) outlineC;
            canvas.drawRoundRect(0.0f, 0.0f, Size.m3305getWidthimpl(contentDrawScope2.mo3916getSizeNHjbRc()), Size.m3302getHeightimpl(contentDrawScope2.mo3916getSizeNHjbRc()), CornerRadius.m3211getXimpl(rounded.getRoundRect().m3286getTopLeftCornerRadiuskKHJgLs()), CornerRadius.m3212getYimpl(rounded.getRoundRect().m3286getTopLeftCornerRadiuskKHJgLs()), this.f6014n);
        } else if (outlineC instanceof Outline.Generic) {
            canvas.drawPath(((Outline.Generic) outlineC).getPath(), this.f6014n);
        }
        contentDrawScope2.drawContent();
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public final /* synthetic */ void onMeasureResultChanged() {
        androidx.compose.ui.node.b.a(this);
    }
}
