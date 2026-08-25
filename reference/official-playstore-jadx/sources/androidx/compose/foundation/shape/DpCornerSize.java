package androidx.compose.foundation.shape;

import androidx.compose.ui.platform.InspectableValue;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import ga.m;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0082\b\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0016\u0010\t\u001a\u00020\u0003HÂ\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0007\u0010\bJ\"\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0016ø\u0001\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u001d\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001ø\u0001\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0019\u001a\u00020\u0018HÖ\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u001a\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001bHÖ\u0003¢\u0006\u0004\b\u001e\u0010\u001fR\u001a\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\n\u0004\b\u0004\u0010 R\u001a\u0010\"\u001a\u00020\u00038VX\u0096\u0004ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\u001a\u0004\b!\u0010\b\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006#"}, d2 = {"Landroidx/compose/foundation/shape/DpCornerSize;", "Landroidx/compose/foundation/shape/CornerSize;", "Landroidx/compose/ui/platform/InspectableValue;", "Landroidx/compose/ui/unit/Dp;", ContentDisposition.Parameters.Size, "<init>", "(FLkotlin/jvm/internal/h;)V", "component1-D9Ej5fM", "()F", "component1", "Landroidx/compose/ui/geometry/Size;", "shapeSize", "Landroidx/compose/ui/unit/Density;", "density", "", "toPx-TmRCtEA", "(JLandroidx/compose/ui/unit/Density;)F", "toPx", "", "toString", "()Ljava/lang/String;", "copy-0680j_4", "(F)Landroidx/compose/foundation/shape/DpCornerSize;", "copy", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "F", "getValueOverride-D9Ej5fM", "valueOverride", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class DpCornerSize implements CornerSize, InspectableValue {
    private final float size;

    public /* synthetic */ DpCornerSize(float f10, h hVar) {
        this(f10);
    }

    /* JADX INFO: renamed from: component1-D9Ej5fM, reason: not valid java name and from getter */
    private final float getSize() {
        return this.size;
    }

    /* JADX INFO: renamed from: copy-0680j_4$default, reason: not valid java name */
    public static /* synthetic */ DpCornerSize m795copy0680j_4$default(DpCornerSize dpCornerSize, float f10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            f10 = dpCornerSize.size;
        }
        return dpCornerSize.m796copy0680j_4(f10);
    }

    /* JADX INFO: renamed from: copy-0680j_4, reason: not valid java name */
    public final DpCornerSize m796copy0680j_4(float size) {
        return new DpCornerSize(size, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof DpCornerSize) && Dp.m5683equalsimpl0(this.size, ((DpCornerSize) other).size);
    }

    @Override // androidx.compose.ui.platform.InspectableValue
    public final /* synthetic */ m getInspectableElements() {
        return androidx.compose.ui.platform.m.a(this);
    }

    @Override // androidx.compose.ui.platform.InspectableValue
    public final /* synthetic */ String getNameFallback() {
        return androidx.compose.ui.platform.m.b(this);
    }

    @Override // androidx.compose.ui.platform.InspectableValue
    public /* bridge */ /* synthetic */ Object getValueOverride() {
        return Dp.m5676boximpl(m797getValueOverrideD9Ej5fM());
    }

    /* JADX INFO: renamed from: getValueOverride-D9Ej5fM, reason: not valid java name */
    public float m797getValueOverrideD9Ej5fM() {
        return this.size;
    }

    public int hashCode() {
        return Dp.m5684hashCodeimpl(this.size);
    }

    @Override // androidx.compose.foundation.shape.CornerSize
    /* JADX INFO: renamed from: toPx-TmRCtEA */
    public float mo789toPxTmRCtEA(long shapeSize, Density density) {
        return density.mo285toPx0680j_4(this.size);
    }

    public String toString() {
        return "CornerSize(size = " + this.size + ".dp)";
    }

    private DpCornerSize(float f10) {
        this.size = f10;
    }
}
