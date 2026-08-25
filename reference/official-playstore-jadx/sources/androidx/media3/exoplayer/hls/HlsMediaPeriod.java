package androidx.media3.exoplayer.hls;

import android.net.Uri;
import android.text.TextUtils;
import androidx.media3.common.C;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.Label;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.StreamKey;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.hls.HlsSampleStreamWrapper;
import androidx.media3.exoplayer.hls.playlist.HlsMultivariantPlaylist;
import androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker;
import androidx.media3.exoplayer.source.CompositeSequenceableLoaderFactory;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import androidx.media3.exoplayer.source.SampleStream;
import androidx.media3.exoplayer.source.SequenceableLoader;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.exoplayer.util.ReleasableExecutor;
import com.google.android.gms.cast.MediaTrack;
import com.google.common.base.c0;
import com.google.common.collect.f1;
import com.google.common.collect.h1;
import com.google.common.collect.o3;
import j$.util.Objects;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import m2.f0;

/* JADX INFO: loaded from: classes3.dex */
final class HlsMediaPeriod implements MediaPeriod, HlsPlaylistTracker.PlaylistEventListener {
    private final Allocator allocator;
    private final boolean allowChunklessPreparation;
    private int audioVideoSampleStreamWrapperCount;
    private final CmcdConfiguration cmcdConfiguration;
    private SequenceableLoader compositeSequenceableLoader;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final HlsDataSourceFactory dataSourceFactory;
    private final c0<ReleasableExecutor> downloadExecutorSupplier;
    private final DrmSessionEventListener.EventDispatcher drmEventDispatcher;
    private final DrmSessionManager drmSessionManager;
    private final MediaSourceEventListener.EventDispatcher eventDispatcher;
    private final HlsExtractorFactory extractorFactory;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private MediaPeriod.Callback mediaPeriodCallback;
    private final TransferListener mediaTransferListener;
    private final int metadataType;
    private int pendingPrepareCount;
    private final PlayerId playerId;
    private final HlsPlaylistTracker playlistTracker;
    private final long timestampAdjusterInitializationTimeoutMs;
    private TrackGroupArray trackGroups;
    private final boolean useSessionKeys;
    private final HlsSampleStreamWrapper.Callback sampleStreamWrapperCallback = new SampleStreamWrapperCallback();
    private final IdentityHashMap<SampleStream, Integer> streamWrapperIndices = new IdentityHashMap<>();
    private final TimestampAdjusterProvider timestampAdjusterProvider = new TimestampAdjusterProvider();
    private HlsSampleStreamWrapper[] sampleStreamWrappers = new HlsSampleStreamWrapper[0];
    private HlsSampleStreamWrapper[] enabledSampleStreamWrappers = new HlsSampleStreamWrapper[0];
    private int[][] manifestUrlIndicesPerWrapper = new int[0][];

    public class SampleStreamWrapperCallback implements HlsSampleStreamWrapper.Callback {
        private SampleStreamWrapperCallback() {
        }

        @Override // androidx.media3.exoplayer.hls.HlsSampleStreamWrapper.Callback
        public void onPlaylistRefreshRequired(Uri uri) {
            HlsMediaPeriod.this.playlistTracker.refreshPlaylist(uri);
        }

        @Override // androidx.media3.exoplayer.hls.HlsSampleStreamWrapper.Callback
        public void onPrepared() {
            if (HlsMediaPeriod.access$106(HlsMediaPeriod.this) > 0) {
                return;
            }
            int i10 = 0;
            for (HlsSampleStreamWrapper hlsSampleStreamWrapper : HlsMediaPeriod.this.sampleStreamWrappers) {
                i10 += hlsSampleStreamWrapper.getTrackGroups().length;
            }
            TrackGroup[] trackGroupArr = new TrackGroup[i10];
            int i11 = 0;
            for (HlsSampleStreamWrapper hlsSampleStreamWrapper2 : HlsMediaPeriod.this.sampleStreamWrappers) {
                int i12 = hlsSampleStreamWrapper2.getTrackGroups().length;
                int i13 = 0;
                while (i13 < i12) {
                    trackGroupArr[i11] = hlsSampleStreamWrapper2.getTrackGroups().get(i13);
                    i13++;
                    i11++;
                }
            }
            HlsMediaPeriod.this.trackGroups = new TrackGroupArray(trackGroupArr);
            HlsMediaPeriod.this.mediaPeriodCallback.onPrepared(HlsMediaPeriod.this);
        }

