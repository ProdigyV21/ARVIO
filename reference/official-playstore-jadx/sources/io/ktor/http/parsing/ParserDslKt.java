package io.ktor.http.parsing;

import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.a;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\u001a\u0017\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0017\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u0002\u0010\u0006\u001a.\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00000\f2\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\nH\u0000¢\u0006\u0004\b\u0002\u0010\r\u001a\u001c\u0010\u000e\u001a\u00020\u0000*\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u0000H\u0080\u0004¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u001c\u0010\u000e\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0080\u0004¢\u0006\u0004\b\u000e\u0010\u0010\u001a\u001c\u0010\u000e\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004H\u0080\u0004¢\u0006\u0004\b\u000e\u0010\u0011\u001a\u001c\u0010\u0012\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0080\u0004¢\u0006\u0004\b\u0012\u0010\u0010\u001a\u001c\u0010\u0012\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004H\u0080\u0004¢\u0006\u0004\b\u0012\u0010\u0011\u001a\u001c\u0010\u0012\u001a\u00020\u0000*\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u0000H\u0080\u0004¢\u0006\u0004\b\u0012\u0010\u000f\u001a\u0017\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0013\u0010\u0003\u001a\u0017\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0014\u0010\u0003\u001a\u001b\u0010\u0016\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u0016\u0010\u0011\u001a\u0017\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u0017\u0010\u0006\u001a\u001c\u0010\u001a\u001a\u00020\u0000*\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0080\u0004¢\u0006\u0004\b\u001a\u0010\u001b\u001a,\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00000\u001e\"\n\b\u0000\u0010\u001d\u0018\u0001*\u00020\u001c*\b\u0012\u0004\u0012\u00020\u00000\u001eH\u0080\b¢\u0006\u0004\b\u001f\u0010 ¨\u0006!"}, d2 = {"Lio/ktor/http/parsing/Grammar;", "grammar", "maybe", "(Lio/ktor/http/parsing/Grammar;)Lio/ktor/http/parsing/Grammar;", "", "value", "(Ljava/lang/String;)Lio/ktor/http/parsing/Grammar;", "Lkotlin/Function1;", "Lio/ktor/http/parsing/GrammarBuilder;", "Lx6/t0;", "Lx6/n;", "block", "Lkotlin/Function0;", "(Lr7/l;)Lr7/a;", "then", "(Ljava/lang/String;Lio/ktor/http/parsing/Grammar;)Lio/ktor/http/parsing/Grammar;", "(Lio/ktor/http/parsing/Grammar;Lio/ktor/http/parsing/Grammar;)Lio/ktor/http/parsing/Grammar;", "(Lio/ktor/http/parsing/Grammar;Ljava/lang/String;)Lio/ktor/http/parsing/Grammar;", "or", "many", "atLeastOne", ContentDisposition.Parameters.Name, "named", "anyOf", "", "other", "to", "(CC)Lio/ktor/http/parsing/Grammar;", "Lio/ktor/http/parsing/ComplexGrammar;", "T", "", "flatten", "(Ljava/util/List;)Ljava/util/List;", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ParserDslKt {

    /* JADX INFO: renamed from: io.ktor.http.parsing.ParserDslKt$maybe$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/http/parsing/Grammar;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements a<Grammar> {
        final /* synthetic */ l<GrammarBuilder, t0> $block;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(l<? super GrammarBuilder, t0> lVar) {
            super(0);
            this.$block = lVar;
        }

        @Override // r7.a
        public final Grammar invoke() {
            GrammarBuilder grammarBuilder = new GrammarBuilder();
            this.$block.invoke(grammarBuilder);
            return ParserDslKt.maybe(grammarBuilder.build());
        }
    }

    public static final Grammar anyOf(String str) {
        return new AnyOfGrammar(str);
    }

    public static final Grammar atLeastOne(Grammar grammar) {
        return new AtLeastOne(grammar);
    }

    public static final <T extends ComplexGrammar> List<Grammar> flatten(List<? extends Grammar> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        if (!it.hasNext()) {
            return arrayList;
        }
        p.h();
        throw null;
    }

    public static final Grammar many(Grammar grammar) {
        return new ManyGrammar(grammar);
    }

    public static final Grammar maybe(Grammar grammar) {
        return new MaybeGrammar(grammar);
    }

    public static final Grammar named(Grammar grammar, String str) {
        return new NamedGrammar(str, grammar);
    }

    public static final Grammar or(Grammar grammar, Grammar grammar2) {
        return new OrGrammar(t7.a.E(grammar, grammar2));
    }

    public static final Grammar then(String str, Grammar grammar) {
        return then(new StringGrammar(str), grammar);
    }

    public static final Grammar to(char c10, char c11) {
        return new RangeGrammar(c10, c11);
    }

    public static final Grammar maybe(String str) {
        return new MaybeGrammar(new StringGrammar(str));
    }

    public static final Grammar or(Grammar grammar, String str) {
        return or(grammar, new StringGrammar(str));
    }

    public static final Grammar then(Grammar grammar, Grammar grammar2) {
        return new SequenceGrammar(t7.a.E(grammar, grammar2));
    }

    public static final a<Grammar> maybe(l<? super GrammarBuilder, t0> lVar) {
        return new AnonymousClass1(lVar);
    }

    public static final Grammar or(String str, Grammar grammar) {
        return or(new StringGrammar(str), grammar);
    }

    public static final Grammar then(Grammar grammar, String str) {
        return then(grammar, new StringGrammar(str));
    }
}
