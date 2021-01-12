# RubikFX

A JavaFX-based application for playing with a 3D model of the Rubik's Cube by rotating 
layers or the whole cube (as originally described [here](https://github.com/jperedadnr/RubikFX) by José Pereda.)

This fork adds the Gradle wrapper and build script to build and package this application.

The Gradle build script produces a stand-alone `RubikFX` application, for Java/JavaFX 11+.

## Gradle tasks

To build and run the `RubikFX` application, execute the Gradle `run` command:

	gradlew run

To create an "exploded" distribution:

	gradlew installDist

and the *platform-specific* distribution should be available in `build/install` directory. The `RubikFX` application can then be executed on the command line:

	cd build/install/RubikFX-linux
	./RubikFX

(or if building on a Windows machine:

	cd build\install\RubikFX-win
	RubikFX.bat

)

To create a zip distribution:

	gradlew distZip

and the *platform-specific* zip distribution, e.g. `RubikFX-win.zip`, should be available in `build/distributions` directory. This file is the zipped version of the above "exploded" distribution.

To create an executable fat jar which includes all dependencies for all supported OS'es:

	gradlew fatJar

and the resulting `RubikFX-no-deps.jar` file should be created in `build/libs` and can be executed directly with the java command:

	java -jar build/libs/RubikFX-no-deps.jar

(or if building on a Windows machine:

	java -jar build\libs\RubikFX-no-deps.jar

)

This fat jar should be portable across all three supported OS'es (Windows, Mac and Linux.)

## IDE support

The project can be imported as-is to any IDE such as Eclipse, IntelliJ IDEA, etc, which understands a Gradle project structure.

