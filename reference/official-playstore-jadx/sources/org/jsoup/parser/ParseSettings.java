package org.jsoup.parser;

import org.jsoup.internal.Normalizer;
import org.jsoup.nodes.Attributes;

/* JADX INFO: loaded from: classes5.dex */
public class ParseSettings {
    public static final ParseSettings htmlDefault = new ParseSettings(false, false);
    public static final ParseSettings preserveCase = new ParseSettings(true, true);
    private final boolean preserveAttributeCase;
    private final boolean preserveTagCase;

    public ParseSettings(boolean z, boolean z5) {
        this.preserveTagCase = z;
        this.preserveAttributeCase = z5;
    }

    public static String normalName(String str) {
        return Normalizer.lowerCase(str.trim());
    }

    public String normalizeAttribute(String str) {
        String strTrim = str.trim();
        return !this.preserveAttributeCase ? Normalizer.lowerCase(strTrim) : strTrim;
    }

    public Attributes normalizeAttributes(Attributes attributes) {
        if (attributes != null && !this.preserveAttributeCase) {
            attributes.normalize();
        }
        return attributes;
    }

    public String normalizeTag(String str) {
        String strTrim = str.trim();
        return !this.preserveTagCase ? Normalizer.lowerCase(strTrim) : strTrim;
    }

    public boolean preserveAttributeCase() {
        return this.preserveAttributeCase;
    }

    public boolean preserveTagCase() {
        return this.preserveTagCase;
    }

    public ParseSettings(ParseSettings parseSettings) {
        this(parseSettings.preserveTagCase, parseSettings.preserveAttributeCase);
    }
}
