package androidx.media3.exoplayer.source.chunk;

import android.net.Uri;
import androidx.media3.common.Format;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.StatsDataSource;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.upstream.Loader;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public abstract class Chunk implements Loader.Loadable {
    protected final StatsDataSource dataSource;
    public final DataSpec dataSpec;
    public final long endTimeUs;
    public final long loadTaskId;
    public final long startTimeUs;
    public final Format trackFormat;
    public final Object trackSelectionData;
    public final int trackSelectionReason;
    public final int type;

    public Chunk(DataSource dataSource, DataSpec dataSpec, int i10, Format format, int i11, Object obj, long j10, long j11) {
        this.dataSource = new StatsDataSource(dataSource);
        dataSpec.getClass();
        this.dataSpec = dataSpec;
        this.type = i10;
        this.trackFormat = format;
        this.trackSelectionReason = i11;
        this.trackSelectionData = obj;
        this.startTimeUs = j10;
        this.endTimeUs = j11;
        this.loadTaskId = LoadEventInfo.getNewId();
    }

    public final long bytesLoaded() {
        return this.dataSource.getBytesRead();
    }

    public final long getDurationUs() {
        return this.endTimeUs - this.startTimeUs;
    }

    public final Map<String, List<String>> getResponseHeaders() {
        return this.dataSource.getLastResponseHeaders();
    }

    public final Uri getUri() {
        return this.dataSource.getLastOpenedUri();
    }
}
