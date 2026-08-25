package com.google.firebase.crashlytics.internal.metadata;

import a0.c;

/* JADX INFO: loaded from: classes4.dex */
final class AutoValue_RolloutAssignment extends RolloutAssignment {
    private final String parameterKey;
    private final String parameterValue;
    private final String rolloutId;
    private final long templateVersion;
    private final String variantId;

    public AutoValue_RolloutAssignment(String str, String str2, String str3, String str4, long j10) {
        if (str == null) {
            throw new NullPointerException("Null rolloutId");
        }
        this.rolloutId = str;
        if (str2 == null) {
            throw new NullPointerException("Null parameterKey");
        }
        this.parameterKey = str2;
        if (str3 == null) {
            throw new NullPointerException("Null parameterValue");
        }
        this.parameterValue = str3;
        if (str4 == null) {
            throw new NullPointerException("Null variantId");
        }
        this.variantId = str4;
        this.templateVersion = j10;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof RolloutAssignment) {
            RolloutAssignment rolloutAssignment = (RolloutAssignment) obj;
            if (this.rolloutId.equals(rolloutAssignment.getRolloutId()) && this.parameterKey.equals(rolloutAssignment.getParameterKey()) && this.parameterValue.equals(rolloutAssignment.getParameterValue()) && this.variantId.equals(rolloutAssignment.getVariantId()) && this.templateVersion == rolloutAssignment.getTemplateVersion()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.metadata.RolloutAssignment
    public String getParameterKey() {
        return this.parameterKey;
    }

    @Override // com.google.firebase.crashlytics.internal.metadata.RolloutAssignment
    public String getParameterValue() {
        return this.parameterValue;
    }

    @Override // com.google.firebase.crashlytics.internal.metadata.RolloutAssignment
    public String getRolloutId() {
        return this.rolloutId;
    }

    @Override // com.google.firebase.crashlytics.internal.metadata.RolloutAssignment
    public long getTemplateVersion() {
        return this.templateVersion;
    }

    @Override // com.google.firebase.crashlytics.internal.metadata.RolloutAssignment
    public String getVariantId() {
        return this.variantId;
    }

    public int hashCode() {
        int iHashCode = (((((((this.rolloutId.hashCode() ^ 1000003) * 1000003) ^ this.parameterKey.hashCode()) * 1000003) ^ this.parameterValue.hashCode()) * 1000003) ^ this.variantId.hashCode()) * 1000003;
        long j10 = this.templateVersion;
        return iHashCode ^ ((int) (j10 ^ (j10 >>> 32)));
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("RolloutAssignment{rolloutId=");
        sb2.append(this.rolloutId);
        sb2.append(", parameterKey=");
        sb2.append(this.parameterKey);
        sb2.append(", parameterValue=");
        sb2.append(this.parameterValue);
        sb2.append(", variantId=");
        sb2.append(this.variantId);
        sb2.append(", templateVersion=");
        return c.j(this.templateVersion, "}", sb2);
    }
}
