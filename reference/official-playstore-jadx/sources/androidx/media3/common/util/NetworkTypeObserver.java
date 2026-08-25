package androidx.media3.common.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.telephony.TelephonyCallback;
import android.telephony.TelephonyDisplayInfo;
import android.telephony.TelephonyManager;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
public final class NetworkTypeObserver {
    private static NetworkTypeObserver staticInstance;
    private final Executor backgroundExecutor;
    private boolean isInitialized;
    private final CopyOnWriteArrayList<ListenerHolder> listeners;
    private final Object lock;
    private int networkType;

    public static final class Api31 {

        public static final class DisplayInfoCallback extends TelephonyCallback implements TelephonyCallback.DisplayInfoListener {
            private final NetworkTypeObserver instance;

            public DisplayInfoCallback(NetworkTypeObserver networkTypeObserver) {
                this.instance = networkTypeObserver;
            }

            public void onDisplayInfoChanged(TelephonyDisplayInfo telephonyDisplayInfo) {
                int overrideNetworkType = telephonyDisplayInfo.getOverrideNetworkType();
                this.instance.updateNetworkType(overrideNetworkType == 3 || overrideNetworkType == 4 || overrideNetworkType == 5 ? 10 : 5);
            }
        }

        private Api31() {
        }

        public static void disambiguate4gAnd5gNsa(Context context, NetworkTypeObserver networkTypeObserver) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                telephonyManager.getClass();
                DisplayInfoCallback displayInfoCallback = new DisplayInfoCallback(networkTypeObserver);
                telephonyManager.registerTelephonyCallback(networkTypeObserver.backgroundExecutor, displayInfoCallback);
                telephonyManager.unregisterTelephonyCallback(displayInfoCallback);
            } catch (RuntimeException unused) {
                networkTypeObserver.updateNetworkType(5);
            }
        }
    }

    public interface Listener {
        void onNetworkTypeChanged(int i10);
    }

    public final class ListenerHolder {
        private final Executor executor;
        private final WeakReference<Listener> listener;

        public ListenerHolder(Listener listener, Executor executor) {
            this.listener = new WeakReference<>(listener);
            this.executor = executor;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$callOnNetworkTypeChanged$0() {
            Listener listener = this.listener.get();
            if (listener != null) {
                listener.onNetworkTypeChanged(NetworkTypeObserver.this.getNetworkType());
            }
        }

        public void callOnNetworkTypeChanged() {
            this.executor.execute(new e(this, 0));
        }

        public boolean canBeRemoved() {
            return this.listener.get() == null;
        }
    }

    public final class Receiver extends BroadcastReceiver {
        private Receiver() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onReceive$0(Context context) {
            NetworkTypeObserver.this.handleConnectivityActionBroadcast(context);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            NetworkTypeObserver.this.backgroundExecutor.execute(new f(this, context, 0));
        }
    }

    private NetworkTypeObserver(Context context) {
        Executor executor = BackgroundExecutor.get();
        this.backgroundExecutor = executor;
        this.listeners = new CopyOnWriteArrayList<>();
        this.lock = new Object();
        this.networkType = 0;
        executor.execute(new f(this, context, 2));
    }

    public static synchronized NetworkTypeObserver getInstance(Context context) {
        try {
            if (staticInstance == null) {
                staticInstance = new NetworkTypeObserver(context);
            }
        } catch (Throwable th) {
            throw th;
        }
        return staticInstance;
    }

    private static int getMobileNetworkType(NetworkInfo networkInfo) {
        switch (networkInfo.getSubtype()) {
            case 1:
            case 2:
                return 3;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 14:
            case 15:
            case 17:
                return 4;
            case 13:
                return 5;
            case 16:
            case 19:
            default:
                return 6;
            case 18:
                return 2;
            case 20:
                return Build.VERSION.SDK_INT >= 29 ? 9 : 0;
        }
    }

    private static int getNetworkTypeFromConnectivityManager(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        int i10 = 0;
        if (connectivityManager == null) {
            return 0;
        }
        try {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            i10 = 1;
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                int type = activeNetworkInfo.getType();
                if (type != 0) {
                    if (type == 1) {
                        return 2;
                    }
                    if (type != 4 && type != 5) {
                        if (type != 6) {
                            return type != 9 ? 8 : 7;
                        }
                        return 5;
                    }
                }
                return getMobileNetworkType(activeNetworkInfo);
            }
        } catch (SecurityException unused) {
        }
        return i10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleConnectivityActionBroadcast(Context context) {
        int networkTypeFromConnectivityManager = getNetworkTypeFromConnectivityManager(context);
        if (Build.VERSION.SDK_INT < 31 || networkTypeFromConnectivityManager != 5) {
            updateNetworkType(networkTypeFromConnectivityManager);
        } else {
            Api31.disambiguate4gAnd5gNsa(context, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: init, reason: merged with bridge method [inline-methods] */
    public void lambda$new$0(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(new Receiver(), intentFilter);
    }

    private void removeClearedReferences() {
        for (ListenerHolder listenerHolder : this.listeners) {
            if (listenerHolder.canBeRemoved()) {
                this.listeners.remove(listenerHolder);
            }
        }
    }

    public static synchronized void resetForTests() {
        staticInstance = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNetworkType(int i10) {
        removeClearedReferences();
        synchronized (this.lock) {
            try {
                if (this.isInitialized && this.networkType == i10) {
                    return;
                }
                this.isInitialized = true;
                this.networkType = i10;
                Iterator<ListenerHolder> it = this.listeners.iterator();
                while (it.hasNext()) {
                    it.next().callOnNetworkTypeChanged();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public int getNetworkType() {
        int i10;
        synchronized (this.lock) {
            i10 = this.networkType;
        }
        return i10;
    }

    @Deprecated
    public void register(Listener listener) {
        register(listener, new d(new Handler(Looper.getMainLooper())));
    }

    public void register(Listener listener, Executor executor) {
        boolean z;
        removeClearedReferences();
        ListenerHolder listenerHolder = new ListenerHolder(listener, executor);
        synchronized (this.lock) {
            this.listeners.add(listenerHolder);
            z = this.isInitialized;
        }
        if (z) {
            listenerHolder.callOnNetworkTypeChanged();
        }
    }
}
