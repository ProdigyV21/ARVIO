package gb;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.jsoup.helper.HttpConnection;

/* JADX INFO: loaded from: classes5.dex */
public final class w extends n0 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final d0 f15827c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f15828a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f15829b;

    static {
        Pattern pattern = d0.f15633d;
        f15827c = xc.d.i0(HttpConnection.FORM_URL_ENCODED);
    }

    public w(ArrayList arrayList, ArrayList arrayList2) {
        this.f15828a = ib.c.x(arrayList);
        this.f15829b = ib.c.x(arrayList2);
    }

    @Override // gb.n0
    public final long a() {
        return d(null, true);
    }

    @Override // gb.n0
    public final d0 b() {
        return f15827c;
    }

    @Override // gb.n0
    public final void c(xb.k kVar) {
        d(kVar, false);
    }

    public final long d(xb.k kVar, boolean z) {
        xb.j jVar = z ? new xb.j() : kVar.h();
        List list = this.f15828a;
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            if (i10 > 0) {
                jVar.M0(38);
            }
            jVar.S0((String) list.get(i10));
            jVar.M0(61);
            jVar.S0((String) this.f15829b.get(i10));
        }
        if (!z) {
            return 0L;
        }
        long j10 = jVar.f22759l;
        jVar.i();
        return j10;
    }
}
