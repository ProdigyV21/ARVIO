package androidx.media3.common;

import android.media.AudioAttributes;
import android.os.Build;
import android.os.Bundle;
import androidx.media3.common.util.Util;

/* JADX INFO: loaded from: classes3.dex */
public final class AudioAttributes {
    public final int allowedCapturePolicy;
    public final int contentType;
    public final int flags;
    public final boolean hapticChannelsMuted;
    public final boolean isContentSpatialized;
    private android.media.AudioAttributes platformAudioAttributes;
    public final int spatializationBehavior;
    public final int usage;
    public static final AudioAttributes DEFAULT = new Builder().build();
    private static final String FIELD_CONTENT_TYPE = Util.intToStringMaxRadix(0);
    private static final String FIELD_FLAGS = Util.intToStringMaxRadix(1);
    private static final String FIELD_USAGE = Util.intToStringMaxRadix(2);
    private static final String FIELD_ALLOWED_CAPTURE_POLICY = Util.intToStringMaxRadix(3);
    private static final String FIELD_SPATIALIZATION_BEHAVIOR = Util.intToStringMaxRadix(4);
    private static final String FIELD_IS_CONTENT_SPATIALIZED = Util.intToStringMaxRadix(5);
    private static final String FIELD_HAPTIC_CHANNELS_MUTED = Util.intToStringMaxRadix(6);

    public static final class Api29 {
        private Api29() {
        }

        public static void setAllowedCapturePolicy(AudioAttributes.Builder builder, int i10) {
            builder.setAllowedCapturePolicy(i10);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void setHapticChannelsMuted(AudioAttributes.Builder builder, boolean z) {
            builder.setHapticChannelsMuted(z);
        }
    }

    public static final class Api32 {
        private Api32() {
        }

        public static void setIsContentSpatialized(AudioAttributes.Builder builder, boolean z) {
            builder.setIsContentSpatialized(z);
        }

        public static void setSpatializationBehavior(AudioAttributes.Builder builder, int i10) {
            builder.setSpatializationBehavior(i10);
        }
    }

    @Deprecated
    public static final class AudioAttributesV21 {
        public final android.media.AudioAttributes audioAttributes;

        private AudioAttributesV21(android.media.AudioAttributes audioAttributes) {
            this.audioAttributes = audioAttributes;
        }
    }

    public static final class Builder {
        private int contentType = 0;
        private int flags = 0;
        private int usage = 1;
        private int allowedCapturePolicy = 1;
        private int spatializationBehavior = 0;
        private boolean isContentSpatialized = false;
        private boolean hapticChannelsMuted = true;

        public AudioAttributes build() {
            return new AudioAttributes(this.contentType, this.flags, this.usage, this.allowedCapturePolicy, this.spatializationBehavior, this.isContentSpatialized, this.hapticChannelsMuted);
        }

        public Builder setAllowedCapturePolicy(int i10) {
            this.allowedCapturePolicy = i10;
            return this;
        }

        public Builder setContentType(int i10) {
            this.contentType = i10;
            return this;
        }

        public Builder setFlags(int i10) {
            this.flags = i10;
            return this;
        }

        public Builder setHapticChannelsMuted(boolean z) {
            this.hapticChannelsMuted = z;
            return this;
        }

        public Builder setIsContentSpatialized(boolean z) {
            this.isContentSpatialized = z;
            return this;
        }

        public Builder setSpatializationBehavior(int i10) {
            this.spatializationBehavior = i10;
            return this;
        }

        public Builder setUsage(int i10) {
            this.usage = i10;
            return this;
        }
    }

    public static AudioAttributes fromBundle(Bundle bundle) {
        Builder builder = new Builder();
        String str = FIELD_CONTENT_TYPE;
        if (bundle.containsKey(str)) {
            builder.setContentType(bundle.getInt(str));
        }
        String str2 = FIELD_FLAGS;
        if (bundle.containsKey(str2)) {
            builder.setFlags(bundle.getInt(str2));
        }
        String str3 = FIELD_USAGE;
        if (bundle.containsKey(str3)) {
            builder.setUsage(bundle.getInt(str3));
        }
        String str4 = FIELD_ALLOWED_CAPTURE_POLICY;
        if (bundle.containsKey(str4)) {
            builder.setAllowedCapturePolicy(bundle.getInt(str4));
        }
        String str5 = FIELD_SPATIALIZATION_BEHAVIOR;
        if (bundle.containsKey(str5)) {
            builder.setSpatializationBehavior(bundle.getInt(str5));
        }
        String str6 = FIELD_IS_CONTENT_SPATIALIZED;
        if (bundle.containsKey(str6)) {
            builder.setIsContentSpatialized(bundle.getBoolean(str6));
        }
        String str7 = FIELD_HAPTIC_CHANNELS_MUTED;
        if (bundle.containsKey(str7)) {
            builder.setHapticChannelsMuted(bundle.getBoolean(str7));
        }
        return builder.build();
    }

