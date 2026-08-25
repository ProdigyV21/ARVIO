package com.arflix.tv.ui.screens.login;

import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.MutableState;
import com.arflix.tv.ui.screens.plugin.PluginScreenKt;
import com.arflix.tv.ui.screens.plugin.PluginViewModel;
import java.util.List;
import r7.p;
import r7.q;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class c implements q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9350i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f9351l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f9352m;

    public /* synthetic */ c(Object obj, Object obj2, int i10) {
        this.f9350i = i10;
        this.f9351l = obj;
        this.f9352m = obj2;
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        switch (this.f9350i) {
            case 0:
                return LoginScreenKt.PremiumTextField_qQqZOpQ$lambda$1$1((String) this.f9351l, (String) this.f9352m, (p) obj, (Composer) obj2, ((Integer) obj3).intValue());
            case 1:
                return PluginScreenKt.PluginScreen$lambda$18$2((List) this.f9351l, (MutableState) this.f9352m, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            default:
                return PluginScreenKt.PluginScreen$lambda$18$3((List) this.f9351l, (PluginViewModel) this.f9352m, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    }
}
