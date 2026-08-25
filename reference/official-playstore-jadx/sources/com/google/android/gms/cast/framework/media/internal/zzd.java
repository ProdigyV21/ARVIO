package com.google.android.gms.cast.framework.media.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.RemoteException;
import com.google.android.gms.cast.framework.ModuleUnavailableException;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.internal.cast.d0;
import com.google.android.gms.internal.cast.h0;

/* JADX INFO: loaded from: classes4.dex */
public final class zzd extends AsyncTask {
    private static final Logger zza = new Logger("FetchBitmapTask");
    private final zzg zzb;
    private final zzb zzc;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v1, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5, types: [com.google.android.gms.cast.framework.media.internal.zzg] */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v9 */
    public zzd(Context context, int i10, int i11, boolean z, long j10, int i12, int i13, int i14, zzb zzbVar) {
        this.zzc = zzbVar;
        Context applicationContext = context.getApplicationContext();
        ?? K = 0;
        K = 0;
        zzc zzcVar = new zzc(this, K);
        Logger logger = d0.f13226a;
        try {
            h0 h0VarA = d0.a(applicationContext.getApplicationContext());
            K = h0VarA.zze() >= 233700000 ? h0VarA.k(new d(applicationContext.getApplicationContext()), new d(this), zzcVar, i10, i11) : h0VarA.p(new d(this), zzcVar, i10, i11);
        } catch (RemoteException e5) {
            e = e5;
            d0.f13226a.d(e, "Unable to call %s on %s.", "newFetchBitmapTaskImpl", h0.class.getSimpleName());
        } catch (ModuleUnavailableException e6) {
            e = e6;
            d0.f13226a.d(e, "Unable to call %s on %s.", "newFetchBitmapTaskImpl", h0.class.getSimpleName());
        }
        this.zzb = K;
    }

    @Override // android.os.AsyncTask
    public final /* bridge */ /* synthetic */ Object doInBackground(Object[] objArr) {
        Uri uri;
        zzg zzgVar;
        Uri[] uriArr = (Uri[]) objArr;
        if (uriArr.length != 1 || (uri = uriArr[0]) == null || (zzgVar = this.zzb) == null) {
            return null;
        }
        try {
            return zzgVar.zze(uri);
        } catch (RemoteException e5) {
            zza.d(e5, "Unable to call %s on %s.", "doFetch", "zzg");
            return null;
        }
    }

    @Override // android.os.AsyncTask
    public final /* bridge */ /* synthetic */ void onPostExecute(Object obj) {
        zzb zzbVar = this.zzc;
        Bitmap bitmap = (Bitmap) obj;
        if (zzbVar != null) {
            zzbVar.zzd(bitmap);
        }
    }
}
