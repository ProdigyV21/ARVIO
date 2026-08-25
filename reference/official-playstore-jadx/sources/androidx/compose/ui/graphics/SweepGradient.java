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
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B1\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0004¢\u0006\u0004\b\t\u0010\nJ\u001e\u0010\u0011\u001a\u00060\rj\u0002`\u000e2\u0006\u0010\f\u001a\u00020\u000bH\u0016ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0096\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001b\u001a\u00020\u001aH\u0016¢\u0006\u0004\b\u001b\u0010\u001cR\u001a\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u0003\u0010\u001dR\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u001eR\u001c\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\u001e\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001f"}, d2 = {"Landroidx/compose/ui/graphics/SweepGradient;", "Landroidx/compose/ui/graphics/ShaderBrush;", "Landroidx/compose/ui/geometry/Offset;", TtmlNode.CENTER, "", "Landroidx/compose/ui/graphics/Color;", "colors", "", "stops", "<init>", "(JLjava/util/List;Ljava/util/List;Lkotlin/jvm/internal/h;)V", "Landroidx/compose/ui/geometry/Size;", ContentDisposition.Parameters.Size, "Landroid/graphics/Shader;", "Landroidx/compose/ui/graphics/Shader;", "createShader-uvyYCjk", "(J)Landroid/graphics/Shader;", "createShader", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "J", "Ljava/util/List;", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SweepGradient extends ShaderBrush {
    private final long center;
    private final List<Color> colors;
    private final List<Float> stops;

    public /* synthetic */ SweepGradient(long j10, List list, List list2, kotlin.jvm.internal.h hVar) {
        this(j10, list, list2);
    }

    @Override // androidx.compose.ui.graphics.ShaderBrush
    /* JADX INFO: renamed from: createShader-uvyYCjk */
    public Shader mo3447createShaderuvyYCjk(long size) {
        long jOffset;
        if (OffsetKt.m3257isUnspecifiedk4lQ0M(this.center)) {
            jOffset = SizeKt.m3315getCenteruvyYCjk(size);
        } else {
            jOffset = OffsetKt.Offset(Offset.m3236getXimpl(this.center) == Float.POSITIVE_INFINITY ? Size.m3305getWidthimpl(size) : Offset.m3236getXimpl(this.center), Offset.m3237getYimpl(this.center) == Float.POSITIVE_INFINITY ? Size.m3302getHeightimpl(size) : Offset.m3237getYimpl(this.center));
        }
        return ShaderKt.m3769SweepGradientShader9KIMszo(jOffset, this.colors, this.stops);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SweepGradient)) {
            return false;
        }
        SweepGradient sweepGradient = (SweepGradient) other;
        return Offset.m3233equalsimpl0(this.center, sweepGradient.center) && p.a(this.colors, sweepGradient.colors) && p.a(this.stops, sweepGradient.stops);
    }

    public int hashCode() {
        int iE = androidx.compose.material3.d.e(this.colors, Offset.m3238hashCodeimpl(this.center) * 31, 31);
        List<Float> list = this.stops;
        return iE + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        String str;
        if (OffsetKt.m3255isSpecifiedk4lQ0M(this.center)) {
            str = "center=" + ((Object) Offset.m3244toStringimpl(this.center)) + ", ";
        } else {
            str = "";
        }
        StringBuilder sbT = a0.c.t("SweepGradient(", str, "colors=");
        sbT.append(this.colors);
        sbT.append(", stops=");
        sbT.append(this.stops);
        sbT.append(')');
        return sbT.toString();
    }

    public /* synthetic */ SweepGradient(long j10, List list, List list2, int i10, kotlin.jvm.internal.h hVar) {
        this(j10, list, (i10 & 4) != 0 ? null : list2, null);
    }

    private SweepGradient(long j10, List<Color> list, List<Float> list2) {
        this.center = j10;
        this.colors = list;
        this.stops = list2;
    }
}
