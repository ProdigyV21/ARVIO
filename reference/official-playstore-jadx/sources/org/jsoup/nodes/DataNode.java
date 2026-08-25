package org.jsoup.nodes;

import java.io.IOException;
import org.jsoup.nodes.Document;

/* JADX INFO: loaded from: classes5.dex */
public class DataNode extends LeafNode {
    public DataNode(String str) {
        this.value = str;
    }

    public String getWholeData() {
        return coreValue();
    }

    @Override // org.jsoup.nodes.Node
    public String nodeName() {
        return "#data";
    }

    @Override // org.jsoup.nodes.Node
    public void outerHtmlHead(Appendable appendable, int i10, Document.OutputSettings outputSettings) throws IOException {
        String wholeData = getWholeData();
        if (outputSettings.syntax() != Document.OutputSettings.Syntax.xml || wholeData.contains("<![CDATA[")) {
            appendable.append(getWholeData());
            return;
        }
        if (parentNameIs("script")) {
            appendable.append("//<![CDATA[\n").append(wholeData).append("\n//]]>");
        } else if (parentNameIs("style")) {
            appendable.append("/*<![CDATA[*/\n").append(wholeData).append("\n/*]]>*/");
        } else {
            appendable.append("<![CDATA[").append(wholeData).append("]]>");
        }
    }

    @Override // org.jsoup.nodes.Node
    public void outerHtmlTail(Appendable appendable, int i10, Document.OutputSettings outputSettings) {
    }

    public DataNode setWholeData(String str) {
        coreValue(str);
        return this;
    }

    @Override // org.jsoup.nodes.Node
    public String toString() {
        return outerHtml();
    }

    @Override // org.jsoup.nodes.Node
    /* JADX INFO: renamed from: clone */
    public DataNode mo7038clone() {
        return (DataNode) super.mo7038clone();
    }
}
