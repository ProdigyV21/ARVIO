package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class d3 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7909i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f7910l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f7911m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f7912n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f7913o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f7914p;

    public /* synthetic */ d3(String str, boolean z, boolean z5, int i10, int i11, int i12) {
        this.f7909i = i12;
        this.f7910l = str;
        this.f7911m = z;
        this.f7912n = z5;
        this.f7913o = i10;
        this.f7914p = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f7909i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return StreamSelectorKt.SourceSizeBadge$lambda$0((String) this.f7910l, this.f7911m, this.f7912n, this.f7913o, this.f7914p, (Composer) obj, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj2).intValue();
                return StreamSelectorKt.SourceSizeBadge$lambda$2((String) this.f7910l, this.f7911m, this.f7912n, this.f7913o, this.f7914p, (Composer) obj, iIntValue2);
            case 2:
                int iIntValue3 = ((Integer) obj2).intValue();
                return StreamSelectorKt.SourceLanguageBadge$lambda$0((String) this.f7910l, this.f7911m, this.f7912n, this.f7913o, this.f7914p, (Composer) obj, iIntValue3);
            case 3:
                int iIntValue4 = ((Integer) obj2).intValue();
                return StreamSelectorKt.SourceLanguageBadge$lambda$2((String) this.f7910l, this.f7911m, this.f7912n, this.f7913o, this.f7914p, (Composer) obj, iIntValue4);
            case 4:
                int iIntValue5 = ((Integer) obj2).intValue();
                return SettingsScreenKt.SourceChip$lambda$0((String) this.f7910l, this.f7911m, this.f7912n, this.f7913o, this.f7914p, (Composer) obj, iIntValue5);
            default:
                int iIntValue6 = ((Integer) obj2).intValue();
                return SkeletonLoaderKt.SkeletonDetailsPage$lambda$2(this.f7911m, this.f7912n, (Modifier) this.f7910l, this.f7913o, this.f7914p, (Composer) obj, iIntValue6);
        }
    }

    public /* synthetic */ d3(boolean z, boolean z5, Modifier modifier, int i10, int i11) {
        this.f7909i = 5;
        this.f7911m = z;
        this.f7912n = z5;
        this.f7910l = modifier;
        this.f7913o = i10;
        this.f7914p = i11;
    }
}
