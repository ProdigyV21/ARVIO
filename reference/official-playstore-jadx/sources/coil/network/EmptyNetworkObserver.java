package coil.network;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0005\u0010\u0003R\u0014\u0010\u0007\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcoil/network/EmptyNetworkObserver;", "Lcoil/network/NetworkObserver;", "<init>", "()V", "Lx6/t0;", "shutdown", "", "isOnline", "()Z", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class EmptyNetworkObserver implements NetworkObserver {
    @Override // coil.network.NetworkObserver
    public boolean isOnline() {
        return true;
    }

    @Override // coil.network.NetworkObserver
    public void shutdown() {
    }
}
