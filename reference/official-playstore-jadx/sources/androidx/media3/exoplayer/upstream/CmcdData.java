package androidx.media3.exoplayer.upstream;

import android.net.Uri;
import android.text.TextUtils;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.UriUtil;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSpec;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import com.google.common.base.o;
import com.google.common.collect.f1;
import com.google.common.collect.h1;
import com.google.common.collect.i1;
import com.google.common.collect.k1;
import com.google.common.collect.o3;
import com.google.common.collect.o4;
import com.google.common.collect.t3;
import com.google.common.collect.v;
import j$.util.Objects;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes3.dex */
public final class CmcdData {
    private static final o COMMA_JOINER = new o(",");
    public static final String OBJECT_TYPE_AUDIO_ONLY = "a";
    public static final String OBJECT_TYPE_INIT_SEGMENT = "i";
    public static final String OBJECT_TYPE_MANIFEST = "m";
    public static final String OBJECT_TYPE_MUXED_AUDIO_AND_VIDEO = "av";
    public static final String OBJECT_TYPE_VIDEO_ONLY = "v";
    public static final String STREAMING_FORMAT_DASH = "d";
    public static final String STREAMING_FORMAT_HLS = "h";
    public static final String STREAMING_FORMAT_SS = "s";
    public static final String STREAM_TYPE_LIVE = "l";
    public static final String STREAM_TYPE_VOD = "v";
    private final CmcdObject cmcdObject;
    private final CmcdRequest cmcdRequest;
    private final CmcdSession cmcdSession;
    private final CmcdStatus cmcdStatus;
    private final int dataTransmissionMode;

    public static final class CmcdObject {
        public final int bitrateKbps;
        public final h1 customDataList;
        public final long objectDurationMs;
        public final String objectType;
        public final int topBitrateKbps;

        public static final class Builder {
            private h1 customDataList;
            private String objectType;
            private int bitrateKbps = C.RATE_UNSET_INT;
            private int topBitrateKbps = C.RATE_UNSET_INT;
            private long objectDurationMs = C.TIME_UNSET;

            public Builder() {
                f1 f1Var = h1.f14020l;
                this.customDataList = o3.f14078o;
            }

            public CmcdObject build() {
                return new CmcdObject(this);
            }

            public Builder setBitrateKbps(int i10) {
                ac.b.j(i10 >= 0 || i10 == -2147483647);
                this.bitrateKbps = i10;
                return this;
            }

            public Builder setCustomDataList(List<String> list) {
                this.customDataList = h1.n(list);
                return this;
            }

            public Builder setObjectDurationMs(long j10) {
                ac.b.j(j10 >= 0 || j10 == C.TIME_UNSET);
                this.objectDurationMs = j10;
                return this;
            }

            public Builder setObjectType(String str) {
                this.objectType = str;
                return this;
            }

            public Builder setTopBitrateKbps(int i10) {
                ac.b.j(i10 >= 0 || i10 == -2147483647);
                this.topBitrateKbps = i10;
                return this;
            }
        }

        public void populateCmcdDataMap(v vVar) {
            ArrayList arrayList = new ArrayList();
            if (this.bitrateKbps != -2147483647) {
                arrayList.add("br=" + this.bitrateKbps);
            }
            if (this.topBitrateKbps != -2147483647) {
                arrayList.add("tb=" + this.topBitrateKbps);
            }
            if (this.objectDurationMs != C.TIME_UNSET) {
                arrayList.add("d=" + this.objectDurationMs);
            }
            if (!TextUtils.isEmpty(this.objectType)) {
                arrayList.add("ot=" + this.objectType);
            }
            arrayList.addAll(this.customDataList);
            if (arrayList.isEmpty()) {
                return;
            }
            vVar.i(CmcdConfiguration.KEY_CMCD_OBJECT, arrayList);
        }

        private CmcdObject(Builder builder) {
            this.bitrateKbps = builder.bitrateKbps;
            this.topBitrateKbps = builder.topBitrateKbps;
            this.objectDurationMs = builder.objectDurationMs;
            this.objectType = builder.objectType;
            this.customDataList = builder.customDataList;
        }
    }

    public static final class CmcdRequest {
        public final long bufferLengthMs;
        public final h1 customDataList;
        public final long deadlineMs;
        public final long measuredThroughputInKbps;
        public final String nextObjectRequest;
        public final String nextRangeRequest;
        public final boolean startup;

