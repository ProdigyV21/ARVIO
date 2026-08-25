package org.jsoup.select;

import androidx.fragment.app.a2;
import j$.util.function.Predicate$CC;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.PseudoTextElement;
import org.jsoup.nodes.TextNode;
import org.jsoup.nodes.XmlDeclaration;
import org.jsoup.parser.ParseSettings;

/* JADX INFO: loaded from: classes5.dex */
public abstract class Evaluator {

    public static final class AllElements extends Evaluator {
        @Override // org.jsoup.select.Evaluator
        public int cost() {
            return 10;
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean lambda$asPredicate$0(Element element, Element element2) {
            return true;
        }

        public String toString() {
            return "*";
        }
    }

    public static final class Attribute extends Evaluator {
        private final String key;

        public Attribute(String str) {
            this.key = str;
        }

        @Override // org.jsoup.select.Evaluator
        public int cost() {
            return 2;
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean lambda$asPredicate$0(Element element, Element element2) {
            return element2.hasAttr(this.key);
        }

        public String toString() {
            return a0.c.l("[", this.key, "]");
        }
    }

    public static abstract class AttributeKeyPair extends Evaluator {
        final String key;
        final String value;

        public AttributeKeyPair(String str, String str2) {
            this(str, str2, true);
        }

        public AttributeKeyPair(String str, String str2, boolean z) {
            Validate.notEmpty(str);
            Validate.notEmpty(str2);
            this.key = Normalizer.normalize(str);
            boolean z5 = (str2.startsWith("'") && str2.endsWith("'")) || (str2.startsWith("\"") && str2.endsWith("\""));
            str2 = z5 ? str2.substring(1, str2.length() - 1) : str2;
            this.value = z ? Normalizer.normalize(str2) : Normalizer.normalize(str2, z5);
        }
    }

    public static final class AttributeStarting extends Evaluator {
        private final String keyPrefix;

        public AttributeStarting(String str) {
            Validate.notNull(str);
            this.keyPrefix = Normalizer.lowerCase(str);
        }

