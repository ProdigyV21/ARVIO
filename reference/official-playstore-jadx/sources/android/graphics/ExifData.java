package android.graphics;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0000\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000b"}, d2 = {"Lcoil/decode/ExifData;", "", "isFlipped", "", "rotationDegrees", "", "(ZI)V", "()Z", "getRotationDegrees", "()I", "Companion", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ExifData {
    public static final ExifData NONE = new ExifData(false, 0);
    private final boolean isFlipped;
    private final int rotationDegrees;

    public ExifData(boolean z, int i10) {
        this.isFlipped = z;
        this.rotationDegrees = i10;
    }

    public final int getRotationDegrees() {
        return this.rotationDegrees;
    }

    /* JADX INFO: renamed from: isFlipped, reason: from getter */
    public final boolean getIsFlipped() {
        return this.isFlipped;
    }
}
