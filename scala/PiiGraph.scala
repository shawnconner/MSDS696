import org.apache.spark._
import org.apache.spark.graphx._
import org.apache.spark.sql.SparkSession
import org.apache.spark.rdd.RDD

object PiiGraph {
  def main(args: Array[String]): Unit = {
    //val sc = new SparkContext(new SparkConf().setMaster("local[2]").setAppName("Reference File Build - Build PII"))
    val sc = new SparkContext(new SparkConf().setAppName("Reference File Build - Build PII"))

    val individuals: RDD[(VertexId, String)] = sc.textFile("/data/Distinct_ids.txt").map{t=>
      val parts = t.split("\\|")
      (parts(1).toLong,parts(0))
    }

    val links : RDD[Edge[Double]] =  sc.textFile("/data/RANDOM_FOREST_OUT.txt").map { t =>
      val parts = t.split(",")
      Edge(parts(0).toLong,parts(1).toLong,parts(2).toDouble)
    }
    val defaultIndividual = ("00000000000000")

    val graph = Graph(individuals,links,defaultIndividual)

    val output = graph.connectedComponents.vertices.map{ case(id,smallestId) => (smallestId,id)}
      .groupByKey().map{t=> Seq(t._1,t._2.mkString(",")).mkString("|")}

    output.saveAsTextFile("/data/RAN_FINAL_CLUSTERS.txt")

  }
}
