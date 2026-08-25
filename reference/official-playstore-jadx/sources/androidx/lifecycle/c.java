package androidx.lifecycle;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class c {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c f3216c = new c();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HashMap f3217a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashMap f3218b = new HashMap();

    public static void b(HashMap map, b bVar, p pVar, Class cls) {
        p pVar2 = (p) map.get(bVar);
        if (pVar2 == null || pVar == pVar2) {
            if (pVar2 == null) {
                map.put(bVar, pVar);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Method " + bVar.f3210b.getName() + " in " + cls.getName() + " already declared with different @OnLifecycleEvent value: previous value " + pVar2 + ", new value " + pVar);
    }

    public final a a(Class cls, Method[] methodArr) {
        int i10;
        Class superclass = cls.getSuperclass();
        HashMap map = new HashMap();
        HashMap map2 = this.f3217a;
        if (superclass != null) {
            a aVarA = (a) map2.get(superclass);
            if (aVarA == null) {
                aVarA = a(superclass, null);
            }
            map.putAll(aVarA.f3195b);
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            a aVarA2 = (a) map2.get(cls2);
            if (aVarA2 == null) {
                aVarA2 = a(cls2, null);
            }
            for (Map.Entry entry : aVarA2.f3195b.entrySet()) {
                b(map, (b) entry.getKey(), (p) entry.getValue(), cls);
            }
        }
        if (methodArr == null) {
            try {
                methodArr = cls.getDeclaredMethods();
            } catch (NoClassDefFoundError e5) {
                throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e5);
            }
        }
        boolean z = false;
        for (Method method : methodArr) {
            l0 l0Var = (l0) method.getAnnotation(l0.class);
            if (l0Var != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length <= 0) {
                    i10 = 0;
                } else {
                    if (!y.class.isAssignableFrom(parameterTypes[0])) {
                        throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                    }
                    i10 = 1;
                }
                p pVarValue = l0Var.value();
                if (parameterTypes.length > 1) {
                    if (!p.class.isAssignableFrom(parameterTypes[1])) {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    }
                    if (pVarValue != p.ON_ANY) {
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                    }
                    i10 = 2;
                }
                if (parameterTypes.length > 2) {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
                b(map, new b(method, i10), pVarValue, cls);
                z = true;
            }
        }
        a aVar = new a(map);
        map2.put(cls, aVar);
        this.f3218b.put(cls, Boolean.valueOf(z));
        return aVar;
    }
}
