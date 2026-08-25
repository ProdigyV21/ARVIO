package com.google.android.gms.auth.api.accounttransfer;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.api.h;
import com.google.android.gms.common.api.i;
import com.google.android.gms.internal.auth.m;

/* JADX INFO: loaded from: classes4.dex */
public final class AccountTransfer {
    public static final String ACTION_ACCOUNT_EXPORT_DATA_AVAILABLE = "com.google.android.gms.auth.ACCOUNT_EXPORT_DATA_AVAILABLE";
    public static final String ACTION_ACCOUNT_IMPORT_DATA_AVAILABLE = "com.google.android.gms.auth.ACCOUNT_IMPORT_DATA_AVAILABLE";
    public static final String ACTION_START_ACCOUNT_EXPORT = "com.google.android.gms.auth.START_ACCOUNT_EXPORT";
    public static final String KEY_EXTRA_ACCOUNT_TYPE = "key_extra_account_type";
    public static final i zza;

    @Deprecated
    public static final m zzb;

    @Deprecated
    public static final m zzc;
    private static final h zzd;
    private static final a zze;

    static {
        h hVar = new h();
        zzd = hVar;
        zza zzaVar = new zza();
        zze = zzaVar;
        zza = new i("AccountTransfer.ACCOUNT_TRANSFER_API", zzaVar, hVar);
        zzb = new m();
        zzc = new m();
    }

    private AccountTransfer() {
    }

    public static AccountTransferClient getAccountTransferClient(Activity activity) {
        return new AccountTransferClient(activity, (zzr) null);
    }

    public static AccountTransferClient getAccountTransferClient(Context context) {
        return new AccountTransferClient(context, (zzr) null);
    }
}
