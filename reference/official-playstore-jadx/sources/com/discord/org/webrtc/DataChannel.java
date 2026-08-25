package com.discord.org.webrtc;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes4.dex */
public class DataChannel {
    private long nativeDataChannel;
    private long nativeObserver;

    public static class Buffer {
        public final boolean binary;
        public final ByteBuffer data;

        public Buffer(ByteBuffer byteBuffer, boolean z) {
            this.data = byteBuffer;
            this.binary = z;
        }
    }

    public static class Init {
        public boolean negotiated;
        public boolean ordered = true;
        public int maxRetransmitTimeMs = -1;
        public int maxRetransmits = -1;
        public String protocol = "";
        public int id = -1;

        public int getId() {
            return this.id;
        }

        public int getMaxRetransmitTimeMs() {
            return this.maxRetransmitTimeMs;
        }

        public int getMaxRetransmits() {
            return this.maxRetransmits;
        }

        public boolean getNegotiated() {
            return this.negotiated;
        }

        public boolean getOrdered() {
            return this.ordered;
        }

        public String getProtocol() {
            return this.protocol;
        }
    }

    public interface Observer {
        void onBufferedAmountChange(long j10);

        void onMessage(Buffer buffer);

        void onStateChange();
    }

    public enum State {
        CONNECTING,
        OPEN,
        CLOSING,
        CLOSED;

        public static State fromNativeIndex(int i10) {
            return values()[i10];
        }
    }

    public DataChannel(long j10) {
        this.nativeDataChannel = j10;
    }

    private void checkDataChannelExists() {
        if (this.nativeDataChannel == 0) {
            throw new IllegalStateException("DataChannel has been disposed.");
        }
    }

    private native long nativeBufferedAmount();

    private native void nativeClose();

    private native int nativeId();

    private native String nativeLabel();

    private native long nativeRegisterObserver(Observer observer);

    private native boolean nativeSend(byte[] bArr, boolean z);

    private native State nativeState();

    private native void nativeUnregisterObserver(long j10);

    public long bufferedAmount() {
        checkDataChannelExists();
        return nativeBufferedAmount();
    }

    public void close() {
        checkDataChannelExists();
        nativeClose();
    }

    public void dispose() {
        checkDataChannelExists();
        JniCommon.nativeReleaseRef(this.nativeDataChannel);
        this.nativeDataChannel = 0L;
    }

    public long getNativeDataChannel() {
        return this.nativeDataChannel;
    }

    public int id() {
        checkDataChannelExists();
        return nativeId();
    }

    public String label() {
        checkDataChannelExists();
        return nativeLabel();
    }

    public void registerObserver(Observer observer) {
        checkDataChannelExists();
        long j10 = this.nativeObserver;
        if (j10 != 0) {
            nativeUnregisterObserver(j10);
        }
        this.nativeObserver = nativeRegisterObserver(observer);
    }

    public boolean send(Buffer buffer) {
        checkDataChannelExists();
        byte[] bArr = new byte[buffer.data.remaining()];
        buffer.data.get(bArr);
        return nativeSend(bArr, buffer.binary);
    }

    public State state() {
        checkDataChannelExists();
        return nativeState();
    }

    public void unregisterObserver() {
        checkDataChannelExists();
        nativeUnregisterObserver(this.nativeObserver);
        this.nativeObserver = 0L;
    }
}
