package com.arflix.tv.data.telegram;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0015\b\u0087\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\bHÆ\u0003J\t\u0010\u0019\u001a\u00020\nHÆ\u0003J;\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0014\u0010\u001b\u001a\u00020\n2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001d\u001a\u00020\bHÖ\u0081\u0004J\n\u0010\u001e\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0014¨\u0006\u001f"}, d2 = {"Lcom/arflix/tv/data/telegram/TelegramChat;", "", TtmlNode.ATTR_ID, "", LinkHeader.Parameters.Title, "", LinkHeader.Parameters.Type, "memberCount", "", "isChannel", "", "<init>", "(JLjava/lang/String;Ljava/lang/String;IZ)V", "getId", "()J", "getTitle", "()Ljava/lang/String;", "getType", "getMemberCount", "()I", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TelegramChat {
    public static final int $stable = 0;
    private final long id;
    private final boolean isChannel;
    private final int memberCount;
    private final String title;
    private final String type;

    public TelegramChat(long j10, String str, String str2, int i10, boolean z) {
        this.id = j10;
        this.title = str;
        this.type = str2;
        this.memberCount = i10;
        this.isChannel = z;
    }

    public static /* synthetic */ TelegramChat copy$default(TelegramChat telegramChat, long j10, String str, String str2, int i10, boolean z, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            j10 = telegramChat.id;
        }
        long j11 = j10;
        if ((i11 & 2) != 0) {
            str = telegramChat.title;
        }
        String str3 = str;
        if ((i11 & 4) != 0) {
            str2 = telegramChat.type;
        }
        String str4 = str2;
        if ((i11 & 8) != 0) {
            i10 = telegramChat.memberCount;
        }
        int i12 = i10;
        if ((i11 & 16) != 0) {
            z = telegramChat.isChannel;
        }
        return telegramChat.copy(j11, str3, str4, i12, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getMemberCount() {
        return this.memberCount;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getIsChannel() {
        return this.isChannel;
    }

    public final TelegramChat copy(long id, String title, String type, int memberCount, boolean isChannel) {
        return new TelegramChat(id, title, type, memberCount, isChannel);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TelegramChat)) {
            return false;
        }
        TelegramChat telegramChat = (TelegramChat) other;
        return this.id == telegramChat.id && p.a(this.title, telegramChat.title) && p.a(this.type, telegramChat.type) && this.memberCount == telegramChat.memberCount && this.isChannel == telegramChat.isChannel;
    }

    public final long getId() {
        return this.id;
    }

    public final int getMemberCount() {
        return this.memberCount;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        long j10 = this.id;
        return ((androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(((int) (j10 ^ (j10 >>> 32))) * 31, 31, this.title), 31, this.type) + this.memberCount) * 31) + (this.isChannel ? 1231 : 1237);
    }

    public final boolean isChannel() {
        return this.isChannel;
    }

    public String toString() {
        return "TelegramChat(id=" + this.id + ", title=" + this.title + ", type=" + this.type + ", memberCount=" + this.memberCount + ", isChannel=" + this.isChannel + ")";
    }
}
