package io.ktor.utils.io;

import java.lang.reflect.Constructor;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.c0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003¨\u0006\u0004"}, d2 = {"<anonymous>", "", "e", "invoke", "io/ktor/utils/io/ExceptionUtilsJvmKt$safeCtor$1"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class ExceptionUtilsJvmKt$createConstructor$$inlined$safeCtor$2 extends r implements l<Throwable, Throwable> {
    final /* synthetic */ Constructor $constructor$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExceptionUtilsJvmKt$createConstructor$$inlined$safeCtor$2(Constructor constructor) {
        super(1);
        this.$constructor$inlined = constructor;
    }

    @Override // r7.l
    public final Throwable invoke(Throwable th) {
        Object c0Var;
        try {
            c0Var = (Throwable) this.$constructor$inlined.newInstance(th);
        } catch (Throwable th2) {
            c0Var = new c0(th2);
        }
        if (c0Var instanceof c0) {
            c0Var = null;
        }
        return (Throwable) c0Var;
    }
}
