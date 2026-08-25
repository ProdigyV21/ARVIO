package n9;

import d8.m;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: loaded from: classes5.dex */
public enum c {
    BOOLEAN(m.BOOLEAN, "boolean", "Z", "java.lang.Boolean"),
    CHAR(m.CHAR, "char", "C", "java.lang.Character"),
    BYTE(m.BYTE, "byte", "B", "java.lang.Byte"),
    SHORT(m.SHORT, "short", "S", "java.lang.Short"),
    INT(m.INT, "int", "I", "java.lang.Integer"),
    FLOAT(m.FLOAT, "float", "F", "java.lang.Float"),
    LONG(m.LONG, "long", "J", "java.lang.Long"),
    DOUBLE(m.DOUBLE, "double", "D", "java.lang.Double");


    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final HashSet f20507w = new HashSet();
    public static final HashMap x = new HashMap();

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final EnumMap f20508y = new EnumMap(m.class);
    public static final HashMap z = new HashMap();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final m f20509i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f20510l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f20511m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final f9.c f20512n;

    static {
        for (c cVar : values()) {
            f20507w.add(cVar.j());
            x.put(cVar.f20510l, cVar);
            f20508y.put(cVar.i(), cVar);
            z.put(cVar.f(), cVar);
        }
    }

    c(m mVar, String str, String str2, String str3) {
        if (mVar == null) {
            a(6);
            throw null;
        }
        this.f20509i = mVar;
        this.f20510l = str;
        this.f20511m = str2;
        this.f20512n = new f9.c(str3);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x000c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ void a(int r7) {
        /*
            Method dump skipped, instruction units count: 250
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: n9.c.a(int):void");
    }

    public static c d(String str) {
        c cVar = (c) x.get(str);
        if (cVar != null) {
            return cVar;
        }
        throw new AssertionError("Non-primitive type name passed: ".concat(str));
    }

    public final String f() {
        String str = this.f20511m;
        if (str != null) {
            return str;
        }
        a(12);
        throw null;
    }

    public final m i() {
        m mVar = this.f20509i;
        if (mVar != null) {
            return mVar;
        }
        a(10);
        throw null;
    }

    public final f9.c j() {
        f9.c cVar = this.f20512n;
        if (cVar != null) {
            return cVar;
        }
        a(13);
        throw null;
    }
}
