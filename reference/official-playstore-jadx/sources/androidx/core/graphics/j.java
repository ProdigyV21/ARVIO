package androidx.core.graphics;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.util.Log;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes3.dex */
public class j extends h {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Class f2114f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Constructor f2115g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Method f2116h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Method f2117i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Method f2118j;
    public final Method k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Method f2119l;

    public j() throws NoSuchMethodException {
        Method methodU;
        Constructor<?> constructor;
        Method methodT;
        Method method;
        Method method2;
        Method method3;
        Class<?> cls = null;
        try {
            Class<?> cls2 = Class.forName("android.graphics.FontFamily");
            constructor = cls2.getConstructor(null);
            methodT = t(cls2);
            Class<?> cls3 = Integer.TYPE;
            method = cls2.getMethod("addFontFromBuffer", ByteBuffer.class, cls3, FontVariationAxis[].class, cls3, cls3);
            method2 = cls2.getMethod("freeze", null);
            method3 = cls2.getMethod("abortCreation", null);
            methodU = u(cls2);
            cls = cls2;
        } catch (ClassNotFoundException | NoSuchMethodException e5) {
            Log.e("TypefaceCompatApi26Impl", "Unable to collect necessary methods for class ".concat(e5.getClass().getName()), e5);
            methodU = null;
            constructor = null;
            methodT = null;
            method = null;
            method2 = null;
            method3 = null;
        }
        this.f2114f = cls;
        this.f2115g = constructor;
        this.f2116h = methodT;
        this.f2117i = method;
        this.f2118j = method2;
        this.k = method3;
        this.f2119l = methodU;
    }

    public static Method t(Class cls) {
        Class<?> cls2 = Integer.TYPE;
        return cls.getMethod("addFontFromAssetManager", AssetManager.class, String.class, cls2, Boolean.TYPE, cls2, cls2, cls2, FontVariationAxis[].class);
    }

    @Override // androidx.core.graphics.h, androidx.core.graphics.o
    public final Typeface f(Context context, i0.h hVar, Resources resources, int i10) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Object objNewInstance;
        Method method = this.f2116h;
        if (method == null) {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        if (method == null) {
            return super.f(context, hVar, resources, i10);
        }
        try {
            objNewInstance = this.f2115g.newInstance(null);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            objNewInstance = null;
        }
        if (objNewInstance != null) {
            i0.i[] iVarArr = hVar.f15988a;
            int length = iVarArr.length;
            int i11 = 0;
            while (true) {
                if (i11 < length) {
                    i0.i iVar = iVarArr[i11];
                    Context context2 = context;
                    if (q(context2, objNewInstance, iVar.f15989a, iVar.f15993e, iVar.f15990b, iVar.f15991c ? 1 : 0, FontVariationAxis.fromFontVariationSettings(iVar.f15992d))) {
                        i11++;
                        context = context2;
                    } else {
                        try {
                            this.k.invoke(objNewInstance, null);
                            break;
                        } catch (IllegalAccessException | InvocationTargetException unused2) {
                        }
                    }
                } else if (s(objNewInstance)) {
                    return r(objNewInstance);
                }
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002a  */
    @Override // androidx.core.graphics.h, androidx.core.graphics.o
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.graphics.Typeface g(android.content.Context r18, androidx.core.provider.k[] r19, int r20) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 261
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.j.g(android.content.Context, androidx.core.provider.k[], int):android.graphics.Typeface");
    }

    @Override // androidx.core.graphics.o
    public final Typeface i(Context context, Resources resources, int i10, String str, int i11) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Object objNewInstance;
        Method method = this.f2116h;
        if (method == null) {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        if (method == null) {
            return super.i(context, resources, i10, str, i11);
        }
        try {
            objNewInstance = this.f2115g.newInstance(null);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            objNewInstance = null;
        }
        if (objNewInstance != null) {
            if (!q(context, objNewInstance, str, 0, -1, -1, null)) {
                try {
                    this.k.invoke(objNewInstance, null);
                } catch (IllegalAccessException | InvocationTargetException unused2) {
                }
            } else if (s(objNewInstance)) {
                return r(objNewInstance);
            }
        }
        return null;
    }

    public final boolean q(Context context, Object obj, String str, int i10, int i11, int i12, FontVariationAxis[] fontVariationAxisArr) {
        try {
            return ((Boolean) this.f2116h.invoke(obj, context.getAssets(), str, 0, Boolean.FALSE, Integer.valueOf(i10), Integer.valueOf(i11), Integer.valueOf(i12), fontVariationAxisArr)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    public Typeface r(Object obj) {
        try {
            Object objNewInstance = Array.newInstance((Class<?>) this.f2114f, 1);
            Array.set(objNewInstance, 0, obj);
            return (Typeface) this.f2119l.invoke(null, objNewInstance, -1, -1);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }

    public final boolean s(Object obj) {
        try {
            return ((Boolean) this.f2118j.invoke(obj, null)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    public Method u(Class cls) throws NoSuchMethodException {
        Class cls2 = Integer.TYPE;
        Method declaredMethod = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", Array.newInstance((Class<?>) cls, 1).getClass(), cls2, cls2);
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }
}
