package androidx.compose.ui.graphics;

import android.graphics.Shader;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001BC\b\u0000\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u001e\u0010\u0014\u001a\u00060\u0010j\u0002`\u00112\u0006\u0010\u000f\u001a\u00020\u000eH\u0016ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001a\u0010\u0018\u001a\u00020\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0096\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001b\u001a\u00020\u001aH\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001e\u001a\u00020\u001dH\u0016¢\u0006\u0004\b\u001e\u0010\u001fR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010 R\u001c\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010 R\u001a\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\b\u0010!R\u001a\u0010\t\u001a\u00020\u00078\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\t\u0010!R\u001a\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u000b\u0010\"R\u001a\u0010%\u001a\u00020\u000e8VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b#\u0010$\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006&"}, d2 = {"Landroidx/compose/ui/graphics/LinearGradient;", "Landroidx/compose/ui/graphics/ShaderBrush;", "", "Landroidx/compose/ui/graphics/Color;", "colors", "", "stops", "Landroidx/compose/ui/geometry/Offset;", TtmlNode.START, TtmlNode.END, "Landroidx/compose/ui/graphics/TileMode;", "tileMode", "<init>", "(Ljava/util/List;Ljava/util/List;JJILkotlin/jvm/internal/h;)V", "Landroidx/compose/ui/geometry/Size;", ContentDisposition.Parameters.Size, "Landroid/graphics/Shader;", "Landroidx/compose/ui/graphics/Shader;", "createShader-uvyYCjk", "(J)Landroid/graphics/Shader;", "createShader", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Ljava/util/List;", "J", "I", "getIntrinsicSize-NH-jbRc", "()J", "intrinsicSize", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LinearGradient extends ShaderBrush {
    private final List<Color> colors;
    private final long end;
    private final long start;
    private final List<Float> stops;
    private final int tileMode;

    public /* synthetic */ LinearGradient(List list, List list2, long j10, long j11, int i10, kotlin.jvm.internal.h hVar) {
        this(list, list2, j10, j11, i10);
    }

    @Override // androidx.compose.ui.graphics.ShaderBrush
    /* JADX INFO: renamed from: createShader-uvyYCjk */
    public Shader mo3447createShaderuvyYCjk(long size) {
        return ShaderKt.m3765LinearGradientShaderVjE6UOU(OffsetKt.Offset(Offset.m3236getXimpl(this.start) == Float.POSITIVE_INFINITY ? Size.m3305getWidthimpl(size) : Offset.m3236getXimpl(this.start), Offset.m3237getYimpl(this.start) == Float.POSITIVE_INFINITY ? Size.m3302getHeightimpl(size) : Offset.m3237getYimpl(this.start)), OffsetKt.Offset(Offset.m3236getXimpl(this.end) == Float.POSITIVE_INFINITY ? Size.m3305getWidthimpl(size) : Offset.m3236getXimpl(this.end), Offset.m3237getYimpl(this.end) == Float.POSITIVE_INFINITY ? Size.m3302getHeightimpl(size) : Offset.m3237getYimpl(this.end)), this.colors, this.stops, this.tileMode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LinearGradient)) {
            return false;
        }
        LinearGradient linearGradient = (LinearGradient) other;
        return p.a(this.colors, linearGradient.colors) && p.a(this.stops, linearGradient.stops) && Offset.m3233equalsimpl0(this.start, linearGradient.start) && Offset.m3233equalsimpl0(this.end, linearGradient.end) && TileMode.m3819equalsimpl0(this.tileMode, linearGradient.tileMode);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0038  */
    @Override // androidx.compose.ui.graphics.Brush
    /* JADX INFO: renamed from: getIntrinsicSize-NH-jbRc */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long getIntrinsicSize() {
        /*
            r5 = this;
            long r0 = r5.start
            float r0 = androidx.compose.ui.geometry.Offset.m3236getXimpl(r0)
            boolean r1 = java.lang.Float.isInfinite(r0)
            r2 = 2143289344(0x7fc00000, float:NaN)
            if (r1 != 0) goto L38
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 != 0) goto L38
            long r0 = r5.end
            float r0 = androidx.compose.ui.geometry.Offset.m3236getXimpl(r0)
            boolean r1 = java.lang.Float.isInfinite(r0)
            if (r1 != 0) goto L38
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 != 0) goto L38
            long r0 = r5.start
            float r0 = androidx.compose.ui.geometry.Offset.m3236getXimpl(r0)
            long r3 = r5.end
            float r1 = androidx.compose.ui.geometry.Offset.m3236getXimpl(r3)
            float r0 = r0 - r1
            float r0 = java.lang.Math.abs(r0)
            goto L39
        L38:
            r0 = r2
        L39:
            long r3 = r5.start
            float r1 = androidx.compose.ui.geometry.Offset.m3237getYimpl(r3)
            boolean r3 = java.lang.Float.isInfinite(r1)
            if (r3 != 0) goto L6e
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L6e
            long r3 = r5.end
            float r1 = androidx.compose.ui.geometry.Offset.m3237getYimpl(r3)
            boolean r3 = java.lang.Float.isInfinite(r1)
            if (r3 != 0) goto L6e
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L6e
            long r1 = r5.start
            float r1 = androidx.compose.ui.geometry.Offset.m3237getYimpl(r1)
            long r2 = r5.end
            float r2 = androidx.compose.ui.geometry.Offset.m3237getYimpl(r2)
            float r1 = r1 - r2
            float r2 = java.lang.Math.abs(r1)
        L6e:
            long r0 = androidx.compose.ui.geometry.SizeKt.Size(r0, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.LinearGradient.getIntrinsicSize():long");
    }

    public int hashCode() {
        int iHashCode = this.colors.hashCode() * 31;
        List<Float> list = this.stops;
        return TileMode.m3820hashCodeimpl(this.tileMode) + ((Offset.m3238hashCodeimpl(this.end) + ((Offset.m3238hashCodeimpl(this.start) + ((iHashCode + (list != null ? list.hashCode() : 0)) * 31)) * 31)) * 31);
    }

    public String toString() {
        String str;
        String str2 = "";
        if (OffsetKt.m3253isFinitek4lQ0M(this.start)) {
            str = "start=" + ((Object) Offset.m3244toStringimpl(this.start)) + ", ";
        } else {
            str = "";
        }
        if (OffsetKt.m3253isFinitek4lQ0M(this.end)) {
            str2 = "end=" + ((Object) Offset.m3244toStringimpl(this.end)) + ", ";
        }
        return "LinearGradient(colors=" + this.colors + ", stops=" + this.stops + ", " + str + str2 + "tileMode=" + ((Object) TileMode.m3821toStringimpl(this.tileMode)) + ')';
    }

    public /* synthetic */ LinearGradient(List list, List list2, long j10, long j11, int i10, int i11, kotlin.jvm.internal.h hVar) {
        this(list, (i11 & 2) != 0 ? null : list2, j10, j11, (i11 & 16) != 0 ? TileMode.INSTANCE.m3823getClamp3opZhB0() : i10, null);
    }

    private LinearGradient(List<Color> list, List<Float> list2, long j10, long j11, int i10) {
        this.colors = list;
        this.stops = list2;
        this.start = j10;
        this.end = j11;
        this.tileMode = i10;
    }
}
