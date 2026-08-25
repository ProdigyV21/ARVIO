package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRect;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\bf\u0018\u0000 Y2\u00020\u0001:\u0001YJ\u001f\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H&¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\n\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0002H&¢\u0006\u0004\b\n\u0010\u0007J\u001f\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H&¢\u0006\u0004\b\u000b\u0010\u0007J\u001f\u0010\f\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0002H&¢\u0006\u0004\b\f\u0010\u0007J/\u0010\u0011\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0002H&¢\u0006\u0004\b\u0011\u0010\u0012J/\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0002H&¢\u0006\u0004\b\u0017\u0010\u0012J?\u0010\u001a\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u0002H&¢\u0006\u0004\b\u001a\u0010\u001bJ?\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u0002H&¢\u0006\u0004\b\u001e\u0010\u001bJ/\u0010%\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020\u00022\u0006\u0010$\u001a\u00020#H\u0016¢\u0006\u0004\b%\u0010&J/\u0010)\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010'\u001a\u00020\u00022\u0006\u0010(\u001a\u00020\u00022\u0006\u0010$\u001a\u00020#H&¢\u0006\u0004\b)\u0010&J\u0017\u0010*\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u001fH&¢\u0006\u0004\b*\u0010+J\u0017\u0010-\u001a\u00020\u00052\u0006\u0010,\u001a\u00020\u001fH&¢\u0006\u0004\b-\u0010+J'\u0010.\u001a\u00020\u00052\u0006\u0010,\u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020\u0002H&¢\u0006\u0004\b.\u0010/J'\u00100\u001a\u00020\u00052\u0006\u0010,\u001a\u00020\u001f2\u0006\u0010'\u001a\u00020\u00022\u0006\u0010(\u001a\u00020\u0002H&¢\u0006\u0004\b0\u0010/J\u0017\u00103\u001a\u00020\u00052\u0006\u00102\u001a\u000201H&¢\u0006\u0004\b3\u00104J$\u0010:\u001a\u00020\u00052\u0006\u00105\u001a\u00020\u00002\b\b\u0002\u00107\u001a\u000206H&ø\u0001\u0000¢\u0006\u0004\b8\u00109J\u000f\u0010;\u001a\u00020\u0005H&¢\u0006\u0004\b;\u0010<J\u000f\u0010=\u001a\u00020\u0005H&¢\u0006\u0004\b=\u0010<J\u000f\u0010>\u001a\u00020\u0005H\u0016¢\u0006\u0004\b>\u0010<J\u001a\u0010A\u001a\u00020\u00052\u0006\u00107\u001a\u000206H&ø\u0001\u0000¢\u0006\u0004\b?\u0010@J\u001a\u0010F\u001a\u00020\u00052\u0006\u0010C\u001a\u00020BH\u0016ø\u0001\u0000¢\u0006\u0004\bD\u0010EJ\u000f\u0010G\u001a\u00020\u001fH&¢\u0006\u0004\bG\u0010HJ*\u0010O\u001a\u00020#2\u0006\u0010I\u001a\u00020\u00002\u0006\u0010J\u001a\u00020\u00002\u0006\u0010L\u001a\u00020KH&ø\u0001\u0000¢\u0006\u0004\bM\u0010NR\"\u0010U\u001a\u00020P8&@&X¦\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\f\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010TR\u0014\u0010V\u001a\u00020#8&X¦\u0004¢\u0006\u0006\u001a\u0004\bV\u0010WR\u0014\u0010X\u001a\u00020#8&X¦\u0004¢\u0006\u0006\u001a\u0004\bX\u0010Wø\u0001\u0002\u0082\u0002\u0011\n\u0005\b¡\u001e0\u0001\n\u0002\b!\n\u0004\b!0\u0001¨\u0006ZÀ\u0006\u0003"}, d2 = {"Landroidx/compose/ui/graphics/Path;", "", "", "x", "y", "Lx6/t0;", "moveTo", "(FF)V", "dx", "dy", "relativeMoveTo", "lineTo", "relativeLineTo", "x1", "y1", "x2", "y2", "quadraticBezierTo", "(FFFF)V", "dx1", "dy1", "dx2", "dy2", "relativeQuadraticBezierTo", "x3", "y3", "cubicTo", "(FFFFFF)V", "dx3", "dy3", "relativeCubicTo", "Landroidx/compose/ui/geometry/Rect;", "rect", "startAngleRadians", "sweepAngleRadians", "", "forceMoveTo", "arcToRad", "(Landroidx/compose/ui/geometry/Rect;FFZ)V", "startAngleDegrees", "sweepAngleDegrees", "arcTo", "addRect", "(Landroidx/compose/ui/geometry/Rect;)V", "oval", "addOval", "addArcRad", "(Landroidx/compose/ui/geometry/Rect;FF)V", "addArc", "Landroidx/compose/ui/geometry/RoundRect;", "roundRect", "addRoundRect", "(Landroidx/compose/ui/geometry/RoundRect;)V", "path", "Landroidx/compose/ui/geometry/Offset;", "offset", "addPath-Uv8p0NA", "(Landroidx/compose/ui/graphics/Path;J)V", "addPath", "close", "()V", "reset", "rewind", "translate-k-4lQ0M", "(J)V", "translate", "Landroidx/compose/ui/graphics/Matrix;", "matrix", "transform-58bKbWc", "([F)V", "transform", "getBounds", "()Landroidx/compose/ui/geometry/Rect;", "path1", "path2", "Landroidx/compose/ui/graphics/PathOperation;", "operation", "op-N5in7k0", "(Landroidx/compose/ui/graphics/Path;Landroidx/compose/ui/graphics/Path;I)Z", "op", "Landroidx/compose/ui/graphics/PathFillType;", "getFillType-Rg-k1Os", "()I", "setFillType-oQ8Xj4U", "(I)V", "fillType", "isConvex", "()Z", "isEmpty", "Companion", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface Path {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/graphics/Path$Companion;", "", "()V", "combine", "Landroidx/compose/ui/graphics/Path;", "operation", "Landroidx/compose/ui/graphics/PathOperation;", "path1", "path2", "combine-xh6zSI8", "(ILandroidx/compose/ui/graphics/Path;Landroidx/compose/ui/graphics/Path;)Landroidx/compose/ui/graphics/Path;", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        /* JADX INFO: renamed from: combine-xh6zSI8, reason: not valid java name */
        public final Path m3722combinexh6zSI8(int operation, Path path1, Path path2) {
            Path Path = AndroidPath_androidKt.Path();
            if (Path.mo3370opN5in7k0(path1, path2, operation)) {
                return Path;
            }
            throw new IllegalArgumentException("Path.combine() failed.  This may be due an invalid path; in particular, check for NaN values.");
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static void arcToRad(Path path, Rect rect, float f10, float f11, boolean z) {
            h.a(path, rect, f10, f11, z);
        }

        @Deprecated
        public static void rewind(Path path) {
            h.b(path);
        }

        @Deprecated
        /* JADX INFO: renamed from: transform-58bKbWc, reason: not valid java name */
        public static void m3724transform58bKbWc(Path path, float[] fArr) {
            h.c(path, fArr);
        }
    }

    void addArc(Rect oval, float startAngleDegrees, float sweepAngleDegrees);

    void addArcRad(Rect oval, float startAngleRadians, float sweepAngleRadians);

    void addOval(Rect oval);

    /* JADX INFO: renamed from: addPath-Uv8p0NA */
    void mo3368addPathUv8p0NA(Path path, long offset);

    void addRect(Rect rect);

    void addRoundRect(RoundRect roundRect);

    void arcTo(Rect rect, float startAngleDegrees, float sweepAngleDegrees, boolean forceMoveTo);

    void arcToRad(Rect rect, float startAngleRadians, float sweepAngleRadians, boolean forceMoveTo);

    void close();

    void cubicTo(float x12, float y12, float x22, float y22, float x32, float y32);

    Rect getBounds();

    /* JADX INFO: renamed from: getFillType-Rg-k1Os */
    int mo3369getFillTypeRgk1Os();

    boolean isConvex();

    boolean isEmpty();

    void lineTo(float x, float y10);

    void moveTo(float x, float y10);

    /* JADX INFO: renamed from: op-N5in7k0 */
    boolean mo3370opN5in7k0(Path path1, Path path2, int operation);

    void quadraticBezierTo(float x12, float y12, float x22, float y22);

    void relativeCubicTo(float dx1, float dy1, float dx2, float dy2, float dx3, float dy3);

    void relativeLineTo(float dx, float dy);

    void relativeMoveTo(float dx, float dy);

    void relativeQuadraticBezierTo(float dx1, float dy1, float dx2, float dy2);

    void reset();

    void rewind();

    /* JADX INFO: renamed from: setFillType-oQ8Xj4U */
    void mo3371setFillTypeoQ8Xj4U(int i10);

    /* JADX INFO: renamed from: transform-58bKbWc */
    void mo3372transform58bKbWc(float[] matrix);

    /* JADX INFO: renamed from: translate-k-4lQ0M */
    void mo3373translatek4lQ0M(long offset);
}
