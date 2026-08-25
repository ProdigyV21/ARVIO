package androidx.compose.ui.graphics;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.Region;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.PointMode;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0005\u0010\u0003J\u000f\u0010\u0006\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0006\u0010\u0003J\u001f\u0010\u000b\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u001f\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0014\u0010\u0011J\u0017\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u001f\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0018\u0010\u0011J\u001a\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0019H\u0016ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ:\u0010&\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\r2\u0006\u0010#\u001a\u00020\"H\u0016ø\u0001\u0000¢\u0006\u0004\b$\u0010%J\"\u0010+\u001a\u00020\u00042\u0006\u0010(\u001a\u00020'2\u0006\u0010#\u001a\u00020\"H\u0016ø\u0001\u0000¢\u0006\u0004\b)\u0010*J\u0014\u0010/\u001a\u00020,*\u00020\"ø\u0001\u0000¢\u0006\u0004\b-\u0010.J*\u00105\u001a\u00020\u00042\u0006\u00101\u001a\u0002002\u0006\u00102\u001a\u0002002\u0006\u0010\n\u001a\u00020\tH\u0016ø\u0001\u0000¢\u0006\u0004\b3\u00104J7\u00106\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b6\u00107JG\u0010:\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\r2\u0006\u00108\u001a\u00020\r2\u0006\u00109\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b:\u0010;J7\u0010<\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b<\u00107J*\u0010A\u001a\u00020\u00042\u0006\u0010=\u001a\u0002002\u0006\u0010>\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\tH\u0016ø\u0001\u0000¢\u0006\u0004\b?\u0010@JO\u0010F\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\r2\u0006\u0010B\u001a\u00020\r2\u0006\u0010C\u001a\u00020\r2\u0006\u0010E\u001a\u00020D2\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\bF\u0010GJ\u001f\u0010H\u001a\u00020\u00042\u0006\u0010(\u001a\u00020'2\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\bH\u0010IJ*\u0010O\u001a\u00020\u00042\u0006\u0010K\u001a\u00020J2\u0006\u0010L\u001a\u0002002\u0006\u0010\n\u001a\u00020\tH\u0016ø\u0001\u0000¢\u0006\u0004\bM\u0010NJB\u0010X\u001a\u00020\u00042\u0006\u0010K\u001a\u00020J2\u0006\u0010Q\u001a\u00020P2\u0006\u0010S\u001a\u00020R2\u0006\u0010T\u001a\u00020P2\u0006\u0010U\u001a\u00020R2\u0006\u0010\n\u001a\u00020\tH\u0016ø\u0001\u0000¢\u0006\u0004\bV\u0010WJ0\u0010_\u001a\u00020\u00042\u0006\u0010Z\u001a\u00020Y2\f\u0010\\\u001a\b\u0012\u0004\u0012\u0002000[2\u0006\u0010\n\u001a\u00020\tH\u0016ø\u0001\u0000¢\u0006\u0004\b]\u0010^J\u000f\u0010`\u001a\u00020\u0004H\u0016¢\u0006\u0004\b`\u0010\u0003J\u000f\u0010a\u001a\u00020\u0004H\u0016¢\u0006\u0004\ba\u0010\u0003J*\u0010e\u001a\u00020\u00042\u0006\u0010Z\u001a\u00020Y2\u0006\u0010\\\u001a\u00020b2\u0006\u0010\n\u001a\u00020\tH\u0016ø\u0001\u0000¢\u0006\u0004\bc\u0010dJ*\u0010l\u001a\u00020\u00042\u0006\u0010g\u001a\u00020f2\u0006\u0010i\u001a\u00020h2\u0006\u0010\n\u001a\u00020\tH\u0016ø\u0001\u0000¢\u0006\u0004\bj\u0010kJ%\u0010_\u001a\u00020\u00042\f\u0010\\\u001a\b\u0012\u0004\u0012\u0002000[2\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b_\u0010mJ-\u0010p\u001a\u00020\u00042\f\u0010\\\u001a\b\u0012\u0004\u0012\u0002000[2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010o\u001a\u00020nH\u0002¢\u0006\u0004\bp\u0010qJ'\u0010e\u001a\u00020\u00042\u0006\u0010\\\u001a\u00020b2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010o\u001a\u00020nH\u0002¢\u0006\u0004\be\u0010rJ'\u0010s\u001a\u00020\u00042\u0006\u0010\\\u001a\u00020b2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010o\u001a\u00020nH\u0002¢\u0006\u0004\bs\u0010rR,\u0010v\u001a\u00060tj\u0002`u8\u0000@\u0000X\u0081\u000e¢\u0006\u0018\n\u0004\bv\u0010w\u0012\u0004\b|\u0010\u0003\u001a\u0004\bx\u0010y\"\u0004\bz\u0010{R\u0018\u0010~\u001a\u0004\u0018\u00010}8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b~\u0010\u007fR\u001a\u0010\u0080\u0001\u001a\u0004\u0018\u00010}8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u0080\u0001\u0010\u007f\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0081\u0001"}, d2 = {"Landroidx/compose/ui/graphics/AndroidCanvas;", "Landroidx/compose/ui/graphics/Canvas;", "<init>", "()V", "Lx6/t0;", "save", "restore", "Landroidx/compose/ui/geometry/Rect;", "bounds", "Landroidx/compose/ui/graphics/Paint;", "paint", "saveLayer", "(Landroidx/compose/ui/geometry/Rect;Landroidx/compose/ui/graphics/Paint;)V", "", "dx", "dy", "translate", "(FF)V", "sx", "sy", "scale", "degrees", "rotate", "(F)V", "skew", "Landroidx/compose/ui/graphics/Matrix;", "matrix", "concat-58bKbWc", "([F)V", "concat", TtmlNode.LEFT, "top", TtmlNode.RIGHT, "bottom", "Landroidx/compose/ui/graphics/ClipOp;", "clipOp", "clipRect-N_I0leg", "(FFFFI)V", "clipRect", "Landroidx/compose/ui/graphics/Path;", "path", "clipPath-mtrdD-E", "(Landroidx/compose/ui/graphics/Path;I)V", "clipPath", "Landroid/graphics/Region$Op;", "toRegionOp--7u2Bmg", "(I)Landroid/graphics/Region$Op;", "toRegionOp", "Landroidx/compose/ui/geometry/Offset;", "p1", "p2", "drawLine-Wko1d7g", "(JJLandroidx/compose/ui/graphics/Paint;)V", "drawLine", "drawRect", "(FFFFLandroidx/compose/ui/graphics/Paint;)V", "radiusX", "radiusY", "drawRoundRect", "(FFFFFFLandroidx/compose/ui/graphics/Paint;)V", "drawOval", TtmlNode.CENTER, "radius", "drawCircle-9KIMszo", "(JFLandroidx/compose/ui/graphics/Paint;)V", "drawCircle", "startAngle", "sweepAngle", "", "useCenter", "drawArc", "(FFFFFFZLandroidx/compose/ui/graphics/Paint;)V", "drawPath", "(Landroidx/compose/ui/graphics/Path;Landroidx/compose/ui/graphics/Paint;)V", "Landroidx/compose/ui/graphics/ImageBitmap;", "image", "topLeftOffset", "drawImage-d-4ec7I", "(Landroidx/compose/ui/graphics/ImageBitmap;JLandroidx/compose/ui/graphics/Paint;)V", "drawImage", "Landroidx/compose/ui/unit/IntOffset;", "srcOffset", "Landroidx/compose/ui/unit/IntSize;", "srcSize", "dstOffset", "dstSize", "drawImageRect-HPBpro0", "(Landroidx/compose/ui/graphics/ImageBitmap;JJJJLandroidx/compose/ui/graphics/Paint;)V", "drawImageRect", "Landroidx/compose/ui/graphics/PointMode;", "pointMode", "", "points", "drawPoints-O7TthRY", "(ILjava/util/List;Landroidx/compose/ui/graphics/Paint;)V", "drawPoints", "enableZ", "disableZ", "", "drawRawPoints-O7TthRY", "(I[FLandroidx/compose/ui/graphics/Paint;)V", "drawRawPoints", "Landroidx/compose/ui/graphics/Vertices;", "vertices", "Landroidx/compose/ui/graphics/BlendMode;", "blendMode", "drawVertices-TPEHhCM", "(Landroidx/compose/ui/graphics/Vertices;ILandroidx/compose/ui/graphics/Paint;)V", "drawVertices", "(Ljava/util/List;Landroidx/compose/ui/graphics/Paint;)V", "", "stepBy", "drawLines", "(Ljava/util/List;Landroidx/compose/ui/graphics/Paint;I)V", "([FLandroidx/compose/ui/graphics/Paint;I)V", "drawRawLines", "Landroid/graphics/Canvas;", "Landroidx/compose/ui/graphics/NativeCanvas;", "internalCanvas", "Landroid/graphics/Canvas;", "getInternalCanvas", "()Landroid/graphics/Canvas;", "setInternalCanvas", "(Landroid/graphics/Canvas;)V", "getInternalCanvas$annotations", "Landroid/graphics/Rect;", "srcRect", "Landroid/graphics/Rect;", "dstRect", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AndroidCanvas implements Canvas {
    private Rect dstRect;
    private android.graphics.Canvas internalCanvas = AndroidCanvas_androidKt.EmptyCanvas;
    private Rect srcRect;

    private final void drawLines(List<Offset> points, Paint paint, int stepBy) {
        if (points.size() >= 2) {
            android.graphics.Paint paintAsFrameworkPaint = paint.asFrameworkPaint();
            int i10 = 0;
            while (i10 < points.size() - 1) {
                long packedValue = points.get(i10).getPackedValue();
                long packedValue2 = points.get(i10 + 1).getPackedValue();
                this.internalCanvas.drawLine(Offset.m3236getXimpl(packedValue), Offset.m3237getYimpl(packedValue), Offset.m3236getXimpl(packedValue2), Offset.m3237getYimpl(packedValue2), paintAsFrameworkPaint);
                i10 += stepBy;
            }
        }
    }

    private final void drawPoints(List<Offset> points, Paint paint) {
        int size = points.size();
        for (int i10 = 0; i10 < size; i10++) {
            long packedValue = points.get(i10).getPackedValue();
            this.internalCanvas.drawPoint(Offset.m3236getXimpl(packedValue), Offset.m3237getYimpl(packedValue), paint.asFrameworkPaint());
        }
    }

    private final void drawRawLines(float[] points, Paint paint, int stepBy) {
        if (points.length < 4 || points.length % 2 != 0) {
            return;
        }
        android.graphics.Paint paintAsFrameworkPaint = paint.asFrameworkPaint();
        int i10 = 0;
        while (i10 < points.length - 3) {
            this.internalCanvas.drawLine(points[i10], points[i10 + 1], points[i10 + 2], points[i10 + 3], paintAsFrameworkPaint);
            i10 += stepBy * 2;
        }
    }

    private final void drawRawPoints(float[] points, Paint paint, int stepBy) {
        if (points.length % 2 == 0) {
            android.graphics.Paint paintAsFrameworkPaint = paint.asFrameworkPaint();
            int i10 = 0;
            while (i10 < points.length - 1) {
                this.internalCanvas.drawPoint(points[i10], points[i10 + 1], paintAsFrameworkPaint);
                i10 += stepBy;
            }
        }
    }

    public static /* synthetic */ void getInternalCanvas$annotations() {
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* JADX INFO: renamed from: clipPath-mtrdD-E, reason: not valid java name */
    public void mo3330clipPathmtrdDE(Path path, int clipOp) {
        android.graphics.Canvas canvas = this.internalCanvas;
        if (!(path instanceof AndroidPath)) {
            throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
        }
        canvas.clipPath(((AndroidPath) path).getInternalPath(), m3341toRegionOp7u2Bmg(clipOp));
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* JADX INFO: renamed from: clipRect-N_I0leg, reason: not valid java name */
    public void mo3331clipRectN_I0leg(float left, float top, float right, float bottom, int clipOp) {
        this.internalCanvas.clipRect(left, top, right, bottom, m3341toRegionOp7u2Bmg(clipOp));
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* JADX INFO: renamed from: clipRect-mtrdD-E, reason: not valid java name */
    public final /* synthetic */ void mo3332clipRectmtrdDE(androidx.compose.ui.geometry.Rect rect, int i10) {
        b.a(this, rect, i10);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* JADX INFO: renamed from: concat-58bKbWc, reason: not valid java name */
    public void mo3333concat58bKbWc(float[] matrix) {
        if (MatrixKt.m3708isIdentity58bKbWc(matrix)) {
            return;
        }
        android.graphics.Matrix matrix2 = new android.graphics.Matrix();
        AndroidMatrixConversions_androidKt.m3348setFromEL8BTi8(matrix2, matrix);
        this.internalCanvas.concat(matrix2);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void disableZ() {
        CanvasUtils.INSTANCE.enableZ(this.internalCanvas, false);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public final /* synthetic */ void drawArc(androidx.compose.ui.geometry.Rect rect, float f10, float f11, boolean z, Paint paint) {
        b.b(this, rect, f10, f11, z, paint);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public final /* synthetic */ void drawArcRad(androidx.compose.ui.geometry.Rect rect, float f10, float f11, boolean z, Paint paint) {
        b.c(this, rect, f10, f11, z, paint);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* JADX INFO: renamed from: drawCircle-9KIMszo, reason: not valid java name */
    public void mo3334drawCircle9KIMszo(long center, float radius, Paint paint) {
        this.internalCanvas.drawCircle(Offset.m3236getXimpl(center), Offset.m3237getYimpl(center), radius, paint.asFrameworkPaint());
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* JADX INFO: renamed from: drawImage-d-4ec7I, reason: not valid java name */
    public void mo3335drawImaged4ec7I(ImageBitmap image, long topLeftOffset, Paint paint) {
        this.internalCanvas.drawBitmap(AndroidImageBitmap_androidKt.asAndroidBitmap(image), Offset.m3236getXimpl(topLeftOffset), Offset.m3237getYimpl(topLeftOffset), paint.asFrameworkPaint());
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* JADX INFO: renamed from: drawImageRect-HPBpro0, reason: not valid java name */
    public void mo3336drawImageRectHPBpro0(ImageBitmap image, long srcOffset, long srcSize, long dstOffset, long dstSize, Paint paint) {
        if (this.srcRect == null) {
            this.srcRect = new Rect();
            this.dstRect = new Rect();
        }
        android.graphics.Canvas canvas = this.internalCanvas;
        Bitmap bitmapAsAndroidBitmap = AndroidImageBitmap_androidKt.asAndroidBitmap(image);
        Rect rect = this.srcRect;
        rect.left = IntOffset.m5802getXimpl(srcOffset);
        rect.top = IntOffset.m5803getYimpl(srcOffset);
        rect.right = IntSize.m5844getWidthimpl(srcSize) + IntOffset.m5802getXimpl(srcOffset);
        rect.bottom = IntSize.m5843getHeightimpl(srcSize) + IntOffset.m5803getYimpl(srcOffset);
        Rect rect2 = this.dstRect;
        rect2.left = IntOffset.m5802getXimpl(dstOffset);
        rect2.top = IntOffset.m5803getYimpl(dstOffset);
        rect2.right = IntSize.m5844getWidthimpl(dstSize) + IntOffset.m5802getXimpl(dstOffset);
        rect2.bottom = IntSize.m5843getHeightimpl(dstSize) + IntOffset.m5803getYimpl(dstOffset);
        canvas.drawBitmap(bitmapAsAndroidBitmap, rect, rect2, paint.asFrameworkPaint());
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* JADX INFO: renamed from: drawLine-Wko1d7g, reason: not valid java name */
    public void mo3337drawLineWko1d7g(long p12, long p22, Paint paint) {
        this.internalCanvas.drawLine(Offset.m3236getXimpl(p12), Offset.m3237getYimpl(p12), Offset.m3236getXimpl(p22), Offset.m3237getYimpl(p22), paint.asFrameworkPaint());
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public final /* synthetic */ void drawOval(androidx.compose.ui.geometry.Rect rect, Paint paint) {
        b.d(this, rect, paint);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void drawPath(Path path, Paint paint) {
        android.graphics.Canvas canvas = this.internalCanvas;
        if (!(path instanceof AndroidPath)) {
            throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
        }
        canvas.drawPath(((AndroidPath) path).getInternalPath(), paint.asFrameworkPaint());
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* JADX INFO: renamed from: drawPoints-O7TthRY, reason: not valid java name */
    public void mo3338drawPointsO7TthRY(int pointMode, List<Offset> points, Paint paint) {
        PointMode.Companion companion = PointMode.INSTANCE;
        if (PointMode.m3751equalsimpl0(pointMode, companion.m3755getLinesr_lszbg())) {
            drawLines(points, paint, 2);
        } else if (PointMode.m3751equalsimpl0(pointMode, companion.m3757getPolygonr_lszbg())) {
            drawLines(points, paint, 1);
        } else if (PointMode.m3751equalsimpl0(pointMode, companion.m3756getPointsr_lszbg())) {
            drawPoints(points, paint);
        }
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* JADX INFO: renamed from: drawRawPoints-O7TthRY, reason: not valid java name */
    public void mo3339drawRawPointsO7TthRY(int pointMode, float[] points, Paint paint) {
        if (points.length % 2 != 0) {
            throw new IllegalArgumentException("points must have an even number of values");
        }
        PointMode.Companion companion = PointMode.INSTANCE;
        if (PointMode.m3751equalsimpl0(pointMode, companion.m3755getLinesr_lszbg())) {
            drawRawLines(points, paint, 2);
        } else if (PointMode.m3751equalsimpl0(pointMode, companion.m3757getPolygonr_lszbg())) {
            drawRawLines(points, paint, 1);
        } else if (PointMode.m3751equalsimpl0(pointMode, companion.m3756getPointsr_lszbg())) {
            drawRawPoints(points, paint, 2);
        }
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public final /* synthetic */ void drawRect(androidx.compose.ui.geometry.Rect rect, Paint paint) {
        b.e(this, rect, paint);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void drawRoundRect(float left, float top, float right, float bottom, float radiusX, float radiusY, Paint paint) {
        this.internalCanvas.drawRoundRect(left, top, right, bottom, radiusX, radiusY, paint.asFrameworkPaint());
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* JADX INFO: renamed from: drawVertices-TPEHhCM, reason: not valid java name */
    public void mo3340drawVerticesTPEHhCM(Vertices vertices, int blendMode, Paint paint) {
        this.internalCanvas.drawVertices(AndroidVertexMode_androidKt.m3384toAndroidVertexModeJOOmi9M(vertices.getVertexMode()), vertices.getPositions().length, vertices.getPositions(), 0, vertices.getTextureCoordinates(), 0, vertices.getColors(), 0, vertices.getIndices(), 0, vertices.getIndices().length, paint.asFrameworkPaint());
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void enableZ() {
        CanvasUtils.INSTANCE.enableZ(this.internalCanvas, true);
    }

    public final android.graphics.Canvas getInternalCanvas() {
        return this.internalCanvas;
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void restore() {
        this.internalCanvas.restore();
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void rotate(float degrees) {
        this.internalCanvas.rotate(degrees);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void save() {
        this.internalCanvas.save();
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void saveLayer(androidx.compose.ui.geometry.Rect bounds, Paint paint) {
        this.internalCanvas.saveLayer(bounds.getLeft(), bounds.getTop(), bounds.getRight(), bounds.getBottom(), paint.asFrameworkPaint(), 31);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void scale(float sx, float sy) {
        this.internalCanvas.scale(sx, sy);
    }

    public final void setInternalCanvas(android.graphics.Canvas canvas) {
        this.internalCanvas = canvas;
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void skew(float sx, float sy) {
        this.internalCanvas.skew(sx, sy);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public final /* synthetic */ void skewRad(float f10, float f11) {
        b.f(this, f10, f11);
    }

    /* JADX INFO: renamed from: toRegionOp--7u2Bmg, reason: not valid java name */
    public final Region.Op m3341toRegionOp7u2Bmg(int i10) {
        return ClipOp.m3456equalsimpl0(i10, ClipOp.INSTANCE.m3460getDifferencertfAjoo()) ? Region.Op.DIFFERENCE : Region.Op.INTERSECT;
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void translate(float dx, float dy) {
        this.internalCanvas.translate(dx, dy);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void drawArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean useCenter, Paint paint) {
        this.internalCanvas.drawArc(left, top, right, bottom, startAngle, sweepAngle, useCenter, paint.asFrameworkPaint());
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void drawOval(float left, float top, float right, float bottom, Paint paint) {
        this.internalCanvas.drawOval(left, top, right, bottom, paint.asFrameworkPaint());
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void drawRect(float left, float top, float right, float bottom, Paint paint) {
        this.internalCanvas.drawRect(left, top, right, bottom, paint.asFrameworkPaint());
    }
}
