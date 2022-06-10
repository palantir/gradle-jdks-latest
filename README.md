# gradle-jdks-latest

Configure [gradle-jdks](https://github.com/palantir/gradle-jdks) with the latest JDKs.

## Background

This primarily exists for Palantir use, to enforce consistency in JDK versions for our library projects that do not publish services.

The repo is regularly kept up to date with the latest versions of JDKs for the Java major versions Palantir cares about.

[**The JDKs being used by this plugin can be seen here.**](https://github.com/palantir/gradle-jdks-latest/blob/develop/gradle-jdks-latest/src/main/resources/latestjdks/latest-jdks.json)

## Disclaimer

You may use this project, but should not except any level of support or that newer plugin versions maintain support for existing Java major versions or keep using the same JDK distributions. 

## Usage

In the root `build.gradle`, either use the new plugins syntax:

```gradle
plugins {
    id 'com.palantir.jdks.latest' version '<latest version>'
}
```

or the old style buildscript syntax:

```gradle
buildscript {
    repositories {
        mavenCentral()
    }
    
    dependencies {
        classpath 'com.palantir.gradle.jdkslatest:gradle-jdks-latest:<latest-version>'
    }
}

apply plugin: 'com.palantir.jdks.latest'
```

This plugin will apply the `com.palantir.jdks` plugins for you, [see those docs](https://github.com/palantir/gradle-jdks#usage) for more details.

You will now not need to configure the JDKs distribution/version for [the java major versions this plugin provides](https://github.com/palantir/gradle-jdks-latest/blob/develop/gradle-jdks-latest/src/main/resources/latestjdks/latest-jdks.json):

```diff
-jdk(11) {
-    distribution = 'azul-zulu'
-    jdkVersion = '11.54.25-11.0.14.1'
-}
```
