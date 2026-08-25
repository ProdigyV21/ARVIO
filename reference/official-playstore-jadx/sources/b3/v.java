package b3;

import android.util.SparseArray;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
public final class v {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final SparseArray f7196i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final /* synthetic */ v[] f7197l;

    /* JADX INFO: Fake field, exist only in values array */
    v EF0;

    static {
        v vVar = new v("MOBILE", 0);
        v vVar2 = new v("WIFI", 1);
        v vVar3 = new v("MOBILE_MMS", 2);
        v vVar4 = new v("MOBILE_SUPL", 3);
        v vVar5 = new v("MOBILE_DUN", 4);
        v vVar6 = new v("MOBILE_HIPRI", 5);
        v vVar7 = new v("WIMAX", 6);
        v vVar8 = new v("BLUETOOTH", 7);
        v vVar9 = new v("DUMMY", 8);
        v vVar10 = new v("ETHERNET", 9);
        v vVar11 = new v("MOBILE_FOTA", 10);
        v vVar12 = new v("MOBILE_IMS", 11);
        v vVar13 = new v("MOBILE_CBS", 12);
        v vVar14 = new v("WIFI_P2P", 13);
        v vVar15 = new v("MOBILE_IA", 14);
        v vVar16 = new v("MOBILE_EMERGENCY", 15);
        v vVar17 = new v("PROXY", 16);
        v vVar18 = new v("VPN", 17);
        v vVar19 = new v("NONE", 18);
        f7197l = new v[]{vVar, vVar2, vVar3, vVar4, vVar5, vVar6, vVar7, vVar8, vVar9, vVar10, vVar11, vVar12, vVar13, vVar14, vVar15, vVar16, vVar17, vVar18, vVar19};
        SparseArray sparseArray = new SparseArray();
        f7196i = sparseArray;
        sparseArray.put(0, vVar);
        sparseArray.put(1, vVar2);
        sparseArray.put(2, vVar3);
        sparseArray.put(3, vVar4);
        sparseArray.put(4, vVar5);
        sparseArray.put(5, vVar6);
        sparseArray.put(6, vVar7);
        sparseArray.put(7, vVar8);
        sparseArray.put(8, vVar9);
        sparseArray.put(9, vVar10);
        sparseArray.put(10, vVar11);
        sparseArray.put(11, vVar12);
        sparseArray.put(12, vVar13);
        sparseArray.put(13, vVar14);
        sparseArray.put(14, vVar15);
        sparseArray.put(15, vVar16);
        sparseArray.put(16, vVar17);
        sparseArray.put(17, vVar18);
        sparseArray.put(-1, vVar19);
    }

    public static v valueOf(String str) {
        return (v) Enum.valueOf(v.class, str);
    }

    public static v[] values() {
        return (v[]) f7197l.clone();
    }
}
