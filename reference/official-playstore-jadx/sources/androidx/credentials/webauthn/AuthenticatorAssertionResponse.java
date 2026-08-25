package androidx.credentials.webauthn;

import androidx.credentials.webauthn.WebAuthnUtils;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.LinkHeader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import kotlin.Metadata;
import kotlin.collections.r;
import kotlin.jvm.internal.h;
import kotlin.text.a;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\t\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0010J\u0006\u0010\u001f\u001a\u00020\u0005J\u0006\u0010 \u001a\u00020\u0005J\b\u0010!\u001a\u00020\u0017H\u0016R\u001a\u0010\u0011\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u000b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u00020\u0017X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0013\"\u0004\b\u001e\u0010\u0015R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Landroidx/credentials/webauthn/AuthenticatorAssertionResponse;", "Landroidx/credentials/webauthn/AuthenticatorResponse;", "requestOptions", "Landroidx/credentials/webauthn/PublicKeyCredentialRequestOptions;", "credentialId", "", TtmlNode.ATTR_TTS_ORIGIN, "", "up", "", "uv", "be", CmcdConfiguration.KEY_BUFFER_STARVATION, "userHandle", "packageName", "clientDataHash", "(Landroidx/credentials/webauthn/PublicKeyCredentialRequestOptions;[BLjava/lang/String;ZZZZ[BLjava/lang/String;[B)V", "authenticatorData", "getAuthenticatorData", "()[B", "setAuthenticatorData", "([B)V", "clientJson", "Lorg/json/JSONObject;", "getClientJson", "()Lorg/json/JSONObject;", "setClientJson", "(Lorg/json/JSONObject;)V", "signature", "getSignature", "setSignature", "dataToSign", "defaultAuthenticatorData", "json", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AuthenticatorAssertionResponse implements AuthenticatorResponse {
    private byte[] authenticatorData;
    private final boolean be;
    private final boolean bs;
    private final byte[] clientDataHash;
    private JSONObject clientJson;
    private final byte[] credentialId;
    private final String origin;
    private final String packageName;
    private final PublicKeyCredentialRequestOptions requestOptions;
    private byte[] signature;
    private final boolean up;
    private byte[] userHandle;
    private final boolean uv;

    public AuthenticatorAssertionResponse(PublicKeyCredentialRequestOptions publicKeyCredentialRequestOptions, byte[] bArr, String str, boolean z, boolean z5, boolean z10, boolean z11, byte[] bArr2, String str2, byte[] bArr3) throws JSONException {
        this.requestOptions = publicKeyCredentialRequestOptions;
        this.credentialId = bArr;
        this.origin = str;
        this.up = z;
        this.uv = z5;
        this.be = z10;
        this.bs = z11;
        this.userHandle = bArr2;
        this.packageName = str2;
        this.clientDataHash = bArr3;
        this.clientJson = new JSONObject();
        this.signature = new byte[0];
        getClientJson().put(LinkHeader.Parameters.Type, "webauthn.get");
        getClientJson().put("challenge", WebAuthnUtils.INSTANCE.b64Encode(publicKeyCredentialRequestOptions.getChallenge()));
        getClientJson().put(TtmlNode.ATTR_TTS_ORIGIN, str);
        if (str2 != null) {
            getClientJson().put("androidPackageName", str2);
        }
        this.authenticatorData = defaultAuthenticatorData();
    }

    public final byte[] dataToSign() throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bArrDigest = this.clientDataHash;
        if (bArrDigest == null) {
            bArrDigest = messageDigest.digest(getClientJson().toString().getBytes(a.f19924a));
        }
        return r.i0(this.authenticatorData, bArrDigest);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    public final byte[] defaultAuthenticatorData() {
        byte[] bArrDigest = MessageDigest.getInstance("SHA-256").digest(this.requestOptions.getRpId().getBytes(a.f19924a));
        boolean z = this.up;
        ?? r1 = z;
        if (this.uv) {
            r1 = (z ? 1 : 0) | 4;
        }
        ?? r12 = r1;
        if (this.be) {
            r12 = (r1 == true ? 1 : 0) | 8;
        }
        int i10 = r12;
        if (this.bs) {
            i10 = (r12 == true ? 1 : 0) | 16;
        }
        return r.i0(r.i0(bArrDigest, new byte[]{(byte) i10}), new byte[]{0, 0, 0, 0});
    }

    public final byte[] getAuthenticatorData() {
        return this.authenticatorData;
    }

    @Override // androidx.credentials.webauthn.AuthenticatorResponse
    public JSONObject getClientJson() {
        return this.clientJson;
    }

    public final byte[] getSignature() {
        return this.signature;
    }

    @Override // androidx.credentials.webauthn.AuthenticatorResponse
    public JSONObject json() throws JSONException {
        byte[] bytes = getClientJson().toString().getBytes(a.f19924a);
        JSONObject jSONObject = new JSONObject();
        if (this.clientDataHash == null) {
            jSONObject.put("clientDataJSON", WebAuthnUtils.INSTANCE.b64Encode(bytes));
        }
        WebAuthnUtils.Companion companion = WebAuthnUtils.INSTANCE;
        jSONObject.put("authenticatorData", companion.b64Encode(this.authenticatorData));
        jSONObject.put("signature", companion.b64Encode(this.signature));
        jSONObject.put("userHandle", companion.b64Encode(this.userHandle));
        return jSONObject;
    }

    public final void setAuthenticatorData(byte[] bArr) {
        this.authenticatorData = bArr;
    }

    @Override // androidx.credentials.webauthn.AuthenticatorResponse
    public void setClientJson(JSONObject jSONObject) {
        this.clientJson = jSONObject;
    }

    public final void setSignature(byte[] bArr) {
        this.signature = bArr;
    }

    public /* synthetic */ AuthenticatorAssertionResponse(PublicKeyCredentialRequestOptions publicKeyCredentialRequestOptions, byte[] bArr, String str, boolean z, boolean z5, boolean z10, boolean z11, byte[] bArr2, String str2, byte[] bArr3, int i10, h hVar) {
        this(publicKeyCredentialRequestOptions, bArr, str, z, z5, z10, z11, bArr2, (i10 & 256) != 0 ? null : str2, (i10 & 512) != 0 ? null : bArr3);
    }
}
