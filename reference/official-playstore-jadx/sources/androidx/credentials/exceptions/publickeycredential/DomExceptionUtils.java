package androidx.credentials.exceptions.publickeycredential;

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
import androidx.credentials.internal.FrameworkClassParsingException;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0001\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Landroidx/credentials/exceptions/publickeycredential/DomExceptionUtils;", "", "()V", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DomExceptionUtils {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String SEPARATOR = "/";

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J:\u0010\u0005\u001a\u0002H\u0006\"\u0006\b\u0000\u0010\u0006\u0018\u00012\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\n\u001a\u0002H\u0006H\u0081\b¢\u0006\u0004\b\u000b\u0010\fJ-\u0010\r\u001a\u0002H\u0006\"\u0004\b\u0000\u0010\u00062\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\n\u001a\u0002H\u0006H\u0002¢\u0006\u0002\u0010\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/credentials/exceptions/publickeycredential/DomExceptionUtils$Companion;", "", "()V", "SEPARATOR", "", "generateDomException", "T", LinkHeader.Parameters.Type, "prefix", "msg", "t", "generateDomException$credentials_release", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "generateException", "domError", "Landroidx/credentials/exceptions/domerrors/DomError;", "(Landroidx/credentials/exceptions/domerrors/DomError;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final <T> T generateException(DomError domError, String msg, T t2) throws FrameworkClassParsingException {
            if (t2 instanceof CreatePublicKeyCredentialDomException) {
                return (T) new CreatePublicKeyCredentialDomException(domError, msg);
            }
            if (t2 instanceof GetPublicKeyCredentialDomException) {
                return (T) new GetPublicKeyCredentialDomException(domError, msg);
            }
            throw new FrameworkClassParsingException();
        }

        public final <T> T generateDomException$credentials_release(String type, String prefix, String msg, T t2) throws FrameworkClassParsingException {
            if (type.equals(prefix.concat(AbortError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_ABORT_ERROR))) {
                return (T) generateException(new AbortError(), msg, t2);
            }
            if (type.equals(prefix.concat(ConstraintError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_CONSTRAINT_ERROR))) {
                return (T) generateException(new ConstraintError(), msg, t2);
            }
            if (type.equals(prefix.concat(DataCloneError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DATA_CLONE_ERROR))) {
                return (T) generateException(new DataCloneError(), msg, t2);
            }
            if (type.equals(prefix.concat(DataError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DATA_ERROR))) {
                return (T) generateException(new DataError(), msg, t2);
            }
            if (type.equals(prefix.concat(EncodingError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_ENCODING_ERROR))) {
                return (T) generateException(new EncodingError(), msg, t2);
            }
            if (type.equals(prefix.concat(HierarchyRequestError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_HIERARCHY_REQUEST_ERROR))) {
                return (T) generateException(new HierarchyRequestError(), msg, t2);
            }
            if (type.equals(prefix.concat(InUseAttributeError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_IN_USE_ATTRIBUTE_ERROR))) {
                return (T) generateException(new InUseAttributeError(), msg, t2);
            }
            if (type.equals(prefix.concat(InvalidCharacterError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_INVALID_CHARACTER_ERROR))) {
                return (T) generateException(new InvalidCharacterError(), msg, t2);
            }
            if (type.equals(prefix.concat(InvalidModificationError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_INVALID_MODIFICATION_ERROR))) {
                return (T) generateException(new InvalidModificationError(), msg, t2);
            }
            if (type.equals(prefix.concat(InvalidNodeTypeError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_INVALID_NODE_TYPE_ERROR))) {
                return (T) generateException(new InvalidNodeTypeError(), msg, t2);
            }
            if (type.equals(prefix.concat(InvalidStateError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_INVALID_STATE_ERROR))) {
                return (T) generateException(new InvalidStateError(), msg, t2);
            }
            if (type.equals(prefix.concat(NamespaceError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_NAMESPACE_ERROR))) {
                return (T) generateException(new NamespaceError(), msg, t2);
            }
            if (type.equals(prefix.concat(NetworkError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_NETWORK_ERROR))) {
                return (T) generateException(new NetworkError(), msg, t2);
            }
            if (type.equals(prefix.concat(NoModificationAllowedError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_NO_MODIFICATION_ALLOWED_ERROR))) {
                return (T) generateException(new NoModificationAllowedError(), msg, t2);
            }
            if (type.equals(prefix.concat(NotAllowedError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_NOT_ALLOWED_ERROR))) {
                return (T) generateException(new NotAllowedError(), msg, t2);
            }
            if (type.equals(prefix.concat(NotFoundError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_NOT_FOUND_ERROR))) {
                return (T) generateException(new NotFoundError(), msg, t2);
            }
            if (type.equals(prefix.concat(NotReadableError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_NOT_READABLE_ERROR))) {
                return (T) generateException(new NotReadableError(), msg, t2);
            }
            if (type.equals(prefix.concat(NotSupportedError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_NOT_SUPPORTED_ERROR))) {
                return (T) generateException(new NotSupportedError(), msg, t2);
            }
            if (type.equals(prefix.concat(OperationError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_OPERATION_ERROR))) {
                return (T) generateException(new OperationError(), msg, t2);
            }
            if (type.equals(prefix.concat(OptOutError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_OPT_OUT_ERROR))) {
                return (T) generateException(new OptOutError(), msg, t2);
            }
            if (type.equals(prefix.concat(QuotaExceededError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_QUOTA_EXCEEDED_ERROR))) {
                return (T) generateException(new QuotaExceededError(), msg, t2);
            }
            if (type.equals(prefix.concat(ReadOnlyError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_READ_ONLY_ERROR))) {
                return (T) generateException(new ReadOnlyError(), msg, t2);
            }
            if (type.equals(prefix.concat(SecurityError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_SECURITY_ERROR))) {
                return (T) generateException(new SecurityError(), msg, t2);
            }
            if (type.equals(prefix.concat(SyntaxError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_SYNTAX_ERROR))) {
                return (T) generateException(new SyntaxError(), msg, t2);
            }
            if (type.equals(prefix.concat(TimeoutError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_TIMEOUT_ERROR))) {
                return (T) generateException(new TimeoutError(), msg, t2);
            }
            if (type.equals(prefix.concat(TransactionInactiveError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_TRANSACTION_INACTIVE_ERROR))) {
                return (T) generateException(new TransactionInactiveError(), msg, t2);
            }
            if (type.equals(prefix.concat(UnknownError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_UNKNOWN_ERROR))) {
                return (T) generateException(new UnknownError(), msg, t2);
            }
            if (type.equals(prefix.concat(VersionError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_VERSION_ERROR))) {
                return (T) generateException(new VersionError(), msg, t2);
            }
            if (type.equals(prefix.concat(WrongDocumentError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_WRONG_DOCUMENT_ERROR))) {
                return (T) generateException(new WrongDocumentError(), msg, t2);
            }
            throw new FrameworkClassParsingException();
        }

        private Companion() {
        }
    }
}
