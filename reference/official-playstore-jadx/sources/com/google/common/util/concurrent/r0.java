package com.google.common.util.concurrent;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcelable;
import android.os.Trace;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.view.Window;
import androidx.appcompat.widget.g4;
import androidx.core.view.q2;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: classes4.dex */
public abstract class r0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static long f14230a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Method f14231b;

    public static void A(Context context, String str) {
        if (str.equals("")) {
            context.deleteFile("androidx.appcompat.app.AppCompatDelegate.application_locales_record_file");
            return;
        }
        try {
            FileOutputStream fileOutputStreamOpenFileOutput = context.openFileOutput("androidx.appcompat.app.AppCompatDelegate.application_locales_record_file", 0);
            XmlSerializer xmlSerializerNewSerializer = Xml.newSerializer();
            try {
                try {
                    try {
                        xmlSerializerNewSerializer.setOutput(fileOutputStreamOpenFileOutput, null);
                        xmlSerializerNewSerializer.startDocument("UTF-8", Boolean.TRUE);
                        xmlSerializerNewSerializer.startTag(null, "locales");
                        xmlSerializerNewSerializer.attribute(null, "application_locales", str);
                        xmlSerializerNewSerializer.endTag(null, "locales");
                        xmlSerializerNewSerializer.endDocument();
                        Log.d("AppLocalesStorageHelper", "Storing App Locales : app-locales: " + str + " persisted successfully.");
                        if (fileOutputStreamOpenFileOutput != null) {
                            fileOutputStreamOpenFileOutput.close();
                        }
                    } catch (Throwable th) {
                        if (fileOutputStreamOpenFileOutput != null) {
                            try {
                                fileOutputStreamOpenFileOutput.close();
                            } catch (IOException unused) {
                            }
                        }
                        throw th;
                    }
                } catch (Exception e5) {
                    Log.w("AppLocalesStorageHelper", "Storing App Locales : Failed to persist app-locales: ".concat(str), e5);
                    if (fileOutputStreamOpenFileOutput != null) {
                        fileOutputStreamOpenFileOutput.close();
                    }
                }
            } catch (IOException unused2) {
            }
        } catch (FileNotFoundException unused3) {
            Log.w("AppLocalesStorageHelper", "Storing App Locales : FileNotFoundException: Cannot open file androidx.appcompat.app.AppCompatDelegate.application_locales_record_file for writing ");
        }
    }

    public static b1.a B(String str) {
        return new b1.a(str, ka.l0.a(ka.x0.f19655d.plus(ka.m0.d())));
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x003c, code lost:
    
        r2 = r4.getAttributeValue(null, "application_locales");
     */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0045 A[EXC_TOP_SPLITTER, PHI: r2
      0x0045: PHI (r2v2 java.lang.String) = (r2v0 java.lang.String), (r2v4 java.lang.String) binds: [B:25:0x004e, B:21:0x0043] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String C(android.content.Context r9) {
        /*
            java.lang.String r0 = "androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            java.lang.String r1 = "AppLocalesStorageHelper"
            java.lang.String r2 = ""
            java.io.FileInputStream r3 = r9.openFileInput(r0)     // Catch: java.io.FileNotFoundException -> L6b
            org.xmlpull.v1.XmlPullParser r4 = android.util.Xml.newPullParser()     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L49
            java.lang.String r5 = "UTF-8"
            r4.setInput(r3, r5)     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L49
            int r5 = r4.getDepth()     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L49
        L17:
            int r6 = r4.next()     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L49
            r7 = 1
            if (r6 == r7) goto L43
            r7 = 3
            if (r6 != r7) goto L2a
            int r8 = r4.getDepth()     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L49
            if (r8 <= r5) goto L43
            goto L2a
        L28:
            r9 = move-exception
            goto L65
        L2a:
            if (r6 == r7) goto L17
            r7 = 4
            if (r6 != r7) goto L30
            goto L17
        L30:
            java.lang.String r6 = r4.getName()     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L49
            java.lang.String r7 = "locales"
            boolean r6 = r6.equals(r7)     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L49
            if (r6 == 0) goto L17
            java.lang.String r5 = "application_locales"
            r6 = 0
            java.lang.String r2 = r4.getAttributeValue(r6, r5)     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L49
        L43:
            if (r3 == 0) goto L51
        L45:
            r3.close()     // Catch: java.io.IOException -> L51
            goto L51
        L49:
            java.lang.String r4 = "Reading app Locales : Unable to parse through file :androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            android.util.Log.w(r1, r4)     // Catch: java.lang.Throwable -> L28
            if (r3 == 0) goto L51
            goto L45
        L51:
            boolean r3 = r2.isEmpty()
            if (r3 != 0) goto L61
            java.lang.String r9 = "Reading app Locales : Locales read from file: androidx.appcompat.app.AppCompatDelegate.application_locales_record_file , appLocales: "
            java.lang.String r9 = r9.concat(r2)
            android.util.Log.d(r1, r9)
            goto L64
        L61:
            r9.deleteFile(r0)
        L64:
            return r2
        L65:
            if (r3 == 0) goto L6a
            r3.close()     // Catch: java.io.IOException -> L6a
        L6a:
            throw r9
        L6b:
            java.lang.String r9 = "Reading app Locales : Locales record file not found: androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            android.util.Log.w(r1, r9)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.r0.C(android.content.Context):java.lang.String");
    }

    public static void D(Window window, boolean z) {
        if (Build.VERSION.SDK_INT >= 30) {
            q2.a(window, z);
            return;
        }
        View decorView = window.getDecorView();
        int systemUiVisibility = decorView.getSystemUiVisibility();
        decorView.setSystemUiVisibility(z ? systemUiVisibility & (-1793) : systemUiVisibility | 1792);
    }

    public static void E(Drawable drawable, int i10) {
        j0.a.g(drawable, i10);
    }

    public static String F(char c10, Locale locale) {
        String upperCase = String.valueOf(c10).toUpperCase(locale);
        if (upperCase.length() > 1) {
            if (c10 != 329) {
                return upperCase.charAt(0) + upperCase.substring(1).toLowerCase(Locale.ROOT);
            }
        } else if (upperCase.equals(String.valueOf(c10).toUpperCase(Locale.ROOT))) {
            return String.valueOf(Character.toTitleCase(c10));
        }
        return upperCase;
    }

    public static void G(int i10, int i11) {
        String strC;
        if (i10 < 0 || i10 >= i11) {
            if (i10 < 0) {
                strC = f4.f.C("%s (%s) must not be negative", "index", Integer.valueOf(i10));
            } else {
                if (i11 < 0) {
                    throw new IllegalArgumentException(androidx.compose.foundation.c.p(i11, "negative size: ", new StringBuilder(String.valueOf(i11).length() + 15)));
                }
                strC = f4.f.C("%s (%s) must be less than size (%s)", "index", Integer.valueOf(i10), Integer.valueOf(i11));
            }
            throw new IndexOutOfBoundsException(strC);
        }
    }

    public static void H(int i10, int i11, int i12) {
        if (i10 < 0 || i11 < i10 || i11 > i12) {
            throw new IndexOutOfBoundsException((i10 < 0 || i10 > i12) ? I(i10, i12, "start index") : (i11 < 0 || i11 > i12) ? I(i11, i12, "end index") : f4.f.C("end index (%s) must not be less than start index (%s)", Integer.valueOf(i11), Integer.valueOf(i10)));
        }
    }

    public static String I(int i10, int i11, String str) {
        if (i10 < 0) {
            return f4.f.C("%s (%s) must not be negative", str, Integer.valueOf(i10));
        }
        if (i11 >= 0) {
            return f4.f.C("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i10), Integer.valueOf(i11));
        }
        throw new IllegalArgumentException(androidx.compose.foundation.c.p(i11, "negative size: ", new StringBuilder(String.valueOf(i11).length() + 15)));
    }

    public static void a(Throwable th, Throwable th2) throws IllegalAccessException, InvocationTargetException {
        if (th != th2) {
            Integer num = j7.a.f19239a;
            if (num == null || num.intValue() >= 19) {
                th.addSuppressed(th2);
                return;
            }
            Method method = i7.m.f16236a;
            if (method != null) {
                method.invoke(th, th2);
            }
        }
    }

    public static void b(String str) {
        if (str.length() > 127) {
            str = str.substring(0, 127);
        }
        Trace.beginSection(str);
    }

    public static void c(int i10) {
        if (2 > i10 || i10 >= 37) {
            StringBuilder sbS = a0.c.s(i10, "radix ", " was not in valid range ");
            sbS.append(new x7.i(2, 36, 1));
            throw new IllegalArgumentException(sbS.toString());
        }
    }

    public static a7.a d(r7.l... lVarArr) {
        if (lVarArr.length > 0) {
            return new a7.a(lVarArr, 0);
        }
        throw new IllegalArgumentException("Failed requirement.");
    }

    public static int e(Comparable comparable, Comparable comparable2) {
        if (comparable == comparable2) {
            return 0;
        }
        if (comparable == null) {
            return -1;
        }
        if (comparable2 == null) {
            return 1;
        }
        return comparable.compareTo(comparable2);
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0087  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String f(g8.w r3, int r4) {
        /*
            r0 = r4 & 1
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L8
            r0 = r2
            goto L9
        L8:
            r0 = r1
        L9:
            r4 = r4 & 2
            if (r4 == 0) goto Le
            r1 = r2
        Le:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            if (r1 == 0) goto L27
            boolean r1 = r3 instanceof g8.j
            if (r1 == 0) goto L1c
            java.lang.String r1 = "<init>"
            goto L24
        L1c:
            f9.f r1 = r3.getName()
            java.lang.String r1 = r1.d()
        L24:
            r4.append(r1)
        L27:
            java.lang.String r1 = "("
            r4.append(r1)
            g8.s0 r1 = r3.M()
            if (r1 == 0) goto L3d
            v9.w r1 = r1.getType()
            y8.v r1 = w(r1)
            r4.append(r1)
        L3d:
            java.util.List r1 = r3.e()
            java.util.Iterator r1 = r1.iterator()
        L45:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L5d
            java.lang.Object r2 = r1.next()
            g8.e1 r2 = (g8.e1) r2
            v9.w r2 = r2.getType()
            y8.v r2 = w(r2)
            r4.append(r2)
            goto L45
        L5d:
            java.lang.String r1 = ")"
            r4.append(r1)
            if (r0 == 0) goto La0
            boolean r0 = r3 instanceof g8.j
            if (r0 == 0) goto L69
            goto L87
        L69:
            v9.w r0 = r3.getReturnType()
            if (r0 == 0) goto L99
            f9.f r1 = d8.k.f14705e
            f9.e r1 = d8.p.f14736d
            boolean r0 = d8.k.D(r0, r1)
            if (r0 == 0) goto L8d
            v9.w r0 = r3.getReturnType()
            boolean r0 = v9.b1.e(r0)
            if (r0 != 0) goto L8d
            boolean r0 = r3 instanceof g8.q0
            if (r0 != 0) goto L8d
        L87:
            java.lang.String r3 = "V"
            r4.append(r3)
            goto La0
        L8d:
            v9.w r3 = r3.getReturnType()
            y8.v r3 = w(r3)
            r4.append(r3)
            goto La0
        L99:
            r3 = 143(0x8f, float:2.0E-43)
            d8.k.a(r3)
            r3 = 0
            throw r3
        La0:
            java.lang.String r3 = r4.toString()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.r0.f(g8.w, int):java.lang.String");
    }

    public static final String g(g8.a aVar) {
        if (!i9.f.o(aVar)) {
            g8.k kVarD = aVar.d();
            g8.f fVar = kVarD instanceof g8.f ? (g8.f) kVarD : null;
            if (fVar != null && !fVar.getName().f15369l) {
                g8.a aVarA = aVar.a();
                g8.u0 u0Var = aVarA instanceof g8.u0 ? (g8.u0) aVarA : null;
                if (u0Var != null) {
                    String strF = f(u0Var, 3);
                    String str = f8.d.f15297a;
                    f9.b bVarE = f8.d.e(m9.d.g(fVar).i());
                    return androidx.compose.foundation.c.m('.', bVarE != null ? n9.b.b(bVarE).e() : androidx.work.impl.t.i(fVar, y8.q.f22971d), strF);
                }
            }
        }
        return null;
    }

    public static final Collection h(Collection collection, Collection collection2) {
        if (collection2.isEmpty()) {
            return collection;
        }
        if (collection == null) {
            return collection2;
        }
        if (collection instanceof LinkedHashSet) {
            ((LinkedHashSet) collection).addAll(collection2);
            return collection;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(collection);
        linkedHashSet.addAll(collection2);
        return linkedHashSet;
    }

    public static int i(char c10) {
        int iDigit = Character.digit((int) c10, 10);
        if (iDigit >= 0) {
            return iDigit;
        }
        throw new IllegalArgumentException("Char " + c10 + " is not a decimal digit");
    }

    public static final boolean j(char c10, char c11, boolean z) {
        if (c10 == c11) {
            return true;
        }
        if (!z) {
            return false;
        }
        char upperCase = Character.toUpperCase(c10);
        char upperCase2 = Character.toUpperCase(c11);
        return upperCase == upperCase2 || Character.toLowerCase(upperCase) == Character.toLowerCase(upperCase2);
    }

    public static final kotlin.reflect.d k(Annotation annotation) {
        return kotlin.jvm.internal.l0.f19747a.b(annotation.annotationType());
    }

    public static final Object l(kotlin.reflect.jvm.internal.impl.protobuf.q qVar, kotlin.reflect.jvm.internal.impl.protobuf.t tVar) {
        if (qVar.k(tVar)) {
            return qVar.j(tVar);
        }
        return null;
    }

    public static final Object m(kotlin.reflect.jvm.internal.impl.protobuf.q qVar, kotlin.reflect.jvm.internal.impl.protobuf.t tVar, int i10) {
        qVar.n(tVar);
        kotlin.reflect.jvm.internal.impl.protobuf.n nVar = qVar.f19820i;
        kotlin.reflect.jvm.internal.impl.protobuf.s sVar = tVar.f19827d;
        nVar.getClass();
        kotlin.reflect.jvm.internal.impl.protobuf.i0 i0Var = nVar.f19812a;
        if (!sVar.f19823m) {
            throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
        }
        Object obj = i0Var.get(sVar);
        if (i10 >= (obj == null ? 0 : ((List) obj).size())) {
            return null;
        }
        qVar.n(tVar);
        if (!sVar.f19823m) {
            throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
        }
        Object obj2 = i0Var.get(sVar);
        if (obj2 != null) {
            return tVar.a(((List) obj2).get(i10));
        }
        throw new IndexOutOfBoundsException();
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final Class n(kotlin.reflect.d dVar) {
        Class clsC = ((kotlin.jvm.internal.f) dVar).c();
        if (!clsC.isPrimitive()) {
            return clsC;
        }
        String name = clsC.getName();
        switch (name.hashCode()) {
            case -1325958191:
                if (!name.equals("double")) {
                }
                break;
            case 104431:
                if (!name.equals("int")) {
                }
                break;
            case 3039496:
                if (!name.equals("byte")) {
                }
                break;
            case 3052374:
                if (!name.equals("char")) {
                }
                break;
            case 3327612:
                if (!name.equals("long")) {
                }
                break;
            case 3625364:
                if (!name.equals("void")) {
                }
                break;
            case 64711720:
                if (!name.equals("boolean")) {
                }
                break;
            case 97526364:
                if (!name.equals("float")) {
                }
                break;
            case 109413500:
                if (!name.equals("short")) {
                }
                break;
        }
        return clsC;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final Class o(kotlin.reflect.d dVar) {
        Class clsC = ((kotlin.jvm.internal.f) dVar).c();
        if (clsC.isPrimitive()) {
            return clsC;
        }
        String name = clsC.getName();
        switch (name.hashCode()) {
            case -2056817302:
                if (name.equals("java.lang.Integer")) {
                    return Integer.TYPE;
                }
                return null;
            case -527879800:
                if (name.equals("java.lang.Float")) {
                    return Float.TYPE;
                }
                return null;
            case -515992664:
                if (name.equals("java.lang.Short")) {
                    return Short.TYPE;
                }
                return null;
            case 155276373:
                if (name.equals("java.lang.Character")) {
                    return Character.TYPE;
                }
                return null;
            case 344809556:
                if (name.equals("java.lang.Boolean")) {
                    return Boolean.TYPE;
                }
                return null;
            case 398507100:
                if (name.equals("java.lang.Byte")) {
                    return Byte.TYPE;
                }
                return null;
            case 398795216:
                if (name.equals("java.lang.Long")) {
                    return Long.TYPE;
                }
                return null;
            case 399092968:
                if (name.equals("java.lang.Void")) {
                    return Void.TYPE;
                }
                return null;
            case 761287205:
                if (name.equals("java.lang.Double")) {
                    return Double.TYPE;
                }
                return null;
            default:
                return null;
        }
    }

    public static long q(double d4) {
        ac.b.k(s(d4), "not a normal value");
        int exponent = Math.getExponent(d4);
        long jDoubleToRawLongBits = Double.doubleToRawLongBits(d4) & 4503599627370495L;
        return exponent == -1023 ? jDoubleToRawLongBits << 1 : jDoubleToRawLongBits | 4503599627370496L;
    }

    public static boolean r() {
        if (Build.VERSION.SDK_INT >= 29) {
            return g2.a.a();
        }
        try {
            if (f14231b == null) {
                f14230a = Trace.class.getField("TRACE_TAG_APP").getLong(null);
                f14231b = Trace.class.getMethod("isTagEnabled", Long.TYPE);
            }
            return ((Boolean) f14231b.invoke(null, Long.valueOf(f14230a))).booleanValue();
        } catch (Exception e5) {
            if (!(e5 instanceof InvocationTargetException)) {
                Log.v("Trace", "Unable to call isTagEnabled via reflection", e5);
                return false;
            }
            Throwable cause = e5.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            throw new RuntimeException(cause);
        }
    }

    public static boolean s(double d4) {
        return Math.getExponent(d4) <= 1023;
    }

    public static boolean u(char c10) {
        return Character.isWhitespace(c10) || Character.isSpaceChar(c10);
    }

    public static final ea.i v(ArrayList arrayList) {
        ea.i iVar = new ea.i();
        for (Object obj : arrayList) {
            p9.n nVar = (p9.n) obj;
            if (nVar != null && nVar != p9.m.f21235b) {
                iVar.add(obj);
            }
        }
        return iVar;
    }

    public static final y8.v w(v9.w wVar) {
        return (y8.v) androidx.work.impl.t.z(wVar, y8.e0.k, ea.f.f15085i);
    }

    public static float x(float f10, float... fArr) {
        for (float f11 : fArr) {
            f10 = Math.max(f10, f11);
        }
        return f10;
    }

    public static float y(float f10, float... fArr) {
        for (float f11 : fArr) {
            f10 = Math.min(f10, f11);
        }
        return f10;
    }

    public static final androidx.navigation.e z(String str, r7.l lVar) {
        androidx.navigation.w0 v0Var;
        androidx.navigation.h hVar = new androidx.navigation.h();
        lVar.invoke(hVar);
        g4 g4Var = hVar.f4673a;
        androidx.navigation.w0 w0Var = (androidx.navigation.w0) g4Var.f1591c;
        if (w0Var == null) {
            Object obj = g4Var.f1592d;
            if (obj instanceof Integer) {
                w0Var = androidx.navigation.w0.f4791b;
            } else if (obj instanceof int[]) {
                w0Var = androidx.navigation.w0.f4792c;
            } else if (obj instanceof Long) {
                w0Var = androidx.navigation.w0.f4793d;
            } else if (obj instanceof long[]) {
                w0Var = androidx.navigation.w0.f4794e;
            } else if (obj instanceof Float) {
                w0Var = androidx.navigation.w0.f4795f;
            } else if (obj instanceof float[]) {
                w0Var = androidx.navigation.w0.f4796g;
            } else if (obj instanceof Boolean) {
                w0Var = androidx.navigation.w0.f4797h;
            } else if (obj instanceof boolean[]) {
                w0Var = androidx.navigation.w0.f4798i;
            } else if ((obj instanceof String) || obj == null) {
                w0Var = androidx.navigation.w0.f4799j;
            } else if ((obj instanceof Object[]) && (((Object[]) obj) instanceof String[])) {
                w0Var = androidx.navigation.w0.k;
            } else {
                if (obj.getClass().isArray() && Parcelable.class.isAssignableFrom(obj.getClass().getComponentType())) {
                    v0Var = new androidx.navigation.s0(obj.getClass().getComponentType());
                } else if (obj.getClass().isArray() && Serializable.class.isAssignableFrom(obj.getClass().getComponentType())) {
                    v0Var = new androidx.navigation.u0(obj.getClass().getComponentType());
                } else if (obj instanceof Parcelable) {
                    v0Var = new androidx.navigation.t0(obj.getClass());
                } else if (obj instanceof Enum) {
                    v0Var = new androidx.navigation.r0(obj.getClass());
                } else {
                    if (!(obj instanceof Serializable)) {
                        throw new IllegalArgumentException("Object of type " + obj.getClass().getName() + " is not supported for navigation arguments.");
                    }
                    v0Var = new androidx.navigation.v0(obj.getClass());
                }
                w0Var = v0Var;
            }
        }
        return new androidx.navigation.e(str, new androidx.navigation.g(w0Var, g4Var.f1589a, g4Var.f1592d, g4Var.f1590b));
    }

    public abstract int p();

    public abstract boolean t();
}
