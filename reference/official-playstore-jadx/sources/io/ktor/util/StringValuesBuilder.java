package io.ktor.util;

import io.ktor.http.ContentDisposition;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010&\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001c\n\u0002\b\f\bf\u0018\u00002\u00020\u0001J\u001f\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0002H¦\u0002¢\u0006\u0004\b\b\u0010\tJ\u001f\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0002H&¢\u0006\u0004\b\b\u0010\u000bJ\u0015\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\fH&¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\u0007H&¢\u0006\u0004\b\u000f\u0010\u0010J'\u0010\u0012\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00040\u00110\fH&¢\u0006\u0004\b\u0012\u0010\u000eJ \u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0002H¦\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0016\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u0002H¦\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u001f\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0002H&¢\u0006\u0004\b\u0018\u0010\u0015J\u0017\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u0019H&¢\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u0019H&¢\u0006\u0004\b\u001d\u0010\u001cJ%\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00020\u001eH&¢\u0006\u0004\b\u001b\u0010 J%\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00020\u001eH&¢\u0006\u0004\b\u001d\u0010 J\u0017\u0010!\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b!\u0010\"J\u000f\u0010#\u001a\u00020\u0013H&¢\u0006\u0004\b#\u0010$J\u001f\u0010!\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0002H&¢\u0006\u0004\b!\u0010\u000bJ\u000f\u0010%\u001a\u00020\u0013H&¢\u0006\u0004\b%\u0010$J\u000f\u0010&\u001a\u00020\u0019H&¢\u0006\u0004\b&\u0010'R\u0014\u0010)\u001a\u00020\u00078&X¦\u0004¢\u0006\u0006\u001a\u0004\b(\u0010\u0010¨\u0006*"}, d2 = {"Lio/ktor/util/StringValuesBuilder;", "", "", ContentDisposition.Parameters.Name, "", "getAll", "(Ljava/lang/String;)Ljava/util/List;", "", "contains", "(Ljava/lang/String;)Z", "value", "(Ljava/lang/String;Ljava/lang/String;)Z", "", "names", "()Ljava/util/Set;", "isEmpty", "()Z", "", "entries", "Lx6/t0;", "set", "(Ljava/lang/String;Ljava/lang/String;)V", "get", "(Ljava/lang/String;)Ljava/lang/String;", "append", "Lio/ktor/util/StringValues;", "stringValues", "appendAll", "(Lio/ktor/util/StringValues;)V", "appendMissing", "", "values", "(Ljava/lang/String;Ljava/lang/Iterable;)V", "remove", "(Ljava/lang/String;)V", "removeKeysWithNoEntries", "()V", "clear", "build", "()Lio/ktor/util/StringValues;", "getCaseInsensitiveName", "caseInsensitiveName", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface StringValuesBuilder {
    void append(String name, String value);

    void appendAll(StringValues stringValues);

    void appendAll(String name, Iterable<String> values);

    void appendMissing(StringValues stringValues);

    void appendMissing(String name, Iterable<String> values);

    StringValues build();

    void clear();

    boolean contains(String name);

    boolean contains(String name, String value);

    Set<Map.Entry<String, List<String>>> entries();

    String get(String name);

    List<String> getAll(String name);

    boolean getCaseInsensitiveName();

    boolean isEmpty();

    Set<String> names();

    void remove(String name);

    boolean remove(String name, String value);

    void removeKeysWithNoEntries();

    void set(String name, String value);
}
