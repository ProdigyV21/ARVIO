package zb;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import java.io.IOError;
import java.io.IOException;
import java.util.Locale;

/* JADX INFO: loaded from: classes5.dex */
public abstract class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static j f23290a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static j f23291b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final boolean f23292c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final boolean f23293d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final boolean f23294e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final boolean f23295f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final int f23296g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final int f23297h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static boolean f23298i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static int f23299j;
    public static int k;

    static {
        boolean zContains = System.getProperty("os.name").toLowerCase(Locale.ENGLISH).contains("win");
        f23292c = zContains;
        boolean z = false;
        f23293d = zContains && System.getenv("PWD") != null && System.getenv("PWD").startsWith(DomExceptionUtils.SEPARATOR);
        f23294e = zContains && System.getenv("MSYSTEM") != null && (System.getenv("MSYSTEM").startsWith("MINGW") || System.getenv("MSYSTEM").equals("MSYS"));
        if (zContains && System.getenv("ConEmuPID") != null) {
            z = true;
        }
        f23295f = z;
        f23296g = 1;
        f23297h = 2;
        if (b("jansi.eager")) {
            c();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:68:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x013a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static zb.j a(boolean r15) {
        /*
            Method dump skipped, instruction units count: 417
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: zb.i.a(boolean):zb.j");
    }

    public static boolean b(String str) {
        try {
            String property = System.getProperty(str);
            if (property.isEmpty()) {
                return true;
            }
            return Boolean.parseBoolean(property);
        } catch (IllegalArgumentException | NullPointerException unused) {
            return false;
        }
    }

    public static synchronized void c() {
        if (!f23298i) {
            f23290a = a(true);
            f23291b = a(false);
            f23298i = true;
        }
    }

    public static synchronized void d() {
        try {
            int i10 = f23299j + 1;
            f23299j = i10;
            if (i10 == 1) {
                c();
                try {
                    f23290a.i();
                    f23291b.i();
                    System.setOut(f23290a);
                    System.setErr(f23291b);
                } catch (IOException e5) {
                    throw new IOError(e5);
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }
}
