package coil.network;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import coil.network.NetworkObserver;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\b\u0005*\u0001\u0014\b\u0003\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0013\u0010\u000b\u001a\u00020\n*\u00020\bH\u0002¢\u0006\u0004\b\u000b\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0012R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0013R\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0014\u0010\u000b\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0017¨\u0006\u0018"}, d2 = {"Lcoil/network/RealNetworkObserver;", "Lcoil/network/NetworkObserver;", "Landroid/net/ConnectivityManager;", "connectivityManager", "Lcoil/network/NetworkObserver$Listener;", "listener", "<init>", "(Landroid/net/ConnectivityManager;Lcoil/network/NetworkObserver$Listener;)V", "Landroid/net/Network;", "network", "", "isOnline", "Lx6/t0;", "onConnectivityChange", "(Landroid/net/Network;Z)V", "(Landroid/net/Network;)Z", "shutdown", "()V", "Landroid/net/ConnectivityManager;", "Lcoil/network/NetworkObserver$Listener;", "coil/network/RealNetworkObserver$networkCallback$1", "networkCallback", "Lcoil/network/RealNetworkObserver$networkCallback$1;", "()Z", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
final class RealNetworkObserver implements NetworkObserver {
    private final ConnectivityManager connectivityManager;
    private final NetworkObserver.Listener listener;
    private final RealNetworkObserver$networkCallback$1 networkCallback;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1, types: [android.net.ConnectivityManager$NetworkCallback, coil.network.RealNetworkObserver$networkCallback$1] */
    public RealNetworkObserver(ConnectivityManager connectivityManager, NetworkObserver.Listener listener) {
        this.connectivityManager = connectivityManager;
        this.listener = listener;
        ?? r4 = new ConnectivityManager.NetworkCallback() { // from class: coil.network.RealNetworkObserver$networkCallback$1
            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onAvailable(Network network) {
                this.this$0.onConnectivityChange(network, true);
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onLost(Network network) {
                this.this$0.onConnectivityChange(network, false);
            }
        };
        this.networkCallback = r4;
        connectivityManager.registerNetworkCallback(new NetworkRequest.Builder().addCapability(12).build(), (ConnectivityManager.NetworkCallback) r4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onConnectivityChange(Network network, boolean isOnline) {
        Network[] allNetworks = this.connectivityManager.getAllNetworks();
        int length = allNetworks.length;
        boolean z = false;
        int i10 = 0;
        while (true) {
            if (i10 >= length) {
                break;
            }
            Network network2 = allNetworks[i10];
            if (p.a(network2, network) ? isOnline : isOnline(network2)) {
                z = true;
                break;
            }
            i10++;
        }
        this.listener.onConnectivityChange(z);
    }

    @Override // coil.network.NetworkObserver
    public boolean isOnline() {
        for (Network network : this.connectivityManager.getAllNetworks()) {
            if (isOnline(network)) {
                return true;
            }
        }
        return false;
    }

    @Override // coil.network.NetworkObserver
    public void shutdown() {
        this.connectivityManager.unregisterNetworkCallback(this.networkCallback);
    }

    private final boolean isOnline(Network network) {
        NetworkCapabilities networkCapabilities = this.connectivityManager.getNetworkCapabilities(network);
        return networkCapabilities != null && networkCapabilities.hasCapability(12);
    }
}
