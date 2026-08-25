package androidx.compose.ui.platform;

import android.graphics.Outline;
import android.os.Build;
import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.CornerRadiusKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRect;
import androidx.compose.ui.geometry.RoundRectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.AndroidPath;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J0\u0010\u001e\u001a\u00020\u001b*\u0004\u0018\u00010\r2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0019H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ=\u0010&\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020\u00192\u0006\u0010%\u001a\u00020$2\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b&\u0010'J\u0018\u0010+\u001a\u00020\u001b2\u0006\u0010(\u001a\u00020\u0015ø\u0001\u0000¢\u0006\u0004\b)\u0010*J\u0015\u0010\"\u001a\u00020\u00062\u0006\u0010-\u001a\u00020,¢\u0006\u0004\b\"\u0010.J\u0018\u0010&\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0017ø\u0001\u0000¢\u0006\u0004\b/\u00100R\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0003\u00101R\u0016\u00102\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b2\u00103R\u0014\u00105\u001a\u0002048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u00106R\u001c\u0010\u0018\u001a\u00020\u00178\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u0018\u00107R\u0016\u0010 \u001a\u00020\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b \u00108R\u0018\u00109\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b9\u0010:R\u0018\u0010;\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b;\u0010:R$\u0010=\u001a\u00020\u001b2\u0006\u0010<\u001a\u00020\u001b8\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\b=\u00103\u001a\u0004\b>\u0010?R\u0016\u0010@\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b@\u00103R\u0018\u0010A\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bA\u0010:R\u0018\u0010B\u001a\u0004\u0018\u00010\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bB\u0010CR\u0016\u0010D\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bD\u0010ER\u001c\u0010F\u001a\u00020\u00158\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\bF\u00107R\u001c\u0010G\u001a\u00020\u00178\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\bG\u00107R\u0016\u0010H\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bH\u00103R\u0016\u0010%\u001a\u00020$8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b%\u0010IR\u0018\u0010J\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bJ\u0010:R\u0018\u0010K\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bK\u0010:R\u0018\u0010M\u001a\u0004\u0018\u00010L8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bM\u0010NR\u0013\u0010Q\u001a\u0004\u0018\u0001048F¢\u0006\u0006\u001a\u0004\bO\u0010PR\u0011\u0010S\u001a\u00020\u001b8F¢\u0006\u0006\u001a\u0004\bR\u0010?R\u0013\u0010V\u001a\u0004\u0018\u00010\u00118F¢\u0006\u0006\u001a\u0004\bT\u0010U\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006W"}, d2 = {"Landroidx/compose/ui/platform/OutlineResolver;", "", "Landroidx/compose/ui/unit/Density;", "density", "<init>", "(Landroidx/compose/ui/unit/Density;)V", "Lx6/t0;", "updateCache", "()V", "Landroidx/compose/ui/geometry/Rect;", "rect", "updateCacheWithRect", "(Landroidx/compose/ui/geometry/Rect;)V", "Landroidx/compose/ui/geometry/RoundRect;", "roundRect", "updateCacheWithRoundRect", "(Landroidx/compose/ui/geometry/RoundRect;)V", "Landroidx/compose/ui/graphics/Path;", "composePath", "updateCacheWithPath", "(Landroidx/compose/ui/graphics/Path;)V", "Landroidx/compose/ui/geometry/Offset;", "offset", "Landroidx/compose/ui/geometry/Size;", ContentDisposition.Parameters.Size, "", "radius", "", "isSameBounds-4L21HEs", "(Landroidx/compose/ui/geometry/RoundRect;JJF)Z", "isSameBounds", "Landroidx/compose/ui/graphics/Shape;", "shape", "alpha", "clipToOutline", "elevation", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "update", "(Landroidx/compose/ui/graphics/Shape;FZFLandroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/ui/unit/Density;)Z", "position", "isInOutline-k-4lQ0M", "(J)Z", "isInOutline", "Landroidx/compose/ui/graphics/Canvas;", "canvas", "(Landroidx/compose/ui/graphics/Canvas;)V", "update-uvyYCjk", "(J)V", "Landroidx/compose/ui/unit/Density;", "isSupportedOutline", "Z", "Landroid/graphics/Outline;", "cachedOutline", "Landroid/graphics/Outline;", "J", "Landroidx/compose/ui/graphics/Shape;", "cachedRrectPath", "Landroidx/compose/ui/graphics/Path;", "outlinePath", "<set-?>", "cacheIsDirty", "getCacheIsDirty$ui_release", "()Z", "usePathForClip", "tmpPath", "tmpRoundRect", "Landroidx/compose/ui/geometry/RoundRect;", "roundedCornerRadius", "F", "rectTopLeft", "rectSize", "outlineNeeded", "Landroidx/compose/ui/unit/LayoutDirection;", "tmpTouchPointPath", "tmpOpPath", "Landroidx/compose/ui/graphics/Outline;", "calculatedOutline", "Landroidx/compose/ui/graphics/Outline;", "getOutline", "()Landroid/graphics/Outline;", "outline", "getOutlineClipSupported", "outlineClipSupported", "getClipPath", "()Landroidx/compose/ui/graphics/Path;", "clipPath", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class OutlineResolver {
    public static final int $stable = 8;
    private boolean cacheIsDirty;
    private final Outline cachedOutline;
    private Path cachedRrectPath;
    private androidx.compose.ui.graphics.Outline calculatedOutline;
    private Density density;
    private boolean isSupportedOutline = true;
    private LayoutDirection layoutDirection;
    private boolean outlineNeeded;
    private Path outlinePath;
    private long rectSize;
    private long rectTopLeft;
    private float roundedCornerRadius;
    private Shape shape;
    private long size;
    private Path tmpOpPath;
    private Path tmpPath;
    private RoundRect tmpRoundRect;
    private Path tmpTouchPointPath;
    private boolean usePathForClip;

    public OutlineResolver(Density density) {
        this.density = density;
        Outline outline = new Outline();
        outline.setAlpha(1.0f);
        this.cachedOutline = outline;
        Size.Companion companion = Size.INSTANCE;
        this.size = companion.m3314getZeroNHjbRc();
        this.shape = RectangleShapeKt.getRectangleShape();
        this.rectTopLeft = Offset.INSTANCE.m3252getZeroF1C5BW0();
        this.rectSize = companion.m3314getZeroNHjbRc();
        this.layoutDirection = LayoutDirection.Ltr;
    }

    /* JADX INFO: renamed from: isSameBounds-4L21HEs, reason: not valid java name */
    private final boolean m4993isSameBounds4L21HEs(RoundRect roundRect, long j10, long j11, float f10) {
        if (roundRect != null && RoundRectKt.isSimple(roundRect) && roundRect.getLeft() == Offset.m3236getXimpl(j10) && roundRect.getTop() == Offset.m3237getYimpl(j10)) {
            if (roundRect.getRight() == Size.m3305getWidthimpl(j11) + Offset.m3236getXimpl(j10)) {
                if (roundRect.getBottom() == Size.m3302getHeightimpl(j11) + Offset.m3237getYimpl(j10) && CornerRadius.m3211getXimpl(roundRect.m3286getTopLeftCornerRadiuskKHJgLs()) == f10) {
                    return true;
                }
            }
        }
        return false;
    }

    private final void updateCache() {
        if (this.cacheIsDirty) {
            this.rectTopLeft = Offset.INSTANCE.m3252getZeroF1C5BW0();
            long j10 = this.size;
            this.rectSize = j10;
            this.roundedCornerRadius = 0.0f;
            this.outlinePath = null;
            this.cacheIsDirty = false;
            this.usePathForClip = false;
            if (!this.outlineNeeded || Size.m3305getWidthimpl(j10) <= 0.0f || Size.m3302getHeightimpl(this.size) <= 0.0f) {
                this.cachedOutline.setEmpty();
                return;
            }
            this.isSupportedOutline = true;
            androidx.compose.ui.graphics.Outline outlineMo230createOutlinePq9zytI = this.shape.mo230createOutlinePq9zytI(this.size, this.layoutDirection, this.density);
            this.calculatedOutline = outlineMo230createOutlinePq9zytI;
            if (outlineMo230createOutlinePq9zytI instanceof Outline.Rectangle) {
                updateCacheWithRect(((Outline.Rectangle) outlineMo230createOutlinePq9zytI).getRect());
            } else if (outlineMo230createOutlinePq9zytI instanceof Outline.Rounded) {
                updateCacheWithRoundRect(((Outline.Rounded) outlineMo230createOutlinePq9zytI).getRoundRect());
            } else if (outlineMo230createOutlinePq9zytI instanceof Outline.Generic) {
                updateCacheWithPath(((Outline.Generic) outlineMo230createOutlinePq9zytI).getPath());
            }
        }
    }

    private final void updateCacheWithPath(Path composePath) {
        if (Build.VERSION.SDK_INT > 28 || composePath.isConvex()) {
            android.graphics.Outline outline = this.cachedOutline;
            if (!(composePath instanceof AndroidPath)) {
                throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
            }
            outline.setConvexPath(((AndroidPath) composePath).getInternalPath());
            this.usePathForClip = !this.cachedOutline.canClip();
        } else {
            this.isSupportedOutline = false;
            this.cachedOutline.setEmpty();
            this.usePathForClip = true;
        }
        this.outlinePath = composePath;
    }

    private final void updateCacheWithRect(Rect rect) {
        this.rectTopLeft = OffsetKt.Offset(rect.getLeft(), rect.getTop());
        this.rectSize = SizeKt.Size(rect.getWidth(), rect.getHeight());
        this.cachedOutline.setRect(t7.a.M(rect.getLeft()), t7.a.M(rect.getTop()), t7.a.M(rect.getRight()), t7.a.M(rect.getBottom()));
    }

    private final void updateCacheWithRoundRect(RoundRect roundRect) {
        float fM3211getXimpl = CornerRadius.m3211getXimpl(roundRect.m3286getTopLeftCornerRadiuskKHJgLs());
        this.rectTopLeft = OffsetKt.Offset(roundRect.getLeft(), roundRect.getTop());
        this.rectSize = SizeKt.Size(roundRect.getWidth(), roundRect.getHeight());
        if (RoundRectKt.isSimple(roundRect)) {
            this.cachedOutline.setRoundRect(t7.a.M(roundRect.getLeft()), t7.a.M(roundRect.getTop()), t7.a.M(roundRect.getRight()), t7.a.M(roundRect.getBottom()), fM3211getXimpl);
            this.roundedCornerRadius = fM3211getXimpl;
            return;
        }
        Path Path = this.cachedRrectPath;
        if (Path == null) {
            Path = AndroidPath_androidKt.Path();
            this.cachedRrectPath = Path;
        }
        Path.reset();
        Path.addRoundRect(roundRect);
        updateCacheWithPath(Path);
    }

    public final void clipToOutline(Canvas canvas) {
        OutlineResolver outlineResolver;
        Path clipPath = getClipPath();
        if (clipPath != null) {
            androidx.compose.ui.graphics.b.m(canvas, clipPath, 0, 2, null);
            return;
        }
        float f10 = this.roundedCornerRadius;
        if (f10 <= 0.0f) {
            androidx.compose.ui.graphics.b.n(canvas, Offset.m3236getXimpl(this.rectTopLeft), Offset.m3237getYimpl(this.rectTopLeft), Size.m3305getWidthimpl(this.rectSize) + Offset.m3236getXimpl(this.rectTopLeft), Size.m3302getHeightimpl(this.rectSize) + Offset.m3237getYimpl(this.rectTopLeft), 0, 16, null);
            return;
        }
        Path Path = this.tmpPath;
        RoundRect roundRect = this.tmpRoundRect;
        if (Path != null) {
            outlineResolver = this;
            if (!m4993isSameBounds4L21HEs(roundRect, this.rectTopLeft, this.rectSize, f10)) {
            }
            androidx.compose.ui.graphics.b.m(canvas, Path, 0, 2, null);
        }
        outlineResolver = this;
        RoundRect roundRectM3290RoundRectgG7oq9Y = RoundRectKt.m3290RoundRectgG7oq9Y(Offset.m3236getXimpl(outlineResolver.rectTopLeft), Offset.m3237getYimpl(outlineResolver.rectTopLeft), Size.m3305getWidthimpl(outlineResolver.rectSize) + Offset.m3236getXimpl(outlineResolver.rectTopLeft), Size.m3302getHeightimpl(outlineResolver.rectSize) + Offset.m3237getYimpl(outlineResolver.rectTopLeft), CornerRadiusKt.CornerRadius$default(outlineResolver.roundedCornerRadius, 0.0f, 2, null));
        if (Path == null) {
            Path = AndroidPath_androidKt.Path();
        } else {
            Path.reset();
        }
        Path.addRoundRect(roundRectM3290RoundRectgG7oq9Y);
        outlineResolver.tmpRoundRect = roundRectM3290RoundRectgG7oq9Y;
        outlineResolver.tmpPath = Path;
        androidx.compose.ui.graphics.b.m(canvas, Path, 0, 2, null);
    }

    /* JADX INFO: renamed from: getCacheIsDirty$ui_release, reason: from getter */
    public final boolean getCacheIsDirty() {
        return this.cacheIsDirty;
    }

    public final Path getClipPath() {
        updateCache();
        return this.outlinePath;
    }

    public final android.graphics.Outline getOutline() {
        updateCache();
        if (this.outlineNeeded && this.isSupportedOutline) {
            return this.cachedOutline;
        }
        return null;
    }

    public final boolean getOutlineClipSupported() {
        return !this.usePathForClip;
    }

    /* JADX INFO: renamed from: isInOutline-k-4lQ0M, reason: not valid java name */
    public final boolean m4994isInOutlinek4lQ0M(long position) {
        androidx.compose.ui.graphics.Outline outline;
        if (this.outlineNeeded && (outline = this.calculatedOutline) != null) {
            return ShapeContainingUtilKt.isInOutline(outline, Offset.m3236getXimpl(position), Offset.m3237getYimpl(position), this.tmpTouchPointPath, this.tmpOpPath);
        }
        return true;
    }

    public final boolean update(Shape shape, float alpha, boolean clipToOutline, float elevation, LayoutDirection layoutDirection, Density density) {
        this.cachedOutline.setAlpha(alpha);
        boolean zA = kotlin.jvm.internal.p.a(this.shape, shape);
        boolean z = !zA;
        if (!zA) {
            this.shape = shape;
            this.cacheIsDirty = true;
        }
        boolean z5 = clipToOutline || elevation > 0.0f;
        if (this.outlineNeeded != z5) {
            this.outlineNeeded = z5;
            this.cacheIsDirty = true;
        }
        if (this.layoutDirection != layoutDirection) {
            this.layoutDirection = layoutDirection;
            this.cacheIsDirty = true;
        }
        if (!kotlin.jvm.internal.p.a(this.density, density)) {
            this.density = density;
            this.cacheIsDirty = true;
        }
        return z;
    }

    /* JADX INFO: renamed from: update-uvyYCjk, reason: not valid java name */
    public final void m4995updateuvyYCjk(long size) {
        if (Size.m3301equalsimpl0(this.size, size)) {
            return;
        }
        this.size = size;
        this.cacheIsDirty = true;
    }
}
