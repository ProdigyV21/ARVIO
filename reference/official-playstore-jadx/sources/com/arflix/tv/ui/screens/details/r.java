package com.arflix.tv.ui.screens.details;

import android.content.res.Configuration;
import androidx.compose.runtime.Composer;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8959i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ List f8960l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f8961m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ FocusSection f8962n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ boolean f8963o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ float f8964p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ float f8965q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ r7.l f8966r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ int f8967s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ Object f8968t;

    public /* synthetic */ r(List list, int i10, FocusSection focusSection, Configuration configuration, float f10, float f11, boolean z, r7.l lVar, int i11) {
        this.f8959i = 2;
        this.f8960l = list;
        this.f8961m = i10;
        this.f8962n = focusSection;
        this.f8968t = configuration;
        this.f8964p = f10;
        this.f8965q = f11;
        this.f8963o = z;
        this.f8966r = lVar;
        this.f8967s = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f8959i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return DetailsScreenKt.DetailsCollectionRail_nbWgWpA$lambda$1(this.f8960l, (String) this.f8968t, this.f8961m, this.f8962n, this.f8963o, this.f8964p, this.f8965q, this.f8966r, this.f8967s, (Composer) obj, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj2).intValue();
                return DetailsScreenKt.DetailsSimilarRail_nbWgWpA$lambda$1(this.f8960l, (Map) this.f8968t, this.f8961m, this.f8962n, this.f8963o, this.f8964p, this.f8965q, this.f8966r, this.f8967s, (Composer) obj, iIntValue2);
            default:
                int iIntValue3 = ((Integer) obj2).intValue();
                return DetailsScreenKt.DetailsEpisodeRail_3GLzNTs$lambda$3(this.f8960l, this.f8961m, this.f8962n, (Configuration) this.f8968t, this.f8964p, this.f8965q, this.f8963o, this.f8966r, this.f8967s, (Composer) obj, iIntValue3);
        }
    }

    public /* synthetic */ r(List list, Object obj, int i10, FocusSection focusSection, boolean z, float f10, float f11, r7.l lVar, int i11, int i12) {
        this.f8959i = i12;
        this.f8960l = list;
        this.f8968t = obj;
        this.f8961m = i10;
        this.f8962n = focusSection;
        this.f8963o = z;
        this.f8964p = f10;
        this.f8965q = f11;
        this.f8966r = lVar;
        this.f8967s = i11;
    }
}
