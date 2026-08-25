package androidx.media3.exoplayer.hls;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.util.LongSparseArray;
import androidx.media3.common.AdPlaybackState;
import androidx.media3.common.AdViewProvider;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.C;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.DefaultDataSource;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.PlayerMessage;
import androidx.media3.exoplayer.drm.DrmSessionManagerProvider;
import androidx.media3.exoplayer.hls.HlsInterstitialsAdsLoader;
import androidx.media3.exoplayer.hls.HlsMediaSource;
import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.ads.AdsLoader;
import androidx.media3.exoplayer.source.ads.AdsMediaSource;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.exoplayer.upstream.Loader;
import androidx.media3.exoplayer.upstream.ParsingLoadable;
import androidx.media3.extractor.text.SubtitleParser;
import com.google.common.base.c0;
import com.google.common.collect.a0;
import com.google.common.collect.b1;
import com.google.common.collect.f1;
import com.google.common.collect.h1;
import com.google.common.collect.o3;
import j$.util.Objects;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes3.dex */
public final class HlsInterstitialsAdsLoader implements AdsLoader {
    private static final String TAG = "HlsInterstitiaAdsLoader";
    private static final int TARGET_DURATION_MULTIPLIER = 3;
    private final ContentMediaSourceAdDataHolder contentMediaSourceAdDataHolder;
    private final DataSource.Factory dataSourceFactory;
    private boolean isReleased;
    private final List<Listener> listeners;
    private Loader loader;
    private PlayerMessage pendingAssetListResolutionMessage;
    private ExoPlayer player;
    private final PlayerListener playerListener;
    private final Map<Object, AdPlaybackState> resumptionStates;

    public static final class AdsMediaSourceFactory implements MediaSource.Factory {
        private final AdViewProvider adViewProvider;
        private final HlsInterstitialsAdsLoader adsLoader;
        private final MediaSource.Factory mediaSourceFactory;

        public AdsMediaSourceFactory(HlsInterstitialsAdsLoader hlsInterstitialsAdsLoader, AdViewProvider adViewProvider, Context context) {
            this(hlsInterstitialsAdsLoader, context, null, adViewProvider);
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public MediaSource createMediaSource(MediaItem mediaItem) {
            mediaItem.localConfiguration.getClass();
            MediaSource mediaSourceCreateMediaSource = this.mediaSourceFactory.createMediaSource(mediaItem);
            MediaItem.AdsConfiguration adsConfiguration = mediaItem.localConfiguration.adsConfiguration;
            if (adsConfiguration == null) {
                return mediaSourceCreateMediaSource;
            }
            if (!(adsConfiguration.adsId instanceof String)) {
                throw new IllegalArgumentException("Please use an AdsConfiguration with an adsId of type String when using HlsInterstitialsAdsLoader");
            }
            DataSpec dataSpec = new DataSpec(mediaItem.localConfiguration.adsConfiguration.adTagUri);
            Object obj = mediaItem.localConfiguration.adsConfiguration.adsId;
            obj.getClass();
            return new AdsMediaSource(mediaSourceCreateMediaSource, dataSpec, obj, this.mediaSourceFactory, this.adsLoader, this.adViewProvider, false);
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public final /* synthetic */ MediaSource.Factory experimentalParseSubtitlesDuringExtraction(boolean z) {
            return androidx.media3.exoplayer.source.h.a(this, z);
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public final /* synthetic */ MediaSource.Factory experimentalSetCodecsToParseWithinGopSampleDependencies(int i10) {
            return androidx.media3.exoplayer.source.h.b(this, i10);
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public int[] getSupportedTypes() {
            return new int[]{2};
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public final /* synthetic */ MediaSource.Factory setCmcdConfigurationFactory(CmcdConfiguration.Factory factory) {
            return androidx.media3.exoplayer.source.h.c(this, factory);
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public final /* synthetic */ MediaSource.Factory setDownloadExecutor(c0 c0Var) {
            return androidx.media3.exoplayer.source.h.d(this, c0Var);
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public final /* synthetic */ MediaSource.Factory setSubtitleParserFactory(SubtitleParser.Factory factory) {
            return androidx.media3.exoplayer.source.h.e(this, factory);
        }

        public AdsMediaSourceFactory(HlsInterstitialsAdsLoader hlsInterstitialsAdsLoader, AdViewProvider adViewProvider, MediaSource.Factory factory) {
            this(hlsInterstitialsAdsLoader, null, factory, adViewProvider);
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public AdsMediaSourceFactory setDrmSessionManagerProvider(DrmSessionManagerProvider drmSessionManagerProvider) {
            this.mediaSourceFactory.setDrmSessionManagerProvider(drmSessionManagerProvider);
            return this;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public AdsMediaSourceFactory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy) {
            this.mediaSourceFactory.setLoadErrorHandlingPolicy(loadErrorHandlingPolicy);
            return this;
        }

        private AdsMediaSourceFactory(HlsInterstitialsAdsLoader hlsInterstitialsAdsLoader, Context context, MediaSource.Factory factory, AdViewProvider adViewProvider) {
            boolean z = true;
            ac.b.j((context == null && factory == null) ? false : true);
            this.adsLoader = hlsInterstitialsAdsLoader;
            if (factory == null) {
                context.getClass();
                factory = new HlsMediaSource.Factory(new DefaultDataSource.Factory(context));
            }
            this.mediaSourceFactory = factory;
            this.adViewProvider = adViewProvider;
            int[] supportedTypes = factory.getSupportedTypes();
            int length = supportedTypes.length;
            int i10 = 0;
            while (true) {
                if (i10 >= length) {
                    z = false;
                    break;
                } else if (supportedTypes[i10] == 2) {
                    break;
                } else {
                    i10++;
                }
            }
            ac.b.s(z);
        }
    }

    public static class AdsResumptionState {
        private static final String FIELD_ADS_ID = Util.intToStringMaxRadix(0);
        private static final String FIELD_AD_PLAYBACK_STATE = Util.intToStringMaxRadix(1);
        private final AdPlaybackState adPlaybackState;
        public final String adsId;

        public AdsResumptionState(String str, AdPlaybackState adPlaybackState) {
            ac.b.j(str.equals(adPlaybackState.adsId));
            this.adsId = str;
            this.adPlaybackState = adPlaybackState;
        }

        public static AdsResumptionState fromBundle(Bundle bundle) {
            String string = bundle.getString(FIELD_ADS_ID);
            string.getClass();
            Bundle bundle2 = bundle.getBundle(FIELD_AD_PLAYBACK_STATE);
            bundle2.getClass();
            return new AdsResumptionState(string, AdPlaybackState.fromBundle(bundle2).withAdsId(string));
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof AdsResumptionState)) {
                return false;
            }
            AdsResumptionState adsResumptionState = (AdsResumptionState) obj;
            return Objects.equals(this.adsId, adsResumptionState.adsId) && Objects.equals(this.adPlaybackState, adsResumptionState.adPlaybackState);
        }

        public int hashCode() {
            return Objects.hash(this.adsId, this.adPlaybackState);
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putString(FIELD_ADS_ID, this.adsId);
            bundle.putBundle(FIELD_AD_PLAYBACK_STATE, this.adPlaybackState.toBundle());
            return bundle;
        }
    }

    public static final class Asset {
        public final long durationUs;
        public final Uri uri;

        public Asset(Uri uri, long j10) {
            this.uri = uri;
            this.durationUs = j10;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Asset)) {
                return false;
            }
            Asset asset = (Asset) obj;
            return this.durationUs == asset.durationUs && Objects.equals(this.uri, asset.uri);
        }

        public int hashCode() {
            return Objects.hash(this.uri, Long.valueOf(this.durationUs));
        }
    }

    public static final class AssetList {
        static final AssetList EMPTY;
        public final h1 assets;
        public final AdPlaybackState.SkipInfo skipInfo;
        public final h1 stringAttributes;

        static {
            f1 f1Var = h1.f14020l;
            o3 o3Var = o3.f14078o;
            EMPTY = new AssetList(o3Var, o3Var, null);
        }

        public AssetList(h1 h1Var, h1 h1Var2, AdPlaybackState.SkipInfo skipInfo) {
            this.assets = h1Var;
            this.stringAttributes = h1Var2;
            this.skipInfo = skipInfo;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AssetList)) {
                return false;
            }
            AssetList assetList = (AssetList) obj;
            return Objects.equals(this.assets, assetList.assets) && Objects.equals(this.stringAttributes, assetList.stringAttributes) && Objects.equals(this.skipInfo, assetList.skipInfo);
        }

        public int hashCode() {
            return Objects.hash(this.assets, this.stringAttributes, this.skipInfo);
        }
    }

    public static class AssetListData {
        private final int adGroupIndex;
        private final int adIndexInAdGroup;
        private final Object adsId;
        private final HlsMediaPlaylist.Interstitial interstitial;
        private final MediaItem mediaItem;
        private final long targetDurationUs;

        public AssetListData(MediaItem mediaItem, Object obj, HlsMediaPlaylist.Interstitial interstitial, int i10, int i11, long j10) {
            ac.b.j(interstitial.assetListUri != null);
            this.mediaItem = mediaItem;
            this.adsId = obj;
            this.adGroupIndex = i10;
            this.adIndexInAdGroup = i11;
            this.targetDurationUs = j10;
            this.interstitial = interstitial;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof AssetListData)) {
                return false;
            }
            AssetListData assetListData = (AssetListData) obj;
            return this.adGroupIndex == assetListData.adGroupIndex && this.adIndexInAdGroup == assetListData.adIndexInAdGroup && this.targetDurationUs == assetListData.targetDurationUs && Objects.equals(this.mediaItem, assetListData.mediaItem) && Objects.equals(this.adsId, assetListData.adsId) && Objects.equals(this.interstitial, assetListData.interstitial);
        }

