package android.content;

import android.content.Context;
import coil.disk.DiskCache;
import java.io.File;
import kotlin.Metadata;
import l7.i;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcoil/util/SingletonDiskCache;", "", "()V", "DIRECTORY", "", "instance", "Lcoil/disk/DiskCache;", "get", "context", "Landroid/content/Context;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class SingletonDiskCache {
    private static final String DIRECTORY = "image_cache";
    public static final SingletonDiskCache INSTANCE = new SingletonDiskCache();
    private static DiskCache instance;

    private SingletonDiskCache() {
    }

    public final synchronized DiskCache get(Context context) {
        DiskCache diskCacheBuild;
        diskCacheBuild = instance;
        if (diskCacheBuild == null) {
            diskCacheBuild = new DiskCache.Builder().directory(i.t0(Utils.getSafeCacheDir(context), new File(DIRECTORY))).build();
            instance = diskCacheBuild;
        }
        return diskCacheBuild;
    }
}