        public static final class Builder {
            private h1 customDataList;
            private String nextObjectRequest;
            private String nextRangeRequest;
            private boolean startup;
            private long bufferLengthMs = C.TIME_UNSET;
            private long measuredThroughputInKbps = -2147483647L;
            private long deadlineMs = C.TIME_UNSET;

            public Builder() {
                f1 f1Var = h1.f14020l;
                this.customDataList = o3.f14078o;
            }

            public CmcdRequest build() {
                return new CmcdRequest(this);
            }

            public Builder setBufferLengthMs(long j10) {
                if (j10 == C.TIME_UNSET) {
                    this.bufferLengthMs = j10;
                    return this;
                }
                if (j10 < 0) {
                    throw new IllegalArgumentException();
                }
                this.bufferLengthMs = ((j10 + 50) / 100) * 100;
                return this;
            }

            public Builder setCustomDataList(List<String> list) {
                this.customDataList = h1.n(list);
                return this;
            }

            public Builder setDeadlineMs(long j10) {
                if (j10 == C.TIME_UNSET) {
                    this.deadlineMs = j10;
                    return this;
                }
                if (j10 < 0) {
                    throw new IllegalArgumentException();
                }
                this.deadlineMs = ((j10 + 50) / 100) * 100;
                return this;
            }

            public Builder setMeasuredThroughputInKbps(long j10) {
                if (j10 == -2147483647L) {
                    this.measuredThroughputInKbps = j10;
                    return this;
                }
                if (j10 < 0) {
                    throw new IllegalArgumentException();
                }
                this.measuredThroughputInKbps = ((j10 + 50) / 100) * 100;
                return this;
            }

            public Builder setNextObjectRequest(String str) {
                this.nextObjectRequest = str == null ? null : Uri.encode(str);
                return this;
            }

            public Builder setNextRangeRequest(String str) {
                this.nextRangeRequest = str;
                return this;
            }

            public Builder setStartup(boolean z) {
                this.startup = z;
                return this;
            }
        }

        public void populateCmcdDataMap(v vVar) {
            ArrayList arrayList = new ArrayList();
            if (this.bufferLengthMs != C.TIME_UNSET) {
                arrayList.add("bl=" + this.bufferLengthMs);
            }
            if (this.measuredThroughputInKbps != -2147483647L) {
                arrayList.add("mtp=" + this.measuredThroughputInKbps);
            }
            if (this.deadlineMs != C.TIME_UNSET) {
                arrayList.add("dl=" + this.deadlineMs);
            }
            if (this.startup) {
                arrayList.add(CmcdConfiguration.KEY_STARTUP);
            }
            if (!TextUtils.isEmpty(this.nextObjectRequest)) {
                arrayList.add(Util.formatInvariant("%s=\"%s\"", CmcdConfiguration.KEY_NEXT_OBJECT_REQUEST, this.nextObjectRequest));
            }
            if (!TextUtils.isEmpty(this.nextRangeRequest)) {
                arrayList.add(Util.formatInvariant("%s=\"%s\"", CmcdConfiguration.KEY_NEXT_RANGE_REQUEST, this.nextRangeRequest));
            }
            arrayList.addAll(this.customDataList);
            if (arrayList.isEmpty()) {
                return;
            }
            vVar.i(CmcdConfiguration.KEY_CMCD_REQUEST, arrayList);
        }

        private CmcdRequest(Builder builder) {
            this.bufferLengthMs = builder.bufferLengthMs;
            this.measuredThroughputInKbps = builder.measuredThroughputInKbps;
            this.deadlineMs = builder.deadlineMs;
            this.startup = builder.startup;
            this.nextObjectRequest = builder.nextObjectRequest;
            this.nextRangeRequest = builder.nextRangeRequest;
            this.customDataList = builder.customDataList;
        }
    }

    public static final class CmcdSession {
        public static final int VERSION = 1;
        public final String contentId;
        public final h1 customDataList;
        public final float playbackRate;
        public final String sessionId;
        public final String streamType;
        public final String streamingFormat;

        public static final class Builder {
            private String contentId;
            private h1 customDataList;
            private float playbackRate = -3.4028235E38f;
            private String sessionId;
            private String streamType;
            private String streamingFormat;

            public Builder() {
                f1 f1Var = h1.f14020l;
                this.customDataList = o3.f14078o;
            }

            public CmcdSession build() {
                return new CmcdSession(this);
            }

            public Builder setContentId(String str) {
                ac.b.j(str == null || str.length() <= 64);
                this.contentId = str;
                return this;
            }

