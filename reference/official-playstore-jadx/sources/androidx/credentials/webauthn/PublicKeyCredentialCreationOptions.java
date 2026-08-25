package androidx.credentials.webauthn;

import android.util.Log;
import androidx.credentials.webauthn.WebAuthnUtils;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.collections.z;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0004R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R \u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\u001b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0014¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0017R\u0011\u0010!\u001a\u00020\"¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u001a\u0010%\u001a\u00020&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u0011\u0010+\u001a\u00020,¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.¨\u0006/"}, d2 = {"Landroidx/credentials/webauthn/PublicKeyCredentialCreationOptions;", "", "requestJson", "", "(Ljava/lang/String;)V", "attestation", "getAttestation", "()Ljava/lang/String;", "setAttestation", "authenticatorSelection", "Landroidx/credentials/webauthn/AuthenticatorSelectionCriteria;", "getAuthenticatorSelection", "()Landroidx/credentials/webauthn/AuthenticatorSelectionCriteria;", "setAuthenticatorSelection", "(Landroidx/credentials/webauthn/AuthenticatorSelectionCriteria;)V", "challenge", "", "getChallenge", "()[B", "excludeCredentials", "", "Landroidx/credentials/webauthn/PublicKeyCredentialDescriptor;", "getExcludeCredentials", "()Ljava/util/List;", "setExcludeCredentials", "(Ljava/util/List;)V", "json", "Lorg/json/JSONObject;", "getJson", "()Lorg/json/JSONObject;", "pubKeyCredParams", "Landroidx/credentials/webauthn/PublicKeyCredentialParameters;", "getPubKeyCredParams", "rp", "Landroidx/credentials/webauthn/PublicKeyCredentialRpEntity;", "getRp", "()Landroidx/credentials/webauthn/PublicKeyCredentialRpEntity;", "timeout", "", "getTimeout", "()J", "setTimeout", "(J)V", "user", "Landroidx/credentials/webauthn/PublicKeyCredentialUserEntity;", "getUser", "()Landroidx/credentials/webauthn/PublicKeyCredentialUserEntity;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PublicKeyCredentialCreationOptions {
    private String attestation;
    private AuthenticatorSelectionCriteria authenticatorSelection;
    private final byte[] challenge;
    private List<PublicKeyCredentialDescriptor> excludeCredentials;
    private final JSONObject json;
    private final List<PublicKeyCredentialParameters> pubKeyCredParams;
    private final PublicKeyCredentialRpEntity rp;
    private long timeout;
    private final PublicKeyCredentialUserEntity user;

    public PublicKeyCredentialCreationOptions(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        this.json = jSONObject;
        String string = jSONObject.getString("challenge");
        WebAuthnUtils.Companion companion = WebAuthnUtils.INSTANCE;
        this.challenge = companion.b64Decode(string);
        JSONObject jSONObject2 = jSONObject.getJSONObject("rp");
        this.rp = new PublicKeyCredentialRpEntity(jSONObject2.getString(ContentDisposition.Parameters.Name), jSONObject2.getString(TtmlNode.ATTR_ID));
        JSONObject jSONObject3 = jSONObject.getJSONObject("user");
        this.user = new PublicKeyCredentialUserEntity(jSONObject3.getString(ContentDisposition.Parameters.Name), companion.b64Decode(jSONObject3.getString(TtmlNode.ATTR_ID)), jSONObject3.getString("displayName"));
        JSONArray jSONArray = jSONObject.getJSONArray("pubKeyCredParams");
        ArrayList arrayList = new ArrayList();
        int length = jSONArray.length();
        for (int i10 = 0; i10 < length; i10++) {
            JSONObject jSONObject4 = jSONArray.getJSONObject(i10);
            arrayList.add(new PublicKeyCredentialParameters(jSONObject4.getString(LinkHeader.Parameters.Type), jSONObject4.getLong("alg")));
        }
        List<PublicKeyCredentialParameters> listC1 = x.c1(arrayList);
        this.pubKeyCredParams = listC1;
        this.timeout = this.json.optLong("timeout", 0L);
        this.excludeCredentials = z.f19728i;
        this.authenticatorSelection = new AuthenticatorSelectionCriteria("platform", "required", false, null, 12, null);
        this.attestation = this.json.optString("attestation", "none");
        Log.i("WebAuthn", "Challenge " + this.challenge + "()");
        StringBuilder sb2 = new StringBuilder("rp ");
        sb2.append(this.rp);
        Log.i("WebAuthn", sb2.toString());
        Log.i("WebAuthn", "user " + this.user);
        Log.i("WebAuthn", "pubKeyCredParams " + listC1);
        Log.i("WebAuthn", "timeout " + this.timeout);
        Log.i("WebAuthn", "excludeCredentials " + this.excludeCredentials);
        Log.i("WebAuthn", "authenticatorSelection " + this.authenticatorSelection);
        Log.i("WebAuthn", "attestation " + this.attestation);
    }

    public final String getAttestation() {
        return this.attestation;
    }

    public final AuthenticatorSelectionCriteria getAuthenticatorSelection() {
        return this.authenticatorSelection;
    }

    public final byte[] getChallenge() {
        return this.challenge;
    }

    public final List<PublicKeyCredentialDescriptor> getExcludeCredentials() {
        return this.excludeCredentials;
    }

    public final JSONObject getJson() {
        return this.json;
    }

    public final List<PublicKeyCredentialParameters> getPubKeyCredParams() {
        return this.pubKeyCredParams;
    }

    public final PublicKeyCredentialRpEntity getRp() {
        return this.rp;
    }

    public final long getTimeout() {
        return this.timeout;
    }

    public final PublicKeyCredentialUserEntity getUser() {
        return this.user;
    }

    public final void setAttestation(String str) {
        this.attestation = str;
    }

    public final void setAuthenticatorSelection(AuthenticatorSelectionCriteria authenticatorSelectionCriteria) {
        this.authenticatorSelection = authenticatorSelectionCriteria;
    }

    public final void setExcludeCredentials(List<PublicKeyCredentialDescriptor> list) {
        this.excludeCredentials = list;
    }

    public final void setTimeout(long j10) {
        this.timeout = j10;
    }
}
