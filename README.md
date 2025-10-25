# Concurrent Multithreaded Programming – Java Snippets

This repository contains the **Java snippet codes** used as examples in the slides of the  
[*Concurrent Multithreaded Programming*](https://github.com/ProgrammationMultiThread/) course  
at **Nantes Université**.

Each snippet illustrates a specific concept from the lectures —  
for instance: atomic operations, synchronization primitives, non-blocking algorithms, or memory models.

---

## 📦 Repository structure

```
src/
 └── main/
      └── java/
           └── snippets/
                ├── locks/
                │    ├── BakeryLock.java
                │    └── TicketLock.java
                ├── atomics/
                │    ├── GetAndIncrement.java
                │    └── CompareAndSwap.java
                ├── threads/
                │    ├── ThreadCreation.java
                │    └── JoinExample.java
                └── ...
```

Each file is an **independent Java program** containing a `main()` method  
and can be executed directly from an IDE or the command line.

---

## 🚀 How to open and run the project

You can import this repository as a **Maven project** in any IDE:
- **Eclipse** → *File → Import → Existing Maven Project*  
- **IntelliJ IDEA** → *Open → pom.xml*  
- **VS Code** → install the *Extension Pack for Java* and open the folder.

To run a snippet manually:
```bash
mvn compile
mvn exec:java -Dexec.mainClass="snippets.locks.BakeryLock"
```

Each snippet is self-contained, so you can compile and run it individually.

---

## 🧭 Notes for students

- These examples are intended for **educational use** only.  
- You are encouraged to read, modify, and experiment with them to understand  
  the behavior of concurrent programs (ordering, atomicity, visibility, etc.).  
- Most snippets are **minimal by design** — error handling and production-level details are omitted intentionally.

---

## 🧩 Related repositories

| Repository | Description |
|-------------|-------------|
| [CM/](https://github.com/ProgrammationMultiThread/CM) | Lecture slides |
| [TD/](https://github.com/ProgrammationMultiThread/TD) | Exercises |
| [TP-webgrep](https://github.com/ProgrammationMultiThread/TP-webgrep) | Practical assignment: parallel web crawler |
| [TP-mandelbrot](https://github.com/ProgrammationMultiThread/TP-mandelbrot) | Practical assignment: parallel image computation |

---

## ⚖️ License

**Code:** [MIT License](./LICENSE)  
© 2025 Matthieu Perrin, Nantes Université

You are free to use, modify, and redistribute this code for any purpose,  
provided that the above notice is included in all copies.

---
