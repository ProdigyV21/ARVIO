package androidx.credentials.provider;

import android.os.Bundle;
import androidx.credentials.PasswordCredential;
import androidx.credentials.internal.FrameworkClassParsingException;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import q7.n;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006¨\u0006\b"}, d2 = {"Landroidx/credentials/provider/BeginCreatePasswordCredentialRequest;", "Landroidx/credentials/provider/BeginCreateCredentialRequest;", "callingAppInfo", "Landroidx/credentials/provider/CallingAppInfo;", "candidateQueryData", "Landroid/os/Bundle;", "(Landroidx/credentials/provider/CallingAppInfo;Landroid/os/Bundle;)V", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class BeginCreatePasswordCredentialRequest extends BeginCreateCredentialRequest {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001f\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0001¢\u0006\u0002\b\t¨\u0006\n"}, d2 = {"Landroidx/credentials/provider/BeginCreatePasswordCredentialRequest$Companion;", "", "()V", "createFrom", "Landroidx/credentials/provider/BeginCreatePasswordCredentialRequest;", "data", "Landroid/os/Bundle;", "callingAppInfo", "Landroidx/credentials/provider/CallingAppInfo;", "createFrom$credentials_release", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        @n
        public final BeginCreatePasswordCredentialRequest createFrom$credentials_release(Bundle data, CallingAppInfo callingAppInfo) throws FrameworkClassParsingException {
            try {
                return new BeginCreatePasswordCredentialRequest(callingAppInfo, data);
            } catch (Exception unused) {
                throw new FrameworkClassParsingException();
            }
        }

        private Companion() {
        }
    }

    public BeginCreatePasswordCredentialRequest(CallingAppInfo callingAppInfo, Bundle bundle) {
        super(PasswordCredential.TYPE_PASSWORD_CREDENTIAL, bundle, callingAppInfo);
    }
}
