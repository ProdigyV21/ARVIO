package androidx.credentials.playservices.controllers.GetSignInIntent;

import androidx.credentials.GetCredentialResponse;
import androidx.credentials.exceptions.GetCredentialUnknownException;
import androidx.credentials.playservices.controllers.GetSignInIntent.CredentialProviderGetSignInIntentController;
import kotlin.jvm.internal.k0;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class b implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f2390i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ CredentialProviderGetSignInIntentController f2391l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f2392m;

    public /* synthetic */ b(CredentialProviderGetSignInIntentController credentialProviderGetSignInIntentController, Object obj, int i10) {
        this.f2390i = i10;
        this.f2391l = credentialProviderGetSignInIntentController;
        this.f2392m = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f2390i) {
            case 0:
                CredentialProviderGetSignInIntentController$handleResponse$3.invoke$lambda$0(this.f2391l, (GetCredentialResponse) this.f2392m);
                break;
            case 1:
                CredentialProviderGetSignInIntentController$handleResponse$4.invoke$lambda$0(this.f2391l, (k0) this.f2392m);
                break;
            case 2:
                CredentialProviderGetSignInIntentController$handleResponse$6.invoke$lambda$0(this.f2391l, (GetCredentialUnknownException) this.f2392m);
                break;
            default:
                CredentialProviderGetSignInIntentController.AnonymousClass1.invoke$lambda$0(this.f2391l, (Exception) this.f2392m);
                break;
        }
    }
}
