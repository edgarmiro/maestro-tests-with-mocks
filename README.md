# Maestro tests with Mocks

### Overview
> An example of how to mock the network layer in Maestro tests.

### Demo

https://github.com/edgarmiro/maestro-tests-with-mocks/assets/2278900/272c97a6-93bf-458a-a93e-950faf8ce3ea

## Used tools

- [Maestro](https://maestro.mobile.dev/) to run the UI tests.
- [Docker](https://www.docker.com/) to manage the mocking service.
- [Mocks Server](https://www.mocks-server.org/) to mock the network responses.

## Get started

1. Start the emulator.
You can run your emulator or create one through Maestro:
```bash
maestro start-device --platform=android
```  

3. Install the `maestro` build variant:
```bash
./gradlew installMaestro
```

3. Start Mocks Server:

```bash
docker compose -f mocks/docker-compose.yml up
```

4. Run Maestro tests:
```bash
maestro test maestro/
```
