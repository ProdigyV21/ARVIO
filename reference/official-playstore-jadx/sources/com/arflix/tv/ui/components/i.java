package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;
import com.arflix.tv.ui.screens.plugin.PluginScreenKt;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;
import com.arflix.tv.updater.UpdateStatus;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class i implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8067i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.a f8068l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f8069m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f8070n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f8071o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f8072p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f8073q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f8074r;

    public /* synthetic */ i(UpdateStatus updateStatus, r7.a aVar, r7.a aVar2, r7.a aVar3, r7.a aVar4, r7.a aVar5, int i10) {
        this.f8071o = updateStatus;
        this.f8068l = aVar;
        this.f8069m = aVar2;
        this.f8072p = aVar3;
        this.f8073q = aVar4;
        this.f8074r = aVar5;
        this.f8070n = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f8067i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return AppUpdateModalKt.AppUpdateModal$lambda$8((UpdateStatus) this.f8071o, this.f8068l, (r7.a) this.f8069m, (r7.a) this.f8072p, (r7.a) this.f8073q, (r7.a) this.f8074r, this.f8070n, (Composer) obj, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj2).intValue();
                return PluginScreenKt.WarningDialog$lambda$4((String) this.f8071o, (String) this.f8072p, (String) this.f8073q, (String) this.f8074r, this.f8068l, (r7.a) this.f8069m, this.f8070n, (Composer) obj, iIntValue2);
            default:
                int iIntValue3 = ((Integer) obj2).intValue();
                return SettingsScreenKt.CatalogDiscoveryTextInputDialog$lambda$4((String) this.f8071o, (String) this.f8069m, (String) this.f8072p, (String) this.f8073q, (r7.l) this.f8074r, this.f8068l, this.f8070n, (Composer) obj, iIntValue3);
        }
    }

    public /* synthetic */ i(String str, String str2, String str3, String str4, r7.a aVar, r7.a aVar2, int i10) {
        this.f8071o = str;
        this.f8072p = str2;
        this.f8073q = str3;
        this.f8074r = str4;
        this.f8068l = aVar;
        this.f8069m = aVar2;
        this.f8070n = i10;
    }

    public /* synthetic */ i(String str, String str2, String str3, String str4, r7.l lVar, r7.a aVar, int i10) {
        this.f8071o = str;
        this.f8069m = str2;
        this.f8072p = str3;
        this.f8073q = str4;
        this.f8074r = lVar;
        this.f8068l = aVar;
        this.f8070n = i10;
    }
}
