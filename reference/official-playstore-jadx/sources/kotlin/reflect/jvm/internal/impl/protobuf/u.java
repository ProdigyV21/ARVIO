package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.Serializable;
import java.util.Collections;

/* JADX INFO: loaded from: classes5.dex */
public abstract class u extends c implements Serializable {
    public static t f(c0 c0Var, c0 c0Var2, int i10, u0 u0Var, Class cls) {
        return new t(c0Var, Collections.EMPTY_LIST, c0Var2, new s(i10, u0Var, true), cls);
    }

    public static t g(c0 c0Var, Object obj, c0 c0Var2, int i10, u0 u0Var, Class cls) {
        return new t(c0Var, obj, c0Var2, new s(i10, u0Var, false), cls);
    }
}
