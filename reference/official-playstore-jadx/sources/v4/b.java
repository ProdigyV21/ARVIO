package v4;

import androidx.credentials.GetCustomCredentialOption;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class b extends GetCustomCredentialOption {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f22158a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f22159b;

    public b(String str, String str2) {
        super("com.google.android.libraries.identity.googleid.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL", d.N0(str, str2), d.N0(str, str2), true, true, null, 32, null);
        this.f22158a = str;
        this.f22159b = str2;
        if (str.length() <= 0) {
            throw new IllegalArgumentException("serverClientId should not be empty");
        }
    }
}
