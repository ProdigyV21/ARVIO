package com.discord.org.webrtc;

/* JADX INFO: loaded from: classes4.dex */
public final class CryptoOptions {
    private final SFrame sframe;
    private final Srtp srtp;

    public static class Builder {
        private boolean enableAes128Sha1_32CryptoCipher;
        private boolean enableEncryptedRtpHeaderExtensions;
        private boolean enableGcmCryptoSuites;
        private boolean requireFrameEncryption;

        public /* synthetic */ Builder(int i10) {
            this();
        }

        public CryptoOptions createCryptoOptions() {
            return new CryptoOptions(0, this.enableGcmCryptoSuites, this.enableAes128Sha1_32CryptoCipher, this.enableEncryptedRtpHeaderExtensions, this.requireFrameEncryption);
        }

        public Builder setEnableAes128Sha1_32CryptoCipher(boolean z) {
            this.enableAes128Sha1_32CryptoCipher = z;
            return this;
        }

        public Builder setEnableEncryptedRtpHeaderExtensions(boolean z) {
            this.enableEncryptedRtpHeaderExtensions = z;
            return this;
        }

        public Builder setEnableGcmCryptoSuites(boolean z) {
            this.enableGcmCryptoSuites = z;
            return this;
        }

        public Builder setRequireFrameEncryption(boolean z) {
            this.requireFrameEncryption = z;
            return this;
        }

        private Builder() {
        }
    }

    public final class SFrame {
        private final boolean requireFrameEncryption;

        public /* synthetic */ SFrame(CryptoOptions cryptoOptions, boolean z, int i10) {
            this(z);
        }

        public boolean getRequireFrameEncryption() {
            return this.requireFrameEncryption;
        }

        private SFrame(boolean z) {
            this.requireFrameEncryption = z;
        }
    }

    public final class Srtp {
        private final boolean enableAes128Sha1_32CryptoCipher;
        private final boolean enableEncryptedRtpHeaderExtensions;
        private final boolean enableGcmCryptoSuites;

        public /* synthetic */ Srtp(CryptoOptions cryptoOptions, boolean z, boolean z5, boolean z10, int i10) {
            this(z, z5, z10);
        }

        public boolean getEnableAes128Sha1_32CryptoCipher() {
            return this.enableAes128Sha1_32CryptoCipher;
        }

        public boolean getEnableEncryptedRtpHeaderExtensions() {
            return this.enableEncryptedRtpHeaderExtensions;
        }

        public boolean getEnableGcmCryptoSuites() {
            return this.enableGcmCryptoSuites;
        }

        private Srtp(boolean z, boolean z5, boolean z10) {
            this.enableGcmCryptoSuites = z;
            this.enableAes128Sha1_32CryptoCipher = z5;
            this.enableEncryptedRtpHeaderExtensions = z10;
        }
    }

    public /* synthetic */ CryptoOptions(int i10, boolean z, boolean z5, boolean z10, boolean z11) {
        this(z, z5, z10, z11);
    }

    public static Builder builder() {
        return new Builder(0);
    }

    public SFrame getSFrame() {
        return this.sframe;
    }

    public Srtp getSrtp() {
        return this.srtp;
    }

    private CryptoOptions(boolean z, boolean z5, boolean z10, boolean z11) {
        this.srtp = new Srtp(this, z, z5, z10, 0);
        this.sframe = new SFrame(this, z11, 0);
    }
}
