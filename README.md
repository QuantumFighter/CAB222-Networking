# CAB222-Networking

CAB222 Network Academy is a Swing desktop app for studying Week 1 networking
concepts through three lessons and six questions with explanatory feedback.

## Week 1 lessons

- **Network scope and devices** introduces LAN, MAN and WAN scope and explains
  the roles of switches, routers and wireless access points.
- **Understanding the OSI model** covers the seven layers and how layering
  helps explain communication and diagnose faults.
- **Understanding encapsulation** follows application data through a TCP
  segment, an IPv4 packet and an Ethernet frame, then describes decapsulation.

Choose a lesson and submit an answer in the **Challenge** panel. **Next**
unlocks after a correct answer. The unit is complete at six correct answers.
Progress lasts for the current session. **New session** clears answers,
feedback and progress, and closing and reopening the app starts a fresh session.

## Build and run in IntelliJ IDEA

1. In IntelliJ, choose **File > Open**, select the repository's
   [`pom.xml`](pom.xml), and choose **Open as Project** to import Maven.
2. In **File > Project Structure > Project**, set the SDK to **JDK 26**.
   In **Settings**, open **Maven > Runner** under **Build Tools** and set
   **JRE** to **Project JDK**.
3. In the **Maven** tool window, expand **Lifecycle** and run **clean**,
   then **verify**. A successful build ends with `BUILD SUCCESS`.
4. Open [`App.java`](src/main/java/io/github/quantumfighter/cab222/App.java)
   and use the green gutter Run icon beside `main()`. The
   **CAB222 Network Academy** window opens on the first lesson at 0/6.

From a terminal with Maven and JDK 26 configured, run `mvn clean verify`
from the repository root to run the same build and tests.

See [JetBrains' Maven setup guide](https://www.jetbrains.com/help/idea/maven-support.html)
for help importing the project or selecting the JDK.
