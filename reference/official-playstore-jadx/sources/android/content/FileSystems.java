package android.content;

import java.io.FileNotFoundException;
import java.io.IOException;
import kotlin.Metadata;
import xb.f0;
import xb.s;

/* JADX INFO: renamed from: coil.util.-FileSystems, reason: invalid class name */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u001b\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001b\u0010\u0007\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0007\u0010\u0005¨\u0006\b"}, d2 = {"Lxb/s;", "Lxb/f0;", "file", "Lx6/t0;", "createFile", "(Lxb/s;Lxb/f0;)V", "directory", "deleteContents", "coil-base_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class FileSystems {
    public static final void createFile(s sVar, f0 f0Var) {
        if (sVar.exists(f0Var)) {
            return;
        }
        Utils.closeQuietly(sVar.sink(f0Var));
    }

    public static final void deleteContents(s sVar, f0 f0Var) throws IOException {
        try {
            IOException iOException = null;
            for (f0 f0Var2 : sVar.list(f0Var)) {
                try {
                    if (sVar.metadata(f0Var2).f22784b) {
                        deleteContents(sVar, f0Var2);
                    }
                    sVar.delete(f0Var2);
                } catch (IOException e5) {
                    if (iOException == null) {
                        iOException = e5;
                    }
                }
            }
            if (iOException != null) {
                throw iOException;
            }
        } catch (FileNotFoundException unused) {
        }
    }
}
