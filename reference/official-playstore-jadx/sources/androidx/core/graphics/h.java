package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes3.dex */
public class h extends o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Class f2105a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Constructor f2106b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Method f2107c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Method f2108d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f2109e = false;

    public static boolean o(String str, boolean z, int i10, Object obj) throws NoSuchMethodException {
        p();
        try {
            try {
                return ((Boolean) f2107c.invoke(obj, str, Integer.valueOf(i10), Boolean.valueOf(z))).booleanValue();
            } catch (InvocationTargetException e5) {
                e = e5;
                throw new RuntimeException(e);
            }
        } catch (IllegalAccessException | InvocationTargetException e6) {
            e = e6;
        }
    }

    public static void p() throws NoSuchMethodException {
        Method method;
        Class<?> cls;
        Method method2;
        if (f2109e) {
            return;
        }
        f2109e = true;
        Constructor<?> constructor = null;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            Constructor<?> constructor2 = cls.getConstructor(null);
            method2 = cls.getMethod("addFontWeightStyle", String.class, Integer.TYPE, Boolean.TYPE);
            method = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass());
            constructor = constructor2;
        } catch (ClassNotFoundException | NoSuchMethodException e5) {
            Log.e("TypefaceCompatApi21Impl", e5.getClass().getName(), e5);
            method = null;
            cls = null;
            method2 = null;
        }
        f2106b = constructor;
        f2105a = cls;
        f2107c = method2;
        f2108d = method;
    }

    @Override // androidx.core.graphics.o
    public Typeface f(Context context, i0.h hVar, Resources resources, int i10) throws NoSuchMethodException {
        p();
        try {
            Object objNewInstance = f2106b.newInstance(null);
            for (i0.i iVar : hVar.f15988a) {
                File fileM = o.m(context);
                if (fileM == null) {
                    return null;
                }
                try {
                    if (!o.d(fileM, resources, iVar.f15994f)) {
                        return null;
                    }
                    if (!o(fileM.getPath(), iVar.f15991c, iVar.f15990b, objNewInstance)) {
                        return null;
                    }
                    fileM.delete();
                } catch (RuntimeException unused) {
                    return null;
                } finally {
                    fileM.delete();
                }
            }
            p();
            try {
                Object objNewInstance2 = Array.newInstance((Class<?>) f2105a, 1);
                Array.set(objNewInstance2, 0, objNewInstance);
                return (Typeface) f2108d.invoke(null, objNewInstance2);
            } catch (IllegalAccessException | InvocationTargetException e5) {
                throw new RuntimeException(e5);
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e6) {
            throw new RuntimeException(e6);
        }
    }

    @Override // androidx.core.graphics.o
    public Typeface g(Context context, androidx.core.provider.k[] kVarArr, int i10) {
        String str;
        if (kVarArr.length >= 1) {
            try {
                ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(l(kVarArr, i10).f2161a, "r", null);
                if (parcelFileDescriptorOpenFileDescriptor != null) {
                    try {
                        try {
                            str = Os.readlink("/proc/self/fd/" + parcelFileDescriptorOpenFileDescriptor.getFd());
                        } finally {
                        }
                    } catch (ErrnoException unused) {
                    }
                    File file = OsConstants.S_ISREG(Os.stat(str).st_mode) ? new File(str) : null;
                    if (file != null && file.canRead()) {
                        Typeface typefaceCreateFromFile = Typeface.createFromFile(file);
                        parcelFileDescriptorOpenFileDescriptor.close();
                        return typefaceCreateFromFile;
                    }
                    FileInputStream fileInputStream = new FileInputStream(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor());
                    try {
                        Typeface typefaceH = h(context, fileInputStream);
                        fileInputStream.close();
                        parcelFileDescriptorOpenFileDescriptor.close();
                        return typefaceH;
                    } finally {
                    }
                }
                if (parcelFileDescriptorOpenFileDescriptor != null) {
                    parcelFileDescriptorOpenFileDescriptor.close();
                    return null;
                }
            } catch (IOException unused2) {
            }
        }
        return null;
    }
}
