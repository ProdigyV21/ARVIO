package retrofit2;

import j$.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes5.dex */
public final class t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final n0 f21588a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public gb.h0 f21589b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public gb.b0 f21590c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ArrayList f21591d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ArrayList f21592e;

    public t0() {
        n0 n0Var = n0.f21524c;
        this.f21591d = new ArrayList();
        this.f21592e = new ArrayList();
        this.f21588a = n0Var;
    }

    public final void a(retrofit2.converter.gson.a aVar) {
        this.f21591d.add(aVar);
    }

    public final void b(String str) {
        Objects.requireNonNull(str, "baseUrl == null");
        androidx.tv.foundation.lazy.list.k kVar = new androidx.tv.foundation.lazy.list.k(1);
        kVar.i(null, str);
        gb.b0 b0VarD = kVar.d();
        if ("".equals(b0VarD.f15622f.get(r0.size() - 1))) {
            this.f21590c = b0VarD;
        } else {
            throw new IllegalArgumentException("baseUrl must end in /: " + b0VarD);
        }
    }

    public final u0 c() {
        if (this.f21590c == null) {
            throw new IllegalStateException("Base URL required.");
        }
        gb.h0 h0Var = this.f21589b;
        if (h0Var == null) {
            h0Var = new gb.h0();
        }
        gb.h0 h0Var2 = h0Var;
        n0 n0Var = this.f21588a;
        Executor executorA = n0Var.a();
        boolean z = n0Var.f21525a;
        ArrayList arrayList = new ArrayList(this.f21592e);
        n nVar = new n(executorA);
        arrayList.addAll(z ? Arrays.asList(j.f21517a, nVar) : Collections.singletonList(nVar));
        ArrayList arrayList2 = this.f21591d;
        ArrayList arrayList3 = new ArrayList(arrayList2.size() + 1 + (z ? 1 : 0));
        b bVar = new b();
        bVar.f21478a = true;
        arrayList3.add(bVar);
        arrayList3.addAll(arrayList2);
        arrayList3.addAll(z ? Collections.singletonList(z.f21618a) : Collections.EMPTY_LIST);
        return new u0(h0Var2, this.f21590c, Collections.unmodifiableList(arrayList3), Collections.unmodifiableList(arrayList), executorA);
    }
}
