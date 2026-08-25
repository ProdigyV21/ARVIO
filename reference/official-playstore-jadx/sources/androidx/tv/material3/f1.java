package androidx.tv.material3;

/* JADX INFO: loaded from: classes3.dex */
public final class f1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f5965a;

    public f1(float f10) {
        this.f5965a = f10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && f1.class == obj.getClass() && this.f5965a == ((f1) obj).f5965a;
    }

    public final int hashCode() {
        return Float.floatToIntBits(1.0f) + a0.c.b(1.0f, a0.c.b(1.0f, a0.c.b(this.f5965a, Float.floatToIntBits(1.0f) * 31, 31), 31), 31);
    }

    public final String toString() {
        return "ClickableSurfaceScale(scale=1.0, focusedScale=" + this.f5965a + ",pressedScale=1.0, disabledScale=1.0, focusedDisabledScale=1.0)";
    }
}
