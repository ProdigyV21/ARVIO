package androidx.media3.exoplayer.source;

import android.os.Handler;
import android.os.Looper;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseMediaSource implements MediaSource {
    private Looper looper;
    private PlayerId playerId;
    private Timeline timeline;
    private final ArrayList<MediaSource.MediaSourceCaller> mediaSourceCallers = new ArrayList<>(1);
    private final HashSet<MediaSource.MediaSourceCaller> enabledMediaSourceCallers = new HashSet<>(1);
    private final MediaSourceEventListener.EventDispatcher eventDispatcher = new MediaSourceEventListener.EventDispatcher();
    private final DrmSessionEventListener.EventDispatcher drmEventDispatcher = new DrmSessionEventListener.EventDispatcher();

    @Override // androidx.media3.exoplayer.source.MediaSource
    public final void addDrmEventListener(Handler handler, DrmSessionEventListener drmSessionEventListener) {
        handler.getClass();
        drmSessionEventListener.getClass();
        this.drmEventDispatcher.addEventListener(handler, drmSessionEventListener);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public final void addEventListener(Handler handler, MediaSourceEventListener mediaSourceEventListener) {
        handler.getClass();
        mediaSourceEventListener.getClass();
        this.eventDispatcher.addEventListener(handler, mediaSourceEventListener);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public /* synthetic */ boolean canUpdateMediaItem(MediaItem mediaItem) {
        return g.a(this, mediaItem);
    }

    public final DrmSessionEventListener.EventDispatcher createDrmEventDispatcher(MediaSource.MediaPeriodId mediaPeriodId) {
        return this.drmEventDispatcher.withParameters(0, mediaPeriodId);
    }

    public final MediaSourceEventListener.EventDispatcher createEventDispatcher(MediaSource.MediaPeriodId mediaPeriodId) {
        return this.eventDispatcher.withParameters(0, mediaPeriodId);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public final void disable(MediaSource.MediaSourceCaller mediaSourceCaller) {
        boolean zIsEmpty = this.enabledMediaSourceCallers.isEmpty();
        this.enabledMediaSourceCallers.remove(mediaSourceCaller);
        if (zIsEmpty || !this.enabledMediaSourceCallers.isEmpty()) {
            return;
        }
        disableInternal();
    }

    public void disableInternal() {
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public final void enable(MediaSource.MediaSourceCaller mediaSourceCaller) {
        this.looper.getClass();
        boolean zIsEmpty = this.enabledMediaSourceCallers.isEmpty();
        this.enabledMediaSourceCallers.add(mediaSourceCaller);
        if (zIsEmpty) {
            enableInternal();
        }
    }

    public void enableInternal() {
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public /* synthetic */ Timeline getInitialTimeline() {
        return g.b(this);
    }

    public final PlayerId getPlayerId() {
        PlayerId playerId = this.playerId;
        playerId.getClass();
        return playerId;
    }

    public final boolean isEnabled() {
        return !this.enabledMediaSourceCallers.isEmpty();
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public /* synthetic */ boolean isSingleWindow() {
        return g.c(this);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public final void prepareSource(MediaSource.MediaSourceCaller mediaSourceCaller, TransferListener transferListener) {
        prepareSource(mediaSourceCaller, transferListener, PlayerId.UNSET);
    }

    public final boolean prepareSourceCalled() {
        return !this.mediaSourceCallers.isEmpty();
    }

    public abstract void prepareSourceInternal(TransferListener transferListener);

    public final void refreshSourceInfo(Timeline timeline) {
        this.timeline = timeline;
        Iterator<MediaSource.MediaSourceCaller> it = this.mediaSourceCallers.iterator();
        while (it.hasNext()) {
            it.next().onSourceInfoRefreshed(this, timeline);
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public final void releaseSource(MediaSource.MediaSourceCaller mediaSourceCaller) {
        this.mediaSourceCallers.remove(mediaSourceCaller);
        if (!this.mediaSourceCallers.isEmpty()) {
            disable(mediaSourceCaller);
            return;
        }
        this.looper = null;
        this.timeline = null;
        this.playerId = null;
        this.enabledMediaSourceCallers.clear();
        releaseSourceInternal();
    }

    public abstract void releaseSourceInternal();

    @Override // androidx.media3.exoplayer.source.MediaSource
    public final void removeDrmEventListener(DrmSessionEventListener drmSessionEventListener) {
        this.drmEventDispatcher.removeEventListener(drmSessionEventListener);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public final void removeEventListener(MediaSourceEventListener mediaSourceEventListener) {
        this.eventDispatcher.removeEventListener(mediaSourceEventListener);
    }

    public final void setPlayerId(PlayerId playerId) {
        this.playerId = playerId;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public /* synthetic */ void updateMediaItem(MediaItem mediaItem) {
        g.e(this, mediaItem);
    }

    public final DrmSessionEventListener.EventDispatcher createDrmEventDispatcher(int i10, MediaSource.MediaPeriodId mediaPeriodId) {
        return this.drmEventDispatcher.withParameters(i10, mediaPeriodId);
    }

    public final MediaSourceEventListener.EventDispatcher createEventDispatcher(int i10, MediaSource.MediaPeriodId mediaPeriodId) {
        return this.eventDispatcher.withParameters(i10, mediaPeriodId);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public final void prepareSource(MediaSource.MediaSourceCaller mediaSourceCaller, TransferListener transferListener, PlayerId playerId) {
        Looper looperMyLooper = Looper.myLooper();
        Looper looper = this.looper;
        ac.b.j(looper == null || looper == looperMyLooper);
        this.playerId = playerId;
        Timeline timeline = this.timeline;
        this.mediaSourceCallers.add(mediaSourceCaller);
        if (this.looper == null) {
            this.looper = looperMyLooper;
            this.enabledMediaSourceCallers.add(mediaSourceCaller);
            prepareSourceInternal(transferListener);
        } else if (timeline != null) {
            enable(mediaSourceCaller);
            mediaSourceCaller.onSourceInfoRefreshed(this, timeline);
        }
    }

    @Deprecated
    public final MediaSourceEventListener.EventDispatcher createEventDispatcher(int i10, MediaSource.MediaPeriodId mediaPeriodId, long j10) {
        return this.eventDispatcher.withParameters(i10, mediaPeriodId);
    }

    @Deprecated
    public final MediaSourceEventListener.EventDispatcher createEventDispatcher(MediaSource.MediaPeriodId mediaPeriodId, long j10) {
        mediaPeriodId.getClass();
        return this.eventDispatcher.withParameters(0, mediaPeriodId);
    }
}
