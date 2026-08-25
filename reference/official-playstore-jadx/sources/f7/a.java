package f7;

import androidx.appcompat.widget.v3;
import io.ktor.http.ContentDisposition;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import x6.c0;
import x6.t0;

/* JADX INFO: loaded from: classes5.dex */
public abstract class a implements d7.d, d, Serializable {
    private final d7.d<Object> completion;

    public a(d7.d dVar) {
        this.completion = dVar;
    }

    public d7.d<t0> create(d7.d<?> dVar) {
        throw new UnsupportedOperationException("create(Continuation) has not been overridden");
    }

    @Override // f7.d
    public d getCallerFrame() {
        d7.d<Object> dVar = this.completion;
        if (dVar instanceof d) {
            return (d) dVar;
        }
        return null;
    }

    public final d7.d<Object> getCompletion() {
        return this.completion;
    }

    public StackTraceElement getStackTraceElement() {
        int iIntValue;
        String strC;
        Method method;
        Object objInvoke;
        Method method2;
        Object objInvoke2;
        e eVar = (e) getClass().getAnnotation(e.class);
        String str = null;
        if (eVar == null || eVar.v() < 1) {
            return null;
        }
        try {
            Field declaredField = getClass().getDeclaredField("label");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(this);
            Integer num = obj instanceof Integer ? (Integer) obj : null;
            iIntValue = (num != null ? num.intValue() : 0) - 1;
        } catch (Exception unused) {
            iIntValue = -1;
        }
        int i10 = iIntValue >= 0 ? eVar.l()[iIntValue] : -1;
        v3 v3Var = f.f15291b;
        v3 v3Var2 = f.f15290a;
        if (v3Var == null) {
            try {
                v3 v3Var3 = new v3(Class.class.getDeclaredMethod("getModule", null), getClass().getClassLoader().loadClass("java.lang.Module").getDeclaredMethod("getDescriptor", null), getClass().getClassLoader().loadClass("java.lang.module.ModuleDescriptor").getDeclaredMethod(ContentDisposition.Parameters.Name, null));
                f.f15291b = v3Var3;
                v3Var = v3Var3;
            } catch (Exception unused2) {
                f.f15291b = v3Var2;
                v3Var = v3Var2;
            }
        }
        if (v3Var != v3Var2 && (method = v3Var.f1798a) != null && (objInvoke = method.invoke(getClass(), null)) != null && (method2 = v3Var.f1799b) != null && (objInvoke2 = method2.invoke(objInvoke, null)) != null) {
            Method method3 = v3Var.f1800c;
            Object objInvoke3 = method3 != null ? method3.invoke(objInvoke2, null) : null;
            if (objInvoke3 instanceof String) {
                str = (String) objInvoke3;
            }
        }
        if (str == null) {
            strC = eVar.c();
        } else {
            strC = str + '/' + eVar.c();
        }
        return new StackTraceElement(strC, eVar.m(), eVar.f(), i10);
    }

    public abstract Object invokeSuspend(Object obj);

    @Override // d7.d
    public final void resumeWith(Object obj) {
        d7.d<Object> dVar = this;
        while (true) {
            a aVar = (a) dVar;
            d7.d<Object> dVar2 = aVar.completion;
            try {
                obj = aVar.invokeSuspend(obj);
                if (obj == e7.a.f15033i) {
                    return;
                }
            } catch (Throwable th) {
                obj = new c0(th);
            }
            aVar.releaseIntercepted();
            if (!(dVar2 instanceof a)) {
                dVar2.resumeWith(obj);
                return;
            }
            dVar = dVar2;
        }
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("Continuation at ");
        Object stackTraceElement = getStackTraceElement();
        if (stackTraceElement == null) {
            stackTraceElement = getClass().getName();
        }
        sb2.append(stackTraceElement);
        return sb2.toString();
    }

    public d7.d<t0> create(Object obj, d7.d<?> dVar) {
        throw new UnsupportedOperationException("create(Any?;Continuation) has not been overridden");
    }

    public void releaseIntercepted() {
    }
}
