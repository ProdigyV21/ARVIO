package kotlin.jvm.internal;

/* JADX INFO: loaded from: classes5.dex */
public class b0 extends a0 {
    public b0(kotlin.reflect.f fVar, String str, String str2) {
        super(e.NO_RECEIVER, ((f) fVar).c(), str, str2, !(fVar instanceof kotlin.reflect.d) ? 1 : 0);
    }

    public Object get(Object obj) {
        return getGetter().call(obj);
    }

    public b0(Class cls, String str, String str2) {
        super(e.NO_RECEIVER, cls, str, str2, 1);
    }
}
