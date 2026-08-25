package androidx.credentials;

import android.os.Bundle;
import androidx.credentials.internal.FrameworkClassParsingException;
import androidx.credentials.internal.RequestValidationHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import q7.n;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000b"}, d2 = {"Landroidx/credentials/PublicKeyCredential;", "Landroidx/credentials/Credential;", "authenticationResponseJson", "", "(Ljava/lang/String;)V", "data", "Landroid/os/Bundle;", "(Ljava/lang/String;Landroid/os/Bundle;)V", "getAuthenticationResponseJson", "()Ljava/lang/String;", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PublicKeyCredential extends Credential {
    public static final String BUNDLE_KEY_AUTHENTICATION_RESPONSE_JSON = "androidx.credentials.BUNDLE_KEY_AUTHENTICATION_RESPONSE_JSON";
    public static final String BUNDLE_KEY_SUBTYPE = "androidx.credentials.BUNDLE_KEY_SUBTYPE";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String TYPE_PUBLIC_KEY_CREDENTIAL = "androidx.credentials.TYPE_PUBLIC_KEY_CREDENTIAL";
    private final String authenticationResponseJson;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0001¢\u0006\u0002\b\u000bJ\u0015\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u0004H\u0001¢\u0006\u0002\b\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/credentials/PublicKeyCredential$Companion;", "", "()V", "BUNDLE_KEY_AUTHENTICATION_RESPONSE_JSON", "", "BUNDLE_KEY_SUBTYPE", "TYPE_PUBLIC_KEY_CREDENTIAL", "createFrom", "Landroidx/credentials/PublicKeyCredential;", "data", "Landroid/os/Bundle;", "createFrom$credentials_release", "toBundle", "authenticationResponseJson", "toBundle$credentials_release", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        @n
        public final PublicKeyCredential createFrom$credentials_release(Bundle data) throws FrameworkClassParsingException {
            try {
                return new PublicKeyCredential(data.getString(PublicKeyCredential.BUNDLE_KEY_AUTHENTICATION_RESPONSE_JSON), data, null);
            } catch (Exception unused) {
                throw new FrameworkClassParsingException();
            }
        }

        @n
        public final Bundle toBundle$credentials_release(String authenticationResponseJson) {
            Bundle bundle = new Bundle();
            bundle.putString(PublicKeyCredential.BUNDLE_KEY_AUTHENTICATION_RESPONSE_JSON, authenticationResponseJson);
            return bundle;
        }

        private Companion() {
        }
    }

    public /* synthetic */ PublicKeyCredential(String str, Bundle bundle, h hVar) {
        this(str, bundle);
    }

    public final String getAuthenticationResponseJson() {
        return this.authenticationResponseJson;
    }

    private PublicKeyCredential(String str, Bundle bundle) {
        super(TYPE_PUBLIC_KEY_CREDENTIAL, bundle);
        this.authenticationResponseJson = str;
        if (!RequestValidationHelper.INSTANCE.isValidJSON(str)) {
            throw new IllegalArgumentException("authenticationResponseJson must not be empty, and must be a valid JSON");
        }
    }

    public PublicKeyCredential(String str) {
        this(str, INSTANCE.toBundle$credentials_release(str));
    }
}
