package com.arflix.tv.ui.components;

import android.content.Context;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.arflix.tv.ui.screens.profile.ProfileSelectionScreenKt;
import com.arflix.tv.ui.screens.profile.ProfileViewModel;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8323i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f8324l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f8325m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f8326n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f8327o;

    public /* synthetic */ r(boolean z, Object obj, Object obj2, Object obj3, int i10) {
        this.f8323i = i10;
        this.f8324l = z;
        this.f8325m = obj;
        this.f8326n = obj2;
        this.f8327o = obj3;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f8323i) {
            case 0:
                return CardLayoutModeKt.CatalogueRowLayoutToggleButton$lambda$1$0(this.f8324l, (ka.k0) this.f8325m, (Context) this.f8326n, (String) this.f8327o);
            case 1:
                return ProfileSelectionScreenKt.ProfileSelectionScreen$lambda$13$0$3$0(this.f8324l, (ProfileViewModel) this.f8325m, (MutableState) this.f8326n, (State) this.f8327o);
            default:
                return ProfileSelectionScreenKt.ProfileSelectionScreen$lambda$13$0$4$0(this.f8324l, (r7.a) this.f8325m, (MutableState) this.f8326n, (State) this.f8327o);
        }
    }
}
