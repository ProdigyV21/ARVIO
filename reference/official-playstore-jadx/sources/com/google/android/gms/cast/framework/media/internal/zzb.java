package com.google.android.gms.cast.framework.media.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.common.internal.t;

/* JADX INFO: loaded from: classes4.dex */
public final class zzb {
    private final Context zza;
    private final ImageHints zzb;
    private Uri zzc;
    private zzd zzd;
    private Bitmap zze;
    private boolean zzf;
    private zza zzg;

    public zzb(Context context) {
        this(context, new ImageHints(-1, 0, 0));
    }

    private final void zze() {
        zzd zzdVar = this.zzd;
        if (zzdVar != null) {
            zzdVar.cancel(true);
            this.zzd = null;
        }
        this.zzc = null;
        this.zze = null;
        this.zzf = false;
    }

    public final void zza(zza zzaVar) {
        this.zzg = zzaVar;
    }

    public final boolean zzb(Uri uri) {
        if (uri == null) {
            zze();
            return true;
        }
        if (uri.equals(this.zzc)) {
            return this.zzf;
        }
        zze();
        this.zzc = uri;
        ImageHints imageHints = this.zzb;
        if (imageHints.getWidthInPixels() == 0 || imageHints.getHeightInPixels() == 0) {
            this.zzd = new zzd(this.zza, 0, 0, false, 2097152L, 5, 333, 10000, this);
        } else {
            this.zzd = new zzd(this.zza, imageHints.getWidthInPixels(), imageHints.getHeightInPixels(), false, 2097152L, 5, 333, 10000, this);
        }
        zzd zzdVar = this.zzd;
        t.i(zzdVar);
        Uri uri2 = this.zzc;
        t.i(uri2);
        zzdVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, uri2);
        return false;
    }

    public final void zzc() {
        zze();
        this.zzg = null;
    }

    public final void zzd(Bitmap bitmap) {
        this.zze = bitmap;
        this.zzf = true;
        zza zzaVar = this.zzg;
        if (zzaVar != null) {
            zzaVar.zza(bitmap);
        }
        this.zzd = null;
    }

    public zzb(Context context, ImageHints imageHints) {
        this.zza = context;
        this.zzb = imageHints;
        zze();
    }
}
