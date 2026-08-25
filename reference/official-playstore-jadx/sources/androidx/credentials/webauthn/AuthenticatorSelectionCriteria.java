package androidx.credentials.webauthn;

import androidx.compose.foundation.c;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u0019"}, d2 = {"Landroidx/credentials/webauthn/AuthenticatorSelectionCriteria;", "", "authenticatorAttachment", "", "residentKey", "requireResidentKey", "", "userVerification", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V", "getAuthenticatorAttachment", "()Ljava/lang/String;", "getRequireResidentKey", "()Z", "getResidentKey", "getUserVerification", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class AuthenticatorSelectionCriteria {
    private final String authenticatorAttachment;
    private final boolean requireResidentKey;
    private final String residentKey;
    private final String userVerification;

    public AuthenticatorSelectionCriteria(String str, String str2, boolean z, String str3) {
        this.authenticatorAttachment = str;
        this.residentKey = str2;
        this.requireResidentKey = z;
        this.userVerification = str3;
    }

    public static /* synthetic */ AuthenticatorSelectionCriteria copy$default(AuthenticatorSelectionCriteria authenticatorSelectionCriteria, String str, String str2, boolean z, String str3, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = authenticatorSelectionCriteria.authenticatorAttachment;
        }
        if ((i10 & 2) != 0) {
            str2 = authenticatorSelectionCriteria.residentKey;
        }
        if ((i10 & 4) != 0) {
            z = authenticatorSelectionCriteria.requireResidentKey;
        }
        if ((i10 & 8) != 0) {
            str3 = authenticatorSelectionCriteria.userVerification;
        }
        return authenticatorSelectionCriteria.copy(str, str2, z, str3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAuthenticatorAttachment() {
        return this.authenticatorAttachment;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getResidentKey() {
        return this.residentKey;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getRequireResidentKey() {
        return this.requireResidentKey;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getUserVerification() {
        return this.userVerification;
    }

    public final AuthenticatorSelectionCriteria copy(String authenticatorAttachment, String residentKey, boolean requireResidentKey, String userVerification) {
        return new AuthenticatorSelectionCriteria(authenticatorAttachment, residentKey, requireResidentKey, userVerification);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AuthenticatorSelectionCriteria)) {
            return false;
        }
        AuthenticatorSelectionCriteria authenticatorSelectionCriteria = (AuthenticatorSelectionCriteria) other;
        return p.a(this.authenticatorAttachment, authenticatorSelectionCriteria.authenticatorAttachment) && p.a(this.residentKey, authenticatorSelectionCriteria.residentKey) && this.requireResidentKey == authenticatorSelectionCriteria.requireResidentKey && p.a(this.userVerification, authenticatorSelectionCriteria.userVerification);
    }

    public final String getAuthenticatorAttachment() {
        return this.authenticatorAttachment;
    }

    public final boolean getRequireResidentKey() {
        return this.requireResidentKey;
    }

    public final String getResidentKey() {
        return this.residentKey;
    }

    public final String getUserVerification() {
        return this.userVerification;
    }

    public int hashCode() {
        return this.userVerification.hashCode() + ((c.c(this.authenticatorAttachment.hashCode() * 31, 31, this.residentKey) + (this.requireResidentKey ? 1231 : 1237)) * 31);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("AuthenticatorSelectionCriteria(authenticatorAttachment=");
        sb2.append(this.authenticatorAttachment);
        sb2.append(", residentKey=");
        sb2.append(this.residentKey);
        sb2.append(", requireResidentKey=");
        sb2.append(this.requireResidentKey);
        sb2.append(", userVerification=");
        return c.u(sb2, this.userVerification, ')');
    }

    public /* synthetic */ AuthenticatorSelectionCriteria(String str, String str2, boolean z, String str3, int i10, h hVar) {
        this(str, str2, (i10 & 4) != 0 ? false : z, (i10 & 8) != 0 ? "preferred" : str3);
    }
}
