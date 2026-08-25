package androidx.media3.common;

import android.view.Surface;

/* JADX INFO: loaded from: classes3.dex */
public final class SurfaceInfo {
    public final int height;
    public final boolean isEncoderInputSurface;
    public final int orientationDegrees;
    public final Surface surface;
    public final int width;

    public SurfaceInfo(Surface surface, int i10, int i11) {
        this(surface, i10, i11, 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SurfaceInfo)) {
            return false;
        }
        SurfaceInfo surfaceInfo = (SurfaceInfo) obj;
        return this.width == surfaceInfo.width && this.height == surfaceInfo.height && this.orientationDegrees == surfaceInfo.orientationDegrees && this.isEncoderInputSurface == surfaceInfo.isEncoderInputSurface && this.surface.equals(surfaceInfo.surface);
    }

    public int hashCode() {
        return (((((((this.surface.hashCode() * 31) + this.width) * 31) + this.height) * 31) + this.orientationDegrees) * 31) + (this.isEncoderInputSurface ? 1 : 0);
    }

    public SurfaceInfo(Surface surface, int i10, int i11, int i12) {
        this(surface, i10, i11, i12, false);
    }

    public SurfaceInfo(Surface surface, int i10, int i11, int i12, boolean z) {
        ac.b.k(i12 == 0 || i12 == 90 || i12 == 180 || i12 == 270, "orientationDegrees must be 0, 90, 180, or 270");
        this.surface = surface;
        this.width = i10;
        this.height = i11;
        this.orientationDegrees = i12;
        this.isEncoderInputSurface = z;
    }
}
