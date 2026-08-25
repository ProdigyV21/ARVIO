package io.ktor.utils.io.jvm.javaio;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;
import vc.b;
import vc.c;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0004\u001a\n \u0001*\u0004\u0018\u00010\u00000\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lvc/b;", "kotlin.jvm.PlatformType", "invoke", "()Lvc/b;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class BlockingKt$ADAPTER_LOGGER$2 extends r implements a<b> {
    public static final BlockingKt$ADAPTER_LOGGER$2 INSTANCE = new BlockingKt$ADAPTER_LOGGER$2();

    public BlockingKt$ADAPTER_LOGGER$2() {
        super(0);
    }

    @Override // r7.a
    public final b invoke() {
        int i10;
        int i11 = c.f22330a;
        b bVarD = c.d(BlockingAdapter.class.getName());
        if (c.f22333d) {
            xc.c cVar = d.f22819a;
            Class cls = null;
            if (cVar == null) {
                if (d.f22820b) {
                    cVar = null;
                } else {
                    try {
                        cVar = new xc.c();
                    } catch (SecurityException unused) {
                        cVar = null;
                    }
                    d.f22819a = cVar;
                    d.f22820b = true;
                }
            }
            if (cVar != null) {
                Class[] classContext = cVar.getClassContext();
                String name = d.class.getName();
                int i12 = 0;
                while (i12 < classContext.length && !name.equals(classContext[i12].getName())) {
                    i12++;
                }
                if (i12 >= classContext.length || (i10 = i12 + 2) >= classContext.length) {
                    throw new IllegalStateException("Failed to find org.slf4j.helpers.Util or its caller in the stack; this should not happen");
                }
                cls = classContext[i10];
            }
            if (cls != null && !cls.isAssignableFrom(BlockingAdapter.class)) {
                d.H0("Detected logger name mismatch. Given name: \"" + bVarD.getName() + "\"; computed name: \"" + cls.getName() + "\".");
                d.H0("See http://www.slf4j.org/codes.html#loggerNameMismatch for an explanation");
            }
        }
        return bVarD;
    }
}
