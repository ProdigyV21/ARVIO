package com.google.android.gms.cast.framework.media.widget;

import android.graphics.Bitmap;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzg implements com.google.android.gms.cast.framework.media.internal.zza {
    final /* synthetic */ ExpandedControllerActivity zza;

    public zzg(ExpandedControllerActivity expandedControllerActivity) {
        Objects.requireNonNull(expandedControllerActivity);
        this.zza = expandedControllerActivity;
    }

    @Override // com.google.android.gms.cast.framework.media.internal.zza
    public final void zza(Bitmap bitmap) {
        if (bitmap != null) {
            ExpandedControllerActivity expandedControllerActivity = this.zza;
            if (expandedControllerActivity.zzh() != null) {
                expandedControllerActivity.zzh().setVisibility(8);
            }
            if (expandedControllerActivity.zzg() != null) {
                expandedControllerActivity.zzg().setVisibility(0);
                expandedControllerActivity.zzg().setImageBitmap(bitmap);
            }
        }
    }
}
