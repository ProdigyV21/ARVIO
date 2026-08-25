package com.arflix.tv.ui.screens.player;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B)\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0004HÆ\u0003J/\u0010\u0013\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0004HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lcom/arflix/tv/ui/screens/player/TranslationResult;", "", "lines", "", "", "success", "", "errorMessage", "<init>", "(Ljava/util/List;ZLjava/lang/String;)V", "getLines", "()Ljava/util/List;", "getSuccess", "()Z", "getErrorMessage", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TranslationResult {
    public static final int $stable = 0;
    private final String errorMessage;
    private final List<String> lines;
    private final boolean success;

    public TranslationResult(List<String> list, boolean z, String str) {
        this.lines = list;
        this.success = z;
        this.errorMessage = str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TranslationResult copy$default(TranslationResult translationResult, List list, boolean z, String str, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            list = translationResult.lines;
        }
        if ((i10 & 2) != 0) {
            z = translationResult.success;
        }
        if ((i10 & 4) != 0) {
            str = translationResult.errorMessage;
        }
        return translationResult.copy(list, z, str);
    }

    public final List<String> component1() {
        return this.lines;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getSuccess() {
        return this.success;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getErrorMessage() {
        return this.errorMessage;
    }

    public final TranslationResult copy(List<String> lines, boolean success, String errorMessage) {
        return new TranslationResult(lines, success, errorMessage);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TranslationResult)) {
            return false;
        }
        TranslationResult translationResult = (TranslationResult) other;
        return kotlin.jvm.internal.p.a(this.lines, translationResult.lines) && this.success == translationResult.success && kotlin.jvm.internal.p.a(this.errorMessage, translationResult.errorMessage);
    }

    public final String getErrorMessage() {
        return this.errorMessage;
    }

    public final List<String> getLines() {
        return this.lines;
    }

    public final boolean getSuccess() {
        return this.success;
    }

    public int hashCode() {
        int iHashCode = ((this.lines.hashCode() * 31) + (this.success ? 1231 : 1237)) * 31;
        String str = this.errorMessage;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        List<String> list = this.lines;
        boolean z = this.success;
        String str = this.errorMessage;
        StringBuilder sb2 = new StringBuilder("TranslationResult(lines=");
        sb2.append(list);
        sb2.append(", success=");
        sb2.append(z);
        sb2.append(", errorMessage=");
        return a0.c.p(sb2, str, ")");
    }

    public /* synthetic */ TranslationResult(List list, boolean z, String str, int i10, kotlin.jvm.internal.h hVar) {
        this(list, z, (i10 & 4) != 0 ? null : str);
    }
}
