package android.content;

import android.graphics.ExifOrientationPolicy;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B7\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ8\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006\u0014"}, d2 = {"Lcoil/util/ImageLoaderOptions;", "", "addLastModifiedToFileCacheKey", "", "networkObserverEnabled", "respectCacheHeaders", "bitmapFactoryMaxParallelism", "", "bitmapFactoryExifOrientationPolicy", "Lcoil/decode/ExifOrientationPolicy;", "(ZZZILcoil/decode/ExifOrientationPolicy;)V", "getAddLastModifiedToFileCacheKey", "()Z", "getBitmapFactoryExifOrientationPolicy", "()Lcoil/decode/ExifOrientationPolicy;", "getBitmapFactoryMaxParallelism", "()I", "getNetworkObserverEnabled", "getRespectCacheHeaders", "copy", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ImageLoaderOptions {
    private final boolean addLastModifiedToFileCacheKey;
    private final ExifOrientationPolicy bitmapFactoryExifOrientationPolicy;
    private final int bitmapFactoryMaxParallelism;
    private final boolean networkObserverEnabled;
    private final boolean respectCacheHeaders;

    public ImageLoaderOptions() {
        this(false, false, false, 0, null, 31, null);
    }

    public static /* synthetic */ ImageLoaderOptions copy$default(ImageLoaderOptions imageLoaderOptions, boolean z, boolean z5, boolean z10, int i10, ExifOrientationPolicy exifOrientationPolicy, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            z = imageLoaderOptions.addLastModifiedToFileCacheKey;
        }
        if ((i11 & 2) != 0) {
            z5 = imageLoaderOptions.networkObserverEnabled;
        }
        if ((i11 & 4) != 0) {
            z10 = imageLoaderOptions.respectCacheHeaders;
        }
        if ((i11 & 8) != 0) {
            i10 = imageLoaderOptions.bitmapFactoryMaxParallelism;
        }
        if ((i11 & 16) != 0) {
            exifOrientationPolicy = imageLoaderOptions.bitmapFactoryExifOrientationPolicy;
        }
        ExifOrientationPolicy exifOrientationPolicy2 = exifOrientationPolicy;
        boolean z11 = z10;
        return imageLoaderOptions.copy(z, z5, z11, i10, exifOrientationPolicy2);
    }

    public final ImageLoaderOptions copy(boolean addLastModifiedToFileCacheKey, boolean networkObserverEnabled, boolean respectCacheHeaders, int bitmapFactoryMaxParallelism, ExifOrientationPolicy bitmapFactoryExifOrientationPolicy) {
        return new ImageLoaderOptions(addLastModifiedToFileCacheKey, networkObserverEnabled, respectCacheHeaders, bitmapFactoryMaxParallelism, bitmapFactoryExifOrientationPolicy);
    }

    public final boolean getAddLastModifiedToFileCacheKey() {
        return this.addLastModifiedToFileCacheKey;
    }

    public final ExifOrientationPolicy getBitmapFactoryExifOrientationPolicy() {
        return this.bitmapFactoryExifOrientationPolicy;
    }

    public final int getBitmapFactoryMaxParallelism() {
        return this.bitmapFactoryMaxParallelism;
    }

    public final boolean getNetworkObserverEnabled() {
        return this.networkObserverEnabled;
    }

    public final boolean getRespectCacheHeaders() {
        return this.respectCacheHeaders;
    }

    public ImageLoaderOptions(boolean z, boolean z5, boolean z10, int i10, ExifOrientationPolicy exifOrientationPolicy) {
        this.addLastModifiedToFileCacheKey = z;
        this.networkObserverEnabled = z5;
        this.respectCacheHeaders = z10;
        this.bitmapFactoryMaxParallelism = i10;
        this.bitmapFactoryExifOrientationPolicy = exifOrientationPolicy;
    }

    public /* synthetic */ ImageLoaderOptions(boolean z, boolean z5, boolean z10, int i10, ExifOrientationPolicy exifOrientationPolicy, int i11, h hVar) {
        this((i11 & 1) != 0 ? true : z, (i11 & 2) != 0 ? true : z5, (i11 & 4) != 0 ? true : z10, (i11 & 8) != 0 ? 4 : i10, (i11 & 16) != 0 ? ExifOrientationPolicy.RESPECT_PERFORMANCE : exifOrientationPolicy);
    }
}
