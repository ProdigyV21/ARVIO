package gb;

import java.io.IOException;

/* JADX INFO: loaded from: classes5.dex */
public final class c extends xb.v {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f15627i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ s0 f15628l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(retrofit2.w wVar, xb.l lVar) {
        super(lVar);
        this.f15628l = wVar;
    }

    @Override // xb.v, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        switch (this.f15627i) {
            case 0:
                ((d) this.f15628l).f15629l.close();
                super.close();
                break;
            default:
                super.close();
                break;
        }
    }

    @Override // xb.v, xb.o0
    public long read(xb.j jVar, long j10) throws IOException {
        switch (this.f15627i) {
            case 1:
                try {
                    return super.read(jVar, j10);
                } catch (IOException e5) {
                    ((retrofit2.w) this.f15628l).f21605n = e5;
                    throw e5;
                }
            default:
                return super.read(jVar, j10);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(xb.o0 o0Var, d dVar) {
        super(o0Var);
        this.f15628l = dVar;
    }
}
