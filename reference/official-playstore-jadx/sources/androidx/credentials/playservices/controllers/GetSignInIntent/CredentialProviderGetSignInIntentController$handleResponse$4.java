package androidx.credentials.playservices.controllers.GetSignInIntent;

import kotlin.Metadata;
import kotlin.jvm.internal.k0;
import kotlin.jvm.internal.r;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class CredentialProviderGetSignInIntentController$handleResponse$4 extends r implements r7.a<t0> {
    final /* synthetic */ k0 $exception;
    final /* synthetic */ CredentialProviderGetSignInIntentController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CredentialProviderGetSignInIntentController$handleResponse$4(CredentialProviderGetSignInIntentController credentialProviderGetSignInIntentController, k0 k0Var) {
        super(0);
        this.this$0 = credentialProviderGetSignInIntentController;
        this.$exception = k0Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void invoke$lambda$0(CredentialProviderGetSignInIntentController credentialProviderGetSignInIntentController, k0 k0Var) {
        credentialProviderGetSignInIntentController.getCallback().onError(k0Var.f19746i);
    }

    @Override // r7.a
    public /* bridge */ /* synthetic */ Object invoke() {
        m5967invoke();
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m5967invoke() {
        this.this$0.getExecutor().execute(new b(this.this$0, this.$exception, 1));
    }
}
