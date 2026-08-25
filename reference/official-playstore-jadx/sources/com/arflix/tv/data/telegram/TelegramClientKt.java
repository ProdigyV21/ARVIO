package com.arflix.tv.data.telegram;

import kotlin.Metadata;
import kotlin.text.o;
import org.drinkless.tdlib.TdApi;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"toTelegramApiExceptionOrNull", "Lcom/arflix/tv/data/telegram/TelegramApiException;", "Lorg/drinkless/tdlib/TdApi$Error;", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class TelegramClientKt {
    public static final TelegramApiException toTelegramApiExceptionOrNull(TdApi.Error error) {
        String string;
        String str = error.message;
        if (str != null && (string = o.L0(str).toString()) != null && string.equalsIgnoreCase("Request aborted")) {
            return null;
        }
        int i10 = error.code;
        String str2 = error.message;
        if (str2 == null) {
            str2 = "Unknown Telegram error";
        }
        return new TelegramApiException(i10, str2);
    }
}
