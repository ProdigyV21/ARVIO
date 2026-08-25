package com.google.firebase.crashlytics.internal;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import m5.b;

/* JADX INFO: loaded from: classes4.dex */
public class RemoteConfigDeferredProxy {
    private final m5.a<u5.a> remoteConfigInteropDeferred;

    public RemoteConfigDeferredProxy(m5.a<u5.a> aVar) {
        this.remoteConfigInteropDeferred = aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$setupListener$0(CrashlyticsRemoteConfigListener crashlyticsRemoteConfigListener, b bVar) {
        ((u5.a) bVar.get()).a();
        Logger.getLogger().d("Registering RemoteConfig Rollouts subscriber");
    }

    public void setupListener(UserMetadata userMetadata) {
        if (userMetadata == null) {
            Logger.getLogger().w("Didn't successfully register with UserMetadata for rollouts listener");
        } else {
            this.remoteConfigInteropDeferred.a(new a3.b(new CrashlyticsRemoteConfigListener(userMetadata), 19));
        }
    }
}
