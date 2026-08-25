package androidx.tv.material3;

/* JADX INFO: loaded from: classes3.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f6121a;

    public j(float f10) {
        this.f6121a = f10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && j.class == obj.getClass() && this.f6121a == ((j) obj).f6121a;
    }

    public final int hashCode() {
        return Float.floatToIntBits(1.0f) + a0.c.b(1.0f, a0.c.b(1.0f, a0.c.b(this.f6121a, Float.floatToIntBits(1.0f) * 31, 31), 31), 31);
    }

    public final String toString() {
        return "ButtonScale(scale=1.0, focusedScale=" + this.f6121a + ", pressedScale=1.0, disabledScale=1.0, focusedDisabledScale=1.0)";
    }
}
