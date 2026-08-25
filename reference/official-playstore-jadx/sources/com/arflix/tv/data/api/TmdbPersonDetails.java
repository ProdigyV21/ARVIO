package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.gson.annotations.SerializedName;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BW\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u000bHÆ\u0003JY\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bHÆ\u0001J\u0014\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010#\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010$\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0018\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006%"}, d2 = {"Lcom/arflix/tv/data/api/TmdbPersonDetails;", "", TtmlNode.ATTR_ID, "", ContentDisposition.Parameters.Name, "", "biography", "placeOfBirth", "birthday", "profilePath", "combinedCredits", "Lcom/arflix/tv/data/api/TmdbCombinedCredits;", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/api/TmdbCombinedCredits;)V", "getId", "()I", "getName", "()Ljava/lang/String;", "getBiography", "getPlaceOfBirth", "getBirthday", "getProfilePath", "getCombinedCredits", "()Lcom/arflix/tv/data/api/TmdbCombinedCredits;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TmdbPersonDetails {
    public static final int $stable = 8;
    private final String biography;
    private final String birthday;

    @SerializedName("combined_credits")
    private final TmdbCombinedCredits combinedCredits;
    private final int id;
    private final String name;

    @SerializedName("place_of_birth")
    private final String placeOfBirth;

    @SerializedName("profile_path")
    private final String profilePath;

    public TmdbPersonDetails() {
        this(0, null, null, null, null, null, null, 127, null);
    }

    public static /* synthetic */ TmdbPersonDetails copy$default(TmdbPersonDetails tmdbPersonDetails, int i10, String str, String str2, String str3, String str4, String str5, TmdbCombinedCredits tmdbCombinedCredits, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = tmdbPersonDetails.id;
        }
        if ((i11 & 2) != 0) {
            str = tmdbPersonDetails.name;
        }
        if ((i11 & 4) != 0) {
            str2 = tmdbPersonDetails.biography;
        }
        if ((i11 & 8) != 0) {
            str3 = tmdbPersonDetails.placeOfBirth;
        }
        if ((i11 & 16) != 0) {
            str4 = tmdbPersonDetails.birthday;
        }
        if ((i11 & 32) != 0) {
            str5 = tmdbPersonDetails.profilePath;
        }
        if ((i11 & 64) != 0) {
            tmdbCombinedCredits = tmdbPersonDetails.combinedCredits;
        }
        String str6 = str5;
        TmdbCombinedCredits tmdbCombinedCredits2 = tmdbCombinedCredits;
        String str7 = str4;
        String str8 = str2;
        return tmdbPersonDetails.copy(i10, str, str8, str3, str7, str6, tmdbCombinedCredits2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getBiography() {
        return this.biography;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getPlaceOfBirth() {
        return this.placeOfBirth;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getBirthday() {
        return this.birthday;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getProfilePath() {
        return this.profilePath;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final TmdbCombinedCredits getCombinedCredits() {
        return this.combinedCredits;
    }

    public final TmdbPersonDetails copy(int id, String name, String biography, String placeOfBirth, String birthday, String profilePath, TmdbCombinedCredits combinedCredits) {
        return new TmdbPersonDetails(id, name, biography, placeOfBirth, birthday, profilePath, combinedCredits);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TmdbPersonDetails)) {
            return false;
        }
        TmdbPersonDetails tmdbPersonDetails = (TmdbPersonDetails) other;
        return this.id == tmdbPersonDetails.id && p.a(this.name, tmdbPersonDetails.name) && p.a(this.biography, tmdbPersonDetails.biography) && p.a(this.placeOfBirth, tmdbPersonDetails.placeOfBirth) && p.a(this.birthday, tmdbPersonDetails.birthday) && p.a(this.profilePath, tmdbPersonDetails.profilePath) && p.a(this.combinedCredits, tmdbPersonDetails.combinedCredits);
    }

    public final String getBiography() {
        return this.biography;
    }

    public final String getBirthday() {
        return this.birthday;
    }

    public final TmdbCombinedCredits getCombinedCredits() {
        return this.combinedCredits;
    }

    public final int getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPlaceOfBirth() {
        return this.placeOfBirth;
    }

    public final String getProfilePath() {
        return this.profilePath;
    }

    public int hashCode() {
        int iC = androidx.compose.foundation.c.c(this.id * 31, 31, this.name);
        String str = this.biography;
        int iHashCode = (iC + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.placeOfBirth;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.birthday;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.profilePath;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        TmdbCombinedCredits tmdbCombinedCredits = this.combinedCredits;
        return iHashCode4 + (tmdbCombinedCredits != null ? tmdbCombinedCredits.hashCode() : 0);
    }

    public String toString() {
        int i10 = this.id;
        String str = this.name;
        String str2 = this.biography;
        String str3 = this.placeOfBirth;
        String str4 = this.birthday;
        String str5 = this.profilePath;
        TmdbCombinedCredits tmdbCombinedCredits = this.combinedCredits;
        StringBuilder sbO = a2.o("TmdbPersonDetails(id=", i10, ", name=", str, ", biography=");
        y.a.i(sbO, str2, ", placeOfBirth=", str3, ", birthday=");
        y.a.i(sbO, str4, ", profilePath=", str5, ", combinedCredits=");
        sbO.append(tmdbCombinedCredits);
        sbO.append(")");
        return sbO.toString();
    }

    public TmdbPersonDetails(int i10, String str, String str2, String str3, String str4, String str5, TmdbCombinedCredits tmdbCombinedCredits) {
        this.id = i10;
        this.name = str;
        this.biography = str2;
        this.placeOfBirth = str3;
        this.birthday = str4;
        this.profilePath = str5;
        this.combinedCredits = tmdbCombinedCredits;
    }

    /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
        java.lang.NullPointerException
        */
    public /* synthetic */ TmdbPersonDetails(int r2, java.lang.String r3, java.lang.String r4, java.lang.String r5, java.lang.String r6, java.lang.String r7, com.arflix.tv.data.api.TmdbCombinedCredits r8, int r9, kotlin.jvm.internal.h r10) {
        /*
            r1 = this;
            r10 = r9 & 1
            if (r10 == 0) goto L5
            r2 = 0
        L5:
            r10 = r9 & 2
            if (r10 == 0) goto Lb
            java.lang.String r3 = ""
        Lb:
            r10 = r9 & 4
            r0 = 0
            if (r10 == 0) goto L11
            r4 = r0
        L11:
            r10 = r9 & 8
            if (r10 == 0) goto L16
            r5 = r0
        L16:
            r10 = r9 & 16
            if (r10 == 0) goto L1b
            r6 = r0
        L1b:
            r10 = r9 & 32
            if (r10 == 0) goto L20
            r7 = r0
        L20:
            r9 = r9 & 64
            if (r9 == 0) goto L2d
            r10 = r0
            r8 = r6
            r9 = r7
            r6 = r4
            r7 = r5
            r4 = r2
            r5 = r3
            r3 = r1
            goto L35
        L2d:
            r10 = r8
            r9 = r7
            r7 = r5
            r8 = r6
            r5 = r3
            r6 = r4
            r3 = r1
            r4 = r2
        L35:
            r3.<init>(r4, r5, r6, r7, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.api.TmdbPersonDetails.<init>(int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.arflix.tv.data.api.TmdbCombinedCredits, int, kotlin.jvm.internal.h):void");
    }
}
