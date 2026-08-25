package androidx.credentials;

import android.os.Bundle;
import androidx.credentials.CreateCredentialRequest;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.PsExtractor;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001BO\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\b¢\u0006\u0002\u0010\u000e¨\u0006\u000f"}, d2 = {"Landroidx/credentials/CreateCustomCredentialRequest;", "Landroidx/credentials/CreateCredentialRequest;", LinkHeader.Parameters.Type, "", "credentialData", "Landroid/os/Bundle;", "candidateQueryData", "isSystemProviderRequired", "", "displayInfo", "Landroidx/credentials/CreateCredentialRequest$DisplayInfo;", "isAutoSelectAllowed", TtmlNode.ATTR_TTS_ORIGIN, "preferImmediatelyAvailableCredentials", "(Ljava/lang/String;Landroid/os/Bundle;Landroid/os/Bundle;ZLandroidx/credentials/CreateCredentialRequest$DisplayInfo;ZLjava/lang/String;Z)V", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class CreateCustomCredentialRequest extends CreateCredentialRequest {
    public CreateCustomCredentialRequest(String str, Bundle bundle, Bundle bundle2, boolean z, CreateCredentialRequest.DisplayInfo displayInfo) {
        this(str, bundle, bundle2, z, displayInfo, false, null, false, 224, null);
    }

    public CreateCustomCredentialRequest(String str, Bundle bundle, Bundle bundle2, boolean z, CreateCredentialRequest.DisplayInfo displayInfo, boolean z5) {
        this(str, bundle, bundle2, z, displayInfo, z5, null, false, PsExtractor.AUDIO_STREAM, null);
    }

    public CreateCustomCredentialRequest(String str, Bundle bundle, Bundle bundle2, boolean z, CreateCredentialRequest.DisplayInfo displayInfo, boolean z5, String str2) {
        this(str, bundle, bundle2, z, displayInfo, z5, str2, false, 128, null);
    }

    public /* synthetic */ CreateCustomCredentialRequest(String str, Bundle bundle, Bundle bundle2, boolean z, CreateCredentialRequest.DisplayInfo displayInfo, boolean z5, String str2, boolean z10, int i10, h hVar) {
        this(str, bundle, bundle2, z, displayInfo, (i10 & 32) != 0 ? false : z5, (i10 & 64) != 0 ? null : str2, (i10 & 128) != 0 ? false : z10);
    }

    public CreateCustomCredentialRequest(String str, Bundle bundle, Bundle bundle2, boolean z, CreateCredentialRequest.DisplayInfo displayInfo, boolean z5, String str2, boolean z10) {
        super(str, bundle, bundle2, z, z5, displayInfo, str2, z10);
        if (str.length() <= 0) {
            throw new IllegalArgumentException("type should not be empty");
        }
    }
}
