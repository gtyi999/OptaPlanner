************************************************************************
file with basedata            : mm43_.bas
initial value random generator: 17320
************************************************************************
projects                      :  1
jobs (incl. supersource/sink ):  12
horizon                       :  77
RESOURCES
  - renewable                 :  2   R
  - nonrenewable              :  2   N
  - doubly constrained        :  0   D
************************************************************************
PROJECT INFORMATION:
pronr.  #jobs rel.date duedate tardcost  MPM-Time
    1     10      0       15        0       15
************************************************************************
PRECEDENCE RELATIONS:
jobnr.    #modes  #successors   successors
   1        1          3           2   3   4
   2        3          1           5
   3        3          2           8  10
   4        3          3           5   6   7
   5        3          2           9  11
   6        3          1           9
   7        3          2           8  10
   8        3          1           9
   9        3          1          12
  10        3          1          12
  11        3          1          12
  12        1          0        
************************************************************************
REQUESTS/DURATIONS:
jobnr. mode duration  R 1  R 2  N 1  N 2
------------------------------------------------------------------------
  1      1     0       0    0    0    0
  2      1     5       7    7    0    5
         2     9       7    7    5    0
         3    10       7    4    0    4
  3      1     3       2   10    0    6
         2     4       2    9    0    5
         3     8       1    7   10    0
  4      1     4       8    4    6    0
         2     4       8    5    0    7
         3     5       6    4    0    3
  5      1     8       4    9    8    0
         2     9       3    8    5    0
         3    10       3    8    3    0
  6      1     2       9    6   10    0
         2     5       9    3    0    4
         3     7       8    2    0    4
  7      1     2       3    5    3    0
         2     7       2    3    3    0
         3     9       2    3    0    8
  8      1     1       3    8    0    7
         2     3       2    6    9    0
         3     7       2    5    0    7
  9      1     1       6    8    0   10
         2     1       5    9    0    6
         3     3       4    7    4    0
 10      1     7       3    7    3    0
         2     8       2    5    1    0
         3     8       1    6    3    0
 11      1     2       2    6    4    0
         2     4       2    4    4    0
         3    10       1    3    3    0
 12      1     0       0    0    0    0
************************************************************************
RESOURCEAVAILABILITIES:
  R 1  R 2  N 1  N 2
   14   16   45   33
************************************************************************
