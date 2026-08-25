package io.ktor.websocket;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u00016B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\t\u0010\bJ\u0017\u0010\n\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\n\u0010\bJ\u0017\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u000b\u0010\bJ\r\u0010\r\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u0003J\u0015\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u000e\u0010\u000fR\"\u0010\u0013\u001a\u0010\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u00110\u00110\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R$\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00068\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R$\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00068\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u001a\u0010\u0017\u001a\u0004\b\u001b\u0010\u0019R$\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00068\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u001c\u0010\u0017\u001a\u0004\b\u001d\u0010\u0019R$\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00068\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u001e\u0010\u0017\u001a\u0004\b\u001f\u0010\u0019R$\u0010 \u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00068\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b \u0010\u0017\u001a\u0004\b!\u0010\u0019R\u0016\u0010#\u001a\u00020\"8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b#\u0010$R\u0016\u0010%\u001a\u00020\"8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b%\u0010$R\u0016\u0010&\u001a\u00020\"8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b&\u0010$R$\u0010(\u001a\u00020'2\u0006\u0010\u0015\u001a\u00020'8\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+R(\u0010,\u001a\u0004\u0018\u00010\"2\b\u0010\u0015\u001a\u0004\u0018\u00010\"8\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/R\u0011\u00103\u001a\u0002008F¢\u0006\u0006\u001a\u0004\b1\u00102R\u0011\u00105\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b4\u0010\u0019¨\u00067"}, d2 = {"Lio/ktor/websocket/FrameParser;", "", "<init>", "()V", "Ljava/nio/ByteBuffer;", "bb", "", "handleStep", "(Ljava/nio/ByteBuffer;)Z", "parseHeader1", "parseLength", "parseMaskKey", "Lx6/t0;", "bodyComplete", "frame", "(Ljava/nio/ByteBuffer;)V", "Ljava/util/concurrent/atomic/AtomicReference;", "Lio/ktor/websocket/FrameParser$State;", "kotlin.jvm.PlatformType", "state", "Ljava/util/concurrent/atomic/AtomicReference;", "<set-?>", "fin", "Z", "getFin", "()Z", "rsv1", "getRsv1", "rsv2", "getRsv2", "rsv3", "getRsv3", "mask", "getMask", "", "opcode", "I", "lastOpcode", "lengthLength", "", "length", "J", "getLength", "()J", "maskKey", "Ljava/lang/Integer;", "getMaskKey", "()Ljava/lang/Integer;", "Lio/ktor/websocket/FrameType;", "getFrameType", "()Lio/ktor/websocket/FrameType;", "frameType", "getBodyReady", "bodyReady", "State", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class FrameParser {
    private boolean fin;
    private int lastOpcode;
    private long length;
    private int lengthLength;
    private boolean mask;
    private Integer maskKey;
    private int opcode;
    private boolean rsv1;
    private boolean rsv2;
    private boolean rsv3;
    private final AtomicReference<State> state = new AtomicReference<>(State.HEADER0);

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lio/ktor/websocket/FrameParser$State;", "", "(Ljava/lang/String;I)V", "HEADER0", "LENGTH", "MASK_KEY", "BODY", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public enum State {
        HEADER0,
        LENGTH,
        MASK_KEY,
        BODY
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[State.values().length];
            try {
                iArr[State.HEADER0.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[State.LENGTH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[State.MASK_KEY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[State.BODY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private final boolean handleStep(ByteBuffer bb2) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[this.state.get().ordinal()];
        if (i10 == 1) {
            return parseHeader1(bb2);
        }
        if (i10 == 2) {
            return parseLength(bb2);
        }
        if (i10 == 3) {
            return parseMaskKey(bb2);
        }
        if (i10 == 4) {
            return false;
        }
        throw new NoWhenBranchMatchedException();
    }

    private final boolean parseHeader1(ByteBuffer bb2) throws ProtocolViolationException {
        int i10 = 0;
        if (bb2.remaining() < 2) {
            return false;
        }
        byte b10 = bb2.get();
        byte b11 = bb2.get();
        this.fin = (b10 & 128) != 0;
        this.rsv1 = (b10 & 64) != 0;
        this.rsv2 = (b10 & 32) != 0;
        this.rsv3 = (b10 & 16) != 0;
        int i11 = b10 & 15;
        this.opcode = i11;
        if (i11 == 0 && this.lastOpcode == 0) {
            throw new ProtocolViolationException("Can't continue finished frames");
        }
        if (i11 == 0) {
            this.opcode = this.lastOpcode;
        } else if (this.lastOpcode != 0 && !getFrameType().getControlFrame()) {
            throw new ProtocolViolationException("Can't start new data frame before finishing previous one");
        }
        if (!getFrameType().getControlFrame()) {
            this.lastOpcode = this.fin ? 0 : this.opcode;
        } else if (!this.fin) {
            throw new ProtocolViolationException("control frames can't be fragmented");
        }
        this.mask = (b11 & 128) != 0;
        int i12 = b11 & 127;
        if (getFrameType().getControlFrame() && i12 > 125) {
            throw new ProtocolViolationException("control frames can't be larger than 125 bytes");
        }
        if (i12 == 126) {
            i10 = 2;
        } else if (i12 == 127) {
            i10 = 8;
        }
        this.lengthLength = i10;
        this.length = i10 == 0 ? i12 : 0L;
        if (i10 > 0) {
            this.state.set(State.LENGTH);
        } else if (this.mask) {
            this.state.set(State.MASK_KEY);
        } else {
            this.state.set(State.BODY);
        }
        return true;
    }

    private final boolean parseLength(ByteBuffer bb2) {
        long j10;
        int iRemaining = bb2.remaining();
        int i10 = this.lengthLength;
        if (iRemaining < i10) {
            return false;
        }
        if (i10 == 2) {
            j10 = ((long) bb2.getShort()) & 65535;
        } else {
            if (i10 != 8) {
                throw new IllegalStateException();
            }
            j10 = bb2.getLong();
        }
        this.length = j10;
        this.state.set(this.mask ? State.MASK_KEY : State.BODY);
        return true;
    }

    private final boolean parseMaskKey(ByteBuffer bb2) {
        if (bb2.remaining() < 4) {
            return false;
        }
        this.maskKey = Integer.valueOf(bb2.getInt());
        this.state.set(State.BODY);
        return true;
    }

    public final void bodyComplete() {
        AtomicReference<State> atomicReference = this.state;
        State state = State.BODY;
        State state2 = State.HEADER0;
        while (!atomicReference.compareAndSet(state, state2)) {
            if (atomicReference.get() != state) {
                throw new IllegalStateException("It should be state BODY but it is " + this.state.get());
            }
        }
        this.opcode = 0;
        this.length = 0L;
        this.lengthLength = 0;
        this.maskKey = null;
    }

    public final void frame(ByteBuffer bb2) {
        if (p.a(bb2.order(), ByteOrder.BIG_ENDIAN)) {
            while (handleStep(bb2)) {
            }
        } else {
            throw new IllegalArgumentException(("Buffer order should be BIG_ENDIAN but it is " + bb2.order()).toString());
        }
    }

    public final boolean getBodyReady() {
        return this.state.get() == State.BODY;
    }

    public final boolean getFin() {
        return this.fin;
    }

    public final FrameType getFrameType() {
        FrameType frameType = FrameType.INSTANCE.get(this.opcode);
        if (frameType != null) {
            return frameType;
        }
        throw new IllegalStateException("Unsupported opcode " + Integer.toHexString(this.opcode));
    }

    public final long getLength() {
        return this.length;
    }

    public final boolean getMask() {
        return this.mask;
    }

    public final Integer getMaskKey() {
        return this.maskKey;
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
}
