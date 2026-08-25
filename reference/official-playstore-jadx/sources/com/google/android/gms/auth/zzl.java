package com.google.android.gms.auth;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import androidx.appcompat.app.i1;
import androidx.fragment.app.a2;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.AacUtil;
import androidx.media3.extractor.flac.FlacConstants;
import androidx.media3.extractor.ts.TsExtractor;
import com.arflix.tv.data.repository.g;
import com.google.android.gms.common.GooglePlayServicesIncorrectManifestValueException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.internal.x;
import com.google.android.gms.common.api.internal.y;
import com.google.android.gms.common.internal.l;
import com.google.android.gms.common.internal.t;
import com.google.android.gms.common.internal.u0;
import com.google.android.gms.common.internal.x0;
import com.google.android.gms.internal.auth.b4;
import com.google.android.gms.internal.auth.c;
import com.google.android.gms.internal.auth.c1;
import com.google.android.gms.internal.auth.g2;
import com.google.android.gms.internal.auth.m0;
import com.google.android.gms.internal.auth.p2;
import com.google.android.gms.internal.auth.s1;
import com.google.android.gms.internal.auth.v1;
import com.google.android.gms.internal.auth.y3;
import com.google.android.gms.internal.auth.z3;
import com.google.android.gms.tasks.h;
import h.f0;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeoutException;
import o3.d;
import o3.e;
import v3.a;

/* JADX INFO: loaded from: classes4.dex */
public class zzl {
    public static final int CHANGE_TYPE_ACCOUNT_ADDED = 1;
    public static final int CHANGE_TYPE_ACCOUNT_REMOVED = 2;
    public static final int CHANGE_TYPE_ACCOUNT_RENAMED_FROM = 3;
    public static final int CHANGE_TYPE_ACCOUNT_RENAMED_TO = 4;
    public static final String GOOGLE_ACCOUNT_TYPE = "com.google";
    public static final String KEY_SUPPRESS_PROGRESS_SCREEN = "suppressProgressScreen";
    public static final String WORK_ACCOUNT_TYPE = "com.google.work";
    public static final String[] zza = {"com.google", "com.google.work", "cn.google"};
    public static final String zzb = "androidPackageName";
    private static final ComponentName zzc = new ComponentName("com.google.android.gms", "com.google.android.gms.auth.GetToken");
    private static final a zzd = zzd.zza("GoogleAuthUtil");

    public static void clearToken(Context context, String str) throws GoogleAuthException, IOException {
        zze(context, str, 0L);
    }

    public static List<AccountChangeEvent> getAccountChangeEvents(Context context, int i10, String str) throws GoogleAuthException, IOException {
        t.g(str, "accountName must be provided");
        t.h("Calling this from your main thread can lead to deadlock");
        zzk(context, 8400000);
        AccountChangeEventsRequest accountChangeEventsRequest = new AccountChangeEventsRequest();
        accountChangeEventsRequest.setAccountName(str);
        accountChangeEventsRequest.setEventIndex(i10);
        c1.c(context);
        if (b4.f13000l.zza().zzb() && zzp(context)) {
            c cVarB = s1.b(context);
            x xVarBuilder = y.builder();
            xVarBuilder.f12809c = new d[]{zze.zzk};
            xVarBuilder.f12807a = new p2(cVarB, accountChangeEventsRequest, 7);
            xVarBuilder.f12810d = 1515;
            try {
                AccountChangeEventsResponse accountChangeEventsResponse = (AccountChangeEventsResponse) zzi(cVarB.doWrite(xVarBuilder.a()), "account change events retrieval");
                zzj(accountChangeEventsResponse);
                return accountChangeEventsResponse.getEvents();
            } catch (ApiException e5) {
                zzl(e5, "account change events retrieval");
            }
        }
        return (List) zzh(context, zzc, new zzi(accountChangeEventsRequest), 0L, null);
    }

