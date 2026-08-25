package androidx.credentials.exceptions.domerrors;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Landroidx/credentials/exceptions/domerrors/OptOutError;", "Landroidx/credentials/exceptions/domerrors/DomError;", "()V", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class OptOutError extends DomError {
    public static final String TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_OPT_OUT_ERROR = "androidx.credentials.TYPE_OPT_OUT_ERROR";

    public OptOutError() {
        super(TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_OPT_OUT_ERROR);
    }
}
