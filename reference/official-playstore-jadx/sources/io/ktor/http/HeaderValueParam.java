package io.ktor.http;

import a0.c;
import io.ktor.http.ContentDisposition;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.text.u;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0007HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0017"}, d2 = {"Lio/ktor/http/HeaderValueParam;", "", ContentDisposition.Parameters.Name, "", "value", "(Ljava/lang/String;Ljava/lang/String;)V", "escapeValue", "", "(Ljava/lang/String;Ljava/lang/String;Z)V", "getEscapeValue", "()Z", "getName", "()Ljava/lang/String;", "getValue", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class HeaderValueParam {
    private final boolean escapeValue;
    private final String name;
    private final String value;

    public HeaderValueParam(String str, String str2, boolean z) {
        this.name = str;
        this.value = str2;
        this.escapeValue = z;
    }

    public static /* synthetic */ HeaderValueParam copy$default(HeaderValueParam headerValueParam, String str, String str2, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = headerValueParam.name;
        }
        if ((i10 & 2) != 0) {
            str2 = headerValueParam.value;
        }
        if ((i10 & 4) != 0) {
            z = headerValueParam.escapeValue;
        }
        return headerValueParam.copy(str, str2, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getValue() {
        return this.value;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getEscapeValue() {
        return this.escapeValue;
    }

    public final HeaderValueParam copy(String name, String value, boolean escapeValue) {
        return new HeaderValueParam(name, value, escapeValue);
    }

    public boolean equals(Object other) {
        if (!(other instanceof HeaderValueParam)) {
            return false;
        }
        HeaderValueParam headerValueParam = (HeaderValueParam) other;
        return u.L(headerValueParam.name, this.name, true) && u.L(headerValueParam.value, this.value, true);
    }

    public final boolean getEscapeValue() {
        return this.escapeValue;
    }

    public final String getName() {
        return this.name;
    }

    public final String getValue() {
        return this.value;
    }

    public int hashCode() {
        String str = this.name;
        Locale locale = Locale.ROOT;
        int iHashCode = str.toLowerCase(locale).hashCode();
        return this.value.toLowerCase(locale).hashCode() + (iHashCode * 31) + iHashCode;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("HeaderValueParam(name=");
        sb2.append(this.name);
        sb2.append(", value=");
        sb2.append(this.value);
        sb2.append(", escapeValue=");
        return c.r(sb2, this.escapeValue, ')');
    }

    public HeaderValueParam(String str, String str2) {
        this(str, str2, false);
    }
}
