package androidx.media3.common;

import android.content.Context;
import androidx.media3.common.util.Util;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
public final class C {
    public static final int ALLOW_CAPTURE_BY_ALL = 1;
    public static final int ALLOW_CAPTURE_BY_NONE = 3;
    public static final int ALLOW_CAPTURE_BY_SYSTEM = 2;
    public static final int AUDIO_CONTENT_TYPE_MOVIE = 3;
    public static final int AUDIO_CONTENT_TYPE_MUSIC = 2;
    public static final int AUDIO_CONTENT_TYPE_SONIFICATION = 4;
    public static final int AUDIO_CONTENT_TYPE_SPEECH = 1;
    public static final int AUDIO_CONTENT_TYPE_UNKNOWN = 0;
    public static final int AUDIO_SESSION_ID_UNSET = 0;
    public static final int AUXILIARY_TRACK_TYPE_DEPTH_INVERSE = 3;
    public static final int AUXILIARY_TRACK_TYPE_DEPTH_LINEAR = 2;
    public static final int AUXILIARY_TRACK_TYPE_DEPTH_METADATA = 4;
    public static final int AUXILIARY_TRACK_TYPE_ORIGINAL = 1;
    public static final int AUXILIARY_TRACK_TYPE_UNDEFINED = 0;
    public static final int BITS_PER_BYTE = 8;
    public static final int BUFFER_FLAG_ENCRYPTED = 1073741824;
    public static final int BUFFER_FLAG_END_OF_STREAM = 4;
    public static final int BUFFER_FLAG_FIRST_SAMPLE = 134217728;
    public static final int BUFFER_FLAG_HAS_SUPPLEMENTAL_DATA = 268435456;
    public static final int BUFFER_FLAG_KEY_FRAME = 1;
    public static final int BUFFER_FLAG_LAST_SAMPLE = 536870912;
    public static final int BUFFER_FLAG_NOT_DEPENDED_ON = 67108864;
    public static final int BYTES_PER_FLOAT = 4;
    public static final String CENC_TYPE_cbc1 = "cbc1";
    public static final String CENC_TYPE_cbcs = "cbcs";
    public static final String CENC_TYPE_cenc = "cenc";
    public static final String CENC_TYPE_cens = "cens";
    public static final int COLOR_RANGE_FULL = 1;
    public static final int COLOR_RANGE_LIMITED = 2;
    public static final int COLOR_SPACE_BT2020 = 6;
    public static final int COLOR_SPACE_BT601 = 2;
    public static final int COLOR_SPACE_BT709 = 1;
    public static final int COLOR_TRANSFER_GAMMA_2_2 = 10;
    public static final int COLOR_TRANSFER_HLG = 7;
    public static final int COLOR_TRANSFER_LINEAR = 1;
    public static final int COLOR_TRANSFER_SDR = 3;
    public static final int COLOR_TRANSFER_SRGB = 2;
    public static final int COLOR_TRANSFER_ST2084 = 6;
    public static final int CONTENT_TYPE_DASH = 0;
    public static final int CONTENT_TYPE_HLS = 2;

    @Deprecated
    public static final int CONTENT_TYPE_MOVIE = 3;

    @Deprecated
    public static final int CONTENT_TYPE_MUSIC = 2;
    public static final int CONTENT_TYPE_OTHER = 4;
    public static final int CONTENT_TYPE_RTSP = 3;

    @Deprecated
    public static final int CONTENT_TYPE_SONIFICATION = 4;

    @Deprecated
    public static final int CONTENT_TYPE_SPEECH = 1;
    public static final int CONTENT_TYPE_SS = 1;

