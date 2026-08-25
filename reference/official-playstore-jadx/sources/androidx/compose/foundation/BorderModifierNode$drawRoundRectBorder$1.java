package androidx.compose.foundation;

import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.Stroke;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class BorderModifierNode$drawRoundRectBorder$1 extends r implements l<ContentDrawScope, t0> {
    final /* synthetic */ long $borderSize;
    final /* synthetic */ Stroke $borderStroke;
    final /* synthetic */ Brush $brush;
    final /* synthetic */ long $cornerRadius;
    final /* synthetic */ boolean $fillArea;
    final /* synthetic */ float $halfStroke;
    final /* synthetic */ float $strokeWidth;
    final /* synthetic */ long $topLeft;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BorderModifierNode$drawRoundRectBorder$1(boolean z, Brush brush, long j10, float f10, float f11, long j11, long j12, Stroke stroke) {
        super(1);
        this.$fillArea = z;
        this.$brush = brush;
        this.$cornerRadius = j10;
        this.$halfStroke = f10;
        this.$strokeWidth = f11;
        this.$topLeft = j11;
        this.$borderSize = j12;
        this.$borderStroke = stroke;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ContentDrawScope) obj);
        return t0.f22605a;
    }

    public final void invoke(ContentDrawScope contentDrawScope) {
        contentDrawScope.drawContent();
        if (this.$fillArea) {
            androidx.compose.ui.graphics.drawscope.c.L(contentDrawScope, this.$brush, 0L, 0L, this.$cornerRadius, 0.0f, null, null, 0, 246, null);
            return;
        }
        float fM3211getXimpl = CornerRadius.m3211getXimpl(this.$cornerRadius);
        float f10 = this.$halfStroke;
        if (fM3211getXimpl >= f10) {
            androidx.compose.ui.graphics.drawscope.c.L(contentDrawScope, this.$brush, this.$topLeft, this.$borderSize, BorderKt.m195shrinkKibmq7A(this.$cornerRadius, f10), 0.0f, this.$borderStroke, null, 0, 208, null);
            return;
        }
        float f11 = this.$strokeWidth;
        float fM3305getWidthimpl = Size.m3305getWidthimpl(contentDrawScope.mo3916getSizeNHjbRc()) - this.$strokeWidth;
        float fM3302getHeightimpl = Size.m3302getHeightimpl(contentDrawScope.mo3916getSizeNHjbRc()) - this.$strokeWidth;
        int iM3460getDifferencertfAjoo = ClipOp.INSTANCE.m3460getDifferencertfAjoo();
        Brush brush = this.$brush;
        long j10 = this.$cornerRadius;
        DrawContext drawContext = contentDrawScope.getDrawContext();
        long jMo3922getSizeNHjbRc = drawContext.mo3922getSizeNHjbRc();
        drawContext.getCanvas().save();
        drawContext.getTransform().mo3925clipRectN_I0leg(f11, f11, fM3305getWidthimpl, fM3302getHeightimpl, iM3460getDifferencertfAjoo);
        androidx.compose.ui.graphics.drawscope.c.L(contentDrawScope, brush, 0L, 0L, j10, 0.0f, null, null, 0, 246, null);
        drawContext.getCanvas().restore();
        drawContext.mo3923setSizeuvyYCjk(jMo3922getSizeNHjbRc);
    }
}
