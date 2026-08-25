package com.arflix.tv.ui.screens.player;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0014\u0010\u0001\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0001\u0010\u0002\"\u0014\u0010\u0003\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0003\u0010\u0002\"\u0014\u0010\u0004\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0004\u0010\u0002\"\u0014\u0010\u0005\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0002\"\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0007\u0010\b\"\u0014\u0010\t\u001a\u00020\u00068\u0002X\u0082T¢\u0006\u0006\n\u0004\b\t\u0010\b\"\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u000b\u0010\f\"\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000f\"\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"", "TAG", "Ljava/lang/String;", "MODEL", "WS_BASE", "MIME_PCM_16K", "", "CLEAR_DELAY_MS", "J", "MIN_LINE_MS", "", "MAX_PENDING_LINES", "I", "", "SENTENCE_TERMINATORS", "[C", "Lkotlin/text/m;", "SENTENCE_SPLIT", "Lkotlin/text/m;", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class GeminiLiveTranslationServiceKt {
    private static final long CLEAR_DELAY_MS = 3000;
    private static final int MAX_PENDING_LINES = 3;
    private static final String MIME_PCM_16K = "audio/pcm;rate=16000";
    private static final long MIN_LINE_MS = 900;
    private static final String MODEL = "models/gemini-3.5-live-translate-preview";
    private static final String TAG = "GeminiLive";
    private static final String WS_BASE = "wss://generativelanguage.googleapis.com/ws/google.ai.generativelanguage.v1beta.GenerativeService.BidiGenerateContent";
    private static final char[] SENTENCE_TERMINATORS = {'.', '!', '?', 8230};
    private static final kotlin.text.m SENTENCE_SPLIT = new kotlin.text.m("(?<=[.!?…])\\s+");
}
