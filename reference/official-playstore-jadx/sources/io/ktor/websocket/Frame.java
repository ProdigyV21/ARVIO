package io.ktor.websocket;

import a0.c;
import io.ktor.util.NIOKt;
import io.ktor.utils.io.charsets.CharsetJVMKt;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.OutputPrimitivesKt;
import io.ktor.utils.io.core.StringsKt;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import ka.z0;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import kotlin.text.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 (2\u00020\u0001:\u0006)*(+,-BI\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0002\u0012\b\b\u0002\u0010\f\u001a\u00020\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0012\u001a\u00020\u0000¢\u0006\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010\n\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\n\u0010\u0014\u001a\u0004\b \u0010\u0016R\u0017\u0010\u000b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0014\u001a\u0004\b!\u0010\u0016R\u0017\u0010\f\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\f\u0010\u0014\u001a\u0004\b\"\u0010\u0016R\u0017\u0010$\u001a\u00020#8\u0006¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'\u0082\u0001\u0005./012¨\u00063"}, d2 = {"Lio/ktor/websocket/Frame;", "", "", "fin", "Lio/ktor/websocket/FrameType;", "frameType", "", "data", "Lka/z0;", "disposableHandle", "rsv1", "rsv2", "rsv3", "<init>", "(ZLio/ktor/websocket/FrameType;[BLka/z0;ZZZ)V", "", "toString", "()Ljava/lang/String;", "copy", "()Lio/ktor/websocket/Frame;", "Z", "getFin", "()Z", "Lio/ktor/websocket/FrameType;", "getFrameType", "()Lio/ktor/websocket/FrameType;", "[B", "getData", "()[B", "Lka/z0;", "getDisposableHandle", "()Lka/z0;", "getRsv1", "getRsv2", "getRsv3", "Ljava/nio/ByteBuffer;", "buffer", "Ljava/nio/ByteBuffer;", "getBuffer", "()Ljava/nio/ByteBuffer;", "Companion", "Binary", "Close", "Ping", "Pong", "Text", "Lio/ktor/websocket/Frame$Binary;", "Lio/ktor/websocket/Frame$Close;", "Lio/ktor/websocket/Frame$Ping;", "Lio/ktor/websocket/Frame$Pong;", "Lio/ktor/websocket/Frame$Text;", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class Frame {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final byte[] Empty = new byte[0];
    private final ByteBuffer buffer;
    private final byte[] data;
    private final z0 disposableHandle;
    private final boolean fin;
    private final FrameType frameType;
    private final boolean rsv1;
    private final boolean rsv2;
    private final boolean rsv3;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tB\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fB3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"Lio/ktor/websocket/Frame$Binary;", "Lio/ktor/websocket/Frame;", "fin", "", "buffer", "Ljava/nio/ByteBuffer;", "(ZLjava/nio/ByteBuffer;)V", "data", "", "(Z[B)V", "packet", "Lio/ktor/utils/io/core/ByteReadPacket;", "(ZLio/ktor/utils/io/core/ByteReadPacket;)V", "rsv1", "rsv2", "rsv3", "(Z[BZZZ)V", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Binary extends Frame {
        public /* synthetic */ Binary(boolean z, byte[] bArr, boolean z5, boolean z10, boolean z11, int i10, h hVar) {
            this(z, bArr, (i10 & 4) != 0 ? false : z5, (i10 & 8) != 0 ? false : z10, (i10 & 16) != 0 ? false : z11);
        }

        public Binary(boolean z, byte[] bArr, boolean z5, boolean z10, boolean z11) {
            super(z, FrameType.BINARY, bArr, NonDisposableHandle.INSTANCE, z5, z10, z11, null);
        }

        public Binary(boolean z, ByteBuffer byteBuffer) {
            this(z, NIOKt.moveToByteArray(byteBuffer));
        }

        public Binary(boolean z, byte[] bArr) {
            this(z, bArr, false, false, false);
        }

        public Binary(boolean z, ByteReadPacket byteReadPacket) {
            this(z, StringsKt.readBytes$default(byteReadPacket, 0, 1, null));
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u0007\b\u0016¢\u0006\u0002\u0010\bB\u000f\b\u0016\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bB\r\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000e¨\u0006\u000f"}, d2 = {"Lio/ktor/websocket/Frame$Close;", "Lio/ktor/websocket/Frame;", "reason", "Lio/ktor/websocket/CloseReason;", "(Lio/ktor/websocket/CloseReason;)V", "packet", "Lio/ktor/utils/io/core/ByteReadPacket;", "(Lio/ktor/utils/io/core/ByteReadPacket;)V", "()V", "buffer", "Ljava/nio/ByteBuffer;", "(Ljava/nio/ByteBuffer;)V", "data", "", "([B)V", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Close extends Frame {
        public Close(byte[] bArr) {
            super(true, FrameType.CLOSE, bArr, NonDisposableHandle.INSTANCE, false, false, false, null);
        }

        public Close(ByteReadPacket byteReadPacket) {
            this(StringsKt.readBytes$default(byteReadPacket, 0, 1, null));
        }

        public Close() {
            this(Frame.Empty);
        }

        public Close(ByteBuffer byteBuffer) {
            this(NIOKt.moveToByteArray(byteBuffer));
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Close(CloseReason closeReason) {
            BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, 0 == true ? 1 : 0);
            try {
                OutputPrimitivesKt.writeShort(bytePacketBuilder, closeReason.getCode());
                StringsKt.writeText$default(bytePacketBuilder, closeReason.getMessage(), 0, 0, (Charset) null, 14, (Object) null);
                this(bytePacketBuilder.build());
            } catch (Throwable th) {
                bytePacketBuilder.release();
                throw th;
            }
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J6\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lio/ktor/websocket/Frame$Companion;", "", "()V", "Empty", "", "byType", "Lio/ktor/websocket/Frame;", "fin", "", "frameType", "Lio/ktor/websocket/FrameType;", "data", "rsv1", "rsv2", "rsv3", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {

        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[FrameType.values().length];
                try {
                    iArr[FrameType.BINARY.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[FrameType.TEXT.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[FrameType.CLOSE.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[FrameType.PING.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[FrameType.PONG.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final Frame byType(boolean fin, FrameType frameType, byte[] data, boolean rsv1, boolean rsv2, boolean rsv3) {
            int i10 = WhenMappings.$EnumSwitchMapping$0[frameType.ordinal()];
            if (i10 == 1) {
                return new Binary(fin, data, rsv1, rsv2, rsv3);
            }
            if (i10 == 2) {
                return new Text(fin, data, rsv1, rsv2, rsv3);
            }
            if (i10 == 3) {
                return new Close(data);
            }
            if (i10 == 4) {
                return new Ping(data);
            }
            if (i10 == 5) {
                return new Pong(data, NonDisposableHandle.INSTANCE);
            }
            throw new NoWhenBranchMatchedException();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\r\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lio/ktor/websocket/Frame$Ping;", "Lio/ktor/websocket/Frame;", "packet", "Lio/ktor/utils/io/core/ByteReadPacket;", "(Lio/ktor/utils/io/core/ByteReadPacket;)V", "buffer", "Ljava/nio/ByteBuffer;", "(Ljava/nio/ByteBuffer;)V", "data", "", "([B)V", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Ping extends Frame {
        public Ping(byte[] bArr) {
            super(true, FrameType.PING, bArr, NonDisposableHandle.INSTANCE, false, false, false, null);
        }

        public Ping(ByteReadPacket byteReadPacket) {
            this(StringsKt.readBytes$default(byteReadPacket, 0, 1, null));
        }

        public Ping(ByteBuffer byteBuffer) {
            this(NIOKt.moveToByteArray(byteBuffer));
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u000f\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tB\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fB\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fB3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0003¢\u0006\u0002\u0010\u0013¨\u0006\u0014"}, d2 = {"Lio/ktor/websocket/Frame$Text;", "Lio/ktor/websocket/Frame;", "fin", "", "data", "", "(Z[B)V", "text", "", "(Ljava/lang/String;)V", "packet", "Lio/ktor/utils/io/core/ByteReadPacket;", "(ZLio/ktor/utils/io/core/ByteReadPacket;)V", "buffer", "Ljava/nio/ByteBuffer;", "(ZLjava/nio/ByteBuffer;)V", "rsv1", "rsv2", "rsv3", "(Z[BZZZ)V", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Text extends Frame {
        public /* synthetic */ Text(boolean z, byte[] bArr, boolean z5, boolean z10, boolean z11, int i10, h hVar) {
            this(z, bArr, (i10 & 4) != 0 ? false : z5, (i10 & 8) != 0 ? false : z10, (i10 & 16) != 0 ? false : z11);
        }

        public Text(boolean z, byte[] bArr, boolean z5, boolean z10, boolean z11) {
            super(z, FrameType.TEXT, bArr, NonDisposableHandle.INSTANCE, z5, z10, z11, null);
        }

        public Text(boolean z, byte[] bArr) {
            this(z, bArr, false, false, false);
        }

        public Text(boolean z, ByteReadPacket byteReadPacket) {
            this(z, StringsKt.readBytes$default(byteReadPacket, 0, 1, null));
        }

        public Text(boolean z, ByteBuffer byteBuffer) {
            this(z, NIOKt.moveToByteArray(byteBuffer));
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public Text(String str) {
            byte[] bArrEncodeToByteArray;
            Charset charset = a.f19924a;
            if (p.a(charset, charset)) {
                bArrEncodeToByteArray = str.getBytes(charset);
            } else {
                bArrEncodeToByteArray = CharsetJVMKt.encodeToByteArray(charset.newEncoder(), str, 0, str.length());
            }
            this(true, bArrEncodeToByteArray);
        }
    }

    public /* synthetic */ Frame(boolean z, FrameType frameType, byte[] bArr, z0 z0Var, boolean z5, boolean z10, boolean z11, h hVar) {
        this(z, frameType, bArr, z0Var, z5, z10, z11);
    }

    public final Frame copy() {
        Companion companion = INSTANCE;
        boolean z = this.fin;
        FrameType frameType = this.frameType;
        byte[] bArr = this.data;
        return companion.byType(z, frameType, Arrays.copyOf(bArr, bArr.length), this.rsv1, this.rsv2, this.rsv3);
    }

    public final ByteBuffer getBuffer() {
        return this.buffer;
    }

    public final byte[] getData() {
        return this.data;
    }

    public final z0 getDisposableHandle() {
        return this.disposableHandle;
    }

    public final boolean getFin() {
        return this.fin;
    }

    public final FrameType getFrameType() {
        return this.frameType;
    }

    public final boolean getRsv1() {
        return this.rsv1;
    }

    public final boolean getRsv2() {
        return this.rsv2;
    }

    public final boolean getRsv3() {
        return this.rsv3;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("Frame ");
        sb2.append(this.frameType);
        sb2.append(" (fin=");
        sb2.append(this.fin);
        sb2.append(", buffer len = ");
        return c.o(sb2, this.data.length, ')');
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0016\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u0006\u0010\nB\u001b\b\u0016\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\rB\u0011\b\u0016\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u0006\u0010\u000e¨\u0006\u000f"}, d2 = {"Lio/ktor/websocket/Frame$Pong;", "Lio/ktor/websocket/Frame;", "", "data", "Lka/z0;", "disposableHandle", "<init>", "([BLka/z0;)V", "Lio/ktor/utils/io/core/ByteReadPacket;", "packet", "(Lio/ktor/utils/io/core/ByteReadPacket;)V", "Ljava/nio/ByteBuffer;", "buffer", "(Ljava/nio/ByteBuffer;Lka/z0;)V", "(Ljava/nio/ByteBuffer;)V", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Pong extends Frame {
        public /* synthetic */ Pong(byte[] bArr, z0 z0Var, int i10, h hVar) {
            this(bArr, (i10 & 2) != 0 ? NonDisposableHandle.INSTANCE : z0Var);
        }

        public Pong(byte[] bArr, z0 z0Var) {
            super(true, FrameType.PONG, bArr, z0Var, false, false, false, null);
        }

        public Pong(ByteReadPacket byteReadPacket) {
            this(StringsKt.readBytes$default(byteReadPacket, 0, 1, null), NonDisposableHandle.INSTANCE);
        }

        public /* synthetic */ Pong(ByteBuffer byteBuffer, z0 z0Var, int i10, h hVar) {
            this(byteBuffer, (i10 & 2) != 0 ? NonDisposableHandle.INSTANCE : z0Var);
        }

        public Pong(ByteBuffer byteBuffer, z0 z0Var) {
            this(NIOKt.moveToByteArray(byteBuffer), z0Var);
        }

        public Pong(ByteBuffer byteBuffer) {
            this(NIOKt.moveToByteArray(byteBuffer), NonDisposableHandle.INSTANCE);
        }
    }

    private Frame(boolean z, FrameType frameType, byte[] bArr, z0 z0Var, boolean z5, boolean z10, boolean z11) {
        this.fin = z;
        this.frameType = frameType;
        this.data = bArr;
        this.disposableHandle = z0Var;
        this.rsv1 = z5;
        this.rsv2 = z10;
        this.rsv3 = z11;
        this.buffer = ByteBuffer.wrap(bArr);
    }

    public /* synthetic */ Frame(boolean z, FrameType frameType, byte[] bArr, z0 z0Var, boolean z5, boolean z10, boolean z11, int i10, h hVar) {
        this(z, frameType, bArr, (i10 & 8) != 0 ? NonDisposableHandle.INSTANCE : z0Var, (i10 & 16) != 0 ? false : z5, (i10 & 32) != 0 ? false : z10, (i10 & 64) != 0 ? false : z11, null);
    }
}