        @Override // androidx.media3.exoplayer.source.SequenceableLoader.Callback
        public void onContinueLoadingRequested(HlsSampleStreamWrapper hlsSampleStreamWrapper) {
            HlsMediaPeriod.this.mediaPeriodCallback.onContinueLoadingRequested(HlsMediaPeriod.this);
        }
    }

    public HlsMediaPeriod(HlsExtractorFactory hlsExtractorFactory, HlsPlaylistTracker hlsPlaylistTracker, HlsDataSourceFactory hlsDataSourceFactory, TransferListener transferListener, CmcdConfiguration cmcdConfiguration, DrmSessionManager drmSessionManager, DrmSessionEventListener.EventDispatcher eventDispatcher, LoadErrorHandlingPolicy loadErrorHandlingPolicy, MediaSourceEventListener.EventDispatcher eventDispatcher2, Allocator allocator, CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory, boolean z, int i10, boolean z5, PlayerId playerId, long j10, c0<ReleasableExecutor> c0Var) {
        this.extractorFactory = hlsExtractorFactory;
        this.playlistTracker = hlsPlaylistTracker;
        this.dataSourceFactory = hlsDataSourceFactory;
        this.mediaTransferListener = transferListener;
        this.cmcdConfiguration = cmcdConfiguration;
        this.drmSessionManager = drmSessionManager;
        this.drmEventDispatcher = eventDispatcher;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy;
        this.eventDispatcher = eventDispatcher2;
        this.allocator = allocator;
        this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory;
        this.allowChunklessPreparation = z;
        this.metadataType = i10;
        this.useSessionKeys = z5;
        this.playerId = playerId;
        this.timestampAdjusterInitializationTimeoutMs = j10;
        this.downloadExecutorSupplier = c0Var;
        this.compositeSequenceableLoader = compositeSequenceableLoaderFactory.empty();
    }

    public static /* synthetic */ int access$106(HlsMediaPeriod hlsMediaPeriod) {
        int i10 = hlsMediaPeriod.pendingPrepareCount - 1;
        hlsMediaPeriod.pendingPrepareCount = i10;
        return i10;
    }

    private void buildAndPrepareAudioSampleStreamWrappers(long j10, List<HlsMultivariantPlaylist.Rendition> list, List<HlsSampleStreamWrapper> list2, List<int[]> list3, Map<String, DrmInitData> map) {
        ArrayList arrayList = new ArrayList(list.size());
        ArrayList arrayList2 = new ArrayList(list.size());
        ArrayList arrayList3 = new ArrayList(list.size());
        HashSet hashSet = new HashSet();
        for (int i10 = 0; i10 < list.size(); i10++) {
            String str = list.get(i10).name;
            if (hashSet.add(str)) {
                arrayList.clear();
                arrayList2.clear();
                arrayList3.clear();
                boolean z = true;
                for (int i11 = 0; i11 < list.size(); i11++) {
                    if (Objects.equals(str, list.get(i11).name)) {
                        HlsMultivariantPlaylist.Rendition rendition = list.get(i11);
                        arrayList3.add(Integer.valueOf(i11));
                        arrayList.add(rendition.url);
                        arrayList2.add(rendition.format);
                        z &= Util.getCodecCountOfType(rendition.format.codecs, 1) == 1;
                    }
                }
                String strC = androidx.compose.material3.d.C("audio:", str);
                HlsSampleStreamWrapper hlsSampleStreamWrapperBuildSampleStreamWrapper = buildSampleStreamWrapper(strC, 1, (Uri[]) arrayList.toArray((Uri[]) Util.castNonNullTypeArray(new Uri[0])), (Format[]) arrayList2.toArray(new Format[0]), null, Collections.EMPTY_LIST, map, j10);
                list3.add(f0.O(arrayList3));
                list2.add(hlsSampleStreamWrapperBuildSampleStreamWrapper);
                if (this.allowChunklessPreparation && z) {
                    hlsSampleStreamWrapperBuildSampleStreamWrapper.prepareWithMultivariantPlaylistInfo(new TrackGroup[]{new TrackGroup(strC, (Format[]) arrayList2.toArray(new Format[0]))}, 0, new int[0]);
                }
            }
        }
    }

