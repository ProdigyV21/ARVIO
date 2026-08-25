package l7;

import h.f0;
import java.io.File;
import java.util.ArrayDeque;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes5.dex */
public final class f extends kotlin.collections.b {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayDeque f20053m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ h f20054n;

    public f(h hVar) {
        this.f20054n = hVar;
        ArrayDeque arrayDeque = new ArrayDeque();
        this.f20053m = arrayDeque;
        File file = hVar.f20056a;
        if (file.isDirectory()) {
            arrayDeque.push(b(file));
        } else if (file.isFile()) {
            arrayDeque.push(new d(file));
        } else {
            this.f19684i = 2;
        }
    }

    @Override // kotlin.collections.b
    public final void a() {
        File file;
        File fileA;
        while (true) {
            ArrayDeque arrayDeque = this.f20053m;
            g gVar = (g) arrayDeque.peek();
            if (gVar == null) {
                file = null;
                break;
            }
            fileA = gVar.a();
            if (fileA == null) {
                arrayDeque.pop();
            } else if (fileA.equals(gVar.f20055a) || !fileA.isDirectory() || arrayDeque.size() >= Integer.MAX_VALUE) {
                break;
            } else {
                arrayDeque.push(b(fileA));
            }
        }
        file = fileA;
        if (file == null) {
            this.f19684i = 2;
        } else {
            this.f19685l = file;
            this.f19684i = 1;
        }
    }

    public final b b(File file) {
        int iC = f0.c(this.f20054n.f20057b);
        if (iC == 0) {
            return new e(file);
        }
        if (iC == 1) {
            return new c(file);
        }
        throw new NoWhenBranchMatchedException();
    }
}
