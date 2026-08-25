package com.google.android.gms.internal.cast;

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
/* JADX INFO: loaded from: classes4.dex */
public final class ma {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final ma f13473l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final ma f13474m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final ma[] f13475n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final /* synthetic */ ma[] f13476o;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f13477i;

    /* JADX INFO: Fake field, exist only in values array */
    ma EF0;

    static {
        za zaVar = za.f13740o;
        ma maVar = new ma("DOUBLE", 0, 0, 1, zaVar);
        za zaVar2 = za.f13739n;
        ma maVar2 = new ma("FLOAT", 1, 1, 1, zaVar2);
        za zaVar3 = za.f13738m;
        ma maVar3 = new ma("INT64", 2, 2, 1, zaVar3);
        ma maVar4 = new ma("UINT64", 3, 3, 1, zaVar3);
        za zaVar4 = za.f13737l;
        ma maVar5 = new ma("INT32", 4, 4, 1, zaVar4);
        ma maVar6 = new ma("FIXED64", 5, 5, 1, zaVar3);
        ma maVar7 = new ma("FIXED32", 6, 6, 1, zaVar4);
        za zaVar5 = za.f13741p;
        ma maVar8 = new ma("BOOL", 7, 7, 1, zaVar5);
        za zaVar6 = za.f13742q;
        ma maVar9 = new ma("STRING", 8, 8, 1, zaVar6);
        za zaVar7 = za.f13745t;
        ma maVar10 = new ma("MESSAGE", 9, 9, 1, zaVar7);
        za zaVar8 = za.f13743r;
        ma maVar11 = new ma("BYTES", 10, 10, 1, zaVar8);
        ma maVar12 = new ma("UINT32", 11, 11, 1, zaVar4);
        za zaVar9 = za.f13744s;
        ma maVar13 = new ma("ENUM", 12, 12, 1, zaVar9);
        ma maVar14 = new ma("SFIXED32", 13, 13, 1, zaVar4);
        ma maVar15 = new ma("SFIXED64", 14, 14, 1, zaVar3);
        ma maVar16 = new ma("SINT32", 15, 15, 1, zaVar4);
        ma maVar17 = new ma("SINT64", 16, 16, 1, zaVar3);
        ma maVar18 = new ma("GROUP", 17, 17, 1, zaVar7);
        ma maVar19 = new ma("DOUBLE_LIST", 18, 18, 2, zaVar);
        ma maVar20 = new ma("FLOAT_LIST", 19, 19, 2, zaVar2);
        ma maVar21 = new ma("INT64_LIST", 20, 20, 2, zaVar3);
        ma maVar22 = new ma("UINT64_LIST", 21, 21, 2, zaVar3);
        ma maVar23 = new ma("INT32_LIST", 22, 22, 2, zaVar4);
        ma maVar24 = new ma("FIXED64_LIST", 23, 23, 2, zaVar3);
        ma maVar25 = new ma("FIXED32_LIST", 24, 24, 2, zaVar4);
        ma maVar26 = new ma("BOOL_LIST", 25, 25, 2, zaVar5);
        ma maVar27 = new ma("STRING_LIST", 26, 26, 2, zaVar6);
        ma maVar28 = new ma("MESSAGE_LIST", 27, 27, 2, zaVar7);
        ma maVar29 = new ma("BYTES_LIST", 28, 28, 2, zaVar8);
        ma maVar30 = new ma("UINT32_LIST", 29, 29, 2, zaVar4);
        ma maVar31 = new ma("ENUM_LIST", 30, 30, 2, zaVar9);
        ma maVar32 = new ma("SFIXED32_LIST", 31, 31, 2, zaVar4);
        ma maVar33 = new ma("SFIXED64_LIST", 32, 32, 2, zaVar3);
        ma maVar34 = new ma("SINT32_LIST", 33, 33, 2, zaVar4);
        ma maVar35 = new ma("SINT64_LIST", 34, 34, 2, zaVar3);
        ma maVar36 = new ma("DOUBLE_LIST_PACKED", 35, 35, 3, zaVar);
        f13473l = maVar36;
        ma maVar37 = new ma("FLOAT_LIST_PACKED", 36, 36, 3, zaVar2);
        ma maVar38 = new ma("INT64_LIST_PACKED", 37, 37, 3, zaVar3);
        ma maVar39 = new ma("UINT64_LIST_PACKED", 38, 38, 3, zaVar3);
        ma maVar40 = new ma("INT32_LIST_PACKED", 39, 39, 3, zaVar4);
        ma maVar41 = new ma("FIXED64_LIST_PACKED", 40, 40, 3, zaVar3);
        ma maVar42 = new ma("FIXED32_LIST_PACKED", 41, 41, 3, zaVar4);
        ma maVar43 = new ma("BOOL_LIST_PACKED", 42, 42, 3, zaVar5);
        ma maVar44 = new ma("UINT32_LIST_PACKED", 43, 43, 3, zaVar4);
        ma maVar45 = new ma("ENUM_LIST_PACKED", 44, 44, 3, zaVar9);
        ma maVar46 = new ma("SFIXED32_LIST_PACKED", 45, 45, 3, zaVar4);
        ma maVar47 = new ma("SFIXED64_LIST_PACKED", 46, 46, 3, zaVar3);
        ma maVar48 = new ma("SINT32_LIST_PACKED", 47, 47, 3, zaVar4);
        ma maVar49 = new ma("SINT64_LIST_PACKED", 48, 48, 3, zaVar3);
        f13474m = maVar49;
        f13476o = new ma[]{maVar, maVar2, maVar3, maVar4, maVar5, maVar6, maVar7, maVar8, maVar9, maVar10, maVar11, maVar12, maVar13, maVar14, maVar15, maVar16, maVar17, maVar18, maVar19, maVar20, maVar21, maVar22, maVar23, maVar24, maVar25, maVar26, maVar27, maVar28, maVar29, maVar30, maVar31, maVar32, maVar33, maVar34, maVar35, maVar36, maVar37, maVar38, maVar39, maVar40, maVar41, maVar42, maVar43, maVar44, maVar45, maVar46, maVar47, maVar48, maVar49, new ma("GROUP_LIST", 49, 49, 2, zaVar7), new ma("MAP", 50, 50, 4, za.f13736i)};
        ma[] maVarArrValues = values();
        f13475n = new ma[maVarArrValues.length];
        for (ma maVar50 : maVarArrValues) {
            f13475n[maVar50.f13477i] = maVar50;
        }
    }

    public ma(String str, int i10, int i11, int i12, za zaVar) {
        this.f13477i = i11;
        int i13 = i12 - 1;
        if (i13 == 1 || i13 == 3) {
            zaVar.getClass();
        }
        if (i12 == 1) {
            za zaVar2 = za.f13736i;
            zaVar.ordinal();
        }
    }

    public static ma[] values() {
        return (ma[]) f13476o.clone();
    }
}
