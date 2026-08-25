package com.google.android.gms.cast;

import android.net.Network;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.fragment.app.a2;
import androidx.media3.session.MediaUtils;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import r3.b;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public class CastDevice extends a implements ReflectedParcelable {
    public static final int CAPABILITY_AUDIO_IN = 8;
    public static final int CAPABILITY_AUDIO_OUT = 4;
    public static final int CAPABILITY_MULTIZONE_GROUP = 32;
    public static final int CAPABILITY_VIDEO_IN = 2;
    public static final int CAPABILITY_VIDEO_OUT = 1;
    public static final Parcelable.Creator<CastDevice> CREATOR = new zzr();
    final String zza;
    final Boolean zzb;
    final Network zzc;
    private final String zzd;
    private InetAddress zze;
    private final String zzf;
    private final String zzg;
    private final String zzh;
    private final int zzi;
    private final List zzj;
    private final com.google.android.gms.cast.internal.zzp zzk;
    private final int zzl;
    private final String zzm;
    private final String zzn;
    private final int zzo;
    private final String zzp;
    private final byte[] zzq;
    private final String zzr;
    private final boolean zzs;
    private final com.google.android.gms.cast.internal.zzaa zzt;
    private final Integer zzu;

    public CastDevice(String str, String str2, String str3, String str4, String str5, int i10, List list, int i11, int i12, String str6, String str7, int i13, String str8, byte[] bArr, String str9, boolean z, com.google.android.gms.cast.internal.zzaa zzaaVar, Integer num, Boolean bool, Network network) {
        this.zzd = zzf(str);
        String strZzf = zzf(str2);
        this.zza = strZzf;
        if (!TextUtils.isEmpty(strZzf)) {
            try {
                this.zze = InetAddress.getByName(strZzf);
            } catch (UnknownHostException e5) {
                String str10 = this.zza;
                String message = e5.getMessage();
                Log.i("CastDevice", a2.n(new StringBuilder(String.valueOf(str10).length() + 48 + String.valueOf(message).length()), "Unable to convert host address (", str10, ") to ipaddress: ", message));
            }
        }
        this.zzf = zzf(str3);
        this.zzg = zzf(str4);
        this.zzh = zzf(str5);
        this.zzi = i10;
        this.zzj = list == null ? new ArrayList() : list;
        this.zzl = i12;
        this.zzm = zzf(str6);
        this.zzn = str7;
        this.zzo = i13;
        this.zzp = str8;
        this.zzq = bArr;
        this.zzr = str9;
        this.zzs = z;
        this.zzt = zzaaVar;
        this.zzu = num;
        this.zzb = bool;
        this.zzc = network;
        this.zzk = new com.google.android.gms.cast.internal.zzp(i11, zzaaVar);
    }

    public static CastDevice getFromBundle(Bundle bundle) {
        ClassLoader classLoader;
        if (bundle == null || (classLoader = CastDevice.class.getClassLoader()) == null) {
            return null;
        }
        bundle.setClassLoader(classLoader);
        return (CastDevice) bundle.getParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE");
    }

    private static String zzf(String str) {
        return str == null ? "" : str;
    }

    public boolean equals(Object obj) {
        int i10;
        byte[] bArr;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CastDevice)) {
            return false;
        }
        CastDevice castDevice = (CastDevice) obj;
        String str = this.zzd;
        if (str == null) {
            return castDevice.zzd == null;
        }
        if (CastUtils.zza(str, castDevice.zzd) && CastUtils.zza(this.zze, castDevice.zze) && CastUtils.zza(this.zzg, castDevice.zzg) && CastUtils.zza(this.zzf, castDevice.zzf)) {
            String str2 = this.zzh;
            if (CastUtils.zza(str2, castDevice.zzh) && (i10 = this.zzi) == castDevice.zzi && CastUtils.zza(this.zzj, castDevice.zzj) && this.zzk.zza() == castDevice.zzk.zza() && this.zzl == castDevice.zzl && CastUtils.zza(this.zzm, castDevice.zzm) && CastUtils.zza(Integer.valueOf(this.zzo), Integer.valueOf(castDevice.zzo)) && CastUtils.zza(this.zzp, castDevice.zzp) && CastUtils.zza(this.zzn, castDevice.zzn) && CastUtils.zza(str2, castDevice.getDeviceVersion()) && i10 == castDevice.getServicePort() && ((((bArr = this.zzq) == null && castDevice.zzq == null) || Arrays.equals(bArr, castDevice.zzq)) && CastUtils.zza(this.zzr, castDevice.zzr) && this.zzs == castDevice.zzs && CastUtils.zza(zzb(), castDevice.zzb()))) {
                if (CastUtils.zza(Boolean.valueOf(zze()), Boolean.valueOf(castDevice.zze() && CastUtils.zza(this.zzc, castDevice.zzc)))) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getDeviceId() {
        String str = this.zzd;
        return str.startsWith("__cast_nearby__") ? str.substring(16) : str;
    }

    public String getDeviceVersion() {
        return this.zzh;
    }

    public String getFriendlyName() {
        return this.zzf;
    }

    public b getIcon(int i10, int i11) {
        List<b> list = this.zzj;
        b bVar = null;
        if (list.isEmpty()) {
            return null;
        }
        if (i10 <= 0 || i11 <= 0) {
            return (b) list.get(0);
        }
        b bVar2 = null;
        for (b bVar3 : list) {
            int i12 = bVar3.f21389m;
            int i13 = bVar3.f21390n;
            if (i12 < i10 || i13 < i11) {
                if (i12 < i10 && i13 < i11 && (bVar2 == null || (bVar2.f21389m < i12 && bVar2.f21390n < i13))) {
                    bVar2 = bVar3;
                }
            } else if (bVar == null || (bVar.f21389m > i12 && bVar.f21390n > i13)) {
                bVar = bVar3;
            }
        }
        return bVar != null ? bVar : bVar2 != null ? bVar2 : (b) list.get(0);
    }

    public List<b> getIcons() {
        return Collections.unmodifiableList(this.zzj);
    }

    public InetAddress getInetAddress() {
        return this.zze;
    }

    @Deprecated
    public Inet4Address getIpAddress() {
        if (hasIPv4Address()) {
            return (Inet4Address) this.zze;
        }
        return null;
    }

    public String getModelName() {
        return this.zzg;
    }

    public int getServicePort() {
        return this.zzi;
    }

    public boolean hasCapabilities(int[] iArr) {
        if (iArr == null) {
            return false;
        }
        for (int i10 : iArr) {
            if (!this.zzk.zzb(i10)) {
                return false;
            }
        }
        return true;
    }

    public boolean hasCapability(int i10) {
        return this.zzk.zzb(i10);
    }

    public boolean hasIPv4Address() {
        return getInetAddress() instanceof Inet4Address;
    }

    public boolean hasIPv6Address() {
        return getInetAddress() instanceof Inet6Address;
    }

    public boolean hasIcons() {
        return !this.zzj.isEmpty();
    }

    public int hashCode() {
        String str = this.zzd;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    public boolean isOnLocalNetwork() {
        return (this.zzd.startsWith("__cast_nearby__") || this.zzs) ? false : true;
    }

    public boolean isSameDevice(CastDevice castDevice) {
        if (castDevice == null) {
            return false;
        }
        if (!TextUtils.isEmpty(getDeviceId()) && !getDeviceId().startsWith("__cast_ble__") && !TextUtils.isEmpty(castDevice.getDeviceId()) && !castDevice.getDeviceId().startsWith("__cast_ble__")) {
            return CastUtils.zza(getDeviceId(), castDevice.getDeviceId());
        }
        String str = this.zzp;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String str2 = castDevice.zzp;
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        return CastUtils.zza(str, str2);
    }

    public void putInBundle(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        bundle.putParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE", this);
    }

    public String toString() {
        com.google.android.gms.cast.internal.zzp zzpVar = this.zzk;
        String strConcat = zzpVar.zzb(64) ? "[dynamic group]" : zzpVar.zzc() ? "[static group]" : zzpVar.zzd() ? "[speaker pair]" : "";
        if (zzpVar.zzb(MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES)) {
            strConcat = strConcat.concat("[cast connect]");
        }
        String str = this.zzf;
        Locale locale = Locale.ROOT;
        int i10 = CastUtils.zza;
        if (!TextUtils.isEmpty(str)) {
            int length = str.length();
            str = length <= 2 ? length == 2 ? "xx" : "x" : String.format(locale, "%c%d%c", Character.valueOf(str.charAt(0)), Integer.valueOf(length - 2), Character.valueOf(str.charAt(length - 1)));
        }
        StringBuilder sbR = a2.r("\"", str, "\" (", this.zzd, ") ");
        sbR.append(strConcat);
        return sbR.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        String str = this.zzd;
        int iM0 = a.a.m0(20293, parcel);
        a.a.g0(parcel, 2, str, false);
        a.a.g0(parcel, 3, this.zza, false);
        a.a.g0(parcel, 4, getFriendlyName(), false);
        a.a.g0(parcel, 5, getModelName(), false);
        a.a.g0(parcel, 6, getDeviceVersion(), false);
        int servicePort = getServicePort();
        a.a.l0(parcel, 7, 4);
        parcel.writeInt(servicePort);
        a.a.k0(parcel, 8, getIcons(), false);
        int iZza = this.zzk.zza();
        a.a.l0(parcel, 9, 4);
        parcel.writeInt(iZza);
        int i11 = this.zzl;
        a.a.l0(parcel, 10, 4);
        parcel.writeInt(i11);
        a.a.g0(parcel, 11, this.zzm, false);
        a.a.g0(parcel, 12, this.zzn, false);
        int i12 = this.zzo;
        a.a.l0(parcel, 13, 4);
        parcel.writeInt(i12);
        a.a.g0(parcel, 14, this.zzp, false);
        a.a.a0(parcel, 15, this.zzq, false);
        a.a.g0(parcel, 16, this.zzr, false);
        boolean z = this.zzs;
        a.a.l0(parcel, 17, 4);
        parcel.writeInt(z ? 1 : 0);
        a.a.f0(parcel, 18, zzb(), i10, false);
        a.a.d0(parcel, 19, this.zzu);
        a.a.Y(parcel, 20, Boolean.valueOf(zze()));
        a.a.f0(parcel, 21, this.zzc, i10, false);
        a.a.n0(iM0, parcel);
    }

    public final String zza() {
        return this.zzn;
    }

    public final com.google.android.gms.cast.internal.zzaa zzb() {
        com.google.android.gms.cast.internal.zzaa zzaaVar = this.zzt;
        return (zzaaVar == null && this.zzk.zzd()) ? com.google.android.gms.cast.internal.zzz.zza(1) : zzaaVar;
    }

    public final int zzc() {
        return this.zzk.zza();
    }

    public final int zzd() {
        com.google.android.gms.cast.internal.zzp zzpVar = this.zzk;
        if (zzpVar.zzb(64)) {
            return 4;
        }
        if (zzpVar.zzc()) {
            return 3;
        }
        if (zzpVar.zzd()) {
            return 5;
        }
        return hasCapability(1) ? 2 : 1;
    }

    public final boolean zze() {
        Boolean bool = this.zzb;
        if (bool != null) {
            return bool.booleanValue();
        }
        int i10 = this.zzl;
        return i10 != -1 && (i10 & 2) > 0;
    }
}