    public static String getAccountId(Context context, String str) throws GoogleAuthException, IOException {
        t.g(str, "accountName must be provided");
        t.h("Calling this from your main thread can lead to deadlock");
        zzk(context, 8400000);
        return getToken(context, str, "^^_account_id_^^", new Bundle());
    }

    public static String getToken(Context context, Account account, String str) throws IOException, GoogleAuthException {
        return getToken(context, account, str, new Bundle());
    }

    @Deprecated
    public static void invalidateToken(Context context, String str) {
        AccountManager.get(context).invalidateAuthToken("com.google", str);
    }

    public static Bundle removeAccount(Context context, final Account account) throws GoogleAuthException, IOException {
        t.i(context);
        zzo(account);
        zzk(context, 8400000);
        c1.c(context);
        if (b4.f13000l.zza().zzc() && zzp(context)) {
            c cVarB = s1.b(context);
            t.j(account, "account cannot be null.");
            x xVarBuilder = y.builder();
            xVarBuilder.f12809c = new d[]{zze.zzk};
            xVarBuilder.f12807a = new p2(cVarB, account, 5);
            xVarBuilder.f12810d = 1517;
            try {
                Bundle bundle = (Bundle) zzi(cVarB.doWrite(xVarBuilder.a()), "account removal");
                zzj(bundle);
                return bundle;
            } catch (ApiException e5) {
                zzl(e5, "account removal");
            }
        }
        return (Bundle) zzh(context, zzc, new zzk() { // from class: com.google.android.gms.auth.zzf
            @Override // com.google.android.gms.auth.zzk
            public final Object zza(IBinder iBinder) throws IOException {
                Bundle bundleQ = v1.a(iBinder).Q(account);
                if (bundleQ != null) {
                    return bundleQ;
                }
                throw new IOException("Service call returned null.");
            }
        }, 0L, null);
    }

    public static Boolean requestGoogleAccountsAccess(Context context) throws GoogleAuthException, IOException {
        t.i(context);
        zzk(context, 11400000);
        String str = context.getApplicationInfo().packageName;
        c1.c(context);
        if (b4.f13000l.zza().zzc() && zzp(context)) {
            c cVarB = s1.b(context);
            t.j(str, "Client package name cannot be null!");
            x xVarBuilder = y.builder();
            xVarBuilder.f12809c = new d[]{zze.zzk};
            xVarBuilder.f12807a = new p2(cVarB, str, 4);
            xVarBuilder.f12810d = 1514;
            try {
                Bundle bundle = (Bundle) zzi(cVarB.doWrite(xVarBuilder.a()), "google accounts access request");
                String string = bundle.getString("Error");
                Intent intent = (Intent) bundle.getParcelable("userRecoveryIntent");
                PendingIntent pendingIntent = (PendingIntent) bundle.getParcelable("userRecoveryPendingIntent");
                if (f0.b(3, g.b(string))) {
                    return Boolean.TRUE;
                }
                zzn(context, "requestGoogleAccountsAccess", string, intent, pendingIntent);
                throw new GoogleAuthException("Invalid state. Shouldn't happen");
            } catch (ApiException e5) {
                zzl(e5, "google accounts access request");
            }
        }
        return (Boolean) zzh(context, zzc, new zzj(str, context), 0L, null);
    }

