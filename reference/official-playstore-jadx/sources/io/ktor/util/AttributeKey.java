package io.ktor.util;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0002H\u0096\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u0004H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Lio/ktor/util/AttributeKey;", "T", "", ContentDisposition.Parameters.Name, "", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "equals", "", "other", "hashCode", "", "toString", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AttributeKey<T> {
    private final String name;

    public AttributeKey(String str) {
        this.name = str;
        if (str.length() == 0) {
            throw new IllegalStateException("Name can't be blank");
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return other != null && AttributeKey.class == other.getClass() && p.a(this.name, ((AttributeKey) other).name);
    }

    public final String getName() {
        return this.name;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public String toString() {
        return "AttributeKey: " + this.name;
    }
}
