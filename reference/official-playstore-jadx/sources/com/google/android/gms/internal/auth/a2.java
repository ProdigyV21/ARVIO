package com.google.android.gms.internal.auth;

/* JADX INFO: loaded from: classes4.dex */
public abstract class a2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final z1 f12981a = new z1();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final z1 f12982b;

    static {
        z1 z1Var = null;
        try {
            z1Var = (z1) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(null).newInstance(null);
        } catch (Exception unused) {
        }
        f12982b = z1Var;
    }
}
