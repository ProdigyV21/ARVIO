package a8;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class s1 extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f262i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f263l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f264m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f265n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s1(int i10, String str, cb.z zVar) {
        super(0);
        this.f263l = i10;
        this.f264m = str;
        this.f265n = zVar;
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Object, x6.s] */
    @Override // r7.a
    public final Object invoke() {
        switch (this.f262i) {
            case 0:
                u1 u1Var = (u1) this.f264m;
                Type typeK = u1Var.k();
                if (typeK instanceof Class) {
                    Class cls = (Class) typeK;
                    return cls.isArray() ? cls.getComponentType() : Object.class;
                }
                boolean z = typeK instanceof GenericArrayType;
                int i10 = this.f263l;
                if (z) {
                    if (i10 == 0) {
                        return ((GenericArrayType) typeK).getGenericComponentType();
                    }
                    throw new x1("Array type has been queried for a non-0th argument: " + u1Var);
                }
                if (!(typeK instanceof ParameterizedType)) {
                    throw new x1("Non-generic type has been queried for arguments: " + u1Var);
                }
                Type type = (Type) ((List) this.f265n.getValue()).get(i10);
                if (!(type instanceof WildcardType)) {
                    return type;
                }
                WildcardType wildcardType = (WildcardType) type;
                Type type2 = (Type) kotlin.collections.r.Z(wildcardType.getLowerBounds());
                return type2 == null ? (Type) kotlin.collections.r.Y(wildcardType.getUpperBounds()) : type2;
            default:
                int i11 = this.f263l;
                ab.g[] gVarArr = new ab.g[i11];
                for (int i12 = 0; i12 < i11; i12++) {
                    gVarArr[i12] = xc.d.K(((String) this.f264m) + '.' + ((cb.z) this.f265n).f7481e[i12], ab.n.f821e, new ab.g[0]);
                }
                return gVarArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s1(u1 u1Var, int i10, x6.s sVar) {
        super(0);
        this.f264m = u1Var;
        this.f263l = i10;
        this.f265n = sVar;
    }
}
