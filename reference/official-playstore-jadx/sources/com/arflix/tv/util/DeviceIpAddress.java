package com.arflix.tv.util;

import android.content.Context;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/arflix/tv/util/DeviceIpAddress;", "", "<init>", "()V", "get", "", "context", "Landroid/content/Context;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DeviceIpAddress {
    public static final int $stable = 0;
    public static final DeviceIpAddress INSTANCE = new DeviceIpAddress();

    private DeviceIpAddress() {
    }

    public final String get(Context context) {
        Object next;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces == null) {
                return null;
            }
            ArrayList list = Collections.list(networkInterfaces);
            ArrayList arrayList = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                x.b0(arrayList, Collections.list(((NetworkInterface) it.next()).getInetAddresses()));
            }
            Iterator it2 = arrayList.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    next = null;
                    break;
                }
                next = it2.next();
                InetAddress inetAddress = (InetAddress) next;
                if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                    break;
                }
            }
            InetAddress inetAddress2 = (InetAddress) next;
            if (inetAddress2 != null) {
                return inetAddress2.getHostAddress();
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }
}
