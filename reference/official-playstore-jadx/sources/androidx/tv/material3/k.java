package androidx.tv.material3;

import androidx.compose.ui.graphics.Shape;

/* JADX INFO: loaded from: classes3.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Shape f6142a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Shape f6143b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Shape f6144c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Shape f6145d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Shape f6146e;

    public k(Shape shape, Shape shape2, Shape shape3, Shape shape4, Shape shape5) {
        this.f6142a = shape;
        this.f6143b = shape2;
        this.f6144c = shape3;
        this.f6145d = shape4;
        this.f6146e = shape5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || k.class != obj.getClass()) {
            return false;
        }
        k kVar = (k) obj;
        return kotlin.jvm.internal.p.a(this.f6142a, kVar.f6142a) && kotlin.jvm.internal.p.a(this.f6143b, kVar.f6143b) && kotlin.jvm.internal.p.a(this.f6144c, kVar.f6144c) && kotlin.jvm.internal.p.a(this.f6145d, kVar.f6145d) && kotlin.jvm.internal.p.a(this.f6146e, kVar.f6146e);
    }

    public final int hashCode() {
        return this.f6146e.hashCode() + androidx.compose.material3.d.c(this.f6145d, androidx.compose.material3.d.c(this.f6144c, androidx.compose.material3.d.c(this.f6143b, this.f6142a.hashCode() * 31, 31), 31), 31);
    }

    public final String toString() {
        return "ButtonShape(shape=" + this.f6142a + ", focusedShape=" + this.f6143b + ", pressedShape=" + this.f6144c + ", disabledShape=" + this.f6145d + ", focusedDisabledShape=" + this.f6146e + ')';
    }
}
