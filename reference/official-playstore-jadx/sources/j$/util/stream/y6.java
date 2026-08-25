package j$.util.stream;

import j$.util.Map;
import j$.util.Spliterator;
import java.util.EnumMap;
import java.util.Map;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'DISTINCT' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: classes2.dex */
public final class y6 {
    public static final y6 DISTINCT;
    public static final y6 ORDERED;
    public static final y6 SHORT_CIRCUIT;
    public static final y6 SIZED;
    public static final y6 SORTED;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int f18963f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final int f18964g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final int f18965h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final int f18966i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final int f18967j;
    public static final int k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final int f18968l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final int f18969m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final int f18970n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final int f18971o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final int f18972p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final int f18973q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final int f18974r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final int f18975s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final int f18976t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final int f18977u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final /* synthetic */ y6[] f18978v;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map f18979a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f18980b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f18981c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f18982d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f18983e;

    public static y6 valueOf(String str) {
        return (y6) Enum.valueOf(y6.class, str);
    }

    public static y6[] values() {
        return (y6[]) f18978v.clone();
    }

    static {
        x6 x6Var = x6.SPLITERATOR;
        j$.util.function.i iVarC = C(x6Var);
        x6 x6Var2 = x6.STREAM;
        iVarC.a(x6Var2);
        x6 x6Var3 = x6.OP;
        ((EnumMap) ((Map) iVarC.f18557b)).put(x6Var3, 3);
        y6 y6Var = new y6("DISTINCT", 0, 0, iVarC);
        DISTINCT = y6Var;
        j$.util.function.i iVarC2 = C(x6Var);
        iVarC2.a(x6Var2);
        ((EnumMap) ((Map) iVarC2.f18557b)).put(x6Var3, 3);
        y6 y6Var2 = new y6("SORTED", 1, 1, iVarC2);
        SORTED = y6Var2;
        j$.util.function.i iVarC3 = C(x6Var);
        iVarC3.a(x6Var2);
        ((EnumMap) ((Map) iVarC3.f18557b)).put(x6Var3, 3);
        x6 x6Var4 = x6.TERMINAL_OP;
        ((EnumMap) ((Map) iVarC3.f18557b)).put(x6Var4, 2);
        x6 x6Var5 = x6.UPSTREAM_TERMINAL_OP;
        ((EnumMap) ((Map) iVarC3.f18557b)).put(x6Var5, 2);
        y6 y6Var3 = new y6("ORDERED", 2, 2, iVarC3);
        ORDERED = y6Var3;
        j$.util.function.i iVarC4 = C(x6Var);
        iVarC4.a(x6Var2);
        ((EnumMap) ((Map) iVarC4.f18557b)).put(x6Var3, 2);
        y6 y6Var4 = new y6("SIZED", 3, 3, iVarC4);
        SIZED = y6Var4;
        j$.util.function.i iVarC5 = C(x6Var3);
        iVarC5.a(x6Var4);
        int i10 = 0;
        y6 y6Var5 = new y6("SHORT_CIRCUIT", 4, 12, iVarC5);
        SHORT_CIRCUIT = y6Var5;
        f18978v = new y6[]{y6Var, y6Var2, y6Var3, y6Var4, y6Var5};
        f18963f = m(x6Var);
        f18964g = m(x6Var2);
        f18965h = m(x6Var3);
        m(x6Var4);
        m(x6Var5);
        for (y6 y6Var6 : values()) {
            i10 |= y6Var6.f18983e;
        }
        f18966i = i10;
        int i11 = f18964g;
        f18967j = i11;
        int i12 = i11 << 1;
        k = i12;
        f18968l = i11 | i12;
        y6 y6Var7 = DISTINCT;
        f18969m = y6Var7.f18981c;
        f18970n = y6Var7.f18982d;
        y6 y6Var8 = SORTED;
        f18971o = y6Var8.f18981c;
        f18972p = y6Var8.f18982d;
        y6 y6Var9 = ORDERED;
        f18973q = y6Var9.f18981c;
        f18974r = y6Var9.f18982d;
        y6 y6Var10 = SIZED;
        f18975s = y6Var10.f18981c;
        f18976t = y6Var10.f18982d;
        f18977u = SHORT_CIRCUIT.f18981c;
    }

    public static j$.util.function.i C(x6 x6Var) {
        j$.util.function.i iVar = new j$.util.function.i(new EnumMap(x6.class), 8);
        iVar.a(x6Var);
        return iVar;
    }

    public y6(String str, int i10, int i11, j$.util.function.i iVar) {
        for (x6 x6Var : x6.values()) {
            Map.EL.putIfAbsent((java.util.Map) iVar.f18557b, x6Var, 0);
        }
        this.f18979a = (java.util.Map) iVar.f18557b;
        int i12 = i11 * 2;
        this.f18980b = i12;
        this.f18981c = 1 << i12;
        this.f18982d = 2 << i12;
        this.f18983e = 3 << i12;
    }

    public final boolean r(int i10) {
        return (i10 & this.f18983e) == this.f18981c;
    }

    public static int m(x6 x6Var) {
        int iIntValue = 0;
        for (y6 y6Var : values()) {
            iIntValue |= ((Integer) y6Var.f18979a.get(x6Var)).intValue() << y6Var.f18980b;
        }
        return iIntValue;
    }

    public static int l(int i10, int i11) {
        int i12;
        if (i10 == 0) {
            i12 = f18966i;
        } else {
            i12 = ~(((f18967j & i10) << 1) | i10 | ((k & i10) >> 1));
        }
        return i10 | (i11 & i12);
    }

    public static int n(Spliterator spliterator) {
        int iCharacteristics = spliterator.characteristics();
        int i10 = iCharacteristics & 4;
        int i11 = f18963f;
        return (i10 == 0 || spliterator.getComparator() == null) ? iCharacteristics & i11 : iCharacteristics & i11 & (-5);
    }
}
