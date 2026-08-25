package com.discord.org.webrtc;

/* JADX INFO: loaded from: classes4.dex */
class FramerateBitrateAdjuster extends BaseBitrateAdjuster {
    private static final int DEFAULT_FRAMERATE_FPS = 30;

    @Override // com.discord.org.webrtc.BaseBitrateAdjuster, com.discord.org.webrtc.BitrateAdjuster
    public void setTargets(int i10, double d4) {
        this.targetFramerateFps = 30.0d;
        this.targetBitrateBps = (int) (((double) (i10 * 30)) / d4);
    }
}
