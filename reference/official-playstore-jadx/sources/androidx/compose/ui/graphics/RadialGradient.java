package androidx.compose.ui.graphics;

import android.graphics.Shader;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001BC\b\u0000\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u001e\u0010\u0014\u001a\u00060\u0010j\u0002`\u00112\u0006\u0010\u000f\u001a\u00020\u000eH\u0016ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001a\u0010\u0018\u001a\u00020\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0096\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001b\u001a\u00020\u001aH\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001e\u001a\u00020\u001dH\u0016¢\u0006\u0004\b\u001e\u0010\u001fR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010 R\u001c\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010 R\u001a\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\b\u0010!R\u0014\u0010\t\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\"R\u001a\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u000b\u0010#R\u001a\u0010&\u001a\u00020\u000e8VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b$\u0010%\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006'"}, d2 = {"Landroidx/compose/ui/graphics/RadialGradient;", "Landroidx/compose/ui/graphics/ShaderBrush;", "", "Landroidx/compose/ui/graphics/Color;", "colors", "", "stops", "Landroidx/compose/ui/geometry/Offset;", TtmlNode.CENTER, "radius", "Landroidx/compose/ui/graphics/TileMode;", "tileMode", "<init>", "(Ljava/util/List;Ljava/util/List;JFILkotlin/jvm/internal/h;)V", "Landroidx/compose/ui/geometry/Size;", ContentDisposition.Parameters.Size, "Landroid/graphics/Shader;", "Landroidx/compose/ui/graphics/Shader;", "createShader-uvyYCjk", "(J)Landroid/graphics/Shader;", "createShader", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Ljava/util/List;", "J", "F", "I", "getIntrinsicSize-NH-jbRc", "()J", "intrinsicSize", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class RadialGradient extends ShaderBrush {
    private final long center;
    private final List<Color> colors;
    private final float radius;
    private final List<Float> stops;
    private final int tileMode;

    public /* synthetic */ RadialGradient(List list, List list2, long j10, float f10, int i10, kotlin.jvm.internal.h hVar) {
        this(list, list2, j10, f10, i10);
    }

    @Override // androidx.compose.ui.graphics.ShaderBrush
    /* JADX INFO: renamed from: createShader-uvyYCjk */
    public Shader mo3447createShaderuvyYCjk(long size) {
        float fM3305getWidthimpl;
        float fM3302getHeightimpl;
        if (OffsetKt.m3257isUnspecifiedk4lQ0M(this.center)) {
            long jM3315getCenteruvyYCjk = SizeKt.m3315getCenteruvyYCjk(size);
            fM3305getWidthimpl = Offset.m3236getXimpl(jM3315getCenteruvyYCjk);
            fM3302getHeightimpl = Offset.m3237getYimpl(jM3315getCenteruvyYCjk);
        } else {
            fM3305getWidthimpl = Offset.m3236getXimpl(this.center) == Float.POSITIVE_INFINITY ? Size.m3305getWidthimpl(size) : Offset.m3236getXimpl(this.center);
            fM3302getHeightimpl = Offset.m3237getYimpl(this.center) == Float.POSITIVE_INFINITY ? Size.m3302getHeightimpl(size) : Offset.m3237getYimpl(this.center);
        }
        List<Color> list = this.colors;
        List<Float> list2 = this.stops;
        long jOffset = OffsetKt.Offset(fM3305getWidthimpl, fM3302getHeightimpl);
        float fM3304getMinDimensionimpl = this.radius;
        if (fM3304getMinDimensionimpl == Float.POSITIVE_INFINITY) {
            fM3304getMinDimensionimpl = Size.m3304getMinDimensionimpl(size) / 2;
        }
        return ShaderKt.m3767RadialGradientShader8uybcMk(jOffset, fM3304getMinDimensionimpl, list, list2, this.tileMode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RadialGradient)) {
            return false;
        }
        RadialGradient radialGradient = (RadialGradient) other;
        return p.a(this.colors, radialGradient.colors) && p.a(this.stops, radialGradient.stops) && Offset.m3233equalsimpl0(this.center, radialGradient.center) && this.radius == radialGradient.radius && TileMode.m3819equalsimpl0(this.tileMode, radialGradient.tileMode);
    }

    @Override // androidx.compose.ui.graphics.Brush
    /* JADX INFO: renamed from: getIntrinsicSize-NH-jbRc */
    public long getIntrinsicSize() {
        float f10 = this.radius;
        if (Float.isInfinite(f10) || Float.isNaN(f10)) {
            return Size.INSTANCE.m3313getUnspecifiedNHjbRc();
        }
        float f11 = this.radius;
        float f12 = 2;
        return SizeKt.Size(f11 * f12, f11 * f12);
    }

    public int hashCode() {
        int iHashCode = this.colors.hashCode() * 31;
        List<Float> list = this.stops;
        return TileMode.m3820hashCodeimpl(this.tileMode) + a0.c.b(this.radius, (Offset.m3238hashCodeimpl(this.center) + ((iHashCode + (list != null ? list.hashCode() : 0)) * 31)) * 31, 31);
    }

    public String toString() {
        String str;
        String str2 = "";
        if (OffsetKt.m3255isSpecifiedk4lQ0M(this.center)) {
            str = "center=" + ((Object) Offset.m3244toStringimpl(this.center)) + ", ";
        } else {
            str = "";
        }
        float f10 = this.radius;
        if (!Float.isInfinite(f10) && !Float.isNaN(f10)) {
            str2 = "radius=" + this.radius + ", ";
        }
        return "RadialGradient(colors=" + this.colors + ", stops=" + this.stops + ", " + str + str2 + "tileMode=" + ((Object) TileMode.m3821toStringimpl(this.tileMode)) + ')';
    }

    public /* synthetic */ RadialGradient(List list, List list2, long j10, float f10, int i10, int i11, kotlin.jvm.internal.h hVar) {
        this(list, (i11 & 2) != 0 ? null : list2, j10, f10, (i11 & 16) != 0 ? TileMode.INSTANCE.m3823getClamp3opZhB0() : i10, null);
    }

    private RadialGradient(List<Color> list, List<Float> list2, long j10, float f10, int i10) {
        this.colors = list;
        this.stops = list2;
        this.center = j10;
        this.radius = f10;
        this.tileMode = i10;
    }
}
