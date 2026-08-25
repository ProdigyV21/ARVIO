package com.google.android.gms.internal.auth;

import android.util.Base64;

/* JADX INFO: loaded from: classes4.dex */
public final class d4 implements c4 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c1 f13020a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c1 f13021b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c1 f13022c;

    static {
        d1 d1Var = new d1(b1.a(), true, true);
        Double dValueOf = Double.valueOf(0.0d);
        new c1(d1Var, "getTokenRefactor__account_data_service_sample_percentage", dValueOf, 2);
        d1Var.n("getTokenRefactor__account_data_service_tokenAPI_usable", true);
        d1Var.m(20L, "getTokenRefactor__account_manager_timeout_seconds");
        d1Var.m(0L, "getTokenRefactor__android_id_shift");
        try {
            f13020a = new c1(d1Var, "getTokenRefactor__blocked_packages", x3.j(Base64.decode("ChNjb20uYW5kcm9pZC52ZW5kaW5nCiBjb20uZ29vZ2xlLmFuZHJvaWQuYXBwcy5tZWV0aW5ncwohY29tLmdvb2dsZS5hbmRyb2lkLmFwcHMubWVzc2FnaW5n", 3)), 3);
            d1Var.n("getTokenRefactor__chimera_get_token_evolved", true);
            d1Var.m(20L, "getTokenRefactor__clear_token_timeout_seconds");
            d1Var.m(20L, "getTokenRefactor__default_task_timeout_seconds");
            f13021b = d1Var.n("getTokenRefactor__gaul_accounts_api_evolved", false);
            f13022c = d1Var.n("getTokenRefactor__gaul_token_api_evolved", false);
            d1Var.m(120L, "getTokenRefactor__get_token_timeout_seconds");
            d1Var.n("getTokenRefactor__gms_account_authenticator_evolved", true);
            new c1(d1Var, "getTokenRefactor__gms_account_authenticator_sample_percentage", dValueOf, 2);
        } catch (Exception e5) {
            throw new AssertionError(e5);
        }
    }

    @Override // com.google.android.gms.internal.auth.c4
    public final x3 zza() {
        return (x3) f13020a.b();
    }

    @Override // com.google.android.gms.internal.auth.c4
    public final boolean zzb() {
        return ((Boolean) f13021b.b()).booleanValue();
    }

    @Override // com.google.android.gms.internal.auth.c4
    public final boolean zzc() {
        return ((Boolean) f13022c.b()).booleanValue();
    }
}
