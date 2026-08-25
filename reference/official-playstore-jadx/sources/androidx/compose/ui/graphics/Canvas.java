package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0004J\u001f\u0010\n\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH&¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH&¢\u0006\u0004\b\u000f\u0010\u0010J!\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\f2\b\b\u0002\u0010\u0012\u001a\u00020\fH&¢\u0006\u0004\b\u0013\u0010\u0010J\u0017\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\fH&¢\u0006\u0004\b\u0015\u0010\u0016J\u001f\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\fH&¢\u0006\u0004\b\u0017\u0010\u0010J\u001f\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\fH\u0016¢\u0006\u0004\b\u001a\u0010\u0010J\u001a\u0010\u001f\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u001bH&ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ$\u0010%\u001a\u00020\u00022\u0006\u0010 \u001a\u00020\u00062\b\b\u0002\u0010\"\u001a\u00020!H\u0016ø\u0001\u0000¢\u0006\u0004\b#\u0010$J<\u0010%\u001a\u00020\u00022\u0006\u0010&\u001a\u00020\f2\u0006\u0010'\u001a\u00020\f2\u0006\u0010(\u001a\u00020\f2\u0006\u0010)\u001a\u00020\f2\b\b\u0002\u0010\"\u001a\u00020!H&ø\u0001\u0000¢\u0006\u0004\b*\u0010+J$\u00100\u001a\u00020\u00022\u0006\u0010-\u001a\u00020,2\b\b\u0002\u0010\"\u001a\u00020!H&ø\u0001\u0000¢\u0006\u0004\b.\u0010/J*\u00106\u001a\u00020\u00022\u0006\u00102\u001a\u0002012\u0006\u00103\u001a\u0002012\u0006\u0010\t\u001a\u00020\bH&ø\u0001\u0000¢\u0006\u0004\b4\u00105J\u001f\u00107\u001a\u00020\u00022\u0006\u0010 \u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b7\u0010\u000bJ7\u00107\u001a\u00020\u00022\u0006\u0010&\u001a\u00020\f2\u0006\u0010'\u001a\u00020\f2\u0006\u0010(\u001a\u00020\f2\u0006\u0010)\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\bH&¢\u0006\u0004\b7\u00108JG\u0010;\u001a\u00020\u00022\u0006\u0010&\u001a\u00020\f2\u0006\u0010'\u001a\u00020\f2\u0006\u0010(\u001a\u00020\f2\u0006\u0010)\u001a\u00020\f2\u0006\u00109\u001a\u00020\f2\u0006\u0010:\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\bH&¢\u0006\u0004\b;\u0010<J\u001f\u0010=\u001a\u00020\u00022\u0006\u0010 \u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b=\u0010\u000bJ7\u0010=\u001a\u00020\u00022\u0006\u0010&\u001a\u00020\f2\u0006\u0010'\u001a\u00020\f2\u0006\u0010(\u001a\u00020\f2\u0006\u0010)\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\bH&¢\u0006\u0004\b=\u00108J*\u0010B\u001a\u00020\u00022\u0006\u0010>\u001a\u0002012\u0006\u0010?\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\bH&ø\u0001\u0000¢\u0006\u0004\b@\u0010AJ7\u0010G\u001a\u00020\u00022\u0006\u0010 \u001a\u00020\u00062\u0006\u0010C\u001a\u00020\f2\u0006\u0010D\u001a\u00020\f2\u0006\u0010F\u001a\u00020E2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\bG\u0010HJO\u0010G\u001a\u00020\u00022\u0006\u0010&\u001a\u00020\f2\u0006\u0010'\u001a\u00020\f2\u0006\u0010(\u001a\u00020\f2\u0006\u0010)\u001a\u00020\f2\u0006\u0010C\u001a\u00020\f2\u0006\u0010D\u001a\u00020\f2\u0006\u0010F\u001a\u00020E2\u0006\u0010\t\u001a\u00020\bH&¢\u0006\u0004\bG\u0010IJ7\u0010L\u001a\u00020\u00022\u0006\u0010 \u001a\u00020\u00062\u0006\u0010J\u001a\u00020\f2\u0006\u0010K\u001a\u00020\f2\u0006\u0010F\u001a\u00020E2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\bL\u0010HJ\u001f\u0010M\u001a\u00020\u00022\u0006\u0010-\u001a\u00020,2\u0006\u0010\t\u001a\u00020\bH&¢\u0006\u0004\bM\u0010NJ*\u0010T\u001a\u00020\u00022\u0006\u0010P\u001a\u00020O2\u0006\u0010Q\u001a\u0002012\u0006\u0010\t\u001a\u00020\bH&ø\u0001\u0000¢\u0006\u0004\bR\u0010SJJ\u0010]\u001a\u00020\u00022\u0006\u0010P\u001a\u00020O2\b\b\u0002\u0010V\u001a\u00020U2\b\b\u0002\u0010X\u001a\u00020W2\b\b\u0002\u0010Y\u001a\u00020U2\b\b\u0002\u0010Z\u001a\u00020W2\u0006\u0010\t\u001a\u00020\bH&ø\u0001\u0000¢\u0006\u0004\b[\u0010\\J0\u0010d\u001a\u00020\u00022\u0006\u0010_\u001a\u00020^2\f\u0010a\u001a\b\u0012\u0004\u0012\u0002010`2\u0006\u0010\t\u001a\u00020\bH&ø\u0001\u0000¢\u0006\u0004\bb\u0010cJ*\u0010h\u001a\u00020\u00022\u0006\u0010_\u001a\u00020^2\u0006\u0010a\u001a\u00020e2\u0006\u0010\t\u001a\u00020\bH&ø\u0001\u0000¢\u0006\u0004\bf\u0010gJ*\u0010o\u001a\u00020\u00022\u0006\u0010j\u001a\u00020i2\u0006\u0010l\u001a\u00020k2\u0006\u0010\t\u001a\u00020\bH&ø\u0001\u0000¢\u0006\u0004\bm\u0010nJ\u000f\u0010p\u001a\u00020\u0002H&¢\u0006\u0004\bp\u0010\u0004J\u000f\u0010q\u001a\u00020\u0002H&¢\u0006\u0004\bq\u0010\u0004ø\u0001\u0001\u0082\u0002\r\n\u0005\b¡\u001e0\u0001\n\u0004\b!0\u0001¨\u0006rÀ\u0006\u0003"}, d2 = {"Landroidx/compose/ui/graphics/Canvas;", "", "Lx6/t0;", "save", "()V", "restore", "Landroidx/compose/ui/geometry/Rect;", "bounds", "Landroidx/compose/ui/graphics/Paint;", "paint", "saveLayer", "(Landroidx/compose/ui/geometry/Rect;Landroidx/compose/ui/graphics/Paint;)V", "", "dx", "dy", "translate", "(FF)V", "sx", "sy", "scale", "degrees", "rotate", "(F)V", "skew", "sxRad", "syRad", "skewRad", "Landroidx/compose/ui/graphics/Matrix;", "matrix", "concat-58bKbWc", "([F)V", "concat", "rect", "Landroidx/compose/ui/graphics/ClipOp;", "clipOp", "clipRect-mtrdD-E", "(Landroidx/compose/ui/geometry/Rect;I)V", "clipRect", TtmlNode.LEFT, "top", TtmlNode.RIGHT, "bottom", "clipRect-N_I0leg", "(FFFFI)V", "Landroidx/compose/ui/graphics/Path;", "path", "clipPath-mtrdD-E", "(Landroidx/compose/ui/graphics/Path;I)V", "clipPath", "Landroidx/compose/ui/geometry/Offset;", "p1", "p2", "drawLine-Wko1d7g", "(JJLandroidx/compose/ui/graphics/Paint;)V", "drawLine", "drawRect", "(FFFFLandroidx/compose/ui/graphics/Paint;)V", "radiusX", "radiusY", "drawRoundRect", "(FFFFFFLandroidx/compose/ui/graphics/Paint;)V", "drawOval", TtmlNode.CENTER, "radius", "drawCircle-9KIMszo", "(JFLandroidx/compose/ui/graphics/Paint;)V", "drawCircle", "startAngle", "sweepAngle", "", "useCenter", "drawArc", "(Landroidx/compose/ui/geometry/Rect;FFZLandroidx/compose/ui/graphics/Paint;)V", "(FFFFFFZLandroidx/compose/ui/graphics/Paint;)V", "startAngleRad", "sweepAngleRad", "drawArcRad", "drawPath", "(Landroidx/compose/ui/graphics/Path;Landroidx/compose/ui/graphics/Paint;)V", "Landroidx/compose/ui/graphics/ImageBitmap;", "image", "topLeftOffset", "drawImage-d-4ec7I", "(Landroidx/compose/ui/graphics/ImageBitmap;JLandroidx/compose/ui/graphics/Paint;)V", "drawImage", "Landroidx/compose/ui/unit/IntOffset;", "srcOffset", "Landroidx/compose/ui/unit/IntSize;", "srcSize", "dstOffset", "dstSize", "drawImageRect-HPBpro0", "(Landroidx/compose/ui/graphics/ImageBitmap;JJJJLandroidx/compose/ui/graphics/Paint;)V", "drawImageRect", "Landroidx/compose/ui/graphics/PointMode;", "pointMode", "", "points", "drawPoints-O7TthRY", "(ILjava/util/List;Landroidx/compose/ui/graphics/Paint;)V", "drawPoints", "", "drawRawPoints-O7TthRY", "(I[FLandroidx/compose/ui/graphics/Paint;)V", "drawRawPoints", "Landroidx/compose/ui/graphics/Vertices;", "vertices", "Landroidx/compose/ui/graphics/BlendMode;", "blendMode", "drawVertices-TPEHhCM", "(Landroidx/compose/ui/graphics/Vertices;ILandroidx/compose/ui/graphics/Paint;)V", "drawVertices", "enableZ", "disableZ", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface Canvas {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        /* JADX INFO: renamed from: clipRect-mtrdD-E, reason: not valid java name */
        public static void m3450clipRectmtrdDE(Canvas canvas, Rect rect, int i10) {
            b.a(canvas, rect, i10);
        }

        @Deprecated
        public static void drawArc(Canvas canvas, Rect rect, float f10, float f11, boolean z, Paint paint) {
            b.b(canvas, rect, f10, f11, z, paint);
        }

        @Deprecated
        public static void drawArcRad(Canvas canvas, Rect rect, float f10, float f11, boolean z, Paint paint) {
            b.c(canvas, rect, f10, f11, z, paint);
        }

        @Deprecated
        public static void drawOval(Canvas canvas, Rect rect, Paint paint) {
            b.d(canvas, rect, paint);
        }

        @Deprecated
        public static void drawRect(Canvas canvas, Rect rect, Paint paint) {
            b.e(canvas, rect, paint);
        }

        @Deprecated
        public static void skewRad(Canvas canvas, float f10, float f11) {
            b.f(canvas, f10, f11);
        }
    }

    /* JADX INFO: renamed from: clipPath-mtrdD-E */
    void mo3330clipPathmtrdDE(Path path, int clipOp);

    /* JADX INFO: renamed from: clipRect-N_I0leg */
    void mo3331clipRectN_I0leg(float left, float top, float right, float bottom, int clipOp);

    /* JADX INFO: renamed from: clipRect-mtrdD-E */
    void mo3332clipRectmtrdDE(Rect rect, int clipOp);

    /* JADX INFO: renamed from: concat-58bKbWc */
    void mo3333concat58bKbWc(float[] matrix);

    void disableZ();

    void drawArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean useCenter, Paint paint);

    void drawArc(Rect rect, float startAngle, float sweepAngle, boolean useCenter, Paint paint);

    void drawArcRad(Rect rect, float startAngleRad, float sweepAngleRad, boolean useCenter, Paint paint);

    /* JADX INFO: renamed from: drawCircle-9KIMszo */
    void mo3334drawCircle9KIMszo(long center, float radius, Paint paint);

    /* JADX INFO: renamed from: drawImage-d-4ec7I */
    void mo3335drawImaged4ec7I(ImageBitmap image, long topLeftOffset, Paint paint);

    /* JADX INFO: renamed from: drawImageRect-HPBpro0 */
    void mo3336drawImageRectHPBpro0(ImageBitmap image, long srcOffset, long srcSize, long dstOffset, long dstSize, Paint paint);

    /* JADX INFO: renamed from: drawLine-Wko1d7g */
    void mo3337drawLineWko1d7g(long p12, long p22, Paint paint);

    void drawOval(float left, float top, float right, float bottom, Paint paint);

    void drawOval(Rect rect, Paint paint);

    void drawPath(Path path, Paint paint);

    /* JADX INFO: renamed from: drawPoints-O7TthRY */
    void mo3338drawPointsO7TthRY(int pointMode, List<Offset> points, Paint paint);

    /* JADX INFO: renamed from: drawRawPoints-O7TthRY */
    void mo3339drawRawPointsO7TthRY(int pointMode, float[] points, Paint paint);

    void drawRect(float left, float top, float right, float bottom, Paint paint);

    void drawRect(Rect rect, Paint paint);

    void drawRoundRect(float left, float top, float right, float bottom, float radiusX, float radiusY, Paint paint);

    /* JADX INFO: renamed from: drawVertices-TPEHhCM */
    void mo3340drawVerticesTPEHhCM(Vertices vertices, int blendMode, Paint paint);

    void enableZ();

    void restore();

    void rotate(float degrees);

    void save();

    void saveLayer(Rect bounds, Paint paint);

    void scale(float sx, float sy);

    void skew(float sx, float sy);

    void skewRad(float sxRad, float syRad);

    void translate(float dx, float dy);
}