        @Override // org.jsoup.select.Evaluator
        public int cost() {
            return 6;
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean lambda$asPredicate$0(Element element, Element element2) {
            Iterator<org.jsoup.nodes.Attribute> it = element2.attributes().asList().iterator();
            while (it.hasNext()) {
                if (Normalizer.lowerCase(it.next().getKey()).startsWith(this.keyPrefix)) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return a0.c.l("[^", this.keyPrefix, "]");
        }
    }

    public static final class AttributeWithValue extends AttributeKeyPair {
        public AttributeWithValue(String str, String str2) {
            super(str, str2);
        }

        @Override // org.jsoup.select.Evaluator
        public int cost() {
            return 3;
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean lambda$asPredicate$0(Element element, Element element2) {
            return element2.hasAttr(this.key) && this.value.equalsIgnoreCase(element2.attr(this.key).trim());
        }

        public String toString() {
            return a2.m("[", this.key, "=", this.value, "]");
        }
    }

    public static final class AttributeWithValueContaining extends AttributeKeyPair {
        public AttributeWithValueContaining(String str, String str2) {
            super(str, str2);
        }

        @Override // org.jsoup.select.Evaluator
        public int cost() {
            return 6;
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean lambda$asPredicate$0(Element element, Element element2) {
            return element2.hasAttr(this.key) && Normalizer.lowerCase(element2.attr(this.key)).contains(this.value);
        }

        public String toString() {
            return a2.m("[", this.key, "*=", this.value, "]");
        }
    }

    public static final class AttributeWithValueEnding extends AttributeKeyPair {
        public AttributeWithValueEnding(String str, String str2) {
            super(str, str2, false);
        }

        @Override // org.jsoup.select.Evaluator
        public int cost() {
            return 4;
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean lambda$asPredicate$0(Element element, Element element2) {
            return element2.hasAttr(this.key) && Normalizer.lowerCase(element2.attr(this.key)).endsWith(this.value);
        }

        public String toString() {
            return a2.m("[", this.key, "$=", this.value, "]");
        }
    }

    public static final class AttributeWithValueMatching extends Evaluator {
        final String key;
        final Pattern pattern;

        public AttributeWithValueMatching(String str, Pattern pattern) {
            this.key = Normalizer.normalize(str);
            this.pattern = pattern;
        }

        @Override // org.jsoup.select.Evaluator
        public int cost() {
            return 8;
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean lambda$asPredicate$0(Element element, Element element2) {
            return element2.hasAttr(this.key) && this.pattern.matcher(element2.attr(this.key)).find();
        }

        public String toString() {
            return a2.m("[", this.key, "~=", this.pattern.toString(), "]");
        }
    }

    public static final class AttributeWithValueNot extends AttributeKeyPair {
        public AttributeWithValueNot(String str, String str2) {
            super(str, str2);
        }

        @Override // org.jsoup.select.Evaluator
        public int cost() {
            return 3;
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean lambda$asPredicate$0(Element element, Element element2) {
            return !this.value.equalsIgnoreCase(element2.attr(this.key));
        }

        public String toString() {
            return a2.m("[", this.key, "!=", this.value, "]");
        }
    }

    public static final class AttributeWithValueStarting extends AttributeKeyPair {
        public AttributeWithValueStarting(String str, String str2) {
            super(str, str2, false);
        }

        @Override // org.jsoup.select.Evaluator
        public int cost() {
            return 4;
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean lambda$asPredicate$0(Element element, Element element2) {
            return element2.hasAttr(this.key) && Normalizer.lowerCase(element2.attr(this.key)).startsWith(this.value);
        }

        public String toString() {
            return a2.m("[", this.key, "^=", this.value, "]");
        }
    }

    public static final class Class extends Evaluator {
        private final String className;

        public Class(String str) {
            this.className = str;
        }

        @Override // org.jsoup.select.Evaluator
        public int cost() {
            return 6;
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean lambda$asPredicate$0(Element element, Element element2) {
            return element2.hasClass(this.className);
        }

        public String toString() {
            return androidx.compose.material3.d.C(".", this.className);
        }
    }

    public static final class ContainsData extends Evaluator {
        private final String searchText;

        public ContainsData(String str) {
            this.searchText = Normalizer.lowerCase(str);
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean lambda$asPredicate$0(Element element, Element element2) {
            return Normalizer.lowerCase(element2.data()).contains(this.searchText);
        }

        public String toString() {
            return a0.c.l(":containsData(", this.searchText, ")");
        }
    }

    public static final class ContainsOwnText extends Evaluator {
        private final String searchText;

        public ContainsOwnText(String str) {
            this.searchText = Normalizer.lowerCase(StringUtil.normaliseWhitespace(str));
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean lambda$asPredicate$0(Element element, Element element2) {
            return Normalizer.lowerCase(element2.ownText()).contains(this.searchText);
        }

        public String toString() {
            return a0.c.l(":containsOwn(", this.searchText, ")");
        }
    }

    public static final class ContainsText extends Evaluator {
        private final String searchText;

        public ContainsText(String str) {
            this.searchText = Normalizer.lowerCase(StringUtil.normaliseWhitespace(str));
        }

        @Override // org.jsoup.select.Evaluator
        public int cost() {
            return 10;
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean lambda$asPredicate$0(Element element, Element element2) {
            return Normalizer.lowerCase(element2.text()).contains(this.searchText);
        }

        public String toString() {
            return a0.c.l(":contains(", this.searchText, ")");
        }
    }

    public static final class ContainsWholeOwnText extends Evaluator {
        private final String searchText;

        public ContainsWholeOwnText(String str) {
            this.searchText = str;
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean lambda$asPredicate$0(Element element, Element element2) {
            return element2.wholeOwnText().contains(this.searchText);
        }

        public String toString() {
            return a0.c.l(":containsWholeOwnText(", this.searchText, ")");
        }
    }

    public static final class ContainsWholeText extends Evaluator {
        private final String searchText;

        public ContainsWholeText(String str) {
            this.searchText = str;
        }

        @Override // org.jsoup.select.Evaluator
        public int cost() {
            return 10;
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean lambda$asPredicate$0(Element element, Element element2) {
            return element2.wholeText().contains(this.searchText);
        }

        public String toString() {
            return a0.c.l(":containsWholeText(", this.searchText, ")");
        }
    }

    public static final class Id extends Evaluator {
        private final String id;

        public Id(String str) {
            this.id = str;
        }

        @Override // org.jsoup.select.Evaluator
        public int cost() {
            return 2;
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean lambda$asPredicate$0(Element element, Element element2) {
            return this.id.equals(element2.id());
        }

        public String toString() {
            return androidx.compose.material3.d.C("#", this.id);
        }
    }

    public static final class IndexEquals extends IndexEvaluator {
        public IndexEquals(int i10) {
            super(i10);
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean lambda$asPredicate$0(Element element, Element element2) {
            return element2.elementSiblingIndex() == this.index;
        }

        public String toString() {
            return String.format(":eq(%d)", Integer.valueOf(this.index));
        }
    }

    public static abstract class IndexEvaluator extends Evaluator {
        final int index;

        public IndexEvaluator(int i10) {
            this.index = i10;
        }
    }

    public static final class IndexGreaterThan extends IndexEvaluator {
        public IndexGreaterThan(int i10) {
            super(i10);
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean lambda$asPredicate$0(Element element, Element element2) {
            return element2.elementSiblingIndex() > this.index;
        }

        public String toString() {
            return String.format(":gt(%d)", Integer.valueOf(this.index));
        }
    }

    public static final class IndexLessThan extends IndexEvaluator {
        public IndexLessThan(int i10) {
            super(i10);
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean lambda$asPredicate$0(Element element, Element element2) {
            return element != element2 && element2.elementSiblingIndex() < this.index;
        }

        public String toString() {
            return String.format(":lt(%d)", Integer.valueOf(this.index));
        }
    }

    public static final class IsEmpty extends Evaluator {
        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean lambda$asPredicate$0(Element element, Element element2) {
            for (Node node : element2.childNodes()) {
                if (node instanceof TextNode) {
                    return ((TextNode) node).isBlank();
                }
                if (!(node instanceof Comment) && !(node instanceof XmlDeclaration) && !(node instanceof DocumentType)) {
                    return false;
                }
            }
            return true;
        }

        public String toString() {
            return ":empty";
        }
    }

    public static final class IsFirstChild extends Evaluator {
        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean lambda$asPredicate$0(Element element, Element element2) {
            Element elementParent = element2.parent();
            return (elementParent == null || (elementParent instanceof Document) || element2 != elementParent.firstElementChild()) ? false : true;
        }

        public String toString() {
            return ":first-child";
        }
    }

    public static final class IsFirstOfType extends IsNthOfType {
        public IsFirstOfType() {
            super(0, 1);
        }

        @Override // org.jsoup.select.Evaluator.CssNthEvaluator
        public String toString() {
            return ":first-of-type";
        }
    }

    public static final class IsLastChild extends Evaluator {
        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean lambda$asPredicate$0(Element element, Element element2) {
            Element elementParent = element2.parent();
            return (elementParent == null || (elementParent instanceof Document) || element2 != elementParent.lastElementChild()) ? false : true;
        }

        public String toString() {
            return ":last-child";
        }
    }

    public static final class IsLastOfType extends IsNthLastOfType {
        public IsLastOfType() {
            super(0, 1);
        }

        @Override // org.jsoup.select.Evaluator.CssNthEvaluator
        public String toString() {
            return ":last-of-type";
        }
    }

    public static final class IsNthChild extends CssNthEvaluator {
        public IsNthChild(int i10, int i11) {
            super(i10, i11);
        }

        @Override // org.jsoup.select.Evaluator.CssNthEvaluator
        public int calculatePosition(Element element, Element element2) {
            return element2.elementSiblingIndex() + 1;
        }

        @Override // org.jsoup.select.Evaluator.CssNthEvaluator
        public String getPseudoClass() {
            return "nth-child";
        }
    }

    public static final class IsNthLastChild extends CssNthEvaluator {
        public IsNthLastChild(int i10, int i11) {
            super(i10, i11);
        }

        @Override // org.jsoup.select.Evaluator.CssNthEvaluator
        public int calculatePosition(Element element, Element element2) {
            if (element2.parent() == null) {
                return 0;
            }
            return element2.parent().childrenSize() - element2.elementSiblingIndex();
        }

        @Override // org.jsoup.select.Evaluator.CssNthEvaluator
        public String getPseudoClass() {
            return "nth-last-child";
        }
    }

    public static class IsNthLastOfType extends CssNthEvaluator {
        public IsNthLastOfType(int i10, int i11) {
            super(i10, i11);
        }

        @Override // org.jsoup.select.Evaluator.CssNthEvaluator
        public int calculatePosition(Element element, Element element2) {
            int i10 = 0;
            if (element2.parent() == null) {
                return 0;
            }
            for (Element elementNextElementSibling = element2; elementNextElementSibling != null; elementNextElementSibling = elementNextElementSibling.nextElementSibling()) {
                if (elementNextElementSibling.normalName().equals(element2.normalName())) {
                    i10++;
                }
            }
            return i10;
        }

        @Override // org.jsoup.select.Evaluator.CssNthEvaluator
        public String getPseudoClass() {
            return "nth-last-of-type";
        }
    }

    public static class IsNthOfType extends CssNthEvaluator {
        public IsNthOfType(int i10, int i11) {
            super(i10, i11);
        }

        @Override // org.jsoup.select.Evaluator.CssNthEvaluator
        public int calculatePosition(Element element, Element element2) {
            Element elementParent = element2.parent();
            if (elementParent == null) {
                return 0;
            }
            int iChildNodeSize = elementParent.childNodeSize();
            int i10 = 0;
            for (int i11 = 0; i11 < iChildNodeSize; i11++) {
                Node nodeChildNode = elementParent.childNode(i11);
                if (nodeChildNode.normalName().equals(element2.normalName())) {
                    i10++;
                }
                if (nodeChildNode == element2) {
                    return i10;
                }
            }
            return i10;
        }

        @Override // org.jsoup.select.Evaluator.CssNthEvaluator
        public String getPseudoClass() {
            return "nth-of-type";
        }
    }

    public static final class IsOnlyChild extends Evaluator {
        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean lambda$asPredicate$0(Element element, Element element2) {
            Element elementParent = element2.parent();
            return (elementParent == null || (elementParent instanceof Document) || !element2.siblingElements().isEmpty()) ? false : true;
        }

        public String toString() {
            return ":only-child";
        }
    }

    public static final class IsOnlyOfType extends Evaluator {
        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean lambda$asPredicate$0(Element element, Element element2) {
            Element elementParent = element2.parent();
            if (elementParent != null && !(elementParent instanceof Document)) {
                int i10 = 0;
                for (Element elementFirstElementChild = elementParent.firstElementChild(); elementFirstElementChild != null; elementFirstElementChild = elementFirstElementChild.nextElementSibling()) {
                    if (elementFirstElementChild.normalName().equals(element2.normalName())) {
                        i10++;
                    }
                    if (i10 > 1) {
                        break;
                    }
                }
                if (i10 == 1) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return ":only-of-type";
        }
    }

    public static final class IsRoot extends Evaluator {
        @Override // org.jsoup.select.Evaluator
        public int cost() {
            return 1;
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean lambda$asPredicate$0(Element element, Element element2) {
            if (element instanceof Document) {
                element = element.firstElementChild();
            }
            return element2 == element;
        }

        public String toString() {
            return ":root";
        }
    }

    public static final class MatchText extends Evaluator {
        @Override // org.jsoup.select.Evaluator
        public int cost() {
            return -1;
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean lambda$asPredicate$0(Element element, Element element2) {
            if (element2 instanceof PseudoTextElement) {
                return true;
            }
            for (TextNode textNode : element2.textNodes()) {
                PseudoTextElement pseudoTextElement = new PseudoTextElement(org.jsoup.parser.Tag.valueOf(element2.tagName(), element2.tag().namespace(), ParseSettings.preserveCase), element2.baseUri(), element2.attributes());
                textNode.replaceWith(pseudoTextElement);
                pseudoTextElement.appendChild(textNode);
            }
            return false;
        }

        public String toString() {
            return ":matchText";
        }
    }

    public static final class Matches extends Evaluator {
        private final Pattern pattern;

        public Matches(Pattern pattern) {
            this.pattern = pattern;
        }

        @Override // org.jsoup.select.Evaluator
        public int cost() {
            return 8;
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean lambda$asPredicate$0(Element element, Element element2) {
            return this.pattern.matcher(element2.text()).find();
        }

        public String toString() {
            return ":matches(" + this.pattern + ")";
        }
    }

    public static final class MatchesOwn extends Evaluator {
        private final Pattern pattern;

        public MatchesOwn(Pattern pattern) {
            this.pattern = pattern;
        }

        @Override // org.jsoup.select.Evaluator
        public int cost() {
            return 7;
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean lambda$asPredicate$0(Element element, Element element2) {
            return this.pattern.matcher(element2.ownText()).find();
        }

        public String toString() {
            return ":matchesOwn(" + this.pattern + ")";
        }
    }

    public static final class MatchesWholeOwnText extends Evaluator {
        private final Pattern pattern;

        public MatchesWholeOwnText(Pattern pattern) {
            this.pattern = pattern;
        }

        @Override // org.jsoup.select.Evaluator
        public int cost() {
            return 7;
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean lambda$asPredicate$0(Element element, Element element2) {
            return this.pattern.matcher(element2.wholeOwnText()).find();
        }

        public String toString() {
            return ":matchesWholeOwnText(" + this.pattern + ")";
        }
    }

    public static final class MatchesWholeText extends Evaluator {
        private final Pattern pattern;

        public MatchesWholeText(Pattern pattern) {
            this.pattern = pattern;
        }

        @Override // org.jsoup.select.Evaluator
        public int cost() {
            return 8;
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean lambda$asPredicate$0(Element element, Element element2) {
            return this.pattern.matcher(element2.wholeText()).find();
        }

        public String toString() {
            return ":matchesWholeText(" + this.pattern + ")";
        }
    }

    public static final class Tag extends Evaluator {
        private final String tagName;

        public Tag(String str) {
            this.tagName = str;
        }

        @Override // org.jsoup.select.Evaluator
        public int cost() {
            return 1;
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean lambda$asPredicate$0(Element element, Element element2) {
            return element2.nameIs(this.tagName);
        }

        public String toString() {
            return this.tagName;
        }
    }

    public static final class TagEndsWith extends Evaluator {
        private final String tagName;

        public TagEndsWith(String str) {
            this.tagName = str;
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean lambda$asPredicate$0(Element element, Element element2) {
            return element2.normalName().endsWith(this.tagName);
        }

        public String toString() {
            return this.tagName;
        }
    }

    public Predicate<Element> asPredicate(final Element element) {
        return new Predicate() { // from class: org.jsoup.select.b
            public /* synthetic */ Predicate and(Predicate predicate) {
                return Predicate$CC.$default$and(this, predicate);
            }

            public /* synthetic */ Predicate negate() {
                return Predicate$CC.$default$negate(this);
            }

            public /* synthetic */ Predicate or(Predicate predicate) {
                return Predicate$CC.$default$or(this, predicate);
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f21016a.lambda$asPredicate$0(element, (Element) obj);
            }
        };
    }

    public int cost() {
        return 5;
    }

    /* JADX INFO: renamed from: matches, reason: merged with bridge method [inline-methods] */
    public abstract boolean lambda$asPredicate$0(Element element, Element element2);

    public void reset() {
    }

    public static abstract class CssNthEvaluator extends Evaluator {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected final int f21014a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        protected final int f21015b;

        public CssNthEvaluator(int i10, int i11) {
            this.f21014a = i10;
            this.f21015b = i11;
        }

        public abstract int calculatePosition(Element element, Element element2);

        public abstract String getPseudoClass();

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean lambda$asPredicate$0(Element element, Element element2) {
            Element elementParent = element2.parent();
            if (elementParent != null && !(elementParent instanceof Document)) {
                int iCalculatePosition = calculatePosition(element, element2);
                int i10 = this.f21014a;
                if (i10 == 0) {
                    return iCalculatePosition == this.f21015b;
                }
                int i11 = this.f21015b;
                if ((iCalculatePosition - i11) * i10 >= 0 && (iCalculatePosition - i11) % i10 == 0) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return this.f21014a == 0 ? String.format(":%s(%d)", getPseudoClass(), Integer.valueOf(this.f21015b)) : this.f21015b == 0 ? String.format(":%s(%dn)", getPseudoClass(), Integer.valueOf(this.f21014a)) : String.format(":%s(%dn%+d)", getPseudoClass(), Integer.valueOf(this.f21014a), Integer.valueOf(this.f21015b));
        }

        public CssNthEvaluator(int i10) {
            this(0, i10);
        }
    }
}
