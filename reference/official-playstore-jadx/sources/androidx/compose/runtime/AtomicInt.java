package androidx.compose.runtime;

import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\n\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0003J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"Landroidx/compose/runtime/AtomicInt;", "Ljava/util/concurrent/atomic/AtomicInteger;", "value", "", "(I)V", "add", "amount", "toByte", "", "toChar", "", "toShort", "", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AtomicInt extends AtomicInteger {
    public static final int $stable = 0;

    public AtomicInt(int i10) {
        super(i10);
    }

    public final int add(int amount) {
        return addAndGet(amount);
    }

    @Override // java.lang.Number
    public final /* bridge */ byte byteValue() {
        return toByte();
    }

    @Override // java.util.concurrent.atomic.AtomicInteger, java.lang.Number
    public final /* bridge */ double doubleValue() {
        return toDouble();
    }

    @Override // java.util.concurrent.atomic.AtomicInteger, java.lang.Number
    public final /* bridge */ float floatValue() {
        return toFloat();
    }

    @Override // java.util.concurrent.atomic.AtomicInteger, java.lang.Number
    public final /* bridge */ int intValue() {
        return toInt();
    }

    @Override // java.util.concurrent.atomic.AtomicInteger, java.lang.Number
    public final /* bridge */ long longValue() {
        return toLong();
    }

    @Override // java.lang.Number
    public final /* bridge */ short shortValue() {
        return toShort();
    }

    public byte toByte() {
        return (byte) intValue();
    }

    public char toChar() {
        return (char) intValue();
    }

    public /* bridge */ double toDouble() {
        return super.doubleValue();
    }

    public /* bridge */ float toFloat() {
        return super.floatValue();
    }

    public /* bridge */ int toInt() {
        return super.intValue();
    }

    public /* bridge */ long toLong() {
        return super.longValue();
    }

    public short toShort() {
        return (short) intValue();
    }
}