    public static TokenData zza(final Context context, final Account account, final String str, Bundle bundle, long j10, Executor executor) throws GoogleAuthException, IOException {
        t.h("Calling this from your main thread can lead to deadlock");
        t.g(str, "Scope cannot be empty or null.");
        zzo(account);
        zzk(context, 8400000);
        final Bundle bundle2 = bundle == null ? new Bundle() : new Bundle(bundle);
        zzm(context, bundle2);
        c1.c(context);
        if (b4.f13000l.zza().zzc() && zzp(context)) {
            c cVarB = s1.b(context);
            t.j(account, "Account name cannot be null!");
            t.g(str, "Scope cannot be null!");
            x xVarBuilder = y.builder();
            xVarBuilder.f12809c = new d[]{zze.zzl};
            xVarBuilder.f12807a = new i1(cVarB, account, str, bundle2);
            xVarBuilder.f12810d = 1512;
            try {
                Bundle bundle3 = (Bundle) zzi(cVarB.doWrite(xVarBuilder.a()), "token retrieval");
                zzj(bundle3);
                return zzg(context, "getTokenWithDetails", bundle3);
            } catch (ApiException e5) {
                zzl(e5, "token retrieval");
            }
        }
        return (TokenData) zzh(context, zzc, new zzk() { // from class: com.google.android.gms.auth.zzg
            @Override // com.google.android.gms.auth.zzk
            public final Object zza(IBinder iBinder) {
                return zzl.zzb(account, str, bundle2, context, iBinder);
            }
        }, 0L, null);
    }

    public static /* synthetic */ TokenData zzb(Account account, String str, Bundle bundle, Context context, IBinder iBinder) throws IOException, RemoteException, GoogleAuthException {
        Bundle bundleH = v1.a(iBinder).H(account, str, bundle);
        if (bundleH != null) {
            return zzg(context, "getTokenWithDetails", bundleH);
        }
        throw new IOException("Service call returned null");
    }

    public static /* bridge */ /* synthetic */ Object zzc(Object obj) throws IOException {
        zzj(obj);
        return obj;
    }

    public static void zze(Context context, String str, long j10) throws GoogleAuthException, IOException {
        t.h("Calling this from your main thread can lead to deadlock");
        zzk(context, 8400000);
        Bundle bundle = new Bundle();
        zzm(context, bundle);
        c1.c(context);
        if (b4.f13000l.zza().zzc() && zzp(context)) {
            c cVarB = s1.b(context);
            m0 m0Var = new m0();
            m0Var.f13073l = str;
            x xVarBuilder = y.builder();
            xVarBuilder.f12809c = new d[]{zze.zzl};
            xVarBuilder.f12807a = new p2(cVarB, m0Var, 6);
            xVarBuilder.f12810d = 1513;
            try {
                zzi(cVarB.doWrite(xVarBuilder.a()), "clear token");
                return;
            } catch (ApiException e5) {
                zzl(e5, "clear token");
            }
        }
        zzh(context, zzc, new zzh(str, bundle), 0L, null);
    }

    public static void zzf(Intent intent) {
        if (intent == null) {
            throw new IllegalArgumentException("Callback cannot be null.");
        }
        try {
            Intent.parseUri(intent.toUri(1), 1);
        } catch (URISyntaxException unused) {
            throw new IllegalArgumentException("Parameter callback contains invalid data. It must be serializable using toUri() and parseUri().");
        }
    }

    private static TokenData zzg(Context context, String str, Bundle bundle) throws GoogleAuthException, IOException {
        TokenData tokenData;
        Parcelable.Creator<TokenData> creator = TokenData.CREATOR;
        ClassLoader classLoader = TokenData.class.getClassLoader();
        if (classLoader != null) {
            bundle.setClassLoader(classLoader);
        }
        Bundle bundle2 = bundle.getBundle("tokenDetails");
        if (bundle2 == null) {
            tokenData = null;
        } else {
            if (classLoader != null) {
                bundle2.setClassLoader(classLoader);
            }
            tokenData = (TokenData) bundle2.getParcelable("TokenData");
        }
        if (tokenData != null) {
            return tokenData;
        }
        zzn(context, "getTokenWithDetails", bundle.getString("Error"), (Intent) bundle.getParcelable("userRecoveryIntent"), (PendingIntent) bundle.getParcelable("userRecoveryPendingIntent"));
        throw new GoogleAuthException("Invalid state. Shouldn't happen");
    }

