package com.google.android.gms.cast.internal;

import android.os.Handler;
import android.os.Looper;
import androidx.loader.content.j;
import c4.b;
import c4.c;
import com.google.android.gms.common.internal.t;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public final class zzav {
    public static final Object zzf = new Object();
    protected final Logger zza;
    zzat zzd;
    Runnable zze;
    private final long zzg;
    private final String zzh;
    private final b zzj = c.f7401a;
    long zzb = -1;
    long zzc = 0;
    private final Handler zzi = new j(Looper.getMainLooper(), 2);

    public zzav(long j10, String str) {
        this.zzg = j10;
        this.zzh = str;
        this.zza = new Logger("RequestTracker", str);
    }

    private final boolean zzg(int i10, Object obj) {
        synchronized (zzf) {
            try {
                if (!zzb()) {
                    return false;
                }
                Locale locale = Locale.ROOT;
                zzh(i10, null, "clearing request " + this.zzb);
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private final void zzh(int i10, Object obj, String str) {
        this.zza.d(str, new Object[0]);
        Object obj2 = zzf;
        synchronized (obj2) {
            try {
                if (this.zzd != null) {
                    long jCurrentTimeMillis = this.zzj.currentTimeMillis();
                    zzat zzatVar = this.zzd;
                    t.i(zzatVar);
                    zzatVar.zzb(this.zzh, this.zzb, i10, obj, this.zzc, jCurrentTimeMillis);
                }
                this.zzb = -1L;
                this.zzd = null;
                synchronized (obj2) {
                    Runnable runnable = this.zze;
                    if (runnable != null) {
                        this.zzi.removeCallbacks(runnable);
                        this.zze = null;
                    }
                }
            } catch (Throwable th) {
                throw th;
            } finally {
            }
        }
    }

    public final void zza(long j10, zzat zzatVar) {
        zzat zzatVar2;
        long j11;
        long j12;
        long jCurrentTimeMillis = this.zzj.currentTimeMillis();
        Object obj = zzf;
        synchronized (obj) {
            zzatVar2 = this.zzd;
            j11 = this.zzb;
            j12 = this.zzc;
            this.zzb = j10;
            this.zzd = zzatVar;
            this.zzc = jCurrentTimeMillis;
        }
        if (zzatVar2 != null) {
            zzatVar2.zza(this.zzh, j11, j12, jCurrentTimeMillis);
        }
        synchronized (obj) {
            try {
                Runnable runnable = this.zze;
                if (runnable != null) {
                    this.zzi.removeCallbacks(runnable);
                }
                Runnable runnable2 = new Runnable() { // from class: com.google.android.gms.cast.internal.zzau
                    @Override // java.lang.Runnable
                    public final /* synthetic */ void run() {
                        this.zza.zzf();
                    }
                };
                this.zze = runnable2;
                this.zzi.postDelayed(runnable2, this.zzg);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean zzb() {
        boolean z;
        synchronized (zzf) {
            z = this.zzb != -1;
        }
        return z;
    }

    public final boolean zzc(long j10) {
        boolean z;
        synchronized (zzf) {
            long j11 = this.zzb;
            z = false;
            if (j11 != -1 && j11 == j10) {
                z = true;
            }
        }
        return z;
    }

    public final boolean zzd(long j10, int i10, Object obj) {
        synchronized (zzf) {
            try {
                if (!zzc(j10)) {
                    return false;
                }
                Locale locale = Locale.ROOT;
                zzh(i10, obj, "request " + j10 + " completed");
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean zze(int i10) {
        return zzg(2002, null);
    }

    public final /* synthetic */ void zzf() {
        synchronized (zzf) {
            try {
                if (zzb()) {
                    zzg(15, null);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