    public static AudioAttributes fromPlatformAudioAttributes(android.media.AudioAttributes audioAttributes) {
        Builder usage = new Builder().setContentType(audioAttributes.getContentType()).setFlags(audioAttributes.getFlags()).setUsage(audioAttributes.getUsage());
        int i10 = Build.VERSION.SDK_INT;
        if (i10 >= 29) {
            usage.setAllowedCapturePolicy(audioAttributes.getAllowedCapturePolicy());
            usage.setHapticChannelsMuted(audioAttributes.areHapticChannelsMuted());
        }
        if (i10 >= 32) {
            usage.setSpatializationBehavior(audioAttributes.getSpatializationBehavior());
            usage.setIsContentSpatialized(audioAttributes.isContentSpatialized());
        }
        return usage.build();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && AudioAttributes.class == obj.getClass()) {
            AudioAttributes audioAttributes = (AudioAttributes) obj;
            if (this.contentType == audioAttributes.contentType && this.flags == audioAttributes.flags && this.usage == audioAttributes.usage && this.allowedCapturePolicy == audioAttributes.allowedCapturePolicy && this.spatializationBehavior == audioAttributes.spatializationBehavior && this.isContentSpatialized == audioAttributes.isContentSpatialized && this.hapticChannelsMuted == audioAttributes.hapticChannelsMuted) {
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public AudioAttributesV21 getAudioAttributesV21() {
        return new AudioAttributesV21(getPlatformAudioAttributes());
    }

    public android.media.AudioAttributes getPlatformAudioAttributes() {
        if (this.platformAudioAttributes == null) {
            AudioAttributes.Builder usage = new AudioAttributes.Builder().setContentType(this.contentType).setFlags(this.flags).setUsage(this.usage);
            int i10 = Build.VERSION.SDK_INT;
            if (i10 >= 29) {
                Api29.setAllowedCapturePolicy(usage, this.allowedCapturePolicy);
                Api29.setHapticChannelsMuted(usage, this.hapticChannelsMuted);
            }
            if (i10 >= 32) {
                Api32.setSpatializationBehavior(usage, this.spatializationBehavior);
                Api32.setIsContentSpatialized(usage, this.isContentSpatialized);
            }
            this.platformAudioAttributes = usage.build();
        }
        return this.platformAudioAttributes;
    }

    public int getStreamType() {
        if ((this.flags & 1) == 1) {
            return 1;
        }
        switch (this.usage) {
        }
        return 1;
    }

    public int hashCode() {
        return ((((((((((((527 + this.contentType) * 31) + this.flags) * 31) + this.usage) * 31) + this.allowedCapturePolicy) * 31) + this.spatializationBehavior) * 31) + (this.isContentSpatialized ? 1 : 0)) * 31) + (this.hapticChannelsMuted ? 1 : 0);
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        int i10 = this.contentType;
        if (i10 != 0) {
            bundle.putInt(FIELD_CONTENT_TYPE, i10);
        }
        int i11 = this.flags;
        if (i11 != 0) {
            bundle.putInt(FIELD_FLAGS, i11);
        }
        int i12 = this.usage;
        if (i12 != 1) {
            bundle.putInt(FIELD_USAGE, i12);
        }
        int i13 = this.allowedCapturePolicy;
        if (i13 != 1) {
            bundle.putInt(FIELD_ALLOWED_CAPTURE_POLICY, i13);
        }
        int i14 = this.spatializationBehavior;
        if (i14 != 0) {
            bundle.putInt(FIELD_SPATIALIZATION_BEHAVIOR, i14);
        }
        boolean z = this.isContentSpatialized;
        if (z) {
            bundle.putBoolean(FIELD_IS_CONTENT_SPATIALIZED, z);
        }
        boolean z5 = this.hapticChannelsMuted;
        if (!z5) {
            bundle.putBoolean(FIELD_HAPTIC_CHANNELS_MUTED, z5);
        }
        return bundle;
    }

    private AudioAttributes(int i10, int i11, int i12, int i13, int i14, boolean z, boolean z5) {
        this.contentType = i10;
        this.flags = i11;
        this.usage = i12;
        this.allowedCapturePolicy = i13;
        this.spatializationBehavior = i14;
        this.isContentSpatialized = z;
        this.hapticChannelsMuted = z5;
    }
}
