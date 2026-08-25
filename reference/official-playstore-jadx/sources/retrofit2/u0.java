package retrofit2;

import gb.i;
import j$.util.Objects;
import j$.util.concurrent.ConcurrentHashMap;
import java.lang.annotation.Annotation;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes5.dex */
public final class u0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ConcurrentHashMap f21595a = new ConcurrentHashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final i.a f21596b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final gb.b0 f21597c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List f21598d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List f21599e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Executor f21600f;

    public u0(i.a aVar, gb.b0 b0Var, List list, List list2, Executor executor) {
        this.f21596b = aVar;
        this.f21597c = b0Var;
        this.f21598d = list;
        this.f21599e = list2;
        this.f21600f = executor;
    }

    public final e a(Type type, Annotation[] annotationArr) {
        Objects.requireNonNull(type, "returnType == null");
        Objects.requireNonNull(annotationArr, "annotations == null");
        List list = this.f21599e;
        int iIndexOf = list.indexOf(null) + 1;
        int size = list.size();
        for (int i10 = iIndexOf; i10 < size; i10++) {
            e eVarA = ((d) list.get(i10)).a(type, annotationArr);
            if (eVarA != null) {
                return eVarA;
            }
        }
        StringBuilder sb2 = new StringBuilder("Could not locate call adapter for ");
        sb2.append(type);
        sb2.append(".\n  Tried:");
        int size2 = list.size();
        while (iIndexOf < size2) {
            sb2.append("\n   * ");
            sb2.append(((d) list.get(iIndexOf)).getClass().getName());
            iIndexOf++;
        }
        throw new IllegalArgumentException(sb2.toString());
    }

    public final Object b(Class cls) {
        if (!cls.isInterface()) {
            throw new IllegalArgumentException("API declarations must be interfaces.");
        }
        ArrayDeque arrayDeque = new ArrayDeque(1);
        arrayDeque.add(cls);
        while (!arrayDeque.isEmpty()) {
            Class cls2 = (Class) arrayDeque.removeFirst();
            if (cls2.getTypeParameters().length != 0) {
                StringBuilder sb2 = new StringBuilder("Type parameters are unsupported on ");
                sb2.append(cls2.getName());
                if (cls2 != cls) {
                    sb2.append(" which is an interface of ");
                    sb2.append(cls.getName());
                }
                throw new IllegalArgumentException(sb2.toString());
            }
            Collections.addAll(arrayDeque, cls2.getInterfaces());
        }
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new s0(this, cls));
    }

    public final l c(Type type, Annotation[] annotationArr, Annotation[] annotationArr2) {
        Objects.requireNonNull(type, "type == null");
        Objects.requireNonNull(annotationArr2, "methodAnnotations == null");
        List list = this.f21598d;
        int iIndexOf = list.indexOf(null) + 1;
        int size = list.size();
        for (int i10 = iIndexOf; i10 < size; i10++) {
            l lVarA = ((k) list.get(i10)).a(type);
            if (lVarA != null) {
                return lVarA;
            }
        }
        StringBuilder sb2 = new StringBuilder("Could not locate RequestBody converter for ");
        sb2.append(type);
        sb2.append(".\n  Tried:");
        int size2 = list.size();
        while (iIndexOf < size2) {
            sb2.append("\n   * ");
            sb2.append(((k) list.get(iIndexOf)).getClass().getName());
            iIndexOf++;
        }
        throw new IllegalArgumentException(sb2.toString());
    }

    public final l d(Type type, Annotation[] annotationArr) {
        Objects.requireNonNull(type, "type == null");
        Objects.requireNonNull(annotationArr, "annotations == null");
        List list = this.f21598d;
        int iIndexOf = list.indexOf(null) + 1;
        int size = list.size();
        for (int i10 = iIndexOf; i10 < size; i10++) {
            l lVarB = ((k) list.get(i10)).b(type, annotationArr, this);
            if (lVarB != null) {
                return lVarB;
            }
        }
        StringBuilder sb2 = new StringBuilder("Could not locate ResponseBody converter for ");
        sb2.append(type);
        sb2.append(".\n  Tried:");
        int size2 = list.size();
        while (iIndexOf < size2) {
            sb2.append("\n   * ");
            sb2.append(((k) list.get(iIndexOf)).getClass().getName());
            iIndexOf++;
        }
        throw new IllegalArgumentException(sb2.toString());
    }

    public final void e(Type type, Annotation[] annotationArr) {
        Objects.requireNonNull(type, "type == null");
        List list = this.f21598d;
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            ((k) list.get(i10)).getClass();
        }
    }
}
