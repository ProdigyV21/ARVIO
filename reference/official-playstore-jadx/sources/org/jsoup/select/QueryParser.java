package org.jsoup.select;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.internal.StringUtil;
import org.jsoup.parser.TokenQueue;
import org.jsoup.select.CombiningEvaluator;
import org.jsoup.select.Evaluator;
import org.jsoup.select.Selector;
import org.jsoup.select.StructuralEvaluator;

/* JADX INFO: loaded from: classes5.dex */
public class QueryParser {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final List<Evaluator> evals = new ArrayList();
    private final String query;
    private final TokenQueue tq;
    private static final char[] Combinators = {',', '>', '+', '~', ' '};
    private static final String[] AttributeEvals = {"=", "!=", "^=", "$=", "*=", "~="};
    private static final Pattern NTH_AB = Pattern.compile("(([+-])?(\\d+)?)n(\\s*([+-])?\\s*\\d+)?", 2);
    private static final Pattern NTH_B = Pattern.compile("([+-])?(\\d+)");

    private QueryParser(String str) {
        Validate.notEmpty(str);
        String strTrim = str.trim();
        this.query = strTrim;
        this.tq = new TokenQueue(strTrim);
    }

    private Evaluator byAttribute() {
        TokenQueue tokenQueue = new TokenQueue(this.tq.chompBalanced('[', ']'));
        String strConsumeToAny = tokenQueue.consumeToAny(AttributeEvals);
        Validate.notEmpty(strConsumeToAny);
        tokenQueue.consumeWhitespace();
        if (tokenQueue.isEmpty()) {
            return strConsumeToAny.startsWith("^") ? new Evaluator.AttributeStarting(strConsumeToAny.substring(1)) : strConsumeToAny.equals("*") ? new Evaluator.AttributeStarting("") : new Evaluator.Attribute(strConsumeToAny);
        }
        if (tokenQueue.matchChomp("=")) {
            return new Evaluator.AttributeWithValue(strConsumeToAny, tokenQueue.remainder());
        }
        if (tokenQueue.matchChomp("!=")) {
            return new Evaluator.AttributeWithValueNot(strConsumeToAny, tokenQueue.remainder());
        }
        if (tokenQueue.matchChomp("^=")) {
            return new Evaluator.AttributeWithValueStarting(strConsumeToAny, tokenQueue.remainder());
        }
        if (tokenQueue.matchChomp("$=")) {
            return new Evaluator.AttributeWithValueEnding(strConsumeToAny, tokenQueue.remainder());
        }
        if (tokenQueue.matchChomp("*=")) {
            return new Evaluator.AttributeWithValueContaining(strConsumeToAny, tokenQueue.remainder());
        }
        if (tokenQueue.matchChomp("~=")) {
            return new Evaluator.AttributeWithValueMatching(strConsumeToAny, Pattern.compile(tokenQueue.remainder()));
        }
        throw new Selector.SelectorParseException("Could not parse attribute query '%s': unexpected token at '%s'", this.query, tokenQueue.remainder());
    }

    private Evaluator byClass() {
        String strConsumeCssIdentifier = this.tq.consumeCssIdentifier();
        Validate.notEmpty(strConsumeCssIdentifier);
        return new Evaluator.Class(strConsumeCssIdentifier.trim());
    }

    private Evaluator byId() {
        String strConsumeCssIdentifier = this.tq.consumeCssIdentifier();
        Validate.notEmpty(strConsumeCssIdentifier);
        return new Evaluator.Id(strConsumeCssIdentifier);
    }

