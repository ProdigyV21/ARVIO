package androidx.media3.exoplayer.offline;

import androidx.media3.common.MediaItem;
import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes3.dex */
public interface SegmentDownloaderFactory {
    SegmentDownloader<?> create(MediaItem mediaItem);

    SegmentDownloaderFactory setDurationUs(long j10);

    SegmentDownloaderFactory setExecutor(Executor executor);

    SegmentDownloaderFactory setMaxMergedSegmentStartTimeDiffMs(long j10);

    SegmentDownloaderFactory setStartPositionUs(long j10);
}
