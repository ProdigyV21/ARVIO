package androidx.compose.ui.geometry;

import a0.c;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import q7.n;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u001f\b\u0087\b\u0018\u0000 F2\u00020\u0001:\u0001FBO\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0007¢\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0015\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u0018J\u0010\u0010\u001a\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u0018J\u0010\u0010\u001b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u0018J\u0016\u0010\u001e\u001a\u00020\u0007HÆ\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u0016\u0010 \u001a\u00020\u0007HÆ\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010\u001dJ\u0016\u0010\"\u001a\u00020\u0007HÆ\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b!\u0010\u001dJ\u0016\u0010$\u001a\u00020\u0007HÆ\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b#\u0010\u001dJc\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u0007HÆ\u0001ø\u0001\u0000¢\u0006\u0004\b%\u0010&J\u0010\u0010)\u001a\u00020(HÖ\u0001¢\u0006\u0004\b)\u0010*J\u001a\u0010,\u001a\u00020\u00102\b\u0010+\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b,\u0010-J\u000f\u0010.\u001a\u00020\u0000H\u0002¢\u0006\u0004\b.\u0010/J/\u00104\u001a\u00020\u00022\u0006\u00100\u001a\u00020\u00022\u0006\u00101\u001a\u00020\u00022\u0006\u00102\u001a\u00020\u00022\u0006\u00103\u001a\u00020\u0002H\u0002¢\u0006\u0004\b4\u00105R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u00106\u001a\u0004\b7\u0010\u0018R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u00106\u001a\u0004\b8\u0010\u0018R\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u00106\u001a\u0004\b9\u0010\u0018R\u0017\u0010\u0006\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u00106\u001a\u0004\b:\u0010\u0018R\u001d\u0010\b\u001a\u00020\u00078\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\b\u0010;\u001a\u0004\b<\u0010\u001dR\u001d\u0010\t\u001a\u00020\u00078\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\t\u0010;\u001a\u0004\b=\u0010\u001dR\u001d\u0010\n\u001a\u00020\u00078\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\n\u0010;\u001a\u0004\b>\u0010\u001dR\u001d\u0010\u000b\u001a\u00020\u00078\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u000b\u0010;\u001a\u0004\b?\u0010\u001dR\u0018\u0010@\u001a\u0004\u0018\u00010\u00008\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b@\u0010AR\u0011\u0010C\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\bB\u0010\u0018R\u0011\u0010E\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\bD\u0010\u0018\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006G"}, d2 = {"Landroidx/compose/ui/geometry/RoundRect;", "", "", TtmlNode.LEFT, "top", TtmlNode.RIGHT, "bottom", "Landroidx/compose/ui/geometry/CornerRadius;", "topLeftCornerRadius", "topRightCornerRadius", "bottomRightCornerRadius", "bottomLeftCornerRadius", "<init>", "(FFFFJJJJLkotlin/jvm/internal/h;)V", "Landroidx/compose/ui/geometry/Offset;", "point", "", "contains-k-4lQ0M", "(J)Z", "contains", "", "toString", "()Ljava/lang/String;", "component1", "()F", "component2", "component3", "component4", "component5-kKHJgLs", "()J", "component5", "component6-kKHJgLs", "component6", "component7-kKHJgLs", "component7", "component8-kKHJgLs", "component8", "copy-MDFrsts", "(FFFFJJJJ)Landroidx/compose/ui/geometry/RoundRect;", "copy", "", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "scaledRadiiRect", "()Landroidx/compose/ui/geometry/RoundRect;", "min", "radius1", "radius2", "limit", "minRadius", "(FFFF)F", "F", "getLeft", "getTop", "getRight", "getBottom", "J", "getTopLeftCornerRadius-kKHJgLs", "getTopRightCornerRadius-kKHJgLs", "getBottomRightCornerRadius-kKHJgLs", "getBottomLeftCornerRadius-kKHJgLs", "_scaledRadiiRect", "Landroidx/compose/ui/geometry/RoundRect;", "getWidth", "width", "getHeight", "height", "Companion", "ui-geometry_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class RoundRect {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final RoundRect Zero = RoundRectKt.m3290RoundRectgG7oq9Y(0.0f, 0.0f, 0.0f, 0.0f, CornerRadius.INSTANCE.m3221getZerokKHJgLs());
    private RoundRect _scaledRadiiRect;
    private final float bottom;
    private final long bottomLeftCornerRadius;
    private final long bottomRightCornerRadius;
    private final float left;
    private final float right;
    private final float top;
    private final long topLeftCornerRadius;
    private final long topRightCornerRadius;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/ui/geometry/RoundRect$Companion;", "", "()V", "Zero", "Landroidx/compose/ui/geometry/RoundRect;", "getZero$annotations", "getZero", "()Landroidx/compose/ui/geometry/RoundRect;", "ui-geometry_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        @n
        public static /* synthetic */ void getZero$annotations() {
        }

        public final RoundRect getZero() {
            return RoundRect.Zero;
        }

        private Companion() {
        }
    }

    public /* synthetic */ RoundRect(float f10, float f11, float f12, float f13, long j10, long j11, long j12, long j13, h hVar) {
        this(f10, f11, f12, f13, j10, j11, j12, j13);
    }

    /* JADX INFO: renamed from: copy-MDFrsts$default, reason: not valid java name */
    public static /* synthetic */ RoundRect m3277copyMDFrsts$default(RoundRect roundRect, float f10, float f11, float f12, float f13, long j10, long j11, long j12, long j13, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            f10 = roundRect.left;
        }
        if ((i10 & 2) != 0) {
            f11 = roundRect.top;
        }
        if ((i10 & 4) != 0) {
            f12 = roundRect.right;
        }
        if ((i10 & 8) != 0) {
            f13 = roundRect.bottom;
        }
        if ((i10 & 16) != 0) {
            j10 = roundRect.topLeftCornerRadius;
        }
        if ((i10 & 32) != 0) {
            j11 = roundRect.topRightCornerRadius;
        }
        if ((i10 & 64) != 0) {
            j12 = roundRect.bottomRightCornerRadius;
        }
        if ((i10 & 128) != 0) {
            j13 = roundRect.bottomLeftCornerRadius;
        }
        long j14 = j13;
        long j15 = j12;
        long j16 = j11;
        long j17 = j10;
        return roundRect.m3283copyMDFrsts(f10, f11, f12, f13, j17, j16, j15, j14);
    }

    public static final RoundRect getZero() {
        return INSTANCE.getZero();
    }

    private final float minRadius(float min, float radius1, float radius2, float limit) {
        float f10 = radius1 + radius2;
        return (f10 <= limit || f10 == 0.0f) ? min : Math.min(min, limit / f10);
    }

    private final RoundRect scaledRadiiRect() {
        RoundRect roundRect = this._scaledRadiiRect;
        if (roundRect != null) {
            return roundRect;
        }
        float fMinRadius = minRadius(minRadius(minRadius(minRadius(1.0f, CornerRadius.m3212getYimpl(this.bottomLeftCornerRadius), CornerRadius.m3212getYimpl(this.topLeftCornerRadius), getHeight()), CornerRadius.m3211getXimpl(this.topLeftCornerRadius), CornerRadius.m3211getXimpl(this.topRightCornerRadius), getWidth()), CornerRadius.m3212getYimpl(this.topRightCornerRadius), CornerRadius.m3212getYimpl(this.bottomRightCornerRadius), getHeight()), CornerRadius.m3211getXimpl(this.bottomRightCornerRadius), CornerRadius.m3211getXimpl(this.bottomLeftCornerRadius), getWidth());
        RoundRect roundRect2 = new RoundRect(this.left * fMinRadius, this.top * fMinRadius, this.right * fMinRadius, this.bottom * fMinRadius, CornerRadiusKt.CornerRadius(CornerRadius.m3211getXimpl(this.topLeftCornerRadius) * fMinRadius, CornerRadius.m3212getYimpl(this.topLeftCornerRadius) * fMinRadius), CornerRadiusKt.CornerRadius(CornerRadius.m3211getXimpl(this.topRightCornerRadius) * fMinRadius, CornerRadius.m3212getYimpl(this.topRightCornerRadius) * fMinRadius), CornerRadiusKt.CornerRadius(CornerRadius.m3211getXimpl(this.bottomRightCornerRadius) * fMinRadius, CornerRadius.m3212getYimpl(this.bottomRightCornerRadius) * fMinRadius), CornerRadiusKt.CornerRadius(CornerRadius.m3211getXimpl(this.bottomLeftCornerRadius) * fMinRadius, CornerRadius.m3212getYimpl(this.bottomLeftCornerRadius) * fMinRadius), null);
        this._scaledRadiiRect = roundRect2;
        return roundRect2;
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final float getLeft() {
        return this.left;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final float getTop() {
        return this.top;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final float getRight() {
        return this.right;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final float getBottom() {
        return this.bottom;
    }

    /* JADX INFO: renamed from: component5-kKHJgLs, reason: not valid java name and from getter */
    public final long getTopLeftCornerRadius() {
        return this.topLeftCornerRadius;
    }

    /* JADX INFO: renamed from: component6-kKHJgLs, reason: not valid java name and from getter */
    public final long getTopRightCornerRadius() {
        return this.topRightCornerRadius;
    }

    /* JADX INFO: renamed from: component7-kKHJgLs, reason: not valid java name and from getter */
    public final long getBottomRightCornerRadius() {
        return this.bottomRightCornerRadius;
    }

    /* JADX INFO: renamed from: component8-kKHJgLs, reason: not valid java name and from getter */
    public final long getBottomLeftCornerRadius() {
        return this.bottomLeftCornerRadius;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00c8  */
    /* JADX INFO: renamed from: contains-k-4lQ0M, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean m3282containsk4lQ0M(long r8) {
        /*
            Method dump skipped, instruction units count: 364
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.geometry.RoundRect.m3282containsk4lQ0M(long):boolean");
    }

    /* JADX INFO: renamed from: copy-MDFrsts, reason: not valid java name */
    public final RoundRect m3283copyMDFrsts(float left, float top, float right, float bottom, long topLeftCornerRadius, long topRightCornerRadius, long bottomRightCornerRadius, long bottomLeftCornerRadius) {
        return new RoundRect(left, top, right, bottom, topLeftCornerRadius, topRightCornerRadius, bottomRightCornerRadius, bottomLeftCornerRadius, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RoundRect)) {
            return false;
        }
        RoundRect roundRect = (RoundRect) other;
        return Float.compare(this.left, roundRect.left) == 0 && Float.compare(this.top, roundRect.top) == 0 && Float.compare(this.right, roundRect.right) == 0 && Float.compare(this.bottom, roundRect.bottom) == 0 && CornerRadius.m3210equalsimpl0(this.topLeftCornerRadius, roundRect.topLeftCornerRadius) && CornerRadius.m3210equalsimpl0(this.topRightCornerRadius, roundRect.topRightCornerRadius) && CornerRadius.m3210equalsimpl0(this.bottomRightCornerRadius, roundRect.bottomRightCornerRadius) && CornerRadius.m3210equalsimpl0(this.bottomLeftCornerRadius, roundRect.bottomLeftCornerRadius);
    }

    public final float getBottom() {
        return this.bottom;
    }

    /* JADX INFO: renamed from: getBottomLeftCornerRadius-kKHJgLs, reason: not valid java name */
    public final long m3284getBottomLeftCornerRadiuskKHJgLs() {
        return this.bottomLeftCornerRadius;
    }

    /* JADX INFO: renamed from: getBottomRightCornerRadius-kKHJgLs, reason: not valid java name */
    public final long m3285getBottomRightCornerRadiuskKHJgLs() {
        return this.bottomRightCornerRadius;
    }

    public final float getHeight() {
        return this.bottom - this.top;
    }

    public final float getLeft() {
        return this.left;
    }

    public final float getRight() {
        return this.right;
    }

    public final float getTop() {
        return this.top;
    }

    /* JADX INFO: renamed from: getTopLeftCornerRadius-kKHJgLs, reason: not valid java name */
    public final long m3286getTopLeftCornerRadiuskKHJgLs() {
        return this.topLeftCornerRadius;
    }

    /* JADX INFO: renamed from: getTopRightCornerRadius-kKHJgLs, reason: not valid java name */
    public final long m3287getTopRightCornerRadiuskKHJgLs() {
        return this.topRightCornerRadius;
    }

    public final float getWidth() {
        return this.right - this.left;
    }

    public int hashCode() {
        return CornerRadius.m3213hashCodeimpl(this.bottomLeftCornerRadius) + ((CornerRadius.m3213hashCodeimpl(this.bottomRightCornerRadius) + ((CornerRadius.m3213hashCodeimpl(this.topRightCornerRadius) + ((CornerRadius.m3213hashCodeimpl(this.topLeftCornerRadius) + c.b(this.bottom, c.b(this.right, c.b(this.top, Float.floatToIntBits(this.left) * 31, 31), 31), 31)) * 31)) * 31)) * 31);
    }

    public String toString() {
        long j10 = this.topLeftCornerRadius;
        long j11 = this.topRightCornerRadius;
        long j12 = this.bottomRightCornerRadius;
        long j13 = this.bottomLeftCornerRadius;
        String str = GeometryUtilsKt.toStringAsFixed(this.left, 1) + ", " + GeometryUtilsKt.toStringAsFixed(this.top, 1) + ", " + GeometryUtilsKt.toStringAsFixed(this.right, 1) + ", " + GeometryUtilsKt.toStringAsFixed(this.bottom, 1);
        if (!CornerRadius.m3210equalsimpl0(j10, j11) || !CornerRadius.m3210equalsimpl0(j11, j12) || !CornerRadius.m3210equalsimpl0(j12, j13)) {
            StringBuilder sbT = c.t("RoundRect(rect=", str, ", topLeft=");
            sbT.append((Object) CornerRadius.m3217toStringimpl(j10));
            sbT.append(", topRight=");
            sbT.append((Object) CornerRadius.m3217toStringimpl(j11));
            sbT.append(", bottomRight=");
            sbT.append((Object) CornerRadius.m3217toStringimpl(j12));
            sbT.append(", bottomLeft=");
            sbT.append((Object) CornerRadius.m3217toStringimpl(j13));
            sbT.append(')');
            return sbT.toString();
        }
        if (CornerRadius.m3211getXimpl(j10) == CornerRadius.m3212getYimpl(j10)) {
            StringBuilder sbT2 = c.t("RoundRect(rect=", str, ", radius=");
            sbT2.append(GeometryUtilsKt.toStringAsFixed(CornerRadius.m3211getXimpl(j10), 1));
            sbT2.append(')');
            return sbT2.toString();
        }
        StringBuilder sbT3 = c.t("RoundRect(rect=", str, ", x=");
        sbT3.append(GeometryUtilsKt.toStringAsFixed(CornerRadius.m3211getXimpl(j10), 1));
        sbT3.append(", y=");
        sbT3.append(GeometryUtilsKt.toStringAsFixed(CornerRadius.m3212getYimpl(j10), 1));
        sbT3.append(')');
        return sbT3.toString();
    }

    private RoundRect(float f10, float f11, float f12, float f13, long j10, long j11, long j12, long j13) {
        this.left = f10;
        this.top = f11;
        this.right = f12;
        this.bottom = f13;
        this.topLeftCornerRadius = j10;
        this.topRightCornerRadius = j11;
        this.bottomRightCornerRadius = j12;
        this.bottomLeftCornerRadius = j13;
    }

    public /* synthetic */ RoundRect(float f10, float f11, float f12, float f13, long j10, long j11, long j12, long j13, int i10, h hVar) {
        this(f10, f11, f12, f13, (i10 & 16) != 0 ? CornerRadius.INSTANCE.m3221getZerokKHJgLs() : j10, (i10 & 32) != 0 ? CornerRadius.INSTANCE.m3221getZerokKHJgLs() : j11, (i10 & 64) != 0 ? CornerRadius.INSTANCE.m3221getZerokKHJgLs() : j12, (i10 & 128) != 0 ? CornerRadius.INSTANCE.m3221getZerokKHJgLs() : j13, null);
    }
}
