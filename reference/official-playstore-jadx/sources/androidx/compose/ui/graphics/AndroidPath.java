package androidx.compose.ui.graphics;

import android.graphics.Path;
import android.graphics.RectF;
import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRect;
import androidx.compose.ui.graphics.PathOperation;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001f\u0010\n\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000e\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000e\u0010\u000bJ\u001f\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000f\u0010\u000bJ\u001f\u0010\u0010\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0010\u0010\u000bJ/\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J/\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u001b\u0010\u0016J?\u0010\u001e\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ?\u0010\"\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u00062\u0006\u0010!\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\"\u0010\u001fJ/\u0010)\u001a\u00020\t2\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u00062\u0006\u0010(\u001a\u00020'H\u0016¢\u0006\u0004\b)\u0010*J\u0017\u0010+\u001a\u00020\t2\u0006\u0010$\u001a\u00020#H\u0016¢\u0006\u0004\b+\u0010,J\u0017\u0010.\u001a\u00020\t2\u0006\u0010-\u001a\u00020#H\u0016¢\u0006\u0004\b.\u0010,J'\u00101\u001a\u00020\t2\u0006\u0010-\u001a\u00020#2\u0006\u0010/\u001a\u00020\u00062\u0006\u00100\u001a\u00020\u0006H\u0016¢\u0006\u0004\b1\u00102J'\u00103\u001a\u00020\t2\u0006\u0010-\u001a\u00020#2\u0006\u0010%\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u0006H\u0016¢\u0006\u0004\b3\u00102J\u0017\u00106\u001a\u00020\t2\u0006\u00105\u001a\u000204H\u0016¢\u0006\u0004\b6\u00107J\"\u0010=\u001a\u00020\t2\u0006\u00108\u001a\u00020\u00012\u0006\u0010:\u001a\u000209H\u0016ø\u0001\u0000¢\u0006\u0004\b;\u0010<J\u000f\u0010>\u001a\u00020\tH\u0016¢\u0006\u0004\b>\u0010?J\u000f\u0010@\u001a\u00020\tH\u0016¢\u0006\u0004\b@\u0010?J\u000f\u0010A\u001a\u00020\tH\u0016¢\u0006\u0004\bA\u0010?J\u001a\u0010D\u001a\u00020\t2\u0006\u0010:\u001a\u000209H\u0016ø\u0001\u0000¢\u0006\u0004\bB\u0010CJ\u001a\u0010I\u001a\u00020\t2\u0006\u0010F\u001a\u00020EH\u0016ø\u0001\u0000¢\u0006\u0004\bG\u0010HJ\u000f\u0010J\u001a\u00020#H\u0016¢\u0006\u0004\bJ\u0010KJ*\u0010R\u001a\u00020'2\u0006\u0010L\u001a\u00020\u00012\u0006\u0010M\u001a\u00020\u00012\u0006\u0010O\u001a\u00020NH\u0016ø\u0001\u0000¢\u0006\u0004\bP\u0010QJ\u0017\u0010S\u001a\u00020'2\u0006\u0010$\u001a\u00020#H\u0002¢\u0006\u0004\bS\u0010TR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010U\u001a\u0004\bV\u0010WR\u0018\u0010Y\u001a\u0004\u0018\u00010X8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bY\u0010ZR\u0018\u0010\\\u001a\u0004\u0018\u00010[8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\\\u0010]R\u0018\u0010_\u001a\u0004\u0018\u00010^8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b_\u0010`R*\u0010g\u001a\u00020a2\u0006\u0010b\u001a\u00020a8V@VX\u0096\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\f\u001a\u0004\bc\u0010d\"\u0004\be\u0010fR\u001a\u0010h\u001a\u00020'8VX\u0096\u0004¢\u0006\f\u0012\u0004\bj\u0010?\u001a\u0004\bh\u0010iR\u0014\u0010k\u001a\u00020'8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bk\u0010i\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006l"}, d2 = {"Landroidx/compose/ui/graphics/AndroidPath;", "Landroidx/compose/ui/graphics/Path;", "Landroid/graphics/Path;", "internalPath", "<init>", "(Landroid/graphics/Path;)V", "", "x", "y", "Lx6/t0;", "moveTo", "(FF)V", "dx", "dy", "relativeMoveTo", "lineTo", "relativeLineTo", "x1", "y1", "x2", "y2", "quadraticBezierTo", "(FFFF)V", "dx1", "dy1", "dx2", "dy2", "relativeQuadraticBezierTo", "x3", "y3", "cubicTo", "(FFFFFF)V", "dx3", "dy3", "relativeCubicTo", "Landroidx/compose/ui/geometry/Rect;", "rect", "startAngleDegrees", "sweepAngleDegrees", "", "forceMoveTo", "arcTo", "(Landroidx/compose/ui/geometry/Rect;FFZ)V", "addRect", "(Landroidx/compose/ui/geometry/Rect;)V", "oval", "addOval", "startAngleRadians", "sweepAngleRadians", "addArcRad", "(Landroidx/compose/ui/geometry/Rect;FF)V", "addArc", "Landroidx/compose/ui/geometry/RoundRect;", "roundRect", "addRoundRect", "(Landroidx/compose/ui/geometry/RoundRect;)V", "path", "Landroidx/compose/ui/geometry/Offset;", "offset", "addPath-Uv8p0NA", "(Landroidx/compose/ui/graphics/Path;J)V", "addPath", "close", "()V", "reset", "rewind", "translate-k-4lQ0M", "(J)V", "translate", "Landroidx/compose/ui/graphics/Matrix;", "matrix", "transform-58bKbWc", "([F)V", "transform", "getBounds", "()Landroidx/compose/ui/geometry/Rect;", "path1", "path2", "Landroidx/compose/ui/graphics/PathOperation;", "operation", "op-N5in7k0", "(Landroidx/compose/ui/graphics/Path;Landroidx/compose/ui/graphics/Path;I)Z", "op", "_rectIsValid", "(Landroidx/compose/ui/geometry/Rect;)Z", "Landroid/graphics/Path;", "getInternalPath", "()Landroid/graphics/Path;", "Landroid/graphics/RectF;", "rectF", "Landroid/graphics/RectF;", "", "radii", "[F", "Landroid/graphics/Matrix;", "mMatrix", "Landroid/graphics/Matrix;", "Landroidx/compose/ui/graphics/PathFillType;", "value", "getFillType-Rg-k1Os", "()I", "setFillType-oQ8Xj4U", "(I)V", "fillType", "isConvex", "()Z", "isConvex$annotations", "isEmpty", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AndroidPath implements Path {
    private final android.graphics.Path internalPath;
    private android.graphics.Matrix mMatrix;
    private float[] radii;
    private RectF rectF;

    /* JADX WARN: Multi-variable type inference failed */
    public AndroidPath() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    private final boolean _rectIsValid(Rect rect) {
        if (Float.isNaN(rect.getLeft())) {
            throw new IllegalStateException("Rect.left is NaN");
        }
        if (Float.isNaN(rect.getTop())) {
            throw new IllegalStateException("Rect.top is NaN");
        }
        if (Float.isNaN(rect.getRight())) {
            throw new IllegalStateException("Rect.right is NaN");
        }
        if (Float.isNaN(rect.getBottom())) {
            throw new IllegalStateException("Rect.bottom is NaN");
        }
        return true;
    }

    public static /* synthetic */ void isConvex$annotations() {
    }

    @Override // androidx.compose.ui.graphics.Path
    public void addArc(Rect oval, float startAngleDegrees, float sweepAngleDegrees) {
        if (!_rectIsValid(oval)) {
            throw new IllegalStateException("invalid rect");
        }
        if (this.rectF == null) {
            this.rectF = new RectF();
        }
        this.rectF.set(oval.getLeft(), oval.getTop(), oval.getRight(), oval.getBottom());
        this.internalPath.addArc(this.rectF, startAngleDegrees, sweepAngleDegrees);
    }

    @Override // androidx.compose.ui.graphics.Path
    public void addArcRad(Rect oval, float startAngleRadians, float sweepAngleRadians) {
        addArc(oval, DegreesKt.degrees(startAngleRadians), DegreesKt.degrees(sweepAngleRadians));
    }

    @Override // androidx.compose.ui.graphics.Path
    public void addOval(Rect oval) {
        if (this.rectF == null) {
            this.rectF = new RectF();
        }
        this.rectF.set(oval.getLeft(), oval.getTop(), oval.getRight(), oval.getBottom());
        this.internalPath.addOval(this.rectF, Path.Direction.CCW);
    }

    @Override // androidx.compose.ui.graphics.Path
    /* JADX INFO: renamed from: addPath-Uv8p0NA, reason: not valid java name */
    public void mo3368addPathUv8p0NA(Path path, long offset) {
        android.graphics.Path path2 = this.internalPath;
        if (!(path instanceof AndroidPath)) {
            throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
        }
        path2.addPath(((AndroidPath) path).getInternalPath(), Offset.m3236getXimpl(offset), Offset.m3237getYimpl(offset));
    }

    @Override // androidx.compose.ui.graphics.Path
    public void addRect(Rect rect) {
        if (!_rectIsValid(rect)) {
            throw new IllegalStateException("invalid rect");
        }
        if (this.rectF == null) {
            this.rectF = new RectF();
        }
        this.rectF.set(rect.getLeft(), rect.getTop(), rect.getRight(), rect.getBottom());
        this.internalPath.addRect(this.rectF, Path.Direction.CCW);
    }

    @Override // androidx.compose.ui.graphics.Path
    public void addRoundRect(RoundRect roundRect) {
        if (this.rectF == null) {
            this.rectF = new RectF();
        }
        this.rectF.set(roundRect.getLeft(), roundRect.getTop(), roundRect.getRight(), roundRect.getBottom());
        if (this.radii == null) {
            this.radii = new float[8];
        }
        float[] fArr = this.radii;
        fArr[0] = CornerRadius.m3211getXimpl(roundRect.m3286getTopLeftCornerRadiuskKHJgLs());
        fArr[1] = CornerRadius.m3212getYimpl(roundRect.m3286getTopLeftCornerRadiuskKHJgLs());
        fArr[2] = CornerRadius.m3211getXimpl(roundRect.m3287getTopRightCornerRadiuskKHJgLs());
        fArr[3] = CornerRadius.m3212getYimpl(roundRect.m3287getTopRightCornerRadiuskKHJgLs());
        fArr[4] = CornerRadius.m3211getXimpl(roundRect.m3285getBottomRightCornerRadiuskKHJgLs());
        fArr[5] = CornerRadius.m3212getYimpl(roundRect.m3285getBottomRightCornerRadiuskKHJgLs());
        fArr[6] = CornerRadius.m3211getXimpl(roundRect.m3284getBottomLeftCornerRadiuskKHJgLs());
        fArr[7] = CornerRadius.m3212getYimpl(roundRect.m3284getBottomLeftCornerRadiuskKHJgLs());
        this.internalPath.addRoundRect(this.rectF, this.radii, Path.Direction.CCW);
    }

    @Override // androidx.compose.ui.graphics.Path
    public void arcTo(Rect rect, float startAngleDegrees, float sweepAngleDegrees, boolean forceMoveTo) {
        float left = rect.getLeft();
        float top = rect.getTop();
        float right = rect.getRight();
        float bottom = rect.getBottom();
        if (this.rectF == null) {
            this.rectF = new RectF();
        }
        this.rectF.set(left, top, right, bottom);
        this.internalPath.arcTo(this.rectF, startAngleDegrees, sweepAngleDegrees, forceMoveTo);
    }

    @Override // androidx.compose.ui.graphics.Path
    public final /* synthetic */ void arcToRad(Rect rect, float f10, float f11, boolean z) {
        h.a(this, rect, f10, f11, z);
    }

    @Override // androidx.compose.ui.graphics.Path
    public void close() {
        this.internalPath.close();
    }

    @Override // androidx.compose.ui.graphics.Path
    public void cubicTo(float x12, float y12, float x22, float y22, float x32, float y32) {
        this.internalPath.cubicTo(x12, y12, x22, y22, x32, y32);
    }

    @Override // androidx.compose.ui.graphics.Path
    public Rect getBounds() {
        if (this.rectF == null) {
            this.rectF = new RectF();
        }
        RectF rectF = this.rectF;
        this.internalPath.computeBounds(rectF, true);
        return new Rect(rectF.left, rectF.top, rectF.right, rectF.bottom);
    }

    @Override // androidx.compose.ui.graphics.Path
    /* JADX INFO: renamed from: getFillType-Rg-k1Os, reason: not valid java name */
    public int mo3369getFillTypeRgk1Os() {
        return this.internalPath.getFillType() == Path.FillType.EVEN_ODD ? PathFillType.INSTANCE.m3733getEvenOddRgk1Os() : PathFillType.INSTANCE.m3734getNonZeroRgk1Os();
    }

    public final android.graphics.Path getInternalPath() {
        return this.internalPath;
    }

    @Override // androidx.compose.ui.graphics.Path
    public boolean isConvex() {
        return this.internalPath.isConvex();
    }

    @Override // androidx.compose.ui.graphics.Path
    public boolean isEmpty() {
        return this.internalPath.isEmpty();
    }

    @Override // androidx.compose.ui.graphics.Path
    public void lineTo(float x, float y10) {
        this.internalPath.lineTo(x, y10);
    }

    @Override // androidx.compose.ui.graphics.Path
    public void moveTo(float x, float y10) {
        this.internalPath.moveTo(x, y10);
    }

    @Override // androidx.compose.ui.graphics.Path
    /* JADX INFO: renamed from: op-N5in7k0, reason: not valid java name */
    public boolean mo3370opN5in7k0(Path path1, Path path2, int operation) {
        PathOperation.Companion companion = PathOperation.INSTANCE;
        Path.Op op = PathOperation.m3738equalsimpl0(operation, companion.m3742getDifferenceb3I0S0c()) ? Path.Op.DIFFERENCE : PathOperation.m3738equalsimpl0(operation, companion.m3743getIntersectb3I0S0c()) ? Path.Op.INTERSECT : PathOperation.m3738equalsimpl0(operation, companion.m3744getReverseDifferenceb3I0S0c()) ? Path.Op.REVERSE_DIFFERENCE : PathOperation.m3738equalsimpl0(operation, companion.m3745getUnionb3I0S0c()) ? Path.Op.UNION : Path.Op.XOR;
        android.graphics.Path path = this.internalPath;
        if (!(path1 instanceof AndroidPath)) {
            throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
        }
        android.graphics.Path internalPath = ((AndroidPath) path1).getInternalPath();
        if (path2 instanceof AndroidPath) {
            return path.op(internalPath, ((AndroidPath) path2).getInternalPath(), op);
        }
        throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
    }

    @Override // androidx.compose.ui.graphics.Path
    public void quadraticBezierTo(float x12, float y12, float x22, float y22) {
        this.internalPath.quadTo(x12, y12, x22, y22);
    }

    @Override // androidx.compose.ui.graphics.Path
    public void relativeCubicTo(float dx1, float dy1, float dx2, float dy2, float dx3, float dy3) {
        this.internalPath.rCubicTo(dx1, dy1, dx2, dy2, dx3, dy3);
    }

    @Override // androidx.compose.ui.graphics.Path
    public void relativeLineTo(float dx, float dy) {
        this.internalPath.rLineTo(dx, dy);
    }

    @Override // androidx.compose.ui.graphics.Path
    public void relativeMoveTo(float dx, float dy) {
        this.internalPath.rMoveTo(dx, dy);
    }

    @Override // androidx.compose.ui.graphics.Path
    public void relativeQuadraticBezierTo(float dx1, float dy1, float dx2, float dy2) {
        this.internalPath.rQuadTo(dx1, dy1, dx2, dy2);
    }

    @Override // androidx.compose.ui.graphics.Path
    public void reset() {
        this.internalPath.reset();
    }

    @Override // androidx.compose.ui.graphics.Path
    public void rewind() {
        this.internalPath.rewind();
    }

    @Override // androidx.compose.ui.graphics.Path
    /* JADX INFO: renamed from: setFillType-oQ8Xj4U, reason: not valid java name */
    public void mo3371setFillTypeoQ8Xj4U(int i10) {
        this.internalPath.setFillType(PathFillType.m3729equalsimpl0(i10, PathFillType.INSTANCE.m3733getEvenOddRgk1Os()) ? Path.FillType.EVEN_ODD : Path.FillType.WINDING);
    }

    @Override // androidx.compose.ui.graphics.Path
    /* JADX INFO: renamed from: transform-58bKbWc, reason: not valid java name */
    public void mo3372transform58bKbWc(float[] matrix) {
        if (this.mMatrix == null) {
            this.mMatrix = new android.graphics.Matrix();
        }
        AndroidMatrixConversions_androidKt.m3348setFromEL8BTi8(this.mMatrix, matrix);
        this.internalPath.transform(this.mMatrix);
    }

    @Override // androidx.compose.ui.graphics.Path
    /* JADX INFO: renamed from: translate-k-4lQ0M, reason: not valid java name */
    public void mo3373translatek4lQ0M(long offset) {
        android.graphics.Matrix matrix = this.mMatrix;
        if (matrix == null) {
            this.mMatrix = new android.graphics.Matrix();
        } else {
            matrix.reset();
        }
        this.mMatrix.setTranslate(Offset.m3236getXimpl(offset), Offset.m3237getYimpl(offset));
        this.internalPath.transform(this.mMatrix);
    }

    public AndroidPath(android.graphics.Path path) {
        this.internalPath = path;
    }

    public /* synthetic */ AndroidPath(android.graphics.Path path, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? new android.graphics.Path() : path);
    }
}
