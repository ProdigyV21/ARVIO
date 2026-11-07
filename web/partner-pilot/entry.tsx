import { createRoot } from "react-dom/client";
import { PartnerDashboard } from "../components/partner/PartnerDashboard";
import ManagedAccount from "../app/partner/account/page";
import JoinPartner from "../app/partner/join/page";
import "./reset.css";

const path = location.pathname.replace(/\/+$/, "");
createRoot(document.getElementById("root")!).render(
  path === "/partner/account" ? <ManagedAccount /> : path === "/partner/join" ? <JoinPartner /> : <PartnerDashboard />
);
