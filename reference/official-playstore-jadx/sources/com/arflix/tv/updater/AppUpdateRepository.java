package com.arflix.tv.updater;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import com.arflix.tv.BuildConfig;
import com.google.gson.Gson;
import dagger.hilt.android.qualifiers.ApplicationContext;
import gb.h0;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Singleton
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u001b\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0011\u0010\t\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0004\b\t\u0010\nJ\r\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\rJ\r\u0010\u000f\u001a\u00020\b¢\u0006\u0004\b\u000f\u0010\nJ\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0086@¢\u0006\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0015R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0016R\u0014\u0010\u0018\u001a\u00020\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Lcom/arflix/tv/updater/AppUpdateRepository;", "", "Landroid/content/Context;", "context", "Lgb/h0;", "okHttpClient", "<init>", "(Landroid/content/Context;Lgb/h0;)V", "", "getInstallerPackageName", "()Ljava/lang/String;", "", "isPlayStoreInstall", "()Z", "supportsSelfUpdate", "getInstalledVersionName", "Lx6/d0;", "Lcom/arflix/tv/updater/AppUpdate;", "getLatestUpdate-IoAF18A", "(Ld7/d;)Ljava/lang/Object;", "getLatestUpdate", "Landroid/content/Context;", "Lgb/h0;", "Lcom/google/gson/Gson;", "gson", "Lcom/google/gson/Gson;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AppUpdateRepository {
    public static final int $stable = 8;
    private final Context context;
    private final Gson gson = new Gson();
    private final h0 okHttpClient;

    @Inject
    public AppUpdateRepository(@ApplicationContext Context context, h0 h0Var) {
        this.context = context;
        this.okHttpClient = h0Var;
    }

    private final String getInstallerPackageName() {
        try {
            return Build.VERSION.SDK_INT >= 30 ? this.context.getPackageManager().getInstallSourceInfo(this.context.getPackageName()).getInstallingPackageName() : this.context.getPackageManager().getInstallerPackageName(this.context.getPackageName());
        } catch (PackageManager.NameNotFoundException | Exception unused) {
            return null;
        }
    }

    public final String getInstalledVersionName() {
        try {
            String str = this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionName;
            return str == null ? BuildConfig.VERSION_NAME : str;
        } catch (Exception unused) {
            return BuildConfig.VERSION_NAME;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX INFO: renamed from: getLatestUpdate-IoAF18A, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6613getLatestUpdateIoAF18A(d7.d<? super x6.d0> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.arflix.tv.updater.AppUpdateRepository$getLatestUpdate$1
            if (r0 == 0) goto L13
            r0 = r5
            com.arflix.tv.updater.AppUpdateRepository$getLatestUpdate$1 r0 = (com.arflix.tv.updater.AppUpdateRepository$getLatestUpdate$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.updater.AppUpdateRepository$getLatestUpdate$1 r0 = new com.arflix.tv.updater.AppUpdateRepository$getLatestUpdate$1
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r5)
            goto L43
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L2d:
            k2.c.G(r5)
            ra.c r5 = ka.x0.f19655d
            com.arflix.tv.updater.AppUpdateRepository$getLatestUpdate$2 r1 = new com.arflix.tv.updater.AppUpdateRepository$getLatestUpdate$2
            r3 = 0
            r1.<init>(r4, r3)
            r0.label = r2
            java.lang.Object r5 = ka.m0.y(r5, r1, r0)
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L43
            return r0
        L43:
            x6.d0 r5 = (x6.d0) r5
            java.lang.Object r5 = r5.f22580i
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.updater.AppUpdateRepository.m6613getLatestUpdateIoAF18A(d7.d):java.lang.Object");
    }

    public final boolean isPlayStoreInstall() {
        return p.a(getInstallerPackageName(), "com.android.vending");
    }

    public final boolean supportsSelfUpdate() {
        return BuildConfig.SELF_UPDATE_ENABLED.booleanValue() && !isPlayStoreInstall();
    }
}
