package com.arflix.tv.ui.components;

import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.ui.focus.FocusRequester;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class d0 implements r7.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7878i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f7879l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.a f7880m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f7881n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f7882o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f7883p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f7884q;

    public /* synthetic */ d0(MutableIntState mutableIntState, FocusRequester focusRequester, String str, r7.a aVar, r7.a aVar2, boolean z) {
        this.f7879l = z;
        this.f7882o = focusRequester;
        this.f7880m = aVar;
        this.f7883p = aVar2;
        this.f7884q = mutableIntState;
        this.f7881n = str;
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        switch (this.f7878i) {
            case 0:
                int iIntValue = ((Integer) obj3).intValue();
                return ContextMenuKt.ContextMenu$lambda$9(this.f7880m, this.f7879l, this.f7881n, (String) this.f7882o, (List) this.f7883p, (r7.l) this.f7884q, (AnimatedVisibilityScope) obj, (Composer) obj2, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj3).intValue();
                return SettingsScreenKt.CatalogPackDeleteConfirmDialog$lambda$5$1(this.f7879l, (FocusRequester) this.f7882o, this.f7880m, (r7.a) this.f7883p, (MutableIntState) this.f7884q, this.f7881n, (BoxScope) obj, (Composer) obj2, iIntValue2);
        }
    }

    public /* synthetic */ d0(r7.a aVar, boolean z, String str, String str2, List list, r7.l lVar) {
        this.f7880m = aVar;
        this.f7879l = z;
        this.f7881n = str;
        this.f7882o = str2;
        this.f7883p = list;
        this.f7884q = lVar;
    }
}
