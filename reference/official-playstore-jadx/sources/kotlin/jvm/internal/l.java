package kotlin.jvm.internal;

import a8.x1;

/* JADX INFO: loaded from: classes5.dex */
public abstract class l extends e implements k, kotlin.reflect.g {
    private final int arity;

    public l(int i10) {
        this(i10, 0, null, e.NO_RECEIVER, null, null);
    }

    @Override // kotlin.jvm.internal.e
    public kotlin.reflect.c computeReflected() {
        return l0.f19747a.a(this);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof l) {
            l lVar = (l) obj;
            return getName().equals(lVar.getName()) && getSignature().equals(lVar.getSignature()) && p.a(getBoundReceiver(), lVar.getBoundReceiver()) && p.a(getOwner(), lVar.getOwner());
        }
        if (obj instanceof kotlin.reflect.g) {
            return obj.equals(compute());
        }
        return false;
    }

    @Override // kotlin.jvm.internal.k
    public int getArity() {
        return this.arity;
    }

    public int hashCode() {
        return getSignature().hashCode() + ((getName().hashCode() + (getOwner() == null ? 0 : getOwner().hashCode() * 31)) * 31);
    }

    @Override // kotlin.reflect.g
    public boolean isExternal() {
        return getReflected().isExternal();
    }

    @Override // kotlin.reflect.g
    public boolean isInfix() {
        return getReflected().isInfix();
    }

    @Override // kotlin.reflect.g
    public boolean isInline() {
        return getReflected().isInline();
    }

    @Override // kotlin.reflect.g
    public boolean isOperator() {
        return getReflected().isOperator();
    }

    @Override // kotlin.jvm.internal.e, kotlin.reflect.c, kotlin.reflect.g
    public boolean isSuspend() {
        return getReflected().isSuspend();
    }

    public String toString() {
        kotlin.reflect.c cVarCompute = compute();
        if (cVarCompute != this) {
            return cVarCompute.toString();
        }
        if ("<init>".equals(getName())) {
            return "constructor (Kotlin reflection is not available)";
        }
        return "function " + getName() + " (Kotlin reflection is not available)";
    }

    public l(int i10, Object obj) {
        this(i10, 0, null, obj, null, null);
    }

    @Override // kotlin.jvm.internal.e
    public kotlin.reflect.g getReflected() {
        kotlin.reflect.c cVarCompute = compute();
        if (cVarCompute != this) {
            return (kotlin.reflect.g) cVarCompute;
        }
        throw new x1();
    }

    public l(int i10, int i11, Class cls, Object obj, String str, String str2) {
        super(obj, cls, str, str2, (i11 & 1) == 1);
        this.arity = i10;
    }
}
