package io.ktor.util;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import s7.g;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010'\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u0006\u0010\u0005\u001a\u00028\u0001¢\u0006\u0002\u0010\u0006J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0015\u0010\u000b\u001a\u00028\u00012\u0006\u0010\u0013\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0014J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u0016\u0010\u0004\u001a\u00028\u0000X\u0096\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\u0005\u001a\u00028\u0001X\u0096\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\n\u0010\b\"\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lio/ktor/util/Entry;", "Key", "Value", "", "key", "value", "(Ljava/lang/Object;Ljava/lang/Object;)V", "getKey", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getValue", "setValue", "(Ljava/lang/Object;)V", "equals", "", "other", "", "hashCode", "", "newValue", "(Ljava/lang/Object;)Ljava/lang/Object;", "toString", "", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class Entry<Key, Value> implements Map.Entry<Key, Value>, g.a {
    private final Key key;
    private Value value;

    public Entry(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    @Override // java.util.Map.Entry
    public boolean equals(Object other) {
        if (other != null && (other instanceof Map.Entry)) {
            Map.Entry entry = (Map.Entry) other;
            if (p.a(entry.getKey(), getKey()) && p.a(entry.getValue(), getValue())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Map.Entry
    public Key getKey() {
        return this.key;
    }

    @Override // java.util.Map.Entry
    public Value getValue() {
        return this.value;
    }

    @Override // java.util.Map.Entry
    public int hashCode() {
        return getValue().hashCode() + getKey().hashCode() + 527;
    }

    @Override // java.util.Map.Entry
    public void setValue(Value value) {
        this.value = value;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getKey());
        sb2.append('=');
        sb2.append(getValue());
        return sb2.toString();
    }

    @Override // java.util.Map.Entry
    public Value setValue(Value newValue) {
        setValue((Object) newValue);
        return getValue();
    }
}
