package androidx.tv.material3;

import androidx.compose.ui.graphics.Shape;

/* JADX INFO: loaded from: classes3.dex */
public final class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Shape f6469a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Shape f6470b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Shape f6471c;

    public w(Shape shape, Shape shape2, Shape shape3) {
        this.f6469a = shape;
        this.f6470b = shape2;
        this.f6471c = shape3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || w.class != obj.getClass()) {
            return false;
        }
        w wVar = (w) obj;
        return kotlin.jvm.internal.p.a(this.f6469a, wVar.f6469a) && kotlin.jvm.internal.p.a(this.f6470b, wVar.f6470b) && kotlin.jvm.internal.p.a(this.f6471c, wVar.f6471c);
    }

    public final int hashCode() {
        return this.f6471c.hashCode() + androidx.compose.material3.d.c(this.f6470b, this.f6469a.hashCode() * 31, 31);
    }

    public final String toString() {
        return "CardShape(shape=" + this.f6469a + ", focusedShape=" + this.f6470b + ", pressedShape=" + this.f6471c + ')';
    }
}
