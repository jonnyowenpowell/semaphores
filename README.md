# Semaphores

This project uses the [Gradle](https://gradle.org/) build tool.

## Building

- On *nix systems or WSL, run `./gradlew build`.
- On Windows, run `gradlew.bat build`.

## Running

- On *nix systems or WSL, run `./gradlew run`.
- On Windows, run `gradlew.bat run`.

_Note_: This command automatically builds prior to running.

## Testing

To test the program for correctness, a Python script is included,
along with a Gradle task to run the script. You will need Python 3.6
or higher installed and available on your PATH as `python3`.

The test script first runs the Java program to collect the output,
and then performs checks on the output for correctness.

To run the test script:
- On *nix systems or WSL, run `./gradlew checkOutput`.
- On Windows, run `gradlew.bat checkOutput`.
