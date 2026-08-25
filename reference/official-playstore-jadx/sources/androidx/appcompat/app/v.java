package androidx.appcompat.app;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class v implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f1196i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Context f1197l;

    public /* synthetic */ v(Context context, int i10) {
        this.f1196i = i10;
        this.f1197l = context;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x009b  */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            r11 = this;
            int r0 = r11.f1196i
            switch(r0) {
                case 0: goto L30;
                case 1: goto L14;
                default: goto L5;
            }
        L5:
            androidx.credentials.a r0 = new androidx.credentials.a
            r1 = 0
            r0.<init>(r1)
            io.sentry.util.l r1 = v1.g.f22135a
            r2 = 0
            android.content.Context r3 = r11.f1197l
            v1.g.t(r3, r0, r1, r2)
            return
        L14:
            java.util.concurrent.ThreadPoolExecutor r4 = new java.util.concurrent.ThreadPoolExecutor
            java.util.concurrent.TimeUnit r9 = java.util.concurrent.TimeUnit.MILLISECONDS
            java.util.concurrent.LinkedBlockingQueue r10 = new java.util.concurrent.LinkedBlockingQueue
            r10.<init>()
            r5 = 0
            r6 = 1
            r7 = 0
            r4.<init>(r5, r6, r7, r9, r10)
            androidx.appcompat.app.v r0 = new androidx.appcompat.app.v
            r1 = 2
            android.content.Context r2 = r11.f1197l
            r0.<init>(r2, r1)
            r4.execute(r0)
            return
        L30:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 33
            r2 = 1
            if (r0 < r1) goto Lb3
            android.content.ComponentName r0 = new android.content.ComponentName
            java.lang.String r1 = "androidx.appcompat.app.AppLocalesMetadataHolderService"
            android.content.Context r3 = r11.f1197l
            r0.<init>(r3, r1)
            android.content.pm.PackageManager r1 = r3.getPackageManager()
            int r1 = r1.getComponentEnabledSetting(r0)
            if (r1 == r2) goto Lb3
            boolean r1 = androidx.core.os.c.a()
            java.lang.String r4 = "locale"
            if (r1 == 0) goto L8e
            t.f r1 = androidx.appcompat.app.z.f1217q
            r1.getClass()
            t.a r5 = new t.a
            r5.<init>(r1)
        L5d:
            boolean r1 = r5.hasNext()
            if (r1 == 0) goto L7c
            java.lang.Object r1 = r5.next()
            java.lang.ref.WeakReference r1 = (java.lang.ref.WeakReference) r1
            java.lang.Object r1 = r1.get()
            androidx.appcompat.app.z r1 = (androidx.appcompat.app.z) r1
            if (r1 == 0) goto L5d
            androidx.appcompat.app.s0 r1 = (androidx.appcompat.app.s0) r1
            android.content.Context r1 = r1.f1189u
            if (r1 == 0) goto L5d
            java.lang.Object r1 = r1.getSystemService(r4)
            goto L7d
        L7c:
            r1 = 0
        L7d:
            if (r1 == 0) goto L93
            android.os.LocaleList r1 = androidx.appcompat.app.x.a(r1)
            androidx.core.os.m r5 = new androidx.core.os.m
            androidx.core.os.p r6 = new androidx.core.os.p
            r6.<init>(r1)
            r5.<init>(r6)
            goto L95
        L8e:
            androidx.core.os.m r5 = androidx.appcompat.app.z.f1213m
            if (r5 == 0) goto L93
            goto L95
        L93:
            androidx.core.os.m r5 = androidx.core.os.m.f2126b
        L95:
            boolean r1 = r5.d()
            if (r1 == 0) goto Lac
            java.lang.String r1 = com.google.common.util.concurrent.r0.C(r3)
            java.lang.Object r4 = r3.getSystemService(r4)
            if (r4 == 0) goto Lac
            android.os.LocaleList r1 = androidx.appcompat.app.w.a(r1)
            androidx.appcompat.app.x.b(r4, r1)
        Lac:
            android.content.pm.PackageManager r1 = r3.getPackageManager()
            r1.setComponentEnabledSetting(r0, r2, r2)
        Lb3:
            androidx.appcompat.app.z.f1216p = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.v.run():void");
    }
}
