package io.sentry.android.core;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import io.sentry.ILogger;
import io.sentry.u2;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes5.dex */
public final class o1 implements SensorEventListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public SensorManager f16677a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Sensor f16678b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public HandlerThread f16679c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Handler f16680d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile a3.b f16682f;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final AtomicLong f16681e = new AtomicLong(0);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f16684h = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f16685i = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ILogger f16683g = u2.f17942i;

    public final void a(Context context) {
        if (this.f16677a == null) {
            this.f16677a = (SensorManager) context.getSystemService("sensor");
        }
        SensorManager sensorManager = this.f16677a;
        if (sensorManager != null && this.f16678b == null) {
            this.f16678b = sensorManager.getDefaultSensor(1, false);
        }
        if (this.f16678b == null || this.f16679c != null) {
            return;
        }
        HandlerThread handlerThread = new HandlerThread("sentry-shake");
        this.f16679c = handlerThread;
        handlerThread.start();
        this.f16680d = new Handler(this.f16679c.getLooper());
    }

    @Override // android.hardware.SensorEventListener
    public final void onAccuracyChanged(Sensor sensor, int i10) {
    }

    @Override // android.hardware.SensorEventListener
    public final void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() != 1) {
            return;
        }
        float[] fArr = sensorEvent.values;
        float f10 = fArr[0] / 9.80665f;
        float f11 = fArr[1] / 9.80665f;
        float f12 = fArr[2] / 9.80665f;
        if ((f12 * f12) + (f11 * f11) + (f10 * f10) > 7.2900004386901855d) {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            if (jElapsedRealtime - this.f16685i > 1500) {
                this.f16684h = 0;
                this.f16685i = jElapsedRealtime;
            }
            int i10 = this.f16684h + 1;
            this.f16684h = i10;
            if (i10 < 2 || jElapsedRealtime - this.f16681e.get() <= 1000) {
                return;
            }
            this.f16681e.set(jElapsedRealtime);
            this.f16684h = 0;
            a3.b bVar = this.f16682f;
            if (bVar != null) {
                FeedbackShakeIntegration feedbackShakeIntegration = (FeedbackShakeIntegration) bVar.f112l;
                WeakReference weakReference = feedbackShakeIntegration.f16343n;
                Activity activity = weakReference != null ? (Activity) weakReference.get() : null;
                Boolean bool = h0.f16496o.f16500n;
                if (activity == null || feedbackShakeIntegration.f16342m == null || feedbackShakeIntegration.f16344o || Boolean.TRUE.equals(bool)) {
                    return;
                }
                activity.runOnUiThread(new androidx.activity.s(feedbackShakeIntegration, activity, 26));
            }
        }
    }
}
