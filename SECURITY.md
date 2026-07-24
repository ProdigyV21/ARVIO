# Security Policy

This document outlines the policy for reporting security vulnerabilities in ARVIO. Maintainers encourage coordinated disclosure to ensure potential security issues can be investigated and remediated before public discussion.

## Supported Versions

Active application development takes place on the `main` branch.

| Version / Branch | Status |
| --- | --- |
| `main` (latest source) | Active development |

Support for tagged releases and previous builds is determined by the maintainers.

## Reporting a Vulnerability

Do **not** report security vulnerabilities through public channels, including:

- Public GitHub issues
- Public Discord channels
- Public forums or social media

To disclose a security vulnerability privately, maintainers are establishing a dedicated private reporting channel. Until a private channel (such as GitHub Private Vulnerability Reporting or a designated security contact) is configured for this repository, please refrain from publicly posting technical details, proof-of-concept exploits, or reproduction steps.

## Information to Include

When submitting a security report, include relevant technical context where possible:

- Affected component or platform (e.g., Android application, Web UI, Netlify auth functions, Supabase Edge Functions, or integrations such as Jellyfin, Emby, Plex, Trakt, or IPTV)
- Version, build number, or git commit hash where the issue was observed
- Technical description of the vulnerability and its potential security impact
- Step-by-step reproduction instructions or proof of concept
- Relevant environment or setup details

Do not include live production credentials, tokens, session keys, or private API keys in a security report.

## Coordinated Disclosure

Maintainers request that security researchers allow reasonable time to investigate, reproduce, and resolve reported findings before technical details are published or disclosed publicly.
