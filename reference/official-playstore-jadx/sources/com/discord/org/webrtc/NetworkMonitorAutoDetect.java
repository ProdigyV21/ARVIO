package com.discord.org.webrtc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.wifi.WifiInfo;
import android.net.wifi.p2p.WifiP2pGroup;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import com.discord.org.webrtc.NetworkChangeDetector;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public class NetworkMonitorAutoDetect extends BroadcastReceiver implements NetworkChangeDetector {
    private static final long INVALID_NET_ID = -1;
    private static final String TAG = "NetworkMonitorAutoDetect";
    private static boolean includeWifiDirect;
    private final ConnectivityManager.NetworkCallback allNetworkCallback;
    final Set<Network> availableNetworks;
    private NetworkChangeDetector.ConnectionType connectionType;
    private ConnectivityManagerDelegate connectivityManagerDelegate;
    private final Context context;
    private final IntentFilter intentFilter;
    private boolean isRegistered;
    private final ConnectivityManager.NetworkCallback mobileNetworkCallback;
    private final NetworkChangeDetector.Observer observer;
    private WifiDirectManagerDelegate wifiDirectManagerDelegate;
    private WifiManagerDelegate wifiManagerDelegate;
    private String wifiSSID;

    public static class ConnectivityManagerDelegate {
        private final Set<Network> availableNetworks;
        private final ConnectivityManager connectivityManager;
        private final boolean getAllNetworksFromCache;
        private final boolean includeOtherUidNetworks;
        private final boolean requestVPN;

        public ConnectivityManagerDelegate(Context context, Set<Network> set, String str) {
            this((ConnectivityManager) context.getSystemService("connectivity"), set, str);
        }

        private static boolean checkFieldTrial(String str, String str2, boolean z) {
            if (str.contains(str2 + ":true")) {
                return true;
            }
            if (str.contains(str2 + ":false")) {
                return false;
            }
            return z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public NetworkChangeDetector.NetworkInformation networkToInfo(Network network) {
            ConnectivityManager connectivityManager;
            if (network == null || (connectivityManager = this.connectivityManager) == null) {
                return null;
            }
            LinkProperties linkProperties = connectivityManager.getLinkProperties(network);
            if (linkProperties == null) {
                Logging.w(NetworkMonitorAutoDetect.TAG, "Detected unknown network: " + network.toString());
                return null;
            }
            if (linkProperties.getInterfaceName() == null) {
                Logging.w(NetworkMonitorAutoDetect.TAG, "Null interface name for network " + network.toString());
                return null;
            }
            NetworkState networkState = getNetworkState(network);
            NetworkChangeDetector.ConnectionType connectionType = NetworkMonitorAutoDetect.getConnectionType(networkState);
            if (connectionType == NetworkChangeDetector.ConnectionType.CONNECTION_NONE) {
                Logging.d(NetworkMonitorAutoDetect.TAG, "Network " + network.toString() + " is disconnected");
                return null;
            }
            if (connectionType == NetworkChangeDetector.ConnectionType.CONNECTION_UNKNOWN || connectionType == NetworkChangeDetector.ConnectionType.CONNECTION_UNKNOWN_CELLULAR) {
                Logging.d(NetworkMonitorAutoDetect.TAG, "Network " + network.toString() + " connection type is " + connectionType + " because it has type " + networkState.getNetworkType() + " and subtype " + networkState.getNetworkSubType());
            }
            return new NetworkChangeDetector.NetworkInformation(linkProperties.getInterfaceName(), connectionType, NetworkMonitorAutoDetect.getUnderlyingConnectionTypeForVpn(networkState), NetworkMonitorAutoDetect.networkToNetId(network), getIPAddresses(linkProperties));
        }

        public NetworkRequest createNetworkRequest() {
            NetworkRequest.Builder builderAddCapability = new NetworkRequest.Builder().addCapability(12);
            if (this.requestVPN) {
                builderAddCapability.removeCapability(15);
            }
            if (Build.VERSION.SDK_INT >= 31 && this.includeOtherUidNetworks) {
                builderAddCapability.setIncludeOtherUidNetworks(true);
            }
            return builderAddCapability.build();
        }

        public List<NetworkChangeDetector.NetworkInformation> getActiveNetworkList() {
            if (!supportNetworkCallback()) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (Network network : getAllNetworks()) {
                NetworkChangeDetector.NetworkInformation networkInformationNetworkToInfo = networkToInfo(network);
                if (networkInformationNetworkToInfo != null) {
                    arrayList.add(networkInformationNetworkToInfo);
                }
            }
            return arrayList;
        }

        public Network[] getAllNetworks() {
            Network[] networkArr;
            if (this.connectivityManager == null) {
                return new Network[0];
            }
            if (!supportNetworkCallback() || !this.getAllNetworksFromCache) {
                return this.connectivityManager.getAllNetworks();
            }
            synchronized (this.availableNetworks) {
                networkArr = (Network[]) this.availableNetworks.toArray(new Network[0]);
            }
            return networkArr;
        }

        public long getDefaultNetId() {
            NetworkInfo activeNetworkInfo;
            NetworkInfo networkInfo;
            if (!supportNetworkCallback() || (activeNetworkInfo = this.connectivityManager.getActiveNetworkInfo()) == null) {
                return -1L;
            }
            long jNetworkToNetId = -1;
            for (Network network : getAllNetworks()) {
                if (hasInternetCapability(network) && (networkInfo = this.connectivityManager.getNetworkInfo(network)) != null && networkInfo.getType() == activeNetworkInfo.getType()) {
                    if (jNetworkToNetId != -1) {
                        throw new RuntimeException("Multiple connected networks of same type are not supported.");
                    }
                    jNetworkToNetId = NetworkMonitorAutoDetect.networkToNetId(network);
                }
            }
            return jNetworkToNetId;
        }

        public NetworkChangeDetector.IPAddress[] getIPAddresses(LinkProperties linkProperties) {
            NetworkChangeDetector.IPAddress[] iPAddressArr = new NetworkChangeDetector.IPAddress[linkProperties.getLinkAddresses().size()];
            Iterator<LinkAddress> it = linkProperties.getLinkAddresses().iterator();
            int i10 = 0;
            while (it.hasNext()) {
                iPAddressArr[i10] = new NetworkChangeDetector.IPAddress(it.next().getAddress().getAddress());
                i10++;
            }
            return iPAddressArr;
        }

        public NetworkState getNetworkState() {
            ConnectivityManager connectivityManager = this.connectivityManager;
            return connectivityManager == null ? new NetworkState(false, -1, -1, -1, -1) : getNetworkState(connectivityManager.getActiveNetworkInfo());
        }

        public boolean hasInternetCapability(Network network) {
            NetworkCapabilities networkCapabilities;
            ConnectivityManager connectivityManager = this.connectivityManager;
            return (connectivityManager == null || (networkCapabilities = connectivityManager.getNetworkCapabilities(network)) == null || !networkCapabilities.hasCapability(12)) ? false : true;
        }

        public void registerNetworkCallback(ConnectivityManager.NetworkCallback networkCallback) {
            this.connectivityManager.registerNetworkCallback(createNetworkRequest(), networkCallback);
        }

        public void releaseCallback(ConnectivityManager.NetworkCallback networkCallback) {
            if (supportNetworkCallback()) {
                Logging.d(NetworkMonitorAutoDetect.TAG, "Unregister network callback");
                this.connectivityManager.unregisterNetworkCallback(networkCallback);
            }
        }

        public void requestMobileNetwork(ConnectivityManager.NetworkCallback networkCallback) {
            NetworkRequest.Builder builder = new NetworkRequest.Builder();
            builder.addCapability(12).addTransportType(0);
            this.connectivityManager.requestNetwork(builder.build(), networkCallback);
        }

        public boolean supportNetworkCallback() {
            return this.connectivityManager != null;
        }

        public ConnectivityManagerDelegate(ConnectivityManager connectivityManager, Set<Network> set, String str) {
            this.connectivityManager = connectivityManager;
            this.availableNetworks = set;
            this.getAllNetworksFromCache = checkFieldTrial(str, "getAllNetworksFromCache", true);
            this.requestVPN = checkFieldTrial(str, "requestVPN", true);
            this.includeOtherUidNetworks = checkFieldTrial(str, "includeOtherUidNetworks", false);
        }

        public NetworkState getNetworkState(Network network) {
            ConnectivityManager connectivityManager;
            NetworkInfo activeNetworkInfo;
            if (network != null && (connectivityManager = this.connectivityManager) != null) {
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(network);
                if (networkInfo == null) {
                    Logging.w(NetworkMonitorAutoDetect.TAG, "Couldn't retrieve information from network " + network.toString());
                    return new NetworkState(false, -1, -1, -1, -1);
                }
                if (networkInfo.getType() != 17) {
                    NetworkCapabilities networkCapabilities = this.connectivityManager.getNetworkCapabilities(network);
                    if (networkCapabilities != null && networkCapabilities.hasTransport(4)) {
                        return new NetworkState(networkInfo.isConnected(), 17, -1, networkInfo.getType(), networkInfo.getSubtype());
                    }
                    return getNetworkState(networkInfo);
                }
                if (networkInfo.getType() == 17) {
                    if (network.equals(this.connectivityManager.getActiveNetwork()) && (activeNetworkInfo = this.connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.getType() != 17) {
                        return new NetworkState(networkInfo.isConnected(), 17, -1, activeNetworkInfo.getType(), activeNetworkInfo.getSubtype());
                    }
                    return new NetworkState(networkInfo.isConnected(), 17, -1, -1, -1);
                }
                return getNetworkState(networkInfo);
            }
            return new NetworkState(false, -1, -1, -1, -1);
        }

        private NetworkState getNetworkState(NetworkInfo networkInfo) {
            if (networkInfo != null && networkInfo.isConnected()) {
                return new NetworkState(true, networkInfo.getType(), networkInfo.getSubtype(), -1, -1);
            }
            return new NetworkState(false, -1, -1, -1, -1);
        }
    }

    public static class NetworkState {
        private final boolean connected;
        private final int subtype;
        private final int type;
        private final int underlyingNetworkSubtypeForVpn;
        private final int underlyingNetworkTypeForVpn;

        public NetworkState(boolean z, int i10, int i11, int i12, int i13) {
            this.connected = z;
            this.type = i10;
            this.subtype = i11;
            this.underlyingNetworkTypeForVpn = i12;
            this.underlyingNetworkSubtypeForVpn = i13;
        }

        public int getNetworkSubType() {
            return this.subtype;
        }

        public int getNetworkType() {
            return this.type;
        }

        public int getUnderlyingNetworkSubtypeForVpn() {
            return this.underlyingNetworkSubtypeForVpn;
        }

        public int getUnderlyingNetworkTypeForVpn() {
            return this.underlyingNetworkTypeForVpn;
        }

        public boolean isConnected() {
            return this.connected;
        }
    }

    public class SimpleNetworkCallback extends ConnectivityManager.NetworkCallback {
        final Set<Network> availableNetworks;

        public SimpleNetworkCallback(Set<Network> set) {
            this.availableNetworks = set;
        }

        private void onNetworkChanged(Network network) {
            NetworkChangeDetector.NetworkInformation networkInformationNetworkToInfo = NetworkMonitorAutoDetect.this.connectivityManagerDelegate.networkToInfo(network);
            if (networkInformationNetworkToInfo != null) {
                NetworkMonitorAutoDetect.this.observer.onNetworkConnect(networkInformationNetworkToInfo);
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            Logging.d(NetworkMonitorAutoDetect.TAG, "Network handle: " + NetworkMonitorAutoDetect.networkToNetId(network) + " becomes available: " + network.toString());
            synchronized (this.availableNetworks) {
                this.availableNetworks.add(network);
            }
            onNetworkChanged(network);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            Logging.d(NetworkMonitorAutoDetect.TAG, "handle: " + NetworkMonitorAutoDetect.networkToNetId(network) + " capabilities changed: " + networkCapabilities.toString());
            onNetworkChanged(network);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
            Logging.d(NetworkMonitorAutoDetect.TAG, "handle: " + NetworkMonitorAutoDetect.networkToNetId(network) + " link properties changed");
            onNetworkChanged(network);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLosing(Network network, int i10) {
            Logging.d(NetworkMonitorAutoDetect.TAG, "Network handle: " + NetworkMonitorAutoDetect.networkToNetId(network) + ", " + network.toString() + " is about to lose in " + i10 + "ms");
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            Logging.d(NetworkMonitorAutoDetect.TAG, "Network handle: " + NetworkMonitorAutoDetect.networkToNetId(network) + ", " + network.toString() + " is disconnected");
            synchronized (this.availableNetworks) {
                this.availableNetworks.remove(network);
            }
            NetworkMonitorAutoDetect.this.observer.onNetworkDisconnect(NetworkMonitorAutoDetect.networkToNetId(network));
        }
    }

    public static class WifiDirectManagerDelegate extends BroadcastReceiver {
        private static final int WIFI_P2P_NETWORK_HANDLE = 0;
        private final Context context;
        private final NetworkChangeDetector.Observer observer;
        private NetworkChangeDetector.NetworkInformation wifiP2pNetworkInfo;

        public WifiDirectManagerDelegate(NetworkChangeDetector.Observer observer, Context context) {
            this.context = context;
            this.observer = observer;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.wifi.p2p.STATE_CHANGED");
            intentFilter.addAction("android.net.wifi.p2p.CONNECTION_STATE_CHANGE");
            context.registerReceiver(this, intentFilter);
            if (Build.VERSION.SDK_INT > 28) {
                WifiP2pManager wifiP2pManager = (WifiP2pManager) context.getSystemService("wifip2p");
                wifiP2pManager.requestGroupInfo(wifiP2pManager.initialize(context, context.getMainLooper(), null), new WifiP2pManager.GroupInfoListener() { // from class: com.discord.org.webrtc.r
                    @Override // android.net.wifi.p2p.WifiP2pManager.GroupInfoListener
                    public final void onGroupInfoAvailable(WifiP2pGroup wifiP2pGroup) {
                        this.f12539a.lambda$new$0(wifiP2pGroup);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: onWifiP2pGroupChange, reason: merged with bridge method [inline-methods] */
        public void lambda$new$0(WifiP2pGroup wifiP2pGroup) {
            if (wifiP2pGroup == null || wifiP2pGroup.getInterface() == null) {
                return;
            }
            try {
                ArrayList list = Collections.list(NetworkInterface.getByName(wifiP2pGroup.getInterface()).getInetAddresses());
                NetworkChangeDetector.IPAddress[] iPAddressArr = new NetworkChangeDetector.IPAddress[list.size()];
                for (int i10 = 0; i10 < list.size(); i10++) {
                    iPAddressArr[i10] = new NetworkChangeDetector.IPAddress(((InetAddress) list.get(i10)).getAddress());
                }
                NetworkChangeDetector.NetworkInformation networkInformation = new NetworkChangeDetector.NetworkInformation(wifiP2pGroup.getInterface(), NetworkChangeDetector.ConnectionType.CONNECTION_WIFI, NetworkChangeDetector.ConnectionType.CONNECTION_NONE, 0L, iPAddressArr);
                this.wifiP2pNetworkInfo = networkInformation;
                this.observer.onNetworkConnect(networkInformation);
            } catch (SocketException e5) {
                Logging.e(NetworkMonitorAutoDetect.TAG, "Unable to get WifiP2p network interface", e5);
            }
        }

        private void onWifiP2pStateChange(int i10) {
            if (i10 == 1) {
                this.wifiP2pNetworkInfo = null;
                this.observer.onNetworkDisconnect(0L);
            }
        }

        public List<NetworkChangeDetector.NetworkInformation> getActiveNetworkList() {
            NetworkChangeDetector.NetworkInformation networkInformation = this.wifiP2pNetworkInfo;
            return networkInformation != null ? Collections.singletonList(networkInformation) : Collections.EMPTY_LIST;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.net.wifi.p2p.CONNECTION_STATE_CHANGE".equals(intent.getAction())) {
                lambda$new$0((WifiP2pGroup) intent.getParcelableExtra("p2pGroupInfo"));
            } else if ("android.net.wifi.p2p.STATE_CHANGED".equals(intent.getAction())) {
                onWifiP2pStateChange(intent.getIntExtra("wifi_p2p_state", 0));
            }
        }

        public void release() {
            this.context.unregisterReceiver(this);
        }
    }

    public NetworkMonitorAutoDetect(NetworkChangeDetector.Observer observer, Context context) {
        HashSet hashSet = new HashSet();
        this.availableNetworks = hashSet;
        this.observer = observer;
        this.context = context;
        this.connectivityManagerDelegate = new ConnectivityManagerDelegate(context, hashSet, observer.getFieldTrialsString());
        this.wifiManagerDelegate = new WifiManagerDelegate(context);
        NetworkState networkState = this.connectivityManagerDelegate.getNetworkState();
        this.connectionType = getConnectionType(networkState);
        this.wifiSSID = getWifiSSID(networkState);
        this.intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        if (includeWifiDirect) {
            this.wifiDirectManagerDelegate = new WifiDirectManagerDelegate(observer, context);
        }
        registerReceiver();
        ConnectivityManager.NetworkCallback networkCallback = null;
        if (!this.connectivityManagerDelegate.supportNetworkCallback()) {
            this.mobileNetworkCallback = null;
            this.allNetworkCallback = null;
            return;
        }
        ConnectivityManager.NetworkCallback networkCallback2 = new ConnectivityManager.NetworkCallback();
        try {
            this.connectivityManagerDelegate.requestMobileNetwork(networkCallback2);
            networkCallback = networkCallback2;
        } catch (SecurityException unused) {
            Logging.w(TAG, "Unable to obtain permission to request a cellular network.");
        }
        this.mobileNetworkCallback = networkCallback;
        SimpleNetworkCallback simpleNetworkCallback = new SimpleNetworkCallback(this.availableNetworks);
        this.allNetworkCallback = simpleNetworkCallback;
        this.connectivityManagerDelegate.registerNetworkCallback(simpleNetworkCallback);
    }

    private void connectionTypeChanged(NetworkState networkState) {
        NetworkChangeDetector.ConnectionType connectionType = getConnectionType(networkState);
        String wifiSSID = getWifiSSID(networkState);
        if (connectionType == this.connectionType && wifiSSID.equals(this.wifiSSID)) {
            return;
        }
        this.connectionType = connectionType;
        this.wifiSSID = wifiSSID;
        Logging.d(TAG, "Network connectivity changed, type is: " + connectionType);
        this.observer.onConnectionTypeChanged(connectionType);
    }

    private static NetworkChangeDetector.ConnectionType getConnectionType(boolean z, int i10, int i11) {
        if (!z) {
            return NetworkChangeDetector.ConnectionType.CONNECTION_NONE;
        }
        if (i10 != 0) {
            if (i10 == 1) {
                return NetworkChangeDetector.ConnectionType.CONNECTION_WIFI;
            }
            if (i10 != 4 && i10 != 5) {
                return i10 != 6 ? i10 != 7 ? i10 != 9 ? i10 != 17 ? NetworkChangeDetector.ConnectionType.CONNECTION_UNKNOWN : NetworkChangeDetector.ConnectionType.CONNECTION_VPN : NetworkChangeDetector.ConnectionType.CONNECTION_ETHERNET : NetworkChangeDetector.ConnectionType.CONNECTION_BLUETOOTH : NetworkChangeDetector.ConnectionType.CONNECTION_4G;
            }
        }
        switch (i11) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
            case 16:
                return NetworkChangeDetector.ConnectionType.CONNECTION_2G;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 17:
                return NetworkChangeDetector.ConnectionType.CONNECTION_3G;
            case 13:
            case 18:
                return NetworkChangeDetector.ConnectionType.CONNECTION_4G;
            case 19:
            default:
                return NetworkChangeDetector.ConnectionType.CONNECTION_UNKNOWN_CELLULAR;
            case 20:
                return NetworkChangeDetector.ConnectionType.CONNECTION_5G;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static NetworkChangeDetector.ConnectionType getUnderlyingConnectionTypeForVpn(NetworkState networkState) {
        return networkState.getNetworkType() != 17 ? NetworkChangeDetector.ConnectionType.CONNECTION_NONE : getConnectionType(networkState.isConnected(), networkState.getUnderlyingNetworkTypeForVpn(), networkState.getUnderlyingNetworkSubtypeForVpn());
    }

    private String getWifiSSID(NetworkState networkState) {
        return getConnectionType(networkState) != NetworkChangeDetector.ConnectionType.CONNECTION_WIFI ? "" : this.wifiManagerDelegate.getWifiSSID();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long networkToNetId(Network network) {
        return network.getNetworkHandle();
    }

    private void registerReceiver() {
        if (this.isRegistered) {
            return;
        }
        this.isRegistered = true;
        this.context.registerReceiver(this, this.intentFilter);
    }

    public static void setIncludeWifiDirect(boolean z) {
        includeWifiDirect = z;
    }

    private void unregisterReceiver() {
        if (this.isRegistered) {
            this.isRegistered = false;
            this.context.unregisterReceiver(this);
        }
    }

    @Override // com.discord.org.webrtc.NetworkChangeDetector
    public void destroy() {
        ConnectivityManager.NetworkCallback networkCallback = this.allNetworkCallback;
        if (networkCallback != null) {
            this.connectivityManagerDelegate.releaseCallback(networkCallback);
        }
        ConnectivityManager.NetworkCallback networkCallback2 = this.mobileNetworkCallback;
        if (networkCallback2 != null) {
            this.connectivityManagerDelegate.releaseCallback(networkCallback2);
        }
        WifiDirectManagerDelegate wifiDirectManagerDelegate = this.wifiDirectManagerDelegate;
        if (wifiDirectManagerDelegate != null) {
            wifiDirectManagerDelegate.release();
        }
        unregisterReceiver();
    }

    @Override // com.discord.org.webrtc.NetworkChangeDetector
    public List<NetworkChangeDetector.NetworkInformation> getActiveNetworkList() {
        List<NetworkChangeDetector.NetworkInformation> activeNetworkList = this.connectivityManagerDelegate.getActiveNetworkList();
        if (activeNetworkList == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(activeNetworkList);
        WifiDirectManagerDelegate wifiDirectManagerDelegate = this.wifiDirectManagerDelegate;
        if (wifiDirectManagerDelegate != null) {
            arrayList.addAll(wifiDirectManagerDelegate.getActiveNetworkList());
        }
        return arrayList;
    }

    @Override // com.discord.org.webrtc.NetworkChangeDetector
    public NetworkChangeDetector.ConnectionType getCurrentConnectionType() {
        return getConnectionType(getCurrentNetworkState());
    }

    public NetworkState getCurrentNetworkState() {
        return this.connectivityManagerDelegate.getNetworkState();
    }

    public long getDefaultNetId() {
        return this.connectivityManagerDelegate.getDefaultNetId();
    }

    public boolean isReceiverRegisteredForTesting() {
        return this.isRegistered;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        NetworkState currentNetworkState = getCurrentNetworkState();
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            connectionTypeChanged(currentNetworkState);
        }
    }

    public void setConnectivityManagerDelegateForTests(ConnectivityManagerDelegate connectivityManagerDelegate) {
        this.connectivityManagerDelegate = connectivityManagerDelegate;
    }

    public void setWifiManagerDelegateForTests(WifiManagerDelegate wifiManagerDelegate) {
        this.wifiManagerDelegate = wifiManagerDelegate;
    }

    @Override // com.discord.org.webrtc.NetworkChangeDetector
    public boolean supportNetworkCallback() {
        return this.connectivityManagerDelegate.supportNetworkCallback();
    }

    public static class WifiManagerDelegate {
        private final Context context;

        public WifiManagerDelegate(Context context) {
            this.context = context;
        }

        public String getWifiSSID() {
            WifiInfo wifiInfo;
            String ssid;
            Intent intentRegisterReceiver = this.context.registerReceiver(null, new IntentFilter("android.net.wifi.STATE_CHANGE"));
            return (intentRegisterReceiver == null || (wifiInfo = (WifiInfo) intentRegisterReceiver.getParcelableExtra("wifiInfo")) == null || (ssid = wifiInfo.getSSID()) == null) ? "" : ssid;
        }

        public WifiManagerDelegate() {
            this.context = null;
        }
    }

    public static NetworkChangeDetector.ConnectionType getConnectionType(NetworkState networkState) {
        return getConnectionType(networkState.isConnected(), networkState.getNetworkType(), networkState.getNetworkSubType());
    }
}
