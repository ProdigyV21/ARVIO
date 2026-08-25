package androidx.tv.material3;

import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.foundation.shape.RoundedCornerShape;

/* JADX INFO: loaded from: classes3.dex */
public final class d3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CornerBasedShape f5912a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final CornerBasedShape f5913b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final CornerBasedShape f5914c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final CornerBasedShape f5915d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final CornerBasedShape f5916e;

    public d3() {
        RoundedCornerShape roundedCornerShape = c3.f5876a;
        RoundedCornerShape roundedCornerShape2 = c3.f5877b;
        RoundedCornerShape roundedCornerShape3 = c3.f5878c;
        RoundedCornerShape roundedCornerShape4 = c3.f5879d;
        RoundedCornerShape roundedCornerShape5 = c3.f5880e;
        this.f5912a = roundedCornerShape;
        this.f5913b = roundedCornerShape2;
        this.f5914c = roundedCornerShape3;
        this.f5915d = roundedCornerShape4;
        this.f5916e = roundedCornerShape5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d3)) {
            return false;
        }
        d3 d3Var = (d3) obj;
        return kotlin.jvm.internal.p.a(this.f5912a, d3Var.f5912a) && kotlin.jvm.internal.p.a(this.f5913b, d3Var.f5913b) && kotlin.jvm.internal.p.a(this.f5914c, d3Var.f5914c) && kotlin.jvm.internal.p.a(this.f5915d, d3Var.f5915d) && kotlin.jvm.internal.p.a(this.f5916e, d3Var.f5916e);
    }

    public final int hashCode() {
        return this.f5916e.hashCode() + ((this.f5915d.hashCode() + ((this.f5914c.hashCode() + ((this.f5913b.hashCode() + (this.f5912a.hashCode() * 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "Shapes(extraSmall=" + this.f5912a + ", small=" + this.f5913b + ", medium=" + this.f5914c + ", large=" + this.f5915d + ", extraLarge=" + this.f5916e + ')';
    }
}
