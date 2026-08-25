package io.ktor.http.parsing;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lio/ktor/http/parsing/RangeGrammar;", "Lio/ktor/http/parsing/Grammar;", "from", "", "to", "(CC)V", "getFrom", "()C", "getTo", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class RangeGrammar extends Grammar {
    private final char from;
    private final char to;

    public RangeGrammar(char c10, char c11) {
        super(null);
        this.from = c10;
        this.to = c11;
    }

    public final char getFrom() {
        return this.from;
    }

    public final char getTo() {
        return this.to;
    }
}
