# Week 1 lesson outline

Author three short lessons for the first sprint. These outlines and question seeds define scope; turn them into explanations in your own words after studying the source pages.

## Lesson 1 network scope and devices

Source: `Lecture 1 Introduction to Networks_v1.pdf`, pages 10 and 18 to 27.

Outcome: distinguish local and wider network scopes and select a basic switch, router or access point for a clearly described situation. Explain the roles of the NIC, transmission medium, client, server, protocol and NIC driver briefly.

Question W1-Q1: Several wired computers and a printer need to communicate within the same small office LAN. Which interconnecting device is the best fit? Correct answer: a switch. Explain the alternatives using the stated scenario rather than claiming a router can never include switching capabilities.

Question W1-Q2: Which device's primary role is to forward traffic between different IP networks? Correct answer: a router. Use a switch, access point and transmission cable as plausible distractors and explain their different roles.

## Lesson 2 the OSI model

Source: lecture pages 29 to 34, 37 to 50, 53 and 55.

Outcome: name the seven layers, explain why layered models are useful and associate a few functions with their layers. Include a compact reference listing layers 7 to 1: Application, Presentation, Session, Transport, Network, Data Link, Physical.

Question W1-Q3: Which OSI layer is associated with logical IP addressing and routing? Correct answer: Network, layer 3.

Question W1-Q4: Which OSI layer represents the transmission of bits as electrical, optical or radio signals? Correct answer: Physical, layer 1.

For both questions, use other OSI layers as distractors and explain their roles. Avoid rules suggesting that every real protocol or modern device fits exclusively into one layer.

## Lesson 3 encapsulation

Source: lecture pages 13 to 16, 35 to 36, 40, 43, 46 and 49.

Outcome: explain headers, trailers and the difference between a segment, packet, frame and bits in an explicitly labelled TCP over IPv4 over Ethernet example. Explain that the receiver removes the encapsulation as it processes the data.

Question W1-Q5: In the stated TCP example, what is the order as application data travels toward transmission? Correct answer: data, TCP segment, IP packet, Ethernet frame, bits. Use alternative orders as distractors.

Question W1-Q6: In an Ethernet frame, which fields identify the source and destination at the data link layer? Correct answer: source and destination MAC addresses. Contrast IP addresses, TCP port numbers and application names in the feedback.

The unit does not teach that all traffic contains a TCP segment. Tutorial 1 uses ICMP within IPv4 within Ethernet, so its nesting will differ from this TCP example.

## Required content fields

Each lesson needs a stable ID, title, learning outcome, text and source reference. Each question needs a stable ID, prompt, four labelled options, exactly one correct option ID and an explanation for each option. Give the correct explanation as feedback too.

Use UTF-8 text resources for the lesson prose and ordinary Java objects for the six questions in Sprint 1. Keep the content outside Swing event handlers so another lesson can be added without changing the interface logic.

## Source review checklist

- Check each answer and each distractor explanation against the cited lecture section.
- Use lesson IDs W1-L1 to W1-L3 and question IDs W1-Q1 to W1-Q6 consistently.
- Prefer concept questions over historical date memorisation in this increment.
- Record ambiguities before implementing grading. The broader Week 1 history section needs separate fact checking because the source contains inconsistent dates.
- Have another learner read a question if practical. If working solo, revisit it after a break and check that exactly one option is defensible in its stated context.
