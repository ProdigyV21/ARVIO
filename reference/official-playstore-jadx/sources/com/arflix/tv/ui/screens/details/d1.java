package com.arflix.tv.ui.screens.details;

import androidx.compose.foundation.gestures.ScrollScope;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.ui.screens.details.DetailsScreenKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class d1 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8758i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f8759l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f8760m;

    public /* synthetic */ d1(Object obj, Object obj2, int i10) {
        this.f8758i = i10;
        this.f8759l = obj;
        this.f8760m = obj2;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f8758i) {
            case 0:
                return DetailsScreenKt.AnonymousClass2.invokeSuspend$lambda$0((kotlin.jvm.internal.h0) this.f8759l, (ScrollScope) this.f8760m, ((Float) obj).floatValue(), ((Float) obj2).floatValue());
            default:
                return DetailsScreenKt.DetailsScreen$lambda$97$2$0((DetailsViewModel) this.f8759l, (r7.p) this.f8760m, (MediaType) obj, ((Integer) obj2).intValue());
        }
    }
}
