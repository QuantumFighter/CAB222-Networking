# Sprint 1 Week 1 network foundations

Build a Java desktop study app in which a learner reads three short Week 1 lessons, answers six challenges, receives explanations and completes the unit. Deliver a usable learning loop and practise small issues, branches, pull requests and a sprint review.

Repository: https://github.com/QuantumFighter/CAB222-Networking

## Goal and capacity

Suggested duration: one week with 10 to 12 focused hours, including study and review. Estimates below are planning assumptions, not deadlines. Set a start and end date when you begin. If your available time is lower, extend the duration before committing to the backlog.

The first demonstration should be possible after S1-04 using the first lesson. The sprint finishes when all three lessons and six questions work together. This covers a selected part of Week 1, rather than claiming complete coverage of the lecture and tutorial.

## Product choices

- Java desktop application, as selected for Sprint 1.
- Swing for the lesson and challenge interface, Maven for the project structure, and JUnit for the grading and progress rules.
- The existing IntelliJ configuration names OpenJDK 26. Verify that the SDK is installed and that IntelliJ and Maven use it. Keep ordinary Java syntax and record the actual chosen JDK in the README.
- A window with a lesson list on the left, readable lesson text in the middle, and a question with answer choices on the right. Show Submit, feedback, Next and an overall completion count.
- Use three small UTF-8 lesson resource files and Java objects for question data in this sprint. JSON and a parser can follow if editing content becomes cumbersome.
- Progress belongs to the current application session. Restarting the application starts a fresh session. No account or database is required.

Swing provides Java desktop UI components. Construct and update the interface on its event dispatch thread. See the [Swing documentation](https://docs.oracle.com/en/java/javase/25/docs/api/java.desktop/javax/swing/package-summary.html). Follow the [Maven directory layout](https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html).

## Sprint backlog

These are draft identifiers, not published GitHub issue numbers. Use the individual files in `issues/` as the issue bodies.

| Order | Draft | Deliverable | Suggested effort | Depends on |
| --- | --- | --- | --- | --- |
| 1 | S1-01 | Runnable IntelliJ and Maven desktop project | 1.5 hours | None |
| 2 | S1-02 | Three source-checked lessons and six questions | 2 hours | None |
| 3 | S1-03 | Read a lesson in the desktop interface | 1.5 hours | S1-01, S1-02 |
| 4 | S1-04 | Submit an answer, see feedback and retry | 2 hours | S1-03 |
| 5 | S1-05 | Navigate questions and complete the unit | 1.5 hours | S1-04 |
| 6 | S1-06 | Demonstrate the app and review learning | 1 hour | S1-05 |

Total estimated task effort: 9.5 hours. Reserve the remaining time for setup problems, study and corrections. Finish S1-01 through S1-06 before taking on optional features.

## Learning content

Use the source map and question seeds in `WEEK-1-CONTENT.md`. Study the relevant lecture sections before writing the lessons. Keep explanations in your own words and distinguish an illustrative TCP example from an ICMP example.

The three lessons are network scope and devices, the OSI model, and encapsulation. Each has two single-answer multiple-choice questions with explanations for every option.

## Starting in IntelliJ

1. Open `C:\Users\reega\IdeaProjects\CAB222-LearningNetworking` and confirm your Git branch is `main`.
2. Record your sprint goal and available time. Create the GitHub issues from `issues/` and put them on the board described below.
3. Create a branch for S1-01, for example `chore/s1-01-project-setup`.
4. Add a `pom.xml` to the existing project and load it as a Maven project. Avoid creating a second repository inside this folder.
5. Add `src/main/java`, `src/main/resources/week1`, and `src/test/java`. Create a small application entry point that opens a titled Swing window.
6. Build and run that window before adding lesson content. Follow the acceptance criteria in S1-01, then open your first pull request.

Suggested classes are `App`, `MainWindow`, `Lesson`, `Question`, `AnswerChecker`, and `StudySession`. Add classes as the issues need them. Keep grading and session progress independent of Swing so they can be tested directly.

## GitHub workflow

Create a milestone named `Sprint 1 - Week 1 foundations`. Create a Projects board with Backlog, Ready, In progress, Review and Done. Assign yourself and add the six issues to the milestone and board. Move one issue at a time into In progress.

For each issue, use a short branch, focused commits and a pull request. In the PR description state the result, how you checked it, and `Closes #<actual issue number>`. Review your diff and acceptance criteria before merging. Record defects found during testing as issues rather than silently widening the sprint.

Each issue file starts with a suggested GitHub title. Paste that title into the title field and the rest into the body. Replace draft dependency names with actual issue links after publication. See [Creating an issue](https://docs.github.com/en/issues/tracking-your-work-with-issues/using-issues/creating-an-issue).

## Completion rules

A question is complete after its first correct answer in the current session. An incorrect answer shows an explanation and allows retry. Correcting an answer increases the completed count once. Repeated clicks and revisiting questions cannot award extra completion. A completed question stays completed and shows its correct answer and explanation when revisited. The unit completes at 6 of 6. Starting a new session clears progress.

## Definition of done

- The issue's acceptance criteria are met and the result is merged through a reviewed pull request.
- The application builds and launches using the documented IntelliJ steps.
- Grading and progress tests pass; the interface is checked manually at a normal desktop window size.
- Each lesson and question has a source reference, and the learner can explain the underlying concept without reading the answer.
- README instructions match the actual project and known defects are recorded.

## Sprint review and retrospective

Demonstrate opening the app, reading a lesson, submitting an incorrect answer, correcting it, finishing the unit and restarting. Then explain a switch versus a router, name all seven OSI layers, and describe encapsulation without notes. Use a fresh scenario to check understanding rather than repeating the six questions from memory.

Record actual versus estimated effort, one thing that helped, one obstacle, and one specific improvement for the next sprint. A working feature demonstrates product progress; explaining a fresh example demonstrates learning progress.

## Remaining Week 1 backlog

Keep these outside the first sprint: Internet history; Internet, intranet and extranet exercises; TCP/IP to OSI mapping; deeper communication component exercises; the complete Wireshark tutorial; and a guided ICMP request/reply packet inspection task. Also defer saved progress, XP, achievements, accounts, an AI tutor, a simulated terminal and real packet parsing.

The next useful increment is an exercise based on Tutorial 1 and `ICMP_Basic.pcapng`, after verifying the actual capture fields. In that exercise, ICMP is carried inside IPv4, inside Ethernet. Do not insert a TCP or UDP transport header into the ICMP example.

## Course references

- `C:\QUT\2026, Semester 2\CAB222 Networks\Week 1\Lecture 1 Introduction to Networks_v1.pdf`: lecture pages 10 to 16, 18 to 27, and 29 to 55 support the selected lessons.
- `C:\QUT\2026, Semester 2\CAB222 Networks\Week 1\Tut1 Packet Encapsulation and Protocol Analysis Using Wireshark.pdf`: tutorial activities identify the practical follow-up.
- Text transcripts in the same folder are supporting study material. Check wording against the lecture because automated transcripts contain recognition errors.

Keep original course PDFs and captures in their existing course folder. The application needs your authored lessons and references to their sources.
