package androidx.media3.exoplayer.audio;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioDeviceInfo;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Pair;
import android.util.SparseArray;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.audio.AudioManagerCompat;
import androidx.media3.common.util.Util;
import com.google.common.collect.a0;
import com.google.common.collect.e1;
import com.google.common.collect.h1;
import com.google.common.collect.k1;
import com.google.common.collect.l1;
import com.google.common.collect.o4;
import com.google.common.collect.v1;
import com.google.common.collect.w1;
import j$.util.Objects;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import m2.f0;

/* JADX INFO: loaded from: classes3.dex */
public final class AudioCapabilities {
    static final l1 ALL_SURROUND_ENCODINGS_AND_MAX_CHANNELS;
    public static final AudioCapabilities DEFAULT_AUDIO_CAPABILITIES = new AudioCapabilities(h1.s(AudioProfile.DEFAULT_AUDIO_PROFILE));
    static final int DEFAULT_MAX_CHANNEL_COUNT = 10;
    static final int DEFAULT_SAMPLE_RATE_HZ = 48000;
    private static final h1 EXTERNAL_SURROUND_SOUND_ENCODINGS;
    private static final String EXTERNAL_SURROUND_SOUND_KEY = "external_surround_sound_enabled";
    private static final String FORCE_EXTERNAL_SURROUND_SOUND_KEY = "use_external_surround_sound_flag";
    private final SparseArray<AudioProfile> encodingToAudioProfile;
    private final int maxChannelCount;

    public static final class Api29 {
        private Api29() {
        }

        public static h1 getDirectPlaybackSupportedEncodings(AudioAttributes audioAttributes) {
            e1 e1VarK = h1.k();
            o4 it = AudioCapabilities.ALL_SURROUND_ENCODINGS_AND_MAX_CHANNELS.keySet().iterator();
            while (it.hasNext()) {
                Integer num = (Integer) it.next();
                int iIntValue = num.intValue();
                if (Build.VERSION.SDK_INT >= Util.getApiLevelThatAudioFormatIntroducedAudioEncoding(iIntValue) && AudioTrack.isDirectPlaybackSupported(new AudioFormat.Builder().setChannelMask(12).setEncoding(iIntValue).setSampleRate(48000).build(), audioAttributes.getPlatformAudioAttributes())) {
                    e1VarK.c(num);
                }
            }
            e1VarK.c(2);
            return e1VarK.f();
        }

        public static int getMaxSupportedChannelCountForPassthrough(int i10, int i11, AudioAttributes audioAttributes) {
            for (int i12 = 10; i12 > 0; i12--) {
                int audioTrackChannelConfig = Util.getAudioTrackChannelConfig(i12);
                if (audioTrackChannelConfig != 0 && AudioTrack.isDirectPlaybackSupported(new AudioFormat.Builder().setEncoding(i10).setSampleRate(i11).setChannelMask(audioTrackChannelConfig).build(), audioAttributes.getPlatformAudioAttributes())) {
                    return i12;
                }
            }
            return 0;
        }
    }

    public static final class Api33 {
        private Api33() {
        }

        public static AudioCapabilities getCapabilitiesInternalForDirectPlayback(AudioManager audioManager, AudioAttributes audioAttributes) {
            return new AudioCapabilities(AudioCapabilities.getAudioProfiles(audioManager.getDirectProfilesForAttributes(audioAttributes.getPlatformAudioAttributes())));
        }

        public static AudioDeviceInfo getDefaultRoutedDeviceForAttributes(AudioManager audioManager, AudioAttributes audioAttributes) {
            audioManager.getClass();
            List<AudioDeviceInfo> audioDevicesForAttributes = audioManager.getAudioDevicesForAttributes(audioAttributes.getPlatformAudioAttributes());
            if (audioDevicesForAttributes.isEmpty()) {
                return null;
            }
            return audioDevicesForAttributes.get(0);
        }
    }

    static {
        Object[] objArr = {2, 5, 6};
        a0.a(3, objArr);
        EXTERNAL_SURROUND_SOUND_ENCODINGS = h1.h(3, objArr);
        k1 k1Var = new k1(4);
        k1Var.c(5, 6);
        k1Var.c(17, 6);
        k1Var.c(7, 6);
        k1Var.c(30, 10);
        k1Var.c(18, 6);
        k1Var.c(6, 8);
        k1Var.c(8, 8);
        k1Var.c(14, 8);
        ALL_SURROUND_ENCODINGS_AND_MAX_CHANNELS = k1Var.a(true);
    }

    private static boolean deviceMaySetExternalSurroundSoundGlobalSetting() {
        String str = Build.MANUFACTURER;
        return str.equals("Amazon") || str.equals("Xiaomi");
    }

