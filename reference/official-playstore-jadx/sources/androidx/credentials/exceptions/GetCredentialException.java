package androidx.credentials.exceptions;

import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\r\n\u0002\b\u0006\b&\u0018\u00002\u00060\u0001j\u0002`\u0002B\u001b\b\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0003\u001a\u00020\u00048\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/credentials/exceptions/GetCredentialException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", LinkHeader.Parameters.Type, "", "errorMessage", "", "(Ljava/lang/String;Ljava/lang/CharSequence;)V", "getErrorMessage", "()Ljava/lang/CharSequence;", "getType", "()Ljava/lang/String;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class GetCredentialException extends Exception {
    private final CharSequence errorMessage;
    private final String type;

    /* JADX WARN: Multi-variable type inference failed */
    public GetCredentialException(String str) {
        this(str, null, 2, 0 == true ? 1 : 0);
    }

    public CharSequence getErrorMessage() {
        return this.errorMessage;
    }

    public String getType() {
        return this.type;
    }

    public /* synthetic */ GetCredentialException(String str, CharSequence charSequence, int i10, h hVar) {
        this(str, (i10 & 2) != 0 ? null : charSequence);
    }

    public GetCredentialException(String str, CharSequence charSequence) {
        super(charSequence != null ? charSequence.toString() : null);
        this.type = str;
        this.errorMessage = charSequence;
    }
}
