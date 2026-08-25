package zb;

/* JADX INFO: loaded from: classes5.dex */
public final class b extends InheritableThreadLocal {
    @Override // java.lang.ThreadLocal
    public final Object initialValue() {
        boolean zBooleanValue;
        try {
            zBooleanValue = ((Boolean) f.f23283n.call()).booleanValue();
        } catch (Exception unused) {
            zBooleanValue = true;
        }
        return Boolean.valueOf(zBooleanValue);
    }
}
