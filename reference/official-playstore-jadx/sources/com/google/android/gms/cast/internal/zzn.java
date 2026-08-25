package com.google.android.gms.cast.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.api.d;
import com.google.android.gms.common.api.h;
import com.google.android.gms.common.api.i;
import com.google.android.gms.common.api.internal.t;
import com.google.android.gms.common.api.internal.x;
import com.google.android.gms.common.api.internal.y;
import com.google.android.gms.common.api.m;
import com.google.android.gms.common.api.n;
import com.google.android.gms.internal.cast.u0;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class zzn extends n {
    private static final h zza;
    private static final a zzb;
    private static final i zzc;

    static {
        h hVar = new h();
        zza = hVar;
        zze zzeVar = new zze();
        zzb = zzeVar;
        zzc = new i("CastApi.API", zzeVar, hVar);
    }

    public zzn(Context context) {
        super(context, null, zzc, d.f12682a, m.f12824c);
    }

    public final com.google.android.gms.tasks.h zza(final String[] strArr, final String str, List list) {
        x xVarBuilder = y.builder();
        xVarBuilder.f12810d = 8423;
        final List list2 = null;
        xVarBuilder.f12807a = new t(strArr, str, list2) { // from class: com.google.android.gms.cast.internal.zzm
            private final /* synthetic */ String[] zzb;
            private final /* synthetic */ String zzc;

            @Override // com.google.android.gms.common.api.internal.t
            public final /* synthetic */ void accept(Object obj, Object obj2) throws RemoteException {
                zzo zzoVar = (zzo) obj;
                zzf zzfVar = new zzf(this.zza, (com.google.android.gms.tasks.i) obj2);
                zzak zzakVar = (zzak) zzoVar.getService();
                zzoVar.getContext();
                zzakVar.zze(zzfVar, this.zzb, this.zzc, null, u0.i());
            }
        };
        return doWrite(xVarBuilder.a());
    }

    public final com.google.android.gms.tasks.h zzb(final String[] strArr) {
        x xVarBuilder = y.builder();
        xVarBuilder.f12807a = new t() { // from class: com.google.android.gms.cast.internal.zzj
            @Override // com.google.android.gms.common.api.internal.t
            public final /* synthetic */ void accept(Object obj, Object obj2) throws RemoteException {
                zzo zzoVar = (zzo) obj;
                zzg zzgVar = new zzg(this.zza, (com.google.android.gms.tasks.i) obj2);
                zzak zzakVar = (zzak) zzoVar.getService();
                zzoVar.getContext();
                zzakVar.zzf(zzgVar, strArr, u0.i());
            }
        };
        xVarBuilder.f12809c = new o3.d[]{com.google.android.gms.cast.zzaq.zzd};
        xVarBuilder.f12808b = false;
        xVarBuilder.f12810d = 8425;
        return doRead(xVarBuilder.a());
    }

    public final com.google.android.gms.tasks.h zzc(final String[] strArr) {
        x xVarBuilder = y.builder();
        xVarBuilder.f12807a = new t() { // from class: com.google.android.gms.cast.internal.zzk
            @Override // com.google.android.gms.common.api.internal.t
            public final /* synthetic */ void accept(Object obj, Object obj2) throws RemoteException {
                zzo zzoVar = (zzo) obj;
                zzh zzhVar = new zzh(this.zza, (com.google.android.gms.tasks.i) obj2);
                zzak zzakVar = (zzak) zzoVar.getService();
                zzoVar.getContext();
                zzakVar.zzg(zzhVar, strArr, u0.i());
            }
        };
        xVarBuilder.f12809c = new o3.d[]{com.google.android.gms.cast.zzaq.zzg};
        xVarBuilder.f12808b = false;
        xVarBuilder.f12810d = 8426;
        return doRead(xVarBuilder.a());
    }

    public final com.google.android.gms.tasks.h zzd(final String[] strArr) {
        x xVarBuilder = y.builder();
        xVarBuilder.f12807a = new t() { // from class: com.google.android.gms.cast.internal.zzl
            @Override // com.google.android.gms.common.api.internal.t
            public final /* synthetic */ void accept(Object obj, Object obj2) throws RemoteException {
                zzo zzoVar = (zzo) obj;
                zzi zziVar = new zzi(this.zza, (com.google.android.gms.tasks.i) obj2);
                zzak zzakVar = (zzak) zzoVar.getService();
                zzoVar.getContext();
                zzakVar.zzh(zziVar, strArr, u0.i());
            }
        };
        xVarBuilder.f12809c = new o3.d[]{com.google.android.gms.cast.zzaq.zzh};
        xVarBuilder.f12808b = false;
        xVarBuilder.f12810d = 8427;
        return doRead(xVarBuilder.a());
    }
}
