package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.o;
import com.google.android.gms.common.api.p;
import com.google.android.gms.common.internal.h;
import com.google.android.gms.common.internal.k;
import java.util.Iterator;
import java.util.Set;
import m4.l;

/* JADX INFO: loaded from: classes4.dex */
public final class zbe extends k {
    private final GoogleSignInOptions zba;

    public zbe(Context context, Looper looper, h hVar, GoogleSignInOptions googleSignInOptions, o oVar, p pVar) {
        super(context, looper, 91, hVar, oVar, pVar);
        Set set = hVar.f12847c;
        GoogleSignInOptions.Builder builder = googleSignInOptions != null ? new GoogleSignInOptions.Builder(googleSignInOptions) : new GoogleSignInOptions.Builder();
        builder.setLogSessionId(l.a());
        if (!set.isEmpty()) {
            Iterator it = set.iterator();
            while (it.hasNext()) {
                builder.requestScopes((Scope) it.next(), new Scope[0]);
            }
        }
        this.zba = builder.build();
    }

    @Override // com.google.android.gms.common.internal.f
    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.auth.api.signin.internal.ISignInService");
        return iInterfaceQueryLocalInterface instanceof zbs ? (zbs) iInterfaceQueryLocalInterface : new zbs(iBinder);
    }

    @Override // com.google.android.gms.common.internal.f, com.google.android.gms.common.api.g
    public final int getMinApkVersion() {
        return 12451000;
    }

    @Override // com.google.android.gms.common.internal.f
    public final String getServiceDescriptor() {
        return "com.google.android.gms.auth.api.signin.internal.ISignInService";
    }

    @Override // com.google.android.gms.common.internal.f
    public final Intent getSignInIntent() {
        return zbm.zbc(getContext(), this.zba);
    }

    @Override // com.google.android.gms.common.internal.f
    public final String getStartServiceAction() {
        return "com.google.android.gms.auth.api.signin.service.START";
    }

    @Override // com.google.android.gms.common.internal.f
    public final boolean providesSignIn() {
        return true;
    }

    public final GoogleSignInOptions zba() {
        return this.zba;
    }
}
