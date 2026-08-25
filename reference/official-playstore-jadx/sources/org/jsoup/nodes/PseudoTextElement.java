package org.jsoup.nodes;

import org.jsoup.nodes.Document;
import org.jsoup.parser.Tag;

/* JADX INFO: loaded from: classes5.dex */
public class PseudoTextElement extends Element {
    public PseudoTextElement(Tag tag, String str, Attributes attributes) {
        super(tag, str, attributes);
    }

    @Override // org.jsoup.nodes.Element, org.jsoup.nodes.Node
    public void outerHtmlHead(Appendable appendable, int i10, Document.OutputSettings outputSettings) {
    }

    @Override // org.jsoup.nodes.Element, org.jsoup.nodes.Node
    public void outerHtmlTail(Appendable appendable, int i10, Document.OutputSettings outputSettings) {
    }
}
