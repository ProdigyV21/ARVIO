package com.google.android.gms.cast;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
final class zzbu {
    private final Map zza = new HashMap();
    private final Map zzb = new HashMap();
    private final Map zzc = new HashMap();

    public final zzbu zza(String str, String str2, int i10) {
        this.zza.put(str, str2);
        this.zzb.put(str2, str);
        this.zzc.put(str, Integer.valueOf(i10));
        return this;
    }

    public final String zzb(String str) {
        return (String) this.zza.get(str);
    }

    public final String zzc(String str) {
        return (String) this.zzb.get(str);
    }

    public final int zzd(String str) {
        Integer num = (Integer) this.zzc.get(str);
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }
}
