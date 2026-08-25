package androidx.credentials.exceptions;

import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0003\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0013\b\u0007\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0006"}, d2 = {"Landroidx/credentials/exceptions/ClearCredentialInterruptedException;", "Landroidx/credentials/exceptions/ClearCredentialException;", "errorMessage", "", "(Ljava/lang/CharSequence;)V", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ClearCredentialInterruptedException extends ClearCredentialException {
    public static final String TYPE_CLEAR_CREDENTIAL_INTERRUPTED_EXCEPTION = "androidx.credentials.TYPE_CLEAR_CREDENTIAL_INTERRUPTED_EXCEPTION";

    /* JADX WARN: Multi-variable type inference failed */
    public ClearCredentialInterruptedException() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public /* synthetic */ ClearCredentialInterruptedException(CharSequence charSequence, int i10, h hVar) {
        this((i10 & 1) != 0 ? null : charSequence);
    }

    public ClearCredentialInterruptedException(CharSequence charSequence) {
        super(TYPE_CLEAR_CREDENTIAL_INTERRUPTED_EXCEPTION, charSequence);
    }
}
