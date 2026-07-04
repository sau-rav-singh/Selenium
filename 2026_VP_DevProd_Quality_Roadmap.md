# 2026 Roadmap: AVP → VP Developer Productivity & Quality Engineering
### Investment Banking Track | 18 Months | 15-18 hrs/week

---

## 1. Time Allocation (15-18 hrs/week, 18 months)

| Phase   | Months | Focus                                                                | Weekly Split                                                     |
|---------|--------|----------------------------------------------------------------------|------------------------------------------------------------------|
| Phase 1 | 1-4    | Engineering Foundations (JVM, Concurrency, Patterns)                 | Core topic 10h / DSA maintenance 3h / System design 2h           |
| Phase 2 | 5-9    | Platform Architecture (Frameworks, API/DB, Docker/K8s, CI/CD)        | Core topic 10h / DSA maintenance 2h / System design 3h           |
| Phase 3 | 10-14  | Developer Productivity Engineering (Tooling, Metrics, Observability) | Core topic 10h / System design 3h / Leadership 2h                |
| Phase 4 | 15-18  | AI for QE + VP System Design + Leadership + Domain + Interview Prep  | Core topic 6h / System design 4h / Domain 3h / Interview prep 5h |

Continuous, every week, all 18 months, non-negotiable:
- 3 DSA problems (Medium, pattern-based, not grind-for-grind's-sake)
- 1 system design whiteboard
- 2 engineering blog/paper reads
- 1 commit to a portfolio project

---

## 2. PHASE 1 (Months 1-4): Stop Being a Test Engineer, Start Being a Platform Engineer

### Month 1: JVM Internals + GC
**Topics:** JVM vs JDK vs JRE, class loading, heap/stack/metaspace, Serial/Parallel/G1/ZGC GC tuning basics.

**Resources:**
- *Effective Java, 3rd Edition* — Joshua Bloch (the actual canonical Java engineering book, more relevant than a generic OOP refresher)
- Oracle's official JVM internals documentation (free, and the interviewer's own source of truth)

**Deliverable:** Whiteboard explanation of heap vs stack, GC pause-time tradeoffs, and why ZGC matters for low-latency trading test infra. Write this up as your first internal architecture doc.

### Month 2: Concurrency
**Topics:** Threads, synchronized/volatile, happens-before, ExecutorService, ForkJoinPool, CompletableFuture, ThreadLocal internals (you already use this in Selenium — now explain *why* it works).

**Resources:**
- *Java Concurrency in Practice* — Brian Goetz (still the definitive reference, no replacement exists)

**Deliverable:** Build a thread-safe parallel test executor and a reporting framework that doesn't race-condition on shared state. Push to GitHub with a README explaining the concurrency model.

### Month 3: Design Patterns + SOLID Applied to Real Frameworks
**Resources:**
- *Head First Design Patterns, 2nd Edition* — Freeman & Robson
- *Design Patterns for High-Quality Automated Tests (Java Edition)* — Anton Angelov

**Deliverable:** Refactor your current Citi framework (or a clean-room version of it) applying Strategy, Factory, Builder, Observer. This is your first "before/after" GitHub PR story for interviews.

