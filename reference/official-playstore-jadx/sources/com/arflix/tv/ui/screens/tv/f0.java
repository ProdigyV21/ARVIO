package com.arflix.tv.ui.screens.tv;

import java.util.HashSet;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class f0 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11231i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ HashSet f11232l;

    public /* synthetic */ f0(HashSet hashSet, int i10) {
        this.f11231i = i10;
        this.f11232l = hashSet;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        boolean zContains;
        switch (this.f11231i) {
            case 0:
                zContains = this.f11232l.contains((String) obj);
                break;
            case 1:
                zContains = this.f11232l.contains((String) obj);
                break;
            case 2:
                zContains = TvViewModel.currentVisiblePlaylistGroups$lambda$3(this.f11232l, (String) obj);
                break;
            default:
                zContains = TvViewModel.currentVisiblePlaylistGroups$lambda$6(this.f11232l, (String) obj);
                break;
        }
        return Boolean.valueOf(zContains);
    }
}
