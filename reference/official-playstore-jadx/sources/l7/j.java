package l7;

import ga.p;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes5.dex */
public final class j implements Iterator, s7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f20058i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f20059l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ p f20060m;

    public j(p pVar) {
        this.f20060m = pVar;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() throws IOException {
        if (this.f20058i == null && !this.f20059l) {
            String line = ((BufferedReader) this.f20060m.f15598b).readLine();
            this.f20058i = line;
            if (line == null) {
                this.f20059l = true;
            }
        }
        return this.f20058i != null;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        String str = this.f20058i;
        this.f20058i = null;
        return str;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
