package com.arflix.tv.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import com.arflix.tv.network.NetworkMonitor;
import com.google.android.gms.cast.MediaError;
import d7.k;
import dagger.hilt.android.qualifiers.ApplicationContext;
import f7.e;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import ma.s;
import ma.u;
import na.h1;
import na.j;
import na.j1;
import na.q0;
import na.y0;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0013\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\r\u0010\n\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\fR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u001d\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u001d\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u00188\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0019\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\b¨\u0006\u001d"}, d2 = {"Lcom/arflix/tv/network/NetworkMonitor;", "", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "", "checkNetworkConnectivity", "()Z", "Lcom/arflix/tv/network/NetworkType;", "getNetworkType", "()Lcom/arflix/tv/network/NetworkType;", "Landroid/content/Context;", "Landroid/net/ConnectivityManager;", "connectivityManager", "Landroid/net/ConnectivityManager;", "Lna/q0;", "_connectionState", "Lna/q0;", "Lna/h1;", "connectionState", "Lna/h1;", "getConnectionState", "()Lna/h1;", "Lna/j;", "isConnectedFlow", "Lna/j;", "()Lna/j;", "isConnected", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class NetworkMonitor {
    public static final int $stable = 8;
    private final q0<Boolean> _connectionState;
    private final h1<Boolean> connectionState;
    private final ConnectivityManager connectivityManager;
    private final Context context;
    private final j<Boolean> isConnectedFlow;

    /* JADX INFO: renamed from: com.arflix.tv.network.NetworkMonitor$isConnectedFlow$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lma/u;", "", "Lx6/t0;", "<anonymous>", "(Lma/u;)V"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.network.NetworkMonitor$isConnectedFlow$1", f = "NetworkMonitor.kt", l = {MediaError.DetailedErrorCode.MEDIA_SRC_NOT_SUPPORTED}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends f7.j implements p<u<? super Boolean>, d7.d<? super t0>, Object> {
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final t0 invokeSuspend$lambda$0(NetworkMonitor networkMonitor, NetworkMonitor$isConnectedFlow$1$callback$1 networkMonitor$isConnectedFlow$1$callback$1) {
            networkMonitor.connectivityManager.unregisterNetworkCallback(networkMonitor$isConnectedFlow$1$callback$1);
            return t0.f22605a;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass1 anonymousClass1 = NetworkMonitor.this.new AnonymousClass1(dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r5v1, types: [android.net.ConnectivityManager$NetworkCallback, com.arflix.tv.network.NetworkMonitor$isConnectedFlow$1$callback$1] */
        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            final u uVar = (u) this.L$0;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                final NetworkMonitor networkMonitor = NetworkMonitor.this;
                final ?? r52 = new ConnectivityManager.NetworkCallback() { // from class: com.arflix.tv.network.NetworkMonitor$isConnectedFlow$1$callback$1
                    @Override // android.net.ConnectivityManager.NetworkCallback
                    public void onAvailable(Network network) {
                        q0 q0Var = networkMonitor._connectionState;
                        Boolean bool = Boolean.TRUE;
                        q0Var.setValue(bool);
                        uVar.mo6685trySendJP2dKIU(bool);
                    }

                    @Override // android.net.ConnectivityManager.NetworkCallback
                    public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                        boolean z = networkCapabilities.hasCapability(12) && networkCapabilities.hasCapability(16);
                        networkMonitor._connectionState.setValue(Boolean.valueOf(z));
                        uVar.mo6685trySendJP2dKIU(Boolean.valueOf(z));
                    }

                    @Override // android.net.ConnectivityManager.NetworkCallback
                    public void onLost(Network network) {
                        boolean zCheckNetworkConnectivity = networkMonitor.checkNetworkConnectivity();
                        networkMonitor._connectionState.setValue(Boolean.valueOf(zCheckNetworkConnectivity));
                        uVar.mo6685trySendJP2dKIU(Boolean.valueOf(zCheckNetworkConnectivity));
                    }
                };
                NetworkMonitor.this.connectivityManager.registerNetworkCallback(new NetworkRequest.Builder().addCapability(12).build(), (ConnectivityManager.NetworkCallback) r52);
                uVar.mo6685trySendJP2dKIU(Boolean.valueOf(NetworkMonitor.this.checkNetworkConnectivity()));
                final NetworkMonitor networkMonitor2 = NetworkMonitor.this;
                r7.a aVar = new r7.a() { // from class: com.arflix.tv.network.a
                    @Override // r7.a
                    public final Object invoke() {
                        return NetworkMonitor.AnonymousClass1.invokeSuspend$lambda$0(networkMonitor2, r52);
                    }
                };
                this.L$0 = null;
                this.L$1 = null;
                this.L$2 = null;
                this.label = 1;
                Object objA = s.a(uVar, aVar, this);
                e7.a aVar2 = e7.a.f15033i;
                if (objA == aVar2) {
                    return aVar2;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(u<? super Boolean> uVar, d7.d<? super t0> dVar) {
            return ((AnonymousClass1) create(uVar, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    @Inject
    public NetworkMonitor(@ApplicationContext Context context) {
        this.context = context;
        this.connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        j1 j1VarB = y0.b(Boolean.valueOf(checkNetworkConnectivity()));
        this._connectionState = j1VarB;
        this.connectionState = y0.e(j1VarB);
        this.isConnectedFlow = y0.h(new na.c(new AnonymousClass1(null), k.f14688i, -2, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean checkNetworkConnectivity() {
        ConnectivityManager connectivityManager = this.connectivityManager;
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        return networkCapabilities != null && networkCapabilities.hasCapability(12) && networkCapabilities.hasCapability(16);
    }

    public final h1<Boolean> getConnectionState() {
        return this.connectionState;
    }

    public final NetworkType getNetworkType() {
        ConnectivityManager connectivityManager = this.connectivityManager;
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        return networkCapabilities == null ? NetworkType.NONE : networkCapabilities.hasTransport(1) ? NetworkType.WIFI : networkCapabilities.hasTransport(3) ? NetworkType.ETHERNET : networkCapabilities.hasTransport(0) ? NetworkType.CELLULAR : NetworkType.OTHER;
    }

    public final boolean isConnected() {
        return checkNetworkConnectivity();
    }

    public final j<Boolean> isConnectedFlow() {
        return this.isConnectedFlow;
    }
}
