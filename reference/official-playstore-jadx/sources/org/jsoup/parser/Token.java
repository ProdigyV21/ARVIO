package org.jsoup.parser;

import a0.c;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.internal.SharedConstants;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Range;

/* JADX INFO: loaded from: classes5.dex */
abstract class Token {
    static final int Unset = -1;
    private int endPos;
    private int startPos;
    final TokenType type;

    public static final class CData extends Character {
        public CData(String str) {
            data(str);
        }

        @Override // org.jsoup.parser.Token.Character
        public String toString() {
            return c.p(new StringBuilder("<![CDATA["), getData(), "]]>");
        }
    }

    public static class Character extends Token implements Cloneable {
        private String data;

        public Character() {
            super(TokenType.Character);
        }

        public Character data(String str) {
            this.data = str;
            return this;
        }

        public String getData() {
            return this.data;
        }

        @Override // org.jsoup.parser.Token
        public Token reset() {
            super.reset();
            this.data = null;
            return this;
        }

        public String toString() {
            return getData();
        }

        public Character clone() {
            try {
                return (Character) super.clone();
            } catch (CloneNotSupportedException e5) {
                throw new RuntimeException(e5);
            }
        }
    }

    public static final class Doctype extends Token {
        boolean forceQuirks;
        final StringBuilder name;
        String pubSysKey;
        final StringBuilder publicIdentifier;
        final StringBuilder systemIdentifier;

        public Doctype() {
            super(TokenType.Doctype);
            this.name = new StringBuilder();
            this.pubSysKey = null;
            this.publicIdentifier = new StringBuilder();
            this.systemIdentifier = new StringBuilder();
            this.forceQuirks = false;
        }

        public String getName() {
            return this.name.toString();
        }

        public String getPubSysKey() {
            return this.pubSysKey;
        }

        public String getPublicIdentifier() {
            return this.publicIdentifier.toString();
        }

        public String getSystemIdentifier() {
            return this.systemIdentifier.toString();
        }

        public boolean isForceQuirks() {
            return this.forceQuirks;
        }

        @Override // org.jsoup.parser.Token
        public Token reset() {
            super.reset();
            Token.reset(this.name);
            this.pubSysKey = null;
            Token.reset(this.publicIdentifier);
            Token.reset(this.systemIdentifier);
            this.forceQuirks = false;
            return this;
        }

        public String toString() {
            return c.p(new StringBuilder("<!doctype "), getName(), ">");
        }
    }

    public static final class EOF extends Token {
        public EOF() {
            super(TokenType.EOF);
        }

        @Override // org.jsoup.parser.Token
        public Token reset() {
            super.reset();
            return this;
        }

        public String toString() {
            return "";
        }
    }

    public static final class EndTag extends Tag {
        public EndTag(TreeBuilder treeBuilder) {
            super(TokenType.EndTag, treeBuilder);
        }

        @Override // org.jsoup.parser.Token.Tag
        public String toString() {
            return c.p(new StringBuilder("</"), toStringName(), ">");
        }
    }

    public static final class StartTag extends Tag {
        public StartTag(TreeBuilder treeBuilder) {
            super(TokenType.StartTag, treeBuilder);
        }

        public StartTag nameAttr(String str, Attributes attributes) {
            this.tagName = str;
            this.attributes = attributes;
            this.normalName = ParseSettings.normalName(str);
            return this;
        }

        @Override // org.jsoup.parser.Token.Tag
        public String toString() {
            String str = isSelfClosing() ? "/>" : ">";
            if (!hasAttributes() || this.attributes.size() <= 0) {
                return c.p(new StringBuilder("<"), toStringName(), str);
            }
            return "<" + toStringName() + " " + this.attributes.toString() + str;
        }

        @Override // org.jsoup.parser.Token.Tag, org.jsoup.parser.Token
        public Tag reset() {
            super.reset();
            this.attributes = null;
            return this;
        }
    }

    public static abstract class Tag extends Token {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final int MaxAttributes = 512;
        private String attrName;
        int attrNameEnd;
        private final StringBuilder attrNameSb;
        int attrNameStart;
        int attrValEnd;
        int attrValStart;
        private String attrValue;
        private final StringBuilder attrValueSb;
        Attributes attributes;
        private boolean hasAttrName;
        private boolean hasAttrValue;
        private boolean hasEmptyAttrValue;
        protected String normalName;
        boolean selfClosing;
        protected String tagName;
        final boolean trackSource;
        final TreeBuilder treeBuilder;

        public Tag(TokenType tokenType, TreeBuilder treeBuilder) {
            super(tokenType);
            this.selfClosing = false;
            this.attrNameSb = new StringBuilder();
            this.hasAttrName = false;
            this.attrValueSb = new StringBuilder();
            this.hasAttrValue = false;
            this.hasEmptyAttrValue = false;
            this.treeBuilder = treeBuilder;
            this.trackSource = treeBuilder.trackSourceRange;
        }

