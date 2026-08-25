package com.arflix.tv.ui.screens.details;

import android.content.res.Configuration;
import androidx.compose.runtime.Composer;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class j implements r7.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8826i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ List f8827l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f8828m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ FocusSection f8829n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ boolean f8830o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ float f8831p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ float f8832q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ r7.l f8833r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Object f8834s;

    public /* synthetic */ j(List list, int i10, FocusSection focusSection, Configuration configuration, float f10, float f11, boolean z, r7.l lVar) {
        this.f8826i = 0;
        this.f8827l = list;
        this.f8828m = i10;
        this.f8829n = focusSection;
        this.f8834s = configuration;
        this.f8831p = f10;
        this.f8832q = f11;
        this.f8830o = z;
        this.f8833r = lVar;
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        switch (this.f8826i) {
            case 0:
                int iIntValue = ((Integer) obj3).intValue();
                return DetailsScreenKt.DetailsTvRows_FSc51bE$lambda$13$0$1(this.f8827l, this.f8828m, this.f8829n, (Configuration) this.f8834s, this.f8831p, this.f8832q, this.f8830o, this.f8833r, (androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj3).intValue();
                return DetailsScreenKt.DetailsTvRows_FSc51bE$lambda$13$0$5(this.f8827l, (String) this.f8834s, this.f8828m, this.f8829n, this.f8830o, this.f8831p, this.f8832q, this.f8833r, (androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, iIntValue2);
            default:
                int iIntValue3 = ((Integer) obj3).intValue();
                return DetailsScreenKt.DetailsTvRows_FSc51bE$lambda$13$0$6(this.f8827l, (Map) this.f8834s, this.f8828m, this.f8829n, this.f8830o, this.f8831p, this.f8832q, this.f8833r, (androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, iIntValue3);
        }
    }

    public /* synthetic */ j(List list, Object obj, int i10, FocusSection focusSection, boolean z, float f10, float f11, r7.l lVar, int i11) {
        this.f8826i = i11;
        this.f8827l = list;
        this.f8834s = obj;
        this.f8828m = i10;
        this.f8829n = focusSection;
        this.f8830o = z;
        this.f8831p = f10;
        this.f8832q = f11;
        this.f8833r = lVar;
    }
}
