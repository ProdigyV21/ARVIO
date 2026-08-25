package androidx.credentials.exceptions.publickeycredential;

import androidx.credentials.exceptions.GetCredentialCustomException;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.internal.FrameworkClassParsingException;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.text.u;
import q7.n;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\r\n\u0002\b\u0005\b\u0016\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u001b\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0016\u0010\u0002\u001a\u00020\u00038\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Landroidx/credentials/exceptions/publickeycredential/GetPublicKeyCredentialException;", "Landroidx/credentials/exceptions/GetCredentialException;", LinkHeader.Parameters.Type, "", "errorMessage", "", "(Ljava/lang/String;Ljava/lang/CharSequence;)V", "getType", "()Ljava/lang/String;", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class GetPublicKeyCredentialException extends GetCredentialException {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String type;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0007¨\u0006\b"}, d2 = {"Landroidx/credentials/exceptions/publickeycredential/GetPublicKeyCredentialException$Companion;", "", "()V", "createFrom", "Landroidx/credentials/exceptions/GetCredentialException;", LinkHeader.Parameters.Type, "", "msg", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        @n
        public final GetCredentialException createFrom(String type, String msg) {
            try {
                if (u.P(type, GetPublicKeyCredentialDomException.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION, false)) {
                    return GetPublicKeyCredentialDomException.INSTANCE.createFrom(type, msg);
                }
                throw new FrameworkClassParsingException();
            } catch (FrameworkClassParsingException unused) {
                return new GetCredentialCustomException(type, msg);
            }
        }

        private Companion() {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public GetPublicKeyCredentialException(String str) {
        this(str, null, 2, 0 == true ? 1 : 0);
    }

    @n
    public static final GetCredentialException createFrom(String str, String str2) {
        return INSTANCE.createFrom(str, str2);
    }

    @Override // androidx.credentials.exceptions.GetCredentialException
    public String getType() {
        return this.type;
    }

    public /* synthetic */ GetPublicKeyCredentialException(String str, CharSequence charSequence, int i10, h hVar) {
        this(str, (i10 & 2) != 0 ? null : charSequence);
    }

    public GetPublicKeyCredentialException(String str, CharSequence charSequence) {
        super(str, charSequence);
        this.type = str;
        if (getType().length() <= 0) {
            throw new IllegalArgumentException("type must not be empty");
        }
    }
}
