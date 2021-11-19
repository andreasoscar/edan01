% Use this editor as a MiniZinc scratch book
include "hamburger.mzn";
var 1..5: numbeef;
var 1..5: numbun;
var 1..5: numchees;
var 1..5: numonions;
var 1..5: numpickles;
var 1..5: numlettuce;
var 1..5: numketchup;
var 1..5: numtomato;
var 1..10000000000: costTotal;

array[1..8] of var int: fd = [numbeef, numbun, numchees, numonions, numpickles, numlettuce, numketchup, numtomato];
constraint 
  (sodium[beef]*numbeef + sodium[bun]*numbun + sodium[chees]*numchees + sodium[onions]*numonions
  + sodium[pickles]*numpickles + sodium[lettuce]*numlettuce + sodium[ketchup]*numketchup
  + sodium[tomato]*numtomato) < 3000;
constraint
  (fat[beef]*numbeef + fat[bun]*numbun + fat[chees]*numchees + fat[onions]*numonions
  + fat[pickles]*numpickles + fat[lettuce]*numlettuce + fat[ketchup]*numketchup
  + fat[tomato]*numtomato) < 150;
constraint
    (calories[beef]*numbeef + calories[bun]*numbun + calories[chees]*numchees + calories[onions]*numonions
  + calories[pickles]*numpickles + calories[lettuce]*numlettuce + calories[ketchup]*numketchup
  + calories[tomato]*numtomato) < 3000;
constraint
  numketchup = numlettuce;
constraint
  numpickles = numtomato;
costTotal = (cost[beef]*numbeef + cost[bun]*numbun + cost[chees]*numchees + cost[onions]*numonions
  + cost[pickles]*numpickles + cost[lettuce]*numlettuce + cost[ketchup]*numketchup
  + cost[tomato]*numtomato);
%solve maximize costTotal;
%output[show(costTotal)];
solve :: int_search(fd, input_order, indomain_max, complete) satisfy;
  