package androidx.datastore.preferences.protobuf;

import java.lang.reflect.Type;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'EF0' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: classes3.dex */
public final class l0 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final l0 f2462l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final l0 f2463m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final l0[] f2464n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final Type[] f2465o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final /* synthetic */ l0[] f2466p;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f2467i;

    /* JADX INFO: Fake field, exist only in values array */
    l0 EF0;

    static {
        g1 g1Var = g1.DOUBLE;
        l0 l0Var = new l0("DOUBLE", 0, 0, 1, g1Var);
        g1 g1Var2 = g1.FLOAT;
        l0 l0Var2 = new l0("FLOAT", 1, 1, 1, g1Var2);
        g1 g1Var3 = g1.LONG;
        l0 l0Var3 = new l0("INT64", 2, 2, 1, g1Var3);
        l0 l0Var4 = new l0("UINT64", 3, 3, 1, g1Var3);
        g1 g1Var4 = g1.INT;
        l0 l0Var5 = new l0("INT32", 4, 4, 1, g1Var4);
        l0 l0Var6 = new l0("FIXED64", 5, 5, 1, g1Var3);
        l0 l0Var7 = new l0("FIXED32", 6, 6, 1, g1Var4);
        g1 g1Var5 = g1.BOOLEAN;
        l0 l0Var8 = new l0("BOOL", 7, 7, 1, g1Var5);
        g1 g1Var6 = g1.STRING;
        l0 l0Var9 = new l0("STRING", 8, 8, 1, g1Var6);
        g1 g1Var7 = g1.MESSAGE;
        l0 l0Var10 = new l0("MESSAGE", 9, 9, 1, g1Var7);
        g1 g1Var8 = g1.BYTE_STRING;
        l0 l0Var11 = new l0("BYTES", 10, 10, 1, g1Var8);
        l0 l0Var12 = new l0("UINT32", 11, 11, 1, g1Var4);
        g1 g1Var9 = g1.ENUM;
        l0 l0Var13 = new l0("ENUM", 12, 12, 1, g1Var9);
        l0 l0Var14 = new l0("SFIXED32", 13, 13, 1, g1Var4);
        l0 l0Var15 = new l0("SFIXED64", 14, 14, 1, g1Var3);
        l0 l0Var16 = new l0("SINT32", 15, 15, 1, g1Var4);
        l0 l0Var17 = new l0("SINT64", 16, 16, 1, g1Var3);
        l0 l0Var18 = new l0("GROUP", 17, 17, 1, g1Var7);
        l0 l0Var19 = new l0("DOUBLE_LIST", 18, 18, 2, g1Var);
        l0 l0Var20 = new l0("FLOAT_LIST", 19, 19, 2, g1Var2);
        l0 l0Var21 = new l0("INT64_LIST", 20, 20, 2, g1Var3);
        l0 l0Var22 = new l0("UINT64_LIST", 21, 21, 2, g1Var3);
        l0 l0Var23 = new l0("INT32_LIST", 22, 22, 2, g1Var4);
        l0 l0Var24 = new l0("FIXED64_LIST", 23, 23, 2, g1Var3);
        l0 l0Var25 = new l0("FIXED32_LIST", 24, 24, 2, g1Var4);
        l0 l0Var26 = new l0("BOOL_LIST", 25, 25, 2, g1Var5);
        l0 l0Var27 = new l0("STRING_LIST", 26, 26, 2, g1Var6);
        l0 l0Var28 = new l0("MESSAGE_LIST", 27, 27, 2, g1Var7);
        l0 l0Var29 = new l0("BYTES_LIST", 28, 28, 2, g1Var8);
        l0 l0Var30 = new l0("UINT32_LIST", 29, 29, 2, g1Var4);
        l0 l0Var31 = new l0("ENUM_LIST", 30, 30, 2, g1Var9);
        l0 l0Var32 = new l0("SFIXED32_LIST", 31, 31, 2, g1Var4);
        l0 l0Var33 = new l0("SFIXED64_LIST", 32, 32, 2, g1Var3);
        l0 l0Var34 = new l0("SINT32_LIST", 33, 33, 2, g1Var4);
        l0 l0Var35 = new l0("SINT64_LIST", 34, 34, 2, g1Var3);
        l0 l0Var36 = new l0("DOUBLE_LIST_PACKED", 35, 35, 3, g1Var);
        f2462l = l0Var36;
        l0 l0Var37 = new l0("FLOAT_LIST_PACKED", 36, 36, 3, g1Var2);
        l0 l0Var38 = new l0("INT64_LIST_PACKED", 37, 37, 3, g1Var3);
        l0 l0Var39 = new l0("UINT64_LIST_PACKED", 38, 38, 3, g1Var3);
        l0 l0Var40 = new l0("INT32_LIST_PACKED", 39, 39, 3, g1Var4);
        l0 l0Var41 = new l0("FIXED64_LIST_PACKED", 40, 40, 3, g1Var3);
        l0 l0Var42 = new l0("FIXED32_LIST_PACKED", 41, 41, 3, g1Var4);
        l0 l0Var43 = new l0("BOOL_LIST_PACKED", 42, 42, 3, g1Var5);
        l0 l0Var44 = new l0("UINT32_LIST_PACKED", 43, 43, 3, g1Var4);
        l0 l0Var45 = new l0("ENUM_LIST_PACKED", 44, 44, 3, g1Var9);
        l0 l0Var46 = new l0("SFIXED32_LIST_PACKED", 45, 45, 3, g1Var4);
        l0 l0Var47 = new l0("SFIXED64_LIST_PACKED", 46, 46, 3, g1Var3);
        l0 l0Var48 = new l0("SINT32_LIST_PACKED", 47, 47, 3, g1Var4);
        l0 l0Var49 = new l0("SINT64_LIST_PACKED", 48, 48, 3, g1Var3);
        f2463m = l0Var49;
        f2466p = new l0[]{l0Var, l0Var2, l0Var3, l0Var4, l0Var5, l0Var6, l0Var7, l0Var8, l0Var9, l0Var10, l0Var11, l0Var12, l0Var13, l0Var14, l0Var15, l0Var16, l0Var17, l0Var18, l0Var19, l0Var20, l0Var21, l0Var22, l0Var23, l0Var24, l0Var25, l0Var26, l0Var27, l0Var28, l0Var29, l0Var30, l0Var31, l0Var32, l0Var33, l0Var34, l0Var35, l0Var36, l0Var37, l0Var38, l0Var39, l0Var40, l0Var41, l0Var42, l0Var43, l0Var44, l0Var45, l0Var46, l0Var47, l0Var48, l0Var49, new l0("GROUP_LIST", 49, 49, 2, g1Var7), new l0("MAP", 50, 50, 4, g1.VOID)};
        f2465o = new Type[0];
        l0[] l0VarArrValues = values();
        f2464n = new l0[l0VarArrValues.length];
        for (l0 l0Var50 : l0VarArrValues) {
            f2464n[l0Var50.f2467i] = l0Var50;
        }
    }

    public l0(String str, int i10, int i11, int i12, g1 g1Var) {
        this.f2467i = i11;
        int iC = h.f0.c(i12);
        if (iC == 1 || iC == 3) {
            g1Var.getClass();
        }
        if (i12 == 1) {
            g1Var.ordinal();
        }
    }

    public static l0 valueOf(String str) {
        return (l0) Enum.valueOf(l0.class, str);
    }

    public static l0[] values() {
        return (l0[]) f2466p.clone();
    }
}
