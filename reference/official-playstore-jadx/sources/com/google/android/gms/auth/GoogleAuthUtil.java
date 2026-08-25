package com.google.android.gms.auth;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.internal.t;
import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class GoogleAuthUtil extends zzl {
    public static final int CHANGE_TYPE_ACCOUNT_ADDED = 1;
    public static final int CHANGE_TYPE_ACCOUNT_REMOVED = 2;
    public static final int CHANGE_TYPE_ACCOUNT_RENAMED_FROM = 3;
    public static final int CHANGE_TYPE_ACCOUNT_RENAMED_TO = 4;
    public static final String GOOGLE_ACCOUNT_TYPE = "com.google";
    public static final String KEY_SUPPRESS_PROGRESS_SCREEN = "suppressProgressScreen";
    public static final String WORK_ACCOUNT_TYPE = "com.google.work";

    private GoogleAuthUtil() {
    }

    public static void clearToken(Context context, String str) throws GoogleAuthException, IOException {
        zzl.clearToken(context, str);
    }

    public static List<AccountChangeEvent> getAccountChangeEvents(Context context, int i10, String str) throws GoogleAuthException, IOException {
        return zzl.getAccountChangeEvents(context, i10, str);
    }

    public static String getAccountId(Context context, String str) throws GoogleAuthException, IOException {
        return zzl.getAccountId(context, str);
    }

    public static String getToken(Context context, Account account, String str) throws IOException, GoogleAuthException {
        return zzl.getToken(context, account, str);
    }

    public static String getTokenWithNotification(Context context, Account account, String str, Bundle bundle) throws IOException, GoogleAuthException {
        if (bundle == null) {
            bundle = new Bundle();
        }
        Bundle bundle2 = bundle;
        bundle2.putBoolean("handle_notification", true);
        return zzg(context, account, str, bundle2, 0L).zza();
    }

    @Deprecated
    public static void invalidateToken(Context context, String str) {
        zzl.invalidateToken(context, str);
    }

    public static Bundle removeAccount(Context context, Account account) throws GoogleAuthException, IOException {
        return zzl.removeAccount(context, account);
    }

    public static Boolean requestGoogleAccountsAccess(Context context) throws GoogleAuthException, IOException {
        return zzl.requestGoogleAccountsAccess(context);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static com.google.android.gms.auth.TokenData zzg(android.content.Context r9, android.accounts.Account r10, java.lang.String r11, android.os.Bundle r12, long r13) throws com.google.android.gms.auth.GoogleAuthException, java.io.IOException {
        /*
            java.lang.String r13 = "User intervention required. Notification has been pushed."
            java.lang.String r14 = "Error when getting token"
            java.lang.String r1 = "GoogleAuthUtil"
            r6 = 0
            r8 = 0
            r2 = r9
            r3 = r10
            r4 = r11
            r5 = r12
            com.google.android.gms.auth.TokenData r9 = com.google.android.gms.auth.zzl.zza(r2, r3, r4, r5, r6, r8)     // Catch: com.google.android.gms.auth.UserRecoverableAuthException -> L15 com.google.android.gms.auth.GooglePlayServicesAvailabilityException -> L18
            o3.g.a(r2)     // Catch: com.google.android.gms.auth.UserRecoverableAuthException -> L15 com.google.android.gms.auth.GooglePlayServicesAvailabilityException -> L18
            return r9
        L15:
            r0 = move-exception
            r9 = r0
            goto L1b
        L18:
            r0 = move-exception
            r9 = r0
            goto L27
        L1b:
            o3.g.a(r2)
            android.util.Log.w(r1, r14, r9)
            com.google.android.gms.auth.UserRecoverableNotifiedException r10 = new com.google.android.gms.auth.UserRecoverableNotifiedException
            r10.<init>(r13, r9)
            throw r10
        L27:
            int r10 = r9.getConnectionStatusCode()
            int r11 = o3.g.f20748e
            o3.e r11 = o3.e.f20741d
            r12 = 18
            r0 = 1
            r3 = 0
            if (r10 != r12) goto L37
            r12 = r0
            goto L41
        L37:
            if (r10 != r0) goto L40
            java.lang.String r12 = "com.google.android.gms"
            boolean r12 = o3.g.c(r2, r12)
            goto L41
        L40:
            r12 = r3
        L41:
            if (r12 != 0) goto L65
            r12 = 9
            if (r10 != r12) goto L4e
            java.lang.String r12 = "com.android.vending"
            boolean r12 = o3.g.c(r2, r12)
            goto L4f
        L4e:
            r12 = r3
        L4f:
            if (r12 != 0) goto L65
            java.lang.String r12 = "n"
            android.content.Intent r12 = r11.a(r2, r10, r12)
            if (r12 != 0) goto L5b
            r12 = 0
            goto L61
        L5b:
            r0 = 201326592(0xc000000, float:9.8607613E-32)
            android.app.PendingIntent r12 = android.app.PendingIntent.getActivity(r2, r3, r12, r0)
        L61:
            r11.f(r2, r10, r12)
            goto L70
        L65:
            o3.l r10 = new o3.l
            r10.<init>(r11, r2)
            r11 = 120000(0x1d4c0, double:5.9288E-319)
            r10.sendEmptyMessageDelayed(r0, r11)
        L70:
            android.util.Log.w(r1, r14, r9)
            com.google.android.gms.auth.UserRecoverableNotifiedException r10 = new com.google.android.gms.auth.UserRecoverableNotifiedException
            r10.<init>(r13, r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.GoogleAuthUtil.zzg(android.content.Context, android.accounts.Account, java.lang.String, android.os.Bundle, long):com.google.android.gms.auth.TokenData");
    }

    public static String getToken(Context context, Account account, String str, Bundle bundle) throws IOException, GoogleAuthException {
        return zzl.getToken(context, account, str, bundle);
    }

    @Deprecated
    public static String getToken(Context context, String str, String str2) throws IOException, GoogleAuthException {
        return zzl.getToken(context, str, str2);
    }

    @Deprecated
    public static String getToken(Context context, String str, String str2, Bundle bundle) throws IOException, GoogleAuthException {
        return zzl.getToken(context, str, str2, bundle);
    }

    public static String getTokenWithNotification(Context context, Account account, String str, Bundle bundle, Intent intent) throws IOException, GoogleAuthException {
        zzl.zzf(intent);
        if (bundle == null) {
            bundle = new Bundle();
        }
        Bundle bundle2 = bundle;
        bundle2.putParcelable("callback_intent", intent);
        bundle2.putBoolean("handle_notification", true);
        return zzg(context, account, str, bundle2, 0L).zza();
    }

    public static String getTokenWithNotification(Context context, Account account, String str, Bundle bundle, String str2, Bundle bundle2) throws IOException, GoogleAuthException {
        t.g(str2, "Authority cannot be empty or null.");
        if (bundle == null) {
            bundle = new Bundle();
        }
        Bundle bundle3 = bundle;
        if (bundle2 == null) {
            bundle2 = new Bundle();
        }
        ContentResolver.validateSyncExtrasBundle(bundle2);
        bundle3.putString("authority", str2);
        bundle3.putBundle("sync_extras", bundle2);
        bundle3.putBoolean("handle_notification", true);
        return zzg(context, account, str, bundle3, 0L).zza();
    }

    @Deprecated
    public static String getTokenWithNotification(Context context, String str, String str2, Bundle bundle) throws IOException, GoogleAuthException {
        return getTokenWithNotification(context, new Account(str, "com.google"), str2, bundle);
    }

    @Deprecated
    public static String getTokenWithNotification(Context context, String str, String str2, Bundle bundle, Intent intent) throws IOException, GoogleAuthException {
        return getTokenWithNotification(context, new Account(str, "com.google"), str2, bundle, intent);
    }

    @Deprecated
    public static String getTokenWithNotification(Context context, String str, String str2, Bundle bundle, String str3, Bundle bundle2) throws IOException, GoogleAuthException {
        return getTokenWithNotification(context, new Account(str, "com.google"), str2, bundle, str3, bundle2);
    }
}