            public Builder setCustomDataList(List<String> list) {
                this.customDataList = h1.n(list);
                return this;
            }

            public Builder setPlaybackRate(float f10) {
                ac.b.j(f10 > 0.0f || f10 == -3.4028235E38f);
                this.playbackRate = f10;
                return this;
            }

            public Builder setSessionId(String str) {
                ac.b.j(str == null || str.length() <= 64);
                this.sessionId = str;
                return this;
            }

            public Builder setStreamType(String str) {
                this.streamType = str;
                return this;
            }

            public Builder setStreamingFormat(String str) {
                this.streamingFormat = str;
                return this;
            }
        }

        public void populateCmcdDataMap(v vVar) {
            ArrayList arrayList = new ArrayList();
            if (!TextUtils.isEmpty(this.contentId)) {
                arrayList.add(Util.formatInvariant("%s=\"%s\"", CmcdConfiguration.KEY_CONTENT_ID, this.contentId));
            }
            if (!TextUtils.isEmpty(this.sessionId)) {
                arrayList.add(Util.formatInvariant("%s=\"%s\"", CmcdConfiguration.KEY_SESSION_ID, this.sessionId));
            }
            if (!TextUtils.isEmpty(this.streamingFormat)) {
                arrayList.add("sf=" + this.streamingFormat);
            }
            if (!TextUtils.isEmpty(this.streamType)) {
                arrayList.add("st=" + this.streamType);
            }
            float f10 = this.playbackRate;
            if (f10 != -3.4028235E38f && f10 != 1.0f) {
                arrayList.add(Util.formatInvariant("%s=%.2f", CmcdConfiguration.KEY_PLAYBACK_RATE, Float.valueOf(f10)));
            }
            arrayList.addAll(this.customDataList);
            if (arrayList.isEmpty()) {
                return;
            }
            vVar.i(CmcdConfiguration.KEY_CMCD_SESSION, arrayList);
        }

        private CmcdSession(Builder builder) {
            this.contentId = builder.contentId;
            this.sessionId = builder.sessionId;
            this.streamingFormat = builder.streamingFormat;
            this.streamType = builder.streamType;
            this.playbackRate = builder.playbackRate;
            this.customDataList = builder.customDataList;
        }
    }

    public static final class CmcdStatus {
        public final boolean bufferStarvation;
        public final h1 customDataList;
        public final int maximumRequestedThroughputKbps;

        public static final class Builder {
            private boolean bufferStarvation;
            private h1 customDataList;
            private int maximumRequestedThroughputKbps = C.RATE_UNSET_INT;

            public Builder() {
                f1 f1Var = h1.f14020l;
                this.customDataList = o3.f14078o;
            }

            public CmcdStatus build() {
                return new CmcdStatus(this);
            }

            public Builder setBufferStarvation(boolean z) {
                this.bufferStarvation = z;
                return this;
            }

            public Builder setCustomDataList(List<String> list) {
                this.customDataList = h1.n(list);
                return this;
            }

            public Builder setMaximumRequestedThroughputKbps(int i10) {
                ac.b.j(i10 >= 0 || i10 == -2147483647);
                if (i10 != -2147483647) {
                    i10 = ((i10 + 50) / 100) * 100;
                }
                this.maximumRequestedThroughputKbps = i10;
                return this;
            }
        }

        public void populateCmcdDataMap(v vVar) {
            ArrayList arrayList = new ArrayList();
            if (this.maximumRequestedThroughputKbps != -2147483647) {
                arrayList.add("rtp=" + this.maximumRequestedThroughputKbps);
            }
            if (this.bufferStarvation) {
                arrayList.add(CmcdConfiguration.KEY_BUFFER_STARVATION);
            }
            arrayList.addAll(this.customDataList);
            if (arrayList.isEmpty()) {
                return;
            }
            vVar.i(CmcdConfiguration.KEY_CMCD_STATUS, arrayList);
        }

        private CmcdStatus(Builder builder) {
            this.maximumRequestedThroughputKbps = builder.maximumRequestedThroughputKbps;
            this.bufferStarvation = builder.bufferStarvation;
            this.customDataList = builder.customDataList;
        }
    }

    public static final class Factory {
        private static final Pattern CUSTOM_KEY_NAME_PATTERN = Pattern.compile(".*-.*");
        private final CmcdConfiguration cmcdConfiguration;
        private boolean didRebuffer;
        private boolean isBufferEmpty;
        private Boolean isLive;
        private String nextObjectRequest;
        private String nextRangeRequest;
        private String objectType;
        private final String streamingFormat;
        private ExoTrackSelection trackSelection;
        private long bufferedDurationUs = C.TIME_UNSET;
        private float playbackRate = -3.4028235E38f;
        private long chunkDurationUs = C.TIME_UNSET;

