package com.arflix.tv.ui.components;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0018\b\u0087\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0013J\t\u0010\u001b\u001a\u00020\nHÆ\u0003JN\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001¢\u0006\u0002\u0010\u001dJ\u0014\u0010\u001e\u001a\u00020\n2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010 \u001a\u00020\bHÖ\u0081\u0004J\n\u0010!\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0015¨\u0006\""}, d2 = {"Lcom/arflix/tv/ui/components/AudioTrack;", "", TtmlNode.ATTR_ID, "", "language", "label", "codec", "channels", "", "isDefault", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Z)V", "getId", "()Ljava/lang/String;", "getLanguage", "getLabel", "getCodec", "getChannels", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Z)Lcom/arflix/tv/ui/components/AudioTrack;", "equals", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class AudioTrack {
    public static final int $stable = 0;
    private final Integer channels;
    private final String codec;
    private final String id;
    private final boolean isDefault;
    private final String label;
    private final String language;

    public AudioTrack(String str, String str2, String str3, String str4, Integer num, boolean z) {
        this.id = str;
        this.language = str2;
        this.label = str3;
        this.codec = str4;
        this.channels = num;
        this.isDefault = z;
    }

    public static /* synthetic */ AudioTrack copy$default(AudioTrack audioTrack, String str, String str2, String str3, String str4, Integer num, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = audioTrack.id;
        }
        if ((i10 & 2) != 0) {
            str2 = audioTrack.language;
        }
        if ((i10 & 4) != 0) {
            str3 = audioTrack.label;
        }
        if ((i10 & 8) != 0) {
            str4 = audioTrack.codec;
        }
        if ((i10 & 16) != 0) {
            num = audioTrack.channels;
        }
        if ((i10 & 32) != 0) {
            z = audioTrack.isDefault;
        }
        Integer num2 = num;
        boolean z5 = z;
        return audioTrack.copy(str, str2, str3, str4, num2, z5);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getLanguage() {
        return this.language;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getCodec() {
        return this.codec;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Integer getChannels() {
        return this.channels;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getIsDefault() {
        return this.isDefault;
    }

    public final AudioTrack copy(String id, String language, String label, String codec, Integer channels, boolean isDefault) {
        return new AudioTrack(id, language, label, codec, channels, isDefault);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AudioTrack)) {
            return false;
        }
        AudioTrack audioTrack = (AudioTrack) other;
        return kotlin.jvm.internal.p.a(this.id, audioTrack.id) && kotlin.jvm.internal.p.a(this.language, audioTrack.language) && kotlin.jvm.internal.p.a(this.label, audioTrack.label) && kotlin.jvm.internal.p.a(this.codec, audioTrack.codec) && kotlin.jvm.internal.p.a(this.channels, audioTrack.channels) && this.isDefault == audioTrack.isDefault;
    }

    public final Integer getChannels() {
        return this.channels;
    }

    public final String getCodec() {
        return this.codec;
    }

    public final String getId() {
        return this.id;
    }

    public final String getLabel() {
        return this.label;
    }

    public final String getLanguage() {
        return this.language;
    }

    public int hashCode() {
        int iC = androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(this.id.hashCode() * 31, 31, this.language), 31, this.label);
        String str = this.codec;
        int iHashCode = (iC + (str == null ? 0 : str.hashCode())) * 31;
        Integer num = this.channels;
        return ((iHashCode + (num != null ? num.hashCode() : 0)) * 31) + (this.isDefault ? 1231 : 1237);
    }

    public final boolean isDefault() {
        return this.isDefault;
    }

    public String toString() {
        String str = this.id;
        String str2 = this.language;
        String str3 = this.label;
        String str4 = this.codec;
        Integer num = this.channels;
        boolean z = this.isDefault;
        StringBuilder sbR = androidx.fragment.app.a2.r("AudioTrack(id=", str, ", language=", str2, ", label=");
        y.a.i(sbR, str3, ", codec=", str4, ", channels=");
        sbR.append(num);
        sbR.append(", isDefault=");
        sbR.append(z);
        sbR.append(")");
        return sbR.toString();
    }

    public /* synthetic */ AudioTrack(String str, String str2, String str3, String str4, Integer num, boolean z, int i10, kotlin.jvm.internal.h hVar) {
        this(str, str2, str3, (i10 & 8) != 0 ? null : str4, (i10 & 16) != 0 ? null : num, (i10 & 32) != 0 ? false : z);
    }
}
