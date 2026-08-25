package m9;

import g8.e1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.collections.s;
import kotlin.collections.z;

/* JADX INFO: loaded from: classes5.dex */
public final class a implements ea.b {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final a f20298l = new a(0);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f20299i;

    public /* synthetic */ a(int i10) {
        this.f20299i = i10;
    }

    @Override // ea.b
    public final Iterable r(Object obj) {
        switch (this.f20299i) {
            case 0:
                int i10 = d.f20302a;
                Collection collectionK = ((e1) obj).k();
                ArrayList arrayList = new ArrayList(s.U(collectionK, 10));
                Iterator it = collectionK.iterator();
                while (it.hasNext()) {
                    arrayList.add(((e1) it.next()).a());
                }
                return arrayList;
            default:
                g8.c cVar = (g8.c) obj;
                Collection collectionK2 = cVar != null ? cVar.k() : null;
                return collectionK2 == null ? z.f19728i : collectionK2;
        }
    }
}
