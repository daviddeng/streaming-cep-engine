/*
 * Copyright 2014 Stratio.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.stratio.streaming.unit

import org.scalatest._
import com.stratio.streaming.utils.StreamsParser
import scala.collection.JavaConversions._
import com.stratio.streaming.commons.constants.ColumnType

class StreamsParserUnitTests
  extends FunSpec
  with GivenWhenThen
  with ShouldMatchers {

  describe("The Stream Parser") {
    it("should get the list of streams from the json") {
      Given("the json response")
      val jsonResponse = """{"count":3,"timestamp":1396450032105,"streams":[{"streamName":"stratio_stats_base","columns":[{"column":"operation","type":"STRING"},{"column":"streamName","type":"STRING"}],"queries":[],"userDefined":false},{"streamName":"stratio_stats_global_by_operation","columns":[{"column":"operation","type":"STRING"},{"column":"streamName","type":"STRING"},{"column":"index","type":"INT"},{"column":"data","type":"LONG"}],"queries":[],"userDefined":false},{"streamName":"teststream","columns":[{"column":"data","type":"DOUBLE"},{"column":"name","type":"STRING"}],"queries":[],"userDefined":true}]}"""
      When("we parse the json")
      val streamsList = StreamsParser.parse(jsonResponse)
      Then("we should get the list of streams")
      streamsList.size should be(3)
      val firstStream = streamsList(0)
      firstStream.getStreamName should be("stratio_stats_base")
      val firstStreamColumns = firstStream.getColumns.toList
      val firstStreamfirstColumn = firstStreamColumns(0)
      firstStreamfirstColumn.getColumn should be ("operation")
      firstStreamfirstColumn.getType should be (ColumnType.STRING)
    }
  }
}