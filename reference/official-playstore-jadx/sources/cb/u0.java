package cb;

import java.lang.ref.SoftReference;

/* JADX INFO: loaded from: classes5.dex */
public final class u0 {
    public volatile SoftReference<Object> reference;

    public final synchronized Object a(r7.a aVar) {
        Object obj = this.reference.get();
        if (obj != null) {
            return obj;
        }
        Object objInvoke = aVar.invoke();
        this.reference = new SoftReference<>(objInvoke);
        return objInvoke;
    }
}
