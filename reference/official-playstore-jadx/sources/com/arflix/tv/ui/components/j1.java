package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.PersonDetails;
import com.arflix.tv.ui.screens.details.DetailsScreenKt;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;
import java.io.Serializable;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class j1 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8105i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f8106l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f8107m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r7.a f8108n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f8109o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f8110p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Serializable f8111q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f8112r;

    public /* synthetic */ j1(MediaItem mediaItem, String str, boolean z, boolean z5, r7.a aVar, int i10, int i11) {
        this.f8111q = mediaItem;
        this.f8112r = str;
        this.f8106l = z;
        this.f8107m = z5;
        this.f8108n = aVar;
        this.f8109o = i10;
        this.f8110p = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f8105i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return PersonModalKt.PersonModal$lambda$11(this.f8106l, (PersonDetails) this.f8111q, this.f8107m, this.f8108n, (r7.p) this.f8112r, this.f8109o, this.f8110p, (Composer) obj, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj2).intValue();
                return DetailsScreenKt.SimilarMediaCard$lambda$4((MediaItem) this.f8111q, (String) this.f8112r, this.f8106l, this.f8107m, this.f8108n, this.f8109o, this.f8110p, (Composer) obj, iIntValue2);
            default:
                int iIntValue3 = ((Integer) obj2).intValue();
                return SettingsScreenKt.SettingsChip$lambda$2((String) this.f8111q, this.f8106l, this.f8108n, this.f8107m, (Modifier) this.f8112r, this.f8109o, this.f8110p, (Composer) obj, iIntValue3);
        }
    }

    public /* synthetic */ j1(String str, boolean z, r7.a aVar, boolean z5, Modifier modifier, int i10, int i11) {
        this.f8111q = str;
        this.f8106l = z;
        this.f8108n = aVar;
        this.f8107m = z5;
        this.f8112r = modifier;
        this.f8109o = i10;
        this.f8110p = i11;
    }

    public /* synthetic */ j1(boolean z, PersonDetails personDetails, boolean z5, r7.a aVar, r7.p pVar, int i10, int i11) {
        this.f8106l = z;
        this.f8111q = personDetails;
        this.f8107m = z5;
        this.f8108n = aVar;
        this.f8112r = pVar;
        this.f8109o = i10;
        this.f8110p = i11;
    }
}
