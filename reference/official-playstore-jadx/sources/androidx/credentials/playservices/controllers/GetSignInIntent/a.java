package androidx.credentials.playservices.controllers.GetSignInIntent;

import androidx.credentials.exceptions.GetCredentialException;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class a implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f2387i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ CredentialProviderGetSignInIntentController f2388l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ GetCredentialException f2389m;

    public /* synthetic */ a(CredentialProviderGetSignInIntentController credentialProviderGetSignInIntentController, GetCredentialException getCredentialException, int i10) {
        this.f2387i = i10;
        this.f2388l = credentialProviderGetSignInIntentController;
        this.f2389m = getCredentialException;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f2387i) {
            case 0:
                CredentialProviderGetSignInIntentController$handleResponse$2.invoke$lambda$0(this.f2388l, this.f2389m);
                break;
            default:
                CredentialProviderGetSignInIntentController$handleResponse$5.invoke$lambda$0(this.f2388l, this.f2389m);
                break;
        }
    }
}
