package io.ktor.network.sockets;

import kotlin.Metadata;
import kotlin.jvm.internal.h;
import q7.g;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\n\b\u0087@\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0014\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005B\u0012\u0012\u0006\u0010\u0003\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0007J\u0010\u0010\u000b\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000e\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u0013\u001a\u00020\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0011\u0010\u0012R \u0010\u0003\u001a\u00020\u00068\u0006ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\f\n\u0004\b\u0003\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0012\u0010\u0018\u001a\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0017\u0010\r\u0088\u0001\u0003\u0092\u0001\u00020\u0006ø\u0001\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001a"}, d2 = {"Lio/ktor/network/sockets/TypeOfService;", "", "", "value", "constructor-impl", "(I)B", "Lx6/k0;", "(B)B", "", "toString-impl", "(B)Ljava/lang/String;", "toString", "hashCode-impl", "(B)I", "hashCode", "other", "", "equals-impl", "(BLjava/lang/Object;)Z", "equals", "B", "getValue-w2LRezQ", "()B", "getIntValue-impl", "intValue", "Companion", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
@g
public final class TypeOfService {
    private final byte value;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final byte UNDEFINED = m6689constructorimpl((byte) 0);
    private static final byte IPTOS_LOWCOST = m6689constructorimpl((byte) 2);
    private static final byte IPTOS_RELIABILITY = m6689constructorimpl((byte) 4);
    private static final byte IPTOS_THROUGHPUT = m6689constructorimpl((byte) 8);
    private static final byte IPTOS_LOWDELAY = m6689constructorimpl((byte) 16);

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006R\u001c\u0010\n\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000b\u0010\u0006R\u001c\u0010\f\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\r\u0010\u0006R\u001c\u0010\u000e\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000f\u0010\u0006\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0010"}, d2 = {"Lio/ktor/network/sockets/TypeOfService$Companion;", "", "()V", "IPTOS_LOWCOST", "Lio/ktor/network/sockets/TypeOfService;", "getIPTOS_LOWCOST-zieKYfw", "()B", "B", "IPTOS_LOWDELAY", "getIPTOS_LOWDELAY-zieKYfw", "IPTOS_RELIABILITY", "getIPTOS_RELIABILITY-zieKYfw", "IPTOS_THROUGHPUT", "getIPTOS_THROUGHPUT-zieKYfw", "UNDEFINED", "getUNDEFINED-zieKYfw", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        /* JADX INFO: renamed from: getIPTOS_LOWCOST-zieKYfw, reason: not valid java name */
        public final byte m6698getIPTOS_LOWCOSTzieKYfw() {
            return TypeOfService.IPTOS_LOWCOST;
        }

        /* JADX INFO: renamed from: getIPTOS_LOWDELAY-zieKYfw, reason: not valid java name */
        public final byte m6699getIPTOS_LOWDELAYzieKYfw() {
            return TypeOfService.IPTOS_LOWDELAY;
        }

        /* JADX INFO: renamed from: getIPTOS_RELIABILITY-zieKYfw, reason: not valid java name */
        public final byte m6700getIPTOS_RELIABILITYzieKYfw() {
            return TypeOfService.IPTOS_RELIABILITY;
        }

        /* JADX INFO: renamed from: getIPTOS_THROUGHPUT-zieKYfw, reason: not valid java name */
        public final byte m6701getIPTOS_THROUGHPUTzieKYfw() {
            return TypeOfService.IPTOS_THROUGHPUT;
        }

        /* JADX INFO: renamed from: getUNDEFINED-zieKYfw, reason: not valid java name */
        public final byte m6702getUNDEFINEDzieKYfw() {
            return TypeOfService.UNDEFINED;
        }

        private Companion() {
        }
    }

    private /* synthetic */ TypeOfService(byte b10) {
        this.value = b10;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ TypeOfService m6688boximpl(byte b10) {
        return new TypeOfService(b10);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static byte m6689constructorimpl(byte b10) {
        return b10;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m6691equalsimpl(byte b10, Object obj) {
        return (obj instanceof TypeOfService) && b10 == ((TypeOfService) obj).m6697unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m6692equalsimpl0(byte b10, byte b11) {
        return b10 == b11;
    }

    /* JADX INFO: renamed from: getIntValue-impl, reason: not valid java name */
    public static final int m6693getIntValueimpl(byte b10) {
        return b10 & 255;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m6694hashCodeimpl(byte b10) {
        return b10;
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m6695toStringimpl(byte b10) {
        return "TypeOfService(value=" + ((Object) String.valueOf(b10 & 255)) + ')';
    }

    public boolean equals(Object obj) {
        return m6691equalsimpl(this.value, obj);
    }

    /* JADX INFO: renamed from: getValue-w2LRezQ, reason: not valid java name and from getter */
    public final byte getValue() {
        return this.value;
    }

    public int hashCode() {
        return m6694hashCodeimpl(this.value);
    }

    public String toString() {
        return m6695toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ byte m6697unboximpl() {
        return this.value;
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static byte m6690constructorimpl(int i10) {
        return m6689constructorimpl((byte) i10);
    }
}
