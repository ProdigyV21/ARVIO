package androidx.credentials.provider;

import android.content.Intent;
import android.credentials.CreateCredentialException;
import android.credentials.CreateCredentialResponse;
import android.credentials.GetCredentialException;
import android.credentials.GetCredentialResponse;
import androidx.compose.ui.graphics.c;
import androidx.credentials.d;
import androidx.credentials.provider.utils.BeginGetCredentialUtil;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0007\u001a\u000e\u0010\u0003\u001a\u0004\u0018\u00010\u0004*\u00020\u0002H\u0007\u001a\u000e\u0010\u0005\u001a\u0004\u0018\u00010\u0006*\u00020\u0002H\u0007\u001a\u000e\u0010\u0007\u001a\u0004\u0018\u00010\b*\u00020\u0002H\u0007\u001a\u000e\u0010\t\u001a\u0004\u0018\u00010\n*\u00020\u0002H\u0007¨\u0006\u000b"}, d2 = {"getBeginGetResponse", "Landroidx/credentials/provider/BeginGetCredentialResponse;", "Landroid/content/Intent;", "getCreateCredentialCredentialResponse", "Landroid/credentials/CreateCredentialResponse;", "getCreateCredentialException", "Landroid/credentials/CreateCredentialException;", "getGetCredentialException", "Landroid/credentials/GetCredentialException;", "getGetCredentialResponse", "Landroid/credentials/GetCredentialResponse;", "credentials_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class IntentHandlerConverters {
    public static final BeginGetCredentialResponse getBeginGetResponse(Intent intent) {
        android.service.credentials.BeginGetCredentialResponse beginGetCredentialResponse;
        if (intent.hasExtra("android.service.credentials.extra.BEGIN_GET_CREDENTIAL_RESPONSE") && (beginGetCredentialResponse = (android.service.credentials.BeginGetCredentialResponse) intent.getParcelableExtra("android.service.credentials.extra.BEGIN_GET_CREDENTIAL_RESPONSE", android.service.credentials.BeginGetCredentialResponse.class)) != null) {
            return BeginGetCredentialUtil.INSTANCE.convertToJetpackResponse(beginGetCredentialResponse);
        }
        return null;
    }

    public static final CreateCredentialResponse getCreateCredentialCredentialResponse(Intent intent) {
        if (intent.hasExtra("android.service.credentials.extra.CREATE_CREDENTIAL_RESPONSE")) {
            return c.c(intent.getParcelableExtra("android.service.credentials.extra.CREATE_CREDENTIAL_RESPONSE", CreateCredentialResponse.class));
        }
        return null;
    }

    public static final CreateCredentialException getCreateCredentialException(Intent intent) {
        if (intent.hasExtra("android.service.credentials.extra.CREATE_CREDENTIAL_EXCEPTION")) {
            return c.b(intent.getParcelableExtra("android.service.credentials.extra.CREATE_CREDENTIAL_EXCEPTION", CreateCredentialException.class));
        }
        return null;
    }

    public static final GetCredentialException getGetCredentialException(Intent intent) {
        if (intent.hasExtra("android.service.credentials.extra.GET_CREDENTIAL_EXCEPTION")) {
            return d.b(intent.getParcelableExtra("android.service.credentials.extra.GET_CREDENTIAL_EXCEPTION", GetCredentialException.class));
        }
        return null;
    }

    public static final GetCredentialResponse getGetCredentialResponse(Intent intent) {
        if (intent.hasExtra("android.service.credentials.extra.GET_CREDENTIAL_RESPONSE")) {
            return c.d(intent.getParcelableExtra("android.service.credentials.extra.GET_CREDENTIAL_RESPONSE", GetCredentialResponse.class));
        }
        return null;
    }
}
