package androidx.compose.runtime.reflect;

import androidx.compose.runtime.Composer;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.e0;
import kotlin.collections.r;
import kotlin.collections.s;
import kotlin.jvm.internal.p;
import qb.d;
import t7.a;
import x7.i;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0014\u001a\u00020\u0003J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0018\u001a\u00020\bH\u0016J:\u0010\u0019\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u00012\u0016\u0010\u001d\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\f\"\u0004\u0018\u00010\u0001H\u0086\u0002¢\u0006\u0002\u0010\u001eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u001b\u0010\u000b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0\f8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\f8F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, d2 = {"Landroidx/compose/runtime/reflect/ComposableMethod;", "", "method", "Ljava/lang/reflect/Method;", "composableInfo", "Landroidx/compose/runtime/reflect/ComposableInfo;", "(Ljava/lang/reflect/Method;Landroidx/compose/runtime/reflect/ComposableInfo;)V", "parameterCount", "", "getParameterCount", "()I", "parameterTypes", "", "Ljava/lang/Class;", "getParameterTypes", "()[Ljava/lang/Class;", "parameters", "Ljava/lang/reflect/Parameter;", "getParameters", "()[Ljava/lang/reflect/Parameter;", "asMethod", "equals", "", "other", "hashCode", "invoke", "composer", "Landroidx/compose/runtime/Composer;", "instance", "args", "(Landroidx/compose/runtime/Composer;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ComposableMethod {
    public static final int $stable = 8;
    private final ComposableInfo composableInfo;
    private final Method method;

    public ComposableMethod(Method method, ComposableInfo composableInfo) {
        this.method = method;
        this.composableInfo = composableInfo;
    }

    /* JADX INFO: renamed from: asMethod, reason: from getter */
    public final Method getMethod() {
        return this.method;
    }

    public boolean equals(Object other) {
        if (other instanceof ComposableMethod) {
            return p.a(this.method, ((ComposableMethod) other).method);
        }
        return false;
    }

    public final int getParameterCount() {
        return this.composableInfo.getRealParamsCount();
    }

    public final Class<?>[] getParameterTypes() {
        return (Class[]) r.S(this.method.getParameterTypes(), 0, this.composableInfo.getRealParamsCount());
    }

    public final Parameter[] getParameters() {
        return (Parameter[]) r.S(this.method.getParameters(), 0, this.composableInfo.getRealParamsCount());
    }

    public int hashCode() {
        return this.method.hashCode();
    }

    public final Object invoke(Composer composer, Object instance, Object... args) {
        Object defaultValue;
        ComposableInfo composableInfo = this.composableInfo;
        int realParamsCount = composableInfo.getRealParamsCount();
        int changedParams = composableInfo.getChangedParams();
        int defaultParams = composableInfo.getDefaultParams();
        int length = this.method.getParameterTypes().length;
        int i10 = realParamsCount + 1;
        int i11 = changedParams + i10;
        int i12 = 0;
        Object[] objArr = new Integer[defaultParams];
        for (int i13 = 0; i13 < defaultParams; i13++) {
            int i14 = i13 * 31;
            i iVarX = d.X(i14, Math.min(i14 + 31, realParamsCount));
            ArrayList arrayList = new ArrayList(s.U(iVarX, 10));
            Iterator it = iVarX.iterator();
            while (it.hasNext()) {
                int iNextInt = ((e0) it).nextInt();
                arrayList.add(Integer.valueOf((iNextInt >= args.length || args[iNextInt] == null) ? 1 : 0));
            }
            int iIntValue = 0;
            int i15 = 0;
            for (Object obj : arrayList) {
                int i16 = i15 + 1;
                if (i15 < 0) {
                    a.Q();
                    throw null;
                }
                iIntValue |= ((Number) obj).intValue() << i15;
                i15 = i16;
            }
            objArr[i13] = Integer.valueOf(iIntValue);
        }
        Object[] objArr2 = new Object[length];
        while (i12 < length) {
            if (i12 >= 0 && i12 < realParamsCount) {
                defaultValue = (i12 < 0 || i12 > args.length - 1) ? ComposableMethodKt.getDefaultValue(this.method.getParameterTypes()[i12]) : args[i12];
            } else if (i12 == realParamsCount) {
                defaultValue = composer;
            } else if (i12 == i10 || (realParamsCount + 2 <= i12 && i12 < i11)) {
                defaultValue = 0;
            } else {
                if (i11 > i12 || i12 >= length) {
                    throw new IllegalStateException("Unexpected index");
                }
                defaultValue = objArr[i12 - i11];
            }
            objArr2[i12] = defaultValue;
            i12++;
        }
        return this.method.invoke(instance, Arrays.copyOf(objArr2, length));
    }
}
