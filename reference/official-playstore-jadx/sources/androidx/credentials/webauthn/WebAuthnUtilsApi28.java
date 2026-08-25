package androidx.credentials.webauthn;

import androidx.credentials.provider.CallingAppInfo;
import java.security.MessageDigest;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0001\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Landroidx/credentials/webauthn/WebAuthnUtilsApi28;", "", "()V", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class WebAuthnUtilsApi28 {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Landroidx/credentials/webauthn/WebAuthnUtilsApi28$Companion;", "", "()V", "appInfoToOrigin", "", "info", "Landroidx/credentials/provider/CallingAppInfo;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final String appInfoToOrigin(CallingAppInfo info) {
            return "android:apk-key-hash:" + WebAuthnUtils.INSTANCE.b64Encode(MessageDigest.getInstance("SHA-256").digest(info.getSigningInfo().getApkContentsSigners()[0].toByteArray()));
        }

        private Companion() {
        }
    }
}
