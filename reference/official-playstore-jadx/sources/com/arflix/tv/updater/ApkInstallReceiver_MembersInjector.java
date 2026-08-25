package com.arflix.tv.updater;

import dagger.MembersInjector;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class ApkInstallReceiver_MembersInjector implements MembersInjector<ApkInstallReceiver> {
    private final Provider<UpdateStatusManager> updateStatusManagerProvider;

    private ApkInstallReceiver_MembersInjector(Provider<UpdateStatusManager> provider) {
        this.updateStatusManagerProvider = provider;
    }

    public static MembersInjector<ApkInstallReceiver> create(Provider<UpdateStatusManager> provider) {
        return new ApkInstallReceiver_MembersInjector(provider);
    }

    public static void injectUpdateStatusManager(ApkInstallReceiver apkInstallReceiver, UpdateStatusManager updateStatusManager) {
        apkInstallReceiver.updateStatusManager = updateStatusManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ApkInstallReceiver apkInstallReceiver) {
        injectUpdateStatusManager(apkInstallReceiver, this.updateStatusManagerProvider.get());
    }
}
