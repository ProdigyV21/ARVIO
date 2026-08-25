package io.ktor.http;

import io.ktor.http.ContentDisposition;
import io.ktor.util.StringValuesSingleImpl;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\u0004H\u0016¨\u0006\t"}, d2 = {"Lio/ktor/http/HeadersSingleImpl;", "Lio/ktor/http/Headers;", "Lio/ktor/util/StringValuesSingleImpl;", ContentDisposition.Parameters.Name, "", "values", "", "(Ljava/lang/String;Ljava/util/List;)V", "toString", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class HeadersSingleImpl extends StringValuesSingleImpl implements Headers {
    public HeadersSingleImpl(String str, List<String> list) {
        super(true, str, list);
    }

    @Override // io.ktor.util.StringValuesSingleImpl
    public String toString() {
        return "Headers " + entries();
    }
}
