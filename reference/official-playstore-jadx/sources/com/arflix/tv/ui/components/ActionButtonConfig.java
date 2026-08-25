package com.arflix.tv.ui.components;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\r\b\u0082\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\f\u0010\rJ\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0007HÆ\u0003¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0007HÆ\u0003¢\u0006\u0004\b\u0012\u0010\u0011J>\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007HÆ\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0015\u0010\rJ\u0010\u0010\u0017\u001a\u00020\u0016HÖ\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u001a\u0010\u001a\u001a\u00020\u00072\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001c\u001a\u0004\b\u001d\u0010\rR\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001e\u001a\u0004\b\u001f\u0010\u000fR\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010 \u001a\u0004\b!\u0010\u0011R\u0017\u0010\t\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010 \u001a\u0004\b\"\u0010\u0011¨\u0006#"}, d2 = {"Lcom/arflix/tv/ui/components/ActionButtonConfig;", "", "", "label", "Lkotlin/Function0;", "Lx6/t0;", "action", "", "highlighted", "enabled", "<init>", "(Ljava/lang/String;Lr7/a;ZZ)V", "component1", "()Ljava/lang/String;", "component2", "()Lr7/a;", "component3", "()Z", "component4", "copy", "(Ljava/lang/String;Lr7/a;ZZ)Lcom/arflix/tv/ui/components/ActionButtonConfig;", "toString", "", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getLabel", "Lr7/a;", "getAction", "Z", "getHighlighted", "getEnabled", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class ActionButtonConfig {
    private final r7.a<x6.t0> action;
    private final boolean enabled;
    private final boolean highlighted;
    private final String label;

    public ActionButtonConfig(String str, r7.a<x6.t0> aVar, boolean z, boolean z5) {
        this.label = str;
        this.action = aVar;
        this.highlighted = z;
        this.enabled = z5;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ActionButtonConfig copy$default(ActionButtonConfig actionButtonConfig, String str, r7.a aVar, boolean z, boolean z5, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = actionButtonConfig.label;
        }
        if ((i10 & 2) != 0) {
            aVar = actionButtonConfig.action;
        }
        if ((i10 & 4) != 0) {
            z = actionButtonConfig.highlighted;
        }
        if ((i10 & 8) != 0) {
            z5 = actionButtonConfig.enabled;
        }
        return actionButtonConfig.copy(str, aVar, z, z5);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    public final r7.a<x6.t0> component2() {
        return this.action;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getHighlighted() {
        return this.highlighted;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getEnabled() {
        return this.enabled;
    }

    public final ActionButtonConfig copy(String label, r7.a<x6.t0> action, boolean highlighted, boolean enabled) {
        return new ActionButtonConfig(label, action, highlighted, enabled);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ActionButtonConfig)) {
            return false;
        }
        ActionButtonConfig actionButtonConfig = (ActionButtonConfig) other;
        return kotlin.jvm.internal.p.a(this.label, actionButtonConfig.label) && kotlin.jvm.internal.p.a(this.action, actionButtonConfig.action) && this.highlighted == actionButtonConfig.highlighted && this.enabled == actionButtonConfig.enabled;
    }

    public final r7.a<x6.t0> getAction() {
        return this.action;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final boolean getHighlighted() {
        return this.highlighted;
    }

    public final String getLabel() {
        return this.label;
    }

    public int hashCode() {
        return ((((this.action.hashCode() + (this.label.hashCode() * 31)) * 31) + (this.highlighted ? 1231 : 1237)) * 31) + (this.enabled ? 1231 : 1237);
    }

    public String toString() {
        return "ActionButtonConfig(label=" + this.label + ", action=" + this.action + ", highlighted=" + this.highlighted + ", enabled=" + this.enabled + ")";
    }

    public /* synthetic */ ActionButtonConfig(String str, r7.a aVar, boolean z, boolean z5, int i10, kotlin.jvm.internal.h hVar) {
        this(str, aVar, (i10 & 4) != 0 ? false : z, (i10 & 8) != 0 ? true : z5);
    }
}
