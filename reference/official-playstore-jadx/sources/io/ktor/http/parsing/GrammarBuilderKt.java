package io.ktor.http.parsing;

import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a(\u0010\u0006\u001a\u00020\u00052\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000¢\u0006\u0002\b\u0003H\u0000¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lkotlin/Function1;", "Lio/ktor/http/parsing/GrammarBuilder;", "Lx6/t0;", "Lx6/n;", "block", "Lio/ktor/http/parsing/Grammar;", "grammar", "(Lr7/l;)Lio/ktor/http/parsing/Grammar;", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class GrammarBuilderKt {
    public static final Grammar grammar(l<? super GrammarBuilder, t0> lVar) {
        GrammarBuilder grammarBuilder = new GrammarBuilder();
        lVar.invoke(grammarBuilder);
        return grammarBuilder.build();
    }
}
