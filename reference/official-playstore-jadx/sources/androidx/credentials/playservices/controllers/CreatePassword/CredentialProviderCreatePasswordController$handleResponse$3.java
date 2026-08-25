package androidx.credentials.playservices.controllers.CreatePassword;

import androidx.credentials.CreateCredentialResponse;
import androidx.credentials.CredentialManagerCallback;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class CredentialProviderCreatePasswordController$handleResponse$3 extends r implements r7.a<t0> {
    final /* synthetic */ CreateCredentialResponse $response;
    final /* synthetic */ CredentialProviderCreatePasswordController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CredentialProviderCreatePasswordController$handleResponse$3(CredentialProviderCreatePasswordController credentialProviderCreatePasswordController, CreateCredentialResponse createCredentialResponse) {
        super(0);
        this.this$0 = credentialProviderCreatePasswordController;
        this.$response = createCredentialResponse;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(CredentialProviderCreatePasswordController credentialProviderCreatePasswordController, CreateCredentialResponse createCredentialResponse) {
        CredentialManagerCallback credentialManagerCallback = credentialProviderCreatePasswordController.callback;
        if (credentialManagerCallback != null) {
            credentialManagerCallback.onResult(createCredentialResponse);
        } else {
            p.i("callback");
            throw null;
        }
    }

    @Override // r7.a
    public /* bridge */ /* synthetic */ Object invoke() {
        m5954invoke();
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m5954invoke() {
        Executor executor = this.this$0.executor;
        if (executor != null) {
            executor.execute(new a(this.this$0, this.$response, 1));
        } else {
            p.i("executor");
            throw null;
        }
    }
}
