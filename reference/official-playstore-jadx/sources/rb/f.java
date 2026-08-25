package rb;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.net.ssl.SSLSocket;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes5.dex */
public class f implements m {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final e f21451f = new e();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class f21452a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Method f21453b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Method f21454c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Method f21455d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Method f21456e;

    public f(Class cls) {
        this.f21452a = cls;
        this.f21453b = cls.getDeclaredMethod("setUseSessionTickets", Boolean.TYPE);
        this.f21454c = cls.getMethod("setHostname", String.class);
        this.f21455d = cls.getMethod("getAlpnSelectedProtocol", null);
        this.f21456e = cls.getMethod("setAlpnProtocols", byte[].class);
    }

    @Override // rb.m
    public final boolean a(SSLSocket sSLSocket) {
        return this.f21452a.isInstance(sSLSocket);
    }

    @Override // rb.m
    public final boolean b() {
        boolean z = qb.c.f21365e;
        return qb.c.f21365e;
    }

    @Override // rb.m
    public final String c(SSLSocket sSLSocket) {
        if (this.f21452a.isInstance(sSLSocket)) {
            try {
                byte[] bArr = (byte[]) this.f21455d.invoke(sSLSocket, null);
                if (bArr != null) {
                    return new String(bArr, kotlin.text.a.f19924a);
                }
            } catch (IllegalAccessException e5) {
                throw new AssertionError(e5);
            } catch (InvocationTargetException e6) {
                Throwable cause = e6.getCause();
                if (!(cause instanceof NullPointerException) || !p.a(((NullPointerException) cause).getMessage(), "ssl == null")) {
                    throw new AssertionError(e6);
                }
            }
        }
        return null;
    }

    @Override // rb.m
    public final void d(SSLSocket sSLSocket, String str, List list) {
        if (this.f21452a.isInstance(sSLSocket)) {
            try {
                this.f21453b.invoke(sSLSocket, Boolean.TRUE);
                if (str != null) {
                    this.f21454c.invoke(sSLSocket, str);
                }
                Method method = this.f21456e;
                qb.n nVar = qb.n.f21385a;
                method.invoke(sSLSocket, io.sentry.util.l.h(list));
            } catch (IllegalAccessException e5) {
                throw new AssertionError(e5);
            } catch (InvocationTargetException e6) {
                throw new AssertionError(e6);
            }
        }
    }
}