    private static w1 getAllBluetoothDeviceTypes() {
        v1 v1Var = new v1(4);
        Integer[] numArr = {8, 7};
        a0.a(2, numArr);
        v1Var.e(2);
        System.arraycopy(numArr, 0, v1Var.f13927a, v1Var.f13928b, 2);
        v1Var.f13928b += 2;
        int i10 = Build.VERSION.SDK_INT;
        if (i10 >= 31) {
            Integer[] numArr2 = {26, 27};
            a0.a(2, numArr2);
            v1Var.e(2);
            System.arraycopy(numArr2, 0, v1Var.f13927a, v1Var.f13928b, 2);
            v1Var.f13928b += 2;
        }
        if (i10 >= 33) {
            v1Var.c(30);
        }
        return v1Var.f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static h1 getAudioProfiles(List<android.media.AudioProfile> list) {
        HashMap map = new HashMap();
        map.put(2, new HashSet(f0.b(12)));
        for (int i10 = 0; i10 < list.size(); i10++) {
            android.media.AudioProfile audioProfile = list.get(i10);
            if (audioProfile.getEncapsulationType() != 1) {
                int format = audioProfile.getFormat();
                if (Util.isEncodingLinearPcm(format) || ALL_SURROUND_ENCODINGS_AND_MAX_CHANNELS.containsKey(Integer.valueOf(format))) {
                    if (map.containsKey(Integer.valueOf(format))) {
                        Set set = (Set) map.get(Integer.valueOf(format));
                        set.getClass();
                        set.addAll(f0.b(audioProfile.getChannelMasks()));
                    } else {
                        map.put(Integer.valueOf(format), new HashSet(f0.b(audioProfile.getChannelMasks())));
                    }
                }
            }
        }
        e1 e1VarK = h1.k();
        for (Map.Entry entry : map.entrySet()) {
            e1VarK.c(new AudioProfile(((Integer) entry.getKey()).intValue(), (Set<Integer>) entry.getValue()));
        }
        return e1VarK.f();
    }

    @Deprecated
    public static AudioCapabilities getCapabilities(Context context) {
        return getCapabilities(context, AudioAttributes.DEFAULT, null);
    }

    public static AudioCapabilities getCapabilitiesInternal(Context context, AudioAttributes audioAttributes, AudioDeviceInfo audioDeviceInfo) {
        return getCapabilitiesInternal(context, context.registerReceiver(null, new IntentFilter("android.media.action.HDMI_AUDIO_PLUG")), audioAttributes, audioDeviceInfo);
    }

    private static int getChannelConfigForPassthrough(int i10) {
        int i11 = Build.VERSION.SDK_INT;
        if (i11 <= 28) {
            if (i10 == 7) {
                i10 = 8;
            } else if (i10 == 3 || i10 == 4 || i10 == 5) {
                i10 = 6;
            }
        }
        if (i11 <= 26 && "fugu".equals(Build.DEVICE) && i10 == 1) {
            i10 = 2;
        }
        return Util.getAudioTrackChannelConfig(i10);
    }

    public static Uri getExternalSurroundSoundGlobalSettingUri() {
        if (deviceMaySetExternalSurroundSoundGlobalSetting()) {
            return Settings.Global.getUriFor(EXTERNAL_SURROUND_SOUND_KEY);
        }
        return null;
    }

    private static boolean isBluetoothConnected(AudioManager audioManager, AudioDeviceInfo audioDeviceInfo) {
        AudioDeviceInfo[] devices;
        if (audioDeviceInfo == null) {
            audioManager.getClass();
            devices = audioManager.getDevices(2);
        } else {
            devices = new AudioDeviceInfo[]{audioDeviceInfo};
        }
        w1 allBluetoothDeviceTypes = getAllBluetoothDeviceTypes();
        for (AudioDeviceInfo audioDeviceInfo2 : devices) {
            if (allBluetoothDeviceTypes.contains(Integer.valueOf(audioDeviceInfo2.getType()))) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AudioCapabilities)) {
            return false;
        }
        AudioCapabilities audioCapabilities = (AudioCapabilities) obj;
        return Util.contentEquals(this.encodingToAudioProfile, audioCapabilities.encodingToAudioProfile) && this.maxChannelCount == audioCapabilities.maxChannelCount;
    }

    @Deprecated
    public Pair<Integer, Integer> getEncodingAndChannelConfigForPassthrough(Format format) {
        return getEncodingAndChannelConfigForPassthrough(format, AudioAttributes.DEFAULT);
    }

    public int getMaxChannelCount() {
        return this.maxChannelCount;
    }

    public int hashCode() {
        return (Util.contentHashCode(this.encodingToAudioProfile) * 31) + this.maxChannelCount;
    }

