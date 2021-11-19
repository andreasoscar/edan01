import org.jacop.core.*; 
import org.jacop.constraints.*; 
import org.jacop.search.*; 

 
public class Main { 
    
    static Main m = new Main (); 
 
    public static void main (String[] args) { 
        int graph_size_1 = 6;
        int start_1 = 1;
        int n_dests_1 = 1;
        int[] dest_1 = {6};
        int n_edges_1 = 7;
        int[] from_1 = {1,1,2,2,3,4,4};
        int[] to_1 = {2,3,3,4,5,5,6};
        int[] cost_1 = {4,2,5,10,3,4,11};

        Store store = new Store();  // define FD store 
        int size = from_1.length; 
        IntVar[][] destWays = new IntVar[n_dests_1][graph_size_1]; 
        int[] fromvec = new int[n_dests_1 + from_1.length];
        int[] tovec = new int[n_dests_1 + to_1.length];
        int[] costvec = new int[n_dests_1 + cost_1.length];
        int[][][] costEdges = new int[n_dests_1][graph_size_1][graph_size_1];
        
        //creating edges from dest node to the start node
        for(int i = 0; i < n_dests_1 + from_1.length; i++) {
            if(i < from_1.length){
                fromvec[i] = from_1[i];
            } else {
                fromvec[i] = dest_1[i - from_1.length];
            }
        }
        for(int i = 0; i < n_dests_1 + to_1.length; i++)
        {
            if(i < to_1.length){
                tovec[i] = to_1[i];
            } else {
                tovec[i] = start_1;
            }
        }

        for(int i = 0; i < n_dests_1 + from_1.length; i++){
            if(i < from_1.length){
                costvec[i] = cost_1[i];
            } else {
                costvec[i] = 0;
            }
        }

        for(int i = 0; i < n_dests_1; i++){
            for(int j = 0; j < graph_size_1; j++)
                destWays[i][j] = new IntVar(store, "v"+(j+1));
        }



    
        for(int i = 0; i < n_dests_1; i++){
            for(int j = 0; j < fromvec.length; j++){
                int from = fromvec[j];
                int to = tovec[j];
                destWays[i][from-1].addDom(to,to);
                destWays[i][to-1].addDom(from,from);
            }
        }

        for(int i = 0; i  < n_dests_1; i++){
            for(int j = 0; j < graph_size_1; j++){
                for(int k = 0; k < graph_size_1; k++){
                    costEdges[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        for(int i = 0; i  < n_dests_1; i++){
            for(int j = 0; j < fromvec.length; j++){
                int from = fromvec[j];
                for(int k = 0; k < fromvec.length; k++){
                    int to = tovec[k];
                    costEdges[i][from-1][to-1] = costvec[j];
                    costEdges[i][to-1][from-1] = costvec[j];
                }
            }
        }

        for(int i = 0; i  < n_dests_1; i++){
            for(int j = 0; j < graph_size_1; j++){
                for(int k = 0; k < graph_size_1; k++){
                    System.out.println(costEdges[i][j][k]);
                }
            }
        }

        for(int i = 0; i < n_dests_1; i++){
            Constraint ctr = new Subcircuit(destWays[i]);
            store.impose(ctr);
        }


        for(int i = 0; i < n_dests_1; i++){
            for(int j = 0; j < graph_size_1; j++){
                for(int k = 0; k < graph_size_1; k++){
                    System.out.println(costEdges[i][j][k]);
                }
            }
        }
       
        for(int i = 0; i < n_dests_1; i++){
            Constraint ctr = new Subcircuit(destWays[i]);
            store.impose(ctr);
        }

        
    }
}