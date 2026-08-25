package androidx.credentials;

import android.content.pm.SigningInfo;
import android.credentials.ClearCredentialStateException;
import android.credentials.CreateCredentialException;
import android.credentials.GetCredentialException;
import android.os.Bundle;
import android.service.credentials.BeginCreateCredentialRequest;
import android.service.credentials.BeginGetCredentialOption;
import android.service.credentials.CallingAppInfo;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class c {
    public static /* synthetic */ ClearCredentialStateException a(String str, String str2) {
        return new ClearCredentialStateException(str, str2);
    }

    public static /* synthetic */ android.credentials.ClearCredentialStateRequest b(Bundle bundle) {
        return new android.credentials.ClearCredentialStateRequest(bundle);
    }

    public static /* synthetic */ CreateCredentialException c(String str, String str2) {
        return new CreateCredentialException(str, str2);
    }

    public static /* synthetic */ android.credentials.CreateCredentialResponse d(Bundle bundle) {
        return new android.credentials.CreateCredentialResponse(bundle);
    }

    public static /* synthetic */ android.credentials.Credential e(Bundle bundle, String str) {
        return new android.credentials.Credential(str, bundle);
    }

    public static /* synthetic */ GetCredentialException f(String str, String str2) {
        return new GetCredentialException(str, str2);
    }

    public static /* synthetic */ android.credentials.GetCredentialResponse g(android.credentials.Credential credential) {
        return new android.credentials.GetCredentialResponse(credential);
    }

    public static /* synthetic */ BeginCreateCredentialRequest h(String str, Bundle bundle, CallingAppInfo callingAppInfo) {
        return new BeginCreateCredentialRequest(str, bundle, callingAppInfo);
    }

    public static /* synthetic */ BeginGetCredentialOption i(String str, String str2, Bundle bundle) {
        return new BeginGetCredentialOption(str, str2, bundle);
    }

    public static /* synthetic */ CallingAppInfo j(String str, SigningInfo signingInfo, String str2) {
        return new CallingAppInfo(str, signingInfo, str2);
    }

    public static /* synthetic */ void k() {
    }

    public static /* synthetic */ void l() {
    }

    public static /* synthetic */ void m() {
    }

    public static /* synthetic */ void n() {
    }

    public static /* synthetic */ void o() {
    }

    public static /* synthetic */ void p() {
    }

    public static /* synthetic */ void q() {
    }

    public static /* synthetic */ void r() {
    }

    public static /* synthetic */ void s() {
    }

    public static /* synthetic */ void t() {
    }
}
