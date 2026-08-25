package androidx.navigation.compose;

import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import java.util.List;
import java.util.Set;
import ka.k0;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final class i extends f7.j implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4572i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f4573l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f4574m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f4575n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ i(Object obj, Object obj2, Object obj3, d7.d dVar, int i10) {
        super(2, dVar);
        this.f4572i = i10;
        this.f4573l = obj;
        this.f4574m = obj2;
        this.f4575n = obj3;
    }

    @Override // f7.a
    public final d7.d create(Object obj, d7.d dVar) {
        switch (this.f4572i) {
            case 0:
                return new i((State) this.f4573l, (p) this.f4574m, (SnapshotStateList) this.f4575n, dVar, 0);
            default:
                return new i((fi.iki.elonen.f) this.f4573l, (String) this.f4574m, (String) this.f4575n, dVar, 1);
        }
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        k0 k0Var = (k0) obj;
        d7.d dVar = (d7.d) obj2;
        switch (this.f4572i) {
            case 0:
                i iVar = (i) create(k0Var, dVar);
                t0 t0Var = t0.f22605a;
                iVar.invokeSuspend(t0Var);
                return t0Var;
            default:
                i iVar2 = (i) create(k0Var, dVar);
                t0 t0Var2 = t0.f22605a;
                iVar2.invokeSuspend(t0Var2);
                return t0Var2;
        }
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        switch (this.f4572i) {
            case 0:
                k2.c.G(obj);
                Set<androidx.navigation.i> set = (Set) ((State) this.f4573l).getValue();
                p pVar = (p) this.f4574m;
                SnapshotStateList snapshotStateList = (SnapshotStateList) this.f4575n;
                for (androidx.navigation.i iVar : set) {
                    if (!((List) pVar.b().f4710e.f20648i.getValue()).contains(iVar) && !snapshotStateList.contains(iVar)) {
                        pVar.b().b(iVar);
                    }
                }
                break;
            default:
                k2.c.G(obj);
                ((h6.d) ((fi.iki.elonen.f) this.f4573l).f15416i).putString((String) this.f4574m, (String) this.f4575n);
                break;
        }
        return t0.f22605a;
    }
}
