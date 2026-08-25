package com.google.android.gms.cast.framework;

import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.zzbm;
import com.google.android.gms.tasks.c;
import com.google.android.gms.tasks.h;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzu extends zzad {
    final /* synthetic */ CastSession zza;

    public /* synthetic */ zzu(CastSession castSession, byte[] bArr) {
        Objects.requireNonNull(castSession);
        this.zza = castSession;
    }

    @Override // com.google.android.gms.cast.framework.zzae
    public final void zzb(String str, String str2) {
        CastSession castSession = this.zza;
        if (castSession.zzj() == null || !castSession.zzj().zza()) {
            return;
        }
        ((zzbm) castSession.zzj()).zzs(str, str2, null).a(new c() { // from class: com.google.android.gms.cast.framework.zzt
            @Override // com.google.android.gms.tasks.c
            public final /* synthetic */ void onComplete(h hVar) {
                this.zza.zza.zze("joinApplication", hVar);
            }
        });
    }

    @Override // com.google.android.gms.cast.framework.zzae
    public final void zzc(String str, LaunchOptions launchOptions) {
        CastSession castSession = this.zza;
        if (castSession.zzj() == null || !castSession.zzj().zza()) {
            return;
        }
        castSession.zzj().zzg(str, launchOptions).a(new c() { // from class: com.google.android.gms.cast.framework.zzs
            @Override // com.google.android.gms.tasks.c
            public final /* synthetic */ void onComplete(h hVar) {
                this.zza.zza.zze("launchApplication", hVar);
            }
        });
    }

    @Override // com.google.android.gms.cast.framework.zzae
    public final void zzd(String str) {
        CastSession castSession = this.zza;
        if (castSession.zzj() == null || !castSession.zzj().zza()) {
            return;
        }
        castSession.zzj().zzh(str);
    }

    @Override // com.google.android.gms.cast.framework.zzae
    public final void zze(int i10) {
        this.zza.zzf(i10);
    }
}
