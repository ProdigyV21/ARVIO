package io.ktor.http;

import io.ktor.util.StringValuesImpl;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.a0;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B!\u0012\u001a\b\u0002\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00060\u0004¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\u0005H\u0016¨\u0006\t"}, d2 = {"Lio/ktor/http/HeadersImpl;", "Lio/ktor/http/Headers;", "Lio/ktor/util/StringValuesImpl;", "values", "", "", "", "(Ljava/util/Map;)V", "toString", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class HeadersImpl extends StringValuesImpl implements Headers {
    /* JADX WARN: Multi-variable type inference failed */
    public HeadersImpl() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Override // io.ktor.util.StringValuesImpl
    public String toString() {
        return "Headers " + entries();
    }

    public /* synthetic */ HeadersImpl(Map map, int i10, h hVar) {
        this((i10 & 1) != 0 ? a0.f19683i : map);
    }

    public HeadersImpl(Map<String, ? extends List<String>> map) {
        super(true, map);
    }
}
