package j6;

import com.squareup.moshi.l;
import com.squareup.moshi.p;
import com.squareup.moshi.q;
import java.io.EOFException;

/* JADX INFO: loaded from: classes4.dex */
public final class a extends l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final l f19228a;

    public a(l lVar) {
        this.f19228a = lVar;
    }

    @Override // com.squareup.moshi.l
    public final Object a(p pVar) throws EOFException {
        if (pVar.b0() != 9) {
            return this.f19228a.a(pVar);
        }
        pVar.R();
        return null;
    }

    @Override // com.squareup.moshi.l
    public final void e(q qVar, Object obj) {
        if (obj == null) {
            qVar.G();
        } else {
            this.f19228a.e(qVar, obj);
        }
    }

    public final String toString() {
        return this.f19228a + ".nullSafe()";
    }
}
