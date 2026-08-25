package com.google.android.gms.common.api;

import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public interface g extends b {
    void connect(com.google.android.gms.common.internal.d dVar);

    void disconnect();

    void disconnect(String str);

    o3.d[] getAvailableFeatures();

    String getEndpointPackageName();

    String getLastDisconnectMessage();

    int getMinApkVersion();

    void getRemoteService(com.google.android.gms.common.internal.n nVar, Set set);

    Set getScopesForConnectionlessNonSignIn();

    boolean isConnected();

    boolean isConnecting();

    void onUserSignOut(com.google.android.gms.common.internal.e eVar);

    boolean requiresGooglePlayServices();

    boolean requiresSignIn();
}
