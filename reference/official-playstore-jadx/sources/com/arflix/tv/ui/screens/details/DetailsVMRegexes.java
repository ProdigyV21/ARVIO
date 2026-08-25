package com.arflix.tv.ui.screens.details;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\u0006\u001a\u0004\b\n\u0010\bR\u0017\u0010\u000b\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\bR\u0017\u0010\r\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\r\u0010\u0006\u001a\u0004\b\u000e\u0010\bR\u0017\u0010\u000f\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0006\u001a\u0004\b\u0010\u0010\bR\u0017\u0010\u0011\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0006\u001a\u0004\b\u0012\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/arflix/tv/ui/screens/details/DetailsVMRegexes;", "", "<init>", "()V", "Lkotlin/text/m;", "reviewWhitespaceRegex", "Lkotlin/text/m;", "getReviewWhitespaceRegex", "()Lkotlin/text/m;", "reviewMarkdownLinkRegex", "getReviewMarkdownLinkRegex", "reviewHtmlTagRegex", "getReviewHtmlTagRegex", "reviewMarkdownNoiseRegex", "getReviewMarkdownNoiseRegex", "reviewSpamRegex", "getReviewSpamRegex", "reviewDomainRegex", "getReviewDomainRegex", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class DetailsVMRegexes {
    public static final DetailsVMRegexes INSTANCE = new DetailsVMRegexes();
    private static final kotlin.text.m reviewWhitespaceRegex = new kotlin.text.m("\\s+");
    private static final kotlin.text.m reviewMarkdownLinkRegex = new kotlin.text.m("\\[([^\\]]+)]\\([^)]*\\)");
    private static final kotlin.text.m reviewHtmlTagRegex = new kotlin.text.m("<[^>]*>");
    private static final kotlin.text.m reviewMarkdownNoiseRegex = new kotlin.text.m("[*_`>#]+");
    private static final kotlin.text.m reviewSpamRegex = new kotlin.text.m("\\b(?:https?://|www\\.|discord\\.gg|t\\.me/|telegram|whatsapp|onlyfans|casino|betting|viagra|loan|crypto|airdrop|promo\\s+code|coupon|download\\s+now|watch\\s+(?:free|online)|free\\s+stream|\\.xyz\\b|\\.top\\b|\\.click\\b|\\.link\\b|\\.site\\b)\\b", 0);
    private static final kotlin.text.m reviewDomainRegex = new kotlin.text.m("\\b[a-z0-9-]+\\.(?:com|net|org|xyz|top|click|link|site|online|shop|info)\\b", 0);

    private DetailsVMRegexes() {
    }

    public final kotlin.text.m getReviewDomainRegex() {
        return reviewDomainRegex;
    }

    public final kotlin.text.m getReviewHtmlTagRegex() {
        return reviewHtmlTagRegex;
    }

    public final kotlin.text.m getReviewMarkdownLinkRegex() {
        return reviewMarkdownLinkRegex;
    }

    public final kotlin.text.m getReviewMarkdownNoiseRegex() {
        return reviewMarkdownNoiseRegex;
    }

    public final kotlin.text.m getReviewSpamRegex() {
        return reviewSpamRegex;
    }

    public final kotlin.text.m getReviewWhitespaceRegex() {
        return reviewWhitespaceRegex;
    }
}