        private void ensureAttrName(int i10, int i11) {
            this.hasAttrName = true;
            String str = this.attrName;
            if (str != null) {
                this.attrNameSb.append(str);
                this.attrName = null;
            }
            if (this.trackSource) {
                int i12 = this.attrNameStart;
                if (i12 > -1) {
                    i10 = i12;
                }
                this.attrNameStart = i10;
                this.attrNameEnd = i11;
            }
        }

        private void ensureAttrValue(int i10, int i11) {
            this.hasAttrValue = true;
            String str = this.attrValue;
            if (str != null) {
                this.attrValueSb.append(str);
                this.attrValue = null;
            }
            if (this.trackSource) {
                int i12 = this.attrValStart;
                if (i12 > -1) {
                    i10 = i12;
                }
                this.attrValStart = i10;
                this.attrValEnd = i11;
            }
        }

        private void resetPendingAttr() {
            Token.reset(this.attrNameSb);
            this.attrName = null;
            this.hasAttrName = false;
            Token.reset(this.attrValueSb);
            this.attrValue = null;
            this.hasEmptyAttrValue = false;
            this.hasAttrValue = false;
            if (this.trackSource) {
                this.attrValEnd = -1;
                this.attrValStart = -1;
                this.attrNameEnd = -1;
                this.attrNameStart = -1;
            }
        }

        private void trackAttributeRange(String str) {
            if (this.trackSource && isStartTag()) {
                TreeBuilder treeBuilder = asStartTag().treeBuilder;
                CharacterReader characterReader = treeBuilder.reader;
                boolean zPreserveAttributeCase = treeBuilder.settings.preserveAttributeCase();
                Map map = (Map) this.attributes.userData(SharedConstants.AttrRangeKey);
                if (map == null) {
                    map = new HashMap();
                    this.attributes.userData(SharedConstants.AttrRangeKey, map);
                }
                if (!zPreserveAttributeCase) {
                    str = Normalizer.lowerCase(str);
                }
                if (map.containsKey(str)) {
                    return;
                }
                if (!this.hasAttrValue) {
                    int i10 = this.attrNameEnd;
                    this.attrValEnd = i10;
                    this.attrValStart = i10;
                }
                int i11 = this.attrNameStart;
                Range.Position position = new Range.Position(i11, characterReader.lineNumber(i11), characterReader.columnNumber(this.attrNameStart));
                int i12 = this.attrNameEnd;
                Range range = new Range(position, new Range.Position(i12, characterReader.lineNumber(i12), characterReader.columnNumber(this.attrNameEnd)));
                int i13 = this.attrValStart;
                Range.Position position2 = new Range.Position(i13, characterReader.lineNumber(i13), characterReader.columnNumber(this.attrValStart));
                int i14 = this.attrValEnd;
                map.put(str, new Range.AttributeRange(range, new Range(position2, new Range.Position(i14, characterReader.lineNumber(i14), characterReader.columnNumber(this.attrValEnd)))));
            }
        }

        public final void appendAttributeName(String str, int i10, int i11) {
            String strReplace = str.replace((char) 0, (char) 65533);
            ensureAttrName(i10, i11);
            if (this.attrNameSb.length() == 0) {
                this.attrName = strReplace;
            } else {
                this.attrNameSb.append(strReplace);
            }
        }

        public final void appendAttributeValue(String str, int i10, int i11) {
            ensureAttrValue(i10, i11);
            if (this.attrValueSb.length() == 0) {
                this.attrValue = str;
            } else {
                this.attrValueSb.append(str);
            }
        }

        public final void appendTagName(String str) {
            String strReplace = str.replace((char) 0, (char) 65533);
            String str2 = this.tagName;
            if (str2 != null) {
                strReplace = str2.concat(strReplace);
            }
            this.tagName = strReplace;
            this.normalName = ParseSettings.normalName(strReplace);
        }

        public final void finaliseTag() {
            if (this.hasAttrName) {
                newAttribute();
            }
        }

        public final boolean hasAttribute(String str) {
            Attributes attributes = this.attributes;
            return attributes != null && attributes.hasKey(str);
        }

        public final boolean hasAttributeIgnoreCase(String str) {
            Attributes attributes = this.attributes;
            return attributes != null && attributes.hasKeyIgnoreCase(str);
        }

        public final boolean hasAttributes() {
            return this.attributes != null;
        }

        public final boolean isSelfClosing() {
            return this.selfClosing;
        }

