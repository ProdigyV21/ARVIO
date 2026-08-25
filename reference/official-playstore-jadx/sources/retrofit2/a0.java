package retrofit2;

import java.lang.reflect.Array;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class a0 extends b1 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f21474b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ b1 f21475c;

    public /* synthetic */ a0(b1 b1Var, int i10) {
        this.f21474b = i10;
        this.f21475c = b1Var;
    }

    @Override // retrofit2.b1
    public final void a(o0 o0Var, Object obj) {
        switch (this.f21474b) {
            case 0:
                Iterable iterable = (Iterable) obj;
                if (iterable != null) {
                    Iterator it = iterable.iterator();
                    while (it.hasNext()) {
                        this.f21475c.a(o0Var, it.next());
                    }
                    break;
                }
                break;
            default:
                if (obj != null) {
                    int length = Array.getLength(obj);
                    for (int i10 = 0; i10 < length; i10++) {
                        this.f21475c.a(o0Var, Array.get(obj, i10));
                    }
                    break;
                }
                break;
        }
    }
}
