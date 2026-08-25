package io.sentry;

import com.google.android.gms.cast.CredentialsData;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.File;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class p3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final io.sentry.protocol.v f17508a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final io.sentry.protocol.v f17509b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ConcurrentHashMap f17510c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final File f17511d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final double f17512e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f17513f = CredentialsData.CREDENTIALS_TYPE_ANDROID;

    public p3(io.sentry.protocol.v vVar, io.sentry.protocol.v vVar2, Map map, File file, z4 z4Var) {
        this.f17508a = vVar;
        this.f17509b = vVar2;
        this.f17510c = new ConcurrentHashMap(map);
        this.f17511d = file;
        this.f17512e = z4Var.i() / 1.0E9d;
    }
}
