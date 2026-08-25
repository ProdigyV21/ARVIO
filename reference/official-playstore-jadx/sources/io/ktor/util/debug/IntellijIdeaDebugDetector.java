package io.ktor.util.debug;

import java.lang.management.ManagementFactory;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import kotlin.text.o;
import r7.a;
import x6.i0;
import x6.s;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0007\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lio/ktor/util/debug/IntellijIdeaDebugDetector;", "", "<init>", "()V", "", "isDebuggerConnected$delegate", "Lx6/s;", "isDebuggerConnected", "()Z", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class IntellijIdeaDebugDetector {
    public static final IntellijIdeaDebugDetector INSTANCE = new IntellijIdeaDebugDetector();

    /* JADX INFO: renamed from: isDebuggerConnected$delegate, reason: from kotlin metadata */
    private static final s isDebuggerConnected = new i0(AnonymousClass2.INSTANCE);

    /* JADX INFO: renamed from: io.ktor.util.debug.IntellijIdeaDebugDetector$isDebuggerConnected$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements a<Boolean> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(0);
        }

        @Override // r7.a
        public final Boolean invoke() {
            boolean zT = false;
            try {
                zT = o.T(ManagementFactory.getRuntimeMXBean().getInputArguments().toString(), "jdwp", false);
            } catch (Throwable unused) {
            }
            return Boolean.valueOf(zT);
        }
    }

    private IntellijIdeaDebugDetector() {
    }

    public final boolean isDebuggerConnected() {
        return ((Boolean) isDebuggerConnected.getValue()).booleanValue();
    }
}
