package android.graphics;

import android.graphics.ImageSource;
import kotlin.Metadata;
import kotlin.text.o;
import x6.e;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u000b"}, d2 = {"Lcoil/decode/AssetMetadata;", "Lcoil/decode/ImageSource$Metadata;", "filePath", "", "(Ljava/lang/String;)V", "fileName", "getFileName$annotations", "()V", "getFileName", "()Ljava/lang/String;", "getFilePath", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class AssetMetadata extends ImageSource.Metadata {
    private final String filePath;

    public AssetMetadata(String str) {
        this.filePath = str;
    }

    @e
    public static /* synthetic */ void getFileName$annotations() {
    }

    public final String getFileName() {
        String str = this.filePath;
        return o.E0('/', str, str);
    }

    public final String getFilePath() {
        return this.filePath;
    }
}
