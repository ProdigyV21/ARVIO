package io.ktor.http;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lio/ktor/http/ContentTypeMatcher;", "", "contains", "", "contentType", "Lio/ktor/http/ContentType;", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface ContentTypeMatcher {
    boolean contains(ContentType contentType);
}
