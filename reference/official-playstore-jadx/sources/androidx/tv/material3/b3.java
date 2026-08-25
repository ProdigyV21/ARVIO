package androidx.tv.material3;

import androidx.compose.ui.graphics.Shape;

/* JADX INFO: loaded from: classes3.dex */
public final class b3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Shape f5839a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Shape f5840b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Shape f5841c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Shape f5842d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Shape f5843e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Shape f5844f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Shape f5845g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Shape f5846h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Shape f5847i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Shape f5848j;

    public b3(Shape shape, Shape shape2, Shape shape3, Shape shape4, Shape shape5, Shape shape6, Shape shape7, Shape shape8, Shape shape9, Shape shape10) {
        this.f5839a = shape;
        this.f5840b = shape2;
        this.f5841c = shape3;
        this.f5842d = shape4;
        this.f5843e = shape5;
        this.f5844f = shape6;
        this.f5845g = shape7;
        this.f5846h = shape8;
        this.f5847i = shape9;
        this.f5848j = shape10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b3.class != obj.getClass()) {
            return false;
        }
        b3 b3Var = (b3) obj;
        return kotlin.jvm.internal.p.a(this.f5839a, b3Var.f5839a) && kotlin.jvm.internal.p.a(this.f5840b, b3Var.f5840b) && kotlin.jvm.internal.p.a(this.f5841c, b3Var.f5841c) && kotlin.jvm.internal.p.a(this.f5842d, b3Var.f5842d) && kotlin.jvm.internal.p.a(this.f5843e, b3Var.f5843e) && kotlin.jvm.internal.p.a(this.f5844f, b3Var.f5844f) && kotlin.jvm.internal.p.a(this.f5845g, b3Var.f5845g) && kotlin.jvm.internal.p.a(this.f5846h, b3Var.f5846h) && kotlin.jvm.internal.p.a(this.f5847i, b3Var.f5847i) && kotlin.jvm.internal.p.a(this.f5848j, b3Var.f5848j);
    }

    public final int hashCode() {
        return this.f5848j.hashCode() + androidx.compose.material3.d.c(this.f5847i, androidx.compose.material3.d.c(this.f5846h, androidx.compose.material3.d.c(this.f5845g, androidx.compose.material3.d.c(this.f5844f, androidx.compose.material3.d.c(this.f5843e, androidx.compose.material3.d.c(this.f5842d, androidx.compose.material3.d.c(this.f5841c, androidx.compose.material3.d.c(this.f5840b, this.f5839a.hashCode() * 31, 31), 31), 31), 31), 31), 31), 31), 31);
    }

    public final String toString() {
        return "SelectableSurfaceShape(shape=" + this.f5839a + ", focusedShape=" + this.f5840b + ",pressedShape=" + this.f5841c + ", selectedShape=" + this.f5842d + ",disabledShape=" + this.f5843e + ", focusedSelectedShape=" + this.f5844f + ", focusedDisabledShape=" + this.f5845g + ",pressedSelectedShape=" + this.f5846h + ", selectedDisabledShape=" + this.f5847i + ", focusedSelectedDisabledShape=" + this.f5848j + ')';
    }
}
