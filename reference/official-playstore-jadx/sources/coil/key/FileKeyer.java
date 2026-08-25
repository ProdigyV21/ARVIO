package coil.key;

import coil.request.Options;
import java.io.File;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcoil/key/FileKeyer;", "Lcoil/key/Keyer;", "Ljava/io/File;", "addLastModifiedToFileCacheKey", "", "(Z)V", "key", "", "data", "options", "Lcoil/request/Options;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class FileKeyer implements Keyer<File> {
    private final boolean addLastModifiedToFileCacheKey;

    public FileKeyer(boolean z) {
        this.addLastModifiedToFileCacheKey = z;
    }

    @Override // coil.key.Keyer
    public String key(File data, Options options) {
        if (!this.addLastModifiedToFileCacheKey) {
            return data.getPath();
        }
        return data.getPath() + ':' + data.lastModified();
    }
}
