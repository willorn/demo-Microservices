 # JDK 21 Migration Notes

 ## Overview
 This repository now compiles and runs on JDK 21. We kept Spring Boot at 2.7.x (works with JDK 21 when toolchain and plugins are updated) and fixed the javac/Lombok error.

 ## Changes Applied
 - Compiler
   - Root `pom.xml`: `maven-compiler-plugin` → 3.11.0 with `<release>21</release>`; added `maven.compiler.release=21`.
   - Set `maven.compiler.source/target=21` in modules.
 - Test plugins
   - `maven-surefire-plugin` and `maven-failsafe-plugin` → 3.2.5.
 - Lombok
   - Upgraded to `1.18.34` (fixes `NoSuchFieldError: JCTree$JCImport.qualid` on JDK 21).

 Files touched
 - `pom.xml` (root)
 - `hm-common/pom.xml`

 ## Build & Run (JDK 21)
 1. Ensure JDK 21 in PATH
    - Windows (PowerShell):
      - `$env:JAVA_HOME='C:\\Program Files\\Java\\jdk-21'`
      - `$env:Path="${env:JAVA_HOME}\\bin;${env:Path}"`
 2. Clean build
    - `mvn -U clean verify`
 3. Run service
    - `mvn -pl hm-service spring-boot:run`
 4. Package, skipping tests (optional)
    - `mvn -DskipTests package`

 ## IDE Setup
 - IntelliJ: Project SDK = 21; enable annotation processing; update Lombok plugin to latest. Re-import Maven after JDK change.

 ## Troubleshooting
 - Invalid target release / toolchain mismatch: confirm `java -version` is 21 and Maven uses that JDK.
 - Lombok errors: ensure plugin and dependency are updated; invalidate caches and restart IDE.
 - Older Surefire fails to detect tests: use the pinned 3.2.5.

 ## Optional: Upgrade to Spring Boot 3
 For official JDK 21 support and Jakarta EE 9 namespaces:
 - Parent: `org.springframework.boot:spring-boot-starter-parent:3.3.x`.
 - Spring Cloud BOM: `2023.0.x`.
 - Replace `javax.*` imports with `jakarta.*` (web/validation/servlet APIs).
 - Update libraries compatible with Boot 3 (e.g., MyBatis‑Plus, Spring Security RSA or alternatives).
 - Re-run tests and integration checks.
