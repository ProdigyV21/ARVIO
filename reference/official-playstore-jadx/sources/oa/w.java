package oa;

import d7.j;
import ka.u1;
import ka.v1;

/* JADX INFO: loaded from: classes5.dex */
public final class w extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ t f20861i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w(t tVar) {
        super(2);
        this.f20861i = tVar;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        int iIntValue = ((Number) obj).intValue();
        j.a aVar = (j.a) obj2;
        j.b key = aVar.getKey();
        j.a aVar2 = this.f20861i.f20855l.get(key);
        if (key != u1.f19642i) {
            return Integer.valueOf(aVar != aVar2 ? Integer.MIN_VALUE : iIntValue + 1);
        }
        v1 v1Var = (v1) aVar2;
        v1 parent = (v1) aVar;
        while (true) {
            if (parent != null) {
                if (parent == v1Var || !(parent instanceof pa.v)) {
                    break;
                }
                parent = parent.getParent();
            } else {
                parent = null;
                break;
            }
        }
        if (parent == v1Var) {
            if (v1Var != null) {
                iIntValue++;
            }
            return Integer.valueOf(iIntValue);
        }
        throw new IllegalStateException(("Flow invariant is violated:\n\t\tEmission from another coroutine is detected.\n\t\tChild of " + parent + ", expected child of " + v1Var + ".\n\t\tFlowCollector is not thread-safe and concurrent emissions are prohibited.\n\t\tTo mitigate this restriction please use 'channelFlow' builder instead of 'flow'").toString());
    }
}
