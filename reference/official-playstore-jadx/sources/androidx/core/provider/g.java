package androidx.core.provider;

import java.util.ArrayList;
import t.l0;

/* JADX INFO: loaded from: classes3.dex */
public final class g implements p0.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2150a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f2151b;

    public /* synthetic */ g(Object obj, int i10) {
        this.f2150a = i10;
        this.f2151b = obj;
    }

    @Override // p0.a
    public final void accept(Object obj) {
        switch (this.f2150a) {
            case 0:
                h hVar = (h) obj;
                if (hVar == null) {
                    hVar = new h(-3);
                }
                ((io.sentry.internal.debugmeta.c) this.f2151b).C(hVar);
                return;
            default:
                h hVar2 = (h) obj;
                synchronized (i.f2156c) {
                    try {
                        l0 l0Var = i.f2157d;
                        ArrayList arrayList = (ArrayList) l0Var.get((String) this.f2151b);
                        if (arrayList == null) {
                            return;
                        }
                        l0Var.remove((String) this.f2151b);
                        for (int i10 = 0; i10 < arrayList.size(); i10++) {
                            ((p0.a) arrayList.get(i10)).accept(hVar2);
                        }
                        return;
                    } finally {
                    }
                }
        }
    }
}
