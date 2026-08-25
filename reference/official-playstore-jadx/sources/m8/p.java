package m8;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.WildcardType;

/* JADX INFO: loaded from: classes5.dex */
public final class p extends e implements w8.h {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Class f20289b;

    public p(f9.f fVar, Class cls) {
        super(fVar);
        this.f20289b = cls;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // w8.h
    public final c0 b() {
        Class cls = this.f20289b;
        return cls.isPrimitive() ? new a0(cls) : ((cls instanceof GenericArrayType) || cls.isArray()) ? new i(cls) : cls instanceof WildcardType ? new f0((WildcardType) cls) : new q(cls);
    }
}
