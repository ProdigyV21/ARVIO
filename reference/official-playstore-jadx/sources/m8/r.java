package m8;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class r extends w implements w8.k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Constructor f20292a;

    public r(Constructor constructor) {
        this.f20292a = constructor;
    }

    @Override // m8.w
    public final Member H() {
        return this.f20292a;
    }

    @Override // w8.k
    public final List e() {
        Constructor constructor = this.f20292a;
        Type[] genericParameterTypes = constructor.getGenericParameterTypes();
        if (genericParameterTypes.length == 0) {
            return kotlin.collections.z.f19728i;
        }
        Class declaringClass = constructor.getDeclaringClass();
        if (declaringClass.getDeclaringClass() != null && !Modifier.isStatic(declaringClass.getModifiers())) {
            genericParameterTypes = (Type[]) kotlin.collections.r.S(genericParameterTypes, 1, genericParameterTypes.length);
        }
        Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();
        if (parameterAnnotations.length >= genericParameterTypes.length) {
            if (parameterAnnotations.length > genericParameterTypes.length) {
                parameterAnnotations = (Annotation[][]) kotlin.collections.r.S(parameterAnnotations, parameterAnnotations.length - genericParameterTypes.length, parameterAnnotations.length);
            }
            return I(genericParameterTypes, parameterAnnotations, constructor.isVarArgs());
        }
        throw new IllegalStateException("Illegal generic signature: " + constructor);
    }

    @Override // w8.y
    public final ArrayList getTypeParameters() {
        TypeVariable[] typeParameters = this.f20292a.getTypeParameters();
        ArrayList arrayList = new ArrayList(typeParameters.length);
        for (TypeVariable typeVariable : typeParameters) {
            arrayList.add(new d0(typeVariable));
        }
        return arrayList;
    }
}
