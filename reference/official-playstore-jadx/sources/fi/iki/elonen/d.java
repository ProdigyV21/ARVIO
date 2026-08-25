package fi.iki.elonen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public final class d implements Iterable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final HashMap f15412i = new HashMap();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ArrayList f15413l = new ArrayList();

    public d(HashMap map) {
        String str = (String) map.get("cookie");
        if (str != null) {
            for (String str2 : str.split(";")) {
                String[] strArrSplit = str2.trim().split("=");
                if (strArrSplit.length == 2) {
                    this.f15412i.put(strArrSplit[0], strArrSplit[1]);
                }
            }
        }
    }

    public final void a() {
        Iterator it = this.f15413l.iterator();
        if (it.hasNext()) {
            it.next().getClass();
            throw new ClassCastException();
        }
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return this.f15412i.keySet().iterator();
    }
}
