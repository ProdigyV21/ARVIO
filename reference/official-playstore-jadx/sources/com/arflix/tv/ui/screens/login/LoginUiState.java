package com.arflix.tv.ui.screens.login;

import androidx.credentials.GetCredentialRequest;
import com.arflix.tv.data.repository.AuthState;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J?\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u001b\u001a\u00020\u00032\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001d\u001a\u00020\u001eHÖ\u0081\u0004J\n\u0010\u001f\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\rR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\r¨\u0006 "}, d2 = {"Lcom/arflix/tv/ui/screens/login/LoginUiState;", "", "isLoading", "", "error", "", "authState", "Lcom/arflix/tv/data/repository/AuthState;", "googleSignInRequest", "Landroidx/credentials/GetCredentialRequest;", "loginReady", "<init>", "(ZLjava/lang/String;Lcom/arflix/tv/data/repository/AuthState;Landroidx/credentials/GetCredentialRequest;Z)V", "()Z", "getError", "()Ljava/lang/String;", "getAuthState", "()Lcom/arflix/tv/data/repository/AuthState;", "getGoogleSignInRequest", "()Landroidx/credentials/GetCredentialRequest;", "getLoginReady", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class LoginUiState {
    public static final int $stable = 8;
    private final AuthState authState;
    private final String error;
    private final GetCredentialRequest googleSignInRequest;
    private final boolean isLoading;
    private final boolean loginReady;

    public LoginUiState() {
        this(false, null, null, null, false, 31, null);
    }

    public static /* synthetic */ LoginUiState copy$default(LoginUiState loginUiState, boolean z, String str, AuthState authState, GetCredentialRequest getCredentialRequest, boolean z5, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = loginUiState.isLoading;
        }
        if ((i10 & 2) != 0) {
            str = loginUiState.error;
        }
        if ((i10 & 4) != 0) {
            authState = loginUiState.authState;
        }
        if ((i10 & 8) != 0) {
            getCredentialRequest = loginUiState.googleSignInRequest;
        }
        if ((i10 & 16) != 0) {
            z5 = loginUiState.loginReady;
        }
        boolean z10 = z5;
        AuthState authState2 = authState;
        return loginUiState.copy(z, str, authState2, getCredentialRequest, z10);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getIsLoading() {
        return this.isLoading;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getError() {
        return this.error;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final AuthState getAuthState() {
        return this.authState;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final GetCredentialRequest getGoogleSignInRequest() {
        return this.googleSignInRequest;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getLoginReady() {
        return this.loginReady;
    }

    public final LoginUiState copy(boolean isLoading, String error, AuthState authState, GetCredentialRequest googleSignInRequest, boolean loginReady) {
        return new LoginUiState(isLoading, error, authState, googleSignInRequest, loginReady);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LoginUiState)) {
            return false;
        }
        LoginUiState loginUiState = (LoginUiState) other;
        return this.isLoading == loginUiState.isLoading && p.a(this.error, loginUiState.error) && p.a(this.authState, loginUiState.authState) && p.a(this.googleSignInRequest, loginUiState.googleSignInRequest) && this.loginReady == loginUiState.loginReady;
    }

    public final AuthState getAuthState() {
        return this.authState;
    }

    public final String getError() {
        return this.error;
    }

    public final GetCredentialRequest getGoogleSignInRequest() {
        return this.googleSignInRequest;
    }

    public final boolean getLoginReady() {
        return this.loginReady;
    }

    public int hashCode() {
        int i10 = (this.isLoading ? 1231 : 1237) * 31;
        String str = this.error;
        int iHashCode = (this.authState.hashCode() + ((i10 + (str == null ? 0 : str.hashCode())) * 31)) * 31;
        GetCredentialRequest getCredentialRequest = this.googleSignInRequest;
        return ((iHashCode + (getCredentialRequest != null ? getCredentialRequest.hashCode() : 0)) * 31) + (this.loginReady ? 1231 : 1237);
    }

    public final boolean isLoading() {
        return this.isLoading;
    }

    public String toString() {
        boolean z = this.isLoading;
        String str = this.error;
        AuthState authState = this.authState;
        GetCredentialRequest getCredentialRequest = this.googleSignInRequest;
        boolean z5 = this.loginReady;
        StringBuilder sb2 = new StringBuilder("LoginUiState(isLoading=");
        sb2.append(z);
        sb2.append(", error=");
        sb2.append(str);
        sb2.append(", authState=");
        sb2.append(authState);
        sb2.append(", googleSignInRequest=");
        sb2.append(getCredentialRequest);
        sb2.append(", loginReady=");
        return a0.c.m(")", z5, sb2);
    }

    public LoginUiState(boolean z, String str, AuthState authState, GetCredentialRequest getCredentialRequest, boolean z5) {
        this.isLoading = z;
        this.error = str;
        this.authState = authState;
        this.googleSignInRequest = getCredentialRequest;
        this.loginReady = z5;
    }

    public /* synthetic */ LoginUiState(boolean z, String str, AuthState authState, GetCredentialRequest getCredentialRequest, boolean z5, int i10, h hVar) {
        this((i10 & 1) != 0 ? false : z, (i10 & 2) != 0 ? null : str, (i10 & 4) != 0 ? AuthState.Loading.INSTANCE : authState, (i10 & 8) != 0 ? null : getCredentialRequest, (i10 & 16) != 0 ? false : z5);
    }
}
