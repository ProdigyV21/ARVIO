package androidx.credentials.playservices.controllers.CreatePublicKeyCredential;

import androidx.credentials.playservices.controllers.CreatePublicKeyCredential.CredentialProviderCreatePublicKeyCredentialController;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class d implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f2385i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ CredentialProviderCreatePublicKeyCredentialController f2386l;

    public /* synthetic */ d(CredentialProviderCreatePublicKeyCredentialController credentialProviderCreatePublicKeyCredentialController, int i10) {
        this.f2385i = i10;
        this.f2386l = credentialProviderCreatePublicKeyCredentialController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f2385i) {
            case 0:
                CredentialProviderCreatePublicKeyCredentialController.AnonymousClass3.invoke$lambda$0(this.f2386l);
                break;
            default:
                CredentialProviderCreatePublicKeyCredentialController.handleResponse$lambda$0(this.f2386l);
                break;
        }
    }
}
