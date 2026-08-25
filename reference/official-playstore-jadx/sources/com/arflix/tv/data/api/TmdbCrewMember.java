package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.gson.annotations.SerializedName;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B;\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J=\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000e¨\u0006\u001d"}, d2 = {"Lcom/arflix/tv/data/api/TmdbCrewMember;", "", TtmlNode.ATTR_ID, "", ContentDisposition.Parameters.Name, "", "job", "profilePath", "department", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()I", "getName", "()Ljava/lang/String;", "getJob", "getProfilePath", "getDepartment", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TmdbCrewMember {
    public static final int $stable = 0;
    private final String department;
    private final int id;
    private final String job;
    private final String name;

    @SerializedName("profile_path")
    private final String profilePath;

    public TmdbCrewMember() {
        this(0, null, null, null, null, 31, null);
    }

    public static /* synthetic */ TmdbCrewMember copy$default(TmdbCrewMember tmdbCrewMember, int i10, String str, String str2, String str3, String str4, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = tmdbCrewMember.id;
        }
        if ((i11 & 2) != 0) {
            str = tmdbCrewMember.name;
        }
        if ((i11 & 4) != 0) {
            str2 = tmdbCrewMember.job;
        }
        if ((i11 & 8) != 0) {
            str3 = tmdbCrewMember.profilePath;
        }
        if ((i11 & 16) != 0) {
            str4 = tmdbCrewMember.department;
        }
        String str5 = str4;
        String str6 = str2;
        return tmdbCrewMember.copy(i10, str, str6, str3, str5);
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
    public final String getJob() {
        return this.job;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getProfilePath() {
        return this.profilePath;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getDepartment() {
        return this.department;
    }

    public final TmdbCrewMember copy(int id, String name, String job, String profilePath, String department) {
        return new TmdbCrewMember(id, name, job, profilePath, department);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TmdbCrewMember)) {
            return false;
        }
        TmdbCrewMember tmdbCrewMember = (TmdbCrewMember) other;
        return this.id == tmdbCrewMember.id && p.a(this.name, tmdbCrewMember.name) && p.a(this.job, tmdbCrewMember.job) && p.a(this.profilePath, tmdbCrewMember.profilePath) && p.a(this.department, tmdbCrewMember.department);
    }

    public final String getDepartment() {
        return this.department;
    }

    public final int getId() {
        return this.id;
    }

    public final String getJob() {
        return this.job;
    }

    public final String getName() {
        return this.name;
    }

    public final String getProfilePath() {
        return this.profilePath;
    }

    public int hashCode() {
        int iC = androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(this.id * 31, 31, this.name), 31, this.job);
        String str = this.profilePath;
        return this.department.hashCode() + ((iC + (str == null ? 0 : str.hashCode())) * 31);
    }

    public String toString() {
        int i10 = this.id;
        String str = this.name;
        String str2 = this.job;
        String str3 = this.profilePath;
        String str4 = this.department;
        StringBuilder sbO = a2.o("TmdbCrewMember(id=", i10, ", name=", str, ", job=");
        y.a.i(sbO, str2, ", profilePath=", str3, ", department=");
        return a0.c.p(sbO, str4, ")");
    }

    public TmdbCrewMember(int i10, String str, String str2, String str3, String str4) {
        this.id = i10;
        this.name = str;
        this.job = str2;
        this.profilePath = str3;
        this.department = str4;
    }

    /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
        java.lang.NullPointerException
        */
    public /* synthetic */ TmdbCrewMember(int r2, java.lang.String r3, java.lang.String r4, java.lang.String r5, java.lang.String r6, int r7, kotlin.jvm.internal.h r8) {
        /*
            r1 = this;
            r8 = r7 & 1
            if (r8 == 0) goto L5
            r2 = 0
        L5:
            r8 = r7 & 2
            java.lang.String r0 = ""
            if (r8 == 0) goto Lc
            r3 = r0
        Lc:
            r8 = r7 & 4
            if (r8 == 0) goto L11
            r4 = r0
        L11:
            r8 = r7 & 8
            if (r8 == 0) goto L16
            r5 = 0
        L16:
            r7 = r7 & 16
            if (r7 == 0) goto L21
            r8 = r0
            r6 = r4
            r7 = r5
            r4 = r2
            r5 = r3
            r3 = r1
            goto L27
        L21:
            r8 = r6
            r7 = r5
            r5 = r3
            r6 = r4
            r3 = r1
            r4 = r2
        L27:
            r3.<init>(r4, r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.api.TmdbCrewMember.<init>(int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, kotlin.jvm.internal.h):void");
    }
}
