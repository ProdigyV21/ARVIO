package j$.util;

/* JADX INFO: loaded from: classes2.dex */
public final class f extends RuntimeException {
    public static void a(Object obj, String str) {
        throw new f("Unsupported " + str + " :" + obj);
    }
}
