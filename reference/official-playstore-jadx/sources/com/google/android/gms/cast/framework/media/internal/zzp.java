package com.google.android.gms.cast.framework.media.internal;

import android.app.BroadcastOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.media.session.l;
import android.view.KeyEvent;
import com.google.android.gms.cast.MediaSeekOptions;
import com.google.android.gms.cast.framework.media.MediaIntentReceiver;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzp extends l {
    final /* synthetic */ zzs zza;

    public zzp(zzs zzsVar) {
        Objects.requireNonNull(zzsVar);
        this.zza = zzsVar;
    }

    private final void zza(long j10) {
        RemoteMediaClient remoteMediaClientZzl = this.zza.zzl();
        if (remoteMediaClientZzl == null) {
            return;
        }
        zzb(Math.min(remoteMediaClientZzl.getStreamDuration(), Math.max(0L, remoteMediaClientZzl.getApproximateStreamPosition() + j10)));
    }

    private final void zzb(long j10) {
        RemoteMediaClient remoteMediaClientZzl = this.zza.zzl();
        if (remoteMediaClientZzl == null) {
            return;
        }
        MediaSeekOptions.Builder builder = new MediaSeekOptions.Builder();
        builder.setPosition(j10);
        remoteMediaClientZzl.seek(builder.build());
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // android.support.v4.media.session.l
    public final void onCustomAction(String str, Bundle bundle) {
        zzs.zzb.d("onCustomAction with action = %s", str);
        switch (str.hashCode()) {
            case -1699820260:
                if (str.equals(MediaIntentReceiver.ACTION_REWIND)) {
                    zza(-this.zza.zzj().getSkipStepMs());
                    return;
                }
                break;
            case -668151673:
                if (str.equals(MediaIntentReceiver.ACTION_STOP_CASTING)) {
                    zzs zzsVar = this.zza;
                    if (zzsVar.zzi() != null) {
                        zzsVar.zzi().endCurrentSession(true);
                        return;
                    }
                    return;
                }
                break;
            case -124479363:
                if (str.equals(MediaIntentReceiver.ACTION_DISCONNECT)) {
                    zzs zzsVar2 = this.zza;
                    if (zzsVar2.zzi() != null) {
                        zzsVar2.zzi().endCurrentSession(false);
                        return;
                    }
                    return;
                }
                break;
            case 1362116196:
                if (str.equals(MediaIntentReceiver.ACTION_FORWARD)) {
                    zza(this.zza.zzj().getSkipStepMs());
                    return;
                }
                break;
        }
        Intent intent = new Intent(str);
        zzs zzsVar3 = this.zza;
        intent.setComponent(zzsVar3.zzk());
        int i10 = Build.VERSION.SDK_INT;
        Context contextZzh = zzsVar3.zzh();
        if (i10 < 34) {
            contextZzh.sendBroadcast(intent);
        } else {
            contextZzh.sendBroadcast(intent, null, BroadcastOptions.makeBasic().setShareIdentityEnabled(true).toBundle());
        }
    }

    @Override // android.support.v4.media.session.l
    public final boolean onMediaButtonEvent(Intent intent) {
        zzs.zzb.d("onMediaButtonEvent", new Object[0]);
        KeyEvent keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
        if (keyEvent == null) {
            return true;
        }
        if (keyEvent.getKeyCode() != 127 && keyEvent.getKeyCode() != 126) {
            return true;
        }
        zzs zzsVar = this.zza;
        if (zzsVar.zzl() == null) {
            return true;
        }
        zzsVar.zzl().togglePlayback();
        return true;
    }

    @Override // android.support.v4.media.session.l
    public final void onPause() {
        zzs.zzb.d("onPause", new Object[0]);
        zzs zzsVar = this.zza;
        if (zzsVar.zzl() != null) {
            zzsVar.zzl().togglePlayback();
        }
    }

    @Override // android.support.v4.media.session.l
    public final void onPlay() {
        zzs.zzb.d("onPlay", new Object[0]);
        zzs zzsVar = this.zza;
        if (zzsVar.zzl() != null) {
            zzsVar.zzl().togglePlayback();
        }
    }

    @Override // android.support.v4.media.session.l
    public final void onSeekTo(long j10) {
        int i10 = zzs.zza;
        zzs.zzb.d("onSeekTo %d", Long.valueOf(j10));
        zzb(j10);
    }

    @Override // android.support.v4.media.session.l
    public final void onSkipToNext() {
        zzs.zzb.d("onSkipToNext", new Object[0]);
        zzs zzsVar = this.zza;
        if (zzsVar.zzl() != null) {
            zzsVar.zzl().queueNext(null);
        }
    }

    @Override // android.support.v4.media.session.l
    public final void onSkipToPrevious() {
        zzs.zzb.d("onSkipToPrevious", new Object[0]);
        zzs zzsVar = this.zza;
        if (zzsVar.zzl() != null) {
            zzsVar.zzl().queuePrev(null);
        }
    }
}
