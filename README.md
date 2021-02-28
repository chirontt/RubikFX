# RubikFX

A JavaFX-based application for playing with a 3D model of the Rubik's Cube by rotating 
layers or the whole cube (as originally described [here](https://github.com/jperedadnr/RubikFX) by José Pereda.)

This fork adds the Gradle wrapper and build script to build and package this application.

The Gradle build script produces a `RubikFX` application, for running in standard JVM/JavaFX 11+.
The build script can also produce stand-alone native executables, using [GraalVM native-image](https://www.graalvm.org/reference-manual/native-image/) utility,
for common platforms (Windows, Mac and Linux.)

## Gradle tasks

### Building for standard JVM environment

To build and run the `RubikFX` application in standard JVM environment, execute the Gradle `run` command:

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

This `RubikFX-no-deps.jar` file should be portable across all three supported OS'es (Windows, Mac and Linux.)

### Building native executable

RubikFX can be compiled to a stand-alone native executable, e.g. producing `RubikFX.exe` in Windows,
using the [GraalVM native-image](https://www.graalvm.org/reference-manual/native-image) utility.
The link shows how to set up GraalVM and its native-image utility for common platforms.
[Gluon](https://gluonhq.com/) also provides some setup [details](https://docs.gluonhq.com/#_platforms) for GraalVM native image creation.

The Gradle build script uses [client-gradle-plugin](https://github.com/gluonhq/client-gradle-plugin)
from Gluon to build the native executable from Gradle with GraalVM.
GraalVM native-image utility will use the configuration files in `src/META-INF/native-image` folder
to assist in native-image generation.

Once the GraalVM prerequisites are set up for the current platform,
run the `nativeBuild` task to produce a native executable:

	gradlew nativeBuild

The `nativeBuild` task will take a while to finish, resulting in a native executable file at:

	build/client/x86_64-linux/RubikFX

(or if building on a Windows machine:

	build\client\x86_64-windows\RubikFX.exe

)

The resulting `RubikFX` native executable can be further reduced in size via compression using the [UPX](https://upx.github.io) utility, as described [here](https://medium.com/graalvm/compressed-graalvm-native-images-4d233766a214).

As an example, the resulting `RubikFX.exe` Windows executable produced here is normally 66MB in size,
but is compressed to 17MB with the UPX command: `upx --best RubikFX.exe`

## IDE support

The project can be imported as-is to any IDE such as Eclipse, IntelliJ IDEA, etc, which understands a Gradle project structure.