        public final String name() {
            String str = this.tagName;
            Validate.isFalse(str == null || str.length() == 0);
            return this.tagName;
        }

        public final void newAttribute() {
            if (this.attributes == null) {
                this.attributes = new Attributes();
            }
            if (this.hasAttrName && this.attributes.size() < 512) {
                String strTrim = (this.attrNameSb.length() > 0 ? this.attrNameSb.toString() : this.attrName).trim();
                if (strTrim.length() > 0) {
                    this.attributes.add(strTrim, this.hasAttrValue ? this.attrValueSb.length() > 0 ? this.attrValueSb.toString() : this.attrValue : this.hasEmptyAttrValue ? "" : null);
                    trackAttributeRange(strTrim);
                }
            }
            resetPendingAttr();
        }

        public final String normalName() {
            return this.normalName;
        }

        public final void setEmptyAttributeValue() {
            this.hasEmptyAttrValue = true;
        }

        public abstract String toString();

        public final String toStringName() {
            String str = this.tagName;
            return str != null ? str : "[unset]";
        }

        @Override // org.jsoup.parser.Token
        public Tag reset() {
            super.reset();
            this.tagName = null;
            this.normalName = null;
            this.selfClosing = false;
            this.attributes = null;
            resetPendingAttr();
            return this;
        }

        public final Tag name(String str) {
            this.tagName = str;
            this.normalName = ParseSettings.normalName(str);
            return this;
        }

        public final void appendTagName(char c10) {
            appendTagName(String.valueOf(c10));
        }

        public final void appendAttributeValue(char c10, int i10, int i11) {
            ensureAttrValue(i10, i11);
            this.attrValueSb.append(c10);
        }

        public final void appendAttributeName(char c10, int i10, int i11) {
            ensureAttrName(i10, i11);
            this.attrNameSb.append(c10);
        }

        public final void appendAttributeValue(int[] iArr, int i10, int i11) {
            ensureAttrValue(i10, i11);
            for (int i12 : iArr) {
                this.attrValueSb.appendCodePoint(i12);
            }
        }
    }

    public enum TokenType {
        Doctype,
        StartTag,
        EndTag,
        Comment,
        Character,
        EOF
    }

    public final Character asCharacter() {
        return (Character) this;
    }

    public final Comment asComment() {
        return (Comment) this;
    }

    public final Doctype asDoctype() {
        return (Doctype) this;
    }

    public final EndTag asEndTag() {
        return (EndTag) this;
    }

    public final StartTag asStartTag() {
        return (StartTag) this;
    }

    public int endPos() {
        return this.endPos;
    }

    public final boolean isCData() {
        return this instanceof CData;
    }

    public final boolean isCharacter() {
        return this.type == TokenType.Character;
    }

    public final boolean isComment() {
        return this.type == TokenType.Comment;
    }

    public final boolean isDoctype() {
        return this.type == TokenType.Doctype;
    }

    public final boolean isEOF() {
        return this.type == TokenType.EOF;
    }

    public final boolean isEndTag() {
        return this.type == TokenType.EndTag;
    }

    public final boolean isStartTag() {
        return this.type == TokenType.StartTag;
    }

    public Token reset() {
        this.startPos = -1;
        this.endPos = -1;
        return this;
    }

    public int startPos() {
        return this.startPos;
    }

    public String tokenType() {
        return getClass().getSimpleName();
    }

    private Token(TokenType tokenType) {
        this.endPos = -1;
        this.type = tokenType;
    }

    public void endPos(int i10) {
        this.endPos = i10;
    }

    public void startPos(int i10) {
        this.startPos = i10;
    }

    public static void reset(StringBuilder sb2) {
        if (sb2 != null) {
            sb2.delete(0, sb2.length());
        }
    }

    public static final class Comment extends Token {
        boolean bogus;
        private final StringBuilder data;
        private String dataS;

        public Comment() {
            super(TokenType.Comment);
            this.data = new StringBuilder();
            this.bogus = false;
        }

        private void ensureData() {
            String str = this.dataS;
            if (str != null) {
                this.data.append(str);
                this.dataS = null;
            }
        }

        public Comment append(String str) {
            ensureData();
            if (this.data.length() == 0) {
                this.dataS = str;
                return this;
            }
            this.data.append(str);
            return this;
        }

        public String getData() {
            String str = this.dataS;
            return str != null ? str : this.data.toString();
        }

        @Override // org.jsoup.parser.Token
        public Token reset() {
            super.reset();
            Token.reset(this.data);
            this.dataS = null;
            this.bogus = false;
            return this;
        }

        public String toString() {
            return c.p(new StringBuilder("<!--"), getData(), "-->");
        }

        public Comment append(char c10) {
            ensureData();
            this.data.append(c10);
            return this;
        }
    }
}
