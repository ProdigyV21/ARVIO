package io.sentry;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.github.jan.supabase.gotrue.SettingsSessionManager;

/* JADX INFO: loaded from: classes4.dex */
public enum m {
    All("__all__"),
    Default("default"),
    Error("error"),
    Feedback("feedback"),
    Session(SettingsSessionManager.SETTINGS_KEY),
    Attachment("attachment"),
    LogItem("log_item"),
    LogByte("log_byte"),
    TraceMetric("trace_metric"),
    Monitor("monitor"),
    Profile("profile"),
    ProfileChunkUi("profile_chunk_ui"),
    ProfileChunk("profile_chunk"),
    Transaction("transaction"),
    Replay("replay"),
    Span(TtmlNode.TAG_SPAN),
    Security("security"),
    UserReport("user_report"),
    Unknown("unknown");

    private final String category;

    m(String str) {
        this.category = str;
    }

    public String getCategory() {
        return this.category;
    }
}
