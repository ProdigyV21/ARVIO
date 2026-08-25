package androidx.media3.exoplayer.source.ads;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import androidx.activity.n;
import androidx.media3.common.AdPlaybackState;
import androidx.media3.common.AdViewProvider;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.source.CompositeMediaSource;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MaskingMediaPeriod;
import androidx.media3.exoplayer.source.MaskingMediaSource;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import androidx.media3.exoplayer.source.ads.AdsLoader;
import androidx.media3.exoplayer.upstream.Allocator;
import j$.util.Objects;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: loaded from: classes3.dex */
public final class AdsMediaSource extends CompositeMediaSource<MediaSource.MediaPeriodId> {
    private static final MediaSource.MediaPeriodId CHILD_SOURCE_MEDIA_PERIOD_ID = new MediaSource.MediaPeriodId(new Object());
    private final MediaSource.Factory adMediaSourceFactory;
    private AdMediaSourceHolder[][] adMediaSourceHolders;
    private AdPlaybackState adPlaybackState;
    private final DataSpec adTagDataSpec;
    private final AdViewProvider adViewProvider;
    private final Object adsId;
    private final AdsLoader adsLoader;
    private ComponentListener componentListener;
    final MediaItem.DrmConfiguration contentDrmConfiguration;
    private final MaskingMediaSource contentMediaSource;
    private Timeline contentTimeline;
    private final Handler mainHandler;
    private final Timeline.Period period;
    private Handler playerHandler;
    private final boolean useLazyContentSourcePreparation;

    public static final class AdLoadException extends IOException {
        public static final int TYPE_AD = 0;
        public static final int TYPE_AD_GROUP = 1;
        public static final int TYPE_ALL_ADS = 2;
        public static final int TYPE_UNEXPECTED = 3;
        public final int type;

        @Target({ElementType.TYPE_USE})
        @Documented
        @Retention(RetentionPolicy.SOURCE)
        public @interface Type {
        }

        private AdLoadException(int i10, Exception exc) {
            super(exc);
            this.type = i10;
        }

        public static AdLoadException createForAd(Exception exc) {
            return new AdLoadException(0, exc);
        }

        public static AdLoadException createForAdGroup(Exception exc, int i10) {
            return new AdLoadException(1, new IOException(a0.c.i(i10, "Failed to load ad group "), exc));
        }

        public static AdLoadException createForAllAds(Exception exc) {
            return new AdLoadException(2, exc);
        }

        public static AdLoadException createForUnexpected(RuntimeException runtimeException) {
            return new AdLoadException(3, runtimeException);
        }

        public RuntimeException getRuntimeExceptionForUnexpected() {
            ac.b.s(this.type == 3);
            Throwable cause = getCause();
            cause.getClass();
            return (RuntimeException) cause;
        }
    }

    public final class AdMediaSourceHolder {
        private final List<MaskingMediaPeriod> activeMediaPeriods = new ArrayList();
        private MediaItem adMediaItem;
        private MediaSource adMediaSource;
        private final MediaSource.MediaPeriodId id;
        private Timeline timeline;

        public AdMediaSourceHolder(MediaSource.MediaPeriodId mediaPeriodId) {
            this.id = mediaPeriodId;
        }

        public MediaPeriod createMediaPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j10) {
            MaskingMediaPeriod maskingMediaPeriod = new MaskingMediaPeriod(mediaPeriodId, allocator, j10);
            this.activeMediaPeriods.add(maskingMediaPeriod);
            MediaSource mediaSource = this.adMediaSource;
            if (mediaSource != null) {
                maskingMediaPeriod.setMediaSource(mediaSource);
                AdsMediaSource adsMediaSource = AdsMediaSource.this;
                MediaItem mediaItem = this.adMediaItem;
                mediaItem.getClass();
                maskingMediaPeriod.setPrepareListener(adsMediaSource.new AdPrepareListener(mediaItem));
            }
            Timeline timeline = this.timeline;
            if (timeline != null) {
                maskingMediaPeriod.createPeriod(new MediaSource.MediaPeriodId(timeline.getUidOfPeriod(0), mediaPeriodId.windowSequenceNumber));
            }
            return maskingMediaPeriod;
        }

