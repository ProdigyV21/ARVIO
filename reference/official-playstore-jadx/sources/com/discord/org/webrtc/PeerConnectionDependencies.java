package com.discord.org.webrtc;

import com.discord.org.webrtc.PeerConnection;

/* JADX INFO: loaded from: classes4.dex */
public final class PeerConnectionDependencies {
    private final PeerConnection.Observer observer;
    private final SSLCertificateVerifier sslCertificateVerifier;

    public static class Builder {
        private PeerConnection.Observer observer;
        private SSLCertificateVerifier sslCertificateVerifier;

        public /* synthetic */ Builder(PeerConnection.Observer observer, int i10) {
            this(observer);
        }

        public PeerConnectionDependencies createPeerConnectionDependencies() {
            return new PeerConnectionDependencies(this.observer, this.sslCertificateVerifier, 0);
        }

        public Builder setSSLCertificateVerifier(SSLCertificateVerifier sSLCertificateVerifier) {
            this.sslCertificateVerifier = sSLCertificateVerifier;
            return this;
        }

        private Builder(PeerConnection.Observer observer) {
            this.observer = observer;
        }
    }

    public /* synthetic */ PeerConnectionDependencies(PeerConnection.Observer observer, SSLCertificateVerifier sSLCertificateVerifier, int i10) {
        this(observer, sSLCertificateVerifier);
    }

    public static Builder builder(PeerConnection.Observer observer) {
        return new Builder(observer, 0);
    }

    public PeerConnection.Observer getObserver() {
        return this.observer;
    }

    public SSLCertificateVerifier getSSLCertificateVerifier() {
        return this.sslCertificateVerifier;
    }

    private PeerConnectionDependencies(PeerConnection.Observer observer, SSLCertificateVerifier sSLCertificateVerifier) {
        this.observer = observer;
        this.sslCertificateVerifier = sSLCertificateVerifier;
    }
}
