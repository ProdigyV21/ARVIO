package androidx.tv.material3;

import androidx.compose.ui.graphics.Shape;

/* JADX INFO: loaded from: classes3.dex */
public final class b1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Shape f5820a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Shape f5821b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Shape f5822c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Shape f5823d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Shape f5824e;

    public b1(Shape shape, Shape shape2, Shape shape3, Shape shape4, Shape shape5) {
        this.f5820a = shape;
        this.f5821b = shape2;
        this.f5822c = shape3;
        this.f5823d = shape4;
        this.f5824e = shape5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b1.class != obj.getClass()) {
            return false;
        }
        b1 b1Var = (b1) obj;
        return kotlin.jvm.internal.p.a(this.f5820a, b1Var.f5820a) && kotlin.jvm.internal.p.a(this.f5821b, b1Var.f5821b) && kotlin.jvm.internal.p.a(this.f5822c, b1Var.f5822c) && kotlin.jvm.internal.p.a(this.f5823d, b1Var.f5823d) && kotlin.jvm.internal.p.a(this.f5824e, b1Var.f5824e);
    }

    public final int hashCode() {
        return this.f5824e.hashCode() + androidx.compose.material3.d.c(this.f5823d, androidx.compose.material3.d.c(this.f5822c, androidx.compose.material3.d.c(this.f5821b, this.f5820a.hashCode() * 31, 31), 31), 31);
    }

    public final String toString() {
        return "ClickableChipShape(shape=" + this.f5820a + ", focusedShape=" + this.f5821b + ", pressedShape=" + this.f5822c + ", disabledShape=" + this.f5823d + ", focusedDisabledShape=" + this.f5824e + ')';
    }
}
