package androidx.tv.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Dp;

/* JADX INFO: loaded from: classes3.dex */
public final class d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final d f5889d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final BorderStroke f5890a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f5891b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Shape f5892c;

    static {
        float f10 = 0;
        f5889d = new d(BorderStrokeKt.m207BorderStrokecXLIe8U(Dp.m5678constructorimpl(f10), Color.INSTANCE.m3507getTransparent0d7_KjU()), Dp.m5678constructorimpl(f10), RectangleShapeKt.getRectangleShape());
    }

    public d(BorderStroke borderStroke, float f10, Shape shape) {
        this.f5890a = borderStroke;
        this.f5891b = f10;
        this.f5892c = shape;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || d.class != obj.getClass()) {
            return false;
        }
        d dVar = (d) obj;
        return kotlin.jvm.internal.p.a(this.f5890a, dVar.f5890a) && Dp.m5683equalsimpl0(this.f5891b, dVar.f5891b) && kotlin.jvm.internal.p.a(this.f5892c, dVar.f5892c);
    }

    public final int hashCode() {
        return this.f5892c.hashCode() + a0.c.C(this.f5891b, this.f5890a.hashCode() * 31, 31);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("Border(border=");
        sb2.append(this.f5890a);
        sb2.append(", inset=");
        a0.c.v(this.f5891b, sb2, ", shape=");
        sb2.append(this.f5892c);
        sb2.append(')');
        return sb2.toString();
    }

    public d(BorderStroke borderStroke, Shape shape, int i10) {
        this(borderStroke, Dp.m5678constructorimpl(0), (i10 & 4) != 0 ? j2.j.f19117a : shape);
    }
}
