package com.arflix.tv.ui.screens.details;

import androidx.compose.runtime.Composer;
import com.arflix.tv.data.model.CastMember;
import com.arflix.tv.ui.screens.profile.ProfileDialogsKt;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class q0 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8954i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f8955l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.a f8956m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f8957n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f8958o;

    public /* synthetic */ q0(CastMember castMember, boolean z, r7.a aVar, int i10) {
        this.f8958o = castMember;
        this.f8955l = z;
        this.f8956m = aVar;
        this.f8957n = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f8954i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return DetailsScreenKt.CircularCastCard$lambda$3((CastMember) this.f8958o, this.f8955l, this.f8956m, this.f8957n, (Composer) obj, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj2).intValue();
                return ProfileDialogsKt.AvatarImageButtons$lambda$1(this.f8955l, this.f8956m, (r7.a) this.f8958o, this.f8957n, (Composer) obj, iIntValue2);
            default:
                int iIntValue3 = ((Integer) obj2).intValue();
                return SettingsScreenKt.MdbListConnectDialog$lambda$6(this.f8955l, (r7.l) this.f8958o, this.f8956m, this.f8957n, (Composer) obj, iIntValue3);
        }
    }

    public /* synthetic */ q0(boolean z, r7.a aVar, r7.a aVar2, int i10) {
        this.f8955l = z;
        this.f8956m = aVar;
        this.f8958o = aVar2;
        this.f8957n = i10;
    }

    public /* synthetic */ q0(boolean z, r7.l lVar, r7.a aVar, int i10) {
        this.f8955l = z;
        this.f8958o = lVar;
        this.f8956m = aVar;
        this.f8957n = i10;
    }
}
