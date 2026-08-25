package m2;

import android.os.Build;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
public final class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f20218a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public UUID f20219b = UUID.randomUUID();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public androidx.work.impl.model.p f20220c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Set f20221d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f20222e;

    public v(Class cls, int i10) {
        this.f20222e = i10;
        this.f20220c = new androidx.work.impl.model.p(this.f20219b.toString(), 0, cls.getName(), (String) null, (h) null, (h) null, 0L, 0L, 0L, (f) null, 0, 0, 0L, 0L, 0L, 0L, false, 0, 0, 0L, 0, 0, 8388602);
        String[] strArr = {cls.getName()};
        LinkedHashSet linkedHashSet = new LinkedHashSet(kotlin.collections.i0.q0(1));
        kotlin.collections.r.m0(strArr, linkedHashSet);
        this.f20221d = linkedHashSet;
    }

    public final g0 a() {
        g0 wVar;
        switch (this.f20222e) {
            case 0:
                if (this.f20218a && this.f20220c.f6931j.f20190c) {
                    throw new IllegalArgumentException("Cannot set backoff criteria on an idle mode job");
                }
                wVar = new w(this.f20219b, this.f20220c, this.f20221d);
                break;
            default:
                if (this.f20218a && this.f20220c.f6931j.f20190c) {
                    throw new IllegalArgumentException("Cannot set backoff criteria on an idle mode job");
                }
                androidx.work.impl.model.p pVar = this.f20220c;
                if (pVar.f6937q) {
                    throw new IllegalArgumentException("PeriodicWorkRequests cannot be expedited");
                }
                wVar = new b0(this.f20219b, pVar, this.f20221d);
                break;
                break;
        }
        f fVar = this.f20220c.f6931j;
        boolean z = (Build.VERSION.SDK_INT >= 24 && fVar.a()) || fVar.f20191d || fVar.f20189b || fVar.f20190c;
        androidx.work.impl.model.p pVar2 = this.f20220c;
        if (pVar2.f6937q) {
            if (z) {
                throw new IllegalArgumentException("Expedited jobs only support network and storage constraints");
            }
            if (pVar2.f6928g > 0) {
                throw new IllegalArgumentException("Expedited jobs cannot be delayed");
            }
        }
        UUID uuidRandomUUID = UUID.randomUUID();
        this.f20219b = uuidRandomUUID;
        String string = uuidRandomUUID.toString();
        androidx.work.impl.model.p pVar3 = this.f20220c;
        g0 g0Var = wVar;
        this.f20220c = new androidx.work.impl.model.p(string, pVar3.f6923b, pVar3.f6924c, pVar3.f6925d, new h(pVar3.f6926e), new h(pVar3.f6927f), pVar3.f6928g, pVar3.f6929h, pVar3.f6930i, new f(pVar3.f6931j), pVar3.k, pVar3.f6932l, pVar3.f6933m, pVar3.f6934n, pVar3.f6935o, pVar3.f6936p, pVar3.f6937q, pVar3.f6938r, pVar3.f6939s, pVar3.f6941u, pVar3.f6942v, pVar3.f6943w, 524288);
        return g0Var;
    }
}
