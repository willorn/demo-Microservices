# Repository Guidelines

## Project Structure & Module Organization
- Maven multi-module project: `hm-common` (shared utilities) and `hm-service` (Spring Boot app).
- Source: `*/src/main/java`; Resources: `*/src/main/resources`; Tests: `*/src/test/java`.
- Docker: service image built from `hm-service/Dockerfile`.

## Build, Test, and Development Commands
- Require JDK 21. Example (PowerShell): set `JAVA_HOME` to JDK 21 and add `bin` to `PATH`.
- Build all modules: `mvn -T 1C clean verify`.
- Build service (and deps): `mvn -pl hm-service -am package`.
- Run service locally: `mvn -pl hm-service spring-boot:run`.
- Run tests: `mvn test` or a class: `mvn -Dtest=ClassNameTest test`.

## Coding Style & Naming Conventions
- Java: 4-space indentation, UTF-8, ~120 char lines.
- Packages lowercase; classes/interfaces `PascalCase`; methods/fields `camelCase`; constants `UPPER_SNAKE_CASE`.
- One public top-level class per file; K&R braces; avoid wildcard imports.
- Use Lombok where present; enable annotation processing in IDE.

## Testing Guidelines
- Framework: JUnit 5 with Spring Boot test starter.
- Tests live in `src/test/java`; name files `*Test.java` (e.g., `UserServiceTest`).
- Prefer isolated unit tests; use `@SpringBootTest` only for integration paths.

## Commit & Pull Request Guidelines
- Conventional Commits (examples):
  - `feat(hm-service): add cart API`
  - `fix(hm-common): null check in IdUtil`
- Keep commits focused; subject ≤ 72 chars; explain the “why” in body.
- PRs: clear summary, linked issues, tests/notes, and any config changes; screenshots for user-visible behavior.

## Security & Configuration Tips
- JDK 21 compatibility: Lombok 1.18.34; `maven-compiler-plugin` uses `<release>21</release>`; surefire/failsafe 3.2.5.
- Keep `maven.compiler.source/target/release` consistent across modules; verify with `mvn -v` and `mvn help:effective-pom`.
- Do not commit secrets; use env vars or `application*.yml` profiles.

## Agent-Specific Instructions
- Scope: applies to the entire repo.
- Prefer minimal, targeted diffs; avoid broad dependency bumps without discussion.
- Update tests and docs alongside code changes.
