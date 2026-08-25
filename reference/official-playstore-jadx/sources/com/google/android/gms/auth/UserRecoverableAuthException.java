package com.google.android.gms.auth;

import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;
import com.google.android.gms.common.internal.t;

/* JADX INFO: loaded from: classes4.dex */
public class UserRecoverableAuthException extends GoogleAuthException {
    private final Intent zza;
    private final PendingIntent zzb;
    private final zzn zzc;

    public UserRecoverableAuthException(String str, Intent intent) {
        this(str, intent, null, zzn.LEGACY);
    }

    public static UserRecoverableAuthException zza(String str, Intent intent, PendingIntent pendingIntent) {
        t.i(intent);
        t.i(pendingIntent);
        return new UserRecoverableAuthException(str, intent, pendingIntent, zzn.AUTH_INSTANTIATION);
    }

    public Intent getIntent() {
        Intent intent = this.zza;
        if (intent != null) {
            return new Intent(intent);
        }
        int iOrdinal = this.zzc.ordinal();
        if (iOrdinal == 0) {
            Log.w("Auth", "Make sure that an intent was provided to class instantiation.");
            return null;
        }
        if (iOrdinal == 1) {
            Log.e("Auth", "This shouldn't happen. Gms API throwing this exception should support the recovery Intent.");
            return null;
        }
        if (iOrdinal != 2) {
            return null;
        }
        Log.e("Auth", "this instantiation of UserRecoverableAuthException doesn't support an Intent.");
        return null;
    }

    private UserRecoverableAuthException(String str, Intent intent, PendingIntent pendingIntent, zzn zznVar) {
        super(str);
        this.zzb = pendingIntent;
        this.zza = intent;
        t.i(zznVar);
        this.zzc = zznVar;
    }
}
