package y8;

import a9.n0;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import com.google.common.util.concurrent.r0;

/* JADX INFO: loaded from: classes5.dex */
public final class r implements kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final n9.b f22972i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final n9.b f22973l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final y f22974m;

    public r(y yVar, n0 n0Var, e9.g gVar) {
        n9.b bVarB = n9.b.b(yVar.h());
        o5.a aVarB = yVar.b();
        n9.b bVarD = null;
        String str = ((z8.a) aVarB.f20777d) != z8.a.MULTIFILE_CLASS_PART ? null : aVarB.f20775b;
        if (str != null && str.length() > 0) {
            bVarD = n9.b.d(str);
        }
        this.f22972i = bVarB;
        this.f22973l = bVarD;
        this.f22974m = yVar;
        Integer num = (Integer) r0.l(n0Var, d9.q.f14874m);
        if (num != null) {
            gVar.getString(num.intValue());
        }
    }

    public final f9.b a() {
        f9.c cVar;
        n9.b bVar = this.f22972i;
        String str = bVar.f20498a;
        int iLastIndexOf = str.lastIndexOf(DomExceptionUtils.SEPARATOR);
        if (iLastIndexOf == -1) {
            cVar = f9.c.f15358c;
            if (cVar == null) {
                n9.b.a(7);
                throw null;
            }
        } else {
            cVar = new f9.c(str.substring(0, iLastIndexOf).replace('/', '.'));
        }
        String strE = bVar.e();
        return new f9.b(cVar, f9.f.j(kotlin.text.o.E0('/', strE, strE)));
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.l
    public final String e() {
        return "Class '" + a().b().b() + '\'';
    }

    public final String toString() {
        return r.class.getSimpleName() + ": " + this.f22972i;
    }
}
