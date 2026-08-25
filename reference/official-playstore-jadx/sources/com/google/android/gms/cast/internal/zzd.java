package com.google.android.gms.cast.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class zzd extends zzq {
    private final List zzb;

    public zzd(String str, String str2, String str3) {
        super(str, "MediaControlChannel", null);
        this.zzb = Collections.synchronizedList(new ArrayList());
    }

    public final void zza() {
        List list = this.zzb;
        synchronized (list) {
            try {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    ((zzav) it.next()).zze(2002);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final List zzb() {
        return this.zzb;
    }

    public final void zzc(zzav zzavVar) {
        this.zzb.add(zzavVar);
    }
}
