package kotlin.reflect.jvm.internal.impl.protobuf;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public abstract class p extends o implements r {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public n f19817l = n.f19811c;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f19818m;

    public final void g(q qVar) {
        i0 i0Var;
        if (!this.f19818m) {
            this.f19817l = this.f19817l.clone();
            this.f19818m = true;
        }
        n nVar = this.f19817l;
        n nVar2 = qVar.f19820i;
        nVar.getClass();
        int i10 = 0;
        while (true) {
            i0Var = nVar2.f19812a;
            if (i10 >= i0Var.f19794l.size()) {
                break;
            }
            nVar.g((Map.Entry) i0Var.f19794l.get(i10));
            i10++;
        }
        Iterator it = i0Var.e().iterator();
        while (it.hasNext()) {
            nVar.g((Map.Entry) it.next());
        }
    }
}
