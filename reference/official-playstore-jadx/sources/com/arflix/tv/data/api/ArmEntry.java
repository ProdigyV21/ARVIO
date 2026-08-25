package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0007J\u001a\u0010\n\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u000bJ\u0014\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000f\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/arflix/tv/data/api/ArmEntry;", "", "myanimelist", "", "<init>", "(Ljava/lang/Integer;)V", "getMyanimelist", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "copy", "(Ljava/lang/Integer;)Lcom/arflix/tv/data/api/ArmEntry;", "equals", "", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class ArmEntry {
    public static final int $stable = 0;

    @SerializedName("myanimelist")
    private final Integer myanimelist;

    /* JADX WARN: Multi-variable type inference failed */
    public ArmEntry() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ ArmEntry copy$default(ArmEntry armEntry, Integer num, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            num = armEntry.myanimelist;
        }
        return armEntry.copy(num);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Integer getMyanimelist() {
        return this.myanimelist;
    }

    public final ArmEntry copy(Integer myanimelist) {
        return new ArmEntry(myanimelist);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ArmEntry) && p.a(this.myanimelist, ((ArmEntry) other).myanimelist);
    }

    public final Integer getMyanimelist() {
        return this.myanimelist;
    }

    public int hashCode() {
        Integer num = this.myanimelist;
        if (num == null) {
            return 0;
        }
        return num.hashCode();
    }

    public String toString() {
        return "ArmEntry(myanimelist=" + this.myanimelist + ")";
    }

    public ArmEntry(Integer num) {
        this.myanimelist = num;
    }

    public /* synthetic */ ArmEntry(Integer num, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : num);
    }
}
