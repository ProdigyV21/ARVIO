package io.ktor.http;

import io.ktor.http.ContentDisposition;
import io.ktor.http.Headers;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.b0;
import r7.p;
import x6.e;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010&\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0007\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000b0\t0\bH\u0016J\u0018\u0010\f\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\u0004H\u0016J\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\bH\u0016J\b\u0010\u0010\u001a\u00020\nH\u0016R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lio/ktor/http/EmptyHeaders;", "Lio/ktor/http/Headers;", "()V", "caseInsensitiveName", "", "getCaseInsensitiveName", "()Z", "entries", "", "", "", "", "getAll", ContentDisposition.Parameters.Name, "isEmpty", "names", "toString", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
@e
public final class EmptyHeaders implements Headers {
    public static final EmptyHeaders INSTANCE = new EmptyHeaders();

    private EmptyHeaders() {
    }

    @Override // io.ktor.util.StringValues
    public boolean contains(String str) {
        return Headers.DefaultImpls.contains(this, str);
    }

    @Override // io.ktor.util.StringValues
    public Set<Map.Entry<String, List<String>>> entries() {
        return b0.f19686i;
    }

    @Override // io.ktor.util.StringValues
    public void forEach(p<? super String, ? super List<String>, t0> pVar) {
        Headers.DefaultImpls.forEach(this, pVar);
    }

    @Override // io.ktor.util.StringValues
    public String get(String str) {
        return Headers.DefaultImpls.get(this, str);
    }

    @Override // io.ktor.util.StringValues
    public List<String> getAll(String name) {
        return null;
    }

    @Override // io.ktor.util.StringValues
    public boolean getCaseInsensitiveName() {
        return true;
    }

    @Override // io.ktor.util.StringValues
    public boolean isEmpty() {
        return true;
    }

    @Override // io.ktor.util.StringValues
    public Set<String> names() {
        return b0.f19686i;
    }

    public String toString() {
        return "Headers " + entries();
    }

    @Override // io.ktor.util.StringValues
    public boolean contains(String str, String str2) {
        return Headers.DefaultImpls.contains(this, str, str2);
    }
}
