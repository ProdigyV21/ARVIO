package com.google.firebase.crashlytics.internal.metadata;

import com.google.firebase.encoders.c;
import com.google.firebase.encoders.d;
import com.google.firebase.encoders.e;
import h5.b;
import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public final class AutoRolloutAssignmentEncoder implements h5.a {
    public static final int CODEGEN_VERSION = 2;
    public static final h5.a CONFIG = new AutoRolloutAssignmentEncoder();

    public static final class RolloutAssignmentEncoder implements d<RolloutAssignment> {
        static final RolloutAssignmentEncoder INSTANCE = new RolloutAssignmentEncoder();
        private static final c ROLLOUTID_DESCRIPTOR = c.a("rolloutId");
        private static final c PARAMETERKEY_DESCRIPTOR = c.a("parameterKey");
        private static final c PARAMETERVALUE_DESCRIPTOR = c.a("parameterValue");
        private static final c VARIANTID_DESCRIPTOR = c.a("variantId");
        private static final c TEMPLATEVERSION_DESCRIPTOR = c.a("templateVersion");

        private RolloutAssignmentEncoder() {
        }

        @Override // com.google.firebase.encoders.d
        public void encode(RolloutAssignment rolloutAssignment, e eVar) throws IOException {
            eVar.f(ROLLOUTID_DESCRIPTOR, rolloutAssignment.getRolloutId());
            eVar.f(PARAMETERKEY_DESCRIPTOR, rolloutAssignment.getParameterKey());
            eVar.f(PARAMETERVALUE_DESCRIPTOR, rolloutAssignment.getParameterValue());
            eVar.f(VARIANTID_DESCRIPTOR, rolloutAssignment.getVariantId());
            eVar.b(TEMPLATEVERSION_DESCRIPTOR, rolloutAssignment.getTemplateVersion());
        }
    }

    private AutoRolloutAssignmentEncoder() {
    }

    @Override // h5.a
    public void configure(b<?> bVar) {
        RolloutAssignmentEncoder rolloutAssignmentEncoder = RolloutAssignmentEncoder.INSTANCE;
        bVar.a(RolloutAssignment.class, rolloutAssignmentEncoder);
        bVar.a(AutoValue_RolloutAssignment.class, rolloutAssignmentEncoder);
    }
}
