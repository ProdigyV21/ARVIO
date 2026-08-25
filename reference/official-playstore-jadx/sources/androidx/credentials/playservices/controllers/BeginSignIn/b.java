package androidx.credentials.playservices.controllers.BeginSignIn;

import androidx.credentials.GetCredentialResponse;
import androidx.credentials.exceptions.GetCredentialUnknownException;
import kotlin.jvm.internal.k0;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class b implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f2368i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ CredentialProviderBeginSignInController f2369l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f2370m;

    public /* synthetic */ b(CredentialProviderBeginSignInController credentialProviderBeginSignInController, Object obj, int i10) {
        this.f2368i = i10;
        this.f2369l = credentialProviderBeginSignInController;
        this.f2370m = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f2368i) {
            case 0:
                CredentialProviderBeginSignInController$handleResponse$3.invoke$lambda$0(this.f2369l, (GetCredentialResponse) this.f2370m);
                break;
            case 1:
                CredentialProviderBeginSignInController$handleResponse$4.invoke$lambda$0(this.f2369l, (k0) this.f2370m);
                break;
            default:
                CredentialProviderBeginSignInController$handleResponse$6.invoke$lambda$0(this.f2369l, (GetCredentialUnknownException) this.f2370m);
                break;
        }
    }
}
