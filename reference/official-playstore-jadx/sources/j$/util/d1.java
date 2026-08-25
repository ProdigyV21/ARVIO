package j$.util;

import java.security.AccessController;

/* JADX INFO: loaded from: classes2.dex */
public abstract class d1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f18533a = ((Boolean) AccessController.doPrivileged(new c1(0))).booleanValue();

    public static void a(Class cls, String str) {
        throw new UnsupportedOperationException(cls + " tripwire tripped but logging not supported: " + str);
    }
}
