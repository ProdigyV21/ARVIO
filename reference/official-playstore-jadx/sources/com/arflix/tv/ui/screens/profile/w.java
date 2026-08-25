package com.arflix.tv.ui.screens.profile;

import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import androidx.navigation.l0;
import com.arflix.tv.data.model.EpisodeIdentity;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.navigation.AppNavigationKt;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class w implements r7.r {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10075i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f10076l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f10077m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f10078n;

    public /* synthetic */ w(int i10, int i11, List list, r7.l lVar) {
        this.f10075i = i11;
        this.f10076l = list;
        this.f10077m = i10;
        this.f10078n = lVar;
    }

    @Override // r7.r
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        switch (this.f10075i) {
            case 0:
                int iIntValue = ((Integer) obj4).intValue();
                return ProfileDialogsKt.ProfileDialogContent$lambda$10$0$0$5$0$0$1((List) this.f10076l, this.f10077m, (r7.l) this.f10078n, (LazyItemScope) obj, ((Integer) obj2).intValue(), (Composer) obj3, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj4).intValue();
                return ProfileDialogsKt.ProfileDialogContent$lambda$10$0$1$1$0$0$0$1((List) this.f10076l, this.f10077m, (r7.l) this.f10078n, (LazyItemScope) obj, ((Integer) obj2).intValue(), (Composer) obj3, iIntValue2);
            default:
                return AppNavigationKt.AppNavigation$lambda$9$0$35$13$0((l0) this.f10076l, (MediaType) this.f10078n, this.f10077m, (EpisodeIdentity) obj, (String) obj2, (String) obj3, (String) obj4);
        }
    }

    public /* synthetic */ w(l0 l0Var, MediaType mediaType, int i10) {
        this.f10075i = 2;
        this.f10076l = l0Var;
        this.f10078n = mediaType;
        this.f10077m = i10;
    }
}
