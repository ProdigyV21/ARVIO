package androidx.credentials.exceptions.publickeycredential;

import androidx.credentials.exceptions.GetCredentialCustomException;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.exceptions.domerrors.AbortError;
import androidx.credentials.exceptions.domerrors.ConstraintError;
import androidx.credentials.exceptions.domerrors.DataCloneError;
import androidx.credentials.exceptions.domerrors.DataError;
import androidx.credentials.exceptions.domerrors.DomError;
import androidx.credentials.exceptions.domerrors.EncodingError;
import androidx.credentials.exceptions.domerrors.HierarchyRequestError;
import androidx.credentials.exceptions.domerrors.InUseAttributeError;
import androidx.credentials.exceptions.domerrors.InvalidCharacterError;
import androidx.credentials.exceptions.domerrors.InvalidModificationError;
import androidx.credentials.exceptions.domerrors.InvalidNodeTypeError;
import androidx.credentials.exceptions.domerrors.InvalidStateError;
import androidx.credentials.exceptions.domerrors.NamespaceError;
import androidx.credentials.exceptions.domerrors.NetworkError;
import androidx.credentials.exceptions.domerrors.NoModificationAllowedError;
import androidx.credentials.exceptions.domerrors.NotAllowedError;
import androidx.credentials.exceptions.domerrors.NotFoundError;
import androidx.credentials.exceptions.domerrors.NotReadableError;
import androidx.credentials.exceptions.domerrors.NotSupportedError;
import androidx.credentials.exceptions.domerrors.OperationError;
import androidx.credentials.exceptions.domerrors.OptOutError;
import androidx.credentials.exceptions.domerrors.QuotaExceededError;
import androidx.credentials.exceptions.domerrors.ReadOnlyError;
import androidx.credentials.exceptions.domerrors.SecurityError;
import androidx.credentials.exceptions.domerrors.SyntaxError;
import androidx.credentials.exceptions.domerrors.TimeoutError;
import androidx.credentials.exceptions.domerrors.TransactionInactiveError;
import androidx.credentials.exceptions.domerrors.UnknownError;
import androidx.credentials.exceptions.domerrors.VersionError;
import androidx.credentials.exceptions.domerrors.WrongDocumentError;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import androidx.credentials.internal.FrameworkClassParsingException;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import q7.n;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0005\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u001b\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Landroidx/credentials/exceptions/publickeycredential/GetPublicKeyCredentialDomException;", "Landroidx/credentials/exceptions/publickeycredential/GetPublicKeyCredentialException;", "domError", "Landroidx/credentials/exceptions/domerrors/DomError;", "errorMessage", "", "(Landroidx/credentials/exceptions/domerrors/DomError;Ljava/lang/CharSequence;)V", "getDomError", "()Landroidx/credentials/exceptions/domerrors/DomError;", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class GetPublicKeyCredentialDomException extends GetPublicKeyCredentialException {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION = "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION";
    private final DomError domError;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Landroidx/credentials/exceptions/publickeycredential/GetPublicKeyCredentialDomException$Companion;", "", "()V", "TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION", "", "createFrom", "Landroidx/credentials/exceptions/GetCredentialException;", LinkHeader.Parameters.Type, "msg", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @n
        public final GetCredentialException createFrom(String type, String msg) {
            Object objGenerateException;
            try {
                DomExceptionUtils.Companion companion = DomExceptionUtils.INSTANCE;
                GetPublicKeyCredentialDomException getPublicKeyCredentialDomException = new GetPublicKeyCredentialDomException(new UnknownError(), null, 2, 0 == true ? 1 : 0);
                if (type.equals("androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_ABORT_ERROR")) {
                    objGenerateException = companion.generateException(new AbortError(), msg, getPublicKeyCredentialDomException);
                } else if (type.equals("androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_CONSTRAINT_ERROR")) {
                    objGenerateException = companion.generateException(new ConstraintError(), msg, getPublicKeyCredentialDomException);
                } else if (type.equals("androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_DATA_CLONE_ERROR")) {
                    objGenerateException = companion.generateException(new DataCloneError(), msg, getPublicKeyCredentialDomException);
                } else if (type.equals("androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_DATA_ERROR")) {
                    objGenerateException = companion.generateException(new DataError(), msg, getPublicKeyCredentialDomException);
                } else if (type.equals("androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_ENCODING_ERROR")) {
                    objGenerateException = companion.generateException(new EncodingError(), msg, getPublicKeyCredentialDomException);
                } else if (type.equals("androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_HIERARCHY_REQUEST_ERROR")) {
                    objGenerateException = companion.generateException(new HierarchyRequestError(), msg, getPublicKeyCredentialDomException);
                } else if (type.equals("androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_IN_USE_ATTRIBUTE_ERROR")) {
                    objGenerateException = companion.generateException(new InUseAttributeError(), msg, getPublicKeyCredentialDomException);
                } else if (type.equals("androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_INVALID_CHARACTER_ERROR")) {
                    objGenerateException = companion.generateException(new InvalidCharacterError(), msg, getPublicKeyCredentialDomException);
                } else if (type.equals("androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_INVALID_MODIFICATION_ERROR")) {
                    objGenerateException = companion.generateException(new InvalidModificationError(), msg, getPublicKeyCredentialDomException);
                } else if (type.equals("androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_INVALID_NODE_TYPE_ERROR")) {
                    objGenerateException = companion.generateException(new InvalidNodeTypeError(), msg, getPublicKeyCredentialDomException);
                } else if (type.equals("androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_INVALID_STATE_ERROR")) {
                    objGenerateException = companion.generateException(new InvalidStateError(), msg, getPublicKeyCredentialDomException);
                } else if (type.equals("androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_NAMESPACE_ERROR")) {
                    objGenerateException = companion.generateException(new NamespaceError(), msg, getPublicKeyCredentialDomException);
                } else if (type.equals("androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_NETWORK_ERROR")) {
                    objGenerateException = companion.generateException(new NetworkError(), msg, getPublicKeyCredentialDomException);
                } else if (type.equals("androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_NO_MODIFICATION_ALLOWED_ERROR")) {
                    objGenerateException = companion.generateException(new NoModificationAllowedError(), msg, getPublicKeyCredentialDomException);
                } else if (type.equals("androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_NOT_ALLOWED_ERROR")) {
                    objGenerateException = companion.generateException(new NotAllowedError(), msg, getPublicKeyCredentialDomException);
                } else if (type.equals("androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_NOT_FOUND_ERROR")) {
                    objGenerateException = companion.generateException(new NotFoundError(), msg, getPublicKeyCredentialDomException);
                } else if (type.equals("androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_NOT_READABLE_ERROR")) {
                    objGenerateException = companion.generateException(new NotReadableError(), msg, getPublicKeyCredentialDomException);
                } else if (type.equals("androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_NOT_SUPPORTED_ERROR")) {
                    objGenerateException = companion.generateException(new NotSupportedError(), msg, getPublicKeyCredentialDomException);
                } else if (type.equals("androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_OPERATION_ERROR")) {
                    objGenerateException = companion.generateException(new OperationError(), msg, getPublicKeyCredentialDomException);
                } else if (type.equals("androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_OPT_OUT_ERROR")) {
                    objGenerateException = companion.generateException(new OptOutError(), msg, getPublicKeyCredentialDomException);
                } else if (type.equals("androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_QUOTA_EXCEEDED_ERROR")) {
                    objGenerateException = companion.generateException(new QuotaExceededError(), msg, getPublicKeyCredentialDomException);
                } else if (type.equals("androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_READ_ONLY_ERROR")) {
                    objGenerateException = companion.generateException(new ReadOnlyError(), msg, getPublicKeyCredentialDomException);
                } else if (type.equals("androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_SECURITY_ERROR")) {
                    objGenerateException = companion.generateException(new SecurityError(), msg, getPublicKeyCredentialDomException);
                } else if (type.equals("androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_SYNTAX_ERROR")) {
                    objGenerateException = companion.generateException(new SyntaxError(), msg, getPublicKeyCredentialDomException);
                } else if (type.equals("androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_TIMEOUT_ERROR")) {
                    objGenerateException = companion.generateException(new TimeoutError(), msg, getPublicKeyCredentialDomException);
                } else if (type.equals("androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_TRANSACTION_INACTIVE_ERROR")) {
                    objGenerateException = companion.generateException(new TransactionInactiveError(), msg, getPublicKeyCredentialDomException);
                } else if (type.equals("androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_UNKNOWN_ERROR")) {
                    objGenerateException = companion.generateException(new UnknownError(), msg, getPublicKeyCredentialDomException);
                } else if (type.equals("androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_VERSION_ERROR")) {
                    objGenerateException = companion.generateException(new VersionError(), msg, getPublicKeyCredentialDomException);
                } else {
                    if (!type.equals("androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_WRONG_DOCUMENT_ERROR")) {
                        throw new FrameworkClassParsingException();
                    }
                    objGenerateException = companion.generateException(new WrongDocumentError(), msg, getPublicKeyCredentialDomException);
                }
                return (GetCredentialException) objGenerateException;
            } catch (FrameworkClassParsingException unused) {
                return new GetCredentialCustomException(type, msg);
            }
        }

        private Companion() {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public GetPublicKeyCredentialDomException(DomError domError) {
        this(domError, null, 2, 0 == true ? 1 : 0);
    }

    @n
    public static final GetCredentialException createFrom(String str, String str2) {
        return INSTANCE.createFrom(str, str2);
    }

    public final DomError getDomError() {
        return this.domError;
    }

    public /* synthetic */ GetPublicKeyCredentialDomException(DomError domError, CharSequence charSequence, int i10, h hVar) {
        this(domError, (i10 & 2) != 0 ? null : charSequence);
    }

    public GetPublicKeyCredentialDomException(DomError domError, CharSequence charSequence) {
        super("androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/" + domError.getType(), charSequence);
        this.domError = domError;
    }
}
