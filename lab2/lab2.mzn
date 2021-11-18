include "data_1.dzn";
int: graph_size;
int: start;
int: n_dests;
array[1..n_dests] of var int: dest;
int: n_edges;
array[1..n_edges] of var int: from;
array[1..n_edges] of var int: to;
array[1..n_edges] of var int: cost;

subcircuit(from);
