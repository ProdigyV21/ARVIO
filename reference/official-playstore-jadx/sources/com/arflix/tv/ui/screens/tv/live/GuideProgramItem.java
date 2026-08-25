package com.arflix.tv.ui.screens.tv.live;

import com.arflix.tv.data.model.IptvProgram;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/GuideProgramItem;", "", "program", "Lcom/arflix/tv/data/model/IptvProgram;", "state", "Lcom/arflix/tv/ui/screens/tv/live/GuideProgramState;", "<init>", "(Lcom/arflix/tv/data/model/IptvProgram;Lcom/arflix/tv/ui/screens/tv/live/GuideProgramState;)V", "getProgram", "()Lcom/arflix/tv/data/model/IptvProgram;", "getState", "()Lcom/arflix/tv/ui/screens/tv/live/GuideProgramState;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class GuideProgramItem {
    private final IptvProgram program;
    private final GuideProgramState state;

    public GuideProgramItem(IptvProgram iptvProgram, GuideProgramState guideProgramState) {
        this.program = iptvProgram;
        this.state = guideProgramState;
    }

    public static /* synthetic */ GuideProgramItem copy$default(GuideProgramItem guideProgramItem, IptvProgram iptvProgram, GuideProgramState guideProgramState, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            iptvProgram = guideProgramItem.program;
        }
        if ((i10 & 2) != 0) {
            guideProgramState = guideProgramItem.state;
        }
        return guideProgramItem.copy(iptvProgram, guideProgramState);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final IptvProgram getProgram() {
        return this.program;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final GuideProgramState getState() {
        return this.state;
    }

    public final GuideProgramItem copy(IptvProgram program, GuideProgramState state) {
        return new GuideProgramItem(program, state);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GuideProgramItem)) {
            return false;
        }
        GuideProgramItem guideProgramItem = (GuideProgramItem) other;
        return kotlin.jvm.internal.p.a(this.program, guideProgramItem.program) && this.state == guideProgramItem.state;
    }

    public final IptvProgram getProgram() {
        return this.program;
    }

    public final GuideProgramState getState() {
        return this.state;
    }

    public int hashCode() {
        return this.state.hashCode() + (this.program.hashCode() * 31);
    }

    public String toString() {
        return "GuideProgramItem(program=" + this.program + ", state=" + this.state + ")";
    }
}
