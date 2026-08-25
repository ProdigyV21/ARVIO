package com.arflix.tv.ui.screens.details;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.graphics.Shadow;
import coil.ImageLoader;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class o0 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8931i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f8932l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ float f8933m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ float f8934n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f8935o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f8936p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f8937q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f8938r;

    public /* synthetic */ o0(String str, ImageLoader imageLoader, int i10, float f10, float f11, Shadow shadow, int i11) {
        this.f8936p = str;
        this.f8937q = imageLoader;
        this.f8932l = i10;
        this.f8933m = f10;
        this.f8934n = f11;
        this.f8938r = shadow;
        this.f8935o = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f8931i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return DetailsScreenKt.DetailsCastRail_6PoWaU8$lambda$1((List) this.f8936p, this.f8932l, (FocusSection) this.f8937q, this.f8933m, this.f8934n, (r7.l) this.f8938r, this.f8935o, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return DetailsScreenKt.DetailsImdbSvgRatingBadge_6PoWaU8$lambda$2((String) this.f8936p, (ImageLoader) this.f8937q, this.f8932l, this.f8933m, this.f8934n, (Shadow) this.f8938r, this.f8935o, (Composer) obj, iIntValue2);
        }
    }

    public /* synthetic */ o0(List list, int i10, FocusSection focusSection, float f10, float f11, r7.l lVar, int i11) {
        this.f8936p = list;
        this.f8932l = i10;
        this.f8937q = focusSection;
        this.f8933m = f10;
        this.f8934n = f11;
        this.f8938r = lVar;
        this.f8935o = i11;
    }
}