        public long getDurationUs() {
            Timeline timeline = this.timeline;
            return timeline == null ? C.TIME_UNSET : timeline.getPeriod(0, AdsMediaSource.this.period).getDurationUs();
        }

        public void handleSourceInfoRefresh(Timeline timeline) {
            ac.b.j(timeline.getPeriodCount() == 1);
            if (this.timeline == null) {
                Object uidOfPeriod = timeline.getUidOfPeriod(0);
                for (int i10 = 0; i10 < this.activeMediaPeriods.size(); i10++) {
                    MaskingMediaPeriod maskingMediaPeriod = this.activeMediaPeriods.get(i10);
                    maskingMediaPeriod.createPeriod(new MediaSource.MediaPeriodId(uidOfPeriod, maskingMediaPeriod.id.windowSequenceNumber));
                }
            }
            this.timeline = timeline;
        }

        public boolean hasMediaSource() {
            return this.adMediaSource != null;
        }

        public void initializeWithMediaSource(MediaSource mediaSource, MediaItem mediaItem) {
            this.adMediaSource = mediaSource;
            this.adMediaItem = mediaItem;
            for (int i10 = 0; i10 < this.activeMediaPeriods.size(); i10++) {
                MaskingMediaPeriod maskingMediaPeriod = this.activeMediaPeriods.get(i10);
                maskingMediaPeriod.setMediaSource(mediaSource);
                maskingMediaPeriod.setPrepareListener(AdsMediaSource.this.new AdPrepareListener(mediaItem));
            }
            AdsMediaSource.this.prepareChildSource(this.id, mediaSource);
        }

        public boolean isInactive() {
            return this.activeMediaPeriods.isEmpty();
        }

        public void release() {
            if (hasMediaSource()) {
                AdsMediaSource.this.releaseChildSource(this.id);
            }
        }

