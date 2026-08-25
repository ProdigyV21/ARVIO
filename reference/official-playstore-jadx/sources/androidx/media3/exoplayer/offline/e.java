package androidx.media3.exoplayer.offline;

import androidx.media3.exoplayer.offline.DownloadManager;
import java.util.Comparator;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class e implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return DownloadManager.InternalHandler.compareStartTimes((Download) obj, (Download) obj2);
    }
}
