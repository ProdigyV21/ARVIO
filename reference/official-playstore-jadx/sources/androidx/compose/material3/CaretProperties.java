package androidx.compose.material3;

import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0016\u0010\t\u001a\u00020\u0002HÆ\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0016\u0010\u000b\u001a\u00020\u0002HÆ\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\n\u0010\bJ'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u0002HÆ\u0001ø\u0001\u0001¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u0010\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0013\u001a\u00020\u0012HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u001a\u0010\u0017\u001a\u00020\u00162\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0017\u0010\u0018R\u001d\u0010\u0003\u001a\u00020\u00028\u0006ø\u0001\u0001ø\u0001\u0000¢\u0006\f\n\u0004\b\u0003\u0010\u0019\u001a\u0004\b\u001a\u0010\bR\u001d\u0010\u0004\u001a\u00020\u00028\u0006ø\u0001\u0001ø\u0001\u0000¢\u0006\f\n\u0004\b\u0004\u0010\u0019\u001a\u0004\b\u001b\u0010\b\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\u001c"}, d2 = {"Landroidx/compose/material3/CaretProperties;", "", "Landroidx/compose/ui/unit/Dp;", "caretHeight", "caretWidth", "<init>", "(FFLkotlin/jvm/internal/h;)V", "component1-D9Ej5fM", "()F", "component1", "component2-D9Ej5fM", "component2", "copy-YgX7TsA", "(FF)Landroidx/compose/material3/CaretProperties;", "copy", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "F", "getCaretHeight-D9Ej5fM", "getCaretWidth-D9Ej5fM", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class CaretProperties {
    public static final int $stable = 0;
    private final float caretHeight;
    private final float caretWidth;

    public /* synthetic */ CaretProperties(float f10, float f11, kotlin.jvm.internal.h hVar) {
        this(f10, f11);
    }

    /* JADX INFO: renamed from: copy-YgX7TsA$default, reason: not valid java name */
    public static /* synthetic */ CaretProperties m1294copyYgX7TsA$default(CaretProperties caretProperties, float f10, float f11, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            f10 = caretProperties.caretHeight;
        }
        if ((i10 & 2) != 0) {
            f11 = caretProperties.caretWidth;
        }
        return caretProperties.m1297copyYgX7TsA(f10, f11);
    }

    /* JADX INFO: renamed from: component1-D9Ej5fM, reason: not valid java name and from getter */
    public final float getCaretHeight() {
        return this.caretHeight;
    }

    /* JADX INFO: renamed from: component2-D9Ej5fM, reason: not valid java name and from getter */
    public final float getCaretWidth() {
        return this.caretWidth;
    }

    /* JADX INFO: renamed from: copy-YgX7TsA, reason: not valid java name */
    public final CaretProperties m1297copyYgX7TsA(float caretHeight, float caretWidth) {
        return new CaretProperties(caretHeight, caretWidth, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CaretProperties)) {
            return false;
        }
        CaretProperties caretProperties = (CaretProperties) other;
        return Dp.m5683equalsimpl0(this.caretHeight, caretProperties.caretHeight) && Dp.m5683equalsimpl0(this.caretWidth, caretProperties.caretWidth);
    }

    /* JADX INFO: renamed from: getCaretHeight-D9Ej5fM, reason: not valid java name */
    public final float m1298getCaretHeightD9Ej5fM() {
        return this.caretHeight;
    }

    /* JADX INFO: renamed from: getCaretWidth-D9Ej5fM, reason: not valid java name */
    public final float m1299getCaretWidthD9Ej5fM() {
        return this.caretWidth;
    }

    public int hashCode() {
        return Dp.m5684hashCodeimpl(this.caretWidth) + (Dp.m5684hashCodeimpl(this.caretHeight) * 31);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("CaretProperties(caretHeight=");
        a0.c.v(this.caretHeight, sb2, ", caretWidth=");
        sb2.append((Object) Dp.m5689toStringimpl(this.caretWidth));
        sb2.append(')');
        return sb2.toString();
    }

    private CaretProperties(float f10, float f11) {
        this.caretHeight = f10;
        this.caretWidth = f11;
    }
}
