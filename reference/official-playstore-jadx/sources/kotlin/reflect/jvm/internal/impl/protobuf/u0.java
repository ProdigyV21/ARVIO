package kotlin.reflect.jvm.internal.impl.protobuf;

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
/* JADX INFO: loaded from: classes5.dex */
public class u0 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final u0 f19829m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final u0 f19830n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final r0 f19831o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final s0 f19832p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final u0 f19833q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final /* synthetic */ u0[] f19834r;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final v0 f19835i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f19836l;

    /* JADX INFO: Fake field, exist only in values array */
    u0 EF0;

    /* JADX INFO: Fake field, exist only in values array */
    u0 EF1;

    /* JADX INFO: Fake field, exist only in values array */
    u0 EF2;

    static {
        u0 u0Var = new u0("DOUBLE", 0, v0.f19840o, 1);
        u0 u0Var2 = new u0("FLOAT", 1, v0.f19839n, 5);
        v0 v0Var = v0.f19838m;
        u0 u0Var3 = new u0("INT64", 2, v0Var, 0);
        u0 u0Var4 = new u0("UINT64", 3, v0Var, 0);
        v0 v0Var2 = v0.f19837l;
        u0 u0Var5 = new u0("INT32", 4, v0Var2, 0);
        f19829m = u0Var5;
        u0 u0Var6 = new u0("FIXED64", 5, v0Var, 1);
        u0 u0Var7 = new u0("FIXED32", 6, v0Var2, 5);
        u0 u0Var8 = new u0("BOOL", 7, v0.f19841p, 0);
        f19830n = u0Var8;
        q0 q0Var = new q0("STRING", 8, v0.f19842q, 2);
        v0 v0Var3 = v0.f19845t;
        r0 r0Var = new r0("GROUP", 9, v0Var3, 3);
        f19831o = r0Var;
        s0 s0Var = new s0("MESSAGE", 10, v0Var3, 2);
        f19832p = s0Var;
        t0 t0Var = new t0("BYTES", 11, v0.f19843r, 2);
        u0 u0Var9 = new u0("UINT32", 12, v0Var2, 0);
        u0 u0Var10 = new u0("ENUM", 13, v0.f19844s, 0);
        f19833q = u0Var10;
        f19834r = new u0[]{u0Var, u0Var2, u0Var3, u0Var4, u0Var5, u0Var6, u0Var7, u0Var8, q0Var, r0Var, s0Var, t0Var, u0Var9, u0Var10, new u0("SFIXED32", 14, v0Var2, 5), new u0("SFIXED64", 15, v0Var, 1), new u0("SINT32", 16, v0Var2, 0), new u0("SINT64", 17, v0Var, 0)};
    }

    public u0(String str, int i10, v0 v0Var, int i11) {
        this.f19835i = v0Var;
        this.f19836l = i11;
    }

    public static u0 valueOf(String str) {
        return (u0) Enum.valueOf(u0.class, str);
    }

    public static u0[] values() {
        return (u0[]) f19834r.clone();
    }

    public boolean a() {
        return !(this instanceof q0);
    }
}
