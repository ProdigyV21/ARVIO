package androidx.media3.exoplayer.offline;

import androidx.media3.common.MediaItem;
import androidx.media3.datasource.cache.CacheWriter;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.drm.DrmSessionManagerProvider;
import androidx.media3.exoplayer.scheduler.RequirementsWatcher;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class b implements DrmSessionManagerProvider, RequirementsWatcher.Listener, CacheWriter.ProgressListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Object f3719a;

    public /* synthetic */ b(Object obj) {
        this.f3719a = obj;
    }

    @Override // androidx.media3.exoplayer.drm.DrmSessionManagerProvider
    public DrmSessionManager get(MediaItem mediaItem) {
        return DownloadHelper.lambda$createMediaSourceInternal$4((DrmSessionManager) this.f3719a, mediaItem);
    }

    @Override // androidx.media3.datasource.cache.CacheWriter.ProgressListener
    public void onProgress(long j10, long j11, long j12) {
        ((ProgressiveDownloader) this.f3719a).onProgress(j10, j11, j12);
    }

    @Override // androidx.media3.exoplayer.scheduler.RequirementsWatcher.Listener
    public void onRequirementsStateChanged(RequirementsWatcher requirementsWatcher, int i10) {
        ((DownloadManager) this.f3719a).onRequirementsStateChanged(requirementsWatcher, i10);
    }
}
