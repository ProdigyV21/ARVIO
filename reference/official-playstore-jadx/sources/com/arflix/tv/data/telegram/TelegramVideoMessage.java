package com.arflix.tv.data.telegram;

import com.google.android.gms.cast.MediaTrack;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\u0006¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001d\u001a\u00020\bHÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\bHÆ\u0003J\t\u0010 \u001a\u00020\u0006HÆ\u0003J\t\u0010!\u001a\u00020\u0006HÆ\u0003JY\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\u0006HÆ\u0001J\u0014\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010&\u001a\u00020\bHÖ\u0081\u0004J\n\u0010'\u001a\u00020\u0006HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0011\u0010\n\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u0011\u0010\f\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013¨\u0006("}, d2 = {"Lcom/arflix/tv/data/telegram/TelegramVideoMessage;", "", "messageId", "", "chatId", "fileName", "", "fileId", "", "fileSize", "duration", "mimeType", MediaTrack.ROLE_CAPTION, "<init>", "(JJLjava/lang/String;IJILjava/lang/String;Ljava/lang/String;)V", "getMessageId", "()J", "getChatId", "getFileName", "()Ljava/lang/String;", "getFileId", "()I", "getFileSize", "getDuration", "getMimeType", "getCaption", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TelegramVideoMessage {
    public static final int $stable = 0;
    private final String caption;
    private final long chatId;
    private final int duration;
    private final int fileId;
    private final String fileName;
    private final long fileSize;
    private final long messageId;
    private final String mimeType;

    public TelegramVideoMessage(long j10, long j11, String str, int i10, long j12, int i11, String str2, String str3) {
        this.messageId = j10;
        this.chatId = j11;
        this.fileName = str;
        this.fileId = i10;
        this.fileSize = j12;
        this.duration = i11;
        this.mimeType = str2;
        this.caption = str3;
    }

    public static /* synthetic */ TelegramVideoMessage copy$default(TelegramVideoMessage telegramVideoMessage, long j10, long j11, String str, int i10, long j12, int i11, String str2, String str3, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            j10 = telegramVideoMessage.messageId;
        }
        return telegramVideoMessage.copy(j10, (i12 & 2) != 0 ? telegramVideoMessage.chatId : j11, (i12 & 4) != 0 ? telegramVideoMessage.fileName : str, (i12 & 8) != 0 ? telegramVideoMessage.fileId : i10, (i12 & 16) != 0 ? telegramVideoMessage.fileSize : j12, (i12 & 32) != 0 ? telegramVideoMessage.duration : i11, (i12 & 64) != 0 ? telegramVideoMessage.mimeType : str2, (i12 & 128) != 0 ? telegramVideoMessage.caption : str3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getMessageId() {
        return this.messageId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getChatId() {
        return this.chatId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getFileName() {
        return this.fileName;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getFileId() {
        return this.fileId;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getFileSize() {
        return this.fileSize;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getDuration() {
        return this.duration;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getMimeType() {
        return this.mimeType;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getCaption() {
        return this.caption;
    }

    public final TelegramVideoMessage copy(long messageId, long chatId, String fileName, int fileId, long fileSize, int duration, String mimeType, String caption) {
        return new TelegramVideoMessage(messageId, chatId, fileName, fileId, fileSize, duration, mimeType, caption);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TelegramVideoMessage)) {
            return false;
        }
        TelegramVideoMessage telegramVideoMessage = (TelegramVideoMessage) other;
        return this.messageId == telegramVideoMessage.messageId && this.chatId == telegramVideoMessage.chatId && p.a(this.fileName, telegramVideoMessage.fileName) && this.fileId == telegramVideoMessage.fileId && this.fileSize == telegramVideoMessage.fileSize && this.duration == telegramVideoMessage.duration && p.a(this.mimeType, telegramVideoMessage.mimeType) && p.a(this.caption, telegramVideoMessage.caption);
    }

    public final String getCaption() {
        return this.caption;
    }

    public final long getChatId() {
        return this.chatId;
    }

    public final int getDuration() {
        return this.duration;
    }

    public final int getFileId() {
        return this.fileId;
    }

    public final String getFileName() {
        return this.fileName;
    }

    public final long getFileSize() {
        return this.fileSize;
    }

    public final long getMessageId() {
        return this.messageId;
    }

    public final String getMimeType() {
        return this.mimeType;
    }

    public int hashCode() {
        long j10 = this.messageId;
        long j11 = this.chatId;
        int iC = (androidx.compose.foundation.c.c(((((int) (j10 ^ (j10 >>> 32))) * 31) + ((int) (j11 ^ (j11 >>> 32)))) * 31, 31, this.fileName) + this.fileId) * 31;
        long j12 = this.fileSize;
        return this.caption.hashCode() + androidx.compose.foundation.c.c((((iC + ((int) (j12 ^ (j12 >>> 32)))) * 31) + this.duration) * 31, 31, this.mimeType);
    }

    public String toString() {
        long j10 = this.messageId;
        long j11 = this.chatId;
        String str = this.fileName;
        int i10 = this.fileId;
        long j12 = this.fileSize;
        int i11 = this.duration;
        String str2 = this.mimeType;
        String str3 = this.caption;
        StringBuilder sbR = androidx.compose.material3.d.r(j10, "TelegramVideoMessage(messageId=", ", chatId=");
        sbR.append(j11);
        sbR.append(", fileName=");
        sbR.append(str);
        sbR.append(", fileId=");
        sbR.append(i10);
        sbR.append(", fileSize=");
        sbR.append(j12);
        sbR.append(", duration=");
        sbR.append(i11);
        y.a.i(sbR, ", mimeType=", str2, ", caption=", str3);
        sbR.append(")");
        return sbR.toString();
    }
}
