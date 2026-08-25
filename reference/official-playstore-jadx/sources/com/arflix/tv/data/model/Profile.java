package com.arflix.tv.data.model;

import androidx.compose.foundation.c;
import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b$\b\u0087\b\u0018\u00002\u00020\u0001Bw\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0006\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\f\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0006¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0006HÆ\u0003J\t\u0010#\u001a\u00020\bHÆ\u0003J\t\u0010$\u001a\u00020\u0006HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010&\u001a\u00020\fHÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010(\u001a\u00020\fHÆ\u0003J\t\u0010)\u001a\u00020\u0006HÆ\u0003J\t\u0010*\u001a\u00020\u0006HÆ\u0003J{\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u00062\b\b\u0002\u0010\u0010\u001a\u00020\u0006HÆ\u0001J\u0014\u0010,\u001a\u00020\f2\b\u0010-\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010.\u001a\u00020\bHÖ\u0081\u0004J\n\u0010/\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u001cR\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0014R\u0011\u0010\u000e\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u001cR\u0011\u0010\u000f\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0017R\u0011\u0010\u0010\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017¨\u00060"}, d2 = {"Lcom/arflix/tv/data/model/Profile;", "", TtmlNode.ATTR_ID, "", ContentDisposition.Parameters.Name, "avatarColor", "", "avatarId", "", "avatarImageVersion", "avatarImageStoragePath", "isKidsProfile", "", "pin", "isLocked", "createdAt", "lastUsedAt", "<init>", "(Ljava/lang/String;Ljava/lang/String;JIJLjava/lang/String;ZLjava/lang/String;ZJJ)V", "getId", "()Ljava/lang/String;", "getName", "getAvatarColor", "()J", "getAvatarId", "()I", "getAvatarImageVersion", "getAvatarImageStoragePath", "()Z", "getPin", "getCreatedAt", "getLastUsedAt", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "equals", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class Profile {
    public static final int $stable = 0;
    private final long avatarColor;
    private final int avatarId;
    private final String avatarImageStoragePath;
    private final long avatarImageVersion;
    private final long createdAt;
    private final String id;
    private final boolean isKidsProfile;
    private final boolean isLocked;
    private final long lastUsedAt;
    private final String name;
    private final String pin;

    public Profile(String str, String str2, long j10, int i10, long j11, String str3, boolean z, String str4, boolean z5, long j12, long j13) {
        this.id = str;
        this.name = str2;
        this.avatarColor = j10;
        this.avatarId = i10;
        this.avatarImageVersion = j11;
        this.avatarImageStoragePath = str3;
        this.isKidsProfile = z;
        this.pin = str4;
        this.isLocked = z5;
        this.createdAt = j12;
        this.lastUsedAt = j13;
    }

    public static /* synthetic */ Profile copy$default(Profile profile, String str, String str2, long j10, int i10, long j11, String str3, boolean z, String str4, boolean z5, long j12, long j13, int i11, Object obj) {
        long j14;
        String str5;
        Profile profile2;
        String str6;
        long j15;
        int i12;
        long j16;
        String str7;
        boolean z10;
        String str8;
        boolean z11;
        long j17;
        String str9 = (i11 & 1) != 0 ? profile.id : str;
        String str10 = (i11 & 2) != 0 ? profile.name : str2;
        long j18 = (i11 & 4) != 0 ? profile.avatarColor : j10;
        int i13 = (i11 & 8) != 0 ? profile.avatarId : i10;
        long j19 = (i11 & 16) != 0 ? profile.avatarImageVersion : j11;
        String str11 = (i11 & 32) != 0 ? profile.avatarImageStoragePath : str3;
        boolean z12 = (i11 & 64) != 0 ? profile.isKidsProfile : z;
        String str12 = (i11 & 128) != 0 ? profile.pin : str4;
        boolean z13 = (i11 & 256) != 0 ? profile.isLocked : z5;
        long j20 = (i11 & 512) != 0 ? profile.createdAt : j12;
        if ((i11 & 1024) != 0) {
            str5 = str9;
            j14 = profile.lastUsedAt;
            str6 = str10;
            j15 = j18;
            i12 = i13;
            j16 = j19;
            str7 = str11;
            z10 = z12;
            str8 = str12;
            z11 = z13;
            j17 = j20;
            profile2 = profile;
        } else {
            j14 = j13;
            str5 = str9;
            profile2 = profile;
            str6 = str10;
            j15 = j18;
            i12 = i13;
            j16 = j19;
            str7 = str11;
            z10 = z12;
            str8 = str12;
            z11 = z13;
            j17 = j20;
        }
        return profile2.copy(str5, str6, j15, i12, j16, str7, z10, str8, z11, j17, j14);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final long getCreatedAt() {
        return this.createdAt;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final long getLastUsedAt() {
        return this.lastUsedAt;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getAvatarColor() {
        return this.avatarColor;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getAvatarId() {
        return this.avatarId;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getAvatarImageVersion() {
        return this.avatarImageVersion;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getAvatarImageStoragePath() {
        return this.avatarImageStoragePath;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getIsKidsProfile() {
        return this.isKidsProfile;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getPin() {
        return this.pin;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final boolean getIsLocked() {
        return this.isLocked;
    }

    public final Profile copy(String id, String name, long avatarColor, int avatarId, long avatarImageVersion, String avatarImageStoragePath, boolean isKidsProfile, String pin, boolean isLocked, long createdAt, long lastUsedAt) {
        return new Profile(id, name, avatarColor, avatarId, avatarImageVersion, avatarImageStoragePath, isKidsProfile, pin, isLocked, createdAt, lastUsedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Profile)) {
            return false;
        }
        Profile profile = (Profile) other;
        return p.a(this.id, profile.id) && p.a(this.name, profile.name) && this.avatarColor == profile.avatarColor && this.avatarId == profile.avatarId && this.avatarImageVersion == profile.avatarImageVersion && p.a(this.avatarImageStoragePath, profile.avatarImageStoragePath) && this.isKidsProfile == profile.isKidsProfile && p.a(this.pin, profile.pin) && this.isLocked == profile.isLocked && this.createdAt == profile.createdAt && this.lastUsedAt == profile.lastUsedAt;
    }

    public final long getAvatarColor() {
        return this.avatarColor;
    }

    public final int getAvatarId() {
        return this.avatarId;
    }

    public final String getAvatarImageStoragePath() {
        return this.avatarImageStoragePath;
    }

    public final long getAvatarImageVersion() {
        return this.avatarImageVersion;
    }

    public final long getCreatedAt() {
        return this.createdAt;
    }

    public final String getId() {
        return this.id;
    }

    public final long getLastUsedAt() {
        return this.lastUsedAt;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPin() {
        return this.pin;
    }

    public int hashCode() {
        int iC = c.c(this.id.hashCode() * 31, 31, this.name);
        long j10 = this.avatarColor;
        int i10 = (((iC + ((int) (j10 ^ (j10 >>> 32)))) * 31) + this.avatarId) * 31;
        long j11 = this.avatarImageVersion;
        int i11 = (i10 + ((int) (j11 ^ (j11 >>> 32)))) * 31;
        String str = this.avatarImageStoragePath;
        int iHashCode = (((i11 + (str == null ? 0 : str.hashCode())) * 31) + (this.isKidsProfile ? 1231 : 1237)) * 31;
        String str2 = this.pin;
        int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        int i12 = this.isLocked ? 1231 : 1237;
        long j12 = this.createdAt;
        long j13 = this.lastUsedAt;
        return ((((iHashCode2 + i12) * 31) + ((int) (j12 ^ (j12 >>> 32)))) * 31) + ((int) (j13 ^ (j13 >>> 32)));
    }

    public final boolean isKidsProfile() {
        return this.isKidsProfile;
    }

    public final boolean isLocked() {
        return this.isLocked;
    }

    public String toString() {
        String str = this.id;
        String str2 = this.name;
        long j10 = this.avatarColor;
        int i10 = this.avatarId;
        long j11 = this.avatarImageVersion;
        String str3 = this.avatarImageStoragePath;
        boolean z = this.isKidsProfile;
        String str4 = this.pin;
        boolean z5 = this.isLocked;
        long j12 = this.createdAt;
        long j13 = this.lastUsedAt;
        StringBuilder sbR = a2.r("Profile(id=", str, ", name=", str2, ", avatarColor=");
        sbR.append(j10);
        sbR.append(", avatarId=");
        sbR.append(i10);
        c.z(sbR, ", avatarImageVersion=", j11, ", avatarImageStoragePath=");
        sbR.append(str3);
        sbR.append(", isKidsProfile=");
        sbR.append(z);
        sbR.append(", pin=");
        sbR.append(str4);
        sbR.append(", isLocked=");
        sbR.append(z5);
        sbR.append(", createdAt=");
        sbR.append(j12);
        sbR.append(", lastUsedAt=");
        sbR.append(j13);
        sbR.append(")");
        return sbR.toString();
    }

    public /* synthetic */ Profile(String str, String str2, long j10, int i10, long j11, String str3, boolean z, String str4, boolean z5, long j12, long j13, int i11, h hVar) {
        this((i11 & 1) != 0 ? UUID.randomUUID().toString() : str, str2, (i11 & 4) != 0 ? ProfileColors.INSTANCE.random() : j10, (i11 & 8) != 0 ? 0 : i10, (i11 & 16) != 0 ? 0L : j11, (i11 & 32) != 0 ? null : str3, (i11 & 64) != 0 ? false : z, (i11 & 128) != 0 ? null : str4, (i11 & 256) != 0 ? false : z5, (i11 & 512) != 0 ? System.currentTimeMillis() : j12, (i11 & 1024) != 0 ? System.currentTimeMillis() : j13);
    }
}
