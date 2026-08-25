package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import com.arflix.tv.data.model.MediaItem;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J-\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001a"}, d2 = {"Lcom/arflix/tv/data/repository/PersonMediaSearchResult;", "", "personId", "", ContentDisposition.Parameters.Name, "", "items", "", "Lcom/arflix/tv/data/model/MediaItem;", "<init>", "(ILjava/lang/String;Ljava/util/List;)V", "getPersonId", "()I", "getName", "()Ljava/lang/String;", "getItems", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class PersonMediaSearchResult {
    public static final int $stable = 0;
    private final List<MediaItem> items;
    private final String name;
    private final int personId;

    public PersonMediaSearchResult(int i10, String str, List<MediaItem> list) {
        this.personId = i10;
        this.name = str;
        this.items = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PersonMediaSearchResult copy$default(PersonMediaSearchResult personMediaSearchResult, int i10, String str, List list, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = personMediaSearchResult.personId;
        }
        if ((i11 & 2) != 0) {
            str = personMediaSearchResult.name;
        }
        if ((i11 & 4) != 0) {
            list = personMediaSearchResult.items;
        }
        return personMediaSearchResult.copy(i10, str, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getPersonId() {
        return this.personId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final List<MediaItem> component3() {
        return this.items;
    }

    public final PersonMediaSearchResult copy(int personId, String name, List<MediaItem> items) {
        return new PersonMediaSearchResult(personId, name, items);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PersonMediaSearchResult)) {
            return false;
        }
        PersonMediaSearchResult personMediaSearchResult = (PersonMediaSearchResult) other;
        return this.personId == personMediaSearchResult.personId && kotlin.jvm.internal.p.a(this.name, personMediaSearchResult.name) && kotlin.jvm.internal.p.a(this.items, personMediaSearchResult.items);
    }

    public final List<MediaItem> getItems() {
        return this.items;
    }

    public final String getName() {
        return this.name;
    }

    public final int getPersonId() {
        return this.personId;
    }

    public int hashCode() {
        return this.items.hashCode() + androidx.compose.foundation.c.c(this.personId * 31, 31, this.name);
    }

    public String toString() {
        int i10 = this.personId;
        String str = this.name;
        return a0.c.q(a2.o("PersonMediaSearchResult(personId=", i10, ", name=", str, ", items="), this.items, ")");
    }
}
