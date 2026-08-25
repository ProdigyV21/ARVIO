package android.graphics;

import android.graphics.ImageSource;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\r"}, d2 = {"Lcoil/decode/ResourceMetadata;", "Lcoil/decode/ImageSource$Metadata;", "packageName", "", "resId", "", "density", "(Ljava/lang/String;II)V", "getDensity", "()I", "getPackageName", "()Ljava/lang/String;", "getResId", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ResourceMetadata extends ImageSource.Metadata {
    private final int density;
    private final String packageName;
    private final int resId;

    public ResourceMetadata(String str, int i10, int i11) {
        this.packageName = str;
        this.resId = i10;
        this.density = i11;
    }

    public final int getDensity() {
        return this.density;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final int getResId() {
        return this.resId;
    }
}
