package qb;

import gb.i0;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLSocket;
import kotlin.collections.s;

/* JADX INFO: loaded from: classes5.dex */
public final class j extends n {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Method f21376c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Method f21377d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Method f21378e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Class f21379f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Class f21380g;

    public j(Method method, Method method2, Method method3, Class cls, Class cls2) {
        this.f21376c = method;
        this.f21377d = method2;
        this.f21378e = method3;
        this.f21379f = cls;
        this.f21380g = cls2;
    }

    @Override // qb.n
    public final void a(SSLSocket sSLSocket) {
        try {
            this.f21378e.invoke(null, sSLSocket);
        } catch (IllegalAccessException e5) {
            throw new AssertionError("failed to remove ALPN", e5);
        } catch (InvocationTargetException e6) {
            throw new AssertionError("failed to remove ALPN", e6);
        }
    }

    @Override // qb.n
    public final void d(SSLSocket sSLSocket, String str, List list) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (((i0) obj) != i0.HTTP_1_0) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(s.U(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((i0) it.next()).f15721i);
        }
        try {
            this.f21376c.invoke(null, sSLSocket, Proxy.newProxyInstance(n.class.getClassLoader(), new Class[]{this.f21379f, this.f21380g}, new i(arrayList2)));
        } catch (IllegalAccessException e5) {
            throw new AssertionError("failed to set ALPN", e5);
        } catch (InvocationTargetException e6) {
            throw new AssertionError("failed to set ALPN", e6);
        }
    }

    @Override // qb.n
    public final String f(SSLSocket sSLSocket) {
        try {
            i iVar = (i) Proxy.getInvocationHandler(this.f21377d.invoke(null, sSLSocket));
            boolean z = iVar.f21374b;
            if (!z && iVar.f21375c == null) {
                n.j(this, "ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", 6);
                return null;
            }
            if (z) {
                return null;
            }
            return iVar.f21375c;
        } catch (IllegalAccessException e5) {
            throw new AssertionError("failed to get ALPN selected protocol", e5);
        } catch (InvocationTargetException e6) {
            throw new AssertionError("failed to get ALPN selected protocol", e6);
        }
    }
}
