package android.graphics;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljava/io/File;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
public final class ImageSources$ImageSource$3 extends r implements a<File> {
    final /* synthetic */ File $cacheDirectory;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImageSources$ImageSource$3(File file) {
        super(0);
        this.$cacheDirectory = file;
    }

    @Override // r7.a
    public final File invoke() {
        return this.$cacheDirectory;
    }
}