        public void releaseMediaPeriod(MaskingMediaPeriod maskingMediaPeriod) {
            this.activeMediaPeriods.remove(maskingMediaPeriod);
            maskingMediaPeriod.releasePeriod();
        }
    }

    public final class AdPrepareListener implements MaskingMediaPeriod.PrepareListener {
        private final MediaItem adMediaItem;

        public AdPrepareListener(MediaItem mediaItem) {
            this.adMediaItem = mediaItem;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onPrepareComplete$0(MediaSource.MediaPeriodId mediaPeriodId) {
            AdsMediaSource.this.adsLoader.handlePrepareComplete(AdsMediaSource.this, mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onPrepareError$1(MediaSource.MediaPeriodId mediaPeriodId, IOException iOException) {
            AdsMediaSource.this.adsLoader.handlePrepareError(AdsMediaSource.this, mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup, iOException);
        }

        @Override // androidx.media3.exoplayer.source.MaskingMediaPeriod.PrepareListener
        public void onPrepareComplete(MediaSource.MediaPeriodId mediaPeriodId) {
            AdsMediaSource.this.mainHandler.post(new e(this, mediaPeriodId, 0));
        }

        @Override // androidx.media3.exoplayer.source.MaskingMediaPeriod.PrepareListener
        public void onPrepareError(MediaSource.MediaPeriodId mediaPeriodId, IOException iOException) {
            MediaSourceEventListener.EventDispatcher eventDispatcherCreateEventDispatcher = AdsMediaSource.this.createEventDispatcher(mediaPeriodId);
            long newId = LoadEventInfo.getNewId();
            MediaItem.LocalConfiguration localConfiguration = this.adMediaItem.localConfiguration;
            localConfiguration.getClass();
            eventDispatcherCreateEventDispatcher.loadError(new LoadEventInfo(newId, new DataSpec(localConfiguration.uri), SystemClock.elapsedRealtime()), 6, (IOException) AdLoadException.createForAd(iOException), true);
            AdsMediaSource.this.mainHandler.post(new d(this, mediaPeriodId, iOException, 0));
        }
    }

    public final class ComponentListener implements AdsLoader.EventListener {
        private final Handler playerHandler;
        private volatile boolean stopped;

        public ComponentListener(Handler handler) {
            this.playerHandler = handler;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onAdPlaybackState$0(AdPlaybackState adPlaybackState) {
            if (this.stopped) {
                return;
            }
            AdsMediaSource.this.onAdPlaybackState(adPlaybackState);
        }

        @Override // androidx.media3.exoplayer.source.ads.AdsLoader.EventListener
        public final /* synthetic */ void onAdClicked() {
            b.a(this);
        }

        @Override // androidx.media3.exoplayer.source.ads.AdsLoader.EventListener
        public void onAdLoadError(AdLoadException adLoadException, DataSpec dataSpec) {
            if (this.stopped) {
                return;
            }
            AdsMediaSource.this.createEventDispatcher(null).loadError(new LoadEventInfo(LoadEventInfo.getNewId(), dataSpec, SystemClock.elapsedRealtime()), 6, (IOException) adLoadException, true);
        }

        @Override // androidx.media3.exoplayer.source.ads.AdsLoader.EventListener
        public void onAdPlaybackState(AdPlaybackState adPlaybackState) {
            if (this.stopped) {
                return;
            }
            this.playerHandler.post(new e(this, adPlaybackState, 1));
        }

        @Override // androidx.media3.exoplayer.source.ads.AdsLoader.EventListener
        public final /* synthetic */ void onAdTapped() {
            b.d(this);
        }

        public void stop() {
            this.stopped = true;
            this.playerHandler.removeCallbacksAndMessages(null);
        }
    }

    public AdsMediaSource(MediaSource mediaSource, DataSpec dataSpec, Object obj, MediaSource.Factory factory, AdsLoader adsLoader, AdViewProvider adViewProvider) {
        this(mediaSource, dataSpec, obj, factory, adsLoader, adViewProvider, true);
    }

    private static int checkValidAdPlaybackStateUpdate(AdPlaybackState adPlaybackState, AdPlaybackState adPlaybackState2) {
        ac.b.s(adPlaybackState.endsWithLivePostrollPlaceHolder() == adPlaybackState2.endsWithLivePostrollPlaceHolder());
        int i10 = adPlaybackState2.adGroupCount - adPlaybackState.adGroupCount;
        ac.b.s(i10 >= 0);
        int i11 = adPlaybackState2.removedAdGroupCount;
        while (i11 < adPlaybackState.adGroupCount) {
            AdPlaybackState.AdGroup adGroup = adPlaybackState.getAdGroup(i11);
            if (adGroup.isLivePostrollPlaceholder()) {
                ac.b.s(i11 == adPlaybackState.adGroupCount - 1);
                return i10;
            }
            AdPlaybackState.AdGroup adGroup2 = adPlaybackState2.getAdGroup(i11);
            ac.b.s(adGroup.count <= adGroup2.count);
            ac.b.s(adGroup.timeUs == adGroup2.timeUs);
            for (int i12 = 0; i12 < adGroup.count; i12++) {
                MediaItem mediaItem = adGroup.mediaItems[i12];
                if (mediaItem != null) {
                    ac.b.s(mediaItem.equals(adGroup2.mediaItems[i12]));
                }
            }
            i11++;
        }
        return i10;
    }

    @RequiresNonNull({"adPlaybackState"})
    private long[][] getAdDurationsUs() {
        AdPlaybackState adPlaybackState = this.adPlaybackState;
        adPlaybackState.getClass();
        boolean zEndsWithLivePostrollPlaceHolder = adPlaybackState.endsWithLivePostrollPlaceHolder();
        int length = this.adMediaSourceHolders.length + (zEndsWithLivePostrollPlaceHolder ? 1 : 0);
        long[][] jArr = new long[length][];
        int i10 = 0;
        while (true) {
            AdMediaSourceHolder[][] adMediaSourceHolderArr = this.adMediaSourceHolders;
            if (i10 >= adMediaSourceHolderArr.length) {
                break;
            }
            jArr[i10] = new long[adMediaSourceHolderArr[i10].length];
            int i11 = 0;
            while (true) {
                AdMediaSourceHolder[] adMediaSourceHolderArr2 = this.adMediaSourceHolders[i10];
                if (i11 < adMediaSourceHolderArr2.length) {
                    AdMediaSourceHolder adMediaSourceHolder = adMediaSourceHolderArr2[i11];
                    jArr[i10][i11] = adMediaSourceHolder == null ? C.TIME_UNSET : adMediaSourceHolder.getDurationUs();
                    i11++;
                }
            }
            i10++;
        }
        if (zEndsWithLivePostrollPlaceHolder) {
            jArr[length - 1] = new long[0];
        }
        return jArr;
    }

    private static MediaItem.AdsConfiguration getAdsConfiguration(MediaItem mediaItem) {
        MediaItem.LocalConfiguration localConfiguration = mediaItem.localConfiguration;
        if (localConfiguration == null) {
            return null;
        }
        return localConfiguration.adsConfiguration;
    }

    private static AdMediaSourceHolder[][] growAdMediaSourceHolderGrid(AdMediaSourceHolder[][] adMediaSourceHolderArr, int i10) {
        int length = adMediaSourceHolderArr.length + i10;
        AdMediaSourceHolder[][] adMediaSourceHolderArr2 = new AdMediaSourceHolder[length][];
        System.arraycopy(adMediaSourceHolderArr, 0, adMediaSourceHolderArr2, 0, adMediaSourceHolderArr.length);
        for (int length2 = adMediaSourceHolderArr.length; length2 < length; length2++) {
            adMediaSourceHolderArr2[length2] = new AdMediaSourceHolder[0];
        }
        return adMediaSourceHolderArr2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$onChildSourceInfoRefreshed$2(Timeline timeline) {
        boolean zHandleContentTimelineChanged = this.adsLoader.handleContentTimelineChanged(this, timeline);
        ac.b.s((zHandleContentTimelineChanged && this.useLazyContentSourcePreparation) ? false : true);
        if (zHandleContentTimelineChanged || this.useLazyContentSourcePreparation) {
            return;
        }
        Handler handler = this.playerHandler;
        handler.getClass();
        handler.post(new n(this, 10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareSourceInternal$0(ComponentListener componentListener) {
        this.adsLoader.start(this, this.adTagDataSpec, this.adsId, this.adViewProvider, componentListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$releaseSourceInternal$1(ComponentListener componentListener) {
        this.adsLoader.stop(this, componentListener);
    }

    private void maybeUpdateAdMediaSources() {
        MediaItem mediaItemBuild;
        AdPlaybackState adPlaybackState = this.adPlaybackState;
        if (adPlaybackState == null) {
            return;
        }
        for (int i10 = 0; i10 < this.adMediaSourceHolders.length; i10++) {
            int i11 = 0;
            while (true) {
                AdMediaSourceHolder[] adMediaSourceHolderArr = this.adMediaSourceHolders[i10];
                if (i11 < adMediaSourceHolderArr.length) {
                    AdMediaSourceHolder adMediaSourceHolder = adMediaSourceHolderArr[i11];
                    AdPlaybackState.AdGroup adGroup = adPlaybackState.getAdGroup(i10);
                    if (adMediaSourceHolder != null && !adMediaSourceHolder.hasMediaSource()) {
                        MediaItem[] mediaItemArr = adGroup.mediaItems;
                        if (i11 < mediaItemArr.length && (mediaItemBuild = mediaItemArr[i11]) != null) {
                            if (this.contentDrmConfiguration != null) {
                                mediaItemBuild = mediaItemBuild.buildUpon().setDrmConfiguration(this.contentDrmConfiguration).build();
                            }
                            adMediaSourceHolder.initializeWithMediaSource(this.adMediaSourceFactory.createMediaSource(mediaItemBuild), mediaItemBuild);
                        }
                    }
                    i11++;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeUpdateSourceInfo() {
        Timeline timeline = this.contentTimeline;
        AdPlaybackState adPlaybackState = this.adPlaybackState;
        if (adPlaybackState == null || timeline == null) {
            return;
        }
        if (adPlaybackState.adGroupCount == 0) {
            refreshSourceInfo(timeline);
        } else {
            this.adPlaybackState = adPlaybackState.withAdDurationsUs(getAdDurationsUs());
            refreshSourceInfo(new SinglePeriodAdTimeline(timeline, this.adPlaybackState));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAdPlaybackState(AdPlaybackState adPlaybackState) {
        AdPlaybackState adPlaybackState2 = this.adPlaybackState;
        if (adPlaybackState2 == null) {
            AdMediaSourceHolder[][] adMediaSourceHolderArr = new AdMediaSourceHolder[adPlaybackState.adGroupCount - (adPlaybackState.endsWithLivePostrollPlaceHolder() ? 1 : 0)][];
            this.adMediaSourceHolders = adMediaSourceHolderArr;
            Arrays.fill(adMediaSourceHolderArr, new AdMediaSourceHolder[0]);
        } else {
            int iCheckValidAdPlaybackStateUpdate = checkValidAdPlaybackStateUpdate(adPlaybackState2, adPlaybackState);
            if (iCheckValidAdPlaybackStateUpdate > 0) {
                this.adMediaSourceHolders = growAdMediaSourceHolderGrid(this.adMediaSourceHolders, iCheckValidAdPlaybackStateUpdate);
            }
        }
        this.adPlaybackState = adPlaybackState;
        maybeUpdateAdMediaSources();
        maybeUpdateSourceInfo();
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource, androidx.media3.exoplayer.source.MediaSource
    public boolean canUpdateMediaItem(MediaItem mediaItem) {
        return Objects.equals(getAdsConfiguration(getMediaItem()), getAdsConfiguration(mediaItem)) && this.contentMediaSource.canUpdateMediaItem(mediaItem);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j10) {
        AdPlaybackState adPlaybackState = this.adPlaybackState;
        adPlaybackState.getClass();
        if (adPlaybackState.adGroupCount <= 0 || !mediaPeriodId.isAd()) {
            MaskingMediaPeriod maskingMediaPeriod = new MaskingMediaPeriod(mediaPeriodId, allocator, j10);
            maskingMediaPeriod.setMediaSource(this.contentMediaSource);
            maskingMediaPeriod.createPeriod(mediaPeriodId);
            return maskingMediaPeriod;
        }
        int i10 = mediaPeriodId.adGroupIndex;
        int i11 = mediaPeriodId.adIndexInAdGroup;
        AdMediaSourceHolder[][] adMediaSourceHolderArr = this.adMediaSourceHolders;
        AdMediaSourceHolder[] adMediaSourceHolderArr2 = adMediaSourceHolderArr[i10];
        if (adMediaSourceHolderArr2.length <= i11) {
            adMediaSourceHolderArr[i10] = (AdMediaSourceHolder[]) Arrays.copyOf(adMediaSourceHolderArr2, i11 + 1);
        }
        AdMediaSourceHolder adMediaSourceHolder = this.adMediaSourceHolders[i10][i11];
        if (adMediaSourceHolder == null) {
            adMediaSourceHolder = new AdMediaSourceHolder(mediaPeriodId);
            this.adMediaSourceHolders[i10][i11] = adMediaSourceHolder;
            maybeUpdateAdMediaSources();
        }
        return adMediaSourceHolder.createMediaPeriod(mediaPeriodId, allocator, j10);
    }

    public Object getAdsId() {
        return this.adsId;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public MediaItem getMediaItem() {
        return this.contentMediaSource.getMediaItem();
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource, androidx.media3.exoplayer.source.BaseMediaSource
    public void prepareSourceInternal(TransferListener transferListener) {
        super.prepareSourceInternal(transferListener);
        Handler handlerCreateHandlerForCurrentLooper = Util.createHandlerForCurrentLooper();
        this.playerHandler = handlerCreateHandlerForCurrentLooper;
        ComponentListener componentListener = new ComponentListener(handlerCreateHandlerForCurrentLooper);
        this.componentListener = componentListener;
        this.contentTimeline = this.contentMediaSource.getTimeline();
        prepareChildSource(CHILD_SOURCE_MEDIA_PERIOD_ID, this.contentMediaSource);
        this.mainHandler.post(new c(this, componentListener, 0));
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        MaskingMediaPeriod maskingMediaPeriod = (MaskingMediaPeriod) mediaPeriod;
        MediaSource.MediaPeriodId mediaPeriodId = maskingMediaPeriod.id;
        if (!mediaPeriodId.isAd()) {
            maskingMediaPeriod.releasePeriod();
            return;
        }
        AdMediaSourceHolder adMediaSourceHolder = this.adMediaSourceHolders[mediaPeriodId.adGroupIndex][mediaPeriodId.adIndexInAdGroup];
        adMediaSourceHolder.getClass();
        adMediaSourceHolder.releaseMediaPeriod(maskingMediaPeriod);
        if (adMediaSourceHolder.isInactive()) {
            adMediaSourceHolder.release();
            this.adMediaSourceHolders[mediaPeriodId.adGroupIndex][mediaPeriodId.adIndexInAdGroup] = null;
        }
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource, androidx.media3.exoplayer.source.BaseMediaSource
    public void releaseSourceInternal() {
        super.releaseSourceInternal();
        ComponentListener componentListener = this.componentListener;
        componentListener.getClass();
        this.componentListener = null;
        this.playerHandler = null;
        componentListener.stop();
        this.contentTimeline = null;
        this.adPlaybackState = null;
        this.adMediaSourceHolders = new AdMediaSourceHolder[0][];
        this.mainHandler.post(new c(this, componentListener, 1));
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource, androidx.media3.exoplayer.source.MediaSource
    public void updateMediaItem(MediaItem mediaItem) {
        this.contentMediaSource.updateMediaItem(mediaItem);
    }

    public AdsMediaSource(MediaSource mediaSource, DataSpec dataSpec, Object obj, MediaSource.Factory factory, AdsLoader adsLoader, AdViewProvider adViewProvider, boolean z) {
        this.useLazyContentSourcePreparation = z;
        this.contentMediaSource = new MaskingMediaSource(mediaSource, z);
        MediaItem.LocalConfiguration localConfiguration = mediaSource.getMediaItem().localConfiguration;
        localConfiguration.getClass();
        this.contentDrmConfiguration = localConfiguration.drmConfiguration;
        this.adMediaSourceFactory = factory;
        this.adsLoader = adsLoader;
        this.adViewProvider = adViewProvider;
        this.adTagDataSpec = dataSpec;
        this.adsId = obj;
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.period = new Timeline.Period();
        this.adMediaSourceHolders = new AdMediaSourceHolder[0][];
        adsLoader.setSupportedContentTypes(factory.getSupportedTypes());
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource
    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(MediaSource.MediaPeriodId mediaPeriodId, MediaSource.MediaPeriodId mediaPeriodId2) {
        return mediaPeriodId.isAd() ? mediaPeriodId : mediaPeriodId2;
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource
    public void lambda$prepareChildSource$0(MediaSource.MediaPeriodId mediaPeriodId, MediaSource mediaSource, Timeline timeline) {
        if (mediaPeriodId.isAd()) {
            AdMediaSourceHolder adMediaSourceHolder = this.adMediaSourceHolders[mediaPeriodId.adGroupIndex][mediaPeriodId.adIndexInAdGroup];
            adMediaSourceHolder.getClass();
            adMediaSourceHolder.handleSourceInfoRefresh(timeline);
            maybeUpdateSourceInfo();
            return;
        }
        ac.b.j(timeline.getPeriodCount() == 1);
        this.contentTimeline = timeline;
        this.mainHandler.post(new e(this, timeline, 2));
        if (this.useLazyContentSourcePreparation) {
            maybeUpdateSourceInfo();
        }
    }
}
