package io.ktor.utils.io.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kotlin.Metadata;
import x6.i0;
import x6.s;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001b\u0010\u0003\u001a\u00020\u0002*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0001¢\u0006\u0004\b\u0003\u0010\u0004\"\u001d\u0010\n\u001a\u0004\u0018\u00010\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\t*\n\u0010\f\"\u00020\u000b2\u00020\u000b¨\u0006\r"}, d2 = {"", "other", "Lx6/t0;", "addSuppressedInternal", "(Ljava/lang/Throwable;Ljava/lang/Throwable;)V", "Ljava/lang/reflect/Method;", "AddSuppressedMethod$delegate", "Lx6/s;", "getAddSuppressedMethod", "()Ljava/lang/reflect/Method;", "AddSuppressedMethod", "Ljava/io/Closeable;", "Closeable", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CloseableJVMKt {
    private static final s AddSuppressedMethod$delegate = new i0(CloseableJVMKt$AddSuppressedMethod$2.INSTANCE);

    public static final void addSuppressedInternal(Throwable th, Throwable th2) throws IllegalAccessException, InvocationTargetException {
        Method addSuppressedMethod = getAddSuppressedMethod();
        if (addSuppressedMethod != null) {
            addSuppressedMethod.invoke(th, th2);
        }
    }

    private static final Method getAddSuppressedMethod() {
        return (Method) AddSuppressedMethod$delegate.getValue();
    }
}
