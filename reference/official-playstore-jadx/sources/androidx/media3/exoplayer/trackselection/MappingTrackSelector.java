package androidx.media3.exoplayer.trackselection;

import android.util.Pair;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.RendererConfiguration;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.t0;
import j$.util.Objects;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public abstract class MappingTrackSelector extends TrackSelector {
    private MappedTrackInfo currentMappedTrackInfo;

    private static int findRenderer(RendererCapabilities[] rendererCapabilitiesArr, TrackGroup trackGroup, int[] iArr, boolean z) throws ExoPlaybackException {
        int length = rendererCapabilitiesArr.length;
        int i10 = 0;
        boolean z5 = true;
        for (int i11 = 0; i11 < rendererCapabilitiesArr.length; i11++) {
            RendererCapabilities rendererCapabilities = rendererCapabilitiesArr[i11];
            int iMax = 0;
            for (int i12 = 0; i12 < trackGroup.length; i12++) {
                iMax = Math.max(iMax, t0.k(rendererCapabilities.supportsFormat(trackGroup.getFormat(i12))));
            }
            boolean z10 = iArr[i11] == 0;
            if (iMax > i10 || (iMax == i10 && z && !z5 && z10)) {
                length = i11;
                z5 = z10;
                i10 = iMax;
            }
        }
        return length;
    }

    private static int[] getFormatSupport(RendererCapabilities rendererCapabilities, TrackGroup trackGroup) throws ExoPlaybackException {
        int[] iArr = new int[trackGroup.length];
        for (int i10 = 0; i10 < trackGroup.length; i10++) {
            iArr[i10] = rendererCapabilities.supportsFormat(trackGroup.getFormat(i10));
        }
        return iArr;
    }

    private static int[] getMixedMimeTypeAdaptationSupports(RendererCapabilities[] rendererCapabilitiesArr) throws ExoPlaybackException {
        int length = rendererCapabilitiesArr.length;
        int[] iArr = new int[length];
        for (int i10 = 0; i10 < length; i10++) {
            iArr[i10] = rendererCapabilitiesArr[i10].supportsMixedMimeTypeAdaptation();
        }
        return iArr;
    }

    public final MappedTrackInfo getCurrentMappedTrackInfo() {
        return this.currentMappedTrackInfo;
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelector
    public final void onSelectionActivated(Object obj) {
        this.currentMappedTrackInfo = (MappedTrackInfo) obj;
    }

    public abstract Pair<RendererConfiguration[], ExoTrackSelection[]> selectTracks(MappedTrackInfo mappedTrackInfo, int[][][] iArr, int[] iArr2, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline) throws ExoPlaybackException;

    @Override // androidx.media3.exoplayer.trackselection.TrackSelector
    public final TrackSelectorResult selectTracks(RendererCapabilities[] rendererCapabilitiesArr, TrackGroupArray trackGroupArray, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline) throws ExoPlaybackException {
        int[] iArr = new int[rendererCapabilitiesArr.length + 1];
        int length = rendererCapabilitiesArr.length + 1;
        TrackGroup[][] trackGroupArr = new TrackGroup[length][];
        int[][][] iArr2 = new int[rendererCapabilitiesArr.length + 1][][];
        for (int i10 = 0; i10 < length; i10++) {
            int i11 = trackGroupArray.length;
            trackGroupArr[i10] = new TrackGroup[i11];
            iArr2[i10] = new int[i11][];
        }
        int[] mixedMimeTypeAdaptationSupports = getMixedMimeTypeAdaptationSupports(rendererCapabilitiesArr);
        for (int i12 = 0; i12 < trackGroupArray.length; i12++) {
            TrackGroup trackGroup = trackGroupArray.get(i12);
            int iFindRenderer = findRenderer(rendererCapabilitiesArr, trackGroup, iArr, trackGroup.type == 5);
            int[] formatSupport = iFindRenderer == rendererCapabilitiesArr.length ? new int[trackGroup.length] : getFormatSupport(rendererCapabilitiesArr[iFindRenderer], trackGroup);
            int i13 = iArr[iFindRenderer];
            trackGroupArr[iFindRenderer][i13] = trackGroup;
            iArr2[iFindRenderer][i13] = formatSupport;
            iArr[iFindRenderer] = i13 + 1;
        }
        TrackGroupArray[] trackGroupArrayArr = new TrackGroupArray[rendererCapabilitiesArr.length];
        String[] strArr = new String[rendererCapabilitiesArr.length];
        int[] iArr3 = new int[rendererCapabilitiesArr.length];
        for (int i14 = 0; i14 < rendererCapabilitiesArr.length; i14++) {
            int i15 = iArr[i14];
            trackGroupArrayArr[i14] = new TrackGroupArray((TrackGroup[]) Util.nullSafeArrayCopy(trackGroupArr[i14], i15));
            iArr2[i14] = (int[][]) Util.nullSafeArrayCopy(iArr2[i14], i15);
            strArr[i14] = rendererCapabilitiesArr[i14].getName();
            iArr3[i14] = rendererCapabilitiesArr[i14].getTrackType();
        }
        MappedTrackInfo mappedTrackInfo = new MappedTrackInfo(strArr, iArr3, trackGroupArrayArr, mixedMimeTypeAdaptationSupports, iArr2, new TrackGroupArray((TrackGroup[]) Util.nullSafeArrayCopy(trackGroupArr[rendererCapabilitiesArr.length], iArr[rendererCapabilitiesArr.length])));
        Pair<RendererConfiguration[], ExoTrackSelection[]> pairSelectTracks = selectTracks(mappedTrackInfo, iArr2, mixedMimeTypeAdaptationSupports, mediaPeriodId, timeline);
        return new TrackSelectorResult((RendererConfiguration[]) pairSelectTracks.first, (ExoTrackSelection[]) pairSelectTracks.second, TrackSelectionUtil.buildTracks(mappedTrackInfo, (TrackSelection[]) pairSelectTracks.second), mappedTrackInfo);
    }

    public static final class MappedTrackInfo {
        public static final int RENDERER_SUPPORT_EXCEEDS_CAPABILITIES_TRACKS = 2;
        public static final int RENDERER_SUPPORT_NO_TRACKS = 0;
        public static final int RENDERER_SUPPORT_PLAYABLE_TRACKS = 3;
        public static final int RENDERER_SUPPORT_UNSUPPORTED_TRACKS = 1;
        private final int rendererCount;
        private final int[][][] rendererFormatSupports;
        private final int[] rendererMixedMimeTypeAdaptiveSupports;
        private final String[] rendererNames;
        private final TrackGroupArray[] rendererTrackGroups;
        private final int[] rendererTrackTypes;
        private final TrackGroupArray unmappedTrackGroups;

        @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
        @Documented
        @Retention(RetentionPolicy.SOURCE)
        public @interface RendererSupport {
        }

        public MappedTrackInfo(String[] strArr, int[] iArr, TrackGroupArray[] trackGroupArrayArr, int[] iArr2, int[][][] iArr3, TrackGroupArray trackGroupArray) {
            this.rendererNames = strArr;
            this.rendererTrackTypes = iArr;
            this.rendererTrackGroups = trackGroupArrayArr;
            this.rendererFormatSupports = iArr3;
            this.rendererMixedMimeTypeAdaptiveSupports = iArr2;
            this.unmappedTrackGroups = trackGroupArray;
            this.rendererCount = iArr.length;
        }

        public int getAdaptiveSupport(int i10, int i11, boolean z) {
            int i12 = this.rendererTrackGroups[i10].get(i11).length;
            int[] iArr = new int[i12];
            int i13 = 0;
            for (int i14 = 0; i14 < i12; i14++) {
                int trackSupport = getTrackSupport(i10, i11, i14);
                if (trackSupport == 4 || (z && trackSupport == 3)) {
                    iArr[i13] = i14;
                    i13++;
                }
            }
            return getAdaptiveSupport(i10, i11, Arrays.copyOf(iArr, i13));
        }

        public int getCapabilities(int i10, int i11, int i12) {
            return this.rendererFormatSupports[i10][i11][i12];
        }

        public int getRendererCount() {
            return this.rendererCount;
        }

        public String getRendererName(int i10) {
            return this.rendererNames[i10];
        }

        public int getRendererSupport(int i10) {
            int iMax = 0;
            for (int[] iArr : this.rendererFormatSupports[i10]) {
                for (int i11 : iArr) {
                    int iK = t0.k(i11);
                    int i12 = 1;
                    if (iK != 0 && iK != 1 && iK != 2) {
                        if (iK != 3) {
                            if (iK == 4) {
                                return 3;
                            }
                            throw new IllegalStateException();
                        }
                        i12 = 2;
                    }
                    iMax = Math.max(iMax, i12);
                }
            }
            return iMax;
        }

        public int getRendererType(int i10) {
            return this.rendererTrackTypes[i10];
        }

        public TrackGroupArray getTrackGroups(int i10) {
            return this.rendererTrackGroups[i10];
        }

        public int getTrackSupport(int i10, int i11, int i12) {
            return t0.k(getCapabilities(i10, i11, i12));
        }

        public int getTypeSupport(int i10) {
            int iMax = 0;
            for (int i11 = 0; i11 < this.rendererCount; i11++) {
                if (this.rendererTrackTypes[i11] == i10) {
                    iMax = Math.max(iMax, getRendererSupport(i11));
                }
            }
            return iMax;
        }

        public TrackGroupArray getUnmappedTrackGroups() {
            return this.unmappedTrackGroups;
        }

        public int getAdaptiveSupport(int i10, int i11, int[] iArr) {
            int i12 = 0;
            int iMin = 16;
            String str = null;
            boolean z = false;
            int i13 = 0;
            while (i12 < iArr.length) {
                String str2 = this.rendererTrackGroups[i10].get(i11).getFormat(iArr[i12]).sampleMimeType;
                int i14 = i13 + 1;
                if (i13 == 0) {
                    str = str2;
                } else {
                    z |= !Objects.equals(str, str2);
                }
                iMin = Math.min(iMin, t0.h(this.rendererFormatSupports[i10][i11][i12]));
                i12++;
                i13 = i14;
            }
            return z ? Math.min(iMin, this.rendererMixedMimeTypeAdaptiveSupports[i10]) : iMin;
        }
    }
}
