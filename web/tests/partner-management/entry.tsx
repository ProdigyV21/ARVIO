import { createRoot } from "react-dom/client";
import { PartnerDashboard } from "../../components/partner/PartnerDashboard";
import ManagedAccount from "../../app/partner/account/page";
createRoot(document.getElementById("root")!).render(location.pathname === "/partner/account" ? <ManagedAccount /> : <PartnerDashboard />);
