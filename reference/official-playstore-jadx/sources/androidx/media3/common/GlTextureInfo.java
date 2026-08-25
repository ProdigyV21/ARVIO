package androidx.media3.common;

import androidx.media3.common.util.GlUtil;

/* JADX INFO: loaded from: classes3.dex */
public final class GlTextureInfo {
    public static final GlTextureInfo UNSET = new GlTextureInfo(-1, -1, -1, -1, -1);
    public final int fboId;
    public final int height;
    public final int rboId;
    public final int texId;
    public final int width;

    public GlTextureInfo(int i10, int i11, int i12, int i13, int i14) {
        this.texId = i10;
        this.fboId = i11;
        this.rboId = i12;
        this.width = i13;
        this.height = i14;
    }

    public void release() throws GlUtil.GlException {
        int i10 = this.texId;
        if (i10 != -1) {
            GlUtil.deleteTexture(i10);
        }
        int i11 = this.fboId;
        if (i11 != -1) {
            GlUtil.deleteFbo(i11);
        }
        int i12 = this.rboId;
        if (i12 != -1) {
            GlUtil.deleteRbo(i12);
        }
    }
}
