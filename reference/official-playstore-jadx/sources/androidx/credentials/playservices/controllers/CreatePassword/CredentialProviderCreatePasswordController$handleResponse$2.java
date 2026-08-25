package androidx.credentials.playservices.controllers.CreatePassword;

import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.exceptions.CreateCredentialException;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/credentials/exceptions/CreateCredentialException;", "e", "Lx6/t0;", "invoke", "(Landroidx/credentials/exceptions/CreateCredentialException;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class CredentialProviderCreatePasswordController$handleResponse$2 extends r implements l<CreateCredentialException, t0> {
    final /* synthetic */ CredentialProviderCreatePasswordController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CredentialProviderCreatePasswordController$handleResponse$2(CredentialProviderCreatePasswordController credentialProviderCreatePasswordController) {
        super(1);
        this.this$0 = credentialProviderCreatePasswordController;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(CredentialProviderCreatePasswordController credentialProviderCreatePasswordController, CreateCredentialException createCredentialException) {
        CredentialManagerCallback credentialManagerCallback = credentialProviderCreatePasswordController.callback;
        if (credentialManagerCallback != null) {
            credentialManagerCallback.onError(createCredentialException);
        } else {
            p.i("callback");
            throw null;
        }
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((CreateCredentialException) obj);
        return t0.f22605a;
    }

    public final void invoke(CreateCredentialException createCredentialException) {
        Executor executor = this.this$0.executor;
        if (executor != null) {
            executor.execute(new a(this.this$0, createCredentialException, 0));
        } else {
            p.i("executor");
            throw null;
        }
    }
}
