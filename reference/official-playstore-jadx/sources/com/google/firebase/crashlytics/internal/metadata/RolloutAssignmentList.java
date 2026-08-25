package com.google.firebase.crashlytics.internal.metadata;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class RolloutAssignmentList {
    static final String ROLLOUTS_STATE = "rolloutsState";
    private final int maxEntries;
    private final List<RolloutAssignment> rolloutsState = new ArrayList();

    public RolloutAssignmentList(int i10) {
        this.maxEntries = i10;
    }

    public List<CrashlyticsReport.Session.Event.RolloutAssignment> getReportRolloutsState() {
        List<RolloutAssignment> rolloutAssignmentList = getRolloutAssignmentList();
        ArrayList arrayList = new ArrayList();
        for (int i10 = 0; i10 < rolloutAssignmentList.size(); i10++) {
            arrayList.add(rolloutAssignmentList.get(i10).toReportProto());
        }
        return arrayList;
    }

    public synchronized List<RolloutAssignment> getRolloutAssignmentList() {
        return Collections.unmodifiableList(new ArrayList(this.rolloutsState));
    }

    public synchronized boolean updateRolloutAssignmentList(List<RolloutAssignment> list) {
        this.rolloutsState.clear();
        if (list.size() <= this.maxEntries) {
            return this.rolloutsState.addAll(list);
        }
        Logger.getLogger().w("Ignored 0 entries when adding rollout assignments. Maximum allowable: " + this.maxEntries);
        return this.rolloutsState.addAll(list.subList(0, this.maxEntries));
    }
}
