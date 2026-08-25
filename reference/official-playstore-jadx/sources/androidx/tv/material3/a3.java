package androidx.tv.material3;

/* JADX INFO: loaded from: classes3.dex */
public final class a3 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a3 f5810c = new a3(1.0f, 1.0f);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f5811a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f5812b;

    public a3(float f10, float f11) {
        this.f5811a = f10;
        this.f5812b = f11;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || a3.class != obj.getClass()) {
            return false;
        }
        a3 a3Var = (a3) obj;
        return this.f5811a == a3Var.f5811a && this.f5812b == a3Var.f5812b;
    }

    public final int hashCode() {
        return Float.floatToIntBits(1.0f) + a0.c.b(1.0f, a0.c.b(1.0f, a0.c.b(1.0f, a0.c.b(this.f5812b, a0.c.b(1.0f, a0.c.b(1.0f, a0.c.b(1.0f, a0.c.b(this.f5811a, Float.floatToIntBits(1.0f) * 31, 31), 31), 31), 31), 31), 31), 31), 31);
    }

    public final String toString() {
        return "SelectableSurfaceScale(scale=1.0, focusedScale=" + this.f5811a + ",pressedScale=1.0, selectedScale=1.0,disabledScale=1.0, focusedSelectedScale=" + this.f5812b + ", focusedDisabledScale=1.0,pressedSelectedScale=1.0, selectedDisabledScale=1.0, focusedSelectedDisabledScale=1.0)";
    }
}
