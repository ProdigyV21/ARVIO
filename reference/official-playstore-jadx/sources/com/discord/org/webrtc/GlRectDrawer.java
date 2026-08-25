package com.discord.org.webrtc;

import com.discord.org.webrtc.GlGenericDrawer;

/* JADX INFO: loaded from: classes4.dex */
public class GlRectDrawer extends GlGenericDrawer {
    private static final String FRAGMENT_SHADER = "void main() {\n  gl_FragColor = sample(tc);\n}\n";

    public static class ShaderCallbacks implements GlGenericDrawer.ShaderCallbacks {
        public /* synthetic */ ShaderCallbacks(int i10) {
            this();
        }

        @Override // com.discord.org.webrtc.GlGenericDrawer.ShaderCallbacks
        public void onNewShader(GlShader glShader) {
        }

        @Override // com.discord.org.webrtc.GlGenericDrawer.ShaderCallbacks
        public void onPrepareShader(GlShader glShader, float[] fArr, int i10, int i11, int i12, int i13) {
        }

        private ShaderCallbacks() {
        }
    }

    public GlRectDrawer() {
        super(FRAGMENT_SHADER, new ShaderCallbacks(0));
    }

    @Override // com.discord.org.webrtc.GlGenericDrawer, com.discord.org.webrtc.RendererCommon.GlDrawer
    public /* bridge */ /* synthetic */ void drawOes(int i10, float[] fArr, int i11, int i12, int i13, int i14, int i15, int i16) {
        super.drawOes(i10, fArr, i11, i12, i13, i14, i15, i16);
    }

    @Override // com.discord.org.webrtc.GlGenericDrawer, com.discord.org.webrtc.RendererCommon.GlDrawer
    public /* bridge */ /* synthetic */ void drawRgb(int i10, float[] fArr, int i11, int i12, int i13, int i14, int i15, int i16) {
        super.drawRgb(i10, fArr, i11, i12, i13, i14, i15, i16);
    }

    @Override // com.discord.org.webrtc.GlGenericDrawer, com.discord.org.webrtc.RendererCommon.GlDrawer
    public /* bridge */ /* synthetic */ void drawYuv(int[] iArr, float[] fArr, int i10, int i11, int i12, int i13, int i14, int i15) {
        super.drawYuv(iArr, fArr, i10, i11, i12, i13, i14, i15);
    }

    @Override // com.discord.org.webrtc.GlGenericDrawer, com.discord.org.webrtc.RendererCommon.GlDrawer
    public /* bridge */ /* synthetic */ void release() {
        super.release();
    }
}
