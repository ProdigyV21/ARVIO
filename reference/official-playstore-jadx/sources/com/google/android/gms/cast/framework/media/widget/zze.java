package com.google.android.gms.cast.framework.media.widget;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class zze {
    public int zza;
    public int zzb;
    public int zzc;
    public int zzd;
    public int zze;
    public boolean zzf;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zze)) {
            return false;
        }
        zze zzeVar = (zze) obj;
        return this.zza == zzeVar.zza && this.zzb == zzeVar.zzb && this.zzc == zzeVar.zzc && this.zzd == zzeVar.zzd && this.zze == zzeVar.zze && this.zzf == zzeVar.zzf;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zza), Integer.valueOf(this.zzb), Integer.valueOf(this.zzc), Integer.valueOf(this.zzd), Integer.valueOf(this.zze), Boolean.valueOf(this.zzf)});
    }
}
