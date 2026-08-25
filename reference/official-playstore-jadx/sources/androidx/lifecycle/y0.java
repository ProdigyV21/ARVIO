package androidx.lifecycle;

/* JADX INFO: loaded from: classes3.dex */
public class y0 implements g1 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static y0 f3312b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3313a;

    @Override // androidx.lifecycle.g1
    public d1 create(Class cls, o1.c cVar) {
        switch (this.f3313a) {
            case 0:
                return new SavedStateHandlesVM();
            default:
                return create(cls);
        }
    }

    @Override // androidx.lifecycle.g1
    public d1 create(Class cls) {
        switch (this.f3313a) {
            case 0:
                throw new UnsupportedOperationException("Factory.create(String) is unsupported.  This Factory requires `CreationExtras` to be passed into `create` method.");
            default:
                try {
                    return (d1) cls.getDeclaredConstructor(null).newInstance(null);
                } catch (IllegalAccessException e5) {
                    throw new RuntimeException("Cannot create an instance of " + cls, e5);
                } catch (InstantiationException e6) {
                    throw new RuntimeException("Cannot create an instance of " + cls, e6);
                } catch (NoSuchMethodException e10) {
                    throw new RuntimeException("Cannot create an instance of " + cls, e10);
                }
        }
    }
}
