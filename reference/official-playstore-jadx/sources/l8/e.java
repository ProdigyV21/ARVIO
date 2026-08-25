package l8;

import com.google.common.util.concurrent.r0;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import kotlin.jvm.internal.p;
import y8.a0;
import y8.m;
import y8.n;
import y8.y;

/* JADX INFO: loaded from: classes5.dex */
public final class e implements y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class f20063a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final o5.a f20064b;

    public e(Class cls, o5.a aVar) {
        this.f20063a = cls;
        this.f20064b = aVar;
    }

    @Override // y8.y
    public final void a(y.c cVar) throws InvocationTargetException {
        for (Annotation annotation : this.f20063a.getDeclaredAnnotations()) {
            b.b(cVar, annotation);
        }
        cVar.a();
    }

    @Override // y8.y
    public final o5.a b() {
        return this.f20064b;
    }

    @Override // y8.y
    public final void c(y8.d dVar) throws InvocationTargetException {
        m mVar;
        Class cls = this.f20063a;
        for (Method method : cls.getDeclaredMethods()) {
            f9.f fVarJ = f9.f.j(method.getName());
            StringBuilder sb2 = new StringBuilder("(");
            for (Class<?> cls2 : method.getParameterTypes()) {
                sb2.append(m8.c.b(cls2));
            }
            sb2.append(")");
            sb2.append(m8.c.b(method.getReturnType()));
            y8.b bVarA = dVar.a(fVarJ, sb2.toString());
            for (Annotation annotation : method.getDeclaredAnnotations()) {
                b.b(bVarA, annotation);
            }
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            int length = parameterAnnotations.length;
            for (int i10 = 0; i10 < length; i10++) {
                for (Annotation annotation2 : parameterAnnotations[i10]) {
                    Class clsC = ((kotlin.jvm.internal.f) r0.k(annotation2)).c();
                    m mVarC = bVarA.c(i10, m8.c.a(clsC), new a(annotation2));
                    if (mVarC != null) {
                        b.c(mVarC, annotation2, clsC);
                    }
                }
            }
            bVarA.a();
        }
        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
        int length2 = declaredConstructors.length;
        int i11 = 0;
        while (i11 < length2) {
            Constructor<?> constructor = declaredConstructors[i11];
            f9.f fVar = f9.h.f15376e;
            StringBuilder sb3 = new StringBuilder("(");
            for (Class<?> cls3 : constructor.getParameterTypes()) {
                sb3.append(m8.c.b(cls3));
            }
            sb3.append(")V");
            y8.b bVarA2 = dVar.a(fVar, sb3.toString());
            for (Annotation annotation3 : constructor.getDeclaredAnnotations()) {
                b.b(bVarA2, annotation3);
            }
            Annotation[][] parameterAnnotations2 = constructor.getParameterAnnotations();
            if (parameterAnnotations2.length != 0) {
                int length3 = constructor.getParameterTypes().length - parameterAnnotations2.length;
                int length4 = parameterAnnotations2.length;
                for (int i12 = 0; i12 < length4; i12++) {
                    Annotation[] annotationArr = parameterAnnotations2[i12];
                    int length5 = annotationArr.length;
                    int i13 = 0;
                    while (i13 < length5) {
                        Annotation annotation4 = annotationArr[i13];
                        Class clsC2 = ((kotlin.jvm.internal.f) r0.k(annotation4)).c();
                        Constructor<?>[] constructorArr = declaredConstructors;
                        Class cls4 = cls;
                        int i14 = length2;
                        m mVarC2 = bVarA2.c(i12 + length3, m8.c.a(clsC2), new a(annotation4));
                        if (mVarC2 != null) {
                            b.c(mVarC2, annotation4, clsC2);
                        }
                        i13++;
                        declaredConstructors = constructorArr;
                        cls = cls4;
                        length2 = i14;
                    }
                }
            }
            Constructor<?>[] constructorArr2 = declaredConstructors;
            Class cls5 = cls;
            int i15 = length2;
            bVarA2.a();
            i11++;
            declaredConstructors = constructorArr2;
            cls = cls5;
            length2 = i15;
        }
        for (Field field : cls.getDeclaredFields()) {
            a0 a0Var = new a0(androidx.compose.foundation.c.m('#', f9.f.j(field.getName()).d(), m8.c.b(field.getType())));
            ArrayList arrayList = new ArrayList();
            for (Annotation annotation5 : field.getDeclaredAnnotations()) {
                Class clsC3 = ((kotlin.jvm.internal.f) r0.k(annotation5)).c();
                f9.b bVarA3 = m8.c.a(clsC3);
                a aVar = new a(annotation5);
                y8.e eVar = dVar.f22919a;
                if (c8.b.f7422a.contains(bVarA3)) {
                    mVar = null;
                } else {
                    n nVar = (n) eVar;
                    mVar = new m(nVar, g8.y.f(nVar.f22959m, bVarA3, nVar.f22960n), bVarA3, arrayList, aVar);
                }
                if (mVar != null) {
                    b.c(mVar, annotation5, clsC3);
                }
            }
            if (!arrayList.isEmpty()) {
                dVar.f22920b.put(a0Var, arrayList);
            }
        }
    }

    @Override // y8.y
    public final String d() {
        return this.f20063a.getName().replace('.', '/') + ".class";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof e) {
            return p.a(this.f20063a, ((e) obj).f20063a);
        }
        return false;
    }

    @Override // y8.y
    public final f9.b h() {
        return m8.c.a(this.f20063a);
    }

    public final int hashCode() {
        return this.f20063a.hashCode();
    }

    public final String toString() {
        return e.class.getName() + ": " + this.f20063a;
    }
}
