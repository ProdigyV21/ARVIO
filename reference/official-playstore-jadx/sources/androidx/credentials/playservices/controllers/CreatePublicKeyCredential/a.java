package androidx.credentials.playservices.controllers.CreatePublicKeyCredential;

import androidx.credentials.CreateCredentialResponse;
import androidx.credentials.exceptions.CreateCredentialException;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class a implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f2376i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ CredentialProviderCreatePublicKeyCredentialController f2377l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f2378m;

    public /* synthetic */ a(CredentialProviderCreatePublicKeyCredentialController credentialProviderCreatePublicKeyCredentialController, Object obj, int i10) {
        this.f2376i = i10;
        this.f2377l = credentialProviderCreatePublicKeyCredentialController;
        this.f2378m = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f2376i) {
            case 0:
                CredentialProviderCreatePublicKeyCredentialController$handleResponse$2.invoke$lambda$0(this.f2377l, (CreateCredentialException) this.f2378m);
                break;
            case 1:
                CredentialProviderCreatePublicKeyCredentialController$handleResponse$4.invoke$lambda$0(this.f2377l, (CreateCredentialException) this.f2378m);
                break;
            default:
                CredentialProviderCreatePublicKeyCredentialController$handleResponse$5.invoke$lambda$0(this.f2377l, (CreateCredentialResponse) this.f2378m);
                break;
        }
    }
}
