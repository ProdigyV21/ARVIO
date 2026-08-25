package androidx.credentials.provider;

import android.content.Intent;
import android.service.credentials.CreateCredentialRequest;
import android.service.credentials.GetCredentialRequest;
import android.util.Log;
import androidx.credentials.CreateCredentialResponse;
import androidx.credentials.CredentialOption;
import androidx.credentials.GetCredentialResponse;
import androidx.credentials.c;
import androidx.credentials.exceptions.CreateCredentialException;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.provider.utils.BeginGetCredentialUtil;
import j$.util.Collection;
import j$.util.stream.Collectors;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import q7.n;
import r7.l;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Landroidx/credentials/provider/PendingIntentHandler;", "", "()V", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PendingIntentHandler {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "PendingIntentHandler";

    @Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\fH\u0007¢\u0006\u0004\b\u000f\u0010\u0010J\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0014H\u0007¢\u0006\u0004\b\u0015\u0010\u0016J\u001f\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0017H\u0007¢\u0006\u0004\b\u0018\u0010\u0019J\u001f\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001aH\u0007¢\u0006\u0004\b\u001c\u0010\u001dJ\u001f\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001eH\u0007¢\u0006\u0004\b\u001f\u0010 R\u0014\u0010\"\u001a\u00020!8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\"\u0010#¨\u0006$"}, d2 = {"Landroidx/credentials/provider/PendingIntentHandler$Companion;", "", "<init>", "()V", "Landroid/content/Intent;", "intent", "Landroidx/credentials/provider/ProviderCreateCredentialRequest;", "retrieveProviderCreateCredentialRequest", "(Landroid/content/Intent;)Landroidx/credentials/provider/ProviderCreateCredentialRequest;", "Landroidx/credentials/provider/BeginGetCredentialRequest;", "retrieveBeginGetCredentialRequest", "(Landroid/content/Intent;)Landroidx/credentials/provider/BeginGetCredentialRequest;", "Landroidx/credentials/CreateCredentialResponse;", "response", "Lx6/t0;", "setCreateCredentialResponse", "(Landroid/content/Intent;Landroidx/credentials/CreateCredentialResponse;)V", "Landroidx/credentials/provider/ProviderGetCredentialRequest;", "retrieveProviderGetCredentialRequest", "(Landroid/content/Intent;)Landroidx/credentials/provider/ProviderGetCredentialRequest;", "Landroidx/credentials/GetCredentialResponse;", "setGetCredentialResponse", "(Landroid/content/Intent;Landroidx/credentials/GetCredentialResponse;)V", "Landroidx/credentials/provider/BeginGetCredentialResponse;", "setBeginGetCredentialResponse", "(Landroid/content/Intent;Landroidx/credentials/provider/BeginGetCredentialResponse;)V", "Landroidx/credentials/exceptions/GetCredentialException;", "exception", "setGetCredentialException", "(Landroid/content/Intent;Landroidx/credentials/exceptions/GetCredentialException;)V", "Landroidx/credentials/exceptions/CreateCredentialException;", "setCreateCredentialException", "(Landroid/content/Intent;Landroidx/credentials/exceptions/CreateCredentialException;)V", "", "TAG", "Ljava/lang/String;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final CredentialOption retrieveProviderGetCredentialRequest$lambda$1(l lVar, Object obj) {
            return (CredentialOption) lVar.invoke(obj);
        }

        @n
        public final BeginGetCredentialRequest retrieveBeginGetCredentialRequest(Intent intent) {
            android.service.credentials.BeginGetCredentialRequest beginGetCredentialRequest = (android.service.credentials.BeginGetCredentialRequest) intent.getParcelableExtra("android.service.credentials.extra.BEGIN_GET_CREDENTIAL_REQUEST", android.service.credentials.BeginGetCredentialRequest.class);
            if (beginGetCredentialRequest != null) {
                return BeginGetCredentialUtil.INSTANCE.convertToJetpackRequest$credentials_release(beginGetCredentialRequest);
            }
            return null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @n
        public final ProviderCreateCredentialRequest retrieveProviderCreateCredentialRequest(Intent intent) {
            CreateCredentialRequest createCredentialRequest = (CreateCredentialRequest) intent.getParcelableExtra("android.service.credentials.extra.CREATE_CREDENTIAL_REQUEST", CreateCredentialRequest.class);
            if (createCredentialRequest == 0) {
                Log.i(PendingIntentHandler.TAG, "Request not found in pendingIntent");
                return (ProviderCreateCredentialRequest) createCredentialRequest;
            }
            try {
                return new ProviderCreateCredentialRequest(androidx.credentials.CreateCredentialRequest.INSTANCE.createFrom(createCredentialRequest.getType(), createCredentialRequest.getData(), createCredentialRequest.getData(), false, createCredentialRequest.getCallingAppInfo().getOrigin()), new CallingAppInfo(createCredentialRequest.getCallingAppInfo().getPackageName(), createCredentialRequest.getCallingAppInfo().getSigningInfo(), createCredentialRequest.getCallingAppInfo().getOrigin()));
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }

        @n
        public final ProviderGetCredentialRequest retrieveProviderGetCredentialRequest(Intent intent) {
            GetCredentialRequest getCredentialRequest = (GetCredentialRequest) intent.getParcelableExtra("android.service.credentials.extra.GET_CREDENTIAL_REQUEST", GetCredentialRequest.class);
            if (getCredentialRequest != null) {
                return ProviderGetCredentialRequest.INSTANCE.createFrom$credentials_release((List) Collection.EL.stream(getCredentialRequest.getCredentialOptions()).map(new a(PendingIntentHandler$Companion$retrieveProviderGetCredentialRequest$1.INSTANCE, 0)).collect(Collectors.toList()), new CallingAppInfo(getCredentialRequest.getCallingAppInfo().getPackageName(), getCredentialRequest.getCallingAppInfo().getSigningInfo(), getCredentialRequest.getCallingAppInfo().getOrigin()));
            }
            Log.i(PendingIntentHandler.TAG, "Get request from framework is null");
            return null;
        }

        @n
        public final void setBeginGetCredentialResponse(Intent intent, BeginGetCredentialResponse response) {
            intent.putExtra("android.service.credentials.extra.BEGIN_GET_CREDENTIAL_RESPONSE", BeginGetCredentialUtil.INSTANCE.convertToFrameworkResponse(response));
        }

        @n
        public final void setCreateCredentialException(Intent intent, CreateCredentialException exception) {
            c.l();
            intent.putExtra("android.service.credentials.extra.CREATE_CREDENTIAL_EXCEPTION", c.c(exception.getType(), exception.getMessage()));
        }

        @n
        public final void setCreateCredentialResponse(Intent intent, CreateCredentialResponse response) {
            c.q();
            intent.putExtra("android.service.credentials.extra.CREATE_CREDENTIAL_RESPONSE", c.d(response.getData()));
        }

        @n
        public final void setGetCredentialException(Intent intent, GetCredentialException exception) {
            c.m();
            intent.putExtra("android.service.credentials.extra.GET_CREDENTIAL_EXCEPTION", c.f(exception.getType(), exception.getMessage()));
        }

        @n
        public final void setGetCredentialResponse(Intent intent, GetCredentialResponse response) {
            c.o();
            c.p();
            intent.putExtra("android.service.credentials.extra.GET_CREDENTIAL_RESPONSE", c.g(c.e(response.getCredential().getData(), response.getCredential().getType())));
        }

        private Companion() {
        }
    }

    @n
    public static final BeginGetCredentialRequest retrieveBeginGetCredentialRequest(Intent intent) {
        return INSTANCE.retrieveBeginGetCredentialRequest(intent);
    }

    @n
    public static final ProviderCreateCredentialRequest retrieveProviderCreateCredentialRequest(Intent intent) {
        return INSTANCE.retrieveProviderCreateCredentialRequest(intent);
    }

    @n
    public static final ProviderGetCredentialRequest retrieveProviderGetCredentialRequest(Intent intent) {
        return INSTANCE.retrieveProviderGetCredentialRequest(intent);
    }

    @n
    public static final void setBeginGetCredentialResponse(Intent intent, BeginGetCredentialResponse beginGetCredentialResponse) {
        INSTANCE.setBeginGetCredentialResponse(intent, beginGetCredentialResponse);
    }

    @n
    public static final void setCreateCredentialException(Intent intent, CreateCredentialException createCredentialException) {
        INSTANCE.setCreateCredentialException(intent, createCredentialException);
    }

    @n
    public static final void setCreateCredentialResponse(Intent intent, CreateCredentialResponse createCredentialResponse) {
        INSTANCE.setCreateCredentialResponse(intent, createCredentialResponse);
    }

    @n
    public static final void setGetCredentialException(Intent intent, GetCredentialException getCredentialException) {
        INSTANCE.setGetCredentialException(intent, getCredentialException);
    }

    @n
    public static final void setGetCredentialResponse(Intent intent, GetCredentialResponse getCredentialResponse) {
        INSTANCE.setGetCredentialResponse(intent, getCredentialResponse);
    }
}
