package androidx.credentials.exceptions;

import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0003\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0013\b\u0007\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0006"}, d2 = {"Landroidx/credentials/exceptions/NoCredentialException;", "Landroidx/credentials/exceptions/GetCredentialException;", "errorMessage", "", "(Ljava/lang/CharSequence;)V", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class NoCredentialException extends GetCredentialException {
    public static final String TYPE_FRAMEWORK_TYPE_NO_CREDENTIAL = "android.credentials.GetCredentialException.TYPE_NO_CREDENTIAL";

    /* JADX WARN: Multi-variable type inference failed */
    public NoCredentialException() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public /* synthetic */ NoCredentialException(CharSequence charSequence, int i10, h hVar) {
        this((i10 & 1) != 0 ? null : charSequence);
    }

    public NoCredentialException(CharSequence charSequence) {
        super(TYPE_FRAMEWORK_TYPE_NO_CREDENTIAL, charSequence);
    }
}
