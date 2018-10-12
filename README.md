# HermeticTesting

Demo app showing how to leverage Dagger & architecture patterns to hermetically seal your espresso/integration tests. The goal is to provide non-flaky, easily repeatable UI level tests run on an android device. 

Combine this with CI & cloud test lab solutions to have a set of stable UI tests in your dev pipeline.


Important tools involved:
- Dagger2
- Espresso
- MVP/Repository pattern (could easily be mv* or however you want to isolate data sources)
- Mockito
- Custom instrumentation test runners
