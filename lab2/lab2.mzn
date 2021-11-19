include "data_1.dzn";
int: graph_size;
int: start;
int: n_dests;
array[1..n_dests] of int: dest;
int: n_edges;
array[1..n_edges] of int: from;
array[1..n_edges] of int: to;
array[1..n_edges] of int: cost;


array[1..n_edges, 1..2] of int: tester = array2d(1..n_edges, 1..2, [if (i mod 2)=0 then from[i div 2 + 1] else to[i div 2 + 1] endif | i in 0..2*n_edges-1]);
array[1..graph_size] of int: balance = [if i = 1 then n_dests elseif     | i in 0..graph_size];
balance[1]