    private void buildAndPrepareMainSampleStreamWrapper(HlsMultivariantPlaylist hlsMultivariantPlaylist, long j10, List<HlsSampleStreamWrapper> list, List<int[]> list2, Map<String, DrmInitData> map) {
        int i10;
        boolean z;
        boolean z5;
        int size = hlsMultivariantPlaylist.variants.size();
        int[] iArr = new int[size];
        int i11 = 0;
        int i12 = 0;
        for (int i13 = 0; i13 < hlsMultivariantPlaylist.variants.size(); i13++) {
            Format format = hlsMultivariantPlaylist.variants.get(i13).format;
            if (format.height > 0 || Util.getCodecsOfType(format.codecs, 2) != null) {
                iArr[i13] = 2;
                i11++;
            } else if (Util.getCodecsOfType(format.codecs, 1) != null) {
                iArr[i13] = 1;
                i12++;
            } else {
                iArr[i13] = -1;
            }
        }
        if (i11 > 0) {
            i10 = i11;
            z5 = false;
            z = true;
        } else if (i12 < size) {
            i10 = size - i12;
            z = false;
            z5 = true;
        } else {
            i10 = size;
            z = false;
            z5 = false;
        }
        Uri[] uriArr = new Uri[i10];
        Format[] formatArr = new Format[i10];
        int[] iArr2 = new int[i10];
        int i14 = 0;
        for (int i15 = 0; i15 < hlsMultivariantPlaylist.variants.size(); i15++) {
            if ((!z || iArr[i15] == 2) && (!z5 || iArr[i15] != 1)) {
                HlsMultivariantPlaylist.Variant variant = hlsMultivariantPlaylist.variants.get(i15);
                uriArr[i14] = variant.url;
                formatArr[i14] = variant.format;
                iArr2[i14] = i15;
                i14++;
            }
        }
        String str = formatArr[0].codecs;
        int codecCountOfType = Util.getCodecCountOfType(str, 2);
        int codecCountOfType2 = Util.getCodecCountOfType(str, 1);
        boolean z10 = (codecCountOfType2 == 1 || (codecCountOfType2 == 0 && hlsMultivariantPlaylist.audios.isEmpty())) && codecCountOfType <= 1 && codecCountOfType2 + codecCountOfType > 0;
        HlsSampleStreamWrapper hlsSampleStreamWrapperBuildSampleStreamWrapper = buildSampleStreamWrapper(MediaTrack.ROLE_MAIN, (z || codecCountOfType2 <= 0) ? 0 : 1, uriArr, formatArr, hlsMultivariantPlaylist.muxedAudioFormat, hlsMultivariantPlaylist.muxedCaptionFormats, map, j10);
        list.add(hlsSampleStreamWrapperBuildSampleStreamWrapper);
        list2.add(iArr2);
        if (this.allowChunklessPreparation && z10) {
            ArrayList arrayList = new ArrayList();
            if (codecCountOfType > 0) {
                Format[] formatArr2 = new Format[i10];
                for (int i16 = 0; i16 < i10; i16++) {
                    formatArr2[i16] = deriveVideoFormat(formatArr[i16]);
                }
                arrayList.add(new TrackGroup(MediaTrack.ROLE_MAIN, formatArr2));
                if (codecCountOfType2 > 0 && (hlsMultivariantPlaylist.muxedAudioFormat != null || hlsMultivariantPlaylist.audios.isEmpty())) {
                    arrayList.add(new TrackGroup("main:audio", deriveAudioFormat(formatArr[0], hlsMultivariantPlaylist.muxedAudioFormat, false)));
                }
                List<Format> list3 = hlsMultivariantPlaylist.muxedCaptionFormats;
                if (list3 != null) {
                    for (int i17 = 0; i17 < list3.size(); i17++) {
                        arrayList.add(new TrackGroup(a0.c.i(i17, "main:cc:"), this.extractorFactory.getOutputTextFormat(list3.get(i17))));
                    }
                }
            } else {
                Format[] formatArr3 = new Format[i10];
                for (int i18 = 0; i18 < i10; i18++) {
                    formatArr3[i18] = deriveAudioFormat(formatArr[i18], hlsMultivariantPlaylist.muxedAudioFormat, true);
                }
                arrayList.add(new TrackGroup(MediaTrack.ROLE_MAIN, formatArr3));
            }
            TrackGroup trackGroup = new TrackGroup("main:id3", new Format.Builder().setId("ID3").setSampleMimeType(MimeTypes.APPLICATION_ID3).build());
            arrayList.add(trackGroup);
            hlsSampleStreamWrapperBuildSampleStreamWrapper.prepareWithMultivariantPlaylistInfo((TrackGroup[]) arrayList.toArray(new TrackGroup[0]), 0, arrayList.indexOf(trackGroup));
        }
    }

