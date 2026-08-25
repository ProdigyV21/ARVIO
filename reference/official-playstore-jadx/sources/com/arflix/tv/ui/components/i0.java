package com.arflix.tv.ui.components;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.MutableState;
import com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class i0 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8075i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f8076l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Context f8077m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f8078n;

    public /* synthetic */ i0(boolean z, Context context, Object obj, int i10) {
        this.f8075i = i10;
        this.f8076l = z;
        this.f8077m = context;
        this.f8078n = obj;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f8075i) {
            case 0:
                return KeepScreenOnKt.KeepScreenOn$lambda$0$0(this.f8076l, this.f8077m, (View) this.f8078n, (DisposableEffectScope) obj);
            default:
                return LiveTvScreenKt.LiveTvScreen$lambda$173$0(this.f8076l, (Activity) this.f8077m, (MutableState) this.f8078n, (DisposableEffectScope) obj);
        }
    }
}
