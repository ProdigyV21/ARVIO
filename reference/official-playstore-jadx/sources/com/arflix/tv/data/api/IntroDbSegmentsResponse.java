package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BO\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\u000b\u0010\fJ\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0010J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\bHÆ\u0003JV\u0010\u001d\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010\u001eJ\u0014\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\"\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010#\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0012\u0010\u0010R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0018\u0010\t\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0018\u0010\n\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014¨\u0006$"}, d2 = {"Lcom/arflix/tv/data/api/IntroDbSegmentsResponse;", "", "imdbId", "", "season", "", "episode", "intro", "Lcom/arflix/tv/data/api/IntroDbSegment;", "recap", "outro", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/arflix/tv/data/api/IntroDbSegment;Lcom/arflix/tv/data/api/IntroDbSegment;Lcom/arflix/tv/data/api/IntroDbSegment;)V", "getImdbId", "()Ljava/lang/String;", "getSeason", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getEpisode", "getIntro", "()Lcom/arflix/tv/data/api/IntroDbSegment;", "getRecap", "getOutro", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/arflix/tv/data/api/IntroDbSegment;Lcom/arflix/tv/data/api/IntroDbSegment;Lcom/arflix/tv/data/api/IntroDbSegment;)Lcom/arflix/tv/data/api/IntroDbSegmentsResponse;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class IntroDbSegmentsResponse {
    public static final int $stable = 0;

    @SerializedName("episode")
    private final Integer episode;

    @SerializedName("imdb_id")
    private final String imdbId;

    @SerializedName("intro")
    private final IntroDbSegment intro;

    @SerializedName("outro")
    private final IntroDbSegment outro;

    @SerializedName("recap")
    private final IntroDbSegment recap;

    @SerializedName("season")
    private final Integer season;

    public IntroDbSegmentsResponse() {
        this(null, null, null, null, null, null, 63, null);
    }

    public static /* synthetic */ IntroDbSegmentsResponse copy$default(IntroDbSegmentsResponse introDbSegmentsResponse, String str, Integer num, Integer num2, IntroDbSegment introDbSegment, IntroDbSegment introDbSegment2, IntroDbSegment introDbSegment3, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = introDbSegmentsResponse.imdbId;
        }
        if ((i10 & 2) != 0) {
            num = introDbSegmentsResponse.season;
        }
        if ((i10 & 4) != 0) {
            num2 = introDbSegmentsResponse.episode;
        }
        if ((i10 & 8) != 0) {
            introDbSegment = introDbSegmentsResponse.intro;
        }
        if ((i10 & 16) != 0) {
            introDbSegment2 = introDbSegmentsResponse.recap;
        }
        if ((i10 & 32) != 0) {
            introDbSegment3 = introDbSegmentsResponse.outro;
        }
        IntroDbSegment introDbSegment4 = introDbSegment2;
        IntroDbSegment introDbSegment5 = introDbSegment3;
        return introDbSegmentsResponse.copy(str, num, num2, introDbSegment, introDbSegment4, introDbSegment5);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getImdbId() {
        return this.imdbId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Integer getSeason() {
        return this.season;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Integer getEpisode() {
        return this.episode;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final IntroDbSegment getIntro() {
        return this.intro;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final IntroDbSegment getRecap() {
        return this.recap;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final IntroDbSegment getOutro() {
        return this.outro;
    }

    public final IntroDbSegmentsResponse copy(String imdbId, Integer season, Integer episode, IntroDbSegment intro, IntroDbSegment recap, IntroDbSegment outro) {
        return new IntroDbSegmentsResponse(imdbId, season, episode, intro, recap, outro);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IntroDbSegmentsResponse)) {
            return false;
        }
        IntroDbSegmentsResponse introDbSegmentsResponse = (IntroDbSegmentsResponse) other;
        return p.a(this.imdbId, introDbSegmentsResponse.imdbId) && p.a(this.season, introDbSegmentsResponse.season) && p.a(this.episode, introDbSegmentsResponse.episode) && p.a(this.intro, introDbSegmentsResponse.intro) && p.a(this.recap, introDbSegmentsResponse.recap) && p.a(this.outro, introDbSegmentsResponse.outro);
    }

    public final Integer getEpisode() {
        return this.episode;
    }

    public final String getImdbId() {
        return this.imdbId;
    }

    public final IntroDbSegment getIntro() {
        return this.intro;
    }

    public final IntroDbSegment getOutro() {
        return this.outro;
    }

    public final IntroDbSegment getRecap() {
        return this.recap;
    }

    public final Integer getSeason() {
        return this.season;
    }

    public int hashCode() {
        String str = this.imdbId;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        Integer num = this.season;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.episode;
        int iHashCode3 = (iHashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
        IntroDbSegment introDbSegment = this.intro;
        int iHashCode4 = (iHashCode3 + (introDbSegment == null ? 0 : introDbSegment.hashCode())) * 31;
        IntroDbSegment introDbSegment2 = this.recap;
        int iHashCode5 = (iHashCode4 + (introDbSegment2 == null ? 0 : introDbSegment2.hashCode())) * 31;
        IntroDbSegment introDbSegment3 = this.outro;
        return iHashCode5 + (introDbSegment3 != null ? introDbSegment3.hashCode() : 0);
    }

    public String toString() {
        return "IntroDbSegmentsResponse(imdbId=" + this.imdbId + ", season=" + this.season + ", episode=" + this.episode + ", intro=" + this.intro + ", recap=" + this.recap + ", outro=" + this.outro + ")";
    }

    public IntroDbSegmentsResponse(String str, Integer num, Integer num2, IntroDbSegment introDbSegment, IntroDbSegment introDbSegment2, IntroDbSegment introDbSegment3) {
        this.imdbId = str;
        this.season = num;
        this.episode = num2;
        this.intro = introDbSegment;
        this.recap = introDbSegment2;
        this.outro = introDbSegment3;
    }

    public /* synthetic */ IntroDbSegmentsResponse(String str, Integer num, Integer num2, IntroDbSegment introDbSegment, IntroDbSegment introDbSegment2, IntroDbSegment introDbSegment3, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : num, (i10 & 4) != 0 ? null : num2, (i10 & 8) != 0 ? null : introDbSegment, (i10 & 16) != 0 ? null : introDbSegment2, (i10 & 32) != 0 ? null : introDbSegment3);
    }
}
