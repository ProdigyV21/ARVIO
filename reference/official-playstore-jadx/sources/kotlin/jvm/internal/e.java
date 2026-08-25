package kotlin.jvm.internal;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public abstract class e implements kotlin.reflect.c, Serializable {
    public static final Object NO_RECEIVER = d.f19737i;
    private final boolean isTopLevel;
    private final String name;
    private final Class owner;
    protected final Object receiver;
    private transient kotlin.reflect.c reflected;
    private final String signature;

    public e(Object obj, Class cls, String str, String str2, boolean z) {
        this.receiver = obj;
        this.owner = cls;
        this.name = str;
        this.signature = str2;
        this.isTopLevel = z;
    }

    @Override // kotlin.reflect.c
    public Object call(Object... objArr) {
        return getReflected().call(objArr);
    }

    @Override // kotlin.reflect.c
    public Object callBy(Map map) {
        return getReflected().callBy(map);
    }

    public kotlin.reflect.c compute() {
        kotlin.reflect.c cVar = this.reflected;
        if (cVar != null) {
            return cVar;
        }
        kotlin.reflect.c cVarComputeReflected = computeReflected();
        this.reflected = cVarComputeReflected;
        return cVarComputeReflected;
    }

    public abstract kotlin.reflect.c computeReflected();

    @Override // kotlin.reflect.b
    public List<Annotation> getAnnotations() {
        return getReflected().getAnnotations();
    }

    public Object getBoundReceiver() {
        return this.receiver;
    }

    @Override // kotlin.reflect.c
    public String getName() {
        return this.name;
    }

    public kotlin.reflect.f getOwner() {
        Class cls = this.owner;
        if (cls == null) {
            return null;
        }
        return this.isTopLevel ? l0.f19747a.c(cls, "") : l0.f19747a.b(cls);
    }

    @Override // kotlin.reflect.c
    public List<kotlin.reflect.l> getParameters() {
        return getReflected().getParameters();
    }

    public abstract kotlin.reflect.c getReflected();

    @Override // kotlin.reflect.c
    public kotlin.reflect.q getReturnType() {
        return getReflected().getReturnType();
    }

    public String getSignature() {
        return this.signature;
    }

    @Override // kotlin.reflect.c
    public List<kotlin.reflect.r> getTypeParameters() {
        return getReflected().getTypeParameters();
    }

    @Override // kotlin.reflect.c
    public kotlin.reflect.u getVisibility() {
        return getReflected().getVisibility();
    }

    @Override // kotlin.reflect.c
    public boolean isAbstract() {
        return getReflected().isAbstract();
    }

    @Override // kotlin.reflect.c
    public boolean isFinal() {
        return getReflected().isFinal();
    }

    @Override // kotlin.reflect.c
    public boolean isOpen() {
        return getReflected().isOpen();
    }

    @Override // kotlin.reflect.c, kotlin.reflect.g
    public boolean isSuspend() {
        return getReflected().isSuspend();
    }
}
