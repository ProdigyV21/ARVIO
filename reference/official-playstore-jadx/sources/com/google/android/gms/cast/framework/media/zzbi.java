package com.google.android.gms.cast.framework.media;

import android.content.DialogInterface;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzbi implements DialogInterface.OnClickListener {
    final /* synthetic */ zzbk zza;
    final /* synthetic */ zzbk zzb;
    final /* synthetic */ TracksChooserDialogFragment zzc;

    public zzbi(TracksChooserDialogFragment tracksChooserDialogFragment, zzbk zzbkVar, zzbk zzbkVar2) {
        this.zza = zzbkVar;
        this.zzb = zzbkVar2;
        Objects.requireNonNull(tracksChooserDialogFragment);
        this.zzc = tracksChooserDialogFragment;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i10) {
        this.zzc.zza(this.zza, this.zzb);
    }
}