        public int hashCode() {
            return (int) ((((long) (((((this.interstitial.hashCode() + ((this.adsId.hashCode() + (this.mediaItem.hashCode() * 31)) * 31)) * 31) + this.adGroupIndex) * 31) + this.adIndexInAdGroup)) * 31) + this.targetDurationUs);
        }
    }

    public static final class ContentMediaSourceAdDataHolder {
        private final Map<Object, AdsLoader.EventListener> activeEventListeners = new HashMap();
        private final Map<Object, AdPlaybackState> activeAdPlaybackStates = new HashMap();
        private final Map<Object, Set<String>> insertedInterstitialIds = new HashMap();
        private final Map<Object, TreeMap<Long, AssetListData>> unresolvedAssetLists = new HashMap();
        private final Set<Object> contentSourceAwaitingFirstAdToStart = new HashSet();
        private final Set<Object> unsupportedAdsIds = new HashSet();

        public void addInsertedInterstitialId(Object obj, String str) {
            Set<String> set = this.insertedInterstitialIds.get(obj);
            if (set != null) {
                set.add(str);
            }
        }

        public void addUnsupportedContentMediaSource(Object obj) {
            this.unsupportedAdsIds.add(obj);
        }

        public boolean awaitingFirstAdToStartFor(Object obj) {
            return this.contentSourceAwaitingFirstAdToStart.contains(obj);
        }

        public AdPlaybackState getAdPlaybackState(Object obj) {
            return this.activeAdPlaybackStates.get(obj);
        }

        public Collection<AdPlaybackState> getAdPlaybackStates() {
            return this.activeAdPlaybackStates.values();
        }

        public AdsLoader.EventListener getEventListener(Object obj) {
            return this.activeEventListeners.get(obj);
        }

        public int getUnresolvedAssetListCount(Object obj) {
            TreeMap<Long, AssetListData> treeMap = this.unresolvedAssetLists.get(obj);
            if (treeMap != null) {
                return treeMap.size();
            }
            return 0;
        }

        public Map<Long, AssetListData> getUnresolvedAssetLists(Object obj) {
            return this.unresolvedAssetLists.get(obj);
        }

        public boolean isIdle() {
            return this.activeEventListeners.isEmpty();
        }

        public boolean isInsertedInterstitialId(Object obj, String str) {
            Set<String> set = this.insertedInterstitialIds.get(obj);
            return set != null && set.contains(str);
        }

        public boolean isManagedContentSource(Object obj) {
            return this.activeAdPlaybackStates.containsKey(obj);
        }

        public boolean isStartedContentMediaSource(Object obj) {
            return this.activeEventListeners.containsKey(obj);
        }

        public boolean isUnsupportedContentMediaSource(Object obj) {
            return this.unsupportedAdsIds.contains(obj);
        }

        public void notifyAdStarted(Object obj) {
            this.contentSourceAwaitingFirstAdToStart.remove(obj);
        }

        public AdPlaybackState putAdPlaybackState(Object obj, AdPlaybackState adPlaybackState) {
            return this.activeAdPlaybackStates.put(obj, adPlaybackState);
        }

        public AdsLoader.EventListener startContentSource(Object obj, AdsLoader.EventListener eventListener) {
            this.insertedInterstitialIds.put(obj, new HashSet());
            this.unresolvedAssetLists.put(obj, new TreeMap<>());
            this.contentSourceAwaitingFirstAdToStart.add(obj);
            return this.activeEventListeners.put(obj, eventListener);
        }

        public AdPlaybackState stopContentSource(Object obj) {
            this.activeEventListeners.remove(obj);
            this.insertedInterstitialIds.remove(obj);
            this.unresolvedAssetLists.remove(obj);
            this.unsupportedAdsIds.remove(obj);
            this.contentSourceAwaitingFirstAdToStart.remove(obj);
            return this.activeAdPlaybackStates.remove(obj);
        }
    }

    public interface Listener {
        void onAdCompleted(MediaItem mediaItem, Object obj, int i10, int i11);

        void onAdSkipped(MediaItem mediaItem, Object obj, int i10, int i11);

        void onAdStarted(MediaItem mediaItem, Object obj, int i10, int i11);

        void onAssetListLoadCompleted(MediaItem mediaItem, Object obj, int i10, int i11, AssetList assetList);

        void onAssetListLoadFailed(MediaItem mediaItem, Object obj, int i10, int i11, IOException iOException, boolean z);

        void onAssetListLoadStarted(MediaItem mediaItem, Object obj, int i10, int i11);

        void onContentTimelineChanged(MediaItem mediaItem, Object obj, Timeline timeline);

        void onMetadata(MediaItem mediaItem, Object obj, int i10, int i11, Metadata metadata);

        void onPrepareCompleted(MediaItem mediaItem, Object obj, int i10, int i11);

        void onPrepareError(MediaItem mediaItem, Object obj, int i10, int i11, IOException iOException);

        void onStart(MediaItem mediaItem, Object obj, AdViewProvider adViewProvider);

        void onStop(MediaItem mediaItem, Object obj, AdPlaybackState adPlaybackState);
    }

    public class LoaderCallback implements Loader.Callback<ParsingLoadable<AssetList>> {
        private final AssetListData assetListData;
        private final Timeline.Window window = new Timeline.Window();

        public LoaderCallback(AssetListData assetListData) {
            this.assetListData = assetListData;
        }

