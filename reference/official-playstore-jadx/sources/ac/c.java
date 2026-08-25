package ac;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import java.util.HashMap;

/* JADX INFO: loaded from: classes5.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final HashMap f826a;

    static {
        HashMap map = new HashMap();
        f826a = map;
        map.put("x86", "x86");
        map.put("i386", "x86");
        map.put("i486", "x86");
        map.put("i586", "x86");
        map.put("i686", "x86");
        map.put("pentium", "x86");
        map.put("x86_64", "x86_64");
        map.put("amd64", "x86_64");
        map.put("em64t", "x86_64");
        map.put("universal", "x86_64");
        map.put("ia64", "ia64");
        map.put("ia64w", "ia64");
        map.put("ia64_32", "ia64_32");
        map.put("ia64n", "ia64_32");
        map.put("ppc", "ppc");
        map.put("power", "ppc");
        map.put("powerpc", "ppc");
        map.put("power_pc", "ppc");
        map.put("power_rs", "ppc");
        map.put("ppc64", "ppc64");
        map.put("power64", "ppc64");
        map.put("powerpc64", "ppc64");
        map.put("power_pc64", "ppc64");
        map.put("power_rs64", "ppc64");
        map.put("aarch64", "arm64");
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x009a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a() {
        /*
            Method dump skipped, instruction units count: 212
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: ac.c.a():java.lang.String");
    }

    public static String b() {
        return c() + DomExceptionUtils.SEPARATOR + a();
    }

    public static String c() {
        String property = System.getProperty("os.name");
        return property.contains("Windows") ? "Windows" : (property.contains("Mac") || property.contains("Darwin")) ? "Mac" : property.contains("Linux") ? "Linux" : property.contains("AIX") ? "AIX" : property.replaceAll("\\W", "");
    }
}
