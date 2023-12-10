# RubikFX

A JavaFX-based desktop application for playing with a 3D model of the Rubik's Cube by rotating 
layers or the whole cube (as originally described [upstream](https://github.com/jperedadnr/RubikFX)
by José Pereda.)

![screenshot](RubikFX.jpg "RubixFX")

For a version that can build native images for desktop, Android and/or iOS platforms,
see [rubiks-cube](https://github.com/gluonhq/gluon-samples/tree/master/rubiks-cube).

---

This fork adds the Gradle and Maven wrappers and build scripts to compile and package
this desktop application.

The Gradle or Maven build script produces a `RubikFX` application, for running in standard JVM/JavaFX 11+.
The build scripts can also produce stand-alone native executables, using
[GraalVM native-image](https://www.graalvm.org/reference-manual/native-image/) utility,
for common platforms - Windows, Linux, and MacOS (untested).

## Gradle build tasks

### Building for standard JVM environment

To build and run the `RubikFX` application in standard JVM environment, execute the Gradle `run` task:

	gradlew run

By default JavaFX 3D will use GPU hardware acceleration for rendering. If hardware acceleration
is not available, i.e. the RubikFX application shows an empty window, the software acceleration
feature can be used instead (but slow!) by setting the `prism.forceGPU` system property to `true`:

	gradlew run -Dprism.forceGPU=true

To create an executable uber jar which includes all dependencies for current platform:

	gradlew uberJar

and the resulting `RubikFX-1.0.0-SNAPSHOT-no-deps-<platform>.jar` file should be created in `build/libs` directory,
and can be executed directly with the `java` command, e.g. in a Linux box:

	java -jar build/libs/RubikFX-1.0.0-SNAPSHOT-no-deps-linux.jar
	java -Dprism.forceGPU=true -jar build/libs/RubikFX-1.0.0-SNAPSHOT-no-deps-linux.jar

(or if building on a Windows machine:

	java -jar build\libs\RubikFX-1.0.0-SNAPSHOT-no-deps-win.jar
	java -Dprism.forceGPU=true -jar build\libs\RubikFX-1.0.0-SNAPSHOT-no-deps-win.jar

)

### Building native executable

RubikFX can be compiled to a stand-alone native executable, e.g. producing `RubikFX.exe` in Windows,
using the [GraalVM native-image](https://www.graalvm.org/reference-manual/native-image) utility.
The link shows how to set up GraalVM and its native-image utility for common platforms.
[Gluon](https://gluonhq.com/) also provides some setup [details](https://docs.gluonhq.com/#_platforms)
for GraalVM native image creation.

The Gradle build script uses [gluonfx-gradle-plugin](https://github.com/gluonhq/gluonfx-gradle-plugin)
from Gluon to build the native executable from Gradle with GraalVM.

Once the GraalVM prerequisites are set up for the current platform,
run the `nativeBuild` task to produce a native executable:

	gradlew nativeBuild

The `nativeBuild` task will take a while to finish, resulting in a native executable file at, e.g. in a Linux box:

	build/gluonfx/x86_64-linux/RubikFX

and can be run directly:

	./build/gluonfx/x86_64-linux/RubikFX
	./build/gluonfx/x86_64-linux/RubikFX -Dprism.forceGPU=true

(or if building on a Windows machine:

	build\gluonfx\x86_64-windows\RubikFX.exe
	build\gluonfx\x86_64-windows\RubikFX.exe -Dprism.forceGPU=true

)

## Maven build tasks

### Building for standard JVM environment

To build and run the `RubikFX` application in standard JVM environment, execute the Maven `javafx:run` task:

	mvnw javafx:run

By default JavaFX 3D will use GPU hardware acceleration for rendering. If hardware acceleration
is not available, i.e. the RubikFX application shows an empty window, the software acceleration
feature can be used instead (but slow!) by setting the `prism.forceGPU` system property to `true`:

	mvnw javafx:run -Dprism.forceGPU=true

To create an executable uber jar which includes all dependencies for the current platform:

	mvnw package

and the resulting `rubikfx-1.0.0-SNAPSHOT-no-deps-<platform>.jar` file should be created in
`target` directory, and can be executed directly with the `java` command, e.g. in a Linux box:

	java -jar target/rubikfx-1.0.0-SNAPSHOT-no-deps-linux.jar
	java -Dprism.forceGPU=true -jar target/rubikfx-1.0.0-SNAPSHOT-no-deps-linux.jar

(or if building on a Windows machine:

	java -jar target\rubikfx-1.0.0-SNAPSHOT-no-deps-win.jar
	java -Dprism.forceGPU=true -jar target\rubikfx-1.0.0-SNAPSHOT-no-deps-win.jar

)

### Building native executable

RubikFX can be compiled to a stand-alone native executable, e.g. producing `RubikFX.exe` in Windows,
using the [GraalVM native-image](https://www.graalvm.org/reference-manual/native-image) utility.
The link shows how to set up GraalVM and its native-image utility for common platforms.
[Gluon](https://gluonhq.com/) also provides some setup [details](https://docs.gluonhq.com/#_platforms)
for GraalVM native image creation.

The Maven build script uses [gluonfx-maven-plugin](https://github.com/gluonhq/gluonfx-maven-plugin)
from Gluon to build the native executable from Maven with GraalVM.

Once the GraalVM prerequisites are set up for the current platform,
run the `gluonfx:build` task to produce a native executable:

	mvnw gluonfx:build

The `gluonfx:build` task will take a while to finish, resulting in a native executable file at, e.g. in a Linux box:

	target/gluonfx/x86_64-linux/RubikFX

and can be run directly:

	./target/gluonfx/x86_64-linux/RubikFX
	./target/gluonfx/x86_64-linux/RubikFX -Dprism.forceGPU=true

(or if building on a Windows machine:

	target\gluonfx\x86_64-windows\RubikFX.exe
	target\gluonfx\x86_64-windows\RubikFX.exe -Dprism.forceGPU=true

)

## IDE support

The project can be imported as-is to any IDE such as Eclipse, IntelliJ IDEA, etc, which understands
a Gradle and/or Maven project structure.

