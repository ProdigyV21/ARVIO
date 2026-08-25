package kotlin.jvm.internal;

import a8.x1;

/* JADX INFO: loaded from: classes5.dex */
public abstract class e0 extends e implements kotlin.reflect.m {
    private final boolean syntheticJavaProperty;

    public e0(Object obj, Class cls, String str, String str2, int i10) {
        super(obj, cls, str, str2, (i10 & 1) == 1);
        this.syntheticJavaProperty = (i10 & 2) == 2;
    }

    @Override // kotlin.jvm.internal.e
    public kotlin.reflect.c compute() {
        return this.syntheticJavaProperty ? this : super.compute();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof e0) {
            e0 e0Var = (e0) obj;
            return getOwner().equals(e0Var.getOwner()) && getName().equals(e0Var.getName()) && getSignature().equals(e0Var.getSignature()) && p.a(getBoundReceiver(), e0Var.getBoundReceiver());
        }
        if (obj instanceof kotlin.reflect.m) {
            return obj.equals(compute());
        }
        return false;
    }

    public int hashCode() {
        return getSignature().hashCode() + ((getName().hashCode() + (getOwner().hashCode() * 31)) * 31);
    }

    @Override // kotlin.reflect.m
    public boolean isConst() {
        return getReflected().isConst();
    }

    @Override // kotlin.reflect.m
    public boolean isLateinit() {
        return getReflected().isLateinit();
    }

    public String toString() {
        kotlin.reflect.c cVarCompute = compute();
        if (cVarCompute != this) {
            return cVarCompute.toString();
        }
        return "property " + getName() + " (Kotlin reflection is not available)";
    }

    @Override // kotlin.jvm.internal.e
    public kotlin.reflect.m getReflected() {
        if (this.syntheticJavaProperty) {
            throw new UnsupportedOperationException("Kotlin reflection is not yet supported for synthetic Java properties. Please follow/upvote https://youtrack.jetbrains.com/issue/KT-55980");
        }
        kotlin.reflect.c cVarCompute = compute();
        if (cVarCompute != this) {
            return (kotlin.reflect.m) cVarCompute;
        }
        throw new x1();
    }
}
