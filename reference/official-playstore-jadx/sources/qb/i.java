package qb;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes5.dex */
public final class i implements InvocationHandler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f21373a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f21374b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f21375c;

    public i(ArrayList arrayList) {
        this.f21373a = arrayList;
    }

    @Override // java.lang.reflect.InvocationHandler
    public final Object invoke(Object obj, Method method, Object[] objArr) {
        if (objArr == null) {
            objArr = new Object[0];
        }
        String name = method.getName();
        Class<?> returnType = method.getReturnType();
        if (p.a(name, "supports") && p.a(Boolean.TYPE, returnType)) {
            return Boolean.TRUE;
        }
        if (p.a(name, "unsupported") && p.a(Void.TYPE, returnType)) {
            this.f21374b = true;
            return null;
        }
        boolean zA = p.a(name, "protocols");
        ArrayList arrayList = this.f21373a;
        if (zA && objArr.length == 0) {
            return arrayList;
        }
        if ((p.a(name, "selectProtocol") || p.a(name, "select")) && String.class.equals(returnType) && objArr.length == 1) {
            Object obj2 = objArr[0];
            if (obj2 instanceof List) {
                List list = (List) obj2;
                int size = list.size();
                if (size >= 0) {
                    int i10 = 0;
                    while (true) {
                        String str = (String) list.get(i10);
                        if (!arrayList.contains(str)) {
                            if (i10 == size) {
                                break;
                            }
                            i10++;
                        } else {
                            this.f21375c = str;
                            return str;
                        }
                    }
                }
                String str2 = (String) arrayList.get(0);
                this.f21375c = str2;
                return str2;
            }
        }
        if ((!p.a(name, "protocolSelected") && !p.a(name, "selected")) || objArr.length != 1) {
            return method.invoke(this, Arrays.copyOf(objArr, objArr.length));
        }
        this.f21375c = (String) objArr[0];
        return null;
    }
}
