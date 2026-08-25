package com.arflix.tv.ui.screens.settings;

import androidx.compose.runtime.Composer;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class l0 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10634i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f10635l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f10636m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r7.a f10637n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f10638o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f10639p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f10640q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f10641r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ x6.o f10642s;

    public /* synthetic */ l0(String str, String str2, r7.a aVar, String str3, String str4, r7.a aVar2, int i10, int i11) {
        this.f10635l = str;
        this.f10636m = str2;
        this.f10637n = aVar;
        this.f10640q = str3;
        this.f10641r = str4;
        this.f10642s = aVar2;
        this.f10638o = i10;
        this.f10639p = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f10634i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return SettingsScreenKt.SubtitlePickerModal$lambda$4(this.f10635l, (List) this.f10640q, this.f10636m, this.f10638o, (r7.l) this.f10641r, (r7.l) this.f10642s, this.f10637n, this.f10639p, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return SettingsScreenKt.TraktActivationModal$lambda$3(this.f10635l, this.f10636m, this.f10637n, (String) this.f10640q, (String) this.f10641r, (r7.a) this.f10642s, this.f10638o, this.f10639p, (Composer) obj, iIntValue2);
        }
    }

    public /* synthetic */ l0(String str, List list, String str2, int i10, r7.l lVar, r7.l lVar2, r7.a aVar, int i11) {
        this.f10635l = str;
        this.f10640q = list;
        this.f10636m = str2;
        this.f10638o = i10;
        this.f10641r = lVar;
        this.f10642s = lVar2;
        this.f10637n = aVar;
        this.f10639p = i11;
    }
}
