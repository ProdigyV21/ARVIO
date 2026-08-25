package com.google.android.gms.internal.auth;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.auth.api.identity.zbc;

/* JADX INFO: loaded from: classes4.dex */
public final class j4 extends com.google.android.gms.common.api.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f13052a;

    public /* synthetic */ j4(int i10) {
        this.f13052a = i10;
    }

    @Override // com.google.android.gms.common.api.a
    public com.google.android.gms.common.api.g buildClient(Context context, Looper looper, com.google.android.gms.common.internal.h hVar, Object obj, com.google.android.gms.common.api.internal.g gVar, com.google.android.gms.common.api.internal.o oVar) {
        switch (this.f13052a) {
            case 0:
                return new e4(context, looper, 224, hVar, gVar, oVar);
            case 1:
                return new com.google.android.gms.internal.cast.t(context, looper, 432, hVar, gVar, oVar);
            case 2:
            case 3:
            case 9:
            case 10:
            default:
                return super.buildClient(context, looper, hVar, obj, gVar, oVar);
            case 4:
                return new m4.s(context, looper, hVar, gVar, oVar);
            case 5:
                return new m4.j(context, looper, hVar, gVar, oVar);
            case 6:
                return new m4.r(context, looper, (zbc) obj, hVar, gVar, oVar);
            case 7:
                return new n4.n(context, looper, hVar, gVar, oVar);
            case 8:
                return new n4.n(context, looper, hVar, gVar, oVar);
            case 11:
                return new u3.d(context, looper, hVar, (com.google.android.gms.common.internal.c0) obj, gVar, oVar);
        }
    }

    @Override // com.google.android.gms.common.api.a
    public com.google.android.gms.common.api.g buildClient(Context context, Looper looper, com.google.android.gms.common.internal.h hVar, Object obj, com.google.android.gms.common.api.o oVar, com.google.android.gms.common.api.p pVar) {
        switch (this.f13052a) {
            case 2:
                return new com.google.android.gms.internal.cast.v3(context, looper, hVar, oVar, pVar);
            case 3:
                return new com.google.android.gms.internal.fido.b2(context, looper, 148, hVar, oVar, pVar);
            case 9:
                hVar.getClass();
                Integer num = hVar.f12852h;
                Bundle bundle = new Bundle();
                bundle.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", hVar.f12845a);
                if (num != null) {
                    bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", num.intValue());
                }
                bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", false);
                bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", false);
                bundle.putString("com.google.android.gms.signin.internal.serverClientId", null);
                bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
                bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", false);
                bundle.putString("com.google.android.gms.signin.internal.hostedDomain", null);
                bundle.putString("com.google.android.gms.signin.internal.logSessionId", null);
                bundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", false);
                return new u4.a(context, looper, hVar, bundle, oVar, pVar);
            case 10:
                obj.getClass();
                throw new ClassCastException();
            default:
                return super.buildClient(context, looper, hVar, obj, oVar, pVar);
        }
    }
}