        public Factory(CmcdConfiguration cmcdConfiguration, String str) {
            this.cmcdConfiguration = cmcdConfiguration;
            this.streamingFormat = str;
        }

        private static String getObjectTypeFromFormat(Format format) {
            String audioMediaMimeType = MimeTypes.getAudioMediaMimeType(format.codecs);
            String videoMediaMimeType = MimeTypes.getVideoMediaMimeType(format.codecs);
            if (audioMediaMimeType != null && videoMediaMimeType != null) {
                return CmcdData.OBJECT_TYPE_MUXED_AUDIO_AND_VIDEO;
            }
            int trackType = MimeTypes.getTrackType(format.sampleMimeType);
            if (trackType == -1) {
                trackType = MimeTypes.getTrackType(format.containerMimeType);
            }
            if (trackType == 1) {
                return CmcdData.OBJECT_TYPE_AUDIO_ONLY;
            }
            if (trackType == 2) {
                return "v";
            }
            return null;
        }

        private static boolean isManifestObjectType(String str) {
            return Objects.equals(str, CmcdData.OBJECT_TYPE_MANIFEST);
        }

        private static boolean isMediaObjectType(String str) {
            return Objects.equals(str, CmcdData.OBJECT_TYPE_AUDIO_ONLY) || Objects.equals(str, "v") || Objects.equals(str, CmcdData.OBJECT_TYPE_MUXED_AUDIO_AND_VIDEO);
        }

