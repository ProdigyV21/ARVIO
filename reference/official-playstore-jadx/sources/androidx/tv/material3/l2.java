package androidx.tv.material3;

import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.ui.graphics.Shape;

/* JADX INFO: loaded from: classes3.dex */
public final class l2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Shape f6188a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Shape f6189b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Shape f6190c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Shape f6191d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Shape f6192e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Shape f6193f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Shape f6194g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Shape f6195h;

    public l2(RoundedCornerShape roundedCornerShape, RoundedCornerShape roundedCornerShape2, RoundedCornerShape roundedCornerShape3, RoundedCornerShape roundedCornerShape4, RoundedCornerShape roundedCornerShape5, RoundedCornerShape roundedCornerShape6, RoundedCornerShape roundedCornerShape7, RoundedCornerShape roundedCornerShape8) {
        this.f6188a = roundedCornerShape;
        this.f6189b = roundedCornerShape2;
        this.f6190c = roundedCornerShape3;
        this.f6191d = roundedCornerShape4;
        this.f6192e = roundedCornerShape5;
        this.f6193f = roundedCornerShape6;
        this.f6194g = roundedCornerShape7;
        this.f6195h = roundedCornerShape8;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || l2.class != obj.getClass()) {
            return false;
        }
        l2 l2Var = (l2) obj;
        if (kotlin.jvm.internal.p.a(this.f6188a, l2Var.f6188a) && kotlin.jvm.internal.p.a(this.f6189b, l2Var.f6189b) && kotlin.jvm.internal.p.a(this.f6190c, l2Var.f6190c) && kotlin.jvm.internal.p.a(this.f6191d, l2Var.f6191d) && kotlin.jvm.internal.p.a(this.f6192e, l2Var.f6192e) && kotlin.jvm.internal.p.a(this.f6193f, l2Var.f6193f) && kotlin.jvm.internal.p.a(this.f6194g, l2Var.f6194g)) {
            return kotlin.jvm.internal.p.a(this.f6195h, l2Var.f6195h);
        }
        return false;
    }

    public final int hashCode() {
        return this.f6195h.hashCode() + androidx.compose.material3.d.c(this.f6194g, androidx.compose.material3.d.c(this.f6193f, androidx.compose.material3.d.c(this.f6192e, androidx.compose.material3.d.c(this.f6191d, androidx.compose.material3.d.c(this.f6190c, androidx.compose.material3.d.c(this.f6189b, this.f6188a.hashCode() * 31, 31), 31), 31), 31), 31), 31);
    }

    public final String toString() {
        return "NavigationDrawerItemShape(shape=" + this.f6188a + ", focusedShape=" + this.f6189b + ",pressedShape=" + this.f6190c + ", selectedShape=" + this.f6191d + ", disabledShape=" + this.f6192e + ", focusedSelectedShape=" + this.f6193f + ", focusedDisabledShape=" + this.f6194g + ", pressedSelectedShape=" + this.f6195h + ')';
    }
}