    private void buildAndPrepareSampleStreamWrappers(long j10) {
        long j11;
        HlsMultivariantPlaylist multivariantPlaylist = this.playlistTracker.getMultivariantPlaylist();
        multivariantPlaylist.getClass();
        Map<String, DrmInitData> mapDeriveOverridingDrmInitData = this.useSessionKeys ? deriveOverridingDrmInitData(multivariantPlaylist.sessionKeyDrmInitData) : Collections.EMPTY_MAP;
        boolean zIsEmpty = multivariantPlaylist.variants.isEmpty();
        List<HlsMultivariantPlaylist.Rendition> list = multivariantPlaylist.audios;
        List<HlsMultivariantPlaylist.Rendition> list2 = multivariantPlaylist.subtitles;
        this.pendingPrepareCount = 0;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (zIsEmpty) {
            j11 = j10;
        } else {
            buildAndPrepareMainSampleStreamWrapper(multivariantPlaylist, j10, arrayList, arrayList2, mapDeriveOverridingDrmInitData);
            arrayList = arrayList;
            j11 = j10;
        }
        buildAndPrepareAudioSampleStreamWrappers(j11, list, arrayList, arrayList2, mapDeriveOverridingDrmInitData);
        this.audioVideoSampleStreamWrapperCount = arrayList.size();
        buildAndPrepareSubtitleSampleStreamWrappers(j11, list2, arrayList, arrayList2, mapDeriveOverridingDrmInitData);
        this.sampleStreamWrappers = (HlsSampleStreamWrapper[]) arrayList.toArray(new HlsSampleStreamWrapper[0]);
        this.manifestUrlIndicesPerWrapper = (int[][]) arrayList2.toArray(new int[0][]);
        this.pendingPrepareCount = this.sampleStreamWrappers.length;
        for (int i10 = 0; i10 < this.audioVideoSampleStreamWrapperCount; i10++) {
            this.sampleStreamWrappers[i10].setIsPrimaryTimestampSource(true);
        }
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.sampleStreamWrappers) {
            hlsSampleStreamWrapper.continuePreparing();
        }
        this.enabledSampleStreamWrappers = this.sampleStreamWrappers;
    }

    private void buildAndPrepareSubtitleSampleStreamWrappers(long j10, List<HlsMultivariantPlaylist.Rendition> list, List<HlsSampleStreamWrapper> list2, List<int[]> list3, Map<String, DrmInitData> map) {
        int i10;
        List<HlsMultivariantPlaylist.Rendition> list4 = list;
        ArrayList arrayList = new ArrayList(list4.size());
        ArrayList arrayList2 = new ArrayList(list4.size());
        ArrayList arrayList3 = new ArrayList(list4.size());
        HashSet hashSet = new HashSet();
        int i11 = 0;
        int i12 = 0;
        while (i12 < list4.size()) {
            String str = list4.get(i12).name;
            if (hashSet.add(str)) {
                arrayList.clear();
                arrayList2.clear();
                arrayList3.clear();
                for (int i13 = i11; i13 < list4.size(); i13++) {
                    if (Objects.equals(str, list4.get(i13).name)) {
                        HlsMultivariantPlaylist.Rendition rendition = list4.get(i13);
                        arrayList3.add(Integer.valueOf(i13));
                        arrayList.add(rendition.url);
                        arrayList2.add(rendition.format);
                    }
                }
                String strC = androidx.compose.material3.d.C("subtitle:", str);
                Format[] formatArr = (Format[]) arrayList2.toArray(new Format[i11]);
                Uri[] uriArr = (Uri[]) arrayList.toArray((Uri[]) Util.castNonNullTypeArray(new Uri[i11]));
                f1 f1Var = h1.f14020l;
                HlsSampleStreamWrapper hlsSampleStreamWrapperBuildSampleStreamWrapper = buildSampleStreamWrapper(strC, 3, uriArr, formatArr, null, o3.f14078o, map, j10);
                list3.add(f0.O(arrayList3));
                list2.add(hlsSampleStreamWrapperBuildSampleStreamWrapper);
                int length = formatArr.length;
                Format[] formatArr2 = new Format[length];
                int i14 = i11;
                while (i14 < length) {
                    formatArr2[i14] = this.extractorFactory.getOutputTextFormat(formatArr[i14]);
                    i14++;
                    i11 = i11;
                }
                int i15 = i11;
                TrackGroup[] trackGroupArr = new TrackGroup[1];
                trackGroupArr[i15] = new TrackGroup(strC, formatArr2);
                i10 = i15;
                hlsSampleStreamWrapperBuildSampleStreamWrapper.prepareWithMultivariantPlaylistInfo(trackGroupArr, i10, new int[i10]);
            } else {
                i10 = i11;
            }
            i12++;
            i11 = i10;
            list4 = list;
        }
    }

    private HlsSampleStreamWrapper buildSampleStreamWrapper(String str, int i10, Uri[] uriArr, Format[] formatArr, Format format, List<Format> list, Map<String, DrmInitData> map, long j10) {
        HlsChunkSource hlsChunkSource = new HlsChunkSource(this.extractorFactory, this.playlistTracker, uriArr, formatArr, this.dataSourceFactory, this.mediaTransferListener, this.timestampAdjusterProvider, this.timestampAdjusterInitializationTimeoutMs, list, this.playerId, this.cmcdConfiguration);
        HlsSampleStreamWrapper.Callback callback = this.sampleStreamWrapperCallback;
        Allocator allocator = this.allocator;
        DrmSessionManager drmSessionManager = this.drmSessionManager;
        DrmSessionEventListener.EventDispatcher eventDispatcher = this.drmEventDispatcher;
        LoadErrorHandlingPolicy loadErrorHandlingPolicy = this.loadErrorHandlingPolicy;
        MediaSourceEventListener.EventDispatcher eventDispatcher2 = this.eventDispatcher;
        int i11 = this.metadataType;
        c0<ReleasableExecutor> c0Var = this.downloadExecutorSupplier;
        return new HlsSampleStreamWrapper(str, i10, callback, hlsChunkSource, map, allocator, j10, format, drmSessionManager, eventDispatcher, loadErrorHandlingPolicy, eventDispatcher2, i11, c0Var != null ? (ReleasableExecutor) c0Var.get() : null);
    }

    private static Format deriveAudioFormat(Format format, Format format2, boolean z) {
        Metadata metadata;
        int i10;
        String str;
        String str2;
        List<Label> list;
        int i11;
        int i12;
        String str3;
        f1 f1Var = h1.f14020l;
        o3 o3Var = o3.f14078o;
        if (format2 != null) {
            str2 = format2.codecs;
            metadata = format2.metadata;
            i11 = format2.channelCount;
            i10 = format2.selectionFlags;
            i12 = format2.roleFlags;
            str = format2.language;
            str3 = format2.label;
            list = format2.labels;
        } else {
            String codecsOfType = Util.getCodecsOfType(format.codecs, 1);
            metadata = format.metadata;
            if (z) {
                i11 = format.channelCount;
                i10 = format.selectionFlags;
                i12 = format.roleFlags;
                str = format.language;
                str3 = format.label;
                str2 = codecsOfType;
                list = format.labels;
            } else {
                i10 = 0;
                str = null;
                str2 = codecsOfType;
                list = o3Var;
                i11 = -1;
                i12 = 0;
                str3 = null;
            }
        }
        return new Format.Builder().setId(format.id).setLabel(str3).setLabels(list).setContainerMimeType(format.containerMimeType).setSampleMimeType(MimeTypes.getMediaMimeType(str2)).setCodecs(str2).setMetadata(metadata).setAverageBitrate(z ? format.averageBitrate : -1).setPeakBitrate(z ? format.peakBitrate : -1).setChannelCount(i11).setSelectionFlags(i10).setRoleFlags(i12).setLanguage(str).build();
    }

    private static Map<String, DrmInitData> deriveOverridingDrmInitData(List<DrmInitData> list) {
        ArrayList arrayList = new ArrayList(list);
        HashMap map = new HashMap();
        int i10 = 0;
        while (i10 < arrayList.size()) {
            DrmInitData drmInitDataMerge = list.get(i10);
            String str = drmInitDataMerge.schemeType;
            i10++;
            int i11 = i10;
            while (i11 < arrayList.size()) {
                DrmInitData drmInitData = (DrmInitData) arrayList.get(i11);
                if (TextUtils.equals(drmInitData.schemeType, str)) {
                    drmInitDataMerge = drmInitDataMerge.merge(drmInitData);
                    arrayList.remove(i11);
                } else {
                    i11++;
                }
            }
            map.put(str, drmInitDataMerge);
        }
        return map;
    }

    private static Format deriveVideoFormat(Format format) {
        String codecsOfType = Util.getCodecsOfType(format.codecs, 2);
        return new Format.Builder().setId(format.id).setLabel(format.label).setLabels(format.labels).setContainerMimeType(format.containerMimeType).setSampleMimeType(MimeTypes.getMediaMimeType(codecsOfType)).setCodecs(codecsOfType).setMetadata(format.metadata).setAverageBitrate(format.averageBitrate).setPeakBitrate(format.peakBitrate).setWidth(format.width).setHeight(format.height).setFrameRate(format.frameRate).setSelectionFlags(format.selectionFlags).setRoleFlags(format.roleFlags).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ List lambda$selectTracks$0(HlsSampleStreamWrapper hlsSampleStreamWrapper) {
        return hlsSampleStreamWrapper.getTrackGroups().getTrackTypes();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean continueLoading(LoadingInfo loadingInfo) {
        if (this.trackGroups != null) {
            return this.compositeSequenceableLoader.continueLoading(loadingInfo);
        }
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.sampleStreamWrappers) {
            hlsSampleStreamWrapper.continuePreparing();
        }
        return false;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void discardBuffer(long j10, boolean z) {
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.enabledSampleStreamWrappers) {
            hlsSampleStreamWrapper.discardBuffer(j10, z);
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long j10, SeekParameters seekParameters) {
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.enabledSampleStreamWrappers) {
            if (hlsSampleStreamWrapper.isVideoSampleStream()) {
                return hlsSampleStreamWrapper.getAdjustedSeekPositionUs(j10, seekParameters);
            }
        }
        return j10;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getBufferedPositionUs() {
        return this.compositeSequenceableLoader.getBufferedPositionUs();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        return this.compositeSequenceableLoader.getNextLoadPositionUs();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v2, types: [int] */
    /* JADX WARN: Type inference failed for: r14v8 */
    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public List<StreamKey> getStreamKeys(List<ExoTrackSelection> list) {
        int[] iArr;
        TrackGroupArray trackGroups;
        int primaryTrackGroupIndex;
        int i10;
        HlsMultivariantPlaylist multivariantPlaylist = this.playlistTracker.getMultivariantPlaylist();
        multivariantPlaylist.getClass();
        boolean zIsEmpty = multivariantPlaylist.variants.isEmpty();
        boolean z = !zIsEmpty;
        int i11 = 0;
        if (zIsEmpty) {
            iArr = new int[0];
            trackGroups = TrackGroupArray.EMPTY;
            primaryTrackGroupIndex = 0;
        } else {
            HlsSampleStreamWrapper hlsSampleStreamWrapper = this.sampleStreamWrappers[0];
            iArr = this.manifestUrlIndicesPerWrapper[0];
            trackGroups = hlsSampleStreamWrapper.getTrackGroups();
            primaryTrackGroupIndex = hlsSampleStreamWrapper.getPrimaryTrackGroupIndex();
        }
        ArrayList arrayList = new ArrayList();
        boolean z5 = false;
        boolean z10 = false;
        for (ExoTrackSelection exoTrackSelection : list) {
            TrackGroup trackGroup = exoTrackSelection.getTrackGroup();
            int iIndexOf = trackGroups.indexOf(trackGroup);
            if (iIndexOf == -1) {
                ?? r14 = z;
                i10 = i11;
                while (true) {
                    HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr = this.sampleStreamWrappers;
                    if (r14 >= hlsSampleStreamWrapperArr.length) {
                        break;
                    }
                    TrackGroupArray trackGroups2 = hlsSampleStreamWrapperArr[r14].getTrackGroups();
                    int iIndexOf2 = trackGroups2.indexOf(trackGroup);
                    if (iIndexOf2 != -1) {
                        int i12 = trackGroups2.get(iIndexOf2).type != 1 ? 2 : 1;
                        int[] iArr2 = this.manifestUrlIndicesPerWrapper[r14];
                        for (int i13 = i10; i13 < exoTrackSelection.length(); i13++) {
                            arrayList.add(new StreamKey(i12, iArr2[exoTrackSelection.getIndexInTrackGroup(i13)]));
                        }
                    } else {
                        r14++;
                    }
                }
            } else if (iIndexOf == primaryTrackGroupIndex) {
                for (int i14 = i11; i14 < exoTrackSelection.length(); i14++) {
                    arrayList.add(new StreamKey(i11, iArr[exoTrackSelection.getIndexInTrackGroup(i14)]));
                }
                i10 = i11;
                z10 = true;
            } else {
                i10 = i11;
                z5 = true;
            }
            i11 = i10;
        }
        int i15 = i11;
        if (z5 && !z10) {
            int i16 = iArr[i15];
            int i17 = multivariantPlaylist.variants.get(i16).format.bitrate;
            for (int i18 = 1; i18 < iArr.length; i18++) {
                int i19 = multivariantPlaylist.variants.get(iArr[i18]).format.bitrate;
                if (i19 < i17) {
                    i16 = iArr[i18];
                    i17 = i19;
                }
            }
            arrayList.add(new StreamKey(i15, i16));
        }
        return arrayList;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        TrackGroupArray trackGroupArray = this.trackGroups;
        trackGroupArray.getClass();
        return trackGroupArray;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean isLoading() {
        return this.compositeSequenceableLoader.isLoading();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void maybeThrowPrepareError() throws IOException {
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.sampleStreamWrappers) {
            hlsSampleStreamWrapper.maybeThrowPrepareError();
        }
    }

    @Override // androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker.PlaylistEventListener
    public void onPlaylistChanged() {
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.sampleStreamWrappers) {
            hlsSampleStreamWrapper.onPlaylistUpdated();
        }
        this.mediaPeriodCallback.onContinueLoadingRequested(this);
    }

    @Override // androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker.PlaylistEventListener
    public boolean onPlaylistError(Uri uri, LoadErrorHandlingPolicy.LoadErrorInfo loadErrorInfo, boolean z) {
        boolean zOnPlaylistError = true;
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.sampleStreamWrappers) {
            zOnPlaylistError &= hlsSampleStreamWrapper.onPlaylistError(uri, loadErrorInfo, z);
        }
        this.mediaPeriodCallback.onContinueLoadingRequested(this);
        return zOnPlaylistError;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void prepare(MediaPeriod.Callback callback, long j10) {
        this.mediaPeriodCallback = callback;
        this.playlistTracker.addListener(this);
        buildAndPrepareSampleStreamWrappers(j10);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long readDiscontinuity() {
        return C.TIME_UNSET;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public void reevaluateBuffer(long j10) {
        this.compositeSequenceableLoader.reevaluateBuffer(j10);
    }

    public void release() {
        this.playlistTracker.removeListener(this);
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.sampleStreamWrappers) {
            hlsSampleStreamWrapper.release();
        }
        this.mediaPeriodCallback = null;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long seekToUs(long j10) {
        HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr = this.enabledSampleStreamWrappers;
        if (hlsSampleStreamWrapperArr.length > 0) {
            boolean zSeekToUs = hlsSampleStreamWrapperArr[0].seekToUs(j10, false);
            int i10 = 1;
            while (true) {
                HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr2 = this.enabledSampleStreamWrappers;
                if (i10 >= hlsSampleStreamWrapperArr2.length) {
                    break;
                }
                hlsSampleStreamWrapperArr2[i10].seekToUs(j10, zSeekToUs);
                i10++;
            }
            if (zSeekToUs) {
                this.timestampAdjusterProvider.reset();
            }
        }
        return j10;
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x00d8  */
    @Override // androidx.media3.exoplayer.source.MediaPeriod
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long selectTracks(androidx.media3.exoplayer.trackselection.ExoTrackSelection[] r21, boolean[] r22, androidx.media3.exoplayer.source.SampleStream[] r23, boolean[] r24, long r25) {
        /*
            Method dump skipped, instruction units count: 279
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.hls.HlsMediaPeriod.selectTracks(androidx.media3.exoplayer.trackselection.ExoTrackSelection[], boolean[], androidx.media3.exoplayer.source.SampleStream[], boolean[], long):long");
    }
}
