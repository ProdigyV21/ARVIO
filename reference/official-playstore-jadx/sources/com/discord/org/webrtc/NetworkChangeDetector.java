package com.discord.org.webrtc;

import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface NetworkChangeDetector {

    public enum ConnectionType {
        CONNECTION_UNKNOWN,
        CONNECTION_ETHERNET,
        CONNECTION_WIFI,
        CONNECTION_5G,
        CONNECTION_4G,
        CONNECTION_3G,
        CONNECTION_2G,
        CONNECTION_UNKNOWN_CELLULAR,
        CONNECTION_BLUETOOTH,
        CONNECTION_VPN,
        CONNECTION_NONE
    }

    public static class IPAddress {
        public final byte[] address;

        public IPAddress(byte[] bArr) {
            this.address = bArr;
        }

        private byte[] getAddress() {
            return this.address;
        }
    }

    public static class NetworkInformation {
        public final long handle;
        public final IPAddress[] ipAddresses;
        public final String name;
        public final ConnectionType type;
        public final ConnectionType underlyingTypeForVpn;

        public NetworkInformation(String str, ConnectionType connectionType, ConnectionType connectionType2, long j10, IPAddress[] iPAddressArr) {
            this.name = str;
            this.type = connectionType;
            this.underlyingTypeForVpn = connectionType2;
            this.handle = j10;
            this.ipAddresses = iPAddressArr;
        }

        private ConnectionType getConnectionType() {
            return this.type;
        }

        private long getHandle() {
            return this.handle;
        }

        private IPAddress[] getIpAddresses() {
            return this.ipAddresses;
        }

        private String getName() {
            return this.name;
        }

        private ConnectionType getUnderlyingConnectionTypeForVpn() {
            return this.underlyingTypeForVpn;
        }
    }

    public static abstract class Observer {
        public String getFieldTrialsString() {
            return "";
        }

        public abstract void onConnectionTypeChanged(ConnectionType connectionType);

        public abstract void onNetworkConnect(NetworkInformation networkInformation);

        public abstract void onNetworkDisconnect(long j10);

        public abstract void onNetworkPreference(List<ConnectionType> list, int i10);
    }

    void destroy();

    List<NetworkInformation> getActiveNetworkList();

    ConnectionType getCurrentConnectionType();

    boolean supportNetworkCallback();
}
