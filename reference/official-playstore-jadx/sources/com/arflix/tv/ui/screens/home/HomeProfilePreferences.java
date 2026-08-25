package com.arflix.tv.ui.screens.home;

import androidx.fragment.app.a2;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u001d\b\u0081\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\nHÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\nHÆ\u0003JY\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\nHÆ\u0001J\u0014\u0010#\u001a\u00020\u00032\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010%\u001a\u00020\u0006HÖ\u0081\u0004J\n\u0010&\u001a\u00020\nHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u0011\u0010\f\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017¨\u0006'"}, d2 = {"Lcom/arflix/tv/ui/screens/home/HomeProfilePreferences;", "", "trailerAutoPlay", "", "trailerSoundEnabled", "trailerDelaySeconds", "", "trailerInCards", "showBudget", "clockFormat", "", "smoothScrolling", "contentLanguage", "<init>", "(ZZIZZLjava/lang/String;ZLjava/lang/String;)V", "getTrailerAutoPlay", "()Z", "getTrailerSoundEnabled", "getTrailerDelaySeconds", "()I", "getTrailerInCards", "getShowBudget", "getClockFormat", "()Ljava/lang/String;", "getSmoothScrolling", "getContentLanguage", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class HomeProfilePreferences {
    public static final int $stable = 0;
    private final String clockFormat;
    private final String contentLanguage;
    private final boolean showBudget;
    private final boolean smoothScrolling;
    private final boolean trailerAutoPlay;
    private final int trailerDelaySeconds;
    private final boolean trailerInCards;
    private final boolean trailerSoundEnabled;

    public HomeProfilePreferences(boolean z, boolean z5, int i10, boolean z10, boolean z11, String str, boolean z12, String str2) {
        this.trailerAutoPlay = z;
        this.trailerSoundEnabled = z5;
        this.trailerDelaySeconds = i10;
        this.trailerInCards = z10;
        this.showBudget = z11;
        this.clockFormat = str;
        this.smoothScrolling = z12;
        this.contentLanguage = str2;
    }

    public static /* synthetic */ HomeProfilePreferences copy$default(HomeProfilePreferences homeProfilePreferences, boolean z, boolean z5, int i10, boolean z10, boolean z11, String str, boolean z12, String str2, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            z = homeProfilePreferences.trailerAutoPlay;
        }
        if ((i11 & 2) != 0) {
            z5 = homeProfilePreferences.trailerSoundEnabled;
        }
        if ((i11 & 4) != 0) {
            i10 = homeProfilePreferences.trailerDelaySeconds;
        }
        if ((i11 & 8) != 0) {
            z10 = homeProfilePreferences.trailerInCards;
        }
        if ((i11 & 16) != 0) {
            z11 = homeProfilePreferences.showBudget;
        }
        if ((i11 & 32) != 0) {
            str = homeProfilePreferences.clockFormat;
        }
        if ((i11 & 64) != 0) {
            z12 = homeProfilePreferences.smoothScrolling;
        }
        if ((i11 & 128) != 0) {
            str2 = homeProfilePreferences.contentLanguage;
        }
        boolean z13 = z12;
        String str3 = str2;
        boolean z14 = z11;
        String str4 = str;
        return homeProfilePreferences.copy(z, z5, i10, z10, z14, str4, z13, str3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getTrailerAutoPlay() {
        return this.trailerAutoPlay;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getTrailerSoundEnabled() {
        return this.trailerSoundEnabled;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getTrailerDelaySeconds() {
        return this.trailerDelaySeconds;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getTrailerInCards() {
        return this.trailerInCards;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getShowBudget() {
        return this.showBudget;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getClockFormat() {
        return this.clockFormat;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getSmoothScrolling() {
        return this.smoothScrolling;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getContentLanguage() {
        return this.contentLanguage;
    }

    public final HomeProfilePreferences copy(boolean trailerAutoPlay, boolean trailerSoundEnabled, int trailerDelaySeconds, boolean trailerInCards, boolean showBudget, String clockFormat, boolean smoothScrolling, String contentLanguage) {
        return new HomeProfilePreferences(trailerAutoPlay, trailerSoundEnabled, trailerDelaySeconds, trailerInCards, showBudget, clockFormat, smoothScrolling, contentLanguage);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HomeProfilePreferences)) {
            return false;
        }
        HomeProfilePreferences homeProfilePreferences = (HomeProfilePreferences) other;
        return this.trailerAutoPlay == homeProfilePreferences.trailerAutoPlay && this.trailerSoundEnabled == homeProfilePreferences.trailerSoundEnabled && this.trailerDelaySeconds == homeProfilePreferences.trailerDelaySeconds && this.trailerInCards == homeProfilePreferences.trailerInCards && this.showBudget == homeProfilePreferences.showBudget && kotlin.jvm.internal.p.a(this.clockFormat, homeProfilePreferences.clockFormat) && this.smoothScrolling == homeProfilePreferences.smoothScrolling && kotlin.jvm.internal.p.a(this.contentLanguage, homeProfilePreferences.contentLanguage);
    }

    public final String getClockFormat() {
        return this.clockFormat;
    }

    public final String getContentLanguage() {
        return this.contentLanguage;
    }

    public final boolean getShowBudget() {
        return this.showBudget;
    }

    public final boolean getSmoothScrolling() {
        return this.smoothScrolling;
    }

    public final boolean getTrailerAutoPlay() {
        return this.trailerAutoPlay;
    }

    public final int getTrailerDelaySeconds() {
        return this.trailerDelaySeconds;
    }

    public final boolean getTrailerInCards() {
        return this.trailerInCards;
    }

    public final boolean getTrailerSoundEnabled() {
        return this.trailerSoundEnabled;
    }

    public int hashCode() {
        return this.contentLanguage.hashCode() + ((androidx.compose.foundation.c.c((((((((((this.trailerAutoPlay ? 1231 : 1237) * 31) + (this.trailerSoundEnabled ? 1231 : 1237)) * 31) + this.trailerDelaySeconds) * 31) + (this.trailerInCards ? 1231 : 1237)) * 31) + (this.showBudget ? 1231 : 1237)) * 31, 31, this.clockFormat) + (this.smoothScrolling ? 1231 : 1237)) * 31);
    }

    public String toString() {
        boolean z = this.trailerAutoPlay;
        boolean z5 = this.trailerSoundEnabled;
        int i10 = this.trailerDelaySeconds;
        boolean z10 = this.trailerInCards;
        boolean z11 = this.showBudget;
        String str = this.clockFormat;
        boolean z12 = this.smoothScrolling;
        String str2 = this.contentLanguage;
        StringBuilder sb2 = new StringBuilder("HomeProfilePreferences(trailerAutoPlay=");
        sb2.append(z);
        sb2.append(", trailerSoundEnabled=");
        sb2.append(z5);
        sb2.append(", trailerDelaySeconds=");
        sb2.append(i10);
        sb2.append(", trailerInCards=");
        sb2.append(z10);
        sb2.append(", showBudget=");
        a2.B(sb2, z11, ", clockFormat=", str, ", smoothScrolling=");
        sb2.append(z12);
        sb2.append(", contentLanguage=");
        sb2.append(str2);
        sb2.append(")");
        return sb2.toString();
    }
}
