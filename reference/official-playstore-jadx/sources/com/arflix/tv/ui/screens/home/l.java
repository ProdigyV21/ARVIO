package com.arflix.tv.ui.screens.home;

import com.arflix.tv.data.model.MediaItem;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class l implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9184i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f9185l;

    public /* synthetic */ l(Object obj, int i10) {
        this.f9184i = i10;
        this.f9185l = obj;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f9184i) {
            case 0:
                return HomeScreenKt.ContentRow_IOMxRjY$lambda$19$1$0$0$0((List) this.f9185l, ((Integer) obj).intValue(), (MediaItem) obj2);
            case 1:
                return HomeScreenKt.HomeScreen$lambda$77$8$5$0((HomeViewModel) this.f9185l, (String) obj, ((Integer) obj2).intValue());
            default:
                return HomeScreenKt.HomeScreen$lambda$77$10$0$0((r7.p) this.f9185l, (String) obj, (String) obj2);
        }
    }
}
