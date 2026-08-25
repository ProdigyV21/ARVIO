package com.discord.org.webrtc;

import android.os.Process;
import com.discord.org.webrtc.EglBase10Impl;
import com.discord.org.webrtc.EglBase14Impl;
import com.discord.socialsdk.NativeCalls;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.AlarmManagerSchedulerBroadcastReceiver;
import io.sentry.android.ndk.SentryNdk;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class i implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f12515i;

    public /* synthetic */ i(int i10) {
        this.f12515i = i10;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f12515i) {
            case 0:
                EglBase10Impl.EglConnection.lambda$new$1();
                break;
            case 1:
                EglBase14Impl.EglConnection.lambda$new$1();
                break;
            case 2:
                NativeCalls.lambda$stopForegroundService$4();
                break;
            case 3:
                NativeCalls.lambda$startForegroundService$3();
                break;
            case 4:
                io.sentry.android.core.internal.util.e.f16601b = Process.myTid();
                break;
            case 5:
                SentryNdk.lambda$static$0();
                break;
            default:
                int i10 = AlarmManagerSchedulerBroadcastReceiver.f12607a;
                break;
        }
    }
}
