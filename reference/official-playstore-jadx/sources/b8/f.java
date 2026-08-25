package b8;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.ArrayList;
import kotlin.reflect.b0;

/* JADX INFO: loaded from: classes5.dex */
public final class f extends u implements d {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f7231e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f7232f;

    /* JADX WARN: Illegal instructions before constructor call */
    public f(Constructor constructor, Object obj, int i10) {
        this.f7231e = i10;
        switch (i10) {
            case 1:
                super(constructor, constructor.getDeclaringClass(), null, constructor.getGenericParameterTypes());
                this.f7232f = obj;
                break;
            default:
                Class declaringClass = constructor.getDeclaringClass();
                Type[] genericParameterTypes = constructor.getGenericParameterTypes();
                super(constructor, declaringClass, null, (Type[]) (genericParameterTypes.length <= 2 ? new Type[0] : kotlin.collections.r.S(genericParameterTypes, 1, genericParameterTypes.length - 1)));
                this.f7232f = obj;
                break;
        }
    }

    @Override // b8.e
    public final Object call(Object[] objArr) {
        switch (this.f7231e) {
            case 0:
                b0.f(this, objArr);
                Constructor constructor = (Constructor) this.f7244a;
                com.squareup.moshi.w wVar = new com.squareup.moshi.w(3);
                wVar.a(this.f7232f);
                wVar.b(objArr);
                wVar.a(null);
                ArrayList arrayList = wVar.f14489a;
                return constructor.newInstance(arrayList.toArray(new Object[arrayList.size()]));
            default:
                b0.f(this, objArr);
                Constructor constructor2 = (Constructor) this.f7244a;
                com.squareup.moshi.w wVar2 = new com.squareup.moshi.w(2);
                wVar2.a(this.f7232f);
                wVar2.b(objArr);
                ArrayList arrayList2 = wVar2.f14489a;
                return constructor2.newInstance(arrayList2.toArray(new Object[arrayList2.size()]));
        }
    }
}
