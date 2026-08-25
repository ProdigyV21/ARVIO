package androidx.compose.foundation.draganddrop;

import android.graphics.Picture;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class CacheDrawScopeDragShadowCallback$cachePicture$1$1 extends r implements l<ContentDrawScope, t0> {
    final /* synthetic */ int $height;
    final /* synthetic */ Picture $picture;
    final /* synthetic */ int $width;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CacheDrawScopeDragShadowCallback$cachePicture$1$1(Picture picture, int i10, int i11) {
        super(1);
        this.$picture = picture;
        this.$width = i10;
        this.$height = i11;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ContentDrawScope) obj);
        return t0.f22605a;
    }

    public final void invoke(ContentDrawScope contentDrawScope) {
        Canvas Canvas = AndroidCanvas_androidKt.Canvas(this.$picture.beginRecording(this.$width, this.$height));
        LayoutDirection layoutDirection = contentDrawScope.getLayoutDirection();
        long jMo3916getSizeNHjbRc = contentDrawScope.mo3916getSizeNHjbRc();
        Density density = contentDrawScope.getDrawContext().getDensity();
        LayoutDirection layoutDirection2 = contentDrawScope.getDrawContext().getLayoutDirection();
        Canvas canvas = contentDrawScope.getDrawContext().getCanvas();
        long jMo3922getSizeNHjbRc = contentDrawScope.getDrawContext().mo3922getSizeNHjbRc();
        DrawContext drawContext = contentDrawScope.getDrawContext();
        drawContext.setDensity(contentDrawScope);
        drawContext.setLayoutDirection(layoutDirection);
        drawContext.setCanvas(Canvas);
        drawContext.mo3923setSizeuvyYCjk(jMo3916getSizeNHjbRc);
        Canvas.save();
        contentDrawScope.drawContent();
        Canvas.restore();
        DrawContext drawContext2 = contentDrawScope.getDrawContext();
        drawContext2.setDensity(density);
        drawContext2.setLayoutDirection(layoutDirection2);
        drawContext2.setCanvas(canvas);
        drawContext2.mo3923setSizeuvyYCjk(jMo3922getSizeNHjbRc);
        this.$picture.endRecording();
        AndroidCanvas_androidKt.getNativeCanvas(contentDrawScope.getDrawContext().getCanvas()).drawPicture(this.$picture);
    }
}
