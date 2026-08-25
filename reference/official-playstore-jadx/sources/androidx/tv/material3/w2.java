package androidx.tv.material3;

import androidx.compose.ui.graphics.Shape;

/* JADX INFO: loaded from: classes3.dex */
public final class w2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Shape f6481a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Shape f6482b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Shape f6483c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Shape f6484d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Shape f6485e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Shape f6486f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Shape f6487g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Shape f6488h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Shape f6489i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Shape f6490j;

    public w2(Shape shape, Shape shape2, Shape shape3, Shape shape4, Shape shape5, Shape shape6, Shape shape7, Shape shape8, Shape shape9, Shape shape10) {
        this.f6481a = shape;
        this.f6482b = shape2;
        this.f6483c = shape3;
        this.f6484d = shape4;
        this.f6485e = shape5;
        this.f6486f = shape6;
        this.f6487g = shape7;
        this.f6488h = shape8;
        this.f6489i = shape9;
        this.f6490j = shape10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || w2.class != obj.getClass()) {
            return false;
        }
        w2 w2Var = (w2) obj;
        return kotlin.jvm.internal.p.a(this.f6481a, w2Var.f6481a) && kotlin.jvm.internal.p.a(this.f6482b, w2Var.f6482b) && kotlin.jvm.internal.p.a(this.f6483c, w2Var.f6483c) && kotlin.jvm.internal.p.a(this.f6484d, w2Var.f6484d) && kotlin.jvm.internal.p.a(this.f6485e, w2Var.f6485e) && kotlin.jvm.internal.p.a(this.f6486f, w2Var.f6486f) && kotlin.jvm.internal.p.a(this.f6487g, w2Var.f6487g) && kotlin.jvm.internal.p.a(this.f6488h, w2Var.f6488h) && kotlin.jvm.internal.p.a(this.f6489i, w2Var.f6489i) && kotlin.jvm.internal.p.a(this.f6490j, w2Var.f6490j);
    }

    public final int hashCode() {
        return this.f6490j.hashCode() + androidx.compose.material3.d.c(this.f6489i, androidx.compose.material3.d.c(this.f6488h, androidx.compose.material3.d.c(this.f6487g, androidx.compose.material3.d.c(this.f6486f, androidx.compose.material3.d.c(this.f6485e, androidx.compose.material3.d.c(this.f6484d, androidx.compose.material3.d.c(this.f6483c, androidx.compose.material3.d.c(this.f6482b, this.f6481a.hashCode() * 31, 31), 31), 31), 31), 31), 31), 31), 31);
    }

    public final String toString() {
        return "SelectableChipShape(shape=" + this.f6481a + ", focusedShape=" + this.f6482b + ", pressedShape=" + this.f6483c + ", selectedShape=" + this.f6484d + ", disabledShape=" + this.f6485e + ", focusedSelectedShape=" + this.f6486f + ", focusedDisabledShape=" + this.f6487g + ",pressedSelectedShape=" + this.f6488h + ", selectedDisabledShape=" + this.f6489i + ", focusedSelectedDisabledShape=" + this.f6490j + ')';
    }
}
