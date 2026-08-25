package androidx.credentials.playservices.controllers.CreatePublicKeyCredential;

import android.os.CancellationSignal;
import androidx.credentials.playservices.controllers.CredentialProviderController;
import androidx.media3.exoplayer.upstream.CmcdData;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0007\u001a\u00020\u00032\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Landroid/os/CancellationSignal;", CmcdData.STREAMING_FORMAT_SS, "Lkotlin/Function0;", "Lx6/t0;", "f", "invoke", "(Landroid/os/CancellationSignal;Lr7/a;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class CredentialProviderCreatePublicKeyCredentialController$handleResponse$1 extends r implements p<CancellationSignal, r7.a<? extends t0>, t0> {
    public static final CredentialProviderCreatePublicKeyCredentialController$handleResponse$1 INSTANCE = new CredentialProviderCreatePublicKeyCredentialController$handleResponse$1();

    public CredentialProviderCreatePublicKeyCredentialController$handleResponse$1() {
        super(2);
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((CancellationSignal) obj, (r7.a<t0>) obj2);
        return t0.f22605a;
    }

    public final void invoke(CancellationSignal cancellationSignal, r7.a<t0> aVar) {
        CredentialProviderController.Companion companion = CredentialProviderController.INSTANCE;
        CredentialProviderController.cancelOrCallbackExceptionOrResult(cancellationSignal, aVar);
    }
}