    @Deprecated
    public boolean isPassthroughPlaybackSupported(Format format) {
        return isPassthroughPlaybackSupported(format, AudioAttributes.DEFAULT);
    }

    public boolean supportsEncoding(int i10) {
        return Util.contains(this.encodingToAudioProfile, i10);
    }

    public String toString() {
        return "AudioCapabilities[maxChannelCount=" + this.maxChannelCount + ", audioProfiles=" + this.encodingToAudioProfile + "]";
    }

    @Deprecated
    public AudioCapabilities(int[] iArr, int i10) {
        this(getAudioProfiles(iArr, i10));
    }

    public static AudioCapabilities getCapabilities(Context context, AudioAttributes audioAttributes, AudioDeviceInfo audioDeviceInfo) {
        return getCapabilitiesInternal(context, audioAttributes, audioDeviceInfo);
    }

    public Pair<Integer, Integer> getEncodingAndChannelConfigForPassthrough(Format format, AudioAttributes audioAttributes) {
        String str = format.sampleMimeType;
        str.getClass();
        int encoding = MimeTypes.getEncoding(str, format.codecs);
        if (!ALL_SURROUND_ENCODINGS_AND_MAX_CHANNELS.containsKey(Integer.valueOf(encoding))) {
            return null;
        }
        if (encoding == 18 && !supportsEncoding(18)) {
            encoding = 6;
        } else if ((encoding == 8 && !supportsEncoding(8)) || (encoding == 30 && !supportsEncoding(30))) {
            encoding = 7;
        }
        if (!supportsEncoding(encoding)) {
            return null;
        }
        AudioProfile audioProfile = this.encodingToAudioProfile.get(encoding);
        audioProfile.getClass();
        int maxSupportedChannelCountForPassthrough = format.channelCount;
        if (maxSupportedChannelCountForPassthrough == -1 || encoding == 18) {
            int i10 = format.sampleRate;
            if (i10 == -1) {
                i10 = 48000;
            }
            maxSupportedChannelCountForPassthrough = audioProfile.getMaxSupportedChannelCountForPassthrough(i10, audioAttributes);
        } else if (!format.sampleMimeType.equals(MimeTypes.AUDIO_DTS_X) || Build.VERSION.SDK_INT >= 33) {
            if (!audioProfile.supportsChannelCount(maxSupportedChannelCountForPassthrough)) {
                return null;
            }
        } else if (maxSupportedChannelCountForPassthrough > 10) {
            return null;
        }
        int channelConfigForPassthrough = getChannelConfigForPassthrough(maxSupportedChannelCountForPassthrough);
        if (channelConfigForPassthrough == 0) {
            return null;
        }
        return Pair.create(Integer.valueOf(encoding), Integer.valueOf(channelConfigForPassthrough));
    }

    public boolean isPassthroughPlaybackSupported(Format format, AudioAttributes audioAttributes) {
        return getEncodingAndChannelConfigForPassthrough(format, audioAttributes) != null;
    }

    private AudioCapabilities(List<AudioProfile> list) {
        this.encodingToAudioProfile = new SparseArray<>();
        for (int i10 = 0; i10 < list.size(); i10++) {
            AudioProfile audioProfile = list.get(i10);
            this.encodingToAudioProfile.put(audioProfile.encoding, audioProfile);
        }
        int iMax = 0;
        for (int i11 = 0; i11 < this.encodingToAudioProfile.size(); i11++) {
            iMax = Math.max(iMax, this.encodingToAudioProfile.valueAt(i11).maxChannelCount);
        }
        this.maxChannelCount = iMax;
    }

