package org.jsoup.nodes;

import java.io.IOException;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;

/* JADX INFO: loaded from: classes5.dex */
public class TextNode extends LeafNode {
    public TextNode(String str) {
        this.value = str;
    }

    public static TextNode createFromEncoded(String str) {
        return new TextNode(Entities.unescape(str));
    }

    public static boolean lastCharIsWhitespace(StringBuilder sb2) {
        return sb2.length() != 0 && sb2.charAt(sb2.length() - 1) == ' ';
    }

    public static String normaliseWhitespace(String str) {
        return StringUtil.normaliseWhitespace(str);
    }

    public static String stripLeadingWhitespace(String str) {
        return str.replaceFirst("^\\s+", "");
    }

    public String getWholeText() {
        return coreValue();
    }

    public boolean isBlank() {
        return StringUtil.isBlank(coreValue());
    }

    @Override // org.jsoup.nodes.Node
    public String nodeName() {
        return "#text";
    }

    @Override // org.jsoup.nodes.Node
    public void outerHtmlHead(Appendable appendable, int i10, Document.OutputSettings outputSettings) throws IOException {
        boolean z;
        boolean z5;
        boolean zPrettyPrint = outputSettings.prettyPrint();
        Node node = this.parentNode;
        Element element = node instanceof Element ? (Element) node : null;
        boolean z10 = zPrettyPrint && !Element.preserveWhitespace(node);
        boolean z11 = element != null && (element.tag().isBlock() || element.tag().formatAsBlock());
        if (z10) {
            boolean z12 = (z11 && this.siblingIndex == 0) || (this.parentNode instanceof Document);
            boolean z13 = z11 && nextSibling() == null;
            Node nodeNextSibling = nextSibling();
            Node nodePreviousSibling = previousSibling();
            boolean zIsBlank = isBlank();
            if ((((nodeNextSibling instanceof Element) && ((Element) nodeNextSibling).shouldIndent(outputSettings)) || (((nodeNextSibling instanceof TextNode) && ((TextNode) nodeNextSibling).isBlank()) || ((nodePreviousSibling instanceof Element) && (((Element) nodePreviousSibling).isBlock() || nodePreviousSibling.nameIs("br"))))) && zIsBlank) {
                return;
            }
            if ((nodePreviousSibling == null && element != null && element.tag().formatAsBlock() && !zIsBlank) || ((outputSettings.outline() && siblingNodes().size() > 0 && !zIsBlank) || (nodePreviousSibling != null && nodePreviousSibling.nameIs("br")))) {
                indent(appendable, i10, outputSettings);
            }
            z = z12;
            z5 = z13;
        } else {
            z = false;
            z5 = false;
        }
        Entities.escape(appendable, coreValue(), outputSettings, false, z10, z, z5);
    }

    @Override // org.jsoup.nodes.Node
    public void outerHtmlTail(Appendable appendable, int i10, Document.OutputSettings outputSettings) throws IOException {
    }

    public TextNode splitText(int i10) {
        String strCoreValue = coreValue();
        Validate.isTrue(i10 >= 0, "Split offset must be not be negative");
        Validate.isTrue(i10 < strCoreValue.length(), "Split offset must not be greater than current text length");
        String strSubstring = strCoreValue.substring(0, i10);
        String strSubstring2 = strCoreValue.substring(i10);
        text(strSubstring);
        TextNode textNode = new TextNode(strSubstring2);
        Node node = this.parentNode;
        if (node != null) {
            node.addChildren(siblingIndex() + 1, textNode);
        }
        return textNode;
    }

    public String text() {
        return StringUtil.normaliseWhitespace(getWholeText());
    }

    @Override // org.jsoup.nodes.Node
    public String toString() {
        return outerHtml();
    }

    public TextNode text(String str) {
        coreValue(str);
        return this;
    }

    @Override // org.jsoup.nodes.Node
    /* JADX INFO: renamed from: clone */
    public TextNode mo7038clone() {
        return (TextNode) super.mo7038clone();
    }
}
