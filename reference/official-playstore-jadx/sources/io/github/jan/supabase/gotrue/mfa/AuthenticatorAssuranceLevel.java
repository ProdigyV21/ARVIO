package io.github.jan.supabase.gotrue.mfa;

import g7.a;
import g7.b;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u0000 \u00052\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0006"}, d2 = {"Lio/github/jan/supabase/gotrue/mfa/AuthenticatorAssuranceLevel;", "", "(Ljava/lang/String;I)V", "AAL1", "AAL2", "Companion", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class AuthenticatorAssuranceLevel {
    private static final /* synthetic */ a $ENTRIES;
    private static final /* synthetic */ AuthenticatorAssuranceLevel[] $VALUES;
    public static final AuthenticatorAssuranceLevel AAL1 = new AuthenticatorAssuranceLevel("AAL1", 0);
    public static final AuthenticatorAssuranceLevel AAL2 = new AuthenticatorAssuranceLevel("AAL2", 1);

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lio/github/jan/supabase/gotrue/mfa/AuthenticatorAssuranceLevel$Companion;", "", "()V", "from", "Lio/github/jan/supabase/gotrue/mfa/AuthenticatorAssuranceLevel;", "value", "", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final AuthenticatorAssuranceLevel from(String value) {
            if (value.equals("aal1")) {
                return AuthenticatorAssuranceLevel.AAL1;
            }
            if (value.equals("aal2")) {
                return AuthenticatorAssuranceLevel.AAL2;
            }
            throw new IllegalArgumentException("Unknown AuthenticatorAssuranceLevel: ".concat(value));
        }

        private Companion() {
        }
    }

    private static final /* synthetic */ AuthenticatorAssuranceLevel[] $values() {
        return new AuthenticatorAssuranceLevel[]{AAL1, AAL2};
    }

    static {
        AuthenticatorAssuranceLevel[] authenticatorAssuranceLevelArr$values = $values();
        $VALUES = authenticatorAssuranceLevelArr$values;
        $ENTRIES = new b(authenticatorAssuranceLevelArr$values);
        INSTANCE = new Companion(null);
    }

    private AuthenticatorAssuranceLevel(String str, int i10) {
    }

    public static a<AuthenticatorAssuranceLevel> getEntries() {
        return $ENTRIES;
    }

    public static AuthenticatorAssuranceLevel valueOf(String str) {
        return (AuthenticatorAssuranceLevel) Enum.valueOf(AuthenticatorAssuranceLevel.class, str);
    }

    public static AuthenticatorAssuranceLevel[] values() {
        return (AuthenticatorAssuranceLevel[]) $VALUES.clone();
    }
}
