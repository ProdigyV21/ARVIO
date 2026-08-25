package androidx.tv.material3;

import androidx.compose.ui.graphics.Shape;

/* JADX INFO: loaded from: classes3.dex */
public final class d2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Shape f5904a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Shape f5905b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Shape f5906c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Shape f5907d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Shape f5908e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Shape f5909f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Shape f5910g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Shape f5911h;

    public d2(Shape shape, Shape shape2, Shape shape3, Shape shape4, Shape shape5, Shape shape6, Shape shape7, Shape shape8) {
        this.f5904a = shape;
        this.f5905b = shape2;
        this.f5906c = shape3;
        this.f5907d = shape4;
        this.f5908e = shape5;
        this.f5909f = shape6;
        this.f5910g = shape7;
        this.f5911h = shape8;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || d2.class != obj.getClass()) {
            return false;
        }
        d2 d2Var = (d2) obj;
        return kotlin.jvm.internal.p.a(this.f5904a, d2Var.f5904a) && kotlin.jvm.internal.p.a(this.f5905b, d2Var.f5905b) && kotlin.jvm.internal.p.a(this.f5906c, d2Var.f5906c) && kotlin.jvm.internal.p.a(this.f5907d, d2Var.f5907d) && kotlin.jvm.internal.p.a(this.f5908e, d2Var.f5908e) && kotlin.jvm.internal.p.a(this.f5909f, d2Var.f5909f) && kotlin.jvm.internal.p.a(this.f5910g, d2Var.f5910g) && kotlin.jvm.internal.p.a(this.f5911h, d2Var.f5911h);
    }

    public final int hashCode() {
        return this.f5911h.hashCode() + androidx.compose.material3.d.c(this.f5910g, androidx.compose.material3.d.c(this.f5909f, androidx.compose.material3.d.c(this.f5908e, androidx.compose.material3.d.c(this.f5907d, androidx.compose.material3.d.c(this.f5906c, androidx.compose.material3.d.c(this.f5905b, this.f5904a.hashCode() * 31, 31), 31), 31), 31), 31), 31);
    }

    public final String toString() {
        return "ListItemShape(shape=" + this.f5904a + ", focusedShape=" + this.f5905b + ",pressedShape=" + this.f5906c + ", selectedShape=" + this.f5907d + ", disabledShape=" + this.f5908e + ", focusedSelectedShape=" + this.f5909f + ", focusedDisabledShape=" + this.f5910g + ", pressedSelectedShape=" + this.f5911h + ')';
    }
}
