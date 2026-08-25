package com.discord.org.webrtc;

import com.discord.org.webrtc.PeerConnection;

/* JADX INFO: loaded from: classes4.dex */
public class RtcCertificatePem {
    private static final long DEFAULT_EXPIRY = 2592000;
    public final String certificate;
    public final String privateKey;

    public RtcCertificatePem(String str, String str2) {
        this.privateKey = str;
        this.certificate = str2;
    }

    public static RtcCertificatePem generateCertificate() {
        return nativeGenerateCertificate(PeerConnection.KeyType.ECDSA, DEFAULT_EXPIRY);
    }

    private static native RtcCertificatePem nativeGenerateCertificate(PeerConnection.KeyType keyType, long j10);

    public String getCertificate() {
        return this.certificate;
    }

    public String getPrivateKey() {
        return this.privateKey;
    }

    public static RtcCertificatePem generateCertificate(PeerConnection.KeyType keyType) {
        return nativeGenerateCertificate(keyType, DEFAULT_EXPIRY);
    }

    public static RtcCertificatePem generateCertificate(long j10) {
        return nativeGenerateCertificate(PeerConnection.KeyType.ECDSA, j10);
    }

    public static RtcCertificatePem generateCertificate(PeerConnection.KeyType keyType, long j10) {
        return nativeGenerateCertificate(keyType, j10);
    }
}
