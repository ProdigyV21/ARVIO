package fi.iki.elonen;

import java.util.HashMap;

/* JADX INFO: loaded from: classes4.dex */
public final class j extends HashMap {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ n f15435i;

    public j(n nVar) {
        this.f15435i = nVar;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Object put(Object obj, Object obj2) {
        String str = (String) obj;
        String str2 = (String) obj2;
        this.f15435i.f15449p.put(str == null ? str : str.toLowerCase(), str2);
        return (String) super.put(str, str2);
    }
}
