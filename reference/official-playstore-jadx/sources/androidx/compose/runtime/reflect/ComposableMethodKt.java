package androidx.compose.runtime.reflect;

import androidx.compose.runtime.Composer;
import com.squareup.moshi.w;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.e0;
import kotlin.collections.s;
import kotlin.jvm.internal.p;
import qb.d;
import x7.i;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\u001a\u0018\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002\u001a\u0010\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\f\u0010\u0006\u001a\u0004\u0018\u00010\u0007*\u00020\b\u001a(\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u000b0\n\"\u0006\b\u0000\u0010\u000b\u0018\u0001*\u0002H\u000b2\u0006\u0010\f\u001a\u00020\u0001H\u0082\b¢\u0006\u0002\u0010\r\u001a\f\u0010\u000e\u001a\u00020\u000f*\u00020\bH\u0002\u001a7\u0010\u0010\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u001a\u0010\u0014\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00110\n\"\u0006\u0012\u0002\b\u00030\u0011¢\u0006\u0002\u0010\u0015\u001a\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u0017*\u0006\u0012\u0002\b\u00030\u0011H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"BITS_PER_INT", "", "changedParamCount", "realValueParams", "thisParams", "defaultParamCount", "asComposableMethod", "Landroidx/compose/runtime/reflect/ComposableMethod;", "Ljava/lang/reflect/Method;", "dup", "", "T", "count", "(Ljava/lang/Object;I)[Ljava/lang/Object;", "getComposableInfo", "Landroidx/compose/runtime/reflect/ComposableInfo;", "getDeclaredComposableMethod", "Ljava/lang/Class;", "methodName", "", "args", "(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;)Landroidx/compose/runtime/reflect/ComposableMethod;", "getDefaultValue", "", "runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ComposableMethodKt {
    private static final int BITS_PER_INT = 31;

    public static final ComposableMethod asComposableMethod(Method method) {
        ComposableInfo composableInfo = getComposableInfo(method);
        if (composableInfo.isComposable()) {
            return new ComposableMethod(method, composableInfo);
        }
        return null;
    }

    private static final int changedParamCount(int i10, int i11) {
        if (i10 == 0) {
            return 1;
        }
        return (int) Math.ceil(((double) (i10 + i11)) / 10.0d);
    }

    private static final int defaultParamCount(int i10) {
        return (int) Math.ceil(((double) i10) / 31.0d);
    }

    private static final <T> T[] dup(T t2, int i10) {
        i iVarX = d.X(0, i10);
        ArrayList arrayList = new ArrayList(s.U(iVarX, 10));
        Iterator<T> it = iVarX.iterator();
        while (it.hasNext()) {
            ((e0) it).nextInt();
            arrayList.add(t2);
        }
        p.h();
        throw null;
    }

    private static final ComposableInfo getComposableInfo(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        int length = parameterTypes.length - 1;
        if (length >= 0) {
            while (true) {
                int i10 = length - 1;
                if (p.a(parameterTypes[length], Composer.class)) {
                    break;
                }
                if (i10 < 0) {
                    break;
                }
                length = i10;
            }
            length = -1;
        } else {
            length = -1;
        }
        if (length == -1) {
            return new ComposableInfo(false, method.getParameterTypes().length, 0, 0);
        }
        int iChangedParamCount = changedParamCount(length, !Modifier.isStatic(method.getModifiers()) ? 1 : 0);
        int i11 = length + 1 + iChangedParamCount;
        int length2 = method.getParameterTypes().length;
        int iDefaultParamCount = length2 != i11 ? defaultParamCount(length) : 0;
        return new ComposableInfo(i11 + iDefaultParamCount == length2, length, iChangedParamCount, iDefaultParamCount);
    }

    public static final ComposableMethod getDeclaredComposableMethod(Class<?> cls, String str, Class<?>... clsArr) throws NoSuchMethodException {
        Method declaredMethod;
        Class cls2 = Integer.TYPE;
        int iChangedParamCount = changedParamCount(clsArr.length, 0);
        try {
            w wVar = new w(3);
            ArrayList arrayList = wVar.f14489a;
            wVar.b(clsArr);
            wVar.a(Composer.class);
            i iVarX = d.X(0, iChangedParamCount);
            ArrayList arrayList2 = new ArrayList(s.U(iVarX, 10));
            Iterator it = iVarX.iterator();
            while (it.hasNext()) {
                ((e0) it).nextInt();
                arrayList2.add(cls2);
            }
            wVar.b(arrayList2.toArray(new Class[0]));
            declaredMethod = cls.getDeclaredMethod(str, (Class[]) arrayList.toArray(new Class[arrayList.size()]));
        } catch (ReflectiveOperationException unused) {
            int iDefaultParamCount = defaultParamCount(clsArr.length);
            try {
                w wVar2 = new w(4);
                ArrayList arrayList3 = wVar2.f14489a;
                wVar2.b(clsArr);
                wVar2.a(Composer.class);
                i iVarX2 = d.X(0, iChangedParamCount);
                ArrayList arrayList4 = new ArrayList(s.U(iVarX2, 10));
                Iterator it2 = iVarX2.iterator();
                while (it2.hasNext()) {
                    ((e0) it2).nextInt();
                    arrayList4.add(cls2);
                }
                wVar2.b(arrayList4.toArray(new Class[0]));
                i iVarX3 = d.X(0, iDefaultParamCount);
                ArrayList arrayList5 = new ArrayList(s.U(iVarX3, 10));
                Iterator it3 = iVarX3.iterator();
                while (it3.hasNext()) {
                    ((e0) it3).nextInt();
                    arrayList5.add(cls2);
                }
                wVar2.b(arrayList5.toArray(new Class[0]));
                declaredMethod = cls.getDeclaredMethod(str, (Class[]) arrayList3.toArray(new Class[arrayList3.size()]));
            } catch (ReflectiveOperationException unused2) {
                declaredMethod = null;
            }
        }
        if (declaredMethod != null) {
            return asComposableMethod(declaredMethod);
        }
        throw new NoSuchMethodException(cls.getName() + '.' + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final Object getDefaultValue(Class<?> cls) {
        String name = cls.getName();
        switch (name.hashCode()) {
            case -1325958191:
                if (name.equals("double")) {
                    return Double.valueOf(0.0d);
                }
                return null;
            case 104431:
                return !name.equals("int") ? null : 0;
            case 3039496:
                return !name.equals("byte") ? null : (byte) 0;
            case 3052374:
                return !name.equals("char") ? null : (char) 0;
            case 3327612:
                return !name.equals("long") ? null : 0L;
            case 64711720:
                if (name.equals("boolean")) {
                    return Boolean.FALSE;
                }
                return null;
            case 97526364:
                if (name.equals("float")) {
                    return Float.valueOf(0.0f);
                }
                return null;
            case 109413500:
                return !name.equals("short") ? null : (short) 0;
            default:
                return null;
        }
    }
}
