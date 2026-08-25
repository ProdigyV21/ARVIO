package b8;

import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.reflect.b0;

/* JADX INFO: loaded from: classes5.dex */
public final class g extends u {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f7233e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ g(Member member, Type type, Class cls, Type[] typeArr, int i10) {
        super(member, type, cls, typeArr);
        this.f7233e = i10;
    }

    @Override // b8.e
    public final Object call(Object[] objArr) {
        switch (this.f7233e) {
            case 0:
                b0.f(this, objArr);
                Constructor constructor = (Constructor) this.f7244a;
                com.squareup.moshi.w wVar = new com.squareup.moshi.w(2);
                wVar.b(objArr);
                wVar.a(null);
                ArrayList arrayList = wVar.f14489a;
                return constructor.newInstance(arrayList.toArray(new Object[arrayList.size()]));
            default:
                b0.f(this, objArr);
                return ((Constructor) this.f7244a).newInstance(Arrays.copyOf(objArr, objArr.length));
        }
    }
}
