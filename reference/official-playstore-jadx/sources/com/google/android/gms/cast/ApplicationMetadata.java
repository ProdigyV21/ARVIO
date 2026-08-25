package com.google.android.gms.cast;

import a0.c;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.internal.CastUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import r3.b;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public class ApplicationMetadata extends a {
    public static final Parcelable.Creator<ApplicationMetadata> CREATOR = new zzd();
    String zza;
    String zzb;
    final List zzc;
    String zzd;
    Uri zze;
    String zzf;
    private String zzg;
    private Boolean zzh;
    private Boolean zzi;
    private final int zzj;

    private ApplicationMetadata() {
        this.zzc = new ArrayList();
        this.zzj = 1;
    }

    public boolean areNamespacesSupported(List<String> list) {
        List list2 = this.zzc;
        return list2 != null && list2.containsAll(list);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ApplicationMetadata)) {
            return false;
        }
        ApplicationMetadata applicationMetadata = (ApplicationMetadata) obj;
        return CastUtils.zza(this.zza, applicationMetadata.zza) && CastUtils.zza(this.zzb, applicationMetadata.zzb) && CastUtils.zza(this.zzc, applicationMetadata.zzc) && CastUtils.zza(this.zzd, applicationMetadata.zzd) && CastUtils.zza(this.zze, applicationMetadata.zze) && CastUtils.zza(this.zzf, applicationMetadata.zzf) && CastUtils.zza(this.zzg, applicationMetadata.zzg) && this.zzj == applicationMetadata.zzj;
    }

    public String getApplicationId() {
        return this.zza;
    }

    public String getIconUrl() {
        return this.zzf;
    }

    @Deprecated
    public List<b> getImages() {
        return null;
    }

    public String getName() {
        return this.zzb;
    }

    public String getSenderAppIdentifier() {
        return this.zzd;
    }

    public List<String> getSupportedNamespaces() {
        return Collections.unmodifiableList(this.zzc);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, Integer.valueOf(this.zzj)});
    }

    public boolean isNamespaceSupported(String str) {
        List list = this.zzc;
        return list != null && list.contains(str);
    }

    public void setIconUrl(String str) {
        this.zzf = str;
    }

    public String toString() {
        String str = this.zza;
        String str2 = this.zzb;
        List list = this.zzc;
        int size = list == null ? 0 : list.size();
        String str3 = this.zzd;
        String strValueOf = String.valueOf(this.zze);
        String str4 = this.zzf;
        String str5 = this.zzg;
        StringBuilder sb2 = new StringBuilder(String.valueOf(str3).length() + String.valueOf(str2).length() + String.valueOf(str).length() + 23 + 20 + String.valueOf(size).length() + 23 + 22 + strValueOf.length() + 11 + String.valueOf(str4).length() + 8 + String.valueOf(str5).length());
        y.a.i(sb2, "applicationId: ", str, ", name: ", str2);
        sb2.append(", namespaces.count: ");
        sb2.append(size);
        sb2.append(", senderAppIdentifier: ");
        sb2.append(str3);
        y.a.i(sb2, ", senderAppLaunchUrl: ", strValueOf, ", iconUrl: ", str4);
        return c.p(sb2, ", type: ", str5);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.g0(parcel, 2, getApplicationId(), false);
        a.a.g0(parcel, 3, getName(), false);
        a.a.k0(parcel, 4, getImages(), false);
        a.a.i0(parcel, getSupportedNamespaces(), 5);
        a.a.g0(parcel, 6, getSenderAppIdentifier(), false);
        a.a.f0(parcel, 7, this.zze, i10, false);
        a.a.g0(parcel, 8, getIconUrl(), false);
        a.a.g0(parcel, 9, this.zzg, false);
        a.a.Y(parcel, 10, this.zzh);
        a.a.Y(parcel, 11, this.zzi);
        int i11 = this.zzj;
        a.a.l0(parcel, 12, 4);
        parcel.writeInt(i11);
        a.a.n0(iM0, parcel);
    }

    public ApplicationMetadata(String str, String str2, List list, List list2, String str3, Uri uri, String str4, String str5, Boolean bool, Boolean bool2, int i10) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = list2;
        this.zzd = str3;
        this.zze = uri;
        this.zzf = str4;
        this.zzg = str5;
        this.zzh = bool;
        this.zzi = bool2;
        this.zzj = i10;
    }
}
