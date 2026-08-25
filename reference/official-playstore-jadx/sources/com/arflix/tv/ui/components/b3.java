package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;
import com.arflix.tv.data.model.MediaItem;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class b3 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7845i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f7846l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f7847m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f7848n;

    public /* synthetic */ b3(int i10, boolean z, int i11, Object obj) {
        this.f7845i = i11;
        this.f7848n = obj;
        this.f7846l = z;
        this.f7847m = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f7845i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return StreamSelectorKt.SourceMetadataChips$lambda$1((SourcePresentation) this.f7848n, this.f7846l, this.f7847m, (Composer) obj, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj2).intValue();
                return ContextMenuKt.ContextMenuItem$lambda$1((ContextAction) this.f7848n, this.f7846l, this.f7847m, (Composer) obj, iIntValue2);
            default:
                int iIntValue3 = ((Integer) obj2).intValue();
                return PersonModalKt.HorizontalKnownForCard$lambda$1((MediaItem) this.f7848n, this.f7846l, this.f7847m, (Composer) obj, iIntValue3);
        }
    }
}
