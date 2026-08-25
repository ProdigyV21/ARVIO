package io.sentry;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.github.jan.supabase.gotrue.SettingsSessionManager;
import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public enum v5 implements i2 {
    Session(SettingsSessionManager.SETTINGS_KEY),
    Event("event"),
    UserFeedback("user_report"),
    Attachment("attachment"),
    Transaction("transaction"),
    Profile("profile"),
    ProfileChunk("profile_chunk"),
    ClientReport("client_report"),
    ReplayEvent("replay_event"),
    ReplayRecording("replay_recording"),
    ReplayVideo("replay_video"),
    CheckIn("check_in"),
    Feedback("feedback"),
    Log("log"),
    TraceMetric("trace_metric"),
    Span(TtmlNode.TAG_SPAN),
    Unknown("__unknown__");

    private final String itemType;

    v5(String str) {
        this.itemType = str;
    }

    public static v5 resolve(Object obj) {
        return obj instanceof j5 ? ((io.sentry.protocol.i) ((j5) obj).f17985l.w(io.sentry.protocol.i.class, "feedback")) == null ? Event : Feedback : obj instanceof io.sentry.protocol.e0 ? Transaction : obj instanceof j7 ? Session : obj instanceof io.sentry.clientreport.b ? ClientReport : Attachment;
    }

    public static v5 valueOfLabel(String str) {
        for (v5 v5Var : values()) {
            if (v5Var.itemType.equals(str)) {
                return v5Var;
            }
        }
        return Unknown;
    }

    public String getItemType() {
        return this.itemType;
    }

    @Override // io.sentry.i2
    public void serialize(l3 l3Var, ILogger iLogger) throws IOException {
        l3Var.value(this.itemType);
    }
}
