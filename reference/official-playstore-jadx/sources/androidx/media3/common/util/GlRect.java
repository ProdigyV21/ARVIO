package androidx.media3.common.util;

/* JADX INFO: loaded from: classes3.dex */
public final class GlRect {
    public int bottom;
    public int left;
    public int right;
    public int top;

    public GlRect(int i10, int i11) {
        this(0, 0, i10, i11);
    }

    public GlRect(int i10, int i11, int i12, int i13) {
        ac.b.j(i10 <= i12 && i11 <= i13);
        this.left = i10;
        this.bottom = i11;
        this.right = i12;
        this.top = i13;
    }
}