    @Deprecated
    public static final int CONTENT_TYPE_UNKNOWN = 0;
    public static final int CRYPTO_MODE_AES_CBC = 2;
    public static final int CRYPTO_MODE_AES_CTR = 1;
    public static final int CRYPTO_MODE_UNENCRYPTED = 0;
    public static final int CRYPTO_TYPE_CUSTOM_BASE = 10000;
    public static final int CRYPTO_TYPE_FRAMEWORK = 2;
    public static final int CRYPTO_TYPE_NONE = 0;
    public static final int CRYPTO_TYPE_UNSUPPORTED = 1;
    public static final int DATA_TYPE_AD = 6;
    public static final int DATA_TYPE_CUSTOM_BASE = 10000;
    public static final int DATA_TYPE_DRM = 3;
    public static final int DATA_TYPE_MANIFEST = 4;
    public static final int DATA_TYPE_MEDIA = 1;
    public static final int DATA_TYPE_MEDIA_INITIALIZATION = 2;
    public static final int DATA_TYPE_MEDIA_PROGRESSIVE_LIVE = 7;
    public static final int DATA_TYPE_TIME_SYNCHRONIZATION = 5;
    public static final int DATA_TYPE_UNKNOWN = 0;
    public static final int DEFAULT_BUFFER_SEGMENT_SIZE = 65536;
    public static final long DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS = 3000;
    public static final long DEFAULT_SEEK_BACK_INCREMENT_MS = 5000;
    public static final long DEFAULT_SEEK_FORWARD_INCREMENT_MS = 15000;
    public static final int ENCODING_AAC_ELD = 15;
    public static final int ENCODING_AAC_ER_BSAC = 1073741824;
    public static final int ENCODING_AAC_HE_V1 = 11;
    public static final int ENCODING_AAC_HE_V2 = 12;
    public static final int ENCODING_AAC_LC = 10;
    public static final int ENCODING_AAC_XHE = 16;
    public static final int ENCODING_AC3 = 5;
    public static final int ENCODING_AC4 = 17;
    public static final int ENCODING_DOLBY_TRUEHD = 14;
    public static final int ENCODING_DTS = 7;
    public static final int ENCODING_DTS_HD = 8;
    public static final int ENCODING_DTS_UHD_P2 = 30;
    public static final int ENCODING_E_AC3 = 6;
    public static final int ENCODING_E_AC3_JOC = 18;
    public static final int ENCODING_INVALID = 0;
    public static final int ENCODING_MP3 = 9;
    public static final int ENCODING_OPUS = 20;
    public static final int ENCODING_PCM_16BIT = 2;
    public static final int ENCODING_PCM_16BIT_BIG_ENDIAN = 268435456;
    public static final int ENCODING_PCM_24BIT = 21;
    public static final int ENCODING_PCM_24BIT_BIG_ENDIAN = 1342177280;
    public static final int ENCODING_PCM_32BIT = 22;
    public static final int ENCODING_PCM_32BIT_BIG_ENDIAN = 1610612736;
    public static final int ENCODING_PCM_8BIT = 3;
    public static final int ENCODING_PCM_FLOAT = 4;
    public static final int FIRST_FRAME_NOT_RENDERED = 1;
    public static final int FIRST_FRAME_NOT_RENDERED_AFTER_STREAM_CHANGE = 2;
    public static final int FIRST_FRAME_NOT_RENDERED_ONLY_ALLOWED_IF_STARTED = 0;
    public static final int FIRST_FRAME_RENDERED = 3;
    public static final int FLAG_AUDIBILITY_ENFORCED = 1;
    public static final int FORMAT_EXCEEDS_CAPABILITIES = 3;
    public static final int FORMAT_HANDLED = 4;
    public static final int FORMAT_UNSUPPORTED_DRM = 2;
    public static final int FORMAT_UNSUPPORTED_SUBTYPE = 1;
    public static final int FORMAT_UNSUPPORTED_TYPE = 0;
    public static final int INDEX_UNSET = -1;
    public static final String LANGUAGE_UNDETERMINED = "und";
    public static final int LENGTH_UNSET = -1;
    public static final int MEDIA_CODEC_PRIORITY_NON_REALTIME = 1;
    public static final int MEDIA_CODEC_PRIORITY_REALTIME = 0;
    public static final long MICROS_PER_SECOND = 1000000;
    public static final long MILLIS_PER_SECOND = 1000;
    public static final long NANOS_PER_SECOND = 1000000000;
    public static final int NETWORK_TYPE_2G = 3;
    public static final int NETWORK_TYPE_3G = 4;
    public static final int NETWORK_TYPE_4G = 5;
    public static final int NETWORK_TYPE_5G_NSA = 10;
    public static final int NETWORK_TYPE_5G_SA = 9;
    public static final int NETWORK_TYPE_CELLULAR_UNKNOWN = 6;
    public static final int NETWORK_TYPE_ETHERNET = 7;
    public static final int NETWORK_TYPE_OFFLINE = 1;
    public static final int NETWORK_TYPE_OTHER = 8;
    public static final int NETWORK_TYPE_UNKNOWN = 0;
    public static final int NETWORK_TYPE_WIFI = 2;
    public static final int PERCENTAGE_UNSET = -1;

