# Kryo Macros [![Build Status](https://travis-ci.org/evolution-gaming/kryo-macros.svg)](https://travis-ci.org/evolution-gaming/kryo-macros) [![license](http://img.shields.io/:license-Apache%202-green.svg)](http://www.apache.org/licenses/LICENSE-2.0.txt) [ ![version](https://api.bintray.com/packages/evolutiongaming/maven/kryo-macros/images/download.svg) ](https://bintray.com/evolutiongaming/maven/kryo-macros/_latestVersion)

Scala macros that generate `com.esotericsoftware.kryo.Serializer` implementations in compile time, based on compile time reflection.

## Features and limitations

- On top level only case classes are supported
- Fields of case classes can be other case classes, Scala collections, options, primitive or `AnyVal` types & classes, Scala enums, standard types & classes: `String`, `Either`, `BigDecimal`, `java.time.Instant`, `scala.concurrent.duration.FiniteDuration`, `org.joda.time.DateTime`
- Fields can be annotated as transient or just be not defined in constructor to avoid parsing and serializing 
- For nested structures need to generate serializers for all case classes 
- Implicitly defined mapping helpers are supported for ADT structures, simple alternative mappings, etc.
- Manual serializers can be used in generated code when defined as implicits

## How to use

Add the following resolver
```sbt
resolvers += Resolver.bintrayRepo("evolutiongaming", "maven")
```

Add the library to your dependencies list
```sbt
libraryDependencies += "com.evolutiongaming" %% "kryo-macros" % "1.1.5"
```

Generate some serializers for your case classes
```scala
import com.evolutiongaming.kryo.Serializer

case class Player(name: String)

val serializer = Serializer.make[Player]
 ```

That's it! You have generated a `com.esotericsoftware.kryo.Serializer` implementation for your Player.
You must know what to do with it if you are here :)

To see generated code just add the following line to your sbt build file 
```sbt
scalacOptions += "-Xmacro-settings:print-serializers"
```

For more examples, please, check out 
[SerializerMacroSpec](https://github.com/evolution-gaming/kryo-macros/tree/master/macros/src/test/scala/com/evolutiongaming/kryo/SerializerMacroSpec.scala)

## How to develop

Run tests and check coverage for both Scala versions
```sh
sbt clean +coverage +test +coverageReport
```

Run benchmarks
```sh
sbt -no-colors clean 'benchmark/jmh:run -prof gc .*SerializerBenchmark.*' >results.txt
```
