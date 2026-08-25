package com.arflix.tv.ui.screens.details;

import androidx.compose.foundation.ScrollState;
import androidx.compose.runtime.MutableState;
import com.arflix.tv.data.model.Episode;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class e implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8761i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f8762l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f8763m;

    public /* synthetic */ e(MutableState mutableState, MutableState mutableState2) {
        this.f8761i = 3;
        this.f8762l = mutableState;
        this.f8763m = mutableState2;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f8761i) {
            case 0:
                return Float.valueOf(DetailsScreenKt.DetailsContent$lambda$10$0((ScrollState) this.f8763m, (MutableState) this.f8762l));
            case 1:
                return DetailsScreenKt.DetailsScreen$lambda$95$0((r7.a) this.f8763m, (MutableState) this.f8762l);
            case 2:
                return DetailsScreenKt.DetailsScreen$lambda$97$9$2$0((DetailsViewModel) this.f8763m, (Episode) this.f8762l);
            default:
                return DetailsScreenKt.DetailsScreen$lambda$97$9$3$0((MutableState) this.f8762l, (MutableState) this.f8763m);
        }
    }

    public /* synthetic */ e(Object obj, Object obj2, int i10) {
        this.f8761i = i10;
        this.f8763m = obj;
        this.f8762l = obj2;
    }
}
