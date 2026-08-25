package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.dynamic.d;
import j$.util.Objects;
import r3.b;

/* JADX INFO: loaded from: classes4.dex */
final class zzi extends zzc {
    final /* synthetic */ ImagePicker zza;

    public /* synthetic */ zzi(ImagePicker imagePicker, byte[] bArr) {
        Objects.requireNonNull(imagePicker);
        this.zza = imagePicker;
    }

    @Override // com.google.android.gms.cast.framework.media.zzd
    public final b zze(MediaMetadata mediaMetadata, int i10) {
        return this.zza.onPickImage(mediaMetadata, i10);
    }

    @Override // com.google.android.gms.cast.framework.media.zzd
    public final com.google.android.gms.dynamic.b zzf() {
        return new d(this.zza);
    }

    @Override // com.google.android.gms.cast.framework.media.zzd
    public final b zzg(MediaMetadata mediaMetadata, ImageHints imageHints) {
        return this.zza.onPickImage(mediaMetadata, imageHints);
    }
}
