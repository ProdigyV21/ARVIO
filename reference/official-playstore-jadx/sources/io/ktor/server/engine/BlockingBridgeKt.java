package io.ktor.server.engine;

import io.ktor.utils.io.jvm.javaio.PollersKt;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.a;
import x6.i0;
import x6.s;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u000f\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0001\u0010\u0002\"\u001d\u0010\u0006\u001a\u0004\u0018\u00010\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"", "safeToRunInPlace", "()Z", "Ljava/lang/reflect/Method;", "isParkingAllowedFunction$delegate", "Lx6/s;", "isParkingAllowedFunction", "()Ljava/lang/reflect/Method;", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BlockingBridgeKt {
    private static final s isParkingAllowedFunction$delegate = new i0(AnonymousClass2.INSTANCE);

    /* JADX INFO: renamed from: io.ktor.server.engine.BlockingBridgeKt$isParkingAllowedFunction$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljava/lang/reflect/Method;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements a<Method> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(0);
        }

        @Override // r7.a
        public final Method invoke() {
            try {
                int i10 = PollersKt.f16280a;
                return PollersKt.class.getMethod("isParkingAllowed", null);
            } catch (Throwable unused) {
                return null;
            }
        }
    }

    private static final Method isParkingAllowedFunction() {
        return (Method) isParkingAllowedFunction$delegate.getValue();
    }

    public static final boolean safeToRunInPlace() {
        boolean zA;
        Method methodIsParkingAllowedFunction = isParkingAllowedFunction();
        if (methodIsParkingAllowedFunction == null) {
            return false;
        }
        try {
            zA = p.a(methodIsParkingAllowedFunction.invoke(null, null), Boolean.TRUE);
        } catch (Throwable unused) {
            zA = false;
        }
        return zA;
    }
}
