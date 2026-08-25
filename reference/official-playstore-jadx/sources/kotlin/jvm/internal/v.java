package kotlin.jvm.internal;

/* JADX INFO: loaded from: classes5.dex */
public class v extends u {
    public v(kotlin.reflect.d dVar, String str, String str2) {
        super(e.NO_RECEIVER, ((f) dVar).c(), str, str2, !androidx.compose.material3.d.B(dVar) ? 1 : 0);
    }

    public Object get(Object obj) {
        return getGetter().call(obj);
    }

    public void set(Object obj, Object obj2) {
        getSetter().call(obj, obj2);
    }

    public v(Class cls, String str, String str2, int i10) {
        super(e.NO_RECEIVER, cls, str, str2, i10);
    }
}
