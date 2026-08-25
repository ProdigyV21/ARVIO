package h9;

import com.google.common.util.concurrent.p0;
import g8.b1;
import g8.h0;
import java.util.ArrayList;
import kotlin.collections.l0;

/* JADX INFO: loaded from: classes5.dex */
public final class b implements c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b f15895b = new b(0);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final b f15896c = new b(1);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final b f15897d = new b(2);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f15898a;

    public /* synthetic */ b(int i10) {
        this.f15898a = i10;
    }

    public static String b(g8.h hVar) {
        String strZ = p0.z(hVar.getName());
        if (hVar instanceof b1) {
            return strZ;
        }
        g8.k kVarD = hVar.d();
        String strB = kVarD instanceof g8.f ? b((g8.h) kVarD) : kVarD instanceof h0 ? p0.A(((h0) kVarD).c().i().e()) : null;
        return (strB == null || strB.equals("")) ? strZ : androidx.compose.foundation.c.m('.', strB, strZ);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [g8.h, g8.k] */
    /* JADX WARN: Type inference failed for: r2v8, types: [g8.d0, g8.k] */
    /* JADX WARN: Type inference failed for: r2v9, types: [g8.k] */
    @Override // h9.c
    public final String a(g8.h hVar, h hVar2) {
        switch (this.f15898a) {
            case 0:
                return hVar instanceof b1 ? hVar2.N(((b1) hVar).getName(), false) : hVar2.o(p0.A(i9.f.g(hVar).e()));
            case 1:
                if (hVar instanceof b1) {
                    return hVar2.N(((b1) hVar).getName(), false);
                }
                ArrayList arrayList = new ArrayList();
                do {
                    arrayList.add(hVar.getName());
                    hVar = hVar.d();
                } while (hVar instanceof g8.f);
                return p0.A(new l0(arrayList));
            default:
                return b(hVar);
        }
    }
}
