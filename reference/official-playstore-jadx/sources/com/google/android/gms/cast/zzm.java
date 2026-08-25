package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.common.api.h;
import com.google.android.gms.common.api.internal.i0;
import com.google.android.gms.common.api.q;
import com.google.android.gms.common.api.v;
import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public final class zzm implements Cast.CastApi {
    @Override // com.google.android.gms.cast.Cast.CastApi
    public final int getActiveInputState(q qVar) throws IllegalStateException {
        h hVar = com.google.android.gms.cast.internal.zzal.zza;
        qVar.getClass();
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final ApplicationMetadata getApplicationMetadata(q qVar) throws IllegalStateException {
        h hVar = com.google.android.gms.cast.internal.zzal.zza;
        qVar.getClass();
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final String getApplicationStatus(q qVar) throws IllegalStateException {
        h hVar = com.google.android.gms.cast.internal.zzal.zza;
        qVar.getClass();
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final int getStandbyState(q qVar) throws IllegalStateException {
        h hVar = com.google.android.gms.cast.internal.zzal.zza;
        qVar.getClass();
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final double getVolume(q qVar) throws IllegalStateException {
        h hVar = com.google.android.gms.cast.internal.zzal.zza;
        qVar.getClass();
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final boolean isMute(q qVar) throws IllegalStateException {
        h hVar = com.google.android.gms.cast.internal.zzal.zza;
        qVar.getClass();
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final v joinApplication(q qVar) {
        return zza(qVar, null, null, null);
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final v launchApplication(q qVar, String str) {
        return ((i0) qVar).f12756b.doWrite(new zzg(this, qVar, str));
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final v leaveApplication(q qVar) {
        return ((i0) qVar).f12756b.doWrite(new zzj(this, qVar));
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final void removeMessageReceivedCallbacks(q qVar, String str) throws IOException, IllegalArgumentException {
        try {
            h hVar = com.google.android.gms.cast.internal.zzal.zza;
            qVar.getClass();
            throw new UnsupportedOperationException();
        } catch (RemoteException unused) {
            throw new IOException("service error");
        }
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final void requestStatus(q qVar) throws IllegalStateException, IOException {
        try {
            h hVar = com.google.android.gms.cast.internal.zzal.zza;
            qVar.getClass();
            throw new UnsupportedOperationException();
        } catch (RemoteException unused) {
            throw new IOException("service error");
        }
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final v sendMessage(q qVar, String str, String str2) {
        return ((i0) qVar).f12756b.doWrite(new zzf(this, qVar, str, str2));
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final void setMessageReceivedCallbacks(q qVar, String str, Cast.MessageReceivedCallback messageReceivedCallback) throws IllegalStateException, IOException {
        try {
            h hVar = com.google.android.gms.cast.internal.zzal.zza;
            qVar.getClass();
            throw new UnsupportedOperationException();
        } catch (RemoteException unused) {
            throw new IOException("service error");
        }
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final void setMute(q qVar, boolean z) throws IllegalStateException, IOException {
        try {
            h hVar = com.google.android.gms.cast.internal.zzal.zza;
            qVar.getClass();
            throw new UnsupportedOperationException();
        } catch (RemoteException unused) {
            throw new IOException("service error");
        }
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final void setVolume(q qVar, double d4) throws IllegalStateException, IOException, IllegalArgumentException {
        try {
            h hVar = com.google.android.gms.cast.internal.zzal.zza;
            qVar.getClass();
            throw new UnsupportedOperationException();
        } catch (RemoteException unused) {
            throw new IOException("service error");
        }
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final v stopApplication(q qVar) {
        return ((i0) qVar).f12756b.doWrite(new zzk(this, qVar));
    }

    public final v zza(q qVar, String str, String str2, zzbn zzbnVar) {
        return ((i0) qVar).f12756b.doWrite(new zzi(this, qVar, str, str2, null));
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final v joinApplication(q qVar, String str) {
        return zza(qVar, str, null, null);
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final v joinApplication(q qVar, String str, String str2) {
        return zza(qVar, str, str2, null);
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final v launchApplication(q qVar, String str, LaunchOptions launchOptions) {
        return ((i0) qVar).f12756b.doWrite(new zzh(this, qVar, str, launchOptions));
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final v stopApplication(q qVar, String str) {
        return ((i0) qVar).f12756b.doWrite(new zzl(this, qVar, str));
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    @Deprecated
    public final v launchApplication(q qVar, String str, boolean z) {
        LaunchOptions.Builder builder = new LaunchOptions.Builder();
        builder.setRelaunchIfRunning(z);
        return ((i0) qVar).f12756b.doWrite(new zzh(this, qVar, str, builder.build()));
    }
}
