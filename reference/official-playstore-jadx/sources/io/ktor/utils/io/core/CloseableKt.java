package io.ktor.utils.io.core;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import kotlin.Metadata;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a?\u0010\u0006\u001a\u00028\u0001\"\f\b\u0000\u0010\u0002*\u00060\u0000j\u0002`\u0001\"\u0004\b\u0001\u0010\u0003*\u00028\u00002\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004H\u0086\bø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\b"}, d2 = {"Ljava/io/Closeable;", "Lio/ktor/utils/io/core/Closeable;", "C", "R", "Lkotlin/Function1;", "block", "use", "(Ljava/io/Closeable;Lr7/l;)Ljava/lang/Object;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CloseableKt {
    public static final <C extends Closeable, R> R use(C c10, l<? super C, ? extends R> lVar) throws IllegalAccessException, IOException, InvocationTargetException {
        try {
            R r4 = (R) lVar.invoke(c10);
            c10.close();
            return r4;
        } catch (Throwable th) {
            try {
                c10.close();
            } catch (Throwable th2) {
                CloseableJVMKt.addSuppressedInternal(th, th2);
            }
            throw th;
        }
    }
}
