package androidx.credentials.provider;

import androidx.credentials.CredentialOption;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import q7.n;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Landroidx/credentials/provider/ProviderGetCredentialRequest;", "", "credentialOptions", "", "Landroidx/credentials/CredentialOption;", "callingAppInfo", "Landroidx/credentials/provider/CallingAppInfo;", "(Ljava/util/List;Landroidx/credentials/provider/CallingAppInfo;)V", "getCallingAppInfo", "()Landroidx/credentials/provider/CallingAppInfo;", "getCredentialOptions", "()Ljava/util/List;", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ProviderGetCredentialRequest {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final CallingAppInfo callingAppInfo;
    private final List<CredentialOption> credentialOptions;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J#\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0001¢\u0006\u0002\b\n¨\u0006\u000b"}, d2 = {"Landroidx/credentials/provider/ProviderGetCredentialRequest$Companion;", "", "()V", "createFrom", "Landroidx/credentials/provider/ProviderGetCredentialRequest;", "options", "", "Landroidx/credentials/CredentialOption;", "callingAppInfo", "Landroidx/credentials/provider/CallingAppInfo;", "createFrom$credentials_release", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        @n
        public final ProviderGetCredentialRequest createFrom$credentials_release(List<? extends CredentialOption> options, CallingAppInfo callingAppInfo) {
            return new ProviderGetCredentialRequest(options, callingAppInfo);
        }

        private Companion() {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ProviderGetCredentialRequest(List<? extends CredentialOption> list, CallingAppInfo callingAppInfo) {
        this.credentialOptions = list;
        this.callingAppInfo = callingAppInfo;
    }

    public final CallingAppInfo getCallingAppInfo() {
        return this.callingAppInfo;
    }

    public final List<CredentialOption> getCredentialOptions() {
        return this.credentialOptions;
    }
}
