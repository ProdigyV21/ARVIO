package androidx.compose.ui.graphics.drawscope;

import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.DegreesKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aM\u0010\n\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00012\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0086\b¢\u0006\u0004\b\n\u0010\u000b\u001a5\u0010\n\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\n\u001a\u00020\u00012\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0086\b¢\u0006\u0004\b\n\u0010\f\u001aA\u0010\n\u001a\u00020\u0007*\u00020\u00002\b\b\u0002\u0010\r\u001a\u00020\u00012\b\b\u0002\u0010\u000e\u001a\u00020\u00012\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0086\b¢\u0006\u0004\b\n\u0010\u000f\u001aA\u0010\u0010\u001a\u00020\u0007*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00012\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0086\b¢\u0006\u0004\b\u0010\u0010\u000f\u001aB\u0010\u0016\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u00012\b\b\u0002\u0010\u0013\u001a\u00020\u00122\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0086\bø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u001aB\u0010\u0019\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00012\b\b\u0002\u0010\u0013\u001a\u00020\u00122\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0086\bø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0015\u001aJ\u0010\u001e\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u00012\b\b\u0002\u0010\u0013\u001a\u00020\u00122\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0086\bø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001aB\u0010\u001e\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u00012\b\b\u0002\u0010\u0013\u001a\u00020\u00122\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0086\bø\u0001\u0000¢\u0006\u0004\b\u001f\u0010\u0015\u001ab\u0010$\u001a\u00020\u0007*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010!\u001a\u00020 2\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0086\bø\u0001\u0000¢\u0006\u0004\b\"\u0010#\u001aB\u0010)\u001a\u00020\u0007*\u00020\u00002\u0006\u0010&\u001a\u00020%2\b\b\u0002\u0010!\u001a\u00020 2\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0086\bø\u0001\u0000¢\u0006\u0004\b'\u0010(\u001a(\u0010+\u001a\u00020\u0007*\u00020\u00002\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u00070\u0006H\u0086\b¢\u0006\u0004\b+\u0010,\u001aF\u00100\u001a\u00020\u0007*\u00020\u00002\u0017\u0010.\u001a\u0013\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\b2\u0017\u0010/\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0086\b¢\u0006\u0004\b0\u00101\u001aP\u0010;\u001a\u00020\u0007*\u00020\u00002\u0006\u00103\u001a\u0002022\u0006\u00105\u001a\u0002042\u0006\u00106\u001a\u00020*2\u0006\u00108\u001a\u0002072\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0086\bø\u0001\u0000¢\u0006\u0004\b9\u0010:\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006<"}, d2 = {"Landroidx/compose/ui/graphics/drawscope/DrawScope;", "", TtmlNode.LEFT, "top", TtmlNode.RIGHT, "bottom", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "block", "inset", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFFFLr7/l;)V", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FLr7/l;)V", "horizontal", "vertical", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFLr7/l;)V", "translate", "degrees", "Landroidx/compose/ui/geometry/Offset;", "pivot", "rotate-Rg1IO4c", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FJLr7/l;)V", "rotate", "radians", "rotateRad-Rg1IO4c", "rotateRad", "scaleX", "scaleY", "scale-Fgt4K4Q", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFJLr7/l;)V", "scale", "scale-Rg1IO4c", "Landroidx/compose/ui/graphics/ClipOp;", "clipOp", "clipRect-rOu3jXo", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFFFILr7/l;)V", "clipRect", "Landroidx/compose/ui/graphics/Path;", "path", "clipPath-KD09W0M", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/graphics/Path;ILr7/l;)V", "clipPath", "Landroidx/compose/ui/graphics/Canvas;", "drawIntoCanvas", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Lr7/l;)V", "Landroidx/compose/ui/graphics/drawscope/DrawTransform;", "transformBlock", "drawBlock", "withTransform", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Lr7/l;Lr7/l;)V", "Landroidx/compose/ui/unit/Density;", "density", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "canvas", "Landroidx/compose/ui/geometry/Size;", ContentDisposition.Parameters.Size, "draw-GRGpd60", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/unit/Density;Landroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/ui/graphics/Canvas;JLr7/l;)V", "draw", "ui-graphics_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DrawScopeKt {
    /* JADX INFO: renamed from: clipPath-KD09W0M, reason: not valid java name */
    public static final void m3982clipPathKD09W0M(DrawScope drawScope, Path path, int i10, l<? super DrawScope, t0> lVar) {
        DrawContext drawContext = drawScope.getDrawContext();
        long jMo3922getSizeNHjbRc = drawContext.mo3922getSizeNHjbRc();
        drawContext.getCanvas().save();
        drawContext.getTransform().mo3924clipPathmtrdDE(path, i10);
        lVar.invoke(drawScope);
        drawContext.getCanvas().restore();
        drawContext.mo3923setSizeuvyYCjk(jMo3922getSizeNHjbRc);
    }

    /* JADX INFO: renamed from: clipPath-KD09W0M$default, reason: not valid java name */
    public static /* synthetic */ void m3983clipPathKD09W0M$default(DrawScope drawScope, Path path, int i10, l lVar, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            i10 = ClipOp.INSTANCE.m3461getIntersectrtfAjoo();
        }
        DrawContext drawContext = drawScope.getDrawContext();
        long jMo3922getSizeNHjbRc = drawContext.mo3922getSizeNHjbRc();
        drawContext.getCanvas().save();
        drawContext.getTransform().mo3924clipPathmtrdDE(path, i10);
        lVar.invoke(drawScope);
        drawContext.getCanvas().restore();
        drawContext.mo3923setSizeuvyYCjk(jMo3922getSizeNHjbRc);
    }

    /* JADX INFO: renamed from: clipRect-rOu3jXo, reason: not valid java name */
    public static final void m3984clipRectrOu3jXo(DrawScope drawScope, float f10, float f11, float f12, float f13, int i10, l<? super DrawScope, t0> lVar) {
        DrawContext drawContext = drawScope.getDrawContext();
        long jMo3922getSizeNHjbRc = drawContext.mo3922getSizeNHjbRc();
        drawContext.getCanvas().save();
        drawContext.getTransform().mo3925clipRectN_I0leg(f10, f11, f12, f13, i10);
        lVar.invoke(drawScope);
        drawContext.getCanvas().restore();
        drawContext.mo3923setSizeuvyYCjk(jMo3922getSizeNHjbRc);
    }

    /* JADX INFO: renamed from: clipRect-rOu3jXo$default, reason: not valid java name */
    public static /* synthetic */ void m3985clipRectrOu3jXo$default(DrawScope drawScope, float f10, float f11, float f12, float f13, int i10, l lVar, int i11, Object obj) {
        float f14 = (i11 & 1) != 0 ? 0.0f : f10;
        float f15 = (i11 & 2) != 0 ? 0.0f : f11;
        if ((i11 & 4) != 0) {
            f12 = Size.m3305getWidthimpl(drawScope.mo3916getSizeNHjbRc());
        }
        float f16 = f12;
        if ((i11 & 8) != 0) {
            f13 = Size.m3302getHeightimpl(drawScope.mo3916getSizeNHjbRc());
        }
        float f17 = f13;
        if ((i11 & 16) != 0) {
            i10 = ClipOp.INSTANCE.m3461getIntersectrtfAjoo();
        }
        DrawContext drawContext = drawScope.getDrawContext();
        long jMo3922getSizeNHjbRc = drawContext.mo3922getSizeNHjbRc();
        drawContext.getCanvas().save();
        drawContext.getTransform().mo3925clipRectN_I0leg(f14, f15, f16, f17, i10);
        lVar.invoke(drawScope);
        drawContext.getCanvas().restore();
        drawContext.mo3923setSizeuvyYCjk(jMo3922getSizeNHjbRc);
    }

    /* JADX INFO: renamed from: draw-GRGpd60, reason: not valid java name */
    public static final void m3986drawGRGpd60(DrawScope drawScope, Density density, LayoutDirection layoutDirection, Canvas canvas, long j10, l<? super DrawScope, t0> lVar) {
        Density density2 = drawScope.getDrawContext().getDensity();
        LayoutDirection layoutDirection2 = drawScope.getDrawContext().getLayoutDirection();
        Canvas canvas2 = drawScope.getDrawContext().getCanvas();
        long jMo3922getSizeNHjbRc = drawScope.getDrawContext().mo3922getSizeNHjbRc();
        DrawContext drawContext = drawScope.getDrawContext();
        drawContext.setDensity(density);
        drawContext.setLayoutDirection(layoutDirection);
        drawContext.setCanvas(canvas);
        drawContext.mo3923setSizeuvyYCjk(j10);
        canvas.save();
        lVar.invoke(drawScope);
        canvas.restore();
        DrawContext drawContext2 = drawScope.getDrawContext();
        drawContext2.setDensity(density2);
        drawContext2.setLayoutDirection(layoutDirection2);
        drawContext2.setCanvas(canvas2);
        drawContext2.mo3923setSizeuvyYCjk(jMo3922getSizeNHjbRc);
    }

    public static final void drawIntoCanvas(DrawScope drawScope, l<? super Canvas, t0> lVar) {
        lVar.invoke(drawScope.getDrawContext().getCanvas());
    }

    public static final void inset(DrawScope drawScope, float f10, float f11, float f12, float f13, l<? super DrawScope, t0> lVar) {
        drawScope.getDrawContext().getTransform().inset(f10, f11, f12, f13);
        lVar.invoke(drawScope);
        drawScope.getDrawContext().getTransform().inset(-f10, -f11, -f12, -f13);
    }

    public static /* synthetic */ void inset$default(DrawScope drawScope, float f10, float f11, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            f10 = 0.0f;
        }
        if ((i10 & 2) != 0) {
            f11 = 0.0f;
        }
        drawScope.getDrawContext().getTransform().inset(f10, f11, f10, f11);
        lVar.invoke(drawScope);
        float f12 = -f10;
        float f13 = -f11;
        drawScope.getDrawContext().getTransform().inset(f12, f13, f12, f13);
    }

    /* JADX INFO: renamed from: rotate-Rg1IO4c, reason: not valid java name */
    public static final void m3987rotateRg1IO4c(DrawScope drawScope, float f10, long j10, l<? super DrawScope, t0> lVar) {
        DrawContext drawContext = drawScope.getDrawContext();
        long jMo3922getSizeNHjbRc = drawContext.mo3922getSizeNHjbRc();
        drawContext.getCanvas().save();
        drawContext.getTransform().mo3928rotateUv8p0NA(f10, j10);
        lVar.invoke(drawScope);
        drawContext.getCanvas().restore();
        drawContext.mo3923setSizeuvyYCjk(jMo3922getSizeNHjbRc);
    }

    /* JADX INFO: renamed from: rotate-Rg1IO4c$default, reason: not valid java name */
    public static /* synthetic */ void m3988rotateRg1IO4c$default(DrawScope drawScope, float f10, long j10, l lVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            j10 = drawScope.mo3915getCenterF1C5BW0();
        }
        DrawContext drawContext = drawScope.getDrawContext();
        long jMo3922getSizeNHjbRc = drawContext.mo3922getSizeNHjbRc();
        drawContext.getCanvas().save();
        drawContext.getTransform().mo3928rotateUv8p0NA(f10, j10);
        lVar.invoke(drawScope);
        drawContext.getCanvas().restore();
        drawContext.mo3923setSizeuvyYCjk(jMo3922getSizeNHjbRc);
    }

    /* JADX INFO: renamed from: rotateRad-Rg1IO4c, reason: not valid java name */
    public static final void m3989rotateRadRg1IO4c(DrawScope drawScope, float f10, long j10, l<? super DrawScope, t0> lVar) {
        DrawContext drawContext = drawScope.getDrawContext();
        long jMo3922getSizeNHjbRc = drawContext.mo3922getSizeNHjbRc();
        drawContext.getCanvas().save();
        drawContext.getTransform().mo3928rotateUv8p0NA(DegreesKt.degrees(f10), j10);
        lVar.invoke(drawScope);
        drawContext.getCanvas().restore();
        drawContext.mo3923setSizeuvyYCjk(jMo3922getSizeNHjbRc);
    }

    /* JADX INFO: renamed from: rotateRad-Rg1IO4c$default, reason: not valid java name */
    public static /* synthetic */ void m3990rotateRadRg1IO4c$default(DrawScope drawScope, float f10, long j10, l lVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            j10 = drawScope.mo3915getCenterF1C5BW0();
        }
        DrawContext drawContext = drawScope.getDrawContext();
        long jMo3922getSizeNHjbRc = drawContext.mo3922getSizeNHjbRc();
        drawContext.getCanvas().save();
        drawContext.getTransform().mo3928rotateUv8p0NA(DegreesKt.degrees(f10), j10);
        lVar.invoke(drawScope);
        drawContext.getCanvas().restore();
        drawContext.mo3923setSizeuvyYCjk(jMo3922getSizeNHjbRc);
    }

    /* JADX INFO: renamed from: scale-Fgt4K4Q, reason: not valid java name */
    public static final void m3991scaleFgt4K4Q(DrawScope drawScope, float f10, float f11, long j10, l<? super DrawScope, t0> lVar) {
        DrawContext drawContext = drawScope.getDrawContext();
        long jMo3922getSizeNHjbRc = drawContext.mo3922getSizeNHjbRc();
        drawContext.getCanvas().save();
        drawContext.getTransform().mo3929scale0AR0LA0(f10, f11, j10);
        lVar.invoke(drawScope);
        drawContext.getCanvas().restore();
        drawContext.mo3923setSizeuvyYCjk(jMo3922getSizeNHjbRc);
    }

    /* JADX INFO: renamed from: scale-Fgt4K4Q$default, reason: not valid java name */
    public static /* synthetic */ void m3992scaleFgt4K4Q$default(DrawScope drawScope, float f10, float f11, long j10, l lVar, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            j10 = drawScope.mo3915getCenterF1C5BW0();
        }
        DrawContext drawContext = drawScope.getDrawContext();
        long jMo3922getSizeNHjbRc = drawContext.mo3922getSizeNHjbRc();
        drawContext.getCanvas().save();
        drawContext.getTransform().mo3929scale0AR0LA0(f10, f11, j10);
        lVar.invoke(drawScope);
        drawContext.getCanvas().restore();
        drawContext.mo3923setSizeuvyYCjk(jMo3922getSizeNHjbRc);
    }

    /* JADX INFO: renamed from: scale-Rg1IO4c, reason: not valid java name */
    public static final void m3993scaleRg1IO4c(DrawScope drawScope, float f10, long j10, l<? super DrawScope, t0> lVar) {
        DrawContext drawContext = drawScope.getDrawContext();
        long jMo3922getSizeNHjbRc = drawContext.mo3922getSizeNHjbRc();
        drawContext.getCanvas().save();
        drawContext.getTransform().mo3929scale0AR0LA0(f10, f10, j10);
        lVar.invoke(drawScope);
        drawContext.getCanvas().restore();
        drawContext.mo3923setSizeuvyYCjk(jMo3922getSizeNHjbRc);
    }

    /* JADX INFO: renamed from: scale-Rg1IO4c$default, reason: not valid java name */
    public static /* synthetic */ void m3994scaleRg1IO4c$default(DrawScope drawScope, float f10, long j10, l lVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            j10 = drawScope.mo3915getCenterF1C5BW0();
        }
        DrawContext drawContext = drawScope.getDrawContext();
        long jMo3922getSizeNHjbRc = drawContext.mo3922getSizeNHjbRc();
        drawContext.getCanvas().save();
        drawContext.getTransform().mo3929scale0AR0LA0(f10, f10, j10);
        lVar.invoke(drawScope);
        drawContext.getCanvas().restore();
        drawContext.mo3923setSizeuvyYCjk(jMo3922getSizeNHjbRc);
    }

    public static final void translate(DrawScope drawScope, float f10, float f11, l<? super DrawScope, t0> lVar) {
        drawScope.getDrawContext().getTransform().translate(f10, f11);
        lVar.invoke(drawScope);
        drawScope.getDrawContext().getTransform().translate(-f10, -f11);
    }

    public static /* synthetic */ void translate$default(DrawScope drawScope, float f10, float f11, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            f10 = 0.0f;
        }
        if ((i10 & 2) != 0) {
            f11 = 0.0f;
        }
        drawScope.getDrawContext().getTransform().translate(f10, f11);
        lVar.invoke(drawScope);
        drawScope.getDrawContext().getTransform().translate(-f10, -f11);
    }

    public static final void withTransform(DrawScope drawScope, l<? super DrawTransform, t0> lVar, l<? super DrawScope, t0> lVar2) {
        DrawContext drawContext = drawScope.getDrawContext();
        long jMo3922getSizeNHjbRc = drawContext.mo3922getSizeNHjbRc();
        drawContext.getCanvas().save();
        lVar.invoke(drawContext.getTransform());
        lVar2.invoke(drawScope);
        drawContext.getCanvas().restore();
        drawContext.mo3923setSizeuvyYCjk(jMo3922getSizeNHjbRc);
    }

    public static final void inset(DrawScope drawScope, float f10, l<? super DrawScope, t0> lVar) {
        drawScope.getDrawContext().getTransform().inset(f10, f10, f10, f10);
        lVar.invoke(drawScope);
        float f11 = -f10;
        drawScope.getDrawContext().getTransform().inset(f11, f11, f11, f11);
    }

    public static final void inset(DrawScope drawScope, float f10, float f11, l<? super DrawScope, t0> lVar) {
        drawScope.getDrawContext().getTransform().inset(f10, f11, f10, f11);
        lVar.invoke(drawScope);
        float f12 = -f10;
        float f13 = -f11;
        drawScope.getDrawContext().getTransform().inset(f12, f13, f12, f13);
    }
}