    private static Object zzh(Context context, ComponentName componentName, zzk zzkVar, long j10, Executor executor) throws IOException, GoogleAuthException {
        o3.a aVar = new o3.a();
        x0 x0VarA = l.a(context);
        try {
            x0VarA.getClass();
            try {
                if (!x0VarA.c(new u0(componentName), aVar, "GoogleAuthUtil", null).d()) {
                    throw new IOException("Could not bind to service.");
                }
                try {
                    return zzkVar.zza(aVar.a());
                } catch (RemoteException | InterruptedException | TimeoutException e5) {
                    Log.i("GoogleAuthUtil", "Error on service connection.", e5);
                    throw new IOException("Error on service connection.", e5);
                }
            } finally {
                x0VarA.d(new u0(componentName), aVar);
            }
        } catch (SecurityException e6) {
            Log.w("GoogleAuthUtil", "SecurityException while bind to auth service: " + e6.getMessage());
            throw new IOException("SecurityException while binding to Auth service.", e6);
        }
    }

    private static Object zzi(h hVar, String str) throws IOException, ApiException {
        try {
            return qb.l.c(hVar);
        } catch (InterruptedException e5) {
            String strL = a0.c.l("Interrupted while waiting for the task of ", str, " to finish.");
            zzd.c(strL, new Object[0]);
            throw new IOException(strL, e5);
        } catch (CancellationException e6) {
            String strL2 = a0.c.l("Canceled while waiting for the task of ", str, " to finish.");
            zzd.c(strL2, new Object[0]);
            throw new IOException(strL2, e6);
        } catch (ExecutionException e10) {
            Throwable cause = e10.getCause();
            if (cause instanceof ApiException) {
                throw ((ApiException) cause);
            }
            String strL3 = a0.c.l("Unable to get a result for ", str, " due to ExecutionException.");
            zzd.c(strL3, new Object[0]);
            throw new IOException(strL3, e10);
        }
    }

    private static Object zzj(Object obj) throws IOException {
        if (obj != null) {
            return obj;
        }
        zzd.c("Service call returned null.", new Object[0]);
        throw new IOException("Service unavailable.");
    }

    private static void zzk(Context context, int i10) throws GoogleAuthException {
        try {
            o3.g.b(context.getApplicationContext(), i10);
        } catch (GooglePlayServicesIncorrectManifestValueException | GooglePlayServicesNotAvailableException e5) {
            throw new GoogleAuthException(e5.getMessage(), e5);
        } catch (GooglePlayServicesRepairableException e6) {
            throw new GooglePlayServicesAvailabilityException(e6.f12664l, e6.getMessage(), new Intent(e6.f12665i));
        }
    }

    private static void zzl(ApiException apiException, String str) {
        zzd.c("%s failed via GoogleAuthServiceClient, falling back to previous approach:\n%s", str, Log.getStackTraceString(apiException));
    }

