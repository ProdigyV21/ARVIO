package androidx.compose.animation.core;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\u000b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0002¢\u0006\u0004\b\u000b\u0010\fJ-\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\rH\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0010\u0010\u0011R\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0005R\u0016\u0010\u0018\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001b\u001a\u00020\u001a8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0016\u0010\u001d\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010\u0019R\u0016\u0010\u001e\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001e\u0010\u0019R\u0016\u0010\u001f\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010\u0019R*\u0010!\u001a\u00020\u00022\u0006\u0010 \u001a\u00020\u00028\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b!\u0010\u0013\u001a\u0004\b\"\u0010\u0015\"\u0004\b#\u0010\u0005R$\u0010&\u001a\u00020\u00022\u0006\u0010 \u001a\u00020\u00028F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b$\u0010\u0015\"\u0004\b%\u0010\u0005\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006'"}, d2 = {"Landroidx/compose/animation/core/SpringSimulation;", "", "", "finalPosition", "<init>", "(F)V", "Lx6/t0;", "init", "()V", "lastDisplacement", "lastVelocity", "getAcceleration", "(FF)F", "", "timeElapsed", "Landroidx/compose/animation/core/Motion;", "updateValues-IJZedt4$animation_core_release", "(FFJ)J", "updateValues", "F", "getFinalPosition", "()F", "setFinalPosition", "", "naturalFreq", "D", "", "initialized", "Z", "gammaPlus", "gammaMinus", "dampedFreq", "value", "dampingRatio", "getDampingRatio", "setDampingRatio", "getStiffness", "setStiffness", "stiffness", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SpringSimulation {
    public static final int $stable = 8;
    private double dampedFreq;
    private float finalPosition;
    private double gammaMinus;
    private double gammaPlus;
    private boolean initialized;
    private double naturalFreq = Math.sqrt(50.0d);
    private float dampingRatio = 1.0f;

    public SpringSimulation(float f10) {
        this.finalPosition = f10;
    }

    private final void init() {
        if (this.initialized) {
            return;
        }
        if (this.finalPosition == SpringSimulationKt.getUNSET()) {
            throw new IllegalStateException("Error: Final position of the spring must be set before the animation starts");
        }
        float f10 = this.dampingRatio;
        double d4 = ((double) f10) * ((double) f10);
        if (f10 > 1.0f) {
            double d10 = this.naturalFreq;
            double d11 = d4 - ((double) 1);
            this.gammaPlus = (Math.sqrt(d11) * d10) + (((double) (-f10)) * d10);
            double d12 = -this.dampingRatio;
            double d13 = this.naturalFreq;
            this.gammaMinus = (d12 * d13) - (Math.sqrt(d11) * d13);
        } else if (f10 >= 0.0f && f10 < 1.0f) {
            this.dampedFreq = Math.sqrt(((double) 1) - d4) * this.naturalFreq;
        }
        this.initialized = true;
    }

    public final float getAcceleration(float lastDisplacement, float lastVelocity) {
        float f10 = lastDisplacement - this.finalPosition;
        double d4 = this.naturalFreq;
        return (float) (((-(d4 * d4)) * ((double) f10)) - (((d4 * 2.0d) * ((double) this.dampingRatio)) * ((double) lastVelocity)));
    }

    public final float getDampingRatio() {
        return this.dampingRatio;
    }

    public final float getFinalPosition() {
        return this.finalPosition;
    }

    public final float getStiffness() {
        double d4 = this.naturalFreq;
        return (float) (d4 * d4);
    }

    public final void setDampingRatio(float f10) {
        if (f10 < 0.0f) {
            throw new IllegalArgumentException("Damping ratio must be non-negative");
        }
        this.dampingRatio = f10;
        this.initialized = false;
    }

    public final void setFinalPosition(float f10) {
        this.finalPosition = f10;
    }

    public final void setStiffness(float f10) {
        if (getStiffness() <= 0.0f) {
            throw new IllegalArgumentException("Spring stiffness constant must be positive.");
        }
        this.naturalFreq = Math.sqrt(f10);
        this.initialized = false;
    }

    /* JADX INFO: renamed from: updateValues-IJZedt4$animation_core_release, reason: not valid java name */
    public final long m111updateValuesIJZedt4$animation_core_release(float lastDisplacement, float lastVelocity, long timeElapsed) {
        double dCos;
        double dExp;
        double dExp2;
        double dExp3;
        init();
        float f10 = lastDisplacement - this.finalPosition;
        double d4 = timeElapsed / 1000.0d;
        float f11 = this.dampingRatio;
        if (f11 > 1.0f) {
            double d10 = f10;
            double d11 = this.gammaMinus;
            double d12 = lastVelocity;
            double d13 = this.gammaPlus;
            double d14 = d10 - (((d11 * d10) - d12) / (d11 - d13));
            double d15 = ((d10 * d11) - d12) / (d11 - d13);
            dExp = (Math.exp(this.gammaPlus * d4) * d15) + (Math.exp(d11 * d4) * d14);
            double d16 = this.gammaMinus;
            dExp2 = Math.exp(d16 * d4) * d14 * d16;
            double d17 = this.gammaPlus;
            dExp3 = Math.exp(d17 * d4) * d15 * d17;
        } else {
            if (f11 != 1.0f) {
                double d18 = ((double) 1) / this.dampedFreq;
                double d19 = this.naturalFreq;
                double d20 = f10;
                double d21 = ((((double) f11) * d19 * d20) + ((double) lastVelocity)) * d18;
                double dExp4 = Math.exp(((double) (-f11)) * d19 * d4) * ((Math.sin(this.dampedFreq * d4) * d21) + (Math.cos(this.dampedFreq * d4) * d20));
                double d22 = this.naturalFreq;
                float f12 = this.dampingRatio;
                double d23 = (-d22) * dExp4 * ((double) f12);
                double dExp5 = Math.exp(((double) (-f12)) * d22 * d4);
                double d24 = this.dampedFreq;
                double dSin = Math.sin(d24 * d4) * (-d24) * d20;
                double d25 = this.dampedFreq;
                dCos = (((Math.cos(d25 * d4) * d21 * d25) + dSin) * dExp5) + d23;
                dExp = dExp4;
                return SpringSimulationKt.Motion((float) (dExp + ((double) this.finalPosition)), (float) dCos);
            }
            double d26 = this.naturalFreq;
            double d27 = f10;
            double d28 = (d26 * d27) + ((double) lastVelocity);
            double d29 = (d28 * d4) + d27;
            dExp = Math.exp((-d26) * d4) * d29;
            double dExp6 = Math.exp((-this.naturalFreq) * d4) * d29;
            double d30 = this.naturalFreq;
            dExp2 = dExp6 * (-d30);
            dExp3 = Math.exp((-d30) * d4) * d28;
        }
        dCos = dExp3 + dExp2;
        return SpringSimulationKt.Motion((float) (dExp + ((double) this.finalPosition)), (float) dCos);
    }
}
