package com.google.android.gms.cast.framework.media;

import android.content.DialogInterface;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzbh implements DialogInterface.OnClickListener {
    final /* synthetic */ TracksChooserDialogFragment zza;

    public zzbh(TracksChooserDialogFragment tracksChooserDialogFragment) {
        Objects.requireNonNull(tracksChooserDialogFragment);
        this.zza = tracksChooserDialogFragment;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i10) {
        TracksChooserDialogFragment tracksChooserDialogFragment = this.zza;
        if (tracksChooserDialogFragment.zzb() != null) {
            tracksChooserDialogFragment.zzb().cancel();
            tracksChooserDialogFragment.zzc(null);
        }
    }
}
