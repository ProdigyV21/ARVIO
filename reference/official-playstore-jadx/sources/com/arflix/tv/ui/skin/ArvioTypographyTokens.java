package com.arflix.tv.ui.skin;

import androidx.compose.ui.text.TextStyle;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.cast.MediaTrack;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003JO\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001f\u001a\u00020 HÖ\u0081\u0004J\n\u0010!\u001a\u00020\"HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r¨\u0006#"}, d2 = {"Lcom/arflix/tv/ui/skin/ArvioTypographyTokens;", "", "heroTitle", "Landroidx/compose/ui/text/TextStyle;", "sectionTitle", "cardTitle", TtmlNode.TAG_BODY, MediaTrack.ROLE_CAPTION, "badge", "button", "<init>", "(Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;)V", "getHeroTitle", "()Landroidx/compose/ui/text/TextStyle;", "getSectionTitle", "getCardTitle", "getBody", "getCaption", "getBadge", "getButton", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class ArvioTypographyTokens {
    public static final int $stable = 0;
    private final TextStyle badge;
    private final TextStyle body;
    private final TextStyle button;
    private final TextStyle caption;
    private final TextStyle cardTitle;
    private final TextStyle heroTitle;
    private final TextStyle sectionTitle;

    public ArvioTypographyTokens(TextStyle textStyle, TextStyle textStyle2, TextStyle textStyle3, TextStyle textStyle4, TextStyle textStyle5, TextStyle textStyle6, TextStyle textStyle7) {
        this.heroTitle = textStyle;
        this.sectionTitle = textStyle2;
        this.cardTitle = textStyle3;
        this.body = textStyle4;
        this.caption = textStyle5;
        this.badge = textStyle6;
        this.button = textStyle7;
    }

    public static /* synthetic */ ArvioTypographyTokens copy$default(ArvioTypographyTokens arvioTypographyTokens, TextStyle textStyle, TextStyle textStyle2, TextStyle textStyle3, TextStyle textStyle4, TextStyle textStyle5, TextStyle textStyle6, TextStyle textStyle7, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            textStyle = arvioTypographyTokens.heroTitle;
        }
        if ((i10 & 2) != 0) {
            textStyle2 = arvioTypographyTokens.sectionTitle;
        }
        if ((i10 & 4) != 0) {
            textStyle3 = arvioTypographyTokens.cardTitle;
        }
        if ((i10 & 8) != 0) {
            textStyle4 = arvioTypographyTokens.body;
        }
        if ((i10 & 16) != 0) {
            textStyle5 = arvioTypographyTokens.caption;
        }
        if ((i10 & 32) != 0) {
            textStyle6 = arvioTypographyTokens.badge;
        }
        if ((i10 & 64) != 0) {
            textStyle7 = arvioTypographyTokens.button;
        }
        TextStyle textStyle8 = textStyle6;
        TextStyle textStyle9 = textStyle7;
        TextStyle textStyle10 = textStyle5;
        TextStyle textStyle11 = textStyle3;
        return arvioTypographyTokens.copy(textStyle, textStyle2, textStyle11, textStyle4, textStyle10, textStyle8, textStyle9);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final TextStyle getHeroTitle() {
        return this.heroTitle;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final TextStyle getSectionTitle() {
        return this.sectionTitle;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final TextStyle getCardTitle() {
        return this.cardTitle;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final TextStyle getBody() {
        return this.body;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final TextStyle getCaption() {
        return this.caption;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final TextStyle getBadge() {
        return this.badge;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final TextStyle getButton() {
        return this.button;
    }

    public final ArvioTypographyTokens copy(TextStyle heroTitle, TextStyle sectionTitle, TextStyle cardTitle, TextStyle body, TextStyle caption, TextStyle badge, TextStyle button) {
        return new ArvioTypographyTokens(heroTitle, sectionTitle, cardTitle, body, caption, badge, button);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ArvioTypographyTokens)) {
            return false;
        }
        ArvioTypographyTokens arvioTypographyTokens = (ArvioTypographyTokens) other;
        return p.a(this.heroTitle, arvioTypographyTokens.heroTitle) && p.a(this.sectionTitle, arvioTypographyTokens.sectionTitle) && p.a(this.cardTitle, arvioTypographyTokens.cardTitle) && p.a(this.body, arvioTypographyTokens.body) && p.a(this.caption, arvioTypographyTokens.caption) && p.a(this.badge, arvioTypographyTokens.badge) && p.a(this.button, arvioTypographyTokens.button);
    }

    public final TextStyle getBadge() {
        return this.badge;
    }

    public final TextStyle getBody() {
        return this.body;
    }

    public final TextStyle getButton() {
        return this.button;
    }

    public final TextStyle getCaption() {
        return this.caption;
    }

    public final TextStyle getCardTitle() {
        return this.cardTitle;
    }

    public final TextStyle getHeroTitle() {
        return this.heroTitle;
    }

    public final TextStyle getSectionTitle() {
        return this.sectionTitle;
    }

    public int hashCode() {
        return this.button.hashCode() + androidx.compose.foundation.c.b(androidx.compose.foundation.c.b(androidx.compose.foundation.c.b(androidx.compose.foundation.c.b(androidx.compose.foundation.c.b(this.heroTitle.hashCode() * 31, 31, this.sectionTitle), 31, this.cardTitle), 31, this.body), 31, this.caption), 31, this.badge);
    }

    public String toString() {
        return "ArvioTypographyTokens(heroTitle=" + this.heroTitle + ", sectionTitle=" + this.sectionTitle + ", cardTitle=" + this.cardTitle + ", body=" + this.body + ", caption=" + this.caption + ", badge=" + this.badge + ", button=" + this.button + ")";
    }
}
