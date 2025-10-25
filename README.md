# Concurrent Multithreaded Programming – Java Snippets

This repository contains the **Java code snippets** used as examples in the slides of the
*[Programmation Concurrente en Multi-Threads](https://github.com/ProgrammationMultiThread/)* course
at **Nantes Université**.

---

## Notes for students

Each file in `src/main/java/` is an **independent Java program** illustrating a specific concept from the lectures.  
You can execute each snippet directly and refer to the course slides for detailed explanations.

- These examples are intended for **educational use** only.  
- You are encouraged to read, modify, and experiment with them to better understand  
  the behavior of concurrent programs (ordering, atomicity, visibility, etc.).  
- Most snippets are **minimal by design** — error handling and production-grade details are intentionally omitted.

---

## How to open and run the project

First, clone the project locally:

```bash
git clone git@github.com:ProgrammationMultiThread/Java-snippets.git
```

You can then import this repository as a **Maven project** in any IDE:
- **Eclipse** → *File → Import → Existing Maven Project*  
- **IntelliJ IDEA** → *Open → pom.xml*  
- **VS Code** → install the *Extension Pack for Java* and open the folder.

To run a snippet manually:
```bash
mvn compile
mvn exec:java -Dexec.mainClass="snippets.introduction.HelloWorld"
```

Each snippet is self-contained and can be compiled or executed independently.

---

## License

All **Java source code and related project files** in this repository  
are distributed under the **MIT License**.

- The full legal text of this license is available in [`LICENSE.txt`](LICENSE.txt).  
- Detailed attributions and cross-repository licensing notes  
  are provided in the [organization-wide license file](https://github.com/ProgrammationMultiThread/.github/blob/main/LICENSE.md).

This license applies only to the **original Java code** provided as part of the  
*Concurrent Multithreaded Programming* course at Nantes Université.  
External libraries and dependencies (e.g. JSoup, JUnit) are distributed  
under their own respective licenses.

### Suggested attribution

> *"Source code from the course **Programmation Concurrente en Multi-Threads** —  
> © 2025 Matthieu Perrin, licensed under the MIT License."*
