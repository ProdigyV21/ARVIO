package b8;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import kotlin.reflect.b0;

/* JADX INFO: loaded from: classes5.dex */
public final class s extends o implements d {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object[] f7242g;

    public s(Method method, Object[] objArr) {
        super(method, false, (Type[]) kotlin.collections.r.T(objArr.length, method.getGenericParameterTypes()).toArray(new Type[0]));
        this.f7242g = objArr;
    }

    @Override // b8.o, b8.e
    public final Object call(Object[] objArr) {
        b0.f(this, objArr);
        com.squareup.moshi.w wVar = new com.squareup.moshi.w(2);
        wVar.b(this.f7242g);
        wVar.b(objArr);
        ArrayList arrayList = wVar.f14489a;
        return e(arrayList.toArray(new Object[arrayList.size()]), null);
    }
}
