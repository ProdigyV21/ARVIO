package vb;

import java.io.Closeable;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import xb.y;

/* JADX INFO: loaded from: classes5.dex */
public final class a implements Closeable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f22265i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final boolean f22266l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final xb.j f22267m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f22268n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final Closeable f22269o;

    public a(boolean z, int i10) {
        this.f22265i = i10;
        switch (i10) {
            case 1:
                this.f22266l = z;
                xb.j jVar = new xb.j();
                this.f22267m = jVar;
                Inflater inflater = new Inflater(true);
                this.f22268n = inflater;
                this.f22269o = new y(jVar, inflater);
                break;
            default:
                this.f22266l = z;
                xb.j jVar2 = new xb.j();
                this.f22267m = jVar2;
                Deflater deflater = new Deflater(-1, true);
                this.f22268n = deflater;
                this.f22269o = new nb.e(jVar2, deflater);
                break;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws Throwable {
        switch (this.f22265i) {
            case 0:
                ((nb.e) this.f22269o).close();
                break;
            default:
                ((y) this.f22269o).close();
                break;
        }
    }
}
