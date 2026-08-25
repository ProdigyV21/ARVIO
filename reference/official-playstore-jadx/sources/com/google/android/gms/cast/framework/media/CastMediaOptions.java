package com.google.android.gms.cast.framework.media;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.dynamic.d;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public class CastMediaOptions extends a {
    private final String zzb;
    private final String zzc;
    private final zzd zzd;
    private final NotificationOptions zze;
    private final boolean zzf;
    private final boolean zzg;
    private static final Logger zza = new Logger("CastMediaOptions");
    public static final Parcelable.Creator<CastMediaOptions> CREATOR = new zza();

    public static final class Builder {
        private String zzb;
        private ImagePicker zzc;
        private String zza = "com.google.android.gms.cast.framework.media.MediaIntentReceiver";
        private NotificationOptions zzd = new NotificationOptions.Builder().build();
        private boolean zze = true;

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r4v0, types: [android.os.IBinder] */
        public CastMediaOptions build() {
            ImagePicker imagePicker = this.zzc;
            return new CastMediaOptions(this.zza, this.zzb, imagePicker == null ? null : imagePicker.zza(), this.zzd, false, this.zze);
        }

        public Builder setExpandedControllerActivityClassName(String str) {
            this.zzb = str;
            return this;
        }

        public Builder setImagePicker(ImagePicker imagePicker) {
            this.zzc = imagePicker;
            return this;
        }

        public Builder setMediaIntentReceiverClassName(String str) {
            this.zza = str;
            return this;
        }

        public Builder setMediaSessionEnabled(boolean z) {
            this.zze = z;
            return this;
        }

        public Builder setNotificationOptions(NotificationOptions notificationOptions) {
            this.zzd = notificationOptions;
            return this;
        }
    }

    public CastMediaOptions(String str, String str2, IBinder iBinder, NotificationOptions notificationOptions, boolean z, boolean z5) {
        zzd zzbVar;
        this.zzb = str;
        this.zzc = str2;
        if (iBinder == null) {
            zzbVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.framework.media.IImagePicker");
            zzbVar = iInterfaceQueryLocalInterface instanceof zzd ? (zzd) iInterfaceQueryLocalInterface : new zzb(iBinder);
        }
        this.zzd = zzbVar;
        this.zze = notificationOptions;
        this.zzf = z;
        this.zzg = z5;
    }

    public String getExpandedControllerActivityClassName() {
        return this.zzc;
    }

    public ImagePicker getImagePicker() {
        zzd zzdVar = this.zzd;
        if (zzdVar == null) {
            return null;
        }
        try {
            return (ImagePicker) d.g0(zzdVar.zzf());
        } catch (RemoteException e5) {
            zza.d(e5, "Unable to call %s on %s.", "getWrappedClientObject", "zzd");
            return null;
        }
    }

    public String getMediaIntentReceiverClassName() {
        return this.zzb;
    }

    public boolean getMediaSessionEnabled() {
        return this.zzg;
    }

    public NotificationOptions getNotificationOptions() {
        return this.zze;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.g0(parcel, 2, getMediaIntentReceiverClassName(), false);
        a.a.g0(parcel, 3, getExpandedControllerActivityClassName(), false);
        zzd zzdVar = this.zzd;
        a.a.b0(parcel, 4, zzdVar == null ? null : zzdVar.asBinder());
        a.a.f0(parcel, 5, getNotificationOptions(), i10, false);
        boolean z = this.zzf;
        a.a.l0(parcel, 6, 4);
        parcel.writeInt(z ? 1 : 0);
        boolean mediaSessionEnabled = getMediaSessionEnabled();
        a.a.l0(parcel, 7, 4);
        parcel.writeInt(mediaSessionEnabled ? 1 : 0);
        a.a.n0(iM0, parcel);
    }

    public final boolean zza() {
        return this.zzf;
    }
}
