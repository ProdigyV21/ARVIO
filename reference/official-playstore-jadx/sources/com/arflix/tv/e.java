package com.arflix.tv;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.ui.screens.collections.CollectionDetailsScreenKt;
import com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel;
import com.arflix.tv.ui.screens.collections.CollectionTab;
import com.arflix.tv.ui.screens.crash.CrashReportActivityKt;
import r7.p;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class e implements p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7727i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f7728l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f7729m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f7730n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f7731o;

    public /* synthetic */ e(CollectionDetailsViewModel collectionDetailsViewModel, CollectionTab collectionTab, MutableState mutableState, MutableState mutableState2) {
        this.f7727i = 1;
        this.f7729m = collectionDetailsViewModel;
        this.f7730n = collectionTab;
        this.f7728l = mutableState;
        this.f7731o = mutableState2;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f7727i) {
            case 0:
                return MainActivity.onCreate$lambda$4$18((State) this.f7729m, (State) this.f7730n, (MainActivity) this.f7731o, (MutableState) this.f7728l, (Composer) obj, ((Integer) obj2).intValue());
            case 1:
                return CollectionDetailsScreenKt.CollectionDetailsScreen$lambda$31$3$0((CollectionDetailsViewModel) this.f7729m, (CollectionTab) this.f7730n, (MutableState) this.f7728l, (MutableState) this.f7731o, (MediaItem) obj, ((Integer) obj2).intValue());
            default:
                return CrashReportActivityKt.CrashReportScreen$lambda$9$0$1((String) this.f7729m, (String) this.f7730n, (String) this.f7731o, (String) this.f7728l, (Composer) obj, ((Integer) obj2).intValue());
        }
    }

    public /* synthetic */ e(Object obj, Object obj2, Object obj3, Object obj4, int i10) {
        this.f7727i = i10;
        this.f7729m = obj;
        this.f7730n = obj2;
        this.f7731o = obj3;
        this.f7728l = obj4;
    }
}