    private static void zzm(Context context, Bundle bundle) {
        String str = context.getApplicationInfo().packageName;
        bundle.putString("clientPackageName", str);
        String str2 = zzb;
        if (TextUtils.isEmpty(bundle.getString(str2))) {
            bundle.putString(str2, str);
        }
        bundle.putLong("service_connection_start_time_millis", SystemClock.elapsedRealtime());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzn(Context context, String str, String str2, Intent intent, PendingIntent pendingIntent) throws GoogleAuthException, IOException {
        String str3;
        int i10;
        int iB = g.b(str2);
        a aVar = zzd;
        StringBuilder sb2 = new StringBuilder("[GoogleAuthUtil] error status:");
        switch (iB) {
            case 1:
                str3 = "CLIENT_LOGIN_DISABLED";
                break;
            case 2:
                str3 = "SOCKET_TIMEOUT";
                break;
            case 3:
                str3 = "SUCCESS";
                break;
            case 4:
                str3 = "UNKNOWN_ERROR";
                break;
            case 5:
                str3 = "NETWORK_ERROR";
                break;
            case 6:
                str3 = "SERVICE_UNAVAILABLE";
                break;
            case 7:
                str3 = "INTNERNAL_ERROR";
                break;
            case 8:
                str3 = "ILLEGAL_ARGUMENT";
                break;
            case 9:
                str3 = "BAD_AUTHENTICATION";
                break;
            case 10:
                str3 = "BAD_TOKEN_REQUEST";
                break;
            case 11:
                str3 = "EMPTY_CONSUMER_PKG_OR_SIG";
                break;
            case 12:
                str3 = "NEEDS_2F";
                break;
            case 13:
                str3 = "NEEDS_POST_SIGN_IN_FLOW";
                break;
            case 14:
                str3 = "NEEDS_BROWSER";
                break;
            case 15:
                str3 = "UNKNOWN";
                break;
            case 16:
                str3 = "NOT_VERIFIED";
                break;
            case 17:
                str3 = "TERMS_NOT_AGREED";
                break;
            case 18:
                str3 = "ACCOUNT_DISABLED";
                break;
            case 19:
                str3 = "CAPTCHA";
                break;
            case 20:
                str3 = "ACCOUNT_DELETED";
                break;
            case 21:
                str3 = "SERVICE_DISABLED";
                break;
            case 22:
                str3 = "CHALLENGE_REQUIRED";
                break;
            case 23:
                str3 = "NEED_PERMISSION";
                break;
            case 24:
                str3 = "NEED_REMOTE_CONSENT";
                break;
            case 25:
                str3 = "INVALID_SCOPE";
                break;
            case 26:
                str3 = "USER_CANCEL";
                break;
            case 27:
                str3 = "PERMISSION_DENIED";
                break;
            case 28:
                str3 = "RESTRICTED_CLIENT";
                break;
            case 29:
                str3 = "INVALID_AUDIENCE";
                break;
            case 30:
                str3 = "UNREGISTERED_ON_API_CONSOLE";
                break;
            case 31:
                str3 = "THIRD_PARTY_DEVICE_MANAGEMENT_REQUIRED";
                break;
            case 32:
                str3 = "DM_INTERNAL_ERROR";
                break;
            case 33:
                str3 = "DM_SYNC_DISABLED";
                break;
            case 34:
                str3 = "DM_ADMIN_BLOCKED";
                break;
            case 35:
                str3 = "DM_ADMIN_PENDING_APPROVAL";
                break;
            case TsExtractor.TS_STREAM_TYPE_H265 /* 36 */:
                str3 = "DM_STALE_SYNC_REQUIRED";
                break;
            case 37:
                str3 = "DM_DEACTIVATED";
                break;
            case FlacConstants.STREAM_INFO_BLOCK_SIZE /* 38 */:
                str3 = "DM_SCREENLOCK_REQUIRED";
                break;
            case NalUnitUtil.H265_NAL_UNIT_TYPE_PREFIX_SEI /* 39 */:
                str3 = "DM_REQUIRED";
                break;
            case 40:
                str3 = "DEVICE_MANAGEMENT_REQUIRED";
                break;
            case 41:
                str3 = "ALREADY_HAS_GMAIL";
                break;
            case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
                str3 = "BAD_PASSWORD";
                break;
            case 43:
                str3 = "BAD_REQUEST";
                break;
            case 44:
                str3 = "BAD_USERNAME";
                break;
            case TsExtractor.TS_STREAM_TYPE_MHAS /* 45 */:
                str3 = "DELETED_GMAIL";
                break;
            case 46:
                str3 = "EXISTING_USERNAME";
                break;
            case 47:
                str3 = "LOGIN_FAIL";
                break;
            case 48:
                str3 = "NOT_LOGGED_IN";
                break;
            case 49:
                str3 = "NO_GMAIL";
                break;
            case 50:
                str3 = "REQUEST_DENIED";
                break;
            case 51:
                str3 = "SERVER_ERROR";
                break;
            case 52:
                str3 = "USERNAME_UNAVAILABLE";
                break;
            case 53:
                str3 = "GPLUS_OTHER";
                break;
            case 54:
                str3 = "GPLUS_NICKNAME";
                break;
            case 55:
                str3 = "GPLUS_INVALID_CHAR";
                break;
            case 56:
                str3 = "GPLUS_INTERSTITIAL";
                break;
            case 57:
                str3 = "GPLUS_PROFILE_ERROR";
                break;
            case 58:
                str3 = "AUTH_SECURITY_ERROR";
                break;
            case 59:
                str3 = "AUTH_BINDING_ERROR";
                break;
            case 60:
                str3 = "ACCOUNT_NOT_PRESENT";
                break;
            case 61:
                str3 = "APP_SUSPENDED";
                break;
            default:
                str3 = "null";
                break;
        }
        sb2.append(str3);
        sb2.append(" with method:");
        sb2.append(str);
        aVar.c(sb2.toString(), new Object[0]);
        if (!f0.b(9, iB) && !f0.b(19, iB) && !f0.b(23, iB) && !f0.b(24, iB) && !f0.b(14, iB) && !f0.b(26, iB) && !f0.b(40, iB) && !f0.b(32, iB) && !f0.b(33, iB) && !f0.b(34, iB) && !f0.b(35, iB) && !f0.b(36, iB) && !f0.b(37, iB) && !f0.b(39, iB) && !f0.b(31, iB) && !f0.b(38, iB)) {
            if (!f0.b(5, iB) && !f0.b(6, iB) && !f0.b(7, iB) && !f0.b(58, iB) && !f0.b(60, iB)) {
                throw new GoogleAuthException(str2);
            }
            throw new IOException(str2);
        }
        c1.c(context);
        if (!((z3) y3.f13151l.f13152i.zza()).zza()) {
            throw new UserRecoverableAuthException(str2, intent);
        }
        if (pendingIntent != null && intent != null) {
            throw UserRecoverableAuthException.zza(str2, intent, pendingIntent);
        }
        Object obj = e.f20740c;
        int i11 = o3.g.f20748e;
        try {
            i10 = context.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            i10 = 0;
        }
        if (i10 >= Integer.MAX_VALUE && pendingIntent == null) {
            a aVar2 = zzd;
            Log.e(aVar2.f22155a, aVar2.b(a0.c.l("Recovery PendingIntent is missing on current Gms version: 2147483647 for method: ", str, ". It should always be present on or above Gms version 2147483647. This indicates a bug in Gms implementation."), new Object[0]));
        }
        if (intent == null) {
            a aVar3 = zzd;
            Log.e(aVar3.f22155a, aVar3.b(a2.m("no recovery Intent found with status=", str2, " for method=", str, ". This shouldn't happen"), new Object[0]));
        }
        throw new UserRecoverableAuthException(str2, intent);
    }

    private static void zzo(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }
        if (TextUtils.isEmpty(account.name)) {
            throw new IllegalArgumentException("Account name cannot be empty!");
        }
        String[] strArr = zza;
        for (int i10 = 0; i10 < 3; i10++) {
            if (strArr[i10].equals(account.type)) {
                return;
            }
        }
        throw new IllegalArgumentException("Account type not supported");
    }

    private static boolean zzp(Context context) {
        if (e.f20741d.b(context, 17895000) != 0) {
            return false;
        }
        g2 g2VarK = b4.f13000l.zza().zza().k();
        String str = context.getApplicationInfo().packageName;
        Iterator it = g2VarK.iterator();
        while (it.hasNext()) {
            if (((String) it.next()).equals(str)) {
                return false;
            }
        }
        return true;
    }

    public static String getToken(Context context, Account account, String str, Bundle bundle) throws IOException, GoogleAuthException {
        zzo(account);
        return zza(context, account, str, bundle, 0L, null).zza();
    }

    @Deprecated
    public static String getToken(Context context, String str, String str2) throws IOException, GoogleAuthException {
        return getToken(context, new Account(str, "com.google"), str2);
    }

    @Deprecated
    public static String getToken(Context context, String str, String str2, Bundle bundle) throws IOException, GoogleAuthException {
        return getToken(context, new Account(str, "com.google"), str2, bundle);
    }
}