### Month 4: Consolidation + System Design Warm-up
**Resources:**
- *System Design Interview, Vol 1* — Alex Xu (start here, don't wait for Phase 3)

**Deliverable:** Design "Test Automation Platform for 500 engineers" as a whiteboard exercise. Not perfect — just get the reps in early so it's second nature by Phase 4.

**Milestone at Month 4:** You can explain JVM memory model, GC tradeoffs, and concurrency primitives without notes. You have one refactored framework repo showing patterns, not just Page Object Model.

---

## 3. PHASE 2 (Months 5-9): Build the Platform, Not Just the Framework

### Month 5-6: Enterprise Selenium + Playwright Platforms
Design and build (not just "use") both, with distinct layers: driver, page, service, reporting, config, utility.

**Resources:**
- *Guide to Test Automation Architecture* (2024) — Craig Risi (Apress) — covers AI/ML integration and tool-agnostic design, directly relevant to this JD
- Playwright official docs — fixtures, tracing, projects, parallelization (docs are better than any course here)

**Deliverable:** Two GitHub repos: `enterprise-selenium-platform`, `enterprise-playwright-platform`. Each with architecture diagram, 100+ meaningful commits, zero copy-paste.

### Month 7: API + Database Engineering
**Resources:**
- REST Assured official docs + OAuth/JWT fundamentals (official specs, free)
- SQL: joins, CTEs, window functions — practice on a real dataset, not just theory

**Deliverable:** API test platform with schema validation + a DB validation framework using JDBC connection pooling.

### Month 8: Docker + Kubernetes for Test Infrastructure
**Resources:**
- *Docker Deep Dive* (2025 Edition) — Nigel Poulton
- *Kubernetes: Up and Running*, 3rd Edition — Burns, Beda, Hightower (O'Reilly)

**Deliverable:** Deploy Selenium Grid and Playwright Grid inside Kubernetes. This single deliverable is one of the most commonly asked "walk me through" questions at VP-level DevProd interviews.

### Month 9: CI/CD Pipeline as Code
**Resources:**
- *Pipeline as Code* — Mohamed Labouardy (Manning) — Jenkins, Kubernetes, Terraform integration
- GitLab CI/CD official docs (you already use this at BNY/Citi — now formalize it into a reusable pipeline template)

**Deliverable:** Full CI/CD quality pipeline: build → test → quality gate → deploy, running your Selenium/Playwright platforms inside Docker on a Kubernetes-backed runner.

**Milestone at Month 9:** Two production-grade framework repos, both containerized, both running through a real CI/CD pipeline you built end-to-end, not configured on top of someone else's.

---

## 4. PHASE 3 (Months 10-14): This Is the Actual VP Differentiator

This phase is what separates you from every other "10 years SDET" candidate. Almost nobody at your level has real developer productivity engineering experience. Build it now.

### Month 10: Developer Tooling + CLI
**Approach:** Build an internal CLI (`test create`, `test execute`, `test debug`, `test report`) using a real CLI framework (Picocli for Java, or Node/TypeScript with Commander if you want cross-stack credibility).

**Resource:** Picocli official docs (free) or Commander.js docs, whichever stack you pick.

**Deliverable:** A working developer CLI in your GitHub, with a demo video. This is your single strongest interview artifact for this specific JD.

### Month 11: Test Impact Analysis + Build Optimization
**Topics:** Git diff-based dependency graphs, smart test selection, build caching, incremental/parallel builds.

**Resource:** Read Google's and Meta's public engineering blog posts on test selection at scale (free, and these are literally what VP interviewers reference).

**Deliverable:** A Test Impact Analysis engine — given a git diff, output the minimal test set that needs to run. This directly answers "how did you reduce regression from 8 hours to 90 minutes."

### Month 12: Quality Gates + Engineering Metrics
**Resources:**
- *Accelerate* — Nicole Forsgren, Jez Humble, Gene Kim (the original DORA book, still the required reference in every VP-level conversation)
- Current 2026 context: DORA's four metrics alone are now considered insufficient because AI-generated code has broken some of the old assumptions (AI now writes 30-70% of committed code at some orgs, and lead time/deployment frequency get misleading as a result). Read up on the **SPACE framework** (Microsoft Research/GitHub, 2021) and **DX Core 4** as the 2026-relevant extensions — these are free industry write-ups, no book required.

**Deliverable:** A Quality Gate Platform — coverage thresholds, flaky test detection, mutation testing — plus an Engineering Metrics Dashboard tracking DORA + a rework-rate metric. This is your answer to "how would you measure engineering productivity in the AI era," which is now a standard VP interview question, not a hypothetical.

### Month 13: Observability for Test Systems
**Resources:**
- *Observability Engineering* — Charity Majors, Liz Fong-Jones, George Miranda (O'Reilly) — the standard modern reference, better fit than generic ELK tutorials
- Prometheus, Grafana, OpenTelemetry official docs (free)

**Deliverable:** A Test Observability Platform: correlation IDs, failure clustering, root cause traceability across your Selenium/Playwright/API platforms.

### Month 14: Consolidation + Leadership Reading Starts
**Resource:** *The Manager's Path* — Camille Fournier (start now, finish by Month 16)

**Milestone at Month 14:** You have a Developer CLI, a Test Impact Analysis engine, a Quality Gate Platform, an Engineering Metrics Dashboard, and a Test Observability Platform. This is the JD, built, not memorized.

---

## 5. PHASE 4 (Months 15-18): AI for QE + VP System Design + Leadership + Domain + Interview Sprint

### Month 15: AI for Quality Engineering
**Topics:** LLM fundamentals (tokens, embeddings, context windows), prompt engineering (zero-shot, few-shot, chain-of-thought, ReAct), RAG (vector DB, retrieval, chunking), agents (planner, memory, tools, MCP), AI testing (prompt testing, hallucination testing, RAG evaluation).

**Resources (2026-current, verified relevant):**
- *AI Engineering: Building Applications with Foundation Models* — Chip Huyen (O'Reilly) — the single best-reviewed 2026 reference for this exact topic, covers evaluation, RAG, agent architecture, and production tradeoffs, written by someone who's actually shipped this
- *The LLM Engineering Handbook* — Paul Iusztin & Maxime Labonne — more hands-on, pairs well with Huyen's book for the practical RAG/fine-tuning side
- Anthropic's own MCP documentation (docs.claude.com) — directly relevant since MCP is explicitly named in your JD

**Deliverable:** Build three things: an AI Test Failure Analyzer, an AI RCA Agent, an AI Test Case Generator. Use Claude or GPT API directly — don't just theorize.

### Month 16: VP-Level System Design
**Resources:**
- *System Design Interview, Vol 1 & 2* — Alex Xu (Vol 1 was started in Month 4 — finish Vol 2 now)

**Practice:** Design each of these on a whiteboard, twice each, until you can do it without hesitation:
- Test Automation Platform at 500-engineer scale
- Developer Productivity Platform
- Quality Gate system for CI/CD
- Test Observability system
- AI Testing Platform

### Month 17: Leadership + Capital Markets Refresh
**Resources:**
- Finish *The Manager's Path* — Camille Fournier
- *Staff Engineer* — Will Larson
- Capital markets refresh (you have this domain already — refresh, don't relearn): *The Trade Lifecycle* — Robert P. Baker, and the Mike Simmons Udemy course on Securities Trade Lifecycle if you want a refresher, not a first pass

**Ongoing through Month 17-18:** 2 internal tech talks/month, mentor 2-3 junior engineers, 2 LinkedIn articles/month on test architecture / DevProd / AI in QE.

### Month 18: Interview Sprint
- 3 mock interviews/week (system design + behavioral)
- Daily: 1 Medium/Hard DSA problem, 5 capital markets questions answered out loud
- Finalize all 5 portfolio repos with polished READMEs and 3-minute demo videos
- Rewrite resume bullets around Developer Productivity Platform outcomes, not "built automation framework"

**Milestone at Month 18:** You can walk into a VP DevProd/QE interview at any investment bank and talk through 5 built platforms, JVM internals, DORA/SPACE metrics in the AI era, and MCP-based AI agents, without reading from a script.

---

## 6. Portfolio Projects (Ship All 8, Not Some)

1. Enterprise Selenium Platform (GitHub)
2. Enterprise Playwright Platform (GitHub)
3. API + DB Test Platform
4. Developer CLI
5. Test Impact Analysis Engine
6. Quality Gate Platform + Engineering Metrics Dashboard
7. Test Observability Platform
8. AI Test Failure Analyzer + RCA Agent + Test Case Generator

Every one of these is a resume bullet and a 10-minute interview story. If you skip one, you skip an interview answer.

---

## 7. Interview Questions You Must Answer Without Prep by Month 18

- How did you reduce regression from 8 hours to 90 minutes?
- How would you build a Developer Productivity Platform for 500+ engineers?
- How would you design Quality Gates for CI/CD?
- How would you measure engineering productivity in 2026, given AI writes 30-70% of code and DORA alone is no longer sufficient?
- How would you introduce AI agents into the SDLC, and what would you test?
- How would you migrate Selenium to Playwright at scale?
- How would you design a test observability system?
- How would you reduce flaky tests by 80%?
- How would you lead framework modernization across 500+ engineers?

---

## 8. Verified Resource List (Consolidated)

| Resource                                                         | Type        | Phase |
|------------------------------------------------------------------|-------------|-------|
| Effective Java, 3rd Ed — Joshua Bloch                            | Book        | 1     |
| Java Concurrency in Practice — Brian Goetz                       | Book        | 1     |
| Head First Design Patterns, 2nd Ed — Freeman & Robson            | Book        | 1     |
| Design Patterns for High-Quality Automated Tests — Anton Angelov | Book        | 2     |
| Guide to Test Automation Architecture (2024) — Craig Risi        | Book        | 2     |
| Docker Deep Dive (2025 Ed) — Nigel Poulton                       | Book        | 2     |
| Kubernetes: Up and Running, 3rd Ed — Burns/Beda/Hightower        | Book        | 2     |
| Pipeline as Code — Mohamed Labouardy                             | Book        | 2     |
| Accelerate — Forsgren/Humble/Kim                                 | Book        | 3     |
| Observability Engineering — Majors/Fong-Jones/Miranda            | Book        | 3     |
| AI Engineering — Chip Huyen                                      | Book        | 4     |
| The LLM Engineering Handbook — Iusztin & Labonne                 | Book        | 4     |
| System Design Interview Vol 1 & 2 — Alex Xu                      | Book        | 1 & 4 |
| The Manager's Path — Camille Fournier                            | Book        | 4     |
| Staff Engineer — Will Larson                                     | Book        | 4     |
| The Trade Lifecycle — Robert P. Baker                            | Book        | 4     |
| SPACE Framework write-ups (Microsoft Research/GitHub, 2021)      | Free online | 3     |
| DORA/DX 2026 State of AI-Assisted Development reports            | Free online | 3     |
| Anthropic MCP documentation                                      | Free online | 4     |

**Dropped from your old plan and why:** AWS SAA certification (not in this JD, optional side project at best), JMeter (not mentioned, low ROI for this specific role), Blind 150 full grind (replaced with 3 problems/week maintenance — you need system design and platform depth more than you need Hard-level LeetCode at VP level).

---

## 9. What to Do This Week

1. Order or download *Effective Java* and *Java Concurrency in Practice*
2. Create a new GitHub repo: `dev-productivity-platform` (this becomes home base for CLI, TIA engine, quality gates)
3. Write the JVM whiteboard doc (heap vs stack, GC tradeoffs) — post it as your first internal architecture note at Citi
4. Block 15-18 hrs/week on your calendar now, not "when things calm down"
5. Tell your manager at Citi you're taking on the CLI/tooling deliverable as a real Q3 initiative, not a side project — this gets you internal proof points before you even interview elsewhere
