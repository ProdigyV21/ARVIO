package t5;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class b implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f21891a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c f21892b;

    public b(Set set, c cVar) {
        this.f21891a = b(set);
        this.f21892b = cVar;
    }

    public static String b(Set set) {
        StringBuilder sb2 = new StringBuilder();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            sb2.append(aVar.f21889a);
            sb2.append('/');
            sb2.append(aVar.f21890b);
            if (it.hasNext()) {
                sb2.append(' ');
            }
        }
        return sb2.toString();
    }

    @Override // t5.e
    public final String a() {
        Set setUnmodifiableSet;
        String str = this.f21891a;
        c cVar = this.f21892b;
        synchronized (cVar.f21894a) {
            setUnmodifiableSet = Collections.unmodifiableSet(cVar.f21894a);
        }
        if (setUnmodifiableSet.isEmpty()) {
            return str;
        }
        return str + ' ' + b(cVar.a());
    }
}
