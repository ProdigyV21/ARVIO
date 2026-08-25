package io.ktor.http.parsing;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lio/ktor/http/parsing/NamedGrammar;", "Lio/ktor/http/parsing/Grammar;", ContentDisposition.Parameters.Name, "", "grammar", "(Ljava/lang/String;Lio/ktor/http/parsing/Grammar;)V", "getGrammar", "()Lio/ktor/http/parsing/Grammar;", "getName", "()Ljava/lang/String;", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class NamedGrammar extends Grammar {
    private final Grammar grammar;
    private final String name;

    public NamedGrammar(String str, Grammar grammar) {
        super(null);
        this.name = str;
        this.grammar = grammar;
    }

    public final Grammar getGrammar() {
        return this.grammar;
    }

    public final String getName() {
        return this.name;
    }
}
