package androidx.compose.ui.platform;

import io.ktor.http.ContentDisposition;
import java.util.Arrays;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a!\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u0013\u0010\u0006\u001a\u00020\u0000*\u00020\u0000H\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a,\u0010\f\u001a\u00028\u0000\"\u0004\b\u0000\u0010\b2\u0006\u0010\t\u001a\u00020\u00002\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\nH\u0081\b¢\u0006\u0004\b\f\u0010\r*\f\b\u0000\u0010\u000f\"\u00020\u000e2\u00020\u000e¨\u0006\u0010"}, d2 = {"", "obj", "", ContentDisposition.Parameters.Name, "simpleIdentityToString", "(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/String;", "nativeClass", "(Ljava/lang/Object;)Ljava/lang/Object;", "R", "lock", "Lkotlin/Function0;", "block", "synchronized", "(Ljava/lang/Object;Lr7/a;)Ljava/lang/Object;", "Ljava/util/concurrent/atomic/AtomicInteger;", "AtomicInt", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class JvmActuals_jvmKt {
    public static final Object nativeClass(Object obj) {
        return obj.getClass();
    }

    public static final String simpleIdentityToString(Object obj, String str) {
        if (str == null) {
            str = obj.getClass().isAnonymousClass() ? obj.getClass().getName() : obj.getClass().getSimpleName();
        }
        return str + '@' + String.format("%07x", Arrays.copyOf(new Object[]{Integer.valueOf(System.identityHashCode(obj))}, 1));
    }

    /* JADX INFO: renamed from: synchronized, reason: not valid java name */
    public static final <R> R m4970synchronized(Object obj, r7.a<? extends R> aVar) {
        R r4;
        synchronized (obj) {
            r4 = (R) aVar.invoke();
        }
        return r4;
    }
}
