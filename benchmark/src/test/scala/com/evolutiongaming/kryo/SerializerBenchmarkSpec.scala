package com.evolutiongaming.kryo

import org.scalatest.{Matchers, WordSpec}

class SerializerBenchmarkSpec extends WordSpec with Matchers {
  val benchmark = new SerializerBenchmark

  "SerializerBenchmark" should {
    s"write and than read to the same value" in {
      benchmark.writeThanReadAnyRefs() shouldEqual benchmark.anyRefsObj
      benchmark.writeThanReadIterables() shouldEqual benchmark.iterablesObj
      benchmark.writeThanReadMaps() shouldEqual benchmark.mapsObj
      benchmark.writeThanReadMutableMaps() shouldEqual benchmark.mutableMapsObj
      benchmark.writeThanReadIntAndLongMaps() shouldEqual benchmark.intAndLongMapsObj
      benchmark.writeThanReadBitSets() shouldEqual benchmark.bitSetsObj
      benchmark.writeThanReadPrimitives() shouldEqual benchmark.primitivesObj
    }
  }
}
