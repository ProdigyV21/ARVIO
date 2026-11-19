const { json } = require("./_backend");
const { runDueTrialEmails } = require("./_trial-emails");
const { runDueMemberEmails } = require("./_member-emails");
const { cleanupExitSurvey } = require("./premium-exit-survey");
const { cleanupPremiumFunnel } = require("./_premium-funnel");

exports.handler = async (event) => {
  try {
    const emailResult = await runDueTrialEmails(event);
    // Member emails (first-month tips, yearly offer, pass reminders, referral
    // rewards) share this schedule; a failure there must not block the rest.
    const memberEmails = await runDueMemberEmails(event).catch((error) => {
      console.error("member email run failed", error && error.message);
      return { error: "member_emails_failed" };
    });
    const funnelEventsDeleted = await cleanupPremiumFunnel(event, 90);
    await cleanupExitSurvey(event).catch((error) => console.error("exit survey cleanup failed", error && error.message));
    return json(200, { ok: true, ...emailResult, member_emails: memberEmails, funnel_events_deleted: funnelEventsDeleted });
  } catch (error) {
    console.error("trial-email-worker failed", error);
    return json(500, { error: "trial_email_worker_failed" });
  }
};

exports.config = { schedule: "*/30 * * * *" };
