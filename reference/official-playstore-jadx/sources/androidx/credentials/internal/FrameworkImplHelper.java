package androidx.credentials.internal;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import androidx.credentials.CreateCredentialRequest;
import androidx.credentials.CreatePasswordRequest;
import androidx.credentials.CreatePublicKeyCredentialRequest;
import androidx.credentials.R;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import q7.n;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0001\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Landroidx/credentials/internal/FrameworkImplHelper;", "", "()V", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class FrameworkImplHelper {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\t"}, d2 = {"Landroidx/credentials/internal/FrameworkImplHelper$Companion;", "", "()V", "getFinalCreateCredentialData", "Landroid/os/Bundle;", "request", "Landroidx/credentials/CreateCredentialRequest;", "context", "Landroid/content/Context;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        @n
        public final Bundle getFinalCreateCredentialData(CreateCredentialRequest request, Context context) {
            Bundle credentialData = request.getCredentialData();
            Bundle bundle = request.getDisplayInfo().toBundle();
            bundle.putParcelable(CreateCredentialRequest.DisplayInfo.BUNDLE_KEY_CREDENTIAL_TYPE_ICON, Icon.createWithResource(context, request instanceof CreatePasswordRequest ? R.drawable.ic_password : request instanceof CreatePublicKeyCredentialRequest ? R.drawable.ic_passkey : R.drawable.ic_other_sign_in));
            credentialData.putBundle(CreateCredentialRequest.DisplayInfo.BUNDLE_KEY_REQUEST_DISPLAY_INFO, bundle);
            return credentialData;
        }

        private Companion() {
        }
    }

    @n
    public static final Bundle getFinalCreateCredentialData(CreateCredentialRequest createCredentialRequest, Context context) {
        return INSTANCE.getFinalCreateCredentialData(createCredentialRequest, context);
    }
}
