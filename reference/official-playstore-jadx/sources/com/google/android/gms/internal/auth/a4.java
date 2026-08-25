package com.google.android.gms.internal.auth;

/* JADX INFO: loaded from: classes4.dex */
public final class a4 implements z3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c1 f12994a;

    static {
        d1 d1Var = new d1(b1.a(), true, true);
        f12994a = d1Var.n("Aang__create_auth_exception_with_pending_intent", false);
        d1Var.n("Aang__enable_add_account_restrictions", false);
        d1Var.n("Aang__log_missing_gaia_id_event", true);
        d1Var.n("Aang__log_obfuscated_gaiaid_status", true);
        d1Var.n("Aang__switch_clear_token_to_aang", false);
    }

    @Override // com.google.android.gms.internal.auth.z3
    public final boolean zza() {
        return ((Boolean) f12994a.b()).booleanValue();
    }
}
