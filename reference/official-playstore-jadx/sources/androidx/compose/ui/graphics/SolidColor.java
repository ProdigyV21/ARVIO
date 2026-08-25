package androidx.compose.ui.graphics;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J*\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0016ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u001a\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0096\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u0019\u0010\u001aR\u001d\u0010\u0003\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0003\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001e"}, d2 = {"Landroidx/compose/ui/graphics/SolidColor;", "Landroidx/compose/ui/graphics/Brush;", "Landroidx/compose/ui/graphics/Color;", "value", "<init>", "(JLkotlin/jvm/internal/h;)V", "Landroidx/compose/ui/geometry/Size;", ContentDisposition.Parameters.Size, "Landroidx/compose/ui/graphics/Paint;", TtmlNode.TAG_P, "", "alpha", "Lx6/t0;", "applyTo-Pq9zytI", "(JLandroidx/compose/ui/graphics/Paint;F)V", "applyTo", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "J", "getValue-0d7_KjU", "()J", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SolidColor extends Brush {
    private final long value;

    public /* synthetic */ SolidColor(long j10, kotlin.jvm.internal.h hVar) {
        this(j10);
    }

    @Override // androidx.compose.ui.graphics.Brush
    /* JADX INFO: renamed from: applyTo-Pq9zytI */
    public void mo3425applyToPq9zytI(long size, Paint p10, float alpha) {
        long jM3471copywmQWz5c$default;
        p10.setAlpha(1.0f);
        if (alpha == 1.0f) {
            jM3471copywmQWz5c$default = this.value;
        } else {
            long j10 = this.value;
            jM3471copywmQWz5c$default = Color.m3471copywmQWz5c$default(j10, Color.m3474getAlphaimpl(j10) * alpha, 0.0f, 0.0f, 0.0f, 14, null);
        }
        p10.mo3357setColor8_81llA(jM3471copywmQWz5c$default);
        if (p10.getShader() != null) {
            p10.setShader(null);
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof SolidColor) && Color.m3473equalsimpl0(this.value, ((SolidColor) other).value);
    }

    /* JADX INFO: renamed from: getValue-0d7_KjU, reason: not valid java name and from getter */
    public final long getValue() {
        return this.value;
    }

    public int hashCode() {
        return Color.m3479hashCodeimpl(this.value);
    }

    public String toString() {
        return "SolidColor(value=" + ((Object) Color.m3480toStringimpl(this.value)) + ')';
    }

    private SolidColor(long j10) {
        super(null);
        this.value = j10;
    }
}
