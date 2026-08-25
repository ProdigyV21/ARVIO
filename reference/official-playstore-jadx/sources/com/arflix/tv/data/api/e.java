package com.arflix.tv.data.api;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.arflix.tv.worker.TraktSyncWorker;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class e {
    public static /* synthetic */ Object a(SimklApi simklApi, String str, String str2, SimklSyncHistoryBody simklSyncHistoryBody, String str3, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addToHistory");
        }
        if ((i10 & 8) != 0) {
            str3 = null;
        }
        return simklApi.addToHistory(str, str2, simklSyncHistoryBody, str3, dVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object b(SimklApi simklApi, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, d7.d dVar, int i10, Object obj) {
        String str10;
        SimklApi simklApi2;
        String str11;
        String str12;
        String str13;
        d7.d dVar2;
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getAllItems");
        }
        String str14 = (i10 & 8) != 0 ? TtmlNode.COMBINE_ALL : str4;
        String str15 = (i10 & 16) != 0 ? null : str5;
        String str16 = (i10 & 32) != 0 ? TraktSyncWorker.SYNC_MODE_FULL : str6;
        String str17 = (i10 & 64) != 0 ? "yes" : str7;
        String str18 = (i10 & 128) != 0 ? "yes" : str8;
        if ((i10 & 256) != 0) {
            str10 = "yes";
            str11 = str;
            str12 = str2;
            str13 = str3;
            dVar2 = dVar;
            simklApi2 = simklApi;
        } else {
            str10 = str9;
            simklApi2 = simklApi;
            str11 = str;
            str12 = str2;
            str13 = str3;
            dVar2 = dVar;
        }
        return simklApi2.getAllItems(str11, str12, str13, str14, str15, str16, str17, str18, str10, dVar2);
    }
}
