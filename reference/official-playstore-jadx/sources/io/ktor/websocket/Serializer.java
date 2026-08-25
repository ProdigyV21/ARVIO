package io.ktor.websocket;

import io.ktor.util.NIOKt;
import java.nio.ByteBuffer;
import java.util.concurrent.ArrayBlockingQueue;
import kotlin.Metadata;
import v7.d;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u0006*\u00020\u0006H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0017\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u0015\u0010\u0019\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u0004¢\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u001b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00040\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0018\u0010 \u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b \u0010!R\u0018\u0010\"\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\"\u0010!R\u0018\u0010$\u001a\u0004\u0018\u00010#8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b$\u0010%R\"\u0010&\u001a\u00020\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)\"\u0004\b*\u0010\u0018R\u0011\u0010,\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b+\u0010)R\u0011\u0010/\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b-\u0010.¨\u00060"}, d2 = {"Lio/ktor/websocket/Serializer;", "", "<init>", "()V", "Lio/ktor/websocket/Frame;", "frame", "Ljava/nio/ByteBuffer;", "buffer", "", "mask", "Lx6/t0;", "serializeHeader", "(Lio/ktor/websocket/Frame;Ljava/nio/ByteBuffer;Z)V", "f", "", "estimateFrameHeaderSize", "(Lio/ktor/websocket/Frame;Z)I", "writeCurrentPayload", "(Ljava/nio/ByteBuffer;)Z", "maskSize", "(Z)I", "maskedIfNeeded", "(Ljava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;", "setMaskBuffer", "(Z)V", "enqueue", "(Lio/ktor/websocket/Frame;)V", "serialize", "(Ljava/nio/ByteBuffer;)V", "Ljava/util/concurrent/ArrayBlockingQueue;", "messages", "Ljava/util/concurrent/ArrayBlockingQueue;", "frameBody", "Ljava/nio/ByteBuffer;", "maskBuffer", "Lio/ktor/websocket/FrameType;", "lastDataFrameType", "Lio/ktor/websocket/FrameType;", "masking", "Z", "getMasking", "()Z", "setMasking", "getHasOutstandingBytes", "hasOutstandingBytes", "getRemainingCapacity", "()I", "remainingCapacity", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Serializer {
    private ByteBuffer frameBody;
    private FrameType lastDataFrameType;
    private ByteBuffer maskBuffer;
    private boolean masking;
    private final ArrayBlockingQueue<Frame> messages = new ArrayBlockingQueue<>(1024);

    private final int estimateFrameHeaderSize(Frame f10, boolean mask) {
        int iRemaining = f10.getBuffer().remaining();
        return (iRemaining < 126 ? 2 : iRemaining <= 32767 ? 4 : 10) + maskSize(mask);
    }

    private final int maskSize(boolean mask) {
        return mask ? 4 : 0;
    }

    private final ByteBuffer maskedIfNeeded(ByteBuffer byteBuffer) {
        ByteBuffer byteBuffer2 = this.maskBuffer;
        if (byteBuffer2 != null) {
            ByteBuffer byteBufferCopy$default = NIOKt.copy$default(byteBuffer, 0, 1, null);
            UtilsKt.xor(byteBufferCopy$default, byteBuffer2);
            if (byteBufferCopy$default != null) {
                return byteBufferCopy$default;
            }
        }
        return byteBuffer;
    }

    private final void serializeHeader(Frame frame, ByteBuffer buffer, boolean mask) {
        int opcode;
        ByteBuffer byteBufferDuplicate;
        int iRemaining = frame.getBuffer().remaining();
        if (iRemaining >= 126) {
            iRemaining = iRemaining <= 65535 ? 126 : 127;
        }
        FrameType frameType = this.lastDataFrameType;
        if (frameType == null) {
            if (!frame.getFin()) {
                this.lastDataFrameType = frame.getFrameType();
            }
            opcode = frame.getFrameType().getOpcode();
        } else if (frameType == frame.getFrameType()) {
            if (frame.getFin()) {
                this.lastDataFrameType = null;
            }
            opcode = 0;
        } else {
            if (!frame.getFrameType().getControlFrame()) {
                throw new IllegalStateException("Can't continue with different data frame opcode");
            }
            opcode = frame.getFrameType().getOpcode();
        }
        buffer.put((byte) (opcode | (frame.getFin() ? 128 : 0) | (frame.getRsv1() ? 64 : 0) | (frame.getRsv2() ? 32 : 0) | (frame.getRsv3() ? 16 : 0)));
        buffer.put((byte) ((mask ? 128 : 0) | iRemaining));
        if (iRemaining == 126) {
            buffer.putShort((short) frame.getBuffer().remaining());
        } else if (iRemaining == 127) {
            buffer.putLong(frame.getBuffer().remaining());
        }
        ByteBuffer byteBuffer = this.maskBuffer;
        if (byteBuffer == null || (byteBufferDuplicate = byteBuffer.duplicate()) == null) {
            return;
        }
        NIOKt.moveTo$default(byteBufferDuplicate, buffer, 0, 2, null);
    }

    private final void setMaskBuffer(boolean mask) {
        if (!mask) {
            this.maskBuffer = null;
            return;
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        byteBufferAllocate.putInt(d.f22178l.d());
        byteBufferAllocate.clear();
        this.maskBuffer = byteBufferAllocate;
    }

    private final boolean writeCurrentPayload(ByteBuffer buffer) {
        ByteBuffer byteBuffer = this.frameBody;
        if (byteBuffer == null) {
            return true;
        }
        NIOKt.moveTo$default(byteBuffer, buffer, 0, 2, null);
        if (byteBuffer.hasRemaining()) {
            return false;
        }
        this.frameBody = null;
        return true;
    }

    public final void enqueue(Frame f10) throws InterruptedException {
        this.messages.put(f10);
    }

    public final boolean getHasOutstandingBytes() {
        return (this.messages.isEmpty() && this.frameBody == null) ? false : true;
    }

    public final boolean getMasking() {
        return this.masking;
    }

    public final int getRemainingCapacity() {
        return this.messages.remainingCapacity();
    }

    public final void serialize(ByteBuffer buffer) {
        Frame framePeek;
        while (writeCurrentPayload(buffer) && (framePeek = this.messages.peek()) != null) {
            boolean z = this.masking;
            setMaskBuffer(z);
            if (buffer.remaining() < estimateFrameHeaderSize(framePeek, z)) {
                return;
            }
            serializeHeader(framePeek, buffer, z);
            this.messages.remove();
            this.frameBody = maskedIfNeeded(framePeek.getBuffer());
        }
    }

    public final void setMasking(boolean z) {
        this.masking = z;
    }
}
