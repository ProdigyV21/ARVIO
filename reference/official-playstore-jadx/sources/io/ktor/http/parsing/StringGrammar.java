package io.ktor.http.parsing;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lio/ktor/http/parsing/StringGrammar;", "Lio/ktor/http/parsing/Grammar;", "value", "", "(Ljava/lang/String;)V", "getValue", "()Ljava/lang/String;", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class StringGrammar extends Grammar {
    private final String value;

    public StringGrammar(String str) {
        super(null);
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }
}
