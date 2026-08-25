package androidx.credentials.exceptions;

import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\r\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001b\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/credentials/exceptions/CreateCredentialCustomException;", "Landroidx/credentials/exceptions/CreateCredentialException;", LinkHeader.Parameters.Type, "", "errorMessage", "", "(Ljava/lang/String;Ljava/lang/CharSequence;)V", "getType", "()Ljava/lang/String;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CreateCredentialCustomException extends CreateCredentialException {
    private final String type;

    /* JADX WARN: Multi-variable type inference failed */
    public CreateCredentialCustomException(String str) {
        this(str, null, 2, 0 == true ? 1 : 0);
    }

    @Override // androidx.credentials.exceptions.CreateCredentialException
    public String getType() {
        return this.type;
    }

    public /* synthetic */ CreateCredentialCustomException(String str, CharSequence charSequence, int i10, h hVar) {
        this(str, (i10 & 2) != 0 ? null : charSequence);
    }

    public CreateCredentialCustomException(String str, CharSequence charSequence) {
        super(str, charSequence);
        this.type = str;
        if (getType().length() <= 0) {
            throw new IllegalArgumentException("type must not be empty");
        }
    }
}
