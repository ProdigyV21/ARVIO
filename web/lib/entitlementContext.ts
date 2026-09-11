"use client";

import { createContext, useContext } from "react";
import type { EntitlementState } from "./entitlement";

export const EntitlementContext = createContext<EntitlementState | null>(null);
export const useEntitlement = () => useContext(EntitlementContext);
