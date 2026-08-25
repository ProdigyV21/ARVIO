package com.google.firebase.sessions;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import kotlin.Metadata;
import w5.l0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/google/firebase/sessions/SessionLifecycleService;", "Landroid/app/Service;", "<init>", "()V", "w5/l0", "com.google.firebase-firebase-sessions"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class SessionLifecycleService extends Service {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final HandlerThread f14393i = new HandlerThread("FirebaseSessions_HandlerThread");

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public l0 f14394l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Messenger f14395m;

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        if (intent == null) {
            Log.d("SessionLifecycleService", "Service bound with null intent. Ignoring.");
            return null;
        }
        Log.d("SessionLifecycleService", "Service bound to new client on process " + intent.getAction());
        Messenger messenger = Build.VERSION.SDK_INT >= 33 ? (Messenger) intent.getParcelableExtra("ClientCallbackMessenger", Messenger.class) : (Messenger) intent.getParcelableExtra("ClientCallbackMessenger");
        if (messenger != null) {
            Message messageObtain = Message.obtain(null, 4, 0, 0);
            messageObtain.replyTo = messenger;
            l0 l0Var = this.f14394l;
            if (l0Var != null) {
                l0Var.sendMessage(messageObtain);
            }
        }
        Messenger messenger2 = this.f14395m;
        if (messenger2 != null) {
            return messenger2.getBinder();
        }
        return null;
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        HandlerThread handlerThread = this.f14393i;
        handlerThread.start();
        this.f14394l = new l0(handlerThread.getLooper());
        this.f14395m = new Messenger(this.f14394l);
    }

    @Override // android.app.Service
    public final void onDestroy() {
        super.onDestroy();
        this.f14393i.quit();
    }
}
