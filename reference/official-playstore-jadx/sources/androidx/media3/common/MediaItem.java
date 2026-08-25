package androidx.media3.common;

import android.net.Uri;
import android.os.Bundle;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.Util;
import com.google.common.collect.e1;
import com.google.common.collect.f1;
import com.google.common.collect.h1;
import com.google.common.collect.l1;
import com.google.common.collect.o3;
import com.google.common.collect.t3;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
public final class MediaItem {
    public static final String DEFAULT_MEDIA_ID = "";
    public final ClippingConfiguration clippingConfiguration;

    @Deprecated
    public final ClippingProperties clippingProperties;
    public final LiveConfiguration liveConfiguration;
    public final LocalConfiguration localConfiguration;
    public final String mediaId;
    public final MediaMetadata mediaMetadata;

    @Deprecated
    public final LocalConfiguration playbackProperties;
    public final RequestMetadata requestMetadata;
    public static final MediaItem EMPTY = new Builder().build();
    private static final String FIELD_MEDIA_ID = Util.intToStringMaxRadix(0);
    private static final String FIELD_LIVE_CONFIGURATION = Util.intToStringMaxRadix(1);
    private static final String FIELD_MEDIA_METADATA = Util.intToStringMaxRadix(2);
    private static final String FIELD_CLIPPING_PROPERTIES = Util.intToStringMaxRadix(3);
    private static final String FIELD_REQUEST_METADATA = Util.intToStringMaxRadix(4);
    private static final String FIELD_LOCAL_CONFIGURATION = Util.intToStringMaxRadix(5);

    public static final class AdsConfiguration {
        private static final String FIELD_AD_TAG_URI = Util.intToStringMaxRadix(0);
        public final Uri adTagUri;
        public final Object adsId;

        public static final class Builder {
            private Uri adTagUri;
            private Object adsId;

            public Builder(Uri uri) {
                this.adTagUri = uri;
            }

            public AdsConfiguration build() {
                return new AdsConfiguration(this);
            }

            public Builder setAdTagUri(Uri uri) {
                this.adTagUri = uri;
                return this;
            }

            public Builder setAdsId(Object obj) {
                this.adsId = obj;
                return this;
            }
        }

        public static AdsConfiguration fromBundle(Bundle bundle) {
            Uri uri = (Uri) bundle.getParcelable(FIELD_AD_TAG_URI);
            uri.getClass();
            return new Builder(uri).build();
        }

