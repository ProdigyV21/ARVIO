package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;
import com.arflix.tv.data.model.CatalogPackManifest;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class o implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8213i = 2;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f8214l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f8215m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r7.a f8216n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f8217o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f8218p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ x6.o f8219q;

    public /* synthetic */ o(CatalogPackManifest catalogPackManifest, boolean z, String str, r7.l lVar, r7.a aVar, int i10) {
        this.f8218p = catalogPackManifest;
        this.f8215m = z;
        this.f8214l = str;
        this.f8219q = lVar;
        this.f8216n = aVar;
        this.f8217o = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f8213i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return AudioTrackSelectorKt.AudioTrackSelector$lambda$5(this.f8215m, (List) this.f8218p, this.f8214l, (r7.l) this.f8219q, this.f8216n, this.f8217o, (Composer) obj, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj2).intValue();
                return SettingsScreenKt.CloudPairModal$lambda$7(this.f8214l, (String) this.f8218p, this.f8215m, this.f8216n, (r7.a) this.f8219q, this.f8217o, (Composer) obj, iIntValue2);
            default:
                int iIntValue3 = ((Integer) obj2).intValue();
                return SettingsScreenKt.CatalogPackImportDialog$lambda$6((CatalogPackManifest) this.f8218p, this.f8215m, this.f8214l, (r7.l) this.f8219q, this.f8216n, this.f8217o, (Composer) obj, iIntValue3);
        }
    }

    public /* synthetic */ o(String str, String str2, boolean z, r7.a aVar, r7.a aVar2, int i10) {
        this.f8214l = str;
        this.f8218p = str2;
        this.f8215m = z;
        this.f8216n = aVar;
        this.f8219q = aVar2;
        this.f8217o = i10;
    }

    public /* synthetic */ o(boolean z, List list, String str, r7.l lVar, r7.a aVar, int i10) {
        this.f8215m = z;
        this.f8218p = list;
        this.f8214l = str;
        this.f8219q = lVar;
        this.f8216n = aVar;
        this.f8217o = i10;
    }
}
