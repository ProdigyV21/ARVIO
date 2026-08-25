package androidx.tv.material3;

import androidx.compose.ui.graphics.Shape;

/* JADX INFO: loaded from: classes3.dex */
public final class g1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Shape f6000a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Shape f6001b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Shape f6002c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Shape f6003d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Shape f6004e;

    public g1(Shape shape, Shape shape2, Shape shape3, Shape shape4, Shape shape5) {
        this.f6000a = shape;
        this.f6001b = shape2;
        this.f6002c = shape3;
        this.f6003d = shape4;
        this.f6004e = shape5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || g1.class != obj.getClass()) {
            return false;
        }
        g1 g1Var = (g1) obj;
        return kotlin.jvm.internal.p.a(this.f6000a, g1Var.f6000a) && kotlin.jvm.internal.p.a(this.f6001b, g1Var.f6001b) && kotlin.jvm.internal.p.a(this.f6002c, g1Var.f6002c) && kotlin.jvm.internal.p.a(this.f6003d, g1Var.f6003d) && kotlin.jvm.internal.p.a(this.f6004e, g1Var.f6004e);
    }

    public final int hashCode() {
        return this.f6004e.hashCode() + androidx.compose.material3.d.c(this.f6003d, androidx.compose.material3.d.c(this.f6002c, androidx.compose.material3.d.c(this.f6001b, this.f6000a.hashCode() * 31, 31), 31), 31);
    }

    public final String toString() {
        return "ClickableSurfaceShape(shape=" + this.f6000a + ", focusedShape=" + this.f6001b + ", pressedShape=" + this.f6002c + ", disabledShape=" + this.f6003d + ", focusedDisabledShape=" + this.f6004e + ')';
    }
}
