package com.arflix.tv.ui.screens.details;

import androidx.compose.runtime.Composer;
import com.arflix.tv.data.model.CastMember;
import com.arflix.tv.data.model.Review;
import com.arflix.tv.ui.screens.profile.ProfileSelectionScreenKt;
import java.io.Serializable;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class p0 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8942i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f8943l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f8944m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f8945n;

    public /* synthetic */ p0(Serializable serializable, boolean z, int i10, int i11) {
        this.f8942i = i11;
        this.f8945n = serializable;
        this.f8943l = z;
        this.f8944m = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f8942i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return DetailsScreenKt.ReviewCard$lambda$4((Review) this.f8945n, this.f8943l, this.f8944m, (Composer) obj, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj2).intValue();
                return DetailsScreenKt.CastCard$lambda$2((CastMember) this.f8945n, this.f8943l, this.f8944m, (Composer) obj, iIntValue2);
            default:
                int iIntValue3 = ((Integer) obj2).intValue();
                return ProfileSelectionScreenKt.ManageProfilesButton$lambda$7(this.f8943l, (r7.a) this.f8945n, this.f8944m, (Composer) obj, iIntValue3);
        }
    }

    public /* synthetic */ p0(boolean z, r7.a aVar, int i10) {
        this.f8942i = 2;
        this.f8943l = z;
        this.f8945n = aVar;
        this.f8944m = i10;
    }
}
