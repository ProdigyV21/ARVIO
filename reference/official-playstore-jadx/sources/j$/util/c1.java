package j$.util;

import java.security.PrivilegedAction;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class c1 implements PrivilegedAction {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f18455a;

    @Override // java.security.PrivilegedAction
    public final Object run() {
        switch (this.f18455a) {
            case 0:
                return Boolean.valueOf(Boolean.getBoolean("org.openjdk.java.util.stream.tripwire"));
            case 1:
                return Boolean.valueOf(Boolean.getBoolean("java.util.secureRandomSeed"));
            default:
                return Boolean.valueOf(Boolean.getBoolean("org.openjdk.java.util.stream.tripwire"));
        }
    }
}
