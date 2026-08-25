package androidx.credentials.provider;

import android.os.Bundle;
import androidx.credentials.PasswordCredential;
import androidx.credentials.PublicKeyCredential;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import q7.n;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u001f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u000e"}, d2 = {"Landroidx/credentials/provider/BeginGetCredentialOption;", "", TtmlNode.ATTR_ID, "", LinkHeader.Parameters.Type, "candidateQueryData", "Landroid/os/Bundle;", "(Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)V", "getCandidateQueryData", "()Landroid/os/Bundle;", "getId", "()Ljava/lang/String;", "getType", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class BeginGetCredentialOption {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Bundle candidateQueryData;
    private final String id;
    private final String type;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J%\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0001¢\u0006\u0002\b\n¨\u0006\u000b"}, d2 = {"Landroidx/credentials/provider/BeginGetCredentialOption$Companion;", "", "()V", "createFrom", "Landroidx/credentials/provider/BeginGetCredentialOption;", TtmlNode.ATTR_ID, "", LinkHeader.Parameters.Type, "candidateQueryData", "Landroid/os/Bundle;", "createFrom$credentials_release", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        @n
        public final BeginGetCredentialOption createFrom$credentials_release(String id, String type, Bundle candidateQueryData) {
            return type.equals(PasswordCredential.TYPE_PASSWORD_CREDENTIAL) ? BeginGetPasswordOption.INSTANCE.createFrom$credentials_release(candidateQueryData, id) : type.equals(PublicKeyCredential.TYPE_PUBLIC_KEY_CREDENTIAL) ? BeginGetPublicKeyCredentialOption.INSTANCE.createFrom$credentials_release(candidateQueryData, id) : new BeginGetCustomCredentialOption(id, type, candidateQueryData);
        }

        private Companion() {
        }
    }

    public BeginGetCredentialOption(String str, String str2, Bundle bundle) {
        this.id = str;
        this.type = str2;
        this.candidateQueryData = bundle;
    }

    public final Bundle getCandidateQueryData() {
        return this.candidateQueryData;
    }

    public final String getId() {
        return this.id;
    }

    public final String getType() {
        return this.type;
    }
}
