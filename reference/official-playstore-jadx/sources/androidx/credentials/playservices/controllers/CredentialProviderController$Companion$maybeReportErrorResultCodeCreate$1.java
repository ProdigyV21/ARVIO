package androidx.credentials.playservices.controllers;

import androidx.credentials.exceptions.CreateCredentialException;
import kotlin.Metadata;
import kotlin.jvm.internal.k0;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\t\u001a\u00020\u0006\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0000\"\b\b\u0002\u0010\u0003*\u00020\u0000\"\b\b\u0003\u0010\u0004*\u00020\u0000\"\b\b\u0004\u0010\u0005*\u00020\u0000H\n¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"", "T1", "T2", "R2", "R1", "E1", "Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class CredentialProviderController$Companion$maybeReportErrorResultCodeCreate$1 extends r implements r7.a<t0> {
    final /* synthetic */ k0 $exception;
    final /* synthetic */ l<CreateCredentialException, t0> $onError;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public CredentialProviderController$Companion$maybeReportErrorResultCodeCreate$1(l<? super CreateCredentialException, t0> lVar, k0 k0Var) {
        super(0);
        this.$onError = lVar;
        this.$exception = k0Var;
    }

    @Override // r7.a
    public /* bridge */ /* synthetic */ Object invoke() {
        m5963invoke();
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m5963invoke() {
        this.$onError.invoke(this.$exception.f19746i);
    }
}
