package com.discord.org.webrtc;

/* JADX INFO: loaded from: classes4.dex */
class BaseBitrateAdjuster implements BitrateAdjuster {
    protected int targetBitrateBps;
    protected double targetFramerateFps;

    @Override // com.discord.org.webrtc.BitrateAdjuster
    public int getAdjustedBitrateBps() {
        return this.targetBitrateBps;
    }

    @Override // com.discord.org.webrtc.BitrateAdjuster
    public double getAdjustedFramerateFps() {
        return this.targetFramerateFps;
    }

    @Override // com.discord.org.webrtc.BitrateAdjuster
    public void reportEncodedFrame(int i10) {
    }

    @Override // com.discord.org.webrtc.BitrateAdjuster
    public void setTargets(int i10, double d4) {
        this.targetBitrateBps = i10;
        this.targetFramerateFps = d4;
    }
}
