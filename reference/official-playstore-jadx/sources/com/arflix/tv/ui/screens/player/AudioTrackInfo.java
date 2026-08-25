package com.arflix.tv.ui.screens.player;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0007HÆ\u0003J_\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0014\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010$\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010%\u001a\u00020\u0007HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013¨\u0006&"}, d2 = {"Lcom/arflix/tv/ui/screens/player/AudioTrackInfo;", "", "index", "", "groupIndex", "trackIndex", "language", "", "label", "channelCount", "sampleRate", "codec", "<init>", "(IIILjava/lang/String;Ljava/lang/String;IILjava/lang/String;)V", "getIndex", "()I", "getGroupIndex", "getTrackIndex", "getLanguage", "()Ljava/lang/String;", "getLabel", "getChannelCount", "getSampleRate", "getCodec", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class AudioTrackInfo {
    public static final int $stable = 0;
    private final int channelCount;
    private final String codec;
    private final int groupIndex;
    private final int index;
    private final String label;
    private final String language;
    private final int sampleRate;
    private final int trackIndex;

    public AudioTrackInfo(int i10, int i11, int i12, String str, String str2, int i13, int i14, String str3) {
        this.index = i10;
        this.groupIndex = i11;
        this.trackIndex = i12;
        this.language = str;
        this.label = str2;
        this.channelCount = i13;
        this.sampleRate = i14;
        this.codec = str3;
    }

    public static /* synthetic */ AudioTrackInfo copy$default(AudioTrackInfo audioTrackInfo, int i10, int i11, int i12, String str, String str2, int i13, int i14, String str3, int i15, Object obj) {
        if ((i15 & 1) != 0) {
            i10 = audioTrackInfo.index;
        }
        if ((i15 & 2) != 0) {
            i11 = audioTrackInfo.groupIndex;
        }
        if ((i15 & 4) != 0) {
            i12 = audioTrackInfo.trackIndex;
        }
        if ((i15 & 8) != 0) {
            str = audioTrackInfo.language;
        }
        if ((i15 & 16) != 0) {
            str2 = audioTrackInfo.label;
        }
        if ((i15 & 32) != 0) {
            i13 = audioTrackInfo.channelCount;
        }
        if ((i15 & 64) != 0) {
            i14 = audioTrackInfo.sampleRate;
        }
        if ((i15 & 128) != 0) {
            str3 = audioTrackInfo.codec;
        }
        int i16 = i14;
        String str4 = str3;
        String str5 = str2;
        int i17 = i13;
        return audioTrackInfo.copy(i10, i11, i12, str, str5, i17, i16, str4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getIndex() {
        return this.index;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getGroupIndex() {
        return this.groupIndex;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getTrackIndex() {
        return this.trackIndex;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getLanguage() {
        return this.language;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getChannelCount() {
        return this.channelCount;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getSampleRate() {
        return this.sampleRate;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getCodec() {
        return this.codec;
    }

    public final AudioTrackInfo copy(int index, int groupIndex, int trackIndex, String language, String label, int channelCount, int sampleRate, String codec) {
        return new AudioTrackInfo(index, groupIndex, trackIndex, language, label, channelCount, sampleRate, codec);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AudioTrackInfo)) {
            return false;
        }
        AudioTrackInfo audioTrackInfo = (AudioTrackInfo) other;
        return this.index == audioTrackInfo.index && this.groupIndex == audioTrackInfo.groupIndex && this.trackIndex == audioTrackInfo.trackIndex && kotlin.jvm.internal.p.a(this.language, audioTrackInfo.language) && kotlin.jvm.internal.p.a(this.label, audioTrackInfo.label) && this.channelCount == audioTrackInfo.channelCount && this.sampleRate == audioTrackInfo.sampleRate && kotlin.jvm.internal.p.a(this.codec, audioTrackInfo.codec);
    }

    public final int getChannelCount() {
        return this.channelCount;
    }

    public final String getCodec() {
        return this.codec;
    }

    public final int getGroupIndex() {
        return this.groupIndex;
    }

    public final int getIndex() {
        return this.index;
    }

    public final String getLabel() {
        return this.label;
    }

    public final String getLanguage() {
        return this.language;
    }

    public final int getSampleRate() {
        return this.sampleRate;
    }

    public final int getTrackIndex() {
        return this.trackIndex;
    }

    public int hashCode() {
        int i10 = ((((this.index * 31) + this.groupIndex) * 31) + this.trackIndex) * 31;
        String str = this.language;
        int iHashCode = (i10 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.label;
        int iHashCode2 = (((((iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31) + this.channelCount) * 31) + this.sampleRate) * 31;
        String str3 = this.codec;
        return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        int i10 = this.index;
        int i11 = this.groupIndex;
        int i12 = this.trackIndex;
        String str = this.language;
        String str2 = this.label;
        int i13 = this.channelCount;
        int i14 = this.sampleRate;
        String str3 = this.codec;
        StringBuilder sbV = androidx.compose.foundation.c.v("AudioTrackInfo(index=", i10, ", groupIndex=", i11, ", trackIndex=");
        sbV.append(i12);
        sbV.append(", language=");
        sbV.append(str);
        sbV.append(", label=");
        androidx.compose.material3.d.A(sbV, str2, ", channelCount=", i13, ", sampleRate=");
        sbV.append(i14);
        sbV.append(", codec=");
        sbV.append(str3);
        sbV.append(")");
        return sbV.toString();
    }
}
