package androidx.credentials;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.CancellationSignal;
import androidx.credentials.CredentialManager;
import androidx.credentials.PrepareGetCredentialResponse;
import androidx.credentials.exceptions.ClearCredentialException;
import androidx.credentials.exceptions.ClearCredentialProviderConfigurationException;
import androidx.credentials.exceptions.CreateCredentialException;
import androidx.credentials.exceptions.CreateCredentialProviderConfigurationException;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.exceptions.GetCredentialProviderConfigurationException;
import androidx.media3.common.C;
import java.util.concurrent.Executor;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u0000 '2\u00020\u0001:\u0001'B\u0011\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005JE\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\n2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012JE\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00132\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\n2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fH\u0017¢\u0006\u0004\b\u0011\u0010\u0015J=\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\n2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u000e0\fH\u0017¢\u0006\u0004\b\u0017\u0010\u0018JE\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00192\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\n2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b0\fH\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ?\u0010!\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\u001e2\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\n2\u0014\u0010\u000f\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0012\u0004\u0012\u00020 0\fH\u0016¢\u0006\u0004\b!\u0010\"J\u000f\u0010$\u001a\u00020#H\u0017¢\u0006\u0004\b$\u0010%R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010&¨\u0006("}, d2 = {"Landroidx/credentials/CredentialManagerImpl;", "Landroidx/credentials/CredentialManager;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroidx/credentials/GetCredentialRequest;", "request", "Landroid/os/CancellationSignal;", "cancellationSignal", "Ljava/util/concurrent/Executor;", "executor", "Landroidx/credentials/CredentialManagerCallback;", "Landroidx/credentials/GetCredentialResponse;", "Landroidx/credentials/exceptions/GetCredentialException;", "callback", "Lx6/t0;", "getCredentialAsync", "(Landroid/content/Context;Landroidx/credentials/GetCredentialRequest;Landroid/os/CancellationSignal;Ljava/util/concurrent/Executor;Landroidx/credentials/CredentialManagerCallback;)V", "Landroidx/credentials/PrepareGetCredentialResponse$PendingGetCredentialHandle;", "pendingGetCredentialHandle", "(Landroid/content/Context;Landroidx/credentials/PrepareGetCredentialResponse$PendingGetCredentialHandle;Landroid/os/CancellationSignal;Ljava/util/concurrent/Executor;Landroidx/credentials/CredentialManagerCallback;)V", "Landroidx/credentials/PrepareGetCredentialResponse;", "prepareGetCredentialAsync", "(Landroidx/credentials/GetCredentialRequest;Landroid/os/CancellationSignal;Ljava/util/concurrent/Executor;Landroidx/credentials/CredentialManagerCallback;)V", "Landroidx/credentials/CreateCredentialRequest;", "Landroidx/credentials/CreateCredentialResponse;", "Landroidx/credentials/exceptions/CreateCredentialException;", "createCredentialAsync", "(Landroid/content/Context;Landroidx/credentials/CreateCredentialRequest;Landroid/os/CancellationSignal;Ljava/util/concurrent/Executor;Landroidx/credentials/CredentialManagerCallback;)V", "Landroidx/credentials/ClearCredentialStateRequest;", "Ljava/lang/Void;", "Landroidx/credentials/exceptions/ClearCredentialException;", "clearCredentialStateAsync", "(Landroidx/credentials/ClearCredentialStateRequest;Landroid/os/CancellationSignal;Ljava/util/concurrent/Executor;Landroidx/credentials/CredentialManagerCallback;)V", "Landroid/app/PendingIntent;", "createSettingsPendingIntent", "()Landroid/app/PendingIntent;", "Landroid/content/Context;", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CredentialManagerImpl implements CredentialManager {
    private static final String INTENT_ACTION_FOR_CREDENTIAL_PROVIDER_SETTINGS = "android.settings.CREDENTIAL_PROVIDER";
    private final Context context;

    public CredentialManagerImpl(Context context) {
        this.context = context;
    }

    @Override // androidx.credentials.CredentialManager
    public final /* synthetic */ Object clearCredentialState(ClearCredentialStateRequest clearCredentialStateRequest, d7.d dVar) {
        return CredentialManager.CC.a(this, clearCredentialStateRequest, dVar);
    }

    @Override // androidx.credentials.CredentialManager
    public void clearCredentialStateAsync(ClearCredentialStateRequest request, CancellationSignal cancellationSignal, Executor executor, CredentialManagerCallback<Void, ClearCredentialException> callback) {
        CredentialProvider bestAvailableProvider$default = CredentialProviderFactory.getBestAvailableProvider$default(new CredentialProviderFactory(this.context), false, 1, null);
        if (bestAvailableProvider$default == null) {
            callback.onError(new ClearCredentialProviderConfigurationException("clearCredentialStateAsync no provider dependencies found - please ensure the desired provider dependencies are added"));
        } else {
            bestAvailableProvider$default.onClearCredential(request, cancellationSignal, executor, callback);
        }
    }

    @Override // androidx.credentials.CredentialManager
    public final /* synthetic */ Object createCredential(Context context, CreateCredentialRequest createCredentialRequest, d7.d dVar) {
        return CredentialManager.CC.b(this, context, createCredentialRequest, dVar);
    }

    @Override // androidx.credentials.CredentialManager
    public void createCredentialAsync(Context context, CreateCredentialRequest request, CancellationSignal cancellationSignal, Executor executor, CredentialManagerCallback<CreateCredentialResponse, CreateCredentialException> callback) {
        CredentialProvider bestAvailableProvider$default = CredentialProviderFactory.getBestAvailableProvider$default(new CredentialProviderFactory(this.context), false, 1, null);
        if (bestAvailableProvider$default == null) {
            callback.onError(new CreateCredentialProviderConfigurationException("createCredentialAsync no provider dependencies found - please ensure the desired provider dependencies are added"));
        } else {
            bestAvailableProvider$default.onCreateCredential(context, request, cancellationSignal, executor, callback);
        }
    }

    @Override // androidx.credentials.CredentialManager
    public PendingIntent createSettingsPendingIntent() {
        Intent intent = new Intent(INTENT_ACTION_FOR_CREDENTIAL_PROVIDER_SETTINGS);
        intent.setData(Uri.parse("package:" + this.context.getPackageName()));
        return PendingIntent.getActivity(this.context, 0, intent, C.BUFFER_FLAG_NOT_DEPENDED_ON);
    }

    @Override // androidx.credentials.CredentialManager
    public final /* synthetic */ Object getCredential(Context context, GetCredentialRequest getCredentialRequest, d7.d dVar) {
        return CredentialManager.CC.c(this, context, getCredentialRequest, dVar);
    }

    @Override // androidx.credentials.CredentialManager
    public void getCredentialAsync(Context context, GetCredentialRequest request, CancellationSignal cancellationSignal, Executor executor, CredentialManagerCallback<GetCredentialResponse, GetCredentialException> callback) {
        CredentialProvider bestAvailableProvider$default = CredentialProviderFactory.getBestAvailableProvider$default(new CredentialProviderFactory(context), false, 1, null);
        if (bestAvailableProvider$default == null) {
            callback.onError(new GetCredentialProviderConfigurationException("getCredentialAsync no provider dependencies found - please ensure the desired provider dependencies are added"));
        } else {
            bestAvailableProvider$default.onGetCredential(context, request, cancellationSignal, executor, callback);
        }
    }

    @Override // androidx.credentials.CredentialManager
    public final /* synthetic */ Object prepareGetCredential(GetCredentialRequest getCredentialRequest, d7.d dVar) {
        return CredentialManager.CC.e(this, getCredentialRequest, dVar);
    }

    @Override // androidx.credentials.CredentialManager
    public void prepareGetCredentialAsync(GetCredentialRequest request, CancellationSignal cancellationSignal, Executor executor, CredentialManagerCallback<PrepareGetCredentialResponse, GetCredentialException> callback) {
        CredentialProvider bestAvailableProvider = new CredentialProviderFactory(this.context).getBestAvailableProvider(false);
        if (bestAvailableProvider == null) {
            callback.onError(new GetCredentialProviderConfigurationException("No Credential Manager provider found"));
        } else {
            bestAvailableProvider.onPrepareCredential(request, cancellationSignal, executor, callback);
        }
    }

    @Override // androidx.credentials.CredentialManager
    public final /* synthetic */ Object getCredential(Context context, PrepareGetCredentialResponse.PendingGetCredentialHandle pendingGetCredentialHandle, d7.d dVar) {
        return CredentialManager.CC.d(this, context, pendingGetCredentialHandle, dVar);
    }

    @Override // androidx.credentials.CredentialManager
    public void getCredentialAsync(Context context, PrepareGetCredentialResponse.PendingGetCredentialHandle pendingGetCredentialHandle, CancellationSignal cancellationSignal, Executor executor, CredentialManagerCallback<GetCredentialResponse, GetCredentialException> callback) {
        CredentialProvider bestAvailableProvider = new CredentialProviderFactory(context).getBestAvailableProvider(false);
        if (bestAvailableProvider == null) {
            callback.onError(new GetCredentialProviderConfigurationException("No Credential Manager provider found"));
        } else {
            bestAvailableProvider.onGetCredential(context, pendingGetCredentialHandle, cancellationSignal, executor, callback);
        }
    }
}
