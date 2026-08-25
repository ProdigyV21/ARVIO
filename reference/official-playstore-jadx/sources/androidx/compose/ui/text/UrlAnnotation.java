package androidx.compose.ui.text;

import androidx.compose.foundation.c;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, d2 = {"Landroidx/compose/ui/text/UrlAnnotation;", "", "url", "", "(Ljava/lang/String;)V", "getUrl", "()Ljava/lang/String;", "equals", "", "other", "hashCode", "", "toString", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class UrlAnnotation {
    public static final int $stable = 0;
    private final String url;

    public UrlAnnotation(String str) {
        this.url = str;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof UrlAnnotation) && p.a(this.url, ((UrlAnnotation) other).url);
    }

    public final String getUrl() {
        return this.url;
    }

    public int hashCode() {
        return this.url.hashCode();
    }

    public String toString() {
        return c.u(new StringBuilder("UrlAnnotation(url="), this.url, ')');
    }
}
