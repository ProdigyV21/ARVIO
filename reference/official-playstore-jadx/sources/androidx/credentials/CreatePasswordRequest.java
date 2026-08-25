package androidx.credentials;

import android.os.Bundle;
import androidx.credentials.CreateCredentialRequest;
import androidx.credentials.internal.FrameworkClassParsingException;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.PsExtractor;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import q7.n;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B7\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tB;\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\u000bBO\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\f\u001a\u00020\r\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013¨\u0006\u0016"}, d2 = {"Landroidx/credentials/CreatePasswordRequest;", "Landroidx/credentials/CreateCredentialRequest;", TtmlNode.ATTR_ID, "", "password", TtmlNode.ATTR_TTS_ORIGIN, "preferImmediatelyAvailableCredentials", "", "isAutoSelectAllowed", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZ)V", "preferDefaultProvider", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZ)V", "displayInfo", "Landroidx/credentials/CreateCredentialRequest$DisplayInfo;", "credentialData", "Landroid/os/Bundle;", "candidateQueryData", "(Ljava/lang/String;Ljava/lang/String;ZLandroidx/credentials/CreateCredentialRequest$DisplayInfo;Ljava/lang/String;ZLandroid/os/Bundle;Landroid/os/Bundle;)V", "getId", "()Ljava/lang/String;", "getPassword", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CreatePasswordRequest extends CreateCredentialRequest {
    public static final String BUNDLE_KEY_ID = "androidx.credentials.BUNDLE_KEY_ID";
    public static final String BUNDLE_KEY_PASSWORD = "androidx.credentials.BUNDLE_KEY_PASSWORD";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String id;
    private final String password;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J'\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000b\u001a\u00020\tH\u0001¢\u0006\u0002\b\fJ\r\u0010\r\u001a\u00020\tH\u0001¢\u0006\u0002\b\u000eJ\u001d\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0004H\u0001¢\u0006\u0002\b\u0012R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087T¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0087T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Landroidx/credentials/CreatePasswordRequest$Companion;", "", "()V", "BUNDLE_KEY_ID", "", "BUNDLE_KEY_PASSWORD", "createFrom", "Landroidx/credentials/CreatePasswordRequest;", "data", "Landroid/os/Bundle;", TtmlNode.ATTR_TTS_ORIGIN, "candidateQueryData", "createFrom$credentials_release", "toCandidateDataBundle", "toCandidateDataBundle$credentials_release", "toCredentialDataBundle", TtmlNode.ATTR_ID, "password", "toCredentialDataBundle$credentials_release", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        @n
        public final CreatePasswordRequest createFrom$credentials_release(Bundle data, String origin, Bundle candidateQueryData) throws FrameworkClassParsingException {
            CreateCredentialRequest.DisplayInfo displayInfo;
            try {
                String string = data.getString("androidx.credentials.BUNDLE_KEY_ID");
                String string2 = data.getString("androidx.credentials.BUNDLE_KEY_PASSWORD");
                try {
                    displayInfo = CreateCredentialRequest.DisplayInfo.INSTANCE.createFrom(data);
                } catch (IllegalArgumentException unused) {
                    displayInfo = new CreateCredentialRequest.DisplayInfo(string, null);
                }
                return new CreatePasswordRequest(string, string2, data.getBoolean("androidx.credentials.BUNDLE_KEY_IS_AUTO_SELECT_ALLOWED", false), displayInfo, origin, data.getBoolean("androidx.credentials.BUNDLE_KEY_PREFER_IMMEDIATELY_AVAILABLE_CREDENTIALS", false), data, candidateQueryData, null);
            } catch (Exception unused2) {
                throw new FrameworkClassParsingException();
            }
        }

        @n
        public final Bundle toCandidateDataBundle$credentials_release() {
            return new Bundle();
        }

        @n
        public final Bundle toCredentialDataBundle$credentials_release(String id, String password) {
            Bundle bundle = new Bundle();
            bundle.putString("androidx.credentials.BUNDLE_KEY_ID", id);
            bundle.putString("androidx.credentials.BUNDLE_KEY_PASSWORD", password);
            return bundle;
        }

        private Companion() {
        }
    }

    public CreatePasswordRequest(String str, String str2) {
        this(str, str2, null, false, false, 28, null);
    }

    public final String getId() {
        return this.id;
    }

    public final String getPassword() {
        return this.password;
    }

    public CreatePasswordRequest(String str, String str2, String str3) {
        this(str, str2, str3, false, false, 24, null);
    }

    public CreatePasswordRequest(String str, String str2, String str3, boolean z) {
        this(str, str2, str3, z, false, 16, null);
    }

    public /* synthetic */ CreatePasswordRequest(String str, String str2, boolean z, CreateCredentialRequest.DisplayInfo displayInfo, String str3, boolean z5, Bundle bundle, Bundle bundle2, h hVar) {
        this(str, str2, z, displayInfo, str3, z5, bundle, bundle2);
    }

    public /* synthetic */ CreatePasswordRequest(String str, String str2, boolean z, CreateCredentialRequest.DisplayInfo displayInfo, String str3, boolean z5, Bundle bundle, Bundle bundle2, int i10, h hVar) {
        this(str, str2, z, displayInfo, (i10 & 16) != 0 ? null : str3, z5, (i10 & 64) != 0 ? INSTANCE.toCredentialDataBundle$credentials_release(str, str2) : bundle, (i10 & 128) != 0 ? INSTANCE.toCandidateDataBundle$credentials_release() : bundle2);
    }

    private CreatePasswordRequest(String str, String str2, boolean z, CreateCredentialRequest.DisplayInfo displayInfo, String str3, boolean z5, Bundle bundle, Bundle bundle2) {
        super(PasswordCredential.TYPE_PASSWORD_CREDENTIAL, bundle, bundle2, false, z, displayInfo, str3, z5);
        this.id = str;
        this.password = str2;
        if (str2.length() <= 0) {
            throw new IllegalArgumentException("password should not be empty");
        }
    }

    public /* synthetic */ CreatePasswordRequest(String str, String str2, String str3, boolean z, boolean z5, int i10, h hVar) {
        this(str, str2, (i10 & 4) != 0 ? null : str3, (i10 & 8) != 0 ? false : z, (i10 & 16) != 0 ? false : z5);
    }

    public CreatePasswordRequest(String str, String str2, String str3, boolean z, boolean z5) {
        this(str, str2, z5, new CreateCredentialRequest.DisplayInfo(str, null), str3, z, null, null, PsExtractor.AUDIO_STREAM, null);
    }

    public CreatePasswordRequest(String str, String str2, String str3, String str4, boolean z, boolean z5) {
        this(str, str2, z5, new CreateCredentialRequest.DisplayInfo(str, null, str4), str3, z, null, null, PsExtractor.AUDIO_STREAM, null);
    }
}
