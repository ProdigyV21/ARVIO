package androidx.credentials.webauthn;

import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.LinkHeader;
import java.security.MessageDigest;
import java.util.Collection;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.collections.a0;
import kotlin.collections.r;
import kotlin.jvm.internal.h;
import kotlin.text.a;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\n\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0010J\b\u0010\u001c\u001a\u00020\u0005H\u0002J\r\u0010\u001d\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u001eJ\b\u0010\u001f\u001a\u00020\u0017H\u0016R\u001a\u0010\u0011\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\f\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u00020\u0017X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Landroidx/credentials/webauthn/AuthenticatorAttestationResponse;", "Landroidx/credentials/webauthn/AuthenticatorResponse;", "requestOptions", "Landroidx/credentials/webauthn/PublicKeyCredentialCreationOptions;", "credentialId", "", "credentialPublicKey", TtmlNode.ATTR_TTS_ORIGIN, "", "up", "", "uv", "be", CmcdConfiguration.KEY_BUFFER_STARVATION, "packageName", "clientDataHash", "(Landroidx/credentials/webauthn/PublicKeyCredentialCreationOptions;[B[BLjava/lang/String;ZZZZLjava/lang/String;[B)V", "attestationObject", "getAttestationObject", "()[B", "setAttestationObject", "([B)V", "clientJson", "Lorg/json/JSONObject;", "getClientJson", "()Lorg/json/JSONObject;", "setClientJson", "(Lorg/json/JSONObject;)V", "authData", "defaultAttestationObject", "defaultAttestationObject$credentials_release", "json", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AuthenticatorAttestationResponse implements AuthenticatorResponse {
    private byte[] attestationObject;
    private final boolean be;
    private final boolean bs;
    private final byte[] clientDataHash;
    private JSONObject clientJson;
    private final byte[] credentialId;
    private final byte[] credentialPublicKey;
    private final String origin;
    private final String packageName;
    private final PublicKeyCredentialCreationOptions requestOptions;
    private final boolean up;
    private final boolean uv;

    public AuthenticatorAttestationResponse(PublicKeyCredentialCreationOptions publicKeyCredentialCreationOptions, byte[] bArr, byte[] bArr2, String str, boolean z, boolean z5, boolean z10, boolean z11, String str2, byte[] bArr3) throws JSONException {
        this.requestOptions = publicKeyCredentialCreationOptions;
        this.credentialId = bArr;
        this.credentialPublicKey = bArr2;
        this.origin = str;
        this.up = z;
        this.uv = z5;
        this.be = z10;
        this.bs = z11;
        this.packageName = str2;
        this.clientDataHash = bArr3;
        this.clientJson = new JSONObject();
        getClientJson().put(LinkHeader.Parameters.Type, "webauthn.create");
        getClientJson().put("challenge", WebAuthnUtils.INSTANCE.b64Encode(publicKeyCredentialCreationOptions.getChallenge()));
        getClientJson().put(TtmlNode.ATTR_TTS_ORIGIN, str);
        if (str2 != null) {
            getClientJson().put("androidPackageName", str2);
        }
        this.attestationObject = defaultAttestationObject$credentials_release();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v23 */
    /* JADX WARN: Type inference failed for: r1v24 */
    /* JADX WARN: Type inference failed for: r1v25 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    private final byte[] authData() {
        byte[] bArrDigest = MessageDigest.getInstance("SHA-256").digest(this.requestOptions.getRp().getId().getBytes(a.f19924a));
        boolean z = this.up;
        ?? r1 = z;
        if (this.uv) {
            r1 = (z ? 1 : 0) | 4;
        }
        ?? r12 = r1;
        if (this.be) {
            r12 = (r1 == true ? 1 : 0) | 8;
        }
        ?? r13 = r12;
        if (this.bs) {
            r13 = (r12 == true ? 1 : 0) | 16;
        }
        int i10 = r13 | 64;
        byte[] bArr = new byte[16];
        for (int i11 = 0; i11 < 16; i11++) {
            bArr[i11] = 0;
        }
        byte[] bArr2 = this.credentialId;
        return r.i0(r.i0(r.i0(r.i0(r.i0(r.i0(bArrDigest, new byte[]{(byte) i10}), new byte[]{0, 0, 0, 0}), bArr), new byte[]{(byte) (bArr2.length >> 8), (byte) bArr2.length}), this.credentialId), this.credentialPublicKey);
    }

    public final byte[] defaultAttestationObject$credentials_release() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("fmt", "none");
        linkedHashMap.put("attStmt", a0.f19683i);
        linkedHashMap.put("authData", authData());
        return new Cbor().encode(linkedHashMap);
    }

    public final byte[] getAttestationObject() {
        return this.attestationObject;
    }

    @Override // androidx.credentials.webauthn.AuthenticatorResponse
    public JSONObject getClientJson() {
        return this.clientJson;
    }

    @Override // androidx.credentials.webauthn.AuthenticatorResponse
    public JSONObject json() throws JSONException {
        byte[] bytes = getClientJson().toString().getBytes(a.f19924a);
        JSONObject jSONObject = new JSONObject();
        if (this.clientDataHash == null) {
            jSONObject.put("clientDataJSON", WebAuthnUtils.INSTANCE.b64Encode(bytes));
        }
        jSONObject.put("attestationObject", WebAuthnUtils.INSTANCE.b64Encode(this.attestationObject));
        jSONObject.put("transports", new JSONArray((Collection) t7.a.E("internal", "hybrid")));
        return jSONObject;
    }

    public final void setAttestationObject(byte[] bArr) {
        this.attestationObject = bArr;
    }

    @Override // androidx.credentials.webauthn.AuthenticatorResponse
    public void setClientJson(JSONObject jSONObject) {
        this.clientJson = jSONObject;
    }

    public /* synthetic */ AuthenticatorAttestationResponse(PublicKeyCredentialCreationOptions publicKeyCredentialCreationOptions, byte[] bArr, byte[] bArr2, String str, boolean z, boolean z5, boolean z10, boolean z11, String str2, byte[] bArr3, int i10, h hVar) {
        this(publicKeyCredentialCreationOptions, bArr, bArr2, str, z, z5, z10, z11, (i10 & 256) != 0 ? null : str2, (i10 & 512) != 0 ? null : bArr3);
    }
}
