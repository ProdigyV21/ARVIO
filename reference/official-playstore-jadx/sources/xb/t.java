package xb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public abstract class t extends s {
    private final s delegate;

    public t(s sVar) {
        this.delegate = sVar;
    }

    @Override // xb.s
    public m0 appendingSink(f0 f0Var, boolean z) throws IOException {
        return this.delegate.appendingSink(onPathParameter(f0Var, "appendingSink", "file"), z);
    }

    @Override // xb.s
    public void atomicMove(f0 f0Var, f0 f0Var2) throws IOException {
        this.delegate.atomicMove(onPathParameter(f0Var, "atomicMove", "source"), onPathParameter(f0Var2, "atomicMove", "target"));
    }

    @Override // xb.s
    public f0 canonicalize(f0 f0Var) throws IOException {
        return onPathResult(this.delegate.canonicalize(onPathParameter(f0Var, "canonicalize", "path")), "canonicalize");
    }

    @Override // xb.s
    public void createDirectory(f0 f0Var, boolean z) throws IOException {
        this.delegate.createDirectory(onPathParameter(f0Var, "createDirectory", "dir"), z);
    }

    @Override // xb.s
    public void createSymlink(f0 f0Var, f0 f0Var2) throws IOException {
        this.delegate.createSymlink(onPathParameter(f0Var, "createSymlink", "source"), onPathParameter(f0Var2, "createSymlink", "target"));
    }

    public final s delegate() {
        return this.delegate;
    }

    @Override // xb.s
    public void delete(f0 f0Var, boolean z) throws IOException {
        this.delegate.delete(onPathParameter(f0Var, "delete", "path"), z);
    }

    @Override // xb.s
    public List<f0> list(f0 f0Var) throws IOException {
        List list = this.delegate.list(onPathParameter(f0Var, "list", "dir"));
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(onPathResult((f0) it.next(), "list"));
        }
        kotlin.collections.w.W(arrayList);
        return arrayList;
    }

    @Override // xb.s
    public List<f0> listOrNull(f0 f0Var) {
        List listListOrNull = this.delegate.listOrNull(onPathParameter(f0Var, "listOrNull", "dir"));
        if (listListOrNull == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = listListOrNull.iterator();
        while (it.hasNext()) {
            arrayList.add(onPathResult((f0) it.next(), "listOrNull"));
        }
        kotlin.collections.w.W(arrayList);
        return arrayList;
    }

    @Override // xb.s
    public ga.m<f0> listRecursively(f0 f0Var, boolean z) {
        return new ga.t(this.delegate.listRecursively(onPathParameter(f0Var, "listRecursively", "dir"), z), new s8.d(this, 15));
    }

    @Override // xb.s
    public q metadataOrNull(f0 f0Var) throws IOException {
        q qVarMetadataOrNull = this.delegate.metadataOrNull(onPathParameter(f0Var, "metadataOrNull", "path"));
        if (qVarMetadataOrNull == null) {
            return null;
        }
        f0 f0Var2 = qVarMetadataOrNull.f22785c;
        if (f0Var2 == null) {
            return qVarMetadataOrNull;
        }
        return new q(qVarMetadataOrNull.f22783a, qVarMetadataOrNull.f22784b, onPathResult(f0Var2, "metadataOrNull"), qVarMetadataOrNull.f22786d, qVarMetadataOrNull.f22787e, qVarMetadataOrNull.f22788f, qVarMetadataOrNull.f22789g, qVarMetadataOrNull.f22790h);
    }

    @Override // xb.s
    public p openReadOnly(f0 f0Var) throws IOException {
        return this.delegate.openReadOnly(onPathParameter(f0Var, "openReadOnly", "file"));
    }

    @Override // xb.s
    public p openReadWrite(f0 f0Var, boolean z, boolean z5) throws IOException {
        return this.delegate.openReadWrite(onPathParameter(f0Var, "openReadWrite", "file"), z, z5);
    }

    @Override // xb.s
    public m0 sink(f0 f0Var, boolean z) {
        return this.delegate.sink(onPathParameter(f0Var, "sink", "file"), z);
    }

    @Override // xb.s
    public o0 source(f0 f0Var) throws IOException {
        return this.delegate.source(onPathParameter(f0Var, "source", "file"));
    }

    public String toString() {
        return kotlin.jvm.internal.l0.f19747a.b(getClass()).r() + '(' + this.delegate + ')';
    }

    public f0 onPathResult(f0 f0Var, String str) {
        return f0Var;
    }

    public f0 onPathParameter(f0 f0Var, String str, String str2) {
        return f0Var;
    }
}
