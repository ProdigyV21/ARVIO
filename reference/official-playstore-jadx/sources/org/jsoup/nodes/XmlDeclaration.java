package org.jsoup.nodes;

import java.io.IOException;
import org.jsoup.SerializationException;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;

/* JADX INFO: loaded from: classes5.dex */
public class XmlDeclaration extends LeafNode {
    private final boolean isProcessingInstruction;

    public XmlDeclaration(String str, boolean z) {
        Validate.notNull(str);
        this.value = str;
        this.isProcessingInstruction = z;
    }

    public String getWholeDeclaration() {
        StringBuilder sbBorrowBuilder = StringUtil.borrowBuilder();
        try {
            getWholeDeclaration(sbBorrowBuilder, new Document.OutputSettings());
            return StringUtil.releaseBuilder(sbBorrowBuilder).trim();
        } catch (IOException e5) {
            throw new SerializationException(e5);
        }
    }

    public String name() {
        return coreValue();
    }

    @Override // org.jsoup.nodes.Node
    public String nodeName() {
        return "#declaration";
    }

    @Override // org.jsoup.nodes.Node
    public void outerHtmlHead(Appendable appendable, int i10, Document.OutputSettings outputSettings) throws IOException {
        appendable.append("<").append(this.isProcessingInstruction ? "!" : "?").append(coreValue());
        getWholeDeclaration(appendable, outputSettings);
        appendable.append(this.isProcessingInstruction ? "!" : "?").append(">");
    }

    @Override // org.jsoup.nodes.Node
    public void outerHtmlTail(Appendable appendable, int i10, Document.OutputSettings outputSettings) {
    }

    @Override // org.jsoup.nodes.Node
    public String toString() {
        return outerHtml();
    }

    @Override // org.jsoup.nodes.Node
    /* JADX INFO: renamed from: clone */
    public XmlDeclaration mo7038clone() {
        return (XmlDeclaration) super.mo7038clone();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void getWholeDeclaration(java.lang.Appendable r11, org.jsoup.nodes.Document.OutputSettings r12) throws java.io.IOException {
        /*
            r10 = this;
            org.jsoup.nodes.Attributes r0 = r10.attributes()
            java.util.Iterator r0 = r0.iterator()
        L8:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L4d
            java.lang.Object r1 = r0.next()
            org.jsoup.nodes.Attribute r1 = (org.jsoup.nodes.Attribute) r1
            java.lang.String r2 = r1.getKey()
            java.lang.String r4 = r1.getValue()
            java.lang.String r1 = r10.nodeName()
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L48
            r1 = 32
            r11.append(r1)
            r11.append(r2)
            boolean r1 = r4.isEmpty()
            if (r1 != 0) goto L48
            java.lang.String r1 = "=\""
            r11.append(r1)
            r8 = 0
            r9 = 0
            r6 = 1
            r7 = 0
            r3 = r11
            r5 = r12
            org.jsoup.nodes.Entities.escape(r3, r4, r5, r6, r7, r8, r9)
            r11 = 34
            r3.append(r11)
            goto L4a
        L48:
            r3 = r11
            r5 = r12
        L4a:
            r11 = r3
            r12 = r5
            goto L8
        L4d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.nodes.XmlDeclaration.getWholeDeclaration(java.lang.Appendable, org.jsoup.nodes.Document$OutputSettings):void");
    }
}
