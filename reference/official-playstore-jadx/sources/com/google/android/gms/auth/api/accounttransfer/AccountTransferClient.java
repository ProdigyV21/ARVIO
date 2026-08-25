package com.google.android.gms.auth.api.accounttransfer;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.api.h;
import com.google.android.gms.common.api.i;
import com.google.android.gms.common.api.m;
import com.google.android.gms.common.api.n;
import com.google.android.gms.common.internal.t;
import com.google.android.gms.internal.auth.o;
import com.google.android.gms.internal.auth.u;
import com.google.android.gms.internal.auth.v;
import com.google.android.gms.internal.auth.x;

/* JADX INFO: loaded from: classes4.dex */
public class AccountTransferClient extends n {
    public static final /* synthetic */ int zza = 0;
    private static final h zzb;
    private static final a zzc;
    private static final i zzd;

    static {
        h hVar = new h();
        zzb = hVar;
        zzb zzbVar = new zzb();
        zzc = zzbVar;
        zzd = new i("AccountTransfer.ACCOUNT_TRANSFER_API", zzbVar, hVar);
    }

    public AccountTransferClient(Activity activity, zzr zzrVar) {
        super(activity, activity, zzd, zzr.zza, new m(new a1.a(), Looper.getMainLooper()));
    }

    public com.google.android.gms.tasks.h getDeviceMetaData(String str) {
        t.i(str);
        return doRead(new zzg(this, 1608, new o(str)));
    }

    public com.google.android.gms.tasks.h notifyCompletion(String str, int i10) {
        t.i(str);
        return doWrite(new zzi(this, 1610, new com.google.android.gms.internal.auth.t(str, i10)));
    }

    public com.google.android.gms.tasks.h retrieveData(String str) {
        t.i(str);
        return doRead(new zze(this, 1607, new u(str)));
    }

    public com.google.android.gms.tasks.h sendData(String str, byte[] bArr) {
        t.i(str);
        t.i(bArr);
        return doWrite(new zzc(this, 1606, new v(str, bArr)));
    }

    public com.google.android.gms.tasks.h showUserChallenge(String str, PendingIntent pendingIntent) {
        t.i(str);
        t.i(pendingIntent);
        return doWrite(new zzh(this, 1609, new x(str, pendingIntent)));
    }

    public AccountTransferClient(Context context, zzr zzrVar) {
        super(context, null, zzd, zzr.zza, new m(new a1.a(), Looper.getMainLooper()));
    }
}
