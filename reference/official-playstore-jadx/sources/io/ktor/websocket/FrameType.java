package io.ktor.websocket;

import kotlin.Metadata;
import kotlin.jvm.internal.h;
import x7.i;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\f\b\u0086\u0001\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0010B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0011"}, d2 = {"Lio/ktor/websocket/FrameType;", "", "controlFrame", "", "opcode", "", "(Ljava/lang/String;IZI)V", "getControlFrame", "()Z", "getOpcode", "()I", "TEXT", "BINARY", "CLOSE", "PING", "PONG", "Companion", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
public enum FrameType {
    TEXT(false, 1),
    BINARY(false, 2),
    CLOSE(true, 8),
    PING(true, 9),
    PONG(true, 10);


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final FrameType[] byOpcodeArray;
    private static final int maxOpcode;
    private final boolean controlFrame;
    private final int opcode;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0013\u0010\t\u001a\u0004\u0018\u00010\u00052\u0006\u0010\n\u001a\u00020\bH\u0086\u0002R\u0018\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lio/ktor/websocket/FrameType$Companion;", "", "()V", "byOpcodeArray", "", "Lio/ktor/websocket/FrameType;", "[Lio/ktor/websocket/FrameType;", "maxOpcode", "", "get", "opcode", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final FrameType get(int opcode) {
            if (opcode < 0 || opcode > FrameType.maxOpcode) {
                return null;
            }
            return FrameType.byOpcodeArray[opcode];
        }

        private Companion() {
        }
    }

    static {
        FrameType frameType;
        FrameType[] frameTypeArrValues = values();
        if (frameTypeArrValues.length == 0) {
            frameType = null;
        } else {
            frameType = frameTypeArrValues[0];
            int length = frameTypeArrValues.length - 1;
            if (length != 0) {
                int i10 = frameType.opcode;
                x7.h it = new i(1, length, 1).iterator();
                while (it.f22624m) {
                    FrameType frameType2 = frameTypeArrValues[it.nextInt()];
                    int i11 = frameType2.opcode;
                    if (i10 < i11) {
                        frameType = frameType2;
                        i10 = i11;
                    }
                }
            }
        }
        int i12 = frameType.opcode;
        maxOpcode = i12;
        int i13 = i12 + 1;
        FrameType[] frameTypeArr = new FrameType[i13];
        for (int i14 = 0; i14 < i13; i14++) {
            FrameType[] frameTypeArrValues2 = values();
            int length2 = frameTypeArrValues2.length;
            FrameType frameType3 = null;
            int i15 = 0;
            boolean z = false;
            while (true) {
                if (i15 >= length2) {
                    if (!z) {
                        break;
                    }
                } else {
                    FrameType frameType4 = frameTypeArrValues2[i15];
                    if (frameType4.opcode == i14) {
                        if (z) {
                            break;
                        }
                        z = true;
                        frameType3 = frameType4;
                    }
                    i15++;
                }
            }
            frameType3 = null;
            frameTypeArr[i14] = frameType3;
        }
        byOpcodeArray = frameTypeArr;
    }

    FrameType(boolean z, int i10) {
        this.controlFrame = z;
        this.opcode = i10;
    }

    public final boolean getControlFrame() {
        return this.controlFrame;
    }

    public final int getOpcode() {
        return this.opcode;
    }
}
