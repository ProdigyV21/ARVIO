package com.caverock.androidsvg;

import org.xml.sax.Attributes;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes4.dex */
public final class m2 implements Attributes {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public XmlPullParser f12376a;

    @Override // org.xml.sax.Attributes
    public final int getIndex(String str) {
        return -1;
    }

    @Override // org.xml.sax.Attributes
    public final int getLength() {
        return this.f12376a.getAttributeCount();
    }

    @Override // org.xml.sax.Attributes
    public final String getLocalName(int i10) {
        return this.f12376a.getAttributeName(i10);
    }

    @Override // org.xml.sax.Attributes
    public final String getQName(int i10) {
        XmlPullParser xmlPullParser = this.f12376a;
        String attributeName = xmlPullParser.getAttributeName(i10);
        if (xmlPullParser.getAttributePrefix(i10) == null) {
            return attributeName;
        }
        return xmlPullParser.getAttributePrefix(i10) + ':' + attributeName;
    }

    @Override // org.xml.sax.Attributes
    public final String getType(int i10) {
        return null;
    }

    @Override // org.xml.sax.Attributes
    public final String getURI(int i10) {
        return this.f12376a.getAttributeNamespace(i10);
    }

    @Override // org.xml.sax.Attributes
    public final String getValue(String str) {
        return null;
    }

    @Override // org.xml.sax.Attributes
    public final int getIndex(String str, String str2) {
        return -1;
    }

    @Override // org.xml.sax.Attributes
    public final String getType(String str) {
        return null;
    }

    @Override // org.xml.sax.Attributes
    public final String getValue(String str, String str2) {
        return null;
    }

    @Override // org.xml.sax.Attributes
    public final String getType(String str, String str2) {
        return null;
    }

    @Override // org.xml.sax.Attributes
    public final String getValue(int i10) {
        return this.f12376a.getAttributeValue(i10);
    }
}
