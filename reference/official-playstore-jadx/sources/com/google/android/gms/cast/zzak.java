package com.google.android.gms.cast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: classes4.dex */
final class zzak extends BroadcastReceiver {
    private zzak() {
        throw null;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if ("com.google.android.gms.cast.remote_display.ACTION_NOTIFICATION_DISCONNECT".equals(intent.getAction())) {
            CastRemoteDisplayLocalService.stopService();
        } else if ("com.google.android.gms.cast.remote_display.ACTION_SESSION_ENDED".equals(intent.getAction())) {
            CastRemoteDisplayLocalService.zzv(false);
        }
    }

    public /* synthetic */ zzak(byte[] bArr) {
    }
}
