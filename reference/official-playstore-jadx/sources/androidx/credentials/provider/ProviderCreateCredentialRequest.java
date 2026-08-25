package androidx.credentials.provider;

import androidx.credentials.CreateCredentialRequest;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/credentials/provider/ProviderCreateCredentialRequest;", "", "callingRequest", "Landroidx/credentials/CreateCredentialRequest;", "callingAppInfo", "Landroidx/credentials/provider/CallingAppInfo;", "(Landroidx/credentials/CreateCredentialRequest;Landroidx/credentials/provider/CallingAppInfo;)V", "getCallingAppInfo", "()Landroidx/credentials/provider/CallingAppInfo;", "getCallingRequest", "()Landroidx/credentials/CreateCredentialRequest;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ProviderCreateCredentialRequest {
    private final CallingAppInfo callingAppInfo;
    private final CreateCredentialRequest callingRequest;

    public ProviderCreateCredentialRequest(CreateCredentialRequest createCredentialRequest, CallingAppInfo callingAppInfo) {
        this.callingRequest = createCredentialRequest;
        this.callingAppInfo = callingAppInfo;
    }

    public final CallingAppInfo getCallingAppInfo() {
        return this.callingAppInfo;
    }

    public final CreateCredentialRequest getCallingRequest() {
        return this.callingRequest;
    }
}
