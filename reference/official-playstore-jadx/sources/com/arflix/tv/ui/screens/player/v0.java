package com.arflix.tv.ui.screens.player;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusRequester;
import com.arflix.tv.data.model.CatalogConfig;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;
import java.io.Serializable;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class v0 implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9774i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f9775l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Serializable f9776m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f9777n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f9778o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f9779p;

    public /* synthetic */ v0(MediaType mediaType, FocusRequester focusRequester, boolean z, FocusRequester focusRequester2, FocusRequester focusRequester3) {
        this.f9776m = mediaType;
        this.f9777n = focusRequester;
        this.f9775l = z;
        this.f9778o = focusRequester2;
        this.f9779p = focusRequester3;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f9774i) {
            case 0:
                return PlayerScreenKt.PlayerScreen$lambda$362$2$9$0$1$0$33$0((MediaType) this.f9776m, (FocusRequester) this.f9777n, this.f9775l, (FocusRequester) this.f9778o, (FocusRequester) this.f9779p);
            default:
                return SettingsScreenKt.CatalogsSettings$lambda$6$2$0$2$2$0(this.f9775l, (CatalogConfig) this.f9776m, (r7.l) this.f9777n, (MutableState) this.f9778o, (MutableState) this.f9779p);
        }
    }

    public /* synthetic */ v0(boolean z, CatalogConfig catalogConfig, r7.l lVar, MutableState mutableState, MutableState mutableState2) {
        this.f9775l = z;
        this.f9776m = catalogConfig;
        this.f9777n = lVar;
        this.f9778o = mutableState;
        this.f9779p = mutableState2;
    }
}
