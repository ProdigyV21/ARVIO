package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.graphics.vector.ImageVector;
import com.arflix.tv.ui.screens.collections.CollectionDetailsScreenKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class w0 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8459i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f8460l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f8461m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r7.a f8462n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f8463o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f8464p;

    public /* synthetic */ w0(ImageVector imageVector, String str, boolean z, r7.a aVar, int i10) {
        this.f8464p = imageVector;
        this.f8460l = str;
        this.f8461m = z;
        this.f8462n = aVar;
        this.f8463o = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f8459i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return MediaContextMenuKt.ContextMenuItem$lambda$1((ImageVector) this.f8464p, this.f8460l, this.f8461m, this.f8462n, this.f8463o, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return CollectionDetailsScreenKt.CollectionTabChip$lambda$6(this.f8460l, this.f8461m, (FocusRequester) this.f8464p, this.f8462n, this.f8463o, (Composer) obj, iIntValue2);
        }
    }

    public /* synthetic */ w0(String str, boolean z, FocusRequester focusRequester, r7.a aVar, int i10) {
        this.f8460l = str;
        this.f8461m = z;
        this.f8464p = focusRequester;
        this.f8462n = aVar;
        this.f8463o = i10;
    }
}
