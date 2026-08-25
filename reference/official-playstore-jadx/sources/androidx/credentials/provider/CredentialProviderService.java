package androidx.credentials.provider;

import android.os.CancellationSignal;
import android.os.OutcomeReceiver;
import android.service.credentials.ClearCredentialStateRequest;
import androidx.credentials.c;
import androidx.credentials.exceptions.ClearCredentialException;
import androidx.credentials.exceptions.CreateCredentialException;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.provider.utils.BeginCreateCredentialUtil;
import androidx.credentials.provider.utils.BeginGetCredentialUtil;
import androidx.credentials.provider.utils.ClearCredentialUtil;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0019\b'\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J1\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0004\b\r\u0010\u000eJ1\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u00062\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\b¢\u0006\u0004\b\u0012\u0010\u0013J1\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\u00062\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\b¢\u0006\u0004\b\u0017\u0010\u0018J5\u0010\u001b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00192\u0006\u0010\u0007\u001a\u00020\u00062\u0014\u0010\u000b\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0012\u0004\u0012\u00020\u001a0\bH&¢\u0006\u0004\b\u001b\u0010\u001cJ3\u0010 \u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u001d2\u0006\u0010\u0007\u001a\u00020\u00062\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f0\bH&¢\u0006\u0004\b \u0010!J3\u0010%\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\"2\u0006\u0010\u0007\u001a\u00020\u00062\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020$0\bH&¢\u0006\u0004\b%\u0010&R*\u0010)\u001a\u00020'2\u0006\u0010(\u001a\u00020'8G@GX\u0086\u000e¢\u0006\u0012\n\u0004\b)\u0010*\u001a\u0004\b)\u0010+\"\u0004\b,\u0010-R.\u0010.\u001a\u0004\u0018\u00010\"2\b\u0010(\u001a\u0004\u0018\u00010\"8G@GX\u0086\u000e¢\u0006\u0012\n\u0004\b.\u0010/\u001a\u0004\b0\u00101\"\u0004\b2\u00103R.\u00104\u001a\u0004\u0018\u00010\u001d2\b\u0010(\u001a\u0004\u0018\u00010\u001d8G@GX\u0086\u000e¢\u0006\u0012\n\u0004\b4\u00105\u001a\u0004\b6\u00107\"\u0004\b8\u00109R.\u0010:\u001a\u0004\u0018\u00010\u00192\b\u0010(\u001a\u0004\u0018\u00010\u00198G@GX\u0086\u000e¢\u0006\u0012\n\u0004\b:\u0010;\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?¨\u0006@"}, d2 = {"Landroidx/credentials/provider/CredentialProviderService;", "Landroid/service/credentials/CredentialProviderService;", "<init>", "()V", "Landroid/service/credentials/BeginGetCredentialRequest;", "request", "Landroid/os/CancellationSignal;", "cancellationSignal", "Landroid/os/OutcomeReceiver;", "Landroid/service/credentials/BeginGetCredentialResponse;", "Landroid/credentials/GetCredentialException;", "callback", "Lx6/t0;", "onBeginGetCredential", "(Landroid/service/credentials/BeginGetCredentialRequest;Landroid/os/CancellationSignal;Landroid/os/OutcomeReceiver;)V", "Landroid/service/credentials/BeginCreateCredentialRequest;", "Landroid/service/credentials/BeginCreateCredentialResponse;", "Landroid/credentials/CreateCredentialException;", "onBeginCreateCredential", "(Landroid/service/credentials/BeginCreateCredentialRequest;Landroid/os/CancellationSignal;Landroid/os/OutcomeReceiver;)V", "Landroid/service/credentials/ClearCredentialStateRequest;", "Ljava/lang/Void;", "Landroid/credentials/ClearCredentialStateException;", "onClearCredentialState", "(Landroid/service/credentials/ClearCredentialStateRequest;Landroid/os/CancellationSignal;Landroid/os/OutcomeReceiver;)V", "Landroidx/credentials/provider/ProviderClearCredentialStateRequest;", "Landroidx/credentials/exceptions/ClearCredentialException;", "onClearCredentialStateRequest", "(Landroidx/credentials/provider/ProviderClearCredentialStateRequest;Landroid/os/CancellationSignal;Landroid/os/OutcomeReceiver;)V", "Landroidx/credentials/provider/BeginGetCredentialRequest;", "Landroidx/credentials/provider/BeginGetCredentialResponse;", "Landroidx/credentials/exceptions/GetCredentialException;", "onBeginGetCredentialRequest", "(Landroidx/credentials/provider/BeginGetCredentialRequest;Landroid/os/CancellationSignal;Landroid/os/OutcomeReceiver;)V", "Landroidx/credentials/provider/BeginCreateCredentialRequest;", "Landroidx/credentials/provider/BeginCreateCredentialResponse;", "Landroidx/credentials/exceptions/CreateCredentialException;", "onBeginCreateCredentialRequest", "(Landroidx/credentials/provider/BeginCreateCredentialRequest;Landroid/os/CancellationSignal;Landroid/os/OutcomeReceiver;)V", "", "<set-?>", "isTestMode", "Z", "()Z", "setTestMode", "(Z)V", "lastCreateRequest", "Landroidx/credentials/provider/BeginCreateCredentialRequest;", "getLastCreateRequest", "()Landroidx/credentials/provider/BeginCreateCredentialRequest;", "setLastCreateRequest", "(Landroidx/credentials/provider/BeginCreateCredentialRequest;)V", "lastGetRequest", "Landroidx/credentials/provider/BeginGetCredentialRequest;", "getLastGetRequest", "()Landroidx/credentials/provider/BeginGetCredentialRequest;", "setLastGetRequest", "(Landroidx/credentials/provider/BeginGetCredentialRequest;)V", "lastClearRequest", "Landroidx/credentials/provider/ProviderClearCredentialStateRequest;", "getLastClearRequest", "()Landroidx/credentials/provider/ProviderClearCredentialStateRequest;", "setLastClearRequest", "(Landroidx/credentials/provider/ProviderClearCredentialStateRequest;)V", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class CredentialProviderService extends android.service.credentials.CredentialProviderService {
    private boolean isTestMode;
    private ProviderClearCredentialStateRequest lastClearRequest;
    private BeginCreateCredentialRequest lastCreateRequest;
    private BeginGetCredentialRequest lastGetRequest;

    public final ProviderClearCredentialStateRequest getLastClearRequest() {
        return this.lastClearRequest;
    }

    public final BeginCreateCredentialRequest getLastCreateRequest() {
        return this.lastCreateRequest;
    }

    public final BeginGetCredentialRequest getLastGetRequest() {
        return this.lastGetRequest;
    }

    /* JADX INFO: renamed from: isTestMode, reason: from getter */
    public final boolean getIsTestMode() {
        return this.isTestMode;
    }

    public final void onBeginCreateCredential(android.service.credentials.BeginCreateCredentialRequest request, CancellationSignal cancellationSignal, final OutcomeReceiver callback) {
        OutcomeReceiver outcomeReceiver = new OutcomeReceiver() { // from class: androidx.credentials.provider.CredentialProviderService$onBeginCreateCredential$outcome$1
            public void onError(CreateCredentialException error) {
                OutcomeReceiver outcomeReceiver2 = callback;
                c.l();
                outcomeReceiver2.onError(c.c(error.getType(), error.getMessage()));
            }

            public void onResult(BeginCreateCredentialResponse response) {
                callback.onResult(BeginCreateCredentialUtil.INSTANCE.convertToFrameworkResponse(response));
            }
        };
        BeginCreateCredentialRequest beginCreateCredentialRequestConvertToJetpackRequest$credentials_release = BeginCreateCredentialUtil.INSTANCE.convertToJetpackRequest$credentials_release(request);
        if (this.isTestMode) {
            this.lastCreateRequest = beginCreateCredentialRequestConvertToJetpackRequest$credentials_release;
        }
        onBeginCreateCredentialRequest(beginCreateCredentialRequestConvertToJetpackRequest$credentials_release, cancellationSignal, outcomeReceiver);
    }

    public abstract void onBeginCreateCredentialRequest(BeginCreateCredentialRequest request, CancellationSignal cancellationSignal, OutcomeReceiver callback);

    public final void onBeginGetCredential(android.service.credentials.BeginGetCredentialRequest request, CancellationSignal cancellationSignal, final OutcomeReceiver callback) {
        BeginGetCredentialRequest beginGetCredentialRequestConvertToJetpackRequest$credentials_release = BeginGetCredentialUtil.INSTANCE.convertToJetpackRequest$credentials_release(request);
        OutcomeReceiver outcomeReceiver = new OutcomeReceiver() { // from class: androidx.credentials.provider.CredentialProviderService$onBeginGetCredential$outcome$1
            public void onError(GetCredentialException error) {
                OutcomeReceiver outcomeReceiver2 = callback;
                c.m();
                outcomeReceiver2.onError(c.f(error.getType(), error.getMessage()));
            }

            public void onResult(BeginGetCredentialResponse response) {
                callback.onResult(BeginGetCredentialUtil.INSTANCE.convertToFrameworkResponse(response));
            }
        };
        if (this.isTestMode) {
            this.lastGetRequest = beginGetCredentialRequestConvertToJetpackRequest$credentials_release;
        }
        onBeginGetCredentialRequest(beginGetCredentialRequestConvertToJetpackRequest$credentials_release, cancellationSignal, outcomeReceiver);
    }

    public abstract void onBeginGetCredentialRequest(BeginGetCredentialRequest request, CancellationSignal cancellationSignal, OutcomeReceiver callback);

    public final void onClearCredentialState(ClearCredentialStateRequest request, CancellationSignal cancellationSignal, final OutcomeReceiver callback) {
        OutcomeReceiver outcomeReceiver = new OutcomeReceiver() { // from class: androidx.credentials.provider.CredentialProviderService$onClearCredentialState$outcome$1
            public void onError(ClearCredentialException error) {
                OutcomeReceiver outcomeReceiver2 = callback;
                c.n();
                outcomeReceiver2.onError(c.a(error.getType(), error.getMessage()));
            }

            public void onResult(Void response) {
                callback.onResult(response);
            }
        };
        ProviderClearCredentialStateRequest providerClearCredentialStateRequestConvertToJetpackRequest$credentials_release = ClearCredentialUtil.INSTANCE.convertToJetpackRequest$credentials_release(request);
        if (this.isTestMode) {
            this.lastClearRequest = providerClearCredentialStateRequestConvertToJetpackRequest$credentials_release;
        }
        onClearCredentialStateRequest(providerClearCredentialStateRequestConvertToJetpackRequest$credentials_release, cancellationSignal, outcomeReceiver);
    }

    public abstract void onClearCredentialStateRequest(ProviderClearCredentialStateRequest request, CancellationSignal cancellationSignal, OutcomeReceiver callback);

    public final void setLastClearRequest(ProviderClearCredentialStateRequest providerClearCredentialStateRequest) {
        this.lastClearRequest = providerClearCredentialStateRequest;
    }

    public final void setLastCreateRequest(BeginCreateCredentialRequest beginCreateCredentialRequest) {
        this.lastCreateRequest = beginCreateCredentialRequest;
    }

    public final void setLastGetRequest(BeginGetCredentialRequest beginGetCredentialRequest) {
        this.lastGetRequest = beginGetCredentialRequest;
    }

    public final void setTestMode(boolean z) {
        this.isTestMode = z;
    }
}
