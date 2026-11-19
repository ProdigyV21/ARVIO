"use client";
import { ExternalLink } from "lucide-react";
import { useTranslation } from "@/lib/i18n";
import { Group, Row, SectionHeader } from "../ui";

export function AboutSection() {
  const translateUi = useTranslation();
  return (
    <>
      <SectionHeader title="About ARVIO" description="ARVIO is a media hub for sources you configure. Catalog entries do not grant viewing rights. Connect only services and media you are authorized to use." />
      <Group title="Credits">
        <Row label="TMDB" hint="This product uses the TMDB API but is not endorsed or certified by TMDB.">
          <a href="https://www.themoviedb.org" target="_blank" rel="noopener noreferrer" aria-label="TMDB">
            <img src="/tmdb-logo.svg" alt="TMDB" width={100} height={16} />
          </a>
        </Row>
        <Row label="TheSportsDB" hint={<>{translateUi("Sports data and artwork provided by ")}<a className="st-link" href="https://www.thesportsdb.com" target="_blank" rel="noopener noreferrer">{translateUi("TheSportsDB")}</a>.</>} />
      </Group>
      <Group>
        <div className="st-group-foot" style={{ borderTop: 0 }}>
          <a className="st-btn" href="https://arvio.tv/credits/" target="_blank" rel="noopener noreferrer"><ExternalLink size={16} /> {translateUi("Credits & copyright reports")}</a>
          {process.env.NEXT_PUBLIC_UNRAID_DISTRIBUTION === "true" && (
            <a className="st-btn" href="/distribution-sources/index.html" target="_blank" rel="noopener noreferrer"><ExternalLink size={16} /> {translateUi("Source code & licences")}</a>
          )}
        </div>
      </Group>
    </>
  );
}
