package b8;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import kotlin.reflect.b0;

/* JADX INFO: loaded from: classes5.dex */
public final class r extends o implements d {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f7241g;

    /* JADX WARN: Illegal instructions before constructor call */
    public r(Method method, Object obj) {
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        super(method, false, (Type[]) (genericParameterTypes.length <= 1 ? new Type[0] : kotlin.collections.r.S(genericParameterTypes, 1, genericParameterTypes.length)));
        this.f7241g = obj;
    }

    @Override // b8.o, b8.e
    public final Object call(Object[] objArr) {
        b0.f(this, objArr);
        com.squareup.moshi.w wVar = new com.squareup.moshi.w(2);
        wVar.a(this.f7241g);
        wVar.b(objArr);
        ArrayList arrayList = wVar.f14489a;
        return e(arrayList.toArray(new Object[arrayList.size()]), null);
    }
}
