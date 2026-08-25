package com.arflix.tv.ui.screens.player;

import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u000fX\u0082T¢\u0006\u0002\n\u0000\"\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"TAG", "", "RTL_LANGUAGES", "", "TRANSLATION_ERROR_CONTENT_BLOCKED", "BLOCKED_FINISH_REASONS", "GROQ_MODEL_ID", "GROQ_URL", "GROQ_REASONING_EFFORT", "GROQ_REASONING_FORMAT", "GEMINI_MODEL_ID", "GEMINI_BASE_URL", "GEMINI_MAX_TRANSIENT_RETRIES", "", "GEMINI_RETRY_BASE_DELAY_MS", "", "GEMINI_RETRYABLE_HTTP", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class SubtitleTranslationServiceKt {
    private static final String GEMINI_BASE_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash-lite:generateContent";
    private static final int GEMINI_MAX_TRANSIENT_RETRIES = 2;
    private static final String GEMINI_MODEL_ID = "gemini-3.5-flash-lite";
    private static final long GEMINI_RETRY_BASE_DELAY_MS = 400;
    private static final String GROQ_MODEL_ID = "openai/gpt-oss-120b";
    private static final String GROQ_REASONING_EFFORT = "low";
    private static final String GROQ_REASONING_FORMAT = "hidden";
    private static final String GROQ_URL = "https://api.groq.com/openai/v1/chat/completions";
    private static final String TAG = "SubtitleTranslation";
    public static final String TRANSLATION_ERROR_CONTENT_BLOCKED = "CONTENT_BLOCKED";
    private static final Set<String> RTL_LANGUAGES = kotlin.collections.r.p0(new String[]{"hebrew", "arabic", "urdu", "persian", "farsi", "yiddish"});
    private static final Set<String> BLOCKED_FINISH_REASONS = kotlin.collections.r.p0(new String[]{"PROHIBITED_CONTENT", "SAFETY", "RECITATION", "BLOCKLIST", "SPII"});
    private static final Set<Integer> GEMINI_RETRYABLE_HTTP = kotlin.collections.r.p0(new Integer[]{500, 502, 503});
}
