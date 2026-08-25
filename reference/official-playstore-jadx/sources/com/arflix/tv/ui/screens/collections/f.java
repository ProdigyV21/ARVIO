package com.arflix.tv.ui.screens.collections;

import androidx.compose.runtime.Composer;
import androidx.lifecycle.d1;
import com.arflix.tv.data.model.Profile;
import com.arflix.tv.ui.screens.tv.TvViewModel;
import com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class f implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8605i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Profile f8606l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f8607m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r7.a f8608n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ r7.a f8609o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ r7.a f8610p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ r7.a f8611q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ r7.a f8612r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ r7.a f8613s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ r7.a f8614t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ int f8615u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ int f8616v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ int f8617w;
    public final /* synthetic */ d1 x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final /* synthetic */ Object f8618y;
    public final /* synthetic */ x6.o z;

    public /* synthetic */ f(TvViewModel tvViewModel, Profile profile, String str, String str2, r7.l lVar, r7.a aVar, r7.a aVar2, r7.a aVar3, r7.a aVar4, r7.a aVar5, r7.a aVar6, r7.a aVar7, int i10, int i11, int i12) {
        this.x = tvViewModel;
        this.f8606l = profile;
        this.f8607m = str;
        this.f8618y = str2;
        this.z = lVar;
        this.f8608n = aVar;
        this.f8609o = aVar2;
        this.f8610p = aVar3;
        this.f8611q = aVar4;
        this.f8612r = aVar5;
        this.f8613s = aVar6;
        this.f8614t = aVar7;
        this.f8615u = i10;
        this.f8616v = i11;
        this.f8617w = i12;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f8605i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return CollectionDetailsScreenKt.CollectionDetailsScreen$lambda$32(this.f8607m, this.f8606l, (CollectionDetailsViewModel) this.x, (r7.p) this.f8618y, (r7.s) this.z, this.f8608n, this.f8609o, this.f8610p, this.f8611q, this.f8612r, this.f8613s, this.f8614t, this.f8615u, this.f8616v, this.f8617w, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return LiveTvScreenKt.LiveTvScreen$lambda$252((TvViewModel) this.x, this.f8606l, this.f8607m, (String) this.f8618y, (r7.l) this.z, this.f8608n, this.f8609o, this.f8610p, this.f8611q, this.f8612r, this.f8613s, this.f8614t, this.f8615u, this.f8616v, this.f8617w, (Composer) obj, iIntValue2);
        }
    }

    public /* synthetic */ f(String str, Profile profile, CollectionDetailsViewModel collectionDetailsViewModel, r7.p pVar, r7.s sVar, r7.a aVar, r7.a aVar2, r7.a aVar3, r7.a aVar4, r7.a aVar5, r7.a aVar6, r7.a aVar7, int i10, int i11, int i12) {
        this.f8607m = str;
        this.f8606l = profile;
        this.x = collectionDetailsViewModel;
        this.f8618y = pVar;
        this.z = sVar;
        this.f8608n = aVar;
        this.f8609o = aVar2;
        this.f8610p = aVar3;
        this.f8611q = aVar4;
        this.f8612r = aVar5;
        this.f8613s = aVar6;
        this.f8614t = aVar7;
        this.f8615u = i10;
        this.f8616v = i11;
        this.f8617w = i12;
    }
}