        private void handleAssetResolutionFailed(final IOException iOException, final boolean z) {
            HlsInterstitialsAdsLoader.this.notifyAssetResolutionFailed(this.assetListData.adsId, this.assetListData.adGroupIndex, this.assetListData.adIndexInAdGroup);
            HlsInterstitialsAdsLoader.this.notifyListeners(new Consumer() { // from class: androidx.media3.exoplayer.hls.f
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    this.f3643i.lambda$handleAssetResolutionFailed$2(iOException, z, (HlsInterstitialsAdsLoader.Listener) obj);
                }
            });
            maybeContinueAssetResolution();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$handleAssetResolutionFailed$2(IOException iOException, boolean z, Listener listener) {
            listener.onAssetListLoadFailed(this.assetListData.mediaItem, this.assetListData.adsId, this.assetListData.adGroupIndex, this.assetListData.adIndexInAdGroup, iOException, z);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onLoadCompleted$0(Listener listener) {
            listener.onAssetListLoadFailed(this.assetListData.mediaItem, this.assetListData.adsId, this.assetListData.adGroupIndex, this.assetListData.adIndexInAdGroup, null, true);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onLoadCompleted$1(AssetList assetList, Listener listener) {
            listener.onAssetListLoadCompleted(this.assetListData.mediaItem, this.assetListData.adsId, this.assetListData.adGroupIndex, this.assetListData.adIndexInAdGroup, assetList);
        }

        private void maybeContinueAssetResolution() {
            ExoPlayer exoPlayer = HlsInterstitialsAdsLoader.this.player;
            if (exoPlayer == null || exoPlayer.getPlaybackState() == 1 || !this.assetListData.mediaItem.equals(exoPlayer.getCurrentMediaItem())) {
                return;
            }
            long jMsToUs = Util.msToUs(exoPlayer.getContentPosition());
            Timeline currentTimeline = exoPlayer.getCurrentTimeline();
            int currentMediaItemIndex = exoPlayer.getCurrentMediaItemIndex();
            HlsInterstitialsAdsLoader.this.maybeExecuteOrSetNextAssetListResolutionMessage(this.assetListData.adsId, currentTimeline, currentMediaItemIndex, currentTimeline.getWindow(currentMediaItemIndex, this.window).positionInFirstPeriodUs, jMsToUs);
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Callback
        public final /* synthetic */ void onLoadStarted(Loader.Loadable loadable, long j10, long j11, int i10) {
            androidx.media3.exoplayer.upstream.h.a(this, loadable, j10, j11, i10);
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Callback
        public void onLoadCanceled(ParsingLoadable<AssetList> parsingLoadable, long j10, long j11, boolean z) {
            handleAssetResolutionFailed(null, true);
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Callback
        public void onLoadCompleted(ParsingLoadable<AssetList> parsingLoadable, long j10, long j11) {
            AssetList result = parsingLoadable.getResult();
            AdPlaybackState adPlaybackState = HlsInterstitialsAdsLoader.this.contentMediaSourceAdDataHolder.getAdPlaybackState(this.assetListData.adsId);
            if ((adPlaybackState != null ? adPlaybackState.getAdGroup(this.assetListData.adGroupIndex).states[this.assetListData.adIndexInAdGroup] : 4) != 0) {
                maybeContinueAssetResolution();
                HlsInterstitialsAdsLoader.this.notifyListeners(new d(this, 2));
                return;
            }
            int i10 = 0;
            if (result == null || result.assets.isEmpty()) {
                handleAssetResolutionFailed(new IOException("empty asset list"), false);
                return;
            }
            adPlaybackState.getClass();
            AdPlaybackState.AdGroup adGroup = adPlaybackState.getAdGroup(this.assetListData.adGroupIndex);
            long j12 = adGroup.durationsUs[this.assetListData.adIndexInAdGroup];
            long j13 = C.TIME_UNSET;
            long j14 = 0;
            long j15 = j12 != C.TIME_UNSET ? adGroup.durationsUs[this.assetListData.adIndexInAdGroup] : 0L;
            int i11 = adGroup.count;
            if (result.assets.size() > 1) {
                adPlaybackState = adPlaybackState.withAdCount(this.assetListData.adGroupIndex, (result.assets.size() + i11) - 1);
                adGroup = adPlaybackState.getAdGroup(this.assetListData.adGroupIndex);
            }
            int i12 = this.assetListData.adIndexInAdGroup;
            long[] jArr = (long[]) adGroup.durationsUs.clone();
            while (i10 < result.assets.size()) {
                Asset asset = (Asset) result.assets.get(i10);
                if (i10 > 0) {
                    i12 = (i11 + i10) - 1;
                }
                long j16 = j13;
                long j17 = asset.durationUs;
                jArr[i12] = j17;
                j14 += j17;
                adPlaybackState = adPlaybackState.withAvailableAdMediaItem(this.assetListData.adGroupIndex, i12, new MediaItem.Builder().setUri(asset.uri).setMimeType(MimeTypes.APPLICATION_M3U8).build());
                if (result.skipInfo != null) {
                    adPlaybackState = adPlaybackState.withAdSkipInfo(this.assetListData.adGroupIndex, i12, result.skipInfo);
                }
                i10++;
                j13 = j16;
            }
            long j18 = j13;
            AdPlaybackState adPlaybackStateWithAdDurationsUs = adPlaybackState.withAdDurationsUs(this.assetListData.adGroupIndex, jArr);
            if (this.assetListData.interstitial.resumeOffsetUs == j18) {
                adPlaybackStateWithAdDurationsUs = adPlaybackStateWithAdDurationsUs.withContentResumeOffsetUs(this.assetListData.adGroupIndex, (adPlaybackStateWithAdDurationsUs.getAdGroup(this.assetListData.adGroupIndex).contentResumeOffsetUs - j15) + j14);
            }
            HlsInterstitialsAdsLoader.this.putAndNotifyAdPlaybackStateUpdate(this.assetListData.adsId, adPlaybackStateWithAdDurationsUs);
            HlsInterstitialsAdsLoader.this.notifyListeners(new g(this, result, 0));
            maybeContinueAssetResolution();
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Callback
        public Loader.LoadErrorAction onLoadError(ParsingLoadable<AssetList> parsingLoadable, long j10, long j11, IOException iOException, int i10) {
            handleAssetResolutionFailed(iOException, false);
            return Loader.DONT_RETRY;
        }
    }

    public class PlayerListener implements Player.Listener {
        private final Timeline.Period period;

        private PlayerListener() {
            this.period = new Timeline.Period();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void lambda$onPlaybackStateChanged$4(Player player, Object obj, Listener listener) {
            MediaItem currentMediaItem = player.getCurrentMediaItem();
            currentMediaItem.getClass();
            listener.onAdStarted(currentMediaItem, obj, player.getCurrentAdGroupIndex(), player.getCurrentAdIndexInAdGroup());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void lambda$onPositionDiscontinuity$1(Player.PositionInfo positionInfo, Object obj, Listener listener) {
            MediaItem mediaItem = positionInfo.mediaItem;
            mediaItem.getClass();
            listener.onAdStarted(mediaItem, obj, positionInfo.adGroupIndex, positionInfo.adIndexInAdGroup);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void lambda$onPositionDiscontinuity$2(Player.PositionInfo positionInfo, Object obj, Listener listener) {
            MediaItem mediaItem = positionInfo.mediaItem;
            mediaItem.getClass();
            listener.onAdStarted(mediaItem, obj, positionInfo.adGroupIndex, positionInfo.adIndexInAdGroup);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void lambda$onPositionDiscontinuity$3(Player.PositionInfo positionInfo, Object obj, Listener listener) {
            MediaItem mediaItem = positionInfo.mediaItem;
            mediaItem.getClass();
            listener.onAdSkipped(mediaItem, obj, positionInfo.adGroupIndex, positionInfo.adIndexInAdGroup);
        }

        private void markAdAsPlayedAndNotifyListeners(MediaItem mediaItem, Object obj, int i10, int i11) {
            AdPlaybackState adPlaybackState = HlsInterstitialsAdsLoader.this.contentMediaSourceAdDataHolder.getAdPlaybackState(obj);
            if (adPlaybackState == null || adPlaybackState.getAdGroup(i10).states[i11] != 1) {
                return;
            }
            HlsInterstitialsAdsLoader.this.putAndNotifyAdPlaybackStateUpdate(obj, adPlaybackState.withPlayedAd(i10, i11));
            HlsInterstitialsAdsLoader.this.notifyListeners(new i(mediaItem, obj, i10, i11, 0));
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onAudioAttributesChanged(AudioAttributes audioAttributes) {
            androidx.media3.common.j.a(this, audioAttributes);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onAudioSessionIdChanged(int i10) {
            androidx.media3.common.j.b(this, i10);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onAvailableCommandsChanged(Player.Commands commands) {
            androidx.media3.common.j.c(this, commands);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onCues(CueGroup cueGroup) {
            androidx.media3.common.j.d(this, cueGroup);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onDeviceInfoChanged(DeviceInfo deviceInfo) {
            androidx.media3.common.j.f(this, deviceInfo);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onDeviceVolumeChanged(int i10, boolean z) {
            androidx.media3.common.j.g(this, i10, z);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onEvents(Player player, Player.Events events) {
            androidx.media3.common.j.h(this, player, events);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onIsLoadingChanged(boolean z) {
            androidx.media3.common.j.i(this, z);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onIsPlayingChanged(boolean z) {
            androidx.media3.common.j.j(this, z);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onLoadingChanged(boolean z) {
            androidx.media3.common.j.k(this, z);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onMaxSeekToPreviousPositionChanged(long j10) {
            androidx.media3.common.j.l(this, j10);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onMediaItemTransition(MediaItem mediaItem, int i10) {
            androidx.media3.common.j.m(this, mediaItem, i10);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
            androidx.media3.common.j.n(this, mediaMetadata);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onMetadata(Metadata metadata) {
            ExoPlayer exoPlayer = HlsInterstitialsAdsLoader.this.player;
            if (exoPlayer == null || !exoPlayer.isPlayingAd()) {
                return;
            }
            exoPlayer.getCurrentTimeline().getPeriod(exoPlayer.getCurrentPeriodIndex(), this.period);
            Object obj = this.period.adPlaybackState.adsId;
            if (obj == null || !HlsInterstitialsAdsLoader.this.contentMediaSourceAdDataHolder.isManagedContentSource(obj)) {
                return;
            }
            MediaItem currentMediaItem = exoPlayer.getCurrentMediaItem();
            currentMediaItem.getClass();
            HlsInterstitialsAdsLoader.this.notifyListeners(new j(exoPlayer.getCurrentAdGroupIndex(), exoPlayer.getCurrentAdIndexInAdGroup(), 0, currentMediaItem, obj, metadata));
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onPlayWhenReadyChanged(boolean z, int i10) {
            androidx.media3.common.j.p(this, z, i10);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
            androidx.media3.common.j.q(this, playbackParameters);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlaybackStateChanged(int i10) {
            ExoPlayer exoPlayer = HlsInterstitialsAdsLoader.this.player;
            if (i10 == 3 && exoPlayer != null && exoPlayer.isPlayingAd()) {
                exoPlayer.getCurrentTimeline().getPeriod(exoPlayer.getCurrentPeriodIndex(), this.period);
                Object obj = this.period.adPlaybackState.adsId;
                if (obj == null || !HlsInterstitialsAdsLoader.this.contentMediaSourceAdDataHolder.awaitingFirstAdToStartFor(obj)) {
                    return;
                }
                HlsInterstitialsAdsLoader.this.contentMediaSourceAdDataHolder.notifyAdStarted(obj);
                HlsInterstitialsAdsLoader.this.notifyListeners(new g(exoPlayer, obj, 1));
            }
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onPlaybackSuppressionReasonChanged(int i10) {
            androidx.media3.common.j.s(this, i10);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onPlayerError(PlaybackException playbackException) {
            androidx.media3.common.j.t(this, playbackException);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onPlayerErrorChanged(PlaybackException playbackException) {
            androidx.media3.common.j.u(this, playbackException);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onPlayerStateChanged(boolean z, int i10) {
            androidx.media3.common.j.v(this, z, i10);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onPlaylistMetadataChanged(MediaMetadata mediaMetadata) {
            androidx.media3.common.j.w(this, mediaMetadata);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onPositionDiscontinuity(int i10) {
            androidx.media3.common.j.x(this, i10);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onRenderedFirstFrame() {
            androidx.media3.common.j.z(this);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onRepeatModeChanged(int i10) {
            androidx.media3.common.j.A(this, i10);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onSeekBackIncrementChanged(long j10) {
            androidx.media3.common.j.B(this, j10);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onSeekForwardIncrementChanged(long j10) {
            androidx.media3.common.j.C(this, j10);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onShuffleModeEnabledChanged(boolean z) {
            androidx.media3.common.j.D(this, z);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onSkipSilenceEnabledChanged(boolean z) {
            androidx.media3.common.j.E(this, z);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onSurfaceSizeChanged(int i10, int i11) {
            androidx.media3.common.j.F(this, i10, i11);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onTimelineChanged(Timeline timeline, int i10) {
            if (timeline.isEmpty()) {
                HlsInterstitialsAdsLoader.this.cancelPendingAssetListResolutionMessage();
            }
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onTrackSelectionParametersChanged(TrackSelectionParameters trackSelectionParameters) {
            androidx.media3.common.j.H(this, trackSelectionParameters);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onTracksChanged(Tracks tracks) {
            androidx.media3.common.j.I(this, tracks);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onVideoSizeChanged(VideoSize videoSize) {
            androidx.media3.common.j.J(this, videoSize);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onVolumeChanged(float f10) {
            androidx.media3.common.j.K(this, f10);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onCues(List list) {
            androidx.media3.common.j.e(this, list);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPositionDiscontinuity(final Player.PositionInfo positionInfo, final Player.PositionInfo positionInfo2, int i10) {
            if (HlsInterstitialsAdsLoader.this.player == null || positionInfo.mediaItem == null || positionInfo2.mediaItem == null || i10 == 4 || i10 == 6 || i10 == 5) {
                HlsInterstitialsAdsLoader.this.cancelPendingAssetListResolutionMessage();
                return;
            }
            Timeline currentTimeline = HlsInterstitialsAdsLoader.this.player.getCurrentTimeline();
            currentTimeline.getPeriod(positionInfo2.periodIndex, this.period);
            final Object obj = this.period.adPlaybackState.adsId;
            if (obj == null || !HlsInterstitialsAdsLoader.this.contentMediaSourceAdDataHolder.isManagedContentSource(obj)) {
                HlsInterstitialsAdsLoader.this.cancelPendingAssetListResolutionMessage();
                return;
            }
            if (i10 == 0) {
                int i11 = positionInfo.adGroupIndex;
                if (i11 != -1) {
                    markAdAsPlayedAndNotifyListeners(positionInfo.mediaItem, obj, i11, positionInfo.adIndexInAdGroup);
                }
                if (positionInfo2.adIndexInAdGroup != -1) {
                    HlsInterstitialsAdsLoader.this.contentMediaSourceAdDataHolder.notifyAdStarted(obj);
                    final int i12 = 0;
                    HlsInterstitialsAdsLoader.this.notifyListeners(new Consumer() { // from class: androidx.media3.exoplayer.hls.h
                        @Override // androidx.media3.common.util.Consumer
                        public final void accept(Object obj2) {
                            switch (i12) {
                                case 0:
                                    HlsInterstitialsAdsLoader.PlayerListener.lambda$onPositionDiscontinuity$1(positionInfo2, obj, (HlsInterstitialsAdsLoader.Listener) obj2);
                                    break;
                                case 1:
                                    HlsInterstitialsAdsLoader.PlayerListener.lambda$onPositionDiscontinuity$2(positionInfo2, obj, (HlsInterstitialsAdsLoader.Listener) obj2);
                                    break;
                                default:
                                    HlsInterstitialsAdsLoader.PlayerListener.lambda$onPositionDiscontinuity$3(positionInfo2, obj, (HlsInterstitialsAdsLoader.Listener) obj2);
                                    break;
                            }
                        }
                    });
                    return;
                }
                return;
            }
            if (i10 != 1 && i10 != 2) {
                if (positionInfo.adGroupIndex == -1 || i10 != 3) {
                    return;
                }
                final int i13 = 2;
                HlsInterstitialsAdsLoader.this.notifyListeners(new Consumer() { // from class: androidx.media3.exoplayer.hls.h
                    @Override // androidx.media3.common.util.Consumer
                    public final void accept(Object obj2) {
                        switch (i13) {
                            case 0:
                                HlsInterstitialsAdsLoader.PlayerListener.lambda$onPositionDiscontinuity$1(positionInfo, obj, (HlsInterstitialsAdsLoader.Listener) obj2);
                                break;
                            case 1:
                                HlsInterstitialsAdsLoader.PlayerListener.lambda$onPositionDiscontinuity$2(positionInfo, obj, (HlsInterstitialsAdsLoader.Listener) obj2);
                                break;
                            default:
                                HlsInterstitialsAdsLoader.PlayerListener.lambda$onPositionDiscontinuity$3(positionInfo, obj, (HlsInterstitialsAdsLoader.Listener) obj2);
                                break;
                        }
                    }
                });
                return;
            }
            long jMsToUs = Util.msToUs(positionInfo2.contentPositionMs);
            long unresolvedAssetListWindowPositionForContentPositionUs = HlsInterstitialsAdsLoader.this.getUnresolvedAssetListWindowPositionForContentPositionUs(jMsToUs, currentTimeline, positionInfo2.periodIndex);
            HlsInterstitialsAdsLoader.this.maybeExecuteOrSetNextAssetListResolutionMessage(obj, currentTimeline, positionInfo2.mediaItemIndex, -this.period.positionInWindowUs, unresolvedAssetListWindowPositionForContentPositionUs != C.TIME_UNSET ? unresolvedAssetListWindowPositionForContentPositionUs : jMsToUs);
            if (positionInfo.adIndexInAdGroup != -1 || positionInfo2.adIndexInAdGroup == -1) {
                return;
            }
            final int i14 = 1;
            HlsInterstitialsAdsLoader.this.notifyListeners(new Consumer() { // from class: androidx.media3.exoplayer.hls.h
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj2) {
                    switch (i14) {
                        case 0:
                            HlsInterstitialsAdsLoader.PlayerListener.lambda$onPositionDiscontinuity$1(positionInfo2, obj, (HlsInterstitialsAdsLoader.Listener) obj2);
                            break;
                        case 1:
                            HlsInterstitialsAdsLoader.PlayerListener.lambda$onPositionDiscontinuity$2(positionInfo2, obj, (HlsInterstitialsAdsLoader.Listener) obj2);
                            break;
                        default:
                            HlsInterstitialsAdsLoader.PlayerListener.lambda$onPositionDiscontinuity$3(positionInfo2, obj, (HlsInterstitialsAdsLoader.Listener) obj2);
                            break;
                    }
                }
            });
        }
    }

    public static class RunnableAtPosition implements Runnable {
        public final long adStartTimeUs;
        private final Runnable runnable;
        private final long targetDurationUs;

        public RunnableAtPosition(long j10, long j11, Runnable runnable) {
            this.adStartTimeUs = j10;
            this.targetDurationUs = j11;
            this.runnable = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.runnable.run();
        }
    }

    public static final class StringAttribute {
        public final String name;
        public final String value;

        public StringAttribute(String str, String str2) {
            this.name = str;
            this.value = str2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof StringAttribute)) {
                return false;
            }
            StringAttribute stringAttribute = (StringAttribute) obj;
            return Objects.equals(this.name, stringAttribute.name) && Objects.equals(this.value, stringAttribute.value);
        }

        public int hashCode() {
            return Objects.hash(this.name, this.value);
        }
    }

    public HlsInterstitialsAdsLoader(Context context) {
        this(new DefaultDataSource.Factory(context));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelPendingAssetListResolutionMessage() {
        PlayerMessage playerMessage = this.pendingAssetListResolutionMessage;
        if (playerMessage != null) {
            playerMessage.cancel();
            this.pendingAssetListResolutionMessage = null;
        }
    }

    private LongSparseArray<List<HlsMediaPlaylist.Interstitial>> filterAndSortWithResolvedStartPositions(h1 h1Var, Object obj, HlsMediaPlaylist hlsMediaPlaylist, long j10, boolean z) {
        LongSparseArray<List<HlsMediaPlaylist.Interstitial>> longSparseArray = new LongSparseArray<>();
        for (int i10 = 0; i10 < h1Var.size(); i10++) {
            HlsMediaPlaylist.Interstitial interstitial = (HlsMediaPlaylist.Interstitial) h1Var.get(i10);
            if (!this.contentMediaSourceAdDataHolder.isInsertedInterstitialId(obj, interstitial.id) && (!z || !interstitial.cue.contains(HlsMediaPlaylist.Interstitial.CUE_TRIGGER_POST))) {
                long jResolveInterstitialStartTimeUs = resolveInterstitialStartTimeUs(interstitial, hlsMediaPlaylist, j10);
                List<HlsMediaPlaylist.Interstitial> arrayList = longSparseArray.indexOfKey(jResolveInterstitialStartTimeUs) < 0 ? new ArrayList<>() : longSparseArray.get(jResolveInterstitialStartTimeUs);
                longSparseArray.put(jResolveInterstitialStartTimeUs, arrayList);
                arrayList.add(interstitial);
            }
        }
        return longSparseArray;
    }

    private AdPlaybackState getAdPlaybackState() {
        Object obj;
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer == null) {
            return null;
        }
        Timeline currentTimeline = exoPlayer.getCurrentTimeline();
        if (currentTimeline.isEmpty() || (obj = currentTimeline.getPeriod(exoPlayer.getCurrentPeriodIndex(), new Timeline.Period()).adPlaybackState.adsId) == null) {
            return null;
        }
        return this.contentMediaSourceAdDataHolder.getAdPlaybackState(obj);
    }

    public static long getClosestSegmentBoundaryUs(long j10, HlsMediaPlaylist hlsMediaPlaylist) {
        long j11;
        long j12 = j10 - hlsMediaPlaylist.startTimeUs;
        if (j12 <= 0 || hlsMediaPlaylist.segments.isEmpty()) {
            return hlsMediaPlaylist.startTimeUs;
        }
        long j13 = hlsMediaPlaylist.durationUs;
        if (j12 >= j13) {
            j11 = hlsMediaPlaylist.startTimeUs;
        } else {
            int size = hlsMediaPlaylist.segments.size() - 1;
            int i10 = 0;
            int i11 = 0;
            while (i10 <= size) {
                i11 = ((size - i10) / 2) + i10;
                HlsMediaPlaylist.Segment segment = hlsMediaPlaylist.segments.get(i11);
                long j14 = segment.relativeStartTimeUs;
                long j15 = segment.durationUs + j14;
                if (j12 >= j14 && j12 <= j15) {
                    break;
                }
                if (j12 < j14) {
                    size = i11 - 1;
                } else {
                    i10 = i11 + 1;
                }
            }
            HlsMediaPlaylist.Segment segment2 = hlsMediaPlaylist.segments.get(i11);
            long j16 = segment2.relativeStartTimeUs;
            if (j12 - j16 < Math.abs(j12 - (j16 + segment2.durationUs))) {
                j11 = hlsMediaPlaylist.startTimeUs;
                j13 = segment2.relativeStartTimeUs;
            } else {
                j11 = hlsMediaPlaylist.startTimeUs + segment2.relativeStartTimeUs;
                j13 = segment2.durationUs;
            }
        }
        return j11 + j13;
    }

    private Loader getLoader() {
        if (this.loader == null) {
            this.loader = new Loader("HLS-interstitials");
        }
        return this.loader;
    }

    private static int getLowestValidAdGroupInsertionIndex(AdPlaybackState adPlaybackState) {
        int i10 = adPlaybackState.adGroupCount;
        while (true) {
            i10--;
            int i11 = adPlaybackState.removedAdGroupCount;
            if (i10 < i11) {
                return i11;
            }
            for (int i12 : adPlaybackState.getAdGroup(i10).states) {
                if (i12 != 0) {
                    return i10 + 1;
                }
            }
        }
    }

    private RunnableAtPosition getNextAssetResolution(Object obj, long j10) {
        Map<Long, AssetListData> unresolvedAssetLists = this.contentMediaSourceAdDataHolder.getUnresolvedAssetLists(obj);
        unresolvedAssetLists.getClass();
        final Map<Long, AssetListData> map = unresolvedAssetLists;
        for (final Long l10 : map.keySet()) {
            if (j10 <= l10.longValue()) {
                final AssetListData assetListData = map.get(l10);
                assetListData.getClass();
                return new RunnableAtPosition(l10.longValue(), assetListData.targetDurationUs, new Runnable() { // from class: androidx.media3.exoplayer.hls.c
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f3637i.lambda$getNextAssetResolution$7(map, l10, assetListData);
                    }
                });
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getUnresolvedAssetListWindowPositionForContentPositionUs(long j10, Timeline timeline, int i10) {
        int adGroupIndexForPositionUs;
        Timeline.Period period = timeline.getPeriod(i10, new Timeline.Period());
        long j11 = j10 - period.positionInWindowUs;
        AdPlaybackState adPlaybackState = period.adPlaybackState;
        if (adPlaybackState.adsId != null && (adGroupIndexForPositionUs = adPlaybackState.getAdGroupIndexForPositionUs(j11, C.TIME_UNSET)) != -1) {
            AdPlaybackState.AdGroup adGroup = adPlaybackState.getAdGroup(adGroupIndexForPositionUs);
            Map<Long, AssetListData> unresolvedAssetLists = this.contentMediaSourceAdDataHolder.getUnresolvedAssetLists(adPlaybackState.adsId);
            if (unresolvedAssetLists != null && unresolvedAssetLists.containsKey(Long.valueOf(adGroup.timeUs))) {
                return adGroup.timeUs - timeline.getWindow(period.windowIndex, new Timeline.Window()).positionInFirstPeriodUs;
            }
        }
        return C.TIME_UNSET;
    }

    private AdPlaybackState insertOrUpdateInterstitialInAdGroup(MediaItem mediaItem, HlsMediaPlaylist.Interstitial interstitial, AdPlaybackState adPlaybackState, int i10, long j10) {
        long[] jArr;
        AdPlaybackState.AdGroup adGroup = adPlaybackState.getAdGroup(i10);
        if (adGroup.getIndexOfAdId(interstitial.id) != -1) {
            return adPlaybackState;
        }
        int iMax = Math.max(adGroup.count, 0);
        long jResolveInterstitialDurationUs = resolveInterstitialDurationUs(interstitial, C.TIME_UNSET);
        if (iMax == 0) {
            jArr = new long[1];
        } else {
            long[] jArr2 = adGroup.durationsUs;
            long[] jArr3 = new long[jArr2.length + 1];
            System.arraycopy(jArr2, 0, jArr3, 0, jArr2.length);
            jArr = jArr3;
        }
        jArr[jArr.length - 1] = jResolveInterstitialDurationUs;
        long j11 = interstitial.resumeOffsetUs;
        if (j11 != C.TIME_UNSET) {
            jResolveInterstitialDurationUs = j11;
        } else if (jResolveInterstitialDurationUs == C.TIME_UNSET) {
            jResolveInterstitialDurationUs = 0;
        }
        AdPlaybackState adPlaybackStateWithContentResumeOffsetUs = adPlaybackState.withAdCount(i10, iMax + 1).withAdId(i10, iMax, interstitial.id).withAdDurationsUs(i10, jArr).withContentResumeOffsetUs(i10, adGroup.contentResumeOffsetUs + jResolveInterstitialDurationUs);
        if (interstitial.skipControlDurationUs != C.TIME_UNSET || interstitial.skipControlOffsetUs != C.TIME_UNSET || interstitial.skipControlLabelId != null) {
            adPlaybackStateWithContentResumeOffsetUs = adPlaybackStateWithContentResumeOffsetUs.withAdSkipInfo(i10, iMax, new AdPlaybackState.SkipInfo(interstitial.skipControlOffsetUs, interstitial.skipControlDurationUs, interstitial.skipControlLabelId));
        }
        AdPlaybackState adPlaybackState2 = adPlaybackStateWithContentResumeOffsetUs;
        if (interstitial.assetUri != null) {
            return adPlaybackState2.withAvailableAdMediaItem(i10, iMax, new MediaItem.Builder().setUri(interstitial.assetUri).setMimeType(MimeTypes.APPLICATION_M3U8).build());
        }
        Object obj = adPlaybackState2.adsId;
        obj.getClass();
        Map<Long, AssetListData> unresolvedAssetLists = this.contentMediaSourceAdDataHolder.getUnresolvedAssetLists(obj);
        unresolvedAssetLists.getClass();
        Map<Long, AssetListData> map = unresolvedAssetLists;
        long j12 = adGroup.timeUs;
        if (j12 == Long.MIN_VALUE) {
            j12 = Long.MAX_VALUE;
        }
        map.put(Long.valueOf(j12), new AssetListData(mediaItem, obj, interstitial, i10, iMax, j10));
        return adPlaybackState2;
    }

    private static boolean isHlsMediaItem(MediaItem mediaItem) {
        MediaItem.LocalConfiguration localConfiguration = mediaItem.localConfiguration;
        localConfiguration.getClass();
        return Objects.equals(localConfiguration.mimeType, MimeTypes.APPLICATION_M3U8) || Util.inferContentType(localConfiguration.uri) == 2;
    }

    private static boolean isLiveMediaItem(MediaItem mediaItem, Timeline timeline) {
        int firstWindowIndex = timeline.getFirstWindowIndex(false);
        Timeline.Window window = new Timeline.Window();
        while (firstWindowIndex != -1) {
            timeline.getWindow(firstWindowIndex, window);
            if (window.mediaItem.equals(mediaItem)) {
                return window.isLive();
            }
            firstWindowIndex = timeline.getNextWindowIndex(firstWindowIndex, 0, false);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$getNextAssetResolution$7(Map map, Long l10, AssetListData assetListData) {
        if (map.remove(l10) != null) {
            startLoadingAssetList(assetListData);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$handleContentTimelineChanged$1(AdsMediaSource adsMediaSource, Object obj, Timeline timeline, Listener listener) {
        listener.onContentTimelineChanged(adsMediaSource.getMediaItem(), obj, timeline);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$handlePrepareComplete$2(AdsMediaSource adsMediaSource, Object obj, int i10, int i11, Listener listener) {
        listener.onPrepareCompleted(adsMediaSource.getMediaItem(), obj, i10, i11);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$handlePrepareError$3(AdsMediaSource adsMediaSource, Object obj, int i10, int i11, IOException iOException, Listener listener) {
        listener.onPrepareError(adsMediaSource.getMediaItem(), obj, i10, i11, iOException);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$startLoadingAssetList$5(AssetListData assetListData, Listener listener) {
        listener.onAssetListLoadStarted(assetListData.mediaItem, assetListData.adsId, assetListData.adGroupIndex, assetListData.adIndexInAdGroup);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void lambda$stop$4(AdsMediaSource adsMediaSource, AdPlaybackState adPlaybackState, Listener listener) {
        MediaItem mediaItem = adsMediaSource.getMediaItem();
        Object adsId = adsMediaSource.getAdsId();
        adPlaybackState.getClass();
        listener.onStop(mediaItem, adsId, adPlaybackState);
    }

    private AdPlaybackState mapInterstitialsForLive(MediaItem mediaItem, HlsMediaPlaylist hlsMediaPlaylist, AdPlaybackState adPlaybackState, long j10, long j11) {
        int i10;
        boolean z;
        AdPlaybackState adPlaybackStateInsertOrUpdateInterstitialInAdGroup = adPlaybackState;
        Object obj = adPlaybackStateInsertOrUpdateInterstitialInAdGroup.adsId;
        obj.getClass();
        LongSparseArray<List<HlsMediaPlaylist.Interstitial>> longSparseArrayFilterAndSortWithResolvedStartPositions = filterAndSortWithResolvedStartPositions(hlsMediaPlaylist.interstitials, obj, hlsMediaPlaylist, j11, true);
        int i11 = 0;
        while (i11 < longSparseArrayFilterAndSortWithResolvedStartPositions.size()) {
            long jKeyAt = longSparseArrayFilterAndSortWithResolvedStartPositions.keyAt(i11);
            List<HlsMediaPlaylist.Interstitial> list = longSparseArrayFilterAndSortWithResolvedStartPositions.get(jKeyAt);
            int i12 = 0;
            while (i12 < list.size()) {
                HlsMediaPlaylist.Interstitial interstitial = list.get(i12);
                long j12 = jKeyAt - hlsMediaPlaylist.startTimeUs;
                if (j12 >= 0) {
                    i10 = i11;
                    if ((hlsMediaPlaylist.targetDurationUs * 3) + hlsMediaPlaylist.durationUs >= j12) {
                        long j13 = j10 + j12;
                        int i13 = adPlaybackStateInsertOrUpdateInterstitialInAdGroup.adGroupCount;
                        int i14 = i13 - 1;
                        int i15 = i13 - 2;
                        while (true) {
                            int i16 = i14;
                            i14 = i15;
                            if (i14 < adPlaybackStateInsertOrUpdateInterstitialInAdGroup.removedAdGroupCount) {
                                i14 = i16;
                                break;
                            }
                            long j14 = adPlaybackStateInsertOrUpdateInterstitialInAdGroup.getAdGroup(i14).timeUs;
                            if (j14 == j13) {
                                z = false;
                                break;
                            }
                            if (j14 < j13) {
                                i14++;
                                break;
                            }
                            i15 = i14 - 1;
                        }
                        z = true;
                        if (z) {
                            if (i14 < getLowestValidAdGroupInsertionIndex(adPlaybackStateInsertOrUpdateInterstitialInAdGroup)) {
                                Log.w(TAG, "Skipping insertion of interstitial attempted to be inserted behind an already initialized ad group.");
                            } else {
                                adPlaybackStateInsertOrUpdateInterstitialInAdGroup = adPlaybackStateInsertOrUpdateInterstitialInAdGroup.withNewAdGroup(i14, j13);
                            }
                        }
                        adPlaybackStateInsertOrUpdateInterstitialInAdGroup = insertOrUpdateInterstitialInAdGroup(mediaItem, interstitial, adPlaybackStateInsertOrUpdateInterstitialInAdGroup, i14, hlsMediaPlaylist.targetDurationUs);
                        this.contentMediaSourceAdDataHolder.addInsertedInterstitialId(obj, interstitial.id);
                    }
                } else {
                    i10 = i11;
                }
                i12++;
                i11 = i10;
            }
            i11++;
        }
        return adPlaybackStateInsertOrUpdateInterstitialInAdGroup;
    }

    private AdPlaybackState mapInterstitialsForVod(MediaItem mediaItem, HlsMediaPlaylist hlsMediaPlaylist, AdPlaybackState adPlaybackState, long j10, long j11, long j12) {
        AdPlaybackState adPlaybackStateWithNewAdGroup = adPlaybackState;
        ac.b.j(adPlaybackStateWithNewAdGroup.adGroupCount == adPlaybackStateWithNewAdGroup.removedAdGroupCount);
        h1 h1Var = hlsMediaPlaylist.interstitials;
        Object obj = adPlaybackStateWithNewAdGroup.adsId;
        obj.getClass();
        LongSparseArray<List<HlsMediaPlaylist.Interstitial>> longSparseArrayFilterAndSortWithResolvedStartPositions = filterAndSortWithResolvedStartPositions(h1Var, obj, hlsMediaPlaylist, j11, false);
        long j13 = hlsMediaPlaylist.startTimeUs + j11;
        long j14 = j13 + j10;
        for (int i10 = 0; i10 < longSparseArrayFilterAndSortWithResolvedStartPositions.size(); i10++) {
            List<HlsMediaPlaylist.Interstitial> list = longSparseArrayFilterAndSortWithResolvedStartPositions.get(longSparseArrayFilterAndSortWithResolvedStartPositions.keyAt(i10));
            for (int i11 = 0; i11 < list.size(); i11++) {
                HlsMediaPlaylist.Interstitial interstitial = list.get(i11);
                long jResolveInterstitialStartTimeUs = resolveInterstitialStartTimeUs(interstitial, hlsMediaPlaylist, j12);
                if (jResolveInterstitialStartTimeUs < j13 && interstitial.cue.contains(HlsMediaPlaylist.Interstitial.CUE_TRIGGER_PRE)) {
                    jResolveInterstitialStartTimeUs = j13;
                } else if (jResolveInterstitialStartTimeUs <= j14 || !interstitial.cue.contains(HlsMediaPlaylist.Interstitial.CUE_TRIGGER_POST)) {
                    if (jResolveInterstitialStartTimeUs < j13 || j14 < jResolveInterstitialStartTimeUs) {
                    }
                } else {
                    jResolveInterstitialStartTimeUs = j14;
                }
                long j15 = j14 == jResolveInterstitialStartTimeUs ? Long.MIN_VALUE : jResolveInterstitialStartTimeUs - hlsMediaPlaylist.startTimeUs;
                int adGroupIndexForPositionUs = adPlaybackStateWithNewAdGroup.getAdGroupIndexForPositionUs(j15, hlsMediaPlaylist.durationUs);
                if (adGroupIndexForPositionUs == -1) {
                    adGroupIndexForPositionUs = adPlaybackStateWithNewAdGroup.removedAdGroupCount;
                    adPlaybackStateWithNewAdGroup = adPlaybackStateWithNewAdGroup.withNewAdGroup(adGroupIndexForPositionUs, j15);
                } else if (adPlaybackStateWithNewAdGroup.getAdGroup(adGroupIndexForPositionUs).timeUs != j15) {
                    adGroupIndexForPositionUs++;
                    adPlaybackStateWithNewAdGroup = adPlaybackStateWithNewAdGroup.withNewAdGroup(adGroupIndexForPositionUs, j15);
                }
                adPlaybackStateWithNewAdGroup = insertOrUpdateInterstitialInAdGroup(mediaItem, interstitial, adPlaybackStateWithNewAdGroup, adGroupIndexForPositionUs, hlsMediaPlaylist.targetDurationUs);
                ContentMediaSourceAdDataHolder contentMediaSourceAdDataHolder = this.contentMediaSourceAdDataHolder;
                Object obj2 = adPlaybackStateWithNewAdGroup.adsId;
                obj2.getClass();
                contentMediaSourceAdDataHolder.addInsertedInterstitialId(obj2, interstitial.id);
            }
        }
        return adPlaybackStateWithNewAdGroup;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeExecuteOrSetNextAssetListResolutionMessage(Object obj, Timeline timeline, int i10, long j10, long j11) {
        Loader loader = this.loader;
        if (loader == null || !loader.isLoading()) {
            cancelPendingAssetListResolutionMessage();
            Timeline.Window window = timeline.getWindow(i10, new Timeline.Window());
            long j12 = j11 + j10;
            RunnableAtPosition nextAssetResolution = getNextAssetResolution(obj, j12);
            if (nextAssetResolution == null) {
                return;
            }
            long j13 = nextAssetResolution.adStartTimeUs;
            if (j13 == Long.MAX_VALUE) {
                j13 = window.durationUs;
            }
            long jMax = Math.max(j12, j13 - (nextAssetResolution.targetDurationUs * 3));
            if (jMax - j12 < 200000) {
                nextAssetResolution.run();
                return;
            }
            long jMax2 = jMax - j10;
            AdPlaybackState adPlaybackState = this.contentMediaSourceAdDataHolder.getAdPlaybackState(obj);
            adPlaybackState.getClass();
            int adGroupIndexForPositionUs = adPlaybackState.getAdGroupIndexForPositionUs(jMax, timeline.getPeriod(0, new Timeline.Period()).durationUs);
            if (adGroupIndexForPositionUs != -1) {
                AdPlaybackState.AdGroup adGroup = adPlaybackState.getAdGroup(adGroupIndexForPositionUs);
                jMax2 = Math.max(jMax2, (adGroup.timeUs + adGroup.contentResumeOffsetUs) - j10);
            }
            ExoPlayer exoPlayer = this.player;
            exoPlayer.getClass();
            PlayerMessage payload = exoPlayer.createMessage(new d(nextAssetResolution, 1)).setPayload(window.mediaItem);
            Looper looperMyLooper = Looper.myLooper();
            looperMyLooper.getClass();
            PlayerMessage position = payload.setLooper(looperMyLooper).setPosition(Math.max(Util.usToMs(jMax2), 0L));
            this.pendingAssetListResolutionMessage = position;
            position.send();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyAssetResolutionFailed(Object obj, int i10, int i11) {
        AdPlaybackState adPlaybackState = this.contentMediaSourceAdDataHolder.getAdPlaybackState(obj);
        if (adPlaybackState == null) {
            return;
        }
        putAndNotifyAdPlaybackStateUpdate(obj, adPlaybackState.withAdLoadError(i10, i11));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyListeners(Consumer<Listener> consumer) {
        for (int i10 = 0; i10 < this.listeners.size(); i10++) {
            consumer.accept(this.listeners.get(i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean putAndNotifyAdPlaybackStateUpdate(Object obj, AdPlaybackState adPlaybackState) {
        if (adPlaybackState.equals(this.contentMediaSourceAdDataHolder.putAdPlaybackState(obj, adPlaybackState))) {
            return false;
        }
        AdsLoader.EventListener eventListener = this.contentMediaSourceAdDataHolder.getEventListener(obj);
        if (eventListener != null) {
            eventListener.onAdPlaybackState(adPlaybackState);
            return true;
        }
        this.contentMediaSourceAdDataHolder.stopContentSource(obj);
        return false;
    }

    private void removeUnresolvedAssetListOfAdGroup(AdPlaybackState adPlaybackState, AdPlaybackState.AdGroup adGroup) {
        ac.b.j(adPlaybackState.adsId != null);
        Map<Long, AssetListData> unresolvedAssetLists = this.contentMediaSourceAdDataHolder.getUnresolvedAssetLists(adPlaybackState.adsId);
        if (unresolvedAssetLists != null) {
            long j10 = adGroup.timeUs;
            if (j10 == Long.MIN_VALUE) {
                j10 = Long.MAX_VALUE;
            }
            unresolvedAssetLists.remove(Long.valueOf(j10));
        }
    }

    private static long resolveInterstitialDurationUs(HlsMediaPlaylist.Interstitial interstitial, long j10) {
        long j11 = interstitial.playoutLimitUs;
        if (j11 != C.TIME_UNSET) {
            return j11;
        }
        long j12 = interstitial.durationUs;
        if (j12 != C.TIME_UNSET) {
            return j12;
        }
        long j13 = interstitial.endDateUnixUs;
        if (j13 != C.TIME_UNSET) {
            return j13 - interstitial.startDateUnixUs;
        }
        long j14 = interstitial.plannedDurationUs;
        return j14 != C.TIME_UNSET ? j14 : j10;
    }

    private static long resolveInterstitialStartTimeUs(HlsMediaPlaylist.Interstitial interstitial, HlsMediaPlaylist hlsMediaPlaylist, long j10) {
        if (interstitial.cue.contains(HlsMediaPlaylist.Interstitial.CUE_TRIGGER_PRE)) {
            return hlsMediaPlaylist.startTimeUs + j10;
        }
        if (interstitial.cue.contains(HlsMediaPlaylist.Interstitial.CUE_TRIGGER_POST)) {
            return hlsMediaPlaylist.startTimeUs + hlsMediaPlaylist.durationUs;
        }
        if (interstitial.snapTypes.contains(HlsMediaPlaylist.Interstitial.SNAP_TYPE_OUT)) {
            return getClosestSegmentBoundaryUs(interstitial.startDateUnixUs, hlsMediaPlaylist);
        }
        if (!interstitial.snapTypes.contains(HlsMediaPlaylist.Interstitial.SNAP_TYPE_IN)) {
            return interstitial.startDateUnixUs;
        }
        long jResolveInterstitialDurationUs = interstitial.resumeOffsetUs;
        if (jResolveInterstitialDurationUs == C.TIME_UNSET) {
            jResolveInterstitialDurationUs = resolveInterstitialDurationUs(interstitial, 0L);
        }
        return getClosestSegmentBoundaryUs(interstitial.startDateUnixUs + jResolveInterstitialDurationUs, hlsMediaPlaylist) - jResolveInterstitialDurationUs;
    }

    private void startLoadingAssetList(AssetListData assetListData) {
        cancelPendingAssetListResolutionMessage();
        Loader loader = getLoader();
        DataSource dataSourceCreateDataSource = this.dataSourceFactory.createDataSource();
        Uri uri = assetListData.interstitial.assetListUri;
        uri.getClass();
        loader.startLoading(new ParsingLoadable(dataSourceCreateDataSource, uri, 6, new AssetListParser()), new LoaderCallback(assetListData), 1);
        notifyListeners(new d(assetListData, 0));
    }

    public void addAdResumptionState(AdsResumptionState adsResumptionState) {
        addAdResumptionState(adsResumptionState.adsId, adsResumptionState.adPlaybackState);
    }

    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    public void clearAllAdResumptionStates() {
        this.resumptionStates.clear();
    }

    public h1 getAdsResumptionStates() {
        a0.c(4, "initialCapacity");
        Object[] objArrCopyOf = new Object[4];
        int i10 = 0;
        for (AdPlaybackState adPlaybackState : this.contentMediaSourceAdDataHolder.getAdPlaybackStates()) {
            boolean zEndsWithLivePostrollPlaceHolder = adPlaybackState.endsWithLivePostrollPlaceHolder();
            if (zEndsWithLivePostrollPlaceHolder || !(adPlaybackState.adsId instanceof String)) {
                Log.i(TAG, zEndsWithLivePostrollPlaceHolder ? "getAdsResumptionStates(): ignoring active ad playback state of live stream. adsId=" + adPlaybackState.adsId : "getAdsResumptionStates(): ignoring active ad playback state when creating resumption states. `adsId` is not of type String: " + Util.castNonNull(adPlaybackState.adsId).getClass());
            } else {
                AdsResumptionState adsResumptionState = new AdsResumptionState((String) adPlaybackState.adsId, adPlaybackState.copy());
                int i11 = i10 + 1;
                int iB = b1.b(objArrCopyOf.length, i11);
                if (iB > objArrCopyOf.length) {
                    objArrCopyOf = Arrays.copyOf(objArrCopyOf, iB);
                }
                objArrCopyOf[i10] = adsResumptionState;
                i10 = i11;
            }
        }
        return h1.h(i10, objArrCopyOf);
    }

    @Override // androidx.media3.exoplayer.source.ads.AdsLoader
    public boolean handleContentTimelineChanged(AdsMediaSource adsMediaSource, Timeline timeline) {
        HlsInterstitialsAdsLoader hlsInterstitialsAdsLoader;
        Timeline timeline2;
        AdPlaybackState adPlaybackStateMapInterstitialsForVod;
        Object adsId = adsMediaSource.getAdsId();
        if (this.isReleased) {
            AdsLoader.EventListener eventListener = this.contentMediaSourceAdDataHolder.getEventListener(adsId);
            if (eventListener != null) {
                AdPlaybackState adPlaybackStateStopContentSource = this.contentMediaSourceAdDataHolder.stopContentSource(adsId);
                adPlaybackStateStopContentSource.getClass();
                if (adPlaybackStateStopContentSource.equals(AdPlaybackState.NONE)) {
                    eventListener.onAdPlaybackState(new AdPlaybackState(adsId, new long[0]));
                    return false;
                }
            }
        } else {
            AdPlaybackState adPlaybackState = this.contentMediaSourceAdDataHolder.getAdPlaybackState(adsId);
            adPlaybackState.getClass();
            AdPlaybackState adPlaybackState2 = AdPlaybackState.NONE;
            if (adPlaybackState.equals(adPlaybackState2) || adPlaybackState.endsWithLivePostrollPlaceHolder()) {
                if (adPlaybackState.equals(adPlaybackState2)) {
                    adPlaybackState = new AdPlaybackState(adsId, new long[0]);
                    if (isLiveMediaItem(adsMediaSource.getMediaItem(), timeline)) {
                        adPlaybackState = adPlaybackState.withLivePostrollPlaceholderAppended(false);
                    }
                }
                AdPlaybackState adPlaybackState3 = adPlaybackState;
                Timeline.Window window = timeline.getWindow(0, new Timeline.Window());
                Object obj = window.manifest;
                if (obj instanceof HlsManifest) {
                    HlsMediaPlaylist hlsMediaPlaylist = ((HlsManifest) obj).mediaPlaylist;
                    int unresolvedAssetListCount = this.contentMediaSourceAdDataHolder.getUnresolvedAssetListCount(adsId);
                    if (window.isLive()) {
                        hlsInterstitialsAdsLoader = this;
                        adPlaybackStateMapInterstitialsForVod = hlsInterstitialsAdsLoader.mapInterstitialsForLive(window.mediaItem, hlsMediaPlaylist, adPlaybackState3, window.positionInFirstPeriodUs, window.defaultPositionUs);
                    } else {
                        hlsInterstitialsAdsLoader = this;
                        adPlaybackStateMapInterstitialsForVod = hlsInterstitialsAdsLoader.mapInterstitialsForVod(window.mediaItem, hlsMediaPlaylist, adPlaybackState3, window.durationUs, window.positionInFirstPeriodUs, window.defaultPositionUs);
                    }
                    AdPlaybackState adPlaybackState4 = adPlaybackStateMapInterstitialsForVod;
                    ExoPlayer exoPlayer = hlsInterstitialsAdsLoader.player;
                    if (unresolvedAssetListCount == hlsInterstitialsAdsLoader.contentMediaSourceAdDataHolder.getUnresolvedAssetListCount(adsId) || exoPlayer == null || !Objects.equals(window.mediaItem, exoPlayer.getCurrentMediaItem())) {
                        timeline2 = timeline;
                    } else {
                        int currentPeriodIndex = exoPlayer.getCurrentPeriodIndex();
                        long jMsToUs = Util.msToUs(exoPlayer.getContentPosition());
                        Timeline.Period period = exoPlayer.getCurrentTimeline().getPeriod(currentPeriodIndex, new Timeline.Period());
                        long j10 = -period.positionInWindowUs;
                        if (period.isPlaceholder) {
                            long j11 = window.durationUs;
                            if (jMsToUs >= j11) {
                                jMsToUs = j11 - 1;
                            }
                            if (window.isLive()) {
                                jMsToUs = window.defaultPositionUs;
                            }
                            int adGroupIndexForPositionUs = adPlaybackState4.getAdGroupIndexForPositionUs(jMsToUs, window.isLive() ? C.TIME_UNSET : window.durationUs);
                            if (adGroupIndexForPositionUs != -1) {
                                jMsToUs = adPlaybackState4.getAdGroup(adGroupIndexForPositionUs).timeUs;
                            }
                            j10 = window.positionInFirstPeriodUs;
                        }
                        timeline2 = timeline;
                        maybeExecuteOrSetNextAssetListResolutionMessage(adsId, timeline2, 0, j10, jMsToUs);
                        hlsInterstitialsAdsLoader = this;
                    }
                    adPlaybackState3 = adPlaybackState4;
                } else {
                    hlsInterstitialsAdsLoader = this;
                    timeline2 = timeline;
                }
                boolean zPutAndNotifyAdPlaybackStateUpdate = putAndNotifyAdPlaybackStateUpdate(adsId, adPlaybackState3);
                if (!hlsInterstitialsAdsLoader.contentMediaSourceAdDataHolder.isUnsupportedContentMediaSource(adsId)) {
                    notifyListeners(new b(adsMediaSource, adsId, timeline2, 0));
                }
                return zPutAndNotifyAdPlaybackStateUpdate;
            }
        }
        return false;
    }

    @Override // androidx.media3.exoplayer.source.ads.AdsLoader
    public void handlePrepareComplete(AdsMediaSource adsMediaSource, int i10, int i11) {
        Object adsId = adsMediaSource.getAdsId();
        if (this.isReleased || this.contentMediaSourceAdDataHolder.isUnsupportedContentMediaSource(adsId)) {
            return;
        }
        notifyListeners(new i(adsMediaSource, adsId, i10, i11, 1));
    }

    @Override // androidx.media3.exoplayer.source.ads.AdsLoader
    public void handlePrepareError(AdsMediaSource adsMediaSource, int i10, int i11, IOException iOException) {
        Object adsId = adsMediaSource.getAdsId();
        AdPlaybackState adPlaybackState = this.contentMediaSourceAdDataHolder.getAdPlaybackState(adsId);
        adPlaybackState.getClass();
        putAndNotifyAdPlaybackStateUpdate(adsId, adPlaybackState.withAdLoadError(i10, i11));
        if (this.isReleased || this.contentMediaSourceAdDataHolder.isUnsupportedContentMediaSource(adsId)) {
            return;
        }
        notifyListeners(new j(i10, i11, 1, adsMediaSource, adsId, iOException));
    }

    public boolean isReleased() {
        return this.isReleased;
    }

    @Override // androidx.media3.exoplayer.source.ads.AdsLoader
    public void release() {
        if (this.contentMediaSourceAdDataHolder.isIdle()) {
            this.player = null;
        }
        clearAllAdResumptionStates();
        cancelPendingAssetListResolutionMessage();
        Loader loader = this.loader;
        if (loader != null) {
            loader.release();
            this.loader = null;
        }
        this.isReleased = true;
    }

    public boolean removeAdResumptionState(Object obj) {
        return this.resumptionStates.remove(obj) != null;
    }

    public void removeListener(Listener listener) {
        this.listeners.remove(listener);
    }

    @Override // androidx.media3.exoplayer.source.ads.AdsLoader
    public void setPlayer(Player player) {
        boolean z = true;
        ac.b.s(!this.isReleased);
        ac.b.j(player == null || (player instanceof ExoPlayer));
        if (Objects.equals(this.player, player)) {
            return;
        }
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer != null && !this.contentMediaSourceAdDataHolder.isIdle()) {
            exoPlayer.removeListener(this.playerListener);
        }
        if (player != null && !this.contentMediaSourceAdDataHolder.isIdle()) {
            z = false;
        }
        ac.b.s(z);
        this.player = (ExoPlayer) player;
    }

    @Override // androidx.media3.exoplayer.source.ads.AdsLoader
    public void setSupportedContentTypes(int... iArr) {
        for (int i10 : iArr) {
            if (i10 == 2) {
                return;
            }
        }
        throw new IllegalArgumentException();
    }

    public void setWithAvailableAdGroup(int i10) {
        MediaItem mediaItem;
        ac.b.s(this.player != null);
        AdPlaybackState adPlaybackState = getAdPlaybackState();
        if (adPlaybackState == null) {
            return;
        }
        ac.b.j(i10 < adPlaybackState.adGroupCount);
        AdPlaybackState.AdGroup adGroup = adPlaybackState.getAdGroup(i10);
        int i11 = 0;
        while (true) {
            int[] iArr = adGroup.states;
            if (i11 >= iArr.length) {
                Object obj = adPlaybackState.adsId;
                obj.getClass();
                putAndNotifyAdPlaybackStateUpdate(obj, adPlaybackState);
                removeUnresolvedAssetListOfAdGroup(adPlaybackState, adGroup);
                return;
            }
            int i12 = iArr[i11];
            if ((i12 == 3 || i12 == 2) && (mediaItem = adGroup.mediaItems[i11]) != null) {
                ac.b.s(mediaItem != null);
                adPlaybackState = adPlaybackState.withAvailableAdMediaItem(i10, i11, mediaItem);
            }
            i11++;
        }
    }

    public void setWithAvailableAdMediaItem(int i10, int i11, MediaItem mediaItem) {
        ac.b.s(this.player != null);
        if (mediaItem != null) {
            ac.b.j(isHlsMediaItem(mediaItem));
        }
        AdPlaybackState adPlaybackState = getAdPlaybackState();
        if (adPlaybackState != null) {
            ac.b.j(i10 < adPlaybackState.adGroupCount);
            AdPlaybackState.AdGroup adGroup = adPlaybackState.getAdGroup(i10);
            ac.b.j(i11 < adGroup.count);
            if (mediaItem == null) {
                mediaItem = adGroup.mediaItems[i11];
                ac.b.s(mediaItem != null);
            }
            if (adGroup.states[i11] != 1) {
                AdPlaybackState adPlaybackStateWithAvailableAdMediaItem = adPlaybackState.withAvailableAdMediaItem(i10, i11, mediaItem);
                Object obj = adPlaybackStateWithAvailableAdMediaItem.adsId;
                obj.getClass();
                putAndNotifyAdPlaybackStateUpdate(obj, adPlaybackStateWithAvailableAdMediaItem);
                removeUnresolvedAssetListOfAdGroup(adPlaybackStateWithAvailableAdMediaItem, adGroup);
            }
        }
    }

    public void setWithSkippedAd(int i10, int i11) {
        ac.b.s(this.player != null);
        AdPlaybackState adPlaybackState = getAdPlaybackState();
        if (adPlaybackState != null) {
            ac.b.j(i10 < adPlaybackState.adGroupCount);
            AdPlaybackState.AdGroup adGroup = adPlaybackState.getAdGroup(i10);
            ac.b.j(i11 < adGroup.count);
            int i12 = adGroup.states[i11];
            if (i12 == 3 || i12 == 4) {
                Log.w(TAG, "ignoring request to set ad for state AD_STATE_SKIPPED for played or failed ad at adGroupIndex=" + i10 + ", adIndexInAgGroup=" + i11);
                return;
            }
            if (i12 != 2) {
                AdPlaybackState adPlaybackStateWithSkippedAd = adPlaybackState.withSkippedAd(i10, i11);
                Object obj = adPlaybackStateWithSkippedAd.adsId;
                obj.getClass();
                putAndNotifyAdPlaybackStateUpdate(obj, adPlaybackStateWithSkippedAd);
                removeUnresolvedAssetListOfAdGroup(adPlaybackStateWithSkippedAd, adGroup);
            }
        }
    }

    public void setWithSkippedAdGroup(int i10) {
        ac.b.s(this.player != null);
        AdPlaybackState adPlaybackState = getAdPlaybackState();
        if (adPlaybackState != null) {
            ac.b.j(i10 < adPlaybackState.adGroupCount);
            AdPlaybackState adPlaybackStateWithSkippedAdGroup = adPlaybackState.withSkippedAdGroup(i10);
            AdPlaybackState.AdGroup adGroup = adPlaybackStateWithSkippedAdGroup.getAdGroup(i10);
            Object obj = adPlaybackStateWithSkippedAdGroup.adsId;
            obj.getClass();
            putAndNotifyAdPlaybackStateUpdate(obj, adPlaybackStateWithSkippedAdGroup);
            removeUnresolvedAssetListOfAdGroup(adPlaybackStateWithSkippedAdGroup, adGroup);
        }
    }

    public void skipCurrentAd() {
        this.player.getClass();
        if (this.player.isPlayingAd()) {
            setWithSkippedAd(this.player.getCurrentAdGroupIndex(), this.player.getCurrentAdIndexInAdGroup());
        }
    }

    public void skipCurrentAdGroup() {
        this.player.getClass();
        if (this.player.isPlayingAd()) {
            setWithSkippedAdGroup(this.player.getCurrentAdGroupIndex());
        }
    }

    @Override // androidx.media3.exoplayer.source.ads.AdsLoader
    public void start(AdsMediaSource adsMediaSource, DataSpec dataSpec, Object obj, AdViewProvider adViewProvider, AdsLoader.EventListener eventListener) {
        if (this.isReleased) {
            eventListener.onAdPlaybackState(new AdPlaybackState(obj, new long[0]));
            return;
        }
        if (this.contentMediaSourceAdDataHolder.isStartedContentMediaSource(obj)) {
            throw new IllegalStateException("media item with adsId='" + obj + "' already started. Make sure adsIds are unique within the same playlist.");
        }
        if (this.contentMediaSourceAdDataHolder.isIdle()) {
            ExoPlayer exoPlayer = this.player;
            ac.b.p(exoPlayer, "setPlayer(Player) needs to be called");
            exoPlayer.addListener(this.playerListener);
        }
        this.contentMediaSourceAdDataHolder.startContentSource(obj, eventListener);
        MediaItem mediaItem = adsMediaSource.getMediaItem();
        if (!isHlsMediaItem(mediaItem)) {
            Log.w(TAG, "Unsupported media item. Playing without ads for adsId=" + obj);
            putAndNotifyAdPlaybackStateUpdate(obj, new AdPlaybackState(obj, new long[0]));
            this.contentMediaSourceAdDataHolder.addUnsupportedContentMediaSource(obj);
            return;
        }
        if ((obj instanceof String) && this.resumptionStates.containsKey(obj)) {
            AdPlaybackState adPlaybackStateRemove = this.resumptionStates.remove(obj);
            adPlaybackStateRemove.getClass();
            putAndNotifyAdPlaybackStateUpdate(obj, adPlaybackStateRemove);
        } else {
            this.contentMediaSourceAdDataHolder.putAdPlaybackState(obj, AdPlaybackState.NONE);
        }
        notifyListeners(new b(mediaItem, obj, adViewProvider, 1));
    }

    @Override // androidx.media3.exoplayer.source.ads.AdsLoader
    public void stop(AdsMediaSource adsMediaSource, AdsLoader.EventListener eventListener) {
        Object adsId = adsMediaSource.getAdsId();
        ac.b.s(this.contentMediaSourceAdDataHolder.isStartedContentMediaSource(adsId) || this.isReleased);
        boolean zIsUnsupportedContentMediaSource = this.contentMediaSourceAdDataHolder.isUnsupportedContentMediaSource(adsId);
        AdPlaybackState adPlaybackStateStopContentSource = this.contentMediaSourceAdDataHolder.stopContentSource(adsId);
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer != null && this.contentMediaSourceAdDataHolder.isIdle()) {
            exoPlayer.removeListener(this.playerListener);
            if (this.isReleased) {
                this.player = null;
            }
        }
        if (!this.isReleased && !zIsUnsupportedContentMediaSource) {
            if (adPlaybackStateStopContentSource != null && (adsId instanceof String) && this.resumptionStates.containsKey(adsId)) {
                this.resumptionStates.put(adsId, adPlaybackStateStopContentSource);
            }
            notifyListeners(new g(adsMediaSource, adPlaybackStateStopContentSource, 2));
        }
        if (this.pendingAssetListResolutionMessage == null || !adsMediaSource.getMediaItem().equals(((PlayerMessage) Util.castNonNull(this.pendingAssetListResolutionMessage)).getPayload())) {
            return;
        }
        cancelPendingAssetListResolutionMessage();
    }

    public HlsInterstitialsAdsLoader(DataSource.Factory factory) {
        this.dataSourceFactory = factory;
        this.playerListener = new PlayerListener();
        this.contentMediaSourceAdDataHolder = new ContentMediaSourceAdDataHolder();
        this.resumptionStates = new HashMap();
        this.listeners = new ArrayList();
    }

    public void addAdResumptionState(Object obj, AdPlaybackState adPlaybackState) {
        ac.b.j(!adPlaybackState.endsWithLivePostrollPlaceHolder());
        if (!this.contentMediaSourceAdDataHolder.isStartedContentMediaSource(obj)) {
            this.resumptionStates.put(obj, adPlaybackState.copy().withAdsId(obj));
            return;
        }
        Log.w(TAG, "Attempting to add an ad resumption state for an adsId that is currently active. adsId=" + obj);
    }
}
