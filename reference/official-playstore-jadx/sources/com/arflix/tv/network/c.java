package com.arflix.tv.network;

import android.content.Context;
import com.arflix.tv.ui.screens.login.LoginScreenKt;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class c implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7774i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Context f7775l;

    public /* synthetic */ c(Context context, int i10) {
        this.f7774i = i10;
        this.f7775l = context;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f7774i) {
            case 0:
                return OkHttpProvider.createCoilImageLoader$lambda$1(this.f7775l);
            default:
                return LoginScreenKt.LoginScreen$lambda$23$0$1$11$0(this.f7775l);
        }
    }
}
