package com.arflix.tv.ui.screens.player;

import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/arflix/tv/ui/screens/player/SubtitleAiModel;", "", "<init>", "(Ljava/lang/String;I)V", "GROQ_LLAMA_70B", "GEMINI_FLASH_25", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SubtitleAiModel {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ SubtitleAiModel[] $VALUES;
    public static final SubtitleAiModel GROQ_LLAMA_70B = new SubtitleAiModel("GROQ_LLAMA_70B", 0);
    public static final SubtitleAiModel GEMINI_FLASH_25 = new SubtitleAiModel("GEMINI_FLASH_25", 1);

    private static final /* synthetic */ SubtitleAiModel[] $values() {
        return new SubtitleAiModel[]{GROQ_LLAMA_70B, GEMINI_FLASH_25};
    }

    static {
        SubtitleAiModel[] subtitleAiModelArr$values = $values();
        $VALUES = subtitleAiModelArr$values;
        $ENTRIES = new g7.b(subtitleAiModelArr$values);
    }

    private SubtitleAiModel(String str, int i10) {
    }

    public static g7.a<SubtitleAiModel> getEntries() {
        return $ENTRIES;
    }

    public static SubtitleAiModel valueOf(String str) {
        return (SubtitleAiModel) Enum.valueOf(SubtitleAiModel.class, str);
    }

    public static SubtitleAiModel[] values() {
        return (SubtitleAiModel[]) $VALUES.clone();
    }
}
