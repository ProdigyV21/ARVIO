package com.arflix.tv.ui.screens.tv.live;

import io.ktor.http.LinkHeader;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J;\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u001c"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/GuideMatchLabels;", "", "now", "", LinkHeader.Rel.Next, "later", "guide", "nowFormat", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getNow", "()Ljava/lang/String;", "getNext", "getLater", "getGuide", "getNowFormat", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class GuideMatchLabels {
    private final String guide;
    private final String later;
    private final String next;
    private final String now;
    private final String nowFormat;

    public GuideMatchLabels(String str, String str2, String str3, String str4, String str5) {
        this.now = str;
        this.next = str2;
        this.later = str3;
        this.guide = str4;
        this.nowFormat = str5;
    }

    public static /* synthetic */ GuideMatchLabels copy$default(GuideMatchLabels guideMatchLabels, String str, String str2, String str3, String str4, String str5, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = guideMatchLabels.now;
        }
        if ((i10 & 2) != 0) {
            str2 = guideMatchLabels.next;
        }
        if ((i10 & 4) != 0) {
            str3 = guideMatchLabels.later;
        }
        if ((i10 & 8) != 0) {
            str4 = guideMatchLabels.guide;
        }
        if ((i10 & 16) != 0) {
            str5 = guideMatchLabels.nowFormat;
        }
        String str6 = str5;
        String str7 = str3;
        return guideMatchLabels.copy(str, str2, str7, str4, str6);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getNow() {
        return this.now;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getNext() {
        return this.next;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getLater() {
        return this.later;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getGuide() {
        return this.guide;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getNowFormat() {
        return this.nowFormat;
    }

    public final GuideMatchLabels copy(String now, String next, String later, String guide, String nowFormat) {
        return new GuideMatchLabels(now, next, later, guide, nowFormat);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GuideMatchLabels)) {
            return false;
        }
        GuideMatchLabels guideMatchLabels = (GuideMatchLabels) other;
        return kotlin.jvm.internal.p.a(this.now, guideMatchLabels.now) && kotlin.jvm.internal.p.a(this.next, guideMatchLabels.next) && kotlin.jvm.internal.p.a(this.later, guideMatchLabels.later) && kotlin.jvm.internal.p.a(this.guide, guideMatchLabels.guide) && kotlin.jvm.internal.p.a(this.nowFormat, guideMatchLabels.nowFormat);
    }

    public final String getGuide() {
        return this.guide;
    }

    public final String getLater() {
        return this.later;
    }

    public final String getNext() {
        return this.next;
    }

    public final String getNow() {
        return this.now;
    }

    public final String getNowFormat() {
        return this.nowFormat;
    }

    public int hashCode() {
        return this.nowFormat.hashCode() + androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(this.now.hashCode() * 31, 31, this.next), 31, this.later), 31, this.guide);
    }

    public String toString() {
        String str = this.now;
        String str2 = this.next;
        String str3 = this.later;
        String str4 = this.guide;
        String str5 = this.nowFormat;
        StringBuilder sbR = androidx.fragment.app.a2.r("GuideMatchLabels(now=", str, ", next=", str2, ", later=");
        y.a.i(sbR, str3, ", guide=", str4, ", nowFormat=");
        return a0.c.p(sbR, str5, ")");
    }
}