    @Deprecated
    public static final int POSITION_UNSET = -1;
    public static final int PRIORITY_DOWNLOAD = -4000;
    public static final int PRIORITY_MAX = 0;
    public static final int PRIORITY_PLAYBACK = -1000;
    public static final int PRIORITY_PLAYBACK_PRELOAD = -3000;
    public static final int PRIORITY_PROCESSING_BACKGROUND = -4000;
    public static final int PRIORITY_PROCESSING_FOREGROUND = -2000;
    public static final int PROJECTION_CUBEMAP = 2;
    public static final int PROJECTION_EQUIRECTANGULAR = 1;
    public static final int PROJECTION_MESH = 3;
    public static final int PROJECTION_RECTANGULAR = 0;
    public static final float RATE_UNSET = -3.4028235E38f;
    public static final int RATE_UNSET_INT = -2147483647;
    public static final int RESULT_BUFFER_READ = -4;
    public static final int RESULT_END_OF_INPUT = -1;
    public static final int RESULT_FORMAT_READ = -5;
    public static final int RESULT_MAX_LENGTH_EXCEEDED = -2;
    public static final int RESULT_NOTHING_READ = -3;
    public static final int ROLE_FLAG_ALTERNATE = 2;
    public static final int ROLE_FLAG_AUXILIARY = 32768;
    public static final int ROLE_FLAG_CAPTION = 64;
    public static final int ROLE_FLAG_COMMENTARY = 8;
    public static final int ROLE_FLAG_DESCRIBES_MUSIC_AND_SOUND = 1024;
    public static final int ROLE_FLAG_DESCRIBES_VIDEO = 512;
    public static final int ROLE_FLAG_DUB = 16;
    public static final int ROLE_FLAG_EASY_TO_READ = 8192;
    public static final int ROLE_FLAG_EMERGENCY = 32;
    public static final int ROLE_FLAG_ENHANCED_DIALOG_INTELLIGIBILITY = 2048;
    public static final int ROLE_FLAG_MAIN = 1;
    public static final int ROLE_FLAG_SIGN = 256;
    public static final int ROLE_FLAG_SUBTITLE = 128;
    public static final int ROLE_FLAG_SUPPLEMENTARY = 4;
    public static final int ROLE_FLAG_TRANSCRIBES_DIALOG = 4096;
    public static final int ROLE_FLAG_TRICK_PLAY = 16384;
    public static final String SANS_SERIF_NAME = "sans-serif";
    public static final int SELECTION_FLAG_AUTOSELECT = 4;
    public static final int SELECTION_FLAG_DEFAULT = 1;
    public static final int SELECTION_FLAG_FORCED = 2;
    public static final int SELECTION_REASON_ADAPTIVE = 3;
    public static final int SELECTION_REASON_CUSTOM_BASE = 10000;
    public static final int SELECTION_REASON_INITIAL = 1;
    public static final int SELECTION_REASON_MANUAL = 2;
    public static final int SELECTION_REASON_TRICK_PLAY = 4;
    public static final int SELECTION_REASON_UNKNOWN = 0;
    public static final String SERIF_NAME = "serif";
    public static final int SPATIALIZATION_BEHAVIOR_AUTO = 0;
    public static final int SPATIALIZATION_BEHAVIOR_NEVER = 1;
    public static final String SSAI_SCHEME = "ssai";
    public static final int STEREO_MODE_INTERLEAVED_LEFT_PRIMARY = 4;
    public static final int STEREO_MODE_INTERLEAVED_RIGHT_PRIMARY = 5;
    public static final int STEREO_MODE_LEFT_RIGHT = 2;
    public static final int STEREO_MODE_MONO = 0;
    public static final int STEREO_MODE_STEREO_MESH = 3;
    public static final int STEREO_MODE_TOP_BOTTOM = 1;
    public static final int STREAM_TYPE_ACCESSIBILITY = 10;
    public static final int STREAM_TYPE_ALARM = 4;
    public static final int STREAM_TYPE_DEFAULT = 3;
    public static final int STREAM_TYPE_DTMF = 8;
    public static final int STREAM_TYPE_MUSIC = 3;
    public static final int STREAM_TYPE_NOTIFICATION = 5;
    public static final int STREAM_TYPE_RING = 2;
    public static final int STREAM_TYPE_SYSTEM = 1;
    public static final int STREAM_TYPE_VOICE_CALL = 0;
    public static final int TEXTURE_MIN_FILTER_LINEAR = 9729;
    public static final int TEXTURE_MIN_FILTER_LINEAR_MIPMAP_LINEAR = 9987;
    public static final long TIME_END_OF_SOURCE = Long.MIN_VALUE;
    public static final long TIME_UNSET = -9223372036854775807L;
    public static final int TRACK_TYPE_AUDIO = 1;
    public static final int TRACK_TYPE_CAMERA_MOTION = 6;
    public static final int TRACK_TYPE_CUSTOM_BASE = 10000;
    public static final int TRACK_TYPE_DEFAULT = 0;
    public static final int TRACK_TYPE_IMAGE = 4;
    public static final int TRACK_TYPE_METADATA = 5;
    public static final int TRACK_TYPE_NONE = -2;
    public static final int TRACK_TYPE_TEXT = 3;
    public static final int TRACK_TYPE_UNKNOWN = -1;
    public static final int TRACK_TYPE_VIDEO = 2;

