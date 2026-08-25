package com.arflix.tv.ui.screens.tv.live;

import io.ktor.http.LinkHeader;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/PlaybackDiagnostic;", "", LinkHeader.Parameters.Title, "", "detail", "severity", "Lcom/arflix/tv/ui/screens/tv/live/PlaybackDiagnosticSeverity;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/ui/screens/tv/live/PlaybackDiagnosticSeverity;)V", "getTitle", "()Ljava/lang/String;", "getDetail", "getSeverity", "()Lcom/arflix/tv/ui/screens/tv/live/PlaybackDiagnosticSeverity;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class PlaybackDiagnostic {
    public static final int $stable = 0;
    private final String detail;
    private final PlaybackDiagnosticSeverity severity;
    private final String title;

    public PlaybackDiagnostic(String str, String str2, PlaybackDiagnosticSeverity playbackDiagnosticSeverity) {
        this.title = str;
        this.detail = str2;
        this.severity = playbackDiagnosticSeverity;
    }

    public static /* synthetic */ PlaybackDiagnostic copy$default(PlaybackDiagnostic playbackDiagnostic, String str, String str2, PlaybackDiagnosticSeverity playbackDiagnosticSeverity, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = playbackDiagnostic.title;
        }
        if ((i10 & 2) != 0) {
            str2 = playbackDiagnostic.detail;
        }
        if ((i10 & 4) != 0) {
            playbackDiagnosticSeverity = playbackDiagnostic.severity;
        }
        return playbackDiagnostic.copy(str, str2, playbackDiagnosticSeverity);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDetail() {
        return this.detail;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final PlaybackDiagnosticSeverity getSeverity() {
        return this.severity;
    }

    public final PlaybackDiagnostic copy(String title, String detail, PlaybackDiagnosticSeverity severity) {
        return new PlaybackDiagnostic(title, detail, severity);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlaybackDiagnostic)) {
            return false;
        }
        PlaybackDiagnostic playbackDiagnostic = (PlaybackDiagnostic) other;
        return kotlin.jvm.internal.p.a(this.title, playbackDiagnostic.title) && kotlin.jvm.internal.p.a(this.detail, playbackDiagnostic.detail) && this.severity == playbackDiagnostic.severity;
    }

    public final String getDetail() {
        return this.detail;
    }

    public final PlaybackDiagnosticSeverity getSeverity() {
        return this.severity;
    }

    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        return this.severity.hashCode() + androidx.compose.foundation.c.c(this.title.hashCode() * 31, 31, this.detail);
    }

    public String toString() {
        String str = this.title;
        String str2 = this.detail;
        PlaybackDiagnosticSeverity playbackDiagnosticSeverity = this.severity;
        StringBuilder sbR = androidx.fragment.app.a2.r("PlaybackDiagnostic(title=", str, ", detail=", str2, ", severity=");
        sbR.append(playbackDiagnosticSeverity);
        sbR.append(")");
        return sbR.toString();
    }

    public /* synthetic */ PlaybackDiagnostic(String str, String str2, PlaybackDiagnosticSeverity playbackDiagnosticSeverity, int i10, kotlin.jvm.internal.h hVar) {
        this(str, str2, (i10 & 4) != 0 ? PlaybackDiagnosticSeverity.Info : playbackDiagnosticSeverity);
    }
}
