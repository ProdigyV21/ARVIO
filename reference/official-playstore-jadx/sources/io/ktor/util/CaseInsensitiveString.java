package io.ktor.util;

import java.util.Locale;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\f\u001a\u00020\bH\u0016J\b\u0010\r\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lio/ktor/util/CaseInsensitiveString;", "", "content", "", "(Ljava/lang/String;)V", "getContent", "()Ljava/lang/String;", "hash", "", "equals", "", "other", "hashCode", "toString", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CaseInsensitiveString {
    private final String content;
    private final int hash;

    public CaseInsensitiveString(String str) {
        this.content = str;
        this.hash = str.toLowerCase(Locale.ROOT).hashCode();
    }

    public boolean equals(Object other) {
        String str;
        CaseInsensitiveString caseInsensitiveString = other instanceof CaseInsensitiveString ? (CaseInsensitiveString) other : null;
        return (caseInsensitiveString == null || (str = caseInsensitiveString.content) == null || !str.equalsIgnoreCase(this.content)) ? false : true;
    }

    public final String getContent() {
        return this.content;
    }

    /* JADX INFO: renamed from: hashCode, reason: from getter */
    public int getHash() {
        return this.hash;
    }

    public String toString() {
        return this.content;
    }
}
