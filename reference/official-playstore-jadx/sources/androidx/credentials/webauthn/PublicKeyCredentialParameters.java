package androidx.credentials.webauthn;

import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Landroidx/credentials/webauthn/PublicKeyCredentialParameters;", "", LinkHeader.Parameters.Type, "", "alg", "", "(Ljava/lang/String;J)V", "getAlg", "()J", "getType", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class PublicKeyCredentialParameters {
    private final long alg;
    private final String type;

    public PublicKeyCredentialParameters(String str, long j10) {
        this.type = str;
        this.alg = j10;
    }

    public static /* synthetic */ PublicKeyCredentialParameters copy$default(PublicKeyCredentialParameters publicKeyCredentialParameters, String str, long j10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = publicKeyCredentialParameters.type;
        }
        if ((i10 & 2) != 0) {
            j10 = publicKeyCredentialParameters.alg;
        }
        return publicKeyCredentialParameters.copy(str, j10);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getAlg() {
        return this.alg;
    }

    public final PublicKeyCredentialParameters copy(String type, long alg) {
        return new PublicKeyCredentialParameters(type, alg);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PublicKeyCredentialParameters)) {
            return false;
        }
        PublicKeyCredentialParameters publicKeyCredentialParameters = (PublicKeyCredentialParameters) other;
        return p.a(this.type, publicKeyCredentialParameters.type) && this.alg == publicKeyCredentialParameters.alg;
    }

    public final long getAlg() {
        return this.alg;
    }

    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        int iHashCode = this.type.hashCode() * 31;
        long j10 = this.alg;
        return iHashCode + ((int) (j10 ^ (j10 >>> 32)));
    }

    public String toString() {
        return "PublicKeyCredentialParameters(type=" + this.type + ", alg=" + this.alg + ')';
    }
}
