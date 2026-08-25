package cb;

import java.lang.ref.SoftReference;

/* JADX INFO: loaded from: classes5.dex */
public final class s extends ClassValue {
    @Override // java.lang.ClassValue
    public final Object computeValue(Class cls) {
        u0 u0Var = new u0();
        u0Var.reference = new SoftReference<>(null);
        return u0Var;
    }
}
