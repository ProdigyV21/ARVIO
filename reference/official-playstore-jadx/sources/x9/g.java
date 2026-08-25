package x9;

import g8.p;
import g8.q;
import g8.v0;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import kotlin.collections.b0;
import kotlin.collections.z;
import p9.n;

/* JADX INFO: loaded from: classes5.dex */
public class g implements n {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f22681b;

    public g(String[] strArr, int i10) {
        String str;
        switch (i10) {
            case 1:
                str = "No member resolution should be done on captured type, it used only during constraint system resolution";
                break;
            case 2:
                str = "Scope for integer literal type (%s)";
                break;
            case 3:
                str = "Error scope for erased receiver type";
                break;
            case 4:
                str = "Scope for abbreviation %s";
                break;
            case 5:
                str = "Scope for stub type %s";
                break;
            case 6:
                str = "A scope for common supertype which is not a normal classifier";
                break;
            case 7:
                str = "Scope for error type %s";
                break;
            case 8:
                str = "Scope for unsupported type %s";
                break;
            case 9:
                str = "Error scope for class %s with arguments: %s";
                break;
            case 10:
                str = "Error resolution candidate for call %s";
                break;
            default:
                throw null;
        }
        Object[] objArrCopyOf = Arrays.copyOf(strArr, strArr.length);
        this.f22681b = String.format(str, Arrays.copyOf(objArrCopyOf, objArrCopyOf.length));
    }

    @Override // p9.n
    public Set a() {
        return b0.f19686i;
    }

    @Override // p9.n
    public Set b() {
        return b0.f19686i;
    }

    @Override // p9.n
    public /* bridge */ /* synthetic */ Collection c(f9.f fVar, o8.b bVar) {
        return h(fVar);
    }

    @Override // p9.n
    public Set d() {
        return b0.f19686i;
    }

    @Override // p9.p
    public g8.h e(f9.f fVar, o8.b bVar) {
        return new a(f9.f.l(String.format("<Error class: %s>", Arrays.copyOf(new Object[]{fVar}, 1))));
    }

    @Override // p9.p
    public Collection f(p9.f fVar, r7.l lVar) {
        return z.f19728i;
    }

    @Override // p9.n
    public /* bridge */ /* synthetic */ Collection g(f9.f fVar, o8.e eVar) {
        return i(fVar);
    }

    public Set h(f9.f fVar) {
        c cVar = new c(k.f22708c, null, h8.g.f15863a, f9.f.l("<Error function>"), 1, v0.f15554b);
        h hVarB = k.b(j.f22694o, new String[0]);
        p pVar = q.f15528e;
        z zVar = z.f19728i;
        cVar.L0(null, null, zVar, zVar, zVar, hVarB, 3, pVar);
        return Collections.singleton(cVar);
    }

    public Set i(f9.f fVar) {
        return k.f22711f;
    }

    public String toString() {
        return androidx.compose.foundation.c.u(new StringBuilder("ErrorScope{"), this.f22681b, '}');
    }
}
