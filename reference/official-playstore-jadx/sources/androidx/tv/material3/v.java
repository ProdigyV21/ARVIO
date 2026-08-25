package androidx.tv.material3;

/* JADX INFO: loaded from: classes3.dex */
public final class v {
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || v.class != obj.getClass()) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Float.floatToIntBits(1.0f) + a0.c.b(1.1f, Float.floatToIntBits(1.0f) * 31, 31);
    }

    public final String toString() {
        return "CardScale(scale=1.0, focusedScale=1.1, pressedScale=1.0)";
    }
}
