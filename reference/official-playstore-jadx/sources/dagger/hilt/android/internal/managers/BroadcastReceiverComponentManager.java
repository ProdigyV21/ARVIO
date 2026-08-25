package dagger.hilt.android.internal.managers;

import android.content.ComponentCallbacks2;
import android.content.Context;
import dagger.hilt.android.internal.Contexts;
import dagger.hilt.internal.GeneratedComponentManager;
import dagger.hilt.internal.Preconditions;

/* JADX INFO: loaded from: classes4.dex */
public final class BroadcastReceiverComponentManager {
    private BroadcastReceiverComponentManager() {
    }

    public static Object generatedComponent(Context context) {
        ComponentCallbacks2 application = Contexts.getApplication(context.getApplicationContext());
        Preconditions.checkArgument(application instanceof GeneratedComponentManager, "Hilt BroadcastReceiver must be attached to an @HiltAndroidApp Application. Found: %s", application.getClass());
        return ((GeneratedComponentManager) application).generatedComponent();
    }
}
