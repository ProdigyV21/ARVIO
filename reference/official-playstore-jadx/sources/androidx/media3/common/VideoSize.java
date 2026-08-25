package androidx.media3.common;

import android.os.Bundle;
import androidx.media3.common.util.Util;

/* JADX INFO: loaded from: classes3.dex */
public final class VideoSize {
    private static final int DEFAULT_HEIGHT = 0;
    private static final float DEFAULT_PIXEL_WIDTH_HEIGHT_RATIO = 1.0f;
    private static final int DEFAULT_WIDTH = 0;
    public final int height;
    public final float pixelWidthHeightRatio;

    @Deprecated
    public final int unappliedRotationDegrees;
    public final int width;
    public static final VideoSize UNKNOWN = new VideoSize(0, 0);
    private static final String FIELD_WIDTH = Util.intToStringMaxRadix(0);
    private static final String FIELD_HEIGHT = Util.intToStringMaxRadix(1);
    private static final String FIELD_PIXEL_WIDTH_HEIGHT_RATIO = Util.intToStringMaxRadix(3);

    public VideoSize(int i10, int i11) {
        this(i10, i11, 1.0f);
    }

    public static VideoSize fromBundle(Bundle bundle) {
        return new VideoSize(bundle.getInt(FIELD_WIDTH, 0), bundle.getInt(FIELD_HEIGHT, 0), bundle.getFloat(FIELD_PIXEL_WIDTH_HEIGHT_RATIO, 1.0f));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof VideoSize) {
            VideoSize videoSize = (VideoSize) obj;
            if (this.width == videoSize.width && this.height == videoSize.height && this.pixelWidthHeightRatio == videoSize.pixelWidthHeightRatio) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return Float.floatToRawIntBits(this.pixelWidthHeightRatio) + ((((217 + this.width) * 31) + this.height) * 31);
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        int i10 = this.width;
        if (i10 != 0) {
            bundle.putInt(FIELD_WIDTH, i10);
        }
        int i11 = this.height;
        if (i11 != 0) {
            bundle.putInt(FIELD_HEIGHT, i11);
        }
        float f10 = this.pixelWidthHeightRatio;
        if (f10 != 1.0f) {
            bundle.putFloat(FIELD_PIXEL_WIDTH_HEIGHT_RATIO, f10);
        }
        return bundle;
    }

    public VideoSize(int i10, int i11, float f10) {
        this.width = i10;
        this.height = i11;
        this.unappliedRotationDegrees = 0;
        this.pixelWidthHeightRatio = f10;
    }

    @Deprecated
    public VideoSize(int i10, int i11, int i12, float f10) {
        this(i10, i11, f10);
    }
}
