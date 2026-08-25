package androidx.media3.exoplayer;

import android.content.Context;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Clock;
import androidx.media3.exoplayer.MetadataRetrieverInternal;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.extractor.DefaultExtractorsFactory;
import com.google.common.util.concurrent.d1;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public final class MetadataRetriever implements AutoCloseable {
    public static final int DEFAULT_MAXIMUM_PARALLEL_RETRIEVALS = 5;
    private final MetadataRetrieverInternal internalRetriever;

    public static final class Builder {
        private Clock clock;
        private final Context context;
        private final MediaItem mediaItem;
        private MediaSource.Factory mediaSourceFactory;

        public Builder(Context context, MediaItem mediaItem) {
            this.context = context != null ? context.getApplicationContext() : null;
            mediaItem.getClass();
            this.mediaItem = mediaItem;
            this.clock = Clock.DEFAULT;
        }

        public MetadataRetriever build() {
            if (this.mediaSourceFactory == null) {
                ac.b.t(this.context != null, "Context must be provided if MediaSource.Factory is not set.");
                this.mediaSourceFactory = new DefaultMediaSourceFactory(this.context, new DefaultExtractorsFactory().setMp4ExtractorFlags(260));
            }
            MediaItem mediaItem = this.mediaItem;
            MediaSource.Factory factory = this.mediaSourceFactory;
            factory.getClass();
            return new MetadataRetriever(new MetadataRetrieverInternal(mediaItem, factory, this.clock));
        }

        public Builder setClock(Clock clock) {
            clock.getClass();
            this.clock = clock;
            return this;
        }

        public Builder setMediaSourceFactory(MediaSource.Factory factory) {
            factory.getClass();
            this.mediaSourceFactory = factory;
            return this;
        }
    }

    @Deprecated
    public static d1<TrackGroupArray> retrieveMetadata(Context context, MediaItem mediaItem) {
        return retrieveMetadata(context, mediaItem, Clock.DEFAULT);
    }

    public static void setMaximumParallelRetrievals(int i10) {
        ac.b.j(i10 >= 1);
        MetadataRetrieverInternal.SharedWorkerThread.MAX_PARALLEL_RETRIEVALS.set(i10);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        this.internalRetriever.close();
    }

    public d1<Long> retrieveDurationUs() {
        return this.internalRetriever.retrieveDurationUs();
    }

    public d1<Timeline> retrieveTimeline() {
        return this.internalRetriever.retrieveTimeline();
    }

    public d1<TrackGroupArray> retrieveTrackGroups() {
        return this.internalRetriever.retrieveTrackGroups();
    }

    private MetadataRetriever(MetadataRetrieverInternal metadataRetrieverInternal) {
        this.internalRetriever = metadataRetrieverInternal;
    }

    @Deprecated
    public static d1<TrackGroupArray> retrieveMetadata(MediaSource.Factory factory, MediaItem mediaItem) {
        return retrieveMetadata(factory, mediaItem, Clock.DEFAULT);
    }

    @Deprecated
    public static d1<TrackGroupArray> retrieveMetadata(Context context, MediaItem mediaItem, Clock clock) {
        MetadataRetriever metadataRetrieverBuild = new Builder(context, mediaItem).setClock(clock).build();
        try {
            d1<TrackGroupArray> d1VarRetrieveTrackGroups = metadataRetrieverBuild.retrieveTrackGroups();
            metadataRetrieverBuild.close();
            return d1VarRetrieveTrackGroups;
        } catch (Throwable th) {
            if (metadataRetrieverBuild != null) {
                try {
                    metadataRetrieverBuild.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @Deprecated
    private static d1<TrackGroupArray> retrieveMetadata(MediaSource.Factory factory, MediaItem mediaItem, Clock clock) {
        MetadataRetriever metadataRetrieverBuild = new Builder(null, mediaItem).setMediaSourceFactory(factory).setClock(clock).build();
        try {
            d1<TrackGroupArray> d1VarRetrieveTrackGroups = metadataRetrieverBuild.retrieveTrackGroups();
            metadataRetrieverBuild.close();
            return d1VarRetrieveTrackGroups;
        } catch (Throwable th) {
            if (metadataRetrieverBuild != null) {
                try {
                    metadataRetrieverBuild.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }
}
