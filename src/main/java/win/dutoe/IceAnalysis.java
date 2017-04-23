/*
 * Powered by blueyi
 */

package win.dutoe;

import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.SparkSession;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public final class IceAnalysis {
  private static final Pattern SPACE = Pattern.compile(" ");
  private native void helloWorld();
  static {
      System.loadLibrary("helloWorld");
  }
  public static void main(String[] args) throws Exception {

    /*
    if (args.length < 1) {
      System.err.println("Usage: JavaWordCount <file>");
      System.exit(1);
    }
    */
    String inFile = "hdfs://wyl-node1:9000/test/img_test.txt";

    SparkSession spark = SparkSession.builder()
//            .master("spark://wyl-node1:7077")  //yarn mode must be disabled
            .master("local")
            .appName("IceAnalysis")
//            .config("deploy-mode", "cluster")
            .getOrCreate();

//    JavaRDD<String> lines = spark.read().textFile(args[0]).javaRDD();
    JavaRDD<String> lines = spark.read().textFile(inFile).javaRDD();

    System.out.println("-----------DEBUG-1-------------");
    new IceAnalysis().helloWorld();
    lines.foreach(new VoidFunction<String>() {
        @Override
        public void call(String s) throws Exception {
            System.out.println(s.replace("board", "blueyi"));
        }
    });
/*
      List<String> line = lines.collect();
      for (String str : line)
          System.out.println(str);
*/
    System.out.println("------------DEBUG-2------------");

    /*
    JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {
      @Override
      public Iterator<String> call(String s) {
        return Arrays.asList(SPACE.split(s)).iterator();
      }
    });

    JavaPairRDD<String, Integer> ones = words.mapToPair(
      new PairFunction<String, String, Integer>() {
        @Override
        public Tuple2<String, Integer> call(String s) {
          return new Tuple2<>(s, 1);
        }
      });

    JavaPairRDD<String, Integer> counts = ones.reduceByKey(
      new Function2<Integer, Integer, Integer>() {
        @Override
        public Integer call(Integer i1, Integer i2) {
          return i1 + i2;
        }
      });


    List<Tuple2<String, Integer>> output = counts.collect();
    for (Tuple2<?,?> tuple : output) {
      System.out.println(tuple._1() + ": " + tuple._2());
    }
    */
    spark.stop();
  }
}
