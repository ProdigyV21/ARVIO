package com.arflix.tv.data.model;

import androidx.compose.foundation.c;
import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import y.a;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u001e\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001Bg\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\t\u0012\b\b\u0002\u0010\u000e\u001a\u00020\t¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\tHÆ\u0003J\u0010\u0010\"\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010\u0019J\u0010\u0010#\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010\u0019J\t\u0010$\u001a\u00020\tHÆ\u0003J\t\u0010%\u001a\u00020\tHÆ\u0003Jv\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\tHÆ\u0001¢\u0006\u0002\u0010'J\u0014\u0010(\u001a\u00020\t2\b\u0010)\u001a\u0004\u0018\u00010*HÖ\u0083\u0004J\n\u0010+\u001a\u00020\u000bHÖ\u0081\u0004J\n\u0010,\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0017R\u0015\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u0018\u0010\u0019R\u0015\u0010\f\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u001b\u0010\u0019R\u0011\u0010\r\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0017R\u0011\u0010\u000e\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0017¨\u0006-"}, d2 = {"Lcom/arflix/tv/data/model/Subtitle;", "Ljava/io/Serializable;", TtmlNode.ATTR_ID, "", "url", "lang", "label", "provider", "isEmbedded", "", "groupIndex", "", "trackIndex", "isForced", "isBitmap", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/Integer;Ljava/lang/Integer;ZZ)V", "getId", "()Ljava/lang/String;", "getUrl", "getLang", "getLabel", "getProvider", "()Z", "getGroupIndex", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTrackIndex", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/Integer;Ljava/lang/Integer;ZZ)Lcom/arflix/tv/data/model/Subtitle;", "equals", "other", "", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class Subtitle implements Serializable {
    public static final int $stable = 0;
    private final Integer groupIndex;
    private final String id;
    private final boolean isBitmap;
    private final boolean isEmbedded;
    private final boolean isForced;
    private final String label;
    private final String lang;
    private final String provider;
    private final Integer trackIndex;
    private final String url;

    public Subtitle(String str, String str2, String str3, String str4, String str5, boolean z, Integer num, Integer num2, boolean z5, boolean z10) {
        this.id = str;
        this.url = str2;
        this.lang = str3;
        this.label = str4;
        this.provider = str5;
        this.isEmbedded = z;
        this.groupIndex = num;
        this.trackIndex = num2;
        this.isForced = z5;
        this.isBitmap = z10;
    }

    public static /* synthetic */ Subtitle copy$default(Subtitle subtitle, String str, String str2, String str3, String str4, String str5, boolean z, Integer num, Integer num2, boolean z5, boolean z10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = subtitle.id;
        }
        if ((i10 & 2) != 0) {
            str2 = subtitle.url;
        }
        if ((i10 & 4) != 0) {
            str3 = subtitle.lang;
        }
        if ((i10 & 8) != 0) {
            str4 = subtitle.label;
        }
        if ((i10 & 16) != 0) {
            str5 = subtitle.provider;
        }
        if ((i10 & 32) != 0) {
            z = subtitle.isEmbedded;
        }
        if ((i10 & 64) != 0) {
            num = subtitle.groupIndex;
        }
        if ((i10 & 128) != 0) {
            num2 = subtitle.trackIndex;
        }
        if ((i10 & 256) != 0) {
            z5 = subtitle.isForced;
        }
        if ((i10 & 512) != 0) {
            z10 = subtitle.isBitmap;
        }
        boolean z11 = z5;
        boolean z12 = z10;
        Integer num3 = num;
        Integer num4 = num2;
        String str6 = str5;
        boolean z13 = z;
        return subtitle.copy(str, str2, str3, str4, str6, z13, num3, num4, z11, z12);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final boolean getIsBitmap() {
        return this.isBitmap;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getLang() {
        return this.lang;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getProvider() {
        return this.provider;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getIsEmbedded() {
        return this.isEmbedded;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Integer getGroupIndex() {
        return this.groupIndex;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final Integer getTrackIndex() {
        return this.trackIndex;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final boolean getIsForced() {
        return this.isForced;
    }

    public final Subtitle copy(String id, String url, String lang, String label, String provider, boolean isEmbedded, Integer groupIndex, Integer trackIndex, boolean isForced, boolean isBitmap) {
        return new Subtitle(id, url, lang, label, provider, isEmbedded, groupIndex, trackIndex, isForced, isBitmap);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Subtitle)) {
            return false;
        }
        Subtitle subtitle = (Subtitle) other;
        return p.a(this.id, subtitle.id) && p.a(this.url, subtitle.url) && p.a(this.lang, subtitle.lang) && p.a(this.label, subtitle.label) && p.a(this.provider, subtitle.provider) && this.isEmbedded == subtitle.isEmbedded && p.a(this.groupIndex, subtitle.groupIndex) && p.a(this.trackIndex, subtitle.trackIndex) && this.isForced == subtitle.isForced && this.isBitmap == subtitle.isBitmap;
    }

    public final Integer getGroupIndex() {
        return this.groupIndex;
    }

    public final String getId() {
        return this.id;
    }

    public final String getLabel() {
        return this.label;
    }

    public final String getLang() {
        return this.lang;
    }

    public final String getProvider() {
        return this.provider;
    }

    public final Integer getTrackIndex() {
        return this.trackIndex;
    }

    public final String getUrl() {
        return this.url;
    }

    public int hashCode() {
        int iC = (c.c(c.c(c.c(c.c(this.id.hashCode() * 31, 31, this.url), 31, this.lang), 31, this.label), 31, this.provider) + (this.isEmbedded ? 1231 : 1237)) * 31;
        Integer num = this.groupIndex;
        int iHashCode = (iC + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.trackIndex;
        return ((((iHashCode + (num2 != null ? num2.hashCode() : 0)) * 31) + (this.isForced ? 1231 : 1237)) * 31) + (this.isBitmap ? 1231 : 1237);
    }

    public final boolean isBitmap() {
        return this.isBitmap;
    }

    public final boolean isEmbedded() {
        return this.isEmbedded;
    }

    public final boolean isForced() {
        return this.isForced;
    }

    public String toString() {
        String str = this.id;
        String str2 = this.url;
        String str3 = this.lang;
        String str4 = this.label;
        String str5 = this.provider;
        boolean z = this.isEmbedded;
        Integer num = this.groupIndex;
        Integer num2 = this.trackIndex;
        boolean z5 = this.isForced;
        boolean z10 = this.isBitmap;
        StringBuilder sbR = a2.r("Subtitle(id=", str, ", url=", str2, ", lang=");
        a.i(sbR, str3, ", label=", str4, ", provider=");
        sbR.append(str5);
        sbR.append(", isEmbedded=");
        sbR.append(z);
        sbR.append(", groupIndex=");
        a2.z(sbR, num, ", trackIndex=", num2, ", isForced=");
        sbR.append(z5);
        sbR.append(", isBitmap=");
        sbR.append(z10);
        sbR.append(")");
        return sbR.toString();
    }

    /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.SSAVar.getPhiList()" because "resultVar" is null
        	at jadx.core.dex.visitors.InitCodeVariables.collectConnectedVars(InitCodeVariables.java:119)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:82)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:48)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:29)
        */
    public /* synthetic */ Subtitle(java.lang.String r14, java.lang.String r15, java.lang.String r16, java.lang.String r17, java.lang.String r18, boolean r19, java.lang.Integer r20, java.lang.Integer r21, boolean r22, boolean r23, int r24, kotlin.jvm.internal.h r25) {
        /*
            r13 = this;
            r0 = r24
            r1 = r0 & 16
            if (r1 == 0) goto La
            java.lang.String r1 = ""
            r7 = r1
            goto Lc
        La:
            r7 = r18
        Lc:
            r1 = r0 & 32
            r2 = 0
            if (r1 == 0) goto L13
            r8 = r2
            goto L15
        L13:
            r8 = r19
        L15:
            r1 = r0 & 64
            r3 = 0
            if (r1 == 0) goto L1c
            r9 = r3
            goto L1e
        L1c:
            r9 = r20
        L1e:
            r1 = r0 & 128(0x80, float:1.8E-43)
            if (r1 == 0) goto L24
            r10 = r3
            goto L26
        L24:
            r10 = r21
        L26:
            r1 = r0 & 256(0x100, float:3.59E-43)
            if (r1 == 0) goto L2c
            r11 = r2
            goto L2e
        L2c:
            r11 = r22
        L2e:
            r0 = r0 & 512(0x200, float:7.17E-43)
            if (r0 == 0) goto L3b
            r12 = r2
            r3 = r14
            r4 = r15
            r5 = r16
            r6 = r17
            r2 = r13
            goto L44
        L3b:
            r12 = r23
            r2 = r13
            r3 = r14
            r4 = r15
            r5 = r16
            r6 = r17
        L44:
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.model.Subtitle.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, java.lang.Integer, java.lang.Integer, boolean, boolean, int, kotlin.jvm.internal.h):void");
    }
}
