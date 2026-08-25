package com.arflix.tv.ui.screens.settings;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.ui.platform.ClipboardManager;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class p1 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10782i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f10783l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.a f10784m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r7.a f10785n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f10786o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f10787p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f10788q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f10789r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Object f10790s;

    public /* synthetic */ p1(String str, String str2, String str3, r7.l lVar, r7.l lVar2, r7.a aVar, r7.a aVar2, int i10) {
        this.f10783l = str;
        this.f10787p = str2;
        this.f10788q = str3;
        this.f10789r = lVar;
        this.f10790s = lVar2;
        this.f10784m = aVar;
        this.f10785n = aVar2;
        this.f10786o = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f10782i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return SettingsScreenKt.InputModalLegacy$lambda$6(this.f10784m, this.f10786o, (List) this.f10787p, (ClipboardManager) this.f10789r, this.f10785n, (MutableIntState) this.f10790s, this.f10783l, (List) this.f10788q, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return SettingsScreenKt.QualityFilterEditorModal$lambda$9(this.f10783l, (String) this.f10787p, (String) this.f10788q, (r7.l) this.f10789r, (r7.l) this.f10790s, this.f10784m, this.f10785n, this.f10786o, (Composer) obj, iIntValue2);
        }
    }

    public /* synthetic */ p1(r7.a aVar, int i10, List list, ClipboardManager clipboardManager, r7.a aVar2, MutableIntState mutableIntState, String str, List list2) {
        this.f10784m = aVar;
        this.f10786o = i10;
        this.f10787p = list;
        this.f10789r = clipboardManager;
        this.f10785n = aVar2;
        this.f10790s = mutableIntState;
        this.f10783l = str;
        this.f10788q = list2;
    }
}
