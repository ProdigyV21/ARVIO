package androidx.media3.exoplayer.source;

import android.net.Uri;
import androidx.media3.datasource.DataSpec;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes3.dex */
public final class LoadEventInfo {
    private static final AtomicLong idSource = new AtomicLong();
    public final long bytesLoaded;
    public final DataSpec dataSpec;
    public final long elapsedRealtimeMs;
    public final long loadDurationMs;
    public final long loadTaskId;
    public final Map<String, List<String>> responseHeaders;
    public final Uri uri;

    public LoadEventInfo(long j10, DataSpec dataSpec, long j11) {
        this(j10, dataSpec, dataSpec.uri, Collections.EMPTY_MAP, j11, 0L, 0L);
    }

    public static long getNewId() {
        return idSource.getAndIncrement();
    }

    public LoadEventInfo copyWithTaskIdAndDurationMs(long j10, long j11) {
        return new LoadEventInfo(j10, this.dataSpec, this.uri, this.responseHeaders, this.elapsedRealtimeMs, j11, this.bytesLoaded);
    }

    public LoadEventInfo(long j10, DataSpec dataSpec, Uri uri, Map<String, List<String>> map, long j11, long j12, long j13) {
        this.loadTaskId = j10;
        this.dataSpec = dataSpec;
        this.uri = uri;
        this.responseHeaders = map;
        this.elapsedRealtimeMs = j11;
        this.loadDurationMs = j12;
        this.bytesLoaded = j13;
    }
}
