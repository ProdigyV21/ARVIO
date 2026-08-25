package com.arflix.tv.data.model;

import androidx.compose.foundation.c;
import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import y.a;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001BU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\f0\u000bHÆ\u0003J[\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bHÆ\u0001J\u0014\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$HÖ\u0083\u0004J\n\u0010%\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010&\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006'"}, d2 = {"Lcom/arflix/tv/data/model/PersonDetails;", "Ljava/io/Serializable;", TtmlNode.ATTR_ID, "", ContentDisposition.Parameters.Name, "", "biography", "placeOfBirth", "birthday", "profilePath", "knownFor", "", "Lcom/arflix/tv/data/model/MediaItem;", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getId", "()I", "getName", "()Ljava/lang/String;", "getBiography", "getPlaceOfBirth", "getBirthday", "getProfilePath", "getKnownFor", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class PersonDetails implements Serializable {
    public static final int $stable = 0;
    private final String biography;
    private final String birthday;
    private final int id;
    private final List<MediaItem> knownFor;
    private final String name;
    private final String placeOfBirth;
    private final String profilePath;

    public PersonDetails(int i10, String str, String str2, String str3, String str4, String str5, List<MediaItem> list) {
        this.id = i10;
        this.name = str;
        this.biography = str2;
        this.placeOfBirth = str3;
        this.birthday = str4;
        this.profilePath = str5;
        this.knownFor = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PersonDetails copy$default(PersonDetails personDetails, int i10, String str, String str2, String str3, String str4, String str5, List list, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = personDetails.id;
        }
        if ((i11 & 2) != 0) {
            str = personDetails.name;
        }
        if ((i11 & 4) != 0) {
            str2 = personDetails.biography;
        }
        if ((i11 & 8) != 0) {
            str3 = personDetails.placeOfBirth;
        }
        if ((i11 & 16) != 0) {
            str4 = personDetails.birthday;
        }
        if ((i11 & 32) != 0) {
            str5 = personDetails.profilePath;
        }
        if ((i11 & 64) != 0) {
            list = personDetails.knownFor;
        }
        String str6 = str5;
        List list2 = list;
        String str7 = str4;
        String str8 = str2;
        return personDetails.copy(i10, str, str8, str3, str7, str6, list2);
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

    public final List<MediaItem> component7() {
        return this.knownFor;
    }

    public final PersonDetails copy(int id, String name, String biography, String placeOfBirth, String birthday, String profilePath, List<MediaItem> knownFor) {
        return new PersonDetails(id, name, biography, placeOfBirth, birthday, profilePath, knownFor);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PersonDetails)) {
            return false;
        }
        PersonDetails personDetails = (PersonDetails) other;
        return this.id == personDetails.id && p.a(this.name, personDetails.name) && p.a(this.biography, personDetails.biography) && p.a(this.placeOfBirth, personDetails.placeOfBirth) && p.a(this.birthday, personDetails.birthday) && p.a(this.profilePath, personDetails.profilePath) && p.a(this.knownFor, personDetails.knownFor);
    }

    public final String getBiography() {
        return this.biography;
    }

    public final String getBirthday() {
        return this.birthday;
    }

    public final int getId() {
        return this.id;
    }

    public final List<MediaItem> getKnownFor() {
        return this.knownFor;
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
        int iC = c.c(c.c(this.id * 31, 31, this.name), 31, this.biography);
        String str = this.placeOfBirth;
        int iHashCode = (iC + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.birthday;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.profilePath;
        return this.knownFor.hashCode() + ((iHashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31);
    }

    public String toString() {
        int i10 = this.id;
        String str = this.name;
        String str2 = this.biography;
        String str3 = this.placeOfBirth;
        String str4 = this.birthday;
        String str5 = this.profilePath;
        List<MediaItem> list = this.knownFor;
        StringBuilder sbO = a2.o("PersonDetails(id=", i10, ", name=", str, ", biography=");
        a.i(sbO, str2, ", placeOfBirth=", str3, ", birthday=");
        a.i(sbO, str4, ", profilePath=", str5, ", knownFor=");
        return a0.c.q(sbO, list, ")");
    }

    public /* synthetic */ PersonDetails(int i10, String str, String str2, String str3, String str4, String str5, List list, int i11, h hVar) {
        this(i10, str, (i11 & 4) != 0 ? "" : str2, (i11 & 8) != 0 ? null : str3, (i11 & 16) != 0 ? null : str4, (i11 & 32) != 0 ? null : str5, (i11 & 64) != 0 ? z.f19728i : list);
    }
}
