package androidx.media3.extractor.text.ttml;

/* JADX INFO: loaded from: classes3.dex */
final class TtmlRegion {
    public final float height;
    public final String id;
    public final float line;
    public final int lineAnchor;
    public final int lineType;
    public final float position;
    public final float textSize;
    public final int textSizeType;
    public final int verticalType;
    public final float width;

    public TtmlRegion(String str) {
        this(str, -3.4028235E38f, -3.4028235E38f, Integer.MIN_VALUE, Integer.MIN_VALUE, -3.4028235E38f, -3.4028235E38f, Integer.MIN_VALUE, -3.4028235E38f, Integer.MIN_VALUE);
    }

    public TtmlRegion(String str, float f10, float f11, int i10, int i11, float f12, float f13, int i12, float f14, int i13) {
        this.id = str;
        this.position = f10;
        this.line = f11;
        this.lineType = i10;
        this.lineAnchor = i11;
        this.width = f12;
        this.height = f13;
        this.textSizeType = i12;
        this.textSize = f14;
        this.verticalType = i13;
    }
}
