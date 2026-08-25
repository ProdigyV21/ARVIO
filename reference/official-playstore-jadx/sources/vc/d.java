package vc;

import org.slf4j.impl.StaticMDCBinder;
import org.slf4j.spi.MDCAdapter;
import v1.i;

/* JADX INFO: loaded from: classes5.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final MDCAdapter f22336a;

    static {
        try {
            f22336a = a();
        } catch (Exception e5) {
            xc.d.I0("MDC binding unsuccessful.", e5);
        } catch (NoClassDefFoundError e6) {
            f22336a = new i();
            String message = e6.getMessage();
            if (message == null || !message.contains("StaticMDCBinder")) {
                throw e6;
            }
            xc.d.H0("Failed to load class \"org.slf4j.impl.StaticMDCBinder\".");
            xc.d.H0("Defaulting to no-operation MDCAdapter implementation.");
            xc.d.H0("See http://www.slf4j.org/codes.html#no_static_mdc_binder for further details.");
        }
    }

    public static MDCAdapter a() {
        try {
            return StaticMDCBinder.getSingleton().getMDCA();
        } catch (NoSuchMethodError unused) {
            return StaticMDCBinder.SINGLETON.getMDCA();
        }
    }
}
