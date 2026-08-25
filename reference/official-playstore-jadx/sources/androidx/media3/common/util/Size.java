package androidx.media3.common.util;

import android.os.Bundle;

/* JADX INFO: loaded from: classes3.dex */
public final class Size {
    private final int height;
    private final int width;
    public static final Size UNKNOWN = new Size(-1, -1);
    public static final Size ZERO = new Size(0, 0);
    private static final String FIELD_WIDTH = Util.intToStringMaxRadix(0);
    private static final String FIELD_HEIGHT = Util.intToStringMaxRadix(1);

    public Size(int i10, int i11) {
        ac.b.j((i10 == -1 || i10 >= 0) && (i11 == -1 || i11 >= 0));
        this.width = i10;
        this.height = i11;
    }

    public static Size fromBundle(Bundle bundle) {
        return new Size(bundle.getInt(FIELD_WIDTH, -1), bundle.getInt(FIELD_HEIGHT, -1));
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof Size) {
            Size size = (Size) obj;
            if (this.width == size.width && this.height == size.height) {
                return true;
            }
        }
        return false;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public int hashCode() {
        int i10 = this.height;
        int i11 = this.width;
        return i10 ^ ((i11 >>> 16) | (i11 << 16));
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(FIELD_WIDTH, this.width);
        bundle.putInt(FIELD_HEIGHT, this.height);
        return bundle;
    }

    public String toString() {
        return this.width + "x" + this.height;
    }
}
