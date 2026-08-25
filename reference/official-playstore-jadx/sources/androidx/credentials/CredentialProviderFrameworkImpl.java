package androidx.credentials;

import android.content.Context;
import android.credentials.ClearCredentialStateException;
import android.credentials.CreateCredentialRequest;
import android.credentials.CredentialOption;
import android.credentials.GetCredentialRequest;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.OutcomeReceiver;
import android.util.Log;
import androidx.credentials.PrepareGetCredentialResponse;
import androidx.credentials.exceptions.ClearCredentialException;
import androidx.credentials.exceptions.ClearCredentialUnknownException;
import androidx.credentials.exceptions.ClearCredentialUnsupportedException;
import androidx.credentials.exceptions.CreateCredentialCancellationException;
import androidx.credentials.exceptions.CreateCredentialCustomException;
import androidx.credentials.exceptions.CreateCredentialException;
import androidx.credentials.exceptions.CreateCredentialInterruptedException;
import androidx.credentials.exceptions.CreateCredentialNoCreateOptionException;
import androidx.credentials.exceptions.CreateCredentialUnknownException;
import androidx.credentials.exceptions.CreateCredentialUnsupportedException;
import androidx.credentials.exceptions.GetCredentialCancellationException;
import androidx.credentials.exceptions.GetCredentialCustomException;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.exceptions.GetCredentialInterruptedException;
import androidx.credentials.exceptions.GetCredentialUnknownException;
import androidx.credentials.exceptions.GetCredentialUnsupportedException;
import androidx.credentials.exceptions.NoCredentialException;
import androidx.credentials.exceptions.publickeycredential.CreatePublicKeyCredentialException;
import androidx.credentials.exceptions.publickeycredential.GetPublicKeyCredentialException;
import androidx.credentials.internal.FrameworkImplHelper;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.r;
import kotlin.text.u;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000Â\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000 O2\u00020\u0001:\u0001OB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\n\u001a\u00020\t2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0013\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0011H\u0003¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0017\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\u0015H\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u001f\u0010\u001a\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u0019H\u0003¢\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001d\u001a\u00020\u001cH\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ=\u0010'\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00152\b\u0010 \u001a\u0004\u0018\u00010\u001f2\u0006\u0010\"\u001a\u00020!2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%0#H\u0016¢\u0006\u0004\b'\u0010(JE\u0010,\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010*\u001a\u00020)2\b\u0010 \u001a\u0004\u0018\u00010\u001f2\u0006\u0010\"\u001a\u00020!2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020%0#H\u0016¢\u0006\u0004\b,\u0010-JE\u0010,\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u00152\b\u0010 \u001a\u0004\u0018\u00010\u001f2\u0006\u0010\"\u001a\u00020!2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020%0#H\u0016¢\u0006\u0004\b,\u0010.JE\u00101\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\f2\b\u0010 \u001a\u0004\u0018\u00010\u001f2\u0006\u0010\"\u001a\u00020!2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u0002000#H\u0016¢\u0006\u0004\b1\u00102J\u0017\u00107\u001a\u00020%2\u0006\u00104\u001a\u000203H\u0000¢\u0006\u0004\b5\u00106J\u0017\u0010;\u001a\u0002002\u0006\u00104\u001a\u000208H\u0000¢\u0006\u0004\b9\u0010:J\u0017\u0010@\u001a\u00020+2\u0006\u0010=\u001a\u00020<H\u0000¢\u0006\u0004\b>\u0010?J\u0017\u0010D\u001a\u00020$2\u0006\u0010=\u001a\u00020AH\u0000¢\u0006\u0004\bB\u0010CJ\u000f\u0010E\u001a\u00020\tH\u0016¢\u0006\u0004\bE\u0010FJ?\u0010J\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020G2\b\u0010 \u001a\u0004\u0018\u00010\u001f2\u0006\u0010\"\u001a\u00020!2\u0014\u0010&\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010H\u0012\u0004\u0012\u00020I0#H\u0016¢\u0006\u0004\bJ\u0010KR\u0016\u0010M\u001a\u0004\u0018\u00010L8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bM\u0010N¨\u0006P"}, d2 = {"Landroidx/credentials/CredentialProviderFrameworkImpl;", "Landroidx/credentials/CredentialProvider;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Lkotlin/Function0;", "Lx6/t0;", "handleNullCredMan", "", "isCredmanDisabled", "(Lr7/a;)Z", "Landroidx/credentials/CreateCredentialRequest;", "request", "Landroid/credentials/CreateCredentialRequest;", "convertCreateRequestToFrameworkClass", "(Landroidx/credentials/CreateCredentialRequest;Landroid/content/Context;)Landroid/credentials/CreateCredentialRequest;", "Landroid/credentials/CreateCredentialRequest$Builder;", "builder", "setOriginForCreateRequest", "(Landroidx/credentials/CreateCredentialRequest;Landroid/credentials/CreateCredentialRequest$Builder;)V", "Landroidx/credentials/GetCredentialRequest;", "Landroid/credentials/GetCredentialRequest;", "convertGetRequestToFrameworkClass", "(Landroidx/credentials/GetCredentialRequest;)Landroid/credentials/GetCredentialRequest;", "Landroid/credentials/GetCredentialRequest$Builder;", "setOriginForGetRequest", "(Landroidx/credentials/GetCredentialRequest;Landroid/credentials/GetCredentialRequest$Builder;)V", "Landroid/credentials/ClearCredentialStateRequest;", "createFrameworkClearCredentialRequest", "()Landroid/credentials/ClearCredentialStateRequest;", "Landroid/os/CancellationSignal;", "cancellationSignal", "Ljava/util/concurrent/Executor;", "executor", "Landroidx/credentials/CredentialManagerCallback;", "Landroidx/credentials/PrepareGetCredentialResponse;", "Landroidx/credentials/exceptions/GetCredentialException;", "callback", "onPrepareCredential", "(Landroidx/credentials/GetCredentialRequest;Landroid/os/CancellationSignal;Ljava/util/concurrent/Executor;Landroidx/credentials/CredentialManagerCallback;)V", "Landroidx/credentials/PrepareGetCredentialResponse$PendingGetCredentialHandle;", "pendingGetCredentialHandle", "Landroidx/credentials/GetCredentialResponse;", "onGetCredential", "(Landroid/content/Context;Landroidx/credentials/PrepareGetCredentialResponse$PendingGetCredentialHandle;Landroid/os/CancellationSignal;Ljava/util/concurrent/Executor;Landroidx/credentials/CredentialManagerCallback;)V", "(Landroid/content/Context;Landroidx/credentials/GetCredentialRequest;Landroid/os/CancellationSignal;Ljava/util/concurrent/Executor;Landroidx/credentials/CredentialManagerCallback;)V", "Landroidx/credentials/CreateCredentialResponse;", "Landroidx/credentials/exceptions/CreateCredentialException;", "onCreateCredential", "(Landroid/content/Context;Landroidx/credentials/CreateCredentialRequest;Landroid/os/CancellationSignal;Ljava/util/concurrent/Executor;Landroidx/credentials/CredentialManagerCallback;)V", "Landroid/credentials/GetCredentialException;", "error", "convertToJetpackGetException$credentials_release", "(Landroid/credentials/GetCredentialException;)Landroidx/credentials/exceptions/GetCredentialException;", "convertToJetpackGetException", "Landroid/credentials/CreateCredentialException;", "convertToJetpackCreateException$credentials_release", "(Landroid/credentials/CreateCredentialException;)Landroidx/credentials/exceptions/CreateCredentialException;", "convertToJetpackCreateException", "Landroid/credentials/GetCredentialResponse;", "response", "convertGetResponseToJetpackClass$credentials_release", "(Landroid/credentials/GetCredentialResponse;)Landroidx/credentials/GetCredentialResponse;", "convertGetResponseToJetpackClass", "Landroid/credentials/PrepareGetCredentialResponse;", "convertPrepareGetResponseToJetpackClass$credentials_release", "(Landroid/credentials/PrepareGetCredentialResponse;)Landroidx/credentials/PrepareGetCredentialResponse;", "convertPrepareGetResponseToJetpackClass", "isAvailableOnDevice", "()Z", "Landroidx/credentials/ClearCredentialStateRequest;", "Ljava/lang/Void;", "Landroidx/credentials/exceptions/ClearCredentialException;", "onClearCredential", "(Landroidx/credentials/ClearCredentialStateRequest;Landroid/os/CancellationSignal;Ljava/util/concurrent/Executor;Landroidx/credentials/CredentialManagerCallback;)V", "Landroid/credentials/CredentialManager;", "credentialManager", "Landroid/credentials/CredentialManager;", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CredentialProviderFrameworkImpl implements CredentialProvider {
    private static final String CREATE_DOM_EXCEPTION_PREFIX = "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION";
    private static final Companion Companion = new Companion(null);
    private static final String GET_DOM_EXCEPTION_PREFIX = "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION";
    private static final String TAG = "CredManProvService";
    private final android.credentials.CredentialManager credentialManager;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Landroidx/credentials/CredentialProviderFrameworkImpl$Companion;", "", "()V", "CREATE_DOM_EXCEPTION_PREFIX", "", "GET_DOM_EXCEPTION_PREFIX", "TAG", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: androidx.credentials.CredentialProviderFrameworkImpl$onClearCredential$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements r7.a<t0> {
        final /* synthetic */ CredentialManagerCallback<Void, ClearCredentialException> $callback;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(CredentialManagerCallback<Void, ClearCredentialException> credentialManagerCallback) {
            super(0);
            this.$callback = credentialManagerCallback;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m5940invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m5940invoke() {
            this.$callback.onError(new ClearCredentialUnsupportedException("Your device doesn't support credential manager"));
        }
    }

    /* JADX INFO: renamed from: androidx.credentials.CredentialProviderFrameworkImpl$onCreateCredential$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C06321 extends r implements r7.a<t0> {
        final /* synthetic */ CredentialManagerCallback<CreateCredentialResponse, CreateCredentialException> $callback;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06321(CredentialManagerCallback<CreateCredentialResponse, CreateCredentialException> credentialManagerCallback) {
            super(0);
            this.$callback = credentialManagerCallback;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m5941invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m5941invoke() {
            this.$callback.onError(new CreateCredentialUnsupportedException("Your device doesn't support credential manager"));
        }
    }

    /* JADX INFO: renamed from: androidx.credentials.CredentialProviderFrameworkImpl$onGetCredential$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C06331 extends r implements r7.a<t0> {
        final /* synthetic */ CredentialManagerCallback<GetCredentialResponse, GetCredentialException> $callback;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06331(CredentialManagerCallback<GetCredentialResponse, GetCredentialException> credentialManagerCallback) {
            super(0);
            this.$callback = credentialManagerCallback;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m5942invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m5942invoke() {
            this.$callback.onError(new GetCredentialUnsupportedException("Your device doesn't support credential manager"));
        }
    }

    /* JADX INFO: renamed from: androidx.credentials.CredentialProviderFrameworkImpl$onGetCredential$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements r7.a<t0> {
        final /* synthetic */ CredentialManagerCallback<GetCredentialResponse, GetCredentialException> $callback;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(CredentialManagerCallback<GetCredentialResponse, GetCredentialException> credentialManagerCallback) {
            super(0);
            this.$callback = credentialManagerCallback;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m5943invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m5943invoke() {
            this.$callback.onError(new GetCredentialUnsupportedException("Your device doesn't support credential manager"));
        }
    }

    /* JADX INFO: renamed from: androidx.credentials.CredentialProviderFrameworkImpl$onPrepareCredential$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C06341 extends r implements r7.a<t0> {
        final /* synthetic */ CredentialManagerCallback<PrepareGetCredentialResponse, GetCredentialException> $callback;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06341(CredentialManagerCallback<PrepareGetCredentialResponse, GetCredentialException> credentialManagerCallback) {
            super(0);
            this.$callback = credentialManagerCallback;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m5944invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m5944invoke() {
            this.$callback.onError(new GetCredentialUnsupportedException("Your device doesn't support credential manager"));
        }
    }

    public CredentialProviderFrameworkImpl(Context context) {
        this.credentialManager = (android.credentials.CredentialManager) context.getSystemService("credential");
    }

    private final android.credentials.CreateCredentialRequest convertCreateRequestToFrameworkClass(CreateCredentialRequest request, Context context) {
        CreateCredentialRequest.Builder alwaysSendAppInfoToProvider = new CreateCredentialRequest.Builder(request.getType(), FrameworkImplHelper.INSTANCE.getFinalCreateCredentialData(request, context), request.getCandidateQueryData()).setIsSystemProviderRequired(request.getIsSystemProviderRequired()).setAlwaysSendAppInfoToProvider(true);
        setOriginForCreateRequest(request, alwaysSendAppInfoToProvider);
        return alwaysSendAppInfoToProvider.build();
    }

    private final android.credentials.GetCredentialRequest convertGetRequestToFrameworkClass(GetCredentialRequest request) {
        GetCredentialRequest.Builder builder = new GetCredentialRequest.Builder(GetCredentialRequest.INSTANCE.toRequestDataBundle(request));
        for (CredentialOption credentialOption : request.getCredentialOptions()) {
            builder.addCredentialOption(new CredentialOption.Builder(credentialOption.getType(), credentialOption.getRequestData(), credentialOption.getCandidateQueryData()).setIsSystemProviderRequired(credentialOption.getIsSystemProviderRequired()).setAllowedProviders(credentialOption.getAllowedProviders()).build());
        }
        setOriginForGetRequest(request, builder);
        return builder.build();
    }

    private final android.credentials.ClearCredentialStateRequest createFrameworkClearCredentialRequest() {
        c.k();
        return c.b(new Bundle());
    }

    private final boolean isCredmanDisabled(r7.a<t0> handleNullCredMan) {
        if (this.credentialManager != null) {
            return false;
        }
        handleNullCredMan.invoke();
        return true;
    }

    private final void setOriginForCreateRequest(CreateCredentialRequest request, CreateCredentialRequest.Builder builder) {
        if (request.getOrigin() != null) {
            builder.setOrigin(request.getOrigin());
        }
    }

    private final void setOriginForGetRequest(GetCredentialRequest request, GetCredentialRequest.Builder builder) {
        if (request.getOrigin() != null) {
            builder.setOrigin(request.getOrigin());
        }
    }

    public final GetCredentialResponse convertGetResponseToJetpackClass$credentials_release(android.credentials.GetCredentialResponse response) {
        android.credentials.Credential credential = response.getCredential();
        return new GetCredentialResponse(Credential.INSTANCE.createFrom(credential.getType(), credential.getData()));
    }

    public final PrepareGetCredentialResponse convertPrepareGetResponseToJetpackClass$credentials_release(android.credentials.PrepareGetCredentialResponse response) {
        return new PrepareGetCredentialResponse.Builder().setFrameworkResponse(response).setPendingGetCredentialHandle(new PrepareGetCredentialResponse.PendingGetCredentialHandle(response.getPendingGetCredentialHandle())).build();
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final CreateCredentialException convertToJetpackCreateException$credentials_release(android.credentials.CreateCredentialException error) {
        String type = error.getType();
        switch (type.hashCode()) {
            case -2055374133:
                if (type.equals(CreateCredentialCancellationException.TYPE_CREATE_CREDENTIAL_CANCELLATION_EXCEPTION)) {
                    return new CreateCredentialCancellationException(error.getMessage());
                }
                break;
            case 1316905704:
                if (type.equals(CreateCredentialUnknownException.TYPE_CREATE_CREDENTIAL_UNKNOWN_EXCEPTION)) {
                    return new CreateCredentialUnknownException(error.getMessage());
                }
                break;
            case 2092588512:
                if (type.equals(CreateCredentialInterruptedException.TYPE_CREATE_CREDENTIAL_INTERRUPTED_EXCEPTION)) {
                    return new CreateCredentialInterruptedException(error.getMessage());
                }
                break;
            case 2131915191:
                if (type.equals(CreateCredentialNoCreateOptionException.TYPE_CREATE_CREDENTIAL_NO_CREATE_OPTION)) {
                    return new CreateCredentialNoCreateOptionException(error.getMessage());
                }
                break;
        }
        return u.P(error.getType(), "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION", false) ? CreatePublicKeyCredentialException.INSTANCE.createFrom(error.getType(), error.getMessage()) : new CreateCredentialCustomException(error.getType(), error.getMessage());
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final GetCredentialException convertToJetpackGetException$credentials_release(android.credentials.GetCredentialException error) {
        String type = error.getType();
        switch (type.hashCode()) {
            case -781118336:
                if (type.equals(GetCredentialUnknownException.TYPE_GET_CREDENTIAL_UNKNOWN_EXCEPTION)) {
                    return new GetCredentialUnknownException(error.getMessage());
                }
                break;
            case -45448328:
                if (type.equals(GetCredentialInterruptedException.TYPE_GET_CREDENTIAL_INTERRUPTED_EXCEPTION)) {
                    return new GetCredentialInterruptedException(error.getMessage());
                }
                break;
            case 580557411:
                if (type.equals(GetCredentialCancellationException.TYPE_GET_CREDENTIAL_CANCELLATION_EXCEPTION)) {
                    return new GetCredentialCancellationException(error.getMessage());
                }
                break;
            case 627896683:
                if (type.equals(NoCredentialException.TYPE_FRAMEWORK_TYPE_NO_CREDENTIAL)) {
                    return new NoCredentialException(error.getMessage());
                }
                break;
        }
        return u.P(error.getType(), "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION", false) ? GetPublicKeyCredentialException.INSTANCE.createFrom(error.getType(), error.getMessage()) : new GetCredentialCustomException(error.getType(), error.getMessage());
    }

    @Override // androidx.credentials.CredentialProvider
    public boolean isAvailableOnDevice() {
        return Build.VERSION.SDK_INT >= 34 && this.credentialManager != null;
    }

    @Override // androidx.credentials.CredentialProvider
    public void onClearCredential(ClearCredentialStateRequest request, CancellationSignal cancellationSignal, Executor executor, final CredentialManagerCallback<Void, ClearCredentialException> callback) {
        Log.i(TAG, "In CredentialProviderFrameworkImpl onClearCredential");
        if (isCredmanDisabled(new AnonymousClass1(callback))) {
            return;
        }
        this.credentialManager.clearCredentialState(createFrameworkClearCredentialRequest(), cancellationSignal, executor, new OutcomeReceiver() { // from class: androidx.credentials.CredentialProviderFrameworkImpl$onClearCredential$outcome$1
            public void onError(ClearCredentialStateException error) {
                Log.i("CredManProvService", "ClearCredentialStateException error returned from framework");
                callback.onError(new ClearCredentialUnknownException(null, 1, null));
            }

            public void onResult(Void response) {
                Log.i("CredManProvService", "Clear result returned from framework: ");
                callback.onResult(response);
            }
        });
    }

    @Override // androidx.credentials.CredentialProvider
    public void onCreateCredential(Context context, final CreateCredentialRequest request, CancellationSignal cancellationSignal, Executor executor, final CredentialManagerCallback<CreateCredentialResponse, CreateCredentialException> callback) {
        if (isCredmanDisabled(new C06321(callback))) {
            return;
        }
        this.credentialManager.createCredential(context, convertCreateRequestToFrameworkClass(request, context), cancellationSignal, executor, new OutcomeReceiver() { // from class: androidx.credentials.CredentialProviderFrameworkImpl$onCreateCredential$outcome$1
            public /* bridge */ /* synthetic */ void onError(Throwable th) {
                onError(androidx.compose.ui.graphics.c.b(th));
            }

            public /* bridge */ /* synthetic */ void onResult(Object obj) {
                onResult(androidx.compose.ui.graphics.c.c(obj));
            }

            public void onError(android.credentials.CreateCredentialException error) {
                Log.i("CredManProvService", "CreateCredentialResponse error returned from framework");
                callback.onError(this.convertToJetpackCreateException$credentials_release(error));
            }

            public void onResult(android.credentials.CreateCredentialResponse response) {
                Log.i("CredManProvService", "Create Result returned from framework: ");
                callback.onResult(CreateCredentialResponse.INSTANCE.createFrom(request.getType(), response.getData()));
            }
        });
    }

    @Override // androidx.credentials.CredentialProvider
    public void onGetCredential(Context context, PrepareGetCredentialResponse.PendingGetCredentialHandle pendingGetCredentialHandle, CancellationSignal cancellationSignal, Executor executor, final CredentialManagerCallback<GetCredentialResponse, GetCredentialException> callback) {
        if (isCredmanDisabled(new C06331(callback))) {
            return;
        }
        this.credentialManager.getCredential(context, pendingGetCredentialHandle.getFrameworkHandle(), cancellationSignal, executor, new OutcomeReceiver() { // from class: androidx.credentials.CredentialProviderFrameworkImpl$onGetCredential$outcome$1
            public /* bridge */ /* synthetic */ void onError(Throwable th) {
                onError(d.b(th));
            }

            public /* bridge */ /* synthetic */ void onResult(Object obj) {
                onResult(androidx.compose.ui.graphics.c.d(obj));
            }

            public void onError(android.credentials.GetCredentialException error) {
                callback.onError(this.convertToJetpackGetException$credentials_release(error));
            }

            public void onResult(android.credentials.GetCredentialResponse response) {
                callback.onResult(this.convertGetResponseToJetpackClass$credentials_release(response));
            }
        });
    }

    @Override // androidx.credentials.CredentialProvider
    public void onPrepareCredential(GetCredentialRequest request, CancellationSignal cancellationSignal, Executor executor, final CredentialManagerCallback<PrepareGetCredentialResponse, GetCredentialException> callback) {
        if (isCredmanDisabled(new C06341(callback))) {
            return;
        }
        this.credentialManager.prepareGetCredential(convertGetRequestToFrameworkClass(request), cancellationSignal, executor, new OutcomeReceiver() { // from class: androidx.credentials.CredentialProviderFrameworkImpl$onPrepareCredential$outcome$1
            public /* bridge */ /* synthetic */ void onError(Throwable th) {
                onError(d.b(th));
            }

            public void onError(android.credentials.GetCredentialException error) {
                callback.onError(this.convertToJetpackGetException$credentials_release(error));
            }

            public void onResult(android.credentials.PrepareGetCredentialResponse response) {
                callback.onResult(this.convertPrepareGetResponseToJetpackClass$credentials_release(response));
            }
        });
    }

    @Override // androidx.credentials.CredentialProvider
    public void onGetCredential(Context context, GetCredentialRequest request, CancellationSignal cancellationSignal, Executor executor, final CredentialManagerCallback<GetCredentialResponse, GetCredentialException> callback) {
        if (isCredmanDisabled(new AnonymousClass2(callback))) {
            return;
        }
        this.credentialManager.getCredential(context, convertGetRequestToFrameworkClass(request), cancellationSignal, executor, new OutcomeReceiver() { // from class: androidx.credentials.CredentialProviderFrameworkImpl$onGetCredential$outcome$2
            public /* bridge */ /* synthetic */ void onError(Throwable th) {
                onError(d.b(th));
            }

            public /* bridge */ /* synthetic */ void onResult(Object obj) {
                onResult(androidx.compose.ui.graphics.c.d(obj));
            }

            public void onError(android.credentials.GetCredentialException error) {
                Log.i("CredManProvService", "GetCredentialResponse error returned from framework");
                callback.onError(this.convertToJetpackGetException$credentials_release(error));
            }

            public void onResult(android.credentials.GetCredentialResponse response) {
                Log.i("CredManProvService", "GetCredentialResponse returned from framework");
                callback.onResult(this.convertGetResponseToJetpackClass$credentials_release(response));
            }
        });
    }
}
