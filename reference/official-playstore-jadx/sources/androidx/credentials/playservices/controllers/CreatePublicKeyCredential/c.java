package androidx.credentials.playservices.controllers.CreatePublicKeyCredential;

import androidx.credentials.playservices.controllers.CreatePublicKeyCredential.CredentialProviderCreatePublicKeyCredentialController;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class c implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f2382i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ CredentialProviderCreatePublicKeyCredentialController f2383l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Throwable f2384m;

    public /* synthetic */ c(CredentialProviderCreatePublicKeyCredentialController credentialProviderCreatePublicKeyCredentialController, Throwable th, int i10) {
        this.f2382i = i10;
        this.f2383l = credentialProviderCreatePublicKeyCredentialController;
        this.f2384m = th;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f2382i) {
            case 0:
                CredentialProviderCreatePublicKeyCredentialController$handleResponse$7.invoke$lambda$0(this.f2383l, this.f2384m);
                break;
            default:
                CredentialProviderCreatePublicKeyCredentialController.AnonymousClass2.invoke$lambda$0(this.f2383l, this.f2384m);
                break;
        }
    }
}
