package androidx.tv.material3;

/* JADX INFO: loaded from: classes3.dex */
public final class c2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f5874a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f5875b;

    public c2(float f10, float f11) {
        this.f5874a = f10;
        this.f5875b = f11;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || c2.class != obj.getClass()) {
            return false;
        }
        c2 c2Var = (c2) obj;
        return this.f5874a == c2Var.f5874a && this.f5875b == c2Var.f5875b;
    }

    public final int hashCode() {
        return Float.floatToIntBits(1.0f) + a0.c.b(1.0f, a0.c.b(this.f5875b, a0.c.b(1.0f, a0.c.b(1.0f, a0.c.b(1.0f, a0.c.b(this.f5874a, Float.floatToIntBits(1.0f) * 31, 31), 31), 31), 31), 31), 31);
    }

    public final String toString() {
        return "ListItemScale(scale=1.0, focusedScale=" + this.f5874a + ", pressedScale=1.0, selectedScale=1.0, disabledScale=1.0, focusedSelectedScale=" + this.f5875b + ", focusedDisabledScale=1.0, pressedSelectedScale=1.0)";
    }
}
