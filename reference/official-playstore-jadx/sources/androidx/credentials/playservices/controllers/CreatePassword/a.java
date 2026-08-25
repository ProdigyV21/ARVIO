package androidx.credentials.playservices.controllers.CreatePassword;

import androidx.credentials.CreateCredentialResponse;
import androidx.credentials.exceptions.CreateCredentialException;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class a implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f2372i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ CredentialProviderCreatePasswordController f2373l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f2374m;

    public /* synthetic */ a(CredentialProviderCreatePasswordController credentialProviderCreatePasswordController, Object obj, int i10) {
        this.f2372i = i10;
        this.f2373l = credentialProviderCreatePasswordController;
        this.f2374m = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f2372i) {
            case 0:
                CredentialProviderCreatePasswordController$handleResponse$2.invoke$lambda$0(this.f2373l, (CreateCredentialException) this.f2374m);
                break;
            default:
                CredentialProviderCreatePasswordController$handleResponse$3.invoke$lambda$0(this.f2373l, (CreateCredentialResponse) this.f2374m);
                break;
        }
    }
}
