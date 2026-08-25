package androidx.loader.app;

import android.os.Looper;
import androidx.lifecycle.i1;
import androidx.lifecycle.y;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import p0.g;

/* JADX INFO: loaded from: classes3.dex */
public final class f extends b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f3326a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LoaderManagerImpl$LoaderViewModel f3327b;

    public f(y yVar, i1 i1Var) {
        this.f3326a = yVar;
        this.f3327b = LoaderManagerImpl$LoaderViewModel.getInstance(i1Var);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [androidx.lifecycle.y, java.lang.Object] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // androidx.loader.app.b
    public final androidx.loader.content.e b(a aVar) {
        LoaderManagerImpl$LoaderViewModel loaderManagerImpl$LoaderViewModel = this.f3327b;
        if (loaderManagerImpl$LoaderViewModel.isCreatingLoader()) {
            throw new IllegalStateException("Called while creating a loader");
        }
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException("initLoader must be called on the main thread");
        }
        c loader = loaderManagerImpl$LoaderViewModel.getLoader(0);
        ?? r32 = this.f3326a;
        if (loader != 0) {
            androidx.loader.content.e eVar = loader.f3319l;
            d dVar = new d(eVar, aVar);
            loader.e(r32, dVar);
            d dVar2 = loader.f3321n;
            if (dVar2 != null) {
                loader.h(dVar2);
            }
            loader.f3320m = r32;
            loader.f3321n = dVar;
            return eVar;
        }
        try {
            loaderManagerImpl$LoaderViewModel.startCreatingLoader();
            androidx.loader.content.e eVarOnCreateLoader = aVar.onCreateLoader(0, null);
            if (eVarOnCreateLoader == null) {
                throw new IllegalArgumentException("Object returned from onCreateLoader must not be null");
            }
            if (eVarOnCreateLoader.getClass().isMemberClass() && !Modifier.isStatic(eVarOnCreateLoader.getClass().getModifiers())) {
                throw new IllegalArgumentException("Object returned from onCreateLoader must not be a non-static inner member class: " + eVarOnCreateLoader);
            }
            c cVar = new c(eVarOnCreateLoader);
            loaderManagerImpl$LoaderViewModel.putLoader(0, cVar);
            loaderManagerImpl$LoaderViewModel.finishCreatingLoader();
            androidx.loader.content.e eVar2 = cVar.f3319l;
            d dVar3 = new d(eVar2, aVar);
            cVar.e(r32, dVar3);
            d dVar4 = cVar.f3321n;
            if (dVar4 != null) {
                cVar.h(dVar4);
            }
            cVar.f3320m = r32;
            cVar.f3321n = dVar3;
            return eVar2;
        } catch (Throwable th) {
            loaderManagerImpl$LoaderViewModel.finishCreatingLoader();
            throw th;
        }
    }

    public final void c(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.f3327b.dump(str, fileDescriptor, printWriter, strArr);
    }

    public final void d() {
        this.f3327b.markForRedelivery();
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder(128);
        sb2.append("LoaderManager{");
        sb2.append(Integer.toHexString(System.identityHashCode(this)));
        sb2.append(" in ");
        g.a(sb2, this.f3326a);
        sb2.append("}}");
        return sb2.toString();
    }
}
