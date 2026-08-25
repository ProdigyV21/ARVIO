package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;
import t.l0;

/* JADX INFO: loaded from: classes3.dex */
public final class i extends o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Class f2110a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Constructor f2111b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Method f2112c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Method f2113d;

    static {
        Class<?> cls;
        Method method;
        Method method2;
        Constructor<?> constructor = null;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            Constructor<?> constructor2 = cls.getConstructor(null);
            Class<?> cls2 = Integer.TYPE;
            method2 = cls.getMethod("addFontWeightStyle", ByteBuffer.class, cls2, List.class, cls2, Boolean.TYPE);
            method = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass());
            constructor = constructor2;
        } catch (ClassNotFoundException | NoSuchMethodException e5) {
            Log.e("TypefaceCompatApi24Impl", e5.getClass().getName(), e5);
            cls = null;
            method = null;
            method2 = null;
        }
        f2111b = constructor;
        f2110a = cls;
        f2112c = method2;
        f2113d = method;
    }

    public static boolean o(Object obj, ByteBuffer byteBuffer, int i10, int i11, boolean z) {
        try {
            return ((Boolean) f2112c.invoke(obj, byteBuffer, Integer.valueOf(i10), null, Integer.valueOf(i11), Boolean.valueOf(z))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    public static Typeface p(Object obj) {
        try {
            Object objNewInstance = Array.newInstance((Class<?>) f2110a, 1);
            Array.set(objNewInstance, 0, obj);
            return (Typeface) f2113d.invoke(null, objNewInstance);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }

    @Override // androidx.core.graphics.o
    public final Typeface f(Context context, i0.h hVar, Resources resources, int i10) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Object objNewInstance;
        MappedByteBuffer map;
        FileInputStream fileInputStream;
        try {
            objNewInstance = f2111b.newInstance(null);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            objNewInstance = null;
        }
        if (objNewInstance != null) {
            for (i0.i iVar : hVar.f15988a) {
                int i11 = iVar.f15994f;
                File fileM = o.m(context);
                if (fileM != null) {
                    try {
                        if (o.d(fileM, resources, i11)) {
                            try {
                                fileInputStream = new FileInputStream(fileM);
                            } catch (IOException unused2) {
                                map = null;
                            }
                            try {
                                FileChannel channel = fileInputStream.getChannel();
                                map = channel.map(FileChannel.MapMode.READ_ONLY, 0L, channel.size());
                                fileInputStream.close();
                                if (map != null && o(objNewInstance, map, iVar.f15993e, iVar.f15990b, iVar.f15991c)) {
                                }
                            } finally {
                            }
                        }
                    } finally {
                        fileM.delete();
                    }
                }
                map = null;
                if (map != null) {
                }
            }
            return p(objNewInstance);
        }
        return null;
    }

    @Override // androidx.core.graphics.o
    public final Typeface g(Context context, androidx.core.provider.k[] kVarArr, int i10) {
        Object objNewInstance;
        try {
            objNewInstance = f2111b.newInstance(null);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            objNewInstance = null;
        }
        if (objNewInstance != null) {
            int i11 = 0;
            l0 l0Var = new l0(0);
            int length = kVarArr.length;
            while (true) {
                if (i11 < length) {
                    androidx.core.provider.k kVar = kVarArr[i11];
                    Uri uri = kVar.f2161a;
                    ByteBuffer byteBufferN = (ByteBuffer) l0Var.get(uri);
                    if (byteBufferN == null) {
                        byteBufferN = o.n(context, uri);
                        l0Var.put(uri, byteBufferN);
                    }
                    if (byteBufferN == null || !o(objNewInstance, byteBufferN, kVar.f2162b, kVar.f2163c, kVar.f2164d)) {
                        break;
                    }
                    i11++;
                } else {
                    Typeface typefaceP = p(objNewInstance);
                    if (typefaceP != null) {
                        return Typeface.create(typefaceP, i10);
                    }
                }
            }
        }
        return null;
    }
}
