package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Scope;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes4.dex */
public abstract class k extends f implements com.google.android.gms.common.api.g, f0 {
    private static volatile Executor zaa;
    private final h zab;
    private final Set zac;
    private final Account zad;

    /* JADX WARN: Illegal instructions before constructor call */
    public k(Context context, Looper looper, int i10, h hVar, com.google.android.gms.common.api.internal.g gVar, com.google.android.gms.common.api.internal.o oVar) {
        x0 x0VarA = l.a(context);
        Object obj = o3.e.f20740c;
        t.i(gVar);
        t.i(oVar);
        super(context, looper, x0VarA, i10, new u(gVar), new u(oVar), hVar.f12850f);
        this.zab = hVar;
        this.zad = hVar.f12845a;
        Set<Scope> set = hVar.f12847c;
        Set<Scope> setValidateScopes = validateScopes(set);
        Iterator<Scope> it = setValidateScopes.iterator();
        while (it.hasNext()) {
            if (!set.contains(it.next())) {
                throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
            }
        }
        this.zac = setValidateScopes;
    }

    public static void zag(Executor executor) {
        zaa = executor;
    }

    @Override // com.google.android.gms.common.internal.f
    public final Account getAccount() {
        return this.zad;
    }

    @Override // com.google.android.gms.common.internal.f
    public Executor getBindServiceExecutor() {
        return zaa;
    }

    public final h getClientSettings() {
        return this.zab;
    }

    public o3.d[] getRequiredFeatures() {
        return new o3.d[0];
    }

    @Override // com.google.android.gms.common.internal.f
    public final Set<Scope> getScopes() {
        return this.zac;
    }

    @Override // com.google.android.gms.common.api.g
    public Set<Scope> getScopesForConnectionlessNonSignIn() {
        return requiresSignIn() ? this.zac : Collections.EMPTY_SET;
    }

    public Set<Scope> validateScopes(Set<Scope> set) {
        return set;
    }
}
