import clojure.lang.IFn;
import javafx.scene.control.Toggle;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

public class MainTopology {
    public static void main(String[] args) throws Exception {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("IntegerSpout",new IntegerSpout());
        builder.setBolt("MultiplierBoult", new MultiplierBolt()).shuffleGrouping("IntegerSpout");


        Config config   = new Config();
        //config.setDebug(true);

        LocalCluster cluster =  new LocalCluster();
        try
        {
            cluster.submitTopology("HelloTopology",config,builder.createTopology());
            Thread.sleep(10000);
        }
        catch (Exception e){
            e.printStackTrace();

        }
        finally {
            cluster.shutdown();
        }


    }
}


