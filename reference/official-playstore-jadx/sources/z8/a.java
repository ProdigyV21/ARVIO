package z8;

import java.util.LinkedHashMap;
import kotlin.collections.i0;

/* JADX INFO: loaded from: classes5.dex */
public enum a {
    UNKNOWN(0),
    CLASS(1),
    FILE_FACADE(2),
    SYNTHETIC_CLASS(3),
    MULTIFILE_CLASS(4),
    MULTIFILE_CLASS_PART(5);


    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final LinkedHashMap f23244l;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f23252i;

    static {
        a[] aVarArrValues = values();
        int iQ0 = i0.q0(aVarArrValues.length);
        LinkedHashMap linkedHashMap = new LinkedHashMap(iQ0 < 16 ? 16 : iQ0);
        for (a aVar : aVarArrValues) {
            linkedHashMap.put(Integer.valueOf(aVar.f23252i), aVar);
        }
        f23244l = linkedHashMap;
    }

    a(int i10) {
        this.f23252i = i10;
    }
}
