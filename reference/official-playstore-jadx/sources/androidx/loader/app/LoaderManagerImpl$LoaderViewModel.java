package androidx.loader.app;

import androidx.lifecycle.d1;
import androidx.lifecycle.g1;
import androidx.lifecycle.h0;
import androidx.lifecycle.h1;
import androidx.lifecycle.i1;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import t.m0;
import t.q;

/* JADX INFO: loaded from: classes3.dex */
class LoaderManagerImpl$LoaderViewModel extends d1 {
    private static final g1 FACTORY = new e();
    private m0 mLoaders = new m0(0);
    private boolean mCreatingLoader = false;

    public static LoaderManagerImpl$LoaderViewModel getInstance(i1 i1Var) {
        return (LoaderManagerImpl$LoaderViewModel) new h1(i1Var, FACTORY, 0).a(LoaderManagerImpl$LoaderViewModel.class);
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        if (this.mLoaders.f() > 0) {
            printWriter.print(str);
            printWriter.println("Loaders:");
            String str2 = str + "    ";
            for (int i10 = 0; i10 < this.mLoaders.f(); i10++) {
                c cVar = (c) this.mLoaders.g(i10);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(this.mLoaders.d(i10));
                printWriter.print(": ");
                printWriter.println(cVar.toString());
                printWriter.print(str2);
                printWriter.print("mId=");
                printWriter.print(0);
                printWriter.print(" mArgs=");
                printWriter.println((Object) null);
                printWriter.print(str2);
                printWriter.print("mLoader=");
                printWriter.println(cVar.f3319l);
                cVar.f3319l.dump(androidx.compose.material3.d.m(str2, "  "), fileDescriptor, printWriter, strArr);
                if (cVar.f3321n != null) {
                    printWriter.print(str2);
                    printWriter.print("mCallbacks=");
                    printWriter.println(cVar.f3321n);
                    d dVar = cVar.f3321n;
                    dVar.getClass();
                    printWriter.print(str2 + "  ");
                    printWriter.print("mDeliveredData=");
                    printWriter.println(dVar.f3323l);
                }
                printWriter.print(str2);
                printWriter.print("mData=");
                androidx.loader.content.e eVar = cVar.f3319l;
                Object obj = cVar.f3243e;
                printWriter.println(eVar.dataToString(obj != h0.k ? obj : null));
                printWriter.print(str2);
                printWriter.print("mStarted=");
                printWriter.println(cVar.f3241c > 0);
            }
        }
    }

    public void finishCreatingLoader() {
        this.mCreatingLoader = false;
    }

    public <D> c getLoader(int i10) {
        return (c) this.mLoaders.c(i10);
    }

    public boolean hasRunningLoaders() {
        d dVar;
        int iF = this.mLoaders.f();
        for (int i10 = 0; i10 < iF; i10++) {
            c cVar = (c) this.mLoaders.g(i10);
            if (cVar.f3241c > 0 && (dVar = cVar.f3321n) != null && !dVar.f3323l) {
                return true;
            }
        }
        return false;
    }

    public boolean isCreatingLoader() {
        return this.mCreatingLoader;
    }

    public void markForRedelivery() {
        int iF = this.mLoaders.f();
        for (int i10 = 0; i10 < iF; i10++) {
            ((c) this.mLoaders.g(i10)).k();
        }
    }

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
    @Override // androidx.lifecycle.d1
    public void onCleared() {
        super.onCleared();
        int iF = this.mLoaders.f();
        for (int i10 = 0; i10 < iF; i10++) {
            c cVar = (c) this.mLoaders.g(i10);
            androidx.loader.content.e eVar = cVar.f3319l;
            eVar.cancelLoad();
            eVar.abandon();
            d dVar = cVar.f3321n;
            if (dVar != null) {
                cVar.h(dVar);
                if (dVar.f3323l) {
                    ((a) dVar.f3325n).onLoaderReset((androidx.loader.content.e) dVar.f3324m);
                }
            }
            eVar.unregisterListener(cVar);
            if (dVar != null) {
                boolean z = dVar.f3323l;
            }
            eVar.reset();
        }
        m0 m0Var = this.mLoaders;
        int i11 = m0Var.f21854n;
        Object[] objArr = m0Var.f21853m;
        for (int i12 = 0; i12 < i11; i12++) {
            objArr[i12] = null;
        }
        m0Var.f21854n = 0;
        m0Var.f21851i = false;
    }

    public void putLoader(int i10, c cVar) {
        this.mLoaders.e(i10, cVar);
    }

    public void removeLoader(int i10) {
        m0 m0Var = this.mLoaders;
        int iA = u.a.a(m0Var.f21854n, i10, m0Var.f21852l);
        if (iA >= 0) {
            Object[] objArr = m0Var.f21853m;
            Object obj = objArr[iA];
            Object obj2 = q.f21862b;
            if (obj != obj2) {
                objArr[iA] = obj2;
                m0Var.f21851i = true;
            }
        }
    }

    public void startCreatingLoader() {
        this.mCreatingLoader = true;
    }
}
