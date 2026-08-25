package io.ktor.http;

import io.ktor.http.ContentDisposition;
import io.ktor.util.StringValues;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.s;
import kotlin.collections.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010&\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001c\n\u0002\b\r\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\n2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0018\u0010\u000e\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\bH\u0096\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u001f\u0010\u000e\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000e\u0010\u0011J\u0015\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u0012H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0015\u0010\u0016J'\u0010\u0018\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\n0\u00170\u0012H\u0016¢\u0006\u0004\b\u0018\u0010\u0014J \u0010\u001a\u001a\u00020\u00192\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\bH\u0096\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u001a\u0010\u001c\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\bH\u0096\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ\u001f\u0010\u001e\u001a\u00020\u00192\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\bH\u0016¢\u0006\u0004\b\u001e\u0010\u001bJ\u0017\u0010!\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\u001fH\u0016¢\u0006\u0004\b!\u0010\"J%\u0010!\u001a\u00020\u00192\u0006\u0010\t\u001a\u00020\b2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\b0#H\u0016¢\u0006\u0004\b!\u0010%J\u0017\u0010&\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\u001fH\u0016¢\u0006\u0004\b&\u0010\"J%\u0010&\u001a\u00020\u00192\u0006\u0010\t\u001a\u00020\b2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\b0#H\u0016¢\u0006\u0004\b&\u0010%J\u0017\u0010'\u001a\u00020\u00192\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b'\u0010(J\u001f\u0010'\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\bH\u0016¢\u0006\u0004\b'\u0010\u0011J\u000f\u0010)\u001a\u00020\u0019H\u0016¢\u0006\u0004\b)\u0010*J\u000f\u0010+\u001a\u00020\u0019H\u0016¢\u0006\u0004\b+\u0010*R\u0014\u0010\u0002\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010,R\u001a\u0010-\u001a\u00020\r8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b-\u0010.\u001a\u0004\b/\u0010\u0016¨\u00060"}, d2 = {"Lio/ktor/http/UrlDecodedParametersBuilder;", "Lio/ktor/http/ParametersBuilder;", "encodedParametersBuilder", "<init>", "(Lio/ktor/http/ParametersBuilder;)V", "Lio/ktor/http/Parameters;", "build", "()Lio/ktor/http/Parameters;", "", ContentDisposition.Parameters.Name, "", "getAll", "(Ljava/lang/String;)Ljava/util/List;", "", "contains", "(Ljava/lang/String;)Z", "value", "(Ljava/lang/String;Ljava/lang/String;)Z", "", "names", "()Ljava/util/Set;", "isEmpty", "()Z", "", "entries", "Lx6/t0;", "set", "(Ljava/lang/String;Ljava/lang/String;)V", "get", "(Ljava/lang/String;)Ljava/lang/String;", "append", "Lio/ktor/util/StringValues;", "stringValues", "appendAll", "(Lio/ktor/util/StringValues;)V", "", "values", "(Ljava/lang/String;Ljava/lang/Iterable;)V", "appendMissing", "remove", "(Ljava/lang/String;)V", "removeKeysWithNoEntries", "()V", "clear", "Lio/ktor/http/ParametersBuilder;", "caseInsensitiveName", "Z", "getCaseInsensitiveName", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class UrlDecodedParametersBuilder implements ParametersBuilder {
    private final boolean caseInsensitiveName;
    private final ParametersBuilder encodedParametersBuilder;

    public UrlDecodedParametersBuilder(ParametersBuilder parametersBuilder) {
        this.encodedParametersBuilder = parametersBuilder;
        this.caseInsensitiveName = parametersBuilder.getCaseInsensitiveName();
    }

    @Override // io.ktor.util.StringValuesBuilder
    public void append(String name, String value) {
        this.encodedParametersBuilder.append(CodecsKt.encodeURLParameter$default(name, false, 1, null), CodecsKt.encodeURLParameterValue(value));
    }

    @Override // io.ktor.util.StringValuesBuilder
    public void appendAll(StringValues stringValues) {
        UrlDecodedParametersBuilderKt.appendAllEncoded(this.encodedParametersBuilder, stringValues);
    }

    @Override // io.ktor.util.StringValuesBuilder
    public void appendMissing(StringValues stringValues) {
        this.encodedParametersBuilder.appendMissing(UrlDecodedParametersBuilderKt.encodeParameters(stringValues).build());
    }

    @Override // io.ktor.util.StringValuesBuilder
    public void clear() {
        this.encodedParametersBuilder.clear();
    }

    @Override // io.ktor.util.StringValuesBuilder
    public boolean contains(String name) {
        return this.encodedParametersBuilder.contains(CodecsKt.encodeURLParameter$default(name, false, 1, null));
    }

    @Override // io.ktor.util.StringValuesBuilder
    public Set<Map.Entry<String, List<String>>> entries() {
        return UrlDecodedParametersBuilderKt.decodeParameters(this.encodedParametersBuilder).entries();
    }

    @Override // io.ktor.util.StringValuesBuilder
    public String get(String name) {
        String str = this.encodedParametersBuilder.get(CodecsKt.encodeURLParameter$default(name, false, 1, null));
        if (str != null) {
            return CodecsKt.decodeURLQueryComponent$default(str, 0, 0, true, null, 11, null);
        }
        return null;
    }

    @Override // io.ktor.util.StringValuesBuilder
    public List<String> getAll(String name) {
        List<String> all = this.encodedParametersBuilder.getAll(CodecsKt.encodeURLParameter$default(name, false, 1, null));
        if (all == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(s.U(all, 10));
        Iterator<T> it = all.iterator();
        while (it.hasNext()) {
            arrayList.add(CodecsKt.decodeURLQueryComponent$default((String) it.next(), 0, 0, true, null, 11, null));
        }
        return arrayList;
    }

    @Override // io.ktor.util.StringValuesBuilder
    public boolean getCaseInsensitiveName() {
        return this.caseInsensitiveName;
    }

    @Override // io.ktor.util.StringValuesBuilder
    public boolean isEmpty() {
        return this.encodedParametersBuilder.isEmpty();
    }

    @Override // io.ktor.util.StringValuesBuilder
    public Set<String> names() {
        Set<String> setNames = this.encodedParametersBuilder.names();
        ArrayList arrayList = new ArrayList(s.U(setNames, 10));
        Iterator<T> it = setNames.iterator();
        while (it.hasNext()) {
            arrayList.add(CodecsKt.decodeURLQueryComponent$default((String) it.next(), 0, 0, false, null, 15, null));
        }
        return x.g1(arrayList);
    }

    @Override // io.ktor.util.StringValuesBuilder
    public void remove(String name) {
        this.encodedParametersBuilder.remove(CodecsKt.encodeURLParameter$default(name, false, 1, null));
    }

    @Override // io.ktor.util.StringValuesBuilder
    public void removeKeysWithNoEntries() {
        this.encodedParametersBuilder.removeKeysWithNoEntries();
    }

    @Override // io.ktor.util.StringValuesBuilder
    public void set(String name, String value) {
        this.encodedParametersBuilder.set(CodecsKt.encodeURLParameter$default(name, false, 1, null), CodecsKt.encodeURLParameterValue(value));
    }

    @Override // io.ktor.util.StringValuesBuilder
    public void appendAll(String name, Iterable<String> values) {
        ParametersBuilder parametersBuilder = this.encodedParametersBuilder;
        String strEncodeURLParameter$default = CodecsKt.encodeURLParameter$default(name, false, 1, null);
        ArrayList arrayList = new ArrayList(s.U(values, 10));
        Iterator<String> it = values.iterator();
        while (it.hasNext()) {
            arrayList.add(CodecsKt.encodeURLParameterValue(it.next()));
        }
        parametersBuilder.appendAll(strEncodeURLParameter$default, arrayList);
    }

    @Override // io.ktor.util.StringValuesBuilder
    public void appendMissing(String name, Iterable<String> values) {
        ParametersBuilder parametersBuilder = this.encodedParametersBuilder;
        String strEncodeURLParameter$default = CodecsKt.encodeURLParameter$default(name, false, 1, null);
        ArrayList arrayList = new ArrayList(s.U(values, 10));
        Iterator<String> it = values.iterator();
        while (it.hasNext()) {
            arrayList.add(CodecsKt.encodeURLParameterValue(it.next()));
        }
        parametersBuilder.appendMissing(strEncodeURLParameter$default, arrayList);
    }

    @Override // io.ktor.util.StringValuesBuilder
    public Parameters build() {
        return UrlDecodedParametersBuilderKt.decodeParameters(this.encodedParametersBuilder);
    }

    @Override // io.ktor.util.StringValuesBuilder
    public boolean contains(String name, String value) {
        return this.encodedParametersBuilder.contains(CodecsKt.encodeURLParameter$default(name, false, 1, null), CodecsKt.encodeURLParameterValue(value));
    }

    @Override // io.ktor.util.StringValuesBuilder
    public boolean remove(String name, String value) {
        return this.encodedParametersBuilder.remove(CodecsKt.encodeURLParameter$default(name, false, 1, null), CodecsKt.encodeURLParameterValue(value));
    }
}
