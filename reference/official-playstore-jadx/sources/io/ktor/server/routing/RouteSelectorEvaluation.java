package io.ktor.server.routing;

import a0.c;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.Parameters;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u00072\u00020\u0001:\u0003\u0007\b\tB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0002\n\u000b¨\u0006\f"}, d2 = {"Lio/ktor/server/routing/RouteSelectorEvaluation;", "", "succeeded", "", "(Z)V", "getSucceeded", "()Z", "Companion", "Failure", "Success", "Lio/ktor/server/routing/RouteSelectorEvaluation$Failure;", "Lio/ktor/server/routing/RouteSelectorEvaluation$Success;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class RouteSelectorEvaluation {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final RouteSelectorEvaluation Constant;
    private static final RouteSelectorEvaluation ConstantPath;
    private static final Failure Failed;
    private static final Failure FailedMethod;
    private static final Failure FailedParameter;
    private static final Failure FailedPath;
    private static final RouteSelectorEvaluation Missing;
    private static final RouteSelectorEvaluation Transparent;
    private static final RouteSelectorEvaluation WildcardPath;
    public static final double qualityConstant = 1.0d;
    public static final double qualityFailedMethod = 0.02d;
    public static final double qualityFailedParameter = 0.01d;
    public static final double qualityMethodParameter = 0.8d;
    public static final double qualityMissing = 0.2d;
    public static final double qualityParameter = 0.8d;
    public static final double qualityParameterWithPrefixOrSuffix = 0.9d;
    public static final double qualityPathParameter = 0.8d;
    public static final double qualityQueryParameter = 1.0d;
    public static final double qualityTailcard = 0.1d;
    public static final double qualityTransparent = -1.0d;
    public static final double qualityWildcard = 0.5d;
    private final boolean succeeded;

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0006\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J-\u0010'\u001a\u00020\u00042\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u001a2\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020.H\u0087\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u000f\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0011\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\fR\u0011\u0010\u0013\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u0011\u0010\u0015\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0006R\u0011\u0010\u0017\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0006R\u000e\u0010\u0019\u001a\u00020\u001aX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001aX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001aX\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\u00020\u001aX\u0086T¢\u0006\b\n\u0000\u0012\u0004\b\u001e\u0010\u0002R\u000e\u0010\u001f\u001a\u00020\u001aX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001aX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u001aX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u001aX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u001aX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u001aX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u001aX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u001aX\u0086T¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lio/ktor/server/routing/RouteSelectorEvaluation$Companion;", "", "()V", "Constant", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "getConstant", "()Lio/ktor/server/routing/RouteSelectorEvaluation;", "ConstantPath", "getConstantPath", "Failed", "Lio/ktor/server/routing/RouteSelectorEvaluation$Failure;", "getFailed", "()Lio/ktor/server/routing/RouteSelectorEvaluation$Failure;", "FailedMethod", "getFailedMethod", "FailedParameter", "getFailedParameter", "FailedPath", "getFailedPath", "Missing", "getMissing", "Transparent", "getTransparent", "WildcardPath", "getWildcardPath", "qualityConstant", "", "qualityFailedMethod", "qualityFailedParameter", "qualityMethodParameter", "getQualityMethodParameter$annotations", "qualityMissing", "qualityParameter", "qualityParameterWithPrefixOrSuffix", "qualityPathParameter", "qualityQueryParameter", "qualityTailcard", "qualityTransparent", "qualityWildcard", "invoke", "succeeded", "", "quality", "parameters", "Lio/ktor/http/Parameters;", "segmentIncrement", "", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public static /* synthetic */ void getQualityMethodParameter$annotations() {
        }

        public static /* synthetic */ RouteSelectorEvaluation invoke$default(Companion companion, boolean z, double d4, Parameters parameters, int i10, int i11, Object obj) {
            if ((i11 & 4) != 0) {
                parameters = Parameters.INSTANCE.getEmpty();
            }
            Parameters parameters2 = parameters;
            if ((i11 & 8) != 0) {
                i10 = 0;
            }
            return companion.invoke(z, d4, parameters2, i10);
        }

        public final RouteSelectorEvaluation getConstant() {
            return RouteSelectorEvaluation.Constant;
        }

        public final RouteSelectorEvaluation getConstantPath() {
            return RouteSelectorEvaluation.ConstantPath;
        }

        public final Failure getFailed() {
            return RouteSelectorEvaluation.Failed;
        }

        public final Failure getFailedMethod() {
            return RouteSelectorEvaluation.FailedMethod;
        }

        public final Failure getFailedParameter() {
            return RouteSelectorEvaluation.FailedParameter;
        }

        public final Failure getFailedPath() {
            return RouteSelectorEvaluation.FailedPath;
        }

        public final RouteSelectorEvaluation getMissing() {
            return RouteSelectorEvaluation.Missing;
        }

        public final RouteSelectorEvaluation getTransparent() {
            return RouteSelectorEvaluation.Transparent;
        }

        public final RouteSelectorEvaluation getWildcardPath() {
            return RouteSelectorEvaluation.WildcardPath;
        }

        @e
        public final RouteSelectorEvaluation invoke(boolean succeeded, double quality, Parameters parameters, int segmentIncrement) {
            return succeeded ? new Success(quality, parameters, segmentIncrement) : new Failure(quality, HttpStatusCode.INSTANCE.getNotFound());
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lio/ktor/server/routing/RouteSelectorEvaluation$Failure;", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "quality", "", "failureStatusCode", "Lio/ktor/http/HttpStatusCode;", "(DLio/ktor/http/HttpStatusCode;)V", "getFailureStatusCode", "()Lio/ktor/http/HttpStatusCode;", "getQuality", "()D", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final /* data */ class Failure extends RouteSelectorEvaluation {
        private final HttpStatusCode failureStatusCode;
        private final double quality;

        public Failure(double d4, HttpStatusCode httpStatusCode) {
            super(false, null);
            this.quality = d4;
            this.failureStatusCode = httpStatusCode;
        }

        public static /* synthetic */ Failure copy$default(Failure failure, double d4, HttpStatusCode httpStatusCode, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                d4 = failure.quality;
            }
            if ((i10 & 2) != 0) {
                httpStatusCode = failure.failureStatusCode;
            }
            return failure.copy(d4, httpStatusCode);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final double getQuality() {
            return this.quality;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final HttpStatusCode getFailureStatusCode() {
            return this.failureStatusCode;
        }

        public final Failure copy(double quality, HttpStatusCode failureStatusCode) {
            return new Failure(quality, failureStatusCode);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Failure)) {
                return false;
            }
            Failure failure = (Failure) other;
            return Double.compare(this.quality, failure.quality) == 0 && p.a(this.failureStatusCode, failure.failureStatusCode);
        }

        public final HttpStatusCode getFailureStatusCode() {
            return this.failureStatusCode;
        }

        public final double getQuality() {
            return this.quality;
        }

        public int hashCode() {
            long jDoubleToLongBits = Double.doubleToLongBits(this.quality);
            return this.failureStatusCode.hashCode() + (((int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32))) * 31);
        }

        public String toString() {
            return "Failure(quality=" + this.quality + ", failureStatusCode=" + this.failureStatusCode + ')';
        }
    }

    static {
        HttpStatusCode.Companion companion = HttpStatusCode.INSTANCE;
        Failed = new Failure(0.0d, companion.getNotFound());
        FailedPath = new Failure(0.0d, companion.getNotFound());
        FailedMethod = new Failure(0.02d, companion.getMethodNotAllowed());
        FailedParameter = new Failure(0.01d, companion.getBadRequest());
        Missing = new Success(0.2d, null, 0, 6, null);
        Constant = new Success(1.0d, null, 0, 6, null);
        Transparent = new Success(-1.0d, null, 0, 6, null);
        ConstantPath = new Success(1.0d, null, 1, 2, null);
        WildcardPath = new Success(0.5d, null, 1, 2, null);
    }

    public /* synthetic */ RouteSelectorEvaluation(boolean z, h hVar) {
        this(z);
    }

    public final boolean getSucceeded() {
        return this.succeeded;
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lio/ktor/server/routing/RouteSelectorEvaluation$Success;", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "quality", "", "parameters", "Lio/ktor/http/Parameters;", "segmentIncrement", "", "(DLio/ktor/http/Parameters;I)V", "getParameters", "()Lio/ktor/http/Parameters;", "getQuality", "()D", "getSegmentIncrement", "()I", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final /* data */ class Success extends RouteSelectorEvaluation {
        private final Parameters parameters;
        private final double quality;
        private final int segmentIncrement;

        public /* synthetic */ Success(double d4, Parameters parameters, int i10, int i11, h hVar) {
            this(d4, (i11 & 2) != 0 ? Parameters.INSTANCE.getEmpty() : parameters, (i11 & 4) != 0 ? 0 : i10);
        }

        public static /* synthetic */ Success copy$default(Success success, double d4, Parameters parameters, int i10, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                d4 = success.quality;
            }
            if ((i11 & 2) != 0) {
                parameters = success.parameters;
            }
            if ((i11 & 4) != 0) {
                i10 = success.segmentIncrement;
            }
            return success.copy(d4, parameters, i10);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final double getQuality() {
            return this.quality;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Parameters getParameters() {
            return this.parameters;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getSegmentIncrement() {
            return this.segmentIncrement;
        }

        public final Success copy(double quality, Parameters parameters, int segmentIncrement) {
            return new Success(quality, parameters, segmentIncrement);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Success)) {
                return false;
            }
            Success success = (Success) other;
            return Double.compare(this.quality, success.quality) == 0 && p.a(this.parameters, success.parameters) && this.segmentIncrement == success.segmentIncrement;
        }

        public final Parameters getParameters() {
            return this.parameters;
        }

        public final double getQuality() {
            return this.quality;
        }

        public final int getSegmentIncrement() {
            return this.segmentIncrement;
        }

        public int hashCode() {
            long jDoubleToLongBits = Double.doubleToLongBits(this.quality);
            return ((this.parameters.hashCode() + (((int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32))) * 31)) * 31) + this.segmentIncrement;
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("Success(quality=");
            sb2.append(this.quality);
            sb2.append(", parameters=");
            sb2.append(this.parameters);
            sb2.append(", segmentIncrement=");
            return c.o(sb2, this.segmentIncrement, ')');
        }

        public Success(double d4, Parameters parameters, int i10) {
            super(true, null);
            this.quality = d4;
            this.parameters = parameters;
            this.segmentIncrement = i10;
        }
    }

    private RouteSelectorEvaluation(boolean z) {
        this.succeeded = z;
    }
}
