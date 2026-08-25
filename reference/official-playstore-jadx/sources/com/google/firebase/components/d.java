package com.google.firebase.components;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class d implements m5.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f14262a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f14263b;

    public /* synthetic */ d(Object obj, int i10) {
        this.f14262a = i10;
        this.f14263b = obj;
    }

    @Override // m5.b
    public final Object get() {
        switch (this.f14262a) {
            case 0:
                String str = (String) this.f14263b;
                try {
                    Class<?> cls = Class.forName(str);
                    if (ComponentRegistrar.class.isAssignableFrom(cls)) {
                        return (ComponentRegistrar) cls.getDeclaredConstructor(null).newInstance(null);
                    }
                    throw new InvalidRegistrarException("Class " + str + " is not an instance of com.google.firebase.components.ComponentRegistrar");
                } catch (ClassNotFoundException unused) {
                    Log.w("ComponentDiscovery", "Class " + str + " is not an found.");
                    return null;
                } catch (IllegalAccessException e5) {
                    throw new InvalidRegistrarException(a0.c.l("Could not instantiate ", str, "."), e5);
                } catch (InstantiationException e6) {
                    throw new InvalidRegistrarException(a0.c.l("Could not instantiate ", str, "."), e6);
                } catch (NoSuchMethodException e10) {
                    throw new InvalidRegistrarException(androidx.compose.material3.d.C("Could not instantiate ", str), e10);
                } catch (InvocationTargetException e11) {
                    throw new InvalidRegistrarException(androidx.compose.material3.d.C("Could not instantiate ", str), e11);
                }
            case 1:
                return (ComponentRegistrar) this.f14263b;
            default:
                return new o5.c((z4.g) this.f14263b);
        }
    }
}
