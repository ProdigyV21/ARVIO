package io.ktor.http;

import io.ktor.http.ContentDisposition;
import io.ktor.http.Parameters;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.b0;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010&\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0007\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000b0\t0\bH\u0016J\u0013\u0010\f\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0096\u0002J\u0018\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u000b2\u0006\u0010\u0010\u001a\u00020\nH\u0016J\b\u0010\u0011\u001a\u00020\u0004H\u0016J\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\n0\bH\u0016J\b\u0010\u0013\u001a\u00020\nH\u0016R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0014"}, d2 = {"Lio/ktor/http/EmptyParameters;", "Lio/ktor/http/Parameters;", "()V", "caseInsensitiveName", "", "getCaseInsensitiveName", "()Z", "entries", "", "", "", "", "equals", "other", "", "getAll", ContentDisposition.Parameters.Name, "isEmpty", "names", "toString", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class EmptyParameters implements Parameters {
    public static final EmptyParameters INSTANCE = new EmptyParameters();

    private EmptyParameters() {
    }

    @Override // io.ktor.util.StringValues
    public boolean contains(String str) {
        return Parameters.DefaultImpls.contains(this, str);
    }

    @Override // io.ktor.util.StringValues
    public Set<Map.Entry<String, List<String>>> entries() {
        return b0.f19686i;
    }

    public boolean equals(Object other) {
        return (other instanceof Parameters) && ((Parameters) other).isEmpty();
    }

    @Override // io.ktor.util.StringValues
    public void forEach(p<? super String, ? super List<String>, t0> pVar) {
        Parameters.DefaultImpls.forEach(this, pVar);
    }

    @Override // io.ktor.util.StringValues
    public String get(String str) {
        return Parameters.DefaultImpls.get(this, str);
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
        return "Parameters " + entries();
    }

    @Override // io.ktor.util.StringValues
    public boolean contains(String str, String str2) {
        return Parameters.DefaultImpls.contains(this, str, str2);
    }
}
