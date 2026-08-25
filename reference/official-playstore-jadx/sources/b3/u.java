package b3;

import android.util.SparseArray;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
public final class u {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final SparseArray f7194i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final /* synthetic */ u[] f7195l;

    /* JADX INFO: Fake field, exist only in values array */
    u EF0;

    static {
        u uVar = new u("UNKNOWN_MOBILE_SUBTYPE", 0);
        u uVar2 = new u("GPRS", 1);
        u uVar3 = new u("EDGE", 2);
        u uVar4 = new u("UMTS", 3);
        u uVar5 = new u("CDMA", 4);
        u uVar6 = new u("EVDO_0", 5);
        u uVar7 = new u("EVDO_A", 6);
        u uVar8 = new u("RTT", 7);
        u uVar9 = new u("HSDPA", 8);
        u uVar10 = new u("HSUPA", 9);
        u uVar11 = new u("HSPA", 10);
        u uVar12 = new u("IDEN", 11);
        u uVar13 = new u("EVDO_B", 12);
        u uVar14 = new u("LTE", 13);
        u uVar15 = new u("EHRPD", 14);
        u uVar16 = new u("HSPAP", 15);
        u uVar17 = new u("GSM", 16);
        u uVar18 = new u("TD_SCDMA", 17);
        u uVar19 = new u("IWLAN", 18);
        u uVar20 = new u("LTE_CA", 19);
        f7195l = new u[]{uVar, uVar2, uVar3, uVar4, uVar5, uVar6, uVar7, uVar8, uVar9, uVar10, uVar11, uVar12, uVar13, uVar14, uVar15, uVar16, uVar17, uVar18, uVar19, uVar20, new u("COMBINED", 20)};
        SparseArray sparseArray = new SparseArray();
        f7194i = sparseArray;
        sparseArray.put(0, uVar);
        sparseArray.put(1, uVar2);
        sparseArray.put(2, uVar3);
        sparseArray.put(3, uVar4);
        sparseArray.put(4, uVar5);
        sparseArray.put(5, uVar6);
        sparseArray.put(6, uVar7);
        sparseArray.put(7, uVar8);
        sparseArray.put(8, uVar9);
        sparseArray.put(9, uVar10);
        sparseArray.put(10, uVar11);
        sparseArray.put(11, uVar12);
        sparseArray.put(12, uVar13);
        sparseArray.put(13, uVar14);
        sparseArray.put(14, uVar15);
        sparseArray.put(15, uVar16);
        sparseArray.put(16, uVar17);
        sparseArray.put(17, uVar18);
        sparseArray.put(18, uVar19);
        sparseArray.put(19, uVar20);
    }

    public static u valueOf(String str) {
        return (u) Enum.valueOf(u.class, str);
    }

    public static u[] values() {
        return (u[]) f7195l.clone();
    }
}
