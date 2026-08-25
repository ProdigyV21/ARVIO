package x8;

import android.util.Log;
import java.util.LinkedHashMap;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes5.dex */
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedHashMap f22669a;

    public o(int i10) {
        switch (i10) {
            case 1:
                this.f22669a = new LinkedHashMap();
                break;
            default:
                this.f22669a = new LinkedHashMap();
                break;
        }
    }

    public void a(z1.b... bVarArr) {
        for (z1.b bVar : bVarArr) {
            int i10 = bVar.f23158a;
            int i11 = bVar.f23159b;
            Integer numValueOf = Integer.valueOf(i10);
            LinkedHashMap linkedHashMap = this.f22669a;
            Object treeMap = linkedHashMap.get(numValueOf);
            if (treeMap == null) {
                treeMap = new TreeMap();
                linkedHashMap.put(numValueOf, treeMap);
            }
            TreeMap treeMap2 = (TreeMap) treeMap;
            if (treeMap2.containsKey(Integer.valueOf(i11))) {
                Log.w("ROOM", "Overriding migration " + treeMap2.get(Integer.valueOf(i11)) + " with " + bVar);
            }
            treeMap2.put(Integer.valueOf(i11), bVar);
        }
    }
}
