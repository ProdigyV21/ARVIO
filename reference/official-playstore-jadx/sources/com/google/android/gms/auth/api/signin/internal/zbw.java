package com.google.android.gms.auth.api.signin.internal;

import android.os.Bundle;
import androidx.loader.app.a;
import androidx.loader.content.e;
import com.google.android.gms.common.api.q;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
final class zbw implements a {
    final /* synthetic */ SignInHubActivity zba;

    public /* synthetic */ zbw(SignInHubActivity signInHubActivity, zbv zbvVar) {
        this.zba = signInHubActivity;
    }

    @Override // androidx.loader.app.a
    public final e onCreateLoader(int i10, Bundle bundle) {
        SignInHubActivity signInHubActivity = this.zba;
        Set set = q.f12827a;
        synchronized (set) {
        }
        return new zbc(signInHubActivity, set);
    }

    @Override // androidx.loader.app.a
    public final /* bridge */ /* synthetic */ void onLoadFinished(e eVar, Object obj) {
        SignInHubActivity signInHubActivity = this.zba;
        signInHubActivity.setResult(signInHubActivity.zbe, signInHubActivity.zbf);
        this.zba.finish();
    }

    @Override // androidx.loader.app.a
    public final void onLoaderReset(e eVar) {
    }
}
