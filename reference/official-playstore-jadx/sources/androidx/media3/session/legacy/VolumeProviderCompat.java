package androidx.media3.session.legacy;

import android.media.VolumeProvider;
import android.os.Build;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes3.dex */
public abstract class VolumeProviderCompat {
    public static final int VOLUME_CONTROL_ABSOLUTE = 2;
    public static final int VOLUME_CONTROL_FIXED = 0;
    public static final int VOLUME_CONTROL_RELATIVE = 1;
    private Callback callback;
    private final String controlId;
    private final int controlType;
    private int currentVolume;
    private final int maxVolume;
    private VolumeProvider volumeProviderFwk;

    public static abstract class Callback {
        public abstract void onVolumeChanged(VolumeProviderCompat volumeProviderCompat);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ControlType {
    }

    public VolumeProviderCompat(int i10, int i11, int i12) {
        this(i10, i11, i12, null);
    }

    public final int getMaxVolume() {
        return this.maxVolume;
    }

    public Object getVolumeProvider() {
        VolumeProviderCompat volumeProviderCompat;
        if (this.volumeProviderFwk != null) {
            volumeProviderCompat = this;
        } else if (Build.VERSION.SDK_INT >= 30) {
            volumeProviderCompat = this;
            volumeProviderCompat.volumeProviderFwk = new VolumeProvider(this.controlType, this.maxVolume, this.currentVolume, this.controlId) { // from class: androidx.media3.session.legacy.VolumeProviderCompat.1
                @Override // android.media.VolumeProvider
                public void onAdjustVolume(int i10) {
                    VolumeProviderCompat.this.onAdjustVolume(i10);
                }

                @Override // android.media.VolumeProvider
                public void onSetVolumeTo(int i10) {
                    VolumeProviderCompat.this.onSetVolumeTo(i10);
                }
            };
        } else {
            volumeProviderCompat = this;
            volumeProviderCompat.volumeProviderFwk = new VolumeProvider(volumeProviderCompat.controlType, volumeProviderCompat.maxVolume, volumeProviderCompat.currentVolume) { // from class: androidx.media3.session.legacy.VolumeProviderCompat.2
                @Override // android.media.VolumeProvider
                public void onAdjustVolume(int i10) {
                    VolumeProviderCompat.this.onAdjustVolume(i10);
                }

                @Override // android.media.VolumeProvider
                public void onSetVolumeTo(int i10) {
                    VolumeProviderCompat.this.onSetVolumeTo(i10);
                }
            };
        }
        return volumeProviderCompat.volumeProviderFwk;
    }

    public void onAdjustVolume(int i10) {
    }

    public void onSetVolumeTo(int i10) {
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public final void setCurrentVolume(int i10) {
        this.currentVolume = i10;
        ((VolumeProvider) getVolumeProvider()).setCurrentVolume(i10);
        Callback callback = this.callback;
        if (callback != null) {
            callback.onVolumeChanged(this);
        }
    }

    public VolumeProviderCompat(int i10, int i11, int i12, String str) {
        this.controlType = i10;
        this.maxVolume = i11;
        this.currentVolume = i12;
        this.controlId = str;
    }
}
