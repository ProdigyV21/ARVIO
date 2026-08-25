package j$.util.stream;

import java.security.AccessController;

/* JADX INFO: loaded from: classes2.dex */
public abstract class g8 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f18703a = ((Boolean) AccessController.doPrivileged(new j$.util.c1(2))).booleanValue();

    public static void a(Class cls, String str) {
        throw new UnsupportedOperationException(cls + " tripwire tripped but logging not supported: " + str);
    }
}
