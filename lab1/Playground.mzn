% Use this editor as a MiniZinc scratch book
%include "data_1.dzn";
include "globals.mzn";
include "data_2.dzn";
int: n;
int: n_prefs;
array[1..n_prefs, 1..2] of int: prefs;
array[1..n] of var 1..n: poslist;
array[1..n_prefs] of var int: distlist;

constraint all_different (poslist);
constraint forall(i in 1..n_prefs) (distlist[i] = abs(poslist[prefs[i,1]] - poslist[prefs[i,2]]));

%var int: numbers = count(distlist,1);
var int: maxvalue = max(distlist);
solve minimize maxvalue;
%output[show(maxvalue)];
%solve maximize numbers;
%output[show(numbers)];



  