    private Evaluator byTag() {
        String strNormalize = Normalizer.normalize(this.tq.consumeElementSelector());
        Validate.notEmpty(strNormalize);
        if (strNormalize.startsWith("*|")) {
            return new CombiningEvaluator.Or(new Evaluator.Tag(strNormalize.substring(2)), new Evaluator.TagEndsWith(strNormalize.replace("*|", ":")));
        }
        if (strNormalize.contains("|")) {
            strNormalize = strNormalize.replace("|", ":");
        }
        return new Evaluator.Tag(strNormalize);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00c8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void combinator(char r10) {
        /*
            Method dump skipped, instruction units count: 207
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.select.QueryParser.combinator(char):void");
    }

    private Evaluator consumeEvaluator() {
        if (this.tq.matchChomp("#")) {
            return byId();
        }
        if (this.tq.matchChomp(".")) {
            return byClass();
        }
        if (this.tq.matchesWord() || this.tq.matches("*|")) {
            return byTag();
        }
        if (this.tq.matches("[")) {
            return byAttribute();
        }
        if (this.tq.matchChomp("*")) {
            return new Evaluator.AllElements();
        }
        if (this.tq.matchChomp(":")) {
            return parsePseudoSelector();
        }
        throw new Selector.SelectorParseException("Could not parse query '%s': unexpected token at '%s'", this.query, this.tq.remainder());
    }

    private int consumeIndex() {
        String strTrim = consumeParens().trim();
        Validate.isTrue(StringUtil.isNumeric(strTrim), "Index must be numeric");
        return Integer.parseInt(strTrim);
    }

    private String consumeParens() {
        return this.tq.chompBalanced('(', ')');
    }

    private String consumeSubQuery() {
        StringBuilder sbBorrowBuilder = StringUtil.borrowBuilder();
        boolean z = false;
        while (!this.tq.isEmpty()) {
            if (!this.tq.matchesAny(Combinators)) {
                if (this.tq.matches("(")) {
                    sbBorrowBuilder.append("(");
                    sbBorrowBuilder.append(this.tq.chompBalanced('(', ')'));
                    sbBorrowBuilder.append(")");
                } else if (this.tq.matches("[")) {
                    sbBorrowBuilder.append("[");
                    sbBorrowBuilder.append(this.tq.chompBalanced('[', ']'));
                    sbBorrowBuilder.append("]");
                } else {
                    sbBorrowBuilder.append(this.tq.consume());
                }
                z = true;
            } else {
                if (z) {
                    break;
                }
                sbBorrowBuilder.append(this.tq.consume());
            }
        }
        return StringUtil.releaseBuilder(sbBorrowBuilder);
    }

    private Evaluator contains(boolean z) {
        String str = z ? ":containsOwn" : ":contains";
        String strUnescape = TokenQueue.unescape(consumeParens());
        Validate.notEmpty(strUnescape, str.concat("(text) query must not be empty"));
        return z ? new Evaluator.ContainsOwnText(strUnescape) : new Evaluator.ContainsText(strUnescape);
    }

    private Evaluator containsData() {
        String strUnescape = TokenQueue.unescape(consumeParens());
        Validate.notEmpty(strUnescape, ":containsData(text) query must not be empty");
        return new Evaluator.ContainsData(strUnescape);
    }

    private Evaluator containsWholeText(boolean z) {
        String str = z ? ":containsWholeOwnText" : ":containsWholeText";
        String strUnescape = TokenQueue.unescape(consumeParens());
        Validate.notEmpty(strUnescape, str.concat("(text) query must not be empty"));
        return z ? new Evaluator.ContainsWholeOwnText(strUnescape) : new Evaluator.ContainsWholeText(strUnescape);
    }

    private Evaluator cssNthChild(boolean z, boolean z5) {
        String strNormalize = Normalizer.normalize(consumeParens());
        Matcher matcher = NTH_AB.matcher(strNormalize);
        Matcher matcher2 = NTH_B.matcher(strNormalize);
        int i10 = 2;
        int i11 = 1;
        if (!"odd".equals(strNormalize)) {
            if ("even".equals(strNormalize)) {
                i11 = 0;
            } else if (matcher.matches()) {
                int i12 = matcher.group(3) != null ? Integer.parseInt(matcher.group(1).replaceFirst("^\\+", "")) : 1;
                i11 = matcher.group(4) != null ? Integer.parseInt(matcher.group(4).replaceFirst("^\\+", "")) : 0;
                i10 = i12;
            } else {
                if (!matcher2.matches()) {
                    throw new Selector.SelectorParseException("Could not parse nth-index '%s': unexpected format", strNormalize);
                }
                i11 = Integer.parseInt(matcher2.group().replaceFirst("^\\+", ""));
                i10 = 0;
            }
        }
        return z5 ? z ? new Evaluator.IsNthLastOfType(i10, i11) : new Evaluator.IsNthOfType(i10, i11) : z ? new Evaluator.IsNthLastChild(i10, i11) : new Evaluator.IsNthChild(i10, i11);
    }

    private Evaluator has() {
        String strConsumeParens = consumeParens();
        Validate.notEmpty(strConsumeParens, ":has(selector) sub-select must not be empty");
        return new StructuralEvaluator.Has(parse(strConsumeParens));
    }

    private Evaluator is() {
        String strConsumeParens = consumeParens();
        Validate.notEmpty(strConsumeParens, ":is(selector) sub-select must not be empty");
        return new StructuralEvaluator.Is(parse(strConsumeParens));
    }

    private Evaluator matches(boolean z) {
        String str = z ? ":matchesOwn" : ":matches";
        String strConsumeParens = consumeParens();
        Validate.notEmpty(strConsumeParens, str.concat("(regex) query must not be empty"));
        return z ? new Evaluator.MatchesOwn(Pattern.compile(strConsumeParens)) : new Evaluator.Matches(Pattern.compile(strConsumeParens));
    }

    private Evaluator matchesWholeText(boolean z) {
        String str = z ? ":matchesWholeOwnText" : ":matchesWholeText";
        String strConsumeParens = consumeParens();
        Validate.notEmpty(strConsumeParens, str.concat("(regex) query must not be empty"));
        return z ? new Evaluator.MatchesWholeOwnText(Pattern.compile(strConsumeParens)) : new Evaluator.MatchesWholeText(Pattern.compile(strConsumeParens));
    }

    private Evaluator not() {
        String strConsumeParens = consumeParens();
        Validate.notEmpty(strConsumeParens, ":not(selector) subselect must not be empty");
        return new StructuralEvaluator.Not(parse(strConsumeParens));
    }

    public static Evaluator parse(String str) {
        try {
            return new QueryParser(str).parse();
        } catch (IllegalArgumentException e5) {
            throw new Selector.SelectorParseException(e5.getMessage());
        }
    }

    private Evaluator parsePseudoSelector() {
        String strConsumeCssIdentifier = this.tq.consumeCssIdentifier();
        strConsumeCssIdentifier.getClass();
        switch (strConsumeCssIdentifier) {
            case "containsData":
                return containsData();
            case "first-child":
                return new Evaluator.IsFirstChild();
            case "matchesWholeText":
                return matchesWholeText(false);
            case "nth-child":
                return cssNthChild(false, false);
            case "nth-last-child":
                return cssNthChild(true, false);
            case "only-child":
                return new Evaluator.IsOnlyChild();
            case "nth-of-type":
                return cssNthChild(false, true);
            case "nth-last-of-type":
                return cssNthChild(true, true);
            case "contains":
                return contains(false);
            case "containsWholeOwnText":
                return containsWholeText(true);
            case "eq":
                return new Evaluator.IndexEquals(consumeIndex());
            case "gt":
                return new Evaluator.IndexGreaterThan(consumeIndex());
            case "is":
                return is();
            case "lt":
                return new Evaluator.IndexLessThan(consumeIndex());
            case "has":
                return has();
            case "not":
                return not();
            case "root":
                return new Evaluator.IsRoot();
            case "empty":
                return new Evaluator.IsEmpty();
            case "containsOwn":
                return contains(true);
            case "matchText":
                return new Evaluator.MatchText();
            case "last-child":
                return new Evaluator.IsLastChild();
            case "matches":
                return matches(false);
            case "matchesWholeOwnText":
                return matchesWholeText(true);
            case "first-of-type":
                return new Evaluator.IsFirstOfType();
            case "only-of-type":
                return new Evaluator.IsOnlyOfType();
            case "matchesOwn":
                return matches(true);
            case "containsWholeText":
                return containsWholeText(false);
            case "last-of-type":
                return new Evaluator.IsLastOfType();
            default:
                throw new Selector.SelectorParseException("Could not parse query '%s': unexpected token at '%s'", this.query, this.tq.remainder());
        }
    }

    public String toString() {
        return this.query;
    }

    public Evaluator parse() {
        this.tq.consumeWhitespace();
        if (this.tq.matchesAny(Combinators)) {
            this.evals.add(new StructuralEvaluator.Root());
            combinator(this.tq.consume());
        } else {
            this.evals.add(consumeEvaluator());
        }
        while (!this.tq.isEmpty()) {
            boolean zConsumeWhitespace = this.tq.consumeWhitespace();
            if (this.tq.matchesAny(Combinators)) {
                combinator(this.tq.consume());
            } else if (zConsumeWhitespace) {
                combinator(' ');
            } else {
                this.evals.add(consumeEvaluator());
            }
        }
        if (this.evals.size() == 1) {
            return this.evals.get(0);
        }
        return new CombiningEvaluator.And(this.evals);
    }
}