    public static AudioCapabilities getCapabilitiesInternal(Context context, Intent intent, AudioAttributes audioAttributes, AudioDeviceInfo audioDeviceInfo) {
        AudioManager audioManager = AudioManagerCompat.getAudioManager(context);
        if (audioDeviceInfo == null) {
            audioDeviceInfo = Build.VERSION.SDK_INT >= 33 ? Api33.getDefaultRoutedDeviceForAttributes(audioManager, audioAttributes) : null;
        }
        int i10 = Build.VERSION.SDK_INT;
        if (i10 >= 33 && (Util.isTv(context) || Util.isAutomotive(context))) {
            return Api33.getCapabilitiesInternalForDirectPlayback(audioManager, audioAttributes);
        }
        if (isBluetoothConnected(audioManager, audioDeviceInfo)) {
            return DEFAULT_AUDIO_CAPABILITIES;
        }
        v1 v1Var = new v1(4);
        v1Var.c(2);
        if (i10 >= 29 && (Util.isTv(context) || Util.isAutomotive(context))) {
            h1 directPlaybackSupportedEncodings = Api29.getDirectPlaybackSupportedEncodings(audioAttributes);
            directPlaybackSupportedEncodings.getClass();
            v1Var.d(directPlaybackSupportedEncodings);
            return new AudioCapabilities(getAudioProfiles(f0.O(v1Var.f()), 10));
        }
        ContentResolver contentResolver = context.getContentResolver();
        boolean z = Settings.Global.getInt(contentResolver, FORCE_EXTERNAL_SURROUND_SOUND_KEY, 0) == 1;
        if ((z || deviceMaySetExternalSurroundSoundGlobalSetting()) && Settings.Global.getInt(contentResolver, EXTERNAL_SURROUND_SOUND_KEY, 0) == 1) {
            h1 h1Var = EXTERNAL_SURROUND_SOUND_ENCODINGS;
            h1Var.getClass();
            v1Var.d(h1Var);
        }
        if (intent != null && !z && intent.getIntExtra("android.media.extra.AUDIO_PLUG_STATE", 0) == 1) {
            int[] intArrayExtra = intent.getIntArrayExtra("android.media.extra.ENCODINGS");
            if (intArrayExtra != null) {
                List listB = f0.b(intArrayExtra);
                listB.getClass();
                v1Var.d(listB);
            }
            return new AudioCapabilities(getAudioProfiles(f0.O(v1Var.f()), intent.getIntExtra("android.media.extra.MAX_CHANNEL_COUNT", 10)));
        }
        return new AudioCapabilities(getAudioProfiles(f0.O(v1Var.f()), 10));
    }

    public static final class AudioProfile {
        public static final AudioProfile DEFAULT_AUDIO_PROFILE;
        private final w1 channelMasks;
        public final int encoding;
        public final int maxChannelCount;

        static {
            DEFAULT_AUDIO_PROFILE = Build.VERSION.SDK_INT >= 33 ? new AudioProfile(2, getAllChannelMasksForMaxChannelCount(10)) : new AudioProfile(2, 10);
        }

        public AudioProfile(int i10, Set<Integer> set) {
            this.encoding = i10;
            w1 w1VarM = w1.m(set);
            this.channelMasks = w1VarM;
            o4 it = w1VarM.iterator();
            int iMax = 0;
            while (it.hasNext()) {
                iMax = Math.max(iMax, Integer.bitCount(((Integer) it.next()).intValue()));
            }
            this.maxChannelCount = iMax;
        }

        private static w1 getAllChannelMasksForMaxChannelCount(int i10) {
            v1 v1Var = new v1(4);
            for (int i11 = 1; i11 <= i10; i11++) {
                v1Var.c(Integer.valueOf(Util.getAudioTrackChannelConfig(i11)));
            }
            return v1Var.f();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AudioProfile)) {
                return false;
            }
            AudioProfile audioProfile = (AudioProfile) obj;
            return this.encoding == audioProfile.encoding && this.maxChannelCount == audioProfile.maxChannelCount && Objects.equals(this.channelMasks, audioProfile.channelMasks);
        }

        public int getMaxSupportedChannelCountForPassthrough(int i10, AudioAttributes audioAttributes) {
            if (this.channelMasks != null) {
                return this.maxChannelCount;
            }
            if (Build.VERSION.SDK_INT >= 29) {
                return Api29.getMaxSupportedChannelCountForPassthrough(this.encoding, i10, audioAttributes);
            }
            Object obj = AudioCapabilities.ALL_SURROUND_ENCODINGS_AND_MAX_CHANNELS.get(Integer.valueOf(this.encoding));
            return ((Integer) (obj != null ? obj : 0)).intValue();
        }

        public int hashCode() {
            int i10 = ((this.encoding * 31) + this.maxChannelCount) * 31;
            w1 w1Var = this.channelMasks;
            return i10 + (w1Var == null ? 0 : w1Var.hashCode());
        }

        public boolean supportsChannelCount(int i10) {
            if (this.channelMasks == null) {
                return i10 <= this.maxChannelCount;
            }
            int audioTrackChannelConfig = Util.getAudioTrackChannelConfig(i10);
            if (audioTrackChannelConfig == 0) {
                return false;
            }
            return this.channelMasks.contains(Integer.valueOf(audioTrackChannelConfig));
        }

        public String toString() {
            return "AudioProfile[format=" + this.encoding + ", maxChannelCount=" + this.maxChannelCount + ", channelMasks=" + this.channelMasks + "]";
        }

        public AudioProfile(int i10, int i11) {
            this.encoding = i10;
            this.maxChannelCount = i11;
            this.channelMasks = null;
        }
    }

    private static h1 getAudioProfiles(int[] iArr, int i10) {
        e1 e1VarK = h1.k();
        if (iArr == null) {
            iArr = new int[0];
        }
        for (int i11 : iArr) {
            e1VarK.c(new AudioProfile(i11, i10));
        }
        return e1VarK.f();
    }
}
