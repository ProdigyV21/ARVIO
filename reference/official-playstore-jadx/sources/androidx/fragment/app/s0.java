package androidx.fragment.app;

/* JADX INFO: loaded from: classes3.dex */
public final class s0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final t.l0 f2835b = new t.l0(0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ b1 f2836a;

    public s0(b1 b1Var) {
        this.f2836a = b1Var;
    }

    public static Class a(ClassLoader classLoader, String str) throws ClassNotFoundException {
        t.l0 l0Var = f2835b;
        t.l0 l0Var2 = (t.l0) l0Var.get(classLoader);
        if (l0Var2 == null) {
            l0Var2 = new t.l0(0);
            l0Var.put(classLoader, l0Var2);
        }
        Class cls = (Class) l0Var2.get(str);
        if (cls != null) {
            return cls;
        }
        Class<?> cls2 = Class.forName(str, false, classLoader);
        l0Var2.put(str, cls2);
        return cls2;
    }

    public static Class b(ClassLoader classLoader, String str) {
        try {
            return a(classLoader, str);
        } catch (ClassCastException e5) {
            throw new Fragment$InstantiationException(a0.c.l("Unable to instantiate fragment ", str, ": make sure class is a valid subclass of Fragment"), e5);
        } catch (ClassNotFoundException e6) {
            throw new Fragment$InstantiationException(a0.c.l("Unable to instantiate fragment ", str, ": make sure class name exists"), e6);
        }
    }
}
