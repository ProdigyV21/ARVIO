package com.google.android.gms.internal.cast;

import android.content.Context;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.cast.internal.zzn;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes4.dex */
public final class z4 {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final Logger f13708j = new Logger("ClientCastAnalytics");
    public static boolean k = true;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SessionManager f13709a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c1 f13710b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c0 f13711c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Long f13713e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public z2.e f13715g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public h1 f13716h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f13717i = 1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f13712d = UUID.randomUUID().toString();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ExecutorService f13714f = Executors.unconfigurableExecutorService(Executors.newCachedThreadPool());

    public z4(Context context, zzn zznVar, SessionManager sessionManager, c1 c1Var, c0 c0Var) {
        this.f13709a = sessionManager;
        this.f13710b = c1Var;
        this.f13711c = c0Var;
    }

    public final void a(c6 c6Var, int i10) {
        this.f13714f.execute(new android.support.v4.os.e(i10, 6, this, c6Var));
    }
}
