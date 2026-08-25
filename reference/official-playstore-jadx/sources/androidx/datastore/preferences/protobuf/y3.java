package androidx.datastore.preferences.protobuf;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'EF2' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: classes3.dex */
public class y3 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final u3 f2565m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final v3 f2566n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final w3 f2567o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final /* synthetic */ y3[] f2568p;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final z3 f2569i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f2570l;

    /* JADX INFO: Fake field, exist only in values array */
    y3 EF0;

    /* JADX INFO: Fake field, exist only in values array */
    y3 EF1;

    /* JADX INFO: Fake field, exist only in values array */
    y3 EF2;

    static {
        y3 y3Var = new y3("DOUBLE", 0, z3.DOUBLE, 1);
        y3 y3Var2 = new y3("FLOAT", 1, z3.FLOAT, 5);
        z3 z3Var = z3.LONG;
        y3 y3Var3 = new y3("INT64", 2, z3Var, 0);
        y3 y3Var4 = new y3("UINT64", 3, z3Var, 0);
        z3 z3Var2 = z3.INT;
        y3 y3Var5 = new y3("INT32", 4, z3Var2, 0);
        y3 y3Var6 = new y3("FIXED64", 5, z3Var, 1);
        y3 y3Var7 = new y3("FIXED32", 6, z3Var2, 5);
        y3 y3Var8 = new y3("BOOL", 7, z3.BOOLEAN, 0);
        u3 u3Var = new u3("STRING", 8, z3.STRING, 2);
        f2565m = u3Var;
        z3 z3Var3 = z3.MESSAGE;
        v3 v3Var = new v3("GROUP", 9, z3Var3, 3);
        f2566n = v3Var;
        w3 w3Var = new w3("MESSAGE", 10, z3Var3, 2);
        f2567o = w3Var;
        f2568p = new y3[]{y3Var, y3Var2, y3Var3, y3Var4, y3Var5, y3Var6, y3Var7, y3Var8, u3Var, v3Var, w3Var, new x3("BYTES", 11, z3.BYTE_STRING, 2), new y3("UINT32", 12, z3Var2, 0), new y3("ENUM", 13, z3.ENUM, 0), new y3("SFIXED32", 14, z3Var2, 5), new y3("SFIXED64", 15, z3Var, 1), new y3("SINT32", 16, z3Var2, 0), new y3("SINT64", 17, z3Var, 0)};
    }

    public y3(String str, int i10, z3 z3Var, int i11) {
        this.f2569i = z3Var;
        this.f2570l = i11;
    }

    public static y3 valueOf(String str) {
        return (y3) Enum.valueOf(y3.class, str);
    }

    public static y3[] values() {
        return (y3[]) f2568p.clone();
    }
}
