package io.sentry.android.core;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.arvio.tv.R;
import io.sentry.r5;
import io.sentry.s4;
import io.sentry.u5;
import io.sentry.w5;
import io.sentry.y6;

/* JADX INFO: loaded from: classes5.dex */
public final class s1 extends AlertDialog {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f16742i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public io.sentry.protocol.v f16743l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public DialogInterface.OnDismissListener f16744m;

    public s1(Context context) {
        super(context, 0);
        this.f16742i = false;
        u5.d().a("UserFeedbackWidget");
    }

    @Override // android.app.AlertDialog, android.app.Dialog
    public final void onCreate(Bundle bundle) {
        io.sentry.protocol.i0 i0VarA;
        super.onCreate(bundle);
        setContentView(R.layout.sentry_dialog_user_feedback);
        setCancelable(this.f16742i);
        r5 feedbackOptions = s4.b().getOptions().getFeedbackOptions();
        final r5 r5Var = new r5();
        r5Var.f17777a = false;
        r5Var.f17778b = true;
        r5Var.f17779c = false;
        r5Var.f17780d = true;
        r5Var.f17781e = true;
        r5Var.f17782f = true;
        r5Var.f17783g = false;
        r5Var.f17777a = feedbackOptions.f17777a;
        r5Var.f17778b = feedbackOptions.f17778b;
        r5Var.f17779c = feedbackOptions.f17779c;
        r5Var.f17780d = feedbackOptions.f17780d;
        r5Var.f17781e = feedbackOptions.f17781e;
        r5Var.f17782f = feedbackOptions.f17782f;
        r5Var.f17783g = feedbackOptions.f17783g;
        r5Var.f17784h = feedbackOptions.f17784h;
        r5Var.f17785i = feedbackOptions.f17785i;
        TextView textView = (TextView) findViewById(R.id.sentry_dialog_user_feedback_title);
        ImageView imageView = (ImageView) findViewById(R.id.sentry_dialog_user_feedback_logo);
        final TextView textView2 = (TextView) findViewById(R.id.sentry_dialog_user_feedback_txt_name);
        final EditText editText = (EditText) findViewById(R.id.sentry_dialog_user_feedback_edt_name);
        final TextView textView3 = (TextView) findViewById(R.id.sentry_dialog_user_feedback_txt_email);
        final EditText editText2 = (EditText) findViewById(R.id.sentry_dialog_user_feedback_edt_email);
        final TextView textView4 = (TextView) findViewById(R.id.sentry_dialog_user_feedback_txt_description);
        final EditText editText3 = (EditText) findViewById(R.id.sentry_dialog_user_feedback_edt_description);
        Button button = (Button) findViewById(R.id.sentry_dialog_user_feedback_btn_send);
        Button button2 = (Button) findViewById(R.id.sentry_dialog_user_feedback_btn_cancel);
        if (r5Var.f17782f) {
            imageView.setVisibility(0);
        } else {
            imageView.setVisibility(8);
        }
        if (r5Var.f17778b || r5Var.f17777a) {
            textView2.setVisibility(0);
            editText.setVisibility(0);
            textView2.setText("Name");
            editText.setHint("Your Name");
            if (r5Var.f17777a) {
                textView2.append(" (Required)");
            }
        } else {
            textView2.setVisibility(8);
            editText.setVisibility(8);
        }
        if (r5Var.f17780d || r5Var.f17779c) {
            textView3.setVisibility(0);
            editText2.setVisibility(0);
            textView3.setText("Email");
            editText2.setHint("your.email@example.org");
            if (r5Var.f17779c) {
                textView3.append(" (Required)");
            }
        } else {
            textView3.setVisibility(8);
            editText2.setVisibility(8);
        }
        if (r5Var.f17781e && (i0VarA = s4.b().w().A()) != null) {
            editText.setText(i0VarA.f17599m);
            editText2.setText(i0VarA.f17597i);
        }
        textView4.setText("Description");
        textView4.append(" (Required)");
        editText3.setHint("What's the bug? What did you expect?");
        textView.setText("Report a Bug");
        button.setText("Send Bug Report");
        button.setOnClickListener(new View.OnClickListener() { // from class: io.sentry.android.core.p1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditText editText4 = editText;
                String strTrim = editText4.getText().toString().trim();
                EditText editText5 = editText2;
                String strTrim2 = editText5.getText().toString().trim();
                EditText editText6 = editText3;
                String strTrim3 = editText6.getText().toString().trim();
                boolean zIsEmpty = strTrim.isEmpty();
                r5 r5Var2 = r5Var;
                if (zIsEmpty && r5Var2.f17777a) {
                    editText4.setError(textView2.getText());
                    return;
                }
                if (strTrim2.isEmpty() && r5Var2.f17779c) {
                    editText5.setError(textView3.getText());
                    return;
                }
                if (strTrim3.isEmpty()) {
                    editText6.setError(textView4.getText());
                    return;
                }
                io.sentry.protocol.i iVar = new io.sentry.protocol.i(strTrim3);
                iVar.f17592m = strTrim;
                iVar.f17591l = strTrim2;
                s1 s1Var = this.f16688i;
                io.sentry.protocol.v vVar = s1Var.f16743l;
                if (vVar != null) {
                    iVar.f17594o = vVar;
                }
                if (!s4.b().v(iVar).equals(io.sentry.protocol.v.f17707l)) {
                    Toast.makeText(s1Var.getContext(), "Thank you for your report!", 0).show();
                }
                s1Var.cancel();
            }
        });
        button2.setText("Cancel");
        button2.setOnClickListener(new androidx.mediarouter.app.c(this, 1));
        setOnDismissListener(this.f16744m);
    }

    @Override // android.app.Dialog
    public final void onStart() {
        super.onStart();
        y6 options = s4.b().getOptions();
        options.getFeedbackOptions().getClass();
        options.getReplayController().x(Boolean.FALSE);
        this.f16743l = options.getReplayController().o();
    }

    @Override // android.app.Dialog
    public final void setCancelable(boolean z) {
        super.setCancelable(z);
        this.f16742i = z;
    }

    @Override // android.app.Dialog
    public final void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.f16744m = onDismissListener;
        final Runnable runnable = s4.b().getOptions().getFeedbackOptions().f17784h;
        if (runnable != null) {
            super.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: io.sentry.android.core.q1
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    s1 s1Var = this.f16732i;
                    s1Var.getClass();
                    runnable.run();
                    s1Var.f16743l = null;
                    DialogInterface.OnDismissListener onDismissListener2 = s1Var.f16744m;
                    if (onDismissListener2 != null) {
                        onDismissListener2.onDismiss(dialogInterface);
                    }
                }
            });
        } else {
            super.setOnDismissListener(this.f16744m);
        }
    }

    @Override // android.app.Dialog
    public final void show() {
        io.sentry.c1 c1VarB = s4.b();
        y6 options = c1VarB.getOptions();
        if (c1VarB.isEnabled() && options.isEnabled()) {
            super.show();
        } else {
            options.getLogger().q(w5.WARNING, "Sentry is disabled. Feedback dialog won't be shown.", new Object[0]);
        }
    }
}
