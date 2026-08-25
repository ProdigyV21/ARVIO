package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import com.google.gson.annotations.SerializedName;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b-\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B¯\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0015J\u0010\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0015J\u0010\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0015J\u0010\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0015J\u000b\u0010)\u001a\u0004\u0018\u00010\bHÆ\u0003J\u0010\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0015J\u0010\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0015J\u000b\u0010,\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\bHÆ\u0003J\u0010\u0010.\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0015J\u0010\u0010/\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0015J\u0010\u00100\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0015J\u0010\u00101\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0015J\u000b\u00102\u001a\u0004\u0018\u00010\bHÆ\u0003J¶\u0001\u00103\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u00104J\u0014\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00108\u001a\u00020\u0003HÖ\u0081\u0004J\n\u00109\u001a\u00020\bHÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0017\u0010\u0015R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0018\u0010\u0015R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0019\u0010\u0015R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0015\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u001c\u0010\u0015R\u0015\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u001d\u0010\u0015R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001bR\u0013\u0010\f\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001bR\u0015\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b \u0010\u0015R\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b!\u0010\u0015R\u0015\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\"\u0010\u0015R\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b#\u0010\u0015R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001b¨\u0006:"}, d2 = {"Lcom/arflix/tv/data/api/ArmMappingEntry;", "", "kitsu", "", "anilist", "myanimelist", "anidb", "animePlanet", "", "anisearch", "livechart", "notifyMoe", "imdb", "themoviedb", "themoviedbSeason", "thetvdb", "thetvdbSeason", LinkHeader.Parameters.Media, "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)V", "getKitsu", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getAnilist", "getMyanimelist", "getAnidb", "getAnimePlanet", "()Ljava/lang/String;", "getAnisearch", "getLivechart", "getNotifyMoe", "getImdb", "getThemoviedb", "getThemoviedbSeason", "getThetvdb", "getThetvdbSeason", "getMedia", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)Lcom/arflix/tv/data/api/ArmMappingEntry;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class ArmMappingEntry {
    public static final int $stable = 0;
    private final Integer anidb;
    private final Integer anilist;

    @SerializedName("anime-planet")
    private final String animePlanet;
    private final Integer anisearch;
    private final String imdb;
    private final Integer kitsu;
    private final Integer livechart;
    private final String media;
    private final Integer myanimelist;

    @SerializedName("notify-moe")
    private final String notifyMoe;
    private final Integer themoviedb;

    @SerializedName("themoviedb-season")
    private final Integer themoviedbSeason;
    private final Integer thetvdb;

    @SerializedName("thetvdb-season")
    private final Integer thetvdbSeason;

    public ArmMappingEntry() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, 16383, null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Integer getKitsu() {
        return this.kitsu;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final Integer getThemoviedb() {
        return this.themoviedb;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final Integer getThemoviedbSeason() {
        return this.themoviedbSeason;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final Integer getThetvdb() {
        return this.thetvdb;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final Integer getThetvdbSeason() {
        return this.thetvdbSeason;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getMedia() {
        return this.media;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Integer getAnilist() {
        return this.anilist;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Integer getMyanimelist() {
        return this.myanimelist;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Integer getAnidb() {
        return this.anidb;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getAnimePlanet() {
        return this.animePlanet;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Integer getAnisearch() {
        return this.anisearch;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Integer getLivechart() {
        return this.livechart;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getNotifyMoe() {
        return this.notifyMoe;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getImdb() {
        return this.imdb;
    }

    public final ArmMappingEntry copy(Integer kitsu, Integer anilist, Integer myanimelist, Integer anidb, String animePlanet, Integer anisearch, Integer livechart, String notifyMoe, String imdb, Integer themoviedb, Integer themoviedbSeason, Integer thetvdb, Integer thetvdbSeason, String media) {
        return new ArmMappingEntry(kitsu, anilist, myanimelist, anidb, animePlanet, anisearch, livechart, notifyMoe, imdb, themoviedb, themoviedbSeason, thetvdb, thetvdbSeason, media);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ArmMappingEntry)) {
            return false;
        }
        ArmMappingEntry armMappingEntry = (ArmMappingEntry) other;
        return p.a(this.kitsu, armMappingEntry.kitsu) && p.a(this.anilist, armMappingEntry.anilist) && p.a(this.myanimelist, armMappingEntry.myanimelist) && p.a(this.anidb, armMappingEntry.anidb) && p.a(this.animePlanet, armMappingEntry.animePlanet) && p.a(this.anisearch, armMappingEntry.anisearch) && p.a(this.livechart, armMappingEntry.livechart) && p.a(this.notifyMoe, armMappingEntry.notifyMoe) && p.a(this.imdb, armMappingEntry.imdb) && p.a(this.themoviedb, armMappingEntry.themoviedb) && p.a(this.themoviedbSeason, armMappingEntry.themoviedbSeason) && p.a(this.thetvdb, armMappingEntry.thetvdb) && p.a(this.thetvdbSeason, armMappingEntry.thetvdbSeason) && p.a(this.media, armMappingEntry.media);
    }

    public final Integer getAnidb() {
        return this.anidb;
    }

    public final Integer getAnilist() {
        return this.anilist;
    }

    public final String getAnimePlanet() {
        return this.animePlanet;
    }

    public final Integer getAnisearch() {
        return this.anisearch;
    }

    public final String getImdb() {
        return this.imdb;
    }

    public final Integer getKitsu() {
        return this.kitsu;
    }

    public final Integer getLivechart() {
        return this.livechart;
    }

    public final String getMedia() {
        return this.media;
    }

    public final Integer getMyanimelist() {
        return this.myanimelist;
    }

    public final String getNotifyMoe() {
        return this.notifyMoe;
    }

    public final Integer getThemoviedb() {
        return this.themoviedb;
    }

    public final Integer getThemoviedbSeason() {
        return this.themoviedbSeason;
    }

    public final Integer getThetvdb() {
        return this.thetvdb;
    }

    public final Integer getThetvdbSeason() {
        return this.thetvdbSeason;
    }

    public int hashCode() {
        Integer num = this.kitsu;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        Integer num2 = this.anilist;
        int iHashCode2 = (iHashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.myanimelist;
        int iHashCode3 = (iHashCode2 + (num3 == null ? 0 : num3.hashCode())) * 31;
        Integer num4 = this.anidb;
        int iHashCode4 = (iHashCode3 + (num4 == null ? 0 : num4.hashCode())) * 31;
        String str = this.animePlanet;
        int iHashCode5 = (iHashCode4 + (str == null ? 0 : str.hashCode())) * 31;
        Integer num5 = this.anisearch;
        int iHashCode6 = (iHashCode5 + (num5 == null ? 0 : num5.hashCode())) * 31;
        Integer num6 = this.livechart;
        int iHashCode7 = (iHashCode6 + (num6 == null ? 0 : num6.hashCode())) * 31;
        String str2 = this.notifyMoe;
        int iHashCode8 = (iHashCode7 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.imdb;
        int iHashCode9 = (iHashCode8 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Integer num7 = this.themoviedb;
        int iHashCode10 = (iHashCode9 + (num7 == null ? 0 : num7.hashCode())) * 31;
        Integer num8 = this.themoviedbSeason;
        int iHashCode11 = (iHashCode10 + (num8 == null ? 0 : num8.hashCode())) * 31;
        Integer num9 = this.thetvdb;
        int iHashCode12 = (iHashCode11 + (num9 == null ? 0 : num9.hashCode())) * 31;
        Integer num10 = this.thetvdbSeason;
        int iHashCode13 = (iHashCode12 + (num10 == null ? 0 : num10.hashCode())) * 31;
        String str4 = this.media;
        return iHashCode13 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        Integer num = this.kitsu;
        Integer num2 = this.anilist;
        Integer num3 = this.myanimelist;
        Integer num4 = this.anidb;
        String str = this.animePlanet;
        Integer num5 = this.anisearch;
        Integer num6 = this.livechart;
        String str2 = this.notifyMoe;
        String str3 = this.imdb;
        Integer num7 = this.themoviedb;
        Integer num8 = this.themoviedbSeason;
        Integer num9 = this.thetvdb;
        Integer num10 = this.thetvdbSeason;
        String str4 = this.media;
        StringBuilder sb2 = new StringBuilder("ArmMappingEntry(kitsu=");
        sb2.append(num);
        sb2.append(", anilist=");
        sb2.append(num2);
        sb2.append(", myanimelist=");
        a2.z(sb2, num3, ", anidb=", num4, ", animePlanet=");
        sb2.append(str);
        sb2.append(", anisearch=");
        sb2.append(num5);
        sb2.append(", livechart=");
        sb2.append(num6);
        sb2.append(", notifyMoe=");
        sb2.append(str2);
        sb2.append(", imdb=");
        sb2.append(str3);
        sb2.append(", themoviedb=");
        sb2.append(num7);
        sb2.append(", themoviedbSeason=");
        a2.z(sb2, num8, ", thetvdb=", num9, ", thetvdbSeason=");
        sb2.append(num10);
        sb2.append(", media=");
        sb2.append(str4);
        sb2.append(")");
        return sb2.toString();
    }

    public ArmMappingEntry(Integer num, Integer num2, Integer num3, Integer num4, String str, Integer num5, Integer num6, String str2, String str3, Integer num7, Integer num8, Integer num9, Integer num10, String str4) {
        this.kitsu = num;
        this.anilist = num2;
        this.myanimelist = num3;
        this.anidb = num4;
        this.animePlanet = str;
        this.anisearch = num5;
        this.livechart = num6;
        this.notifyMoe = str2;
        this.imdb = str3;
        this.themoviedb = num7;
        this.themoviedbSeason = num8;
        this.thetvdb = num9;
        this.thetvdbSeason = num10;
        this.media = str4;
    }

    public /* synthetic */ ArmMappingEntry(Integer num, Integer num2, Integer num3, Integer num4, String str, Integer num5, Integer num6, String str2, String str3, Integer num7, Integer num8, Integer num9, Integer num10, String str4, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : num, (i10 & 2) != 0 ? null : num2, (i10 & 4) != 0 ? null : num3, (i10 & 8) != 0 ? null : num4, (i10 & 16) != 0 ? null : str, (i10 & 32) != 0 ? null : num5, (i10 & 64) != 0 ? null : num6, (i10 & 128) != 0 ? null : str2, (i10 & 256) != 0 ? null : str3, (i10 & 512) != 0 ? null : num7, (i10 & 1024) != 0 ? null : num8, (i10 & 2048) != 0 ? null : num9, (i10 & 4096) != 0 ? null : num10, (i10 & 8192) != 0 ? null : str4);
    }
}
