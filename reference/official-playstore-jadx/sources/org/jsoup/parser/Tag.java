package org.jsoup.parser;

import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.LinkHeader;
import j$.util.function.Consumer$CC;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.nodes.b;

/* JADX INFO: loaded from: classes5.dex */
public class Tag implements Cloneable {
    private static final Map<String, Tag> Tags = new HashMap();
    private static final String[] blockTags;
    private static final String[] emptyTags;
    private static final String[] formListedTags;
    private static final String[] formSubmitTags;
    private static final String[] formatAsInlineTags;
    private static final String[] inlineTags;
    private static final Map<String, String[]> namespaces;
    private static final String[] preserveWhitespaceTags;
    private String namespace;
    private final String normalName;
    private String tagName;
    private boolean isBlock = true;
    private boolean formatAsBlock = true;
    private boolean empty = false;
    private boolean selfClosing = false;
    private boolean preserveWhitespace = false;
    private boolean formList = false;
    private boolean formSubmit = false;

    static {
        String[] strArr = {"html", TtmlNode.TAG_HEAD, TtmlNode.TAG_BODY, "frameset", "script", "noscript", "style", "meta", "link", LinkHeader.Parameters.Title, "frame", "noframes", "section", "nav", "aside", "hgroup", "header", "footer", TtmlNode.TAG_P, "h1", "h2", "h3", "h4", "h5", "h6", "ul", "ol", "pre", TtmlNode.TAG_DIV, "blockquote", "hr", "address", "figure", "figcaption", "form", "fieldset", "ins", "del", CmcdConfiguration.KEY_DEADLINE, "dt", "dd", "li", "table", MediaTrack.ROLE_CAPTION, "thead", "tfoot", "tbody", "colgroup", "col", "tr", "th", "td", "video", "audio", "canvas", "details", "menu", "plaintext", "template", "article", MediaTrack.ROLE_MAIN, "svg", "math", TtmlNode.CENTER, "template", "dir", "applet", "marquee", "listing"};
        blockTags = strArr;
        String[] strArr2 = {"object", TtmlNode.RUBY_BASE, "font", TtmlNode.TAG_TT, CmcdData.OBJECT_TYPE_INIT_SEGMENT, "b", "u", "big", "small", "em", "strong", "dfn", "code", "samp", "kbd", "var", "cite", "abbr", "time", "acronym", "mark", TtmlNode.ATTR_TTS_RUBY, "rt", "rp", "rtc", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "img", "br", "wbr", "map", "q", "sub", "sup", "bdo", "iframe", "embed", TtmlNode.TAG_SPAN, "input", "select", "textarea", "label", "button", "optgroup", "option", "legend", "datalist", "keygen", "output", "progress", "meter", "area", "param", "source", "track", "summary", "command", "device", "area", "basefont", "bgsound", "menuitem", "param", "source", "track", "data", "bdi", CmcdData.STREAMING_FORMAT_SS, "strike", "nobr", "rb", "text", "mi", "mo", "msup", "mn", "mtext"};
        inlineTags = strArr2;
        String[] strArr3 = {"meta", "link", TtmlNode.RUBY_BASE, "frame", "img", "br", "wbr", "embed", "hr", "input", "keygen", "col", "command", "device", "area", "basefont", "bgsound", "menuitem", "param", "source", "track"};
        emptyTags = strArr3;
        String[] strArr4 = {LinkHeader.Parameters.Title, CmcdData.OBJECT_TYPE_AUDIO_ONLY, TtmlNode.TAG_P, "h1", "h2", "h3", "h4", "h5", "h6", "pre", "address", "li", "th", "td", "script", "style", "ins", "del", CmcdData.STREAMING_FORMAT_SS};
        formatAsInlineTags = strArr4;
        String[] strArr5 = {"pre", "plaintext", LinkHeader.Parameters.Title, "textarea"};
        preserveWhitespaceTags = strArr5;
        String[] strArr6 = {"button", "fieldset", "input", "keygen", "object", "output", "select", "textarea"};
        formListedTags = strArr6;
        String[] strArr7 = {"input", "keygen", "object", "select", "textarea"};
        formSubmitTags = strArr7;
        HashMap map = new HashMap();
        namespaces = map;
        map.put(Parser.NamespaceMathml, new String[]{"math", "mi", "mo", "msup", "mn", "mtext"});
        map.put(Parser.NamespaceSvg, new String[]{"svg", "text"});
        final int i10 = 0;
        setupTags(strArr, new Consumer() { // from class: org.jsoup.parser.a
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Tag tag = (Tag) obj;
                switch (i10) {
                    case 0:
                        Tag.lambda$static$0(tag);
                        break;
                    case 1:
                        Tag.lambda$static$1(tag);
                        break;
                    case 2:
                        tag.empty = true;
                        break;
                    case 3:
                        tag.formatAsBlock = false;
                        break;
                    case 4:
                        tag.preserveWhitespace = true;
                        break;
                    case 5:
                        tag.formList = true;
                        break;
                    default:
                        tag.formSubmit = true;
                        break;
                }
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                int i11 = i10;
                return Consumer$CC.$default$andThen(this, consumer);
            }
        });
        final int i11 = 1;
        setupTags(strArr2, new Consumer() { // from class: org.jsoup.parser.a
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Tag tag = (Tag) obj;
                switch (i11) {
                    case 0:
                        Tag.lambda$static$0(tag);
                        break;
                    case 1:
                        Tag.lambda$static$1(tag);
                        break;
                    case 2:
                        tag.empty = true;
                        break;
                    case 3:
                        tag.formatAsBlock = false;
                        break;
                    case 4:
                        tag.preserveWhitespace = true;
                        break;
                    case 5:
                        tag.formList = true;
                        break;
                    default:
                        tag.formSubmit = true;
                        break;
                }
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                int i112 = i11;
                return Consumer$CC.$default$andThen(this, consumer);
            }
        });
        final int i12 = 2;
        setupTags(strArr3, new Consumer() { // from class: org.jsoup.parser.a
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Tag tag = (Tag) obj;
                switch (i12) {
                    case 0:
                        Tag.lambda$static$0(tag);
                        break;
                    case 1:
                        Tag.lambda$static$1(tag);
                        break;
                    case 2:
                        tag.empty = true;
                        break;
                    case 3:
                        tag.formatAsBlock = false;
                        break;
                    case 4:
                        tag.preserveWhitespace = true;
                        break;
                    case 5:
                        tag.formList = true;
                        break;
                    default:
                        tag.formSubmit = true;
                        break;
                }
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                int i112 = i12;
                return Consumer$CC.$default$andThen(this, consumer);
            }
        });
        final int i13 = 3;
        setupTags(strArr4, new Consumer() { // from class: org.jsoup.parser.a
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Tag tag = (Tag) obj;
                switch (i13) {
                    case 0:
                        Tag.lambda$static$0(tag);
                        break;
                    case 1:
                        Tag.lambda$static$1(tag);
                        break;
                    case 2:
                        tag.empty = true;
                        break;
                    case 3:
                        tag.formatAsBlock = false;
                        break;
                    case 4:
                        tag.preserveWhitespace = true;
                        break;
                    case 5:
                        tag.formList = true;
                        break;
                    default:
                        tag.formSubmit = true;
                        break;
                }
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                int i112 = i13;
                return Consumer$CC.$default$andThen(this, consumer);
            }
        });
        final int i14 = 4;
        setupTags(strArr5, new Consumer() { // from class: org.jsoup.parser.a
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Tag tag = (Tag) obj;
                switch (i14) {
                    case 0:
                        Tag.lambda$static$0(tag);
                        break;
                    case 1:
                        Tag.lambda$static$1(tag);
                        break;
                    case 2:
                        tag.empty = true;
                        break;
                    case 3:
                        tag.formatAsBlock = false;
                        break;
                    case 4:
                        tag.preserveWhitespace = true;
                        break;
                    case 5:
                        tag.formList = true;
                        break;
                    default:
                        tag.formSubmit = true;
                        break;
                }
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                int i112 = i14;
                return Consumer$CC.$default$andThen(this, consumer);
            }
        });
        final int i15 = 5;
        setupTags(strArr6, new Consumer() { // from class: org.jsoup.parser.a
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Tag tag = (Tag) obj;
                switch (i15) {
                    case 0:
                        Tag.lambda$static$0(tag);
                        break;
                    case 1:
                        Tag.lambda$static$1(tag);
                        break;
                    case 2:
                        tag.empty = true;
                        break;
                    case 3:
                        tag.formatAsBlock = false;
                        break;
                    case 4:
                        tag.preserveWhitespace = true;
                        break;
                    case 5:
                        tag.formList = true;
                        break;
                    default:
                        tag.formSubmit = true;
                        break;
                }
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                int i112 = i15;
                return Consumer$CC.$default$andThen(this, consumer);
            }
        });
        final int i16 = 6;
        setupTags(strArr7, new Consumer() { // from class: org.jsoup.parser.a
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Tag tag = (Tag) obj;
                switch (i16) {
                    case 0:
                        Tag.lambda$static$0(tag);
                        break;
                    case 1:
                        Tag.lambda$static$1(tag);
                        break;
                    case 2:
                        tag.empty = true;
                        break;
                    case 3:
                        tag.formatAsBlock = false;
                        break;
                    case 4:
                        tag.preserveWhitespace = true;
                        break;
                    case 5:
                        tag.formList = true;
                        break;
                    default:
                        tag.formSubmit = true;
                        break;
                }
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                int i112 = i16;
                return Consumer$CC.$default$andThen(this, consumer);
            }
        });
        for (Map.Entry entry : map.entrySet()) {
            setupTags((String[]) entry.getValue(), new b(entry, 1));
        }
    }

    private Tag(String str, String str2) {
        this.tagName = str;
        this.normalName = Normalizer.lowerCase(str);
        this.namespace = str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$static$0(Tag tag) {
        tag.isBlock = true;
        tag.formatAsBlock = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$static$1(Tag tag) {
        tag.isBlock = false;
        tag.formatAsBlock = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$static$7(Map.Entry entry, Tag tag) {
        tag.namespace = (String) entry.getKey();
    }

    private static void setupTags(String[] strArr, Consumer<Tag> consumer) {
        for (String str : strArr) {
            Map<String, Tag> map = Tags;
            Tag tag = map.get(str);
            if (tag == null) {
                tag = new Tag(str, Parser.NamespaceHtml);
                map.put(tag.tagName, tag);
            }
            consumer.accept(tag);
        }
    }

    public static Tag valueOf(String str, String str2, ParseSettings parseSettings) {
        Validate.notEmpty(str);
        Validate.notNull(str2);
        Map<String, Tag> map = Tags;
        Tag tag = map.get(str);
        if (tag != null && tag.namespace.equals(str2)) {
            return tag;
        }
        String strNormalizeTag = parseSettings.normalizeTag(str);
        Validate.notEmpty(strNormalizeTag);
        String strLowerCase = Normalizer.lowerCase(strNormalizeTag);
        Tag tag2 = map.get(strLowerCase);
        if (tag2 == null || !tag2.namespace.equals(str2)) {
            Tag tag3 = new Tag(strNormalizeTag, str2);
            tag3.isBlock = false;
            return tag3;
        }
        if (!parseSettings.preserveTagCase() || strNormalizeTag.equals(strLowerCase)) {
            return tag2;
        }
        Tag tagClone = tag2.clone();
        tagClone.tagName = strNormalizeTag;
        return tagClone;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Tag)) {
            return false;
        }
        Tag tag = (Tag) obj;
        return this.tagName.equals(tag.tagName) && this.empty == tag.empty && this.formatAsBlock == tag.formatAsBlock && this.isBlock == tag.isBlock && this.preserveWhitespace == tag.preserveWhitespace && this.selfClosing == tag.selfClosing && this.formList == tag.formList && this.formSubmit == tag.formSubmit;
    }

    public boolean formatAsBlock() {
        return this.formatAsBlock;
    }

    public String getName() {
        return this.tagName;
    }

    public int hashCode() {
        return (((((((((((((this.tagName.hashCode() * 31) + (this.isBlock ? 1 : 0)) * 31) + (this.formatAsBlock ? 1 : 0)) * 31) + (this.empty ? 1 : 0)) * 31) + (this.selfClosing ? 1 : 0)) * 31) + (this.preserveWhitespace ? 1 : 0)) * 31) + (this.formList ? 1 : 0)) * 31) + (this.formSubmit ? 1 : 0);
    }

    public boolean isBlock() {
        return this.isBlock;
    }

    public boolean isEmpty() {
        return this.empty;
    }

    public boolean isFormListed() {
        return this.formList;
    }

    public boolean isFormSubmittable() {
        return this.formSubmit;
    }

    public boolean isInline() {
        return !this.isBlock;
    }

    public boolean isKnownTag() {
        return Tags.containsKey(this.tagName);
    }

    public boolean isSelfClosing() {
        return this.empty || this.selfClosing;
    }

    public String namespace() {
        return this.namespace;
    }

    public String normalName() {
        return this.normalName;
    }

    public boolean preserveWhitespace() {
        return this.preserveWhitespace;
    }

    public Tag setSelfClosing() {
        this.selfClosing = true;
        return this;
    }

    public String toString() {
        return this.tagName;
    }

    public static boolean isKnownTag(String str) {
        return Tags.containsKey(str);
    }

    public Tag clone() {
        try {
            return (Tag) super.clone();
        } catch (CloneNotSupportedException e5) {
            throw new RuntimeException(e5);
        }
    }

    public static Tag valueOf(String str) {
        return valueOf(str, Parser.NamespaceHtml, ParseSettings.preserveCase);
    }

    public static Tag valueOf(String str, ParseSettings parseSettings) {
        return valueOf(str, Parser.NamespaceHtml, parseSettings);
    }
}
