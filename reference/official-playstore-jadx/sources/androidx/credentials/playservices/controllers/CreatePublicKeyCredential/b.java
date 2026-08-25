package androidx.credentials.playservices.controllers.CreatePublicKeyCredential;

import androidx.credentials.playservices.controllers.CreatePublicKeyCredential.CredentialProviderCreatePublicKeyCredentialController;
import org.json.JSONException;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class b implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f2379i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ CredentialProviderCreatePublicKeyCredentialController f2380l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ JSONException f2381m;

    public /* synthetic */ b(CredentialProviderCreatePublicKeyCredentialController credentialProviderCreatePublicKeyCredentialController, JSONException jSONException, int i10) {
        this.f2379i = i10;
        this.f2380l = credentialProviderCreatePublicKeyCredentialController;
        this.f2381m = jSONException;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f2379i) {
            case 0:
                CredentialProviderCreatePublicKeyCredentialController$handleResponse$6.invoke$lambda$0(this.f2380l, this.f2381m);
                break;
            default:
                CredentialProviderCreatePublicKeyCredentialController.AnonymousClass1.invoke$lambda$0(this.f2380l, this.f2381m);
                break;
        }
    }
}
