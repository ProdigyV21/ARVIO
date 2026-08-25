package coil.disk;

import java.io.IOException;
import kotlin.Metadata;
import xb.f0;
import xb.m0;
import xb.s;
import xb.t;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"coil/disk/DiskLruCache$fileSystem$1", "Lxb/t;", "Lxb/f0;", "file", "", "mustCreate", "Lxb/m0;", "sink", "(Lxb/f0;Z)Lxb/m0;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class DiskLruCache$fileSystem$1 extends t {
    public DiskLruCache$fileSystem$1(s sVar) {
        super(sVar);
    }

    @Override // xb.t, xb.s
    public m0 sink(f0 file, boolean mustCreate) throws IOException {
        f0 f0VarD = file.d();
        if (f0VarD != null) {
            createDirectories(f0VarD);
        }
        return super.sink(file, mustCreate);
    }
}