    @Deprecated
    public static final int TYPE_DASH = 0;

    @Deprecated
    public static final int TYPE_HLS = 2;

    @Deprecated
    public static final int TYPE_OTHER = 4;

    @Deprecated
    public static final int TYPE_RTSP = 3;

    @Deprecated
    public static final int TYPE_SS = 1;
    public static final int USAGE_ALARM = 4;
    public static final int USAGE_ASSISTANCE_ACCESSIBILITY = 11;
    public static final int USAGE_ASSISTANCE_NAVIGATION_GUIDANCE = 12;
    public static final int USAGE_ASSISTANCE_SONIFICATION = 13;
    public static final int USAGE_ASSISTANT = 16;
    public static final int USAGE_GAME = 14;
    public static final int USAGE_MEDIA = 1;
    public static final int USAGE_NOTIFICATION = 5;
    public static final int USAGE_NOTIFICATION_COMMUNICATION_DELAYED = 9;
    public static final int USAGE_NOTIFICATION_COMMUNICATION_INSTANT = 8;
    public static final int USAGE_NOTIFICATION_COMMUNICATION_REQUEST = 7;
    public static final int USAGE_NOTIFICATION_EVENT = 10;
    public static final int USAGE_NOTIFICATION_RINGTONE = 6;
    public static final int USAGE_UNKNOWN = 0;
    public static final int USAGE_VOICE_COMMUNICATION = 2;
    public static final int USAGE_VOICE_COMMUNICATION_SIGNALLING = 3;
    public static final int VIDEO_CHANGE_FRAME_RATE_STRATEGY_OFF = Integer.MIN_VALUE;
    public static final int VIDEO_CHANGE_FRAME_RATE_STRATEGY_ONLY_IF_SEAMLESS = 0;
    public static final int VIDEO_CODEC_FLAG_H264 = 1;
    public static final int VIDEO_CODEC_FLAG_H265 = 2;
    public static final int VIDEO_OUTPUT_MODE_NONE = -1;
    public static final int VIDEO_OUTPUT_MODE_SURFACE_YUV = 1;
    public static final int VIDEO_OUTPUT_MODE_YUV = 0;
    public static final int VIDEO_SCALING_MODE_DEFAULT = 1;
    public static final int VIDEO_SCALING_MODE_SCALE_TO_FIT = 1;
    public static final int VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING = 2;
    public static final int VOLUME_FLAG_ALLOW_RINGER_MODES = 2;
    public static final int VOLUME_FLAG_PLAY_SOUND = 4;
    public static final int VOLUME_FLAG_REMOVE_SOUND_AND_VIBRATE = 8;
    public static final int VOLUME_FLAG_SHOW_UI = 1;
    public static final int VOLUME_FLAG_VIBRATE = 16;
    public static final int VOLUME_OPERATION_TYPE_MUTE = 1;
    public static final int VOLUME_OPERATION_TYPE_SET_VOLUME = 0;
    public static final int VOLUME_OPERATION_TYPE_UNMUTE = 2;
    public static final int WAKE_MODE_LOCAL = 1;
    public static final int WAKE_MODE_NETWORK = 2;
    public static final int WAKE_MODE_NONE = 0;
    public static final UUID UUID_NIL = new UUID(0, 0);
    public static final UUID COMMON_PSSH_UUID = new UUID(1186680826959645954L, -5988876978535335093L);
    public static final UUID CLEARKEY_UUID = new UUID(-2129748144642739255L, 8654423357094679310L);
    public static final UUID WIDEVINE_UUID = new UUID(-1301668207276963122L, -6645017420763422227L);
    public static final UUID PLAYREADY_UUID = new UUID(-7348484286925749626L, -6083546864340672619L);

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface AudioAllowedCapturePolicy {
    }

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface AudioContentType {
    }

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface AudioFlags {
    }

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface AudioUsage {
    }

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface AuxiliaryTrackType {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface BufferFlags {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface ColorRange {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface ColorSpace {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface ColorTransfer {
    }

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface ContentType {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface CryptoMode {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface CryptoType {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface DataType {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Encoding {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface FirstFrameState {
    }

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface FormatSupport {
    }

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface NetworkType {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface PcmEncoding {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Priority {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Projection {
    }

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface RoleFlags {
    }

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface SelectionFlags {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface SelectionReason {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface SpatializationBehavior {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface StereoMode {
    }

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface StreamType {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface TextureMinFilter {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface TrackType {
    }

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface VideoChangeFrameRateStrategy {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface VideoCodecFlags {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface VideoOutputMode {
    }

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface VideoScalingMode {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface VolumeFlags {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface VolumeOperationType {
    }

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface WakeMode {
    }

    private C() {
    }

    @Deprecated
    public static int generateAudioSessionIdV21(Context context) {
        return Util.generateAudioSessionIdV21(context);
    }

    @Deprecated
    public static int getErrorCodeForMediaDrmErrorCode(int i10) {
        return Util.getErrorCodeForMediaDrmErrorCode(i10);
    }

    @Deprecated
    public static String getFormatSupportString(int i10) {
        return Util.getFormatSupportString(i10);
    }

    @Deprecated
    public static long msToUs(long j10) {
        return Util.msToUs(j10);
    }

    @Deprecated
    public static long usToMs(long j10) {
        return Util.usToMs(j10);
    }
}
