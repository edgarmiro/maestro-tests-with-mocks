# Maestro tests with Mocks

### Overview
> An example of how to mock the network layer in Maestro tests.

You can find more info about the motivation behind this approach in the post [The Night’s Watch: Safeguarding store operations](https://medium.com/mercadona-tech/the-nights-watch-safeguarding-store-operations-4c3acf727af2)

### Demo



https://github.com/edgarmiro/maestro-tests-with-mocks/assets/2278900/9811d546-867d-4ab3-8488-62871b2905dd



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

## Build variants

There are three different build variants.

- *debug:* To develop the application.
- *release:* The final version.
- *maestro:* The mocked version.

## Notes

For the sake of simplicity, we've omitted, in purpose, some important pieces in a production application like using ViewModels or adding Retrofit or Ktor for the network calls, as well as adding other types of tests.
