package androidx.credentials.playservices.controllers.BeginSignIn;

import androidx.credentials.exceptions.GetCredentialException;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class a implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f2365i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ CredentialProviderBeginSignInController f2366l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ GetCredentialException f2367m;

    public /* synthetic */ a(CredentialProviderBeginSignInController credentialProviderBeginSignInController, GetCredentialException getCredentialException, int i10) {
        this.f2365i = i10;
        this.f2366l = credentialProviderBeginSignInController;
        this.f2367m = getCredentialException;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f2365i) {
            case 0:
                CredentialProviderBeginSignInController$handleResponse$2.invoke$lambda$0(this.f2366l, this.f2367m);
                break;
            default:
                CredentialProviderBeginSignInController$handleResponse$5.invoke$lambda$0(this.f2366l, this.f2367m);
                break;
        }
    }
}
