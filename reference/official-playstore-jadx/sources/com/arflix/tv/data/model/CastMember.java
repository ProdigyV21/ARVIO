package com.arflix.tv.data.model;

import androidx.compose.foundation.c;
import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J3\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006\u001b"}, d2 = {"Lcom/arflix/tv/data/model/CastMember;", "Ljava/io/Serializable;", TtmlNode.ATTR_ID, "", ContentDisposition.Parameters.Name, "", "character", "profilePath", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()I", "getName", "()Ljava/lang/String;", "getCharacter", "getProfilePath", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class CastMember implements Serializable {
    public static final int $stable = 0;
    private final String character;
    private final int id;
    private final String name;
    private final String profilePath;

    public CastMember(int i10, String str, String str2, String str3) {
        this.id = i10;
        this.name = str;
        this.character = str2;
        this.profilePath = str3;
    }

    public static /* synthetic */ CastMember copy$default(CastMember castMember, int i10, String str, String str2, String str3, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = castMember.id;
        }
        if ((i11 & 2) != 0) {
            str = castMember.name;
        }
        if ((i11 & 4) != 0) {
            str2 = castMember.character;
        }
        if ((i11 & 8) != 0) {
            str3 = castMember.profilePath;
        }
        return castMember.copy(i10, str, str2, str3);
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
    public final String getCharacter() {
        return this.character;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getProfilePath() {
        return this.profilePath;
    }

    public final CastMember copy(int id, String name, String character, String profilePath) {
        return new CastMember(id, name, character, profilePath);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CastMember)) {
            return false;
        }
        CastMember castMember = (CastMember) other;
        return this.id == castMember.id && p.a(this.name, castMember.name) && p.a(this.character, castMember.character) && p.a(this.profilePath, castMember.profilePath);
    }

    public final String getCharacter() {
        return this.character;
    }

    public final int getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getProfilePath() {
        return this.profilePath;
    }

    public int hashCode() {
        int iC = c.c(c.c(this.id * 31, 31, this.name), 31, this.character);
        String str = this.profilePath;
        return iC + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        int i10 = this.id;
        String str = this.name;
        return a2.n(a2.o("CastMember(id=", i10, ", name=", str, ", character="), this.character, ", profilePath=", this.profilePath, ")");
    }

    public /* synthetic */ CastMember(int i10, String str, String str2, String str3, int i11, h hVar) {
        this(i10, str, (i11 & 4) != 0 ? "" : str2, (i11 & 8) != 0 ? null : str3);
    }
}
