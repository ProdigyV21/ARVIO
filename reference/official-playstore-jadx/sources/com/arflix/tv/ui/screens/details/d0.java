package com.arflix.tv.ui.screens.details;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class d0 implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8756i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f8757l;

    public /* synthetic */ d0(Object obj, int i10) {
        this.f8756i = i10;
        this.f8757l = obj;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f8756i) {
            case 0:
                return DetailsScreenKt.DetailsScreen$lambda$97$1$0((DetailsViewModel) this.f8757l);
            case 1:
                return DetailsScreenKt.DetailsScreen$lambda$97$13$0$0((DetailsViewModel) this.f8757l);
            default:
                return DetailsScreenKt.CircularCastCard$lambda$1$0((r7.a) this.f8757l);
        }
    }
}