        public Builder buildUpon() {
            return new Builder(this.adTagUri).setAdsId(this.adsId);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AdsConfiguration)) {
                return false;
            }
            AdsConfiguration adsConfiguration = (AdsConfiguration) obj;
            return this.adTagUri.equals(adsConfiguration.adTagUri) && Objects.equals(this.adsId, adsConfiguration.adsId);
        }

        public int hashCode() {
            int iHashCode = this.adTagUri.hashCode() * 31;
            Object obj = this.adsId;
            return iHashCode + (obj != null ? obj.hashCode() : 0);
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putParcelable(FIELD_AD_TAG_URI, this.adTagUri);
            return bundle;
        }

        private AdsConfiguration(Builder builder) {
            this.adTagUri = builder.adTagUri;
            this.adsId = builder.adsId;
        }
    }

    public static final class Builder {
        private AdsConfiguration adsConfiguration;
        private ClippingConfiguration.Builder clippingConfiguration;
        private String customCacheKey;
        private DrmConfiguration.Builder drmConfiguration;
        private long imageDurationMs;
        private LiveConfiguration.Builder liveConfiguration;
        private String mediaId;
        private MediaMetadata mediaMetadata;
        private String mimeType;
        private RequestMetadata requestMetadata;
        private List<StreamKey> streamKeys;
        private h1 subtitleConfigurations;
        private Object tag;
        private Uri uri;

        public MediaItem build() {
            LocalConfiguration localConfiguration;
            ac.b.s(this.drmConfiguration.licenseUri == null || this.drmConfiguration.scheme != null);
            Uri uri = this.uri;
            if (uri != null) {
                localConfiguration = new LocalConfiguration(uri, this.mimeType, this.drmConfiguration.scheme != null ? this.drmConfiguration.build() : null, this.adsConfiguration, this.streamKeys, this.customCacheKey, this.subtitleConfigurations, this.tag, this.imageDurationMs);
            } else {
                localConfiguration = null;
            }
            String str = this.mediaId;
            if (str == null) {
                str = "";
            }
            String str2 = str;
            ClippingProperties clippingPropertiesBuildClippingProperties = this.clippingConfiguration.buildClippingProperties();
            LiveConfiguration liveConfigurationBuild = this.liveConfiguration.build();
            MediaMetadata mediaMetadata = this.mediaMetadata;
            if (mediaMetadata == null) {
                mediaMetadata = MediaMetadata.EMPTY;
            }
            return new MediaItem(str2, clippingPropertiesBuildClippingProperties, localConfiguration, liveConfigurationBuild, mediaMetadata, this.requestMetadata);
        }

        @Deprecated
        public Builder setAdTagUri(String str) {
            return setAdTagUri(str != null ? Uri.parse(str) : null);
        }

        public Builder setAdsConfiguration(AdsConfiguration adsConfiguration) {
            this.adsConfiguration = adsConfiguration;
            return this;
        }

        @Deprecated
        public Builder setClipEndPositionMs(long j10) {
            this.clippingConfiguration.setEndPositionMs(j10);
            return this;
        }

        @Deprecated
        public Builder setClipRelativeToDefaultPosition(boolean z) {
            this.clippingConfiguration.setRelativeToDefaultPosition(z);
            return this;
        }

        @Deprecated
        public Builder setClipRelativeToLiveWindow(boolean z) {
            this.clippingConfiguration.setRelativeToLiveWindow(z);
            return this;
        }

        @Deprecated
        public Builder setClipStartPositionMs(long j10) {
            this.clippingConfiguration.setStartPositionMs(j10);
            return this;
        }

        @Deprecated
        public Builder setClipStartsAtKeyFrame(boolean z) {
            this.clippingConfiguration.setStartsAtKeyFrame(z);
            return this;
        }

        public Builder setClippingConfiguration(ClippingConfiguration clippingConfiguration) {
            this.clippingConfiguration = clippingConfiguration.buildUpon();
            return this;
        }

        public Builder setCustomCacheKey(String str) {
            this.customCacheKey = str;
            return this;
        }

        public Builder setDrmConfiguration(DrmConfiguration drmConfiguration) {
            this.drmConfiguration = drmConfiguration != null ? drmConfiguration.buildUpon() : new DrmConfiguration.Builder();
            return this;
        }

        @Deprecated
        public Builder setDrmForceDefaultLicenseUri(boolean z) {
            this.drmConfiguration.setForceDefaultLicenseUri(z);
            return this;
        }

        @Deprecated
        public Builder setDrmKeySetId(byte[] bArr) {
            this.drmConfiguration.setKeySetId(bArr);
            return this;
        }

        @Deprecated
        public Builder setDrmLicenseRequestHeaders(Map<String, String> map) {
            DrmConfiguration.Builder builder = this.drmConfiguration;
            if (map == null) {
                map = t3.f14114q;
            }
            builder.setLicenseRequestHeaders(map);
            return this;
        }

        @Deprecated
        public Builder setDrmLicenseUri(Uri uri) {
            this.drmConfiguration.setLicenseUri(uri);
            return this;
        }

        @Deprecated
        public Builder setDrmMultiSession(boolean z) {
            this.drmConfiguration.setMultiSession(z);
            return this;
        }

        @Deprecated
        public Builder setDrmPlayClearContentWithoutKey(boolean z) {
            this.drmConfiguration.setPlayClearContentWithoutKey(z);
            return this;
        }

        @Deprecated
        public Builder setDrmSessionForClearPeriods(boolean z) {
            this.drmConfiguration.setForceSessionsForAudioAndVideoTracks(z);
            return this;
        }

        @Deprecated
        public Builder setDrmSessionForClearTypes(List<Integer> list) {
            DrmConfiguration.Builder builder = this.drmConfiguration;
            if (list == null) {
                f1 f1Var = h1.f14020l;
                list = o3.f14078o;
            }
            builder.setForcedSessionTrackTypes(list);
            return this;
        }

        @Deprecated
        public Builder setDrmUuid(UUID uuid) {
            this.drmConfiguration.setNullableScheme(uuid);
            return this;
        }

        public Builder setImageDurationMs(long j10) {
            ac.b.j(j10 > 0 || j10 == C.TIME_UNSET);
            this.imageDurationMs = j10;
            return this;
        }

        public Builder setLiveConfiguration(LiveConfiguration liveConfiguration) {
            this.liveConfiguration = liveConfiguration.buildUpon();
            return this;
        }

        @Deprecated
        public Builder setLiveMaxOffsetMs(long j10) {
            this.liveConfiguration.setMaxOffsetMs(j10);
            return this;
        }

        @Deprecated
        public Builder setLiveMaxPlaybackSpeed(float f10) {
            this.liveConfiguration.setMaxPlaybackSpeed(f10);
            return this;
        }

        @Deprecated
        public Builder setLiveMinOffsetMs(long j10) {
            this.liveConfiguration.setMinOffsetMs(j10);
            return this;
        }

        @Deprecated
        public Builder setLiveMinPlaybackSpeed(float f10) {
            this.liveConfiguration.setMinPlaybackSpeed(f10);
            return this;
        }

        @Deprecated
        public Builder setLiveTargetOffsetMs(long j10) {
            this.liveConfiguration.setTargetOffsetMs(j10);
            return this;
        }

        public Builder setMediaId(String str) {
            str.getClass();
            this.mediaId = str;
            return this;
        }

        public Builder setMediaMetadata(MediaMetadata mediaMetadata) {
            this.mediaMetadata = mediaMetadata;
            return this;
        }

        public Builder setMimeType(String str) {
            this.mimeType = str;
            return this;
        }

        public Builder setRequestMetadata(RequestMetadata requestMetadata) {
            this.requestMetadata = requestMetadata;
            return this;
        }

        public Builder setStreamKeys(List<StreamKey> list) {
            this.streamKeys = (list == null || list.isEmpty()) ? Collections.EMPTY_LIST : Collections.unmodifiableList(new ArrayList(list));
            return this;
        }

        public Builder setSubtitleConfigurations(List<SubtitleConfiguration> list) {
            this.subtitleConfigurations = h1.n(list);
            return this;
        }

        @Deprecated
        public Builder setSubtitles(List<Subtitle> list) {
            h1 h1VarN;
            if (list != null) {
                h1VarN = h1.n(list);
            } else {
                f1 f1Var = h1.f14020l;
                h1VarN = o3.f14078o;
            }
            this.subtitleConfigurations = h1VarN;
            return this;
        }

        public Builder setTag(Object obj) {
            this.tag = obj;
            return this;
        }

        public Builder setUri(String str) {
            return setUri(str == null ? null : Uri.parse(str));
        }

        public Builder() {
            this.clippingConfiguration = new ClippingConfiguration.Builder();
            this.drmConfiguration = new DrmConfiguration.Builder();
            this.streamKeys = Collections.EMPTY_LIST;
            f1 f1Var = h1.f14020l;
            this.subtitleConfigurations = o3.f14078o;
            this.liveConfiguration = new LiveConfiguration.Builder();
            this.requestMetadata = RequestMetadata.EMPTY;
            this.imageDurationMs = C.TIME_UNSET;
        }

        @Deprecated
        public Builder setAdTagUri(Uri uri) {
            return setAdTagUri(uri, null);
        }

        @Deprecated
        public Builder setDrmLicenseUri(String str) {
            this.drmConfiguration.setLicenseUri(str);
            return this;
        }

        public Builder setUri(Uri uri) {
            this.uri = uri;
            return this;
        }

        @Deprecated
        public Builder setAdTagUri(Uri uri, Object obj) {
            this.adsConfiguration = uri != null ? new AdsConfiguration.Builder(uri).setAdsId(obj).build() : null;
            return this;
        }

        private Builder(MediaItem mediaItem) {
            DrmConfiguration.Builder builder;
            this();
            this.clippingConfiguration = mediaItem.clippingConfiguration.buildUpon();
            this.mediaId = mediaItem.mediaId;
            this.mediaMetadata = mediaItem.mediaMetadata;
            this.liveConfiguration = mediaItem.liveConfiguration.buildUpon();
            this.requestMetadata = mediaItem.requestMetadata;
            LocalConfiguration localConfiguration = mediaItem.localConfiguration;
            if (localConfiguration != null) {
                this.customCacheKey = localConfiguration.customCacheKey;
                this.mimeType = localConfiguration.mimeType;
                this.uri = localConfiguration.uri;
                this.streamKeys = localConfiguration.streamKeys;
                this.subtitleConfigurations = localConfiguration.subtitleConfigurations;
                this.tag = localConfiguration.tag;
                DrmConfiguration drmConfiguration = localConfiguration.drmConfiguration;
                if (drmConfiguration != null) {
                    builder = drmConfiguration.buildUpon();
                } else {
                    builder = new DrmConfiguration.Builder();
                }
                this.drmConfiguration = builder;
                this.adsConfiguration = localConfiguration.adsConfiguration;
                this.imageDurationMs = localConfiguration.imageDurationMs;
            }
        }
    }

    public static class ClippingConfiguration {
        public final boolean allowUnseekableMedia;
        public final long endPositionMs;
        public final long endPositionUs;
        public final boolean relativeToDefaultPosition;
        public final boolean relativeToLiveWindow;
        public final long startPositionMs;
        public final long startPositionUs;
        public final boolean startsAtKeyFrame;
        public static final ClippingConfiguration UNSET = new Builder().build();
        private static final String FIELD_START_POSITION_MS = Util.intToStringMaxRadix(0);
        private static final String FIELD_END_POSITION_MS = Util.intToStringMaxRadix(1);
        private static final String FIELD_RELATIVE_TO_LIVE_WINDOW = Util.intToStringMaxRadix(2);
        private static final String FIELD_RELATIVE_TO_DEFAULT_POSITION = Util.intToStringMaxRadix(3);
        private static final String FIELD_STARTS_AT_KEY_FRAME = Util.intToStringMaxRadix(4);
        static final String FIELD_START_POSITION_US = Util.intToStringMaxRadix(5);
        static final String FIELD_END_POSITION_US = Util.intToStringMaxRadix(6);
        private static final String FIELD_ALLOW_UNSEEKABLE_MEDIA = Util.intToStringMaxRadix(7);

        public static final class Builder {
            private boolean allowUnseekableMedia;
            private long endPositionUs;
            private boolean relativeToDefaultPosition;
            private boolean relativeToLiveWindow;
            private long startPositionUs;
            private boolean startsAtKeyFrame;

            public ClippingConfiguration build() {
                return new ClippingConfiguration(this);
            }

            @Deprecated
            public ClippingProperties buildClippingProperties() {
                return new ClippingProperties(this);
            }

            public Builder setAllowUnseekableMedia(boolean z) {
                this.allowUnseekableMedia = z;
                return this;
            }

            public Builder setEndPositionMs(long j10) {
                return setEndPositionUs(Util.msToUs(j10));
            }

            public Builder setEndPositionUs(long j10) {
                ac.b.j(j10 == Long.MIN_VALUE || j10 >= 0);
                this.endPositionUs = j10;
                return this;
            }

            public Builder setRelativeToDefaultPosition(boolean z) {
                this.relativeToDefaultPosition = z;
                return this;
            }

            public Builder setRelativeToLiveWindow(boolean z) {
                this.relativeToLiveWindow = z;
                return this;
            }

            public Builder setStartPositionMs(long j10) {
                return setStartPositionUs(Util.msToUs(j10));
            }

            public Builder setStartPositionUs(long j10) {
                ac.b.j(j10 >= 0);
                this.startPositionUs = j10;
                return this;
            }

            public Builder setStartsAtKeyFrame(boolean z) {
                this.startsAtKeyFrame = z;
                return this;
            }

            public Builder() {
                this.endPositionUs = Long.MIN_VALUE;
            }

            private Builder(ClippingConfiguration clippingConfiguration) {
                this.startPositionUs = clippingConfiguration.startPositionUs;
                this.endPositionUs = clippingConfiguration.endPositionUs;
                this.relativeToLiveWindow = clippingConfiguration.relativeToLiveWindow;
                this.relativeToDefaultPosition = clippingConfiguration.relativeToDefaultPosition;
                this.startsAtKeyFrame = clippingConfiguration.startsAtKeyFrame;
                this.allowUnseekableMedia = clippingConfiguration.allowUnseekableMedia;
            }
        }

        public static ClippingProperties fromBundle(Bundle bundle) {
            Builder builder = new Builder();
            String str = FIELD_START_POSITION_MS;
            ClippingConfiguration clippingConfiguration = UNSET;
            Builder allowUnseekableMedia = builder.setStartPositionMs(bundle.getLong(str, clippingConfiguration.startPositionMs)).setEndPositionMs(bundle.getLong(FIELD_END_POSITION_MS, clippingConfiguration.endPositionMs)).setRelativeToLiveWindow(bundle.getBoolean(FIELD_RELATIVE_TO_LIVE_WINDOW, clippingConfiguration.relativeToLiveWindow)).setRelativeToDefaultPosition(bundle.getBoolean(FIELD_RELATIVE_TO_DEFAULT_POSITION, clippingConfiguration.relativeToDefaultPosition)).setStartsAtKeyFrame(bundle.getBoolean(FIELD_STARTS_AT_KEY_FRAME, clippingConfiguration.startsAtKeyFrame)).setAllowUnseekableMedia(bundle.getBoolean(FIELD_ALLOW_UNSEEKABLE_MEDIA, clippingConfiguration.allowUnseekableMedia));
            long j10 = bundle.getLong(FIELD_START_POSITION_US, clippingConfiguration.startPositionUs);
            if (j10 != clippingConfiguration.startPositionUs) {
                allowUnseekableMedia.setStartPositionUs(j10);
            }
            long j11 = bundle.getLong(FIELD_END_POSITION_US, clippingConfiguration.endPositionUs);
            if (j11 != clippingConfiguration.endPositionUs) {
                allowUnseekableMedia.setEndPositionUs(j11);
            }
            return allowUnseekableMedia.buildClippingProperties();
        }

        public Builder buildUpon() {
            return new Builder();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ClippingConfiguration)) {
                return false;
            }
            ClippingConfiguration clippingConfiguration = (ClippingConfiguration) obj;
            return this.startPositionUs == clippingConfiguration.startPositionUs && this.endPositionUs == clippingConfiguration.endPositionUs && this.relativeToLiveWindow == clippingConfiguration.relativeToLiveWindow && this.relativeToDefaultPosition == clippingConfiguration.relativeToDefaultPosition && this.startsAtKeyFrame == clippingConfiguration.startsAtKeyFrame && this.allowUnseekableMedia == clippingConfiguration.allowUnseekableMedia;
        }

        public int hashCode() {
            long j10 = this.startPositionUs;
            int i10 = ((int) (j10 ^ (j10 >>> 32))) * 31;
            long j11 = this.endPositionUs;
            return ((((((((i10 + ((int) ((j11 >>> 32) ^ j11))) * 31) + (this.relativeToLiveWindow ? 1 : 0)) * 31) + (this.relativeToDefaultPosition ? 1 : 0)) * 31) + (this.startsAtKeyFrame ? 1 : 0)) * 31) + (this.allowUnseekableMedia ? 1 : 0);
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            long j10 = this.startPositionMs;
            ClippingConfiguration clippingConfiguration = UNSET;
            if (j10 != clippingConfiguration.startPositionMs) {
                bundle.putLong(FIELD_START_POSITION_MS, j10);
            }
            long j11 = this.endPositionMs;
            if (j11 != clippingConfiguration.endPositionMs) {
                bundle.putLong(FIELD_END_POSITION_MS, j11);
            }
            long j12 = this.startPositionUs;
            if (j12 != clippingConfiguration.startPositionUs) {
                bundle.putLong(FIELD_START_POSITION_US, j12);
            }
            long j13 = this.endPositionUs;
            if (j13 != clippingConfiguration.endPositionUs) {
                bundle.putLong(FIELD_END_POSITION_US, j13);
            }
            boolean z = this.relativeToLiveWindow;
            if (z != clippingConfiguration.relativeToLiveWindow) {
                bundle.putBoolean(FIELD_RELATIVE_TO_LIVE_WINDOW, z);
            }
            boolean z5 = this.relativeToDefaultPosition;
            if (z5 != clippingConfiguration.relativeToDefaultPosition) {
                bundle.putBoolean(FIELD_RELATIVE_TO_DEFAULT_POSITION, z5);
            }
            boolean z10 = this.startsAtKeyFrame;
            if (z10 != clippingConfiguration.startsAtKeyFrame) {
                bundle.putBoolean(FIELD_STARTS_AT_KEY_FRAME, z10);
            }
            boolean z11 = this.allowUnseekableMedia;
            if (z11 != clippingConfiguration.allowUnseekableMedia) {
                bundle.putBoolean(FIELD_ALLOW_UNSEEKABLE_MEDIA, z11);
            }
            return bundle;
        }

        private ClippingConfiguration(Builder builder) {
            this.startPositionMs = Util.usToMs(builder.startPositionUs);
            this.endPositionMs = Util.usToMs(builder.endPositionUs);
            this.startPositionUs = builder.startPositionUs;
            this.endPositionUs = builder.endPositionUs;
            this.relativeToLiveWindow = builder.relativeToLiveWindow;
            this.relativeToDefaultPosition = builder.relativeToDefaultPosition;
            this.startsAtKeyFrame = builder.startsAtKeyFrame;
            this.allowUnseekableMedia = builder.allowUnseekableMedia;
        }
    }

    @Deprecated
    public static final class ClippingProperties extends ClippingConfiguration {
        public static final ClippingProperties UNSET = new ClippingConfiguration.Builder().buildClippingProperties();

        private ClippingProperties(ClippingConfiguration.Builder builder) {
            super(builder);
        }
    }

    public static final class DrmConfiguration {
        public final boolean forceDefaultLicenseUri;
        public final h1 forcedSessionTrackTypes;
        private final byte[] keySetId;
        public final l1 licenseRequestHeaders;
        public final Uri licenseUri;
        public final boolean multiSession;
        public final boolean playClearContentWithoutKey;

        @Deprecated
        public final l1 requestHeaders;
        public final UUID scheme;

        @Deprecated
        public final h1 sessionForClearTypes;

        @Deprecated
        public final UUID uuid;
        private static final String FIELD_SCHEME = Util.intToStringMaxRadix(0);
        private static final String FIELD_LICENSE_URI = Util.intToStringMaxRadix(1);
        private static final String FIELD_LICENSE_REQUEST_HEADERS = Util.intToStringMaxRadix(2);
        private static final String FIELD_MULTI_SESSION = Util.intToStringMaxRadix(3);
        static final String FIELD_PLAY_CLEAR_CONTENT_WITHOUT_KEY = Util.intToStringMaxRadix(4);
        private static final String FIELD_FORCE_DEFAULT_LICENSE_URI = Util.intToStringMaxRadix(5);
        private static final String FIELD_FORCED_SESSION_TRACK_TYPES = Util.intToStringMaxRadix(6);
        private static final String FIELD_KEY_SET_ID = Util.intToStringMaxRadix(7);

        public static final class Builder {
            private boolean forceDefaultLicenseUri;
            private h1 forcedSessionTrackTypes;
            private byte[] keySetId;
            private l1 licenseRequestHeaders;
            private Uri licenseUri;
            private boolean multiSession;
            private boolean playClearContentWithoutKey;
            private UUID scheme;

            /* JADX INFO: Access modifiers changed from: private */
            @Deprecated
            public Builder setNullableScheme(UUID uuid) {
                this.scheme = uuid;
                return this;
            }

            public DrmConfiguration build() {
                return new DrmConfiguration(this);
            }

            @Deprecated
            public Builder forceSessionsForAudioAndVideoTracks(boolean z) {
                return setForceSessionsForAudioAndVideoTracks(z);
            }

            public Builder setForceDefaultLicenseUri(boolean z) {
                this.forceDefaultLicenseUri = z;
                return this;
            }

            public Builder setForceSessionsForAudioAndVideoTracks(boolean z) {
                o3 o3VarT;
                if (z) {
                    o3VarT = h1.t(2, 1);
                } else {
                    f1 f1Var = h1.f14020l;
                    o3VarT = o3.f14078o;
                }
                setForcedSessionTrackTypes(o3VarT);
                return this;
            }

            public Builder setForcedSessionTrackTypes(List<Integer> list) {
                this.forcedSessionTrackTypes = h1.n(list);
                return this;
            }

            public Builder setKeySetId(byte[] bArr) {
                this.keySetId = bArr != null ? Arrays.copyOf(bArr, bArr.length) : null;
                return this;
            }

            public Builder setLicenseRequestHeaders(Map<String, String> map) {
                this.licenseRequestHeaders = l1.b(map);
                return this;
            }

            public Builder setLicenseUri(Uri uri) {
                this.licenseUri = uri;
                return this;
            }

            public Builder setMultiSession(boolean z) {
                this.multiSession = z;
                return this;
            }

            public Builder setPlayClearContentWithoutKey(boolean z) {
                this.playClearContentWithoutKey = z;
                return this;
            }

            public Builder setScheme(UUID uuid) {
                this.scheme = uuid;
                return this;
            }

            public Builder setLicenseUri(String str) {
                this.licenseUri = str == null ? null : Uri.parse(str);
                return this;
            }

            public Builder(UUID uuid) {
                this();
                this.scheme = uuid;
            }

            @Deprecated
            private Builder() {
                this.licenseRequestHeaders = t3.f14114q;
                this.playClearContentWithoutKey = true;
                f1 f1Var = h1.f14020l;
                this.forcedSessionTrackTypes = o3.f14078o;
            }

            private Builder(DrmConfiguration drmConfiguration) {
                this.scheme = drmConfiguration.scheme;
                this.licenseUri = drmConfiguration.licenseUri;
                this.licenseRequestHeaders = drmConfiguration.licenseRequestHeaders;
                this.multiSession = drmConfiguration.multiSession;
                this.playClearContentWithoutKey = drmConfiguration.playClearContentWithoutKey;
                this.forceDefaultLicenseUri = drmConfiguration.forceDefaultLicenseUri;
                this.forcedSessionTrackTypes = drmConfiguration.forcedSessionTrackTypes;
                this.keySetId = drmConfiguration.keySetId;
            }
        }

        public static DrmConfiguration fromBundle(Bundle bundle) {
            String string = bundle.getString(FIELD_SCHEME);
            string.getClass();
            UUID uuidFromString = UUID.fromString(string);
            Uri uri = (Uri) bundle.getParcelable(FIELD_LICENSE_URI);
            l1 l1VarBundleToStringImmutableMap = BundleCollectionUtil.bundleToStringImmutableMap(BundleCollectionUtil.getBundleWithDefault(bundle, FIELD_LICENSE_REQUEST_HEADERS, Bundle.EMPTY));
            boolean z = bundle.getBoolean(FIELD_MULTI_SESSION, false);
            boolean z5 = bundle.getBoolean(FIELD_PLAY_CLEAR_CONTENT_WITHOUT_KEY, false);
            boolean z10 = bundle.getBoolean(FIELD_FORCE_DEFAULT_LICENSE_URI, false);
            h1 h1VarN = h1.n(BundleCollectionUtil.getIntegerArrayListWithDefault(bundle, FIELD_FORCED_SESSION_TRACK_TYPES, new ArrayList()));
            return new Builder(uuidFromString).setLicenseUri(uri).setLicenseRequestHeaders(l1VarBundleToStringImmutableMap).setMultiSession(z).setForceDefaultLicenseUri(z10).setPlayClearContentWithoutKey(z5).setForcedSessionTrackTypes(h1VarN).setKeySetId(bundle.getByteArray(FIELD_KEY_SET_ID)).build();
        }

        public Builder buildUpon() {
            return new Builder();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DrmConfiguration)) {
                return false;
            }
            DrmConfiguration drmConfiguration = (DrmConfiguration) obj;
            return this.scheme.equals(drmConfiguration.scheme) && Objects.equals(this.licenseUri, drmConfiguration.licenseUri) && Objects.equals(this.licenseRequestHeaders, drmConfiguration.licenseRequestHeaders) && this.multiSession == drmConfiguration.multiSession && this.forceDefaultLicenseUri == drmConfiguration.forceDefaultLicenseUri && this.playClearContentWithoutKey == drmConfiguration.playClearContentWithoutKey && this.forcedSessionTrackTypes.equals(drmConfiguration.forcedSessionTrackTypes) && Arrays.equals(this.keySetId, drmConfiguration.keySetId);
        }

        public byte[] getKeySetId() {
            byte[] bArr = this.keySetId;
            if (bArr != null) {
                return Arrays.copyOf(bArr, bArr.length);
            }
            return null;
        }

        public int hashCode() {
            int iHashCode = this.scheme.hashCode() * 31;
            Uri uri = this.licenseUri;
            return Arrays.hashCode(this.keySetId) + ((this.forcedSessionTrackTypes.hashCode() + ((((((((this.licenseRequestHeaders.hashCode() + ((iHashCode + (uri != null ? uri.hashCode() : 0)) * 31)) * 31) + (this.multiSession ? 1 : 0)) * 31) + (this.forceDefaultLicenseUri ? 1 : 0)) * 31) + (this.playClearContentWithoutKey ? 1 : 0)) * 31)) * 31);
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putString(FIELD_SCHEME, this.scheme.toString());
            Uri uri = this.licenseUri;
            if (uri != null) {
                bundle.putParcelable(FIELD_LICENSE_URI, uri);
            }
            if (!this.licenseRequestHeaders.isEmpty()) {
                bundle.putBundle(FIELD_LICENSE_REQUEST_HEADERS, BundleCollectionUtil.stringMapToBundle(this.licenseRequestHeaders));
            }
            boolean z = this.multiSession;
            if (z) {
                bundle.putBoolean(FIELD_MULTI_SESSION, z);
            }
            boolean z5 = this.playClearContentWithoutKey;
            if (z5) {
                bundle.putBoolean(FIELD_PLAY_CLEAR_CONTENT_WITHOUT_KEY, z5);
            }
            boolean z10 = this.forceDefaultLicenseUri;
            if (z10) {
                bundle.putBoolean(FIELD_FORCE_DEFAULT_LICENSE_URI, z10);
            }
            if (!this.forcedSessionTrackTypes.isEmpty()) {
                bundle.putIntegerArrayList(FIELD_FORCED_SESSION_TRACK_TYPES, new ArrayList<>(this.forcedSessionTrackTypes));
            }
            byte[] bArr = this.keySetId;
            if (bArr != null) {
                bundle.putByteArray(FIELD_KEY_SET_ID, bArr);
            }
            return bundle;
        }

        private DrmConfiguration(Builder builder) {
            ac.b.s((builder.forceDefaultLicenseUri && builder.licenseUri == null) ? false : true);
            UUID uuid = builder.scheme;
            uuid.getClass();
            this.scheme = uuid;
            this.uuid = uuid;
            this.licenseUri = builder.licenseUri;
            this.requestHeaders = builder.licenseRequestHeaders;
            this.licenseRequestHeaders = builder.licenseRequestHeaders;
            this.multiSession = builder.multiSession;
            this.forceDefaultLicenseUri = builder.forceDefaultLicenseUri;
            this.playClearContentWithoutKey = builder.playClearContentWithoutKey;
            this.sessionForClearTypes = builder.forcedSessionTrackTypes;
            this.forcedSessionTrackTypes = builder.forcedSessionTrackTypes;
            this.keySetId = builder.keySetId != null ? Arrays.copyOf(builder.keySetId, builder.keySetId.length) : null;
        }
    }

    public static final class LiveConfiguration {
        public final long maxOffsetMs;
        public final float maxPlaybackSpeed;
        public final long minOffsetMs;
        public final float minPlaybackSpeed;
        public final long targetOffsetMs;
        public static final LiveConfiguration UNSET = new Builder().build();
        private static final String FIELD_TARGET_OFFSET_MS = Util.intToStringMaxRadix(0);
        private static final String FIELD_MIN_OFFSET_MS = Util.intToStringMaxRadix(1);
        private static final String FIELD_MAX_OFFSET_MS = Util.intToStringMaxRadix(2);
        private static final String FIELD_MIN_PLAYBACK_SPEED = Util.intToStringMaxRadix(3);
        private static final String FIELD_MAX_PLAYBACK_SPEED = Util.intToStringMaxRadix(4);

        public static final class Builder {
            private long maxOffsetMs;
            private float maxPlaybackSpeed;
            private long minOffsetMs;
            private float minPlaybackSpeed;
            private long targetOffsetMs;

            public LiveConfiguration build() {
                return new LiveConfiguration(this);
            }

            public Builder setMaxOffsetMs(long j10) {
                this.maxOffsetMs = j10;
                return this;
            }

            public Builder setMaxPlaybackSpeed(float f10) {
                this.maxPlaybackSpeed = f10;
                return this;
            }

            public Builder setMinOffsetMs(long j10) {
                this.minOffsetMs = j10;
                return this;
            }

            public Builder setMinPlaybackSpeed(float f10) {
                this.minPlaybackSpeed = f10;
                return this;
            }

            public Builder setTargetOffsetMs(long j10) {
                this.targetOffsetMs = j10;
                return this;
            }

            public Builder() {
                this.targetOffsetMs = C.TIME_UNSET;
                this.minOffsetMs = C.TIME_UNSET;
                this.maxOffsetMs = C.TIME_UNSET;
                this.minPlaybackSpeed = -3.4028235E38f;
                this.maxPlaybackSpeed = -3.4028235E38f;
            }

            private Builder(LiveConfiguration liveConfiguration) {
                this.targetOffsetMs = liveConfiguration.targetOffsetMs;
                this.minOffsetMs = liveConfiguration.minOffsetMs;
                this.maxOffsetMs = liveConfiguration.maxOffsetMs;
                this.minPlaybackSpeed = liveConfiguration.minPlaybackSpeed;
                this.maxPlaybackSpeed = liveConfiguration.maxPlaybackSpeed;
            }
        }

        public static LiveConfiguration fromBundle(Bundle bundle) {
            Builder builder = new Builder();
            String str = FIELD_TARGET_OFFSET_MS;
            LiveConfiguration liveConfiguration = UNSET;
            return builder.setTargetOffsetMs(bundle.getLong(str, liveConfiguration.targetOffsetMs)).setMinOffsetMs(bundle.getLong(FIELD_MIN_OFFSET_MS, liveConfiguration.minOffsetMs)).setMaxOffsetMs(bundle.getLong(FIELD_MAX_OFFSET_MS, liveConfiguration.maxOffsetMs)).setMinPlaybackSpeed(bundle.getFloat(FIELD_MIN_PLAYBACK_SPEED, liveConfiguration.minPlaybackSpeed)).setMaxPlaybackSpeed(bundle.getFloat(FIELD_MAX_PLAYBACK_SPEED, liveConfiguration.maxPlaybackSpeed)).build();
        }

        public Builder buildUpon() {
            return new Builder();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof LiveConfiguration)) {
                return false;
            }
            LiveConfiguration liveConfiguration = (LiveConfiguration) obj;
            return this.targetOffsetMs == liveConfiguration.targetOffsetMs && this.minOffsetMs == liveConfiguration.minOffsetMs && this.maxOffsetMs == liveConfiguration.maxOffsetMs && this.minPlaybackSpeed == liveConfiguration.minPlaybackSpeed && this.maxPlaybackSpeed == liveConfiguration.maxPlaybackSpeed;
        }

        public int hashCode() {
            long j10 = this.targetOffsetMs;
            long j11 = this.minOffsetMs;
            int i10 = ((((int) (j10 ^ (j10 >>> 32))) * 31) + ((int) (j11 ^ (j11 >>> 32)))) * 31;
            long j12 = this.maxOffsetMs;
            int i11 = (i10 + ((int) ((j12 >>> 32) ^ j12))) * 31;
            float f10 = this.minPlaybackSpeed;
            int iFloatToIntBits = (i11 + (f10 != 0.0f ? Float.floatToIntBits(f10) : 0)) * 31;
            float f11 = this.maxPlaybackSpeed;
            return iFloatToIntBits + (f11 != 0.0f ? Float.floatToIntBits(f11) : 0);
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            long j10 = this.targetOffsetMs;
            LiveConfiguration liveConfiguration = UNSET;
            if (j10 != liveConfiguration.targetOffsetMs) {
                bundle.putLong(FIELD_TARGET_OFFSET_MS, j10);
            }
            long j11 = this.minOffsetMs;
            if (j11 != liveConfiguration.minOffsetMs) {
                bundle.putLong(FIELD_MIN_OFFSET_MS, j11);
            }
            long j12 = this.maxOffsetMs;
            if (j12 != liveConfiguration.maxOffsetMs) {
                bundle.putLong(FIELD_MAX_OFFSET_MS, j12);
            }
            float f10 = this.minPlaybackSpeed;
            if (f10 != liveConfiguration.minPlaybackSpeed) {
                bundle.putFloat(FIELD_MIN_PLAYBACK_SPEED, f10);
            }
            float f11 = this.maxPlaybackSpeed;
            if (f11 != liveConfiguration.maxPlaybackSpeed) {
                bundle.putFloat(FIELD_MAX_PLAYBACK_SPEED, f11);
            }
            return bundle;
        }

        private LiveConfiguration(Builder builder) {
            this(builder.targetOffsetMs, builder.minOffsetMs, builder.maxOffsetMs, builder.minPlaybackSpeed, builder.maxPlaybackSpeed);
        }

        @Deprecated
        public LiveConfiguration(long j10, long j11, long j12, float f10, float f11) {
            this.targetOffsetMs = j10;
            this.minOffsetMs = j11;
            this.maxOffsetMs = j12;
            this.minPlaybackSpeed = f10;
            this.maxPlaybackSpeed = f11;
        }
    }

    public static final class LocalConfiguration {
        public final AdsConfiguration adsConfiguration;
        public final String customCacheKey;
        public final DrmConfiguration drmConfiguration;
        public final long imageDurationMs;
        public final String mimeType;
        public final List<StreamKey> streamKeys;
        public final h1 subtitleConfigurations;

        @Deprecated
        public final List<Subtitle> subtitles;
        public final Object tag;
        public final Uri uri;
        private static final String FIELD_URI = Util.intToStringMaxRadix(0);
        private static final String FIELD_MIME_TYPE = Util.intToStringMaxRadix(1);
        private static final String FIELD_DRM_CONFIGURATION = Util.intToStringMaxRadix(2);
        private static final String FIELD_ADS_CONFIGURATION = Util.intToStringMaxRadix(3);
        private static final String FIELD_STREAM_KEYS = Util.intToStringMaxRadix(4);
        private static final String FIELD_CUSTOM_CACHE_KEY = Util.intToStringMaxRadix(5);
        private static final String FIELD_SUBTITLE_CONFIGURATION = Util.intToStringMaxRadix(6);
        private static final String FIELD_IMAGE_DURATION_MS = Util.intToStringMaxRadix(7);

        public static LocalConfiguration fromBundle(Bundle bundle) {
            List listFromBundleList;
            h1 h1VarFromBundleList;
            Bundle bundle2 = bundle.getBundle(FIELD_DRM_CONFIGURATION);
            DrmConfiguration drmConfigurationFromBundle = bundle2 == null ? null : DrmConfiguration.fromBundle(bundle2);
            Bundle bundle3 = bundle.getBundle(FIELD_ADS_CONFIGURATION);
            AdsConfiguration adsConfigurationFromBundle = bundle3 != null ? AdsConfiguration.fromBundle(bundle3) : null;
            ArrayList parcelableArrayList = bundle.getParcelableArrayList(FIELD_STREAM_KEYS);
            if (parcelableArrayList == null) {
                f1 f1Var = h1.f14020l;
                listFromBundleList = o3.f14078o;
            } else {
                listFromBundleList = BundleCollectionUtil.fromBundleList(new e(5), parcelableArrayList);
            }
            List list = listFromBundleList;
            ArrayList parcelableArrayList2 = bundle.getParcelableArrayList(FIELD_SUBTITLE_CONFIGURATION);
            if (parcelableArrayList2 == null) {
                f1 f1Var2 = h1.f14020l;
                h1VarFromBundleList = o3.f14078o;
            } else {
                h1VarFromBundleList = BundleCollectionUtil.fromBundleList(new e(6), parcelableArrayList2);
            }
            h1 h1Var = h1VarFromBundleList;
            long j10 = bundle.getLong(FIELD_IMAGE_DURATION_MS, C.TIME_UNSET);
            Uri uri = (Uri) bundle.getParcelable(FIELD_URI);
            uri.getClass();
            return new LocalConfiguration(uri, bundle.getString(FIELD_MIME_TYPE), drmConfigurationFromBundle, adsConfigurationFromBundle, list, bundle.getString(FIELD_CUSTOM_CACHE_KEY), h1Var, null, j10);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof LocalConfiguration)) {
                return false;
            }
            LocalConfiguration localConfiguration = (LocalConfiguration) obj;
            return this.uri.equals(localConfiguration.uri) && Objects.equals(this.mimeType, localConfiguration.mimeType) && Objects.equals(this.drmConfiguration, localConfiguration.drmConfiguration) && Objects.equals(this.adsConfiguration, localConfiguration.adsConfiguration) && this.streamKeys.equals(localConfiguration.streamKeys) && Objects.equals(this.customCacheKey, localConfiguration.customCacheKey) && this.subtitleConfigurations.equals(localConfiguration.subtitleConfigurations) && Objects.equals(this.tag, localConfiguration.tag) && this.imageDurationMs == localConfiguration.imageDurationMs;
        }

        public int hashCode() {
            int iHashCode = this.uri.hashCode() * 31;
            String str = this.mimeType;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            DrmConfiguration drmConfiguration = this.drmConfiguration;
            int iHashCode3 = (iHashCode2 + (drmConfiguration == null ? 0 : drmConfiguration.hashCode())) * 31;
            AdsConfiguration adsConfiguration = this.adsConfiguration;
            int iHashCode4 = (this.streamKeys.hashCode() + ((iHashCode3 + (adsConfiguration == null ? 0 : adsConfiguration.hashCode())) * 31)) * 31;
            String str2 = this.customCacheKey;
            int iHashCode5 = (this.subtitleConfigurations.hashCode() + ((iHashCode4 + (str2 == null ? 0 : str2.hashCode())) * 31)) * 31;
            Object obj = this.tag;
            return (int) ((((long) (iHashCode5 + (obj != null ? obj.hashCode() : 0))) * 31) + this.imageDurationMs);
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putParcelable(FIELD_URI, this.uri);
            String str = this.mimeType;
            if (str != null) {
                bundle.putString(FIELD_MIME_TYPE, str);
            }
            DrmConfiguration drmConfiguration = this.drmConfiguration;
            if (drmConfiguration != null) {
                bundle.putBundle(FIELD_DRM_CONFIGURATION, drmConfiguration.toBundle());
            }
            AdsConfiguration adsConfiguration = this.adsConfiguration;
            if (adsConfiguration != null) {
                bundle.putBundle(FIELD_ADS_CONFIGURATION, adsConfiguration.toBundle());
            }
            if (!this.streamKeys.isEmpty()) {
                bundle.putParcelableArrayList(FIELD_STREAM_KEYS, BundleCollectionUtil.toBundleArrayList(this.streamKeys, new e(3)));
            }
            String str2 = this.customCacheKey;
            if (str2 != null) {
                bundle.putString(FIELD_CUSTOM_CACHE_KEY, str2);
            }
            if (!this.subtitleConfigurations.isEmpty()) {
                bundle.putParcelableArrayList(FIELD_SUBTITLE_CONFIGURATION, BundleCollectionUtil.toBundleArrayList(this.subtitleConfigurations, new e(4)));
            }
            long j10 = this.imageDurationMs;
            if (j10 != C.TIME_UNSET) {
                bundle.putLong(FIELD_IMAGE_DURATION_MS, j10);
            }
            return bundle;
        }

        private LocalConfiguration(Uri uri, String str, DrmConfiguration drmConfiguration, AdsConfiguration adsConfiguration, List<StreamKey> list, String str2, h1 h1Var, Object obj, long j10) {
            this.uri = uri;
            this.mimeType = MimeTypes.normalizeMimeType(str);
            this.drmConfiguration = drmConfiguration;
            this.adsConfiguration = adsConfiguration;
            this.streamKeys = list;
            this.customCacheKey = str2;
            this.subtitleConfigurations = h1Var;
            e1 e1VarK = h1.k();
            for (int i10 = 0; i10 < h1Var.size(); i10++) {
                e1VarK.c(((SubtitleConfiguration) h1Var.get(i10)).buildUpon().buildSubtitle());
            }
            this.subtitles = e1VarK.f();
            this.tag = obj;
            this.imageDurationMs = j10;
        }
    }

    public static final class RequestMetadata {
        public final Bundle extras;
        public final Uri mediaUri;
        public final String searchQuery;
        public static final RequestMetadata EMPTY = new Builder().build();
        private static final String FIELD_MEDIA_URI = Util.intToStringMaxRadix(0);
        private static final String FIELD_SEARCH_QUERY = Util.intToStringMaxRadix(1);
        private static final String FIELD_EXTRAS = Util.intToStringMaxRadix(2);

        public static final class Builder {
            private Bundle extras;
            private Uri mediaUri;
            private String searchQuery;

            public RequestMetadata build() {
                return new RequestMetadata(this);
            }

            public Builder setExtras(Bundle bundle) {
                this.extras = bundle;
                return this;
            }

            public Builder setMediaUri(Uri uri) {
                this.mediaUri = uri;
                return this;
            }

            public Builder setSearchQuery(String str) {
                this.searchQuery = str;
                return this;
            }

            public Builder() {
            }

            private Builder(RequestMetadata requestMetadata) {
                this.mediaUri = requestMetadata.mediaUri;
                this.searchQuery = requestMetadata.searchQuery;
                this.extras = requestMetadata.extras;
            }
        }

        public static RequestMetadata fromBundle(Bundle bundle) {
            return new Builder().setMediaUri((Uri) bundle.getParcelable(FIELD_MEDIA_URI)).setSearchQuery(bundle.getString(FIELD_SEARCH_QUERY)).setExtras(bundle.getBundle(FIELD_EXTRAS)).build();
        }

        public Builder buildUpon() {
            return new Builder();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof RequestMetadata)) {
                return false;
            }
            RequestMetadata requestMetadata = (RequestMetadata) obj;
            if (Objects.equals(this.mediaUri, requestMetadata.mediaUri) && Objects.equals(this.searchQuery, requestMetadata.searchQuery)) {
                if ((this.extras == null) == (requestMetadata.extras == null)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            Uri uri = this.mediaUri;
            int iHashCode = (uri == null ? 0 : uri.hashCode()) * 31;
            String str = this.searchQuery;
            return ((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + (this.extras != null ? 1 : 0);
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            Uri uri = this.mediaUri;
            if (uri != null) {
                bundle.putParcelable(FIELD_MEDIA_URI, uri);
            }
            String str = this.searchQuery;
            if (str != null) {
                bundle.putString(FIELD_SEARCH_QUERY, str);
            }
            Bundle bundle2 = this.extras;
            if (bundle2 != null) {
                bundle.putBundle(FIELD_EXTRAS, bundle2);
            }
            return bundle;
        }

        private RequestMetadata(Builder builder) {
            this.mediaUri = builder.mediaUri;
            this.searchQuery = builder.searchQuery;
            this.extras = builder.extras;
        }
    }

    @Deprecated
    public static final class Subtitle extends SubtitleConfiguration {
        @Deprecated
        public Subtitle(Uri uri, String str, String str2) {
            this(uri, str, str2, 0);
        }

        @Deprecated
        public Subtitle(Uri uri, String str, String str2, int i10) {
            this(uri, str, str2, i10, 0, null);
        }

        @Deprecated
        public Subtitle(Uri uri, String str, String str2, int i10, int i11, String str3) {
            super(uri, str, str2, i10, i11, str3, null);
        }

        private Subtitle(SubtitleConfiguration.Builder builder) {
            super(builder);
        }
    }

    public static MediaItem fromBundle(Bundle bundle) {
        String string = bundle.getString(FIELD_MEDIA_ID, "");
        string.getClass();
        Bundle bundle2 = bundle.getBundle(FIELD_LIVE_CONFIGURATION);
        LiveConfiguration liveConfigurationFromBundle = bundle2 == null ? LiveConfiguration.UNSET : LiveConfiguration.fromBundle(bundle2);
        Bundle bundle3 = bundle.getBundle(FIELD_MEDIA_METADATA);
        MediaMetadata mediaMetadataFromBundle = bundle3 == null ? MediaMetadata.EMPTY : MediaMetadata.fromBundle(bundle3);
        Bundle bundle4 = bundle.getBundle(FIELD_CLIPPING_PROPERTIES);
        ClippingProperties clippingPropertiesFromBundle = bundle4 == null ? ClippingProperties.UNSET : ClippingConfiguration.fromBundle(bundle4);
        Bundle bundle5 = bundle.getBundle(FIELD_REQUEST_METADATA);
        RequestMetadata requestMetadataFromBundle = bundle5 == null ? RequestMetadata.EMPTY : RequestMetadata.fromBundle(bundle5);
        Bundle bundle6 = bundle.getBundle(FIELD_LOCAL_CONFIGURATION);
        return new MediaItem(string, clippingPropertiesFromBundle, bundle6 == null ? null : LocalConfiguration.fromBundle(bundle6), liveConfigurationFromBundle, mediaMetadataFromBundle, requestMetadataFromBundle);
    }

    public static MediaItem fromUri(String str) {
        return new Builder().setUri(str).build();
    }

    private Bundle toBundle(boolean z) {
        LocalConfiguration localConfiguration;
        Bundle bundle = new Bundle();
        if (!this.mediaId.equals("")) {
            bundle.putString(FIELD_MEDIA_ID, this.mediaId);
        }
        if (!this.liveConfiguration.equals(LiveConfiguration.UNSET)) {
            bundle.putBundle(FIELD_LIVE_CONFIGURATION, this.liveConfiguration.toBundle());
        }
        if (!this.mediaMetadata.equals(MediaMetadata.EMPTY)) {
            bundle.putBundle(FIELD_MEDIA_METADATA, this.mediaMetadata.toBundle());
        }
        if (!this.clippingConfiguration.equals(ClippingConfiguration.UNSET)) {
            bundle.putBundle(FIELD_CLIPPING_PROPERTIES, this.clippingConfiguration.toBundle());
        }
        if (!this.requestMetadata.equals(RequestMetadata.EMPTY)) {
            bundle.putBundle(FIELD_REQUEST_METADATA, this.requestMetadata.toBundle());
        }
        if (z && (localConfiguration = this.localConfiguration) != null) {
            bundle.putBundle(FIELD_LOCAL_CONFIGURATION, localConfiguration.toBundle());
        }
        return bundle;
    }

    public Builder buildUpon() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaItem)) {
            return false;
        }
        MediaItem mediaItem = (MediaItem) obj;
        return Objects.equals(this.mediaId, mediaItem.mediaId) && this.clippingConfiguration.equals(mediaItem.clippingConfiguration) && Objects.equals(this.localConfiguration, mediaItem.localConfiguration) && Objects.equals(this.liveConfiguration, mediaItem.liveConfiguration) && Objects.equals(this.mediaMetadata, mediaItem.mediaMetadata) && Objects.equals(this.requestMetadata, mediaItem.requestMetadata);
    }

    public int hashCode() {
        int iHashCode = this.mediaId.hashCode() * 31;
        LocalConfiguration localConfiguration = this.localConfiguration;
        return this.requestMetadata.hashCode() + ((this.mediaMetadata.hashCode() + ((this.clippingConfiguration.hashCode() + ((this.liveConfiguration.hashCode() + ((iHashCode + (localConfiguration != null ? localConfiguration.hashCode() : 0)) * 31)) * 31)) * 31)) * 31);
    }

    public Bundle toBundleIncludeLocalConfiguration() {
        return toBundle(true);
    }

    public static class SubtitleConfiguration {
        public final String id;
        public final String label;
        public final String language;
        public final String mimeType;
        public final int roleFlags;
        public final int selectionFlags;
        public final Uri uri;
        private static final String FIELD_URI = Util.intToStringMaxRadix(0);
        private static final String FIELD_MIME_TYPE = Util.intToStringMaxRadix(1);
        private static final String FIELD_LANGUAGE = Util.intToStringMaxRadix(2);
        private static final String FIELD_SELECTION_FLAGS = Util.intToStringMaxRadix(3);
        private static final String FIELD_ROLE_FLAGS = Util.intToStringMaxRadix(4);
        private static final String FIELD_LABEL = Util.intToStringMaxRadix(5);
        private static final String FIELD_ID = Util.intToStringMaxRadix(6);

        public static final class Builder {
            private String id;
            private String label;
            private String language;
            private String mimeType;
            private int roleFlags;
            private int selectionFlags;
            private Uri uri;

            /* JADX INFO: Access modifiers changed from: private */
            public Subtitle buildSubtitle() {
                return new Subtitle(this);
            }

            public SubtitleConfiguration build() {
                return new SubtitleConfiguration(this);
            }

            public Builder setId(String str) {
                this.id = str;
                return this;
            }

            public Builder setLabel(String str) {
                this.label = str;
                return this;
            }

            public Builder setLanguage(String str) {
                this.language = str;
                return this;
            }

            public Builder setMimeType(String str) {
                this.mimeType = MimeTypes.normalizeMimeType(str);
                return this;
            }

            public Builder setRoleFlags(int i10) {
                this.roleFlags = i10;
                return this;
            }

            public Builder setSelectionFlags(int i10) {
                this.selectionFlags = i10;
                return this;
            }

            public Builder setUri(Uri uri) {
                this.uri = uri;
                return this;
            }

            public Builder(Uri uri) {
                this.uri = uri;
            }

            private Builder(SubtitleConfiguration subtitleConfiguration) {
                this.uri = subtitleConfiguration.uri;
                this.mimeType = subtitleConfiguration.mimeType;
                this.language = subtitleConfiguration.language;
                this.selectionFlags = subtitleConfiguration.selectionFlags;
                this.roleFlags = subtitleConfiguration.roleFlags;
                this.label = subtitleConfiguration.label;
                this.id = subtitleConfiguration.id;
            }
        }

        public static SubtitleConfiguration fromBundle(Bundle bundle) {
            Uri uri = (Uri) bundle.getParcelable(FIELD_URI);
            uri.getClass();
            String string = bundle.getString(FIELD_MIME_TYPE);
            String string2 = bundle.getString(FIELD_LANGUAGE);
            int i10 = bundle.getInt(FIELD_SELECTION_FLAGS, 0);
            int i11 = bundle.getInt(FIELD_ROLE_FLAGS, 0);
            String string3 = bundle.getString(FIELD_LABEL);
            return new Builder(uri).setMimeType(string).setLanguage(string2).setSelectionFlags(i10).setRoleFlags(i11).setLabel(string3).setId(bundle.getString(FIELD_ID)).build();
        }

        public Builder buildUpon() {
            return new Builder();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SubtitleConfiguration)) {
                return false;
            }
            SubtitleConfiguration subtitleConfiguration = (SubtitleConfiguration) obj;
            return this.uri.equals(subtitleConfiguration.uri) && Objects.equals(this.mimeType, subtitleConfiguration.mimeType) && Objects.equals(this.language, subtitleConfiguration.language) && this.selectionFlags == subtitleConfiguration.selectionFlags && this.roleFlags == subtitleConfiguration.roleFlags && Objects.equals(this.label, subtitleConfiguration.label) && Objects.equals(this.id, subtitleConfiguration.id);
        }

        public int hashCode() {
            int iHashCode = this.uri.hashCode() * 31;
            String str = this.mimeType;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.language;
            int iHashCode3 = (((((iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31) + this.selectionFlags) * 31) + this.roleFlags) * 31;
            String str3 = this.label;
            int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.id;
            return iHashCode4 + (str4 != null ? str4.hashCode() : 0);
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putParcelable(FIELD_URI, this.uri);
            String str = this.mimeType;
            if (str != null) {
                bundle.putString(FIELD_MIME_TYPE, str);
            }
            String str2 = this.language;
            if (str2 != null) {
                bundle.putString(FIELD_LANGUAGE, str2);
            }
            int i10 = this.selectionFlags;
            if (i10 != 0) {
                bundle.putInt(FIELD_SELECTION_FLAGS, i10);
            }
            int i11 = this.roleFlags;
            if (i11 != 0) {
                bundle.putInt(FIELD_ROLE_FLAGS, i11);
            }
            String str3 = this.label;
            if (str3 != null) {
                bundle.putString(FIELD_LABEL, str3);
            }
            String str4 = this.id;
            if (str4 != null) {
                bundle.putString(FIELD_ID, str4);
            }
            return bundle;
        }

        private SubtitleConfiguration(Uri uri, String str, String str2, int i10, int i11, String str3, String str4) {
            this.uri = uri;
            this.mimeType = MimeTypes.normalizeMimeType(str);
            this.language = str2;
            this.selectionFlags = i10;
            this.roleFlags = i11;
            this.label = str3;
            this.id = str4;
        }

        private SubtitleConfiguration(Builder builder) {
            this.uri = builder.uri;
            this.mimeType = builder.mimeType;
            this.language = builder.language;
            this.selectionFlags = builder.selectionFlags;
            this.roleFlags = builder.roleFlags;
            this.label = builder.label;
            this.id = builder.id;
        }
    }

    private MediaItem(String str, ClippingProperties clippingProperties, LocalConfiguration localConfiguration, LiveConfiguration liveConfiguration, MediaMetadata mediaMetadata, RequestMetadata requestMetadata) {
        this.mediaId = str;
        this.localConfiguration = localConfiguration;
        this.playbackProperties = localConfiguration;
        this.liveConfiguration = liveConfiguration;
        this.mediaMetadata = mediaMetadata;
        this.clippingConfiguration = clippingProperties;
        this.clippingProperties = clippingProperties;
        this.requestMetadata = requestMetadata;
    }

    public static MediaItem fromUri(Uri uri) {
        return new Builder().setUri(uri).build();
    }

    public Bundle toBundle() {
        return toBundle(false);
    }
}
