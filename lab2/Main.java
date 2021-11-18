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
        int[] fromvec = new int[n_dests_1 + from_1.length];
        int[] tovec = new int[n_dests_1 + to_1.length];
        int[] costvec = new int[n_dests_1 + cost_1.length];

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
            if(i >= from_1.length){
                costvec[i] = 0;
            } else {
                costvec[i] = cost_1[i];
            }
        }

        IntVar[] verticiesFD = new IntVar[n_edges_1];

        for(int i = 0; i < n_edges_1; i++){
            verticiesFD[i] = new IntVar(store, "v"+i, 0, n_edges_1);
        }

       // Constraint ctr = new Subcircuit(verticiesFD);

        //store.impose(ctr);

        //System.out.println(store);

         
        IntVar a = new IntVar(store, "a", 1, 3); 
        IntVar b = new IntVar(store, "b", 1, 3); 
        IntVar c = new IntVar(store, "c", 1, 3); 
        IntVar[] v = {a, b, c};
        Constraint ctr = new Subcircuit(v); store.impose(ctr);
 
        System.out.println(store);

        Search<IntVar> search =newDepthFirstSearch<IntVar>();
        SelectChoicePoint<IntVar> select =newInputOrderSelect<IntVar>(store, v,newIndomainMin<IntVar>());
        boolean result = search.labeling(store, select);
        if( result ) { 
            System.out.println("Solution: " + v[0]+", "+v[1] +", "+v[2] +", "+v[3]); 
        }
        else{ 
            System.out.println("***No"); 
        }
 
 
        
    }
}