package androidx.datastore.preferences.protobuf;

import j$.util.concurrent.ConcurrentHashMap;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public abstract class r0 extends c {
    private static Map<Object, r0> defaultInstanceMap = new ConcurrentHashMap();
    protected int memoizedSerializedSize;
    protected g3 unknownFields;

    public r0() {
        this.memoizedHashCode = 0;
        this.unknownFields = g3.f2439f;
        this.memoizedSerializedSize = -1;
    }

    public static r0 i(Class cls) {
        r0 r0Var = defaultInstanceMap.get(cls);
        if (r0Var == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                r0Var = defaultInstanceMap.get(cls);
            } catch (ClassNotFoundException e5) {
                throw new IllegalStateException("Class initialization cannot fail.", e5);
            }
        }
        if (r0Var != null) {
            return r0Var;
        }
        r0 r0Var2 = (r0) ((r0) p3.a(cls)).h(6);
        if (r0Var2 == null) {
            throw new IllegalStateException();
        }
        defaultInstanceMap.put(cls, r0Var2);
        return r0Var2;
    }

    public static Object j(Method method, r0 r0Var, Object... objArr) {
        try {
            return method.invoke(r0Var, objArr);
        } catch (IllegalAccessException e5) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e5);
        } catch (InvocationTargetException e6) {
            Throwable cause = e6.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    public static r0 l(b1.d dVar, FileInputStream fileInputStream) throws InvalidProtocolBufferException {
        p pVar = new p(fileInputStream);
        d0 d0VarA = d0.a();
        r0 r0Var = (r0) dVar.h(4);
        try {
            j2 j2Var = j2.f2452c;
            j2Var.getClass();
            o2 o2VarA = j2Var.a(r0Var.getClass());
            r rVar = pVar.f2506b;
            if (rVar == null) {
                rVar = new r(pVar);
            }
            o2VarA.a(r0Var, rVar, d0VarA);
            o2VarA.b(r0Var);
            if (r0Var.k()) {
                return r0Var;
            }
            throw new InvalidProtocolBufferException(new UninitializedMessageException().getMessage());
        } catch (IOException e5) {
            if (e5.getCause() instanceof InvalidProtocolBufferException) {
                throw ((InvalidProtocolBufferException) e5.getCause());
            }
            throw new InvalidProtocolBufferException(e5.getMessage());
        } catch (RuntimeException e6) {
            if (e6.getCause() instanceof InvalidProtocolBufferException) {
                throw ((InvalidProtocolBufferException) e6.getCause());
            }
            throw e6;
        }
    }

    public static void m(Class cls, r0 r0Var) {
        defaultInstanceMap.put(cls, r0Var);
    }

    @Override // androidx.datastore.preferences.protobuf.v1
    public final int a() {
        if (this.memoizedSerializedSize == -1) {
            j2 j2Var = j2.f2452c;
            j2Var.getClass();
            this.memoizedSerializedSize = j2Var.a(getClass()).c(this);
        }
        return this.memoizedSerializedSize;
    }

    @Override // androidx.datastore.preferences.protobuf.w1
    public final r0 b() {
        return (r0) h(6);
    }

    @Override // androidx.datastore.preferences.protobuf.v1
    public final o0 c() {
        o0 o0Var = (o0) h(5);
        o0Var.h();
        o0.i(o0Var.f2483l, this);
        return o0Var;
    }

    @Override // androidx.datastore.preferences.protobuf.v1
    public final void d(u uVar) {
        j2 j2Var = j2.f2452c;
        j2Var.getClass();
        o2 o2VarA = j2Var.a(getClass());
        v vVar = uVar.f2535c;
        if (vVar == null) {
            vVar = new v(uVar);
        }
        o2VarA.h(this, vVar);
    }

    @Override // androidx.datastore.preferences.protobuf.v1
    public final o0 e() {
        return (o0) h(5);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!((r0) h(6)).getClass().isInstance(obj)) {
            return false;
        }
        j2 j2Var = j2.f2452c;
        j2Var.getClass();
        return j2Var.a(getClass()).i(this, (r0) obj);
    }

    public abstract Object h(int i10);

    public final int hashCode() {
        int i10 = this.memoizedHashCode;
        if (i10 != 0) {
            return i10;
        }
        j2 j2Var = j2.f2452c;
        j2Var.getClass();
        int iG = j2Var.a(getClass()).g(this);
        this.memoizedHashCode = iG;
        return iG;
    }

    public final boolean k() {
        byte bByteValue = ((Byte) h(1)).byteValue();
        if (bByteValue == 1) {
            return true;
        }
        if (bByteValue == 0) {
            return false;
        }
        j2 j2Var = j2.f2452c;
        j2Var.getClass();
        boolean zD = j2Var.a(getClass()).d(this);
        h(2);
        return zD;
    }

    public final String toString() {
        String string = super.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("# ");
        sb2.append(string);
        x1.k(this, sb2, 0);
        return sb2.toString();
    }
}