        private void validateCustomDataListFormat(List<String> list) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                ac.b.s(CUSTOM_KEY_NAME_PATTERN.matcher(Util.split(it.next(), "=")[0]).matches());
            }
        }

        public CmcdData createCmcdData() {
            int iCeilDivide;
            int requestedMaximumThroughputKbps;
            int iCeilDivide2;
            boolean zIsManifestObjectType = isManifestObjectType(this.objectType);
            if (!zIsManifestObjectType) {
                ac.b.p(this.trackSelection, "Track selection must be set");
            }
            if (this.objectType == null) {
                ExoTrackSelection exoTrackSelection = this.trackSelection;
                exoTrackSelection.getClass();
                this.objectType = getObjectTypeFromFormat(exoTrackSelection.getSelectedFormat());
            }
            boolean zIsMediaObjectType = isMediaObjectType(this.objectType);
            if (zIsMediaObjectType) {
                ac.b.t(this.bufferedDurationUs != C.TIME_UNSET, "Buffered duration must be set");
                ac.b.t(this.chunkDurationUs != C.TIME_UNSET, "Chunk duration must be set");
            }
            i1 customData = this.cmcdConfiguration.requestConfig.getCustomData();
            t3 t3Var = customData.f14025o;
            o4 it = t3Var.keySet().iterator();
            while (it.hasNext()) {
                validateCustomDataListFormat(customData.get((String) it.next()));
            }
            if (zIsManifestObjectType) {
                iCeilDivide = C.RATE_UNSET_INT;
                requestedMaximumThroughputKbps = -2147483647;
                iCeilDivide2 = -2147483647;
            } else {
                ExoTrackSelection exoTrackSelection2 = this.trackSelection;
                exoTrackSelection2.getClass();
                int iMax = exoTrackSelection2.getSelectedFormat().bitrate;
                iCeilDivide = Util.ceilDivide(iMax, 1000);
                TrackGroup trackGroup = exoTrackSelection2.getTrackGroup();
                for (int i10 = 0; i10 < trackGroup.length; i10++) {
                    iMax = Math.max(iMax, trackGroup.getFormat(i10).bitrate);
                }
                iCeilDivide2 = Util.ceilDivide(iMax, 1000);
                jCeilDivide = exoTrackSelection2.getLatestBitrateEstimate() != -2147483647L ? Util.ceilDivide(exoTrackSelection2.getLatestBitrateEstimate(), 1000L) : -2147483647L;
                requestedMaximumThroughputKbps = this.cmcdConfiguration.requestConfig.getRequestedMaximumThroughputKbps(iCeilDivide);
            }
            CmcdObject.Builder builder = new CmcdObject.Builder();
            if (this.cmcdConfiguration.isBitrateLoggingAllowed()) {
                builder.setBitrateKbps(iCeilDivide);
            }
            if (this.cmcdConfiguration.isTopBitrateLoggingAllowed()) {
                builder.setTopBitrateKbps(iCeilDivide2);
            }
            if (zIsMediaObjectType && this.cmcdConfiguration.isObjectDurationLoggingAllowed()) {
                builder.setObjectDurationMs(Util.usToMs(this.chunkDurationUs));
            }
            if (this.cmcdConfiguration.isObjectTypeLoggingAllowed()) {
                builder.setObjectType(this.objectType);
            }
            if (t3Var.containsKey(CmcdConfiguration.KEY_CMCD_OBJECT)) {
                builder.setCustomDataList(customData.get(CmcdConfiguration.KEY_CMCD_OBJECT));
            }
            CmcdRequest.Builder builder2 = new CmcdRequest.Builder();
            if (zIsMediaObjectType) {
                if (this.cmcdConfiguration.isBufferLengthLoggingAllowed()) {
                    builder2.setBufferLengthMs(Util.usToMs(this.bufferedDurationUs));
                }
                if (this.cmcdConfiguration.isDeadlineLoggingAllowed()) {
                    builder2.setDeadlineMs(Util.usToMs((long) (this.bufferedDurationUs / this.playbackRate)));
                }
            }
            if (this.cmcdConfiguration.isMeasuredThroughputLoggingAllowed()) {
                builder2.setMeasuredThroughputInKbps(jCeilDivide);
            }
            if (this.cmcdConfiguration.isStartupLoggingAllowed()) {
                builder2.setStartup(this.didRebuffer || this.isBufferEmpty);
            }
            if (this.cmcdConfiguration.isNextObjectRequestLoggingAllowed()) {
                builder2.setNextObjectRequest(this.nextObjectRequest);
            }
            if (this.cmcdConfiguration.isNextRangeRequestLoggingAllowed()) {
                builder2.setNextRangeRequest(this.nextRangeRequest);
            }
            if (t3Var.containsKey(CmcdConfiguration.KEY_CMCD_REQUEST)) {
                builder2.setCustomDataList(customData.get(CmcdConfiguration.KEY_CMCD_REQUEST));
            }
            CmcdSession.Builder builder3 = new CmcdSession.Builder();
            if (this.cmcdConfiguration.isContentIdLoggingAllowed()) {
                builder3.setContentId(this.cmcdConfiguration.contentId);
            }
            if (this.cmcdConfiguration.isSessionIdLoggingAllowed()) {
                builder3.setSessionId(this.cmcdConfiguration.sessionId);
            }
            if (this.cmcdConfiguration.isStreamingFormatLoggingAllowed()) {
                builder3.setStreamingFormat(this.streamingFormat);
            }
            if (this.isLive != null && this.cmcdConfiguration.isStreamTypeLoggingAllowed()) {
                Boolean bool = this.isLive;
                bool.getClass();
                builder3.setStreamType(bool.booleanValue() ? CmcdData.STREAM_TYPE_LIVE : "v");
            }
            if (this.cmcdConfiguration.isPlaybackRateLoggingAllowed()) {
                builder3.setPlaybackRate(this.playbackRate);
            }
            if (t3Var.containsKey(CmcdConfiguration.KEY_CMCD_SESSION)) {
                builder3.setCustomDataList(customData.get(CmcdConfiguration.KEY_CMCD_SESSION));
            }
            CmcdStatus.Builder builder4 = new CmcdStatus.Builder();
            if (this.cmcdConfiguration.isMaximumRequestThroughputLoggingAllowed()) {
                builder4.setMaximumRequestedThroughputKbps(requestedMaximumThroughputKbps);
            }
            if (this.cmcdConfiguration.isBufferStarvationLoggingAllowed()) {
                builder4.setBufferStarvation(this.didRebuffer);
            }
            if (t3Var.containsKey(CmcdConfiguration.KEY_CMCD_STATUS)) {
                builder4.setCustomDataList(customData.get(CmcdConfiguration.KEY_CMCD_STATUS));
            }
            return new CmcdData(builder.build(), builder2.build(), builder3.build(), builder4.build(), this.cmcdConfiguration.dataTransmissionMode);
        }

        public Factory setBufferedDurationUs(long j10) {
            ac.b.j(j10 >= 0);
            this.bufferedDurationUs = j10;
            return this;
        }

        public Factory setChunkDurationUs(long j10) {
            ac.b.j(j10 >= 0);
            this.chunkDurationUs = j10;
            return this;
        }

        public Factory setDidRebuffer(boolean z) {
            this.didRebuffer = z;
            return this;
        }

        public Factory setIsBufferEmpty(boolean z) {
            this.isBufferEmpty = z;
            return this;
        }

        public Factory setIsLive(boolean z) {
            this.isLive = Boolean.valueOf(z);
            return this;
        }

        public Factory setNextObjectRequest(String str) {
            this.nextObjectRequest = str;
            return this;
        }

        public Factory setNextRangeRequest(String str) {
            this.nextRangeRequest = str;
            return this;
        }

        public Factory setObjectType(String str) {
            this.objectType = str;
            return this;
        }

        public Factory setPlaybackRate(float f10) {
            ac.b.j(f10 == -3.4028235E38f || f10 > 0.0f);
            this.playbackRate = f10;
            return this;
        }

        public Factory setTrackSelection(ExoTrackSelection exoTrackSelection) {
            this.trackSelection = exoTrackSelection;
            return this;
        }
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface ObjectType {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface StreamType {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface StreamingFormat {
    }

    public static DataSpec removeFromDataSpec(DataSpec dataSpec) {
        Uri uriRemoveFromUri = removeFromUri(dataSpec.uri);
        if (!Objects.equals(uriRemoveFromUri, dataSpec.uri)) {
            dataSpec = dataSpec.withUri(uriRemoveFromUri);
        }
        if (!dataSpec.httpRequestHeaders.containsKey(CmcdConfiguration.KEY_CMCD_OBJECT) && !dataSpec.httpRequestHeaders.containsKey(CmcdConfiguration.KEY_CMCD_REQUEST) && !dataSpec.httpRequestHeaders.containsKey(CmcdConfiguration.KEY_CMCD_STATUS) && !dataSpec.httpRequestHeaders.containsKey(CmcdConfiguration.KEY_CMCD_SESSION)) {
            return dataSpec;
        }
        k1 k1Var = new k1(4);
        for (Map.Entry<String, String> entry : dataSpec.httpRequestHeaders.entrySet()) {
            if (!entry.getKey().equals(CmcdConfiguration.KEY_CMCD_OBJECT) && !entry.getKey().equals(CmcdConfiguration.KEY_CMCD_REQUEST) && !entry.getKey().equals(CmcdConfiguration.KEY_CMCD_STATUS) && !entry.getKey().equals(CmcdConfiguration.KEY_CMCD_SESSION)) {
                k1Var.d(entry);
            }
        }
        return dataSpec.withRequestHeaders(k1Var.a(true));
    }

    public static Uri removeFromUri(Uri uri) {
        return (!uri.isHierarchical() || uri.getQueryParameter(CmcdConfiguration.CMCD_QUERY_PARAMETER_KEY) == null) ? uri : UriUtil.removeQueryParameter(uri, CmcdConfiguration.CMCD_QUERY_PARAMETER_KEY);
    }

    public DataSpec addToDataSpec(DataSpec dataSpec) {
        v vVar = new v();
        this.cmcdObject.populateCmcdDataMap(vVar);
        this.cmcdRequest.populateCmcdDataMap(vVar);
        this.cmcdSession.populateCmcdDataMap(vVar);
        this.cmcdStatus.populateCmcdDataMap(vVar);
        if (this.dataTransmissionMode != 0) {
            ArrayList arrayList = new ArrayList();
            Iterator it = vVar.a().values().iterator();
            while (it.hasNext()) {
                arrayList.addAll((Collection) it.next());
            }
            Collections.sort(arrayList);
            return dataSpec.buildUpon().setUri(dataSpec.uri.buildUpon().appendQueryParameter(CmcdConfiguration.CMCD_QUERY_PARAMETER_KEY, COMMA_JOINER.b(arrayList)).build()).build();
        }
        k1 k1Var = new k1(4);
        for (String str : vVar.keySet()) {
            List list = vVar.get((Object) str);
            Collections.sort(list);
            k1Var.c(str, COMMA_JOINER.b(list));
        }
        return dataSpec.withAdditionalHeaders(k1Var.a(true));
    }

    private CmcdData(CmcdObject cmcdObject, CmcdRequest cmcdRequest, CmcdSession cmcdSession, CmcdStatus cmcdStatus, int i10) {
        this.cmcdObject = cmcdObject;
        this.cmcdRequest = cmcdRequest;
        this.cmcdSession = cmcdSession;
        this.cmcdStatus = cmcdStatus;
        this.dataTransmissionMode = i10;
    }
}
