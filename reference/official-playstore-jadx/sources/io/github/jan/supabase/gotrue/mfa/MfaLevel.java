package io.github.jan.supabase.gotrue.mfa;

import io.ktor.http.LinkHeader;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lio/github/jan/supabase/gotrue/mfa/MfaLevel;", "", "current", "Lio/github/jan/supabase/gotrue/mfa/AuthenticatorAssuranceLevel;", LinkHeader.Rel.Next, "(Lio/github/jan/supabase/gotrue/mfa/AuthenticatorAssuranceLevel;Lio/github/jan/supabase/gotrue/mfa/AuthenticatorAssuranceLevel;)V", "getCurrent", "()Lio/github/jan/supabase/gotrue/mfa/AuthenticatorAssuranceLevel;", "getNext", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class MfaLevel {
    private final AuthenticatorAssuranceLevel current;
    private final AuthenticatorAssuranceLevel next;

    public MfaLevel(AuthenticatorAssuranceLevel authenticatorAssuranceLevel, AuthenticatorAssuranceLevel authenticatorAssuranceLevel2) {
        this.current = authenticatorAssuranceLevel;
        this.next = authenticatorAssuranceLevel2;
    }

    public static /* synthetic */ MfaLevel copy$default(MfaLevel mfaLevel, AuthenticatorAssuranceLevel authenticatorAssuranceLevel, AuthenticatorAssuranceLevel authenticatorAssuranceLevel2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            authenticatorAssuranceLevel = mfaLevel.current;
        }
        if ((i10 & 2) != 0) {
            authenticatorAssuranceLevel2 = mfaLevel.next;
        }
        return mfaLevel.copy(authenticatorAssuranceLevel, authenticatorAssuranceLevel2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final AuthenticatorAssuranceLevel getCurrent() {
        return this.current;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final AuthenticatorAssuranceLevel getNext() {
        return this.next;
    }

    public final MfaLevel copy(AuthenticatorAssuranceLevel current, AuthenticatorAssuranceLevel next) {
        return new MfaLevel(current, next);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MfaLevel)) {
            return false;
        }
        MfaLevel mfaLevel = (MfaLevel) other;
        return this.current == mfaLevel.current && this.next == mfaLevel.next;
    }

    public final AuthenticatorAssuranceLevel getCurrent() {
        return this.current;
    }

    public final AuthenticatorAssuranceLevel getNext() {
        return this.next;
    }

    public int hashCode() {
        return this.next.hashCode() + (this.current.hashCode() * 31);
    }

    public String toString() {
        return "MfaLevel(current=" + this.current + ", next=" + this.next + ')';
    }
}
