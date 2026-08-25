package kotlin.reflect.jvm.internal.impl.protobuf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public abstract class q extends u implements r {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final n f19820i;

    public q() {
        this.f19820i = new n();
    }

    public final boolean h() {
        i0 i0Var = this.f19820i.f19812a;
        int i10 = 0;
        while (true) {
            if (i10 >= i0Var.f19794l.size()) {
                Iterator it = i0Var.e().iterator();
                while (it.hasNext()) {
                    if (!n.e((Map.Entry) it.next())) {
                    }
                }
                return true;
            }
            if (!n.e((Map.Entry) i0Var.f19794l.get(i10))) {
                break;
            }
            i10++;
        }
        return false;
    }

    public final int i() {
        i0 i0Var = this.f19820i.f19812a;
        int iD = 0;
        for (int i10 = 0; i10 < i0Var.f19794l.size(); i10++) {
            Map.Entry entry = (Map.Entry) i0Var.f19794l.get(i10);
            iD += n.d((m) entry.getKey(), entry.getValue());
        }
        for (Map.Entry entry2 : i0Var.e()) {
            iD += n.d((m) entry2.getKey(), entry2.getValue());
        }
        return iD;
    }

    public final Object j(t tVar) {
        n(tVar);
        s sVar = tVar.f19827d;
        Object obj = this.f19820i.f19812a.get(sVar);
        if (obj == null) {
            return tVar.f19825b;
        }
        if (!sVar.f19823m) {
            return tVar.a(obj);
        }
        if (sVar.f19822l.f19835i != v0.f19844s) {
            return obj;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = ((List) obj).iterator();
        while (it.hasNext()) {
            arrayList.add(tVar.a(it.next()));
        }
        return arrayList;
    }

    public final boolean k(t tVar) {
        n(tVar);
        s sVar = tVar.f19827d;
        n nVar = this.f19820i;
        nVar.getClass();
        if (sVar.f19823m) {
            throw new IllegalArgumentException("hasField() can only be called on non-repeated fields.");
        }
        return nVar.f19812a.get(sVar) != null;
    }

    public final void l() {
        this.f19820i.f();
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean m(kotlin.reflect.jvm.internal.impl.protobuf.i r10, kotlin.reflect.jvm.internal.impl.protobuf.j r11, kotlin.reflect.jvm.internal.impl.protobuf.l r12, int r13) throws kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException {
        /*
            Method dump skipped, instruction units count: 265
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.protobuf.q.m(kotlin.reflect.jvm.internal.impl.protobuf.i, kotlin.reflect.jvm.internal.impl.protobuf.j, kotlin.reflect.jvm.internal.impl.protobuf.l, int):boolean");
    }

    public final void n(t tVar) {
        if (tVar.f19824a != d()) {
            throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
        }
    }

    public q(p pVar) {
        pVar.f19817l.f();
        pVar.f19818m = false;
        this.f19820i = pVar.f19817l;
    }
}
